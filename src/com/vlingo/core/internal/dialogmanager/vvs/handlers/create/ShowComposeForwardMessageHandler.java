package com.vlingo.core.internal.dialogmanager.vvs.handlers.create;

import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.sdk.recognition.VLAction;
import java.util.LinkedList;

public class ShowComposeForwardMessageHandler extends ShowComposeMessageHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    this.messages = ((LinkedList)getListener().getState(DialogDataType.MESSAGE_MATCHES));
    return super.executeAction(paramVLAction, paramVVSActionHandlerListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.create.ShowComposeForwardMessageHandler
 * JD-Core Version:    0.6.0
 */