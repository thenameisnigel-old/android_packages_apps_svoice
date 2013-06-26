package com.vlingo.core.internal.dialogmanager.controllers;

import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.StateController;
import com.vlingo.core.internal.dialogmanager.StateController.Rule;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.EditScheduleAction;
import com.vlingo.core.internal.dialogmanager.actions.ScheduleAppointmentAction;
import com.vlingo.core.internal.dialogmanager.actions.ViewScheduleAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.AddEventAcceptedText;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventController extends StateController
  implements WidgetActionListener
{
  private Map<String, String> maps;
  private Map<String, StateController.Rule> rules = new HashMap();
  private ScheduleEvent scheduleEvent;

  public EventController()
  {
    this.rules.put("title", new StateController.Rule(VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_MAIN_EVENT).getFieldId(), getString(ResourceIdProvider.string.core_car_tts_EVENT_SAY_TITLE, new Object[0]), true));
    this.rules.put("time", new StateController.Rule("", null, false));
    this.rules.put("Confirm", new StateController.Rule(VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_EVENT_TITLE).getFieldId(), getString(ResourceIdProvider.string.core_car_event_save_confirm, new Object[0]), false));
    this.maps = new HashMap();
    this.maps.put("Value", "title");
  }

  private void save()
  {
    ((ScheduleAppointmentAction)getAction(DMActionType.SCHEDULE_APPOINTMENT, ScheduleAppointmentAction.class)).event(this.scheduleEvent).queue();
    sendAcceptedText();
  }

  private void sendAcceptedText()
  {
    if (this.scheduleEvent != null)
    {
      String str = null;
      if (this.scheduleEvent.getAttendeeNames() != null)
        str = this.scheduleEvent.getAttendeeNames().toString();
      sendAcceptedText(new AddEventAcceptedText(this.scheduleEvent.getTitle(), this.scheduleEvent.getBeginDate(), this.scheduleEvent.getBeginTime(), null, null, null, str, null));
    }
  }

  public void actionSuccess()
  {
    super.actionSuccess();
    unified().showSystemTurn(ResourceIdProvider.string.core_car_event_saved);
    UserLoggingEngine.getInstance().landingPageAction("event saved", null, true);
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("event");
    if (paramVLAction.getName().equals("LPAction"));
    do
      return false;
    while (!isAllSlotsFilled());
    this.scheduleEvent = new ScheduleEvent();
    this.scheduleEvent.setTitle(((StateController.Rule)this.rules.get("title")).getValue());
    Date localDate = new Date();
    localDate.setHours(1 + localDate.getHours());
    localDate.setMinutes(0);
    localDate.setSeconds(0);
    String str = ((StateController.Rule)this.rules.get("time")).getValue();
    if (StringUtils.isNullOrWhiteSpace(str))
      str = new SimpleDateFormat(DateUtil.CANONICAL_TIME_FORMAT).format(localDate);
    while (true)
    {
      this.scheduleEvent.setBegin(localDate.getTime());
      this.scheduleEvent.setEnd(DateUtil.getMillisFromTimeString(str) + DateUtil.ONE_HOUR);
      WidgetDecorator localWidgetDecorator = null;
      if (VLActionUtil.getParamBool(paramVLAction, "confirm", false, false))
        localWidgetDecorator = WidgetDecorator.makeConfirmButton();
      paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.ScheduleEvent, localWidgetDecorator, this.scheduleEvent, this);
      if (!hasConfirm())
        break;
      executeConfirm();
      break;
      localDate.setTime(DateUtil.getMillisFromTimeString(str));
    }
  }

  public Map<String, String> getRuleMappings()
  {
    return this.maps;
  }

  public Map<String, StateController.Rule> getRules()
  {
    return this.rules;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Default"))
    {
      getListener().interruptTurn();
      save();
    }
    while (true)
    {
      return;
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Cancel"))
      {
        getListener().interruptTurn();
        unified().showSystemTurn(getString(ResourceIdProvider.string.core_car_tts_TASK_CANCELLED, new Object[0]));
        reset();
        continue;
      }
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.View"))
      {
        getListener().interruptTurn();
        ((ViewScheduleAction)getAction(DMActionType.SCHEDULE_VIEW, ViewScheduleAction.class)).scheduleEvent((ScheduleEvent)paramObject).queue();
        continue;
      }
      if (!paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Edit"))
        continue;
      getListener().interruptTurn();
      ((EditScheduleAction)getAction(DMActionType.SCHEDULE_EDIT, EditScheduleAction.class)).scheduleEvent((ScheduleEvent)paramObject).queue();
    }
  }

  public boolean handleLPAction(VLAction paramVLAction)
  {
    int i = 0;
    String str = VLActionUtil.getParamString(paramVLAction, "Action", false);
    if ((str != null) && (("send".equalsIgnoreCase(str)) || ("save".equalsIgnoreCase(str))))
    {
      save();
      i = 1;
    }
    return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.EventController
 * JD-Core Version:    0.6.0
 */