package com.vlingo.sdk.tts;

public enum VLTextToSpeechErrors
{
  static
  {
    ERROR_NETWORK = new VLTextToSpeechErrors("ERROR_NETWORK", 1);
    ERROR_SERVER = new VLTextToSpeechErrors("ERROR_SERVER", 2);
    ERROR_CLIENT = new VLTextToSpeechErrors("ERROR_CLIENT", 3);
    VLTextToSpeechErrors[] arrayOfVLTextToSpeechErrors = new VLTextToSpeechErrors[4];
    arrayOfVLTextToSpeechErrors[0] = ERROR_NETWORK_TIMEOUT;
    arrayOfVLTextToSpeechErrors[1] = ERROR_NETWORK;
    arrayOfVLTextToSpeechErrors[2] = ERROR_SERVER;
    arrayOfVLTextToSpeechErrors[3] = ERROR_CLIENT;
    $VALUES = arrayOfVLTextToSpeechErrors;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.tts.VLTextToSpeechErrors
 * JD-Core Version:    0.6.0
 */