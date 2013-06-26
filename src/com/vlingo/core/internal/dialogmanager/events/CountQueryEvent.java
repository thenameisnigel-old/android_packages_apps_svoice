package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.sdk.recognition.dialog.VLDialogEvent.Builder;
import java.util.ArrayList;
import java.util.List;

public abstract class CountQueryEvent<T> extends QueryEvent
{
  private final List<T> items;
  private final int offset;
  private final int totalCount;

  protected CountQueryEvent()
  {
    this.items = new ArrayList();
    this.offset = 0;
    this.totalCount = 0;
  }

  protected CountQueryEvent(List<T> paramList, int paramInt1, int paramInt2)
  {
    this.items = paramList;
    this.offset = paramInt1;
    this.totalCount = paramInt2;
  }

  public List<T> getItems()
  {
    return this.items;
  }

  public int getOffset()
  {
    return this.offset;
  }

  public int getTotalCount()
  {
    return this.totalCount;
  }

  public boolean isMeaningful()
  {
    if ((getItems() != null) && (!getItems().isEmpty()));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected void writeTotalCount(VLDialogEvent.Builder paramBuilder)
  {
    writeTotalCount(paramBuilder, getTotalCount());
  }

  protected void writeTotalCount(VLDialogEvent.Builder paramBuilder, int paramInt)
  {
    paramBuilder.eventField("matchcount", String.valueOf(paramInt));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.CountQueryEvent
 * JD-Core Version:    0.6.0
 */