package com.vlingo.core.internal.util;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SMSUtil
{
  private static final String ACTION_SMS_SENT = "com.vlingo.client.actions.ACTION_SMS_SENT";
  public static final String ADDRESS = "address";
  public static final String BODY = "body";
  public static final String DATE = "date";
  public static final String ERROR_CODE = "err_code";
  private static final String EXTRA_URI = "com.vlingo.client.extras.URI";
  private static final Uri INBOX = Uri.parse("content://sms/inbox");
  private static final Object LOCK;
  public static final int MESSAGE_TYPE_ALL = 0;
  public static final int MESSAGE_TYPE_DRAFT = 3;
  public static final int MESSAGE_TYPE_FAILED = 5;
  public static final int MESSAGE_TYPE_INBOX = 1;
  public static final int MESSAGE_TYPE_OUTBOX = 4;
  public static final int MESSAGE_TYPE_QUEUED = 6;
  public static final int MESSAGE_TYPE_SENT = 2;
  private static final Uri OUTBOX = Uri.parse("content://sms/outbox");
  public static final String PERSON_ID = "person";
  public static final String READ = "read";
  public static final String STATUS = "status";
  public static final int STATUS_COMPLETE = 0;
  public static final int STATUS_FAILED = 128;
  public static final int STATUS_NONE = -1;
  public static final int STATUS_PENDING = 64;
  public static final String SUBJECT = "subject";
  public static final String THREAD_ID = "thread_id";
  public static final String TYPE = "type";
  private static Map<Long, SMSSendCallbackWrapper> smsSendCallback;
  private static SMSResultReceiver smsSendReceiver;
  private static int smsSendReceiverRefCount = 0;

  static
  {
    smsSendCallback = new HashMap();
    LOCK = new Object();
  }

  public static Uri addMessageToOutbox(ContentResolver paramContentResolver, String paramString1, String paramString2, Long paramLong, boolean paramBoolean, long paramLong1)
  {
    return addMessageToUri(paramContentResolver, OUTBOX, paramString1, paramString2, null, paramLong, true, paramBoolean, paramLong1);
  }

  public static Uri addMessageToUri(ContentResolver paramContentResolver, Uri paramUri, String paramString1, String paramString2, String paramString3, Long paramLong, boolean paramBoolean1, boolean paramBoolean2, long paramLong1)
  {
    ContentValues localContentValues = new ContentValues(7);
    localContentValues.put("address", paramString1);
    if (paramLong != null)
      localContentValues.put("date", paramLong);
    if (paramBoolean1);
    for (Integer localInteger = Integer.valueOf(1); ; localInteger = Integer.valueOf(0))
    {
      localContentValues.put("read", localInteger);
      localContentValues.put("subject", paramString3);
      localContentValues.put("body", paramString2);
      if (paramBoolean2)
        localContentValues.put("status", Integer.valueOf(64));
      if (paramLong1 != -1L)
        localContentValues.put("thread_id", Long.valueOf(paramLong1));
      return paramContentResolver.insert(paramUri, localContentValues);
    }
  }

  public static ContactData findMatchingContactDataInInboxOutbox(Context paramContext, ContactData[] paramArrayOfContactData)
  {
    Object localObject;
    if ((paramArrayOfContactData == null) || (paramArrayOfContactData.length == 0))
      localObject = null;
    while (true)
    {
      return localObject;
      localObject = null;
      ContactData localContactData1 = null;
      long l1 = -1L;
      long l2 = -1L;
      HashMap localHashMap1 = new HashMap();
      HashMap localHashMap2 = new HashMap();
      int i = paramArrayOfContactData.length;
      for (int j = 0; j < i; j++)
      {
        ContactData localContactData2 = paramArrayOfContactData[j];
        localHashMap1.put(PhoneNumberUtils.stripSeparators(localContactData2.address), localContactData2);
        localHashMap2.put(Long.valueOf(localContactData2.contact.primaryContactID), localContactData2);
      }
      Cursor localCursor1 = paramContext.getContentResolver().query(INBOX, null, null, null, "date DESC");
      int m;
      label136: long l5;
      long l6;
      label252: Cursor localCursor2;
      int k;
      label289: long l3;
      long l4;
      if (localCursor1.moveToFirst())
      {
        m = 0;
        String str3 = localCursor1.getString(localCursor1.getColumnIndexOrThrow("address")).toString();
        localCursor1.getString(localCursor1.getColumnIndexOrThrow("body")).toString();
        l5 = localCursor1.getLong(localCursor1.getColumnIndexOrThrow("person"));
        l6 = localCursor1.getLong(localCursor1.getColumnIndexOrThrow("date"));
        ContactUtil.getContactFullName(l5, paramContext);
        String str4 = PhoneNumberUtils.stripSeparators(str3);
        if (localHashMap1.containsKey(str4))
        {
          localObject = (ContactData)localHashMap1.get(str4);
          l1 = l6;
        }
      }
      else
      {
        localCursor1.close();
        localCursor2 = paramContext.getContentResolver().query(OUTBOX, null, null, null, "date DESC");
        if (localCursor2.moveToFirst())
        {
          k = 0;
          String str1 = localCursor2.getString(localCursor2.getColumnIndexOrThrow("address")).toString();
          localCursor2.getString(localCursor2.getColumnIndexOrThrow("body")).toString();
          l3 = localCursor2.getLong(localCursor2.getColumnIndexOrThrow("person"));
          l4 = localCursor2.getLong(localCursor2.getColumnIndexOrThrow("date"));
          ContactUtil.getContactFullName(l3, paramContext);
          String str2 = PhoneNumberUtils.stripSeparators(str1);
          if (!localHashMap1.containsKey(str2))
            break label490;
          localContactData1 = (ContactData)localHashMap1.get(str2);
          l2 = l4;
        }
      }
      while (true)
      {
        localCursor2.close();
        if ((localObject == null) || (localContactData1 == null))
          break label547;
        if (l1 > l2)
          break;
        localObject = localContactData1;
        break;
        if (localHashMap2.containsKey(Long.valueOf(l5)))
        {
          localObject = (ContactData)localHashMap2.get(Long.valueOf(l5));
          l1 = l6;
          break label252;
        }
        m++;
        if (!localCursor1.moveToNext())
          break label252;
        if (m < 10)
          break label136;
        break label252;
        label490: if (localHashMap2.containsKey(Long.valueOf(l3)))
        {
          localContactData1 = (ContactData)localHashMap2.get(Long.valueOf(l3));
          l2 = l4;
          continue;
        }
        k++;
        if (!localCursor2.moveToNext())
          continue;
        if (k < 10)
          break label289;
      }
      label547: if (localObject != null)
        continue;
      if (localContactData1 != null)
      {
        localObject = localContactData1;
        continue;
      }
      localObject = null;
    }
  }

  public static ContactMatch findMatchingDisplayItemInInboxOutbox(Context paramContext, ContactMatch[] paramArrayOfContactMatch)
  {
    ContactMatch localContactMatch = null;
    if ((paramArrayOfContactMatch == null) || (paramArrayOfContactMatch.length == 0));
    while (true)
    {
      return localContactMatch;
      ArrayList localArrayList = new ArrayList();
      int i = paramArrayOfContactMatch.length;
      for (int j = 0; j < i; j++)
        localArrayList.addAll(paramArrayOfContactMatch[j].getAllData());
      ContactData localContactData = findMatchingContactDataInInboxOutbox(paramContext, (ContactData[])localArrayList.toArray(new ContactData[localArrayList.size()]));
      if (localContactData == null)
        continue;
      localContactMatch = localContactData.contact;
    }
  }

  public static ArrayList<TextMessage> getLastNUnreadMessages(Context paramContext, int paramInt)
  {
    ArrayList localArrayList = new ArrayList(paramInt);
    try
    {
      Cursor localCursor = paramContext.getContentResolver().query(INBOX, null, "read = 0", null, "date DESC");
      if ((CursorUtil.isValid(localCursor)) && (localCursor.moveToFirst()))
      {
        int i = 0;
        do
        {
          long l1 = localCursor.getLong(0);
          String str1 = localCursor.getString(localCursor.getColumnIndexOrThrow("address")).toString();
          String str2 = localCursor.getString(localCursor.getColumnIndexOrThrow("body")).toString();
          long l2 = localCursor.getLong(localCursor.getColumnIndexOrThrow("date"));
          String str3 = ContactUtil.getContactFullNameFromPhoneNumber(str1, paramContext);
          localArrayList.add(new TextMessage(PhoneNumberUtils.stripSeparators(str1), str3, str2, null, l1, l2));
          i++;
        }
        while ((localCursor.moveToNext()) && (i < paramInt));
      }
      localCursor.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static TextMessage getMostRecentMessageFromInbox(Context paramContext)
  {
    ArrayList localArrayList = getRecentMessagesFromInbox(paramContext, false, 1);
    if (localArrayList.size() > 0);
    for (TextMessage localTextMessage = (TextMessage)localArrayList.get(0); ; localTextMessage = null)
      return localTextMessage;
  }

  public static ArrayList<TextMessage> getRecentMessagesFromInbox(Context paramContext, boolean paramBoolean)
  {
    return getRecentMessagesFromInbox(paramContext, paramBoolean, 20);
  }

  // ERROR //
  public static ArrayList<TextMessage> getRecentMessagesFromInbox(Context paramContext, boolean paramBoolean, int paramInt)
  {
    // Byte code:
    //   0: new 246	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 247	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: new 305	java/util/HashSet
    //   11: dup
    //   12: invokespecial 306	java/util/HashSet:<init>	()V
    //   15: astore 4
    //   17: aconst_null
    //   18: astore 5
    //   20: iconst_0
    //   21: istore 6
    //   23: aload_0
    //   24: invokevirtual 192	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   27: getstatic 98	com/vlingo/core/internal/util/SMSUtil:INBOX	Landroid/net/Uri;
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: ldc 194
    //   35: invokevirtual 198	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   38: astore 5
    //   40: aload 5
    //   42: invokeinterface 204 1 0
    //   47: ifeq +171 -> 218
    //   50: aload 5
    //   52: aload 5
    //   54: ldc 23
    //   56: invokeinterface 208 2 0
    //   61: invokeinterface 212 2 0
    //   66: invokevirtual 218	java/lang/String:toString	()Ljava/lang/String;
    //   69: astore 12
    //   71: aload 12
    //   73: ifnull +113 -> 186
    //   76: iload_1
    //   77: ifeq +13 -> 90
    //   80: aload 4
    //   82: aload 12
    //   84: invokevirtual 309	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   87: ifne +99 -> 186
    //   90: aload_0
    //   91: aload 12
    //   93: invokestatic 313	com/vlingo/core/internal/util/ContactUtil:getPersonIdFromPhoneNumber	(Landroid/content/Context;Ljava/lang/String;)J
    //   96: lstore 14
    //   98: aload_0
    //   99: aload 12
    //   101: invokestatic 317	com/vlingo/core/internal/util/ContactUtil:getLookupKeyFromPhoneNumber	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   104: astore 16
    //   106: lload 14
    //   108: ldc2_w 149
    //   111: lcmp
    //   112: ifeq +120 -> 232
    //   115: lload 14
    //   117: aload_0
    //   118: invokestatic 228	com/vlingo/core/internal/util/ContactUtil:getContactFullName	(JLandroid/content/Context;)Ljava/lang/String;
    //   121: astore 18
    //   123: aload_3
    //   124: new 15	com/vlingo/core/internal/util/SMSUtil$TextMessage
    //   127: dup
    //   128: aload 12
    //   130: aload 18
    //   132: aload 5
    //   134: aload 5
    //   136: ldc 26
    //   138: invokeinterface 208 2 0
    //   143: invokeinterface 212 2 0
    //   148: invokevirtual 218	java/lang/String:toString	()Ljava/lang/String;
    //   151: aload 16
    //   153: lload 14
    //   155: aload 5
    //   157: aload 5
    //   159: ldc 29
    //   161: invokeinterface 208 2 0
    //   166: invokeinterface 222 2 0
    //   171: invokespecial 287	com/vlingo/core/internal/util/SMSUtil$TextMessage:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V
    //   174: invokevirtual 290	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   177: pop
    //   178: aload 4
    //   180: aload 12
    //   182: invokevirtual 318	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   185: pop
    //   186: iinc 6 1
    //   189: aload 5
    //   191: invokeinterface 242 1 0
    //   196: ifeq +22 -> 218
    //   199: iload 6
    //   201: bipush 50
    //   203: if_icmpge +15 -> 218
    //   206: aload_3
    //   207: invokevirtual 259	java/util/ArrayList:size	()I
    //   210: istore 13
    //   212: iload 13
    //   214: iload_2
    //   215: if_icmplt -165 -> 50
    //   218: aload 5
    //   220: ifnull +10 -> 230
    //   223: aload 5
    //   225: invokeinterface 239 1 0
    //   230: aload_3
    //   231: areturn
    //   232: aload 12
    //   234: invokestatic 321	android/telephony/PhoneNumberUtils:formatNumber	(Ljava/lang/String;)Ljava/lang/String;
    //   237: astore 17
    //   239: aload 17
    //   241: astore 18
    //   243: goto -120 -> 123
    //   246: astore 9
    //   248: aload 5
    //   250: ifnull -20 -> 230
    //   253: aload 5
    //   255: invokeinterface 239 1 0
    //   260: goto -30 -> 230
    //   263: astore 10
    //   265: goto -35 -> 230
    //   268: astore 7
    //   270: aload 5
    //   272: ifnull +10 -> 282
    //   275: aload 5
    //   277: invokeinterface 239 1 0
    //   282: aload 7
    //   284: athrow
    //   285: astore 11
    //   287: goto -57 -> 230
    //   290: astore 8
    //   292: goto -10 -> 282
    //
    // Exception table:
    //   from	to	target	type
    //   23	212	246	java/lang/Exception
    //   232	239	246	java/lang/Exception
    //   253	260	263	java/lang/Exception
    //   23	212	268	finally
    //   232	239	268	finally
    //   223	230	285	java/lang/Exception
    //   275	282	290	java/lang/Exception
  }

  public static Intent getSMSIntent(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + paramString1));
    localIntent.putExtra("address", paramString1);
    localIntent.putExtra("sms_body", paramString2);
    localIntent.setType("vnd.android-dir/mms-sms");
    return localIntent;
  }

  public static Intent getSMSIntent(String[] paramArrayOfString, String paramString)
  {
    String str1 = "";
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
    {
      String str3 = paramArrayOfString[j];
      str1 = str1 + str3 + ", ";
    }
    String str2 = str1.substring(0, -1 + str1.length());
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:"));
    localIntent.putExtra("address", str2);
    localIntent.putExtra("sms_body", paramString);
    localIntent.setType("vnd.android-dir/mms-sms");
    return localIntent;
  }

  public static Intent getSMSReplyIntent(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = null;
    Cursor localCursor2;
    if ((paramString1 != null) && (paramString1.trim().length() > 0))
    {
      String[] arrayOfString = paramString1.trim().split(" ");
      localCursor2 = paramContext.getContentResolver().query(INBOX, null, null, null, "date DESC");
      if (localCursor2.moveToFirst())
      {
        String str1 = localCursor2.getString(localCursor2.getColumnIndexOrThrow("address")).toString();
        localCursor2.getString(localCursor2.getColumnIndexOrThrow("body")).toString();
        String str2 = ContactUtil.getContactFullName(localCursor2.getLong(localCursor2.getColumnIndexOrThrow("person")), paramContext);
        if ((str2 == null) || (str2.trim().length() <= 0) || (!isNameMatch(str2.trim().split(" "), arrayOfString)))
          break label268;
        localObject = str1;
      }
    }
    while (true)
    {
      localCursor2.close();
      if (localObject == null)
      {
        Cursor localCursor1 = paramContext.getContentResolver().query(INBOX, null, null, null, "date DESC");
        if (localCursor1.moveToFirst())
        {
          localObject = localCursor1.getString(localCursor1.getColumnIndexOrThrow("address")).toString();
          localCursor1.getString(localCursor1.getColumnIndexOrThrow("body")).toString();
          ContactUtil.getContactFullName(localCursor1.getLong(localCursor1.getColumnIndexOrThrow("person")), paramContext);
        }
        localCursor1.close();
      }
      return getSMSIntent((String)localObject, paramString2);
      label268: if (localCursor2.moveToNext())
        break;
    }
  }

  private static boolean isNameMatch(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    int i = 0;
    int k;
    if (i < paramArrayOfString1.length)
    {
      k = 0;
      label11: if (k < paramArrayOfString2.length)
        if (!paramArrayOfString1[i].equalsIgnoreCase(paramArrayOfString2[k]));
    }
    for (int j = 1; ; j = 0)
    {
      return j;
      k++;
      break label11;
      i++;
      break;
    }
  }

  public static boolean moveMessageToFolder(Context paramContext, Uri paramUri, int paramInt)
  {
    int i = 1;
    int j = 0;
    if (paramUri == null);
    while (true)
    {
      return j;
      int k = 0;
      int m = 0;
      label59: ContentValues localContentValues;
      label96: int n;
      switch (paramInt)
      {
      default:
        break;
      case 1:
      case 3:
        localContentValues = new ContentValues(2);
        localContentValues.put("type", Integer.valueOf(paramInt));
        if (k != 0)
        {
          localContentValues.put("read", Integer.valueOf(0));
          n = 0;
        }
      case 2:
      case 4:
      case 5:
      case 6:
        try
        {
          int i1 = paramContext.getContentResolver().update(paramUri, localContentValues, null, null);
          n = i1;
          label117: if (i == n);
          while (true)
          {
            j = i;
            break;
            m = 1;
            break label59;
            k = 1;
            break label59;
            if (m == 0)
              break label96;
            localContentValues.put("read", Integer.valueOf(i));
            break label96;
            i = 0;
          }
        }
        catch (Exception localException)
        {
          break label117;
        }
      }
    }
  }

  private static void releaseSmsSendReceiver(Context paramContext)
  {
    synchronized (LOCK)
    {
      smsSendReceiverRefCount = -1 + smsSendReceiverRefCount;
      if (smsSendReceiverRefCount == 0)
        paramContext.unregisterReceiver(smsSendReceiver);
      return;
    }
  }

  public static void sendSMS(Context paramContext, String paramString1, String paramString2, SMSSendCallback paramSMSSendCallback)
  {
    long l = System.currentTimeMillis();
    SMSSendCallbackWrapper localSMSSendCallbackWrapper = new SMSSendCallbackWrapper(paramSMSSendCallback);
    smsSendCallback.put(Long.valueOf(l), localSMSSendCallbackWrapper);
    Uri localUri = null;
    try
    {
      IntentFilter localIntentFilter = new IntentFilter("com.vlingo.client.actions.ACTION_SMS_SENT");
      setupSmsSendReceiver(paramContext, localIntentFilter);
      String str = PhoneNumberUtils.stripSeparators(paramString1);
      localUri = addMessageToOutbox(paramContext.getContentResolver(), str, paramString2, Long.valueOf(System.currentTimeMillis()), false, 0L);
      Intent localIntent = new Intent("com.vlingo.client.actions.ACTION_SMS_SENT");
      localIntent.putExtra("id", l);
      localIntent.putExtra("com.vlingo.client.extras.URI", localUri);
      PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, localIntent, 268435456);
      SmsManager localSmsManager = SmsManager.getDefault();
      ArrayList localArrayList1 = localSmsManager.divideMessage(paramString2);
      if (localArrayList1.size() > 1)
      {
        ArrayList localArrayList2 = new ArrayList();
        for (int i = 0; ; i++)
        {
          int j = localArrayList1.size();
          if (i >= j)
            break;
          localArrayList2.add(localPendingIntent);
        }
        localSMSSendCallbackWrapper.setParts(localArrayList1.size());
        localSmsManager.sendMultipartTextMessage(str, null, localArrayList1, localArrayList2, null);
      }
      else
      {
        localSmsManager.sendTextMessage(str, null, paramString2, localPendingIntent, null);
      }
    }
    catch (Exception localException)
    {
      smsSendCallback.remove(Long.valueOf(l));
      paramSMSSendCallback.onSMSSent(false, 1);
      moveMessageToFolder(paramContext, localUri, 5);
      releaseSmsSendReceiver(paramContext);
    }
  }

  private static void setupSmsSendReceiver(Context paramContext, IntentFilter paramIntentFilter)
  {
    synchronized (LOCK)
    {
      if ((smsSendReceiver == null) || (smsSendReceiverRefCount == 0))
      {
        smsSendReceiver = new SMSResultReceiver();
        paramContext.registerReceiver(smsSendReceiver, paramIntentFilter);
        smsSendReceiverRefCount = 1;
        return;
      }
      smsSendReceiverRefCount = 1 + smsSendReceiverRefCount;
    }
  }

  public static class SMSResultReceiver extends BroadcastReceiver
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      long l;
      SMSUtil.SMSSendCallbackWrapper localSMSSendCallbackWrapper;
      int i;
      boolean bool;
      if (paramIntent.getAction().equals("com.vlingo.client.actions.ACTION_SMS_SENT"))
      {
        Uri localUri = paramIntent.getData();
        l = paramIntent.getExtras().getLong("id");
        localSMSSendCallbackWrapper = (SMSUtil.SMSSendCallbackWrapper)SMSUtil.smsSendCallback.get(Long.valueOf(l));
        i = getResultCode();
        bool = false;
        if (i == -1)
          break label119;
        if (paramIntent.hasExtra("com.vlingo.client.extras.URI"))
          localUri = (Uri)paramIntent.getParcelableExtra("com.vlingo.client.extras.URI");
        bool = true;
        SMSUtil.moveMessageToFolder(paramContext, localUri, 5);
      }
      label184: 
      while (true)
      {
        if ((localSMSSendCallbackWrapper != null) && (localSMSSendCallbackWrapper.getParts() > 1))
          localSMSSendCallbackWrapper.setParts(-1 + localSMSSendCallbackWrapper.getParts());
        while (true)
        {
          return;
          label119: if (!paramIntent.hasExtra("com.vlingo.client.extras.URI"))
            break label184;
          SMSUtil.moveMessageToFolder(paramContext, (Uri)paramIntent.getParcelableExtra("com.vlingo.client.extras.URI"), 2);
          break;
          SMSUtil.smsSendCallback.remove(Long.valueOf(l));
          if (localSMSSendCallbackWrapper != null)
            localSMSSendCallbackWrapper.getCallback().onSMSSent(bool, i);
          SMSUtil.access$100(paramContext);
        }
      }
    }
  }

  public static abstract interface SMSSendCallback
  {
    public abstract void onSMSSent(boolean paramBoolean, int paramInt);
  }

  public static class SMSSendCallbackWrapper
  {
    private SMSUtil.SMSSendCallback callback;
    private int parts = 1;

    public SMSSendCallbackWrapper(SMSUtil.SMSSendCallback paramSMSSendCallback)
    {
      setCallback(paramSMSSendCallback);
      setParts(this.parts);
    }

    public SMSUtil.SMSSendCallback getCallback()
    {
      return this.callback;
    }

    public int getParts()
    {
      return this.parts;
    }

    public void setCallback(SMSUtil.SMSSendCallback paramSMSSendCallback)
    {
      this.callback = paramSMSSendCallback;
    }

    public void setParts(int paramInt)
    {
      this.parts = paramInt;
    }
  }

  public static class TextMessage
  {
    public final String address;
    public final String body;
    public final long date;
    public final long id;
    public final String lookupKey;
    public final String name;

    public TextMessage(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong1, long paramLong2)
    {
      this.address = paramString1;
      this.name = paramString2;
      this.body = paramString3;
      this.lookupKey = paramString4;
      this.id = paramLong1;
      this.date = paramLong2;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.SMSUtil
 * JD-Core Version:    0.6.0
 */