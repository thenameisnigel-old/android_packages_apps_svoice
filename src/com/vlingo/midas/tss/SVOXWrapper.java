package com.vlingo.midas.tss;

import android.content.Context;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import com.svox.classic.TTS;
import com.svox.classic.TTS.TTSSpeechData;
import com.svox.classic.TTS.TTSSynthStrModifiers;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.ShortArrayOutputStream;
import com.vlingo.midas.util.WAVFileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class SVOXWrapper
  implements TTS.TTSSpeechData
{
  private static final String HIGH_QUALITY = "_HQ";
  private static final String LICENCE_PATH = "/data/data/com.vlingo.midas/files/";
  private static final String ROOT_PATH = "/system/tts/lang_SVOX/";
  private static final String ROOT_PATH_HIGH_QUALITY = "/system/tts/lang_SVOXP/";
  private static Map<String, String> smLanguages;
  private static final Pattern smLicanceExpirationDatePattern = Pattern.compile("(\\d+ )(\\d+ \\d+ \\d+)");
  private static SVoxPronunciationManager svoxPronunciationManager;
  private ShortArrayOutputStream mBuffer;
  private long mChannelHandle;
  private long mConfigHandle;
  private long mEngineHandle;
  private boolean mIsInitialized = false;
  private TextToSpeech.OnUtteranceCompletedListener mListener;
  private TTS mSofa;
  private WAVFileWriter mWavFile;
  private Object svoxLockObject = new Object();

  public SVOXWrapper(Locale paramLocale)
  {
    try
    {
      this.mSofa = new TTS("/data/data/com.vlingo.midas/files/", this);
      setLanguage(paramLocale);
      label39: return;
    }
    catch (RuntimeException localRuntimeException)
    {
      break label39;
    }
  }

  private ShortArrayOutputStream getBuffer()
  {
    if (this.mBuffer == null)
      this.mBuffer = new ShortArrayOutputStream(200000);
    return this.mBuffer;
  }

  private static Map<String, String> getLanguages()
  {
    if (smLanguages == null)
      initLanguages();
    return smLanguages;
  }

  public static boolean highQualityTTS(Locale paramLocale)
  {
    String str1 = paramLocale.toString();
    String str2 = (String)getLanguages().get(str1 + "_HQ");
    return new File("/system/tts/lang_SVOXP/" + str2).exists();
  }

  public static void init(Context paramContext)
  {
    svoxPronunciationManager = SVoxPronunciationManager.getInstance(paramContext);
  }

  private static void initLanguages()
  {
    smLanguages = new HashMap();
    smLanguages.put("en_US", "svox-lh0pt2en-US22.pil");
    smLanguages.put("en_US_HQ", "svox-lh0lt2en-US22.pil");
    smLanguages.put("en_GB", "svox-kh0pt2en-GB22.pil");
  }

  public static boolean isExpired()
  {
    return false;
  }

  public static boolean isInstalled(Locale paramLocale)
  {
    boolean bool = highQualityTTS(paramLocale);
    if (!bool)
    {
      String str1 = paramLocale.toString();
      String str2 = (String)getLanguages().get(str1);
      bool = new File("/system/tts/lang_SVOX/" + str2).exists();
    }
    return bool;
  }

  public static boolean isSupportedByLocale(Locale paramLocale)
  {
    String str = paramLocale.toString();
    return getLanguages().containsKey(str);
  }

  private void setLanguage(Locale paramLocale)
  {
    String str = paramLocale.toString();
    synchronized (this.svoxLockObject)
    {
      this.mConfigHandle = this.mSofa.loadConfiguration(str);
      if (highQualityTTS(paramLocale))
      {
        this.mEngineHandle = this.mSofa.newEngine("/system/tts/lang_SVOXP/");
        this.mChannelHandle = this.mSofa.newChannel(this.mEngineHandle, "");
        this.mIsInitialized = true;
        return;
      }
      this.mEngineHandle = this.mSofa.newEngine("/system/tts/lang_SVOX/");
    }
  }

  public void destroy()
  {
    synchronized (this.svoxLockObject)
    {
      if (this.mIsInitialized)
      {
        this.mSofa.closeChannel(this.mEngineHandle, this.mChannelHandle);
        this.mSofa.closeEngine(this.mEngineHandle);
        this.mSofa.unloadConfiguration(this.mConfigHandle);
        this.mIsInitialized = false;
        this.mSofa = null;
      }
      return;
    }
  }

  protected void finalize()
    throws Throwable
  {
    super.finalize();
  }

  public boolean isInitialized()
  {
    return this.mIsInitialized;
  }

  public void onSpeechData(short[] paramArrayOfShort)
  {
    getBuffer().write(paramArrayOfShort);
  }

  // ERROR //
  public void onSpeechDone(int paramInt)
  {
    // Byte code:
    //   0: iload_1
    //   1: ifge +3 -> 4
    //   4: aload_0
    //   5: getfield 213	com/vlingo/midas/tss/SVOXWrapper:mListener	Landroid/speech/tts/TextToSpeech$OnUtteranceCompletedListener;
    //   8: ifnull +62 -> 70
    //   11: aload_0
    //   12: invokespecial 205	com/vlingo/midas/tss/SVOXWrapper:getBuffer	()Lcom/vlingo/midas/util/ShortArrayOutputStream;
    //   15: invokevirtual 217	com/vlingo/midas/util/ShortArrayOutputStream:toShortArray	()[S
    //   18: astore 6
    //   20: aload_0
    //   21: getfield 219	com/vlingo/midas/tss/SVOXWrapper:mWavFile	Lcom/vlingo/midas/util/WAVFileWriter;
    //   24: aload 6
    //   26: iconst_1
    //   27: sipush 22050
    //   30: invokevirtual 224	com/vlingo/midas/util/WAVFileWriter:write	([SII)V
    //   33: aload_0
    //   34: getfield 219	com/vlingo/midas/tss/SVOXWrapper:mWavFile	Lcom/vlingo/midas/util/WAVFileWriter;
    //   37: invokevirtual 227	com/vlingo/midas/util/WAVFileWriter:close	()V
    //   40: aload_0
    //   41: getfield 78	com/vlingo/midas/tss/SVOXWrapper:mBuffer	Lcom/vlingo/midas/util/ShortArrayOutputStream;
    //   44: invokevirtual 228	com/vlingo/midas/util/ShortArrayOutputStream:close	()V
    //   47: aload_0
    //   48: aconst_null
    //   49: putfield 78	com/vlingo/midas/tss/SVOXWrapper:mBuffer	Lcom/vlingo/midas/util/ShortArrayOutputStream;
    //   52: aload_0
    //   53: getfield 213	com/vlingo/midas/tss/SVOXWrapper:mListener	Landroid/speech/tts/TextToSpeech$OnUtteranceCompletedListener;
    //   56: ifnull +14 -> 70
    //   59: aload_0
    //   60: getfield 213	com/vlingo/midas/tss/SVOXWrapper:mListener	Landroid/speech/tts/TextToSpeech$OnUtteranceCompletedListener;
    //   63: ldc 177
    //   65: invokeinterface 233 2 0
    //   70: return
    //   71: astore 4
    //   73: aload_0
    //   74: getfield 78	com/vlingo/midas/tss/SVOXWrapper:mBuffer	Lcom/vlingo/midas/util/ShortArrayOutputStream;
    //   77: invokevirtual 228	com/vlingo/midas/util/ShortArrayOutputStream:close	()V
    //   80: aload_0
    //   81: aconst_null
    //   82: putfield 78	com/vlingo/midas/tss/SVOXWrapper:mBuffer	Lcom/vlingo/midas/util/ShortArrayOutputStream;
    //   85: goto -33 -> 52
    //   88: astore_2
    //   89: aload_0
    //   90: getfield 78	com/vlingo/midas/tss/SVOXWrapper:mBuffer	Lcom/vlingo/midas/util/ShortArrayOutputStream;
    //   93: invokevirtual 228	com/vlingo/midas/util/ShortArrayOutputStream:close	()V
    //   96: aload_0
    //   97: aconst_null
    //   98: putfield 78	com/vlingo/midas/tss/SVOXWrapper:mBuffer	Lcom/vlingo/midas/util/ShortArrayOutputStream;
    //   101: aload_2
    //   102: athrow
    //   103: astore_3
    //   104: goto -8 -> 96
    //   107: astore 5
    //   109: goto -29 -> 80
    //   112: astore 7
    //   114: goto -67 -> 47
    //
    // Exception table:
    //   from	to	target	type
    //   11	40	71	java/io/IOException
    //   11	40	88	finally
    //   89	96	103	java/io/IOException
    //   73	80	107	java/io/IOException
    //   40	47	112	java/io/IOException
  }

  public int setOnUtteranceCompletedListener(TextToSpeech.OnUtteranceCompletedListener paramOnUtteranceCompletedListener)
  {
    this.mListener = paramOnUtteranceCompletedListener;
    return 0;
  }

  public int speakToFile(String paramString1, String paramString2)
  {
    String str1 = svoxPronunciationManager.processTimeZones(paramString1);
    String str2 = svoxPronunciationManager.prepareText(str1).trim().replaceAll(" +", " ");
    synchronized (this.svoxLockObject)
    {
      int i;
      if (!this.mIsInitialized)
        i = -1;
      while (true)
      {
        return i;
        try
        {
          this.mWavFile = new WAVFileWriter(new RandomAccessFile(paramString2, "rw"));
          String str3 = "DEFAULT";
          if (MidasSettings.getBoolean("svox_default_cap_context", false))
            str3 = "DEFAULT_CAPS";
          this.mSofa.putSynthModifStr(this.mEngineHandle, this.mChannelHandle, TTS.TTSSynthStrModifiers.PreprocContext, str3);
          this.mSofa.synthStringAsync(this.mEngineHandle, this.mChannelHandle, str2);
          i = 0;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          i = -1;
        }
        catch (IOException localIOException)
        {
          i = -1;
        }
      }
    }
  }

  public int stop()
  {
    int i = -1;
    synchronized (this.svoxLockObject)
    {
      if (this.mIsInitialized)
      {
        int j = this.mSofa.abort(this.mEngineHandle, this.mChannelHandle);
        if (this.mListener != null)
          this.mListener.onUtteranceCompleted(null);
        if (j == 0)
          i = 0;
      }
    }
    return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.tss.SVOXWrapper
 * JD-Core Version:    0.6.0
 */