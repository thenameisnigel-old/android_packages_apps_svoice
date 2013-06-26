package com.vlingo.midas.phrasespotter.dialog;

import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.sdk.recognition.NBestData;

public class UserTurnObject
  implements DialogObject
{
  private NBestData nbest;
  private String text;

  public UserTurnObject(String paramString, NBestData paramNBestData)
  {
    this.text = paramString;
    this.nbest = paramNBestData;
  }

  public void execute(ConversationActivity paramConversationActivity)
  {
    paramConversationActivity.showUserText(this.text, this.nbest);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.phrasespotter.dialog.UserTurnObject
 * JD-Core Version:    0.6.0
 */