package com.vlingo.midas.gui.widgets;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class ClockWidget$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.this$0.onTimeChanged();
    this.this$0.invalidate();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ClockWidget.1
 * JD-Core Version:    0.6.0
 */