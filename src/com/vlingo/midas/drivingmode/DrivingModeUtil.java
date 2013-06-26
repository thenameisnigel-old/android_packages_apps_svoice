package com.vlingo.midas.drivingmode;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.midas.gui.homewidget.InCarWidgetHelpTextReceiver;

public class DrivingModeUtil
{
  private static void disableDrivingModeSetting(Context paramContext)
  {
    Settings.disableDrivingModeSetting();
  }

  private static void enableDrivingModeSetting(Context paramContext)
  {
    Settings.enableDrivingModeSetting();
  }

  public static Uri getUri()
  {
    return Settings.System.getUriFor("driving_mode_on");
  }

  public static boolean isDrivingModeEnabled(Context paramContext)
  {
    return Settings.isDrivingModeEnabled();
  }

  public static boolean isLocalDrivingModeEnabled(Context paramContext)
  {
    return Settings.getBoolean("driving_mode_on", false);
  }

  public static boolean isSystemDrivingModeEnabled(Context paramContext)
  {
    int i = 0;
    try
    {
      int j = Settings.System.getInt(paramContext.getContentResolver(), "driving_mode_on");
      if (j != 0)
        i = 1;
      return i;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      while (true)
        localSettingNotFoundException.printStackTrace();
    }
  }

  public static void toggleDrivingMode(Context paramContext)
  {
    if (isDrivingModeEnabled(paramContext))
      disableDrivingModeSetting(paramContext);
    while (true)
    {
      Intent localIntent = new Intent(paramContext, InCarWidgetHelpTextReceiver.class);
      localIntent.setAction("com.vlingo.client.widget.action.driving_mode_changed");
      paramContext.sendBroadcast(localIntent);
      return;
      enableDrivingModeSetting(paramContext);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.drivingmode.DrivingModeUtil
 * JD-Core Version:    0.6.0
 */