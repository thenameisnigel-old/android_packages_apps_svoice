package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class LPActionHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    boolean bool = false;
    Controller localController = (Controller)paramVVSActionHandlerListener.getState(DialogDataType.ACTIVE_CONTROLLER);
    if (localController != null)
      bool = localController.executeAction(paramVLAction, paramVVSActionHandlerListener);
    while (true)
    {
      return bool;
      String str = VLActionUtil.getParamString(paramVLAction, "Action", false);
      if ((StringUtils.isNullOrWhiteSpace(str)) || (!"exit".equals(str)))
        continue;
      paramVVSActionHandlerListener.finishDialog();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.LPActionHandler
 * JD-Core Version:    0.6.0
 */