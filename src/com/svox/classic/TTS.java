package com.svox.classic;

import java.lang.ref.WeakReference;

public class TTS
{
  private static TTSBreakEvent mBreakListener;
  private static TTSLoadEvent mLoadListener;
  private static TTSPositionEvent mPosListener;
  private static TTSSpeechData mSpeechListener;
  private static TTSVisemeEvent mVisemeListener;
  private String mSystemPath;

  static
  {
    System.loadLibrary("svoxtts");
  }

  public TTS(String paramString, TTSSpeechData paramTTSSpeechData)
    throws RuntimeException
  {
    if (paramString.endsWith("/"));
    for (this.mSystemPath = paramString; ; this.mSystemPath = (paramString + "/"))
    {
      mSpeechListener = paramTTSSpeechData;
      mLoadListener = null;
      mPosListener = null;
      mVisemeListener = null;
      mBreakListener = null;
      int i = native_setup(new WeakReference(this), this.mSystemPath);
      if (i == 0)
        break;
      throw new RuntimeException("Failed to initialize SVOX system. Error code: " + Integer.toString(i));
    }
  }

  private native void native_finalize();

  private final native int native_getOutputType(long paramLong1, long paramLong2);

  private final native int native_getSynthModifInt(long paramLong1, long paramLong2, int paramInt);

  private final native String native_getSynthModifStr(long paramLong1, long paramLong2, int paramInt)
    throws RuntimeException;

  private final native int native_putSynthModifInt(long paramLong1, long paramLong2, int paramInt1, int paramInt2);

  private final native int native_putSynthModifStr(long paramLong1, long paramLong2, int paramInt, String paramString);

  private final native int native_setOutputType(long paramLong1, long paramLong2, int paramInt);

  private native int native_setup(Object paramObject, String paramString);

  private static void receiveBreak(int paramInt1, int paramInt2, int paramInt3)
  {
    if (mBreakListener != null)
      mBreakListener.onBreak(TTSBreakType.values()[paramInt1], paramInt2, paramInt3);
  }

  private static void receivePosMarker(String paramString)
  {
    if (mPosListener != null)
      mPosListener.onPositionMarker(paramString);
  }

  private static void receiveSpeechData(short[] paramArrayOfShort)
  {
    mSpeechListener.onSpeechData(paramArrayOfShort);
  }

  private static void receiveViseme(String paramString1, int paramInt1, String paramString2, int paramInt2, int paramInt3)
  {
    if (mVisemeListener != null)
      mVisemeListener.onViseme(paramString1, paramInt1, paramString2, paramInt2, paramInt3);
  }

  private final native void setBreakListener(boolean paramBoolean);

  private final native void setPosListener(boolean paramBoolean);

  private final native void setVisemeListener(boolean paramBoolean);

  public final native int abort(long paramLong1, long paramLong2);

  public final native int checkConfiguration(String paramString);

  public final native int closeChannel(long paramLong1, long paramLong2)
    throws RuntimeException;

  public final native int closeEngine(long paramLong);

  public final native TTSDetectedLanguage[] detectLanguage(long paramLong, String paramString);

  protected void finalize()
  {
    native_finalize();
  }

  public final native String findConfigurationMainFile(String paramString)
    throws RuntimeException;

  public final native String findLingwareFile(String paramString1, String paramString2)
    throws RuntimeException;

  public final native boolean getAbortionState(long paramLong1, long paramLong2);

  public final native String[] getContextNames(long paramLong, String paramString)
    throws RuntimeException;

  public final native String[] getDetectableLanguageInfo()
    throws RuntimeException;

  public final native String getEngineStatusMessage(long paramLong)
    throws RuntimeException;

  public final native TTSWarning[] getEngineWarnings(long paramLong)
    throws RuntimeException;

  public final native boolean getFastG2PMode(long paramLong1, long paramLong2)
    throws RuntimeException;

  public final native TTSLingwareInfo getLingwareInfo(String paramString)
    throws RuntimeException;

  public final native TTSVoiceInfo[] getLingwareVoiceInfo(String paramString)
    throws RuntimeException;

  public final native String getOutputFile(long paramLong1, long paramLong2)
    throws RuntimeException;

