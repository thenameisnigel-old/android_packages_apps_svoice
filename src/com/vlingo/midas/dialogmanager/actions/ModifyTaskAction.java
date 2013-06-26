package com.vlingo.midas.dialogmanager.actions;

import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ModifyTaskInterface;
import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.core.internal.schedule.ScheduleUtil;
import com.vlingo.core.internal.schedule.ScheduleUtilException;

public class ModifyTaskAction extends DMAction
  implements ModifyTaskInterface
{
  private ScheduleTask modifiedTask;
  private ScheduleTask origTask;

  protected void execute()
  {
    if (this.modifiedTask != null);
    while (true)
    {
      try
      {
        ScheduleUtil.updateTask(getContext(), this.origTask, this.modifiedTask);
        getListener().actionSuccess();
        return;
      }
      catch (ScheduleUtilException localScheduleUtilException)
      {
        getListener().actionFail("Unable to modify task: " + localScheduleUtilException.getLocalizedMessage());
        continue;
      }
      getListener().actionFail("No task to update");
    }
  }

  public ModifyTaskAction modified(ScheduleTask paramScheduleTask)
  {
    this.modifiedTask = paramScheduleTask;
    return this;
  }

  public ModifyTaskAction original(ScheduleTask paramScheduleTask)
  {
    this.origTask = paramScheduleTask;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.ModifyTaskAction
 * JD-Core Version:    0.6.0
 */