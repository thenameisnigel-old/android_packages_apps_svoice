package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.schedule.ScheduleTask;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;
import com.vlingo.sdk.recognition.dialog.VLDialogEventFieldGroup.Builder;
import java.util.Iterator;
import java.util.List;

public class TaskResolvedEvent extends CountQueryEvent<ScheduleTask>
{
  private final String NAME = "task-resolved";

  public TaskResolvedEvent()
  {
  }

  public TaskResolvedEvent(List<ScheduleTask> paramList, int paramInt1, int paramInt2)
  {
    super(paramList, paramInt1, paramInt2);
  }

  public String getName()
  {
    return "task-resolved";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    if (getItems() == null);
    VLDialogEvent.Builder localBuilder;
    for (VLDialogEvent localVLDialogEvent = null; ; localVLDialogEvent = localBuilder.build())
    {
      return localVLDialogEvent;
      localBuilder = new VLDialogEvent.Builder("task-resolved");
      writeTotalCount(localBuilder);
      VLDialogEventFieldGroup.Builder localBuilder1 = new VLDialogEventFieldGroup.Builder("tasks");
      int i = getOffset();
      Iterator localIterator = getItems().iterator();
      while (localIterator.hasNext())
      {
        ScheduleTask localScheduleTask = (ScheduleTask)localIterator.next();
        VLDialogEventFieldGroup.Builder localBuilder2 = new VLDialogEventFieldGroup.Builder("task");
        localBuilder2.eventField("id", String.valueOf(i));
        i++;
        if (!StringUtils.isNullOrWhiteSpace(localScheduleTask.getTitle()))
          localBuilder2.eventField("title", localScheduleTask.getTitle());
        if (!StringUtils.isNullOrWhiteSpace(localScheduleTask.getBeginDate()))
          localBuilder2.eventField("date", localScheduleTask.getBeginDate());
        localBuilder1.eventFieldGroup(localBuilder2.build());
      }
      localBuilder.eventFieldGroup(localBuilder1.build());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.TaskResolvedEvent
 * JD-Core Version:    0.6.0
 */