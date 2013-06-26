package com.vlingo.core.internal.audio;

public abstract interface AudioLogger
{
  public abstract void dumpToFile();

  public abstract void writeData(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  public abstract void writeData(short[] paramArrayOfShort, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioLogger
 * JD-Core Version:    0.6.0
 */