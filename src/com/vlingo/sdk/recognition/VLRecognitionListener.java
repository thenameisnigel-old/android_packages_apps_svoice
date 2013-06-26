package com.vlingo.sdk.recognition;

public abstract interface VLRecognitionListener
{
  public abstract void onCancelled();

  public abstract void onError(VLRecognitionErrors paramVLRecognitionErrors, String paramString);

  public abstract long onRecoToneStarting();

  public abstract void onRecognitionResults(VLRecognitionResult paramVLRecognitionResult);

  public abstract void onRmsChanged(int paramInt);

  public abstract void onStateChanged(VLRecognitionStates paramVLRecognitionStates);

  public abstract void onWarning(VLRecognitionWarnings paramVLRecognitionWarnings, String paramString);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.VLRecognitionListener
 * JD-Core Version:    0.6.0
 */