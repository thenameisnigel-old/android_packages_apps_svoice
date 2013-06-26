package com.vlingo.midas;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface.IntegrateAppType;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.IncomingAlertHandler;
import com.vlingo.core.internal.display.WakeLockManager;
import com.vlingo.core.internal.safereader.ISafeReaderAlertHandler;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ADMController;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValuesInterface;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.gui.WakeLockManagerMidas;
import com.vlingo.midas.iux.IUXManager;
import com.vlingo.midas.safereader.JProjectBackgroundHandler;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.CscFeature;
import com.vlingo.midas.util.IntegratedAppInfo;
import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;

public class MidasValues
  implements ClientSuppliedValuesInterface
{
  private Context context;
  private IntegratedAppInfoInterface fmRadioAppInfo;
  private IntegratedAppInfoInterface memoAppInfo;

  public MidasValues(Context paramContext)
  {
    this.context = paramContext;
    this.memoAppInfo = null;
  }

  public void disableDrivingModeSetting()
  {
    if (isSystemSetting())
      Settings.System.putInt(this.context.getContentResolver(), "driving_mode_on", 0);
    while (true)
    {
      return;
      Settings.setBoolean("driving_mode_on", false);
    }
  }

  public void enableDrivingModeSetting()
  {
    if (isSystemSetting())
      Settings.System.putInt(this.context.getContentResolver(), "driving_mode_on", 1);
    while (true)
    {
      return;
      Settings.setBoolean("driving_mode_on", true);
    }
  }

  public ADMController getADMController()
  {
    return MidasADMController.getInstance();
  }

  public AssetManager getAssets()
  {
    return this.context.getAssets();
  }

  public Configuration getConfiguration()
  {
    return this.context.getResources().getConfiguration();
  }

  public ContentResolver getContentResolver()
  {
    return this.context.getContentResolver();
  }

  public int getDrivingModeWidgetMax()
  {
    return 3;
  }

  public IntegratedAppInfoInterface getFmRadioApplicationInfo()
  {
    if (this.fmRadioAppInfo == null)
      this.fmRadioAppInfo = new IntegratedAppInfo(IntegratedAppInfoInterface.IntegrateAppType.FM_RADIO);
    return this.fmRadioAppInfo;
  }

  public void getForegroundFocus(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    JProjectBackgroundHandler.getInstance().getForegroundFocus(paramISafeReaderAlertHandler);
  }

  public String getHomeAddress()
  {
    return Settings.getString("car_nav_home_address", null);
  }

  public Class getLaunchingClass()
  {
    return ConversationActivity.class;
  }

  public int getMaxDisplayNumber()
  {
    return MidasSettings.getInt("widget_display_max", 0);
  }

  public IntegratedAppInfoInterface getMemoApplicationInfo()
  {
    if (this.memoAppInfo == null)
      this.memoAppInfo = new IntegratedAppInfo(IntegratedAppInfoInterface.IntegrateAppType.MEMO);
    return this.memoAppInfo;
  }

  public String getMmsSubject(Cursor paramCursor)
  {
    return paramCursor.getString(paramCursor.getColumnIndex("sub"));
  }

  public int getRegularWidgetMax()
  {
    return 6;
  }

  public VVSActionHandler getSafeReaderHandler(boolean paramBoolean, LinkedList<SafeReaderAlert> paramLinkedList)
  {
    IncomingAlertHandler localIncomingAlertHandler = new IncomingAlertHandler(paramBoolean);
    localIncomingAlertHandler.init(paramLinkedList);
    return localIncomingAlertHandler;
  }

  public WakeLockManager getWakeLockManager()
  {
    return WakeLockManagerMidas.getInstance();
  }

  public boolean handleNoResultAnswersWithGoogleSearch()
  {
    String str = Settings.getString("language", "en-US");
    if ((!ClientConfiguration.isChineseBuild()) && ("en-US".equals(str)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isBlockingMode()
  {
    int i = 0;
    int j = Settings.System.getInt(this.context.getContentResolver(), "dormant_switch_onoff", 0);
    if ((Settings.System.getInt(this.context.getContentResolver(), "dormant_disable_notifications", 0) == 1) && (j == 1))
    {
      if (Settings.System.getInt(this.context.getContentResolver(), "dormant_always", 0) != 1)
        break label59;
      i = 1;
    }
    while (true)
    {
      return i;
      label59: Calendar localCalendar = Calendar.getInstance();
      int k = 60 * localCalendar.get(11) + localCalendar.get(12);
      int m = 60 * Settings.System.getInt(this.context.getContentResolver(), "dormant_start_hour", 0) + Settings.System.getInt(this.context.getContentResolver(), "dormant_start_min", 0);
      int n = 60 * Settings.System.getInt(this.context.getContentResolver(), "dormant_end_hour", 0) + Settings.System.getInt(this.context.getContentResolver(), "dormant_end_min", 0);
      if (m < n)
      {
        if ((k < m) || (k >= n))
          continue;
        i = 1;
        continue;
      }
      if (m > n)
      {
        if ((k >= n) && (k < m))
          continue;
        i = 1;
        continue;
      }
      i = 1;
    }
  }

  public boolean isDrivingModeEnabled()
  {
    if (isSystemSetting());
    for (boolean bool = isSystemSetting(true); ; bool = Settings.getBoolean("driving_mode_on", false))
      return bool;
  }

  public boolean isDrivingModeMessageNotificationEnabled()
  {
    if (isMessageNotificationSystemSetting());
    for (boolean bool = isMessageNotificationSystemSetting(true); ; bool = isDrivingModeEnabled())
      return bool;
  }

  public boolean isEyesFree()
  {
    return MidasSettings.getBoolean("is_eyes_free_mode", false);
  }

  public boolean isIUXComplete()
  {
    return IUXManager.isIUXComplete();
  }

  public boolean isLanguageChangeAllowed()
  {
    return true;
  }

  public boolean isMessageNotificationSystemSetting()
  {
    return isSystemSetting(false);
  }

  public boolean isMessageNotificationSystemSetting(boolean paramBoolean)
  {
    int i = 1;
    int j = Settings.System.getInt(ApplicationAdapter.getInstance().getApplicationContext().getContentResolver(), "driving_mode_message_notification", -99);
    if (paramBoolean)
      if (j == 0);
    while (true)
    {
      return i;
      i = 0;
      continue;
      if (j != -99)
        continue;
      i = 0;
    }
  }

  public boolean isMessageReadbackFlowEnabled()
  {
    return true;
  }

  public boolean isMessagingLocked()
  {
    return false;
  }

  public boolean isReadMessageBodyEnabled()
  {
    return Settings.getBoolean("car_safereader_read_message", false);
  }

  public boolean isSeamless()
  {
    return MidasSettings.getBoolean("seamless_wakeup", false);
  }

  public boolean isSystemSetting()
  {
    return isSystemSetting(false);
  }

  public boolean isSystemSetting(boolean paramBoolean)
  {
    int i = 1;
    int j = Settings.System.getInt(ApplicationAdapter.getInstance().getApplicationContext().getContentResolver(), "driving_mode_on", -99);
    if (paramBoolean)
      if (j == 0);
    while (true)
    {
      return i;
      i = 0;
      continue;
      if (j != -99)
        continue;
      i = 0;
    }
  }

  public boolean isTalkbackOn()
  {
    int i = 1;
    try
    {
      int j = Settings.Secure.getInt(this.context.getContentResolver(), "accessibility_enabled");
      if (j == i);
      while (true)
      {
        return i;
        i = 0;
      }
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      while (true)
        i = 0;
    }
  }

  public boolean isVideoCallingSupported()
  {
    int i = 0;
    File localFile1 = new File("/system/lib/libvtmanager.so");
    File localFile2 = new File("/system/lib/libvtstack.so");
    boolean bool = new CscFeature().getEnableStatus("CscFeature_IMS_EnableVoLTE");
    if (((localFile1.exists()) && (localFile2.exists())) || (bool))
      i = 1;
    return i;
  }

  public void releaseForegroundFocus(ISafeReaderAlertHandler paramISafeReaderAlertHandler)
  {
    JProjectBackgroundHandler.getInstance().releaseForegroundFocus(paramISafeReaderAlertHandler);
  }

  public boolean shouldIncomingMessagesReadout()
  {
    int i = 0;
    if (isSystemSetting())
      if ((!isSystemSetting(true)) && (!VlingoApplication.getInstance().isInForeground()));
    for (i = 1; ; i = 1)
      do
        return i;
      while ((!Settings.getBoolean("driving_mode_on", false)) && (!VlingoApplication.getInstance().isInForeground()));
  }

  public void updateCurrentLocale(Resources paramResources)
  {
    MidasSettings.updateCurrentLocale(paramResources);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.MidasValues
 * JD-Core Version:    0.6.0
 */