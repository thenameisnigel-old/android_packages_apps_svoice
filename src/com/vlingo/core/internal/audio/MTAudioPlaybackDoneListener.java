package com.vlingo.core.internal.audio;

import com.vlingo.core.internal.util.ActivityUtil;

public abstract class MTAudioPlaybackDoneListener
  implements IAudioPlaybackService.AudioPlaybackListener
{
  public final void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    ActivityUtil.runOnMainThread(new MTAudioPlaybackDoneListener.1(this, paramAudioRequest));
  }

  public final void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    ActivityUtil.runOnMainThread(new MTAudioPlaybackDoneListener.2(this, paramAudioRequest));
  }

  public abstract void onRequestDone(AudioRequest paramAudioRequest);

  public final void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    ActivityUtil.runOnMainThread(new MTAudioPlaybackDoneListener.3(this, paramAudioRequest));
  }

  public final void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.MTAudioPlaybackDoneListener
 * JD-Core Version:    0.6.0
 */