package com.samsung.wfd;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class WfdDevice$1
  implements Parcelable.Creator<WfdDevice>
{
  public WfdDevice createFromParcel(Parcel paramParcel)
  {
    return new WfdDevice(paramParcel);
  }

  public WfdDevice[] newArray(int paramInt)
  {
    return new WfdDevice[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WfdDevice.1
 * JD-Core Version:    0.6.0
 */