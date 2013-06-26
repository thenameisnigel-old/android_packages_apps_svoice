package com.vlingo.core.internal.endpoints;

public enum WithSpeechSilenceDurationCategory
{
  static
  {
    MEDIUM = new WithSpeechSilenceDurationCategory("MEDIUM", 1);
    LONG = new WithSpeechSilenceDurationCategory("LONG", 2);
    WithSpeechSilenceDurationCategory[] arrayOfWithSpeechSilenceDurationCategory = new WithSpeechSilenceDurationCategory[3];
    arrayOfWithSpeechSilenceDurationCategory[0] = SHORT;
    arrayOfWithSpeechSilenceDurationCategory[1] = MEDIUM;
    arrayOfWithSpeechSilenceDurationCategory[2] = LONG;
    $VALUES = arrayOfWithSpeechSilenceDurationCategory;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.endpoints.WithSpeechSilenceDurationCategory
 * JD-Core Version:    0.6.0
 */