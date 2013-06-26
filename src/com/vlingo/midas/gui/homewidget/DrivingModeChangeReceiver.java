package com.vlingo.midas.gui.homewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.vlingo.midas.drivingmode.DrivingModeUtil;

public class DrivingModeChangeReceiver extends BroadcastReceiver
{
  public static final String ACTION_DRIVRING_MODE_CHANGE = "com.vlingo.client.widget.action.driving_mode_change";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("com.vlingo.client.widget.action.driving_mode_change"))
      DrivingModeUtil.toggleDrivingMode(paramContext);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.homewidget.DrivingModeChangeReceiver
 * JD-Core Version:    0.6.0
 */