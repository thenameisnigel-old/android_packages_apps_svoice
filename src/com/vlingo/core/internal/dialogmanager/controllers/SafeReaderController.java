package com.vlingo.core.internal.dialogmanager.controllers;

import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.StateController;
import com.vlingo.core.internal.dialogmanager.StateController.Rule;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.types.RecipientType;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.messages.SMSMMSProvider;
import com.vlingo.core.internal.recognition.acceptedtext.AcceptedText.TextType;
import com.vlingo.core.internal.recognition.acceptedtext.BaseAcceptedText;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.util.AlertReadoutUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.CorePackageInfoProvider;
import com.vlingo.core.internal.util.DialUtil;
import com.vlingo.core.internal.util.PhoneUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SafeReaderController extends StateController
  implements WidgetActionListener
{
  protected LinkedList<? extends SafeReaderAlert> alertQueue;
  protected SafeReaderAlert currentAlert = null;
  private boolean isDialingEnabled;
  private boolean isSilentMode;

  private void displayMultipleMessages()
  {
    if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
    {
      this.currentAlert = ((SafeReaderAlert)this.alertQueue.peek());
      if (this.currentAlert != null)
      {
        VVSActionBase.UnifiedPrompter localUnifiedPrompter = unified();
        ResourceIdProvider.string localstring = ResourceIdProvider.string.core_car_tts_SAFEREADER_MULTIPLE_NEW_MESSAGES;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = String.valueOf(this.alertQueue.size());
        localUnifiedPrompter.showSystemTurn(getString(localstring, arrayOfObject), VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SAFEREADER_POSTMSG));
        getListener().showWidget(WidgetUtil.WidgetKey.MultipleMessageDisplay, null, getMessagesForWidget(this.alertQueue), this);
      }
    }
  }

  private String getBodyShown(String paramString1, boolean paramBoolean, String paramString2)
  {
    String str;
    if (isSilentMode())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = paramString1;
      str = getString(localstring1, arrayOfObject1) + ". ";
    }
    while (true)
    {
      return str;
      if (this.isDialingEnabled)
      {
        if (paramBoolean)
        {
          ResourceIdProvider.string localstring5 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN;
          Object[] arrayOfObject5 = new Object[1];
          arrayOfObject5[0] = paramString2;
          str = getString(localstring5, arrayOfObject5);
          continue;
        }
        ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN;
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = paramString1;
        arrayOfObject4[1] = paramString2;
        str = getString(localstring4, arrayOfObject4);
        continue;
      }
      if (paramBoolean)
      {
        ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN_NOCALL;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = paramString2;
        str = getString(localstring3, arrayOfObject3);
        continue;
      }
      ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN_NOCALL;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramString1;
      arrayOfObject2[1] = paramString2;
      str = getString(localstring2, arrayOfObject2);
    }
  }

  private String getBodySpoken(String paramString1, boolean paramBoolean, String paramString2)
  {
    String str;
    if (isSilentMode())
      str = "";
    while (true)
    {
      return str;
      if (this.isDialingEnabled)
      {
        if (paramBoolean)
        {
          ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN;
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = paramString2;
          str = getString(localstring4, arrayOfObject4);
          continue;
        }
        ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SPOKEN;
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = paramString1;
        arrayOfObject3[1] = paramString2;
        str = getString(localstring3, arrayOfObject3);
        continue;
      }
      if (paramBoolean)
      {
        ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN_NOCALL;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = paramString2;
        str = getString(localstring2, arrayOfObject2);
        continue;
      }
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN_NOCALL;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramString1;
      arrayOfObject1[1] = paramString2;
      str = getString(localstring1, arrayOfObject1);
    }
  }

  private DialogFieldID getFieldId(boolean paramBoolean)
  {
    DialogFieldID localDialogFieldID;
    if (isSilentMode())
      localDialogFieldID = VlingoAndroidCore.getFieldId(FieldIds.DEFAULT);
    while (true)
    {
      return localDialogFieldID;
      if (paramBoolean)
      {
        localDialogFieldID = VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SAFEREADER_POSTMSG);
        continue;
      }
      localDialogFieldID = VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SAFEREADER_NEWMSG);
    }
  }

  private String getMessageBody(SMSMMSAlert paramSMSMMSAlert)
  {
    return paramSMSMMSAlert.getDisplayableMessageText(ApplicationAdapter.getInstance().getApplicationContext());
  }

  private List<? extends MessageType> getMessagesForWidget(Queue<? extends SafeReaderAlert> paramQueue)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramQueue != null)
      while (!paramQueue.isEmpty())
      {
        SafeReaderAlert localSafeReaderAlert = (SafeReaderAlert)paramQueue.poll();
        if (!AlertReadoutUtil.isSMSMMSAlert(localSafeReaderAlert))
          continue;
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localSafeReaderAlert;
        MessageType localMessageType = new MessageType(getMessageBody(localSMSMMSAlert), localSMSMMSAlert.getSenderName(), "");
        localMessageType.setId(localSMSMMSAlert.getId());
        if (localSMSMMSAlert.getType() == "SMS")
          localMessageType.setType("SMS");
        while (true)
        {
          localArrayList.add(localMessageType);
          break;
          if (localSMSMMSAlert.getType() != "MMS")
            continue;
          localMessageType.setType("MMS");
        }
      }
    return localArrayList;
  }

  private String getPromptShown(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    ResourceIdProvider.string localstring = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    localStringBuilder.append(getString(localstring, arrayOfObject)).append(". ");
    if (!isSilentMode())
    {
      if (!this.isDialingEnabled)
        break label73;
      localStringBuilder.append(getString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_CMD_READ, new Object[0]));
    }
    while (true)
    {
      return localStringBuilder.toString();
      label73: localStringBuilder.append(getString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_CMD_READ_NOCALL, new Object[0]));
    }
  }

  private String getPromptSpoken(String paramString)
  {
    String str;
    if (isSilentMode())
    {
      str = "";
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    ResourceIdProvider.string localstring = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    localStringBuilder.append(getString(localstring, arrayOfObject));
    localStringBuilder.append(". ");
    if (this.isDialingEnabled)
      localStringBuilder.append(getString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_CMD_READ, new Object[0]));
    while (true)
    {
      str = localStringBuilder.toString();
      break;
      localStringBuilder.append(getString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_CMD_READ_NOCALL, new Object[0]));
    }
  }

  private String getSenderDisplayText(SafeReaderAlert paramSafeReaderAlert)
  {
    String str;
    if (paramSafeReaderAlert == null)
      str = "";
    while (true)
    {
      return str;
      if (StringUtils.isNullOrWhiteSpace(paramSafeReaderAlert.getSenderDisplayName()))
      {
        str = paramSafeReaderAlert.getAddress();
        continue;
      }
      str = paramSafeReaderAlert.getSenderDisplayName();
    }
  }

  private void next()
  {
  }

  void callSender()
  {
    String str1;
    String str2;
    ResourceIdProvider.string localstring4;
    Object[] arrayOfObject4;
    if (this.currentAlert != null)
    {
      sendAcceptedText(new BaseAcceptedText(this.currentAlert.getSenderDisplayName(), AcceptedText.TextType.DIAL));
      PhoneUtil.turnOnSpeakerphoneIfRequired(ApplicationAdapter.getInstance().getApplicationContext());
      str1 = this.currentAlert.getSenderDisplayName();
      if (!StringUtils.isNullOrWhiteSpace(str1))
        break label153;
      String str4 = this.currentAlert.getAddress();
      ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_voicedial_call_name;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = str1;
      str2 = getString(localstring3, arrayOfObject3);
      localstring4 = ResourceIdProvider.string.core_voicedial_call_name;
      arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = DialUtil.getTTSForAddress(str4);
    }
    label153: ResourceIdProvider.string localstring2;
    Object[] arrayOfObject2;
    for (String str3 = getString(localstring4, arrayOfObject4); ; str3 = getString(localstring2, arrayOfObject2))
    {
      getListener().showVlingoTextAndTTS(str2, str3);
      DialUtil.dial(getListener().getActivityContext(), this.currentAlert.getAddress(), this, getListener());
      return;
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_voicedial_call_name;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = str1;
      str2 = getString(localstring1, arrayOfObject1);
      localstring2 = ResourceIdProvider.string.core_voicedial_call_name;
      arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = StringUtils.formatPhoneNumberForTTS(str1);
    }
  }

  protected void displayMessageBody(boolean paramBoolean)
  {
    SMSMMSAlert localSMSMMSAlert;
    String str1;
    if ((this.currentAlert != null) && (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert)))
    {
      localSMSMMSAlert = (SMSMMSAlert)this.currentAlert;
      if (AlertReadoutUtil.isMMSAlert(this.currentAlert))
      {
        str1 = localSMSMMSAlert.getSubject();
        if (StringUtils.isNullOrWhiteSpace(str1))
          str1 = getString(ResourceIdProvider.string.core_car_sms_message_empty_tts, new Object[0]);
        String str2 = this.currentAlert.getSenderDisplayName();
        if (StringUtils.isNullOrWhiteSpace(str2))
          str2 = this.currentAlert.getAddress();
        String str3 = StringUtils.formatPhoneNumberForTTS(str2);
        MessageType localMessageType = new MessageType(str1, str2, "");
        localMessageType.addRecipient(new RecipientType(str2, this.currentAlert.getAddress()));
        showSystemTurn(getBodyShown(localMessageType.getContactName(), paramBoolean, str1), getBodySpoken(str3, paramBoolean, str1), true, getFieldId(true));
        getListener().showWidget(WidgetUtil.WidgetKey.MessageReadback, null, localMessageType, this);
      }
    }
    while (true)
    {
      return;
      str1 = localSMSMMSAlert.getBody();
      break;
      if (this.currentAlert == null)
        continue;
    }
  }

  protected void displayShowMessagePrompt()
  {
    if (this.currentAlert != null)
    {
      String str1 = getString(ResourceIdProvider.string.core_car_safereader_hidden_message_body, new Object[0]);
      String str2 = this.currentAlert.getSenderDisplayName();
      if (StringUtils.isNullOrWhiteSpace(str2))
        str2 = this.currentAlert.getAddress();
      String str3 = StringUtils.formatPhoneNumberForTTS(str2);
      MessageType localMessageType = new MessageType(str1, str2, "");
      localMessageType.addRecipient(new RecipientType(this.currentAlert.getSenderDisplayName(), this.currentAlert.getAddress()));
      showSystemTurn(getPromptShown(localMessageType.getContactName()), getPromptSpoken(str3), true, getFieldId(false));
      getListener().showWidget(WidgetUtil.WidgetKey.MessageReadbackBodyHidden, null, localMessageType, this);
    }
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.isDialingEnabled = CorePackageInfoProvider.hasDialing();
    if (paramVLAction == null)
      if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()) && (!this.alertQueue.isEmpty()))
      {
        if (this.alertQueue.size() <= 1)
          break label62;
        displayMultipleMessages();
      }
    while (true)
    {
      return false;
      label62: this.currentAlert = ((SafeReaderAlert)this.alertQueue.poll());
      if (ClientSuppliedValues.isReadMessageBodyEnabled())
      {
        displayMessageBody(false);
        continue;
      }
      displayShowMessagePrompt();
      continue;
      if ((paramVLAction.getName().equals("LPAction")) || (!paramVLAction.getName().equals("SafereaderReply")))
        continue;
      reply(VLActionUtil.getParamString(paramVLAction, "Text", false));
    }
  }

  public Map<String, String> getRuleMappings()
  {
    return null;
  }

  public Map<String, StateController.Rule> getRules()
  {
    return null;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    this.isDialingEnabled = CorePackageInfoProvider.hasDialing();
    if (paramIntent != null)
    {
      if (!paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Readout"))
        break label39;
      getListener().interruptTurn();
      displayMessageBody(true);
    }
    while (true)
    {
      return;
      label39: if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Call"))
      {
        getListener().interruptTurn();
        callSender();
        continue;
      }
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Reply"))
      {
        getListener().interruptTurn();
        reply("");
        continue;
      }
      if (!paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Choice"))
        continue;
      getListener().interruptTurn();
      long l = paramIntent.getLongExtra("id", -1L);
      String str = paramIntent.getStringExtra("message_type");
      if ((l == -1L) || (StringUtils.isNullOrWhiteSpace(str)) || ((!str.equalsIgnoreCase("SMS")) && (!str.equalsIgnoreCase("MMS"))))
        continue;
      SMSMMSAlert localSMSMMSAlert = SMSMMSProvider.getInstance().findbyId(l, str, this.alertQueue);
      if (localSMSMMSAlert == null)
        continue;
      this.currentAlert = localSMSMMSAlert;
      if (ClientSuppliedValues.isReadMessageBodyEnabled())
      {
        displayMessageBody(false);
        continue;
      }
      displayShowMessagePrompt();
    }
  }

  public boolean handleLPAction(VLAction paramVLAction)
  {
    int i = 1;
    this.isDialingEnabled = CorePackageInfoProvider.hasDialing();
    String str = VLActionUtil.getParamString(paramVLAction, "Action", false);
    if (str != null)
      if ("safereader:read".equalsIgnoreCase(str))
        displayMessageBody(true);
    while (true)
    {
      return i;
      if ("safereader:call".equalsIgnoreCase(str))
      {
        callSender();
        continue;
      }
      if ("safereader:reply".equalsIgnoreCase(str))
      {
        reply(VLActionUtil.getParamString(paramVLAction, "Text", false));
        continue;
      }
      if ("safereader:next".equalsIgnoreCase(str))
      {
        next();
        continue;
      }
      i = 0;
    }
  }

  public boolean isSilentMode()
  {
    return this.isSilentMode;
  }

  public void newMessageArrived()
  {
  }

  void reply(String paramString)
  {
    if (this.currentAlert != null)
      if (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert))
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)this.currentAlert;
        SafeReaderReplyController localSafeReaderReplyController = new SafeReaderReplyController();
        getListener().storeState(DialogDataType.ACTIVE_CONTROLLER, localSafeReaderReplyController);
        MessageType localMessageType = new MessageType(paramString, localSMSMMSAlert.getSenderDisplayName(), localSMSMMSAlert.getAddress());
        localSafeReaderReplyController.executeAction(null, getListener(), localMessageType);
      }
    while (true)
    {
      return;
      unified().showSystemTurn(getString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NO_REPLY, new Object[0]));
    }
  }

  public void reset()
  {
    super.reset();
  }

  public void setAlert(SafeReaderAlert paramSafeReaderAlert)
  {
    this.currentAlert = paramSafeReaderAlert;
  }

  public void setAlertQueue(LinkedList<? extends SafeReaderAlert> paramLinkedList)
  {
    this.alertQueue = paramLinkedList;
  }

  public void setAlertQueue(LinkedList<? extends SafeReaderAlert> paramLinkedList, boolean paramBoolean)
  {
    this.alertQueue = paramLinkedList;
  }

  public void setSilentMode(boolean paramBoolean)
  {
    this.isSilentMode = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.SafeReaderController
 * JD-Core Version:    0.6.0
 */