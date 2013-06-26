package com.vlingo.midas.util.log;

abstract interface IEventLogUtil
{
  public abstract void disabled();

  public abstract void enabled();

  public abstract boolean isEnabled();

  public abstract void onRecognitionComplete();

  public abstract void onRecognitionError();

  public abstract void onRecognitionResult(String paramString);

  public abstract void onRecognitionStart();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.log.IEventLogUtil
 * JD-Core Version:    0.6.0
 */