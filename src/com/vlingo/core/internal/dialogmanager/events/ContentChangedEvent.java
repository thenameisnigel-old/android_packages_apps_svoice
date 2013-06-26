package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.dialogmanager.DialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;

public class ContentChangedEvent extends DialogEvent
{
  private final String NAME = "content-changed";
  private String content;
  private String type;

  public ContentChangedEvent(String paramString1, String paramString2)
  {
    super(false, true, true);
    this.type = paramString1;
    this.content = paramString2;
  }

  public String getName()
  {
    return this.type;
  }

  public VLDialogEvent getVLDialogEvent()
  {
    VLDialogEvent.Builder localBuilder = new VLDialogEvent.Builder("content-changed");
    localBuilder.eventField(this.type, this.content);
    return localBuilder.build();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.ContentChangedEvent
 * JD-Core Version:    0.6.0
 */