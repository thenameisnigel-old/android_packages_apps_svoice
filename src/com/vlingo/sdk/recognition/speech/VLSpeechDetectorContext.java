package com.vlingo.sdk.recognition.speech;

import com.vlingo.sdk.recognition.AudioSourceInfo;
import com.vlingo.sdk.recognition.AudioSourceInfo.SourceFormat;
import com.vlingo.sdk.recognition.AudioSourceInfo.SourceType;

public final class VLSpeechDetectorContext
{
  public static final float DEFAULT_MIN_VOICE_DURATION = 0.08F;
  public static final float DEFAULT_MIN_VOICE_LEVEL = 57.0F;
  public static final float DEFAULT_SILENCE_THRESHOLD = 11.0F;
  public static final float DEFAULT_VOICE_PORTION = 0.02F;
  private AudioSourceInfo audioSourceInfo;
  private float minVoiceDuration;
  private float minVoiceLevel;
  private float silenceThreshold;
  private float voicePortion;

  private VLSpeechDetectorContext(Builder paramBuilder)
  {
    this.audioSourceInfo = paramBuilder.audioSourceInfo;
    this.silenceThreshold = paramBuilder.silenceThreshold;
    this.minVoiceDuration = paramBuilder.minVoiceDuration;
    this.voicePortion = paramBuilder.voicePortion;
    this.minVoiceLevel = paramBuilder.minVoiceLevel;
  }

  public AudioSourceInfo getAudioSourceInfo()
  {
    return this.audioSourceInfo;
  }

  public float getMinVoiceDuration()
  {
    return this.minVoiceDuration;
  }

  public float getMinVoiceLevel()
  {
    return this.minVoiceLevel;
  }

  public float getSilenceThreshold()
  {
    return this.silenceThreshold;
  }

  public float getVoicePortion()
  {
    return this.voicePortion;
  }

  public static class Builder
  {
    private AudioSourceInfo audioSourceInfo = AudioSourceInfo.getDataBufferSource(AudioSourceInfo.SourceFormat.PCM_16KHZ_16BIT);
    private float minVoiceDuration = 0.08F;
    private float minVoiceLevel = 57.0F;
    private float silenceThreshold = 11.0F;
    private float voicePortion = 0.02F;

    private void validateDefined(Object paramObject)
    {
      if (paramObject == null)
        throw new IllegalArgumentException("Value cannot be null");
    }

    public Builder audioSourceInfo(AudioSourceInfo paramAudioSourceInfo)
    {
      validateDefined(paramAudioSourceInfo);
      if (paramAudioSourceInfo.getType() != AudioSourceInfo.SourceType.BUFFER)
        throw new IllegalArgumentException("Unsupported audio source, supported formats are: SourceType.BUFFER");
      AudioSourceInfo.SourceFormat localSourceFormat = paramAudioSourceInfo.getFormat();
      if ((localSourceFormat != AudioSourceInfo.SourceFormat.PCM_16KHZ_16BIT) && (localSourceFormat != AudioSourceInfo.SourceFormat.PCM_8KHZ_16BIT))
        throw new IllegalArgumentException("Unsupported audio format format, supported formats are: SourceFormat.PCM_16KHZ_16BIT, SourceFormat.PCM_8KHZ_16BIT");
      this.audioSourceInfo = paramAudioSourceInfo;
      return this;
    }

    public VLSpeechDetectorContext build()
    {
      return new VLSpeechDetectorContext(this, null);
    }

    public Builder speechDetectorParams(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      this.silenceThreshold = paramFloat1;
      this.minVoiceDuration = paramFloat2;
      this.voicePortion = paramFloat3;
      this.minVoiceLevel = paramFloat4;
      return this;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.speech.VLSpeechDetectorContext
 * JD-Core Version:    0.6.0
 */