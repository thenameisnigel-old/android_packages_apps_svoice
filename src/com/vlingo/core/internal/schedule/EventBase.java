package com.vlingo.core.internal.schedule;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings.System;
import android.text.format.Time;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.StringUtils;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Date;

public abstract class EventBase
  implements Cloneable, Comparable<EventBase>
{
  protected ScheduleTime begin = new ScheduleTime(0L);
  protected String description;
  protected ScheduleTime end = new ScheduleTime(0L);
  protected long id;
  private String mAmPmText = "";
  private Uri newEventUri;
  protected String title;

  public EventBase clone()
  {
    try
    {
      localEventBase = (EventBase)super.clone();
      return localEventBase;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
      {
        localCloneNotSupportedException.printStackTrace();
        EventBase localEventBase = null;
      }
    }
  }

  public int compareTo(EventBase paramEventBase)
  {
    int i;
    if (this.begin.getTime() < paramEventBase.begin.getTime())
      i = -1;
    while (true)
    {
      return i;
      if (this.begin.getTime() == paramEventBase.begin.getTime())
      {
        i = 0;
        continue;
      }
      i = 1;
    }
  }

  protected String determineDate(long paramLong, String paramString)
  {
    Object[] arrayOfObject;
    if ((paramLong > 0L) && (StringUtils.isNullOrWhiteSpace(paramString)))
    {
      Date localDate = new Date(paramLong);
      arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(1900 + localDate.getYear());
      arrayOfObject[1] = Integer.valueOf(1 + localDate.getMonth());
      arrayOfObject[2] = Integer.valueOf(localDate.getDate());
    }
    for (String str = String.format("%04d-%02d-%02d", arrayOfObject); ; str = paramString)
      return str;
  }

  protected String determineTime(long paramLong, String paramString)
  {
    boolean bool;
    if ((paramLong > 0L) && (StringUtils.isNullOrWhiteSpace(paramString)))
    {
      String str2 = Settings.System.getString(ApplicationAdapter.getInstance().getApplicationContext().getContentResolver(), "time_12_24");
      bool = false;
      if (!StringUtils.isNullOrWhiteSpace(str2))
      {
        if (!str2.equals("12"))
          break label65;
        bool = true;
      }
    }
    for (String str1 = formatTime(paramLong, bool); ; str1 = paramString)
    {
      return str1;
      label65: bool = false;
      break;
    }
  }

  protected String formatTime(long paramLong, boolean paramBoolean)
  {
    boolean bool = true;
    StringBuffer localStringBuffer = new StringBuffer();
    Time localTime = new Time();
    localTime.set(paramLong);
    localTime.normalize(bool);
    String str;
    if (localTime.hour < 12)
    {
      if (!paramBoolean)
        break label114;
      localStringBuffer.append(localTime.format("%I:%M"));
      if (!bool)
        break label98;
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_format_time_AM);
      label80: setAmPmText(str);
    }
    while (true)
    {
      return localStringBuffer.toString();
      bool = false;
      break;
      label98: str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_format_time_PM);
      break label80;
      label114: localStringBuffer.append(localTime.format("%H:%M"));
      setAmPmText("");
    }
  }

  public String getAmPmText()
  {
    return this.mAmPmText;
  }

  public long getBegin()
  {
    return this.begin.getTime();
  }

  public String getBeginCanonical()
  {
    return this.begin.getDateStr() + " " + this.begin.getTimeStr(false);
  }

  public String getBeginDate()
  {
    return this.begin.getDateStr();
  }

  public String getBeginTime()
  {
    return this.begin.getTimeStr(false);
  }

  public String getDescription()
  {
    return this.description;
  }

  public long getEventID()
  {
    return this.id;
  }

  public long getID()
  {
    return this.id;
  }

  public Uri getNewEventUri()
  {
    return this.newEventUri;
  }

  public String getTitle()
  {
    if (this.title == null);
    for (String str = ""; ; str = this.title)
      return str;
  }

  protected void normalizeToNow()
    throws ParseException
  {
    this.begin = new ScheduleTime(System.currentTimeMillis());
  }

  public void setAmPmText(String paramString)
  {
    this.mAmPmText = paramString;
  }

  public void setBegin(long paramLong)
  {
    this.begin = new ScheduleTime(paramLong);
  }

  public void setBegin(String paramString)
    throws InvalidParameterException, ParseException
  {
    if (StringUtils.isNullOrWhiteSpace(paramString));
    for (this.begin = new ScheduleTime(0L); ; this.begin = new ScheduleTime(paramString))
      return;
  }

  public void setDescription(String paramString)
  {
    this.description = paramString;
  }

  public void setEnd(long paramLong)
  {
    this.end = new ScheduleTime(paramLong);
  }

  public void setEnd(String paramString)
    throws InvalidParameterException, ParseException
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString))
    {
      if (StringUtils.split(paramString, ' ').length != 2)
        throw new InvalidParameterException("Canonical begin must be of format YYYY-MM-DD hh:mm, received: " + paramString);
      this.end = new ScheduleTime(paramString);
    }
  }

  protected void setEndTime()
    throws ParseException
  {
  }

  public void setEventID(long paramLong)
  {
    this.id = paramLong;
  }

  public void setNewEventUri(Uri paramUri)
  {
    this.newEventUri = paramUri;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public String toString()
  {
    return super.toString() + ": event title=" + getTitle() + " desc=" + getDescription() + " start=" + getBegin() + " id=" + getID();
  }

  public class ScheduleTime
  {
    private long time;

    public ScheduleTime(long arg2)
    {
      Object localObject;
      this.time = localObject;
    }

    public ScheduleTime(String arg2)
      throws ParseException
    {
      String str;
      this.time = DateUtil.getDateFromCanonicalDateAndTimeString(str).getTime();
    }

    public ScheduleTime(Date arg2)
    {
      Object localObject;
      this.time = localObject.getTime();
    }

    public Date getDate()
    {
      return new Date(this.time);
    }

    public String getDateStr()
    {
      Date localDate = getDate();
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(1900 + localDate.getYear());
      arrayOfObject[1] = Integer.valueOf(1 + localDate.getMonth());
      arrayOfObject[2] = Integer.valueOf(localDate.getDate());
      return String.format("%04d-%02d-%02d", arrayOfObject);
    }

    public long getTime()
    {
      return this.time;
    }

    public String getTimeStr(boolean paramBoolean)
    {
      return EventBase.this.formatTime(this.time, paramBoolean);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.schedule.EventBase
 * JD-Core Version:    0.6.0
 */