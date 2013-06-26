package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.controllers.MessagesController;
import com.vlingo.core.internal.dialogmanager.controllers.SafeReaderController;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.logging.Logger;
import com.vlingo.sdk.recognition.VLAction;

public class SafeReaderReplyHandler extends VVSActionHandler
{
  private Logger log = Logger.getLogger(SafeReaderReplyHandler.class);

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    Controller localController = (Controller)paramVVSActionHandlerListener.getState(DialogDataType.ACTIVE_CONTROLLER);
    if (localController == null)
    {
      localController = getController(SafeReaderController.class);
      paramVVSActionHandlerListener.storeState(DialogDataType.ACTIVE_CONTROLLER, localController);
    }
    while (true)
    {
      localController.executeAction(paramVLAction, paramVVSActionHandlerListener);
      return false;
      if (((localController instanceof SafeReaderController)) || ((localController instanceof MessagesController)))
        continue;
      localController = getController(SafeReaderController.class);
      paramVVSActionHandlerListener.storeState(DialogDataType.ACTIVE_CONTROLLER, localController);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.SafeReaderReplyHandler
 * JD-Core Version:    0.6.0
 */