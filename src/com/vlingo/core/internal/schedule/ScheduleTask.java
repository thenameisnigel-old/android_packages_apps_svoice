package com.vlingo.core.internal.schedule;

import com.vlingo.core.internal.util.StringUtils;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ScheduleTask extends EventBase
  implements Cloneable
{
  private String accountName;
  private int bodyLength;
  private int bodyType = 1;
  private boolean completed = false;
  private int groupid = 1;
  private int importance = 1;
  private String reminderDateCanonical;
  private int reminderSet;
  private long reminderTime = -1L;
  private String reminderTimeCanonical;
  private int reminderType;
  private long utcDueDate;

  public static List<ScheduleTask> clone(List<ScheduleTask> paramList)
  {
    List localList;
    try
    {
      localList = (List)paramList.getClass().newInstance();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localList.add((ScheduleTask)((ScheduleTask)localIterator.next()).clone());
    }
    catch (Exception localException)
    {
      throw new RuntimeException("List cloning unsupported", localException);
    }
    return localList;
  }

  public String getAccountName()
  {
    return this.accountName;
  }

  public int getBodyLength()
  {
    return this.bodyLength;
  }

  public int getBodyType()
  {
    return this.bodyType;
  }

  public boolean getCompleted()
  {
    return this.completed;
  }

  public int getGroupid()
  {
    return this.groupid;
  }

  public int getImportance()
  {
    return this.importance;
  }

  public String getReminderDateTime()
  {
    if ((!StringUtils.isNullOrWhiteSpace(this.reminderDateCanonical)) && (!StringUtils.isNullOrWhiteSpace(this.reminderTimeCanonical)));
    for (String str = this.reminderDateCanonical + " " + this.reminderTimeCanonical; ; str = null)
      return str;
  }

  public int getReminderSet()
  {
    return this.reminderSet;
  }

  public long getReminderTime()
  {
    return this.reminderTime;
  }

  public int getReminderType()
  {
    return this.reminderType;
  }

  public long getUtcDueDate()
  {
    return this.utcDueDate;
  }

  public boolean hasDueDate()
  {
    if (this.begin.getTime() > 0L);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void normalize(boolean paramBoolean)
    throws ParseException
  {
    if ((!StringUtils.isNullOrWhiteSpace(this.reminderDateCanonical)) && (!StringUtils.isNullOrWhiteSpace(this.reminderTimeCanonical)))
      this.reminderTime = DateUtil.getDateFromCanonicalDateAndTimeString(this.reminderDateCanonical + " " + this.reminderTimeCanonical).getTime();
    while (true)
    {
      return;
      this.reminderDateCanonical = determineDate(this.reminderTime, this.reminderDateCanonical);
      this.reminderTimeCanonical = determineTime(this.reminderTime, this.reminderTimeCanonical);
    }
  }

  void setAccountName(String paramString)
  {
    this.accountName = paramString;
  }

  void setBodyLength(int paramInt)
  {
    this.bodyLength = paramInt;
  }

  void setBodyType(int paramInt)
  {
    this.bodyType = paramInt;
  }

  public void setCompleted(boolean paramBoolean)
  {
    this.completed = paramBoolean;
  }

  void setGroupId(int paramInt)
  {
    this.groupid = paramInt;
  }

  void setImportance(int paramInt)
  {
    this.importance = paramInt;
  }

  public void setReminderDateTime(String paramString)
    throws InvalidParameterException
  {
    if (StringUtils.isNullOrWhiteSpace(paramString))
    {
      this.reminderDateCanonical = null;
      this.reminderTimeCanonical = null;
      this.reminderTime = -1L;
      this.reminderSet = 0;
    }
    for (this.reminderType = 0; ; this.reminderType = 2)
    {
      return;
      String[] arrayOfString = StringUtils.split(paramString, ' ');
      if (arrayOfString.length != 2)
        throw new InvalidParameterException("Canonical begin must be of format YYYY-MM-DD hh:mm, received: " + paramString);
      this.reminderDateCanonical = arrayOfString[0];
      this.reminderTimeCanonical = arrayOfString[1];
      this.reminderSet = 1;
    }
  }

  void setReminderSet(int paramInt)
  {
    this.reminderSet = paramInt;
  }

  void setReminderTime(long paramLong)
  {
    this.reminderTime = paramLong;
  }

  void setReminderType(int paramInt)
  {
    this.reminderType = paramInt;
  }

  void setUtcDueDate(long paramLong)
  {
    this.utcDueDate = paramLong;
  }

  public String toString()
  {
    return "ScheduleTask{" + super.toString() + "utcDueDate=" + this.utcDueDate + "accountName=" + this.accountName + "reminderType=" + this.reminderType + "reminderSet=" + this.reminderSet + "reminderTime=" + this.reminderTime + "importance=" + this.importance + "groupid=" + this.groupid + "bodyLength=" + this.bodyLength + "bodyType=" + this.bodyType + "completed=" + this.completed + "reminderTimeCanonical=" + this.reminderTimeCanonical + "preminderDateCanonical=" + this.reminderDateCanonical + "}";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.schedule.ScheduleTask
 * JD-Core Version:    0.6.0
 */