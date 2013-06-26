package com.vlingo.core.internal.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.schedule.ScheduleEvent;

public class ViewScheduleAction extends DMAction
{
  private ScheduleEvent mScheduleEvent;

  protected void execute()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setClassName("com.android.calendar", "com.android.calendar.EventInfoActivity");
    localIntent.setType("vnd.android.cursor.item/event");
    localIntent.setData(Uri.parse(String.valueOf(this.mScheduleEvent.getEventID())));
    localIntent.putExtra("title", this.mScheduleEvent.getTitle());
    localIntent.putExtra("beginTime", this.mScheduleEvent.getBegin());
    localIntent.putExtra("endTime", this.mScheduleEvent.getEnd());
    getContext().startActivity(localIntent);
  }

  public void readyToExec()
  {
    execute();
  }

  public ViewScheduleAction scheduleEvent(ScheduleEvent paramScheduleEvent)
  {
    this.mScheduleEvent = paramScheduleEvent;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.ViewScheduleAction
 * JD-Core Version:    0.6.0
 */