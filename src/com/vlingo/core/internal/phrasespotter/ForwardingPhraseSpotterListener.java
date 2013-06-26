package com.vlingo.core.internal.phrasespotter;

import com.vlingo.core.internal.audio.MicrophoneStream;

public class ForwardingPhraseSpotterListener
  implements PhraseSpotterListener
{
  private PhraseSpotterListener target;

  public void onPhraseDetected(String paramString)
  {
    target().onPhraseDetected(paramString);
  }

  public void onPhraseSpotted(String paramString)
  {
    target().onPhraseSpotted(paramString);
  }

  public void onPhraseSpotterStarted()
  {
    target().onPhraseSpotterStarted();
  }

  public void onPhraseSpotterStopped()
  {
    target().onPhraseSpotterStopped();
  }

  public void onSeamlessPhraseSpotted(String paramString, MicrophoneStream paramMicrophoneStream)
  {
    target().onSeamlessPhraseSpotted(paramString, paramMicrophoneStream);
  }

  public ForwardingPhraseSpotterListener target(PhraseSpotterListener paramPhraseSpotterListener)
  {
    this.target = paramPhraseSpotterListener;
    return this;
  }

  public PhraseSpotterListener target()
  {
    return this.target;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.phrasespotter.ForwardingPhraseSpotterListener
 * JD-Core Version:    0.6.0
 */