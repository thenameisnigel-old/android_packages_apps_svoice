package com.vlingo.midas.gui.widgets;

import android.os.CountDownTimer;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.settings.Settings;

class TimerWidget$1 extends CountDownTimer
{
  boolean first_check = true;

  public void onFinish()
  {
    TimerWidget.access$002(0L);
    TimerWidget.access$200(this.this$0);
    TimerWidget.access$100(this.this$0);
    if (!Settings.getBoolean("reset_screen_off_after_launch_timer", false))
      DialogFlow.getInstance().cancelDialog();
  }

  public void onTick(long paramLong)
  {
    TimerWidget.access$002(paramLong);
    if (this.first_check)
    {
      TimerWidget.access$022(1000L);
      this.first_check = false;
    }
    if (!TimerWidget.pause)
      TimerWidget.access$100(this.this$0);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.TimerWidget.1
 * JD-Core Version:    0.6.0
 */