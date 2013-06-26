package com.vlingo.core.internal.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NullAudioRequest extends AudioRequest
  implements Parcelable
{
  public static final Parcelable.Creator<NullAudioRequest> CREATOR = new NullAudioRequest.1();

  protected NullAudioRequest()
  {
  }

  public NullAudioRequest(Parcel paramParcel)
  {
    super(paramParcel);
  }

  public static NullAudioRequest getRequest()
  {
    return new NullAudioRequest();
  }

  public boolean prepareForPlayback(Context paramContext, AudioPlayer paramAudioPlayer)
  {
    return true;
  }

  public void setDataSource(Context paramContext, MediaPlayer paramMediaPlayer, AudioPlayer paramAudioPlayer)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.NullAudioRequest
 * JD-Core Version:    0.6.0
 */