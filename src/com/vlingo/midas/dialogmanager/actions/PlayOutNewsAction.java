package com.vlingo.midas.dialogmanager.actions;

import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;
import com.vlingo.core.internal.audio.TTSRequest;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.util.ActivityUtil;

public class PlayOutNewsAction extends DMAction
{
  private PlayOutNewsAudioPlaybackListener playbackListener;
  private String tts;

  protected void execute()
  {
    TTSRequest localTTSRequest = TTSRequest.getResult(this.tts);
    localTTSRequest.setFlag(1);
    this.playbackListener = new PlayOutNewsAudioPlaybackListener(null);
    AudioPlayerProxy.play(localTTSRequest, this.playbackListener);
  }

  public void tts(String paramString)
  {
    this.tts = paramString;
  }

  private class PlayOutNewsAudioPlaybackListener
    implements IAudioPlaybackService.AudioPlaybackListener
  {
    private PlayOutNewsAudioPlaybackListener()
    {
    }

    public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
    {
      String str = "request ignored, reason=" + paramReasonCanceled;
      PlayOutNewsAction.this.getListener().actionFail(str);
    }

    public void onRequestDidPlay(AudioRequest paramAudioRequest)
    {
      PlayOutNewsAction.this.getListener().actionSuccess();
    }

    public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
    {
      String str = "request ignored, reason=" + paramReasonIgnored;
      PlayOutNewsAction.this.getListener().actionFail(str);
    }

    public void onRequestWillPlay(AudioRequest paramAudioRequest)
    {
      ActivityUtil.scheduleOnMainThread(new PlayOutNewsAction.PlayOutNewsAudioPlaybackListener.1(this), 500L);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.PlayOutNewsAction
 * JD-Core Version:    0.6.0
 */