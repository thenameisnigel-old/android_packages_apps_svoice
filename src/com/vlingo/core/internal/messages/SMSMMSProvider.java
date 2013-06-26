package com.vlingo.core.internal.messages;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.ContactUtil;
import com.vlingo.core.internal.util.StringUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class SMSMMSProvider
{
  private static final String BODY = "body";
  private static final String CONTENT_URL_MESSAGE_CONVERSATION = "content://mms-sms/conversations";
  private static final String CONTENT_URL_MMS = "content://mms/";
  private static final String CONTENT_URL_SMS = "content://sms/";
  private static final String DATE = "date";
  private static final String ID = "_id";
  private static final Uri MESSAGE_CONVERSATION_URI;
  private static final String MIME_TYPE = "ct_t";
  private static final long MMS_DATE_CORRECTION_FACTOR = 1000L;
  private static final String[] MMS_PROJECTION;
  public static final String MMS_TYPE = "MMS";
  private static final Uri MMS_URI;
  private static final String MSG_BOX = "msg_box";
  private static final int MSG_BOX_INBOX = 1;
  private static final String NAME = "name";
  private static final String PHONE = "address";
  private static final String QUERY_FRAGMENT_NOT_SEEN = "seen = 0";
  private static final String QUERY_FRAGMENT_NOT_SEEN_AND_NOT_READ = "seen = 0 AND read = 0";
  private static final String QUERY_FRAGMENT_SMS_RECEIVED_FLAG = "type = 1";
  private static final String READ = "read";
  private static final String SEEN = "seen";
  private static final String[] SMS_PROJECTION;
  public static final String SMS_TYPE = "SMS";
  private static final Uri SMS_URI;
  private static final String SORT_ORDER = "date DESC";
  public static final String SUBJECT = "sub";
  private static final String TYPE = "type";
  private static final String VALUE = "value";
  private static final SMSMMSProvider instance = new SMSMMSProvider();
  Context m_context = null;
  private long mostRecentTimestamp = 0L;

  static
  {
    SMS_URI = Uri.parse("content://sms/");
    MMS_URI = Uri.parse("content://mms/");
    MESSAGE_CONVERSATION_URI = Uri.parse("content://mms-sms/conversations");
    String[] arrayOfString1 = new String[1];
    arrayOfString1[0] = "*";
    SMS_PROJECTION = arrayOfString1;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = "*";
    MMS_PROJECTION = arrayOfString2;
  }

  private String getAddress(Cursor paramCursor, int paramInt)
  {
    String str = getString(paramCursor, "address");
    if (str == null)
    {
      str = getAddressNumber(paramInt);
      if (str != null);
    }
    return str;
  }

  private String getAddressNumber(int paramInt)
  {
    String str1 = new String("msg_id=" + paramInt);
    Uri localUri = Uri.parse("content://mms/" + paramInt + "/addr");
    Cursor localCursor = this.m_context.getContentResolver().query(localUri, null, str1, null, null);
    Object localObject = null;
    String str2;
    if (localCursor.moveToFirst())
    {
      str2 = getString(localCursor, "address");
      if (str2 == null)
        break label148;
    }
    while (true)
    {
      try
      {
        Long.parseLong(str2.replace("-", ""));
        localObject = str2;
        printColumns("getAddressNumber", localCursor);
        if (localCursor == null)
          continue;
        localCursor.close();
        return localObject;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        if (localObject == null)
          localObject = str2;
      }
      label148: if (localCursor.moveToNext())
        break;
    }
  }

  public static SMSMMSProvider getInstance()
  {
    return instance;
  }

  private int getInt(Cursor paramCursor, String paramString)
  {
    int i = -1;
    int j = paramCursor.getColumnIndex(paramString);
    if (j >= 0)
      i = paramCursor.getInt(j);
    return i;
  }

  private long getLong(Cursor paramCursor, String paramString)
  {
    long l = 0L;
    int i = paramCursor.getColumnIndex(paramString);
    if (i >= 0)
      l = paramCursor.getLong(i);
    return l;
  }

  private Cursor getMMSCursor(long paramLong, String paramString1, String paramString2)
  {
    long l;
    if (paramLong > 0L)
    {
      l = paramLong / 1000L;
      if (StringUtils.isNullOrWhiteSpace(paramString1))
        break label66;
    }
    label66: for (paramString1 = paramString1 + " AND date > " + l; ; paramString1 = "date > " + l)
      return this.m_context.getContentResolver().query(MMS_URI, MMS_PROJECTION, paramString1, null, paramString2);
  }

  private LinkedList<SMSMMSAlert> getNewAlertsOfType(Cursor paramCursor, String paramString)
  {
    LinkedList localLinkedList = new LinkedList();
    int i;
    if (paramCursor != null)
      i = 1;
    try
    {
      label27: int j;
      String str1;
      String str2;
      if ((i & paramCursor.moveToFirst()) != 0)
      {
        j = getInt(paramCursor, "_id");
        if ("MMS".equals(paramString));
        str1 = getAddress(paramCursor, j);
        str2 = ContactUtil.getContactDisplayNameByAddress(this.m_context, str1);
        if (str2 == null)
          str2 = "";
        if ((!"MMS".equals(paramString)) || (getInt(paramCursor, "msg_box") == 1))
          break label125;
      }
      while (true)
      {
        boolean bool = paramCursor.moveToNext();
        if (bool)
          break label27;
        return localLinkedList;
        i = 0;
        break;
        label125: String str3 = getString(paramCursor, "body");
        long l = getLong(paramCursor, "date");
        if ("MMS".equals(paramString))
          l *= 1000L;
        if (l > this.mostRecentTimestamp)
          this.mostRecentTimestamp = l;
        String str4 = null;
        if ("MMS".equals(paramString))
          str4 = ClientSuppliedValues.getMmsSubject(paramCursor);
        SMSMMSAlert localSMSMMSAlert = new SMSMMSAlert(j, l, str1, str2, str3, str4, paramString);
        if (localLinkedList.contains(localSMSMMSAlert))
          continue;
        localLinkedList.add(localSMSMMSAlert);
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        if (paramCursor == null)
          continue;
        paramCursor.close();
      }
    }
    finally
    {
      if (paramCursor != null)
        paramCursor.close();
    }
    throw localObject;
  }

  private Cursor getSMSCursor(long paramLong, String paramString1, String paramString2)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString1))
    {
      str = paramString1 + " AND type = 1";
      if (paramLong > 0L)
        if (StringUtils.isNullOrWhiteSpace(str))
          break label98;
    }
    label98: for (String str = str + " AND date > " + paramLong; ; str = "date > " + paramLong)
    {
      return this.m_context.getContentResolver().query(SMS_URI, SMS_PROJECTION, str, null, paramString2);
      str = "type = 1";
      break;
    }
  }

  private String getString(Cursor paramCursor, String paramString)
  {
    String str = null;
    int i = paramCursor.getColumnIndex(paramString);
    if (i >= 0)
      str = paramCursor.getString(i);
    return str;
  }

  private void printColumns(String paramString, Cursor paramCursor)
  {
  }

  public SMSMMSAlert findbyId(long paramLong, String paramString, LinkedList<SMSMMSAlert> paramLinkedList)
  {
    Object localObject1 = null;
    String str1 = "SMS";
    if (paramString.equalsIgnoreCase("MMS"))
      str1 = "MMS";
    while (paramLinkedList.size() > 0)
    {
      Iterator localIterator = paramLinkedList.iterator();
      while (localIterator.hasNext())
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localIterator.next();
        if ((localSMSMMSAlert.getId() != paramLong) || (localSMSMMSAlert.getType() != str1))
          continue;
        localObject1 = localSMSMMSAlert;
      }
      if (!paramString.equalsIgnoreCase("SMS"))
        continue;
      str1 = "SMS";
    }
    Object localObject2 = localObject1;
    Object localObject3 = null;
    while (true)
    {
      try
      {
        if (!"SMS".equalsIgnoreCase(str1))
          continue;
        localObject3 = this.m_context.getContentResolver().query(SMS_URI, null, "_id = " + paramLong, null, null);
        ((Cursor)localObject3).moveToFirst();
        if (((Cursor)localObject3).isAfterLast())
          break label375;
        int i = (int)paramLong;
        String str2 = getAddress((Cursor)localObject3, i);
        String str3 = getString((Cursor)localObject3, "body");
        long l = getLong((Cursor)localObject3, "date");
        String str4 = null;
        String str5 = ContactUtil.getContactDisplayNameByAddress(this.m_context, str2);
        if (!"MMS".equalsIgnoreCase(str1))
          continue;
        str4 = ClientSuppliedValues.getMmsSubject((Cursor)localObject3);
        localObject5 = new SMSMMSAlert(paramLong, l, str2, str5, str3, str4, str1);
        return localObject5;
        if (!"MMS".equalsIgnoreCase(str1))
          continue;
        Cursor localCursor = this.m_context.getContentResolver().query(MMS_URI, null, "_id = " + paramLong, null, null);
        localObject3 = localCursor;
        continue;
      }
      catch (Exception localException)
      {
        if (localObject3 != null)
        {
          ((Cursor)localObject3).close();
          localObject5 = localObject2;
          continue;
        }
      }
      finally
      {
        if (localObject3 == null)
          continue;
        ((Cursor)localObject3).close();
      }
      Object localObject5 = localObject2;
      continue;
      label375: localObject5 = localObject2;
    }
  }

  public LinkedList<SMSMMSAlert> getAlerts(long paramLong, int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    LinkedList localLinkedList = new LinkedList();
    if (paramBoolean);
    for (String str = paramString2 + " ASC"; ; str = paramString2 + " DESC")
    {
      localLinkedList.addAll(getNewAlertsOfType(getMMSCursor(paramLong, paramString1, str), "MMS"));
      localLinkedList.addAll(getNewAlertsOfType(getSMSCursor(paramLong, paramString1, str), "SMS"));
      Collections.sort(localLinkedList, new SMSMMSProvider.1(this, paramBoolean));
      if (paramInt <= 0)
        break;
      while (localLinkedList.size() > paramInt)
        localLinkedList.removeLast();
    }
    return localLinkedList;
  }

  public LinkedList<SMSMMSAlert> getAllNewAlerts()
  {
    return getAllNewAlerts(0);
  }

  public LinkedList<SMSMMSAlert> getAllNewAlerts(int paramInt)
  {
    return getAlerts(0L, paramInt, "seen = 0 AND read = 0", "date", false);
  }

  protected LinkedList<SMSMMSAlert> getNewSafereaderAlerts()
  {
    return getNewSafereaderAlerts(0);
  }

  public LinkedList<SMSMMSAlert> getNewSafereaderAlerts(int paramInt)
  {
    LinkedList localLinkedList = getAlerts(Settings.getLong("car_safereader_last_saferead_time", 0L), paramInt, "seen = 0", "date", false);
    if (!localLinkedList.isEmpty())
      Settings.setLong("car_safereader_last_saferead_time", this.mostRecentTimestamp);
    this.mostRecentTimestamp = 0L;
    return localLinkedList;
  }

  public LinkedList<SMSMMSAlert> getUnreadSMSAndMMSMessagesJayceeStyle()
  {
    long l = Settings.getLong("car_safereader_last_saferead_time", 0L) - 10000L;
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.addAll(getNewAlertsOfType(getMMSCursor(l, "seen = 0", "date DESC"), "MMS"));
    localLinkedList.addAll(getNewAlertsOfType(getSMSCursor(l, "seen = 0", "date DESC"), "SMS"));
    if (!localLinkedList.isEmpty())
      Settings.setLong("car_safereader_last_saferead_time", this.mostRecentTimestamp);
    this.mostRecentTimestamp = 0L;
    return localLinkedList;
  }

  public void markAsRead(Context paramContext, long paramLong, String paramString)
  {
    Uri localUri = SMS_URI;
    if (paramString.equals("MMS"))
      localUri = MMS_URI;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("read", Integer.valueOf(1));
    localContentValues.put("seen", Integer.valueOf(1));
    if (localContentResolver.update(localUri, localContentValues, "_id = " + paramLong, null) != 1);
  }

  public void registerSMSMMSContentObserver(ContentObserver paramContentObserver)
  {
    this.m_context.getContentResolver().registerContentObserver(MESSAGE_CONVERSATION_URI, true, paramContentObserver);
  }

  void setContext(Context paramContext)
  {
    this.m_context = paramContext;
  }

  public void unregisterContentObserver(ContentObserver paramContentObserver)
  {
    this.m_context.getContentResolver().unregisterContentObserver(paramContentObserver);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.messages.SMSMMSProvider
 * JD-Core Version:    0.6.0
 */