package com.vlingo.sdk.internal.audio;

public class VLSil
{
  private static final float FRAME_LENGTH = 0.02F;
  private static final float MIN_ENERGY = 0.0F;
  private static final int VLSIL_HISTO_SIZE = 100;
  private static final int VLSIL_MAX_FRAME_SIZE = 320;
  public static final int VLSIL_NO_SPEECH_DETECTED = -1;
  public static final int VLSIL_OK;
  int currentSpeechIndex;
  int[] energyHisto = new int[100];
  float minVoiceDuration;
  float minVoiceLevel;
  int mostRecentSpeechSample;
  int numAboveCurrentSpeechIndex;
  int numFrames;
  int numFramesAboveSpeechThresh;
  int numSamples;
  int sampleRate;
  short[] samples = new short[320];
  int samplesSoFar;
  float silenceThreshold;
  float voicePortion;

  public VLSil(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.sampleRate = paramInt;
    this.voicePortion = paramFloat3;
    this.silenceThreshold = paramFloat1;
    this.minVoiceDuration = paramFloat2;
    this.minVoiceLevel = paramFloat4;
    initForUtterance();
  }

  private float computeEnergy(short[] paramArrayOfShort, int paramInt)
  {
    float f = 0.0F;
    int i = 0;
    for (int j = 0; j < paramInt; j++)
    {
      int n = paramArrayOfShort[j];
      f += n * n;
      i += n;
    }
    int k = i / paramInt;
    int m = (int)(f / paramInt) - k * k;
    if (m < 1)
      m = 1;
    return (float)(10.0D * Math.log10(m));
  }

  private float updateHistoAndSpeechThresh(float paramFloat)
  {
    int i = (int)(paramFloat - 0.0F);
    if (i < 0)
      i = 0;
    if (i >= 100)
      i = 99;
    this.numFrames = (1 + this.numFrames);
    int[] arrayOfInt = this.energyHisto;
    arrayOfInt[i] = (1 + arrayOfInt[i]);
    int j = (int)(this.numFrames * this.voicePortion);
    if (j < 1)
      j = 1;
    if (i > this.currentSpeechIndex)
      this.numAboveCurrentSpeechIndex = (1 + this.numAboveCurrentSpeechIndex);
    if (this.numAboveCurrentSpeechIndex > j)
    {
      this.currentSpeechIndex = (1 + this.currentSpeechIndex);
      if (this.currentSpeechIndex < 100)
      {
        this.numAboveCurrentSpeechIndex -= this.energyHisto[this.currentSpeechIndex];
        if (this.numAboveCurrentSpeechIndex > j)
          break label146;
      }
    }
    label146: label216: 
    while (true)
    {
      return 0.0F + this.currentSpeechIndex;
      this.currentSpeechIndex = (1 + this.currentSpeechIndex);
      break;
      if (this.numAboveCurrentSpeechIndex >= j)
        continue;
      while (true)
      {
        if (this.currentSpeechIndex < 0)
          break label216;
        int k = this.numAboveCurrentSpeechIndex + this.energyHisto[this.currentSpeechIndex];
        if (k > j)
          break;
        this.currentSpeechIndex = (-1 + this.currentSpeechIndex);
        this.numAboveCurrentSpeechIndex = k;
      }
    }
  }

  public void initForUtterance()
  {
    this.samplesSoFar = 0;
    this.mostRecentSpeechSample = -1;
    this.numSamples = 0;
    for (int i = 0; i < 100; i++)
      this.energyHisto[i] = 0;
    this.currentSpeechIndex = 0;
    this.numFrames = 0;
    this.numAboveCurrentSpeechIndex = 0;
    this.numFramesAboveSpeechThresh = 0;
  }

  public int processShortArray(short[] paramArrayOfShort, int paramInt)
  {
    int i = 0;
    int j = (int)(0.02F * this.sampleRate);
    int k = (int)(this.minVoiceDuration / 0.02F);
    int m = 0;
    if (j > 320)
      j = 320;
    int n = this.numSamples;
    while (true)
    {
      int i1;
      int i2;
      if (i < paramInt)
      {
        i1 = n;
        int i4;
        for (i2 = i; (i1 < j) && (i2 < paramInt); i2 = i4)
        {
          short[] arrayOfShort = this.samples;
          int i3 = i1 + 1;
          i4 = i2 + 1;
          arrayOfShort[i1] = paramArrayOfShort[i2];
          i1 = i3;
        }
        if (i1 == j)
        {
          float f = computeEnergy(this.samples, j);
          if (f > m)
            m = (int)f;
          if ((f >= updateHistoAndSpeechThresh(f) - this.silenceThreshold) && (f >= this.minVoiceLevel))
          {
            this.numFramesAboveSpeechThresh = (1 + this.numFramesAboveSpeechThresh);
            if (this.numFramesAboveSpeechThresh >= k)
              this.mostRecentSpeechSample = this.samplesSoFar;
          }
          while (true)
          {
            this.samplesSoFar = (j + this.samplesSoFar);
            n = 0;
            i = i2;
            break;
            this.numFramesAboveSpeechThresh = 0;
          }
        }
      }
      else
      {
        this.numSamples = n;
        return this.mostRecentSpeechSample;
      }
      n = i1;
      i = i2;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.audio.VLSil
 * JD-Core Version:    0.6.0
 */