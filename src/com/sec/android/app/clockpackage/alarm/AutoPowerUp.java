package com.sec.android.app.clockpackage.alarm;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.text.format.Time;
import android.util.Log;
import com.sec.android.app.CscFeature;

public class AutoPowerUp
{
  static final String KEY_AUTO_POWER_UP = "auto_power_up";
  private static final String TAG = "AutoPowerUp";

  public static String getDataString(boolean paramBoolean, long paramLong)
  {
    String str1;
    if ((!paramBoolean) || (paramLong == -1L))
    {
      str1 = "0000000000000";
      return str1;
    }
    Time localTime = new Time("UTC");
    localTime.set(paramLong - 60000L);
    localTime.normalize(false);
    String str2 = localTime.year;
    String str3;
    label101: String str4;
    label133: String str5;
    if (1 + localTime.month < 10)
    {
      str3 = "0" + (1 + localTime.month);
      if (localTime.monthDay >= 10)
        break label263;
      str4 = "0" + localTime.monthDay;
      if (localTime.hour >= 10)
        break label286;
      str5 = "0" + localTime.hour;
      label165: if (localTime.minute >= 10)
        break label309;
    }
    label263: label286: label309: for (String str6 = "0" + localTime.minute; ; str6 = localTime.minute)
    {
      str1 = "1" + str2 + str3 + str4 + str5 + str6;
      break;
      str3 = 1 + localTime.month;
      break label101;
      str4 = localTime.monthDay;
      break label133;
      str5 = localTime.hour;
      break label165;
    }
  }

  public static void sendData(Context paramContext, long paramLong)
  {
    int i;
    if (CscFeature.getInstance().getEnableStatus("CscFeature_Clock_ExclusiveEnablingAutoPowerSetting"))
      i = 0;
    while (true)
    {
      try
      {
        int j = Settings.System.getInt(paramContext.getContentResolver(), "auto_power_on_off");
        i = j;
        if (i > 0)
          return;
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException)
      {
        Log.d("AutoPowerUp", "Can't find isAutoPowerOnOffEnable : ");
        continue;
      }
      boolean bool = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("auto_power_up", true);
      AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
      String str = getDataString(bool, paramLong);
      if ((str == "") || (str == null))
        continue;
      Log.d("AutoPowerUp", "enabled: " + bool + ", time: " + paramLong + ", mData: " + str);
      localAlarmManager.setAutoPowerUp(str);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.clockpackage.alarm.AutoPowerUp
 * JD-Core Version:    0.6.0
 */