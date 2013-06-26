package com.vlingo.core.internal.lmtt;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Audio.Media;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LMTTService extends Service
{
  public static final String ACTION_DEBUG = "com.vlingo.lmtt.action.DEBUG";
  public static final String ACTION_REINITIALIZE = "com.vlingo.lmtt.action.REINITIALIZE";
  public static final String ACTION_SEND_LANGUAGE_CHANGE = "com.vlingo.lmtt.action.LANGUAGE_CHANGE";
  public static final String ACTION_SEND_UPDATE = "com.vlingo.lmtt.action.SEND_UPDATE";
  public static final int DEBUG_ACTION_CLEARCLIENTDB = 1;
  public static final int DEBUG_ACTION_CLEARSERVERDB = 2;
  public static final int DEBUG_ACTION_SENDLMTT = 3;
  public static final int DEBUG_TYPE_CONTACTS = 2;
  public static final int DEBUG_TYPE_MUSIC = 1;
  private static final int DEFAULT_INACTIVITY_SHUTDOWN_PERIOD_MINS = 10080;
  public static final String EXTRA_BOOT_START = "com.vlingo.lmtt.extra.BOOT_START";
  public static final String EXTRA_CLEAR_LMTT = "com.vlingo.lmtt.extra.CLEAR_LMTT";
  public static final String EXTRA_DEBUG_ACTION = "com.vlingo.lmtt.extra.DEBUG_ACTION";
  public static final String EXTRA_DEBUG_TYPE = "com.vlingo.lmtt.extra.DEBUG_TYPE";
  public static final String EXTRA_LMTT_TYPE = "com.vlingo.lmtt.extra.LMTT_TYPE";
  public static final String EXTRA_SKIP_INITIAL_DELAY = "com.vlingo.lmtt.extra.SKIP_DELAY";
  private static final long MS_PER_MINUTE = 60000L;
  private List<ContentObserver> contactsObservers = new ArrayList();
  private volatile LMTTManager lmttUpdate = null;
  private Handler mInactivityHandler;

  private LMTTManager getLMTTUpdate()
  {
    monitorenter;
    try
    {
      if (this.lmttUpdate == null)
        this.lmttUpdate = new LMTTManager(getContentResolver());
      LMTTManager localLMTTManager = this.lmttUpdate;
      monitorexit;
      return localLMTTManager;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private long getRemainingTime()
  {
    long l = Settings.getLong("lmtt.last_app_start_time", 0L) + 60000L * Settings.getInt("lmtt.no_activity_shutdown_period_mins", 10080) - System.currentTimeMillis();
    if (l < 0L)
      l = 0L;
    return l;
  }

  private void inactivityCheck()
  {
    long l = getRemainingTime();
    if (l <= 0L)
    {
      Settings.setBoolean("lmtt.force_fullupdate_on_start", true);
      stopSelf();
    }
    while (true)
    {
      return;
      initDelayedShutdown(l);
    }
  }

  private void initDelayedShutdown(long paramLong)
  {
    this.mInactivityHandler.removeMessages(1);
    this.mInactivityHandler.sendEmptyMessageDelayed(1, paramLong);
  }

  private void registerObserver(LMTTItem.LmttItemType paramLmttItemType, Uri paramUri)
  {
    if (Settings.getBoolean("lmtt.enable." + paramLmttItemType.getCategory(), true))
    {
      VContentObserver localVContentObserver = new VContentObserver(paramLmttItemType);
      getContentResolver().registerContentObserver(paramUri, false, localVContentObserver);
      this.contactsObservers.add(localVContentObserver);
    }
  }

  public static void updateAppStartTime()
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    localContext.startService(new Intent(localContext, LMTTService.class));
  }

  List<ContentObserver> getContactsObservers()
  {
    return this.contactsObservers;
  }

  Handler getInactivityHandler()
  {
    return this.mInactivityHandler;
  }

  void initializeService()
  {
    registerObserver(LMTTItem.LmttItemType.TYPE_CONTACT, ContactsContract.Contacts.CONTENT_URI);
    registerObserver(LMTTItem.LmttItemType.TYPE_SONG, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
    registerObserver(LMTTItem.LmttItemType.TYPE_PLAYLIST, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
    this.mInactivityHandler = new InactivityHandler(null);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    if (ClientSuppliedValues.isIUXComplete())
      initializeService();
    while (true)
    {
      return;
      stopSelf();
    }
  }

  public void onDestroy()
  {
    uninitializeService();
    super.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    monitorenter;
    int i;
    label29: boolean bool2;
    while (true)
    {
      try
      {
        if (!ClientSuppliedValues.isIUXComplete())
          break;
        if ((paramIntent != null) && (!paramIntent.getBooleanExtra("com.vlingo.lmtt.extra.BOOT_START", false)))
          continue;
        inactivityCheck();
        i = 1;
        return i;
        if ("com.vlingo.lmtt.action.REINITIALIZE".equals(paramIntent.getAction()))
        {
          uninitializeService();
          initializeService();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      boolean bool1 = Settings.getBoolean("lmtt.force_fullupdate_on_start", false);
      Settings.setLong("lmtt.last_app_start_time", System.currentTimeMillis());
      initDelayedShutdown(60000L * Settings.getInt("lmtt.no_activity_shutdown_period_mins", 10080));
      String str1 = paramIntent.getAction();
      int j;
      int k;
      if ("com.vlingo.lmtt.action.DEBUG".equals(str1))
      {
        j = paramIntent.getIntExtra("com.vlingo.lmtt.extra.DEBUG_TYPE", 2);
        k = paramIntent.getIntExtra("com.vlingo.lmtt.extra.DEBUG_ACTION", 3);
      }
      switch (j)
      {
      case 2:
        if (k == 1)
        {
          LMTTDBUtil.clearLMTTTable(LMTTItem.LmttItemType.TYPE_CONTACT);
          break;
        }
        if ((k == 2) || (k != 3))
          break;
        getLMTTUpdate().fireUpdate(LMTTManager.LmttUpdateType.LMTT_CONTACT_UPDATE, true, false);
        break;
      case 1:
        if (k == 1)
        {
          LMTTDBUtil.clearLMTTTable(LMTTItem.LmttItemType.TYPE_PLAYLIST);
          LMTTDBUtil.clearLMTTTable(LMTTItem.LmttItemType.TYPE_SONG);
          break;
        }
        if ((k == 2) || (k != 3))
          break;
        getLMTTUpdate().fireUpdate(LMTTManager.LmttUpdateType.LMTT_MUSIC_UPDATE, true, false);
        break;
        if ("com.vlingo.lmtt.action.LANGUAGE_CHANGE".equals(str1))
        {
          getLMTTUpdate().fireUpdate(LMTTManager.LmttUpdateType.LMTT_LANGUAGE_UPDATE, true, false);
          continue;
        }
        if ((!"com.vlingo.lmtt.action.SEND_UPDATE".equals(str1)) && (!bool1))
          continue;
        if (bool1)
          Settings.setBoolean("lmtt.force_fullupdate_on_start", false);
        bool2 = paramIntent.getBooleanExtra("com.vlingo.lmtt.extra.SKIP_DELAY", false);
        if (bool1)
          break label408;
        if (!paramIntent.getBooleanExtra("com.vlingo.lmtt.extra.CLEAR_LMTT", false))
          break label414;
        break label408;
      }
    }
    while (true)
    {
      String str2 = paramIntent.getStringExtra("com.vlingo.lmtt.extra.LMTT_TYPE");
      if ((str2 == null) || ("pim".equals(str2)))
        getLMTTUpdate().fireUpdate(LMTTManager.LmttUpdateType.LMTT_CONTACT_UPDATE, bool2, bool3);
      if ((str2 != null) && (!"music".equals(str2)))
        break;
      getLMTTUpdate().fireUpdate(LMTTManager.LmttUpdateType.LMTT_MUSIC_UPDATE, bool2, bool3);
      break;
      stopSelf();
      break;
      i = 1;
      break label29;
      label408: boolean bool3 = true;
      continue;
      label414: bool3 = false;
    }
  }

  void uninitializeService()
  {
    Iterator localIterator = this.contactsObservers.iterator();
    while (localIterator.hasNext())
    {
      ContentObserver localContentObserver = (ContentObserver)localIterator.next();
      getContentResolver().unregisterContentObserver(localContentObserver);
    }
    this.contactsObservers.clear();
    if (this.mInactivityHandler != null)
    {
      this.mInactivityHandler.removeMessages(1);
      this.mInactivityHandler = null;
    }
  }

  private class InactivityHandler extends Handler
  {
    private static final int SHUTDOWN = 1;

    private InactivityHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      LMTTService.this.inactivityCheck();
    }
  }

  private class VContentObserver extends ContentObserver
  {
    private LMTTItem.LmttItemType type = LMTTItem.LmttItemType.TYPE_UNKNOWN;

    public VContentObserver(LMTTItem.LmttItemType arg2)
    {
      super();
      Object localObject;
      this.type = localObject;
    }

    public void onChange(boolean paramBoolean)
    {
      switch (LMTTService.1.$SwitchMap$com$vlingo$core$internal$lmtt$LMTTItem$LmttItemType[this.type.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        return;
        LMTTService.this.getLMTTUpdate().fireUpdate(LMTTManager.LmttUpdateType.LMTT_CONTACT_UPDATE, false, false);
        continue;
        LMTTService.this.getLMTTUpdate().fireUpdate(LMTTManager.LmttUpdateType.LMTT_MUSIC_UPDATE, false, false);
        continue;
        LMTTService.this.getLMTTUpdate().fireUpdate(LMTTManager.LmttUpdateType.LMTT_MUSIC_UPDATE, false, false);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTService
 * JD-Core Version:    0.6.0
 */