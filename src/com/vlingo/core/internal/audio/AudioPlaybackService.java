package com.vlingo.core.internal.audio;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.lang.ref.WeakReference;

public class AudioPlaybackService extends Service
  implements IAudioPlaybackService.AudioPlaybackListener
{
  private int NumClients = 0;
  private int NumRequests = 0;
  private final IBinder mBinder = new ServiceStub(this);
  private AudioPlayer player;

  private void stopServiceIfPossible()
  {
    if ((this.NumClients <= 0) && (this.NumRequests <= 0) && (!this.player.isBusy()))
      stopSelf();
  }

  public boolean isPlaying()
  {
    return this.player.isPlaying();
  }

  public IBinder onBind(Intent paramIntent)
  {
    this.NumClients = (1 + this.NumClients);
    return this.mBinder;
  }

  public void onCreate()
  {
    super.onCreate();
    this.player = new AudioPlayer(this, this);
  }

  public void onDestroy()
  {
    this.player.onDestroy();
    super.onDestroy();
  }

  public void onRebind(Intent paramIntent)
  {
    this.NumClients = (1 + this.NumClients);
  }

  public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    this.NumRequests = (-1 + this.NumRequests);
    stopServiceIfPossible();
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    this.NumRequests = (-1 + this.NumRequests);
    stopServiceIfPossible();
  }

  public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    this.NumRequests = (-1 + this.NumRequests);
    stopServiceIfPossible();
  }

  public void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
    this.NumRequests = (1 + this.NumRequests);
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return 1;
  }

  public boolean onUnbind(Intent paramIntent)
  {
    this.NumClients = (-1 + this.NumClients);
    stopServiceIfPossible();
    return false;
  }

  public void pause()
  {
    this.player.pause();
  }

  public void play(AudioRequest paramAudioRequest)
  {
    this.player.play(paramAudioRequest);
  }

  public void play(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    this.player.play(paramAudioRequest, paramAudioPlaybackListener);
  }

  public void resume()
  {
    this.player.resume();
  }

  public void stop()
  {
    this.player.stop();
  }

  static class ServiceStub extends Binder
    implements IAudioPlaybackService
  {
    WeakReference<AudioPlaybackService> mService;

    ServiceStub(AudioPlaybackService paramAudioPlaybackService)
    {
      this.mService = new WeakReference(paramAudioPlaybackService);
    }

    public boolean isPlaying()
    {
      return ((AudioPlaybackService)this.mService.get()).isPlaying();
    }

    public void pause()
    {
      ((AudioPlaybackService)this.mService.get()).pause();
    }

    public void play(AudioRequest paramAudioRequest)
    {
      ((AudioPlaybackService)this.mService.get()).play(paramAudioRequest);
    }

    public void play(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
    {
      ((AudioPlaybackService)this.mService.get()).play(paramAudioRequest, paramAudioPlaybackListener);
    }

    public void resume()
    {
      ((AudioPlaybackService)this.mService.get()).resume();
    }

    public void stop()
    {
      ((AudioPlaybackService)this.mService.get()).stop();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioPlaybackService
 * JD-Core Version:    0.6.0
 */