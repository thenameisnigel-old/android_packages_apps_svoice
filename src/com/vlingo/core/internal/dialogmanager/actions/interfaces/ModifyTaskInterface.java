package com.vlingo.core.internal.dialogmanager.actions.interfaces;

import com.vlingo.core.internal.schedule.ScheduleTask;

public abstract interface ModifyTaskInterface extends ActionInterface
{
  public abstract ModifyTaskInterface modified(ScheduleTask paramScheduleTask);

  public abstract ModifyTaskInterface original(ScheduleTask paramScheduleTask);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.interfaces.ModifyTaskInterface
 * JD-Core Version:    0.6.0
 */