package com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve;

import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.events.MessageResolvedEvent;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.util.OrdinalUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.LinkedList;

public class ResolveMessageHandler extends QueryHandler
{
  private String which;

  protected boolean executeQuery(VLAction paramVLAction)
  {
    this.which = VLActionUtil.getParamString(paramVLAction, "which", false);
    new LinkedList();
    try
    {
      localLinkedList = (LinkedList)getListener().getState(DialogDataType.MESSAGE_MATCHES);
      localSMSMMSAlert = null;
      if ((localLinkedList != null) && (!localLinkedList.isEmpty()))
      {
        if (!StringUtils.isNullOrWhiteSpace(this.which))
          localSMSMMSAlert = (SMSMMSAlert)OrdinalUtil.getElementFromList(localLinkedList, this.which);
      }
      else
      {
        if (localSMSMMSAlert != null)
          break label109;
        sendEmptyEvent();
        return false;
      }
    }
    catch (ClassCastException localClassCastException)
    {
      while (true)
      {
        LinkedList localLinkedList;
        sendEmptyEvent();
        continue;
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localLinkedList.get(0);
        continue;
        label109: MessageResolvedEvent localMessageResolvedEvent = new MessageResolvedEvent(localSMSMMSAlert);
        getListener().sendEvent(localMessageResolvedEvent, this.turn);
      }
    }
  }

  protected void sendEmptyEvent()
  {
    MessageResolvedEvent localMessageResolvedEvent = new MessageResolvedEvent();
    getListener().sendEvent(localMessageResolvedEvent, this.turn);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ResolveMessageHandler
 * JD-Core Version:    0.6.0
 */