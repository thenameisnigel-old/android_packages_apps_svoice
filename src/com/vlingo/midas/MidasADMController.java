package com.vlingo.midas;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.util.DisplayMetrics;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ADMController;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.midas.drivingmode.DrivingModeUtil;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.settings.MidasSettings;
import java.lang.reflect.Method;
import java.util.Locale;

public class MidasADMController extends ADMController
{
  public static String AGGRESSIVE_NOISE_CANCELLATION = "MidasFeature.AGGRESSIVE_NOISE_CANCELLATION";
  public static String DRIVING_MODE_GUI;
  public static String ENDPOINT_DETECTION = "MidasFeature.ENDPOINT_DETECTION";
  public static String EYES_FREE = "MidasFeature.EYES_FREE";
  public static String TALKBACK;
  public static final String X_VLCONF_DRIVING_MODE = "isInDrivingMode";
  DrivingModeContentObserver drivingModeContentObserver = null;
  DrivingModeSharedPreferenceChangeListener drivingModeSharedPreferenceChangeListener = null;
  TalkbackContentObserver talkbackContentObserver = null;
  boolean wasDrivingModeEnabledForDM;

  static
  {
    DRIVING_MODE_GUI = "MidasFeature.DRIVING_MODE_GUI";
    TALKBACK = "MidasFeature.TALKBACK";
  }

  protected MidasADMController()
  {
    if ((isDrivingModeEnabled()) && (!ClientSuppliedValues.isTalkbackOn()));
    for (boolean bool = true; ; bool = false)
    {
      this.wasDrivingModeEnabledForDM = bool;
      this.drivingModeContentObserver = new DrivingModeContentObserver(new Handler());
      this.drivingModeSharedPreferenceChangeListener = new DrivingModeSharedPreferenceChangeListener(null);
      this.talkbackContentObserver = new TalkbackContentObserver(new Handler());
      ApplicationAdapter.getInstance().getApplicationContext().getContentResolver().registerContentObserver(DrivingModeUtil.getUri(), false, this.drivingModeContentObserver);
      ApplicationAdapter.getInstance().getApplicationContext().getContentResolver().registerContentObserver(Settings.Secure.getUriFor("accessibility_enabled"), true, this.talkbackContentObserver);
      Settings.getSharedPreferences().registerOnSharedPreferenceChangeListener(this.drivingModeSharedPreferenceChangeListener);
      return;
    }
  }

