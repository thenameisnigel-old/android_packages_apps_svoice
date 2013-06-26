package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.dialogmanager.DialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;

public class ActionFailedEvent extends DialogEvent
{
  private final String NAME = "action-failed";
  private final String reason;

  public ActionFailedEvent(String paramString)
  {
    super(true, false, false);
    this.reason = paramString;
  }

  public String getName()
  {
    return "action-failed";
  }

  public String getReason()
  {
    return this.reason;
  }

  public VLDialogEvent getVLDialogEvent()
  {
    VLDialogEvent.Builder localBuilder = new VLDialogEvent.Builder("action-failed");
    localBuilder.eventField("reason", this.reason);
    return localBuilder.build();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent
 * JD-Core Version:    0.6.0
 */