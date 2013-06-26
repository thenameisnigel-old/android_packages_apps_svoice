package com.sec.android.facedetection;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class SecFace$1
  implements Parcelable.Creator<SecFace>
{
  public SecFace createFromParcel(Parcel paramParcel)
  {
    return new SecFace(paramParcel);
  }

  public SecFace[] newArray(int paramInt)
  {
    return new SecFace[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.facedetection.SecFace.1
 * JD-Core Version:    0.6.0
 */