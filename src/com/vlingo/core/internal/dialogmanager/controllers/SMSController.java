package com.vlingo.core.internal.dialogmanager.controllers;

import android.content.Context;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SendMessageInterface;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.types.RecipientType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.recognition.acceptedtext.SMSAcceptedText;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.DialUtil;
import com.vlingo.core.internal.util.PhoneUtil;
import com.vlingo.core.internal.util.SMSUtil;
import com.vlingo.core.internal.util.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SMSController extends VDSMSBaseController
{
  static final HashMap<VDSMSBaseController.FieldID, DialogFieldID> field_ids = new HashMap();

  static
  {
    field_ids.put(VDSMSBaseController.FieldID.CONTACT, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SMS_CONTACT));
    field_ids.put(VDSMSBaseController.FieldID.TYPE, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SMS_TYPE_NUM));
  }

  private void promptForMessage()
  {
    this.state = VDSMSBaseController.State.NEED_MESSAGE;
    String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_sms_speak_msg_tts);
    unified().showSystemTurn(str, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SMS_MSG));
    UserLoggingEngine.getInstance().landingPageViewed("car-sms-message");
  }

  protected void actionDefault()
  {
    getListener().asyncHandlerStarted();
    super.actionDefault();
  }

  public void actionFail(String paramString)
  {
    String str;
    if (!paramString.equalsIgnoreCase("cancelled"))
    {
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_sms_error_sending);
      UserLoggingEngine.getInstance().errorDisplayed(str, str);
    }
    while (true)
    {
      unified().showSystemTurn(str);
      getListener().asyncHandlerDone();
      super.actionFail(paramString);
      return;
      str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_tts_TASK_CANCELLED);
    }
  }

  public void actionSuccess()
  {
    String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_tts_SMS_SENT_CONFIRM_DEMAND);
    unified().showSystemTurn(str);
    UserLoggingEngine.getInstance().landingPageAction("sms sent", null, true);
    getListener().asyncHandlerDone();
    super.actionSuccess();
  }

  protected boolean checkHistory(ContactMatch paramContactMatch)
  {
    int i = 0;
    ContactMatch[] arrayOfContactMatch = new ContactMatch[1];
    arrayOfContactMatch[i] = paramContactMatch;
    ContactMatch localContactMatch = SMSUtil.findMatchingDisplayItemInInboxOutbox(getListener().getActivityContext(), arrayOfContactMatch);
    if (localContactMatch != null)
    {
      List localList2 = localContactMatch.getData(ContactType.SMS);
      if (localList2.size() == 1)
      {
        this.address = ((ContactData)localList2.get(0)).address;
        if (StringUtils.isNullOrWhiteSpace(this.message))
        {
          promptForMessage();
          UserLoggingEngine.getInstance().landingPageViewed("car-sms-message");
          i = 1;
        }
      }
    }
    while (true)
    {
      return i;
      promptForConfirmation();
      break;
      Object localObject = null;
      Iterator localIterator = paramContactMatch.getPhoneData().iterator();
      while (localIterator.hasNext())
      {
        ContactData localContactData = (ContactData)localIterator.next();
        if (localContactData.type != 2)
          continue;
        if (localObject == null)
        {
          localObject = localContactData;
          continue;
        }
        localObject = null;
      }
      if (localObject != null)
      {
        this.address = localObject.address;
        if (StringUtils.isNullOrWhiteSpace(this.message))
          promptForMessage();
        while (true)
        {
          i = 1;
          break;
          promptForConfirmation();
        }
      }
      List localList1 = paramContactMatch.getPhoneData();
      if (localList1.isEmpty())
        continue;
      Map localMap = DialUtil.getPhoneTypeMap(getListener().getActivityContext().getResources(), localList1, this.phoneType);
      if (!localMap.containsKey(this.phoneType))
        continue;
      this.address = ((ContactData)localMap.get(this.phoneType)).address;
      promptForConfirmation();
      i = 1;
    }
  }

  protected ContactType getCapability()
  {
    return ContactType.SMS;
  }

  protected String getContactPrompt()
  {
    return VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_sms_text_who);
  }

  protected HashMap<VDSMSBaseController.FieldID, DialogFieldID> getFieldIds()
  {
    return field_ids;
  }

  protected String getNoContactPrompt()
  {
    return VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_tts_NO_MATCH_DEMAND_MESSAGE);
  }

  protected String getType()
  {
    return "sms";
  }

  protected void handleNeedMessage()
  {
    if ((!StringUtils.isNullOrWhiteSpace(this.message)) || (PhoneUtil.phoneInUse(ApplicationAdapter.getInstance().getApplicationContext())))
      promptForConfirmation();
    while (true)
    {
      return;
      promptForMessage();
    }
  }

  protected void promptForConfirmation()
  {
    this.state = VDSMSBaseController.State.NEED_CONFIRMATION;
    String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_sms_send_confirm);
    unified().showSystemTurn(str, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SMS_MSG));
    MessageType localMessageType = new MessageType(this.message, this.contactSelectedName, this.address);
    localMessageType.addRecipient(new RecipientType(this.contactSelectedName, this.address));
    WidgetDecorator localWidgetDecorator = WidgetDecorator.makeSendButton().cancelButton();
    getListener().showWidget(WidgetUtil.WidgetKey.ComposeMessage, localWidgetDecorator, localMessageType, this);
  }

  protected void sendAction()
  {
    String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_message_sending_readout);
    getListener().showVlingoTextAndTTS(str, str);
    MessageType localMessageType = new MessageType(this.message, this.contactSelectedName, this.address);
    localMessageType.addRecipient(new RecipientType(this.contactSelectedName, this.address));
    getListener().showWidget(WidgetUtil.WidgetKey.ComposeMessage, null, localMessageType, this);
    ((SendMessageInterface)getAction(DMActionType.SEND_MESSAGE, SendMessageInterface.class)).address(this.address).message(this.message).queue();
    if (this.message != null)
      sendAcceptedText(new SMSAcceptedText(this.message, this.contactSelectedName));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.SMSController
 * JD-Core Version:    0.6.0
 */