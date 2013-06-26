package com.vlingo.core.internal.audio;

public abstract interface IAudioPlaybackService
{
  public abstract boolean isPlaying();

  public abstract void pause();

  public abstract void play(AudioRequest paramAudioRequest);

  public abstract void play(AudioRequest paramAudioRequest, AudioPlaybackListener paramAudioPlaybackListener);

  public abstract void resume();

  public abstract void stop();

  public static abstract interface AudioPlaybackListener
  {
    public abstract void onRequestCancelled(AudioRequest paramAudioRequest, ReasonCanceled paramReasonCanceled);

    public abstract void onRequestDidPlay(AudioRequest paramAudioRequest);

    public abstract void onRequestIgnored(AudioRequest paramAudioRequest, ReasonIgnored paramReasonIgnored);

    public abstract void onRequestWillPlay(AudioRequest paramAudioRequest);

    public static enum ReasonCanceled
    {
      static
      {
        ReasonCanceled[] arrayOfReasonCanceled = new ReasonCanceled[2];
        arrayOfReasonCanceled[0] = ERROR;
        arrayOfReasonCanceled[1] = STOPPED;
        $VALUES = arrayOfReasonCanceled;
      }
    }

    public static enum ReasonIgnored
    {
      static
      {
        OTHER_REQUEST_PLAYING = new ReasonIgnored("OTHER_REQUEST_PLAYING", 1);
        RECOGNITION_ACTIVE = new ReasonIgnored("RECOGNITION_ACTIVE", 2);
        ReasonIgnored[] arrayOfReasonIgnored = new ReasonIgnored[3];
        arrayOfReasonIgnored[0] = PAUSED;
        arrayOfReasonIgnored[1] = OTHER_REQUEST_PLAYING;
        arrayOfReasonIgnored[2] = RECOGNITION_ACTIVE;
        $VALUES = arrayOfReasonIgnored;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.IAudioPlaybackService
 * JD-Core Version:    0.6.0
 */