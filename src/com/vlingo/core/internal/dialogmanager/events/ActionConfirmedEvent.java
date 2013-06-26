package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.dialogmanager.DialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;

public class ActionConfirmedEvent extends DialogEvent
{
  private final String NAME = "action-confirmed";

  public ActionConfirmedEvent()
  {
    super(false, true, true);
  }

  public String getName()
  {
    return "action-confirmed";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    return new VLDialogEvent.Builder("action-confirmed").build();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent
 * JD-Core Version:    0.6.0
 */