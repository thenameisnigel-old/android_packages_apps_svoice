package com.vlingo.sdk.recognition;

import com.vlingo.sdk.internal.util.StringUtils;

public class VLRecognitionContext
{
  public static final boolean DEFAULT_AUTO_ENDPOINTING = true;
  public static final boolean DEFAULT_AUTO_PUNCTUATION = true;
  public static final String DEFAULT_FIELD_ID = "vp_car_main";
  public static final String DEFAULT_LANGUAGE = "en-US";
  public static final float DEFAULT_MIN_VOICE_DURATION = 0.08F;
  public static final float DEFAULT_MIN_VOICE_LEVEL = 57.0F;
  public static final int DEFAULT_NOSPEECH_ENDPOINT_TIMEOUT_MS = 5000;
  public static final boolean DEFAULT_PROFANITY_FILTER = true;
  public static final float DEFAULT_SILENCE_THRESHOLD = 11.0F;
  public static final int DEFAULT_SPEECH_ENDPOINT_TIMEOUT_MS = 1500;
  public static final int DEFAULT_SPEEX_COMPLEXITY = 3;
  public static final int DEFAULT_SPEEX_QUALITY = 8;
  public static final int DEFAULT_SPEEX_VARIABLE_BITRATE = 0;
  public static final int DEFAULT_SPEEX_VOICE_ACTIVITY_DETECTION = 1;
  public static final float DEFAULT_VOICE_PORTION = 0.02F;
  public static final int MAX_AUDIO_TIME = 40000;
  public static final int MAX_ENDPOINT_TIMEOUT_MS = 10000;
  public static final int MIN_ENDPOINT_TIMEOUT_MS = 200;
  private String appName;
  private AudioSourceInfo audioSourceInfo;
  private boolean autoEndpointing;
  private boolean autoPunctuation;
  private CapitalizationMode capitalizationMode;
  private String controlName;
  private String currentText;
  private int cursorPosition;
  private String fieldID;
  private String language;
  private int maxAudioTime;
  private float minVoiceDuration;
  private float minVoiceLevel;
  private int nospeechEndpointTimeout;
  private boolean profanityFilter;
  private String screenName;
  private float silenceThreshold;
  private int speechEndpointTimeout;
  private int speexComplexity;
  private int speexQuality;
  private int speexVariableBitrate;
  private int speexVoiceActivityDetection;
  private float voicePortion;

  protected VLRecognitionContext(Builder paramBuilder)
  {
    this.language = paramBuilder.language;
    this.maxAudioTime = paramBuilder.maxAudioTime;
    this.autoEndpointing = paramBuilder.autoEndpointing;
    this.speechEndpointTimeout = paramBuilder.speechEndpointTimeout;
    this.nospeechEndpointTimeout = paramBuilder.nospeechEndpointTimeout;
    this.silenceThreshold = paramBuilder.silenceThreshold;
    this.minVoiceDuration = paramBuilder.minVoiceDuration;
    this.voicePortion = paramBuilder.voicePortion;
    this.minVoiceLevel = paramBuilder.minVoiceLevel;
    this.speexQuality = paramBuilder.speexQuality;
    this.speexVariableBitrate = paramBuilder.speexVariableBitrate;
    this.speexVoiceActivityDetection = paramBuilder.speexVoiceActivityDetection;
    this.speexComplexity = paramBuilder.speexComplexity;
    this.audioSourceInfo = paramBuilder.audioSourceInfo;
    this.autoPunctuation = paramBuilder.autoPunctuation;
    this.capitalizationMode = paramBuilder.capitalizationMode;
    this.fieldID = paramBuilder.fieldID;
    this.appName = paramBuilder.appName;
    this.screenName = paramBuilder.screenName;
    this.controlName = paramBuilder.controlName;
    this.currentText = paramBuilder.currentText;
    this.cursorPosition = paramBuilder.cursorPosition;
    this.profanityFilter = paramBuilder.profanityFilter;
  }

  public boolean autoEndpointingEnabled()
  {
    return this.autoEndpointing;
  }

  public String getAppName()
  {
    return this.appName;
  }

  public AudioSourceInfo getAudioSourceInfo()
  {
    return this.audioSourceInfo;
  }

  public boolean getAutoPunctuation()
  {
    return this.autoPunctuation;
  }

  public CapitalizationMode getCapitalizationMode()
  {
    return this.capitalizationMode;
  }

  public String getControlName()
  {
    return this.controlName;
  }

  public String getCurrentText()
  {
    return this.currentText;
  }

  public int getCursorPosition()
  {
    return this.cursorPosition;
  }

  public String getFieldID()
  {
    return this.fieldID;
  }

  public String getLanguage()
  {
    return this.language;
  }

  public int getMaxAudioTime()
  {
    return this.maxAudioTime;
  }

  public float getMinVoiceDuration()
  {
    return this.minVoiceDuration;
  }

  public float getMinVoiceLevel()
  {
    return this.minVoiceLevel;
  }

  public int getNoSpeechEndPointTimeout()
  {
    return this.nospeechEndpointTimeout;
  }

  public boolean getProfanityFilter()
  {
    return this.profanityFilter;
  }

  public String getScreenName()
  {
    return this.screenName;
  }

  public float getSilenceThreshold()
  {
    return this.silenceThreshold;
  }

  public int getSpeechEndpointTimeout()
  {
    return this.speechEndpointTimeout;
  }

  public int getSpeexComplexity()
  {
    return this.speexComplexity;
  }

  public int getSpeexQuality()
  {
    return this.speexQuality;
  }

