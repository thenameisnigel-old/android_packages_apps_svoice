package com.vlingo.midas.phrasespotter;

import com.samsung.voiceshell.VoiceEngine;
import com.samsung.voiceshell.VoiceEngineWrapper;
import com.vlingo.core.internal.audio.MicrophoneStream.AudioSourceType;
import com.vlingo.core.internal.phrasespotter.CoreSpotterParameters;
import com.vlingo.core.internal.phrasespotter.PhraseSpotterParameters;
import com.vlingo.core.internal.phrasespotter.PhraseSpotterParameters.Builder;
import com.vlingo.core.internal.phrasespotter.VLPhraseSpotter;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.settings.MidasSettings;
import java.nio.ByteBuffer;

public class SamsungPhraseSpotter
  implements VLPhraseSpotter
{
  private static final int SAMSUNG_CHUNK_LENGTH_MS = 10;
  private static final int SAMSUNG_SEAMLESS_TIMEOUT_MS = 400;
  private final int NUM_MODELS = 5;
  private VoiceEngine VElib;

  public static PhraseSpotterParameters getPhraseSpotterParameters()
  {
    return getPhraseSpotterParameters(PhraseSpotterUtil.chooseAudioSourceType());
  }

  public static PhraseSpotterParameters getPhraseSpotterParameters(MicrophoneStream.AudioSourceType paramAudioSourceType)
  {
    CoreSpotterParameters localCoreSpotterParameters;
    if (!MidasSettings.getBoolean("samsung_wakeup_engine_enable", false))
      localCoreSpotterParameters = PhraseSpotterUtil.getWakeupCoreParameters(VlingoApplication.getInstance().getResources());
    for (PhraseSpotterParameters.Builder localBuilder = new PhraseSpotterParameters.Builder(Settings.getLanguageApplication(), localCoreSpotterParameters); ; localBuilder = new PhraseSpotterParameters.Builder(Settings.getLanguageApplication()))
    {
      localBuilder.setChunkLength(10);
      localBuilder.setSeamlessTimeout(400);
      localBuilder.setAudioSourceType(paramAudioSourceType);
      return localBuilder.build();
    }
  }

  public void destroy()
  {
    if (this.VElib != null)
      this.VElib.terminateVerify();
    this.VElib = null;
  }

  public int getDeltaD()
  {
    return -200;
  }

  public int getDeltaS()
  {
    return 50;
  }

  public long getPhraseContext()
  {
    return 0L;
  }

  public float getSpottedPhraseScore()
  {
    return 0.0F;
  }

  public void init()
  {
    int[] arrayOfInt = new int[5];
    int i = 2;
    this.VElib = VoiceEngineWrapper.getInstance();
    if (MidasSettings.getBoolean("driving_mode_on", false))
      i = 3;
    VoiceEngine localVoiceEngine1 = this.VElib;
    this.VElib.getClass();
    if (localVoiceEngine1.checkFileExistence("/data/data/com.vlingo.midas/", i, arrayOfInt) != 0)
    {
      this.VElib.terminateVerify();
      VoiceEngine localVoiceEngine2 = this.VElib;
      String str = this.VElib.m_UBMpath_default;
      this.VElib.getClass();
      localVoiceEngine2.initializeVerify(str, "/data/data/com.vlingo.midas/", i);
      this.VElib.setMode(1);
      this.VElib.setAdaptationModelPath(this.VElib.m_UBMpath_default);
      this.VElib.startVerify();
    }
  }

  public String phrasespotPipe(long paramLong1, ByteBuffer paramByteBuffer, long paramLong2)
  {
    return null;
  }

  public String processShortArray(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    short[] arrayOfShort1 = new short[1];
    short[] arrayOfShort2 = new short[1];
    arrayOfShort2[0] = (short)paramInt1;
    VoiceEngine localVoiceEngine1 = this.VElib;
    this.VElib.getClass();
    if (localVoiceEngine1.processBuffer(paramArrayOfShort, paramInt2, "/data/data/com.vlingo.midas/", arrayOfShort2, arrayOfShort1) == 1)
    {
      this.VElib.setMode(2);
      VoiceEngine localVoiceEngine2 = this.VElib;
      String str2 = this.VElib.m_UBMpath_default;
      this.VElib.getClass();
      localVoiceEngine2.performContinuousAdaptation(str2, "/data/data/com.vlingo.midas/");
    }
    for (String str1 = String.valueOf(arrayOfShort1[0]); ; str1 = null)
      return str1;
  }

  public boolean useSeamlessFeature(String paramString)
  {
    int i = 1;
    if ((MidasSettings.getBoolean("seamless_wakeup", false)) && (Integer.parseInt(paramString) == i))
      this.VElib.setMode(2);
    while (true)
    {
      return i;
      i = 0;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.phrasespotter.SamsungPhraseSpotter
 * JD-Core Version:    0.6.0
 */