package com.vlingo.core.internal.dialogmanager.vvs.handlers.delete;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.DeleteAppointmentAction;
import com.vlingo.core.internal.dialogmanager.events.ActionCancelledEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.List;

public class ShowDeleteAppointmentHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private ScheduleEvent event = null;

  private void deleteAppointment()
  {
    ((DeleteAppointmentAction)getAction(DMActionType.DELETE_APPOINTMENT, DeleteAppointmentAction.class)).event(this.event).queue();
  }

  private ScheduleEvent getEvent(int paramInt)
    throws InvalidParameterException
  {
    List localList = (List)getListener().getState(DialogDataType.CALENDAR_MATCHES);
    try
    {
      ScheduleEvent localScheduleEvent = (ScheduleEvent)localList.get(paramInt);
      return localScheduleEvent;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
    }
    throw new InvalidParameterException("Schedule Index passed in is not in the cache");
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
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
    throws InvalidParameterException
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.event = getEvent(VLActionUtil.getParamInt(paramVLAction, "id", -1, true));
    UserLoggingEngine.getInstance().landingPageViewed("appointment");
    WidgetDecorator localWidgetDecorator = null;
    if (VLActionUtil.getParamBool(paramVLAction, "confirm", false, false))
      localWidgetDecorator = WidgetDecorator.makeConfirmButton();
    paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.DeleteEvent, localWidgetDecorator, this.event, this);
    if (VLActionUtil.getParamBool(paramVLAction, "execute", false, false))
      deleteAppointment();
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
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.delete.ShowDeleteAppointmentHandler
 * JD-Core Version:    0.6.0
 */