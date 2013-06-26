package com.vlingo.sdk.recognition;

public enum VLRecognitionErrors
{
  static
  {
    ERROR_NETWORK = new VLRecognitionErrors("ERROR_NETWORK", 1);
    ERROR_AUDIO = new VLRecognitionErrors("ERROR_AUDIO", 2);
    ERROR_SERVER = new VLRecognitionErrors("ERROR_SERVER", 3);
    ERROR_CLIENT = new VLRecognitionErrors("ERROR_CLIENT", 4);
    ERROR_SPEECH_TIMEOUT = new VLRecognitionErrors("ERROR_SPEECH_TIMEOUT", 5);
    ERROR_NO_MATCH = new VLRecognitionErrors("ERROR_NO_MATCH", 6);
    ERROR_RECOGNIZER_BUSY = new VLRecognitionErrors("ERROR_RECOGNIZER_BUSY", 7);
    ERROR_INSUFFICENT_PERMISSIONS = new VLRecognitionErrors("ERROR_INSUFFICENT_PERMISSIONS", 8);
    VLRecognitionErrors[] arrayOfVLRecognitionErrors = new VLRecognitionErrors[9];
    arrayOfVLRecognitionErrors[0] = ERROR_NETWORK_TIMEOUT;
    arrayOfVLRecognitionErrors[1] = ERROR_NETWORK;
    arrayOfVLRecognitionErrors[2] = ERROR_AUDIO;
    arrayOfVLRecognitionErrors[3] = ERROR_SERVER;
    arrayOfVLRecognitionErrors[4] = ERROR_CLIENT;
    arrayOfVLRecognitionErrors[5] = ERROR_SPEECH_TIMEOUT;
    arrayOfVLRecognitionErrors[6] = ERROR_NO_MATCH;
    arrayOfVLRecognitionErrors[7] = ERROR_RECOGNIZER_BUSY;
    arrayOfVLRecognitionErrors[8] = ERROR_INSUFFICENT_PERMISSIONS;
    $VALUES = arrayOfVLRecognitionErrors;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.VLRecognitionErrors
 * JD-Core Version:    0.6.0
 */