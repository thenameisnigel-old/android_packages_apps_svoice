package com.vlingo.core.internal.audio;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ToneAudioRequest$1
  implements Parcelable.Creator<ToneAudioRequest>
{
  public ToneAudioRequest createFromParcel(Parcel paramParcel)
  {
    return new ToneAudioRequest(paramParcel);
  }

  public ToneAudioRequest[] newArray(int paramInt)
  {
    return new ToneAudioRequest[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.ToneAudioRequest.1
 * JD-Core Version:    0.6.0
 */