package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.controllers.SafeReaderController;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.sdk.recognition.VLAction;
import java.util.LinkedList;

public class SafeReaderHandler extends VVSActionHandler
{
  SafeReaderAlert alert;
  LinkedList<? extends SafeReaderAlert> alerts;
  private boolean isSilentMode;

  public SafeReaderHandler(boolean paramBoolean)
  {
    this.isSilentMode = paramBoolean;
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    ((Controller)paramVVSActionHandlerListener.getState(DialogDataType.ACTIVE_CONTROLLER));
    Controller localController = getController(SafeReaderController.class);
    ((SafeReaderController)localController).setSilentMode(isSilentMode());
    ((SafeReaderController)localController).setAlertQueue(this.alerts);
    ((SafeReaderController)localController).setAlert(this.alert);
    paramVVSActionHandlerListener.storeState(DialogDataType.ACTIVE_CONTROLLER, localController);
    localController.executeAction(paramVLAction, paramVVSActionHandlerListener);
    return false;
  }

  public void init(SafeReaderAlert paramSafeReaderAlert)
  {
    this.alert = paramSafeReaderAlert;
  }

  public void init(LinkedList<? extends SafeReaderAlert> paramLinkedList)
  {
    this.alerts = paramLinkedList;
  }

  public boolean isSilentMode()
  {
    return this.isSilentMode;
  }

  public void setSilentMode(boolean paramBoolean)
  {
    this.isSilentMode = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.SafeReaderHandler
 * JD-Core Version:    0.6.0
 */