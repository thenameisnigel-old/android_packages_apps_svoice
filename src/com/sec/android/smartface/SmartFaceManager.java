package com.sec.android.smartface;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SmartFaceManager
{
  public static final int SERVICETYPE_MOTION = 16;
  public static final int SERVICETYPE_PAUSE = 2;
  public static final int SERVICETYPE_ROTATION = 8;
  public static final int SERVICETYPE_SAMPLE = 1;
  public static final int SERVICETYPE_SCROLL = 1;
  public static final int SERVICETYPE_STAY = 4;
  public static final String SMARTFACE_SERVICE = "samsung.smartfaceservice";
  private static final String TAG = "SmartFaceManager";
  private final Condition complete = this.lock.newCondition();
  private final Lock lock = new ReentrantLock();
  private int mCallbackData;
  private SmartFaceClient mClient = null;
  private EventHandler mEventHandler = null;
  private SmartFaceInfoListener mListener = null;
  private ISmartFaceService mService = null;

  private SmartFaceManager(ISmartFaceService paramISmartFaceService)
  {
    this.mService = paramISmartFaceService;
    this.mClient = new SmartFaceClient();
    Looper localLooper1 = Looper.myLooper();
    if (localLooper1 != null)
      this.mEventHandler = new EventHandler(localLooper1);
    while (true)
    {
      return;
      Looper localLooper2 = Looper.getMainLooper();
      if (localLooper2 != null)
      {
        this.mEventHandler = new EventHandler(localLooper2);
        continue;
      }
      this.mEventHandler = null;
    }
  }

  public static SmartFaceManager getSmartFaceManager()
  {
    IBinder localIBinder = ServiceManager.getService("samsung.smartfaceservice");
    SmartFaceManager localSmartFaceManager;
    if (localIBinder == null)
    {
      Log.e("SmartFaceManager", "Fail binding the service. SmartFaceService may not be running properly.");
      localSmartFaceManager = null;
    }
    while (true)
    {
      return localSmartFaceManager;
      localSmartFaceManager = new SmartFaceManager(ISmartFaceService.Stub.asInterface(localIBinder));
      Log.d("SmartFaceManager", "A new instance of SmartFaceManager is created");
    }
  }

  public boolean checkForSmartRotation(int paramInt)
  {
    int i = 0;
    HandlerThread localHandlerThread = new HandlerThread("Smart Rotation Wait Thread");
    localHandlerThread.start();
    this.mEventHandler = new EventHandler(localHandlerThread.getLooper());
    setListener(new SmartFaceInfoListener()
    {
      public void onInfo(FaceInfo paramFaceInfo, int paramInt)
      {
        Log.e("SmartFaceManager", "checkForSmartRotation onInfo: " + Integer.toBinaryString(paramInt) + ": " + paramFaceInfo.needToRotate);
        if ((paramInt & 0x8) != 0)
        {
          SmartFaceManager.this.lock.lock();
          SmartFaceManager.access$102(SmartFaceManager.this, paramFaceInfo.needToRotate);
          SmartFaceManager.this.complete.signal();
          SmartFaceManager.this.lock.unlock();
        }
      }
    });
    this.lock.lock();
    start(8);
    this.mCallbackData = -1;
    this.complete.awaitUninterruptibly();
    if (this.mCallbackData > 0)
      i = 1;
    this.mCallbackData = -1;
    this.complete.awaitUninterruptibly();
    if (this.mCallbackData > 0)
      i = 1;
    this.lock.unlock();
    stop();
    localHandlerThread.quit();
    return i;
  }

  public boolean checkForSmartStay()
  {
    int i = 0;
    HandlerThread localHandlerThread = new HandlerThread("Smart Stay Wait Thread");
    localHandlerThread.start();
    this.mEventHandler = new EventHandler(localHandlerThread.getLooper());
    setListener(new SmartFaceInfoListener()
    {
      public void onInfo(FaceInfo paramFaceInfo, int paramInt)
      {
        Log.e("SmartFaceManager", "checkForSmartStay onInfo: " + Integer.toBinaryString(paramInt) + ": " + paramFaceInfo.needToStay);
        if ((paramInt & 0x4) != 0)
        {
          SmartFaceManager.this.lock.lock();
          SmartFaceManager.access$102(SmartFaceManager.this, paramFaceInfo.needToStay);
          SmartFaceManager.this.complete.signal();
          SmartFaceManager.this.lock.unlock();
        }
      }
    });
    this.lock.lock();
    start(4);
    this.mCallbackData = -1;
    this.complete.awaitUninterruptibly();
    if (this.mCallbackData > 0)
      i = 1;
    this.mCallbackData = -1;
    this.complete.awaitUninterruptibly();
    if (this.mCallbackData > 0)
      i = 1;
    this.lock.unlock();
    stop();
    localHandlerThread.quit();
    return i;
  }

  public int getSupportedServices()
  {
    try
    {
      int j = this.mService.getSupportedServices();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        localRemoteException.printStackTrace();
        int i = 0;
      }
    }
  }

  public void setListener(SmartFaceInfoListener paramSmartFaceInfoListener)
  {
    this.mListener = paramSmartFaceInfoListener;
  }

  public void start(int paramInt)
  {
    try
    {
      this.mService.register(this.mClient, paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  public void stop()
  {
    try
    {
      this.mService.unregister(this.mClient);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  private class EventHandler extends Handler
  {
    public EventHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (SmartFaceManager.this.mListener != null)
        SmartFaceManager.this.mListener.onInfo((FaceInfo)paramMessage.obj, paramMessage.what);
      while (true)
      {
        return;
        Log.e("SmartFaceManager", "Listener is null");
      }
    }
  }

  private class SmartFaceClient extends ISmartFaceClient.Stub
  {
    SmartFaceClient()
    {
      Log.e("SmartFaceManager", "New SmartFaceClient");
    }

    public void onInfo(FaceInfo paramFaceInfo, int paramInt)
    {
      if (SmartFaceManager.this.mEventHandler != null)
      {
        Message localMessage = SmartFaceManager.this.mEventHandler.obtainMessage(paramInt, 0, 0, paramFaceInfo);
        SmartFaceManager.this.mEventHandler.sendMessage(localMessage);
      }
      while (true)
      {
        return;
        Log.e("SmartFaceManager", "EventHandler is null");
      }
    }
  }

  public static abstract interface SmartFaceInfoListener
  {
    public abstract void onInfo(FaceInfo paramFaceInfo, int paramInt);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.smartface.SmartFaceManager
 * JD-Core Version:    0.6.0
 */