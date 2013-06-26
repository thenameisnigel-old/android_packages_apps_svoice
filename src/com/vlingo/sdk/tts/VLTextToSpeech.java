package com.vlingo.sdk.tts;

import com.vlingo.sdk.VLComponent;

public abstract interface VLTextToSpeech extends VLComponent
{
  public abstract void cancel();

  public abstract String[] getSupportedLanguageList();

  public abstract void synthesizeToFile(VLTextToSpeechRequest paramVLTextToSpeechRequest, String paramString, VLTextToSpeechListener paramVLTextToSpeechListener);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.tts.VLTextToSpeech
 * JD-Core Version:    0.6.0
 */