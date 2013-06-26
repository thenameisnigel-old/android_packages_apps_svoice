package com.vlingo.core.internal.schedule;

import java.text.ParseException;

public abstract class ScheduleEventBase extends EventBase
{
  protected String location;
  protected String organizer;

  public ScheduleEventBase clone()
  {
    return (ScheduleEventBase)super.clone();
  }

  public long getEnd()
  {
    return this.end.getTime();
  }

  public String getEndDate()
  {
    return this.end.getDateStr();
  }

  public String getEndTime()
  {
    return this.end.getTimeStr(false);
  }

  public String getLocation()
  {
    return this.location;
  }

  public String getOrganizer()
  {
    return this.organizer;
  }

  protected void normalizeToNow()
    throws ParseException
  {
    super.normalizeToNow();
    this.end = new EventBase.ScheduleTime(this, DateUtil.endOfGivenDay(this.begin.getTime()));
  }

  protected abstract void setEndTime()
    throws ParseException;

  public void setLocation(String paramString)
  {
    this.location = paramString;
  }

  public void setOrganizer(String paramString)
  {
    this.organizer = paramString;
  }

  public String toString()
  {
    return super.toString() + " loc=" + getLocation() + " org=" + getOrganizer() + " end=" + getEnd();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.schedule.ScheduleEventBase
 * JD-Core Version:    0.6.0
 */