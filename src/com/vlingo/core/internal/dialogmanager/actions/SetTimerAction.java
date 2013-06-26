package com.vlingo.core.internal.dialogmanager.actions;

import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;

public class SetTimerAction extends DMAction
{
  private String time;

  protected void execute()
  {
    if (this.time != null)
      getListener().actionSuccess();
  }

  public SetTimerAction time(String paramString)
  {
    this.time = paramString;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.SetTimerAction
 * JD-Core Version:    0.6.0
 */