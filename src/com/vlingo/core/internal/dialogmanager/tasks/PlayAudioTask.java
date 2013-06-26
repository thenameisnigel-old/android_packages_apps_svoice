package com.vlingo.core.internal.dialogmanager.tasks;

import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;

public abstract class PlayAudioTask extends PausableTask
  implements IAudioPlaybackService.AudioPlaybackListener
{
  protected IAudioPlaybackService.AudioPlaybackListener mAudioPlaybackListener;
  private int mFileToPlayResid;

  public boolean isSystemTts()
  {
    return false;
  }

  public void onCancelled()
  {
    AudioPlayerProxy.stop();
    notifyFinished();
  }

  public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    if (this.mAudioPlaybackListener != null)
      this.mAudioPlaybackListener.onRequestCancelled(paramAudioRequest, paramReasonCanceled);
    notifyFinished();
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    if (this.mAudioPlaybackListener != null)
      this.mAudioPlaybackListener.onRequestDidPlay(paramAudioRequest);
    notifyFinished();
  }

  public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    if (this.mAudioPlaybackListener != null)
      this.mAudioPlaybackListener.onRequestIgnored(paramAudioRequest, paramReasonIgnored);
    notifyFinished();
  }

  public void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
    if (this.mAudioPlaybackListener != null)
      this.mAudioPlaybackListener.onRequestWillPlay(paramAudioRequest);
  }

  public abstract void run();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.tasks.PlayAudioTask
 * JD-Core Version:    0.6.0
 */