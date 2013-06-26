package com.vlingo.core.internal.schedule;

import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ScheduleEvent extends ScheduleEventBase
{
  private final int DEFAULT_DURATION = 3600000;
  protected boolean allDay;
  private List<ContactData> contactDataList;

  public static List<ScheduleEvent> clone(List<ScheduleEvent> paramList)
  {
    List localList;
    try
    {
      localList = (List)paramList.getClass().newInstance();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localList.add(((ScheduleEvent)localIterator.next()).clone());
    }
    catch (Exception localException)
    {
      throw new RuntimeException("List cloning unsupported", localException);
    }
    return localList;
  }

  private String contactDataListToString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.contactDataList != null)
    {
      Iterator localIterator = this.contactDataList.iterator();
      while (localIterator.hasNext())
      {
        ContactData localContactData = (ContactData)localIterator.next();
        if (localStringBuilder.length() == 0)
          localStringBuilder.append("List<ContactData>{");
        localStringBuilder.append(localContactData.toString());
      }
      if (localStringBuilder.length() != 0)
        localStringBuilder.append("}");
    }
    return localStringBuilder.toString();
  }

  public void addContactData(ContactData paramContactData)
  {
    if (this.contactDataList == null)
      this.contactDataList = new LinkedList();
    if (!this.contactDataList.contains(paramContactData))
      this.contactDataList.add(paramContactData);
  }

  public ScheduleEvent clone()
  {
    ScheduleEvent localScheduleEvent = (ScheduleEvent)super.clone();
    localScheduleEvent.contactDataList = ContactData.clone(this.contactDataList);
    return localScheduleEvent;
  }

  public boolean getAllDay()
  {
    return this.allDay;
  }

  public List<String> getAttendeeNames()
  {
    LinkedList localLinkedList = new LinkedList();
    if (this.contactDataList != null)
    {
      Iterator localIterator = this.contactDataList.iterator();
      while (localIterator.hasNext())
      {
        ContactData localContactData = (ContactData)localIterator.next();
        String str = localContactData.contact.primaryDisplayName;
        if ((str == null) || (str.isEmpty()))
          str = localContactData.getNormalizedAddress();
        localLinkedList.add(str);
      }
    }
    return localLinkedList;
  }

  public List<ContactData> getContactDataList()
  {
    return this.contactDataList;
  }

  public long getDuration()
  {
    return this.end.getTime() - this.begin.getTime();
  }

  public void resetContactData()
  {
    this.contactDataList = null;
  }

  public void setAllDay(boolean paramBoolean)
  {
    this.allDay = paramBoolean;
  }

  public void setContactDataList(List<ContactData> paramList)
  {
    this.contactDataList = paramList;
  }

  public void setDuration(int paramInt)
  {
    if (((this.end == null) || (this.end.getTime() == 0L)) && (this.begin.getTime() != 0L))
      if (paramInt <= 0)
        break label58;
    label58: for (this.end = new EventBase.ScheduleTime(this, this.begin.getTime() + paramInt); ; this.end = new EventBase.ScheduleTime(this, 3600000L + this.begin.getTime()))
      return;
  }

  protected void setEndTime()
    throws ParseException
  {
  }

  public String toString()
  {
    return "ScheduleEvent{" + super.toString() + "," + contactDataListToString() + "}";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.schedule.ScheduleEvent
 * JD-Core Version:    0.6.0
 */