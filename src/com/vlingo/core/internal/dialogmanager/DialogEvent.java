package com.vlingo.core.internal.dialogmanager;

import com.vlingo.sdk.recognition.dialog.VLDialogEvent;

public abstract class DialogEvent
{
  private final boolean preempt;
  private final boolean terminalState;
  private final boolean unique;
  private final boolean userInitiated;

  protected DialogEvent(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.terminalState = paramBoolean1;
    this.userInitiated = paramBoolean3;
    this.unique = true;
    this.preempt = paramBoolean2;
  }

  protected DialogEvent(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this.terminalState = paramBoolean1;
    this.userInitiated = paramBoolean2;
    this.unique = paramBoolean3;
    this.preempt = paramBoolean4;
  }

  public abstract String getName();

  public abstract VLDialogEvent getVLDialogEvent();

  public boolean isTerminalState()
  {
    return this.terminalState;
  }

  public boolean isUnique()
  {
    return this.unique;
  }

  public boolean isUserInitiated()
  {
    return this.userInitiated;
  }

  public boolean shouldStopTtsReco()
  {
    return this.preempt;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogEvent
 * JD-Core Version:    0.6.0
 */