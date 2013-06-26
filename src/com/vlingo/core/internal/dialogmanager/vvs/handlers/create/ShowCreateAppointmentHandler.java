package com.vlingo.core.internal.dialogmanager.vvs.handlers.create;

import android.content.Intent;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.EditScheduleAction;
import com.vlingo.core.internal.dialogmanager.actions.ScheduleAppointmentAction;
import com.vlingo.core.internal.dialogmanager.actions.ViewScheduleAction;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SendEmailInterface;
import com.vlingo.core.internal.dialogmanager.events.ActionCancelledEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.events.ContentChangedEvent;
import com.vlingo.core.internal.dialogmanager.util.EventUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.recognition.acceptedtext.AddEventAcceptedText;
import com.vlingo.core.internal.recognition.acceptedtext.EmailAcceptedText;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.List;

public class ShowCreateAppointmentHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private final String DESCRIPTION = "description";
  private final String TITLE = "title";
  private ScheduleEvent event;

  private void createAppointment()
  {
    ((ScheduleAppointmentAction)getAction(DMActionType.SCHEDULE_APPOINTMENT, ScheduleAppointmentAction.class)).event(this.event).queue();
    sendAcceptedText();
  }

  private ScheduleEvent getScheduleEvent(VLAction paramVLAction)
  {
    return EventUtil.extractScheduleEvent(paramVLAction, (List)getListener().getState(DialogDataType.CONTACT_MATCHES));
  }

  private void sendAcceptedText()
  {
    if (this.event != null)
    {
      String str = null;
      if (this.event.getAttendeeNames() != null)
        str = this.event.getAttendeeNames().toString();
      sendAcceptedText(new AddEventAcceptedText(this.event.getTitle(), this.event.getBeginDate(), this.event.getBeginTime(), this.event.getEndTime(), String.valueOf(this.event.getDuration()), this.event.getLocation(), str, null));
    }
  }

  private void sendInvitations()
  {
    List localList = this.event.getContactDataList();
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      if (localIterator.hasNext())
      {
        ContactData localContactData = (ContactData)localIterator.next();
        SendEmailInterface localSendEmailInterface = ((SendEmailInterface)getAction(DMActionType.SEND_EMAIL, SendEmailInterface.class)).contact(localContactData).subject(this.event.getTitle()).message("content");
        ((DMAction)localSendEmailInterface).listener(new ShowCreateAppointmentHandler.1(this));
        localSendEmailInterface.queue();
        if (localContactData == null);
        for (String str = null; ; str = localContactData.address)
        {
          sendAcceptedText(new EmailAcceptedText(str, this.event.getTitle(), null));
          break;
        }
      }
    }
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
    sendInvitations();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
    throws InvalidParameterException
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("appointment");
    this.event = getScheduleEvent(paramVLAction);
    WidgetDecorator localWidgetDecorator = null;
    if (VLActionUtil.getParamBool(paramVLAction, "confirm", false, false))
      localWidgetDecorator = WidgetDecorator.makeConfirmButton();
    paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.ScheduleEvent, localWidgetDecorator, this.event, this);
    if (VLActionUtil.getParamBool(paramVLAction, "execute", false, false))
      createAppointment();
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
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.BodyChange"))
      {
        if (!paramIntent.hasExtra("title"))
          continue;
        ContentChangedEvent localContentChangedEvent = new ContentChangedEvent("title", VLActionUtil.extractParamString(paramIntent, "title"));
        getListener().queueEvent(localContentChangedEvent, this.turn);
        continue;
      }
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.View"))
      {
        getListener().interruptTurn();
        ((ViewScheduleAction)getAction(DMActionType.SCHEDULE_VIEW, ViewScheduleAction.class)).scheduleEvent((ScheduleEvent)paramObject).queue();
        continue;
      }
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Edit"))
      {
        getListener().interruptTurn();
        ((EditScheduleAction)getAction(DMActionType.SCHEDULE_EDIT, EditScheduleAction.class)).scheduleEvent((ScheduleEvent)paramObject).queue();
        continue;
      }
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.create.ShowCreateAppointmentHandler
 * JD-Core Version:    0.6.0
 */