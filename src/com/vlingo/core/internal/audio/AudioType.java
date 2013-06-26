package com.vlingo.core.internal.audio;

public enum AudioType
{
  public final int bytesPerSample;
  public final int channel;
  public final int encoding;
  public final int frequency;

  static
  {
    AudioType[] arrayOfAudioType = new AudioType[2];
    arrayOfAudioType[0] = PCM_22k;
    arrayOfAudioType[1] = PCM_44k;
    $VALUES = arrayOfAudioType;
  }

  private AudioType(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.frequency = paramInt1;
    this.encoding = paramInt2;
    this.channel = paramInt3;
    this.bytesPerSample = paramInt4;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioType
 * JD-Core Version:    0.6.0
 */