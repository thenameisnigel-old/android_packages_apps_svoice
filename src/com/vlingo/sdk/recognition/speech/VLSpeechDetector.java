package com.vlingo.sdk.recognition.speech;

import com.vlingo.sdk.VLComponent;

public abstract interface VLSpeechDetector extends VLComponent
{
  public abstract boolean processShortArray(short[] paramArrayOfShort, int paramInt1, int paramInt2);

  public abstract boolean startSpeechDetector(VLSpeechDetectorContext paramVLSpeechDetectorContext);

  public abstract void stopSpeechDetector();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.speech.VLSpeechDetector
 * JD-Core Version:    0.6.0
 */