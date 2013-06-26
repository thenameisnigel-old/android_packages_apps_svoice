package com.vlingo.midas.dialogmanager.actions;

import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.DeleteTaskInterface;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.schedule.ScheduleUtilException;

public class DeleteTaskAction extends DMAction
  implements DeleteTaskInterface
{
  private ScheduleTask task;

  protected void execute()
  {
    if (this.task != null);
    while (true)
    {
      try
      {
        ScheduleUtil.deleteTask(getContext(), this.task.getID());
        getListener().actionSuccess();
        return;
      }
      catch (ScheduleUtilException localScheduleUtilException)
      {
        getListener().actionFail("Unable to delete task: " + localScheduleUtilException.getLocalizedMessage());
        continue;
      }
      getListener().actionFail("No task to delete");
    }
  }

  public DeleteTaskAction task(ScheduleTask paramScheduleTask)
  {
    this.task = paramScheduleTask;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.DeleteTaskAction
 * JD-Core Version:    0.6.0
 */