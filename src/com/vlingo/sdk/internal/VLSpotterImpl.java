package com.vlingo.sdk.internal;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.phrasespotter.SensoryJNI;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.recognition.spotter.SpotterFilenameMaps;
import com.vlingo.sdk.recognition.spotter.VLSpotter;
import com.vlingo.sdk.recognition.spotter.VLSpotterContext;
import com.vlingo.sdk.recognition.spotter.VLSpotterContext.GrammarSource;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;

public final class VLSpotterImpl extends VLComponentImpl
  implements VLSpotter
{
  private boolean mIsStarted;
  private long mSensoryCtx;
  private SensoryJNI mSensoryJNI;
  private VLSpotterContext mSpotterContext;

  public VLSpotterImpl(VLComponentManager paramVLComponentManager, Handler paramHandler)
  {
    super(paramVLComponentManager, paramHandler);
    SensoryJNI.init();
    this.mSensoryJNI = new SensoryJNI();
  }

  private String getAcousticModelFilename(String paramString)
  {
    AudioManager localAudioManager = (AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio");
    boolean bool = sensoryFileExists(paramString + (String)SpotterFilenameMaps.SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.get(this.mSpotterContext.getLanguage()));
    if ((localAudioManager.isMusicActive()) && (bool));
    for (String str = (String)SpotterFilenameMaps.SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.get(this.mSpotterContext.getLanguage()); ; str = (String)SpotterFilenameMaps.SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.get(this.mSpotterContext.getLanguage()))
      return str;
  }

  private String getDirectory(String paramString)
  {
    String str1 = getSVoiceAppDirectory();
    if (paramString != null)
    {
      String str2 = getAcousticModelFilename(paramString + File.separator);
      if (sensoryFileExists(paramString + File.separator + str2))
        str1 = paramString;
    }
    return str1;
  }

  private String getGrammarModelFilename(String paramString)
  {
    AudioManager localAudioManager = (AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio");
    boolean bool = sensoryFileExists(paramString + (String)SpotterFilenameMaps.SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.get(this.mSpotterContext.getLanguage()));
    if ((localAudioManager.isMusicActive()) && (bool));
    for (String str = (String)SpotterFilenameMaps.SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.get(this.mSpotterContext.getLanguage()); ; str = (String)SpotterFilenameMaps.SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.get(this.mSpotterContext.getLanguage()))
      return str;
  }

  private String getSVoiceAppDirectory()
  {
    return ApplicationAdapter.getInstance().getApplicationContext().getDir("vlsdk_raw", 0).toString();
  }

  private boolean reinitSpotter(String paramString)
  {
    boolean bool;
    if (VLSdk.isSensory2Using())
    {
      if (this.mSensoryCtx != 0L)
        this.mSensoryJNI.phrasespotClose(this.mSensoryCtx);
      String str5 = getDirectory(paramString) + File.separator;
      String str6 = getAcousticModelFilename(str5);
      String str7 = getGrammarModelFilename(str5);
      this.mSensoryCtx = this.mSensoryJNI.phrasespotInit(str5, str6, str7);
      if (this.mSensoryCtx == 0L)
      {
        Log.e("VLG_EXCEPTION", "[sensory2.0] mSensoryCtx = 0 IN reinitSpotter");
        bool = false;
      }
    }
    while (true)
    {
      return bool;
      bool = true;
      continue;
      this.mSensoryJNI.Deinitialize();
      File localFile1 = ApplicationAdapter.getInstance().getApplicationContext().getDir("vlsdk_raw", 0);
      String str1 = (String)SpotterFilenameMaps.SPOTTER_ACOUSTIC_MODEL_FILENAME.get(this.mSpotterContext.getLanguage());
      if (str1 == null)
      {
        Log.e("VLG_EXCEPTION", "acousticModelFilename == null");
        bool = false;
        continue;
      }
      String str2 = localFile1.getAbsolutePath() + File.separator + str1;
      File localFile2 = new File(str2);
      if (!localFile2.exists())
      {
        Log.e("VLG_EXCEPTION", "Could not find acoustic model file: " + str2);
        bool = false;
        continue;
      }
      if (this.mSpotterContext.getGrammarSource().isCompiledFileSource())
      {
        bool = this.mSensoryJNI.Initialize(str2, this.mSpotterContext.getGrammarSource().getCompiledFilepath(), SensoryJNI.GRAMMAR_FORMALITY_DEFAULT, this.mSpotterContext.getBeam(), this.mSpotterContext.getAbsBeam(), this.mSpotterContext.getAoffset(), this.mSpotterContext.getDelay(), null, 0);
        if (bool)
          continue;
        continue;
      }
      String str3 = (String)SpotterFilenameMaps.SPOTTER_PRONUN_MODEL_FILENAME.get(this.mSpotterContext.getLanguage());
      if (str3 == null)
      {
        Log.e("VLG_EXCEPTION", "No pronun model filename defined for language: " + this.mSpotterContext.getLanguage());
        bool = false;
        continue;
      }
      String str4 = localFile1.getAbsolutePath() + File.separator + str3;
      File localFile3 = new File(str4);
      if (!localFile3.exists())
      {
        Log.e("VLG_EXCEPTION", "Could not find pronun model file: " + str4);
        bool = false;
        continue;
      }
      bool = this.mSensoryJNI.InitializePhrases(str2, str4, this.mSpotterContext.getGrammarSource().getGrammarSpec(), this.mSpotterContext.getGrammarSource().getWordList(), this.mSpotterContext.getGrammarSource().getPronunciationList(), SensoryJNI.GRAMMAR_FORMALITY_DEFAULT, this.mSpotterContext.getBeam(), this.mSpotterContext.getAbsBeam(), this.mSpotterContext.getAoffset(), this.mSpotterContext.getDelay(), null, 0);
      if (bool)
        continue;
    }
  }

  private boolean sensoryFileExists(String paramString)
  {
    return new File(paramString).exists();
  }

  public float getLastScore()
  {
    monitorenter;
    try
    {
      validateInstance();
      if (!this.mIsStarted)
        throw new IllegalStateException("Cannot call getLastScore when Spotter is not started.");
    }
    finally
    {
      monitorexit;
    }
    float f = this.mSensoryJNI.GetLastScore();
    monitorexit;
    return f;
  }

  public long getSpotterContext()
  {
    return this.mSensoryCtx;
  }

  public String[] getSupportedLanguageList()
  {
    monitorenter;
    try
    {
      validateInstance();
      String[] arrayOfString = Settings.SUPPORTED_LANGUAGES;
      monitorexit;
      return arrayOfString;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  void onDestroy()
  {
    if (VLSdk.isSensory2Using())
      this.mSensoryJNI.phrasespotClose(this.mSensoryCtx);
    while (true)
    {
      return;
      this.mSensoryJNI.Deinitialize();
      this.mSensoryJNI = null;
      this.mSpotterContext = null;
    }
  }

  public String phrasespotPipe(long paramLong1, ByteBuffer paramByteBuffer, long paramLong2)
  {
    if (this.mSensoryJNI != null);
    for (String str = this.mSensoryJNI.phrasespotPipe(paramLong1, paramByteBuffer, paramLong2); ; str = null)
      return str;
  }

  public String processShortArray(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    monitorenter;
    try
    {
      validateInstance();
      if (!this.mIsStarted)
        throw new IllegalStateException("Cannot call processShortArray when Spotter is not started.");
    }
    finally
    {
      monitorexit;
    }
    if (paramInt1 > 0)
      paramArrayOfShort = Arrays.copyOfRange(paramArrayOfShort, paramInt1, paramInt2);
    String str = this.mSensoryJNI.ProcessShortArray(paramArrayOfShort, paramInt2);
    if (str != null)
    {
      int i = str.length();
      if (i <= 0);
    }
    while (true)
    {
      monitorexit;
      return str;
      str = null;
    }
  }

  public boolean startSpotter(VLSpotterContext paramVLSpotterContext, String paramString)
  {
    int i = 1;
    monitorenter;
    try
    {
      validateInstance();
      if (paramVLSpotterContext == null)
        throw new IllegalArgumentException("context must be specified");
    }
    finally
    {
      monitorexit;
    }
    if (this.mIsStarted)
      throw new IllegalStateException("Spotter already started");
    if (this.mSpotterContext != paramVLSpotterContext)
    {
      this.mSpotterContext = paramVLSpotterContext;
      boolean bool = reinitSpotter(paramString);
      if (!bool)
      {
        i = 0;
        monitorexit;
        return i;
      }
    }
    if (VLSdk.isSensory2Using())
    {
      String str = getDirectory(paramString) + File.separator;
      getAcousticModelFilename(str);
      getGrammarModelFilename(str);
      this.mSensoryCtx = this.mSensoryJNI.phrasespotInit(str, getAcousticModelFilename(str), getGrammarModelFilename(str));
      if (this.mSensoryCtx != 0L);
    }
    while (true)
    {
      this.mIsStarted = true;
      break;
      this.mSensoryJNI.MakeReady();
    }
  }

  public void stopSpotter()
  {
    monitorenter;
    try
    {
      validateInstance();
      if (this.mIsStarted)
      {
        this.mSensoryJNI.MakeReady();
        this.mIsStarted = false;
      }
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLSpotterImpl
 * JD-Core Version:    0.6.0
 */