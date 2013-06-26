package com.vlingo.core.internal.util;

import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskQueue
{
  private Task mCurrentTask = null;
  private boolean mDisableProcessingOnResume;
  private Handler mHandler;
  private boolean mIsPaused;
  private TaskQueueListener mListener;
  private ArrayList<Task> mTaskList = new ArrayList();

  public TaskQueue(Handler paramHandler)
  {
    this.mHandler = paramHandler;
  }

  public TaskQueue(Handler paramHandler, TaskQueueListener paramTaskQueueListener)
  {
    this.mHandler = paramHandler;
    this.mListener = paramTaskQueueListener;
  }

  private void notifyQueueCancelled()
  {
    ActivityUtil.scheduleOnMainThread(new Runnable()
    {
      public void run()
      {
        if (TaskQueue.this.mListener != null)
          TaskQueue.this.mListener.onQueueCancelled();
      }
    });
  }

  private void notifyQueueDone()
  {
    ActivityUtil.scheduleOnMainThread(new Runnable()
    {
      public void run()
      {
        if (TaskQueue.this.mListener != null)
          TaskQueue.this.mListener.onQueueDone();
      }
    });
  }

  private void notifyTaskStarting(Task paramTask)
  {
    if (this.mListener != null)
      this.mListener.onTaskStarting(paramTask);
  }

  private void onCurrentTaskDone()
  {
    monitorenter;
    while (true)
    {
      try
      {
        boolean bool = this.mCurrentTask.isCancelled();
        this.mCurrentTask = null;
        if (this.mTaskList.isEmpty())
        {
          if (this.mListener == null)
            continue;
          if (!bool)
            continue;
          notifyQueueCancelled();
          return;
          notifyQueueDone();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      processNextTask();
    }
  }

  private void processNextTask()
  {
    monitorenter;
    try
    {
      if ((!this.mIsPaused) && (this.mCurrentTask == null) && (!this.mTaskList.isEmpty()))
      {
        Task localTask = (Task)this.mTaskList.get(0);
        this.mDisableProcessingOnResume = true;
        notifyTaskStarting(localTask);
        this.mDisableProcessingOnResume = false;
        if (!this.mIsPaused)
        {
          this.mCurrentTask = localTask;
          this.mTaskList.remove(0);
          this.mHandler.post(this.mCurrentTask);
        }
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

  public void cancel()
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.mTaskList.iterator();
      while (localIterator.hasNext())
        ((Task)localIterator.next()).onAborted();
    }
    finally
    {
      monitorexit;
    }
    this.mTaskList.clear();
    if (this.mCurrentTask != null)
      this.mCurrentTask.cancel();
    monitorexit;
  }

  public void deleteQueuedTaskOfType(Class<? extends Task> paramClass)
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.mTaskList.iterator();
      while (localIterator.hasNext())
      {
        if (((Task)localIterator.next()).getClass() != paramClass)
          continue;
        localIterator.remove();
      }
    }
    finally
    {
      monitorexit;
    }
  }

  public boolean hasQueuedOrRunningTaskOfType(Class<? extends Task> paramClass)
  {
    monitorenter;
    try
    {
      if (this.mCurrentTask != null)
      {
        Class localClass = this.mCurrentTask.getClass();
        if (localClass != paramClass);
      }
      boolean bool;
      for (int i = 1; ; i = bool)
      {
        return i;
        bool = isQueuedTask(paramClass);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean hasQueuedTask()
  {
    monitorenter;
    try
    {
      int i = this.mTaskList.size();
      if (i > 0)
      {
        j = 1;
        return j;
      }
      int j = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public boolean hasQueuedTask(Task paramTask)
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.mTaskList.iterator();
      while (localIterator.hasNext())
      {
        Task localTask = (Task)localIterator.next();
        if (localTask != paramTask)
          continue;
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public boolean isQueuedTask(Class<? extends Task> paramClass)
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.mTaskList.iterator();
      while (localIterator.hasNext())
      {
        Class localClass = ((Task)localIterator.next()).getClass();
        if (localClass != paramClass)
          continue;
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public boolean isRunningTask()
  {
    monitorenter;
    try
    {
      Task localTask = this.mCurrentTask;
      if (localTask != null)
      {
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public boolean isRunningTask(Task paramTask)
  {
    monitorenter;
    try
    {
      Task localTask = this.mCurrentTask;
      if (localTask == paramTask)
      {
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public void pause()
  {
    monitorenter;
    try
    {
      this.mIsPaused = true;
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

  public void queueTask(Task paramTask)
  {
    monitorenter;
    try
    {
      this.mTaskList.add(paramTask);
      Task.access$002(paramTask, this);
      processNextTask();
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

  public void resume()
  {
    monitorenter;
    try
    {
      if (this.mIsPaused)
      {
        this.mIsPaused = false;
        if (!this.mDisableProcessingOnResume)
          processNextTask();
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

  public static abstract class Task
    implements Runnable
  {
    private boolean finished = false;
    private boolean isCancelled = false;
    private TaskQueue mParentQueue = null;

    private void cancel()
    {
      this.isCancelled = true;
      onCancelled();
    }

    protected TaskQueue getParentQueue()
    {
      return this.mParentQueue;
    }

    protected boolean isCancelled()
    {
      return this.isCancelled;
    }

    protected void notifyFinished()
    {
      if (!this.finished)
      {
        this.finished = true;
        if (this.mParentQueue != null)
          this.mParentQueue.onCurrentTaskDone();
      }
    }

    protected void onAborted()
    {
    }

    protected void onCancelled()
    {
    }

    public abstract void run();
  }

  public static abstract interface TaskQueueListener
  {
    public abstract void onQueueCancelled();

    public abstract void onQueueDone();

    public abstract void onTaskStarting(TaskQueue.Task paramTask);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.TaskQueue
 * JD-Core Version:    0.6.0
 */