package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.dialogmanager.DialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;

public class ActionReplyEvent extends DialogEvent
{
  private final String FIELDID_FIELD_NAME = "fieldid";
  private final String ID_FIELD_NAME = "id";
  private final String NAME = "action-reply";
  private final String SENDER_NAME_FIELD_NAME = "sender.name";
  private final String SENDER_PHONE_FIELD_NAME = "sender.phone";
  private String fieldId;
  private long id;
  private String senderName;
  private String senderPhone;

  public ActionReplyEvent(long paramLong, String paramString1, String paramString2, String paramString3)
  {
    super(false, true, true);
    this.id = paramLong;
    this.senderName = paramString1;
    this.senderPhone = paramString2;
    this.fieldId = paramString3;
  }

  public String getName()
  {
    return "action-reply";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    VLDialogEvent.Builder localBuilder = new VLDialogEvent.Builder("action-reply");
    localBuilder.eventField("id", String.valueOf(this.id));
    localBuilder.eventField("sender.name", this.senderName);
    localBuilder.eventField("sender.phone", this.senderPhone);
    localBuilder.eventField("fieldid", this.fieldId);
    return localBuilder.build();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.ActionReplyEvent
 * JD-Core Version:    0.6.0
 */