  public TTSOutputType getOutputType(long paramLong1, long paramLong2)
    throws RuntimeException
  {
    int i = native_getOutputType(paramLong1, paramLong2);
    if (i < 0)
      throw new RuntimeException("Error in native getOutputType call : " + Integer.toString(i));
    return TTSOutputType.values()[i];
  }

  public final native String[] getPhonAlphabetsInfo(int paramInt)
    throws RuntimeException;

  public int getSynthModifInt(long paramLong1, long paramLong2, TTSSynthIntModifiers paramTTSSynthIntModifiers)
    throws RuntimeException
  {
    int i = native_getSynthModifInt(paramLong1, paramLong2, paramTTSSynthIntModifiers.ordinal());
    if (i < 0)
      throw new RuntimeException("Error in native call : " + Integer.toString(i));
    return i;
  }

  public String getSynthModifStr(long paramLong1, long paramLong2, TTSSynthStrModifiers paramTTSSynthStrModifiers)
    throws RuntimeException
  {
    return native_getSynthModifStr(paramLong1, paramLong2, paramTTSSynthStrModifiers.ordinal());
  }

  public final native TTSSystemInfo getSystemInfo()
    throws RuntimeException;

  public final native String getSystemStatusMessage()
    throws RuntimeException;

  public final native TTSWarning[] getSystemWarnings()
    throws RuntimeException;

  public native TTSConfigurationInfo getVoiceConfigurationInfo(String paramString)
    throws RuntimeException;

  public native String[] getVoiceConfigurationNames()
    throws RuntimeException;

  public final native TTSVoiceInfo[] getVoicesInfo()
    throws RuntimeException;

  public final native long loadConfiguration(String paramString)
    throws RuntimeException;

  public void loadConfigurationAsync(String paramString)
    throws RuntimeException
  {
    new Thread(new Runnable(paramString)
    {
      public void run()
      {
        long l = TTS.this.loadConfiguration(this.val$config);
        if (TTS.mLoadListener != null)
          TTS.mLoadListener.onLoadDone(l);
      }
    }).start();
  }

  public native long loadLingware(String paramString1, String paramString2)
    throws RuntimeException;

  public void loadLingwareAsync(String paramString1, String paramString2)
    throws RuntimeException
  {
    new Thread(new Runnable(paramString1, paramString2)
    {
      public void run()
      {
        long l = TTS.this.loadLingware(this.val$path, this.val$file);
        if (TTS.mLoadListener != null)
          TTS.mLoadListener.onLoadDone(l);
      }
    }).start();
  }

  public final native long newChannel(long paramLong, String paramString)
    throws RuntimeException;

  public final native long newEngine(String paramString)
    throws RuntimeException;

  public final native int putPosMarker(long paramLong1, long paramLong2, String paramString);

  public int putSynthModifInt(long paramLong1, long paramLong2, TTSSynthIntModifiers paramTTSSynthIntModifiers, int paramInt)
  {
    return native_putSynthModifInt(paramLong1, paramLong2, paramTTSSynthIntModifiers.ordinal(), paramInt);
  }

  public int putSynthModifStr(long paramLong1, long paramLong2, TTSSynthStrModifiers paramTTSSynthStrModifiers, String paramString)
  {
    return native_putSynthModifStr(paramLong1, paramLong2, paramTTSSynthStrModifiers.ordinal(), paramString);
  }

  public final native int resetAbort(long paramLong1, long paramLong2);

  public final native int resetBreakCounts(long paramLong1, long paramLong2);

  public void setBreakEventListener(TTSBreakEvent paramTTSBreakEvent)
  {
    mBreakListener = paramTTSBreakEvent;
    if (mBreakListener != null)
      setBreakListener(true);
    while (true)
    {
      return;
      setBreakListener(false);
    }
  }

  public final native int setFastG2PMode(long paramLong1, long paramLong2, boolean paramBoolean);

  public void setLoadEventListener(TTSLoadEvent paramTTSLoadEvent)
  {
    mLoadListener = paramTTSLoadEvent;
  }

  public final native int setOutputFile(long paramLong1, long paramLong2, String paramString);

