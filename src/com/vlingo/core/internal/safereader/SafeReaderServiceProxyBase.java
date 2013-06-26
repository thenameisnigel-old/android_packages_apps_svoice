package com.vlingo.core.internal.safereader;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class SafeReaderServiceProxyBase
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
  private ServiceBinder sb;
  private ISafeReaderServiceEngine service;
  private ServiceToken serviceToken;

  public SafeReaderServiceProxyBase(Context paramContext)
  {
    this.context = paramContext;
    this.handler = new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        SafeReaderServiceProxyBase.this.handleBase(paramMessage);
      }
    };
  }

  private ServiceToken bindToService(Context paramContext, ServiceConnection paramServiceConnection)
  {
    ContextWrapper localContextWrapper = new ContextWrapper(paramContext);
    localContextWrapper.startService(new Intent(localContextWrapper, getSafeReaderServiceClass()));
    this.sb = new ServiceBinder(paramServiceConnection);
    if (localContextWrapper.bindService(new Intent().setClass(localContextWrapper, getSafeReaderServiceClass()), this.sb, 0));
    for (ServiceToken localServiceToken = new ServiceToken(localContextWrapper); ; localServiceToken = null)
      return localServiceToken;
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
    return false;
  }

  public void connect()
  {
    monitorenter;
    try
    {
      if ((this.serviceToken == null) && (this.service == null) && (!this.isConnecting) && (!this.isConnected))
      {
        this.isConnecting = true;
        this.serviceToken = bindToService(this.context, new ServiceConnection()
        {
          public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
          {
            SafeReaderServiceProxyBase.this.handler.sendMessage(SafeReaderServiceProxyBase.this.handler.obtainMessage(1, paramIBinder));
          }

          public void onServiceDisconnected(ComponentName paramComponentName)
          {
            SafeReaderServiceProxyBase.this.handler.sendMessage(SafeReaderServiceProxyBase.this.handler.obtainMessage(2));
          }
        });
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

  protected Class<SafeReaderService> getSafeReaderServiceClass()
  {
    return SafeReaderService.class;
  }

  // ERROR //
  protected void handleBase(Message paramMessage)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: getfield 173	android/os/Message:what	I
    //   6: tableswitch	default:+34 -> 40, 1:+86->92, 2:+152->158, 3:+34->40, 4:+138->144, 5:+159->165
    //   41: getfield 54	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:isConnected	Z
    //   44: istore 5
    //   46: iload 5
    //   48: ifeq +19 -> 67
    //   51: aload_0
    //   52: getfield 128	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:service	Lcom/vlingo/core/internal/safereader/ISafeReaderServiceEngine;
    //   55: ifnull +12 -> 67
    //   58: aload_0
    //   59: aload_1
    //   60: aload_0
    //   61: getfield 128	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:service	Lcom/vlingo/core/internal/safereader/ISafeReaderServiceEngine;
    //   64: invokevirtual 188	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:handleMessageForService	(Landroid/os/Message;Lcom/vlingo/core/internal/safereader/ISafeReaderServiceEngine;)V
    //   67: aload_0
    //   68: getfield 54	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:isConnected	Z
    //   71: ifeq +18 -> 89
    //   74: aload_1
    //   75: getfield 173	android/os/Message:what	I
    //   78: iconst_5
    //   79: if_icmpeq +10 -> 89
    //   82: aload_0
    //   83: invokestatic 194	android/os/SystemClock:uptimeMillis	()J
    //   86: putfield 56	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:lastCommandTime	J
    //   89: aload_0
    //   90: monitorexit
    //   91: return
    //   92: aload_0
    //   93: getfield 52	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:isConnecting	Z
    //   96: ifeq -29 -> 67
    //   99: aload_0
    //   100: aload_1
    //   101: getfield 198	android/os/Message:obj	Ljava/lang/Object;
    //   104: checkcast 200	com/vlingo/core/internal/safereader/ISafeReaderServiceEngine
    //   107: putfield 128	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:service	Lcom/vlingo/core/internal/safereader/ISafeReaderServiceEngine;
    //   110: aload_0
    //   111: iconst_1
    //   112: putfield 54	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:isConnected	Z
    //   115: aload_0
    //   116: iconst_0
    //   117: putfield 52	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:isConnecting	Z
    //   120: aload_0
    //   121: invokevirtual 202	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:executeQueue	()V
    //   124: aload_0
    //   125: getfield 68	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:handler	Landroid/os/Handler;
    //   128: iconst_5
    //   129: ldc2_w 203
    //   132: invokevirtual 208	android/os/Handler:sendEmptyMessageDelayed	(IJ)Z
    //   135: pop
    //   136: goto -69 -> 67
    //   139: astore_2
    //   140: aload_0
    //   141: monitorexit
    //   142: aload_2
    //   143: athrow
    //   144: aload_0
    //   145: getfield 54	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:isConnected	Z
    //   148: ifeq -81 -> 67
    //   151: aload_0
    //   152: invokevirtual 202	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:executeQueue	()V
    //   155: goto -88 -> 67
    //   158: aload_0
    //   159: invokevirtual 211	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:release	()V
    //   162: goto -95 -> 67
    //   165: aload_0
    //   166: getfield 54	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:isConnected	Z
    //   169: ifeq -102 -> 67
    //   172: aload_0
    //   173: invokevirtual 213	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:allowsDelayDisconnect	()Z
    //   176: ifeq +25 -> 201
    //   179: invokestatic 194	android/os/SystemClock:uptimeMillis	()J
    //   182: aload_0
    //   183: getfield 56	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:lastCommandTime	J
    //   186: lsub
    //   187: ldc2_w 203
    //   190: lcmp
    //   191: ifle +10 -> 201
    //   194: aload_0
    //   195: invokevirtual 211	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:release	()V
    //   198: goto -131 -> 67
    //   201: aload_0
    //   202: getfield 68	com/vlingo/core/internal/safereader/SafeReaderServiceProxyBase:handler	Landroid/os/Handler;
    //   205: iconst_5
    //   206: ldc2_w 203
    //   209: invokevirtual 208	android/os/Handler:sendEmptyMessageDelayed	(IJ)Z
    //   212: pop
    //   213: goto -146 -> 67
    //   216: astore 6
    //   218: goto -151 -> 67
    //
    // Exception table:
    //   from	to	target	type
    //   2	46	139	finally
    //   51	67	139	finally
    //   67	89	139	finally
    //   92	136	139	finally
    //   144	213	139	finally
    //   51	67	216	java/lang/Exception
  }

  protected abstract void handleMessageForService(Message paramMessage, ISafeReaderServiceEngine paramISafeReaderServiceEngine);

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
        unbindFromService(this.serviceToken);
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

  public void unbindFromService(ServiceToken paramServiceToken)
  {
    if (paramServiceToken == null);
    while (true)
    {
      return;
      ContextWrapper localContextWrapper = paramServiceToken.mWrappedContext;
      if (this.sb == null)
        continue;
      localContextWrapper.unbindService(this.sb);
      this.sb = null;
    }
  }

  protected static class ServiceBinder
    implements ServiceConnection
  {
    ServiceConnection mCallback;

    ServiceBinder(ServiceConnection paramServiceConnection)
    {
      this.mCallback = paramServiceConnection;
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      if (this.mCallback != null)
        this.mCallback.onServiceConnected(paramComponentName, paramIBinder);
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      if (this.mCallback != null)
        this.mCallback.onServiceDisconnected(paramComponentName);
    }
  }

  protected static class ServiceToken
  {
    public ContextWrapper mWrappedContext;

    public ServiceToken(ContextWrapper paramContextWrapper)
    {
      this.mWrappedContext = paramContextWrapper;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereader.SafeReaderServiceProxyBase
 * JD-Core Version:    0.6.0
 */