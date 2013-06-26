package com.vlingo.core.internal.dialogmanager.actions.interfaces;

import com.vlingo.core.internal.util.Alarm;

public abstract interface ModifyAlarmInterface extends ActionInterface
{
  public abstract ModifyAlarmInterface modified(Alarm paramAlarm);

  public abstract ModifyAlarmInterface original(Alarm paramAlarm);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.interfaces.ModifyAlarmInterface
 * JD-Core Version:    0.6.0
 */