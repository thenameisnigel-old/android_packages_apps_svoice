package com.vlingo.sdk.internal.logging;

public abstract interface LogTarget
{
  public abstract void close();

  public abstract void log(String paramString, boolean paramBoolean);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.logging.LogTarget
 * JD-Core Version:    0.6.0
 */