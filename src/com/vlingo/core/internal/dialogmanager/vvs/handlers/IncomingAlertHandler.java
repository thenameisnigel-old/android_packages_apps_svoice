package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.controllers.AlertReadoutController;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.util.AlertReadoutUtil;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class IncomingAlertHandler extends VVSActionHandler
{
  SafeReaderAlert alert;
  Queue<? extends SafeReaderAlert> alerts;
  private boolean isSilentMode;

  public IncomingAlertHandler(boolean paramBoolean)
  {
    this.isSilentMode = paramBoolean;
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    ((Controller)paramVVSActionHandlerListener.getState(DialogDataType.ACTIVE_CONTROLLER));
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.alerts.iterator();
    while (localIterator.hasNext())
    {
      SafeReaderAlert localSafeReaderAlert = (SafeReaderAlert)localIterator.next();
      if (!AlertReadoutUtil.isSMSMMSAlert(localSafeReaderAlert))
        continue;
      localLinkedList.add((SMSMMSAlert)localSafeReaderAlert);
    }
    Controller localController;
    if ((!ClientSuppliedValues.isDrivingModeEnabled()) || ((ClientSuppliedValues.isDrivingModeEnabled()) && (ClientSuppliedValues.isDrivingModeMessageNotificationEnabled())))
    {
      localController = getController(AlertReadoutController.class);
      ((AlertReadoutController)localController).setSilentMode(isSilentMode());
      ((AlertReadoutController)localController).setSenderQueue(AlertReadoutUtil.createSMSMMSSenderQueueMap(localLinkedList));
      if (!ClientSuppliedValues.isDrivingModeEnabled())
        break label164;
      ((AlertReadoutController)localController).setShowMessageBody(false);
    }
    while (true)
    {
      paramVVSActionHandlerListener.storeState(DialogDataType.ACTIVE_CONTROLLER, localController);
      localController.executeAction(paramVLAction, paramVVSActionHandlerListener);
      return false;
      label164: ((AlertReadoutController)localController).setShowMessageBody(ClientSuppliedValues.isReadMessageBodyEnabled());
    }
  }

  public void init(SafeReaderAlert paramSafeReaderAlert)
  {
    this.alert = paramSafeReaderAlert;
  }

  public void init(Queue<? extends SafeReaderAlert> paramQueue)
  {
    this.alerts = paramQueue;
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
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.IncomingAlertHandler
 * JD-Core Version:    0.6.0
 */