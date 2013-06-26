package com.vlingo.core.internal.dialogmanager.controllers;

import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.util.DialogDataUtil;
import com.vlingo.core.internal.dialogmanager.util.EventUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.schedule.ScheduleQueryObject;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Iterator;
import java.util.List;

public class CheckScheduleController extends Controller
  implements WidgetActionListener
{
  private List<ScheduleEvent> events;

  private ScheduleQueryObject extractNonCanonicalScheduleQuery(VLAction paramVLAction)
  {
    ScheduleQueryObject localScheduleQueryObject = new ScheduleQueryObject();
    String str = VLActionUtil.getParamString(paramVLAction, "date", false);
    if (!StringUtils.isNullOrWhiteSpace(str))
    {
      long l1 = DateUtil.getMillisFromString(str, true);
      long l2 = DateUtil.endOfGivenDay(l1);
      localScheduleQueryObject.setBegin(l1);
      localScheduleQueryObject.setEnd(l2);
    }
    localScheduleQueryObject.setCount(EventUtil.getCount(paramVLAction));
    return localScheduleQueryObject;
  }

  public CheckScheduleController clone()
    throws CloneNotSupportedException
  {
    CheckScheduleController localCheckScheduleController = (CheckScheduleController)super.clone();
    localCheckScheduleController.events = ScheduleEvent.clone(this.events);
    return localCheckScheduleController;
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("schedule");
    this.events = null;
    ScheduleQueryObject localScheduleQueryObject = extractNonCanonicalScheduleQuery(paramVLAction);
    this.events = ScheduleUtil.getScheduledEvents(paramVVSActionHandlerListener.getActivityContext(), localScheduleQueryObject);
    if ((this.events == null) || (this.events.isEmpty()))
    {
      String str1 = getString(ResourceIdProvider.string.core_car_tts_SCHEDULE_NO_EVENTS, new Object[0]);
      showSystemTurn(str1, str1, false, null);
    }
    while (true)
    {
      reset();
      return false;
      new StringBuilder();
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_checkEventYouHaveToday;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.toString(this.events.size());
      String str2 = getString(localstring1, arrayOfObject1);
      showSystemTurn(str2, str2, false, null);
      Iterator localIterator = this.events.iterator();
      while (localIterator.hasNext())
      {
        ScheduleEvent localScheduleEvent = (ScheduleEvent)localIterator.next();
        ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_checkEventTitleTimeDetailBrief;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = localScheduleEvent.getTitle();
        arrayOfObject2[1] = localScheduleEvent.getBeginTime();
        paramVVSActionHandlerListener.tts(getString(localstring2, arrayOfObject2));
      }
      DialogDataUtil.displayScheduleEventList(paramVVSActionHandlerListener, paramVLAction, this.events, this);
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CheckScheduleController{");
    localStringBuilder.append("List<ScheduleEvent>{");
    Iterator localIterator = this.events.iterator();
    while (localIterator.hasNext())
      localStringBuilder.append(((ScheduleEvent)localIterator.next()).toString());
    localStringBuilder.append("}");
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.CheckScheduleController
 * JD-Core Version:    0.6.0
 */