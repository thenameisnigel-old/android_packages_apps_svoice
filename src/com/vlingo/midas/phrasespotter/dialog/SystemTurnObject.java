package com.vlingo.midas.phrasespotter.dialog;

import com.vlingo.midas.gui.ConversationActivity;

public class SystemTurnObject
  implements DialogObject
{
  private String text;

  public SystemTurnObject(String paramString)
  {
    this.text = paramString;
  }

  public void execute(ConversationActivity paramConversationActivity)
  {
    paramConversationActivity.showVlingoText(this.text);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.phrasespotter.dialog.SystemTurnObject
 * JD-Core Version:    0.6.0
 */