package com.vlingo.core.internal.audio;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class MediaAudioRequest$1
  implements Parcelable.Creator<MediaAudioRequest>
{
  public MediaAudioRequest createFromParcel(Parcel paramParcel)
  {
    return new MediaAudioRequest(paramParcel);
  }

  public MediaAudioRequest[] newArray(int paramInt)
  {
    return new MediaAudioRequest[paramInt];
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.MediaAudioRequest.1
 * JD-Core Version:    0.6.0
 */