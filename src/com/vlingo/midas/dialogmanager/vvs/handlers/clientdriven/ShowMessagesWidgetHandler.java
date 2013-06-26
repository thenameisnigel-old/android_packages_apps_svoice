package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.controllers.MessagesController;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.messages.SMSMMSProvider;
import com.vlingo.midas.gui.Widget;
import com.vlingo.sdk.recognition.VLAction;
import java.util.LinkedList;

public class ShowMessagesWidgetHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    ((Controller)paramVVSActionHandlerListener.getState(DialogDataType.ACTIVE_CONTROLLER));
    Controller localController = getController(MessagesController.class);
    int i = Widget.getMultiWidgetItemsUltimateMax();
    LinkedList localLinkedList = SMSMMSProvider.getInstance().getAllNewAlerts(i);
    ((MessagesController)localController).setMessageAlertQueue(localLinkedList);
    return localController.executeAction(paramVLAction, paramVVSActionHandlerListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowMessagesWidgetHandler
 * JD-Core Version:    0.6.0
 */