package com.sec.android.app.clockpackage.alarm;

import android.content.Context;
import android.content.SharedPreferences;

public class Alarms
{
  public static final String PREF_START_DAY_OF_WEEK = "preferences_week_start_day";

  public static int getStartDayOfWeek(Context paramContext)
  {
    return paramContext.getSharedPreferences("AlarmClock", 0).getInt("preferences_week_start_day", 1);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.clockpackage.alarm.Alarms
 * JD-Core Version:    0.6.0
 */