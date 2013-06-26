package com.vlingo.core.internal.dialogmanager.util;

import android.content.Intent;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DialogDataUtil
{
  public static void displayScheduleEventList(VVSActionHandlerListener paramVVSActionHandlerListener, VLAction paramVLAction, List<ScheduleEvent> paramList, WidgetActionListener paramWidgetActionListener)
  {
    Object localObject;
    if (VLActionUtil.getParamString(paramVLAction, "choices", false) != null)
      localObject = null;
    try
    {
      List localList2 = getChoicesAsInts(paramVLAction);
      localObject = localList2;
      label23: if (localObject == null);
      while (true)
      {
        return;
        List localList1 = extractByPosition(paramList, localObject);
        if (localList1 != null)
          paramList = localList1;
        paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.ShowEventChoices, null, paramList, paramWidgetActionListener);
      }
    }
    catch (JSONException localJSONException)
    {
      break label23;
    }
  }

  public static <T> List<T> extractByPosition(List<T> paramList, List<Integer> paramList1)
  {
    ArrayList localArrayList = new ArrayList(paramList1.size());
    if (paramList == null);
    while (true)
    {
      return localArrayList;
      Iterator localIterator = paramList1.iterator();
      while (localIterator.hasNext())
      {
        Integer localInteger = (Integer)localIterator.next();
        try
        {
          localArrayList.add(paramList.get(localInteger.intValue()));
        }
        catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
        {
        }
      }
    }
  }

  public static List<Integer> getChoicesAsInts(VLAction paramVLAction)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONObject(paramVLAction.getParamValue("choices")).getJSONArray("id");
    int i = localJSONArray.length();
    ArrayList localArrayList = new ArrayList(i);
    for (int j = 0; j < i; j++)
      localArrayList.add(Integer.valueOf(localJSONArray.getInt(j)));
    return localArrayList;
  }

  public static List<String> getChoicesAsStrings(VLAction paramVLAction)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONObject(paramVLAction.getParamValue("choices")).getJSONArray("id");
    int i = localJSONArray.length();
    ArrayList localArrayList = new ArrayList(i);
    for (int j = 0; j < i; j++)
      localArrayList.add(localJSONArray.getString(j));
    return localArrayList;
  }

  public static ContactData getContactDataFromId(List<ContactMatch> paramList, String paramString)
  {
    ContactData localContactData;
    if (paramList == null)
      localContactData = null;
    while (true)
    {
      return localContactData;
      int[] arrayOfInt = splitAddressFromContactId(paramString);
      int i = arrayOfInt[0];
      int j = arrayOfInt[1];
      ContactMatch localContactMatch = getContactMatchFromId(paramList, i);
      if (localContactMatch != null)
      {
        try
        {
          localContactData = (ContactData)localContactMatch.getAllData().get(j);
        }
        catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
        {
          localContactData = null;
        }
        continue;
      }
      localContactData = null;
    }
  }

  public static ContactMatch getContactMatchFromId(List<ContactMatch> paramList, int paramInt)
  {
    try
    {
      localContactMatch = (ContactMatch)paramList.get(paramInt);
      return localContactMatch;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      while (true)
        ContactMatch localContactMatch = null;
    }
  }

  public static Set<String> getMessageRawPhoneNumbers(String paramString)
    throws JSONException
  {
    LinkedHashSet localLinkedHashSet = null;
    if (StringUtils.isNullOrWhiteSpace(paramString));
    while (true)
    {
      return localLinkedHashSet;
      JSONObject localJSONObject = new JSONObject(paramString);
      try
      {
        JSONArray localJSONArray = localJSONObject.getJSONArray("numbers");
        int i = localJSONArray.length();
        localLinkedHashSet = new LinkedHashSet(i);
        for (int j = 0; j < i; j++)
          localLinkedHashSet.add(localJSONArray.getString(j));
      }
      catch (JSONException localJSONException)
      {
      }
    }
  }

  public static Set<ContactData> getMessageRecipientContacts(List<ContactMatch> paramList, String paramString)
    throws JSONException
  {
    LinkedHashSet localLinkedHashSet = null;
    if (StringUtils.isNullOrWhiteSpace(paramString));
    while (true)
    {
      return localLinkedHashSet;
      JSONObject localJSONObject = new JSONObject(paramString);
      try
      {
        JSONArray localJSONArray = localJSONObject.getJSONArray("contacts");
        int i = localJSONArray.length();
        localLinkedHashSet = new LinkedHashSet(i);
        for (int j = 0; j < i; j++)
        {
          ContactData localContactData = getContactDataFromId(paramList, localJSONArray.getString(j));
          if (localContactData == null)
            continue;
          localLinkedHashSet.add(localContactData);
        }
      }
      catch (JSONException localJSONException)
      {
      }
    }
  }

  public static <T> List<T> getPrunedList(List<T> paramList, int paramInt, boolean paramBoolean)
  {
    if ((paramList == null) || (paramInt < 1))
      paramList = null;
    int i;
    do
    {
      return paramList;
      i = paramList.size();
    }
    while (paramInt >= i);
    int j = 0;
    int k = i;
    if (paramBoolean)
      j = k - paramInt;
    while (true)
    {
      paramList = paramList.subList(j, k);
      break;
      k = paramInt;
    }
  }

  public static int[] splitAddressFromContactId(String paramString)
    throws InvalidParameterException
  {
    String[] arrayOfString = StringUtils.split(paramString, '.');
    if (arrayOfString.length != 2)
      throw new InvalidParameterException("Expected Contact Address id to be of form <contact_id>.<address_id>: " + paramString);
    int[] arrayOfInt = new int[2];
    try
    {
      arrayOfInt[0] = Integer.parseInt(arrayOfString[0]);
      arrayOfInt[1] = Integer.parseInt(arrayOfString[1]);
      return arrayOfInt;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    throw new InvalidParameterException("Expected Contact Address id to be of form <contact_id>.<address_id>: " + paramString);
  }

  public static class ChoiceListUtil<T>
  {
    public List<Integer> choiceIds = null;
    public List<T> displayedChoices = null;

    public String getIdOfSelection(Intent paramIntent)
    {
      int i = VLActionUtil.extractParamInt(paramIntent, "choice", -1);
      return this.choiceIds.get(i) + "";
    }

    public boolean showChoiceListWidget(VVSActionHandlerListener paramVVSActionHandlerListener, VLAction paramVLAction, DialogDataType paramDialogDataType, WidgetUtil.WidgetKey paramWidgetKey1, WidgetUtil.WidgetKey paramWidgetKey2, WidgetActionListener paramWidgetActionListener, Map<String, Object> paramMap)
    {
      int i = 0;
      if (VLActionUtil.getParamString(paramVLAction, "choices", false) != null);
      try
      {
        this.choiceIds = DialogDataUtil.getChoicesAsInts(paramVLAction);
        label21: if (this.choiceIds == null)
          return i;
        this.displayedChoices = DialogDataUtil.extractByPosition((List)paramVVSActionHandlerListener.getState(paramDialogDataType), this.choiceIds);
        if (this.displayedChoices.size() == 1)
          paramVVSActionHandlerListener.showWidget(paramWidgetKey2, null, this.displayedChoices.get(0), paramWidgetActionListener);
        while (true)
        {
          i = 1;
          break;
          paramVVSActionHandlerListener.showWidget(paramWidgetKey1, null, this.displayedChoices, paramWidgetActionListener);
        }
      }
      catch (JSONException localJSONException)
      {
        break label21;
      }
    }

    public boolean showChoiceListWidget(VVSActionHandlerListener paramVVSActionHandlerListener, VLAction paramVLAction, DialogDataType paramDialogDataType, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener, Map<String, Object> paramMap)
    {
      int i = 0;
      if (VLActionUtil.getParamString(paramVLAction, "choices", false) != null);
      try
      {
        this.choiceIds = DialogDataUtil.getChoicesAsInts(paramVLAction);
        label21: List localList = (List)paramVVSActionHandlerListener.getState(paramDialogDataType);
        if ((this.choiceIds == null) || (localList == null));
        while (true)
        {
          return i;
          this.displayedChoices = DialogDataUtil.extractByPosition(localList, this.choiceIds);
          paramVVSActionHandlerListener.showWidget(paramWidgetKey, null, this.displayedChoices, paramWidgetActionListener);
          i = 1;
        }
      }
      catch (JSONException localJSONException)
      {
        break label21;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.util.DialogDataUtil
 * JD-Core Version:    0.6.0
 */