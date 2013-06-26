package com.vlingo.midas.gui.customviews;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import java.util.TimeZone;

class AnalogClock$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.intent.action.TIMEZONE_CHANGED"))
    {
      String str = paramIntent.getStringExtra("time-zone");
      AnalogClock.access$002(this.this$0, new Time(TimeZone.getTimeZone(str).getID()));
    }
    AnalogClock.access$100(this.this$0);
    this.this$0.invalidate();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.customviews.AnalogClock.1
 * JD-Core Version:    0.6.0
 */