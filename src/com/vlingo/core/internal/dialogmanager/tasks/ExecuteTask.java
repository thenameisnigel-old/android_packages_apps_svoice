package com.vlingo.core.internal.dialogmanager.tasks;

import com.vlingo.core.internal.util.TaskQueue.Task;

public class ExecuteTask extends TaskQueue.Task
{
  private Runnable mRunnable;

  public ExecuteTask(Runnable paramRunnable)
  {
    this.mRunnable = paramRunnable;
  }

  public void run()
  {
    monitorenter;
    try
    {
      if (this.mRunnable != null)
        new Thread(this.mRunnable, "ExecuteTask").start();
      notifyFinished();
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
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.tasks.ExecuteTask
 * JD-Core Version:    0.6.0
 */