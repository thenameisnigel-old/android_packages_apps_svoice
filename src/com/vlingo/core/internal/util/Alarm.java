package com.vlingo.core.internal.util;

import android.text.format.Time;
import com.vlingo.core.internal.schedule.DateUtil;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class Alarm
  implements Cloneable
{
  private static final int DELETE_FRIDAY_MASK = 286331137;
  private static final int DELETE_MONDAY_MASK = 285282577;
  private static final int DELETE_SATURDAY_MASK = 286331152;
  private static final int DELETE_SUNDAY_MASK = 269553937;
  private static final int DELETE_THURSDAY_MASK = 286330897;
  private static final int DELETE_TUESDAY_MASK = 286265617;
  private static final int DELETE_WEDNESDAY_MASK = 286265617;
  public static final int FRIDAY_MASK = 16;
  public static final int MONDAY_MASK = 1048576;
  public static final int SATURDAY_MASK = 1;
  public static final int SUNDAY_MASK = 16777216;
  public static final int THURSDAY_MASK = 256;
  public static final int TUESDAY_MASK = 65536;
  public static final int WEDNESDAY_MASK = 4096;
  public static final int WEEKDAY_MASK_FRI_SAT = 17895680;
  public static final int WEEKDAY_MASK_SAT_SUN = 1118480;
  public static final int WEEKEND_MASK_FRI_SAT = 17;
  public static final int WEEKEND_MASK_SAT_SUN = 16777217;
  private boolean active = false;
  private int dayMask = 0;
  private int id = 0;
  private String name = null;
  private boolean repeating = false;
  private long time = 0L;

  private void addDayToAlarm(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return;
      this.dayMask = (0x1000000 | this.dayMask);
      continue;
      this.dayMask = (0x100000 | this.dayMask);
      continue;
      this.dayMask = (0x10000 | this.dayMask);
      continue;
      this.dayMask = (0x1000 | this.dayMask);
      continue;
      this.dayMask = (0x100 | this.dayMask);
      continue;
      this.dayMask = (0x10 | this.dayMask);
      continue;
      this.dayMask = (0x1 | this.dayMask);
    }
  }

  private static String addDayToCanonical(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (!paramString1.equals("")));
    for (String str = paramString1 + " " + paramString2; ; str = paramString2)
      return str;
  }

  public static List<Alarm> clone(List<Alarm> paramList)
  {
    List localList;
    try
    {
      localList = (List)paramList.getClass().newInstance();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localList.add(((Alarm)localIterator.next()).clone());
    }
    catch (Exception localException)
    {
      throw new RuntimeException("List cloning unsupported", localException);
    }
    return localList;
  }

  private void deleteDayFromAlarm(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return;
      this.dayMask = (0x10111111 & this.dayMask);
      continue;
      this.dayMask = (0x11011111 & this.dayMask);
      continue;
      this.dayMask = (0x11101111 & this.dayMask);
      continue;
      this.dayMask = (0x11101111 & this.dayMask);
      continue;
      this.dayMask = (0x11111011 & this.dayMask);
      continue;
      this.dayMask = (0x11111101 & this.dayMask);
      continue;
      this.dayMask = (0x11111110 & this.dayMask);
    }
  }

  public static String getDaysCanonical(int paramInt)
  {
    String str = "";
    if ((0x1000000 & paramInt) > 0)
      str = addDayToCanonical(str, "sun");
    if ((0x100000 & paramInt) > 0)
      str = addDayToCanonical(str, "mon");
    if ((0x10000 & paramInt) > 0)
      str = addDayToCanonical(str, "tue");
    if ((paramInt & 0x1000) > 0)
      str = addDayToCanonical(str, "wed");
    if ((paramInt & 0x100) > 0)
      str = addDayToCanonical(str, "thu");
    if ((paramInt & 0x10) > 0)
      str = addDayToCanonical(str, "fri");
    if ((paramInt & 0x1) > 0)
      str = addDayToCanonical(str, "sat");
    return str;
  }

  private boolean isHourMinuteLaterThanNow()
  {
    int i = 1;
    Time localTime = new Time();
    localTime.setToNow();
    if (localTime.hour < getHour());
    while (true)
    {
      return i;
      if ((localTime.hour == getHour()) && (localTime.minute < getMinute()))
        continue;
      i = 0;
    }
  }

  public void addDayFromDaysCanonicalForm(String paramString)
  {
    Time localTime;
    int i;
    if (StringUtils.isNullOrWhiteSpace(paramString))
    {
      localTime = new Time();
      localTime.setToNow();
      if (isHourMinuteLaterThanNow())
      {
        i = localTime.weekDay;
        addDayToAlarm(i);
      }
    }
    while (true)
    {
      return;
      i = (1 + localTime.weekDay) % 7;
      break;
      StringTokenizer localStringTokenizer = new StringTokenizer(paramString, " ");
      while (localStringTokenizer.hasMoreTokens())
      {
        int j = StringUtils.convertDayOfWeekToInt(localStringTokenizer.nextToken());
        if (j == -1)
          continue;
        addDayToAlarm(j);
      }
    }
  }

  public Alarm clone()
  {
    try
    {
      localAlarm = (Alarm)super.clone();
      return localAlarm;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
      {
        localCloneNotSupportedException.printStackTrace();
        Alarm localAlarm = null;
      }
    }
  }

  public void deleteDayFromDaysCanonicalForm(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString))
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(paramString, " ");
      while (localStringTokenizer.hasMoreTokens())
      {
        int i = StringUtils.convertDayOfWeekToInt(localStringTokenizer.nextToken());
        if (i == -1)
          continue;
        deleteDayFromAlarm(i);
      }
    }
  }

  public int getDayMask()
  {
    return this.dayMask;
  }

  public int getHour()
  {
    return (int)this.time / 100;
  }

  public int getId()
  {
    return this.id;
  }

  public int getMinute()
  {
    return (int)this.time % 100;
  }

  public String getName()
  {
    return this.name;
  }

  public long getTime()
  {
    return this.time;
  }

  public String getTimeCanonical()
  {
    int i = getMinute();
    String str1 = new String();
    if (i < 10)
      str1 = "0";
    String str2 = str1 + String.valueOf(i);
    return String.valueOf(getHour()) + ':' + str2;
  }

  public boolean isActive()
  {
    return this.active;
  }

  public boolean isDay(DaysOfWeek paramDaysOfWeek, MatchType paramMatchType)
  {
    int i = 1;
    int j;
    int k;
    label64: int m;
    switch (1.$SwitchMap$com$vlingo$core$internal$util$Alarm$DaysOfWeek[paramDaysOfWeek.ordinal()])
    {
    default:
      j = 1;
      if ((j & this.dayMask) == 0)
        break;
      k = i;
      if (paramMatchType == MatchType.EXACT)
        if ((j ^ this.dayMask) != 0)
        {
          m = i;
          label84: if ((k == 0) || (m != 0))
            break label152;
        }
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return i;
      j = 16777216;
      break;
      j = 1048576;
      break;
      j = 65536;
      break;
      j = 4096;
      break;
      j = 256;
      break;
      j = 16;
      break;
      k = 0;
      break label64;
      m = 0;
      break label84;
      label152: i = 0;
      continue;
      i = k;
    }
  }

  public boolean isWeekday(WeekendType paramWeekendType, MatchType paramMatchType)
  {
    if (!isWeekend(paramWeekendType, paramMatchType));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isWeekend(WeekendType paramWeekendType, MatchType paramMatchType)
  {
    int i = 1;
    int j;
    int k;
    if (paramMatchType == MatchType.EXACT)
      switch (1.$SwitchMap$com$vlingo$core$internal$util$Alarm$WeekendType[paramWeekendType.ordinal()])
      {
      default:
        if (((0x1 & this.dayMask) == 0) || ((0x1000000 & this.dayMask) == 0))
          break;
        j = i;
        if ((0x111110 & this.dayMask) == 0)
        {
          k = i;
          label71: if ((j == 0) || (k == 0))
            break label145;
        }
      case 1:
      }
    while (true)
    {
      return i;
      if (((0x10 & this.dayMask) != 0) && ((0x1 & this.dayMask) != 0))
      {
        j = i;
        label105: if ((0x1111100 & this.dayMask) != 0)
          break label127;
      }
      label127: for (k = i; ; k = 0)
      {
        break;
        j = 0;
        break label105;
      }
      j = 0;
      break;
      k = 0;
      break label71;
      label145: i = 0;
      continue;
      switch (1.$SwitchMap$com$vlingo$core$internal$util$Alarm$WeekendType[paramWeekendType.ordinal()])
      {
      default:
        if ((0x1000001 & this.dayMask) != 0)
          continue;
        i = 0;
        break;
      case 1:
        if ((0x11 & this.dayMask) != 0)
          continue;
        i = 0;
      }
    }
  }

  public boolean isWeeklyRepeating()
  {
    return this.repeating;
  }

  public void setActive(boolean paramBoolean)
  {
    this.active = paramBoolean;
  }

  public void setDayFromDaysCanonicalForm(String paramString)
  {
    this.dayMask = 0;
    addDayFromDaysCanonicalForm(paramString);
  }

  public void setDayMask(int paramInt)
  {
    this.dayMask = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public void setTimeFromCanonical(String paramString)
    throws InvalidParameterException
  {
    Time localTime = new Time();
    localTime.setToNow();
    long l1 = DateUtil.getTimeFromTimeString(paramString, localTime, true);
    long l2 = (l1 + TimeZone.getDefault().getOffset(l1)) / 1000L;
    this.time = (l2 % 3600L / 60L + 100L * (l2 / 3600L % 24L));
  }

  public void setWeeklyRepeating(boolean paramBoolean)
  {
    this.repeating = paramBoolean;
  }

  public String toString()
  {
    return "Alarm{id=" + this.id + "," + "active=" + this.active + "," + "name=" + this.name + "," + "time=" + this.time + "," + "dayMask=" + this.dayMask + "," + "isRepeating=" + this.repeating + "}";
  }

  public static enum DaysOfWeek
  {
    static
    {
      MONDAY = new DaysOfWeek("MONDAY", 1);
      TUESDAY = new DaysOfWeek("TUESDAY", 2);
      WEDNESDAY = new DaysOfWeek("WEDNESDAY", 3);
      THURSDAY = new DaysOfWeek("THURSDAY", 4);
      FRIDAY = new DaysOfWeek("FRIDAY", 5);
      SATURDAY = new DaysOfWeek("SATURDAY", 6);
      DaysOfWeek[] arrayOfDaysOfWeek = new DaysOfWeek[7];
      arrayOfDaysOfWeek[0] = SUNDAY;
      arrayOfDaysOfWeek[1] = MONDAY;
      arrayOfDaysOfWeek[2] = TUESDAY;
      arrayOfDaysOfWeek[3] = WEDNESDAY;
      arrayOfDaysOfWeek[4] = THURSDAY;
      arrayOfDaysOfWeek[5] = FRIDAY;
      arrayOfDaysOfWeek[6] = SATURDAY;
      $VALUES = arrayOfDaysOfWeek;
    }
  }

  public static enum MatchType
  {
    static
    {
      MatchType[] arrayOfMatchType = new MatchType[2];
      arrayOfMatchType[0] = EXACT;
      arrayOfMatchType[1] = LOOSE;
      $VALUES = arrayOfMatchType;
    }
  }

  public static enum WeekendType
  {
    static
    {
      FRI_SAT = new WeekendType("FRI_SAT", 1);
      WeekendType[] arrayOfWeekendType = new WeekendType[2];
      arrayOfWeekendType[0] = SAT_SUN;
      arrayOfWeekendType[1] = FRI_SAT;
      $VALUES = arrayOfWeekendType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.Alarm
 * JD-Core Version:    0.6.0
 */