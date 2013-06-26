package com.vlingo.midas.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ModifyAlarmInterface;
import com.vlingo.core.internal.util.Alarm;

public class ModifyAlarmAction extends DMAction
  implements ModifyAlarmInterface
{
  private static final String INTENT_ALARM_ACTIVE = "alarm_activate";
  private static final String INTENT_EDIT_ALARM_ACTION = "com.sec.android.clockpackage.DIRECT_EDIT_ALARM";
  private static final String INTENT_EDIT_ALARM_EXTRA = "listitemId";
  private Alarm modifiedAlarm;
  private Alarm origAlarm;

  protected void execute()
  {
    if (this.modifiedAlarm != null)
    {
      storeModifiedAlarm();
      getListener().actionSuccess();
      getContext().sendBroadcast(new Intent("com.samsung.sec.android.clockpackage.alarm.NOTIFY_ALARM_CHANGE"));
    }
    while (true)
    {
      return;
      getListener().actionFail("No alarm to update");
    }
  }

  public ModifyAlarmAction modified(Alarm paramAlarm)
  {
    this.modifiedAlarm = paramAlarm;
    return this;
  }

  public ModifyAlarmAction original(Alarm paramAlarm)
  {
    this.origAlarm = paramAlarm;
    return this;
  }

  public void storeModifiedAlarm()
  {
    Intent localIntent = new Intent("com.sec.android.clockpackage.DIRECT_EDIT_ALARM");
    localIntent.putExtra("listitemId", this.origAlarm.getId());
    localIntent.putExtra("android.intent.extra.alarm.HOUR", this.modifiedAlarm.getHour());
    localIntent.putExtra("android.intent.extra.alarm.MINUTES", this.modifiedAlarm.getMinute());
    localIntent.putExtra("alarm_activate", this.modifiedAlarm.isActive());
    localIntent.putExtra("alarm_repeat", this.modifiedAlarm.getDayMask());
    if (this.modifiedAlarm.isWeeklyRepeating());
    for (int i = 1; ; i = 0)
    {
      localIntent.putExtra("alarm_everyweekrepeat", i);
      getContext().sendBroadcast(localIntent);
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.ModifyAlarmAction
 * JD-Core Version:    0.6.0
 */