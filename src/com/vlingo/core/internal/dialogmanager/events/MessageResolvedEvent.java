package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;
import com.vlingo.sdk.recognition.dialog.VLDialogEventFieldGroup.Builder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MessageResolvedEvent extends CountQueryEvent<SMSMMSAlert>
{
  private final String ID_FIELD_NAME = "id";
  private final String MESSAGES_FIELD_GROUP_NAME = "messages";
  private final String MESSAGE_FILED_GROUP_NAME = "message";
  private final String NAME = "message-resolved";
  private final String SENDER_NAME_FIELD_NAME = "sender.name";
  private final String SENDER_PHHONE_FIELD_NAME = "sender.phone";

  public MessageResolvedEvent()
  {
  }

  public MessageResolvedEvent(SMSMMSAlert paramSMSMMSAlert)
  {
    this(new ArrayList(Arrays.asList(arrayOfSMSMMSAlert)));
  }

  public MessageResolvedEvent(List<SMSMMSAlert> paramList)
  {
  }

  public String getName()
  {
    return "message-resolved";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    if (getItems() == null);
    VLDialogEvent.Builder localBuilder;
    for (VLDialogEvent localVLDialogEvent = null; ; localVLDialogEvent = localBuilder.build())
    {
      return localVLDialogEvent;
      localBuilder = new VLDialogEvent.Builder("message-resolved");
      writeTotalCount(localBuilder);
      VLDialogEventFieldGroup.Builder localBuilder1 = new VLDialogEventFieldGroup.Builder("messages");
      Iterator localIterator = getItems().iterator();
      while (localIterator.hasNext())
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localIterator.next();
        VLDialogEventFieldGroup.Builder localBuilder2 = new VLDialogEventFieldGroup.Builder("message");
        localBuilder2.eventField("id", String.valueOf(localSMSMMSAlert.getId()));
        if (!StringUtils.isNullOrWhiteSpace(localSMSMMSAlert.getSenderDisplayName()))
          localBuilder2.eventField("sender.name", localSMSMMSAlert.getSenderDisplayName());
        localBuilder2.eventField("sender.phone", localSMSMMSAlert.getAddress());
        localBuilder1.eventFieldGroup(localBuilder2.build());
      }
      localBuilder.eventFieldGroup(localBuilder1.build());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.MessageResolvedEvent
 * JD-Core Version:    0.6.0
 */