package com.vlingo.midas.gui.widgets;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class DriveTimeWidget$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.intent.action.TIMEZONE_CHANGED"))
      paramIntent.getStringExtra("time-zone");
    this.this$0.onTimeChanged();
    DriveTimeWidget.access$000(this.this$0);
    this.this$0.invalidate();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DriveTimeWidget.1
 * JD-Core Version:    0.6.0
 */