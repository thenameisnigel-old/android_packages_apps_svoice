package com.vlingo.core.internal.schedule;

import com.vlingo.core.internal.contacts.ContactDBUtil;
import com.vlingo.core.internal.contacts.ContactLookupType;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.StringUtils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class ScheduleQueryObject extends ScheduleEventBase
{
  private String attendee;
  private List<ContactMatch> attendeeMatches;
  private int count;
  private boolean countFromEnd;
  private boolean includeAllDay = true;
  private Type type = Type.BEGIN;

  private boolean matchTime(ScheduleEvent paramScheduleEvent)
  {
    int i = 0;
    if ((paramScheduleEvent.getAllDay() == true) && (!this.includeAllDay));
    while (true)
    {
      return i;
      switch (1.$SwitchMap$com$vlingo$core$internal$schedule$ScheduleQueryObject$Type[this.type.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      }
      do
      {
        do
        {
          do
          {
            i = 1;
            break;
          }
          while ((this.begin.getTime() <= paramScheduleEvent.getBegin()) || (paramScheduleEvent.getAllDay()));
          break;
        }
        while ((this.end.getTime() >= paramScheduleEvent.getEnd()) || (paramScheduleEvent.getAllDay()));
        if ((goto 17) || (this.begin.getTime() == paramScheduleEvent.getEnd()))
          break;
      }
      while (this.end.getTime() != paramScheduleEvent.getBegin());
    }
  }

  private void normalize()
  {
    if ((!StringUtils.isNullOrWhiteSpace(this.attendee)) && (this.attendeeMatches == null))
    {
      ContactMatch localContactMatch = ContactDBUtil.getExactContactMatch(ApplicationAdapter.getInstance().getApplicationContext(), this.attendee);
      if (localContactMatch == null)
        break label58;
      this.attendeeMatches = new ArrayList();
      this.attendeeMatches.add(localContactMatch);
    }
    while (true)
    {
      return;
      label58: this.attendeeMatches = ContactDBUtil.getContactMatches(ApplicationAdapter.getInstance().getApplicationContext(), this.attendee, EnumSet.of(ContactLookupType.EMAIL_ADDRESS), true);
    }
  }

  public boolean containsString(ScheduleEvent paramScheduleEvent)
  {
    int i = 0;
    normalize();
    if ((!StringUtils.isNullOrWhiteSpace(this.title)) && (!StringUtils.containsString(this.title, paramScheduleEvent.getTitle())))
      break label101;
    while (true)
    {
      label30: return i;
      if ((!matchTime(paramScheduleEvent)) || ((!StringUtils.isNullOrWhiteSpace(this.location)) && (!StringUtils.containsString(this.location, paramScheduleEvent.getLocation()))))
        continue;
      if ((this.attendeeMatches != null) && (!this.attendeeMatches.isEmpty()))
      {
        label101: String str;
        Iterator localIterator2;
        label132: 
        do
        {
          Iterator localIterator1 = ScheduleUtil.getEventAttendees(ApplicationAdapter.getInstance().getApplicationContext(), paramScheduleEvent, 25).iterator();
          break label132;
          if (!localIterator1.hasNext())
            break label30;
          str = (String)localIterator1.next();
          localIterator2 = this.attendeeMatches.iterator();
          if (!localIterator2.hasNext())
            break;
        }
        while (!StringUtils.containsString(((ContactMatch)localIterator2.next()).primaryDisplayName, str));
        i = 1;
        continue;
      }
      if (!StringUtils.isNullOrWhiteSpace(this.attendee))
      {
        if (!ScheduleUtil.isAttendeeContainInEvent(ApplicationAdapter.getInstance().getApplicationContext(), paramScheduleEvent.getEventID(), this.attendee))
          continue;
        i = 1;
        continue;
      }
      i = 1;
    }
  }

  public boolean containsWord(ScheduleEvent paramScheduleEvent)
  {
    int i = 0;
    normalize();
    if ((!StringUtils.isNullOrWhiteSpace(this.title)) && (!StringUtils.containsWord(this.title, paramScheduleEvent.getTitle())))
      break label101;
    while (true)
    {
      label30: return i;
      if ((!matchTime(paramScheduleEvent)) || ((!StringUtils.isNullOrWhiteSpace(this.location)) && (!StringUtils.containsWord(this.location, paramScheduleEvent.getLocation()))))
        continue;
      if ((this.attendeeMatches != null) && (!this.attendeeMatches.isEmpty()))
      {
        label101: String str;
        Iterator localIterator2;
        label132: 
        do
        {
          Iterator localIterator1 = ScheduleUtil.getEventAttendees(ApplicationAdapter.getInstance().getApplicationContext(), paramScheduleEvent, 25).iterator();
          break label132;
          if (!localIterator1.hasNext())
            break label30;
          str = (String)localIterator1.next();
          localIterator2 = this.attendeeMatches.iterator();
          if (!localIterator2.hasNext())
            break;
        }
        while (!StringUtils.containsWord(((ContactMatch)localIterator2.next()).primaryDisplayName, str));
        i = 1;
        continue;
      }
      if (!StringUtils.isNullOrWhiteSpace(this.attendee))
      {
        if (!ScheduleUtil.isAttendeeContainInEvent(ApplicationAdapter.getInstance().getApplicationContext(), paramScheduleEvent.getEventID(), this.attendee))
          continue;
        i = 1;
        continue;
      }
      i = 1;
    }
  }

  public int getCount()
  {
    return this.count;
  }

  public boolean matches(ScheduleEvent paramScheduleEvent)
  {
    int i = 0;
    normalize();
    if ((!StringUtils.isNullOrWhiteSpace(this.title)) && (!this.title.equalsIgnoreCase(paramScheduleEvent.getTitle())))
      break label101;
    while (true)
    {
      label30: return i;
      if ((!matchTime(paramScheduleEvent)) || ((!StringUtils.isNullOrWhiteSpace(this.location)) && (!this.location.equalsIgnoreCase(paramScheduleEvent.getLocation()))))
        continue;
      if ((this.attendeeMatches != null) && (!this.attendeeMatches.isEmpty()))
      {
        label101: String str;
        Iterator localIterator2;
        label132: 
        do
        {
          Iterator localIterator1 = ScheduleUtil.getEventAttendees(ApplicationAdapter.getInstance().getApplicationContext(), paramScheduleEvent, 25).iterator();
          break label132;
          if (!localIterator1.hasNext())
            break label30;
          str = (String)localIterator1.next();
          localIterator2 = this.attendeeMatches.iterator();
          if (!localIterator2.hasNext())
            break;
        }
        while (!((ContactMatch)localIterator2.next()).primaryDisplayName.equals(str));
        i = 1;
        continue;
      }
      if (!StringUtils.isNullOrWhiteSpace(this.attendee))
      {
        if (!ScheduleUtil.isAttendeeContainInEvent(ApplicationAdapter.getInstance().getApplicationContext(), paramScheduleEvent.getEventID(), this.attendee))
          continue;
        i = 1;
        continue;
      }
      i = 1;
    }
  }

  public void setAttendee(String paramString)
  {
    this.attendee = paramString;
  }

  public void setCount(int paramInt)
  {
    if (paramInt < 0)
      this.countFromEnd = true;
    this.count = Math.abs(paramInt);
  }

  protected void setEndTime()
    throws ParseException
  {
    if (this.end.getTime() == 0L)
      this.end = new EventBase.ScheduleTime(this, DateUtil.endOfGivenDay(this.begin.getTime()));
  }

  public void setIncludeAllDay(boolean paramBoolean)
  {
    this.includeAllDay = paramBoolean;
  }

  public void setType(Type paramType)
  {
    this.type = paramType;
  }

  public void setType(String paramString)
  {
    this.type = Type.of(paramString);
  }

  public boolean shouldCountFromEnd()
  {
    return this.countFromEnd;
  }

  public static enum Type
  {
    static
    {
      Type[] arrayOfType = new Type[3];
      arrayOfType[0] = BEGIN;
      arrayOfType[1] = END;
      arrayOfType[2] = INTERSECT;
      $VALUES = arrayOfType;
    }

    public static Type of(String paramString)
    {
      Type[] arrayOfType = values();
      int i = arrayOfType.length;
      int j = 0;
      Type localType;
      if (j < i)
      {
        localType = arrayOfType[j];
        if (!localType.toString().equalsIgnoreCase(paramString));
      }
      while (true)
      {
        return localType;
        j++;
        break;
        localType = BEGIN;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.schedule.ScheduleQueryObject
 * JD-Core Version:    0.6.0
 */