package com.vlingo.core.internal.audio;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class NullAudioRequest$1
  implements Parcelable.Creator<NullAudioRequest>
{
  public NullAudioRequest createFromParcel(Parcel paramParcel)
  {
    return new NullAudioRequest(paramParcel);
  }

  public NullAudioRequest[] newArray(int paramInt)
  {
    return new NullAudioRequest[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.NullAudioRequest.1
 * JD-Core Version:    0.6.0
 */