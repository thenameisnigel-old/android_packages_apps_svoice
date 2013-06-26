package com.vlingo.core.internal.dialogmanager.tasks;

import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.MediaAudioRequest;

public class PlayMediaTask extends PlayAudioTask
{
  private int mFileToPlayResid;

  public PlayMediaTask(int paramInt)
  {
    this(null, paramInt);
  }

  public PlayMediaTask(IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener, int paramInt)
  {
    this.mAudioPlaybackListener = paramAudioPlaybackListener;
    this.mFileToPlayResid = paramInt;
  }

  private void playMedia()
  {
    AudioPlayerProxy.play(MediaAudioRequest.getRequest(this.mFileToPlayResid), this);
  }

  public boolean isSystemTts()
  {
    return false;
  }

  public void run()
  {
    playMedia();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.tasks.PlayMediaTask
 * JD-Core Version:    0.6.0
 */