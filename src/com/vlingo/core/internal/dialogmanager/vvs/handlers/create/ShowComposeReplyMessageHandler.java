package com.vlingo.core.internal.dialogmanager.vvs.handlers.create;

import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.types.RecipientType;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.sdk.recognition.VLAction;
import java.util.LinkedList;

public class ShowComposeReplyMessageHandler extends ShowComposeMessageHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    this.messages = ((LinkedList)getListener().getState(DialogDataType.MESSAGE_MATCHES));
    if ((this.messages != null) && (!this.messages.isEmpty()))
    {
      SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)this.messages.get(0);
      this.replyRecipient = new RecipientType(localSMSMMSAlert.getSenderDisplayName(), localSMSMMSAlert.getAddress());
    }
    return super.executeAction(paramVLAction, paramVVSActionHandlerListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.create.ShowComposeReplyMessageHandler
 * JD-Core Version:    0.6.0
 */