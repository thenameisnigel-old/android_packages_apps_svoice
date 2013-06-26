package com.samsung.wfd;

import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class WfdDevice
  implements Parcelable
{
  public static final Parcelable.Creator<WfdDevice> CREATOR = new WfdDevice.1();
  private static final String TAG = "WfdDevice";
  public WifiP2pDevice p2pDevice;
  public WfdInfo wfdInfo;

  public WfdDevice()
  {
    this.wfdInfo = null;
    this.p2pDevice = null;
  }

  public WfdDevice(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public WfdDevice(WfdDevice paramWfdDevice)
  {
    this.wfdInfo = paramWfdDevice.wfdInfo;
    this.p2pDevice = paramWfdDevice.p2pDevice;
  }

  public WfdDevice(WfdInfo paramWfdInfo, WifiP2pDevice paramWifiP2pDevice)
  {
    this.wfdInfo = new WfdInfo(paramWfdInfo);
    this.p2pDevice = new WifiP2pDevice(paramWifiP2pDevice);
    if (this.p2pDevice != null)
      this.wfdInfo.coupledDeviceAddress = this.p2pDevice.deviceAddress;
  }

  private void readFromParcel(Parcel paramParcel)
  {
    this.wfdInfo = ((WfdInfo)paramParcel.readValue(null));
    this.p2pDevice = ((WifiP2pDevice)paramParcel.readValue(null));
  }

  public int describeContents()
  {
    return 0;
  }

  public WifiP2pDevice getP2PDevice()
  {
    return this.p2pDevice;
  }

  public WfdInfo getWfdInfo()
  {
    return this.wfdInfo;
  }

  public void setP2PDevice(WifiP2pDevice paramWifiP2pDevice)
  {
    this.p2pDevice = new WifiP2pDevice(paramWifiP2pDevice);
  }

  public void setWfdInfo(WfdInfo paramWfdInfo)
  {
    this.wfdInfo = new WfdInfo(paramWfdInfo);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeValue(this.wfdInfo);
    paramParcel.writeValue(this.p2pDevice);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WfdDevice
 * JD-Core Version:    0.6.0
 */