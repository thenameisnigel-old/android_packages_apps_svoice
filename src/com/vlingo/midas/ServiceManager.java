package com.vlingo.midas;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.lmtt.LMTTService;
import com.vlingo.core.internal.safereader.SafeReaderProxy;
import com.vlingo.core.internal.safereaderimpl.SafeReaderEngine;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;

public class ServiceManager
{
  private static ServiceManager smInstance;
  private boolean mSafeReaderInitialized = false;

  public static ServiceManager getInstance()
  {
    if (smInstance == null)
      smInstance = new ServiceManager();
    return smInstance;
  }

  private boolean startSafeReaderService(boolean paramBoolean)
  {
    if (!this.mSafeReaderInitialized)
    {
      SafeReaderProxy.init(VlingoApplication.getInstance().getApplicationContext());
      SafeReaderProxy.safeReadingInit(new SafeReaderEngine(SafeReaderProxy.getContext()));
      ClientSuppliedValues.getForegroundFocus(DialogFlow.getInstance());
      this.mSafeReaderInitialized = true;
    }
    if (ClientSuppliedValues.shouldIncomingMessagesReadout())
      SafeReaderProxy.startSafeReading();
    return true;
  }

  public boolean startAllServices(boolean paramBoolean)
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    Intent localIntent = new Intent(localContext, LMTTService.class);
    if (paramBoolean)
      localIntent.setAction("com.vlingo.lmtt.action.SEND_UPDATE");
    if (localContext.startService(localIntent) != null);
    for (int i = 1; ; i = 0)
      return 0x1 & i & startSafeReaderService(false);
  }

  public void stopSafereaderService(boolean paramBoolean)
  {
    SafeReaderProxy.stopSafeReading();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.ServiceManager
 * JD-Core Version:    0.6.0
 */