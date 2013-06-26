package com.vlingo.midas.dialogmanager.actions;

import com.vlingo.core.internal.phrasespotter.PhraseSpotter;

class PlayOutNewsMultiAction$PlayOutNewsAudioPlaybackListener$1
  implements Runnable
{
  public void run()
  {
    PhraseSpotter localPhraseSpotter = PhraseSpotter.getInstance();
    localPhraseSpotter.stopPhraseSpotting();
    localPhraseSpotter.startPhraseSpotting();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.PlayOutNewsMultiAction.PlayOutNewsAudioPlaybackListener.1
 * JD-Core Version:    0.6.0
 */