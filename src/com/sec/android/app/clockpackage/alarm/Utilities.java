package com.sec.android.app.clockpackage.alarm;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import java.util.Formatter;

public class Utilities
{
  public static final String ACTION_ALARM_CHANGED = "android.intent.action.ALARM_CHANGED";
  public static final String EXTRA_ALARMSET = "alarmSet";
  private static StringBuilder mBuilder = new StringBuilder();
  private static Formatter mFmt = new Formatter(mBuilder);

  public static final int getAmPmHour(int paramInt)
  {
    int i = 1;
    if (paramInt < 12)
      i = -1;
    int j = paramInt % 12;
    if (j == 0)
      j = 12;
    return i * j;
  }

  public static final String toDigitString(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    mBuilder.delete(0, mBuilder.length());
    mFmt.format("%d", arrayOfObject);
    return mFmt.toString();
  }

  public static final String toTwoDigitString(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    mBuilder.delete(0, mBuilder.length());
    mFmt.format("%02d", arrayOfObject);
    return mFmt.toString();
  }

  public static final void updateIndicatorAlarm(Context paramContext)
  {
    Cursor localCursor = paramContext.getContentResolver().query(AlarmProvider.CONTENT_URI, null, "active > 0", null, null);
    int i = localCursor.getCount();
    localCursor.close();
    Intent localIntent = new Intent("android.intent.action.ALARM_CHANGED");
    if (i > 0);
    for (boolean bool = true; ; bool = false)
    {
      localIntent.putExtra("alarmSet", bool);
      paramContext.sendBroadcast(localIntent);
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.clockpackage.alarm.Utilities
 * JD-Core Version:    0.6.0
 */