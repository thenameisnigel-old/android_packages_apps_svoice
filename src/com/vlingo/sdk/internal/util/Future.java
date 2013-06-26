package com.vlingo.sdk.internal.util;

import java.util.TimerTask;

public class Future
{
  private boolean alive = false;
  private boolean cancelled = false;
  private boolean complete = false;
  private final Runnable run;
  private TimerTask scheduleTask;
  private Thread thread;

  Future(Runnable paramRunnable)
  {
    this.run = paramRunnable;
  }

  public void cancel()
  {
    monitorenter;
    try
    {
      if ((!this.complete) && (!this.cancelled))
      {
        if (this.scheduleTask != null)
        {
          this.scheduleTask.cancel();
          this.scheduleTask = null;
        }
        if ((this.thread != null) && (Thread.currentThread() != this.thread))
          this.thread.interrupt();
        this.cancelled = true;
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

  void complete()
  {
    monitorenter;
    try
    {
      this.complete = true;
      this.alive = false;
      this.thread = null;
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

  Runnable getRunnable()
  {
    return this.run;
  }

  public boolean isAlive()
  {
    monitorenter;
    try
    {
      boolean bool = this.alive;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  boolean isCancelled()
  {
    monitorenter;
    try
    {
      boolean bool = this.cancelled;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  void setScheduleTask(TimerTask paramTimerTask)
  {
    monitorenter;
    try
    {
      this.scheduleTask = paramTimerTask;
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

  void setThread(Thread paramThread)
  {
    monitorenter;
    try
    {
      this.thread = paramThread;
      this.alive = true;
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.Future
 * JD-Core Version:    0.6.0
 */