package com.vlingo.core.internal.phrasespotter;

import com.vlingo.core.internal.audio.MicrophoneStream;

public abstract interface PhraseSpotterListener
{
  public abstract void onPhraseDetected(String paramString);

  public abstract void onPhraseSpotted(String paramString);

  public abstract void onPhraseSpotterStarted();

  public abstract void onPhraseSpotterStopped();

  public abstract void onSeamlessPhraseSpotted(String paramString, MicrophoneStream paramMicrophoneStream);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.phrasespotter.PhraseSpotterListener
 * JD-Core Version:    0.6.0
 */