package com.vlingo.sdk.tts;

public abstract interface VLTextToSpeechListener
{
  public abstract void onError(VLTextToSpeechErrors paramVLTextToSpeechErrors, String paramString);

  public abstract void onSuccess();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.tts.VLTextToSpeechListener
 * JD-Core Version:    0.6.0
 */