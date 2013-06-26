package com.vlingo.core.internal.dialogmanager.tasks;

import com.vlingo.core.internal.dialogmanager.actions.interfaces.ActionInterface;
import com.vlingo.core.internal.util.TaskQueue.Task;

public class ActionTask extends TaskQueue.Task
{
  private final ActionInterface action;

  public ActionTask(ActionInterface paramActionInterface)
  {
    this.action = paramActionInterface;
  }

  protected void onAborted()
  {
    this.action.abort();
  }

  public void run()
  {
    this.action.readyToExec();
    notifyFinished();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.tasks.ActionTask
 * JD-Core Version:    0.6.0
 */