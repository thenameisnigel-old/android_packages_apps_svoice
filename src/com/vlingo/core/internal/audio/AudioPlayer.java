package com.vlingo.core.internal.audio;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.bluetooth.OnBluetoothAudioOnTimeoutTask;
import com.vlingo.core.internal.settings.LanguageChangeReceiver;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.VlingoApplicationInterface;

class AudioPlayer
{
  public static final long BT_START_DELAY_TIME = 2000L;
  static final int COMPLETE = 1;
  static final int ERROR = 105;
  static final int PLAY = 0;
  static final int SHUT_DOWN = 3;
  static final int STOP = 2;
  final AudioFocusManager audioFocusManager;
  final Context context;
  final Handler handler;
  boolean isInitialized = false;
  private boolean isPaused = false;
  boolean isShutDown = false;
  boolean isSupposedToBePlaying = false;
  boolean isWorking = false;
  private final LanguageChangeListener languageListener;
  MediaPlayer mediaPlayer;
  private OnBluetoothAudioOnTimeoutTask onBtOnTask = null;
  private final PhoneListener phoneListener;
  AudioRequest playingRequest = null;
  final IAudioPlaybackService.AudioPlaybackListener serviceListener;
  final TelephonyManager telephonyManager;
  final TTSEngine ttsEngine;

