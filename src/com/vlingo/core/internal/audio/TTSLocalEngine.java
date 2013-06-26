package com.vlingo.core.internal.audio;

import android.speech.tts.TextToSpeech.EngineInfo;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public abstract class TTSLocalEngine
{
  public abstract List<TextToSpeech.EngineInfo> getEngines();

  public abstract Object getLanguage();

  public abstract int setLanguage(Locale paramLocale);

  public abstract int setOnUtteranceCompletedListener(TextToSpeech.OnUtteranceCompletedListener paramOnUtteranceCompletedListener);

  public abstract void setSpeechRate(float paramFloat);

  public abstract void shutdown();

  public abstract int stop();

  public abstract int synthesizeToFile(String paramString1, HashMap<String, String> paramHashMap, String paramString2);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.TTSLocalEngine
 * JD-Core Version:    0.6.0
 */