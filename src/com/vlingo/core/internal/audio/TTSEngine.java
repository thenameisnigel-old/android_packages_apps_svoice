package com.vlingo.core.internal.audio;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.EngineInfo;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;
import com.vlingo.core.internal.CoreAdapterRegistrar;
import com.vlingo.core.internal.CoreAdapterRegistrar.AdapterList;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.DeviceWorkarounds;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.tts.VLTextToSpeech;
import com.vlingo.sdk.tts.VLTextToSpeechErrors;
import com.vlingo.sdk.tts.VLTextToSpeechListener;
import com.vlingo.sdk.tts.VLTextToSpeechRequest;
import com.vlingo.sdk.tts.VLTextToSpeechRequest.Builder;
import com.vlingo.sdk.tts.VLTextToSpeechRequest.SpeechRate;
import com.vlingo.sdk.tts.VLTextToSpeechRequest.Type;
import com.vlingo.sdk.tts.VLTextToSpeechRequest.VoiceType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class TTSEngine
{
  private static final boolean ALLOW_LOCAL_TTS = true;
  private static final boolean ALLOW_NETWORK_TTS = true;
  private static final int LOCAL_TTS_RENDER_TIMEOUT_MS = 30000;
  private static final float LOCAL_TTS_SPEECH_RATE_NORMAL = 1.0F;
  private static final String LOG_TAG = "TTSEngine";
  private static final String SVOX_REQUIRED_ENGINE_NAME = "SVox";
  private static final int THREAD_WAIT_TIME_MS = 250;
  public static final String TTS_ENGINE_PACKAGE_NAME_GOOGLE = "com.google.android.tts";
  public static final String TTS_ENGINE_PACKAGE_NAME_SAMSUNG = "com.samsung.SMT";
  private static final VLTextToSpeechRequest.SpeechRate TTS_RATE;
  private static final VLTextToSpeechRequest.Type TTS_TYPE;
  private static final VLTextToSpeechRequest.VoiceType TTS_VOICE;
  private static TTSEngine instance;
  private static Object synthMutex = new Object();
  private final Context context;
  private TTSLocalEngine localTTSEngine = null;
  private Object localTTSEngineLockObject;

  static
  {
    instance = null;
    TTS_VOICE = VLTextToSpeechRequest.VoiceType.FEMALE;
    TTS_TYPE = VLTextToSpeechRequest.Type.PLAIN;
    TTS_RATE = VLTextToSpeechRequest.SpeechRate.NORMAL;
  }

  private TTSEngine(Context paramContext)
  {
    this.context = paramContext;
    this.localTTSEngineLockObject = new Object();
  }

  private void destroyLocalTTSEngine()
  {
    if (this.localTTSEngine != null)
    {
      this.localTTSEngine.stop();
      this.localTTSEngine.shutdown();
      this.localTTSEngine = null;
    }
  }

  private boolean engineExists(TTSLocalEngine paramTTSLocalEngine, String paramString)
  {
    Iterator localIterator = paramTTSLocalEngine.getEngines().iterator();
    do
      if (!localIterator.hasNext())
        break;
    while (!((TextToSpeech.EngineInfo)localIterator.next()).name.equals(paramString));
    for (int i = 1; ; i = 0)
      return i;
  }

  private String fetchLocalTTS(Context paramContext, TTSRequest paramTTSRequest)
  {
    Object localObject1 = TTSCache.getCachedTTSPath(paramTTSRequest, "local_tts", paramContext);
    if (localObject1 == null)
    {
      TTSLocalEngine localTTSLocalEngine = getLocalTTSEngine();
      if (localTTSLocalEngine != null)
        synchronized (synthMutex)
        {
          String str1 = paramTTSRequest.getTextToSpeak();
          String str2 = TTSCache.getTempFile(paramTTSRequest, "local_tts", paramContext).getAbsolutePath();
          LocalTTSUttListener localLocalTTSUttListener = new LocalTTSUttListener();
          localTTSLocalEngine.setOnUtteranceCompletedListener(localLocalTTSUttListener);
          HashMap localHashMap = new HashMap();
          localHashMap.put("utteranceId", str1);
          if (localTTSLocalEngine.synthesizeToFile(str1, localHashMap, str2) == 0)
          {
            long l1 = 30000L + System.currentTimeMillis();
            while (!localLocalTTSUttListener.isComplete())
            {
              long l2 = System.currentTimeMillis();
              if (l2 >= l1)
                break;
              try
              {
                Thread.sleep(250L);
              }
              catch (InterruptedException localInterruptedException)
              {
              }
            }
            if (!localLocalTTSUttListener.isComplete())
              break label177;
            localObject1 = str2;
            if (paramTTSRequest.isCacheable)
              localObject1 = TTSCache.cacheTTSRequest(paramTTSRequest, (String)localObject1, "local_tts", paramContext);
          }
          break label192;
          label177: destroyLocalTTSEngine();
        }
    }
    label192: return (String)localObject1;
  }

  private static String fetchNetworkTTS(Context paramContext, TTSRequest paramTTSRequest)
  {
    String str = TTSCache.getCachedTTSPath(paramTTSRequest, "network_tts", paramContext);
    if (str == null)
    {
      FetchTTSTask localFetchTTSTask = new FetchTTSTask(paramTTSRequest, paramContext);
      synchronized (synthMutex)
      {
        new Thread(localFetchTTSTask).start();
        long l1 = System.currentTimeMillis() + Settings.getInt("network_tts_timeout", 5000);
        while (!localFetchTTSTask.isComplete())
        {
          long l2 = System.currentTimeMillis();
          if (l2 >= l1)
            break;
          try
          {
            Thread.sleep(250L);
          }
          catch (InterruptedException localInterruptedException)
          {
          }
        }
        if (localFetchTTSTask.isComplete())
        {
          if (localFetchTTSTask.isSuccess())
          {
            str = localFetchTTSTask.audioFilePath;
            if (paramTTSRequest.isCacheable)
              str = TTSCache.cacheTTSRequest(paramTTSRequest, str, "network_tts", paramContext);
          }
        }
        else
          VLSdk.getInstance().getTextToSpeech().cancel();
      }
    }
    return str;
  }

  private static VLTextToSpeechRequest getDefaultSDKRequest(String paramString)
  {
    VLTextToSpeechRequest.Builder localBuilder = new VLTextToSpeechRequest.Builder();
    localBuilder.text(paramString);
    localBuilder.voice(TTS_VOICE);
    localBuilder.type(TTS_TYPE);
    localBuilder.speechRate(TTS_RATE);
    localBuilder.language(Settings.getString("language", "en-US"));
    return localBuilder.build();
  }

  public static TTSEngine getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new TTSEngine(paramContext);
    return instance;
  }

  private TTSLocalEngine getLocalTTSEngine()
  {
    Locale localLocale = Settings.getCurrentLocale();
    boolean bool1 = Settings.getBoolean("tts_local_required_engine", false);
    boolean bool2 = Settings.getBoolean("tts_local_ignore_use_speech_rate", false);
    String str1 = Settings.getString("tts_local_tts_fallback_engine", "com.google.android.tts");
    LocalTTSInitListener localLocalTTSInitListener1;
    String str2;
    TTSLocalEngine localTTSLocalEngine;
    Class localClass;
    int i;
    synchronized (this.localTTSEngineLockObject)
    {
      if (this.localTTSEngine == null)
      {
        localLocalTTSInitListener1 = new LocalTTSInitListener();
        str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_tts_local_required_engine_name);
        if ((bool1) && (!DeviceWorkarounds.shouldIgnoreRequiredSamsungTTSEngine()))
        {
          if (str2 == null)
          {
            Log.e("VLG_TTSEngine", "no required package name found");
            localTTSLocalEngine = null;
            break label539;
          }
          if ("SVox".equals(str2))
          {
            localClass = CoreAdapterRegistrar.get(CoreAdapterRegistrar.AdapterList.ExternalTTSEngine);
            i = -1;
            if (localClass == null);
          }
        }
      }
    }
    try
    {
      this.localTTSEngine = ((TTSLocalEngine)localClass.newInstance());
      int j = this.localTTSEngine.setLanguage(localLocale);
      i = j;
      label156: if (i >= 0)
      {
        localTTSLocalEngine = this.localTTSEngine;
        monitorexit;
        break label539;
        localObject2 = finally;
        monitorexit;
        throw localObject2;
      }
      else
      {
        bool1 = false;
        Log.e("TTSEngine", "setLanguage failed, returned " + i);
        str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_tts_local_fallback_engine_name);
        if (str2 != null)
        {
          this.localTTSEngine = new TTSLocalDefaultEngine(new TextToSpeech(this.context, localLocalTTSInitListener1, str2));
          if ((initializeTextToSpeech(this.localTTSEngine, localLocalTTSInitListener1, bool2, localLocale)) && (engineExists(this.localTTSEngine, str2)))
          {
            localTTSLocalEngine = this.localTTSEngine;
            monitorexit;
            break label539;
          }
        }
        if ((bool1) && (!DeviceWorkarounds.shouldIgnoreRequiredSamsungTTSEngine()))
        {
          this.localTTSEngine = new TTSLocalDefaultEngine(new TextToSpeech(this.context, localLocalTTSInitListener1, str2));
          if (!initializeTextToSpeech(this.localTTSEngine, localLocalTTSInitListener1, bool2, localLocale))
          {
            destroyLocalTTSEngine();
            monitorexit;
            localTTSLocalEngine = null;
            break label539;
          }
          if (!engineExists(this.localTTSEngine, str2))
          {
            destroyLocalTTSEngine();
            monitorexit;
            localTTSLocalEngine = null;
            break label539;
          }
          if ((bool1) && (!DeviceWorkarounds.shouldIgnoreRequiredSamsungTTSEngine()))
          {
            localTTSLocalEngine = this.localTTSEngine;
            monitorexit;
            break label539;
          }
        }
        LocalTTSInitListener localLocalTTSInitListener2 = new LocalTTSInitListener();
        this.localTTSEngine = new TTSLocalDefaultEngine(new TextToSpeech(this.context, localLocalTTSInitListener2));
        if (!initializeTextToSpeech(this.localTTSEngine, localLocalTTSInitListener2, bool2, localLocale))
          destroyLocalTTSEngine();
        if ((this.localTTSEngine == null) && (str1 != null))
        {
          LocalTTSInitListener localLocalTTSInitListener3 = new LocalTTSInitListener();
          this.localTTSEngine = new TTSLocalDefaultEngine(new TextToSpeech(this.context, localLocalTTSInitListener3, str1));
          if (initializeTextToSpeech(this.localTTSEngine, localLocalTTSInitListener3, bool2, localLocale));
        }
        monitorexit;
        localTTSLocalEngine = this.localTTSEngine;
      }
    }
    catch (Exception localException)
    {
      break label156;
    }
    label539: return localTTSLocalEngine;
  }

  private boolean initializeTextToSpeech(TTSLocalEngine paramTTSLocalEngine, LocalTTSInitListener paramLocalTTSInitListener, boolean paramBoolean, Locale paramLocale)
  {
    int i = 0;
    while (!paramLocalTTSInitListener.isInitialized())
      try
      {
        Thread.sleep(250L);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    if (!paramLocalTTSInitListener.isSuccess());
    while (true)
    {
      return i;
      if ((this.localTTSEngine.getLanguage() == null) && (this.localTTSEngine.setLanguage(paramLocale) < 0))
        continue;
      if (paramBoolean)
        this.localTTSEngine.setSpeechRate(1.0F);
      i = 1;
    }
  }

  public void destroy()
  {
    synchronized (this.localTTSEngineLockObject)
    {
      destroyLocalTTSEngine();
      return;
    }
  }

  protected void finalize()
    throws Throwable
  {
    super.finalize();
  }

  public String getFilePathForTTSRequest(Context paramContext, TTSRequest paramTTSRequest)
  {
    String str = null;
    if ((0 == 0) && (paramTTSRequest.allowNetworkTTS) && (Settings.getBoolean("use_network_tts", true)))
      str = fetchNetworkTTS(paramContext, paramTTSRequest);
    if ((str == null) && (paramTTSRequest.allowLocalTTS))
      str = fetchLocalTTS(paramContext, paramTTSRequest);
    return str;
  }

  public void onSystemLanguageChanged()
  {
    synchronized (this.localTTSEngineLockObject)
    {
      destroyLocalTTSEngine();
      return;
    }
  }

  private static class FetchTTSTask
    implements Runnable, VLTextToSpeechListener
  {
    String audioFilePath = null;
    final Context context;
    volatile boolean isComplete = false;
    volatile boolean isSuccess = false;
    final TTSRequest request;

    public FetchTTSTask(TTSRequest paramTTSRequest, Context paramContext)
    {
      this.request = paramTTSRequest;
      this.context = paramContext;
    }

    boolean isComplete()
    {
      monitorenter;
      try
      {
        boolean bool = this.isComplete;
        monitorexit;
        return bool;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    boolean isSuccess()
    {
      monitorenter;
      try
      {
        boolean bool = this.isSuccess;
        monitorexit;
        return bool;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    public void onError(VLTextToSpeechErrors paramVLTextToSpeechErrors, String paramString)
    {
      this.isComplete = true;
      this.isSuccess = false;
    }

    public void onSuccess()
    {
      this.isComplete = true;
      this.isSuccess = true;
    }

    public void run()
    {
      VLTextToSpeechRequest localVLTextToSpeechRequest = TTSEngine.access$000(this.request.getTextToSpeak());
      try
      {
        VLTextToSpeech localVLTextToSpeech = VLSdk.getInstance().getTextToSpeech();
        try
        {
          File localFile = TTSCache.getTempFile(this.request, "network_tts", this.context);
          if ((localFile.exists()) || ((localFile.createNewFile()) && ((localFile.canWrite()) || (localFile.setWritable(true)))))
          {
            this.audioFilePath = localFile.getAbsolutePath();
            if (this.audioFilePath != null)
              localVLTextToSpeech.synthesizeToFile(localVLTextToSpeechRequest, this.audioFilePath, this);
          }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
        }
        catch (IOException localIOException)
        {
        }
        label107: return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        break label107;
      }
    }
  }

  class LocalTTSInitListener
    implements TextToSpeech.OnInitListener
  {
    volatile boolean isInitialized = false;
    volatile boolean isSuccess = false;

    LocalTTSInitListener()
    {
    }

    boolean isInitialized()
    {
      monitorenter;
      try
      {
        boolean bool = this.isInitialized;
        monitorexit;
        return bool;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    boolean isSuccess()
    {
      monitorenter;
      try
      {
        boolean bool = this.isSuccess;
        monitorexit;
        return bool;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    public void onInit(int paramInt)
    {
      if (paramInt == 0)
        this.isSuccess = true;
      this.isInitialized = true;
    }
  }

  class LocalTTSUttListener
    implements TextToSpeech.OnUtteranceCompletedListener
  {
    volatile boolean isComplete = false;

    LocalTTSUttListener()
    {
    }

    boolean isComplete()
    {
      monitorenter;
      try
      {
        boolean bool = this.isComplete;
        monitorexit;
        return bool;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    public void onUtteranceCompleted(String paramString)
    {
      if (TTSEngine.this.localTTSEngine != null)
        TTSEngine.this.localTTSEngine.setOnUtteranceCompletedListener(null);
      this.isComplete = true;
    }
  }

  private static class TTSLocalDefaultEngine extends TTSLocalEngine
  {
    private TextToSpeech mTextToSpeach;

    public TTSLocalDefaultEngine(TextToSpeech paramTextToSpeech)
    {
      this.mTextToSpeach = paramTextToSpeech;
    }

    public List<TextToSpeech.EngineInfo> getEngines()
    {
      return this.mTextToSpeach.getEngines();
    }

    public Object getLanguage()
    {
      return this.mTextToSpeach.getLanguage();
    }

    public int setLanguage(Locale paramLocale)
    {
      return this.mTextToSpeach.setLanguage(paramLocale);
    }

    public int setOnUtteranceCompletedListener(TextToSpeech.OnUtteranceCompletedListener paramOnUtteranceCompletedListener)
    {
      return this.mTextToSpeach.setOnUtteranceCompletedListener(paramOnUtteranceCompletedListener);
    }

    public void setSpeechRate(float paramFloat)
    {
      this.mTextToSpeach.setSpeechRate(paramFloat);
    }

    public void shutdown()
    {
      this.mTextToSpeach.shutdown();
    }

    public int stop()
    {
      return this.mTextToSpeach.stop();
    }

    public int synthesizeToFile(String paramString1, HashMap<String, String> paramHashMap, String paramString2)
    {
      return this.mTextToSpeach.synthesizeToFile(paramString1, paramHashMap, paramString2);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.TTSEngine
 * JD-Core Version:    0.6.0
 */