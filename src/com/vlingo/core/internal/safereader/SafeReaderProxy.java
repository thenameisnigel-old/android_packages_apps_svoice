package com.vlingo.core.internal.safereader;

import android.content.Context;
import android.os.Message;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SafeReaderProxy extends SafeReaderServiceProxyBase
{
  private static final int REGISTER_MSG_LISTENER = 27;
  private static final int SAFEREADER_DEINIT = 30;
  private static final int SAFEREADER_GET_LAST_ALERT = 26;
  private static final int SAFEREADER_INIT = 29;
  private static final int SAFEREADER_STATUS = 25;
  private static final int SAFEREADING_START = 23;
  private static final int SAFEREADING_STOP = 24;
  private static final int UNREGISTER_MSG_LISTENER = 28;
  private static SafeReaderProxy instance = null;
  private List<ISafeReaderAlertHandler> safeReaderListeners = new CopyOnWriteArrayList();

  private SafeReaderProxy(Context paramContext)
  {
    super(paramContext);
  }

  public static void deinit()
  {
    if (instance != null)
    {
      instance.release();
      instance = null;
    }
  }

  public static Context getContext()
  {
    return instance.context;
  }

  public static void init(Context paramContext)
  {
    if (instance == null)
      instance = new SafeReaderProxy(paramContext);
  }

  private void registerListener(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    monitorenter;
    try
    {
      if (!this.safeReaderListeners.contains(paramISafeReaderAlertHandler))
        this.safeReaderListeners.add(paramISafeReaderAlertHandler);
      execute(27, paramISafeReaderAlertHandler);
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

  public static void registerSafeReaderListener(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    instance.registerListener(paramISafeReaderAlertHandler);
  }

  private void safeReaderDeinit()
  {
    monitorenter;
    try
    {
      execute(30);
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

  private void safeReaderInit(ISafeReaderServiceEngine paramISafeReaderServiceEngine)
  {
    monitorenter;
    try
    {
      execute(29, paramISafeReaderServiceEngine);
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

  public static void safeReadingDeinit()
  {
    instance.safeReaderDeinit();
  }

  public static void safeReadingInit(ISafeReaderServiceEngine paramISafeReaderServiceEngine)
  {
    instance.safeReaderInit(paramISafeReaderServiceEngine);
  }

  private void startSafeReader()
  {
    monitorenter;
    try
    {
      execute(23);
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

  public static void startSafeReading()
  {
    instance.startSafeReader();
  }

  private void stopSafeReader()
  {
    monitorenter;
    try
    {
      execute(24);
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

  public static void stopSafeReading()
  {
    instance.stopSafeReader();
  }

  private void unregisterListener(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    monitorenter;
    try
    {
      this.safeReaderListeners.remove(paramISafeReaderAlertHandler);
      execute(28, paramISafeReaderAlertHandler);
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

  public static void unregisterSafeReaderListener(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    instance.unregisterListener(paramISafeReaderAlertHandler);
  }

  private void updateSafeReaderStatus()
  {
    monitorenter;
    try
    {
      execute(25);
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

  public static void updateStatus()
  {
    instance.updateSafeReaderStatus();
  }

  protected boolean allowsDelayDisconnect()
  {
    return false;
  }

  protected String getMessageName(Message paramMessage)
  {
    String str;
    switch (paramMessage.what)
    {
    case 26:
    default:
      str = "UNKNOWN";
    case 23:
    case 24:
    case 25:
    case 27:
    case 28:
    case 29:
    case 30:
    }
    while (true)
    {
      return str;
      str = "SAFEREADER_ON";
      continue;
      str = "SAFEREADER_OFF";
      continue;
      str = "SAFEREADER_STATUS";
      continue;
      str = "REGISTER_MSG_LISTENER";
      continue;
      str = "UNREGISTER_MSG_LISTENER";
      continue;
      str = "SAFEREADER_INIT";
      continue;
      str = "SAFEREADER_DEINIT";
    }
  }

  protected void handleMessageForService(Message paramMessage, ISafeReaderServiceEngine paramISafeReaderServiceEngine)
  {
    switch (paramMessage.what)
    {
    case 1:
    case 26:
    default:
    case 27:
    case 28:
    case 24:
    case 23:
    case 25:
    case 29:
    case 30:
    }
    while (true)
    {
      return;
      paramISafeReaderServiceEngine.registerAlertHandler((ISafeReaderAlertHandler)paramMessage.obj);
      continue;
      paramISafeReaderServiceEngine.unregisterAlertHandler((ISafeReaderAlertHandler)paramMessage.obj);
      continue;
      paramISafeReaderServiceEngine.stopSafeReading();
      continue;
      paramISafeReaderServiceEngine.startSafeReading();
      continue;
      paramISafeReaderServiceEngine.broadcastStatusUpdate();
      continue;
      paramISafeReaderServiceEngine.safeReaderInit((ISafeReaderServiceEngine)paramMessage.obj);
      continue;
      paramISafeReaderServiceEngine.safeReaderDeinit();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereader.SafeReaderProxy
 * JD-Core Version:    0.6.0
 */