  public int setOutputType(long paramLong1, long paramLong2, TTSOutputType paramTTSOutputType)
  {
    return native_setOutputType(paramLong1, paramLong2, paramTTSOutputType.ordinal());
  }

  public void setPositionMarkerListener(TTSPositionEvent paramTTSPositionEvent)
  {
    mPosListener = paramTTSPositionEvent;
    if (mPosListener != null)
      setPosListener(true);
    while (true)
    {
      return;
      setPosListener(false);
    }
  }

  public final native int setVisemeAlphabet(long paramLong1, long paramLong2, String paramString)
    throws RuntimeException;

  public void setVisemeEventListener(TTSVisemeEvent paramTTSVisemeEvent)
  {
    mVisemeListener = paramTTSVisemeEvent;
    if (mVisemeListener != null)
      setVisemeListener(true);
    while (true)
    {
      return;
      setPosListener(false);
    }
  }

  public final native int synthFinish(long paramLong1, long paramLong2);

  public final native int synthItemFile(long paramLong1, long paramLong2, String paramString);

  public final native int synthStr(long paramLong1, long paramLong2, String paramString);

  public final native int synthString(long paramLong1, long paramLong2, String paramString);

  public void synthStringAsync(long paramLong1, long paramLong2, String paramString)
  {
    new Thread(new Runnable(paramLong1, paramLong2, paramString)
    {
      public void run()
      {
        int i = TTS.this.synthString(this.val$engine, this.val$channel, this.val$text);
        if (TTS.mSpeechListener != null)
          TTS.mSpeechListener.onSpeechDone(i);
      }
    }).start();
  }

  public final native int synthTextFile(long paramLong1, long paramLong2, String paramString);

  public final native int unloadConfiguration(long paramLong);

  public final native int unloadLingware(long paramLong);

  public static enum TTSAge
  {
    static
    {
      Adult = new TTSAge("Adult", 2);
      Senior = new TTSAge("Senior", 3);
      Undefined = new TTSAge("Undefined", 4);
      TTSAge[] arrayOfTTSAge = new TTSAge[5];
      arrayOfTTSAge[0] = Child;
      arrayOfTTSAge[1] = Teen;
      arrayOfTTSAge[2] = Adult;
      arrayOfTTSAge[3] = Senior;
      arrayOfTTSAge[4] = Undefined;
      ENUM$VALUES = arrayOfTTSAge;
    }
  }

  public static abstract interface TTSBreakEvent
  {
    public abstract void onBreak(TTS.TTSBreakType paramTTSBreakType, int paramInt1, int paramInt2);
  }

  public static enum TTSBreakType
  {
    static
    {
      ExtData = new TTSBreakType("ExtData", 1);
      Sentence = new TTSBreakType("Sentence", 2);
      TTSBreakType[] arrayOfTTSBreakType = new TTSBreakType[3];
      arrayOfTTSBreakType[0] = Word;
      arrayOfTTSBreakType[1] = ExtData;
      arrayOfTTSBreakType[2] = Sentence;
      ENUM$VALUES = arrayOfTTSBreakType;
    }
  }

  public class TTSConfigurationInfo
  {
    private TTS.TTSAge mAge;
    private int mCompression;
    private TTS.TTSGender mGender;
    private String mLanguage;
    private boolean mLicensed;
    private String mProduct;
    private int mSampleRate;

    public TTSConfigurationInfo(String paramInt1, int paramInt2, int paramInt3, int paramBoolean, boolean paramString1, String paramInt4, int arg8)
    {
      this.mLanguage = paramInt1;
      this.mSampleRate = paramBoolean;
      this.mLicensed = paramString1;
      this.mGender = TTS.TTSGender.values()[paramInt2];
      this.mAge = TTS.TTSAge.values()[paramInt3];
      this.mProduct = paramInt4;
      int i;
      this.mCompression = i;
    }

    public TTS.TTSAge age()
    {
      return this.mAge;
    }

    public int compressionType()
    {
      return this.mCompression;
    }

    public TTS.TTSGender gender()
    {
      return this.mGender;
    }

    public boolean isLicensed()
    {
      return this.mLicensed;
    }

    public String language()
    {
      return this.mLanguage;
    }

