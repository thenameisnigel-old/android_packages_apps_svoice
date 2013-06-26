package com.vlingo.midas.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.sec.android.app.clockpackage.alarm.AlarmProvider;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SetAlarmInterface;
import com.vlingo.core.internal.util.Alarm;

public class SetAlarmAndReminderAction extends DMAction
  implements SetAlarmInterface
{
  private static final String INTENT_ADD_ALARM_ACTION = "com.sec.android.clockpackage.ADD_ALARM";
  private Alarm alarm;

  public static void addAlarm(Context paramContext, Alarm paramAlarm)
  {
    Intent localIntent;
    if (paramAlarm != null)
    {
      localIntent = new Intent("com.sec.android.clockpackage.ADD_ALARM");
      localIntent.putExtra("android.intent.extra.alarm.MESSAGE", paramAlarm.getName());
      localIntent.putExtra("android.intent.extra.alarm.HOUR", paramAlarm.getHour());
      localIntent.putExtra("android.intent.extra.alarm.MINUTES", paramAlarm.getMinute());
      localIntent.putExtra("alarm_repeat", paramAlarm.getDayMask());
      if (!paramAlarm.isWeeklyRepeating())
        break label95;
    }
    label95: for (int i = 1; ; i = 0)
    {
      localIntent.putExtra("alarm_everyweekrepeat", i);
      localIntent.putExtra("android.intent.extra.alarm.SKIP_UI", true);
      paramContext.sendBroadcast(localIntent);
      AlarmProvider.enableNextAlert(paramContext);
      return;
    }
  }

  public SetAlarmAndReminderAction alarm(Alarm paramAlarm)
  {
    this.alarm = paramAlarm;
    return this;
  }

  protected void execute()
  {
    if (this.alarm != null)
    {
      String str = getContext().getResources().getString(2131361859);
      this.alarm.setName(str);
    }
    while (true)
    {
      try
      {
        addAlarm(getContext(), this.alarm);
        getListener().actionSuccess();
        return;
      }
      catch (Exception localException)
      {
        getListener().actionFail("Unable to schedule alarm: " + localException.getLocalizedMessage());
        continue;
      }
      getListener().actionFail("No alarm to set");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.SetAlarmAndReminderAction
 * JD-Core Version:    0.6.0
 */