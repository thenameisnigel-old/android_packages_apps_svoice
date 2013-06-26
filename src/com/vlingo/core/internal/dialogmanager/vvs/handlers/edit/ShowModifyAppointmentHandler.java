package com.vlingo.core.internal.dialogmanager.vvs.handlers.edit;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.ModifyAppointmentAction;
import com.vlingo.core.internal.dialogmanager.events.ActionCancelledEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.util.EventUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.EditEventAcceptedText;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

public class ShowModifyAppointmentHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private ScheduleEvent modifiedEvent;
  private ScheduleEvent originalEvent;

  private ScheduleEvent getEvent(int paramInt)
    throws InvalidParameterException
  {
    List localList = (List)getListener().getState(DialogDataType.CALENDAR_MATCHES);
    if (localList != null);
    try
    {
      return (ScheduleEvent)localList.get(paramInt);
      throw new InvalidParameterException("There is no events in cache");
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
    }
    throw new InvalidParameterException("Schedule Index passed in is not in the cache");
  }

  private void sendAcceptedText()
  {
    if ((this.modifiedEvent != null) && (this.originalEvent != null))
    {
      String str1 = null;
      String str2 = null;
      if (this.modifiedEvent.getAttendeeNames() != null)
        str1 = this.modifiedEvent.getAttendeeNames().toString();
      if (this.originalEvent.getAttendeeNames() != null)
        str2 = this.originalEvent.getAttendeeNames().toString();
      sendAcceptedText(new EditEventAcceptedText(this.modifiedEvent.getTitle(), this.modifiedEvent.getBeginDate(), this.modifiedEvent.getBeginTime(), this.modifiedEvent.getEndTime(), String.valueOf(this.modifiedEvent.getDuration()), this.modifiedEvent.getLocation(), str1, null, this.originalEvent.getTitle(), this.originalEvent.getBeginDate(), this.originalEvent.getBeginTime(), this.originalEvent.getEndTime(), String.valueOf(this.originalEvent.getDuration()), this.originalEvent.getLocation(), str2, null));
    }
  }

  private void storeEvent()
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(this.modifiedEvent);
    getListener().storeState(DialogDataType.CALENDAR_MATCHES, localLinkedList);
  }

  private void updateAppointment()
  {
    ((ModifyAppointmentAction)getAction(DMActionType.MODIFY_APPOINTMENT, ModifyAppointmentAction.class)).original(this.originalEvent).modified(this.modifiedEvent).queue();
    sendAcceptedText();
  }

  public void actionFail(String paramString)
  {
    ActionFailedEvent localActionFailedEvent = new ActionFailedEvent(paramString);
    getListener().sendEvent(localActionFailedEvent, this.turn);
  }

  public void actionSuccess()
  {
    ActionCompletedEvent localActionCompletedEvent = new ActionCompletedEvent();
    getListener().sendEvent(localActionCompletedEvent, this.turn);
    storeEvent();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
    throws InvalidParameterException
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.originalEvent = getEvent(VLActionUtil.getParamInt(paramVLAction, "id", -1, true));
    List localList = (List)(List)getListener().getState(DialogDataType.CONTACT_MATCHES);
    this.modifiedEvent = EventUtil.mergeChanges(paramVLAction, this.originalEvent, localList);
    UserLoggingEngine.getInstance().landingPageViewed("appointment");
    WidgetDecorator localWidgetDecorator = null;
    if (VLActionUtil.getParamBool(paramVLAction, "confirm", false, false))
      localWidgetDecorator = WidgetDecorator.makeConfirmButton();
    paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.EditEvent, localWidgetDecorator, this.modifiedEvent, this);
    if (VLActionUtil.getParamBool(paramVLAction, "execute", false, false))
      updateAppointment();
    return false;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Default"))
    {
      ActionConfirmedEvent localActionConfirmedEvent = new ActionConfirmedEvent();
      getListener().sendEvent(localActionConfirmedEvent, this.turn);
    }
    while (true)
    {
      return;
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Cancel"))
      {
        ActionCancelledEvent localActionCancelledEvent = new ActionCancelledEvent();
        getListener().sendEvent(localActionCancelledEvent, this.turn);
        continue;
      }
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.edit.ShowModifyAppointmentHandler
 * JD-Core Version:    0.6.0
 */