package com.vlingo.core.internal.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class AudioRequest
  implements Parcelable, IAudioPlaybackService.AudioPlaybackListener
{
  public static final int FLAG_IGNORE_STOP_REQUEST = 2;
  public static final int FLAG_KEEP_MEDIAPLAYER_OPEN_ON_COMPLETION = 4;
  public static final int FLAG_OVERRIDE_CURRENTLY_PLAYING_AUDIO = 1;
  public static final int FLAG_PLAY_DURING_RECOGNITION = 8;
  public int audioFocusType = 2;
  public int audioStream = 3;
  public int flags = 0;
  IAudioPlaybackService.AudioPlaybackListener listener = null;
  public boolean requestAudioFocus = true;

  protected AudioRequest()
  {
  }

  protected AudioRequest(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean hasFlag(int paramInt)
  {
    if ((paramInt & this.flags) != 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    if (this.listener != null)
      this.listener.onRequestCancelled(paramAudioRequest, paramReasonCanceled);
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    if (this.listener != null)
      this.listener.onRequestDidPlay(paramAudioRequest);
  }

  public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    if (this.listener != null)
      this.listener.onRequestIgnored(paramAudioRequest, paramReasonIgnored);
  }

  public void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
    if (this.listener != null)
      this.listener.onRequestWillPlay(paramAudioRequest);
  }

  public void onSetDataSourceFailed()
  {
  }

  public abstract boolean prepareForPlayback(Context paramContext, AudioPlayer paramAudioPlayer);

  public void readFromParcel(Parcel paramParcel)
  {
  }

  public abstract void setDataSource(Context paramContext, MediaPlayer paramMediaPlayer, AudioPlayer paramAudioPlayer)
    throws Exception;

  public void setFlag(int paramInt)
  {
    this.flags = (paramInt | this.flags);
  }

  public void setStream(int paramInt)
  {
    this.audioStream = paramInt;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioRequest
 * JD-Core Version:    0.6.0
 */