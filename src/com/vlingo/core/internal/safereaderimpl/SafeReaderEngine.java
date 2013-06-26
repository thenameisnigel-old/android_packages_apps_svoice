package com.vlingo.core.internal.safereaderimpl;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.safereader.ISafeReaderAlertChangeListener;
import com.vlingo.core.internal.safereader.ISafeReaderAlertHandler;
import com.vlingo.core.internal.safereader.ISafeReaderAlertSource;
import com.vlingo.core.internal.safereader.ISafeReaderServiceEngine;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.safereader.SafeReaderProxy;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ADMController;
import com.vlingo.core.internal.util.ADMFeatureListener;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.PhoneUtil;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SafeReaderEngine
  implements ISafeReaderServiceEngine, ISafeReaderAlertChangeListener, ADMFeatureListener
{
  private final int SAFEREADER_NOTIFICATION_ID = 2000;
  private LinkedList<SafeReaderAlert> alertQueue = new LinkedList();
  private final ArrayList<ISafeReaderAlertSource> alertSources;
  protected AudioManager audioManager;
  protected final Context context;
  private final BroadcastReceiver intentReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent != null)
        SafeReaderEngine.this.handleIntent(paramContext, paramIntent);
    }
  };
  protected boolean isAudioPlaying = false;
  protected boolean isPaused = false;
  protected boolean isSafeReaderOn = false;
  protected boolean isSilenced = false;
  private final SafeReaderOnNotification notificationManager;
  protected final PhoneListener phoneListener;
  protected final List<ISafeReaderAlertHandler> remoteMsgListeners;
  protected TelephonyManager telephonyManager;
  protected boolean useDelay = false;

  public SafeReaderEngine(Context paramContext)
  {
    this.context = paramContext;
    this.remoteMsgListeners = new CopyOnWriteArrayList();
    this.telephonyManager = ((TelephonyManager)paramContext.getSystemService("phone"));
    this.audioManager = ((AudioManager)paramContext.getSystemService("audio"));
    this.notificationManager = new SafeReaderOnNotification(paramContext, 2000, this.isSilenced);
    this.alertSources = new ArrayList();
    this.phoneListener = new PhoneListener(null);
    this.alertSources.add(new SMSMMSAlertSource());
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.vlingo.client.safereader.ACTION_SAFEREADER_OFF");
    paramContext.registerReceiver(this.intentReceiver, localIntentFilter);
  }

  private void handleIntent(Context paramContext, Intent paramIntent)
  {
    if ("com.vlingo.client.safereader.ACTION_SAFEREADER_OFF".equals(paramIntent.getAction()))
      Settings.disableDrivingModeSetting();
  }

  public void broadcastStatusUpdate()
  {
  }

  public boolean doesSupportNotifications()
  {
    return Settings.getBoolean("safereader_notifications", false);
  }

  public Notification getNotification()
  {
    return this.notificationManager.getNotification(this.context, this.isAudioPlaying, true);
  }

  public int getNotificationId()
  {
    return 2000;
  }

  public boolean isSafeReaderOn()
  {
    return this.isSafeReaderOn;
  }

  public boolean isSilenced()
  {
    return this.isSilenced;
  }

  public void onNewSafeReaderAlert(LinkedList<? extends SafeReaderAlert> paramLinkedList)
  {
    new Thread(new Runnable(paramLinkedList)
    {
      public void run()
      {
        while (true)
        {
          Object localObject2;
          long l1;
          synchronized (SafeReaderEngine.this)
          {
            if ((SafeReaderEngine.this.isSafeReaderOn()) && (!SafeReaderEngine.this.isPaused) && (!ClientSuppliedValues.isMessagingLocked()) && (!ClientSuppliedValues.isBlockingMode()) && (!PhoneUtil.phoneInUse(SafeReaderProxy.getContext())))
            {
              localObject2 = null;
              l1 = 0L;
              if (SafeReaderEngine.this.useDelay)
              {
                Iterator localIterator2 = SafeReaderEngine.this.remoteMsgListeners.iterator();
                if (localIterator2.hasNext())
                {
                  long l2 = Math.max(l1, ((ISafeReaderAlertHandler)localIterator2.next()).readoutDelay());
                  l1 = l2;
                  continue;
                  l1 -= 100L;
                }
              }
            }
          }
          try
          {
            Thread.sleep(100L);
            label131: if ((!SafeReaderEngine.this.remoteMsgListeners.equals(localObject2)) && (localObject2 == null))
              localObject2 = new CopyOnWriteArrayList(SafeReaderEngine.this.remoteMsgListeners);
            if ((SafeReaderEngine.this.remoteMsgListeners.equals(localObject2)) && (l1 > 0L))
              continue;
            Iterator localIterator1 = SafeReaderEngine.this.remoteMsgListeners.iterator();
            while (localIterator1.hasNext())
            {
              ISafeReaderAlertHandler localISafeReaderAlertHandler = (ISafeReaderAlertHandler)localIterator1.next();
              localISafeReaderAlertHandler.setSilentMode(SafeReaderEngine.this.isSilenced());
              localISafeReaderAlertHandler.handleAlert(this.val$alerts);
              continue;
              localObject1 = finally;
              monitorexit;
              throw localObject1;
            }
            SafeReaderEngine.this.useDelay = false;
            SafeReaderEngine.this.alertQueue.clear();
            while (true)
            {
              monitorexit;
              return;
              if (SafeReaderEngine.this.alertQueue == null)
                SafeReaderEngine.access$202(SafeReaderEngine.this, new LinkedList());
              SafeReaderEngine.this.alertQueue.addAll(0, this.val$alerts);
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            break label131;
          }
        }
      }
    }).start();
  }

  public void onStatusChanged(String paramString, boolean paramBoolean)
  {
    if (paramString.equals(ADMController.SAFEREADER))
    {
      if (!paramBoolean)
        break label18;
      SafeReaderProxy.startSafeReading();
    }
    while (true)
    {
      return;
      label18: SafeReaderProxy.stopSafeReading();
    }
  }

  public void pause()
  {
    if (isSafeReaderOn())
    {
      this.isPaused = true;
      AudioPlayerProxy.stop();
    }
  }

  public void registerAlertHandler(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    if ((paramISafeReaderAlertHandler != null) && (!this.remoteMsgListeners.contains(paramISafeReaderAlertHandler)))
      this.remoteMsgListeners.add(paramISafeReaderAlertHandler);
  }

  public void resume()
  {
    if (isSafeReaderOn())
      this.isPaused = false;
  }

  public void safeReaderDeinit()
  {
    ClientSuppliedValues.getADMController().removeListener(this);
  }

  public void safeReaderInit(ISafeReaderServiceEngine paramISafeReaderServiceEngine)
  {
    ADMController localADMController = ClientSuppliedValues.getADMController();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = ADMController.SAFEREADER;
    localADMController.addListener(this, arrayOfString);
  }

  public void setSilenced(boolean paramBoolean)
  {
    this.isSilenced = paramBoolean;
    this.notificationManager.setSilent(paramBoolean);
  }

  public void skipCurrentlyPlayingItem()
  {
    if (isSafeReaderOn());
  }

  public void startSafeReading()
  {
    if (!isSafeReaderOn())
    {
      Settings.setLong("car_safereader_last_saferead_time", System.currentTimeMillis());
      this.isPaused = false;
      this.isSafeReaderOn = true;
      this.telephonyManager.listen(this.phoneListener, 32);
      Iterator localIterator = this.alertSources.iterator();
      while (localIterator.hasNext())
        ((ISafeReaderAlertSource)localIterator.next()).onStart(this.context, this);
    }
  }

  public void stopSafeReading()
  {
    if (isSafeReaderOn())
    {
      this.isAudioPlaying = false;
      this.isSafeReaderOn = false;
      this.telephonyManager.listen(this.phoneListener, 0);
      Iterator localIterator = this.alertSources.iterator();
      while (localIterator.hasNext())
        ((ISafeReaderAlertSource)localIterator.next()).onStop(this.context);
      if (!VlingoApplicationService.isAppInForeground())
        AudioPlayerProxy.stop();
    }
  }

  public void unregisterAlertHandler(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    if ((paramISafeReaderAlertHandler != null) && (this.remoteMsgListeners.contains(paramISafeReaderAlertHandler)))
      this.remoteMsgListeners.remove(paramISafeReaderAlertHandler);
  }

  public void updateNotification()
  {
    this.notificationManager.updateNotification(this.context, this.isAudioPlaying);
  }

  private class PhoneListener extends PhoneStateListener
  {
    private PhoneListener()
    {
    }

    public void onCallStateChanged(int paramInt, String paramString)
    {
      switch (paramInt)
      {
      default:
      case 0:
      }
      while (true)
      {
        return;
        if ((SafeReaderEngine.this.alertQueue != null) && (!SafeReaderEngine.this.alertQueue.isEmpty()))
        {
          SafeReaderEngine.this.useDelay = true;
          LinkedList localLinkedList = new LinkedList();
          localLinkedList.addAll(SafeReaderEngine.this.alertQueue);
          SafeReaderEngine.this.onNewSafeReaderAlert(localLinkedList);
          continue;
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereaderimpl.SafeReaderEngine
 * JD-Core Version:    0.6.0
 */