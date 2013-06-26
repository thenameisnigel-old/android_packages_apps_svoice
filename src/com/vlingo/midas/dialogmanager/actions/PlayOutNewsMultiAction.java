package com.vlingo.midas.dialogmanager.actions;

import android.content.Context;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;
import com.vlingo.core.internal.audio.TTSEngine;
import com.vlingo.core.internal.audio.TTSRequest;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.phrasespotter.PhraseSpotter;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PlayOutNewsMultiAction extends DMAction
{
  private boolean isPlayingFirst = true;
  private PlayOutNewsAudioPlaybackListener playbackListener;
  private LinkedList<TTSRequest> requests = new LinkedList();
  private List<String> ttsStrings;

  private void playTts()
  {
    if ((this.ttsStrings == null) || (this.ttsStrings.isEmpty()))
      getListener().actionFail("No news found.");
    while (true)
    {
      return;
      TTSRequest localTTSRequest = TTSRequest.getResult((String)this.ttsStrings.get(0));
      this.playbackListener = new PlayOutNewsAudioPlaybackListener(null);
      AudioPlayerProxy.play(localTTSRequest, this.playbackListener);
      for (int i = 1; i < this.ttsStrings.size(); i++)
        this.requests.add(TTSRequest.getResult((String)this.ttsStrings.get(i)));
    }
  }

  private void prepareAllRequests()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
        Iterator localIterator = new ArrayList(PlayOutNewsMultiAction.this.requests).iterator();
        while (localIterator.hasNext())
          ((TTSRequest)localIterator.next()).prepareForPlayback(localContext, TTSEngine.getInstance(localContext));
      }
    }).start();
  }

  protected void execute()
  {
    playTts();
  }

  public void tts(List<String> paramList)
  {
    this.ttsStrings = paramList;
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
      PhraseSpotter.getInstance().stopPhraseSpotting();
      PlayOutNewsMultiAction.this.getListener().actionFail(str);
      AudioFocusManager.getInstance(PlayOutNewsMultiAction.this.getContext()).abandonAudioFocus();
    }

    public void onRequestDidPlay(AudioRequest paramAudioRequest)
    {
      TTSRequest localTTSRequest = (TTSRequest)PlayOutNewsMultiAction.this.requests.poll();
      if (localTTSRequest != null)
        AudioPlayerProxy.play(localTTSRequest, this);
      while (true)
      {
        return;
        PhraseSpotter.getInstance().stopPhraseSpotting();
        PlayOutNewsMultiAction.this.getListener().actionSuccess();
        AudioFocusManager.getInstance(PlayOutNewsMultiAction.this.getContext()).abandonAudioFocus();
      }
    }

    public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
    {
      String str = "request ignored, reason=" + paramReasonIgnored;
      PhraseSpotter.getInstance().stopPhraseSpotting();
      PlayOutNewsMultiAction.this.getListener().actionFail(str);
      AudioFocusManager.getInstance(PlayOutNewsMultiAction.this.getContext()).abandonAudioFocus();
    }

    public void onRequestWillPlay(AudioRequest paramAudioRequest)
    {
      ActivityUtil.scheduleOnMainThread(new PlayOutNewsMultiAction.PlayOutNewsAudioPlaybackListener.1(this), 500L);
      if (PlayOutNewsMultiAction.this.isPlayingFirst)
      {
        PlayOutNewsMultiAction.access$202(PlayOutNewsMultiAction.this, false);
        PlayOutNewsMultiAction.this.prepareAllRequests();
        AudioFocusManager.getInstance(PlayOutNewsMultiAction.this.getContext()).requestAudioFocus(3, 2);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.PlayOutNewsMultiAction
 * JD-Core Version:    0.6.0
 */