    public String productType()
    {
      return this.mProduct;
    }

    public int sampleRate()
    {
      return this.mSampleRate;
    }
  }

  public class TTSDetectedLanguage
  {
    private String mLanguage;
    private int mProbability;

    public TTSDetectedLanguage(String paramInt, int arg3)
    {
      this.mLanguage = paramInt;
      int i;
      this.mProbability = i;
    }

    public String language()
    {
      return this.mLanguage;
    }

    public int probability()
    {
      return this.mProbability;
    }
  }

  public static enum TTSGender
  {
    static
    {
      TTSGender[] arrayOfTTSGender = new TTSGender[3];
      arrayOfTTSGender[0] = Female;
      arrayOfTTSGender[1] = Male;
      arrayOfTTSGender[2] = Undefined;
      ENUM$VALUES = arrayOfTTSGender;
    }
  }

  public class TTSLingwareInfo
  {
    private int mLingwareID;
    private int mNumVoices;
    private String mPlatform;
    private String mVersion;
    private int mVersionMajor;
    private int mVersionMinor;
    private int mVersionRevision;
    private String mVersionSubrevision;

    public TTSLingwareInfo(String paramInt1, int paramInt2, int paramInt3, int paramString1, String paramInt4, int paramInt5, int arg8)
    {
      this.mPlatform = paramInt1;
      this.mVersionMajor = paramInt2;
      this.mVersionMinor = paramInt3;
      this.mVersionRevision = paramString1;
      this.mVersionSubrevision = paramInt4;
      this.mLingwareID = paramInt5;
      int i;
      this.mNumVoices = i;
      this.mVersion = (Integer.toString(this.mVersionMajor) + "." + Integer.toString(this.mVersionMinor) + "." + Integer.toString(this.mVersionRevision) + "." + this.mVersionSubrevision);
    }

    public int lingware_id()
    {
      return this.mLingwareID;
    }

    public int numberOfVoices()
    {
      return this.mNumVoices;
    }

    public String platform()
    {
      return this.mPlatform;
    }

    public String version()
    {
      return this.mVersion;
    }

    public int version_major()
    {
      return this.mVersionMajor;
    }

    public int version_minor()
    {
      return this.mVersionMinor;
    }

    public int version_revision()
    {
      return this.mVersionRevision;
    }

    public String version_subrevision()
    {
      return this.mVersionSubrevision;
    }
  }

  public static abstract interface TTSLoadEvent
  {
    public abstract void onLoadDone(long paramLong);
  }

  public static enum TTSMarkupMode
  {
    static
    {
      Default = new TTSMarkupMode("Default", 1);
      NonCritical = new TTSMarkupMode("NonCritical", 2);
      TTSMarkupMode[] arrayOfTTSMarkupMode = new TTSMarkupMode[3];
      arrayOfTTSMarkupMode[0] = None;
      arrayOfTTSMarkupMode[1] = Default;
      arrayOfTTSMarkupMode[2] = NonCritical;
      ENUM$VALUES = arrayOfTTSMarkupMode;
    }
  }

  public static enum TTSOutputType
  {
    static
    {
      TTSOutputType[] arrayOfTTSOutputType = new TTSOutputType[2];
      arrayOfTTSOutputType[0] = Signal;
      arrayOfTTSOutputType[1] = SynthUnits;
      ENUM$VALUES = arrayOfTTSOutputType;
    }
  }

  public static abstract interface TTSPositionEvent
  {
    public abstract void onPositionMarker(String paramString);
  }

  public static abstract interface TTSSpeechData
  {
    public abstract void onSpeechData(short[] paramArrayOfShort);

    public abstract void onSpeechDone(int paramInt);
  }

  public static enum TTSSynthIntModifiers
  {
    static
    {
      Pitch = new TTSSynthIntModifiers("Pitch", 1);
      Volume = new TTSSynthIntModifiers("Volume", 2);
      MarkupMode = new TTSSynthIntModifiers("MarkupMode", 3);
      TTSSynthIntModifiers[] arrayOfTTSSynthIntModifiers = new TTSSynthIntModifiers[4];
      arrayOfTTSSynthIntModifiers[0] = Speed;
      arrayOfTTSSynthIntModifiers[1] = Pitch;
      arrayOfTTSSynthIntModifiers[2] = Volume;
      arrayOfTTSSynthIntModifiers[3] = MarkupMode;
      ENUM$VALUES = arrayOfTTSSynthIntModifiers;
    }
  }

