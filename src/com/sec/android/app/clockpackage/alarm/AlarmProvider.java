package com.sec.android.app.clockpackage.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.util.Log;
import com.sec.android.app.CscFeature;
import java.util.Calendar;
import java.util.Date;

public class AlarmProvider
{
  static final String AUTHORITY = "com.samsung.sec.android.clockpackage";
  public static final int COLUMN_ALARMACTIVE = 1;
  public static final int COLUMN_ALARMALERTTIME = 3;
  public static final int COLUMN_ALARMNAME = 20;
  public static final int COLUMN_ALARMSOUNDTONE = 16;
  public static final int COLUMN_ALARMSOUNDTYPE = 15;
  public static final int COLUMN_ALARMSOUNDURI = 19;
  public static final int COLUMN_ALARMSOUNDVOLUME = 17;
  public static final int COLUMN_ALARMTIME = 4;
  public static final int COLUMN_CREATETIME = 2;
  public static final int COLUMN_DAILYBRIEF = 11;
  public static final int COLUMN_ID = 0;
  public static final int COLUMN_NOTIFICATIONTYPE = 6;
  public static final int COLUMN_REPEATTYPE = 5;
  public static final int COLUMN_SNOOZEACTIVE = 7;
  public static final int COLUMN_SNOOZEDONECOUNT = 10;
  public static final int COLUMN_SNOOZEDURATION = 8;
  public static final int COLUMN_SNOOZEREPEAT = 9;
  public static final int COLUMN_SUBDUEACTIVE = 12;
  public static final int COLUMN_SUBDUEDURATION = 13;
  public static final int COLUMN_SUBDUETONE = 14;
  public static final int COLUMN_SUBDUEURI = 18;
  public static final Uri CONTENT_URI = Uri.parse("content://com.samsung.sec.android.clockpackage/alarm");
  static final String DATABASE_NAME = "alarm.db";
  static final int DATABASE_VERSION = 1;
  private static final boolean DEBUG = false;
  private static final String DM12 = "E h:mm aa";
  private static final String DM24 = "E k:mm";
  public static final String PARAMETER_NOTIFY = "notify";
  static final String TABLE_NAME = "alarm";
  private static final String TAG = "AlarmProvider";
  public static final String TAG_ACTIVATION = "active";
  public static final String TAG_ALARMALERTTIME = "alerttime";
  public static final String TAG_ALARMNAME = "name";
  public static final String TAG_ALARMSOUNDTONE = "alarmtone";
  public static final String TAG_ALARMSOUNDTYPE = "alarmsound";
  public static final String TAG_ALARMSOUNDURI = "alarmuri";
  public static final String TAG_ALARMTIME = "alarmtime";
  public static final String TAG_ALARMVOLUME = "volume";
  public static final String TAG_CREATETIME = "createtime";
  public static final String TAG_DAILYBRIEFING = "dailybrief";
  public static final String TAG_ID = "_id";
  public static final String TAG_NOTIFICATIONTYPE = "notitype";
  public static final String TAG_REPEATTYPE = "repeattype";
  public static final String TAG_SNOOZEACTIVATION = "snzactive";
  public static final String TAG_SNOOZEDONECOUNT = "snzcount";
  public static final String TAG_SNOOZEDURATION = "snzduration";
  public static final String TAG_SNOOZEREPEAT = "snzrepeat";
  public static final String TAG_SUBDUEACTIVATION = "sbdactive";
  public static final String TAG_SUBDUEDURATION = "sbdduration";
  public static final String TAG_SUBDUETONE = "sbdtone";
  public static final String TAG_SUBDUEURI = "sbduri";
  private static boolean mIsTablet = false;
  private static PendingIntent mPendingIntent;

