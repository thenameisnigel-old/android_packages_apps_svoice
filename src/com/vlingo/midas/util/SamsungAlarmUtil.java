package com.vlingo.midas.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.vlingo.core.internal.alarms.AlarmQueryObject;
import com.vlingo.core.internal.schedule.ScheduleUtilException;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.core.internal.util.AlarmUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SamsungAlarmUtil extends AlarmUtil
{
  private static final String ALARM_ACTIVE = "active";
  private static final String ALARM_ALARMTIME = "alarmtime";
  private static final String ALARM_ID = "_id";
  private static final String ALARM_NAME = "name";
  private static final String[] ALARM_PROJECTION;
  private static final String ALARM_REPEATTYPE = "repeattype";
  private static final Uri ALARM_URI = Uri.parse("content://com.samsung.sec.android.clockpackage/alarm");
  private static final String AUTHORITY = "com.samsung.sec.android.clockpackage";
  private static final int REPEATING_INCLUSION_MASK = 15;
  private static final int REPEATING_REMOVAL_MASK = -16;
  private static final String TABLE_NAME = "alarm";
  private static final int WEEKLY_NO_REPEAT_MASK = 1;
  private static final int WEEKLY_REPEAT_MASK = 5;

  static
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "name";
    arrayOfString[2] = "active";
    arrayOfString[3] = "alarmtime";
    arrayOfString[4] = "repeattype";
    ALARM_PROJECTION = arrayOfString;
  }

  public static void deleteAlarm(Context paramContext, long paramLong)
    throws ScheduleUtilException
  {
    throw new UnsupportedOperationException();
  }

  public static Alarm getAlarm(Context paramContext, long paramLong)
    throws ScheduleUtilException
  {
    Alarm localAlarm = null;
    Cursor localCursor;
    try
    {
      localCursor = paramContext.getContentResolver().query(ContentUris.withAppendedId(ALARM_URI, paramLong), ALARM_PROJECTION, null, null, null);
      int i = localCursor.getCount();
      if ((localCursor == null) || (i < 1))
        throw new ScheduleUtilException("Error in getting alarm.");
    }
    catch (ScheduleUtilException localScheduleUtilException)
    {
      Log.e("ScheduleUtilException:", localScheduleUtilException.getMessage());
      localScheduleUtilException.printStackTrace();
      throw localScheduleUtilException;
    }
    if (localCursor.moveToFirst())
    {
      localAlarm = getAlarm(localCursor, new AlarmIndices(localCursor));
      if (localCursor != null)
        localCursor.close();
    }
    return localAlarm;
  }

  private static Alarm getAlarm(Cursor paramCursor, AlarmIndices paramAlarmIndices)
  {
    Alarm localAlarm = new Alarm();
    localAlarm.setName(paramCursor.getString(paramAlarmIndices.ALARM_NAME_COL));
    if (paramCursor.getInt(paramAlarmIndices.ALARM_ACTIVE_COL) != 0);
    for (boolean bool = true; ; bool = false)
    {
      localAlarm.setActive(bool);
      localAlarm.setTime(paramCursor.getLong(paramAlarmIndices.ALARM_ALARMTIME_COL));
      localAlarm.setId(paramCursor.getInt(paramAlarmIndices.ALARM_ID_COL));
      localAlarm.setDayMask(getDayMask(paramCursor.getInt(paramAlarmIndices.ALARM_REPEATTYPE_COL)));
      localAlarm.setWeeklyRepeating(isRepeating(paramCursor.getInt(paramAlarmIndices.ALARM_REPEATTYPE_COL)));
      return localAlarm;
    }
  }

  public static List<Alarm> getAlarms(Context paramContext)
  {
    Object localObject = null;
    Cursor localCursor = paramContext.getContentResolver().query(ALARM_URI, ALARM_PROJECTION, null, null, "alarmtime ASC");
    if (localCursor == null);
    while (true)
    {
      return localObject;
      if (!localCursor.moveToFirst())
        continue;
      LinkedList localLinkedList = new LinkedList();
      AlarmIndices localAlarmIndices = new AlarmIndices(localCursor);
      do
        localLinkedList.add(getAlarm(localCursor, localAlarmIndices));
      while (localCursor.moveToNext());
      localCursor.close();
      localObject = localLinkedList;
    }
  }

  public static List<Alarm> getAlarms(Context paramContext, AlarmQueryObject paramAlarmQueryObject)
  {
    List localList = getAlarms(paramContext);
    LinkedList localLinkedList = new LinkedList();
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Alarm localAlarm = (Alarm)localIterator.next();
        if (!paramAlarmQueryObject.matches(localAlarm))
          continue;
        localLinkedList.add(localAlarm);
      }
    }
    return localLinkedList;
  }

  private static void getColumnTypes(Cursor paramCursor, AlarmIndices paramAlarmIndices)
  {
    int i = paramCursor.getType(paramAlarmIndices.ALARM_ID_COL);
    int j = paramCursor.getType(paramAlarmIndices.ALARM_NAME_COL);
    int k = paramCursor.getType(paramAlarmIndices.ALARM_ACTIVE_COL);
    int m = paramCursor.getType(paramAlarmIndices.ALARM_ALARMTIME_COL);
    Toast.makeText(null, "foo" + i + j + k + m, 1).show();
  }

  private static int getDayMask(int paramInt)
  {
    return (paramInt & 0xFFFFFFF0) >> 4;
  }

  private static boolean isRepeating(int paramInt)
  {
    if ((paramInt & 0xF) == 5);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static void updateAlarm(Context paramContext, Alarm paramAlarm1, Alarm paramAlarm2)
    throws ScheduleUtilException
  {
    throw new UnsupportedOperationException();
  }

  private static class AlarmIndices
  {
    final int ALARM_ACTIVE_COL;
    final int ALARM_ALARMTIME_COL;
    final int ALARM_ID_COL;
    final int ALARM_NAME_COL;
    final int ALARM_REPEATTYPE_COL;

    AlarmIndices(Cursor paramCursor)
    {
      this.ALARM_ID_COL = paramCursor.getColumnIndexOrThrow("_id");
      this.ALARM_NAME_COL = paramCursor.getColumnIndexOrThrow("name");
      this.ALARM_ACTIVE_COL = paramCursor.getColumnIndexOrThrow("active");
      this.ALARM_ALARMTIME_COL = paramCursor.getColumnIndexOrThrow("alarmtime");
      this.ALARM_REPEATTYPE_COL = paramCursor.getColumnIndexOrThrow("repeattype");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.SamsungAlarmUtil
 * JD-Core Version:    0.6.0
 */