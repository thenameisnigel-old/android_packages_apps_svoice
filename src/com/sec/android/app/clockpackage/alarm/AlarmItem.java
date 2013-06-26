package com.sec.android.app.clockpackage.alarm;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcel;
import android.util.Log;
import java.util.Calendar;
import java.util.Date;

public final class AlarmItem
{
  private static final boolean DEBUG = true;
  public static final int FLAG_NOTIFICATION_DAILY_BRIEFING = 16;
  public static final int FLAG_NOTIFICATION_SNOOZE = 256;
  public static final int FLAG_NOTIFICATION_SUBDUE = 1;
  private static final int MINUTE_TO_MILLI = 60000;
  private static final String TAG = "AlarmItem";
  public int activate = 0;
  public long alarmAlertTime = -1L;
  public String alarmName = "";
  public int alarmSoundTone = 0;
  public int alarmSoundType = 0;
  public int alarmTime = -1;
  public int alarmVolume = 7;
  public long createTime = -1L;
  public int dailyBriefing = 0;
  public int id = -1;
  public int notificationType = 0;
  public int repeatType = 0;
  public boolean snoozeActivate = false;
  public int snoozeDoneCount = 0;
  public int snoozeDuration = 1;
  public int snoozeRepeat = 2;
  public String soundUri = "";
  public boolean subdueActivate = false;
  public int subdueDuration = 1;
  public int subdueTone = 0;
  public int subdueUri = 0;

