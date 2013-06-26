package com.vlingo.core.internal.dialogmanager.actions;

import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.schedule.ScheduleUtilException;

public class ScheduleAppointmentAction extends DMAction
{
  private ScheduleEvent event;

  public ScheduleAppointmentAction event(ScheduleEvent paramScheduleEvent)
  {
    this.event = paramScheduleEvent;
    return this;
  }

  protected void execute()
  {
    if (this.event != null);
    while (true)
    {
      try
      {
        ScheduleUtil.addEvent(getContext(), this.event);
        getListener().actionSuccess();
        return;
      }
      catch (ScheduleUtilException localScheduleUtilException)
      {
        getListener().actionFail("Unable to schedule event: " + localScheduleUtilException.getLocalizedMessage());
        continue;
      }
      getListener().actionFail("No event to schedule");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.ScheduleAppointmentAction
 * JD-Core Version:    0.6.0
 */