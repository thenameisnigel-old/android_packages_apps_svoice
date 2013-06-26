package com.vlingo.midas.util;

import android.content.res.Resources;
import android.text.format.Time;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.CallLogUtil;
import com.vlingo.core.internal.util.LoggedCall;
import com.vlingo.core.internal.util.SMSUtil;
import com.vlingo.core.internal.util.SMSUtil.TextMessage;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.ControlFragment;
import java.util.ArrayList;
import java.util.List;

public class CheckPhoneEvents
{
  private static CheckPhoneEvents myInstance = null;
  private ControlFragment delegate = null;
  private Resources res = null;

  private CheckPhoneEvents(ControlFragment paramControlFragment)
  {
    this.delegate = paramControlFragment;
    this.res = this.delegate.getResources();
  }

  public static CheckPhoneEvents getInstance(ControlFragment paramControlFragment)
  {
    if (myInstance != null);
    for (CheckPhoneEvents localCheckPhoneEvents = myInstance; ; localCheckPhoneEvents = new CheckPhoneEvents(paramControlFragment))
      return localCheckPhoneEvents;
  }

  private String ttsAlarmDetails(ScheduleEvent paramScheduleEvent)
  {
    String str1 = paramScheduleEvent.getTitle();
    Long localLong = Long.valueOf(paramScheduleEvent.getBegin());
    Time localTime = new Time();
    localTime.set(localLong.longValue());
    String str2 = localTime.format(this.res.getString(2131362632));
    int i = Integer.parseInt(localTime.format("%H"));
    Resources localResources2;
    Object[] arrayOfObject2;
    if (!str1.isEmpty())
    {
      localResources2 = this.res;
      arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = str1;
      arrayOfObject2[1] = str2;
    }
    Resources localResources1;
    Object[] arrayOfObject1;
    for (String str3 = localResources2.getString(2131362253, arrayOfObject2); ; str3 = localResources1.getQuantityString(2131492865, i, arrayOfObject1))
    {
      return str3;
      localResources1 = this.res;
      arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = str2;
    }
  }

