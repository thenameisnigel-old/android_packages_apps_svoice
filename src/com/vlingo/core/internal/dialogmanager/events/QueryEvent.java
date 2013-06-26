package com.vlingo.core.internal.dialogmanager.events;

import com.vlingo.core.internal.dialogmanager.DialogEvent;

public abstract class QueryEvent extends DialogEvent
{
  protected QueryEvent()
  {
    super(false, false, false, false);
  }

  public abstract boolean isMeaningful();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.events.QueryEvent
 * JD-Core Version:    0.6.0
 */