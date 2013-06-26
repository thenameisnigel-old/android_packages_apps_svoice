package com.samsung.wfd;

import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class WfdStatus
  implements Parcelable
{
  public static final Parcelable.Creator<WfdStatus> CREATOR = new WfdStatus.1();
  public WifiP2pDevice connectedDevice = null;
  public int sessionId = -1;
  public int state = WfdEnums.SessionState.INVALID.ordinal();

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.state);
    paramParcel.writeInt(this.sessionId);
    paramParcel.writeValue(this.connectedDevice);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WfdStatus
 * JD-Core Version:    0.6.0
 */