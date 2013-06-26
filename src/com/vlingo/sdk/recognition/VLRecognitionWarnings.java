package com.vlingo.sdk.recognition;

public enum VLRecognitionWarnings
{
  static
  {
    WARNING_NOTHING_RECOGNIZED = new VLRecognitionWarnings("WARNING_NOTHING_RECOGNIZED", 1);
    VLRecognitionWarnings[] arrayOfVLRecognitionWarnings = new VLRecognitionWarnings[2];
    arrayOfVLRecognitionWarnings[0] = WARNING_SERVER;
    arrayOfVLRecognitionWarnings[1] = WARNING_NOTHING_RECOGNIZED;
    $VALUES = arrayOfVLRecognitionWarnings;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.VLRecognitionWarnings
 * JD-Core Version:    0.6.0
 */