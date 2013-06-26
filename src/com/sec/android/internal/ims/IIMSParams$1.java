package com.sec.android.internal.ims;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class IIMSParams$1
  implements Parcelable.Creator<IIMSParams>
{
  public IIMSParams createFromParcel(Parcel paramParcel)
  {
    return new IIMSParams(paramParcel);
  }

  public IIMSParams[] newArray(int paramInt)
  {
    return new IIMSParams[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.IIMSParams.1
 * JD-Core Version:    0.6.0
 */