  public int getSpeexVariableBitrate()
  {
    return this.speexVariableBitrate;
  }

  public int getSpeexVoiceActivityDetection()
  {
    return this.speexVoiceActivityDetection;
  }

  public float getVoicePortion()
  {
    return this.voicePortion;
  }

  public static class Builder
  {
    private String appName = null;
    private AudioSourceInfo audioSourceInfo;
    private boolean autoEndpointing = true;
    private boolean autoPunctuation = true;
    private VLRecognitionContext.CapitalizationMode capitalizationMode = VLRecognitionContext.CapitalizationMode.DEFAULT;
    private String controlName = null;
    private String currentText = null;
    private int cursorPosition = 0;
    private String fieldID = "vp_car_main";
    private String language = "en-US";
    private int maxAudioTime = 40000;
    private float minVoiceDuration = 0.08F;
    private float minVoiceLevel = 57.0F;
    private int nospeechEndpointTimeout = 5000;
    private boolean profanityFilter = true;
    private String screenName = null;
    private float silenceThreshold = 11.0F;
    private int speechEndpointTimeout = 1500;
    private int speexComplexity = 3;
    private int speexQuality = 8;
    private int speexVariableBitrate = 0;
    private int speexVoiceActivityDetection = 1;
    private float voicePortion = 0.02F;

    protected Builder()
    {
    }

    public Builder(AudioSourceInfo paramAudioSourceInfo)
    {
      validateDefined(paramAudioSourceInfo);
      this.audioSourceInfo = paramAudioSourceInfo;
    }

    private void validateDefined(Object paramObject)
    {
      if (paramObject == null)
        throw new IllegalArgumentException("Value cannot be null");
    }

    private void validateIntBoundary(int paramInt1, int paramInt2, int paramInt3)
    {
      if ((paramInt1 > paramInt2) || (paramInt1 < paramInt3))
        throw new IllegalArgumentException("Value must be between " + paramInt3 + " and " + paramInt2);
    }

    private void validateStringNotEmpty(String paramString)
    {
      if (StringUtils.isNullOrWhiteSpace(paramString))
        throw new IllegalArgumentException("Value cannot be null or empty");
    }

    public Builder appInfo(String paramString1, String paramString2, String paramString3)
    {
      this.appName = paramString1;
      this.screenName = paramString2;
      this.controlName = paramString3;
      return this;
    }

    public Builder audioSourceInfo(AudioSourceInfo paramAudioSourceInfo)
    {
      validateDefined(paramAudioSourceInfo);
      this.audioSourceInfo = paramAudioSourceInfo;
      return this;
    }

    public Builder autoEndPointingTimeouts(int paramInt1, int paramInt2)
    {
      validateIntBoundary(paramInt1, 10000, 200);
      validateIntBoundary(paramInt2, 10000, 200);
      this.speechEndpointTimeout = paramInt1;
      this.nospeechEndpointTimeout = paramInt2;
      return this;
    }

    public Builder autoEndpointing(boolean paramBoolean)
    {
      this.autoEndpointing = paramBoolean;
      return this;
    }

    public Builder autoPunctuation(boolean paramBoolean)
    {
      this.autoPunctuation = paramBoolean;
      return this;
    }

    public VLRecognitionContext build()
    {
      return new VLRecognitionContext(this);
    }

    public Builder capitalizationMode(VLRecognitionContext.CapitalizationMode paramCapitalizationMode)
    {
      validateDefined(paramCapitalizationMode);
      this.capitalizationMode = paramCapitalizationMode;
      return this;
    }

    public Builder currentText(String paramString)
    {
      if (paramString != null)
        this.currentText = paramString;
      return this;
    }

    public Builder cursorPosition(int paramInt)
    {
      if (paramInt >= 0)
        this.cursorPosition = paramInt;
      return this;
    }

    public Builder fieldID(String paramString)
    {
      validateStringNotEmpty(paramString);
      this.fieldID = paramString;
      return this;
    }

    public Builder language(String paramString)
    {
      validateDefined(paramString);
      this.language = paramString;
      return this;
    }

    public Builder maxAudioTime(int paramInt)
    {
      validateIntBoundary(paramInt, 40000, 0);
      this.maxAudioTime = paramInt;
      return this;
    }

    public Builder profanityFilter(boolean paramBoolean)
    {
      this.profanityFilter = paramBoolean;
      return this;
    }

    public Builder speechDetectorParams(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      this.silenceThreshold = paramFloat1;
      this.minVoiceDuration = paramFloat2;
      this.voicePortion = paramFloat3;
      this.minVoiceLevel = paramFloat4;
      return this;
    }

    public Builder speexParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.speexComplexity = paramInt1;
      this.speexQuality = paramInt2;
      this.speexVariableBitrate = paramInt3;
      this.speexVoiceActivityDetection = paramInt4;
      return this;
    }
  }

  public static enum CapitalizationMode
  {
    static
    {
      DEFAULT = new CapitalizationMode("DEFAULT", 1);
      SENTENCES = new CapitalizationMode("SENTENCES", 2);
      PROPER_NOUN = new CapitalizationMode("PROPER_NOUN", 3);
      CapitalizationMode[] arrayOfCapitalizationMode = new CapitalizationMode[4];
      arrayOfCapitalizationMode[0] = OFF;
      arrayOfCapitalizationMode[1] = DEFAULT;
      arrayOfCapitalizationMode[2] = SENTENCES;
      arrayOfCapitalizationMode[3] = PROPER_NOUN;
      $VALUES = arrayOfCapitalizationMode;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.VLRecognitionContext
 * JD-Core Version:    0.6.0
 */