  public static void enableNextAlert(Context paramContext)
  {
    AlarmItem localAlarmItem = getNextAlarm(paramContext);
    if (localAlarmItem != null)
    {
      AlarmManager localAlarmManager2 = (AlarmManager)paramContext.getSystemService("alarm");
      if (mPendingIntent != null)
        localAlarmManager2.cancel(mPendingIntent);
      Intent localIntent = new Intent("com.samsung.sec.android.clockpackage.alarm.ALARM_ALERT");
      Parcel localParcel = Parcel.obtain();
      localAlarmItem.writeToParcel(localParcel);
      localParcel.setDataPosition(0);
      localIntent.putExtra("com.samsung.sec.android.clockpackage.alarm.ALARM_DATA", localParcel.marshall());
      mPendingIntent = PendingIntent.getBroadcast(paramContext, 0, localIntent, 268435456);
      localAlarmManager2.set(0, localAlarmItem.alarmAlertTime, mPendingIntent);
      setStatusBarIcon(paramContext, true);
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(new Date(localAlarmItem.alarmAlertTime));
      saveNextAlarm(paramContext, formatDayAndTime(paramContext, localCalendar));
      if ((!mIsTablet) && (CscFeature.getInstance().getEnableStatus("CscFeature_Clock_EnableAutoPowerOnOffMenu")))
        AutoPowerUp.sendData(paramContext, localAlarmItem.alarmAlertTime);
      Utilities.updateIndicatorAlarm(paramContext);
      return;
    }
    AlarmManager localAlarmManager1 = (AlarmManager)paramContext.getSystemService("alarm");
    if (mPendingIntent != null)
      localAlarmManager1.cancel(mPendingIntent);
    while (true)
    {
      mPendingIntent = null;
      setStatusBarIcon(paramContext, false);
      saveNextAlarm(paramContext, "");
      if ((mIsTablet) || (!CscFeature.getInstance().getEnableStatus("CscFeature_Clock_EnableAutoPowerOnOffMenu")))
        break;
      AutoPowerUp.sendData(paramContext, -1L);
      break;
      Log.d("TAG", "mPendingIntent == null am.cancel");
      mPendingIntent = PendingIntent.getBroadcast(paramContext, 0, new Intent("com.samsung.sec.android.clockpackage.alarm.ALARM_ALERT"), 268435456);
      localAlarmManager1.cancel(mPendingIntent);
    }
  }

  private static String formatDayAndTime(Context paramContext, Calendar paramCalendar)
  {
    String str1;
    if (get24HourMode(paramContext))
    {
      str1 = "E k:mm";
      if (paramCalendar != null)
        break label26;
    }
    label26: for (String str2 = ""; ; str2 = (String)DateFormat.format(str1, paramCalendar))
    {
      return str2;
      str1 = "E h:mm aa";
      break;
    }
  }

  static boolean get24HourMode(Context paramContext)
  {
    return DateFormat.is24HourFormat(paramContext);
  }

  public static final AlarmItem getNextAlarm(Context paramContext)
  {
    Object localObject = null;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Cursor localCursor1 = localContentResolver.query(CONTENT_URI, null, "active > 0", null, "alerttime ASC");
    localCursor1.moveToFirst();
    if (localCursor1.getCount() <= 0)
    {
      localCursor1.close();
      return localObject;
    }
    long l = localCursor1.getLong(3);
    localCursor1.close();
    Cursor localCursor2 = localContentResolver.query(CONTENT_URI, null, "alerttime = " + l, null, "createtime DESC");
    localCursor2.moveToFirst();
    int i = localCursor2.getCount();
    AlarmItem[] arrayOfAlarmItem = new AlarmItem[i];
    int k;
    int m;
    int n;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localCursor2.close();
        k = -1;
        m = 256;
        n = i - 1;
        if (n >= 0)
          break label193;
        if (k < 0)
          break;
        localObject = arrayOfAlarmItem[k];
        break;
      }
      arrayOfAlarmItem[j] = AlarmItem.createItemFromCursor(localCursor2);
      localCursor2.moveToNext();
    }
    label193: if (arrayOfAlarmItem[n].activate > 0)
    {
      if ((arrayOfAlarmItem[n].notificationType != 0) && (arrayOfAlarmItem[n].notificationType != 16))
        break label241;
      m = 0;
      k = n;
    }
    while (true)
    {
      n--;
      break;
      label241: if ((0x1 & arrayOfAlarmItem[n].notificationType) == 1)
      {
        if (m == 0)
          continue;
        m = 256;
        k = n;
        continue;
      }
      if ((0x100 & arrayOfAlarmItem[n].notificationType) != 256)
        continue;
      if (arrayOfAlarmItem[n].snoozeDoneCount == 0)
      {
        m = 0;
        k = n;
        continue;
      }
      if (m == 0)
        continue;
      m = 256;
      k = n;
    }
  }

  static void saveNextAlarm(Context paramContext, String paramString)
  {
    Settings.System.putString(paramContext.getContentResolver(), "next_alarm_formatted", paramString);
  }

  private static void setStatusBarIcon(Context paramContext, boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.ALARM_CHANGED");
    localIntent.putExtra("alarmSet", paramBoolean);
    paramContext.sendBroadcast(localIntent);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.clockpackage.alarm.AlarmProvider
 * JD-Core Version:    0.6.0
 */