package com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve;

import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.actions.EditScheduleAction;
import com.vlingo.core.internal.dialogmanager.actions.ViewScheduleAction;
import com.vlingo.core.internal.dialogmanager.events.ChoiceSelectedEvent;
import com.vlingo.core.internal.dialogmanager.util.DialogDataUtil.ChoiceListUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;

public class ShowAppointmentsHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private DialogDataUtil.ChoiceListUtil<ScheduleEvent> listUtil = new DialogDataUtil.ChoiceListUtil();

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    VLActionUtil.getParamString(paramVLAction, "choices", true);
    UserLoggingEngine.getInstance().landingPageViewed("appointment");
    this.listUtil.showChoiceListWidget(paramVVSActionHandlerListener, paramVLAction, DialogDataType.CALENDAR_MATCHES, WidgetUtil.WidgetKey.ShowEventChoices, WidgetUtil.WidgetKey.ShowEvent, this, null);
    return false;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Choice"))
    {
      ChoiceSelectedEvent localChoiceSelectedEvent = new ChoiceSelectedEvent(this.listUtil.getIdOfSelection(paramIntent));
      getListener().sendEvent(localChoiceSelectedEvent, this.turn);
    }
    while (true)
    {
      return;
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
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ShowAppointmentsHandler
 * JD-Core Version:    0.6.0
 */