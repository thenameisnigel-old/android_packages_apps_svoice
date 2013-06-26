package com.vlingo.sdk.internal.lmtt;

import com.vlingo.sdk.internal.AndroidServerDetails;
import com.vlingo.sdk.internal.http.HttpCallback;
import com.vlingo.sdk.internal.http.HttpResponse;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.vlservice.VLHttpServiceRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LMTTComm
{
  public static final int LMTT_ERROR = 3;
  public static final int LMTT_OK = 1;
  public static final int LMTT_REQUIRES_FULL_RESYNC = 2;
  private static final String SERVER_COUNT_CONTACT = "contact";
  private static final String SERVER_COUNT_PLAYLIST = "playlist";
  private static final String SERVER_COUNT_SONG = "song";

  public static VLHttpServiceRequest createLMTTRequest(List<LMTTItem> paramList, boolean paramBoolean, String paramString, HttpCallback paramHttpCallback)
  {
    String str = generateXML(paramList, paramBoolean);
    VLHttpServiceRequest localVLHttpServiceRequest = VLHttpServiceRequest.createVLRequest("LMTT", paramHttpCallback, AndroidServerDetails.getLMTTURL(), str, paramString);
    localVLHttpServiceRequest.setGzipPostData(true);
    localVLHttpServiceRequest.setMaxRetry(0);
    return localVLHttpServiceRequest;
  }

  private static String generateXML(List<LMTTItem> paramList, boolean paramBoolean)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      LMTTItem localLMTTItem = (LMTTItem)localIterator.next();
      switch (1.$SwitchMap$com$vlingo$sdk$internal$lmtt$LMTTItem$LmttItemType[localLMTTItem.type.ordinal()])
      {
      default:
        break;
      case 1:
        localArrayList1.add(localLMTTItem);
        break;
      case 2:
        localArrayList2.add(localLMTTItem);
        break;
      case 3:
        localArrayList3.add(localLMTTItem);
      }
    }
    StringBuilder localStringBuilder = new StringBuilder(20 * paramList.size());
    localStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    localStringBuilder.append("<LMTT>");
    if (localArrayList1.size() > 0)
    {
      if (paramBoolean)
      {
        localStringBuilder.append("<PIM t=\"w\">");
        getListItemsXML(localArrayList1, localStringBuilder);
        localStringBuilder.append("</PIM>");
      }
    }
    else if ((localArrayList2.size() > 0) || (localArrayList3.size() > 0))
    {
      if (!paramBoolean)
        break label329;
      localStringBuilder.append("<MU t=\"w\">");
    }
    while (true)
    {
      if (localArrayList2.size() > 0)
      {
        localStringBuilder.append("<SUS>");
        getListItemsXML(localArrayList2, localStringBuilder);
        localStringBuilder.append("</SUS>");
      }
      if (localArrayList3.size() > 0)
      {
        localStringBuilder.append("<PUS>");
        getListItemsXML(localArrayList3, localStringBuilder);
        localStringBuilder.append("</PUS>");
      }
      localStringBuilder.append("</MU>");
      localStringBuilder.append("</LMTT>");
      return localStringBuilder.toString();
      localStringBuilder.append("<PIM t=\"p\">");
      break;
      label329: localStringBuilder.append("<MU t=\"p\">");
    }
  }

  private static void getListItemsXML(List<LMTTItem> paramList, StringBuilder paramStringBuilder)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      paramStringBuilder.append(((LMTTItem)localIterator.next()).getXML());
  }

  private static LMTTItem.LmttItemType mapServerType2ClientType(String paramString)
  {
    LMTTItem.LmttItemType localLmttItemType = LMTTItem.LmttItemType.TYPE_UNKNOWN;
    if ("playlist".equalsIgnoreCase(paramString))
      localLmttItemType = LMTTItem.LmttItemType.TYPE_PLAYLIST;
    while (true)
    {
      return localLmttItemType;
      if ("song".equalsIgnoreCase(paramString))
      {
        localLmttItemType = LMTTItem.LmttItemType.TYPE_SONG;
        continue;
      }
      if (!"contact".equalsIgnoreCase(paramString))
        continue;
      localLmttItemType = LMTTItem.LmttItemType.TYPE_CONTACT;
    }
  }

  public static HashMap<LMTTItem.LmttItemType, Integer> parseLMTTResponse(HttpResponse paramHttpResponse)
  {
    String str1 = paramHttpResponse.getDataAsString().trim();
    HashMap localHashMap;
    try
    {
      String str2 = StringUtils.getSubstring(str1, "count=\"", "\"");
      String str3 = StringUtils.getSubstring(str1, "type=\"", "\"");
      if ((str2 != null) && (str3 != null))
      {
        String[] arrayOfString1 = StringUtils.split(str2, ',');
        String[] arrayOfString2 = StringUtils.split(str3, ',');
        if ((arrayOfString1.length >= 1) && (arrayOfString2.length >= 1) && (arrayOfString1.length == arrayOfString2.length))
        {
          localHashMap = new HashMap();
          for (int i = 0; i < arrayOfString2.length; i++)
          {
            if (StringUtils.isNullOrWhiteSpace(arrayOfString1[i]))
              continue;
            int j = Integer.valueOf(arrayOfString1[i]).intValue();
            localHashMap.put(mapServerType2ClientType(arrayOfString2[i]), Integer.valueOf(j));
          }
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localHashMap = null;
    }
    return localHashMap;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.lmtt.LMTTComm
 * JD-Core Version:    0.6.0
 */