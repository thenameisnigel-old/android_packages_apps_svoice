package com.vlingo.core.internal.dialogmanager.actions;

import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.schedule.ScheduleUtilException;

public class DeleteAppointmentAction extends DMAction
{
  private ScheduleEvent event;

  public DeleteAppointmentAction event(ScheduleEvent paramScheduleEvent)
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
        ScheduleUtil.deleteEvent(getContext(), this.event.getID());
        getListener().actionSuccess();
        return;
      }
      catch (ScheduleUtilException localScheduleUtilException)
      {
        getListener().actionFail("Unable to delete event: " + localScheduleUtilException.getLocalizedMessage());
        continue;
      }
      getListener().actionFail("No event to delete");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.DeleteAppointmentAction
 * JD-Core Version:    0.6.0
 */