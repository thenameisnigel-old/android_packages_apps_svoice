package com.vlingo.core.internal.recognition;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.telephony.TelephonyManager;
import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.audio.MicrophoneStream.TaskType;
import com.vlingo.core.internal.recognition.service.AndroidRecognitionStateListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.internal.recognizer.reader.DataReadyListner;
import com.vlingo.sdk.recognition.AudioSourceInfo;
import com.vlingo.sdk.recognition.AudioSourceInfo.SourceFormat;
import com.vlingo.sdk.recognition.VLRecognitionContext;
import com.vlingo.sdk.recognition.VLRecognitionContext.Builder;
import com.vlingo.sdk.recognition.VLRecognitionErrors;
import com.vlingo.sdk.recognition.VLRecognitionListener;
import com.vlingo.sdk.recognition.VLRecognitionResult;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import com.vlingo.sdk.recognition.VLRecognitionWarnings;
import com.vlingo.sdk.recognition.VLRecognizer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AndroidRecognitionManager
  implements VLRecognitionListener
{
  private static AndroidRecognitionManager instance = null;
  public final String RECOGNITION_SERVICE_FIELDID = "dm_main";
  private boolean isActive = false;
  private AndroidSRContext m_RecognitionContext = null;
  private AndroidResultDispatcher m_ResultDispatcher = null;
  private MicrophoneStream micStream;
  private final List<AndroidRecognitionStateListener> recognitionStateListeners = new CopyOnWriteArrayList();

  private void abortRecognition()
  {
    VLSdk.getInstance().getRecognizer().cancelRecognition();
    closeMicStream();
  }

  public static void cancelRecognition()
  {
    getInstance().abortRecognition();
  }

  // ERROR //
  private void closeMicStream()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 78	com/vlingo/core/internal/recognition/AndroidRecognitionManager:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   4: ifnull +15 -> 19
    //   7: aload_0
    //   8: getfield 78	com/vlingo/core/internal/recognition/AndroidRecognitionManager:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   11: invokevirtual 83	com/vlingo/core/internal/audio/MicrophoneStream:close	()V
    //   14: aload_0
    //   15: aconst_null
    //   16: putfield 78	com/vlingo/core/internal/recognition/AndroidRecognitionManager:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   19: return
    //   20: astore_2
    //   21: aload_0
    //   22: aconst_null
    //   23: putfield 78	com/vlingo/core/internal/recognition/AndroidRecognitionManager:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   26: goto -7 -> 19
    //   29: astore_1
    //   30: aload_0
    //   31: aconst_null
    //   32: putfield 78	com/vlingo/core/internal/recognition/AndroidRecognitionManager:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   35: aload_1
    //   36: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   7	14	20	java/lang/Exception
    //   7	14	29	finally
  }

  public static AndroidRecognitionManager getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = new AndroidRecognitionManager();
      AndroidRecognitionManager localAndroidRecognitionManager = instance;
      monitorexit;
      return localAndroidRecognitionManager;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void handleIUXNotComplete(Context paramContext)
  {
    handleIUXNotCompleteLaunch(paramContext);
  }

  private static void handleIUXNotCompleteLaunch(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, ClientSuppliedValues.getLaunchingClass());
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }

  public static boolean initiateRecognition(Context paramContext, String paramString, AudioSourceInfo paramAudioSourceInfo, int paramInt)
  {
    return getInstance().startRecognition(paramString, paramAudioSourceInfo, paramInt);
  }

  private boolean isIdle()
  {
    monitorenter;
    try
    {
      boolean bool = this.isActive;
      if (!bool)
      {
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  // ERROR //
  private void notifyRecognitionStateListenersOfEvent(int paramInt1, int paramInt2, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 45	com/vlingo/core/internal/recognition/AndroidRecognitionManager:recognitionStateListeners	Ljava/util/List;
    //   4: astore 4
    //   6: aload 4
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 45	com/vlingo/core/internal/recognition/AndroidRecognitionManager:recognitionStateListeners	Ljava/util/List;
    //   13: invokeinterface 127 1 0
    //   18: astore 6
    //   20: aload 6
    //   22: invokeinterface 132 1 0
    //   27: ifeq +33 -> 60
    //   30: aload 6
    //   32: invokeinterface 136 1 0
    //   37: checkcast 138	com/vlingo/core/internal/recognition/service/AndroidRecognitionStateListener
    //   40: aload_0
    //   41: iload_1
    //   42: iload_2
    //   43: aload_3
    //   44: invokeinterface 142 5 0
    //   49: goto -29 -> 20
    //   52: astore 5
    //   54: aload 4
    //   56: monitorexit
    //   57: aload 5
    //   59: athrow
    //   60: aload 4
    //   62: monitorexit
    //   63: return
    //
    // Exception table:
    //   from	to	target	type
    //   9	57	52	finally
    //   60	63	52	finally
  }

  public static void processRecognition()
  {
    getInstance().stopRecognition();
  }

  private boolean recognitionStartCheck()
  {
    int i = 0;
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    if (!ClientSuppliedValues.isIUXComplete())
      handleIUXNotComplete(localContext);
    while (true)
    {
      return i;
      if (((TelephonyManager)localContext.getSystemService("phone")).getCallState() != 0)
      {
        notifyRecognitionStateListenersOfEvent(108, 4, "Phone in use");
        continue;
      }
      NetworkInfo localNetworkInfo = ((ConnectivityManager)localContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo == null) || (localNetworkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTED))
      {
        notifyRecognitionStateListenersOfEvent(106, 4, "Network not available");
        continue;
      }
      if (!isIdle())
      {
        notifyRecognitionStateListenersOfEvent(113, 4, "Recognizer is busy");
        continue;
      }
      i = 1;
    }
  }

  public static void setResultDispatcher(AndroidResultDispatcher paramAndroidResultDispatcher)
  {
    AndroidRecognitionManager localAndroidRecognitionManager = getInstance();
    localAndroidRecognitionManager.m_ResultDispatcher = paramAndroidResultDispatcher;
    localAndroidRecognitionManager.m_RecognitionContext.setCustom6Context(null);
  }

  private boolean startRecognition(String paramString, AudioSourceInfo paramAudioSourceInfo, int paramInt)
  {
    int i;
    if (!recognitionStartCheck())
      i = 0;
    while (true)
    {
      return i;
      VLRecognitionContext.Builder localBuilder1 = new VLRecognitionContext.Builder(paramAudioSourceInfo);
      if (paramAudioSourceInfo != null)
        localBuilder1.audioSourceInfo(paramAudioSourceInfo);
      if (paramAudioSourceInfo == null)
      {
        if (MicrophoneStream.testStream != null)
          AudioSourceInfo.getStreamSource(this.micStream, AudioSourceInfo.SourceFormat.PCM_16KHZ_16BIT);
      }
      else
      {
        localBuilder1.autoEndPointingTimeouts(1500, 5000);
        localBuilder1.language(Settings.getLanguageApplication());
        VLRecognitionContext.Builder localBuilder2 = localBuilder1.fieldID(paramString);
        if (paramInt > 0)
          localBuilder2.maxAudioTime(paramInt);
        VLRecognitionContext localVLRecognitionContext = localBuilder2.build();
        VLSdk.getInstance().getRecognizer().startRecognition(localVLRecognitionContext, this, new DataReadyListner());
        monitorenter;
      }
      try
      {
        this.isActive = true;
        monitorexit;
        i = 1;
        continue;
        this.micStream = MicrophoneStream.request(localBuilder1.build(), MicrophoneStream.TaskType.RECOGNITION);
        MicrophoneStream localMicrophoneStream = this.micStream;
        if (this.micStream.is16KHz());
        for (AudioSourceInfo.SourceFormat localSourceFormat = AudioSourceInfo.SourceFormat.PCM_16KHZ_16BIT; ; localSourceFormat = AudioSourceInfo.SourceFormat.PCM_8KHZ_16BIT)
        {
          AudioSourceInfo.getStreamSource(localMicrophoneStream, localSourceFormat);
          break;
        }
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  private void stopRecognition()
  {
    if (isIdle());
    while (true)
    {
      return;
      VLSdk.getInstance().getRecognizer().stopRecognition();
    }
  }

  public void addRecognitionStateListener(AndroidRecognitionStateListener paramAndroidRecognitionStateListener)
  {
    synchronized (this.recognitionStateListeners)
    {
      if (!this.recognitionStateListeners.contains(paramAndroidRecognitionStateListener))
        this.recognitionStateListeners.add(paramAndroidRecognitionStateListener);
      return;
    }
  }

  public SRContext getSRContext()
  {
    return this.m_RecognitionContext;
  }

  public boolean isActive()
  {
    monitorenter;
    try
    {
      boolean bool = isIdle();
      if (!bool)
      {
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public void onCancelled()
  {
    monitorenter;
    try
    {
      this.isActive = false;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void onError(VLRecognitionErrors paramVLRecognitionErrors, String paramString)
  {
    closeMicStream();
    notifyRecognitionStateListenersOfEvent(103, 5, null);
    switch (1.$SwitchMap$com$vlingo$sdk$recognition$VLRecognitionErrors[paramVLRecognitionErrors.ordinal()])
    {
    default:
      notifyRecognitionStateListenersOfEvent(106, 4, paramString);
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      AndroidRecognitionResults localAndroidRecognitionResults = new AndroidRecognitionResults(paramString, null);
      if (this.m_ResultDispatcher != null)
        this.m_ResultDispatcher.handleResults(localAndroidRecognitionResults);
      monitorenter;
      try
      {
        this.isActive = false;
        return;
        notifyRecognitionStateListenersOfEvent(107, 4, paramString);
        continue;
        notifyRecognitionStateListenersOfEvent(108, 4, paramString);
        continue;
        notifyRecognitionStateListenersOfEvent(107, 4, paramString);
        continue;
        notifyRecognitionStateListenersOfEvent(111, 4, paramString);
        continue;
        notifyRecognitionStateListenersOfEvent(113, 4, paramString);
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  public long onRecoToneStarting()
  {
    return 0L;
  }

  public void onRecognitionResults(VLRecognitionResult paramVLRecognitionResult)
  {
    notifyRecognitionStateListenersOfEvent(103, 5, null);
    String str = paramVLRecognitionResult.getResultString();
    if (str == null)
      notifyRecognitionStateListenersOfEvent(111, 5, null);
    while (true)
    {
      AndroidRecognitionResults localAndroidRecognitionResults = new AndroidRecognitionResults(null, str);
      if (this.m_ResultDispatcher != null)
        this.m_ResultDispatcher.handleResults(localAndroidRecognitionResults);
      monitorenter;
      try
      {
        this.isActive = false;
        return;
        notifyRecognitionStateListenersOfEvent(112, 5, null);
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  public void onRmsChanged(int paramInt)
  {
    if (this.m_ResultDispatcher != null)
      this.m_ResultDispatcher.onRmsChanged(paramInt);
  }

  public void onStateChanged(VLRecognitionStates paramVLRecognitionStates)
  {
    switch (1.$SwitchMap$com$vlingo$sdk$recognition$VLRecognitionStates[paramVLRecognitionStates.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      notifyRecognitionStateListenersOfEvent(100, 5, null);
      continue;
      notifyRecognitionStateListenersOfEvent(102, 5, null);
      continue;
      if (this.m_ResultDispatcher != null)
        this.m_ResultDispatcher.notifyWorking();
      notifyRecognitionStateListenersOfEvent(101, 5, null);
      continue;
      closeMicStream();
      if (this.m_ResultDispatcher != null)
        this.m_ResultDispatcher.notifyWorking();
      notifyRecognitionStateListenersOfEvent(104, 5, null);
    }
  }

  public void onWarning(VLRecognitionWarnings paramVLRecognitionWarnings, String paramString)
  {
    notifyRecognitionStateListenersOfEvent(103, 5, null);
  }

  public void removeRecognitionStateListener(AndroidRecognitionStateListener paramAndroidRecognitionStateListener)
  {
    synchronized (this.recognitionStateListeners)
    {
      if (this.recognitionStateListeners.contains(paramAndroidRecognitionStateListener))
        this.recognitionStateListeners.remove(paramAndroidRecognitionStateListener);
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.AndroidRecognitionManager
 * JD-Core Version:    0.6.0
 */