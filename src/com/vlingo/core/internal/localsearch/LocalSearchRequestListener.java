package com.vlingo.core.internal.localsearch;

public abstract interface LocalSearchRequestListener
{
  public abstract void onRequestComplete(boolean paramBoolean, Object paramObject);

  public abstract void onRequestFailed(String paramString);

  public abstract void onRequestScheduled();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchRequestListener
 * JD-Core Version:    0.6.0
 */