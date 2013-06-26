package com.vlingo.sdk.recognition;

public enum VLRecognitionStates
{
  static
  {
    CONNECTING = new VLRecognitionStates("CONNECTING", 1);
    LISTENING = new VLRecognitionStates("LISTENING", 2);
    THINKING = new VLRecognitionStates("THINKING", 3);
    VLRecognitionStates[] arrayOfVLRecognitionStates = new VLRecognitionStates[4];
    arrayOfVLRecognitionStates[0] = GETTING_READY;
    arrayOfVLRecognitionStates[1] = CONNECTING;
    arrayOfVLRecognitionStates[2] = LISTENING;
    arrayOfVLRecognitionStates[3] = THINKING;
    $VALUES = arrayOfVLRecognitionStates;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.VLRecognitionStates
 * JD-Core Version:    0.6.0
 */