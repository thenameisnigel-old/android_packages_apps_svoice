package com.vlingo.core.internal.safereader;

import java.util.LinkedList;

public abstract interface ISafeReaderAlertHandler
{
  public abstract void handleAlert(LinkedList<? extends SafeReaderAlert> paramLinkedList);

  public abstract boolean isSilentMode();

  public abstract long readoutDelay();

  public abstract void setSilentMode(boolean paramBoolean);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereader.ISafeReaderAlertHandler
 * JD-Core Version:    0.6.0
 */