  private void calculateAlertTime()
  {
    Calendar localCalendar = Calendar.getInstance();
    long l1 = localCalendar.getTimeInMillis();
    switch (this.activate)
    {
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 10:
    }
    while (true)
    {
      return;
      this.alarmAlertTime = -1L;
      this.snoozeDoneCount = 0;
      continue;
      if (this.subdueActivate)
      {
        if (this.snoozeActivate)
        {
          if (l1 > this.alarmAlertTime)
          {
            long l12 = this.alarmAlertTime;
            int i3 = this.snoozeDoneCount;
            label123: long l13;
            if (i3 >= Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat])
            {
              this.snoozeDoneCount = 0;
              this.activate = 3;
              this.alarmAlertTime = getNextAlertTime(localCalendar);
              l13 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
            }
            while (true)
            {
              if (l1 <= l13)
              {
                this.alarmAlertTime = l13;
                if (this.activate != 0)
                  break label365;
                Log.d("AlarmItem", "----------------- ALARM_INACTIVE -----------------");
                Log.d("AlarmItem", "next alert : snooze active, subdue active");
                Log.d("AlarmItem", "alarm done");
                break;
                this.snoozeDoneCount = (1 + this.snoozeDoneCount);
                l12 += 60000 * Alarm.ALARM_DURATION_TABLE[this.snoozeDuration];
                if (l12 > l1)
                {
                  this.activate = 2;
                  this.alarmAlertTime = l12;
                  if (this.activate == 1)
                    Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
                  while (true)
                  {
                    Log.d("AlarmItem", "next alert : snooze active, subdue active");
                    Log.d("AlarmItem", "found some next snooze valid. set new snooze 1");
                    break;
                    if (this.activate != 2)
                      continue;
                    Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
                  }
                }
                i3++;
                break label123;
              }
              localCalendar.setTimeInMillis(this.alarmAlertTime);
              this.alarmAlertTime = getNextAlertTime(localCalendar);
              l13 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
            }
            label365: if (this.activate == 1)
              Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
            while (true)
            {
              Log.d("AlarmItem", "next alert : snooze active, subdue active");
              break;
              if (this.activate == 2)
              {
                Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
                continue;
              }
              if (this.activate != 3)
                continue;
              Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
            }
          }
          long l7 = 0L;
          if (this.snoozeDoneCount == 0)
            l7 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
          if (l7 > l1)
          {
            this.activate = 3;
            this.alarmAlertTime = l7;
            if (this.activate == 1)
              Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
            while (true)
            {
              Log.d("AlarmItem", "next alert : snooze active, subdue active");
              Log.d("AlarmItem", "set new smart alarm as selected");
              break;
              if (this.activate != 2)
                continue;
              Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
            }
          }
          if (l1 > this.alarmAlertTime)
          {
            if (this.snoozeDoneCount < Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat])
            {
              long l10 = this.alarmAlertTime;
              int i2 = this.snoozeDoneCount;
              label567: long l11;
              if (i2 > Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat])
              {
                this.snoozeDoneCount = 0;
                this.activate = 3;
                this.alarmAlertTime = getNextAlertTime(localCalendar);
                l11 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
              }
              while (true)
              {
                if (l1 <= l11)
                {
                  this.alarmAlertTime = l11;
                  if (this.activate != 0)
                    break label809;
                  Log.d("AlarmItem", "----------------- ALARM_INACTIVE -----------------");
                  Log.d("AlarmItem", "next alert : snooze active, subdue active");
                  Log.d("AlarmItem", "alarm done");
                  break;
                  this.snoozeDoneCount = (1 + this.snoozeDoneCount);
                  l10 += 60000 * Alarm.ALARM_DURATION_TABLE[this.snoozeDuration];
                  if (l10 > l1)
                  {
                    this.activate = 2;
                    this.alarmAlertTime = l10;
                    if (this.activate == 1)
                      Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
                    while (true)
                    {
                      Log.d("AlarmItem", "next alert : snooze active, subdue active");
                      Log.d("AlarmItem", "found some next snooze valid. set new snooze 2");
                      break;
                      if (this.activate != 2)
                        continue;
                      Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
                    }
                  }
                  i2++;
                  break label567;
                }
                localCalendar.setTimeInMillis(this.alarmAlertTime);
                this.alarmAlertTime = getNextAlertTime(localCalendar);
                l11 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
              }
              label809: if (this.activate == 1)
                Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
              while (true)
              {
                Log.d("AlarmItem", "next alert : snooze active, subdue active");
                break;
                if (this.activate == 2)
                {
                  Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
                  continue;
                }
                if (this.activate != 3)
                  continue;
                Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
              }
            }
            this.snoozeDoneCount = 0;
            label912: long l9;
            if (-1 + (0xF & this.repeatType) == 0)
            {
              if (getAlertDayCount() == 1)
              {
                this.activate = 0;
                this.alarmAlertTime = -1L;
              }
            }
            else
            {
              this.activate = 3;
              this.alarmAlertTime = getNextAlertTime(localCalendar);
              l9 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
            }
            while (true)
            {
              if (l1 <= l9)
              {
                this.alarmAlertTime = l9;
                if (this.activate != 0)
                  break label1039;
                Log.d("AlarmItem", "----------------- ALARM_INACTIVE -----------------");
                Log.d("AlarmItem", "next alert : snooze active, subdue active");
                Log.d("AlarmItem", "alarm done");
                break;
                clearRepeatDay(localCalendar);
                break label912;
              }
              localCalendar.setTimeInMillis(this.alarmAlertTime);
              this.alarmAlertTime = getNextAlertTime(localCalendar);
              l9 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
            }
            label1039: if (this.activate == 1)
              Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
            while (true)
            {
              Log.d("AlarmItem", "next alert : snooze active, subdue active");
              break;
              if (this.activate == 2)
              {
                Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
                continue;
              }
              if (this.activate != 3)
                continue;
              Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
            }
          }
          if (this.snoozeDoneCount > Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat])
          {
            if (-1 + (0xF & this.repeatType) == 0)
            {
              if (getAlertDayCount() == 1)
              {
                this.snoozeDoneCount = 0;
                this.activate = 0;
                this.alarmAlertTime = -1L;
                Log.d("AlarmItem", "----------------- ALARM_INACTIVE -----------------");
                Log.d("AlarmItem", "next alert : snooze active, subdue active");
                Log.d("AlarmItem", "snooze done. inactive alarm.");
                continue;
              }
              clearRepeatDay(localCalendar);
            }
            this.snoozeDoneCount = 0;
            this.activate = 3;
            this.alarmAlertTime = getNextAlertTime(localCalendar);
            long l8 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
            while (true)
            {
              if (l1 <= l8)
              {
                this.alarmAlertTime = l8;
                Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
                Log.d("AlarmItem", "next alert : snooze active, subdue active");
                Log.d("AlarmItem", "snooze done. set next smart alarm.");
                break;
              }
              localCalendar.setTimeInMillis(this.alarmAlertTime);
              this.alarmAlertTime = getNextAlertTime(localCalendar);
              l8 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
            }
          }
          if (this.snoozeDoneCount > 0)
          {
            this.activate = 2;
            label1318: if (this.activate != 1)
              break label1361;
            Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
          }
          while (true)
          {
            Log.d("AlarmItem", "next alert : snooze active, subdue active");
            Log.d("AlarmItem", "set new alarm as selected");
            break;
            this.activate = 1;
            break label1318;
            label1361: if (this.activate != 2)
              continue;
            Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
          }
        }
        this.snoozeDoneCount = 0;
        long l5 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
        if (l5 > l1)
        {
          this.activate = 3;
          this.alarmAlertTime = l5;
          if (this.activate == 1)
            Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
          while (true)
          {
            Log.d("AlarmItem", "next alert : snooze inactive, subdue active");
            Log.d("AlarmItem", "set new smart alarm as selected");
            break;
            if (this.activate != 2)
              continue;
            Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
          }
        }
        if (-1 + (0xF & this.repeatType) == 0)
        {
          if (getAlertDayCount() == 1)
          {
            this.snoozeDoneCount = 0;
            this.alarmAlertTime = -1L;
            this.activate = 0;
            Log.d("AlarmItem", "----------------- ALARM_INACTIVE -----------------");
            Log.d("AlarmItem", "next alert : snooze inactive, subdue active");
            Log.d("AlarmItem", "snooze end up. set new smart alarm at next day. (2)");
            continue;
          }
          clearRepeatDay(localCalendar);
        }
        this.snoozeDoneCount = 0;
        this.activate = 3;
        this.alarmAlertTime = getNextAlertTime(localCalendar);
        long l6 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
        while (true)
        {
          if (l1 <= l6)
          {
            this.alarmAlertTime = l6;
            if (this.activate != 0)
              break label1670;
            Log.d("AlarmItem", "----------------- ALARM_INACTIVE -----------------");
            Log.d("AlarmItem", "next alert : snooze active, subdue active");
            Log.d("AlarmItem", "alarm done");
            break;
          }
          localCalendar.setTimeInMillis(this.alarmAlertTime);
          this.alarmAlertTime = getNextAlertTime(localCalendar);
          l6 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
        }
        label1670: if (this.activate == 1)
          Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
        while (true)
        {
          Log.d("AlarmItem", "next alert : snooze inactive, subdue active");
          break;
          if (this.activate == 2)
          {
            Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
            continue;
          }
          if (this.activate != 3)
            continue;
          Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
        }
      }
      if (this.snoozeActivate)
      {
        if (this.snoozeDoneCount > Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat])
        {
          if ((0xE & this.repeatType) == 0)
          {
            if (getAlertDayCount() == 1)
            {
              this.snoozeDoneCount = 0;
              this.activate = 0;
              this.alarmAlertTime = -1L;
              if (this.activate == 1)
                Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
              while (true)
              {
                Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
                Log.d("AlarmItem", "all snooze had finished. clear alarm.");
                break;
                if (this.activate != 2)
                  continue;
                Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
              }
            }
            clearRepeatDay(localCalendar);
          }
          int i1 = getNextAlertDayOffset(localCalendar);
          this.activate = 1;
          this.alarmAlertTime = getNextAlertTime(localCalendar);
          this.snoozeDoneCount = 0;
          label1877: if (l1 <= this.alarmAlertTime)
          {
            if (this.activate != 1)
              break label1961;
            Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
          }
          while (true)
          {
            Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
            Log.d("AlarmItem", "new alarm set as normal alarm with snooze on next (" + i1 + ") day");
            break;
            localCalendar.setTimeInMillis(this.alarmAlertTime);
            this.alarmAlertTime = getNextAlertTime(localCalendar);
            break label1877;
            label1961: if (this.activate != 2)
              continue;
            Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
          }
        }
        if (this.activate == 1)
        {
          if (this.alarmAlertTime > l1)
          {
            if (this.snoozeDoneCount > 0)
            {
              this.activate = 2;
              Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
              Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
              Log.d("AlarmItem", "active alarm changed as snooze");
              continue;
            }
            Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
            Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
            Log.d("AlarmItem", "active alarm set");
            continue;
          }
          for (int m = this.snoozeDoneCount; ; m++)
          {
            if (m >= Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat]);
            do
            {
              if (this.alarmAlertTime <= l1)
                break label2169;
              this.activate = 2;
              Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
              Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
              Log.d("AlarmItem", "found next snooze.");
              break;
              this.snoozeDoneCount = (1 + this.snoozeDoneCount);
              this.alarmAlertTime += 60000 * Alarm.ALARM_DURATION_TABLE[this.snoozeDuration];
            }
            while (this.alarmAlertTime > l1);
          }
          label2169: if ((0xE & this.repeatType) == 0)
          {
            if (getAlertDayCount() == 1)
            {
              this.snoozeDoneCount = 0;
              this.alarmAlertTime = -1L;
              this.activate = 0;
              Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
              Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
              Log.d("AlarmItem", "alarm fired but no snooze can be alert.");
              continue;
            }
            clearRepeatDay(localCalendar);
          }
          this.snoozeDoneCount = 0;
          this.activate = 1;
          int n = getNextAlertDayOffset(localCalendar);
          for (this.alarmAlertTime = getNextAlertTime(localCalendar); ; this.alarmAlertTime = getNextAlertTime(localCalendar))
          {
            if (l1 <= this.alarmAlertTime)
            {
              Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
              Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
              Log.d("AlarmItem", "change to active. new alarm set as normal alarm with snooze on next (" + n + ") day");
              break;
            }
            localCalendar.setTimeInMillis(this.alarmAlertTime);
          }
        }
        if (this.activate == 2)
        {
          if (this.alarmAlertTime < l1);
          for (int k = this.snoozeDoneCount; ; k++)
          {
            if (k >= Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat]);
            do
            {
              if (this.snoozeDoneCount <= Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat])
                break label2604;
              if ((0xE & this.repeatType) != 0)
                break label2502;
              if (getAlertDayCount() != 1)
                break label2497;
              this.snoozeDoneCount = 0;
              this.alarmAlertTime = -1L;
              this.activate = 0;
              Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
              Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
              Log.d("AlarmItem", "snooze end. change as inactive alarm.");
              break;
              this.snoozeDoneCount = (1 + this.snoozeDoneCount);
              this.alarmAlertTime += 60000 * Alarm.ALARM_DURATION_TABLE[this.snoozeDuration];
            }
            while (this.alarmAlertTime > l1);
          }
          label2497: clearRepeatDay(localCalendar);
          label2502: this.snoozeDoneCount = 0;
          int j = getNextAlertDayOffset(localCalendar);
          this.activate = 1;
          for (this.alarmAlertTime = getNextAlertTime(localCalendar); ; this.alarmAlertTime = getNextAlertTime(localCalendar))
          {
            if (l1 <= this.alarmAlertTime)
            {
              Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
              Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
              Log.d("AlarmItem", "change to active. new alarm set as normal alarm with snooze on next (" + j + ") day");
              break;
            }
            localCalendar.setTimeInMillis(this.alarmAlertTime);
          }
        }
        label2604: if (this.activate == 1)
          Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
        while (true)
        {
          Log.d("AlarmItem", "next alert : snooze active, subdue inactive");
          Log.d("AlarmItem", "set next snooze.");
          break;
          if (this.activate != 2)
            continue;
          Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
        }
      }
      if (l1 > this.alarmAlertTime)
      {
        if ((0xE & this.repeatType) == 0)
        {
          if (getAlertDayCount() == 1)
          {
            this.snoozeDoneCount = 0;
            this.alarmAlertTime = -1L;
            this.activate = 0;
            Log.d("AlarmItem", "----------------- ALARM_INACTIVE -----------------");
            Log.d("AlarmItem", "next alert : snooze inactive, subdue inactive");
            Log.d("AlarmItem", "alarm set as tomorrow.");
            continue;
          }
          clearRepeatDay(localCalendar);
        }
        this.snoozeDoneCount = 0;
        int i = getNextAlertDayOffset(localCalendar);
        this.activate = 1;
        this.alarmAlertTime = getNextAlertTime(localCalendar);
        label2760: if (l1 <= this.alarmAlertTime)
        {
          if (this.activate != 1)
            break label2844;
          Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
        }
        while (true)
        {
          Log.d("AlarmItem", "next alert : snooze inactive, subdue inactive");
          Log.d("AlarmItem", "new alarm set as normal alarm on next (" + i + ") day");
          break;
          localCalendar.setTimeInMillis(this.alarmAlertTime);
          this.alarmAlertTime = getNextAlertTime(localCalendar);
          break label2760;
          label2844: if (this.activate != 2)
            continue;
          Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
        }
      }
      if (this.activate == 1)
        Log.d("AlarmItem", "----------------- ALARM_ACTIVE -----------------");
      while (true)
      {
        Log.d("AlarmItem", "next alert : snooze inactive, subdue inactive");
        Log.d("AlarmItem", "valid alarm as one shot alert.");
        break;
        if (this.activate != 2)
          continue;
        Log.d("AlarmItem", "----------------- ALARM_SNOOZE -----------------");
      }
      if (this.alarmAlertTime > l1)
      {
        Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
        Log.d("AlarmItem", "next alert : snooze whatever, subdue active");
        Log.d("AlarmItem", "just let smart alarm alert.");
        continue;
      }
      long l2 = this.alarmAlertTime + 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
      if (l2 > l1)
      {
        this.alarmAlertTime = l2;
        this.activate = 1;
        Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
        Log.d("AlarmItem", "next alert : snooze whatever, subdue active");
        Log.d("AlarmItem", "smart alarm has gone but we got active.");
        continue;
      }
      if (this.snoozeActivate)
      {
        long l3 = l2;
        label3028: if (this.snoozeDoneCount > Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat]);
        while (true)
        {
          if (l3 >= l1)
            break label3268;
          if ((0xE & this.repeatType) != 0)
            break label3151;
          if (getAlertDayCount() != 1)
            break label3146;
          this.activate = 0;
          this.alarmAlertTime = -1L;
          this.snoozeDoneCount = 0;
          Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
          Log.d("AlarmItem", "next alert : snooze whatever, subdue active");
          Log.d("AlarmItem", "all snooze also useless. inactive this alarm.");
          break;
          this.snoozeDoneCount = (1 + this.snoozeDoneCount);
          l3 += Alarm.ALARM_DURATION_TABLE[this.snoozeDuration];
          if (l3 <= l1)
            break label3028;
        }
        label3146: clearRepeatDay(localCalendar);
        label3151: this.activate = 3;
        this.alarmAlertTime = getNextAlertTime(localCalendar);
        long l4 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
        while (true)
        {
          if (l1 <= l4)
          {
            this.alarmAlertTime = l4;
            this.snoozeDoneCount = 0;
            Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
            Log.d("AlarmItem", "next alert : snooze whatever, subdue active");
            Log.d("AlarmItem", "find other day to alert smart alarm.");
            break;
          }
          localCalendar.setTimeInMillis(this.alarmAlertTime);
          this.alarmAlertTime = getNextAlertTime(localCalendar);
          l4 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
        }
        label3268: this.activate = 2;
        this.alarmAlertTime = l3;
        Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
        Log.d("AlarmItem", "next alert : snooze whatever, subdue active");
        Log.d("AlarmItem", "found snooze to alert.");
        continue;
      }
      this.activate = 0;
      this.alarmAlertTime = -1L;
      this.snoozeDoneCount = 0;
      Log.d("AlarmItem", "----------------- ALARM_SUBDUE -----------------");
      Log.d("AlarmItem", "next alert : snooze whatever, subdue active");
      Log.d("AlarmItem", "go inactive.");
      continue;
      this.activate = 1;
      Log.d("AlarmItem", "----------------- ALARM_SUBDUE_NEXT -----------------");
      Log.d("AlarmItem", "next alert : snooze whatever, subdue active");
      Log.d("AlarmItem", "we are going to active cause get next alarm.");
    }
  }

  public static final AlarmItem createItemFromCursor(Cursor paramCursor)
  {
    int i = 1;
    AlarmItem localAlarmItem = new AlarmItem();
    localAlarmItem.id = paramCursor.getInt(0);
    localAlarmItem.activate = paramCursor.getInt(i);
    localAlarmItem.createTime = paramCursor.getLong(2);
    localAlarmItem.alarmAlertTime = paramCursor.getLong(3);
    localAlarmItem.alarmTime = paramCursor.getInt(4);
    localAlarmItem.repeatType = paramCursor.getInt(5);
    localAlarmItem.notificationType = paramCursor.getInt(6);
    int j;
    if (paramCursor.getInt(7) == i)
    {
      j = i;
      localAlarmItem.snoozeActivate = j;
      localAlarmItem.snoozeDuration = paramCursor.getInt(8);
      localAlarmItem.snoozeRepeat = paramCursor.getInt(9);
      localAlarmItem.snoozeDoneCount = paramCursor.getInt(10);
      localAlarmItem.dailyBriefing = paramCursor.getInt(11);
      if (paramCursor.getInt(12) != i)
        break label275;
    }
    while (true)
    {
      localAlarmItem.subdueActivate = i;
      localAlarmItem.subdueDuration = paramCursor.getInt(13);
      localAlarmItem.subdueTone = paramCursor.getInt(14);
      localAlarmItem.alarmSoundType = paramCursor.getInt(15);
      localAlarmItem.alarmSoundTone = paramCursor.getInt(16);
      localAlarmItem.alarmVolume = paramCursor.getInt(17);
      localAlarmItem.subdueUri = paramCursor.getInt(18);
      localAlarmItem.soundUri = paramCursor.getString(19);
      localAlarmItem.alarmName = paramCursor.getString(20);
      return localAlarmItem;
      j = 0;
      break;
      label275: i = 0;
    }
  }

  private long getNextAlertTime(Calendar paramCalendar)
  {
    paramCalendar.add(6, getNextAlertDayOffset(paramCalendar));
    paramCalendar.set(11, this.alarmTime / 100);
    paramCalendar.set(12, this.alarmTime % 100);
    paramCalendar.set(13, 0);
    paramCalendar.set(14, 0);
    return paramCalendar.getTimeInMillis();
  }

  public void calculateFirstAlertTime()
  {
    Log.d("AlarmItem", "calculateFirstAlertTime");
    Calendar localCalendar1 = Calendar.getInstance();
    long l1 = localCalendar1.getTimeInMillis();
    Calendar.getInstance().setTimeInMillis(this.alarmAlertTime);
    Log.d("AlarmItem", "calendar:" + l1 + "system:" + System.currentTimeMillis());
    Log.d("AlarmItem", "alarmAlertTime" + this.alarmAlertTime + "currentMillis:" + l1);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTimeInMillis(this.alarmAlertTime);
    localCalendar2.add(6, -1);
    this.alarmAlertTime = getNextAlertTime(localCalendar2);
    long l2;
    if (this.activate == 3)
    {
      l2 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
      if ((this.alarmAlertTime > l1) && (l2 < l1))
        this.activate = 1;
    }
    while (true)
    {
      return;
      if (l2 > l1)
      {
        this.alarmAlertTime = l2;
        continue;
      }
      if (this.alarmAlertTime >= l1)
        continue;
      localCalendar1.setTimeInMillis(this.alarmAlertTime);
      this.alarmAlertTime = getNextAlertTime(localCalendar2);
      this.alarmAlertTime -= 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
      continue;
      Log.d("AlarmItem", "alarmAlertTime" + this.alarmAlertTime + "currentMillis:" + l1);
      if (this.alarmAlertTime >= l1)
        continue;
      localCalendar1.setTimeInMillis(this.alarmAlertTime);
      this.alarmAlertTime = getNextAlertTime(localCalendar1);
    }
  }

  public void calculateNextAlertTime()
  {
    Log.d("AlarmItem", "calculateNextAlertTime");
    switch (this.activate)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      calculateAlertTime();
      return;
      if (!this.snoozeActivate)
        continue;
      this.snoozeDoneCount = (1 + this.snoozeDoneCount);
      this.alarmAlertTime += 60000 * Alarm.ALARM_DURATION_TABLE[this.snoozeDuration];
      continue;
      this.activate = 10;
      this.alarmAlertTime += 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
    }
  }

  public void calculateNextSmartAlertTime()
  {
    Log.d("AlarmItem", "calculateNextSmartAlertTime");
    calculateOriginalAlertTime();
    Calendar localCalendar = Calendar.getInstance();
    long l1 = localCalendar.getTimeInMillis();
    long l2;
    if ((0xF & this.repeatType) == 1)
    {
      if (getAlertDayCount() == 1)
        this.activate = 0;
    }
    else
    {
      Log.d("AlarmItem", "active : " + this.activate);
      localCalendar.setTimeInMillis(this.alarmAlertTime);
      this.alarmAlertTime = getNextAlertTime(localCalendar);
      l2 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
    }
    while (true)
    {
      if (l1 <= l2)
      {
        this.alarmAlertTime = l2;
        return;
        clearRepeatDay(localCalendar);
        break;
      }
      localCalendar.setTimeInMillis(this.alarmAlertTime);
      this.alarmAlertTime = getNextAlertTime(localCalendar);
      l2 = this.alarmAlertTime - 60000 * Alarm.ALARM_DURATION_TABLE[this.subdueDuration];
    }
  }

  public void calculateOriginalAlertTime()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(11, this.alarmTime / 100);
    localCalendar.set(12, this.alarmTime % 100);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    this.alarmAlertTime = localCalendar.getTimeInMillis();
  }

  public void clearRepeatDay(Calendar paramCalendar)
  {
    int i = paramCalendar.get(7);
    int j = 1;
    for (int k = 0; ; k++)
    {
      if (k > 7 - i)
      {
        Log.d("AlarmItem", "offset:" + i);
        Log.d("AlarmItem", "repeat type : " + Integer.toHexString(this.repeatType));
        Log.d("AlarmItem", "operator : " + Integer.toHexString(j) + "\n~operator : " + Integer.toHexString(j ^ 0xFFFFFFFF));
        this.repeatType &= (j ^ 0xFFFFFFFF);
        Log.d("AlarmItem", "repeat type : " + Integer.toHexString(this.repeatType));
        return;
      }
      j <<= 4;
    }
  }

  public int getAlertDayCount()
  {
    int i = 0;
    int j = 1;
    for (int k = 0; ; k++)
    {
      if (k >= 7)
      {
        Log.d("AlarmItem", "nCount : " + i);
        return i;
      }
      j <<= 4;
      if ((j & this.repeatType) <= 0)
        continue;
      i++;
    }
  }

  public ContentValues getContentValues()
  {
    int i = 1;
    ContentValues localContentValues = new ContentValues();
    int j = 0;
    if (this.snoozeActivate)
      j = 0x0 | 0x100;
    if (this.subdueActivate)
      j |= 1;
    localContentValues.put("active", Integer.valueOf(this.activate));
    localContentValues.put("createtime", Long.valueOf(this.createTime));
    localContentValues.put("alerttime", Long.valueOf(this.alarmAlertTime));
    localContentValues.put("alarmtime", Integer.valueOf(this.alarmTime));
    localContentValues.put("repeattype", Integer.valueOf(this.repeatType));
    localContentValues.put("notitype", Integer.valueOf(j));
    int k;
    if (this.snoozeActivate)
    {
      k = i;
      localContentValues.put("snzactive", Integer.valueOf(k));
      localContentValues.put("snzduration", Integer.valueOf(this.snoozeDuration));
      localContentValues.put("snzrepeat", Integer.valueOf(this.snoozeRepeat));
      localContentValues.put("snzcount", Integer.valueOf(this.snoozeDoneCount));
      localContentValues.put("dailybrief", Integer.valueOf(this.dailyBriefing));
      if (!this.subdueActivate)
        break label327;
    }
    while (true)
    {
      localContentValues.put("sbdactive", Integer.valueOf(i));
      localContentValues.put("sbdduration", Integer.valueOf(this.subdueDuration));
      localContentValues.put("sbdtone", Integer.valueOf(this.subdueTone));
      localContentValues.put("alarmsound", Integer.valueOf(this.alarmSoundType));
      localContentValues.put("alarmtone", Integer.valueOf(this.alarmSoundTone));
      localContentValues.put("volume", Integer.valueOf(this.alarmVolume));
      localContentValues.put("sbduri", Integer.valueOf(this.subdueUri));
      localContentValues.put("alarmuri", this.soundUri);
      localContentValues.put("name", this.alarmName);
      return localContentValues;
      k = 0;
      break;
      label327: i = 0;
    }
  }

  public int getNextAlertDayOffset(Calendar paramCalendar)
  {
    int i = paramCalendar.get(7);
    for (int j = 1; ; j++)
    {
      if (j > 7)
        j = 0;
      int k;
      do
      {
        return j;
        k = i + j;
        if (k <= 7)
          continue;
        k -= 7;
      }
      while ((268435456 >> k * 4 & this.repeatType >> 4) > 0);
    }
  }

  public void readFromIntent(Intent paramIntent)
  {
    int i = 1;
    byte[] arrayOfByte = paramIntent.getByteArrayExtra("com.samsung.sec.android.clockpackage.alarm.ALARM_DATA");
    Parcel localParcel;
    int j;
    if (arrayOfByte != null)
    {
      localParcel = Parcel.obtain();
      localParcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
      localParcel.setDataPosition(0);
      this.id = localParcel.readInt();
      this.activate = localParcel.readInt();
      this.createTime = localParcel.readLong();
      this.alarmAlertTime = localParcel.readLong();
      this.alarmTime = localParcel.readInt();
      this.repeatType = localParcel.readInt();
      this.notificationType = localParcel.readInt();
      if (localParcel.readInt() != i)
        break label238;
      j = i;
      this.snoozeActivate = j;
      this.snoozeDuration = localParcel.readInt();
      this.snoozeRepeat = localParcel.readInt();
      this.snoozeDoneCount = localParcel.readInt();
      this.dailyBriefing = localParcel.readInt();
      if (localParcel.readInt() != i)
        break label244;
    }
    while (true)
    {
      this.subdueActivate = i;
      this.subdueDuration = localParcel.readInt();
      this.subdueTone = localParcel.readInt();
      this.alarmSoundType = localParcel.readInt();
      this.alarmSoundTone = localParcel.readInt();
      this.alarmVolume = localParcel.readInt();
      this.subdueUri = localParcel.readInt();
      this.soundUri = localParcel.readString();
      this.alarmName = localParcel.readString();
      return;
      label238: j = 0;
      break;
      label244: i = 0;
    }
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(this.alarmAlertTime);
    localStringBuilder.append("id : ");
    localStringBuilder.append(this.id);
    localStringBuilder.append(", \n");
    localStringBuilder.append("activate : ");
    localStringBuilder.append(this.activate);
    localStringBuilder.append(", \n");
    localStringBuilder.append("createTime : ");
    localStringBuilder.append(this.createTime);
    localStringBuilder.append(", \n");
    localStringBuilder.append("AlertTime : ");
    localStringBuilder.append(this.alarmAlertTime);
    localStringBuilder.append(", \n");
    localStringBuilder.append("AlertT___ (at cal) : ");
    localStringBuilder.append(Alarm.digitToAlphabetStr(localCalendar.getTime().toString()));
    localStringBuilder.append(", \n");
    localStringBuilder.append("alarmT___ : ");
    localStringBuilder.append(Alarm.digitToAlphabetStr(Integer.toString(this.alarmTime)));
    localStringBuilder.append(", \n");
    localStringBuilder.append("repeatType : 0x");
    localStringBuilder.append(Integer.toHexString(this.repeatType));
    localStringBuilder.append(", \n");
    localStringBuilder.append("notificationType : 0x");
    localStringBuilder.append(Integer.toHexString(this.notificationType));
    localStringBuilder.append(", \n");
    localStringBuilder.append("snoozeActivate : ");
    localStringBuilder.append(this.snoozeActivate);
    localStringBuilder.append(", \n");
    localStringBuilder.append("snoozeDuration : ");
    localStringBuilder.append(Alarm.ALARM_DURATION_TABLE[this.snoozeDuration]);
    localStringBuilder.append(", \n");
    localStringBuilder.append("snoozeRepeat : ");
    localStringBuilder.append(Alarm.ALARM_SNOOZE_COUNT_TABLE[this.snoozeRepeat]);
    localStringBuilder.append(", \n");
    localStringBuilder.append("snoozeDoneCount : ");
    localStringBuilder.append(this.snoozeDoneCount);
    localStringBuilder.append(", \n");
    localStringBuilder.append("dailyBriefing : ");
    localStringBuilder.append(this.dailyBriefing);
    localStringBuilder.append(", \n");
    localStringBuilder.append("subdueActivate : ");
    localStringBuilder.append(this.subdueActivate);
    localStringBuilder.append(", \n");
    localStringBuilder.append("subdueDuration : ");
    localStringBuilder.append(this.subdueDuration);
    localStringBuilder.append(", \n");
    localStringBuilder.append("subdueTone : ");
    localStringBuilder.append(this.subdueTone);
    localStringBuilder.append(", \n");
    localStringBuilder.append("alarmSoundType : ");
    localStringBuilder.append(this.alarmSoundType);
    localStringBuilder.append(", \n");
    localStringBuilder.append("alarmSoundTone : ");
    localStringBuilder.append(this.alarmSoundTone);
    localStringBuilder.append(", \n");
    localStringBuilder.append("alarmVolume : ");
    localStringBuilder.append(this.alarmVolume);
    localStringBuilder.append(", \n");
    localStringBuilder.append("subdueUri : ");
    localStringBuilder.append(this.subdueUri);
    localStringBuilder.append(", \n");
    return localStringBuilder.toString();
  }

  public void writeToParcel(Parcel paramParcel)
  {
    int i = 1;
    paramParcel.writeInt(this.id);
    paramParcel.writeInt(this.activate);
    paramParcel.writeLong(this.createTime);
    paramParcel.writeLong(this.alarmAlertTime);
    paramParcel.writeInt(this.alarmTime);
    paramParcel.writeInt(this.repeatType);
    paramParcel.writeInt(this.notificationType);
    int j;
    if (this.snoozeActivate)
    {
      j = i;
      paramParcel.writeInt(j);
      paramParcel.writeInt(this.snoozeDuration);
      paramParcel.writeInt(this.snoozeRepeat);
      paramParcel.writeInt(this.snoozeDoneCount);
      paramParcel.writeInt(this.dailyBriefing);
      if (!this.subdueActivate)
        break label186;
    }
    while (true)
    {
      paramParcel.writeInt(i);
      paramParcel.writeInt(this.subdueDuration);
      paramParcel.writeInt(this.subdueTone);
      paramParcel.writeInt(this.alarmSoundType);
      paramParcel.writeInt(this.alarmSoundTone);
      paramParcel.writeInt(this.alarmVolume);
      paramParcel.writeInt(this.subdueUri);
      paramParcel.writeString(this.soundUri);
      paramParcel.writeString(this.alarmName);
      return;
      j = 0;
      break;
      label186: i = 0;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.clockpackage.alarm.AlarmItem
 * JD-Core Version:    0.6.0
 */