package com.vlingo.core.internal.lmtt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LMTTServiceBootStart extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Intent localIntent = new Intent(paramContext, LMTTService.class);
    localIntent.putExtra("com.vlingo.lmtt.extra.BOOT_START", true);
    if (paramContext.startService(localIntent) != null);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.lmtt.LMTTServiceBootStart
 * JD-Core Version:    0.6.0
 */