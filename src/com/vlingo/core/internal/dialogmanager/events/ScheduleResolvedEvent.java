package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.schedule.ScheduleEvent;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;
import com.vlingo.sdk.recognition.dialog.VLDialogEventFieldGroup.Builder;
import java.util.Iterator;
import java.util.List;

public class ScheduleResolvedEvent extends CountQueryEvent<ScheduleEvent>
{
  private final String NAME = "appointment-resolved";

  public ScheduleResolvedEvent()
  {
  }

  public ScheduleResolvedEvent(List<ScheduleEvent> paramList, int paramInt1, int paramInt2)
  {
    super(paramList, paramInt1, paramInt2);
  }

  public String getName()
  {
    return "appointment-resolved";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    if (getItems() == null);
    VLDialogEvent.Builder localBuilder;
    for (VLDialogEvent localVLDialogEvent = null; ; localVLDialogEvent = localBuilder.build())
    {
      return localVLDialogEvent;
      localBuilder = new VLDialogEvent.Builder("appointment-resolved");
      writeTotalCount(localBuilder);
      VLDialogEventFieldGroup.Builder localBuilder1 = new VLDialogEventFieldGroup.Builder("appointments");
      int i = getOffset();
      Iterator localIterator1 = getItems().iterator();
      while (localIterator1.hasNext())
      {
        ScheduleEvent localScheduleEvent = (ScheduleEvent)localIterator1.next();
        VLDialogEventFieldGroup.Builder localBuilder2 = new VLDialogEventFieldGroup.Builder("appointment");
        localBuilder2.eventField("id", String.valueOf(i));
        i++;
        if (!StringUtils.isNullOrWhiteSpace(localScheduleEvent.getTitle()))
          localBuilder2.eventField("title", localScheduleEvent.getTitle());
        if (!StringUtils.isNullOrWhiteSpace(localScheduleEvent.getBeginTime()))
          localBuilder2.eventField("time", localScheduleEvent.getBeginTime());
        if (!StringUtils.isNullOrWhiteSpace(localScheduleEvent.getBeginDate()))
          localBuilder2.eventField("date", localScheduleEvent.getBeginDate());
        localBuilder2.eventField("all.day", Boolean.toString(localScheduleEvent.getAllDay()));
        if (localScheduleEvent.getDuration() > 0L)
          localBuilder2.eventField("duration", String.valueOf(localScheduleEvent.getDuration() / 60000L));
        if (!StringUtils.isNullOrWhiteSpace(localScheduleEvent.getLocation()))
          localBuilder2.eventField("location", localScheduleEvent.getLocation());
        List localList = localScheduleEvent.getAttendeeNames();
        int j = 0;
        if ((localList != null) && (localList.size() > 0))
        {
          VLDialogEventFieldGroup.Builder localBuilder3 = new VLDialogEventFieldGroup.Builder("invitees");
          Iterator localIterator2 = localList.iterator();
          while (localIterator2.hasNext())
          {
            String str = (String)localIterator2.next();
            VLDialogEventFieldGroup.Builder localBuilder4 = new VLDialogEventFieldGroup.Builder("invitee");
            localBuilder4.eventField("id", String.valueOf(j));
            j++;
            localBuilder4.eventField("name", str);
            localBuilder3.eventFieldGroup(localBuilder4.build());
          }
          localBuilder2.eventFieldGroup(localBuilder3.build());
        }
        localBuilder1.eventFieldGroup(localBuilder2.build());
      }
      localBuilder.eventFieldGroup(localBuilder1.build());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.ScheduleResolvedEvent
 * JD-Core Version:    0.6.0
 */