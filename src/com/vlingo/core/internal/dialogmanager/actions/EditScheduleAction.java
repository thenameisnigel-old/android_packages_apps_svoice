package com.vlingo.core.internal.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.schedule.ScheduleEvent;

public class EditScheduleAction extends DMAction
{
  private ScheduleEvent mScheduleEvent;

  protected void execute()
  {
    Intent localIntent = new Intent("android.intent.action.EDIT");
    localIntent.setData(this.mScheduleEvent.getNewEventUri());
    if (this.mScheduleEvent.getNewEventUri() == null)
      localIntent.setType("vnd.android.cursor.item/event");
    localIntent.putExtra("action_event_type", 1);
    localIntent.putExtra("title", this.mScheduleEvent.getTitle());
    localIntent.putExtra("beginTime", this.mScheduleEvent.getBegin());
    localIntent.putExtra("endTime", this.mScheduleEvent.getEnd());
    getContext().startActivity(localIntent);
  }

  public void readyToExec()
  {
    execute();
  }

  public EditScheduleAction scheduleEvent(ScheduleEvent paramScheduleEvent)
  {
    this.mScheduleEvent = paramScheduleEvent;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.EditScheduleAction
 * JD-Core Version:    0.6.0
 */