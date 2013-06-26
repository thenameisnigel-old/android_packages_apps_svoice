package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.midas.dialogmanager.controllers.AddMemoController;
import com.vlingo.sdk.recognition.VLAction;

public class ShowComposeMemoHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    return ((AddMemoController)getController(AddMemoController.class)).executeAction(paramVLAction, paramVVSActionHandlerListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowComposeMemoHandler
 * JD-Core Version:    0.6.0
 */