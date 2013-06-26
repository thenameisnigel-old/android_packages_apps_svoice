package com.sec.android.app.sns3.svc.sp.facebook.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sec.android.app.sns3.svc.sp.SnsSpResponse;

public final class SnsFbResponseMyAccountInfo extends SnsSpResponse
  implements Parcelable
{
  public static final Parcelable.Creator<SnsFbResponseMyAccountInfo> CREATOR = new SnsFbResponseMyAccountInfo.1();
  public String mId;
  public String mName;
  public String mPic;
  public String mPic_big;
  public String mUserName;

  public SnsFbResponseMyAccountInfo()
  {
  }

  private SnsFbResponseMyAccountInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.mId = paramParcel.readString();
    this.mName = paramParcel.readString();
    this.mPic = paramParcel.readString();
    this.mPic_big = paramParcel.readString();
    this.mUserName = paramParcel.readString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mId);
    paramParcel.writeString(this.mName);
    paramParcel.writeString(this.mPic);
    paramParcel.writeString(this.mPic_big);
    paramParcel.writeString(this.mUserName);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.sns3.svc.sp.facebook.response.SnsFbResponseMyAccountInfo
 * JD-Core Version:    0.6.0
 */