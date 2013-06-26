package com.vlingo.core.internal.safereaderimpl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.safereader.SafeReaderProxy;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ClientSuppliedValues;

public class BootStartSaferReaderReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool = Settings.getBoolean("safereader_start_on_boot", true);
    SafeReaderProxy.safeReadingInit(new SafeReaderEngine(SafeReaderProxy.getContext()));
    if (bool)
    {
      ClientSuppliedValues.releaseForegroundFocus(DialogFlow.getInstance());
      if ((ClientSuppliedValues.shouldIncomingMessagesReadout()) && (Settings.getBoolean("tos_accepted", false)) && (Settings.getBoolean("iux_complete", false)))
        SafeReaderProxy.startSafeReading();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereaderimpl.BootStartSaferReaderReceiver
 * JD-Core Version:    0.6.0
 */