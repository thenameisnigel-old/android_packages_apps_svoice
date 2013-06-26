package com.samsung.wakeupsetting;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

class BluetoothManager$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if ("android.bluetooth.device.action.ACL_CONNECTED".equals(str));
    while (true)
    {
      return;
      if ((!"android.bluetooth.device.action.ACL_DISCONNECTED".equals(str)) && (!"android.bluetooth.device.action.BOND_STATE_CHANGED".equals(str)))
      {
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(str))
        {
          int j = paramIntent.getExtras().getInt("android.bluetooth.adapter.extra.STATE");
          BluetoothManager.access$000(this.this$0, j);
          continue;
        }
        if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(str))
        {
          ((BluetoothDevice)paramIntent.getExtras().getParcelable("android.bluetooth.device.extra.DEVICE"));
          int i = paramIntent.getExtras().getInt("android.bluetooth.profile.extra.STATE");
          BluetoothManager.access$000(this.this$0, i);
          continue;
        }
        if (!"android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED".equals(str))
          continue;
        continue;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.BluetoothManager.1
 * JD-Core Version:    0.6.0
 */