package com.samsung.android.service.gesture;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class GestureEvent$1
  implements Parcelable.Creator<GestureEvent>
{
  public GestureEvent createFromParcel(Parcel paramParcel)
  {
    return new GestureEvent(paramParcel);
  }

  public GestureEvent[] newArray(int paramInt)
  {
    return new GestureEvent[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.android.service.gesture.GestureEvent.1
 * JD-Core Version:    0.6.0
 */