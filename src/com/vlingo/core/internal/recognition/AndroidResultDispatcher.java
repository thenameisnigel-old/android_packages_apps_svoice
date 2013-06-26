package com.vlingo.core.internal.recognition;

public abstract class AndroidResultDispatcher
{
  public abstract boolean handleResults(AndroidRecognitionResults paramAndroidRecognitionResults);

  public abstract void notifyWorking();

  public abstract void onRmsChanged(int paramInt);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.AndroidResultDispatcher
 * JD-Core Version:    0.6.0
 */