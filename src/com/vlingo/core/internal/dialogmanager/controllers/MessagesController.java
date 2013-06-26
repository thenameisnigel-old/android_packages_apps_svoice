package com.vlingo.core.internal.dialogmanager.controllers;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.types.RecipientType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.messages.MessageSenderSummaries;
import com.vlingo.core.internal.messages.MessageSenderSummary;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.messages.SMSMMSProvider;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.AlertReadoutUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MessagesController extends SafeReaderController
  implements WidgetActionListener
{
  private String COMMA = getString(ResourceIdProvider.string.core_comma, new Object[0]);
  private String PERIOD = getString(ResourceIdProvider.string.core_dot, new Object[0]);
  private String SPACE = " ";
  private boolean capped;
  protected LinkedList<SMSMMSAlert> messageAlertQueue;
  private MessageSenderSummaries messageSenderSummaryList = null;

  private void displayMessage()
  {
    if ((this.currentAlert != null) && (AlertReadoutUtil.isSMSMMSAlert(this.currentAlert)))
    {
      SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)this.currentAlert;
      String str1 = getMessageBody(localSMSMMSAlert);
      String str2 = localSMSMMSAlert.getSenderDisplayName();
      if (StringUtils.isNullOrWhiteSpace(str2))
        str2 = localSMSMMSAlert.getAddress();
      String str3 = StringUtils.formatPhoneNumberForTTS(str2);
      MessageType localMessageType = new MessageType(str1, str2, "");
      localMessageType.addRecipient(new RecipientType(str2, localSMSMMSAlert.getAddress()));
      StringBuilder localStringBuilder1 = new StringBuilder();
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localMessageType.getContactName();
      StringBuilder localStringBuilder2 = localStringBuilder1.append(getString(localstring1, arrayOfObject1)).append(this.PERIOD).append(this.SPACE);
      ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = str1;
      String str4 = getString(localstring2, arrayOfObject2);
      StringBuilder localStringBuilder3 = new StringBuilder();
      ResourceIdProvider.string localstring3 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = str3;
      StringBuilder localStringBuilder4 = localStringBuilder3.append(getString(localstring3, arrayOfObject3)).append(this.PERIOD).append(this.SPACE).append(this.COMMA).append(this.SPACE);
      ResourceIdProvider.string localstring4 = ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN;
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = str1;
      String str5 = getString(localstring4, arrayOfObject4);
      SMSMMSProvider.getInstance().markAsRead(ApplicationAdapter.getInstance().getApplicationContext(), this.currentAlert.getId(), this.currentAlert.getType());
      showSystemTurn(str4, str5, true, VlingoAndroidCore.getFieldId(FieldIds.VP_CAR_SAFEREADER_POSTMSG));
      getListener().showWidget(WidgetUtil.WidgetKey.MessageReadback, null, localMessageType, this);
    }
  }

  private void displayMultipleMessages(Queue<SMSMMSAlert> paramQueue, boolean paramBoolean)
  {
    boolean bool;
    if ((paramBoolean) && (Settings.getBoolean("multi.widget.client.collapse", true)))
    {
      bool = true;
      this.messageSenderSummaryList = new MessageSenderSummaries(paramQueue, bool);
      if ((this.messageSenderSummaryList != null) && (!this.messageSenderSummaryList.getSummaries().isEmpty()))
      {
        this.messageSenderSummaryList.getSummaries();
        this.currentAlert = ((MessageSenderSummary)this.messageSenderSummaryList.getSummaries().peek()).getAlert();
        if (this.currentAlert != null)
        {
          if (this.messageSenderSummaryList.getSummaries().size() != 1)
            break label129;
          systemTurnForAllFromSender(paramQueue);
        }
      }
    }
    while (true)
    {
      getListener().showWidget(WidgetUtil.WidgetKey.MultipleMessageShowWidget, null, this.messageSenderSummaryList.getSummaries(), this);
      return;
      bool = false;
      break;
      label129: systemTurnForAllMesages(paramQueue);
    }
  }

  private void displayMultipleMessagesFromSender(SMSMMSAlert paramSMSMMSAlert)
  {
    displayMultipleMessages(getNewQueueForSender(paramSMSMMSAlert.getSenderName(), this.messageAlertQueue), false);
  }

  private String getMessageBody(SMSMMSAlert paramSMSMMSAlert)
  {
    return paramSMSMMSAlert.getDisplayableMessageText(ApplicationAdapter.getInstance().getApplicationContext());
  }

  private List<? extends MessageType> getMessagesForWidget(Queue<SMSMMSAlert> paramQueue)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramQueue != null)
      while (!paramQueue.isEmpty())
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)paramQueue.poll();
        if ((!localSMSMMSAlert.isSMS()) && (!localSMSMMSAlert.isMMS()))
          continue;
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

  private Queue<SMSMMSAlert> getNewQueueForSender(String paramString, Queue<SMSMMSAlert> paramQueue)
  {
    Iterator localIterator = paramQueue.iterator();
    LinkedList localLinkedList = new LinkedList();
    while (localIterator.hasNext())
    {
      SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localIterator.next();
      if (!localSMSMMSAlert.getSenderName().equals(paramString))
        continue;
      localLinkedList.add(localSMSMMSAlert);
    }
    return localLinkedList;
  }

  private String getSenderText(SMSMMSAlert paramSMSMMSAlert)
  {
    String str;
    if (paramSMSMMSAlert == null)
      str = "";
    while (true)
    {
      return str;
      str = paramSMSMMSAlert.getSenderDisplayName();
      if (!StringUtils.isNullOrWhiteSpace(str))
        continue;
      str = paramSMSMMSAlert.getAddress();
    }
  }

  private String getSenderTtsText(SMSMMSAlert paramSMSMMSAlert)
  {
    if (paramSMSMMSAlert == null);
    String str1;
    for (String str2 = ""; ; str2 = StringUtils.formatPhoneNumberForTTS(str1))
    {
      return str2;
      str1 = paramSMSMMSAlert.getSenderDisplayName();
      if (!StringUtils.isNullOrWhiteSpace(str1))
        continue;
      str1 = paramSMSMMSAlert.getAddress();
    }
  }

  private void handleShowUnreadMessagesWidget()
  {
    if ((this.messageAlertQueue != null) && (!this.messageAlertQueue.isEmpty()))
      if (!this.messageAlertQueue.isEmpty())
      {
        if (this.messageAlertQueue.size() <= 1)
          break label48;
        displayMultipleMessages(this.messageAlertQueue, true);
      }
    while (true)
    {
      return;
      label48: this.currentAlert = ((SafeReaderAlert)this.messageAlertQueue.poll());
      displayMessage();
      continue;
      String str = getString(ResourceIdProvider.string.core_message_reading_none, new Object[0]);
      showSystemTurn(str, str);
    }
  }

  private void markAsRead(Queue<SMSMMSAlert> paramQueue)
  {
    Iterator localIterator = paramQueue.iterator();
    SMSMMSProvider localSMSMMSProvider = SMSMMSProvider.getInstance();
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    while (localIterator.hasNext())
    {
      SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localIterator.next();
      localSMSMMSProvider.markAsRead(localContext, localSMSMMSAlert.getId(), localSMSMMSAlert.getType());
    }
  }

  private void systemTurnForAllFromSender(Queue<SMSMMSAlert> paramQueue)
  {
    if (this.messageSenderSummaryList.getSummaries().size() <= 0);
    while (true)
    {
      return;
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_message_reading_multi_from_sender;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(paramQueue.size());
      arrayOfObject1[1] = getSenderText(((MessageSenderSummary)this.messageSenderSummaryList.getSummaries().peek()).getAlert());
      String str1 = getString(localstring1, arrayOfObject1);
      ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_message_reading_multi_from_sender;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Integer.valueOf(paramQueue.size());
      arrayOfObject2[1] = getSenderTtsText(((MessageSenderSummary)this.messageSenderSummaryList.getSummaries().peek()).getAlert());
      String str2 = getString(localstring2, arrayOfObject2);
      Iterator localIterator = this.messageSenderSummaryList.getSummaries().iterator();
      while (localIterator.hasNext())
      {
        SMSMMSAlert localSMSMMSAlert = ((MessageSenderSummary)localIterator.next()).getAlert();
        String str4 = str2 + this.COMMA + this.SPACE + this.PERIOD + this.SPACE;
        str2 = str4 + getString(ResourceIdProvider.string.core_alert_message, new Object[0]) + this.PERIOD + this.SPACE + getMessageBody(localSMSMMSAlert);
      }
      String str3 = str2 + this.PERIOD;
      getListener().showVlingoTextAndTTS(str1, str3);
    }
  }

  private void systemTurnForAllMesages(Queue<SMSMMSAlert> paramQueue)
  {
    if (this.messageSenderSummaryList.getSummaries().size() <= 0);
    while (true)
    {
      return;
      ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_car_tts_SAFEREADER_MULTIPLE_NEW_MESSAGES;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(paramQueue.size());
      String str1 = getString(localstring1, arrayOfObject1);
      String str2 = str1;
      Iterator localIterator = this.messageSenderSummaryList.getSummaries().iterator();
      if (localIterator.hasNext())
      {
        MessageSenderSummary localMessageSenderSummary = (MessageSenderSummary)localIterator.next();
        int i = localMessageSenderSummary.getCount();
        SMSMMSAlert localSMSMMSAlert = localMessageSenderSummary.getAlert();
        String str4 = str2 + this.COMMA + this.SPACE + this.PERIOD + this.SPACE;
        StringBuilder localStringBuilder2;
        ResourceIdProvider.string localstring3;
        Object[] arrayOfObject3;
        if (i == 1)
        {
          localStringBuilder2 = new StringBuilder().append(str4);
          localstring3 = ResourceIdProvider.string.core_message_reading_single_from_sender;
          arrayOfObject3 = new Object[2];
          arrayOfObject3[0] = Integer.valueOf(1);
          arrayOfObject3[1] = getSenderTtsText(localSMSMMSAlert);
        }
        StringBuilder localStringBuilder1;
        ResourceIdProvider.string localstring2;
        Object[] arrayOfObject2;
        for (String str5 = getString(localstring3, arrayOfObject3); ; str5 = getString(localstring2, arrayOfObject2))
        {
          str2 = str5 + this.PERIOD + this.SPACE + this.COMMA + this.SPACE + getString(ResourceIdProvider.string.core_message_reading_preface, new Object[0]) + this.SPACE + getMessageBody(localSMSMMSAlert);
          break;
          localStringBuilder1 = new StringBuilder().append(str4);
          localstring2 = ResourceIdProvider.string.core_message_reading_multi_from_sender;
          arrayOfObject2 = new Object[2];
          arrayOfObject2[0] = Integer.valueOf(i);
          arrayOfObject2[1] = getSenderTtsText(localSMSMMSAlert);
        }
      }
      String str3 = str2 + this.PERIOD;
      getListener().showVlingoTextAndTTS(str1, str3);
    }
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    if (paramVLAction != null)
    {
      if (!paramVLAction.getName().equals("ShowUnreadMessagesWidget"))
        break label32;
      handleShowUnreadMessagesWidget();
    }
    while (true)
    {
      return false;
      label32: if (!paramVLAction.getName().equals("ShowWCISWidget"))
        continue;
      handleShowUnreadMessagesWidget();
    }
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    long l;
    int i;
    String str1;
    boolean bool;
    if (paramIntent != null)
    {
      if (!paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Choice"))
        break label160;
      getListener().interruptTurn();
      l = paramIntent.getLongExtra("id", -1L);
      i = paramIntent.getIntExtra("item_count", -1);
      str1 = paramIntent.getStringExtra("message_type");
      String str2 = paramIntent.getStringExtra("from_read_messages");
      bool = false;
      if (str2 != null)
        bool = str2.equalsIgnoreCase("true");
      if ((str1.equalsIgnoreCase("SMS")) || (str1.equalsIgnoreCase("MMS")))
        break label107;
    }
    while (true)
    {
      return;
      label107: SMSMMSAlert localSMSMMSAlert = SMSMMSProvider.getInstance().findbyId(l, str1, this.messageAlertQueue);
      if (localSMSMMSAlert == null)
        continue;
      this.currentAlert = localSMSMMSAlert;
      if ((i > 1) && (bool))
      {
        displayMultipleMessagesFromSender(localSMSMMSAlert);
        continue;
      }
      displayMessage();
      continue;
      label160: if (paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Call"))
      {
        getListener().interruptTurn();
        callSender();
        continue;
      }
      if (!paramIntent.getAction().equalsIgnoreCase("com.vlingo.core.internal.dialogmanager.Reply"))
        continue;
      getListener().interruptTurn();
      reply("");
    }
  }

  public void setCapped(boolean paramBoolean)
  {
    this.capped = paramBoolean;
  }

  public void setMessageAlertQueue(LinkedList<SMSMMSAlert> paramLinkedList)
  {
    this.messageAlertQueue = paramLinkedList;
    this.capped = false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.controllers.MessagesController
 * JD-Core Version:    0.6.0
 */