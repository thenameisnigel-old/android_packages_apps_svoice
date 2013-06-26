package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.actions.SendEmailAction;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SendEmailInterface;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SendMessageInterface;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.util.DialogDataUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;
import java.security.InvalidParameterException;
import java.util.List;

public class SendMessageHandler extends VVSActionHandler
{
  private ContactData getContact(String paramString)
  {
    ContactData localContactData = DialogDataUtil.getContactDataFromId((List)getListener().getState(DialogDataType.CONTACT_MATCHES), paramString);
    if (localContactData == null)
      throw new InvalidParameterException("SendMessage Contact and Address ID doesn't match what was sent: " + paramString);
    return localContactData;
  }

  public void actionFail(String paramString)
  {
    ActionFailedEvent localActionFailedEvent = new ActionFailedEvent(paramString);
    getListener().sendEvent(localActionFailedEvent, this.turn);
  }

  public void actionSuccess()
  {
    ActionCompletedEvent localActionCompletedEvent = new ActionCompletedEvent();
    getListener().sendEvent(localActionCompletedEvent, this.turn);
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
    throws InvalidParameterException
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("car-sms-message");
    String str1 = VLActionUtil.getParamString(paramVLAction, "id", true);
    String str2 = VLActionUtil.getParamString(paramVLAction, "subject", false);
    String str3 = VLActionUtil.getParamString(paramVLAction, "message", true);
    String str4 = VLActionUtil.getParamString(paramVLAction, "capability", true);
    ContactData localContactData = getContact(str1);
    ContactType localContactType = ContactType.of(str4);
    switch (1.$SwitchMap$com$vlingo$core$internal$contacts$ContactType[localContactType.ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return false;
      ((SendMessageInterface)getAction(DMActionType.SEND_MESSAGE, SendMessageInterface.class)).address(localContactData.address).message(str3).queue();
      continue;
      ((SendEmailAction)getAction(DMActionType.SEND_MESSAGE, SendEmailAction.class)).contact(localContactData).subject(str2).message(str3).queue();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.SendMessageHandler
 * JD-Core Version:    0.6.0
 */