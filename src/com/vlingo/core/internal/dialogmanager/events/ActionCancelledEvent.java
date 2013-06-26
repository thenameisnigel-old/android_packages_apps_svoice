package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.dialogmanager.DialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;

public class ActionCancelledEvent extends DialogEvent
{
  private final String NAME = "action-cancelled";

  public ActionCancelledEvent()
  {
    super(true, true, true);
  }

  public String getName()
  {
    return "action-cancelled";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    return new VLDialogEvent.Builder("action-cancelled").build();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.ActionCancelledEvent
 * JD-Core Version:    0.6.0
 */