package com.samsung.myplace;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.System;
import com.vlingo.midas.gui.ConversationActivity;
import java.util.ArrayList;
import java.util.Iterator;

public class BTNotificationHandler extends BroadcastReceiver
{
  private static boolean launched = false;
  ArrayList<MyPlaceData> mDataList = null;

  private void launchSVoiceInDrivingMode(Context paramContext)
  {
    Settings.System.putInt(paramContext.getContentResolver(), "driving_mode_on", 1);
    Intent localIntent = new Intent(paramContext, ConversationActivity.class);
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (this.mDataList != null)
      this.mDataList = null;
    BluetoothDevice localBluetoothDevice = (BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
    this.mDataList = new SVoiceMyPlaceDBProvider(paramContext).getSelectedMyPlaceDataList("3");
    if (paramIntent.getAction().equalsIgnoreCase("android.bluetooth.device.action.ACL_CONNECTED"))
    {
      Iterator localIterator = this.mDataList.iterator();
      while (localIterator.hasNext())
      {
        MyPlaceData localMyPlaceData = (MyPlaceData)localIterator.next();
        if (!localBluetoothDevice.getName().equalsIgnoreCase(localMyPlaceData.getValue()))
          continue;
        launched = true;
        launchSVoiceInDrivingMode(paramContext);
      }
    }
    while (true)
    {
      return;
      if ((!paramIntent.getAction().equalsIgnoreCase("android.bluetooth.device.action.ACL_CONNECTED")) && (launched))
      {
        Settings.System.putInt(paramContext.getContentResolver(), "driving_mode_on", 0);
        continue;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.myplace.BTNotificationHandler
 * JD-Core Version:    0.6.0
 */