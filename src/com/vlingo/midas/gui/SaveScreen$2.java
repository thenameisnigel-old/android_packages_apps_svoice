package com.vlingo.midas.gui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class SaveScreen$2 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if (str.equals("android.intent.action.TIMEZONE_CHANGED"))
      SaveScreen.access$100(this.this$0);
    if (str.equals("android.intent.action.DATE_CHANGED"))
      SaveScreen.access$100(this.this$0);
    if (str.equals("android.intent.action.TIME_TICK"))
      SaveScreen.access$100(this.this$0);
    if ((paramIntent.getAction().equals("android.intent.action.DOCK_EVENT")) && (paramIntent.getIntExtra("android.intent.extra.DOCK_STATE", 0) == 0))
      this.this$0.finish();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.SaveScreen.2
 * JD-Core Version:    0.6.0
 */