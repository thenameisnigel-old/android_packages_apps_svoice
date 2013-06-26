package com.vlingo.core.internal.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.PhoneNumberUtils;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.DMActionFactory;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.VideoDialAction;
import com.vlingo.core.internal.dialogmanager.actions.VoiceDialAction;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.settings.Settings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DialUtil
{
  public static void dial(Context paramContext, String paramString, DMAction.Listener paramListener, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    dial(paramContext, paramString, paramListener, paramVVSActionHandlerListener, false);
  }

  public static void dial(Context paramContext, String paramString, DMAction.Listener paramListener, VVSActionHandlerListener paramVVSActionHandlerListener, boolean paramBoolean)
  {
    if (paramBoolean)
      ((VideoDialAction)DMActionFactory.getInstance(DMActionType.VIDEO_DIAL, VideoDialAction.class)).address(paramString).setContext(paramContext).setListener(paramListener).setActionHandlerListener(paramVVSActionHandlerListener).queue();
    while (true)
    {
      return;
      ((VoiceDialAction)DMActionFactory.getInstance(DMActionType.VOICE_DIAL, VoiceDialAction.class)).address(paramString).setContext(paramContext).setListener(paramListener).setActionHandlerListener(paramVVSActionHandlerListener).queue();
    }
  }

  public static List<ContactData> filterContactDataByType(List<ContactData> paramList, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString.equalsIgnoreCase("call"));
    while (true)
    {
      return paramList;
      String str = paramString.replaceAll("\\d", "");
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        ContactData localContactData = (ContactData)localIterator.next();
        if (!ContactUtil.getTypeStringEN(localContactData.type).equalsIgnoreCase(str))
          continue;
        localArrayList.add(localContactData);
      }
      paramList = localArrayList;
    }
  }

  private static String getContactId(String paramString, Context paramContext)
  {
    String str1 = null;
    if (StringUtils.isNullOrWhiteSpace(paramString));
    for (String str2 = null; ; str2 = str1)
    {
      return str2;
      ContentResolver localContentResolver = paramContext.getContentResolver();
      Uri localUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString));
      String[] arrayOfString = new String[2];
      arrayOfString[0] = "display_name";
      arrayOfString[1] = "_id";
      Cursor localCursor = localContentResolver.query(localUri, arrayOfString, null, null, null);
      if (localCursor.moveToFirst())
        do
          str1 = localCursor.getString(localCursor.getColumnIndex("_id"));
        while (localCursor.moveToNext());
      localCursor.close();
    }
  }

  public static String getDefaultNumber(List<ContactData> paramList)
  {
    String str = null;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ContactData localContactData = (ContactData)localIterator.next();
      if (localContactData.isDefault <= 0)
        continue;
      str = localContactData.address;
    }
    return str;
  }

  public static Intent getDialIntent(String paramString)
  {
    String str = PhoneNumberUtils.stripSeparators(paramString);
    Intent localIntent = new Intent("android.intent.action.CALL", Uri.fromParts("tel", str, null));
    localIntent.addFlags(335544320);
    if ((!((AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio")).isWiredHeadsetOn()) && (Build.VERSION.SDK_INT > 15))
      localIntent.putExtra("android.phone.extra.speakeron", Settings.getBoolean("car_auto_start_speakerphone", false));
    if (isPhoneExistsInContacts(str))
      localIntent.putExtra("origin", "from_contact");
    return localIntent;
  }

  public static int getPhoneType(ContactType paramContactType, String paramString)
  {
    int i = -1;
    if (StringUtils.isNullOrWhiteSpace(paramString));
    while (true)
    {
      return i;
      if (paramContactType != ContactType.CALL)
        continue;
      if (paramString.equalsIgnoreCase("work"))
      {
        i = 3;
        continue;
      }
      if (paramString.equalsIgnoreCase("mobile"))
      {
        i = 2;
        continue;
      }
      if (paramString.equalsIgnoreCase("home"))
      {
        i = 1;
        continue;
      }
      if (!paramString.equalsIgnoreCase("other"))
        continue;
      i = 7;
    }
  }

  public static Map<String, ContactData> getPhoneTypeMap(Resources paramResources, List<ContactData> paramList, String paramString)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    HashMap localHashMap = new HashMap();
    List localList = filterContactDataByType(paramList, paramString);
    Collections.sort(localList, new ContactDataComparator());
    Iterator localIterator1 = localList.iterator();
    while (localIterator1.hasNext())
    {
      ContactData localContactData2 = (ContactData)localIterator1.next();
      if (localHashMap.containsKey(Integer.valueOf(localContactData2.type)))
      {
        Integer localInteger2 = (Integer)localHashMap.get(Integer.valueOf(localContactData2.type));
        localHashMap.put(Integer.valueOf(localContactData2.type), Integer.valueOf(1 + localInteger2.intValue()));
        continue;
      }
      localHashMap.put(Integer.valueOf(localContactData2.type), Integer.valueOf(1));
    }
    int i = 1;
    Integer localInteger1 = null;
    Iterator localIterator2 = localList.iterator();
    while (localIterator2.hasNext())
    {
      ContactData localContactData1 = (ContactData)localIterator2.next();
      if (!localHashMap.containsKey(Integer.valueOf(localContactData1.type)))
        continue;
      if (localInteger1 == null)
        localInteger1 = Integer.valueOf(localContactData1.type);
      if (localInteger1.intValue() != localContactData1.type)
      {
        i = 1;
        localInteger1 = Integer.valueOf(localContactData1.type);
      }
      if (((Integer)localHashMap.get(Integer.valueOf(localContactData1.type))).intValue() > 1)
      {
        localLinkedHashMap.put((String)ContactsContract.CommonDataKinds.Phone.getTypeLabel(paramResources, localContactData1.type, localContactData1.label) + " " + i, localContactData1);
        i++;
        continue;
      }
      localLinkedHashMap.put((String)ContactsContract.CommonDataKinds.Phone.getTypeLabel(paramResources, localContactData1.type, localContactData1.label), localContactData1);
    }
    return localLinkedHashMap;
  }

  public static String getTTSForAddress(String paramString)
  {
    String str = "";
    int i = 0;
    while (true)
      if (i < paramString.length())
      {
        int j = -1;
        try
        {
          int k = Integer.parseInt(paramString.charAt(i) + "");
          j = k;
          if ((j < 0) || (j > 9))
            i++;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          while (true)
          {
            if (paramString.charAt(i) != '-')
              continue;
            str = str + "- ";
            continue;
            str = str + paramString.charAt(i) + " ";
          }
        }
      }
    if (str.length() > 1)
      paramString = str.substring(0, -1 + str.length());
    return paramString;
  }

  public static Intent getVideoDialIntent(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.CALL", Uri.fromParts("tel", PhoneNumberUtils.stripSeparators(paramString), null));
    localIntent.putExtra("videocall", true);
    localIntent.setFlags(268435456);
    return localIntent;
  }

  private static boolean isPhoneExistsInContacts(String paramString)
  {
    if (getContactId(paramString, ApplicationAdapter.getInstance().getApplicationContext()) != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  private static class ContactDataComparator
    implements Comparator<ContactData>
  {
    public int compare(ContactData paramContactData1, ContactData paramContactData2)
    {
      int i;
      if (paramContactData1.getScore() > paramContactData2.getScore())
        i = -1;
      while (true)
      {
        return i;
        if (paramContactData1.getScore() < paramContactData2.getScore())
        {
          i = 1;
          continue;
        }
        i = paramContactData1.type - paramContactData2.type;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.DialUtil
 * JD-Core Version:    0.6.0
 */