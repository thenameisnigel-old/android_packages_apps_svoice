package com.vlingo.core.internal.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.text.format.Time;
import android.widget.Toast;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SetAlarmInterface;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.util.Alarm;
import com.vlingo.core.internal.util.StringUtils;
import java.util.TimeZone;

public class SetAlarmAction extends DMAction
  implements SetAlarmInterface
{
  private String time;

  public SetAlarmAction alarm(Alarm paramAlarm)
  {
    this.time = paramAlarm.getTimeCanonical();
    return this;
  }

  protected void execute()
  {
    if (!StringUtils.isNullOrWhiteSpace(this.time))
    {
      String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_default_alarm_title);
      Intent localIntent = new Intent("android.intent.action.SET_ALARM");
      Time localTime = new Time();
      long l1 = DateUtil.getTimeFromTimeString(this.time, localTime, true);
      long l2 = (l1 + TimeZone.getDefault().getOffset(l1)) / 1000L;
      int i = (int)(l2 % 3600L / 60L);
      int j = (int)(l2 / 3600L);
      localIntent.putExtra("android.intent.extra.alarm.MESSAGE", str);
      localIntent.putExtra("android.intent.extra.alarm.HOUR", j);
      localIntent.putExtra("android.intent.extra.alarm.MINUTES", i);
      if (Integer.parseInt(Build.VERSION.SDK) >= 11)
        localIntent.putExtra("android.intent.extra.alarm.SKIP_UI", true);
      getContext().startActivity(localIntent);
      getListener().actionSuccess();
    }
    while (true)
    {
      return;
      Toast.makeText(getContext(), "execute(), no time so not sending", 1).show();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.SetAlarmAction
 * JD-Core Version:    0.6.0
 */