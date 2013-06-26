package com.vlingo.core.internal.contacts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ContactDBUtil
{
  private static final String EVENT_LUNAR_CONTENT_ITEM_TYPE = "vnd.pantech.cursor.item/lunar_event";
  private static final Pattern japaneseHonorifics = Pattern.compile("(\\w+)(さん|くん|ちゃん|様)$");

  private static String appendMatchToWhereClause(List<String> paramList, String paramString1, String paramString2)
  {
    if ((paramList == null) || (paramList.size() == 0));
    StringBuilder localStringBuilder;
    for (String str1 = null; ; str1 = localStringBuilder.toString())
    {
      return str1;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("((");
      for (int i = 0; i < paramList.size(); i++)
      {
        if (StringUtils.isNullOrWhiteSpace((String)paramList.get(i)))
          continue;
        String str2 = ((String)paramList.get(i)).replace("'", "''");
        if (i > 0)
          localStringBuilder.append(" OR ");
        localStringBuilder.append(paramString1).append(" LIKE '").append(str2).append("%'").append(" OR ").append(paramString1).append(" LIKE '% ").append(str2).append("%'");
      }
      localStringBuilder.append(") AND (").append("mimetype").append(" = '").append(paramString2).append("'))");
    }
  }

  private static String appendMatchToWhereClauseOnly(List<String> paramList, String paramString)
  {
    if ((paramList == null) || (paramList.size() == 0));
    StringBuilder localStringBuilder;
    for (String str1 = null; ; str1 = localStringBuilder.toString())
    {
      return str1;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("(");
      for (int i = 0; i < paramList.size(); i++)
      {
        if (StringUtils.isNullOrWhiteSpace((String)paramList.get(i)))
          continue;
        String str2 = ((String)paramList.get(i)).replace("'", "''");
        if (i > 0)
          localStringBuilder.append(" OR ");
        localStringBuilder.append(paramString).append(" LIKE '").append(str2).append("%'").append(" OR ").append(paramString).append(" LIKE '% ").append(str2).append("%'");
      }
      localStringBuilder.append(")");
    }
  }

  private static String appendMatchToWhereClause_MimeType(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("mimetype").append(" = '").append(paramString).append("'");
    return localStringBuilder.toString();
  }

  private static List<String> augmentWords(String[] paramArrayOfString)
  {
    LinkedList localLinkedList = new LinkedList();
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
    {
      String str1 = paramArrayOfString[j];
      Pattern localPattern = Pattern.compile("(?i).*" + str1 + ".*");
      Iterator localIterator = Settings.getContactNameList().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        if (!localPattern.matcher(str2).matches())
          continue;
        localLinkedList.add(str2);
      }
    }
    return localLinkedList;
  }

  public static int convertEmailType(int paramInt)
  {
    if (paramInt == 3)
      paramInt = 7;
    if (paramInt == 2)
      paramInt = 3;
    return paramInt;
  }

  public static void findMatchesByName(Context paramContext, String paramString, EnumSet<ContactLookupType> paramEnumSet, HashMap<Long, ContactMatch> paramHashMap, List<Long> paramList)
  {
    boolean bool = false;
    String[] arrayOfString1;
    String[] arrayOfString3;
    List localList;
    StringBuilder localStringBuilder;
    label443: label458: String[] arrayOfString2;
    int i;
    if ((paramString != null) && (paramString.trim().length() > 0))
    {
      bool = StringUtils.nameIsKorean(paramString);
      if (bool)
      {
        String[] arrayOfString5 = getKoreanDisplayNameWhereClause(paramString, "data1");
        arrayOfString1 = new String[arrayOfString5.length];
        int i1 = arrayOfString5.length;
        int i2 = 0;
        int i4;
        for (int i3 = 0; i2 < i1; i3 = i4)
        {
          String str3 = arrayOfString5[i2];
          i4 = i3 + 1;
          arrayOfString1[i3] = ("(" + str3 + ") AND (" + "mimetype='vnd.android.cursor.item/name' OR mimetype='vnd.android.cursor.item/nickname' OR mimetype='vnd.android.cursor.item/organization'" + ")");
          i2++;
        }
      }
      String str1 = "";
      if (StringUtils.isChineseString(paramString))
        for (int n = 0; n < paramString.length(); n++)
          str1 = str1 + paramString.substring(n, n + 1) + " ";
      if (StringUtils.nameIsJapanese(paramString))
      {
        str1 = paramString;
        for (String str2 : paramString.split(" "))
        {
          Matcher localMatcher = japaneseHonorifics.matcher(str2);
          if (!localMatcher.matches())
            continue;
          str1 = str1 + " " + localMatcher.group(0);
        }
      }
      str1 = paramString;
      arrayOfString3 = str1.split(" ");
      if (StringUtils.stringIsNonAsciiWithCase(str1))
      {
        localList = augmentWords(arrayOfString3);
        localStringBuilder = new StringBuilder(1024);
        localStringBuilder.append("(").append(appendMatchToWhereClause_MimeType("vnd.android.cursor.item/name")).append(" AND (").append(appendMatchToWhereClauseOnly(localList, "data2")).append(" OR ").append(appendMatchToWhereClauseOnly(localList, "data5")).append(" OR ").append(appendMatchToWhereClauseOnly(localList, "data3")).append(")) OR ((");
        if (!"data1".equals("data1"))
          break label507;
        localStringBuilder.append(appendMatchToWhereClause_MimeType("vnd.android.cursor.item/nickname")).append(" OR ").append(appendMatchToWhereClause_MimeType("vnd.android.cursor.item/organization")).append(") AND ").append(appendMatchToWhereClauseOnly(localList, "data1")).append(")");
        arrayOfString1 = new String[1];
        arrayOfString1[0] = localStringBuilder.toString();
        arrayOfString2 = arrayOfString1;
        i = arrayOfString2.length;
      }
    }
    for (int j = 0; ; j++)
      if ((j >= i) || (findMatchesByNamePerPass(paramContext, paramString, arrayOfString2[j], bool, paramEnumSet, paramHashMap, paramList)))
      {
        return;
        localList = Arrays.asList(arrayOfString3);
        break;
        label507: localStringBuilder.append(appendMatchToWhereClause_MimeType("vnd.android.cursor.item/nickname")).append(" AND ").append(appendMatchToWhereClauseOnly(localList, "data1")).append(") OR (").append(appendMatchToWhereClause_MimeType("vnd.android.cursor.item/organization")).append(" AND ").append(appendMatchToWhereClauseOnly(localList, "data1")).append("))");
        break label443;
        arrayOfString1 = new String[1];
        arrayOfString1[0] = "(mimetype='vnd.android.cursor.item/name' OR mimetype='vnd.android.cursor.item/nickname' OR mimetype='vnd.android.cursor.item/organization')";
        break label458;
      }
  }

  public static boolean findMatchesByNamePerPass(Context paramContext, String paramString1, String paramString2, boolean paramBoolean, EnumSet<ContactLookupType> paramEnumSet, HashMap<Long, ContactMatch> paramHashMap, List<Long> paramList)
  {
    String[] arrayOfString = new String[7];
    arrayOfString[0] = "contact_id";
    arrayOfString[1] = "raw_contact_id";
    arrayOfString[2] = "lookup";
    arrayOfString[3] = "display_name";
    arrayOfString[4] = "starred";
    arrayOfString[5] = "data1";
    arrayOfString[6] = "times_contacted";
    Uri localUri = ContactsContract.Data.CONTENT_URI;
    int i = 0;
    Cursor localCursor = null;
    while (true)
    {
      try
      {
        localCursor = paramContext.getContentResolver().query(localUri, arrayOfString, paramString2, null, null);
        if ((localCursor == null) || (!localCursor.moveToFirst()))
          continue;
        int j = localCursor.getColumnIndex("raw_contact_id");
        int k = localCursor.getColumnIndex("contact_id");
        m = localCursor.getColumnIndex("lookup");
        int n = localCursor.getColumnIndex("display_name");
        i1 = localCursor.getColumnIndex("starred");
        int i2 = localCursor.getColumnIndex("data1");
        int i3 = localCursor.getColumnIndex("times_contacted");
        l = localCursor.getLong(k);
        str1 = localCursor.getString(i2);
        str2 = localCursor.getString(n);
        i4 = localCursor.getInt(i3);
        ContactMatch localContactMatch1 = (ContactMatch)paramHashMap.get(Long.valueOf(l));
        if ((!paramBoolean) || (StringUtils.matchesKoreanNamePattern(paramString1, str2)))
          continue;
        boolean bool2 = localCursor.moveToNext();
        if (!bool2)
        {
          return i;
          if (localContactMatch1 == null)
            continue;
          localContactMatch1.addExtraName(str1);
          paramList.add(Long.valueOf(localCursor.getLong(j)));
          i = 1;
          continue;
          String str3 = localCursor.getString(m);
          if (localCursor.getInt(i1) == 1)
          {
            bool1 = true;
            ContactMatch localContactMatch2 = new ContactMatch(str2, l, str3, bool1);
            paramHashMap.put(Long.valueOf(l), localContactMatch2);
            localContactMatch2.addExtraName(str1);
            localContactMatch2.setTimesContacted(i4);
            continue;
          }
        }
      }
      finally
      {
        int m;
        int i1;
        long l;
        String str1;
        String str2;
        int i4;
        if (localCursor == null)
          continue;
        localCursor.close();
      }
      boolean bool1 = false;
    }
  }

  private static Cursor getContactCursor(Context paramContext)
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "data2";
    arrayOfString[2] = "data3";
    return paramContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI, arrayOfString, "mimetype='vnd.android.cursor.item/name' AND in_visible_group=1", null, null);
  }

  public static int getContactDetails(Context paramContext, EnumSet<ContactLookupType> paramEnumSet, HashMap<Long, ContactMatch> paramHashMap, List<Long> paramList)
  {
    int i = 0;
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "contact_id";
    arrayOfString[1] = "data1";
    arrayOfString[2] = "data2";
    arrayOfString[3] = "data3";
    arrayOfString[4] = "mimetype";
    arrayOfString[5] = "is_super_primary";
    String str1 = getWhere(paramEnumSet);
    int j = 1;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    Iterator localIterator = paramList.iterator();
    if (localIterator.hasNext())
    {
      Long localLong = (Long)localIterator.next();
      Uri localUri = Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, localLong.longValue()), "entity");
      Cursor localCursor = null;
      while (true)
      {
        ContactMatch localContactMatch;
        String str2;
        int i4;
        String str4;
        int i5;
        try
        {
          localCursor = paramContext.getContentResolver().query(localUri, arrayOfString, str1, null, null);
          if ((localCursor == null) || (!localCursor.moveToFirst()))
            continue;
          if (j == 0)
            continue;
          k = localCursor.getColumnIndex("contact_id");
          m = localCursor.getColumnIndex("data1");
          n = localCursor.getColumnIndex("data2");
          i1 = localCursor.getColumnIndex("data3");
          i2 = localCursor.getColumnIndex("mimetype");
          i3 = localCursor.getColumnIndex("is_super_primary");
          j = 0;
          localContactMatch = (ContactMatch)paramHashMap.get(Long.valueOf(localCursor.getLong(k)));
          str2 = localCursor.getString(m);
          i4 = localCursor.getInt(n);
          String str3 = localCursor.getString(i1);
          str4 = localCursor.getString(i2);
          i5 = localCursor.getInt(i3);
          if (!str4.equals("vnd.android.cursor.item/phone_v2"))
            continue;
          if ((StringUtils.isNullOrWhiteSpace(str3)) || (i4 != 0))
            continue;
          ContactData localContactData4 = new ContactData(localContactMatch, ContactData.Kind.Phone, str2, str3, i5);
          localContactMatch.addPhone(localContactData4);
          i++;
          boolean bool = localCursor.moveToNext();
          if (bool)
            continue;
          if (localCursor == null)
            break;
          localCursor.close();
          break;
          ContactData.Kind localKind2 = ContactData.Kind.Phone;
          localContactData4 = new ContactData(localContactMatch, localKind2, str2, i4, i5);
          continue;
          if (!str4.equals("vnd.android.cursor.item/email_v2"))
            break label524;
          if ((!StringUtils.isNullOrWhiteSpace(str3)) && (i4 == 0))
          {
            localContactData3 = new ContactData(localContactMatch, ContactData.Kind.Email, str2, str3, i5);
            localContactMatch.addEmail(localContactData3);
            continue;
          }
        }
        finally
        {
          if (localCursor == null)
            continue;
          localCursor.close();
        }
        ContactData.Kind localKind1 = ContactData.Kind.Email;
        int i6 = convertEmailType(i4);
        ContactData localContactData3 = new ContactData(localContactMatch, localKind1, str2, i6, i5);
        continue;
        label524: if (str4.equals("vnd.android.cursor.item/postal-address_v2"))
        {
          ContactData localContactData1 = new ContactData(localContactMatch, ContactData.Kind.Address, str2, i4, i5);
          localContactMatch.addAddress(localContactData1);
          continue;
        }
        if ((!str4.equals("vnd.android.cursor.item/contact_event")) && (!str4.equals("vnd.pantech.cursor.item/lunar_event")))
          continue;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
          Date localDate = localSimpleDateFormat.parse(str2);
          String str6 = android.text.format.DateFormat.getDateFormat(paramContext).format(localDate);
          str5 = str6;
          ContactData localContactData2 = new ContactData(localContactMatch, ContactData.Kind.Birthday, str5, i4, i5);
          localContactMatch.addBirthday(localContactData2);
        }
        catch (ParseException localParseException)
        {
          while (true)
            String str5 = str2;
        }
      }
    }
    return i;
  }

  public static List<ContactMatch> getContactMatches(Context paramContext, String paramString, EnumSet<ContactLookupType> paramEnumSet, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList1 = new ArrayList();
    findMatchesByName(paramContext, paramString, paramEnumSet, localHashMap, localArrayList1);
    if ((localHashMap.size() > 0) && ((paramEnumSet.contains(ContactLookupType.PHONE_NUMBER)) || (paramEnumSet.contains(ContactLookupType.EMAIL_ADDRESS)) || (paramEnumSet.contains(ContactLookupType.ADDRESS)) || (paramEnumSet.contains(ContactLookupType.BIRTHDAY)) || (paramEnumSet.contains(ContactType.UNDEFINED)) || (paramEnumSet.isEmpty())))
      getContactDetails(paramContext, paramEnumSet, localHashMap, localArrayList1);
    if ((localHashMap.size() > 0) && ((paramEnumSet.contains(ContactLookupType.SOCIAL_NETWORK)) || (paramEnumSet.isEmpty())))
      getSocialContactData(paramContext, localHashMap, localArrayList1);
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = localHashMap.values().iterator();
    while (localIterator.hasNext())
    {
      ContactMatch localContactMatch = (ContactMatch)localIterator.next();
      if ((!paramBoolean) && (!localContactMatch.containsData()))
        continue;
      localArrayList2.add(localContactMatch);
    }
    return localArrayList2;
  }

  public static ContactData getContactMatchesByEmail(Context paramContext, String paramString)
  {
    Cursor localCursor = null;
    Uri localUri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    String[] arrayOfString = new String[9];
    arrayOfString[0] = "contact_id";
    arrayOfString[1] = "raw_contact_id";
    arrayOfString[2] = "lookup";
    arrayOfString[3] = "display_name";
    arrayOfString[4] = "starred";
    arrayOfString[5] = "data1";
    arrayOfString[6] = "data2";
    arrayOfString[7] = "is_super_primary";
    arrayOfString[8] = "times_contacted";
    String str1 = "data1 like '" + paramString + "'";
    try
    {
      localCursor = paramContext.getContentResolver().query(localUri, arrayOfString, str1, null, null);
      if ((localCursor != null) && (localCursor.moveToFirst()) && (localCursor.isLast()))
      {
        int i = localCursor.getColumnIndex("contact_id");
        int j = localCursor.getColumnIndex("lookup");
        int k = localCursor.getColumnIndex("display_name");
        int m = localCursor.getColumnIndex("starred");
        int n = localCursor.getColumnIndex("data1");
        int i1 = localCursor.getColumnIndex("data2");
        int i2 = localCursor.getColumnIndex("is_super_primary");
        int i3 = localCursor.getColumnIndex("times_contacted");
        long l = localCursor.getLong(i);
        String str2 = localCursor.getString(j);
        String str3 = localCursor.getString(k);
        if (localCursor.getInt(m) == 1);
        for (boolean bool = true; ; bool = false)
        {
          ContactMatch localContactMatch = new ContactMatch(str3, l, str2, bool);
          String str4 = localCursor.getString(n);
          int i4 = localCursor.getInt(i1);
          int i5 = localCursor.getInt(i3);
          localContactMatch.addExtraName(str4);
          localContactMatch.setTimesContacted(i5);
          int i6 = localCursor.getInt(i2);
          localContactData = new ContactData(localContactMatch, ContactData.Kind.Email, str4, i4, i6);
          return localContactData;
        }
      }
      if (localCursor != null)
        localCursor.close();
      ContactData localContactData = null;
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
  }

  // ERROR //
  public static ContactMatch getExactContactMatch(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +13 -> 14
    //   4: aload_1
    //   5: invokevirtual 141	java/lang/String:trim	()Ljava/lang/String;
    //   8: invokevirtual 490	java/lang/String:isEmpty	()Z
    //   11: ifeq +7 -> 18
    //   14: aconst_null
    //   15: astore_2
    //   16: aload_2
    //   17: areturn
    //   18: aconst_null
    //   19: astore_3
    //   20: bipush 7
    //   22: anewarray 48	java/lang/String
    //   25: astore 4
    //   27: aload 4
    //   29: iconst_0
    //   30: ldc 230
    //   32: aastore
    //   33: aload 4
    //   35: iconst_1
    //   36: ldc 232
    //   38: aastore
    //   39: aload 4
    //   41: iconst_2
    //   42: ldc 234
    //   44: aastore
    //   45: aload 4
    //   47: iconst_3
    //   48: ldc 236
    //   50: aastore
    //   51: aload 4
    //   53: iconst_4
    //   54: ldc 238
    //   56: aastore
    //   57: aload 4
    //   59: iconst_5
    //   60: ldc 149
    //   62: aastore
    //   63: aload 4
    //   65: bipush 6
    //   67: ldc 240
    //   69: aastore
    //   70: new 35	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   77: ldc_w 492
    //   80: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload_1
    //   84: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: ldc 56
    //   89: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: astore 5
    //   97: new 35	java/lang/StringBuilder
    //   100: dup
    //   101: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   104: ldc 86
    //   106: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload 5
    //   111: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: ldc 72
    //   116: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: ldc 155
    //   121: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: ldc 88
    //   126: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: astore 6
    //   134: aload_0
    //   135: invokevirtual 252	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   138: getstatic 246	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   141: aload 4
    //   143: aload 6
    //   145: aconst_null
    //   146: aconst_null
    //   147: invokevirtual 258	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   150: astore_3
    //   151: aload_3
    //   152: ifnull +226 -> 378
    //   155: aload_3
    //   156: invokeinterface 495 1 0
    //   161: iconst_1
    //   162: if_icmpne +216 -> 378
    //   165: aload_3
    //   166: invokeinterface 263 1 0
    //   171: ifeq +207 -> 378
    //   174: aload_3
    //   175: aload_3
    //   176: ldc 230
    //   178: invokeinterface 267 2 0
    //   183: invokeinterface 271 2 0
    //   188: invokestatic 283	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   191: astore 9
    //   193: aload_3
    //   194: aload_3
    //   195: ldc 234
    //   197: invokeinterface 267 2 0
    //   202: invokeinterface 274 2 0
    //   207: astore 10
    //   209: aload_3
    //   210: aload_3
    //   211: ldc 236
    //   213: invokeinterface 267 2 0
    //   218: invokeinterface 274 2 0
    //   223: astore 11
    //   225: aload_3
    //   226: aload_3
    //   227: ldc 238
    //   229: invokeinterface 267 2 0
    //   234: invokeinterface 277 2 0
    //   239: istore 12
    //   241: aload_3
    //   242: aload_3
    //   243: ldc 240
    //   245: invokeinterface 267 2 0
    //   250: invokeinterface 277 2 0
    //   255: istore 13
    //   257: aload_3
    //   258: aload_3
    //   259: ldc 149
    //   261: invokeinterface 267 2 0
    //   266: invokeinterface 274 2 0
    //   271: astore 14
    //   273: aload 9
    //   275: invokevirtual 338	java/lang/Long:longValue	()J
    //   278: lstore 15
    //   280: iload 12
    //   282: iconst_1
    //   283: if_icmpne +47 -> 330
    //   286: iconst_1
    //   287: istore 17
    //   289: new 290	com/vlingo/core/internal/contacts/ContactMatch
    //   292: dup
    //   293: aload 11
    //   295: lload 15
    //   297: aload 10
    //   299: iload 17
    //   301: invokespecial 307	com/vlingo/core/internal/contacts/ContactMatch:<init>	(Ljava/lang/String;JLjava/lang/String;Z)V
    //   304: astore_2
    //   305: aload_2
    //   306: iload 13
    //   308: invokevirtual 314	com/vlingo/core/internal/contacts/ContactMatch:setTimesContacted	(I)V
    //   311: aload_2
    //   312: aload 14
    //   314: invokevirtual 304	com/vlingo/core/internal/contacts/ContactMatch:addExtraName	(Ljava/lang/String;)V
    //   317: aload_3
    //   318: ifnull -302 -> 16
    //   321: aload_3
    //   322: invokeinterface 300 1 0
    //   327: goto -311 -> 16
    //   330: iconst_0
    //   331: istore 17
    //   333: goto -44 -> 289
    //   336: astore 8
    //   338: aconst_null
    //   339: astore_2
    //   340: aload_3
    //   341: ifnull -325 -> 16
    //   344: aload_3
    //   345: invokeinterface 300 1 0
    //   350: goto -334 -> 16
    //   353: astore 7
    //   355: aload_3
    //   356: ifnull +9 -> 365
    //   359: aload_3
    //   360: invokeinterface 300 1 0
    //   365: aload 7
    //   367: athrow
    //   368: astore 7
    //   370: goto -15 -> 355
    //   373: astore 18
    //   375: goto -35 -> 340
    //   378: aconst_null
    //   379: astore_2
    //   380: goto -63 -> 317
    //
    // Exception table:
    //   from	to	target	type
    //   134	305	336	java/lang/Exception
    //   134	305	353	finally
    //   305	317	368	finally
    //   305	317	373	java/lang/Exception
  }

  private static String[] getFacebookAccountTypeAndName(Context paramContext)
  {
    Account[] arrayOfAccount = AccountManager.get(paramContext).getAccounts();
    int i = arrayOfAccount.length;
    int j = 0;
    String[] arrayOfString;
    if (j < i)
    {
      Account localAccount = arrayOfAccount[j];
      if ((localAccount.type != null) && (localAccount.type.toLowerCase().contains("facebook")))
      {
        arrayOfString = new String[2];
        arrayOfString[0] = localAccount.type;
        arrayOfString[1] = localAccount.name;
      }
    }
    while (true)
    {
      return arrayOfString;
      j++;
      break;
      arrayOfString = null;
    }
  }

  private static String[] getJapaneseDisplayNameWhereClause(String paramString)
  {
    if (StringUtils.isNullOrWhiteSpace(paramString));
    while (true)
    {
      return null;
      paramString.replaceAll("\\s+", "");
    }
  }

  private static String[] getKoreanDisplayNameWhereClause(String paramString1, String paramString2)
  {
    String[] arrayOfString2;
    if (StringUtils.isNullOrWhiteSpace(paramString1))
    {
      arrayOfString2 = null;
      return arrayOfString2;
    }
    String str1 = paramString1.replaceAll("\\s+", "");
    String[] arrayOfString1;
    if (str1.length() == 1)
    {
      arrayOfString1 = new String[1];
      arrayOfString1[0] = (str1 + "%");
    }
    while (true)
    {
      arrayOfString2 = new String[arrayOfString1.length];
      for (int j = 0; j < arrayOfString1.length; j++)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        String str2 = arrayOfString1[j].replace("'", "''");
        localStringBuilder.append(paramString2).append(" LIKE '").append(str2).append("'");
        arrayOfString2[j] = localStringBuilder.toString();
      }
      break;
      if (str1.length() == 2)
      {
        arrayOfString1 = new String[2];
        arrayOfString1[0] = paramString1;
        arrayOfString1[1] = ("%" + str1 + "%");
        continue;
      }
      if (str1.length() == 3)
      {
        if (str1.endsWith("이"))
        {
          arrayOfString1 = new String[3];
          arrayOfString1[0] = paramString1;
          arrayOfString1[1] = ("_" + str1.substring(0, 2));
          arrayOfString1[2] = str1;
          continue;
        }
        arrayOfString1 = new String[4];
        arrayOfString1[0] = paramString1;
        arrayOfString1[1] = (str1 + "%");
        arrayOfString1[2] = ("%" + str1.substring(1));
        arrayOfString1[3] = (str1.substring(0, 2) + "%");
        continue;
      }
      int i = str1.length();
      arrayOfString1 = new String[3];
      arrayOfString1[0] = paramString1;
      arrayOfString1[1] = ("%" + str1 + "%");
      arrayOfString1[2] = ("%" + str1.substring(i - 2, i));
    }
  }

  private static int getSocialContactData(Context paramContext, HashMap<Long, ContactMatch> paramHashMap, List<Long> paramList)
  {
    int i = 0;
    String[] arrayOfString1 = getFacebookAccountTypeAndName(paramContext);
    String[] arrayOfString2;
    Uri localUri;
    int j;
    int k;
    int m;
    String str1;
    Cursor localCursor;
    if (arrayOfString1 != null)
    {
      arrayOfString2 = new String[2];
      arrayOfString2[0] = "contact_id";
      arrayOfString2[1] = "sourceid";
      localUri = ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("account_name", arrayOfString1[1]).appendQueryParameter("account_type", arrayOfString1[0]).build();
      j = 1;
      k = 0;
      m = 0;
      Iterator localIterator = paramList.iterator();
      if (localIterator.hasNext())
      {
        Long localLong = (Long)localIterator.next();
        str1 = "_id=" + localLong;
        localCursor = null;
      }
    }
    while (true)
    {
      String str2;
      try
      {
        localCursor = paramContext.getContentResolver().query(localUri, arrayOfString2, str1, null, null);
        if ((localCursor == null) || (!localCursor.moveToFirst()))
          continue;
        if (j == 0)
          continue;
        k = localCursor.getColumnIndex("contact_id");
        m = localCursor.getColumnIndex("sourceid");
        j = 0;
        long l = localCursor.getLong(k);
        str2 = localCursor.getString(m);
        ContactMatch localContactMatch = (ContactMatch)paramHashMap.get(Long.valueOf(l));
        if ((str2 == null) || (str2.length() == 0))
          continue;
        localContactMatch.addSocial(new ContactData(localContactMatch, ContactData.Kind.Facebook, str2, 2001, 0));
        i++;
        if (localCursor == null)
          break;
        localCursor.close();
      }
      finally
      {
        if (localCursor == null)
          continue;
        localCursor.close();
      }
    }
  }

  public static String getWhere(EnumSet<ContactLookupType> paramEnumSet)
  {
    String str = "";
    if ((paramEnumSet.contains(ContactLookupType.PHONE_NUMBER)) || (paramEnumSet.isEmpty()))
      str = str + "mimetype='vnd.android.cursor.item/phone_v2'";
    if ((paramEnumSet.contains(ContactLookupType.EMAIL_ADDRESS)) || (paramEnumSet.isEmpty()))
    {
      if (str.length() > 0)
        str = str + " OR ";
      str = str + "mimetype='vnd.android.cursor.item/email_v2'";
    }
    if ((paramEnumSet.contains(ContactLookupType.ADDRESS)) || (paramEnumSet.isEmpty()))
    {
      if (str.length() > 0)
        str = str + " OR ";
      str = str + "mimetype='vnd.android.cursor.item/postal-address_v2'";
    }
    if ((paramEnumSet.contains(ContactLookupType.BIRTHDAY)) || (paramEnumSet.isEmpty()))
    {
      if (str.length() > 0)
        str = str + " OR ";
      str = str + "((mimetype='vnd.android.cursor.item/contact_event' OR mimetype='vnd.pantech.cursor.item/lunar_event') AND data2=3)";
    }
    return str;
  }

  static boolean isAddressBookEmpty(Context paramContext)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "contact_id";
    Uri localUri = ContactsContract.Data.CONTENT_URI;
    Cursor localCursor = null;
    try
    {
      localCursor = paramContext.getContentResolver().query(localUri, arrayOfString, "", null, null);
      localCursor.moveToFirst();
      int i = localCursor.getCount();
      if (i == 0)
      {
        j = 1;
        return j;
      }
      int j = 0;
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
  }

  // ERROR //
  public static void populateContactMapping(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: invokestatic 587	com/vlingo/core/internal/contacts/ContactDBUtil:getContactCursor	(Landroid/content/Context;)Landroid/database/Cursor;
    //   6: astore_1
    //   7: new 107	java/util/ArrayList
    //   10: dup
    //   11: invokespecial 424	java/util/ArrayList:<init>	()V
    //   14: astore 4
    //   16: aload_1
    //   17: ifnull +107 -> 124
    //   20: aload_1
    //   21: ldc 193
    //   23: invokeinterface 267 2 0
    //   28: istore 6
    //   30: aload_1
    //   31: ldc 199
    //   33: invokeinterface 267 2 0
    //   38: istore 7
    //   40: aload_1
    //   41: invokeinterface 263 1 0
    //   46: ifeq +78 -> 124
    //   49: aload_1
    //   50: iload 6
    //   52: invokeinterface 274 2 0
    //   57: astore 8
    //   59: aload_1
    //   60: iload 7
    //   62: invokeinterface 274 2 0
    //   67: astore 9
    //   69: aload 8
    //   71: ifnull +21 -> 92
    //   74: aload 4
    //   76: aload 8
    //   78: invokevirtual 588	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   81: ifne +11 -> 92
    //   84: aload 4
    //   86: aload 8
    //   88: invokevirtual 589	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   91: pop
    //   92: aload 9
    //   94: ifnull +21 -> 115
    //   97: aload 4
    //   99: aload 9
    //   101: invokevirtual 588	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   104: ifne +11 -> 115
    //   107: aload 4
    //   109: aload 9
    //   111: invokevirtual 589	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   114: pop
    //   115: aload_1
    //   116: invokeinterface 297 1 0
    //   121: ifne -72 -> 49
    //   124: aload 4
    //   126: invokestatic 593	com/vlingo/core/internal/settings/Settings:setContactNameList	(Ljava/util/ArrayList;)V
    //   129: aload_1
    //   130: ifnull +9 -> 139
    //   133: aload_1
    //   134: invokeinterface 300 1 0
    //   139: return
    //   140: astore_2
    //   141: aload_1
    //   142: ifnull +9 -> 151
    //   145: aload_1
    //   146: invokeinterface 300 1 0
    //   151: aload_2
    //   152: athrow
    //   153: astore 5
    //   155: goto -16 -> 139
    //   158: astore_3
    //   159: goto -8 -> 151
    //
    // Exception table:
    //   from	to	target	type
    //   2	129	140	finally
    //   133	139	153	java/lang/Exception
    //   145	151	158	java/lang/Exception
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.contacts.ContactDBUtil
 * JD-Core Version:    0.6.0
 */