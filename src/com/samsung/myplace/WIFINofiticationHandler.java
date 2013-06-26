package com.samsung.myplace;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.System;
import com.vlingo.midas.gui.ConversationActivity;
import java.util.ArrayList;
import java.util.Iterator;

public class WIFINofiticationHandler extends BroadcastReceiver
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
    String str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getSSID();
    if ((str != null) && (str.length() > 0))
      str = str.substring(1, -1 + str.length());
    this.mDataList = new SVoiceMyPlaceDBProvider(paramContext).getSelectedMyPlaceDataList("2");
    Iterator localIterator = this.mDataList.iterator();
    while (true)
    {
      if (localIterator.hasNext())
      {
        if (!str.equalsIgnoreCase(((MyPlaceData)localIterator.next()).getValue().trim()))
          break label121;
        launched = true;
        launchSVoiceInDrivingMode(paramContext);
      }
      while (true)
      {
        return;
        label121: if (!launched)
          break;
        Settings.System.putInt(paramContext.getContentResolver(), "driving_mode_on", 0);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.myplace.WIFINofiticationHandler
 * JD-Core Version:    0.6.0
 */