package com.vlingo.core.internal.safereader;

import android.content.Context;

public abstract interface ISafeReaderAlertSource
{
  public abstract String getName();

  public abstract void onStart(Context paramContext, ISafeReaderAlertChangeListener paramISafeReaderAlertChangeListener);

  public abstract void onStop(Context paramContext);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereader.ISafeReaderAlertSource
 * JD-Core Version:    0.6.0
 */