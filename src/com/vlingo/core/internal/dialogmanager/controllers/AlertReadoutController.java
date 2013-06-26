package com.vlingo.core.internal.dialogmanager.controllers;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.StateController;
import com.vlingo.core.internal.dialogmanager.StateController.Rule;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.events.ActionCallEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionForwardEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionReplyEvent;
import com.vlingo.core.internal.dialogmanager.types.MessageReadoutType;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.messages.SMSMMSProvider;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.util.AlertReadoutUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.OrdinalUtil;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AlertReadoutController extends StateController
  implements WidgetActionListener
{
  protected LinkedList<SMSMMSAlert> alertQueue;
  protected SafeReaderAlert currentAlert = null;
  private String currentContactSearch = null;
  private String currentOrdinalSearch = null;
  protected MessageReadoutType currentSender = null;
  private boolean isSilentMode;
  private int messagePosition = 0;
  protected LinkedList<MessageReadoutType> senderList;
  protected HashMap<String, MessageReadoutType> senderQueue;
  private boolean showMessageBody;

  private void displayMultipleMessages(boolean paramBoolean)
  {
    WidgetDecorator localWidgetDecorator;
    String str2;
    String str3;
    if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()) && (this.currentSender != null))
    {
      localWidgetDecorator = null;
      if (this.showMessageBody)
        localWidgetDecorator = WidgetDecorator.makeShowMessageBody();
      String str1 = StringUtils.formatPhoneNumberForTTS(this.currentSender.getDisplayNameOrAddress());
      if (this.alertQueue.size() <= ClientSuppliedValues.getMaxDisplayNumber())
        break label219;
      ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_readout_multi_message_overflow;
      Object[] arrayOfObject3 = new Object[3];
      arrayOfObject3[0] = Integer.valueOf(this.currentSender.getMessageCount());
      arrayOfObject3[1] = this.currentSender.getDisplayNameOrAddress();
      arrayOfObject3[2] = Integer.valueOf(ClientSuppliedValues.getMaxDisplayNumber());
      str2 = getString(localstring3, arrayOfObject3);
      ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_readout_multi_message;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Integer.valueOf(this.currentSender.getMessageCount());
      arrayOfObject2[1] = str1;
      str3 = getString(localstring2, arrayOfObject2);
      getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_READBACK_MSGCHOOSE));
      if (ClientSuppliedValues.isDrivingModeEnabled())
        break label267;
      showSystemTurn(str2, str3, false, null);
      getListener().showWidget(WidgetUtil.WidgetKey.MultipleMessageReadoutWidget, localWidgetDecorator, getMessagesForWidget(this.alertQueue), this);
      getListener().startReco();
    }
    label267: label344: 
    while (true)
    {
      return;
      label219: ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_readout_multi_message;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(this.currentSender.getMessageCount());
      arrayOfObject1[1] = this.currentSender.getDisplayNameOrAddress();
      str2 = getString(localstring1, arrayOfObject1);
      break;
      if (ClientSuppliedValues.isTalkbackOn())
        getListener().ttsAnyway(str3);
      while (true)
      {
        if ((this.senderList == null) || (this.senderList.isEmpty()))
          break label344;
        getListener().showWidget(WidgetUtil.WidgetKey.MultipleSenderMessageReadoutWidget, localWidgetDecorator, this.senderList, this);
        break;
        getListener().tts(str3);
        getListener().startReco();
      }
    }
  }

  private void displayMultipleSenders(boolean paramBoolean)
  {
    WidgetDecorator localWidgetDecorator;
    int i;
    String str1;
    String str2;
    if ((this.senderList != null) && (!this.senderList.isEmpty()))
    {
      localWidgetDecorator = null;
      if (this.showMessageBody)
        localWidgetDecorator = WidgetDecorator.makeShowMessageBody();
      i = AlertReadoutUtil.getTotalMessagesFromSenderQueue(this.senderQueue);
      if (this.senderList.size() <= ClientSuppliedValues.getRegularWidgetMax())
        break label195;
      ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_readout_multi_sender_overflow;
      Object[] arrayOfObject4 = new Object[3];
      arrayOfObject4[0] = Integer.valueOf(i);
      arrayOfObject4[1] = Integer.valueOf(this.senderList.size());
      arrayOfObject4[2] = Integer.valueOf(ClientSuppliedValues.getRegularWidgetMax());
      str1 = getString(localstring4, arrayOfObject4);
      ResourceIdProvider.string localstring5 = ResourceIdProvider.string.core_readout_multi_sender_overflow_spoken;
      Object[] arrayOfObject5 = new Object[2];
      arrayOfObject5[0] = Integer.valueOf(i);
      arrayOfObject5[1] = Integer.valueOf(this.senderList.size());
      str2 = getString(localstring5, arrayOfObject5);
      getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_READBACK_SENDERCHOOSE));
      if (ClientSuppliedValues.isDrivingModeEnabled())
        break label361;
      showSystemTurn(str1, str2, true, null);
    }
    while (true)
    {
      getListener().showWidget(WidgetUtil.WidgetKey.MultipleSenderMessageReadoutWidget, localWidgetDecorator, this.senderList, this);
      return;
      label195: if (this.senderList.size() > ClientSuppliedValues.getDrivingModeWidgetMax())
      {
        ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_readout_multi_sender_dm_overflow;
        Object[] arrayOfObject2 = new Object[3];
        arrayOfObject2[0] = Integer.valueOf(i);
        arrayOfObject2[1] = Integer.valueOf(this.senderList.size());
        arrayOfObject2[2] = Integer.valueOf(ClientSuppliedValues.getRegularWidgetMax());
        str1 = getString(localstring2, arrayOfObject2);
        ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_readout_multi_sender_overflow_spoken;
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = Integer.valueOf(i);
        arrayOfObject3[1] = Integer.valueOf(this.senderList.size());
        str2 = getString(localstring3, arrayOfObject3);
        break;
      }
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_readout_multi_sender;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(i);
      arrayOfObject1[1] = Integer.valueOf(this.senderList.size());
      str1 = getString(localstring1, arrayOfObject1);
      str2 = generateVerboseMultiSenderSpoken(this.senderList, i);
      break;
      label361: String str3 = generateVerboseMultiSenderSpoken(this.senderList, i);
      if (ClientSuppliedValues.isTalkbackOn())
      {
        getListener().ttsAnyway(str3);
        continue;
      }
      getListener().tts(str3);
      getListener().startReco();
    }
  }

  private void forward()
  {
    if ((this.currentAlert != null) && (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert)))
    {
      ActionForwardEvent localActionForwardEvent2 = new ActionForwardEvent(this.currentAlert.getId(), this.currentAlert.getSenderDisplayName(), this.currentAlert.getAddress(), this.turn.getFieldId().getFieldId());
      getListener().sendEvent(localActionForwardEvent2, this.turn);
    }
    while (true)
    {
      return;
      if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)this.alertQueue.get(0);
        ActionForwardEvent localActionForwardEvent1 = new ActionForwardEvent(localSMSMMSAlert.getId(), localSMSMMSAlert.getSenderDisplayName(), localSMSMMSAlert.getAddress(), this.turn.getFieldId().getFieldId());
        getListener().sendEvent(localActionForwardEvent1, this.turn);
        continue;
      }
    }
  }

  private LinkedList<SMSMMSAlert> generateSingleList(SMSMMSAlert paramSMSMMSAlert)
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(paramSMSMMSAlert);
    return localLinkedList;
  }

  private String generateVerboseMultiSenderSpoken(LinkedList<MessageReadoutType> paramLinkedList, int paramInt)
  {
    String str1 = "";
    int i = paramLinkedList.size();
    for (int j = 0; j < i; j++)
    {
      if (j != 0)
      {
        str1 = str1 + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_comma);
        if (j == i - 1)
        {
          String str6 = str1 + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_space);
          str1 = str6 + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_and);
        }
      }
      String str5 = str1 + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_space);
      str1 = str5 + StringUtils.formatPhoneNumberForTTS(((MessageReadoutType)paramLinkedList.get(j)).getDisplayNameOrAddress());
    }
    ResourceIdProvider localResourceIdProvider2;
    ResourceIdProvider.string localstring2;
    Object[] arrayOfObject2;
    if (i > ClientSuppliedValues.getDrivingModeWidgetMax())
    {
      ClientSuppliedValues.getDrivingModeWidgetMax();
      localResourceIdProvider2 = VlingoAndroidCore.getResourceProvider();
      localstring2 = ResourceIdProvider.string.core_readout_multi_sender_info_verbose_overflow_spoken;
      arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = Integer.valueOf(paramInt);
      arrayOfObject2[1] = Integer.valueOf(paramLinkedList.size());
      arrayOfObject2[2] = str1;
    }
    ResourceIdProvider localResourceIdProvider1;
    ResourceIdProvider.string localstring1;
    Object[] arrayOfObject1;
    for (String str2 = localResourceIdProvider2.getString(localstring2, arrayOfObject2); ; str2 = localResourceIdProvider1.getString(localstring1, arrayOfObject1))
    {
      String str3 = str2 + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_dot);
      String str4 = str3 + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_space);
      return str4 + VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_readout_multi_sender_command_spoken);
      localResourceIdProvider1 = VlingoAndroidCore.getResourceProvider();
      localstring1 = ResourceIdProvider.string.core_readout_multi_sender_info_verbose_spoken;
      arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(paramInt);
      arrayOfObject1[1] = str1;
    }
  }

  private List<MessageType> getMessagesForWidget(LinkedList<? extends SafeReaderAlert> paramLinkedList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramLinkedList != null)
    {
      Iterator localIterator = paramLinkedList.iterator();
      while (localIterator.hasNext())
      {
        SafeReaderAlert localSafeReaderAlert = (SafeReaderAlert)localIterator.next();
        if (!AlertReadoutUtil.isSMSMMSAlert(localSafeReaderAlert))
          continue;
        localArrayList.add(AlertReadoutUtil.getMessageTypeFromAlert(localSafeReaderAlert, this.showMessageBody));
      }
    }
    return localArrayList;
  }

  private void multipleMessages(boolean paramBoolean)
  {
    if (this.alertQueue.size() > 1)
      displayMultipleMessages(paramBoolean);
    while (true)
    {
      return;
      this.currentAlert = ((SafeReaderAlert)this.alertQueue.get(0));
      displaySingleMessage(paramBoolean);
    }
  }

  private void next()
  {
    if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
    {
      this.messagePosition = (1 + this.messagePosition);
      if ((this.messagePosition < this.alertQueue.size()) && (this.messagePosition >= 0))
      {
        this.currentAlert = ((SafeReaderAlert)this.alertQueue.get(this.messagePosition));
        if (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert))
          getListener().storeState(DialogDataType.MESSAGE_MATCHES, generateSingleList((SMSMMSAlert)this.currentAlert));
        displaySingleMessage(false);
      }
    }
    while (true)
    {
      return;
      noValidNextMessage();
    }
  }

  private void noValidMessages(boolean paramBoolean)
  {
    String str2;
    String str3;
    if (paramBoolean)
      if (!StringUtils.isNullOrWhiteSpace(this.currentContactSearch))
      {
        ResourceIdProvider.string localstring6 = ResourceIdProvider.string.core_message_none_from_sender;
        Object[] arrayOfObject6 = new Object[1];
        arrayOfObject6[0] = this.currentContactSearch;
        str2 = getString(localstring6, arrayOfObject6);
        ResourceIdProvider.string localstring7 = ResourceIdProvider.string.core_message_none_from_sender_spoken;
        Object[] arrayOfObject7 = new Object[1];
        arrayOfObject7[0] = this.currentContactSearch;
        str3 = getString(localstring7, arrayOfObject7);
        this.currentContactSearch = null;
        showSystemTurn(str2, str3);
        reset();
      }
    while (true)
    {
      return;
      if (!StringUtils.isNullOrWhiteSpace(this.currentOrdinalSearch))
      {
        ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_message_none_from_sender;
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = this.currentOrdinalSearch;
        str2 = getString(localstring4, arrayOfObject4);
        ResourceIdProvider.string localstring5 = ResourceIdProvider.string.core_message_none_from_sender_spoken;
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = this.currentOrdinalSearch;
        str3 = getString(localstring5, arrayOfObject5);
        this.currentOrdinalSearch = null;
        break;
      }
      str2 = getString(ResourceIdProvider.string.core_message_reading_none, new Object[0]);
      str3 = getString(ResourceIdProvider.string.core_message_reading_none_spoken, new Object[0]);
      break;
      WidgetDecorator localWidgetDecorator = WidgetDecorator.makeShowMessageBody();
      String str1;
      if (!StringUtils.isNullOrWhiteSpace(this.currentContactSearch))
      {
        ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_readout_no_match_found;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = this.currentContactSearch;
        str1 = getString(localstring3, arrayOfObject3);
        this.currentContactSearch = null;
        label236: if (ClientSuppliedValues.isDrivingModeEnabled())
          break label386;
        showSystemTurn(str1, str1, true, null);
      }
      while (true)
      {
        if ((this.alertQueue == null) || (this.alertQueue.isEmpty()))
          break label455;
        if (!ClientSuppliedValues.isDrivingModeEnabled())
          break label430;
        if ((this.senderList == null) || (this.senderList.isEmpty()))
          break;
        getListener().showWidget(WidgetUtil.WidgetKey.MultipleSenderMessageReadoutWidget, localWidgetDecorator, this.senderList, this);
        break;
        if (!StringUtils.isNullOrWhiteSpace(this.currentOrdinalSearch))
        {
          ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_readout_no_match_found;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = this.currentOrdinalSearch;
          str1 = getString(localstring2, arrayOfObject2);
          this.currentOrdinalSearch = null;
          break label236;
        }
        ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_readout_no_match_found;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = "";
        str1 = getString(localstring1, arrayOfObject1);
        break label236;
        label386: if (ClientSuppliedValues.isTalkbackOn())
        {
          getListener().ttsAnyway(str1);
          continue;
        }
        getListener().tts(str1);
        getListener().startReco();
      }
      label430: getListener().showWidget(WidgetUtil.WidgetKey.MultipleMessageReadoutWidget, localWidgetDecorator, getMessagesForWidget(this.alertQueue), this);
      continue;
      label455: if ((this.senderList == null) || (this.senderList.isEmpty()))
        continue;
      getListener().showWidget(WidgetUtil.WidgetKey.MultipleSenderMessageReadoutWidget, localWidgetDecorator, this.senderList, this);
    }
  }

  private void noValidNextMessage()
  {
    String str1 = StringUtils.formatPhoneNumberForTTS(this.currentSender.getDisplayNameOrAddress());
    String str2;
    String str3;
    if (ClientSuppliedValues.isDrivingModeEnabled())
    {
      str2 = getString(ResourceIdProvider.string.core_no_more_messages_verbose, new Object[0]);
      if (this.currentSender == null)
        break label88;
      ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_no_more_messages_spoken;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = str1;
      str3 = getString(localstring3, arrayOfObject3);
    }
    while (true)
    {
      showSystemTurn(str2, str3, true, null);
      reset();
      return;
      str2 = getString(ResourceIdProvider.string.core_no_more_messages_regular, new Object[0]);
      break;
      label88: if (this.currentAlert != null)
      {
        if (!StringUtils.isNullOrWhiteSpace(this.currentAlert.getSenderDisplayName()));
        for (String str4 = this.currentAlert.getSenderDisplayName(); ; str4 = StringUtils.formatPhoneNumberForTTS(this.currentAlert.getAddress()))
        {
          ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_no_more_messages_spoken;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = str4;
          str3 = getString(localstring2, arrayOfObject2);
          break;
        }
      }
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_no_more_messages_spoken;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = "";
      str3 = getString(localstring1, arrayOfObject1);
    }
  }

  void call()
  {
    if ((this.currentAlert != null) && (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert)))
    {
      ActionCallEvent localActionCallEvent2 = new ActionCallEvent(this.currentAlert.getSenderDisplayName(), this.currentAlert.getAddress(), this.turn.getFieldId().getFieldId());
      getListener().sendEvent(localActionCallEvent2, this.turn);
    }
    while (true)
    {
      return;
      if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)this.alertQueue.get(0);
        ActionCallEvent localActionCallEvent1 = new ActionCallEvent(localSMSMMSAlert.getSenderDisplayName(), localSMSMMSAlert.getAddress(), this.turn.getFieldId().getFieldId());
        getListener().sendEvent(localActionCallEvent1, this.turn);
        continue;
      }
    }
  }

  protected void displaySingleMessage(boolean paramBoolean)
  {
    boolean bool;
    WidgetDecorator localWidgetDecorator1;
    MessageType localMessageType;
    String str3;
    String str4;
    String str5;
    if ((this.currentAlert != null) && (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert)))
    {
      SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)this.currentAlert;
      bool = false;
      if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
        bool = AlertReadoutUtil.hasNext(this.alertQueue, this.messagePosition);
      localWidgetDecorator1 = null;
      localMessageType = AlertReadoutUtil.getMessageTypeFromAlert(this.currentAlert, this.showMessageBody);
      if (!paramBoolean)
        break label341;
      str3 = StringUtils.formatPhoneNumberForTTS(localSMSMMSAlert.getSenderName());
      if (!this.showMessageBody)
        break label236;
      Context localContext2 = ApplicationAdapter.getInstance().getApplicationContext();
      SMSMMSProvider.getInstance().markAsRead(localContext2, this.currentAlert.getId(), this.currentAlert.getType());
      str4 = getString(ResourceIdProvider.string.core_readout_single_message, new Object[0]);
      ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_readout_single_message_initial_spoken;
      Object[] arrayOfObject4 = new Object[2];
      arrayOfObject4[0] = str3;
      arrayOfObject4[1] = localMessageType.getMessage();
      str5 = getString(localstring4, arrayOfObject4);
      localWidgetDecorator1 = WidgetDecorator.makeShowMessageBody();
      getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SAFEREADER_READBACKMSG));
      if (ClientSuppliedValues.isDrivingModeEnabled())
        break label277;
      showSystemTurn(str4, str5, true, null);
      label205: if ((!ClientSuppliedValues.isReadMessageBodyEnabled()) && (!this.showMessageBody))
        break label321;
      getListener().showWidget(WidgetUtil.WidgetKey.MessageReadback, localWidgetDecorator1, localMessageType, this);
    }
    while (true)
    {
      return;
      label236: str4 = getString(ResourceIdProvider.string.core_readout_single_message_initial_hidden, new Object[0]);
      ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_readout_single_message_initial_hidden_spoken;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = str3;
      str5 = getString(localstring3, arrayOfObject3);
      break;
      label277: if (ClientSuppliedValues.isTalkbackOn())
      {
        getListener().ttsAnyway(str5);
        break label205;
      }
      getListener().tts(str5);
      getListener().startReco();
      break label205;
      label321: getListener().showWidget(WidgetUtil.WidgetKey.MessageReadbackBodyHidden, localWidgetDecorator1, localMessageType, this);
    }
    label341: WidgetDecorator localWidgetDecorator2 = WidgetDecorator.makeShowMessageBody();
    String str1;
    String str2;
    if (bool)
    {
      str1 = getString(ResourceIdProvider.string.core_readout_single_message_next, new Object[0]);
      ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_readout_single_message_next_spoken;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localMessageType.getMessage();
      str2 = getString(localstring2, arrayOfObject2);
      label391: Context localContext1 = ApplicationAdapter.getInstance().getApplicationContext();
      SMSMMSProvider.getInstance().markAsRead(localContext1, this.currentAlert.getId(), this.currentAlert.getType());
      getListener().setFieldId(VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SAFEREADER_READBACKMSG));
      if (ClientSuppliedValues.isDrivingModeEnabled())
        break label516;
      showSystemTurn(str1, str2, true, null);
    }
    while (true)
    {
      getListener().showWidget(WidgetUtil.WidgetKey.MessageReadback, localWidgetDecorator2, localMessageType, this);
      break;
      str1 = getString(ResourceIdProvider.string.core_readout_single_message, new Object[0]);
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_readout_single_message_spoken;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localMessageType.getMessage();
      str2 = getString(localstring1, arrayOfObject1);
      break label391;
      label516: if (ClientSuppliedValues.isTalkbackOn())
      {
        getListener().ttsAnyway(str2);
        continue;
      }
      getListener().tts(str2);
      getListener().startReco();
    }
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    if ((paramVLAction != null) && (paramVLAction.getName().equals("LPAction")));
    while (true)
    {
      return false;
      if ((paramVLAction != null) && (paramVLAction.getName().equals("SafereaderReply")))
      {
        reply();
        continue;
      }
      if (this.currentAlert != null)
      {
        displaySingleMessage(true);
        continue;
      }
      if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
      {
        multipleMessages(true);
        continue;
      }
      if ((this.senderQueue != null) && (!this.senderQueue.isEmpty()))
      {
        this.senderList = new LinkedList();
        this.senderList.addAll(this.senderQueue.values());
        AlertReadoutUtil.sortMessageReadoutList(this.senderList);
        if (this.senderQueue.size() > 1)
        {
          displayMultipleSenders(true);
          continue;
        }
        this.currentSender = ((MessageReadoutType)this.senderList.get(0));
        this.alertQueue = ((LinkedList)this.currentSender.getMessagesFromSender().clone());
        getListener().storeState(DialogDataType.MESSAGE_MATCHES, this.alertQueue);
        multipleMessages(true);
        continue;
      }
      noValidMessages(true);
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
    if (paramIntent != null)
    {
      if (!paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Readout"))
        break label37;
      getListener().interruptTurn();
      this.showMessageBody = true;
      displaySingleMessage(false);
    }
    while (true)
    {
      return;
      label37: if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Reply"))
      {
        getListener().interruptTurn();
        reply();
        continue;
      }
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Next"))
      {
        getListener().interruptTurn();
        next();
        continue;
      }
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Forward"))
      {
        getListener().interruptTurn();
        forward();
        continue;
      }
      if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Call"))
      {
        getListener().interruptTurn();
        call();
        continue;
      }
      if (!paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Choice"))
        continue;
      getListener().interruptTurn();
      int i = paramIntent.getIntExtra("id", -1);
      String str = paramIntent.getStringExtra("message_type");
      if ((i == -1) || (StringUtils.isNullOrWhiteSpace(str)))
        continue;
      if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)this.alertQueue.get(i);
        if (localSMSMMSAlert == null)
          continue;
        this.currentAlert = localSMSMMSAlert;
        getListener().storeState(DialogDataType.MESSAGE_MATCHES, generateSingleList(localSMSMMSAlert));
        this.showMessageBody = true;
        this.messagePosition = i;
        displaySingleMessage(false);
        continue;
      }
      if ((this.senderList == null) || (this.senderList.isEmpty()))
        continue;
      this.currentSender = ((MessageReadoutType)this.senderList.get(i));
      if (this.currentSender == null)
        continue;
      LinkedList localLinkedList = (LinkedList)this.currentSender.getMessagesFromSender().clone();
      if ((localLinkedList == null) || (localLinkedList.isEmpty()))
        continue;
      this.alertQueue = localLinkedList;
      this.showMessageBody = true;
      this.currentAlert = ((SafeReaderAlert)this.alertQueue.get(0));
      if (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert))
        getListener().storeState(DialogDataType.MESSAGE_MATCHES, generateSingleList((SMSMMSAlert)this.currentAlert));
      displaySingleMessage(false);
    }
  }

  public boolean handleLPAction(VLAction paramVLAction)
  {
    String str1 = VLActionUtil.getParamString(paramVLAction, "Action", false);
    int i;
    if (str1 != null)
      if ("message:read".equalsIgnoreCase(str1))
      {
        this.showMessageBody = true;
        if (this.currentAlert != null)
        {
          displaySingleMessage(false);
          i = 1;
        }
      }
    while (true)
    {
      return i;
      if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
      {
        this.currentAlert = ((SafeReaderAlert)this.alertQueue.get(0));
        displaySingleMessage(false);
        break;
      }
      if ((this.senderList == null) || (this.senderList.isEmpty()))
        break;
      displayMultipleSenders(false);
      break;
      if ("safereader:reply".equalsIgnoreCase(str1))
      {
        reply();
        i = 1;
        continue;
      }
      if ("message:next".equalsIgnoreCase(str1))
      {
        next();
        i = 1;
        continue;
      }
      if ("message:choose".equalsIgnoreCase(str1))
      {
        String str2 = VLActionUtil.getParamString(paramVLAction, "Which", false);
        if (!StringUtils.isNullOrWhiteSpace(str2))
        {
          this.currentOrdinalSearch = str2;
          if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
          {
            List localList = this.alertQueue.subList(0, Math.min(this.alertQueue.size(), ClientSuppliedValues.getMaxDisplayNumber()));
            if (ClientSuppliedValues.isDrivingModeEnabled());
            for (SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)OrdinalUtil.getElementFromList(this.alertQueue, str2); ; localSMSMMSAlert = (SMSMMSAlert)OrdinalUtil.getElementFromList(localList, str2))
            {
              if (localSMSMMSAlert != null)
                break label271;
              noValidMessages(false);
              i = 1;
              break;
            }
            label271: this.showMessageBody = true;
            this.currentAlert = localSMSMMSAlert;
            this.messagePosition = OrdinalUtil.ordinalToInt(str2, this.alertQueue.size());
            getListener().storeState(DialogDataType.MESSAGE_MATCHES, generateSingleList(localSMSMMSAlert));
            displaySingleMessage(false);
            i = 1;
            continue;
          }
          if ((this.senderList != null) && (!this.senderList.isEmpty()))
          {
            MessageReadoutType localMessageReadoutType = (MessageReadoutType)OrdinalUtil.getElementFromList(this.senderList.subList(0, Math.min(this.senderList.size(), ClientSuppliedValues.getMaxDisplayNumber())), str2);
            if (localMessageReadoutType == null)
            {
              noValidMessages(false);
              i = 1;
              continue;
            }
            this.showMessageBody = true;
            this.currentSender = localMessageReadoutType;
            this.alertQueue = ((LinkedList)this.currentSender.getMessagesFromSender().clone());
            this.currentAlert = ((SafeReaderAlert)this.alertQueue.get(0));
            if (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert))
              getListener().storeState(DialogDataType.MESSAGE_MATCHES, generateSingleList((SMSMMSAlert)this.currentAlert));
            displaySingleMessage(false);
            i = 1;
            continue;
          }
        }
        String str3 = VLActionUtil.getParamString(paramVLAction, "Contact", false);
        if (!StringUtils.isNullOrWhiteSpace(str3))
        {
          this.currentContactSearch = str3;
          if ((this.senderQueue != null) && (!this.senderQueue.isEmpty()))
          {
            new HashMap();
            HashMap localHashMap = AlertReadoutUtil.getMessageQueueByContactName(this.senderQueue, str3);
            if ((localHashMap != null) && (!localHashMap.isEmpty()))
            {
              this.showMessageBody = true;
              this.senderQueue = localHashMap;
              this.senderList = new LinkedList();
              this.senderList.addAll(this.senderQueue.values());
              AlertReadoutUtil.sortMessageReadoutList(this.senderList);
              if (this.senderList.size() > 1)
              {
                displayMultipleSenders(false);
                i = 1;
                continue;
              }
              this.currentSender = ((MessageReadoutType)this.senderList.get(0));
              this.alertQueue = ((LinkedList)this.currentSender.getMessagesFromSender().clone());
              this.currentAlert = ((SafeReaderAlert)this.alertQueue.get(0));
              if (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert))
                getListener().storeState(DialogDataType.MESSAGE_MATCHES, generateSingleList((SMSMMSAlert)this.currentAlert));
              displaySingleMessage(false);
              i = 1;
              continue;
            }
          }
        }
        noValidMessages(false);
        i = 1;
        continue;
      }
      if (this.currentAlert != null)
        displaySingleMessage(false);
      while (true)
      {
        i = 1;
        break;
        if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
        {
          displayMultipleMessages(false);
          continue;
        }
        if ((this.senderList == null) || (this.senderList.isEmpty()))
          continue;
        displayMultipleSenders(false);
      }
      i = 0;
    }
  }

  public boolean isSilentMode()
  {
    return this.isSilentMode;
  }

  void reply()
  {
    if ((this.currentAlert != null) && (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert)))
    {
      ActionReplyEvent localActionReplyEvent2 = new ActionReplyEvent(this.currentAlert.getId(), this.currentAlert.getSenderDisplayName(), this.currentAlert.getAddress(), this.turn.getFieldId().getFieldId());
      getListener().sendEvent(localActionReplyEvent2, this.turn);
    }
    while (true)
    {
      return;
      if ((this.alertQueue != null) && (!this.alertQueue.isEmpty()))
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)this.alertQueue.get(0);
        ActionReplyEvent localActionReplyEvent1 = new ActionReplyEvent(localSMSMMSAlert.getId(), localSMSMMSAlert.getSenderDisplayName(), localSMSMMSAlert.getAddress(), this.turn.getFieldId().getFieldId());
        getListener().sendEvent(localActionReplyEvent1, this.turn);
        continue;
      }
      unified().showSystemTurn(getString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NO_REPLY, new Object[0]));
    }
  }

  public void setCurrentContactSearch(String paramString)
  {
    this.currentContactSearch = paramString;
  }

  public void setSenderQueue(HashMap<String, MessageReadoutType> paramHashMap)
  {
    this.senderQueue = paramHashMap;
    this.alertQueue = null;
    this.senderList = null;
    this.currentAlert = null;
    this.currentSender = null;
  }

  public void setShowMessageBody(boolean paramBoolean)
  {
    this.showMessageBody = paramBoolean;
  }

  public void setSilentMode(boolean paramBoolean)
  {
    this.isSilentMode = paramBoolean;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.AlertReadoutController
 * JD-Core Version:    0.6.0
 */