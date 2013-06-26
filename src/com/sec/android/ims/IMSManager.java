package com.sec.android.ims;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.sec.android.ims.message.IMessageEnabler;
import com.sec.android.internal.ims.IIMSService;
import com.sec.android.internal.ims.IIMSService.Stub;

public class IMSManager extends Handler
{
  private static final boolean DEBUG = true;
  protected static final int EVENT_REGISTER = 1;
  protected static final int EVENT_REGISTER_ISIM = 2;
  protected static final int EVENT_REGISTER_LISTENER = 5;
  protected static final int EVENT_UNREGISTER = 3;
  protected static final int EVENT_UNREGISTER_ISIM = 4;
  protected static final int EVENT_UNREGISTER_LISTENER = 6;
  private static final String LOG_TAG = "IMSManager";
  public static final String PROPERTY_IMS_MODE = "persist.sys.ims.mode";
  public static final String PROPERTY_IMS_REGISTERED = "persist.sys.ims.reg";
  public static final String PROPERTY_IMS_REGISTERED_TAG = "persist.sys.ims.regtag";
  private static IMSManager imsManager = null;
  private int events;
  private boolean isIt3gNetwork;
  private byte[] isimResponse;
  private IMSEventListener listener;
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      try
      {
        IMSManager.access$700("onServiceConnected: IIMSService");
        IMSManager.access$102(IMSManager.this, IIMSService.Stub.asInterface(paramIBinder));
        if (IMSManager.this.listener != null)
        {
          IMSManager.this.mHandler.sendMessage(IMSManager.this.obtainMessage(5));
          IMSManager.this.listener.handleEvent(1001, -1, -1, -1, null, null);
        }
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      IMSManager.access$102(IMSManager.this, null);
    }
  };
  private Context mContext;
  ManagerHandler mHandler;
  private IIMSService mService;
  ServiceSender mServiceSender;
  Thread mServiceSenderThread;
  private String pcscfAddr;

  private IMSManager(Context paramContext)
  {
    this.mContext = paramContext;
    Intent localIntent = new Intent("com.sec.android.ims.IMSService");
    this.mContext.bindService(localIntent, this.mConnection, 1);
    this.mServiceSender = new ServiceSender();
    this.mServiceSenderThread = new Thread(this.mServiceSender, "IMSManager");
    this.mServiceSenderThread.start();
    log("IMSManager getting created, mService: " + this.mService);
  }

  public static IMSManager getInstance(Context paramContext)
  {
    monitorenter;
    try
    {
      if (imsManager == null)
        imsManager = new IMSManager(paramContext);
      IMSManager localIMSManager = imsManager;
      monitorexit;
      return localIMSManager;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private static void log(String paramString)
  {
    Log.e("IMSManager", paramString);
  }

  static String requestToString(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default:
      str = "<unknown event>";
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return str;
      str = "EVENT_REGISTER";
      continue;
      str = "EVENT_REGISTER_ISIM";
      continue;
      str = "EVENT_UNREGISTER";
      continue;
      str = "EVENT_UNREGISTER_ISIM";
      continue;
      str = "EVENT_REGISTER_LISTENER";
      continue;
      str = "EVENT_UNREGISTER_LISTENER";
    }
  }

  public IMessageEnabler getMessageEnabler()
  {
    try
    {
      IMessageEnabler localIMessageEnabler2 = this.mService.getMessageEnabler();
      localIMessageEnabler1 = localIMessageEnabler2;
      return localIMessageEnabler1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        localRemoteException.printStackTrace();
        IMessageEnabler localIMessageEnabler1 = null;
      }
    }
  }

  public boolean isRegistered()
  {
    try
    {
      boolean bool2 = this.mService.isRegistered();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        localRemoteException.printStackTrace();
        boolean bool1 = false;
      }
    }
  }

  public void register(String paramString, boolean paramBoolean)
  {
    log("[Service]< " + requestToString(1));
    this.pcscfAddr = paramString;
    this.isIt3gNetwork = paramBoolean;
    this.mHandler.sendMessage(obtainMessage(1));
  }

  public void register(String paramString, byte[] paramArrayOfByte)
  {
    log("[Service]< " + requestToString(2));
    this.pcscfAddr = paramString;
    this.isimResponse = paramArrayOfByte;
    this.mHandler.sendMessage(obtainMessage(2));
  }

  public void registerListener(IMSEventListener paramIMSEventListener, int paramInt)
  {
    log("[Service]< " + requestToString(5));
    this.listener = paramIMSEventListener;
    this.events = paramInt;
    if (this.mService != null)
      this.mHandler.sendMessage(obtainMessage(5));
    while (true)
    {
      return;
      log("Service isn't connected ");
    }
  }

  public void unregister()
  {
    log("[Service]< " + requestToString(3));
    this.mHandler.sendMessage(obtainMessage(3));
  }

  public void unregister(String paramString, byte[] paramArrayOfByte)
  {
    log("[Service]< " + requestToString(4));
    this.pcscfAddr = paramString;
    this.isimResponse = paramArrayOfByte;
    this.mHandler.sendMessage(obtainMessage(4));
  }

  public void unregisterListener(IMSEventListener paramIMSEventListener)
  {
    log("[Service]< " + requestToString(6));
    this.listener = paramIMSEventListener;
    this.mHandler.sendMessage(obtainMessage(6));
  }

  private final class ManagerHandler extends Handler
  {
    private ManagerHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      Log.d("IMSManager", "IMS ServiceSender handleMessage : " + paramMessage.what);
      if (IMSManager.this.mService == null)
        Log.e("IMSManager", "<<<<<<<Not Yet Connected to IMS service>>>>>>>>>>");
      while (true)
      {
        return;
        switch (paramMessage.what)
        {
        case 4:
        default:
          break;
        case 1:
          try
          {
            IMSManager.this.mService.register(IMSManager.this.pcscfAddr, IMSManager.this.isIt3gNetwork);
          }
          catch (RemoteException localRemoteException5)
          {
            localRemoteException5.printStackTrace();
          }
          break;
        case 2:
          try
          {
            IMSManager.this.mService.registerWithISIMResponse(IMSManager.this.pcscfAddr, IMSManager.this.isimResponse);
          }
          catch (RemoteException localRemoteException4)
          {
            localRemoteException4.printStackTrace();
          }
          break;
        case 3:
          try
          {
            IMSManager.this.mService.unregister();
          }
          catch (RemoteException localRemoteException3)
          {
            localRemoteException3.printStackTrace();
          }
          break;
        case 5:
          try
          {
            if (IMSManager.this.mService == null)
              continue;
            IMSManager.this.mService.registerListener(IMSManager.this.listener.callback, IMSManager.this.events);
          }
          catch (RemoteException localRemoteException2)
          {
            localRemoteException2.printStackTrace();
          }
          break;
        case 6:
          try
          {
            if (IMSManager.this.mService != null)
              IMSManager.this.mService.unregisterListener(IMSManager.this.listener.callback);
            IMSManager.access$502(IMSManager.this, null);
          }
          catch (RemoteException localRemoteException1)
          {
            localRemoteException1.printStackTrace();
          }
        }
      }
    }
  }

  class ServiceSender
    implements Runnable
  {
    ServiceSender()
    {
      Log.d("IMSManager", "IMS ServiceSender");
    }

    public void run()
    {
      try
      {
        while (true)
        {
          Looper.prepare();
          IMSManager.this.mHandler = new IMSManager.ManagerHandler(IMSManager.this, null);
          Looper.loop();
        }
      }
      catch (Throwable localThrowable)
      {
        Log.e("IMSManager", "Uncaught exception", localThrowable);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.ims.IMSManager
 * JD-Core Version:    0.6.0
 */