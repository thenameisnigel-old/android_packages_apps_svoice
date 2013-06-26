package com.vlingo.core.internal.alarms;

import com.vlingo.core.internal.util.Alarm;
import com.vlingo.core.internal.util.Alarm.MatchType;
import com.vlingo.core.internal.util.StringUtils;
import java.security.InvalidParameterException;

public class AlarmQueryObject
{
  private Boolean active = null;
  protected long begin = 0L;
  protected String beginTime = null;
  private int count = 0;
  protected Integer dayMask = null;
  private Alarm.MatchType daysMatchType = Alarm.MatchType.LOOSE;
  protected long end = 0L;
  protected String endTime = null;
  private Boolean isRepeating = null;
  private String name = null;

  public static boolean isDayMaskMatches(Integer paramInteger1, Integer paramInteger2, Alarm.MatchType paramMatchType)
  {
    int i = 1;
    if (paramInteger1 == null);
    while (true)
    {
      return i;
      if (paramInteger1.equals(paramInteger2))
        continue;
      if (Alarm.MatchType.EXACT.equals(paramMatchType))
      {
        i = 0;
        continue;
      }
      if (((paramInteger1.intValue() | paramInteger2.intValue()) == paramInteger2.intValue()) || ((paramInteger1.intValue() == 1118480) && ((paramInteger1.intValue() & paramInteger2.intValue()) >= i)) || ((paramInteger1.intValue() == 16777217) && ((paramInteger1.intValue() & paramInteger2.intValue()) >= i)))
        continue;
      i = 0;
    }
  }

  private long stringCanonicalLong(String paramString)
  {
    try
    {
      String[] arrayOfString = paramString.split(":");
      int i = Integer.parseInt(arrayOfString[0]);
      int j = Integer.parseInt(arrayOfString[1]);
      l = j + i * 100;
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        long l = 0L;
    }
  }

  public Boolean getActive()
  {
    return this.active;
  }

  public String getBeginCanonical()
  {
    return this.beginTime;
  }

  public int getCount()
  {
    return this.count;
  }

  public Integer getDayMask()
  {
    return this.dayMask;
  }

  public Alarm.MatchType getDaysMatchType()
  {
    return this.daysMatchType;
  }

  public String getName()
  {
    return this.name;
  }

  public Boolean isRepeating()
  {
    return this.isRepeating;
  }

  public boolean matches(Alarm paramAlarm)
  {
    int i = 0;
    normalize();
    if ((this.beginTime != null) && (this.endTime != null) && (this.begin >= 0L) && (this.end >= 0L) && ((this.begin > paramAlarm.getTime()) || (this.end < paramAlarm.getTime())));
    while (true)
    {
      return i;
      if (((getActive() != null) && (getActive().booleanValue() != paramAlarm.isActive())) || ((!StringUtils.isNullOrWhiteSpace(getName())) && (!getName().equalsIgnoreCase(paramAlarm.getName()))) || (!isDayMaskMatches(getDayMask(), Integer.valueOf(paramAlarm.getDayMask()), getDaysMatchType())) || ((isRepeating() != null) && (isRepeating().booleanValue() != paramAlarm.isWeeklyRepeating())))
        continue;
      i = 1;
    }
  }

  public void normalize()
  {
    if (!StringUtils.isNullOrWhiteSpace(this.beginTime))
      this.begin = stringCanonicalLong(this.beginTime);
    if (!StringUtils.isNullOrWhiteSpace(this.endTime))
      this.end = stringCanonicalLong(this.endTime);
  }

  public void setActive(Boolean paramBoolean)
  {
    this.active = paramBoolean;
  }

  public void setBegin(long paramLong)
  {
    this.begin = paramLong;
  }

  public void setBegin(String paramString)
    throws InvalidParameterException
  {
    setBeginTime(paramString);
  }

  public void setBeginTime(String paramString)
  {
    this.beginTime = paramString;
  }

  public void setCount(int paramInt)
  {
    this.count = paramInt;
  }

  public void setDayMask(Integer paramInteger)
  {
    this.dayMask = paramInteger;
  }

  public void setDaysMatchType(Alarm.MatchType paramMatchType)
  {
    this.daysMatchType = paramMatchType;
  }

  public void setEnd(long paramLong)
  {
    this.end = paramLong;
  }

  public void setEnd(String paramString)
    throws InvalidParameterException
  {
    setEndTime(paramString);
  }

  public void setEndTime(String paramString)
  {
    this.endTime = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setRepeating(Boolean paramBoolean)
  {
    this.isRepeating = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.alarms.AlarmQueryObject
 * JD-Core Version:    0.6.0
 */