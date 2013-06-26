package com.vlingo.core.internal.dialogmanager.tasks;

import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.TTSRequest;
import com.vlingo.core.internal.settings.VoicePrompt;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ClientSuppliedValues;

public class PlayTTSTask extends PlayAudioTask
{
  private boolean isTTSAnyway = false;
  private boolean mIsSystemTts;
  private String mTTSText;

  public PlayTTSTask(IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener, String paramString)
  {
    this(paramAudioPlaybackListener, paramString, false);
  }

  public PlayTTSTask(IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener, String paramString, boolean paramBoolean)
  {
    this.mTTSText = paramString;
    this.mAudioPlaybackListener = paramAudioPlaybackListener;
    this.mIsSystemTts = paramBoolean;
  }

  public PlayTTSTask(String paramString)
  {
    this(null, paramString);
  }

  public PlayTTSTask(String paramString, boolean paramBoolean)
  {
    this(null, paramString, paramBoolean);
  }

  private void playTTS()
  {
    if (isTTSAnyway())
      AudioPlayerProxy.playAnyway(TTSRequest.getPrompt(this.mTTSText, true), this);
    while (true)
    {
      return;
      if ((VoicePrompt.isEnabled()) || (ClientSuppliedValues.isDrivingModeEnabled()))
      {
        AudioPlayerProxy.play(TTSRequest.getPrompt(this.mTTSText), this);
        continue;
      }
      ActivityUtil.scheduleOnMainThread(new PlayTTSTask.1(this), 1000L);
    }
  }

  public boolean isSystemTts()
  {
    return this.mIsSystemTts;
  }

  public boolean isTTSAnyway()
  {
    return this.isTTSAnyway;
  }

  public void run()
  {
    monitorenter;
    try
    {
      if (this.mTTSText != null)
        playTTS();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setTTSAnyway(boolean paramBoolean)
  {
    this.isTTSAnyway = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.tasks.PlayTTSTask
 * JD-Core Version:    0.6.0
 */