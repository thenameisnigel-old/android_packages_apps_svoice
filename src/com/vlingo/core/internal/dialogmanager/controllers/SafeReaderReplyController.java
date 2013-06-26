package com.vlingo.core.internal.dialogmanager.controllers;

import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.sdk.recognition.VLAction;

public class SafeReaderReplyController extends SMSController
{
  public void executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener, MessageType paramMessageType)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    setListener(paramVVSActionHandlerListener);
    this.contactSelectedName = paramMessageType.getContactName();
    this.address = paramMessageType.getAddress();
    this.message = paramMessageType.getMessage();
    handleNeedMessage();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.SafeReaderReplyController
 * JD-Core Version:    0.6.0
 */