package com.vlingo.core.internal.phrasespotter;

import com.vlingo.core.internal.audio.MicrophoneStream.AudioSourceType;
import com.vlingo.core.internal.util.StringUtils;

public class PhraseSpotterParameters
{
  private MicrophoneStream.AudioSourceType audioSourceType;
  private int chunkLengthMs;
  private CoreSpotterParameters coreSpotterParams;
  private String language;
  private int preBufferLengthMs;
  private int recorderSleepMs;
  private int seamlessTimeoutMs;

  private PhraseSpotterParameters()
  {
  }

  private PhraseSpotterParameters(Builder paramBuilder)
  {
    this.language = paramBuilder.language;
    this.chunkLengthMs = paramBuilder.chunkLengthMs;
    this.recorderSleepMs = paramBuilder.recorderSleepMs;
    this.preBufferLengthMs = paramBuilder.preBufferLengthMs;
    this.seamlessTimeoutMs = paramBuilder.seamlessTimeoutMs;
    this.audioSourceType = paramBuilder.audioSourceType;
    this.coreSpotterParams = paramBuilder.coreSpotterParams;
  }

  public MicrophoneStream.AudioSourceType getAudioSourceType()
  {
    return this.audioSourceType;
  }

  public int getChunkLength()
  {
    return this.chunkLengthMs;
  }

  public CoreSpotterParameters getCoreSpotterParams()
  {
    return this.coreSpotterParams;
  }

  public String getLanguage()
  {
    return this.language;
  }

  public int getPreBufferLength()
  {
    return this.preBufferLengthMs;
  }

  public int getRecorderSleep()
  {
    return this.recorderSleepMs;
  }

  public int getSeamlessTimeout()
  {
    return this.seamlessTimeoutMs;
  }

  public static class Builder
  {
    public static final int DEFAULT_CHUNK_LENGTH_MS = 10;
    public static final int DEFAULT_PREBUFFER_LENGTH_MS = 500;
    public static final int DEFAULT_RECORDER_SLEEP_MS = 5;
    public static final int DEFAULT_SEAMLESS_TIMEOUT_MS = 1000;
    private MicrophoneStream.AudioSourceType audioSourceType = MicrophoneStream.AudioSourceType.UNSPECIFIED;
    private int chunkLengthMs = 10;
    CoreSpotterParameters coreSpotterParams;
    private String language;
    private int preBufferLengthMs = 500;
    private int recorderSleepMs = 5;
    private int seamlessTimeoutMs = 1000;

    private Builder()
    {
    }

    public Builder(String paramString)
    {
      this();
      if (StringUtils.isNullOrWhiteSpace(paramString))
        throw new IllegalArgumentException("language cannot be null or empty");
      this.language = paramString;
      this.coreSpotterParams = null;
    }

    public Builder(String paramString, CoreSpotterParameters paramCoreSpotterParameters)
    {
      this();
      if (StringUtils.isNullOrWhiteSpace(paramString))
        throw new IllegalArgumentException("language cannot be null or empty");
      this.language = paramString;
      this.coreSpotterParams = paramCoreSpotterParameters;
    }

    public PhraseSpotterParameters build()
    {
      if (this.recorderSleepMs >= this.chunkLengthMs)
        throw new IllegalStateException("recorderSleep duration must be smaller than chunk length");
      if (this.preBufferLengthMs % this.chunkLengthMs > 0)
        throw new IllegalStateException("preBufferLength duration must be a multiple of chunk length");
      return new PhraseSpotterParameters(this, null);
    }

    public Builder setAudioSourceType(MicrophoneStream.AudioSourceType paramAudioSourceType)
    {
      this.audioSourceType = paramAudioSourceType;
      return this;
    }

    public Builder setChunkLength(int paramInt)
    {
      this.chunkLengthMs = paramInt;
      return this;
    }

    public Builder setPreBufferLength(int paramInt)
    {
      this.preBufferLengthMs = paramInt;
      return this;
    }

    public Builder setRecorderSleep(int paramInt)
    {
      this.recorderSleepMs = paramInt;
      return this;
    }

    public Builder setSeamlessTimeout(int paramInt)
    {
      this.seamlessTimeoutMs = paramInt;
      return this;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.phrasespotter.PhraseSpotterParameters
 * JD-Core Version:    0.6.0
 */