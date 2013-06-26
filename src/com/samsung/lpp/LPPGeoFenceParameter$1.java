package com.samsung.lpp;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class LPPGeoFenceParameter$1
  implements Parcelable.Creator<LPPGeoFenceParameter>
{
  public LPPGeoFenceParameter createFromParcel(Parcel paramParcel)
  {
    return new LPPGeoFenceParameter(paramParcel.readString(), paramParcel.readDouble(), paramParcel.readDouble(), paramParcel.readInt(), paramParcel.readInt());
  }

  public LPPGeoFenceParameter[] newArray(int paramInt)
  {
    return new LPPGeoFenceParameter[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.lpp.LPPGeoFenceParameter.1
 * JD-Core Version:    0.6.0
 */