  private Locale getCurrentSystemLanguage()
  {
    try
    {
      Class localClass = Class.forName("android.app.ActivityManagerNative");
      Method localMethod1 = localClass.getMethod("getDefault", new Class[0]);
      localMethod1.setAccessible(true);
      Object localObject = localMethod1.invoke(localClass, new Object[0]);
      Method localMethod2 = localClass.getMethod("getConfiguration", new Class[0]);
      localMethod2.setAccessible(true);
      localLocale = ((Configuration)localMethod2.invoke(localObject, new Object[0])).locale;
      return localLocale;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        Locale localLocale = Locale.getDefault();
      }
    }
  }

  protected static ADMController getInstance()
  {
    if (instance == null)
      instance = new MidasADMController();
    return instance;
  }

  private void handleDrivingModeSettingChange(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      DialogFlow.getInstance().removeUserProperties("isInDrivingMode");
      DialogFlow.getInstance().addUserProperties("isInDrivingMode", Boolean.TRUE.toString().toLowerCase(MidasSettings.getCurrentLocale()));
    }
    while (true)
    {
      return;
      DialogFlow.getInstance().removeUserProperties("isInDrivingMode");
      DialogFlow.getInstance().addUserProperties("isInDrivingMode", Boolean.FALSE.toString().toLowerCase(MidasSettings.getCurrentLocale()));
    }
  }

  private static boolean isDetailedTtsFeedback()
  {
    return MidasSettings.getBoolean("detailed_tts_feedback", false);
  }

  private boolean isDrivingModeEnabled()
  {
    return DrivingModeUtil.isDrivingModeEnabled(VlingoApplication.getInstance());
  }

  private boolean isHeadsetModeEnabled()
  {
    AudioManager localAudioManager = (AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio");
    if ((BluetoothManager.isHeadsetConnected()) || (localAudioManager.isWiredHeadsetOn()));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void processOnChange()
  {
    refreshFeatureStates();
    this.onBoot = false;
    refreshAppModeStates();
  }

  protected boolean getFeatureStatus(String paramString)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramString.equals(AGGRESSIVE_NOISE_CANCELLATION))
      bool1 = isDrivingModeEnabled();
    while (true)
    {
      return bool1;
      if (paramString.equals(DRIVING_MODE_GUI))
      {
        if ((isDrivingModeEnabled()) && (!ClientSuppliedValues.isTalkbackOn()))
          continue;
        bool1 = false;
        continue;
      }
      if (paramString.equals(ENDPOINT_DETECTION))
      {
        bool1 = isDrivingModeEnabled();
        continue;
      }
      if (paramString.equals(EYES_FREE))
      {
        if ((isDrivingModeEnabled()) || (isHeadsetModeEnabled()) || (isDetailedTtsFeedback()))
          bool2 = bool1;
        bool1 = bool2;
        continue;
      }
      if (paramString.equals(TALKBACK))
      {
        bool1 = ClientSuppliedValues.isTalkbackOn();
        continue;
      }
      bool1 = super.getFeatureStatus(paramString);
    }
  }

  @SuppressLint({"NewApi"})
  protected void notibuilder()
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    NotificationManager localNotificationManager = (NotificationManager)localContext.getSystemService("notification");
    Resources localResources1 = localContext.getResources();
    AssetManager localAssetManager = localResources1.getAssets();
    DisplayMetrics localDisplayMetrics = localResources1.getDisplayMetrics();
    Configuration localConfiguration = new Configuration(localResources1.getConfiguration());
    localConfiguration.locale = getCurrentSystemLanguage();
    Resources localResources2 = new Resources(localAssetManager, localDisplayMetrics, localConfiguration);
    PendingIntent localPendingIntent = PendingIntent.getActivity(localContext, 0, new Intent(localContext, ConversationActivity.class), 0);
    Notification localNotification = new Notification.Builder(localContext).setContentTitle(localResources2.getString(2131361884)).setContentText(localResources2.getString(2131362759)).setSmallIcon(2130837970).setContentIntent(localPendingIntent).build();
    localNotification.flags = (0x2 | localNotification.flags);
    localNotificationManager.notify(0, localNotification);
  }

  protected void refreshAppModeStates()
  {
    boolean bool1 = isDrivingModeEnabled();
    boolean bool2 = ClientSuppliedValues.isTalkbackOn();
    boolean bool3;
    if ((bool1) && (!bool2))
    {
      bool3 = true;
      if (this.wasDrivingModeEnabledForDM != bool3)
      {
        handleDrivingModeSettingChange(bool3);
        this.wasDrivingModeEnabledForDM = bool3;
      }
      if ((!bool1) || (bool2))
        break label55;
      notibuilder();
    }
    while (true)
    {
      return;
      bool3 = false;
      break;
      label55: ((NotificationManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("notification")).cancelAll();
    }
  }

  private class DrivingModeContentObserver extends ContentObserver
  {
    public DrivingModeContentObserver(Handler arg2)
    {
      super();
    }

    public void onChange(boolean paramBoolean)
    {
      boolean bool1 = DrivingModeUtil.isSystemDrivingModeEnabled(VlingoApplication.getInstance());
      boolean bool2 = DrivingModeUtil.isLocalDrivingModeEnabled(VlingoApplication.getInstance());
      DialogFlow.getInstance().cancelTurn();
      if (bool1 != bool2)
      {
        if (!bool1)
          break label52;
        Settings.System.putInt(ApplicationAdapter.getInstance().getApplicationContext().getContentResolver(), "voice_input_control", 1);
      }
      while (true)
      {
        Settings.setBoolean("driving_mode_on", bool1);
        return;
        label52: if (Settings.getInt("temp_input_voice_control", 0) == 0)
        {
          Settings.System.putInt(ApplicationAdapter.getInstance().getApplicationContext().getContentResolver(), "voice_input_control", 0);
          continue;
        }
        Settings.System.putInt(ApplicationAdapter.getInstance().getApplicationContext().getContentResolver(), "voice_input_control", 1);
      }
    }
  }

  private class DrivingModeSharedPreferenceChangeListener
    implements SharedPreferences.OnSharedPreferenceChangeListener
  {
    private DrivingModeSharedPreferenceChangeListener()
    {
    }

    public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
    {
      if (paramString == null);
      while (true)
      {
        return;
        if (paramString.equals("driving_mode_on"))
        {
          MidasADMController.this.processOnChange();
          continue;
        }
      }
    }
  }

  private class TalkbackContentObserver extends ContentObserver
  {
    public TalkbackContentObserver(Handler arg2)
    {
      super();
    }

    public void onChange(boolean paramBoolean)
    {
      MidasADMController.this.processOnChange();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.MidasADMController
 * JD-Core Version:    0.6.0
 */