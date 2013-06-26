package com.vlingo.core.internal.safereader;

import java.util.LinkedList;

public abstract interface ISafeReaderAlertChangeListener
{
  public abstract void onNewSafeReaderAlert(LinkedList<? extends SafeReaderAlert> paramLinkedList);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereader.ISafeReaderAlertChangeListener
 * JD-Core Version:    0.6.0
 */