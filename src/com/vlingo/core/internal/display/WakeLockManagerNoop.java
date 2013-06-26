package com.vlingo.core.internal.display;

public class WakeLockManagerNoop
  implements WakeLockManager
{
  private static WakeLockManager instance;

  public static WakeLockManager getInstance()
  {
    if (instance == null)
      instance = new WakeLockManagerNoop();
    return instance;
  }

  public void acquireWakeLock()
  {
  }

  public void releaseWakeLock()
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.display.WakeLockManagerNoop
 * JD-Core Version:    0.6.0
 */