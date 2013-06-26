package com.samsung.lpp;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LPPGeoFenceParameter
  implements Parcelable
{
  public static final Parcelable.Creator<LPPGeoFenceParameter> CREATOR = new LPPGeoFenceParameter.1();
  public int mDirection;
  public double mLat;
  public double mLon;
  public String mName;
  public int mRadius;

  public LPPGeoFenceParameter(String paramString, double paramDouble1, double paramDouble2, int paramInt)
  {
    this.mName = paramString;
    this.mLat = paramDouble1;
    this.mLon = paramDouble2;
    this.mDirection = paramInt;
    this.mRadius = 300;
  }

  public LPPGeoFenceParameter(String paramString, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
  {
    this.mName = paramString;
    this.mLat = paramDouble1;
    this.mLon = paramDouble2;
    this.mDirection = paramInt1;
    this.mRadius = paramInt2;
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mName);
    paramParcel.writeDouble(this.mLat);
    paramParcel.writeDouble(this.mLon);
    paramParcel.writeInt(this.mDirection);
    paramParcel.writeInt(this.mRadius);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.lpp.LPPGeoFenceParameter
 * JD-Core Version:    0.6.0
 */