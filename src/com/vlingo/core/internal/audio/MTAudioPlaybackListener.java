package com.vlingo.core.internal.audio;

import com.vlingo.core.internal.util.ActivityUtil;

public class MTAudioPlaybackListener
  implements IAudioPlaybackService.AudioPlaybackListener
{
  final IAudioPlaybackService.AudioPlaybackListener listener;

  public MTAudioPlaybackListener(IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    this.listener = paramAudioPlaybackListener;
  }

  public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    ActivityUtil.runOnMainThread(new MTAudioPlaybackListener.1(this, paramAudioRequest, paramReasonCanceled));
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    ActivityUtil.runOnMainThread(new MTAudioPlaybackListener.2(this, paramAudioRequest));
  }

  public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    ActivityUtil.runOnMainThread(new MTAudioPlaybackListener.3(this, paramAudioRequest, paramReasonIgnored));
  }

  public void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
    ActivityUtil.runOnMainThread(new MTAudioPlaybackListener.4(this, paramAudioRequest));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.MTAudioPlaybackListener
 * JD-Core Version:    0.6.0
 */