package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.controllers.AlertReadoutController;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.messages.SMSMMSProvider;
import com.vlingo.core.internal.util.AlertReadoutUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.HashMap;

public class AlertReadbackHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    ((Controller)paramVVSActionHandlerListener.getState(DialogDataType.ACTIVE_CONTROLLER));
    Controller localController = getController(AlertReadoutController.class);
    HashMap localHashMap = AlertReadoutUtil.createSMSMMSSenderQueueMap(SMSMMSProvider.getInstance().getAllNewAlerts(0));
    String str = VLActionUtil.getParamString(paramVLAction, "Contact", false);
    if (!StringUtils.isNullOrWhiteSpace(str))
      localHashMap = AlertReadoutUtil.getMessageQueueByContactName(localHashMap, str);
    ((AlertReadoutController)localController).setCurrentContactSearch(str);
    ((AlertReadoutController)localController).setSenderQueue(localHashMap);
    ((AlertReadoutController)localController).setShowMessageBody(true);
    return localController.executeAction(paramVLAction, paramVVSActionHandlerListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.AlertReadbackHandler
 * JD-Core Version:    0.6.0
 */