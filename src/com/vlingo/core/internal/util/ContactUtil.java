package com.vlingo.core.internal.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.PhoneLookup;

public class ContactUtil
{
  private static final int MAX_RECENTS = 15;
  private static final int MAX_STARRED = 30;

  // ERROR //
  public static String getContactDisplayNameByAddress(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: getstatic 23	android/provider/ContactsContract$PhoneLookup:CONTENT_FILTER_URI	Landroid/net/Uri;
    //   3: aload_1
    //   4: invokestatic 29	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   7: invokestatic 33	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   10: astore_2
    //   11: iconst_1
    //   12: anewarray 35	java/lang/String
    //   15: astore_3
    //   16: aload_3
    //   17: iconst_0
    //   18: ldc 37
    //   20: aastore
    //   21: aconst_null
    //   22: astore 4
    //   24: aload_0
    //   25: invokevirtual 43	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   28: aload_2
    //   29: aload_3
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: invokevirtual 49	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   36: astore 4
    //   38: aload 4
    //   40: ifnull +40 -> 80
    //   43: aload 4
    //   45: invokeinterface 55 1 0
    //   50: ifeq +30 -> 80
    //   53: aload 4
    //   55: iconst_0
    //   56: invokeinterface 59 2 0
    //   61: astore 10
    //   63: aload 10
    //   65: astore_1
    //   66: aload 4
    //   68: ifnull +10 -> 78
    //   71: aload 4
    //   73: invokeinterface 62 1 0
    //   78: aload_1
    //   79: areturn
    //   80: aload 4
    //   82: ifnull -4 -> 78
    //   85: aload 4
    //   87: invokeinterface 62 1 0
    //   92: goto -14 -> 78
    //   95: astore 9
    //   97: goto -19 -> 78
    //   100: astore 7
    //   102: aload 4
    //   104: ifnull -26 -> 78
    //   107: aload 4
    //   109: invokeinterface 62 1 0
    //   114: goto -36 -> 78
    //   117: astore 8
    //   119: goto -41 -> 78
    //   122: astore 5
    //   124: aload 4
    //   126: ifnull +10 -> 136
    //   129: aload 4
    //   131: invokeinterface 62 1 0
    //   136: aload 5
    //   138: athrow
    //   139: astore 11
    //   141: goto -63 -> 78
    //   144: astore 6
    //   146: goto -10 -> 136
    //
    // Exception table:
    //   from	to	target	type
    //   85	92	95	java/lang/Exception
    //   24	63	100	java/lang/Exception
    //   107	114	117	java/lang/Exception
    //   24	63	122	finally
    //   71	78	139	java/lang/Exception
    //   129	136	144	java/lang/Exception
  }

