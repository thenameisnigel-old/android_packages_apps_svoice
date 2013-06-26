package com.vlingo.sdk.internal.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class ThreadPoolExecutor
{
  private volatile int availWorkers = 0;
  private volatile boolean dynamic = false;
  private volatile int maxPoolSize = 1;
  private volatile int minPoolSize = 1;
  private volatile int nextThreadID = 1;
  private volatile boolean paused = false;
  private volatile int priority = 5;
  private final Vector<Future> queue = new Vector();
  private volatile boolean shutDown = false;
  private final String workerName;
  private final Vector<Thread> workers = new Vector();

  public ThreadPoolExecutor()
  {
    this("Worker");
  }

  public ThreadPoolExecutor(String paramString)
  {
    this.workerName = paramString;
  }

  private static boolean isRunnableHighPriority(Runnable paramRunnable)
  {
    if ((paramRunnable instanceof ThreadPoolRunnable));
    for (boolean bool = ((ThreadPoolRunnable)paramRunnable).isHighPriority(); ; bool = false)
      return bool;
  }

  private static boolean isRunnableOrdered(Runnable paramRunnable)
  {
    if ((paramRunnable instanceof ThreadPoolRunnable));
    for (boolean bool = ((ThreadPoolRunnable)paramRunnable).isOrdered(); ; bool = false)
      return bool;
  }

  private static boolean isRunnableRetry(Runnable paramRunnable)
  {
    if ((paramRunnable instanceof ThreadPoolRunnable));
    for (boolean bool = ((ThreadPoolRunnable)paramRunnable).isRetry(); ; bool = false)
      return bool;
  }

  public void clear()
  {
    synchronized (this.queue)
    {
      this.queue.removeAllElements();
      this.queue.notifyAll();
      return;
    }
  }

  public Future execute(Runnable paramRunnable)
  {
    if (this.shutDown)
      throw new IllegalStateException();
    synchronized (this.queue)
    {
      Future localFuture;
      synchronized (this.workers)
      {
        if ((this.workers.size() < this.maxPoolSize) && (this.availWorkers == 0))
        {
          Worker localWorker = new Worker(null);
          StringBuilder localStringBuilder = new StringBuilder().append(this.workerName);
          int m = this.nextThreadID;
          this.nextThreadID = (m + 1);
          Thread localThread = new Thread(localWorker, m);
          localThread.setPriority(this.priority);
          this.workers.addElement(localThread);
          this.availWorkers = (1 + this.availWorkers);
          localThread.start();
        }
        localFuture = new Future(paramRunnable);
      }
      try
      {
        if (isRunnableHighPriority(paramRunnable))
        {
          int i = 0;
          int j = this.queue.size();
          for (int k = 0; ; k++)
          {
            if (k < j)
            {
              if (isRunnableHighPriority(((Future)this.queue.elementAt(k)).getRunnable()))
                continue;
              this.queue.insertElementAt(localFuture, k);
              i = 1;
            }
            if (i == 0)
              this.queue.addElement(localFuture);
            this.queue.notifyAll();
            return localFuture;
            localObject4 = finally;
            monitorexit;
            throw localObject4;
            label258: Object localObject1;
            throw localObject1;
          }
        }
        this.queue.addElement(localFuture);
      }
      finally
      {
        break label258;
      }
    }
  }

  public Future executeLater(Runnable paramRunnable, long paramLong)
  {
    Future localFuture = new Future(paramRunnable);
    Timer localTimer = TimerSingleton.getTimer();
    1 local1 = new TimerTask(localFuture)
    {
      public void run()
      {
        this.val$fut.setScheduleTask(null);
        ThreadPoolExecutor.this.execute(this.val$fut.getRunnable());
      }
    };
    localFuture.setScheduleTask(local1);
    localTimer.schedule(local1, paramLong);
    return localFuture;
  }

  public boolean isBusy()
  {
    if ((this.availWorkers == 0) && (this.workers.size() == this.maxPoolSize));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isEmpty()
  {
    synchronized (this.queue)
    {
      boolean bool = this.queue.isEmpty();
      return bool;
    }
  }

  public void pause()
  {
    if (!this.paused)
      synchronized (this.queue)
      {
        this.paused = true;
        this.queue.notifyAll();
      }
  }

  public void resume()
  {
    if (this.paused)
      synchronized (this.queue)
      {
        this.paused = false;
        this.queue.notifyAll();
      }
  }

  public void setDynamicSizing(boolean paramBoolean)
  {
    this.dynamic = paramBoolean;
  }

  public void setMaxPoolSize(int paramInt)
  {
    this.maxPoolSize = paramInt;
  }

  public void setMinPoolSize(int paramInt)
  {
    this.minPoolSize = paramInt;
  }

  public void setThreadPriority(int paramInt)
  {
    this.priority = paramInt;
  }

  public void shutdown()
  {
    synchronized (this.queue)
    {
      this.shutDown = true;
      this.queue.notifyAll();
      return;
    }
  }

  private class Worker
    implements Runnable
  {
    private Worker()
    {
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   4: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   7: astore_1
      //   8: aload_1
      //   9: monitorenter
      //   10: aload_0
      //   11: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   14: invokestatic 34	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$200	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Z
      //   17: ifne +16 -> 33
      //   20: aload_0
      //   21: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   24: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   27: invokevirtual 40	java/util/Vector:isEmpty	()Z
      //   30: ifeq +33 -> 63
      //   33: aload_0
      //   34: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   37: invokestatic 43	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$300	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Z
      //   40: istore_3
      //   41: iload_3
      //   42: ifne +21 -> 63
      //   45: aload_0
      //   46: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   49: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   52: invokevirtual 46	java/lang/Object:wait	()V
      //   55: goto -45 -> 10
      //   58: astore 25
      //   60: goto -50 -> 10
      //   63: aload_0
      //   64: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   67: invokestatic 43	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$300	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Z
      //   70: ifeq +56 -> 126
      //   73: aload_0
      //   74: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   77: invokestatic 49	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$400	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   80: astore 21
      //   82: aload 21
      //   84: monitorenter
      //   85: aload_0
      //   86: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   89: invokestatic 49	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$400	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   92: invokestatic 55	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   95: invokevirtual 59	java/util/Vector:removeElement	(Ljava/lang/Object;)Z
      //   98: pop
      //   99: aload_0
      //   100: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   103: invokestatic 63	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$510	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)I
      //   106: pop
      //   107: aload 21
      //   109: monitorexit
      //   110: aload_1
      //   111: monitorexit
      //   112: return
      //   113: astore 22
      //   115: aload 21
      //   117: monitorexit
      //   118: aload 22
      //   120: athrow
      //   121: astore_2
      //   122: aload_1
      //   123: monitorexit
      //   124: aload_2
      //   125: athrow
      //   126: aload_0
      //   127: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   130: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   133: invokevirtual 67	java/util/Vector:firstElement	()Ljava/lang/Object;
      //   136: checkcast 69	com/vlingo/sdk/internal/util/Future
      //   139: astore 4
      //   141: aload_0
      //   142: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   145: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   148: aload 4
      //   150: invokevirtual 59	java/util/Vector:removeElement	(Ljava/lang/Object;)Z
      //   153: pop
      //   154: aload_1
      //   155: monitorexit
      //   156: aconst_null
      //   157: astore 6
      //   159: aload 4
      //   161: monitorenter
      //   162: aload 4
      //   164: invokevirtual 72	com/vlingo/sdk/internal/util/Future:isCancelled	()Z
      //   167: ifne +26 -> 193
      //   170: aload_0
      //   171: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   174: invokestatic 63	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$510	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)I
      //   177: pop
      //   178: aload 4
      //   180: invokevirtual 76	com/vlingo/sdk/internal/util/Future:getRunnable	()Ljava/lang/Runnable;
      //   183: astore 6
      //   185: aload 4
      //   187: invokestatic 55	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   190: invokevirtual 80	com/vlingo/sdk/internal/util/Future:setThread	(Ljava/lang/Thread;)V
      //   193: aload 4
      //   195: monitorexit
      //   196: aload 6
      //   198: ifnull -198 -> 0
      //   201: iconst_1
      //   202: istore 8
      //   204: aload 6
      //   206: invokeinterface 82 1 0
      //   211: aload 6
      //   213: invokestatic 86	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$600	(Ljava/lang/Runnable;)Z
      //   216: ifeq +50 -> 266
      //   219: aload_0
      //   220: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   223: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   226: astore 18
      //   228: aload 18
      //   230: monitorenter
      //   231: aload 6
      //   233: invokestatic 89	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$700	(Ljava/lang/Runnable;)Z
      //   236: ifne +11 -> 247
      //   239: aload 6
      //   241: invokestatic 92	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$800	(Ljava/lang/Runnable;)Z
      //   244: ifeq +166 -> 410
      //   247: aload_0
      //   248: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   251: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   254: aload 4
      //   256: iconst_0
      //   257: invokevirtual 96	java/util/Vector:insertElementAt	(Ljava/lang/Object;I)V
      //   260: aload 18
      //   262: monitorexit
      //   263: iconst_0
      //   264: istore 8
      //   266: aload 4
      //   268: monitorenter
      //   269: iload 8
      //   271: ifeq +8 -> 279
      //   274: aload 4
      //   276: invokevirtual 99	com/vlingo/sdk/internal/util/Future:complete	()V
      //   279: aload 4
      //   281: aconst_null
      //   282: invokevirtual 80	com/vlingo/sdk/internal/util/Future:setThread	(Ljava/lang/Thread;)V
      //   285: aload 4
      //   287: monitorexit
      //   288: aload_0
      //   289: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   292: invokestatic 102	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$508	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)I
      //   295: pop
      //   296: aload_0
      //   297: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   300: invokestatic 105	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$900	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Z
      //   303: ifeq -303 -> 0
      //   306: aload_0
      //   307: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   310: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   313: astore 12
      //   315: aload 12
      //   317: monitorenter
      //   318: aload_0
      //   319: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   322: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   325: invokevirtual 40	java/util/Vector:isEmpty	()Z
      //   328: ifeq +121 -> 449
      //   331: aload_0
      //   332: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   335: invokestatic 49	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$400	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   338: astore 14
      //   340: aload 14
      //   342: monitorenter
      //   343: aload_0
      //   344: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   347: invokestatic 49	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$400	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   350: invokevirtual 109	java/util/Vector:size	()I
      //   353: aload_0
      //   354: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   357: invokestatic 112	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$1000	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)I
      //   360: if_icmple +86 -> 446
      //   363: aload_0
      //   364: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   367: invokestatic 63	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$510	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)I
      //   370: pop
      //   371: aload_0
      //   372: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   375: invokestatic 49	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$400	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   378: invokestatic 55	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   381: invokevirtual 59	java/util/Vector:removeElement	(Ljava/lang/Object;)Z
      //   384: pop
      //   385: aload 14
      //   387: monitorexit
      //   388: aload 12
      //   390: monitorexit
      //   391: goto -279 -> 112
      //   394: astore 13
      //   396: aload 12
      //   398: monitorexit
      //   399: aload 13
      //   401: athrow
      //   402: astore 7
      //   404: aload 4
      //   406: monitorexit
      //   407: aload 7
      //   409: athrow
      //   410: aload_0
      //   411: getfield 15	com/vlingo/sdk/internal/util/ThreadPoolExecutor$Worker:this$0	Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;
      //   414: invokestatic 30	com/vlingo/sdk/internal/util/ThreadPoolExecutor:access$100	(Lcom/vlingo/sdk/internal/util/ThreadPoolExecutor;)Ljava/util/Vector;
      //   417: aload 4
      //   419: invokevirtual 116	java/util/Vector:addElement	(Ljava/lang/Object;)V
      //   422: goto -162 -> 260
      //   425: astore 19
      //   427: aload 18
      //   429: monitorexit
      //   430: aload 19
      //   432: athrow
      //   433: astore 9
      //   435: goto -169 -> 266
      //   438: astore 10
      //   440: aload 4
      //   442: monitorexit
      //   443: aload 10
      //   445: athrow
      //   446: aload 14
      //   448: monitorexit
      //   449: aload 12
      //   451: monitorexit
      //   452: goto -452 -> 0
      //   455: astore 15
      //   457: aload 14
      //   459: monitorexit
      //   460: aload 15
      //   462: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   45	55	58	java/lang/InterruptedException
      //   85	110	113	finally
      //   115	118	113	finally
      //   10	41	121	finally
      //   45	55	121	finally
      //   63	85	121	finally
      //   110	112	121	finally
      //   118	124	121	finally
      //   126	156	121	finally
      //   318	343	394	finally
      //   388	399	394	finally
      //   449	452	394	finally
      //   460	463	394	finally
      //   162	196	402	finally
      //   404	407	402	finally
      //   231	263	425	finally
      //   410	430	425	finally
      //   204	231	433	java/lang/Throwable
      //   430	433	433	java/lang/Throwable
      //   274	288	438	finally
      //   440	443	438	finally
      //   343	388	455	finally
      //   446	449	455	finally
      //   457	460	455	finally
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.ThreadPoolExecutor
 * JD-Core Version:    0.6.0
 */