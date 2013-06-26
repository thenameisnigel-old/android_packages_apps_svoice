package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.sdk.recognition.VLAction;

public class CancelActionHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    paramVVSActionHandlerListener.storeState(DialogDataType.ACTIVE_CONTROLLER, null);
    paramVVSActionHandlerListener.finishDialog();
    paramVVSActionHandlerListener.userCancel();
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.CancelActionHandler
 * JD-Core Version:    0.6.0
 */