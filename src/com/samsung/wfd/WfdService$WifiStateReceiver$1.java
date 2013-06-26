package com.samsung.wfd;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager.GroupInfoListener;
import java.util.Collection;
import java.util.Iterator;

class WfdService$WifiStateReceiver$1
  implements WifiP2pManager.GroupInfoListener
{
  public void onGroupInfoAvailable(WifiP2pGroup paramWifiP2pGroup)
  {
    this.this$1.this$0.mGroup = paramWifiP2pGroup;
    this.this$1.this$0.logd("Group list size " + paramWifiP2pGroup.getClientList().size());
    Iterator localIterator = paramWifiP2pGroup.getClientList().iterator();
    while (localIterator.hasNext())
    {
      WifiP2pDevice localWifiP2pDevice = (WifiP2pDevice)localIterator.next();
      WfdService.access$802(this.this$1.this$0, localWifiP2pDevice.deviceAddress);
      this.this$1.this$0.logd("Device Name - " + localWifiP2pDevice.deviceName + " Address - " + localWifiP2pDevice.deviceAddress + " peer.wfdDevInfo - " + " GO addr - " + localWifiP2pDevice.groupownerAddress + " GO GOdeviceName - " + localWifiP2pDevice.GOdeviceName + " Status " + localWifiP2pDevice.status);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WfdService.WifiStateReceiver.1
 * JD-Core Version:    0.6.0
 */