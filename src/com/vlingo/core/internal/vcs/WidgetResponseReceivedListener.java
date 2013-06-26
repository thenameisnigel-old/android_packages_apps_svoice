package com.vlingo.core.internal.vcs;

public abstract interface WidgetResponseReceivedListener
{
  public abstract void onRequestFailed();

  public abstract void onRequestScheduled();

  public abstract void onResponseReceived();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.vcs.WidgetResponseReceivedListener
 * JD-Core Version:    0.6.0
 */