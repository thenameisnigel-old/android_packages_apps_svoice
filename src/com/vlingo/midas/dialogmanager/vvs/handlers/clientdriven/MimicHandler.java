package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.midas.mimic.MimicPlaybackTask;
import com.vlingo.midas.mimic.MimicRecordTask;
import com.vlingo.sdk.recognition.VLAction;

public class MimicHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("chatbot");
    DialogFlow localDialogFlow = DialogFlow.getInstance();
    localDialogFlow.queuePauseableTask(new MimicRecordTask(paramVVSActionHandlerListener.getActivityContext()));
    localDialogFlow.queuePauseableTask(new MimicPlaybackTask(paramVVSActionHandlerListener.getActivityContext()));
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.MimicHandler
 * JD-Core Version:    0.6.0
 */