package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.util.Alarm;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;
import com.vlingo.sdk.recognition.dialog.VLDialogEventFieldGroup.Builder;
import java.util.Iterator;
import java.util.List;

public class AlarmResolvedEvent extends CountQueryEvent<Alarm>
{
  private final String NAME = "alarm-resolved";

  public AlarmResolvedEvent()
  {
  }

  public AlarmResolvedEvent(List<Alarm> paramList, int paramInt1, int paramInt2)
  {
    super(paramList, paramInt1, paramInt2);
  }

  public String getName()
  {
    return "alarm-resolved";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    if (getItems() == null);
    VLDialogEvent.Builder localBuilder;
    for (VLDialogEvent localVLDialogEvent = null; ; localVLDialogEvent = localBuilder.build())
    {
      return localVLDialogEvent;
      localBuilder = new VLDialogEvent.Builder("alarm-resolved");
      writeTotalCount(localBuilder);
      VLDialogEventFieldGroup.Builder localBuilder1 = new VLDialogEventFieldGroup.Builder("alarms");
      int i = getOffset();
      Iterator localIterator = getItems().iterator();
      while (localIterator.hasNext())
      {
        Alarm localAlarm = (Alarm)localIterator.next();
        VLDialogEventFieldGroup.Builder localBuilder2 = new VLDialogEventFieldGroup.Builder("alarm");
        localBuilder2.eventField("id", String.valueOf(i));
        i++;
        if (!StringUtils.isNullOrWhiteSpace(localAlarm.getTimeCanonical()))
          localBuilder2.eventField("time", localAlarm.getTimeCanonical());
        localBuilder2.eventField("set", String.valueOf(localAlarm.isActive()));
        localBuilder2.eventField("days", "" + Alarm.getDaysCanonical(localAlarm.getDayMask()));
        localBuilder2.eventField("repeat", String.valueOf(localAlarm.isWeeklyRepeating()));
        localBuilder1.eventFieldGroup(localBuilder2.build());
      }
      localBuilder.eventFieldGroup(localBuilder1.build());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.AlarmResolvedEvent
 * JD-Core Version:    0.6.0
 */