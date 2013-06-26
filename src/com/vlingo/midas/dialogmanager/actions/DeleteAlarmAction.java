package com.vlingo.midas.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import com.sec.android.app.clockpackage.alarm.AlarmProvider;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.DeleteAlarmInterface;
import com.vlingo.core.internal.util.Alarm;

public class DeleteAlarmAction extends DMAction
  implements DeleteAlarmInterface
{
  private static final String INTENT_DELETE_ALARM_ACTION = "com.sec.android.clockpackage.DELETE_ALARM";
  private static final String INTENT_DELETE_ALARM_EXTRA = "listitemId";
  private Alarm alarm;

  public DeleteAlarmAction alarm(Alarm paramAlarm)
  {
    this.alarm = paramAlarm;
    return this;
  }

  public void deleteAlarm()
  {
    Intent localIntent = new Intent("com.sec.android.clockpackage.DELETE_ALARM");
    localIntent.putExtra("listitemId", this.alarm.getId());
    getContext().sendBroadcast(localIntent);
  }

  protected void execute()
  {
    if (this.alarm != null)
    {
      deleteAlarm();
      getListener().actionSuccess();
      AlarmProvider.enableNextAlert(getContext());
      getContext().sendBroadcast(new Intent("com.samsung.sec.android.clockpackage.alarm.NOTIFY_ALARM_CHANGE"));
    }
    while (true)
    {
      return;
      getListener().actionFail("No alarm to delete");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.DeleteAlarmAction
 * JD-Core Version:    0.6.0
 */