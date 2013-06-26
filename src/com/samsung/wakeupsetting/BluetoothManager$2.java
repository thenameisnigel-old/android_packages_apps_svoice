package com.samsung.wakeupsetting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;

class BluetoothManager$2
  implements BluetoothProfile.ServiceListener
{
  public void onServiceConnected(int paramInt, BluetoothProfile paramBluetoothProfile)
  {
    if (BluetoothManager.access$100(this.this$0) == 2)
    {
      BluetoothAdapter.getDefaultAdapter().closeProfileProxy(1, paramBluetoothProfile);
      BluetoothManager.access$102(this.this$0, 0);
      BluetoothManager.access$202(this.this$0, null);
    }
    while (true)
    {
      return;
      if (paramInt == 1)
      {
        BluetoothManager.access$202(this.this$0, (BluetoothHeadset)paramBluetoothProfile);
        if (!BluetoothManager.isHeadsetConnected())
          continue;
        BluetoothManager.setBluetoothAudioOn(true);
        BluetoothManager.access$302(this.this$0, BluetoothManager.access$400(this.this$0));
        if (BluetoothManager.access$300(this.this$0) == null)
          continue;
        BluetoothManager.access$200(this.this$0).startVoiceRecognition(BluetoothManager.access$300(this.this$0));
        continue;
      }
    }
  }

  public void onServiceDisconnected(int paramInt)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.BluetoothManager.2
 * JD-Core Version:    0.6.0
 */