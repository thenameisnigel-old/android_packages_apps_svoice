package com.samsung.speech.enhance;

import com.samsung.voiceshell.VoiceEngine;
import com.vlingo.core.internal.audio.ASyncAudioDataSink;
import com.vlingo.core.internal.audio.AudioFilterAdapter;
import com.vlingo.core.internal.audio.MicAnimationAdapter;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.recognition.VLRecognizer;

public class NoiseCancellationAdapter extends ASyncAudioDataSink
  implements AudioFilterAdapter
{
  static final int SAMPLE_SIZE = 320;
  private static boolean isCurrentUTTSaturated;
  private static boolean isPreviousUTTSaturated;
  private static boolean toastedOnce = false;
  private VoiceEngine SamsungVoiceEngine = initializeVoiceEngine();
  private EPDListener epdListener;
  private int globalAudioSourceId = 6;
  private boolean isDRCon = true;
  private boolean isFirstFrame = true;
  int[] stats;
  short[] temp;
  private final int threadPriority = -8;
  private final boolean useSeperateThreadFromMicAnim = true;

  static
  {
    isCurrentUTTSaturated = false;
    isPreviousUTTSaturated = false;
  }

  // ERROR //
  private VoiceEngine initializeVoiceEngine()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 63	com/samsung/voiceshell/VoiceEngineWrapper:getInstance	()Lcom/samsung/voiceshell/VoiceEngine;
    //   5: astore 4
    //   7: aload 4
    //   9: astore_3
    //   10: aload_0
    //   11: monitorexit
    //   12: aload_3
    //   13: areturn
    //   14: astore_2
    //   15: aconst_null
    //   16: astore_3
    //   17: goto -7 -> 10
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	14	java/lang/Throwable
    //   2	7	20	finally
  }

  public boolean checkSaturation(short[] paramArrayOfShort, int paramInt)
  {
    int i = 0;
    int j = 0;
    for (int k = 0; k < paramArrayOfShort.length; k++)
    {
      if ((paramArrayOfShort[k] <= 8192) && (paramArrayOfShort[k] >= -8192))
        continue;
      j++;
    }
    if (j > 100)
      i = 1;
    if (j > 1);
    return i;
  }

  public int filter(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    if (this.SamsungVoiceEngine == null)
      return paramInt2;
    super.filter(paramArrayOfShort, paramInt1, paramInt2);
    short[] arrayOfShort = new short[paramInt2];
    for (int i = 0; i < paramInt2; i++)
      arrayOfShort[i] = paramArrayOfShort[i];
    int j;
    if (this.globalAudioSourceId == 6)
    {
      j = this.SamsungVoiceEngine.processNSFrame(paramArrayOfShort, paramInt2);
      label66: this.epdListener.processValue(j, paramInt2);
      if (this.isFirstFrame == true)
        if (isPreviousUTTSaturated != true)
          break label165;
    }
    label165: for (this.isDRCon = false; ; this.isDRCon = true)
    {
      isPreviousUTTSaturated = false;
      this.isFirstFrame = false;
      isCurrentUTTSaturated = checkSaturation(paramArrayOfShort, paramInt2);
      if (isCurrentUTTSaturated)
      {
        this.isDRCon = false;
        isPreviousUTTSaturated = true;
      }
      if (this.isDRCon != true)
        break;
      this.SamsungVoiceEngine.processDRC(paramArrayOfShort, paramInt2);
      break;
      j = this.SamsungVoiceEngine.processNSFrame(arrayOfShort, paramInt2);
      break label66;
    }
  }

  public int filter(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3)
  {
    this.globalAudioSourceId = paramInt3;
    return filter(paramArrayOfShort, paramInt1, paramInt2);
  }

  public void init(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.SamsungVoiceEngine == null);
    while (true)
    {
      return;
      super.init(paramInt1, paramInt2, paramInt3, -8);
      this.stats = new int[''];
      this.temp = new short[320];
      this.epdListener = new EPDListener(paramInt1, paramInt2, paramInt3);
      this.SamsungVoiceEngine.initializeNS();
      this.SamsungVoiceEngine.initializeDRC();
      isCurrentUTTSaturated = false;
      this.isFirstFrame = true;
      MicAnimationAdapter.init();
    }
  }

  public boolean quit()
  {
    return super.quit();
  }

  public int sink(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    int i = 0;
    while (true)
    {
      if (i < paramArrayOfShort.length)
      {
        int j = 320;
        if (i + 320 > paramArrayOfShort.length)
          j = paramArrayOfShort.length - i;
        System.arraycopy(paramArrayOfShort, i, this.temp, 0, j);
      }
      try
      {
        this.SamsungVoiceEngine.getSpectrum(this.temp, this.stats);
        label62: MicAnimationAdapter.notifyListeners(this.stats);
        i += 320;
        continue;
        return 0;
      }
      catch (NoSuchMethodError localNoSuchMethodError)
      {
        break label62;
      }
    }
  }

  public int sink(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3)
  {
    sink(paramArrayOfShort, paramInt1, paramInt2);
    return 0;
  }

  class EPDListener
  {
    private final long samolesSilenceNoSpeechLimit;
    private long samolesSilenceSpeechLimit;
    private long samplesSilence;
    private boolean speechWasDetected;

    public EPDListener(int paramInt1, int paramInt2, int arg4)
    {
      this.samolesSilenceSpeechLimit = (paramInt1 * paramInt2 / 1000L);
      int i;
      this.samolesSilenceNoSpeechLimit = (paramInt1 * i / 1000L);
    }

    public void processValue(int paramInt1, int paramInt2)
    {
      if (paramInt1 != 0)
      {
        this.speechWasDetected = true;
        this.samplesSilence = 0L;
      }
      while (true)
      {
        return;
        this.samplesSilence += paramInt2;
        if (this.samolesSilenceSpeechLimit < 1600L)
          this.samolesSilenceSpeechLimit = 1600L;
        if (((!this.speechWasDetected) || (this.samplesSilence < this.samolesSilenceSpeechLimit)) && ((this.speechWasDetected) || (this.samplesSilence < this.samolesSilenceNoSpeechLimit)))
          continue;
        VLSdk.getInstance().getRecognizer().stopRecognition();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.speech.enhance.NoiseCancellationAdapter
 * JD-Core Version:    0.6.0
 */