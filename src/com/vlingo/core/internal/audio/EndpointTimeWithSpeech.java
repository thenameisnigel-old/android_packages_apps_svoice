package com.vlingo.core.internal.audio;

public enum EndpointTimeWithSpeech
{
  private int endpointTimeWithSpeechMilliseconds;

  static
  {
    MEDIUM = new EndpointTimeWithSpeech("MEDIUM", 1, 750);
    LONG = new EndpointTimeWithSpeech("LONG", 2, 1750);
    DEFAULT = new EndpointTimeWithSpeech("DEFAULT", 3, MEDIUM.getEndpointTimeWithSpeechMilliseconds());
    EndpointTimeWithSpeech[] arrayOfEndpointTimeWithSpeech = new EndpointTimeWithSpeech[4];
    arrayOfEndpointTimeWithSpeech[0] = SHORT;
    arrayOfEndpointTimeWithSpeech[1] = MEDIUM;
    arrayOfEndpointTimeWithSpeech[2] = LONG;
    arrayOfEndpointTimeWithSpeech[3] = DEFAULT;
    $VALUES = arrayOfEndpointTimeWithSpeech;
  }

  private EndpointTimeWithSpeech(int paramInt)
  {
    this.endpointTimeWithSpeechMilliseconds = paramInt;
  }

  public int getEndpointTimeWithSpeechMilliseconds()
  {
    return this.endpointTimeWithSpeechMilliseconds;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.EndpointTimeWithSpeech
 * JD-Core Version:    0.6.0
 */