package com.vlingo.sdk.tts;

public final class VLTextToSpeechRequest
{
  public static final String DEFAULT_LANGUAGE = "en-US";
  public static final int DEFAULT_MAX_WORDS = 30;
  public static final boolean DEFAULT_READ_MSG_BODY = true;
  public static final SpeechRate DEFAULT_SPEECH_RATE;
  public static final Type DEFAULT_TYPE;
  public static final VoiceType DEFAULT_VOICE = VoiceType.FEMALE;
  private String language;
  private int maxWords;
  private String msgFrom;
  private boolean msgReadBody;
  private String msgSubject;
  private SpeechRate speechRate;
  private String text;
  private Type type;
  private VoiceType voice;

  static
  {
    DEFAULT_SPEECH_RATE = SpeechRate.NORMAL;
    DEFAULT_TYPE = Type.PLAIN;
  }

  private VLTextToSpeechRequest(Builder paramBuilder)
  {
    this.language = paramBuilder.language;
    this.text = paramBuilder.text;
    this.voice = paramBuilder.voice;
    this.type = paramBuilder.type;
    this.maxWords = paramBuilder.maxWords;
    this.speechRate = paramBuilder.speechRate;
    this.msgFrom = paramBuilder.msgFrom;
    this.msgSubject = paramBuilder.msgSubject;
    this.msgReadBody = paramBuilder.msgReadBody;
  }

  public String getLanguage()
  {
    return this.language;
  }

  public int getMaxWords()
  {
    return this.maxWords;
  }

  public String getMsgFrom()
  {
    return this.msgFrom;
  }

  public boolean getMsgReadyBody()
  {
    return this.msgReadBody;
  }

  public String getMsgSubject()
  {
    return this.msgSubject;
  }

  public SpeechRate getSpeechRate()
  {
    return this.speechRate;
  }

  public String getText()
  {
    return this.text;
  }

  public Type getType()
  {
    return this.type;
  }

  public VoiceType getVoice()
  {
    return this.voice;
  }

  public static class Builder
  {
    private String language = "en-US";
    private int maxWords = 30;
    private String msgFrom = null;
    private boolean msgReadBody = true;
    private String msgSubject = null;
    private VLTextToSpeechRequest.SpeechRate speechRate = VLTextToSpeechRequest.DEFAULT_SPEECH_RATE;
    private String text = "This is an example of vlingo TTS";
    private VLTextToSpeechRequest.Type type = VLTextToSpeechRequest.DEFAULT_TYPE;
    private VLTextToSpeechRequest.VoiceType voice = VLTextToSpeechRequest.DEFAULT_VOICE;

    public VLTextToSpeechRequest build()
    {
      return new VLTextToSpeechRequest(this, null);
    }

    public Builder language(String paramString)
    {
      this.language = paramString;
      return this;
    }

    public Builder maxWords(int paramInt)
    {
      this.maxWords = paramInt;
      return this;
    }

    public Builder msgReadyBody(boolean paramBoolean)
    {
      this.msgReadBody = paramBoolean;
      return this;
    }

    public Builder msgSubject(String paramString)
    {
      this.msgSubject = paramString;
      return this;
    }

    public Builder speechRate(VLTextToSpeechRequest.SpeechRate paramSpeechRate)
    {
      this.speechRate = paramSpeechRate;
      return this;
    }

    public Builder text(String paramString)
    {
      this.text = paramString;
      return this;
    }

    public Builder type(VLTextToSpeechRequest.Type paramType)
    {
      this.type = paramType;
      return this;
    }

    public Builder voice(VLTextToSpeechRequest.VoiceType paramVoiceType)
    {
      this.voice = paramVoiceType;
      return this;
    }
  }

  public static enum SpeechRate
  {
    static
    {
      SLOW = new SpeechRate("SLOW", 1);
      NORMAL = new SpeechRate("NORMAL", 2);
      FAST = new SpeechRate("FAST", 3);
      VERY_FAST = new SpeechRate("VERY_FAST", 4);
      SpeechRate[] arrayOfSpeechRate = new SpeechRate[5];
      arrayOfSpeechRate[0] = VERY_SLOW;
      arrayOfSpeechRate[1] = SLOW;
      arrayOfSpeechRate[2] = NORMAL;
      arrayOfSpeechRate[3] = FAST;
      arrayOfSpeechRate[4] = VERY_FAST;
      $VALUES = arrayOfSpeechRate;
    }
  }

  public static enum Type
  {
    static
    {
      EMAIL = new Type("EMAIL", 1);
      SMS = new Type("SMS", 2);
      MMS = new Type("MMS", 3);
      Type[] arrayOfType = new Type[4];
      arrayOfType[0] = PLAIN;
      arrayOfType[1] = EMAIL;
      arrayOfType[2] = SMS;
      arrayOfType[3] = MMS;
      $VALUES = arrayOfType;
    }
  }

  public static enum VoiceType
  {
    static
    {
      FEMALE = new VoiceType("FEMALE", 1);
      VoiceType[] arrayOfVoiceType = new VoiceType[2];
      arrayOfVoiceType[0] = MALE;
      arrayOfVoiceType[1] = FEMALE;
      $VALUES = arrayOfVoiceType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.tts.VLTextToSpeechRequest
 * JD-Core Version:    0.6.0
 */