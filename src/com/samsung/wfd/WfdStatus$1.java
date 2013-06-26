package com.samsung.wfd;

import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Parcel;
import android.os.Parcelable.Creator;

class WfdStatus$1
  implements Parcelable.Creator<WfdStatus>
{
  public WfdStatus createFromParcel(Parcel paramParcel)
  {
    WfdStatus localWfdStatus = new WfdStatus();
    localWfdStatus.state = paramParcel.readInt();
    localWfdStatus.sessionId = paramParcel.readInt();
    localWfdStatus.connectedDevice = ((WifiP2pDevice)paramParcel.readValue(null));
    return localWfdStatus;
  }

  public WfdStatus[] newArray(int paramInt)
  {
    return new WfdStatus[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WfdStatus.1
 * JD-Core Version:    0.6.0
 */