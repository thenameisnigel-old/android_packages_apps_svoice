package com.vlingo.core.internal.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class ServiceProxyBase<IS, S>
{
  protected static final int IDLE_DELAY = 5;
  private static final int IDLE_DELAY_INTERVAL = 30000;
  protected static final int MSG_BASE = 20;
  protected static final int RUN_QUEUE = 4;
  protected static final int SERVICE_CONNECTED = 1;
  protected static final int SERVICE_DISCONNECTED = 2;
  protected final Context context;
  private final Handler handler;
  private boolean isConnected = false;
  private boolean isConnecting = false;
  private long lastCommandTime = 0L;
  private LinkedList<Message> pendingRequestQueue = new LinkedList();
  private IS service;
  private ServiceBindingUtil.ServiceToken serviceToken;

  public ServiceProxyBase(Context paramContext)
  {
    this.context = paramContext;
    this.handler = new ServiceProxyBase.1(this);
  }

  // ERROR //
  private void handleBase(Message paramMessage)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: getfield 75	android/os/Message:what	I
    //   6: tableswitch	default:+34 -> 40, 1:+86->92, 2:+149->155, 3:+34->40, 4:+135->141, 5:+156->162
    //   41: getfield 44	com/vlingo/core/internal/util/ServiceProxyBase:isConnected	Z
    //   44: istore 5
    //   46: iload 5
    //   48: ifeq +19 -> 67
    //   51: aload_0
    //   52: getfield 77	com/vlingo/core/internal/util/ServiceProxyBase:service	Ljava/lang/Object;
    //   55: ifnull +12 -> 67
    //   58: aload_0
    //   59: aload_1
    //   60: aload_0
    //   61: getfield 77	com/vlingo/core/internal/util/ServiceProxyBase:service	Ljava/lang/Object;
    //   64: invokevirtual 81	com/vlingo/core/internal/util/ServiceProxyBase:handleMessageForService	(Landroid/os/Message;Ljava/lang/Object;)V
    //   67: aload_0
    //   68: getfield 44	com/vlingo/core/internal/util/ServiceProxyBase:isConnected	Z
    //   71: ifeq +18 -> 89
    //   74: aload_1
    //   75: getfield 75	android/os/Message:what	I
    //   78: iconst_5
    //   79: if_icmpeq +10 -> 89
    //   82: aload_0
    //   83: invokestatic 87	android/os/SystemClock:uptimeMillis	()J
    //   86: putfield 46	com/vlingo/core/internal/util/ServiceProxyBase:lastCommandTime	J
    //   89: aload_0
    //   90: monitorexit
    //   91: return
    //   92: aload_0
    //   93: getfield 42	com/vlingo/core/internal/util/ServiceProxyBase:isConnecting	Z
    //   96: ifeq -29 -> 67
    //   99: aload_0
    //   100: aload_1
    //   101: getfield 90	android/os/Message:obj	Ljava/lang/Object;
    //   104: putfield 77	com/vlingo/core/internal/util/ServiceProxyBase:service	Ljava/lang/Object;
    //   107: aload_0
    //   108: iconst_1
    //   109: putfield 44	com/vlingo/core/internal/util/ServiceProxyBase:isConnected	Z
    //   112: aload_0
    //   113: iconst_0
    //   114: putfield 42	com/vlingo/core/internal/util/ServiceProxyBase:isConnecting	Z
    //   117: aload_0
    //   118: invokevirtual 93	com/vlingo/core/internal/util/ServiceProxyBase:executeQueue	()V
    //   121: aload_0
    //   122: getfield 60	com/vlingo/core/internal/util/ServiceProxyBase:handler	Landroid/os/Handler;
    //   125: iconst_5
    //   126: ldc2_w 94
    //   129: invokevirtual 101	android/os/Handler:sendEmptyMessageDelayed	(IJ)Z
    //   132: pop
    //   133: goto -66 -> 67
    //   136: astore_2
    //   137: aload_0
    //   138: monitorexit
    //   139: aload_2
    //   140: athrow
    //   141: aload_0
    //   142: getfield 44	com/vlingo/core/internal/util/ServiceProxyBase:isConnected	Z
    //   145: ifeq -78 -> 67
    //   148: aload_0
    //   149: invokevirtual 93	com/vlingo/core/internal/util/ServiceProxyBase:executeQueue	()V
    //   152: goto -85 -> 67
    //   155: aload_0
    //   156: invokevirtual 104	com/vlingo/core/internal/util/ServiceProxyBase:release	()V
    //   159: goto -92 -> 67
    //   162: aload_0
    //   163: getfield 44	com/vlingo/core/internal/util/ServiceProxyBase:isConnected	Z
    //   166: ifeq -99 -> 67
    //   169: aload_0
    //   170: invokevirtual 108	com/vlingo/core/internal/util/ServiceProxyBase:allowsDelayDisconnect	()Z
    //   173: ifeq +25 -> 198
    //   176: invokestatic 87	android/os/SystemClock:uptimeMillis	()J
    //   179: aload_0
    //   180: getfield 46	com/vlingo/core/internal/util/ServiceProxyBase:lastCommandTime	J
    //   183: lsub
    //   184: ldc2_w 94
    //   187: lcmp
    //   188: ifle +10 -> 198
    //   191: aload_0
    //   192: invokevirtual 104	com/vlingo/core/internal/util/ServiceProxyBase:release	()V
    //   195: goto -128 -> 67
    //   198: aload_0
    //   199: getfield 60	com/vlingo/core/internal/util/ServiceProxyBase:handler	Landroid/os/Handler;
    //   202: iconst_5
    //   203: ldc2_w 94
    //   206: invokevirtual 101	android/os/Handler:sendEmptyMessageDelayed	(IJ)Z
    //   209: pop
    //   210: goto -143 -> 67
    //   213: astore 6
    //   215: goto -148 -> 67
    //
    // Exception table:
    //   from	to	target	type
    //   2	46	136	finally
    //   51	67	136	finally
    //   67	89	136	finally
    //   92	133	136	finally
    //   141	210	136	finally
    //   51	67	213	java/lang/Exception
  }

  private void queue(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
  {
    monitorenter;
    try
    {
      queue(this.handler.obtainMessage(paramInt1, paramInt2, paramInt3, paramObject));
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

  private void queue(Message paramMessage)
  {
    monitorenter;
    if (paramMessage != null);
    try
    {
      this.pendingRequestQueue.add(paramMessage);
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

  protected boolean allowsDelayDisconnect()
  {
    return true;
  }

  public void connect()
  {
    monitorenter;
    try
    {
      if ((this.serviceToken == null) && (this.service == null) && (!this.isConnecting) && (!this.isConnected))
      {
        this.isConnecting = true;
        this.serviceToken = ServiceBindingUtil.bindToService(this.context, getServiceClass(), getInterfaceClass(), new ServiceProxyBase.2(this));
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

  protected void execute(int paramInt)
  {
    monitorenter;
    try
    {
      execute(paramInt, 0, 0, null);
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

  protected void execute(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
  {
    monitorenter;
    try
    {
      if (this.isConnected)
        this.handler.sendMessage(this.handler.obtainMessage(paramInt1, paramInt2, paramInt3, paramObject));
      while (true)
      {
        return;
        if (!this.isConnecting)
          connect();
        if (paramInt1 == 4)
          continue;
        queue(paramInt1, paramInt2, paramInt3, paramObject);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void execute(int paramInt, Object paramObject)
  {
    monitorenter;
    try
    {
      execute(paramInt, 0, 0, paramObject);
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

  protected void executeQueue()
  {
    monitorenter;
    try
    {
      if ((this.service == null) || (this.pendingRequestQueue.size() <= 0))
        break label70;
      Iterator localIterator = this.pendingRequestQueue.iterator();
      while (localIterator.hasNext())
      {
        Message localMessage = (Message)localIterator.next();
        this.handler.sendMessage(localMessage);
      }
    }
    finally
    {
      monitorexit;
    }
    this.pendingRequestQueue.clear();
    label70: monitorexit;
  }

  protected abstract Class<IS> getInterfaceClass();

  protected abstract String getMessageName(Message paramMessage);

  protected String getMessageNameBase(Message paramMessage)
  {
    String str;
    switch (paramMessage.what)
    {
    case 3:
    default:
      str = getMessageName(paramMessage);
    case 1:
    case 2:
    case 4:
    case 5:
    }
    while (true)
    {
      return str;
      str = "SERVICE_CONNECTED";
      continue;
      str = "SERVICE_DISCONNECTED";
      continue;
      str = "RUN_QUEUE";
      continue;
      str = "IDLE_DELAY";
    }
  }

  protected IS getService()
  {
    monitorenter;
    try
    {
      Object localObject2 = this.service;
      monitorexit;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }

  protected abstract Class<S> getServiceClass();

  protected abstract void handleMessageForService(Message paramMessage, IS paramIS);

  public void release()
  {
    monitorenter;
    try
    {
      if (this.serviceToken != null)
      {
        this.isConnected = false;
        this.isConnecting = false;
        if (this.pendingRequestQueue.size() > 0)
          this.pendingRequestQueue.clear();
        this.handler.removeMessages(5);
        this.handler.removeMessages(4);
        ServiceBindingUtil.unbindFromService(this.serviceToken);
        this.serviceToken = null;
        this.service = null;
        this.lastCommandTime = 0L;
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ServiceProxyBase
 * JD-Core Version:    0.6.0
 */