  AudioPlayer(Context paramContext, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    createMediaPlayer();
    HandlerThread localHandlerThread = new HandlerThread("AudioPlayer");
    this.ttsEngine = TTSEngine.getInstance(paramContext.getApplicationContext());
    this.languageListener = new LanguageChangeListener();
    this.languageListener.register(paramContext);
    localHandlerThread.start();
    this.handler = new AudioPlayerHandler(localHandlerThread);
    this.context = paramContext;
    this.serviceListener = paramAudioPlaybackListener;
    this.telephonyManager = ((TelephonyManager)paramContext.getSystemService("phone"));
    this.phoneListener = new PhoneListener();
    this.audioFocusManager = AudioFocusManager.getInstance(paramContext);
    monitorenter;
    try
    {
      this.telephonyManager.listen(this.phoneListener, 32);
      if (this.telephonyManager.getCallState() != 0)
        this.isPaused = true;
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

  private void createMediaPlayer()
  {
    this.mediaPlayer = new MediaPlayer();
  }

  private boolean setDataSource(AudioRequest paramAudioRequest)
  {
    this.isInitialized = false;
    try
    {
      this.mediaPlayer.reset();
      this.mediaPlayer.setOnPreparedListener(null);
      paramAudioRequest.setDataSource(this.context, this.mediaPlayer, this);
      this.mediaPlayer.setAudioStreamType(paramAudioRequest.audioStream);
      this.mediaPlayer.prepare();
      this.isInitialized = true;
      return this.isInitialized;
    }
    catch (Exception localException)
    {
      while (true)
      {
        this.isInitialized = false;
        paramAudioRequest.onSetDataSourceFailed();
      }
    }
  }

  public TTSEngine getTTSEngine()
  {
    return this.ttsEngine;
  }

  public boolean isBusy()
  {
    if ((isPlaying()) || (this.isWorking));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isPlaying()
  {
    return this.isSupposedToBePlaying;
  }

  public void onDestroy()
  {
    this.languageListener.unregister(this.context);
    this.telephonyManager.listen(this.phoneListener, 0);
    this.handler.removeCallbacksAndMessages(null);
    this.handler.sendMessage(this.handler.obtainMessage(3));
  }

  public void pause()
  {
    monitorenter;
    try
    {
      this.isPaused = true;
      stop();
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

  public void play(AudioRequest paramAudioRequest)
  {
    play(paramAudioRequest, null);
  }

  public void play(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener paramAudioPlaybackListener)
  {
    if (this.isPaused)
    {
      this.serviceListener.onRequestIgnored(paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored.PAUSED);
      paramAudioRequest.onRequestIgnored(paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored.PAUSED);
    }
    while (true)
    {
      return;
      this.isWorking = true;
      paramAudioRequest.listener = paramAudioPlaybackListener;
      this.handler.sendMessage(this.handler.obtainMessage(0, paramAudioRequest));
    }
  }

  public void resume()
  {
    monitorenter;
    try
    {
      this.isPaused = false;
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

  public void stop()
  {
    this.handler.sendMessage(this.handler.obtainMessage(2));
  }

  private class AudioPlayerHandler extends Handler
  {
    HandlerThread thread;

    public AudioPlayerHandler(HandlerThread arg2)
    {
      super();
      this.thread = localObject;
    }

    private void handleError(AudioRequest paramAudioRequest)
    {
      if ((AudioPlayer.this.playingRequest != null) && (!AudioPlayer.this.playingRequest.hasFlag(4)))
        releaseMediaPlayer();
      AudioPlayer.this.playingRequest = null;
      AudioPlayer.this.isSupposedToBePlaying = false;
      AudioPlayer.this.serviceListener.onRequestCancelled(paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled.ERROR);
      paramAudioRequest.onRequestCancelled(paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled.ERROR);
      AudioPlayer.this.isWorking = false;
    }

    private void handleStart(AudioRequest paramAudioRequest)
    {
      if (paramAudioRequest.requestAudioFocus)
      {
        AudioPlayer.this.audioFocusManager.requestAudioFocus(paramAudioRequest.audioStream, paramAudioRequest.audioFocusType);
        if ((BluetoothManager.isBluetoothAudioSupported()) && (!BluetoothManager.isBluetoothAudioOn()))
          AudioPlayer.access$002(AudioPlayer.this, OnBluetoothAudioOnTimeoutTask.runTaskOnBluetoothAudioOn(2000L, new AudioPlayer.AudioPlayerHandler.1(this, paramAudioRequest), AudioPlayer.this.handler));
      }
      while (true)
      {
        return;
        handleStartSub(paramAudioRequest);
        continue;
        handleStartSub(paramAudioRequest);
      }
    }

    private void handleStartSub(AudioRequest paramAudioRequest)
    {
      AudioPlayer.this.isSupposedToBePlaying = true;
      AudioPlayer.this.playingRequest = paramAudioRequest;
      AudioPlayer.this.serviceListener.onRequestWillPlay(paramAudioRequest);
      boolean bool1 = true;
      if ((paramAudioRequest instanceof MultiPartAudioRequest))
        bool1 = ((MultiPartAudioRequest)paramAudioRequest).isOnFirstPart();
      if (bool1)
        paramAudioRequest.onRequestWillPlay(paramAudioRequest);
      if ((paramAudioRequest instanceof NullAudioRequest))
        skipAudioStart(paramAudioRequest);
      while (true)
      {
        return;
        if (AudioPlayer.this.mediaPlayer == null)
          AudioPlayer.this.createMediaPlayer();
        if (AudioPlayer.this.setDataSource(paramAudioRequest))
          break;
        skipAudioStart(paramAudioRequest);
      }
      AudioPlayer.MediaPlayerListener localMediaPlayerListener = new AudioPlayer.MediaPlayerListener(AudioPlayer.this, paramAudioRequest);
      AudioPlayer.this.mediaPlayer.setOnCompletionListener(localMediaPlayerListener);
      AudioPlayer.this.mediaPlayer.setOnErrorListener(localMediaPlayerListener);
      if ((paramAudioRequest instanceof ToneAudioRequest))
      {
        int j = AudioPlayer.this.mediaPlayer.getDuration();
        AudioPlayer.this.handler.sendMessageDelayed(AudioPlayer.this.handler.obtainMessage(1, paramAudioRequest), j);
      }
      boolean bool2 = ((AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio")).isBluetoothScoOn();
      int i = BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1);
      if ((bool2 == true) && (i == 2));
      while (true)
      {
        AudioPlayer.this.mediaPlayer.start();
        break;
        if ((!bool2) && (i == 2))
          continue;
      }
    }

    private void handleStop(boolean paramBoolean)
    {
      if ((!paramBoolean) && (AudioPlayer.this.playingRequest != null) && (AudioPlayer.this.playingRequest.hasFlag(2)));
      while (true)
      {
        return;
        if (AudioPlayer.this.onBtOnTask != null)
        {
          AudioPlayer.this.onBtOnTask.Cancel();
          AudioPlayer.access$002(AudioPlayer.this, null);
        }
        if (AudioPlayer.this.isSupposedToBePlaying)
        {
          if ((AudioPlayer.this.isInitialized) && (AudioPlayer.this.playingRequest != null))
          {
            AudioPlayer.this.mediaPlayer.reset();
            AudioPlayer.this.isInitialized = false;
            if (!AudioPlayer.this.playingRequest.hasFlag(4))
              releaseMediaPlayer();
          }
          AudioPlayer.this.serviceListener.onRequestCancelled(AudioPlayer.this.playingRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled.STOPPED);
          AudioPlayer.this.playingRequest.onRequestCancelled(AudioPlayer.this.playingRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled.STOPPED);
        }
        AudioPlayer.this.playingRequest = null;
        AudioPlayer.this.isSupposedToBePlaying = false;
        AudioPlayer.this.isWorking = false;
      }
    }

    private void releaseMediaPlayer()
    {
      if (AudioPlayer.this.mediaPlayer != null)
      {
        AudioPlayer.this.mediaPlayer.setOnCompletionListener(null);
        AudioPlayer.this.mediaPlayer.setOnErrorListener(null);
        AudioPlayer.this.mediaPlayer.release();
        AudioPlayer.this.mediaPlayer = null;
      }
    }

    private void skipAudioStart(AudioRequest paramAudioRequest)
    {
      AudioPlayer.this.serviceListener.onRequestDidPlay(paramAudioRequest);
      paramAudioRequest.onRequestDidPlay(paramAudioRequest);
      AudioPlayer.this.playingRequest = null;
      AudioPlayer.this.isSupposedToBePlaying = false;
      AudioPlayer.this.isWorking = false;
    }

    public void handleMessage(Message paramMessage)
    {
      if (AudioPlayer.this.isShutDown);
      while (true)
      {
        return;
        AudioRequest localAudioRequest = (AudioRequest)paramMessage.obj;
        switch (paramMessage.what)
        {
        default:
          break;
        case 0:
          if (localAudioRequest.hasFlag(1))
            handleStop(true);
          if (AudioPlayer.this.isPlaying())
          {
            AudioPlayer.this.serviceListener.onRequestIgnored(localAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored.OTHER_REQUEST_PLAYING);
            localAudioRequest.onRequestIgnored(localAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored.OTHER_REQUEST_PLAYING);
            continue;
          }
          if (!localAudioRequest.prepareForPlayback(AudioPlayer.this.context, AudioPlayer.this))
          {
            handleError(localAudioRequest);
            continue;
          }
          handleStart(localAudioRequest);
          break;
        case 1:
          if (((localAudioRequest instanceof MultiPartAudioRequest)) && (((MultiPartAudioRequest)localAudioRequest).areMorePartsWaiting()))
          {
            handleStart(localAudioRequest);
            continue;
          }
          if ((localAudioRequest.requestAudioFocus) && (!ApplicationAdapter.getInstance().getVlingoApp().isAppInForeground()))
            AudioPlayer.this.audioFocusManager.abandonAudioFocus();
          if (!localAudioRequest.hasFlag(4))
            releaseMediaPlayer();
          AudioPlayer.this.playingRequest = null;
          AudioPlayer.this.isSupposedToBePlaying = false;
          AudioPlayer.this.serviceListener.onRequestDidPlay(localAudioRequest);
          localAudioRequest.onRequestDidPlay(localAudioRequest);
          AudioPlayer.this.isWorking = false;
          break;
        case 2:
          handleStop(false);
          break;
        case 105:
          handleError(localAudioRequest);
          break;
        case 3:
          AudioPlayer.this.isShutDown = true;
          releaseMediaPlayer();
          this.thread.getLooper().quit();
          AudioPlayer.this.isWorking = false;
        }
      }
    }
  }

  class LanguageChangeListener extends LanguageChangeReceiver
  {
    LanguageChangeListener()
    {
    }

    public void onLanguageChanged(String paramString)
    {
      if (AudioPlayer.this.ttsEngine != null)
        AudioPlayer.this.ttsEngine.onSystemLanguageChanged();
    }
  }

  private class MediaPlayerListener
    implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener
  {
    AudioRequest request;

    public MediaPlayerListener(AudioRequest arg2)
    {
      Object localObject;
      this.request = localObject;
    }

    public void onCompletion(MediaPlayer paramMediaPlayer)
    {
      if ((this.request != AudioPlayer.this.playingRequest) || ((this.request instanceof ToneAudioRequest)));
      while (true)
      {
        return;
        AudioPlayer.this.handler.sendMessage(AudioPlayer.this.handler.obtainMessage(1, this.request));
      }
    }

    public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
    {
      if (AudioPlayer.this.playingRequest == this.request)
      {
        if (paramMediaPlayer != null)
        {
          paramMediaPlayer.setOnCompletionListener(null);
          paramMediaPlayer.setOnErrorListener(null);
        }
        AudioPlayer.this.handler.sendMessage(AudioPlayer.this.handler.obtainMessage(105, this.request));
      }
      return true;
    }
  }

  class PhoneListener extends PhoneStateListener
  {
    PhoneListener()
    {
    }

    public void onCallStateChanged(int paramInt, String paramString)
    {
      switch (paramInt)
      {
      default:
      case 1:
      case 2:
      case 0:
      }
      while (true)
      {
        return;
        AudioPlayer.this.pause();
        continue;
        AudioPlayer.this.pause();
        continue;
        AudioPlayer.this.resume();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioPlayer
 * JD-Core Version:    0.6.0
 */