  private String ttsAlarmNumber(List<ScheduleEvent> paramList)
  {
    String str = "";
    if (paramList.size() > 0)
    {
      Resources localResources = this.res;
      int i = paramList.size();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramList.size());
      str = localResources.getQuantityString(2131492868, i, arrayOfObject);
    }
    return str;
  }

  private String ttsCallDetails(LoggedCall paramLoggedCall)
  {
    String str1 = StringUtils.formatPhoneNumberForTTS(paramLoggedCall.getName());
    Long localLong = Long.valueOf(paramLoggedCall.getDate());
    Time localTime = new Time();
    localTime.set(localLong.longValue());
    String str2 = localTime.format(this.res.getString(2131362632));
    int i = Integer.parseInt(localTime.format("%H"));
    Resources localResources2;
    Object[] arrayOfObject2;
    if (!str1.isEmpty())
    {
      localResources2 = this.res;
      arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = str1;
      arrayOfObject2[1] = str2;
    }
    Resources localResources1;
    Object[] arrayOfObject1;
    for (String str3 = localResources2.getQuantityString(2131492864, i, arrayOfObject2); ; str3 = localResources1.getQuantityString(2131492865, i, arrayOfObject1))
    {
      return str3;
      localResources1 = this.res;
      arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = str2;
    }
  }

  private String ttsCallNumber(ArrayList<LoggedCall> paramArrayList)
  {
    String str = "";
    if (paramArrayList.size() > 0)
    {
      Resources localResources = this.res;
      int i = paramArrayList.size();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramArrayList.size());
      str = localResources.getQuantityString(2131492867, i, arrayOfObject);
    }
    return str;
  }

  private void ttsEventSummary(ArrayList<SMSUtil.TextMessage> paramArrayList, List<ScheduleEvent> paramList, ArrayList<LoggedCall> paramArrayList1, int paramInt)
  {
    String str1 = Settings.getString("language", "en-US");
    String str2;
    String str5;
    if (str1.equals("ja-JP"))
      if (paramInt > 9)
      {
        ResourceIdProvider localResourceIdProvider5 = VlingoAndroidCore.getResourceProvider();
        ResourceIdProvider.string localstring5 = ResourceIdProvider.string.core_checkEventJapaneseMoreTenYouHave;
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = Integer.valueOf(paramInt);
        str2 = localResourceIdProvider5.getString(localstring5, arrayOfObject5);
        String str3 = "" + ttsCallNumber(paramArrayList1);
        if ((paramArrayList1.size() > 0) && ((paramArrayList.size() > 0) || (paramList.size() > 0)))
          str3 = str3 + ", ";
        String str4 = str3 + ttsSmsNumber(paramArrayList);
        if ((paramArrayList.size() > 0) && (paramList.size() > 0))
          str4 = str4 + ", ";
        str5 = str4 + ttsAlarmNumber(paramList);
        if (!str1.equals("ko-KR"))
          break label526;
      }
    label526: for (String str6 = str5 + " " + str2; ; str6 = str2 + str5)
    {
      this.delegate.announceTTSOnDialogIdle(str6);
      return;
      ResourceIdProvider localResourceIdProvider4 = VlingoAndroidCore.getResourceProvider();
      ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_checkEventYouHave;
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Integer.valueOf(paramInt);
      str2 = localResourceIdProvider4.getString(localstring4, arrayOfObject4);
      break;
      if (str1.equals("ru-RU"))
      {
        int i;
        label329: int j;
        label338: int k;
        if (paramInt == 2)
        {
          i = 1;
          if (paramInt != 3)
            break label409;
          j = 1;
          k = i | j;
          if (paramInt != 4)
            break label415;
        }
        label409: label415: for (int m = 1; ; m = 0)
        {
          if ((m | k) == 0)
            break label421;
          ResourceIdProvider localResourceIdProvider3 = VlingoAndroidCore.getResourceProvider();
          ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_checkEventRussianTwoThreeFourYouHave;
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = Integer.valueOf(paramInt);
          str2 = localResourceIdProvider3.getString(localstring3, arrayOfObject3);
          break;
          i = 0;
          break label329;
          j = 0;
          break label338;
        }
        label421: if (paramInt % 10 == 1)
        {
          ResourceIdProvider localResourceIdProvider2 = VlingoAndroidCore.getResourceProvider();
          ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_checkEventOneYouHave;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Integer.valueOf(paramInt);
          str2 = localResourceIdProvider2.getString(localstring2, arrayOfObject2);
          break;
        }
        ResourceIdProvider localResourceIdProvider1 = VlingoAndroidCore.getResourceProvider();
        ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_checkEventYouHave;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(paramInt);
        str2 = localResourceIdProvider1.getString(localstring1, arrayOfObject1);
        break;
      }
      str2 = this.res.getString(2131362627);
      break;
    }
  }

  private void ttsOneEvent(ArrayList<SMSUtil.TextMessage> paramArrayList, List<ScheduleEvent> paramList, ArrayList<LoggedCall> paramArrayList1, int paramInt)
  {
    String str1 = this.res.getString(2131362627);
    String str2 = "";
    String str3 = Settings.getString("language", "en-US");
    String str6;
    if (1 == paramArrayList.size())
      if (str3.equals("ko-KR"))
      {
        str6 = ttsSmsNumber(paramArrayList) + " " + str1 + ". ";
        str2 = str6 + ttsSmsDetails((SMSUtil.TextMessage)paramArrayList.get(0));
      }
    do
    {
      this.delegate.announceTTSOnDialogIdle(str2);
      return;
      str6 = str1 + ttsSmsNumber(paramArrayList) + " ";
      break;
      if (1 != paramList.size())
        continue;
      if (str3.equals("ko-KR"));
      for (String str5 = ttsAlarmNumber(paramList) + " " + str1 + ". "; ; str5 = str1 + ttsAlarmNumber(paramList) + " ")
      {
        str2 = str5 + ttsAlarmDetails((ScheduleEvent)paramList.get(0));
        break;
      }
    }
    while (1 != paramArrayList1.size());
    if (str3.equals("ko-KR"));
    for (String str4 = ttsCallNumber(paramArrayList1) + " " + str1 + ". "; ; str4 = str1 + ttsCallNumber(paramArrayList1) + " ")
    {
      str2 = str4 + ttsCallDetails((LoggedCall)paramArrayList1.get(0));
      break;
    }
  }

  private String ttsSmsDetails(SMSUtil.TextMessage paramTextMessage)
  {
    String str1 = paramTextMessage.name;
    String str2 = StringUtils.formatPhoneNumberForTTS(paramTextMessage.address);
    Long localLong = Long.valueOf(paramTextMessage.date);
    Time localTime = new Time();
    localTime.set(localLong.longValue());
    String str3 = localTime.format(this.res.getString(2131362632));
    int i = Integer.parseInt(localTime.format("%H"));
    String str4;
    if (!str1.isEmpty())
    {
      Resources localResources3 = this.res;
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = str1;
      arrayOfObject3[1] = str3;
      str4 = localResources3.getQuantityString(2131492864, i, arrayOfObject3);
    }
    while (true)
    {
      return str4;
      if (!str2.isEmpty())
      {
        Resources localResources2 = this.res;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = str2;
        arrayOfObject2[1] = str3;
        str4 = localResources2.getQuantityString(2131492864, i, arrayOfObject2);
        continue;
      }
      Resources localResources1 = this.res;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = str3;
      str4 = localResources1.getQuantityString(2131492865, i, arrayOfObject1);
    }
  }

  private String ttsSmsNumber(ArrayList<SMSUtil.TextMessage> paramArrayList)
  {
    String str = "";
    if (paramArrayList.size() > 0)
    {
      Resources localResources = this.res;
      int i = paramArrayList.size();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramArrayList.size());
      str = localResources.getQuantityString(2131492866, i, arrayOfObject);
    }
    return str;
  }

  public boolean check()
  {
    int i = 1;
    ArrayList localArrayList1 = SMSUtil.getLastNUnreadMessages(this.delegate.getActivity(), 50);
    int j = localArrayList1.size();
    List localList = ScheduleUtil.getFiredCalendarAlerts(this.delegate.getActivity(), 50);
    int k = localList.size();
    ArrayList localArrayList2 = CallLogUtil.getLastNMissedCalls(this.delegate.getActivity(), 50);
    int m = localArrayList2.size() + (j + k);
    if (m == i)
      ttsOneEvent(localArrayList1, localList, localArrayList2, m);
    while (true)
    {
      return i;
      if (m > i)
      {
        ttsEventSummary(localArrayList1, localList, localArrayList2, m);
        continue;
      }
      i = 0;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.CheckPhoneEvents
 * JD-Core Version:    0.6.0
 */