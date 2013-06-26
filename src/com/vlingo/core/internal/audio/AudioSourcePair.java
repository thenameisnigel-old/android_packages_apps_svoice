package com.vlingo.core.internal.audio;

public class AudioSourcePair
{
  private int fallback;
  private int preferred;

  public AudioSourcePair(int paramInt1, int paramInt2)
  {
    this.preferred = paramInt1;
    this.fallback = paramInt2;
  }

  public int getFallback()
  {
    return this.fallback;
  }

  public int getPreferred()
  {
    return this.preferred;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioSourcePair
 * JD-Core Version:    0.6.0
 */