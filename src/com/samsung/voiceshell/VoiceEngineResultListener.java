package com.samsung.voiceshell;

public abstract interface VoiceEngineResultListener
{
  public abstract void OnEnrollResult(int paramInt1, int paramInt2, int paramInt3);

  public abstract void OnRmsForWave(int paramInt);

  public abstract void OnSpectrumData(int[] paramArrayOfInt);

  public abstract void OnVerifyResult(int paramInt, short paramShort);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.voiceshell.VoiceEngineResultListener
 * JD-Core Version:    0.6.0
 */