  public static String getContactFullName(long paramLong, Context paramContext)
  {
    Cursor localCursor = null;
    if (paramLong != 0L)
    {
      Uri localUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, paramLong);
      ContentResolver localContentResolver = paramContext.getContentResolver();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "display_name";
      localCursor = localContentResolver.query(localUri, arrayOfString, null, null, null);
    }
    if (localCursor != null);
    try
    {
      if (localCursor.moveToFirst())
      {
        str = localCursor.getString(localCursor.getColumnIndex("display_name"));
        if (str != null)
        {
          localCursor.close();
          return str;
        }
      }
      localCursor.close();
      String str = "";
    }
    finally
    {
      localCursor.close();
    }
  }

  public static String getContactFullNameFromPhoneNumber(String paramString, Context paramContext)
  {
    Cursor localCursor = null;
    String str = null;
    if ((paramString != null) && (paramString.length() > 0))
    {
      Uri localUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, paramString);
      ContentResolver localContentResolver = paramContext.getContentResolver();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "display_name";
      localCursor = localContentResolver.query(localUri, arrayOfString, null, null, null);
    }
    if (CursorUtil.isValid(localCursor))
    {
      if (localCursor.moveToFirst())
        str = localCursor.getString(localCursor.getColumnIndex("display_name"));
      localCursor.close();
    }
    if (str == null)
      str = "";
    return str;
  }

  // ERROR //
  public static String getLastOutgoingCall(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: getstatic 98	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   5: astore 7
    //   7: iconst_2
    //   8: anewarray 35	java/lang/String
    //   11: astore 8
    //   13: aload 8
    //   15: iconst_0
    //   16: ldc 100
    //   18: aastore
    //   19: aload 8
    //   21: iconst_1
    //   22: ldc 102
    //   24: aastore
    //   25: aload_0
    //   26: invokevirtual 43	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   29: aload 7
    //   31: aload 8
    //   33: ldc 104
    //   35: aconst_null
    //   36: ldc 106
    //   38: invokevirtual 49	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore_1
    //   42: aload_1
    //   43: ifnull +67 -> 110
    //   46: aload_1
    //   47: invokeinterface 55 1 0
    //   52: ifeq +58 -> 110
    //   55: aload_1
    //   56: ldc 102
    //   58: invokeinterface 79 2 0
    //   63: istore 10
    //   65: aload_1
    //   66: iload 10
    //   68: invokeinterface 59 2 0
    //   73: astore 11
    //   75: aload 11
    //   77: astore 6
    //   79: aload 6
    //   81: ifnull +16 -> 97
    //   84: aload_1
    //   85: ifnull +9 -> 94
    //   88: aload_1
    //   89: invokeinterface 62 1 0
    //   94: aload 6
    //   96: areturn
    //   97: aload_1
    //   98: invokeinterface 109 1 0
    //   103: istore 12
    //   105: iload 12
    //   107: ifne -42 -> 65
    //   110: aload_1
    //   111: ifnull +9 -> 120
    //   114: aload_1
    //   115: invokeinterface 62 1 0
    //   120: aconst_null
    //   121: astore 6
    //   123: goto -29 -> 94
    //   126: astore 4
    //   128: aload_1
    //   129: ifnull -9 -> 120
    //   132: aload_1
    //   133: invokeinterface 62 1 0
    //   138: goto -18 -> 120
    //   141: astore 5
    //   143: goto -23 -> 120
    //   146: astore_2
    //   147: aload_1
    //   148: ifnull +9 -> 157
    //   151: aload_1
    //   152: invokeinterface 62 1 0
    //   157: aload_2
    //   158: athrow
    //   159: astore 13
    //   161: goto -67 -> 94
    //   164: astore 9
    //   166: goto -46 -> 120
    //   169: astore_3
    //   170: goto -13 -> 157
    //
    // Exception table:
    //   from	to	target	type
    //   2	75	126	java/lang/Exception
    //   97	105	126	java/lang/Exception
    //   132	138	141	java/lang/Exception
    //   2	75	146	finally
    //   97	105	146	finally
    //   88	94	159	java/lang/Exception
    //   114	120	164	java/lang/Exception
    //   151	157	169	java/lang/Exception
  }

  public static String getLookupKeyFromPhoneNumber(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    if (paramString == null);
    while (true)
    {
      return localObject1;
      ContentResolver localContentResolver = paramContext.getContentResolver();
      Uri localUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString));
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "lookup";
      Cursor localCursor = localContentResolver.query(localUri, arrayOfString, null, null, null);
      if (localCursor == null)
        continue;
      try
      {
        if (localCursor.getCount() > 0)
        {
          localCursor.moveToFirst();
          String str = localCursor.getString(0);
          localCursor.close();
          localObject1 = str;
          continue;
        }
        localCursor.close();
      }
      finally
      {
        localCursor.close();
      }
    }
  }

  public static long getPersonIdFromPhoneNumber(Context paramContext, String paramString)
  {
    long l1;
    if (paramString == null)
      l1 = -1L;
    while (true)
    {
      return l1;
      ContentResolver localContentResolver = paramContext.getContentResolver();
      Uri localUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString));
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "_id";
      Cursor localCursor = localContentResolver.query(localUri, arrayOfString, null, null, null);
      if (localCursor != null);
      try
      {
        if (localCursor.getCount() > 0)
        {
          localCursor.moveToFirst();
          long l2 = Long.valueOf(localCursor.getLong(0)).longValue();
          l1 = l2;
          localCursor.close();
          continue;
        }
        localCursor.close();
        l1 = -1L;
      }
      finally
      {
        localCursor.close();
      }
    }
  }

  public static String getTypeStringEN(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default:
      str = "";
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 0:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 20:
    }
    while (true)
    {
      return str;
      str = "home";
      continue;
      str = "mobile";
      continue;
      str = "work";
      continue;
      str = "fax work";
      continue;
      str = "fax home";
      continue;
      str = "pager";
      continue;
      str = "other";
      continue;
      str = "custom";
      continue;
      str = "callback";
      continue;
      str = "car";
      continue;
      str = "company main";
      continue;
      str = "isdn";
      continue;
      str = "main";
      continue;
      str = "other fax";
      continue;
      str = "radio";
      continue;
      str = "telex";
      continue;
      str = "tty tdd";
      continue;
      str = "work mobile";
      continue;
      str = "work pager";
      continue;
      str = "assistant";
      continue;
      str = "mms";
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ContactUtil
 * JD-Core Version:    0.6.0
 */