package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Random;

public class ChatbotSing extends VVSActionHandler
{
  private int mFileToPlayResid;

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("chatbot");
    this.mFileToPlayResid = 0;
    switch (new Random().nextInt(3))
    {
    default:
      this.mFileToPlayResid = 2131099650;
    case 0:
    case 1:
    }
    while (true)
    {
      DialogFlow.getInstance().playMedia(this.mFileToPlayResid);
      return false;
      this.mFileToPlayResid = 2131099648;
      continue;
      this.mFileToPlayResid = 2131099649;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ChatbotSing
 * JD-Core Version:    0.6.0
 */