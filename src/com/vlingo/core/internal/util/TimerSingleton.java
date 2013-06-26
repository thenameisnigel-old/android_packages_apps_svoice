package com.vlingo.core.internal.util;

import java.util.Timer;

public class TimerSingleton
{
  private static TimerSingletonTimer timer;

  public static void destroy()
  {
    monitorenter;
    try
    {
      if (timer != null)
      {
        timer.cancelTimer();
        timer = null;
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static Timer getTimer()
  {
    monitorenter;
    try
    {
      if (timer == null)
        timer = new TimerSingletonTimer();
      TimerSingletonTimer localTimerSingletonTimer = timer;
      monitorexit;
      return localTimerSingletonTimer;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static class TimerSingletonTimer extends Timer
  {
    TimerSingletonTimer()
    {
      super();
    }

    public void cancel()
    {
    }

    protected void cancelTimer()
    {
      super.cancel();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.TimerSingleton
 * JD-Core Version:    0.6.0
 */