package com.samsung.lpp;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.HashMap;

public class LPPManager
{
  public static final String ACTION_SERVICE_READY = "com.samsung.lpp.SERVICE_READY";
  public static final int ALL_SERVICE_AVAILABLE = 0;
  public static final int DIRECTION_BOTH = 3;
  public static final int DIRECTION_ENTER = 1;
  public static final int DIRECTION_EXIT = 2;
  public static final int GEOFENCE_AVAILABLE = 32;
  public static final int GEOFENCE_UNAVAILABLE = 64;
  public static final int SERVICE_BATTERY = 16;
  public static final int SERVICE_GPS = 2;
  public static final int SERVICE_LPP = 1;
  public static final int SERVICE_NLP = 4;
  public static final int SERVICE_WIFI = 8;
  private static final String TAG = "LPPManager";
  private HashMap<LPPListener, ListenerTransport> mListeners = new HashMap();
  private HashMap<LPPListener, ListenerTransport> mListenersOnDemand = new HashMap();
  private HashMap<LPPListener, ListenerTransport> mListenersWifi = new HashMap();
  private ILPPManager mService;

  public LPPManager(ILPPManager paramILPPManager)
  {
    this.mService = paramILPPManager;
  }

  // ERROR //
  private boolean _modifyGeoFence(LPPGeoFenceParameter paramLPPGeoFenceParameter, LPPListener paramLPPListener)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: getfield 52	com/samsung/lpp/LPPManager:mListeners	Ljava/util/HashMap;
    //   6: astore 6
    //   8: aload 6
    //   10: monitorenter
    //   11: aload_0
    //   12: getfield 52	com/samsung/lpp/LPPManager:mListeners	Ljava/util/HashMap;
    //   15: aload_2
    //   16: invokevirtual 66	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   19: checkcast 6	com/samsung/lpp/LPPManager$ListenerTransport
    //   22: astore 8
    //   24: aload 8
    //   26: ifnonnull +17 -> 43
    //   29: ldc 35
    //   31: ldc 68
    //   33: invokestatic 74	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   36: pop
    //   37: aload 6
    //   39: monitorexit
    //   40: goto +46 -> 86
    //   43: aload_0
    //   44: getfield 58	com/samsung/lpp/LPPManager:mService	Lcom/samsung/lpp/ILPPManager;
    //   47: aload_1
    //   48: aload 8
    //   50: invokeinterface 80 3 0
    //   55: istore 9
    //   57: aload 6
    //   59: monitorexit
    //   60: iload 9
    //   62: istore_3
    //   63: goto +23 -> 86
    //   66: astore 7
    //   68: aload 6
    //   70: monitorexit
    //   71: aload 7
    //   73: athrow
    //   74: astore 4
    //   76: ldc 35
    //   78: ldc 82
    //   80: aload 4
    //   82: invokestatic 86	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   85: pop
    //   86: iload_3
    //   87: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   11	71	66	finally
    //   2	11	74	android/os/RemoteException
    //   71	74	74	android/os/RemoteException
  }

  private boolean _requestGeoFence(LPPGeoFenceParameter paramLPPGeoFenceParameter, LPPListener paramLPPListener)
  {
    boolean bool;
    try
    {
      synchronized (this.mListeners)
      {
        ListenerTransport localListenerTransport = (ListenerTransport)this.mListeners.get(paramLPPListener);
        if (localListenerTransport == null)
          localListenerTransport = new ListenerTransport(paramLPPListener);
        this.mListeners.put(paramLPPListener, localListenerTransport);
        bool = this.mService.requestGeoFence(paramLPPGeoFenceParameter, localListenerTransport);
      }
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("LPPManager", "requestGeoFence: DeadObjectException", localRemoteException);
      bool = false;
    }
    return bool;
  }

  private boolean _requestGeoFenceWifi(String paramString, LPPListener paramLPPListener)
  {
    boolean bool;
    try
    {
      synchronized (this.mListenersWifi)
      {
        ListenerTransport localListenerTransport = (ListenerTransport)this.mListenersWifi.get(paramLPPListener);
        if (localListenerTransport == null)
          localListenerTransport = new ListenerTransport(paramLPPListener);
        this.mListenersWifi.put(paramLPPListener, localListenerTransport);
        bool = this.mService.requestGeoFenceSetting(paramString, localListenerTransport);
      }
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("LPPManager", "requestGeoFenceSetting: DeadObjectException", localRemoteException);
      bool = false;
    }
    return bool;
  }

  private boolean _requestGeoFenceWifi(String paramString1, String paramString2, int paramInt, LPPListener paramLPPListener)
  {
    boolean bool;
    try
    {
      synchronized (this.mListenersWifi)
      {
        ListenerTransport localListenerTransport = (ListenerTransport)this.mListenersWifi.get(paramLPPListener);
        if (localListenerTransport == null)
          localListenerTransport = new ListenerTransport(paramLPPListener);
        this.mListenersWifi.put(paramLPPListener, localListenerTransport);
        bool = this.mService.requestGeoFenceWifi(paramString1, paramString2, paramInt, localListenerTransport);
      }
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("LPPManager", "requestGeoFenceWifi: DeadObjectException", localRemoteException);
      bool = false;
    }
    return bool;
  }

  public int getDisabledServices()
  {
    int i = 1;
    if (this.mService == null)
      Log.e("LPPManager", "LPP service is not supported");
    while (true)
    {
      return i;
      try
      {
        int j = this.mService.getDisabledServices();
        i = j;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("LPPManager", "RemoteException in getDisabledServices", localRemoteException);
      }
    }
  }

  public Location getLastOnDemandLocation()
  {
    Object localObject = null;
    if (this.mService == null)
      Log.e("LPPManager", "LPP service is not supported");
    while (true)
    {
      return localObject;
      try
      {
        Location localLocation = this.mService.getLastOnDemandLocation();
        localObject = localLocation;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("LPPManager", "RemoteException in getLastOnDemandLocation", localRemoteException);
      }
    }
  }

  public boolean modifyGeoFence(LPPGeoFenceParameter paramLPPGeoFenceParameter, LPPListener paramLPPListener)
  {
    if (this.mService == null)
      Log.e("LPPManager", "LPP service is not supported");
    for (boolean bool = false; ; bool = _modifyGeoFence(paramLPPGeoFenceParameter, paramLPPListener))
    {
      return bool;
      if (paramLPPListener == null)
        throw new IllegalArgumentException("listener==null");
      if (paramLPPGeoFenceParameter == null)
        throw new IllegalArgumentException("parameter==null");
      if ((paramLPPGeoFenceParameter.mDirection != 1) && (paramLPPGeoFenceParameter.mDirection != 2) && (paramLPPGeoFenceParameter.mDirection != 3))
        throw new IllegalArgumentException("Direction is not correct");
      if ((paramLPPGeoFenceParameter.mLat < -90.0D) || (paramLPPGeoFenceParameter.mLat > 90.0D))
        throw new IllegalArgumentException("Latitude is not correct");
      if ((paramLPPGeoFenceParameter.mLon >= -180.0D) && (paramLPPGeoFenceParameter.mLon <= 180.0D))
        continue;
      throw new IllegalArgumentException("Longitude is not correct");
    }
  }

  public void removeGeoFence(LPPListener paramLPPListener)
  {
    if (this.mService == null)
      Log.e("LPPManager", "LPP service is not supported");
    while (true)
    {
      return;
      if (paramLPPListener == null)
        throw new IllegalArgumentException("listener==null");
      try
      {
        ListenerTransport localListenerTransport1 = (ListenerTransport)this.mListeners.remove(paramLPPListener);
        if (localListenerTransport1 == null)
          break label75;
        this.mService.removeGeoFence(localListenerTransport1);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("LPPManager", "removeGeoFence: DeadObjectException", localRemoteException);
      }
      continue;
      label75: ListenerTransport localListenerTransport2 = (ListenerTransport)this.mListenersWifi.remove(paramLPPListener);
      if (localListenerTransport2 == null)
        continue;
      this.mService.removeGeoFenceWifi(localListenerTransport2);
    }
  }

  public boolean requestGeoFence(LPPGeoFenceParameter paramLPPGeoFenceParameter, LPPListener paramLPPListener)
  {
    if (this.mService == null)
      Log.e("LPPManager", "LPP service is not supported");
    for (boolean bool = false; ; bool = _requestGeoFence(paramLPPGeoFenceParameter, paramLPPListener))
    {
      return bool;
      if (paramLPPListener == null)
        throw new IllegalArgumentException("listener==null");
      if (paramLPPGeoFenceParameter == null)
        throw new IllegalArgumentException("parameter==null");
      if ((paramLPPGeoFenceParameter.mDirection != 1) && (paramLPPGeoFenceParameter.mDirection != 2) && (paramLPPGeoFenceParameter.mDirection != 3))
        throw new IllegalArgumentException("Direction is not correct");
      if ((paramLPPGeoFenceParameter.mLat < -90.0D) || (paramLPPGeoFenceParameter.mLat > 90.0D))
        throw new IllegalArgumentException("Latitude is not correct");
      if ((paramLPPGeoFenceParameter.mLon >= -180.0D) && (paramLPPGeoFenceParameter.mLon <= 180.0D))
        continue;
      throw new IllegalArgumentException("Longitude is not correct");
    }
  }

  public boolean requestGeoFence(LPPListener paramLPPListener)
  {
    boolean bool = false;
    if (this.mService == null)
      Log.e("LPPManager", "LPP service is not supported");
    while (true)
    {
      return bool;
      if (paramLPPListener == null)
        throw new IllegalArgumentException("listener==null");
      bool = _requestGeoFenceWifi(null, null, 0, paramLPPListener);
    }
  }

  public boolean requestGeoFence(String paramString, LPPListener paramLPPListener)
  {
    if (this.mService == null)
      Log.e("LPPManager", "LPP service is not supported");
    for (boolean bool = false; ; bool = _requestGeoFenceWifi(paramString, paramLPPListener))
    {
      return bool;
      if (paramLPPListener == null)
        throw new IllegalArgumentException("listener==null");
      if (paramString != null)
        continue;
      throw new IllegalArgumentException("settingsString==null");
    }
  }

  public boolean requestGeoFence(String paramString1, String paramString2, int paramInt, LPPListener paramLPPListener)
  {
    if (this.mService == null)
      Log.e("LPPManager", "LPP service is not supported");
    for (boolean bool = false; ; bool = _requestGeoFenceWifi(paramString1, paramString2, paramInt, paramLPPListener))
    {
      return bool;
      if (paramLPPListener == null)
        throw new IllegalArgumentException("listener==null");
      if ((paramString1 != null) && (paramString2 != null))
        continue;
      throw new IllegalArgumentException("parameter is null");
    }
  }

  public void requestOnDemandLocation(LPPListener paramLPPListener)
  {
    if (this.mService == null)
      Log.e("LPPManager", "LPP service is not supported");
    while (true)
    {
      return;
      if (paramLPPListener == null)
        throw new IllegalArgumentException("listener==null");
      try
      {
        synchronized (this.mListenersOnDemand)
        {
          ListenerTransport localListenerTransport = (ListenerTransport)this.mListenersOnDemand.get(paramLPPListener);
          if (localListenerTransport == null)
            localListenerTransport = new ListenerTransport(paramLPPListener);
          this.mListenersOnDemand.put(paramLPPListener, localListenerTransport);
          this.mService.requestOnDemandLocation(localListenerTransport);
        }
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("LPPManager", "requestOnDemandLocation: DeadObjectException", localRemoteException);
      }
    }
  }

  private class ListenerTransport extends ILPPListener.Stub
  {
    private static final int TYPE_GEOFENCE_AVAILABILITY = 4;
    private static final int TYPE_GEOFENCE_DETECTED = 1;
    private static final int TYPE_GEOFENCE_WIFI_RESULT = 5;
    private static final int TYPE_LOCATION_REPORTED = 6;
    private static final int TYPE_STATUS_DISABLED = 3;
    private static final int TYPE_STATUS_ENABLED = 2;
    private LPPListener mListener;
    private final Handler mListenerHandler;

    ListenerTransport(LPPListener arg2)
    {
      Object localObject;
      this.mListener = localObject;
      this.mListenerHandler = new LPPManager.ListenerTransport.1(this, LPPManager.this);
    }

    private void _handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 4:
      case 2:
      case 3:
      case 5:
      case 6:
      }
      while (true)
      {
        return;
        this.mListener.onGeoFenceDetected(paramMessage.arg1, (LPPGeoFenceParameter)paramMessage.obj);
        continue;
        this.mListener.onGeoFenceAvailabilityCallback(paramMessage.arg1, (LPPGeoFenceParameter)paramMessage.obj);
        continue;
        this.mListener.onStatusEnabled(paramMessage.arg1);
        continue;
        this.mListener.onStatusDisabled(paramMessage.arg1);
        continue;
        this.mListener.onWifiGeoFenceRequestResult(paramMessage.arg1);
        if (paramMessage.arg1 != 64)
          continue;
        LPPManager.this.removeGeoFence(this.mListener);
        continue;
        this.mListener.onLocationReported((Location)paramMessage.obj);
      }
    }

    public void onGeoFenceAvailabilityCallback(int paramInt, LPPGeoFenceParameter paramLPPGeoFenceParameter)
    {
      Message localMessage = Message.obtain();
      localMessage.what = 4;
      localMessage.arg1 = paramInt;
      localMessage.obj = paramLPPGeoFenceParameter;
      this.mListenerHandler.sendMessage(localMessage);
    }

    public void onGeoFenceDetected(int paramInt, LPPGeoFenceParameter paramLPPGeoFenceParameter)
    {
      Message localMessage = Message.obtain();
      localMessage.what = 1;
      localMessage.arg1 = paramInt;
      localMessage.obj = paramLPPGeoFenceParameter;
      this.mListenerHandler.sendMessage(localMessage);
    }

    public void onLocationReported(Location paramLocation)
    {
      Message localMessage = Message.obtain();
      localMessage.what = 6;
      localMessage.obj = paramLocation;
      this.mListenerHandler.sendMessage(localMessage);
    }

    public void onStatusDisabled(int paramInt)
    {
      Message localMessage = Message.obtain();
      localMessage.what = 3;
      localMessage.arg1 = paramInt;
      this.mListenerHandler.sendMessage(localMessage);
    }

    public void onStatusEnabled(int paramInt)
    {
      Message localMessage = Message.obtain();
      localMessage.what = 2;
      localMessage.arg1 = paramInt;
      this.mListenerHandler.sendMessage(localMessage);
    }

    public void onWifiGeoFenceRequestResult(int paramInt)
    {
      Message localMessage = Message.obtain();
      localMessage.what = 5;
      localMessage.arg1 = paramInt;
      this.mListenerHandler.sendMessage(localMessage);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.lpp.LPPManager
 * JD-Core Version:    0.6.0
 */