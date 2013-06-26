package com.vlingo.core.internal.dialogmanager.util;

import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.schedule.ScheduleQueryObject;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.core.internal.schedule.TaskQueryObject;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

public class EventUtil
{
  public static ScheduleEvent extractScheduleEvent(VLAction paramVLAction, List<ContactMatch> paramList)
  {
    ScheduleEvent localScheduleEvent = new ScheduleEvent();
    localScheduleEvent.setTitle(VLActionUtil.getParamString(paramVLAction, "title", false));
    localScheduleEvent.setLocation(VLActionUtil.getParamString(paramVLAction, "location", false));
    try
    {
      localScheduleEvent.setBegin(VLActionUtil.getParamString(paramVLAction, "begin", false));
      int i = VLActionUtil.getParamInt(paramVLAction, "duration", 0, false);
      if (i > 0)
        localScheduleEvent.setDuration(1000 * (i * 60));
      List localList = VLActionUtil.getParamStringList(paramVLAction, "invitees", "id", false);
      if (localList != null)
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          ContactData localContactData = DialogDataUtil.getContactDataFromId(paramList, (String)localIterator.next());
          if (localContactData == null)
            continue;
          localScheduleEvent.addContactData(localContactData);
        }
      }
    }
    catch (InvalidParameterException localInvalidParameterException)
    {
      while (true)
        localInvalidParameterException.printStackTrace();
    }
    catch (ParseException localParseException)
    {
      while (true)
        localParseException.printStackTrace();
    }
    return localScheduleEvent;
  }

  public static ScheduleQueryObject extractScheduleQuery(VLAction paramVLAction, List<ContactMatch> paramList)
  {
    ScheduleQueryObject localScheduleQueryObject = new ScheduleQueryObject();
    try
    {
      localScheduleQueryObject.setTitle(VLActionUtil.getParamString(paramVLAction, "title", false));
      localScheduleQueryObject.setLocation(VLActionUtil.getParamString(paramVLAction, "location", false));
      localScheduleQueryObject.setAttendee(VLActionUtil.getParamString(paramVLAction, "invitee", false));
      localScheduleQueryObject.setBegin(VLActionUtil.getParamString(paramVLAction, "range.start", false));
      localScheduleQueryObject.setEnd(VLActionUtil.getParamString(paramVLAction, "range.end", false));
      localScheduleQueryObject.setType(VLActionUtil.getParamString(paramVLAction, "match.type", false));
      localScheduleQueryObject.setCount(getCount(paramVLAction));
      localScheduleQueryObject.setIncludeAllDay(VLActionUtil.getParamBool(paramVLAction, "include.all.day", true, false));
      return localScheduleQueryObject;
    }
    catch (InvalidParameterException localInvalidParameterException)
    {
      while (true)
        localInvalidParameterException.printStackTrace();
    }
    catch (ParseException localParseException)
    {
      while (true)
        localParseException.printStackTrace();
    }
  }

  public static ScheduleTask extractTask(VLAction paramVLAction)
  {
    ScheduleTask localScheduleTask = new ScheduleTask();
    try
    {
      localScheduleTask.setTitle(VLActionUtil.getParamString(paramVLAction, "title", false));
      localScheduleTask.setBegin(VLActionUtil.getParamString(paramVLAction, "date", false));
      localScheduleTask.setReminderDateTime(VLActionUtil.getParamString(paramVLAction, "reminder", false));
      localScheduleTask.normalize(true);
      label46: return localScheduleTask;
    }
    catch (ParseException localParseException)
    {
      break label46;
    }
    catch (InvalidParameterException localInvalidParameterException)
    {
      break label46;
    }
  }

  public static TaskQueryObject extractTaskQuery(VLAction paramVLAction)
  {
    TaskQueryObject localTaskQueryObject = new TaskQueryObject();
    try
    {
      localTaskQueryObject.setBegin(VLActionUtil.getParamString(paramVLAction, "range.start", false));
      localTaskQueryObject.setEnd(VLActionUtil.getParamString(paramVLAction, "range.end", false));
      if (VLActionUtil.getParamBool(paramVLAction, "match.undated", false, false))
        localTaskQueryObject.matchUndatedItems();
      if (VLActionUtil.getParamBool(paramVLAction, "match.completed", false, false))
        localTaskQueryObject.matchCompletedItems();
      localTaskQueryObject.setTitle(VLActionUtil.getParamString(paramVLAction, "title", false));
      localTaskQueryObject.setCount(VLActionUtil.getParamInt(paramVLAction, "count", 0, false));
      localTaskQueryObject.normalize(true);
      label88: return localTaskQueryObject;
    }
    catch (ParseException localParseException)
    {
      break label88;
    }
    catch (InvalidParameterException localInvalidParameterException)
    {
      break label88;
    }
  }

  public static int getCount(VLAction paramVLAction)
  {
    int i = VLActionUtil.getParamInt(paramVLAction, "count", 0, false);
    if (i != 0);
    for (int j = i; ; j = i)
    {
      return j;
      if (!Settings.getBoolean("multi.widget.client.capped", true))
        continue;
      int k = Settings.getMultiWidgetItemsInitialMax();
      if (k >= i)
        continue;
      i = k;
    }
  }

  private static boolean isDeleteKeyword(String paramString)
  {
    if ((paramString != null) && (paramString.equals("#NULL#")));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static ScheduleEvent mergeChanges(VLAction paramVLAction, ScheduleEvent paramScheduleEvent, List<ContactMatch> paramList)
  {
    ScheduleEvent localScheduleEvent = paramScheduleEvent.clone();
    localScheduleEvent.setTitle(mergeValues(paramVLAction, "title", paramScheduleEvent.getTitle()));
    String str1 = VLActionUtil.getParamString(paramVLAction, "begin", false);
    if (isDeleteKeyword(str1))
      localScheduleEvent.setBegin(0L);
    while (true)
    {
      localScheduleEvent.setEnd(0L);
      String str2 = VLActionUtil.getParamString(paramVLAction, "duration", false);
      if (!StringUtils.isNullOrWhiteSpace(str2))
        str2 = String.valueOf(60000 * Integer.decode(str2).intValue());
      mergeValues(paramVLAction, "duration", String.valueOf(paramScheduleEvent.getDuration()));
      String str3;
      if (StringUtils.isNullOrWhiteSpace(str2))
      {
        str3 = String.valueOf(paramScheduleEvent.getDuration());
        label110: if (StringUtils.isNullOrWhiteSpace(str3));
      }
      try
      {
        localScheduleEvent.setDuration(Integer.decode(str3).intValue());
        label130: localScheduleEvent.setLocation(mergeValues(paramVLAction, "location", paramScheduleEvent.getLocation()));
        if (!StringUtils.isNullOrWhiteSpace(VLActionUtil.getParamString(paramVLAction, "invitees", false)))
        {
          List localList = VLActionUtil.getParamStringList(paramVLAction, "invitees", "id", false);
          if (localList != null)
          {
            Iterator localIterator = localList.iterator();
            while (true)
            {
              while (true)
              {
                if (!localIterator.hasNext())
                  break label277;
                ContactData localContactData = DialogDataUtil.getContactDataFromId(paramList, (String)localIterator.next());
                if (localContactData == null)
                  continue;
                localScheduleEvent.addContactData(localContactData);
                continue;
                if (str1 == null)
                  break;
                try
                {
                  localScheduleEvent.setBegin(str1);
                }
                catch (InvalidParameterException localInvalidParameterException)
                {
                  localInvalidParameterException.printStackTrace();
                }
                catch (ParseException localParseException)
                {
                  localParseException.printStackTrace();
                }
              }
              break;
              if (isDeleteKeyword(str2))
              {
                str3 = null;
                break label110;
              }
              str3 = str2;
              break label110;
            }
          }
        }
        label277: return localScheduleEvent;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        break label130;
      }
    }
  }

  public static ScheduleTask mergeChanges(VLAction paramVLAction, ScheduleTask paramScheduleTask)
  {
    ScheduleTask localScheduleTask = (ScheduleTask)paramScheduleTask.clone();
    localScheduleTask.setTitle(mergeValues(paramVLAction, "title", paramScheduleTask.getTitle()));
    String str1 = VLActionUtil.getParamString(paramVLAction, "date", false);
    if (isDeleteKeyword(str1))
      localScheduleTask.setBegin(0L);
    while (true)
    {
      String str2 = VLActionUtil.getParamString(paramVLAction, "reminder", false);
      if (isDeleteKeyword(str1))
      {
        localScheduleTask.setReminderDateTime(null);
        label63: boolean bool = VLActionUtil.getParamBool(paramVLAction, "complete", false, false);
        if (bool)
          localScheduleTask.setCompleted(bool);
      }
      try
      {
        localScheduleTask.normalize(true);
        label89: return localScheduleTask;
        if (str1 == null)
          continue;
        try
        {
          localScheduleTask.setBegin(str1);
        }
        catch (InvalidParameterException localInvalidParameterException)
        {
          localInvalidParameterException.printStackTrace();
        }
        catch (ParseException localParseException1)
        {
          localParseException1.printStackTrace();
        }
        continue;
        if (StringUtils.isNullOrWhiteSpace(str2))
          break label63;
        localScheduleTask.setReminderDateTime(str2);
      }
      catch (ParseException localParseException2)
      {
        break label89;
      }
    }
  }

  public static Alarm mergeChanges(VLAction paramVLAction, Alarm paramAlarm)
  {
    Alarm localAlarm = paramAlarm.clone();
    localAlarm.setTimeFromCanonical(mergeValues(paramVLAction, "time", paramAlarm.getTimeCanonical()));
    String str = mergeValues(paramVLAction, "set", String.valueOf(paramAlarm.isActive()));
    if (isDeleteKeyword(str))
      localAlarm.setActive(false);
    while (true)
    {
      return localAlarm;
      localAlarm.setActive(Boolean.valueOf(str).booleanValue());
    }
  }

  private static String mergeValues(VLAction paramVLAction, String paramString1, String paramString2)
  {
    return mergeValues(VLActionUtil.getParamString(paramVLAction, paramString1, false), paramString2);
  }

  private static String mergeValues(String paramString1, String paramString2)
  {
    if (StringUtils.isNullOrWhiteSpace(paramString1));
    while (true)
    {
      return paramString2;
      if (isDeleteKeyword(paramString1))
      {
        paramString2 = null;
        continue;
      }
      paramString2 = paramString1;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.util.EventUtil
 * JD-Core Version:    0.6.0
 */