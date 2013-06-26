package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.dialogmanager.DialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;

public class ChoiceSelectedEvent extends DialogEvent
{
  private final String NAME = "choice-selected";
  private final String id;

  public ChoiceSelectedEvent(String paramString)
  {
    super(false, true, true);
    this.id = paramString;
  }

  public String getName()
  {
    return "choice-selected";
  }

  public VLDialogEvent getVLDialogEvent()
  {
    VLDialogEvent.Builder localBuilder = new VLDialogEvent.Builder("choice-selected");
    localBuilder.eventField("choice-uid", this.id);
    return localBuilder.build();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.ChoiceSelectedEvent
 * JD-Core Version:    0.6.0
 */