package com.vlingo.sdk.internal.http;

import com.vlingo.sdk.internal.util.Future;
import com.vlingo.sdk.internal.util.ThreadPoolExecutor;
import com.vlingo.sdk.internal.util.ThreadPoolRunnable;
import com.vlingo.sdk.internal.util.TimerSingleton;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class HttpManager
{
  private static final int DEFAULT_BACKGROUND_TASK_DELAY = 5000;
  private static HttpManager s_HttpManager = null;
  private final Object backgroudExecutorLock = new Object();
  private final ThreadPoolExecutor backgroundExecutor = new ThreadPoolExecutor("BackgroundHttpManager");
  private volatile boolean backgroundExecutorPaused = false;
  private final ThreadPoolExecutor onDemandExecutor = new ThreadPoolExecutor("OnDemandHttpManager");
  private final HashMap<HttpRequest, HttpRequestFetchTask> requestTable = new HashMap();
  private final Timer timeoutTimer;

  protected HttpManager()
  {
    this.onDemandExecutor.setMinPoolSize(1);
    this.onDemandExecutor.setMaxPoolSize(3);
    this.onDemandExecutor.setDynamicSizing(true);
    this.onDemandExecutor.setThreadPriority(10);
    this.backgroundExecutor.setMinPoolSize(0);
    this.backgroundExecutor.setMaxPoolSize(1);
    this.backgroundExecutor.setDynamicSizing(true);
    this.backgroundExecutor.setThreadPriority(1);
    this.timeoutTimer = TimerSingleton.getTimer();
  }

  public static void destroy()
  {
    s_HttpManager = null;
  }

  private void doBackgroundRequestInternal(HttpRequest paramHttpRequest, boolean paramBoolean1, boolean paramBoolean2)
  {
    monitorenter;
    try
    {
      HttpRequestFetchTask localHttpRequestFetchTask = new HttpRequestFetchTask(paramHttpRequest, true, paramBoolean1, paramBoolean2, null);
      localHttpRequestFetchTask.start();
      this.requestTable.put(paramHttpRequest, localHttpRequestFetchTask);
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

  private void doBackgroundRequestLaterInternal(HttpRequest paramHttpRequest, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    monitorenter;
    if (paramLong == 0L);
    try
    {
      doBackgroundRequest(paramHttpRequest, paramBoolean1, paramBoolean2);
      while (true)
      {
        return;
        HttpRequestFetchTask localHttpRequestFetchTask = new HttpRequestFetchTask(paramHttpRequest, true, paramBoolean1, paramBoolean2, null);
        localHttpRequestFetchTask.schedule(paramLong);
        this.requestTable.put(paramHttpRequest, localHttpRequestFetchTask);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void doRequestNowInternal(HttpRequest paramHttpRequest)
  {
    monitorenter;
    try
    {
      HttpRequestFetchTask localHttpRequestFetchTask = new HttpRequestFetchTask(paramHttpRequest, null);
      localHttpRequestFetchTask.start();
      this.requestTable.put(paramHttpRequest, localHttpRequestFetchTask);
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

  public static HttpManager getInstance()
  {
    monitorenter;
    try
    {
      if (s_HttpManager == null)
        s_HttpManager = new HttpManager();
      HttpManager localHttpManager = s_HttpManager;
      monitorexit;
      return localHttpManager;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private void requestHasTimedOut(HttpRequest paramHttpRequest, HttpRequestFetchTimeoutTask paramHttpRequestFetchTimeoutTask)
  {
    monitorenter;
    try
    {
      if ((this.requestTable.containsKey(paramHttpRequest)) && (((HttpRequestFetchTask)this.requestTable.get(paramHttpRequest)).timeout(paramHttpRequestFetchTimeoutTask)))
        this.requestTable.remove(paramHttpRequest);
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

  private void requestWasRan(HttpRequest paramHttpRequest)
  {
    monitorenter;
    try
    {
      this.requestTable.remove(paramHttpRequest);
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

  public void cancelRequest(HttpRequest paramHttpRequest)
  {
    cancelRequestInternal(paramHttpRequest);
  }

  protected void cancelRequestInternal(HttpRequest paramHttpRequest)
  {
    monitorenter;
    try
    {
      if (this.requestTable.containsKey(paramHttpRequest))
        ((HttpRequestFetchTask)this.requestTable.remove(paramHttpRequest)).cancel();
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

  public void doBackgroundRequest(HttpRequest paramHttpRequest, boolean paramBoolean1, boolean paramBoolean2)
  {
    doBackgroundRequestInternal(paramHttpRequest, paramBoolean1, paramBoolean2);
  }

  public void doBackgroundRequestLater(HttpRequest paramHttpRequest, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    doBackgroundRequestLaterInternal(paramHttpRequest, paramLong, paramBoolean1, paramBoolean2);
  }

  public void doRequestNow(HttpRequest paramHttpRequest)
  {
    doRequestNowInternal(paramHttpRequest);
  }

  public void pause()
  {
    synchronized (this.backgroudExecutorLock)
    {
      if (!this.backgroundExecutorPaused)
      {
        this.backgroundExecutorPaused = true;
        this.backgroundExecutor.pause();
      }
      return;
    }
  }

  public void resume()
  {
    synchronized (this.backgroudExecutorLock)
    {
      if (this.backgroundExecutorPaused)
      {
        this.backgroundExecutorPaused = false;
        this.backgroundExecutor.resume();
      }
      return;
    }
  }

  private class HttpRequestFetchTask
    implements ThreadPoolRunnable
  {
    private final boolean background;
    private boolean finished = false;
    private Future future = null;
    private final boolean highPriority;
    private final boolean ordered;
    private final HttpRequest parentRequest;
    private HttpManager.HttpRequestFetchTimeoutTask timeoutTask = null;
    private volatile long waitTime = 0L;

    private HttpRequestFetchTask(HttpRequest arg2)
    {
      this(localHttpRequest, false, false, false);
    }

    private HttpRequestFetchTask(HttpRequest paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean arg5)
    {
      this.parentRequest = paramBoolean1;
      this.background = paramBoolean2;
      this.highPriority = paramBoolean3;
      boolean bool;
      this.ordered = bool;
    }

    private void finish()
    {
      monitorenter;
      try
      {
        this.finished = true;
        if (this.timeoutTask != null)
        {
          this.timeoutTask.cancel();
          this.timeoutTask = null;
        }
        if (this.future != null)
        {
          this.future.cancel();
          this.future = null;
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

    private void schedule(long paramLong)
    {
      monitorenter;
      try
      {
        if (!this.finished)
        {
          if (paramLong <= 0L)
            break label111;
          this.waitTime = paramLong;
        }
        label111: for (this.future = HttpManager.this.backgroundExecutor.executeLater(this, paramLong); ; this.future = HttpManager.this.backgroundExecutor.execute(this))
        {
          if (this.timeoutTask != null)
          {
            this.timeoutTask.cancel();
            this.timeoutTask = null;
          }
          if (this.parentRequest.getTimeout() > 0)
          {
            this.timeoutTask = new HttpManager.HttpRequestFetchTimeoutTask(HttpManager.this, this.parentRequest, null);
            HttpManager.this.timeoutTimer.schedule(this.timeoutTask, this.parentRequest.getTimeout());
          }
          return;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    private void start()
    {
      monitorenter;
      try
      {
        if (!this.finished)
          if (!this.background)
            break label106;
        label106: for (this.future = HttpManager.this.backgroundExecutor.execute(this); ; this.future = HttpManager.this.onDemandExecutor.execute(this))
        {
          if (this.timeoutTask != null)
          {
            this.timeoutTask.cancel();
            this.timeoutTask = null;
          }
          if (this.parentRequest.getTimeout() > 0)
          {
            this.timeoutTask = new HttpManager.HttpRequestFetchTimeoutTask(HttpManager.this, this.parentRequest, null);
            HttpManager.this.timeoutTimer.schedule(this.timeoutTask, this.parentRequest.getTimeout());
          }
          return;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public void cancel()
    {
      monitorenter;
      try
      {
        if (!this.finished)
        {
          this.parentRequest.notifyCancelled();
          finish();
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

    public boolean isHighPriority()
    {
      if ((this.background) && (this.highPriority));
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean isOrdered()
    {
      if ((this.background) && (this.ordered));
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean isRetry()
    {
      int i = 1;
      monitorenter;
      try
      {
        if ((!this.finished) && (this.parentRequest.isRetry()))
          if (this.background)
          {
            long l = this.waitTime;
            if (l > 0L)
              break label43;
          }
        while (true)
        {
          return i;
          label43: schedule(2L * this.waitTime);
          i = 0;
          continue;
          i = 0;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public void run()
    {
      this.parentRequest.notifyWillExecute();
      HttpResponse localHttpResponse = this.parentRequest.fetchResponse();
      monitorenter;
      try
      {
        if (!this.finished)
        {
          finish();
          this.parentRequest.notifyResponse(localHttpResponse);
        }
        monitorexit;
        HttpManager.this.requestWasRan(this.parentRequest);
        return;
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public boolean timeout(HttpManager.HttpRequestFetchTimeoutTask paramHttpRequestFetchTimeoutTask)
    {
      monitorenter;
      try
      {
        if (paramHttpRequestFetchTimeoutTask == this.timeoutTask)
        {
          if (!this.finished)
          {
            finish();
            this.finished = this.parentRequest.notifyTimeout();
          }
          bool = this.finished;
          return bool;
        }
        boolean bool = false;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  private class HttpRequestFetchTimeoutTask extends TimerTask
  {
    private final HttpRequest request;

    private HttpRequestFetchTimeoutTask(HttpRequest arg2)
    {
      Object localObject;
      this.request = localObject;
    }

    public void run()
    {
      HttpManager.this.requestHasTimedOut(this.request, this);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.HttpManager
 * JD-Core Version:    0.6.0
 */