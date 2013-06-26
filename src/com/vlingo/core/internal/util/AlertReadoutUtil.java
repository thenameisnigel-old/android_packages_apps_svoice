package com.vlingo.core.internal.util;

import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.types.MessageReadoutType;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.types.RecipientType;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class AlertReadoutUtil
{
  public static final int EXPANDED_LIST_SIZE = 6;
  public static final int SHORT_LIST_SIZE = 3;

  public static HashMap<String, MessageReadoutType> createSMSMMSSenderQueueMap(LinkedList<? extends SafeReaderAlert> paramLinkedList)
  {
    HashMap localHashMap = new HashMap();
    if ((paramLinkedList != null) && (!paramLinkedList.isEmpty()))
    {
      Iterator localIterator = paramLinkedList.iterator();
      while (localIterator.hasNext())
      {
        SafeReaderAlert localSafeReaderAlert = (SafeReaderAlert)localIterator.next();
        if ((!isSMSAlert(localSafeReaderAlert)) && (!isMMSAlert(localSafeReaderAlert)))
          continue;
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localSafeReaderAlert;
        if (StringUtils.isNullOrWhiteSpace(localSMSMMSAlert.getAddress()))
          continue;
        if (localHashMap.containsKey(localSMSMMSAlert.getAddress()))
        {
          MessageReadoutType localMessageReadoutType2 = (MessageReadoutType)localHashMap.get(localSMSMMSAlert.getAddress());
          localMessageReadoutType2.getMessagesFromSender().add(localSMSMMSAlert);
          localHashMap.put(localSMSMMSAlert.getAddress(), localMessageReadoutType2);
          continue;
        }
        LinkedList localLinkedList = new LinkedList();
        localLinkedList.add(localSMSMMSAlert);
        MessageReadoutType localMessageReadoutType1 = new MessageReadoutType(localSMSMMSAlert.getAddress(), localLinkedList);
        localMessageReadoutType1.setDisplayName(localSMSMMSAlert.getSenderDisplayName());
        localHashMap.put(localSMSMMSAlert.getAddress(), localMessageReadoutType1);
      }
    }
    return localHashMap;
  }

  public static HashMap<String, MessageReadoutType> getMessageQueueByContactName(HashMap<String, MessageReadoutType> paramHashMap, String paramString)
  {
    HashMap localHashMap = new HashMap();
    if ((paramHashMap != null) && (!paramHashMap.isEmpty()) && (!StringUtils.isNullOrWhiteSpace(paramString)))
    {
      localHashMap = nameMatchByType(paramHashMap, paramString, SearchType.EXACT);
      if ((localHashMap == null) || (localHashMap.isEmpty()))
      {
        localHashMap = nameMatchByType(paramHashMap, paramString, SearchType.CONTAINS);
        if ((localHashMap == null) || (localHashMap.isEmpty()))
          localHashMap = nameMatchByType(paramHashMap, paramString, SearchType.FALLBACK);
      }
    }
    return localHashMap;
  }

  public static MessageType getMessageTypeFromAlert(SafeReaderAlert paramSafeReaderAlert, boolean paramBoolean)
  {
    SMSMMSAlert localSMSMMSAlert;
    String str1;
    label41: MessageType localMessageType;
    if (isSMSMMSAlert(paramSafeReaderAlert))
    {
      localSMSMMSAlert = (SMSMMSAlert)paramSafeReaderAlert;
      if (paramBoolean)
        if (isMMSAlert(localSMSMMSAlert))
        {
          str1 = localSMSMMSAlert.getSubject();
          if (StringUtils.isNullOrWhiteSpace(str1))
            str1 = "";
          String str2 = localSMSMMSAlert.getSenderName();
          localMessageType = new MessageType(str1, str2, "");
          if (!isSMSAlert(paramSafeReaderAlert))
            break label118;
          localMessageType.setType("SMS");
          label74: localMessageType.addRecipient(new RecipientType(str2, localSMSMMSAlert.getAddress()));
        }
    }
    while (true)
    {
      return localMessageType;
      str1 = localSMSMMSAlert.getBody();
      break;
      str1 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_safereader_hidden_message_body);
      break label41;
      label118: if (!isMMSAlert(paramSafeReaderAlert))
        break label74;
      localMessageType.setType("MMS");
      break label74;
      localMessageType = null;
    }
  }

  public static int getTotalMessagesFromSenderQueue(HashMap<String, MessageReadoutType> paramHashMap)
  {
    int i = 0;
    if ((paramHashMap != null) && (!paramHashMap.isEmpty()))
    {
      Iterator localIterator = paramHashMap.values().iterator();
      while (localIterator.hasNext())
      {
        MessageReadoutType localMessageReadoutType = (MessageReadoutType)localIterator.next();
        if (localMessageReadoutType == null)
          continue;
        i += localMessageReadoutType.getMessageCount();
      }
    }
    return i;
  }

  public static boolean hasNext(LinkedList<?> paramLinkedList, int paramInt)
  {
    if ((paramLinkedList != null) && (!paramLinkedList.isEmpty()) && (paramInt >= 0) && (paramInt + 1 < paramLinkedList.size()));
    for (int i = 1; ; i = 0)
      return i;
  }

  private static boolean isKoreanName(String paramString)
  {
    int i = 0;
    int k;
    if (StringUtils.isNullOrWhiteSpace(paramString))
    {
      char[] arrayOfChar = paramString.toCharArray();
      int j = arrayOfChar.length;
      k = 0;
      if (k >= j)
        break label51;
      char c = arrayOfChar[k];
      if (Character.UnicodeBlock.HANGUL_SYLLABLES == Character.UnicodeBlock.of(c))
        break label45;
    }
    while (true)
    {
      return i;
      label45: k++;
      break;
      label51: i = 1;
    }
  }

  public static boolean isMMSAlert(SafeReaderAlert paramSafeReaderAlert)
  {
    if ((paramSafeReaderAlert != null) && (paramSafeReaderAlert.getType() != null));
    for (boolean bool = paramSafeReaderAlert.getType().equals("MMS"); ; bool = false)
      return bool;
  }

  public static boolean isSMSAlert(SafeReaderAlert paramSafeReaderAlert)
  {
    if ((paramSafeReaderAlert != null) && (paramSafeReaderAlert.getType() != null));
    for (boolean bool = paramSafeReaderAlert.getType().equals("SMS"); ; bool = false)
      return bool;
  }

  public static boolean isSMSMMSAlert(SafeReaderAlert paramSafeReaderAlert)
  {
    int i = 0;
    if ((paramSafeReaderAlert != null) && (paramSafeReaderAlert.getType() != null) && ((isSMSAlert(paramSafeReaderAlert)) || (isMMSAlert(paramSafeReaderAlert))))
      i = 1;
    return i;
  }

  private static HashMap<String, MessageReadoutType> nameMatchByType(HashMap<String, MessageReadoutType> paramHashMap, String paramString, SearchType paramSearchType)
  {
    HashMap localHashMap = new HashMap();
    if (isKoreanName(paramString))
      paramString = paramString.replaceAll("\\s+", "");
    String str1 = StringUtils.cleanPhoneNumber(paramString);
    if ((paramHashMap != null) && (!paramHashMap.isEmpty()))
    {
      String[] arrayOfString;
      Iterator localIterator3;
      switch (1.$SwitchMap$com$vlingo$core$internal$util$AlertReadoutUtil$SearchType[paramSearchType.ordinal()])
      {
      default:
        arrayOfString = paramString.split("\\s");
        if ((isKoreanName(paramString)) && (paramString.endsWith("이")))
          arrayOfString[arrayOfString.length] = paramString.substring(0, 2);
        localIterator3 = paramHashMap.entrySet().iterator();
      case 1:
      case 2:
      }
      while (localIterator3.hasNext())
      {
        Map.Entry localEntry3 = (Map.Entry)localIterator3.next();
        if ((localEntry3 == null) || (localEntry3.getValue() == null) || (localEntry3.getKey() == null))
          continue;
        for (int i = 0; i < arrayOfString.length; i++)
        {
          String str6 = ((MessageReadoutType)localEntry3.getValue()).getDisplayName();
          if ((str6 == null) || (!str6.toLowerCase().contains(arrayOfString[i].toLowerCase())))
            continue;
          localHashMap.put(localEntry3.getKey(), localEntry3.getValue());
        }
        Iterator localIterator2 = paramHashMap.entrySet().iterator();
        while (localIterator2.hasNext())
        {
          Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
          if ((localEntry2 == null) || (localEntry2.getValue() == null) || (localEntry2.getKey() == null))
            continue;
          String str4 = ((MessageReadoutType)localEntry2.getValue()).getDisplayName();
          String str5 = StringUtils.cleanPhoneNumber(((MessageReadoutType)localEntry2.getValue()).getAddress());
          if (((str4 == null) || (!str4.equalsIgnoreCase(paramString))) && ((str5 == null) || (!str5.equalsIgnoreCase(str1)) || (StringUtils.isNullOrWhiteSpace(str1))))
            continue;
          localHashMap.put(localEntry2.getKey(), localEntry2.getValue());
          continue;
          Iterator localIterator1 = paramHashMap.entrySet().iterator();
          while (localIterator1.hasNext())
          {
            Map.Entry localEntry1 = (Map.Entry)localIterator1.next();
            if ((localEntry1 == null) || (localEntry1.getValue() == null) || (localEntry1.getKey() == null))
              continue;
            String str2 = ((MessageReadoutType)localEntry1.getValue()).getDisplayName();
            String str3 = StringUtils.cleanPhoneNumber(((MessageReadoutType)localEntry1.getValue()).getAddress());
            if (((str2 == null) || (!str2.toLowerCase().contains(paramString.toLowerCase()))) && ((str3 == null) || (!str3.toLowerCase().contains(str1)) || (StringUtils.isNullOrWhiteSpace(str1))))
              continue;
            localHashMap.put(localEntry1.getKey(), localEntry1.getValue());
          }
        }
      }
    }
    return localHashMap;
  }

  public static List<MessageReadoutType> sortMessageReadoutList(List<MessageReadoutType> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
      Collections.sort(paramList, new MessageReadoutTypeComparator());
    while (true)
    {
      return paramList;
      paramList = new LinkedList();
    }
  }

  public static void trimListToMaxDisplay(LinkedList<?> paramLinkedList)
  {
    if ((paramLinkedList != null) && (!paramLinkedList.isEmpty()) && (ClientSuppliedValues.getMaxDisplayNumber() > 0))
      while (paramLinkedList.size() > ClientSuppliedValues.getMaxDisplayNumber())
        paramLinkedList.removeLast();
  }

  private static class MessageReadoutTypeComparator
    implements Comparator<MessageReadoutType>
  {
    public int compare(MessageReadoutType paramMessageReadoutType1, MessageReadoutType paramMessageReadoutType2)
    {
      return (int)(paramMessageReadoutType2.getMostRecentTimestamp() - paramMessageReadoutType1.getMostRecentTimestamp());
    }
  }

  private static enum SearchType
  {
    static
    {
      CONTAINS = new SearchType("CONTAINS", 1);
      FALLBACK = new SearchType("FALLBACK", 2);
      SearchType[] arrayOfSearchType = new SearchType[3];
      arrayOfSearchType[0] = EXACT;
      arrayOfSearchType[1] = CONTAINS;
      arrayOfSearchType[2] = FALLBACK;
      $VALUES = arrayOfSearchType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.AlertReadoutUtil
 * JD-Core Version:    0.6.0
 */