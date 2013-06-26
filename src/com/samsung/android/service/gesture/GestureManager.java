package com.samsung.android.service.gesture;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GestureManager
{
  public static final String AIR_MOTION_AIRBROWSE = "air_motion_turn";
  public static final String AIR_MOTION_AIRJUMP = "air_motion_scroll";
  public static final String AIR_MOTION_AIRPIN = "air_motion_clip";
  public static final String AIR_MOTION_CALL_ACCEPT = "air_motion_call_accept";
  public static final String AIR_MOTION_MOVE = "air_motion_item_move";
  public static final String CAMERA_PROVIDER = "camera_provider";
  public static final String IR_PROVIDER = "ir_provider";
  private static final String TAG = "GestureManager";
  public static final String TSP_PROVIDER = "tsp_provider";
  private boolean mBound = false;
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      Log.v("GestureManager", "onServiceConnected");
      GestureManager.access$002(GestureManager.this, IGestureService.Stub.asInterface(paramIBinder));
      GestureManager.access$102(GestureManager.this, true);
      GestureManager.this.mConnectionListener.onServiceConnected();
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      Log.v("GestureManager", "onServiceDisconnected");
      GestureManager.access$102(GestureManager.this, false);
      GestureManager.this.mConnectionListener.onServiceDisconnected();
    }
  };
  private ServiceConnectionListener mConnectionListener;
  private Context mContext;
  private final CopyOnWriteArrayList<GestureListenerDelegate> mListenerDelegates = new CopyOnWriteArrayList();
  private IGestureService mService;

  public GestureManager(Context paramContext, ServiceConnectionListener paramServiceConnectionListener)
  {
    this.mConnectionListener = paramServiceConnectionListener;
    this.mContext = paramContext;
    bindtoService();
  }

  private void bindtoService()
  {
    Intent localIntent = new Intent();
    localIntent.setClassName("com.samsung.android.app.gestureservice", "com.samsung.android.app.gestureservice.GestureService");
    this.mContext.bindService(localIntent, this.mConnection, 1);
  }

  private GestureProviderInfo createProvider(String paramString, Bundle paramBundle)
  {
    GestureProviderInfo localGestureProviderInfo = new GestureProviderInfo();
    localGestureProviderInfo.setName(paramBundle.getString("name"));
    localGestureProviderInfo.setSupportedGestures(paramBundle.getIntegerArrayList("supported_gesture"));
    return localGestureProviderInfo;
  }

  public GestureProviderInfo getProvider(String paramString)
  {
    Object localObject = null;
    if (paramString == null)
      throw new IllegalArgumentException("name==null");
    try
    {
      Bundle localBundle = this.mService.getProviderInfo(paramString);
      if (localBundle != null)
      {
        GestureProviderInfo localGestureProviderInfo = createProvider(paramString, localBundle);
        localObject = localGestureProviderInfo;
      }
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("GestureManager", "getProvider: RemoteException", localRemoteException);
    }
    return localObject;
  }

  public List<String> getProviders()
  {
    try
    {
      List localList2 = this.mService.getProviders();
      localList1 = localList2;
      return localList1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        Log.e("GestureManager", "getProviders: RemoteException", localRemoteException);
        List localList1 = null;
      }
    }
  }

  @Deprecated
  public void registerListener(GestureListener paramGestureListener, String paramString)
  {
    registerListener(paramGestureListener, paramString, "air_motion_scroll");
  }

  public void registerListener(GestureListener paramGestureListener, String paramString1, String paramString2)
  {
    registerListener(paramGestureListener, paramString1, paramString2, true);
  }

  public void registerListener(GestureListener paramGestureListener, String paramString1, String paramString2, boolean paramBoolean)
  {
    if (!this.mBound)
      bindtoService();
    while (true)
    {
      return;
      Log.d("GestureManager", "registerListener");
      if (paramGestureListener == null)
        continue;
      Object localObject = null;
      Iterator localIterator = this.mListenerDelegates.iterator();
      while (localIterator.hasNext())
      {
        GestureListenerDelegate localGestureListenerDelegate = (GestureListenerDelegate)localIterator.next();
        if (!localGestureListenerDelegate.getListener().equals(paramGestureListener))
          continue;
        localObject = localGestureListenerDelegate;
      }
      if (localObject == null)
      {
        localObject = new GestureListenerDelegate(paramGestureListener, null);
        this.mListenerDelegates.add(localObject);
      }
      try
      {
        this.mService.registerCallback((IBinder)localObject, paramString1, paramString2, paramBoolean);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("GestureManager", "RemoteException in registerListener: ", localRemoteException);
      }
    }
  }

  public void resetGestureService(String paramString)
  {
    try
    {
      this.mService.resetGestureService(paramString);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  public void unbindFromService()
  {
    if (this.mBound)
    {
      this.mContext.unbindService(this.mConnection);
      this.mBound = false;
    }
  }

  public void unregisterListener(GestureListener paramGestureListener)
  {
    unregisterListener(paramGestureListener, "camera_provider");
    unregisterListener(paramGestureListener, "ir_provider");
    unregisterListener(paramGestureListener, "tsp_provider");
  }

  public void unregisterListener(GestureListener paramGestureListener, String paramString)
  {
    Log.v("GestureManager", "unregisterListener");
    if (paramGestureListener == null);
    while (true)
    {
      return;
      Object localObject = null;
      Iterator localIterator = this.mListenerDelegates.iterator();
      while (localIterator.hasNext())
      {
        GestureListenerDelegate localGestureListenerDelegate = (GestureListenerDelegate)localIterator.next();
        if (!localGestureListenerDelegate.getListener().equals(paramGestureListener))
          continue;
        localObject = localGestureListenerDelegate;
      }
      if (localObject == null)
        continue;
      try
      {
        if (!this.mService.unregisterCallback(localObject, paramString))
          continue;
        this.mListenerDelegates.remove(localObject);
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("GestureManager", "RemoteException in unregisterListener: ", localRemoteException);
      }
    }
  }

  private class GestureListenerDelegate extends IGestureCallback.Stub
  {
    private Handler mHandler;
    private final GestureListener mListener;

    GestureListenerDelegate(GestureListener paramHandler, Handler arg3)
    {
      this.mListener = paramHandler;
      Object localObject;
      if (localObject == null);
      for (Looper localLooper = GestureManager.this.mContext.getMainLooper(); ; localLooper = localObject.getLooper())
      {
        this.mHandler = new GestureManager.GestureListenerDelegate.1(this, localLooper, GestureManager.this);
        return;
      }
    }

    @Deprecated
    public void gestureCallback(GestureEvent paramGestureEvent)
      throws RemoteException
    {
      Message localMessage = Message.obtain();
      localMessage.what = 0;
      localMessage.obj = paramGestureEvent;
      this.mHandler.sendMessage(localMessage);
    }

    public GestureListener getListener()
    {
      return this.mListener;
    }

    @Deprecated
    public String getListenerInfo()
      throws RemoteException
    {
      return this.mListener.toString();
    }
  }

  public static abstract interface ServiceConnectionListener
  {
    public abstract void onServiceConnected();

    public abstract void onServiceDisconnected();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.android.service.gesture.GestureManager
 * JD-Core Version:    0.6.0
 */