  public static enum TTSSynthStrModifiers
  {
    static
    {
      PreprocContext = new TTSSynthStrModifiers("PreprocContext", 1);
      ProsodyContext = new TTSSynthStrModifiers("ProsodyContext", 2);
      Language = new TTSSynthStrModifiers("Language", 3);
      TTSSynthStrModifiers[] arrayOfTTSSynthStrModifiers = new TTSSynthStrModifiers[4];
      arrayOfTTSSynthStrModifiers[0] = Voice;
      arrayOfTTSSynthStrModifiers[1] = PreprocContext;
      arrayOfTTSSynthStrModifiers[2] = ProsodyContext;
      arrayOfTTSSynthStrModifiers[3] = Language;
      ENUM$VALUES = arrayOfTTSSynthStrModifiers;
    }
  }

  public class TTSSystemInfo
  {
    private String mPlatform;
    private int mSystemID;
    private String mVersion;
    private int mVersionMajor;
    private int mVersionMinor;
    private int mVersionRevision;
    private String mVersionSubrevision;

    public TTSSystemInfo(String paramInt1, int paramInt2, int paramInt3, int paramString1, String paramInt4, int arg7)
    {
      this.mPlatform = paramInt1;
      this.mVersionMajor = paramInt2;
      this.mVersionMinor = paramInt3;
      this.mVersionRevision = paramString1;
      this.mVersionSubrevision = paramInt4;
      int i;
      this.mSystemID = i;
      this.mVersion = (Integer.toString(this.mVersionMajor) + "." + Integer.toString(this.mVersionMinor) + "." + Integer.toString(this.mVersionRevision) + "." + this.mVersionSubrevision);
    }

    public String platform()
    {
      return this.mPlatform;
    }

    public int system_id()
    {
      return this.mSystemID;
    }

    public String version()
    {
      return this.mVersion;
    }

    public int version_major()
    {
      return this.mVersionMajor;
    }

    public int version_minor()
    {
      return this.mVersionMinor;
    }

    public int version_revision()
    {
      return this.mVersionRevision;
    }

    public String version_subrevision()
    {
      return this.mVersionSubrevision;
    }
  }

  public static abstract interface TTSVisemeEvent
  {
    public abstract void onViseme(String paramString1, int paramInt1, String paramString2, int paramInt2, int paramInt3);
  }

  public class TTSVoiceInfo
  {
    private TTS.TTSAge mAge;
    private TTS.TTSGender mGender;
    private String mLanguage;
    private boolean mLicensed;
    private String mName;
    private int mSampleRate;

    public TTSVoiceInfo(String paramString1, String paramInt1, int paramInt2, int paramInt3, int paramBoolean, boolean arg7)
    {
      this.mName = paramString1;
      this.mLanguage = paramInt1;
      this.mSampleRate = paramBoolean;
      this.mGender = TTS.TTSGender.values()[paramInt2];
      this.mAge = TTS.TTSAge.values()[paramInt3];
      boolean bool;
      this.mLicensed = bool;
    }

    public TTS.TTSAge age()
    {
      return this.mAge;
    }

    public TTS.TTSGender gender()
    {
      return this.mGender;
    }

    public boolean isLicensed()
    {
      return this.mLicensed;
    }

    public String language()
    {
      return this.mLanguage;
    }

    public String name()
    {
      return this.mName;
    }

    public int sampleRate()
    {
      return this.mSampleRate;
    }
  }

  public class TTSWarning
  {
    private int mWarningCode;
    private String mWarningMessage;

    public TTSWarning(int paramString, String arg3)
    {
      this.mWarningCode = paramString;
      Object localObject;
      this.mWarningMessage = localObject;
    }

    public int code()
    {
      return this.mWarningCode;
    }

    public String message()
    {
      return this.mWarningMessage;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.svox.classic.TTS
 * JD-Core Version:    0.6.0
 */