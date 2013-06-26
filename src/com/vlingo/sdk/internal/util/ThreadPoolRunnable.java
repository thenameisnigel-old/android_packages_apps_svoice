package com.vlingo.sdk.internal.util;

public abstract interface ThreadPoolRunnable extends Runnable
{
  public abstract boolean isHighPriority();

  public abstract boolean isOrdered();

  public abstract boolean isRetry();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.ThreadPoolRunnable
 * JD-Core Version:    0.6.0
 */