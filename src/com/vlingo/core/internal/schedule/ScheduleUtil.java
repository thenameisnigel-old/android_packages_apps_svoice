package com.vlingo.core.internal.schedule;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.CalendarContract.CalendarAlerts;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.text.format.Time;
import android.util.Log;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.contacts.ContactDBUtil;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ScheduleUtil
{
  public static final String ACCOUNT_KEY = "accountKey";
  public static final String ACCOUNT_NAME = "accountName";
  public static final String ACCOUNT_NAME_MYTASK = "My task";
  public static final Uri ATTENDEES_URI;
  public static final String ATTENDEE_EMAIL = "attendeeEmail";
  public static final String ATTENDEE_EVENT_ID = "event_id";
  public static final String ATTENDEE_NAME = "attendeeName";
  public static final String ATTENDEE_RELATIONSHIP = "attendeeRelationship";
  public static final String ATTENDEE_STATUS = "attendeeStatus";
  public static final String ATTENDEE_TYPE = "attendeeType";
  private static final int AVAILABLE = 1;
  public static final String BODY = "body";
  public static final String BODY_SIZE = "body_size";
  public static final String BODY_TYPE = "bodyType";
  public static final String COMPLETE = "complete";
  private static final int DEFAULT_MAX_DISPLAY_MATCHES = 6;
  private static final int DEFAULT_MAX_QUERY_MATCHES = 100;
  private static int DELETED_CHECK = 0;
  public static final String DUE_DATE = "due_date";
  private static final String EMAIL_PATTERN = ".*@.*\\..*";
  public static final String EVENTS_DEFAULT_SORT_ORDER = "begin ASC";
  public static final Uri EVENTS_INSTANCES_CONTENT_URI;
  public static final String[] EVENT_PROJECTION;
  public static final String GROUP_ID = "groupId";
  public static final String IMPORTANCE = "importance";
  private static int MAX_ATTENDEES = 0;
  public static final String MY_CALENDAR = "My calendar";
  private static final int NOT_AVAILABLE = 0;
  private static final int NOT_INITIALIZED = -1;
  private static final int PROJECTION_ACCESS_LEVEL = 4;
  private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
  private static final int PROJECTION_ACCOUNT_TYPE = 5;
  private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
  private static final int PROJECTION_ID_INDEX = 0;
  private static final int PROJECTION_SYNC_EVENTS = 3;
  public static final String[] REMINDER_PROJECTION;
  public static final String REMINDER_SET = "reminder_set";
  public static final String REMINDER_TIME = "reminder_time";
  public static final String REMINDER_TYPE = "reminder_type";
  public static final String[] SCHEDULE_END_PROJECTION;
  public static final String[] SCHEDULE_PROJECTION;
  public static final String SCHEDULE_SORT_ORDER = "allDay DESC, begin ASC, title ASC";
  public static final String START_DATE = "start_date";
  public static final String SUBJECT = "subject";
  public static final String SYNC_DIRTY = "_sync_dirty";
  public static final Uri TASKSACCOUTS_CONTENT_URI;
  public static final Uri TASKSGROUP_CONTENT_URI;
  public static final Uri TASKS_REMINDERS_CONTENT_URI = Uri.parse("content://com.android.calendar/TasksReminders");
  static final String TASKS_WHERE = "_id=?";
  public static final String TASK_ACCOUNT_KEY = "accountKey";
  public static final String TASK_ACCOUNT_NAME = "accountName";
  public static final String TASK_BODY = "body";
  public static final String TASK_BODY_SIZE = "body_size";
  public static final String TASK_BODY_TYPE = "bodyType";
  public static final String TASK_COMPLETE = "complete";
  public static final String TASK_DELETED = "deleted";
  public static final String TASK_DUE_DATE = "due_date";
  public static final String TASK_GROUPID = "groupId";
  public static final String TASK_ID = "_id";
  public static final String TASK_IMPORTANCE = "importance";
  protected static final int TASK_INDEX_REMINDER_ACCOUNTKEY = 7;
  protected static final int TASK_INDEX_REMINDER_DUEDATE = 6;
  protected static final int TASK_INDEX_REMINDER_ID = 0;
  protected static final int TASK_INDEX_REMINDER_REMINDER_TIME = 2;
  protected static final int TASK_INDEX_REMINDER_REMINDER_TYPE = 8;
  protected static final int TASK_INDEX_REMINDER_STARTDATE = 5;
  protected static final int TASK_INDEX_REMINDER_STATE = 3;
  protected static final int TASK_INDEX_REMINDER_SUBJECT = 4;
  protected static final int TASK_INDEX_REMINDER_TASKID = 1;
  public static final String[] TASK_PROJECTION;
  public static final String TASK_REMINDER_SET = "reminder_set";
  public static final String TASK_REMINDER_TIME = "reminder_time";
  public static final String TASK_REMINDER_TYPE = "reminder_type";
  public static final String TASK_SORT_ORDER = "due_date";
  public static final String TASK_SUBJECT = "subject";
  public static final String TASK_URI = "content://com.android.calendar/syncTasks";
  public static final String TASK_UTC_DUE_DATE = "utc_due_date";
  public static final String UTC_DUE_DATE = "utc_due_date";
  public static final String UTC_START_DATE = "utc_start_date";
  public static final String _ID = "_id";

  static
  {
    TASKSACCOUTS_CONTENT_URI = Uri.parse("content://com.android.calendar/TasksAccounts");
    TASKSGROUP_CONTENT_URI = Uri.parse("content://com.android.calendar/taskGroup");
    EVENTS_INSTANCES_CONTENT_URI = Uri.parse("content://com.android.calendar/instances/when");
    ATTENDEES_URI = Uri.parse("content://com.android.calendar/attendees");
    String[] arrayOfString1 = new String[9];
    arrayOfString1[0] = "_id";
    arrayOfString1[1] = "task_id";
    arrayOfString1[2] = "reminder_time";
    arrayOfString1[3] = "state";
    arrayOfString1[4] = "subject";
    arrayOfString1[5] = "start_date";
    arrayOfString1[6] = "due_date";
    arrayOfString1[7] = "accountKey";
    arrayOfString1[8] = "reminder_type";
    REMINDER_PROJECTION = arrayOfString1;
    String[] arrayOfString2 = new String[9];
    arrayOfString2[0] = "_id";
    arrayOfString2[1] = "title";
    arrayOfString2[2] = "allDay";
    arrayOfString2[3] = "begin";
    arrayOfString2[4] = "end";
    arrayOfString2[5] = "event_id";
    arrayOfString2[6] = "eventLocation";
    arrayOfString2[7] = "description";
    arrayOfString2[8] = "organizer";
    SCHEDULE_PROJECTION = arrayOfString2;
    String[] arrayOfString3 = new String[3];
    arrayOfString3[0] = "_id";
    arrayOfString3[1] = "dtend";
    arrayOfString3[2] = "duration";
    SCHEDULE_END_PROJECTION = arrayOfString3;
    String[] arrayOfString4 = new String[12];
    arrayOfString4[0] = "_id";
    arrayOfString4[1] = "subject";
    arrayOfString4[2] = "due_date";
    arrayOfString4[3] = "utc_due_date";
    arrayOfString4[4] = "accountName";
    arrayOfString4[5] = "reminder_type";
    arrayOfString4[6] = "reminder_set";
    arrayOfString4[7] = "reminder_time";
    arrayOfString4[8] = "importance";
    arrayOfString4[9] = "groupId";
    arrayOfString4[10] = "body";
    arrayOfString4[11] = "complete";
    TASK_PROJECTION = arrayOfString4;
    String[] arrayOfString5 = new String[6];
    arrayOfString5[0] = "_id";
    arrayOfString5[1] = "account_name";
    arrayOfString5[2] = "calendar_displayName";
    arrayOfString5[3] = "sync_events";
    arrayOfString5[4] = "calendar_access_level";
    arrayOfString5[5] = "account_type";
    EVENT_PROJECTION = arrayOfString5;
    MAX_ATTENDEES = 15;
    DELETED_CHECK = -1;
  }

  private static void addAttendeesToEvent(Context paramContext, List<ContactData> paramList, int paramInt)
  {
    int i = paramList.size();
    if (paramInt > 0)
      for (int j = 0; j < i; j++)
      {
        ContactData localContactData = (ContactData)paramList.get(j);
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("attendeeEmail", localContactData.address);
        localContentValues.put("attendeeName", localContactData.contact.primaryDisplayName);
        localContentValues.put("attendeeRelationship", Integer.valueOf(1));
        localContentValues.put("attendeeStatus", Integer.valueOf(3));
        localContentValues.put("attendeeType", Integer.valueOf(1));
        localContentValues.put("event_id", Integer.valueOf(paramInt));
        paramContext.getContentResolver().insert(ATTENDEES_URI, localContentValues);
      }
  }

  private static boolean addDeleted(Context paramContext, Uri paramUri)
  {
    Cursor localCursor;
    if (DELETED_CHECK == -1)
      localCursor = null;
    while (true)
    {
      try
      {
        localCursor = paramContext.getContentResolver().query(paramUri, null, null, null, null);
        if (localCursor.getColumnIndex("deleted") != -1)
          continue;
        DELETED_CHECK = 0;
        if (localCursor == null)
          continue;
        localCursor.close();
        if (DELETED_CHECK == 1)
        {
          i = 1;
          return i;
          DELETED_CHECK = 1;
          continue;
        }
      }
      finally
      {
        if (localCursor == null)
          continue;
        localCursor.close();
      }
      int i = 0;
    }
  }

  public static Uri addEvent(Context paramContext, ScheduleEvent paramScheduleEvent)
    throws ScheduleUtilException
  {
    ContentResolver localContentResolver;
    try
    {
      localContentResolver = paramContext.getContentResolver();
      if (paramScheduleEvent == null)
        throw new ScheduleUtilException("Null ScheduleEvent detected.  Internal Error");
    }
    catch (ScheduleUtilException localScheduleUtilException)
    {
      Log.e("RuntimeException in ScheduleUtil:", localScheduleUtilException.getMessage());
      localScheduleUtilException.printStackTrace();
      throw localScheduleUtilException;
      if (!hasCalendarAccount(paramContext))
        throw new ScheduleUtilException("Error no calendar account synced.");
    }
    catch (Exception localException)
    {
      Log.e("VLG_CarActivity", localException.getMessage());
      StringWriter localStringWriter = new StringWriter();
      localException.printStackTrace(new PrintWriter(localStringWriter));
      Log.e("VLG_CarActivity", localException.toString() + "\n\r" + localStringWriter.toString());
      throw new ScheduleUtilException("Error in adding an event.");
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("title", paramScheduleEvent.getTitle());
    localContentValues.put("calendar_id", Long.valueOf(getCurrentCalendarId(paramContext)));
    localContentValues.put("eventTimezone", Time.getCurrentTimezone());
    String str1 = paramScheduleEvent.getDescription();
    if ((str1 != null) && (str1.length() > 0))
      localContentValues.put("description", str1);
    String str2 = paramScheduleEvent.getLocation();
    if ((str2 != null) && (str2.length() > 0))
      localContentValues.put("eventLocation", str2);
    String str3 = paramScheduleEvent.getOrganizer();
    if ((str3 != null) && (str3.length() > 0))
      localContentValues.put("organizer", str3);
    long l1;
    if (paramScheduleEvent.getAllDay())
      l1 = 1L;
    while (true)
    {
      localContentValues.put("allDay", Integer.valueOf((int)l1));
      long l2 = paramScheduleEvent.getBegin();
      if (l2 > 0L)
        localContentValues.put("dtstart", Long.valueOf(l2));
      long l3 = paramScheduleEvent.getEnd();
      if (l3 > 0L)
        localContentValues.put("dtend", Long.valueOf(l3));
      Uri localUri = localContentResolver.insert(CalendarContract.Events.CONTENT_URI, localContentValues);
      paramScheduleEvent.setNewEventUri(localUri);
      if (localUri == null)
        throw new ScheduleUtilException("Error in adding an event.");
      int i = getRowIDFromURI(localUri);
      List localList = paramScheduleEvent.getContactDataList();
      if ((localList != null) && (canSaveAttendee(paramContext)))
        addAttendeesToEvent(paramContext, localList, i);
      return localUri;
      l1 = 0L;
    }
  }

  public static Uri addTask(Context paramContext, ScheduleTask paramScheduleTask)
    throws ScheduleUtilException
  {
    ContentResolver localContentResolver;
    try
    {
      localContentResolver = paramContext.getContentResolver();
      if (paramScheduleTask == null)
        throw new ScheduleUtilException("Null ScheduleTask detected.  Internal Error");
    }
    catch (ScheduleUtilException localScheduleUtilException)
    {
      Log.e("ScheduleUtilException:", localScheduleUtilException.getMessage());
      localScheduleUtilException.printStackTrace();
      throw localScheduleUtilException;
    }
    ContentValues localContentValues1 = getContentValues(paramScheduleTask);
    Uri localUri1 = Uri.parse("content://com.android.calendar/syncTasks");
    Uri localUri2;
    try
    {
      localUri2 = localContentResolver.insert(localUri1, localContentValues1);
      if (paramScheduleTask.getReminderTime() != -1L)
      {
        ContentValues localContentValues2 = getReminderContentValues(localUri2, paramScheduleTask);
        localContentResolver.insert(TASKS_REMINDERS_CONTENT_URI, localContentValues2);
      }
      if (localUri2 == null)
        throw new ScheduleUtilException("Error in adding a task.");
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new ScheduleUtilException("Error in adding a task." + localIllegalArgumentException.getLocalizedMessage());
    }
    paramScheduleTask.setNewEventUri(localUri2);
    paramScheduleTask.setEventID(getRowIDFromURI(localUri2));
    return localUri2;
  }

  public static boolean canSaveAttendee(Context paramContext)
  {
    String str = getCurrentCalendar(paramContext);
    if ((str != null) && (!str.equals("My calendar")));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static void deleteEvent(Context paramContext, long paramLong)
    throws ScheduleUtilException
  {
    Uri localUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, paramLong);
    if (paramContext.getContentResolver().delete(localUri, null, null) != 1)
      throw new ScheduleUtilException("Error in deleting a task.");
  }

  public static void deleteReminder(Context paramContext, Uri paramUri)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Uri localUri = Uri.parse("content://com.android.calendar/syncTasks");
    Cursor localCursor = paramContext.getContentResolver().query(localUri, null, null, null, null);
    localCursor.moveToPosition(-1);
    int i = Integer.parseInt(paramUri.getLastPathSegment());
    int j = 0;
    while (localCursor.moveToNext())
    {
      if (i != localCursor.getLong(1))
        continue;
      j = localCursor.getInt(0);
    }
    if (localContentResolver.delete(ContentUris.withAppendedId(TASKS_REMINDERS_CONTENT_URI, j), null, null) != 1);
  }

  public static void deleteTask(Context paramContext, long paramLong)
    throws ScheduleUtilException
  {
    try
    {
      ContentResolver localContentResolver = paramContext.getContentResolver();
      Uri localUri = ContentUris.withAppendedId(Uri.parse("content://com.android.calendar/syncTasks"), paramLong);
      deleteReminder(paramContext, localUri);
      if (localContentResolver.delete(localUri, null, null) != 1)
        throw new ScheduleUtilException("Error in deleting a task.");
    }
    catch (ScheduleUtilException localScheduleUtilException)
    {
      Log.e("ScheduleUtilException:", localScheduleUtilException.getMessage());
      localScheduleUtilException.printStackTrace();
      throw localScheduleUtilException;
    }
  }

  private static boolean doesEventHaveDuration(Context paramContext, long paramLong)
  {
    Cursor localCursor = null;
    try
    {
      Uri localUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, paramLong);
      localCursor = paramContext.getContentResolver().query(localUri, SCHEDULE_END_PROJECTION, null, null, null);
      int i;
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        boolean bool = localCursor.isNull(localCursor.getColumnIndex("duration"));
        if (!bool)
          i = 1;
      }
      do
      {
        return i;
        i = 0;
      }
      while (localCursor == null);
      localCursor.close();
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
  }

  public static List<ScheduleCalendar> getCalendars(Context paramContext)
  {
    Cursor localCursor = paramContext.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, EVENT_PROJECTION, null, null, null);
    LinkedList localLinkedList = new LinkedList();
    while (localCursor.moveToNext())
    {
      ScheduleCalendar localScheduleCalendar = new ScheduleCalendar();
      localScheduleCalendar.setID(localCursor.getLong(0));
      localScheduleCalendar.setDisplayName(localCursor.getString(2));
      localScheduleCalendar.setAccountName(localCursor.getString(1));
      localScheduleCalendar.setEnabled(localCursor.getInt(3));
      localScheduleCalendar.setAccessLevel(localCursor.getInt(4));
      localScheduleCalendar.setAccountType(localCursor.getString(5));
      localLinkedList.add(localScheduleCalendar);
    }
    return localLinkedList;
  }

  private static ContentValues getContentValues(ScheduleTask paramScheduleTask)
  {
    int i = 0;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("subject", paramScheduleTask.getTitle());
    localContentValues.put("body", "");
    if (paramScheduleTask.hasDueDate())
    {
      long l = paramScheduleTask.getBegin();
      localContentValues.put("due_date", Long.valueOf(l));
      localContentValues.put("utc_due_date", Long.valueOf(l));
    }
    localContentValues.put("accountName", "My task");
    localContentValues.put("accountKey", Integer.valueOf(0));
    localContentValues.put("reminder_type", Integer.valueOf(paramScheduleTask.getReminderType()));
    localContentValues.put("reminder_time", Long.valueOf(paramScheduleTask.getReminderTime()));
    localContentValues.put("reminder_set", Integer.valueOf(paramScheduleTask.getReminderSet()));
    localContentValues.put("importance", Integer.valueOf(paramScheduleTask.getImportance()));
    localContentValues.put("groupId", Integer.valueOf(paramScheduleTask.getGroupid()));
    localContentValues.put("bodyType", Integer.valueOf(paramScheduleTask.getBodyType()));
    localContentValues.put("body_size", Integer.valueOf(paramScheduleTask.getBodyLength()));
    if (paramScheduleTask.getCompleted())
      i = 1;
    localContentValues.put("complete", Integer.valueOf(i));
    return localContentValues;
  }

  public static String getCurrentCalendar(Context paramContext)
  {
    String str1 = null;
    String str2 = Settings.getString("calendar.app_package", null);
    String str3 = Settings.getString("calendar.preference_filename", null);
    String str4 = Settings.getString("calendar.default_calendar_key", null);
    if ((str2 == null) || (str3 == null) || (str4 == null));
    while (true)
    {
      return str1;
      try
      {
        Context localContext = paramContext.getApplicationContext().createPackageContext(str2, 0);
        str1 = localContext.getSharedPreferences(str3, 5).getString(str4, null);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
      }
    }
  }

  private static long getCurrentCalendarId(Context paramContext)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    long l1 = -1L;
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "account_name";
    arrayOfString[1] = "ownerAccount";
    arrayOfString[2] = "_id";
    arrayOfString[3] = "sync_events";
    Cursor localCursor = null;
    try
    {
      String str1 = "";
      if (!Settings.getBoolean("use_hidden_calendars", true))
        str1 = " AND visible=1";
      localCursor = localContentResolver.query(CalendarContract.Calendars.CONTENT_URI, arrayOfString, "sync_events=1 AND calendar_access_level>=500" + str1, null, null);
      String str2 = getCurrentCalendar(paramContext);
      String str3;
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        l1 = localCursor.getLong(localCursor.getColumnIndex("_id"));
        str3 = localCursor.getString(localCursor.getColumnIndex("account_name"));
        if (!localCursor.getString(localCursor.getColumnIndex("ownerAccount")).equals(str2))
          break label213;
        long l2 = localCursor.getLong(localCursor.getColumnIndex("_id"));
        l1 = l2;
      }
      while (true)
      {
        return l1;
        label213: if (str3.matches(".*@.*\\..*"))
          l1 = localCursor.getLong(localCursor.getColumnIndex("_id"));
        boolean bool = localCursor.moveToNext();
        if (bool)
          break;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        if (localCursor == null)
          continue;
        localCursor.close();
      }
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject;
  }

  public static long getDateEndMillis(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Date should not be null");
    return DateUtil.endOfGivenDay(DateUtil.getMillisFromString(paramString, true));
  }

  public static long getDateStartMillis(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Date should not be null");
    return DateUtil.startOfGivenDay(DateUtil.getMillisFromString(paramString, true));
  }

  private static ScheduleEvent getEvent(Context paramContext, Cursor paramCursor, ScheduleEventIndices paramScheduleEventIndices)
  {
    ScheduleEvent localScheduleEvent = new ScheduleEvent();
    localScheduleEvent.setTitle(paramCursor.getString(paramScheduleEventIndices.TITLE_COL));
    localScheduleEvent.setLocation(paramCursor.getString(paramScheduleEventIndices.EVENT_LOCATION_COL));
    localScheduleEvent.setDescription(paramCursor.getString(paramScheduleEventIndices.DESCRIPTION_COL));
    localScheduleEvent.setOrganizer(paramCursor.getString(paramScheduleEventIndices.ORGANIZER_COL));
    localScheduleEvent.setEventID(paramCursor.getLong(paramScheduleEventIndices.EVENT_ID_COL));
    if (paramCursor.getInt(paramScheduleEventIndices.ALL_DAY_COL) == 1)
    {
      localScheduleEvent.setAllDay(true);
      Date localDate = new Date((paramCursor.getLong(paramScheduleEventIndices.BEGIN_COL) + paramCursor.getLong(paramScheduleEventIndices.END_COL)) / 2L);
      localDate.setHours(0);
      localDate.setMinutes(0);
      localDate.setSeconds(0);
      localScheduleEvent.setBegin(localDate.getTime());
      localDate.setHours(23);
      localDate.setMinutes(59);
      localDate.setSeconds(59);
      localScheduleEvent.setEnd(localDate.getTime());
    }
    while (true)
    {
      return localScheduleEvent;
      localScheduleEvent.setBegin(paramCursor.getLong(paramScheduleEventIndices.BEGIN_COL));
      localScheduleEvent.setEnd(paramCursor.getLong(paramScheduleEventIndices.END_COL));
    }
  }

  public static List<String> getEventAttendees(Context paramContext, ScheduleEvent paramScheduleEvent, int paramInt)
  {
    LinkedList localLinkedList = new LinkedList();
    ContentResolver localContentResolver = paramContext.getContentResolver();
    String str1 = "event_id = " + paramScheduleEvent.getID();
    Cursor localCursor = null;
    try
    {
      localCursor = localContentResolver.query(ATTENDEES_URI, null, str1, null, null);
      if (localCursor != null)
      {
        localCursor.moveToFirst();
        int i = localCursor.getCount();
        localCursor.getColumnCount();
        int j = 0;
        if (j < i)
        {
          String str2 = localCursor.getString(localCursor.getColumnIndex("attendeeName"));
          if (StringUtils.isNullOrWhiteSpace(str2))
          {
            String str3 = localCursor.getString(localCursor.getColumnIndex("attendeeEmail"));
            if (!StringUtils.isNullOrWhiteSpace(str3))
            {
              ContactData localContactData = ContactDBUtil.getContactMatchesByEmail(paramContext, str3);
              if (localContactData != null)
                localLinkedList.add(localContactData.contact.primaryDisplayName);
            }
          }
          while (true)
          {
            localCursor.moveToNext();
            j++;
            break;
            localLinkedList.add(str2);
          }
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      if (localCursor != null)
        localCursor.close();
    }
    return localLinkedList;
  }

  // ERROR //
  private static List<ContactData> getEventAttendeesCD(Context paramContext, ScheduleEvent paramScheduleEvent, int paramInt)
  {
    // Byte code:
    //   0: new 551	java/util/LinkedList
    //   3: dup
    //   4: invokespecial 552	java/util/LinkedList:<init>	()V
    //   7: astore_3
    //   8: aload_0
    //   9: invokevirtual 299	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   12: astore 4
    //   14: new 368	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 369	java/lang/StringBuilder:<init>	()V
    //   21: ldc_w 743
    //   24: invokevirtual 376	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: aload_1
    //   28: invokevirtual 746	com/vlingo/core/internal/schedule/ScheduleEvent:getID	()J
    //   31: invokevirtual 749	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   34: invokevirtual 380	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: astore 5
    //   39: aconst_null
    //   40: astore 6
    //   42: aload 4
    //   44: getstatic 200	com/vlingo/core/internal/schedule/ScheduleUtil:ATTENDEES_URI	Landroid/net/Uri;
    //   47: aconst_null
    //   48: aload 5
    //   50: aconst_null
    //   51: aconst_null
    //   52: invokevirtual 311	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   55: astore 6
    //   57: aload 6
    //   59: ifnull +205 -> 264
    //   62: aload 6
    //   64: invokeinterface 541 1 0
    //   69: pop
    //   70: aload 6
    //   72: invokeinterface 752 1 0
    //   77: istore 9
    //   79: aload 6
    //   81: invokeinterface 755 1 0
    //   86: pop
    //   87: iconst_0
    //   88: istore 11
    //   90: iload 11
    //   92: iload 9
    //   94: if_icmpge +151 -> 245
    //   97: aload 6
    //   99: ldc 28
    //   101: invokeinterface 317 2 0
    //   106: istore 12
    //   108: aload 6
    //   110: iload 12
    //   112: invokeinterface 560 2 0
    //   117: astore 13
    //   119: aload 6
    //   121: ldc 34
    //   123: invokeinterface 317 2 0
    //   128: istore 14
    //   130: aload 6
    //   132: iload 14
    //   134: invokeinterface 560 2 0
    //   139: astore 15
    //   141: aload 13
    //   143: invokestatic 760	com/vlingo/core/internal/util/StringUtils:isNullOrWhiteSpace	(Ljava/lang/String;)Z
    //   146: ifne +78 -> 224
    //   149: aload_1
    //   150: invokevirtual 420	com/vlingo/core/internal/schedule/ScheduleEvent:getOrganizer	()Ljava/lang/String;
    //   153: aload 13
    //   155: invokevirtual 496	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   158: ifne +66 -> 224
    //   161: aload_1
    //   162: invokevirtual 420	com/vlingo/core/internal/schedule/ScheduleEvent:getOrganizer	()Ljava/lang/String;
    //   165: aload 15
    //   167: invokevirtual 496	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   170: ifne +54 -> 224
    //   173: aload_0
    //   174: aload 13
    //   176: invokestatic 766	com/vlingo/core/internal/contacts/ContactDBUtil:getContactMatchesByEmail	(Landroid/content/Context;Ljava/lang/String;)Lcom/vlingo/core/internal/contacts/ContactData;
    //   179: astore 17
    //   181: aload 17
    //   183: ifnonnull +32 -> 215
    //   186: new 265	com/vlingo/core/internal/contacts/ContactData
    //   189: dup
    //   190: new 281	com/vlingo/core/internal/contacts/ContactMatch
    //   193: dup
    //   194: aload 15
    //   196: lconst_0
    //   197: aload 13
    //   199: iconst_0
    //   200: invokespecial 770	com/vlingo/core/internal/contacts/ContactMatch:<init>	(Ljava/lang/String;JLjava/lang/String;Z)V
    //   203: getstatic 776	com/vlingo/core/internal/contacts/ContactData$Kind:Email	Lcom/vlingo/core/internal/contacts/ContactData$Kind;
    //   206: aload 13
    //   208: iconst_0
    //   209: iconst_0
    //   210: invokespecial 779	com/vlingo/core/internal/contacts/ContactData:<init>	(Lcom/vlingo/core/internal/contacts/ContactMatch;Lcom/vlingo/core/internal/contacts/ContactData$Kind;Ljava/lang/String;II)V
    //   213: astore 17
    //   215: aload_3
    //   216: aload 17
    //   218: invokeinterface 579 2 0
    //   223: pop
    //   224: aload 6
    //   226: invokeinterface 525 1 0
    //   231: pop
    //   232: iinc 11 1
    //   235: goto -145 -> 90
    //   238: astore 7
    //   240: aload 7
    //   242: invokevirtual 652	java/lang/Exception:printStackTrace	()V
    //   245: aload 6
    //   247: ifnull +10 -> 257
    //   250: aload 6
    //   252: invokeinterface 320 1 0
    //   257: aload_3
    //   258: areturn
    //   259: astore 7
    //   261: goto -21 -> 240
    //   264: goto -19 -> 245
    //
    // Exception table:
    //   from	to	target	type
    //   42	87	238	java/lang/Exception
    //   97	232	259	java/lang/Exception
  }

  public static String getEventText(ScheduleEvent paramScheduleEvent)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramScheduleEvent != null)
    {
      localStringBuffer.append(paramScheduleEvent.getTitle());
      localStringBuffer.append(". ");
      if (!paramScheduleEvent.getAllDay())
        break label76;
      localStringBuffer.append(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_schedule_all_day) + " ");
    }
    while (true)
    {
      return localStringBuffer.toString();
      label76: localStringBuffer.append(makeTimeTTSString(paramScheduleEvent.getBegin(), paramScheduleEvent.getEnd()));
    }
  }

  private static List<ScheduleEvent> getEvents(Context paramContext, int paramInt, Cursor paramCursor, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramCursor == null) || (paramBoolean));
    try
    {
      boolean bool1 = paramCursor.moveToLast();
      if (bool1)
      {
        ScheduleEventIndices localScheduleEventIndices = new ScheduleEventIndices(paramCursor);
        label40: boolean bool3;
        do
        {
          localArrayList.add(getEvent(paramContext, paramCursor, localScheduleEventIndices));
          if (localArrayList.size() >= paramInt)
            break;
          if (!paramBoolean)
            break label107;
          bool3 = paramCursor.moveToPrevious();
        }
        while (bool3);
      }
      while (true)
      {
        return localArrayList;
        bool1 = paramCursor.moveToFirst();
        break;
        label107: boolean bool2 = paramCursor.moveToNext();
        if (bool2)
          break label40;
      }
    }
    finally
    {
      if (paramCursor != null)
        paramCursor.close();
    }
    throw localObject;
  }

  public static List<ScheduleEvent> getFiredCalendarAlerts(Context paramContext, int paramInt)
  {
    return getEvents(paramContext, paramInt, paramContext.getContentResolver().query(CalendarContract.CalendarAlerts.CONTENT_URI, SCHEDULE_PROJECTION, "state=1", null, "allDay DESC, begin ASC, title ASC LIMIT 50"), false);
  }

  public static ScheduleEvent getNextScheduleEvent(Context paramContext)
  {
    long l1 = System.currentTimeMillis();
    long l2 = DateUtil.endOfGivenDay(l1);
    ScheduleEvent localScheduleEvent = null;
    List localList = getTimeBoxedScheduleItems(paramContext, 1, l1, l2, false);
    if (localList.size() > 0)
      localScheduleEvent = (ScheduleEvent)localList.get(0);
    return localScheduleEvent;
  }

  public static List<ScheduleEvent> getNextScheduleItems(Context paramContext, long paramLong1, long paramLong2, int paramInt)
  {
    return getTimeBoxedScheduleItems(paramContext, paramInt, paramLong1, paramLong2, false);
  }

  public static List<ScheduleEvent> getPrevScheduleItems(Context paramContext, int paramInt)
  {
    long l = System.currentTimeMillis();
    Time localTime = new Time();
    localTime.set(l);
    localTime.hour = 0;
    localTime.minute = 0;
    localTime.second = 0;
    return getTimeBoxedScheduleItems(paramContext, paramInt, localTime.normalize(false), l, false);
  }

  public static ContentValues getReminderContentValues(Uri paramUri, ScheduleTask paramScheduleTask)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("task_id", Integer.valueOf(Integer.parseInt(paramUri.getLastPathSegment())));
    localContentValues.put("reminder_time", Long.valueOf(paramScheduleTask.getReminderTime()));
    localContentValues.put("state", Integer.valueOf(0));
    localContentValues.put("start_date", Integer.valueOf(0));
    if (paramScheduleTask.hasDueDate())
      localContentValues.put("due_date", Long.valueOf(paramScheduleTask.getBegin()));
    localContentValues.put("accountkey", Integer.valueOf(0));
    localContentValues.put("subject", paramScheduleTask.getTitle());
    localContentValues.put("reminder_type", Integer.valueOf(2));
    return localContentValues;
  }

  private static int getRowIDFromURI(Uri paramUri)
  {
    int i = 0;
    List localList = paramUri.getPathSegments();
    int k;
    if (paramUri != null)
    {
      int j = localList.size();
      if (j > 0)
        k = j - 1;
    }
    try
    {
      int m = Integer.parseInt(((String)localList.get(k)).toString());
      i = m;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static List<ScheduleEvent> getScheduledEvents(Context paramContext, ScheduleQueryObject paramScheduleQueryObject)
  {
    long l1 = System.currentTimeMillis();
    long l2 = paramScheduleQueryObject.getBegin();
    if (l2 == 0L)
      l2 = l1;
    long l3 = paramScheduleQueryObject.getEnd();
    if (l3 == 0L)
      l3 = DateUtil.endOfGivenDay(l2);
    int i = paramScheduleQueryObject.getCount();
    if (i < 1)
      i = 6;
    List localList = getTimeBoxedScheduleItems(paramContext, 100, l2, l3, paramScheduleQueryObject.shouldCountFromEnd());
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = localList.iterator();
    while (localIterator1.hasNext())
    {
      ScheduleEvent localScheduleEvent3 = (ScheduleEvent)localIterator1.next();
      if ((!paramScheduleQueryObject.matches(localScheduleEvent3)) || (j >= i))
        continue;
      localScheduleEvent3.setContactDataList(getEventAttendeesCD(paramContext, localScheduleEvent3, MAX_ATTENDEES));
      localArrayList.add(localScheduleEvent3);
      j++;
    }
    if (localArrayList.size() == 0)
    {
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
      {
        ScheduleEvent localScheduleEvent2 = (ScheduleEvent)localIterator2.next();
        if ((!paramScheduleQueryObject.containsString(localScheduleEvent2)) || (j >= i))
          continue;
        localScheduleEvent2.setContactDataList(getEventAttendeesCD(paramContext, localScheduleEvent2, MAX_ATTENDEES));
        localArrayList.add(localScheduleEvent2);
        j++;
      }
      if (localArrayList.size() == 0)
      {
        Iterator localIterator3 = localList.iterator();
        while (localIterator3.hasNext())
        {
          ScheduleEvent localScheduleEvent1 = (ScheduleEvent)localIterator3.next();
          if ((!paramScheduleQueryObject.containsWord(localScheduleEvent1)) || (j >= i))
            continue;
          localScheduleEvent1.setContactDataList(getEventAttendeesCD(paramContext, localScheduleEvent1, MAX_ATTENDEES));
          localArrayList.add(localScheduleEvent1);
          j++;
        }
      }
    }
    return localArrayList;
  }

  public static ScheduleTask getTask(Context paramContext, long paramLong)
    throws ScheduleUtilException
  {
    ScheduleTask localScheduleTask = null;
    Cursor localCursor;
    try
    {
      localCursor = paramContext.getContentResolver().query(ContentUris.withAppendedId(Uri.parse("content://com.android.calendar/syncTasks"), paramLong), TASK_PROJECTION, null, null, null);
      int i = localCursor.getCount();
      if ((localCursor == null) || (i < 1))
        throw new ScheduleUtilException("Error in getting task.");
    }
    catch (ScheduleUtilException localScheduleUtilException)
    {
      Log.e("ScheduleUtilException:", localScheduleUtilException.getMessage());
      localScheduleUtilException.printStackTrace();
      throw localScheduleUtilException;
    }
    if (localCursor.moveToFirst())
    {
      localScheduleTask = getTask(localCursor, new TaskIndices(localCursor));
      if (localCursor != null)
        localCursor.close();
    }
    return localScheduleTask;
  }

  private static ScheduleTask getTask(Cursor paramCursor, TaskIndices paramTaskIndices)
  {
    ScheduleTask localScheduleTask = new ScheduleTask();
    localScheduleTask.setTitle(paramCursor.getString(paramTaskIndices.TASK_SUBJECT_COL));
    localScheduleTask.setEventID(paramCursor.getInt(paramTaskIndices.TASK_ID_COL));
    localScheduleTask.setBegin(paramCursor.getLong(paramTaskIndices.TASK_DUE_DATE_COL));
    localScheduleTask.setUtcDueDate(paramCursor.getLong(paramTaskIndices.TASK_UTC_DUE_DATE_COL));
    localScheduleTask.setAccountName(paramCursor.getString(paramTaskIndices.TASK_ACCOUNT_NAME_COL));
    localScheduleTask.setReminderType(paramCursor.getInt(paramTaskIndices.TASK_REMINDER_TYPE_COL));
    localScheduleTask.setReminderSet(paramCursor.getInt(paramTaskIndices.TASK_REMINDER_SET_COL));
    localScheduleTask.setReminderTime(paramCursor.getLong(paramTaskIndices.TASK_REMINDER_TIME_COL));
    localScheduleTask.setImportance(paramCursor.getInt(paramTaskIndices.TASK_IMPORTANCE_COL));
    localScheduleTask.setGroupId(paramCursor.getInt(paramTaskIndices.TASK_GROUPID_COL));
    localScheduleTask.setDescription(paramCursor.getString(paramTaskIndices.TASK_BODY_COL));
    if (paramCursor.getInt(paramTaskIndices.TASK_COMPLETE_COL) == 0);
    for (boolean bool = false; ; bool = true)
    {
      localScheduleTask.setCompleted(bool);
      return localScheduleTask;
    }
  }

  private static List<ScheduleTask> getTaskItems(Context paramContext, int paramInt, TaskQueryObject paramTaskQueryObject)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Uri localUri = Uri.parse("content://com.android.calendar/syncTasks");
    String str = "complete=0";
    if (addDeleted(paramContext, localUri))
      str = str + " AND deleted=0";
    if ((!paramTaskQueryObject.getMatchUndated()) && (paramTaskQueryObject.getBegin() > 0L))
      str = str + " AND due_date>=" + paramTaskQueryObject.getBegin() + " AND " + "due_date" + "<=" + paramTaskQueryObject.getEnd();
    return getTasks(paramInt, localContentResolver.query(localUri, TASK_PROJECTION, str, null, "due_date"), paramTaskQueryObject.shouldCountFromEnd());
  }

  private static List<ScheduleTask> getTasks(int paramInt, Cursor paramCursor, boolean paramBoolean)
  {
    LinkedList localLinkedList = new LinkedList();
    boolean bool;
    label39: ScheduleTask localScheduleTask;
    if (paramCursor != null)
    {
      if (!paramBoolean)
        break label97;
      bool = paramCursor.moveToLast();
      if (bool)
      {
        TaskIndices localTaskIndices = new TaskIndices(paramCursor);
        localScheduleTask = getTask(paramCursor, localTaskIndices);
      }
    }
    while (true)
    {
      try
      {
        localScheduleTask.normalize(false);
        localLinkedList.add(localScheduleTask);
        if (localLinkedList.size() >= paramInt)
          continue;
        if (paramBoolean)
        {
          if (paramCursor.moveToPrevious())
            break label39;
          if (paramCursor == null)
            continue;
          paramCursor.close();
          return localLinkedList;
          label97: bool = paramCursor.moveToFirst();
        }
      }
      catch (ParseException localParseException)
      {
        localParseException.printStackTrace();
        continue;
      }
      if (paramCursor.moveToNext())
        break label39;
    }
  }

  public static List<ScheduleTask> getTasks(Context paramContext, TaskQueryObject paramTaskQueryObject)
  {
    long l1 = System.currentTimeMillis();
    long l2 = paramTaskQueryObject.getBegin();
    if (l2 == 0L)
      l2 = l1;
    if (paramTaskQueryObject.getEnd() == 0L)
      DateUtil.endOfGivenDay(l2);
    int i = paramTaskQueryObject.getCount();
    if (i < 1)
      i = 6;
    List localList = getTaskItems(paramContext, 100, paramTaskQueryObject);
    int j = 0;
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator1 = localList.iterator();
    while (localIterator1.hasNext())
    {
      ScheduleTask localScheduleTask3 = (ScheduleTask)localIterator1.next();
      if ((!paramTaskQueryObject.matches(localScheduleTask3)) || (j >= i))
        continue;
      localLinkedList.add(localScheduleTask3);
      j++;
    }
    if (localLinkedList.size() == 0)
    {
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
      {
        ScheduleTask localScheduleTask2 = (ScheduleTask)localIterator2.next();
        if ((!paramTaskQueryObject.containsString(localScheduleTask2)) || (j >= i))
          continue;
        localLinkedList.add(localScheduleTask2);
        j++;
      }
      if (localLinkedList.size() == 0)
      {
        Iterator localIterator3 = localList.iterator();
        while (localIterator3.hasNext())
        {
          ScheduleTask localScheduleTask1 = (ScheduleTask)localIterator3.next();
          if ((!paramTaskQueryObject.containsWord(localScheduleTask1)) || (j >= i))
            continue;
          localLinkedList.add(localScheduleTask1);
          j++;
        }
      }
    }
    return localLinkedList;
  }

  private static List<ScheduleEvent> getTimeBoxedScheduleItems(Context paramContext, int paramInt, long paramLong1, long paramLong2, boolean paramBoolean)
  {
    Uri.Builder localBuilder;
    if (Settings.getBoolean("use_hidden_calendars", true))
    {
      localBuilder = EVENTS_INSTANCES_CONTENT_URI.buildUpon();
      ContentUris.appendId(localBuilder, paramLong1);
      ContentUris.appendId(localBuilder, paramLong2);
    }
    ArrayList localArrayList;
    for (Cursor localCursor = paramContext.getContentResolver().query(localBuilder.build(), SCHEDULE_PROJECTION, "", new String[0], "begin ASC"); ; localCursor = CalendarContract.Instances.query(paramContext.getContentResolver(), SCHEDULE_PROJECTION, paramLong1, paramLong2))
    {
      List localList = getEvents(paramContext, paramInt, localCursor, paramBoolean);
      localArrayList = new ArrayList();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ScheduleEvent localScheduleEvent = (ScheduleEvent)localIterator.next();
        if (((localScheduleEvent.getBegin() < paramLong1) || (localScheduleEvent.getBegin() > paramLong2)) && ((paramLong1 < localScheduleEvent.getBegin()) || (paramLong1 > localScheduleEvent.getEnd())))
          continue;
        localArrayList.add(localScheduleEvent);
      }
    }
    return localArrayList;
  }

  public static String getTimeTTSString(long paramLong)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Time localTime = new Time();
    localTime.set(paramLong);
    localTime.normalize(true);
    String[] arrayOfString = localTime.format("%H %M ").split(" ");
    if (arrayOfString[0].equals("00"))
    {
      localStringBuffer.append(" zero ");
      if (!arrayOfString[1].equals("00"))
        break label124;
      localStringBuffer.append(localTime.format("hundred hours "));
    }
    while (true)
    {
      return localStringBuffer.toString();
      localStringBuffer.append(" " + arrayOfString[0]);
      break;
      label124: localStringBuffer.append(" " + arrayOfString[1]);
    }
  }

  public static boolean hasCalendarAccount(Context paramContext)
  {
    Iterator localIterator = getCalendars(paramContext).iterator();
    do
      if (!localIterator.hasNext())
        break;
    while (!((ScheduleCalendar)localIterator.next()).isEnabled());
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isAttendeeContainInEvent(Context paramContext, long paramLong, String paramString)
  {
    int i = 0;
    String str1 = "event_id = " + paramLong;
    Cursor localCursor = null;
    while (true)
    {
      try
      {
        localCursor = paramContext.getContentResolver().query(ATTENDEES_URI, null, str1, null, null);
        if (localCursor == null)
          continue;
        localCursor.moveToFirst();
        int k = 0;
        if (k >= localCursor.getCount())
          continue;
        String str2 = localCursor.getString(localCursor.getColumnIndex("attendeeName"));
        if (StringUtils.isNullOrWhiteSpace(str2))
          continue;
        boolean bool = str2.equalsIgnoreCase(paramString);
        if (!bool)
          continue;
        i = 1;
        if (localCursor == null)
          continue;
        localCursor.close();
        if (i != 0)
        {
          j = 1;
          return j;
          localCursor.moveToNext();
          k++;
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        if (localCursor == null)
          continue;
        localCursor.close();
        continue;
      }
      finally
      {
        if (localCursor == null)
          continue;
        localCursor.close();
      }
      int j = 0;
    }
  }

  public static ScheduleTask[] listTask(Context paramContext)
    throws ScheduleUtilException
  {
    Cursor localCursor;
    try
    {
      localCursor = paramContext.getContentResolver().query(Uri.parse("content://com.android.calendar/syncTasks"), TASK_PROJECTION, null, null, null);
      int i = localCursor.getCount();
      if ((localCursor == null) || (i < 1))
        throw new ScheduleUtilException("Error in listing tasks.");
    }
    catch (ScheduleUtilException localScheduleUtilException)
    {
      Log.e("ScheduleUtilException:", localScheduleUtilException.getMessage());
      localScheduleUtilException.printStackTrace();
      throw localScheduleUtilException;
    }
    ArrayList localArrayList;
    if (localCursor.moveToFirst())
    {
      localArrayList = new ArrayList();
      TaskIndices localTaskIndices = new TaskIndices(localCursor);
      do
        localArrayList.add(getTask(localCursor, localTaskIndices));
      while (localCursor.moveToNext());
      if (localCursor != null)
        localCursor.close();
    }
    for (ScheduleTask[] arrayOfScheduleTask = (ScheduleTask[])localArrayList.toArray(new ScheduleTask[1]); ; arrayOfScheduleTask = null)
      return arrayOfScheduleTask;
  }

  private static String makeTimeTTSString(long paramLong1, long paramLong2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(getTimeTTSString(paramLong1));
    localStringBuffer.append(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_schedule_to));
    localStringBuffer.append(" ");
    localStringBuffer.append(getTimeTTSString(paramLong2));
    return localStringBuffer.toString();
  }

  private static List<ContactData> mergeContactList(List<ContactData> paramList1, List<ContactData> paramList2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(paramList2);
    if ((paramList1 != null) && (paramList1.size() != 0))
    {
      Iterator localIterator1 = paramList2.iterator();
      while (localIterator1.hasNext())
      {
        ContactData localContactData1 = (ContactData)localIterator1.next();
        Iterator localIterator2 = paramList1.iterator();
        while (localIterator2.hasNext())
        {
          ContactData localContactData2 = (ContactData)localIterator2.next();
          if (!localContactData1.equals(localContactData2))
            continue;
          localArrayList.remove(localContactData2);
        }
      }
    }
    return localArrayList;
  }

  public static void updateEvent(Context paramContext, ScheduleEvent paramScheduleEvent1, ScheduleEvent paramScheduleEvent2)
    throws ScheduleUtilException
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("title", paramScheduleEvent2.getTitle());
    localContentValues.put("eventLocation", paramScheduleEvent2.getLocation());
    localContentValues.put("description", paramScheduleEvent2.getDescription());
    localContentValues.put("dtstart", Long.valueOf(paramScheduleEvent2.getBegin()));
    int i;
    if (paramScheduleEvent2.getAllDay())
    {
      i = 1;
      localContentValues.put("allDay", Integer.valueOf(i));
      if (!doesEventHaveDuration(paramContext, paramScheduleEvent1.id))
      {
        if (paramScheduleEvent2.getEnd() == 0L)
          break label151;
        localContentValues.put("dtend", Long.valueOf(paramScheduleEvent2.getEnd()));
      }
    }
    Uri localUri;
    while (true)
    {
      localUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, paramScheduleEvent1.getID());
      if (paramContext.getContentResolver().update(localUri, localContentValues, null, null) == 1)
        break label176;
      throw new ScheduleUtilException("Error in updating event.");
      i = 0;
      break;
      label151: if (paramScheduleEvent2.getDuration() == 0L)
        continue;
      localContentValues.put("duration", Long.valueOf(paramScheduleEvent2.getDuration()));
    }
    label176: int j = getRowIDFromURI(localUri);
    List localList = mergeContactList(getEventAttendeesCD(paramContext, paramScheduleEvent1, MAX_ATTENDEES), paramScheduleEvent2.getContactDataList());
    if ((localList != null) && (localList.size() > 0))
      addAttendeesToEvent(paramContext, localList, j);
  }

  public static void updateReminder(Context paramContext, Uri paramUri, ScheduleTask paramScheduleTask)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Cursor localCursor = paramContext.getContentResolver().query(TASKS_REMINDERS_CONTENT_URI, null, null, null, null);
    localCursor.moveToPosition(-1);
    int i = Integer.parseInt(paramUri.getLastPathSegment());
    int j = 0;
    while (localCursor.moveToNext())
    {
      if (i != localCursor.getLong(1))
        continue;
      j = localCursor.getInt(0);
    }
    if (paramScheduleTask.getReminderTime() != -1L)
    {
      ContentValues localContentValues = getReminderContentValues(paramUri, paramScheduleTask);
      localContentResolver.update(ContentUris.withAppendedId(TASKS_REMINDERS_CONTENT_URI, j), localContentValues, null, null);
    }
    if (localCursor != null)
      localCursor.close();
  }

  public static void updateTask(Context paramContext, ScheduleTask paramScheduleTask1, ScheduleTask paramScheduleTask2)
    throws ScheduleUtilException
  {
    ContentValues localContentValues = getContentValues(paramScheduleTask2);
    Uri localUri = ContentUris.withAppendedId(Uri.parse("content://com.android.calendar/syncTasks"), paramScheduleTask1.getID());
    int i = paramContext.getContentResolver().update(localUri, localContentValues, null, null);
    updateReminder(paramContext, localUri, paramScheduleTask2);
    if (i != 1)
      throw new ScheduleUtilException("Error in updating task.");
  }

  public static class ScheduleCalendar
  {
    private long ID = -1L;
    private int accessLevel = -1;
    private String accountName = null;
    private String accountType = null;
    private String displayName = null;
    private boolean enabled = false;

    int getAccessLevel()
    {
      return this.accessLevel;
    }

    String getAccountName()
    {
      return this.accountName;
    }

    String getAccountType()
    {
      return this.accountType;
    }

    String getDisplayName()
    {
      return this.displayName;
    }

    long getID()
    {
      return this.ID;
    }

    boolean isEnabled()
    {
      return this.enabled;
    }

    void setAccessLevel(int paramInt)
    {
      this.accessLevel = paramInt;
    }

    void setAccountName(String paramString)
    {
      this.accountName = paramString;
    }

    void setAccountType(String paramString)
    {
      this.accountType = paramString;
    }

    void setDisplayName(String paramString)
    {
      this.displayName = paramString;
    }

    void setEnabled(int paramInt)
    {
      if (paramInt == 0);
      for (boolean bool = false; ; bool = true)
      {
        this.enabled = bool;
        return;
      }
    }

    void setEnabled(boolean paramBoolean)
    {
      this.enabled = paramBoolean;
    }

    void setID(long paramLong)
    {
      this.ID = paramLong;
    }

    public String toString()
    {
      return "" + this.ID + " " + this.displayName + " " + this.accountName + " " + this.enabled;
    }
  }

  private static class ScheduleEventIndices
  {
    final int ALL_DAY_COL;
    final int BEGIN_COL;
    final int DESCRIPTION_COL;
    final int END_COL;
    final int EVENT_ID_COL;
    final int EVENT_LOCATION_COL;
    final int ORGANIZER_COL;
    final int TITLE_COL;

    ScheduleEventIndices(Cursor paramCursor)
    {
      this.TITLE_COL = paramCursor.getColumnIndex("title");
      this.EVENT_LOCATION_COL = paramCursor.getColumnIndex("eventLocation");
      this.DESCRIPTION_COL = paramCursor.getColumnIndex("description");
      this.ORGANIZER_COL = paramCursor.getColumnIndex("organizer");
      this.EVENT_ID_COL = paramCursor.getColumnIndex("event_id");
      this.BEGIN_COL = paramCursor.getColumnIndex("begin");
      this.END_COL = paramCursor.getColumnIndex("end");
      this.ALL_DAY_COL = paramCursor.getColumnIndex("allDay");
    }
  }

  private static class TaskIndices
  {
    final int TASK_ACCOUNT_NAME_COL;
    final int TASK_BODY_COL;
    final int TASK_COMPLETE_COL;
    final int TASK_DUE_DATE_COL;
    final int TASK_GROUPID_COL;
    final int TASK_ID_COL;
    final int TASK_IMPORTANCE_COL;
    final int TASK_REMINDER_SET_COL;
    final int TASK_REMINDER_TIME_COL;
    final int TASK_REMINDER_TYPE_COL;
    final int TASK_SUBJECT_COL;
    final int TASK_UTC_DUE_DATE_COL;

    TaskIndices(Cursor paramCursor)
    {
      this.TASK_SUBJECT_COL = paramCursor.getColumnIndex("subject");
      this.TASK_ID_COL = paramCursor.getColumnIndex("_id");
      this.TASK_DUE_DATE_COL = paramCursor.getColumnIndex("due_date");
      this.TASK_UTC_DUE_DATE_COL = paramCursor.getColumnIndex("utc_due_date");
      this.TASK_ACCOUNT_NAME_COL = paramCursor.getColumnIndex("accountName");
      this.TASK_REMINDER_TYPE_COL = paramCursor.getColumnIndex("reminder_type");
      this.TASK_REMINDER_SET_COL = paramCursor.getColumnIndex("reminder_set");
      this.TASK_REMINDER_TIME_COL = paramCursor.getColumnIndex("reminder_time");
      this.TASK_IMPORTANCE_COL = paramCursor.getColumnIndex("importance");
      this.TASK_GROUPID_COL = paramCursor.getColumnIndex("groupId");
      this.TASK_BODY_COL = paramCursor.getColumnIndex("body");
      this.TASK_COMPLETE_COL = paramCursor.getColumnIndex("complete");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.schedule.ScheduleUtil
 * JD-Core Version:    0.6.0
 */