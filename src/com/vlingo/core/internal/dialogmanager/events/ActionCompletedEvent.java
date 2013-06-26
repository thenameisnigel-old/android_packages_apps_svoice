package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.dialogmanager.DialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;

public class ActionCompletedEvent extends DialogEvent
{
  private final String NAME = "action-completed";

  public ActionCompletedEvent()
  {
    super(true, false, false);
  }

  public String getName()
  {
    return "action-completed";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    return new VLDialogEvent.Builder("action-completed").build();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent
 * JD-Core Version:    0.6.0
 */