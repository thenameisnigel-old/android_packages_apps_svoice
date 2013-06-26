package com.vlingo.core.internal.dialogmanager.actions;

import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.schedule.ScheduleUtilException;

public class ModifyAppointmentAction extends DMAction
{
  private ScheduleEvent modifiedEvent;
  private ScheduleEvent origEvent;

  protected void execute()
  {
    if (this.modifiedEvent != null);
    while (true)
    {
      try
      {
        ScheduleUtil.updateEvent(getContext(), this.origEvent, this.modifiedEvent);
        getListener().actionSuccess();
        return;
      }
      catch (ScheduleUtilException localScheduleUtilException)
      {
        getListener().actionFail("Unable to modify event: " + localScheduleUtilException.getLocalizedMessage());
        continue;
      }
      getListener().actionFail("No event to update");
    }
  }

  public ModifyAppointmentAction modified(ScheduleEvent paramScheduleEvent)
  {
    this.modifiedEvent = paramScheduleEvent;
    return this;
  }

  public ModifyAppointmentAction original(ScheduleEvent paramScheduleEvent)
  {
    this.origEvent = paramScheduleEvent;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.ModifyAppointmentAction
 * JD-Core Version:    0.6.0
 */