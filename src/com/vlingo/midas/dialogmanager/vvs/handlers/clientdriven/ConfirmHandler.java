package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.sdk.recognition.VLAction;

public class ConfirmHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    Controller localController = (Controller)paramVVSActionHandlerListener.getState(DialogDataType.ACTIVE_CONTROLLER);
    if ((localController != null) && ("cmd:yes".equals(paramVLAction.getParamValue("Type"))));
    for (boolean bool = localController.executeAction(paramVLAction, paramVVSActionHandlerListener); ; bool = false)
    {
      return bool;
      getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.DEFAULT));
      getListener().finishTurn();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ConfirmHandler
 * JD-Core Version:    0.6.0
 */