package com.vlingo.core.internal.safereader;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import java.lang.ref.WeakReference;

public class SafeReaderService extends Service
{
  public static final String ACTION_SAFEREADER_DEINIT = "com.vlingo.client.safereader.ACTION_SAFEREADER_DEINIT";
  public static final String ACTION_SAFEREADER_OFF = "com.vlingo.client.safereader.ACTION_SAFEREADER_OFF";
  public static final String ACTION_SAFEREADER_ON = "com.vlingo.client.safereader.ACTION_SAFEREADER_ON";
  public static final String ACTION_SAFEREADER_PAUSE = "com.vlingo.client.safereader.ACTION_SAFEREADER_PAUSE";
  public static final String ACTION_SAFEREADER_RESUME = "com.vlingo.client.safereader.ACTION_SAFEREADER_RESUME";
  public static final String ACTION_SKIP_CURRENT_ITEM = "com.vlingo.client.safereader.ACTION_SKIP_CURRENT_ITEM";
  public static final String ACTION_UPDATE_STATUS = "com.vlingo.client.safereader.ACTION_UPDATE_STATUS";
  public static final String ACTION_UPDATE_WIDGETS = "com.vlingo.client.safereader.ACTION_UPDATE_WIDGETS";
  private int NumClients = 0;
  private final BroadcastReceiver intentReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent != null)
        SafeReaderService.this.handleIntent(paramContext, paramIntent);
    }
  };
  private final IBinder mBinder = new ServiceStub(this);
  private ISafeReaderServiceEngine safeReaderEngine;

  private void stopServiceIfPossible()
  {
    if ((this.NumClients <= 0) && (!isSafeReaderOn()))
      stopSelf();
  }

  public void broadcastStatusUpdate()
  {
    if (this.safeReaderEngine == null);
    while (true)
    {
      return;
      this.safeReaderEngine.broadcastStatusUpdate();
    }
  }

  public void handleIntent(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if ("com.vlingo.client.safereader.ACTION_SAFEREADER_ON".equals(str))
      startSafeReading();
    while (true)
    {
      return;
      if ("com.vlingo.client.safereader.ACTION_SAFEREADER_OFF".equals(str))
      {
        stopSafeReading();
        continue;
      }
      if ("com.vlingo.client.safereader.ACTION_SAFEREADER_DEINIT".equals(str))
      {
        safeReaderDeinit();
        continue;
      }
      if ("com.vlingo.client.safereader.ACTION_SKIP_CURRENT_ITEM".equals(str))
      {
        skipCurrentlyPlayingItem();
        continue;
      }
      if ("com.vlingo.client.safereader.ACTION_UPDATE_STATUS".equals(str))
      {
        broadcastStatusUpdate();
        continue;
      }
      if ("com.vlingo.client.safereader.ACTION_SAFEREADER_PAUSE".equals(str))
      {
        pause();
        continue;
      }
      if ("com.vlingo.client.safereader.ACTION_SAFEREADER_RESUME".equals(str))
      {
        resume();
        continue;
      }
      if (!"com.vlingo.client.safereader.ACTION_UPDATE_WIDGETS".equals(str))
        continue;
    }
  }

  public boolean isSafeReaderOn()
  {
    if (this.safeReaderEngine == null);
    for (boolean bool = false; ; bool = this.safeReaderEngine.isSafeReaderOn())
      return bool;
  }

  public IBinder onBind(Intent paramIntent)
  {
    this.NumClients = (1 + this.NumClients);
    return this.mBinder;
  }

  public void onCreate()
  {
    super.onCreate();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.vlingo.client.safereader.ACTION_SAFEREADER_ON");
    localIntentFilter.addAction("com.vlingo.client.safereader.ACTION_SAFEREADER_OFF");
    localIntentFilter.addAction("com.vlingo.client.safereader.ACTION_SAFEREADER_DEINIT");
    localIntentFilter.addAction("com.vlingo.client.safereader.ACTION_SKIP_CURRENT_ITEM");
    localIntentFilter.addAction("com.vlingo.client.safereader.ACTION_UPDATE_STATUS");
    localIntentFilter.addAction("com.vlingo.client.safereader.ACTION_SAFEREADER_PAUSE");
    localIntentFilter.addAction("com.vlingo.client.safereader.ACTION_SAFEREADER_RESUME");
    localIntentFilter.addAction("com.vlingo.client.safereader.ACTION_UPDATE_WIDGETS");
    registerReceiver(this.intentReceiver, localIntentFilter);
  }

  public void onDestroy()
  {
    unregisterReceiver(this.intentReceiver);
    stopSafeReading();
    super.onDestroy();
  }

  public void onRebind(Intent paramIntent)
  {
    this.NumClients = (1 + this.NumClients);
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent != null)
      handleIntent(null, paramIntent);
    return 1;
  }

  public boolean onUnbind(Intent paramIntent)
  {
    this.NumClients = (-1 + this.NumClients);
    stopServiceIfPossible();
    return false;
  }

  public void pause()
  {
    if (this.safeReaderEngine == null);
    while (true)
    {
      return;
      if (isSafeReaderOn())
      {
        this.safeReaderEngine.pause();
        continue;
      }
    }
  }

  public void registerListener(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    if (this.safeReaderEngine == null);
    while (true)
    {
      return;
      this.safeReaderEngine.registerAlertHandler(paramISafeReaderAlertHandler);
    }
  }

  public void resume()
  {
    if (this.safeReaderEngine == null);
    while (true)
    {
      return;
      if (isSafeReaderOn())
      {
        this.safeReaderEngine.resume();
        continue;
      }
    }
  }

  public void safeReaderDeinit()
  {
    if (this.safeReaderEngine == null);
    while (true)
    {
      return;
      stopSafeReading();
      stopServiceIfPossible();
      this.safeReaderEngine.safeReaderDeinit();
    }
  }

  public void safeReaderInit(ISafeReaderServiceEngine paramISafeReaderServiceEngine)
  {
    if (paramISafeReaderServiceEngine != null)
    {
      if (this.safeReaderEngine == null)
        break label31;
      if (this.safeReaderEngine.getClass() != paramISafeReaderServiceEngine.getClass())
        break label26;
    }
    while (true)
    {
      return;
      label26: this.safeReaderEngine = null;
      label31: this.safeReaderEngine = paramISafeReaderServiceEngine;
      this.safeReaderEngine.safeReaderInit(paramISafeReaderServiceEngine);
    }
  }

  public void skipCurrentlyPlayingItem()
  {
    if (this.safeReaderEngine == null);
    while (true)
    {
      return;
      if (isSafeReaderOn())
      {
        this.safeReaderEngine.skipCurrentlyPlayingItem();
        continue;
      }
    }
  }

  public void startSafeReading()
  {
    if (this.safeReaderEngine == null);
    while (true)
    {
      return;
      if (!isSafeReaderOn())
      {
        this.safeReaderEngine.startSafeReading();
        if (!this.safeReaderEngine.doesSupportNotifications())
          continue;
        startForeground(this.safeReaderEngine.getNotificationId(), this.safeReaderEngine.getNotification());
        continue;
      }
    }
  }

  public void stopSafeReading()
  {
    if (this.safeReaderEngine == null);
    while (true)
    {
      return;
      if (isSafeReaderOn())
      {
        this.safeReaderEngine.stopSafeReading();
        if (!this.safeReaderEngine.doesSupportNotifications())
          continue;
        stopForeground(true);
        continue;
      }
    }
  }

  public void unregisterListener(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    if (this.safeReaderEngine == null);
    while (true)
    {
      return;
      this.safeReaderEngine.unregisterAlertHandler(paramISafeReaderAlertHandler);
    }
  }

  static class ServiceStub extends Binder
    implements ISafeReaderServiceEngine
  {
    WeakReference<SafeReaderService> mService;

    ServiceStub(SafeReaderService paramSafeReaderService)
    {
      this.mService = new WeakReference(paramSafeReaderService);
    }

    public void broadcastStatusUpdate()
    {
      ((SafeReaderService)this.mService.get()).broadcastStatusUpdate();
    }

    public boolean doesSupportNotifications()
    {
      return false;
    }

    public Notification getNotification()
    {
      return null;
    }

    public int getNotificationId()
    {
      return 0;
    }

    public boolean isSafeReaderOn()
    {
      return ((SafeReaderService)this.mService.get()).isSafeReaderOn();
    }

    public void pause()
    {
      ((SafeReaderService)this.mService.get()).pause();
    }

    public void registerAlertHandler(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
    {
      ((SafeReaderService)this.mService.get()).registerListener(paramISafeReaderAlertHandler);
    }

    public void resume()
    {
      ((SafeReaderService)this.mService.get()).resume();
    }

    public void safeReaderDeinit()
    {
      ((SafeReaderService)this.mService.get()).safeReaderDeinit();
    }

    public void safeReaderInit(ISafeReaderServiceEngine paramISafeReaderServiceEngine)
    {
      ((SafeReaderService)this.mService.get()).safeReaderInit(paramISafeReaderServiceEngine);
    }

    public void skipCurrentlyPlayingItem()
    {
      ((SafeReaderService)this.mService.get()).skipCurrentlyPlayingItem();
    }

    public void startSafeReading()
    {
      ((SafeReaderService)this.mService.get()).startSafeReading();
    }

    public void stopSafeReading()
    {
      ((SafeReaderService)this.mService.get()).stopSafeReading();
    }

    public void unregisterAlertHandler(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
    {
      ((SafeReaderService)this.mService.get()).unregisterListener(paramISafeReaderAlertHandler);
    }

    public void updateNotification()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereader.SafeReaderService
 * JD-Core Version:    0.6.0
 */