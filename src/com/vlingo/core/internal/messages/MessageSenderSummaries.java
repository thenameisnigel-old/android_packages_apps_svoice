package com.vlingo.core.internal.messages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MessageSenderSummaries
{
  private LinkedList<MessageSenderSummary> summaries;

  public MessageSenderSummaries()
  {
    this.summaries = new LinkedList();
  }

  public MessageSenderSummaries(Queue<SMSMMSAlert> paramQueue)
  {
  }

  public MessageSenderSummaries(Queue<SMSMMSAlert> paramQueue, boolean paramBoolean)
  {
    this.summaries = new LinkedList();
    Iterator localIterator = paramQueue.iterator();
    while (localIterator.hasNext())
    {
      SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localIterator.next();
      MessageSenderSummary localMessageSenderSummary1 = null;
      if (paramBoolean)
        localMessageSenderSummary1 = getMatchedSender(localSMSMMSAlert);
      if (localMessageSenderSummary1 != null)
      {
        localMessageSenderSummary1.setCount(1 + localMessageSenderSummary1.getCount());
        continue;
      }
      MessageSenderSummary localMessageSenderSummary2 = new MessageSenderSummary(localSMSMMSAlert, 1);
      this.summaries.add(localMessageSenderSummary2);
    }
  }

  private MessageSenderSummary getMatchedSender(SMSMMSAlert paramSMSMMSAlert)
  {
    Iterator localIterator = this.summaries.iterator();
    MessageSenderSummary localMessageSenderSummary;
    do
    {
      if (!localIterator.hasNext())
        break;
      localMessageSenderSummary = (MessageSenderSummary)localIterator.next();
    }
    while (!localMessageSenderSummary.getAlert().getSenderName().equals(paramSMSMMSAlert.getSenderName()));
    while (true)
    {
      return localMessageSenderSummary;
      localMessageSenderSummary = null;
    }
  }

  public LinkedList<MessageSenderSummary> getSummaries()
  {
    return this.summaries;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.messages.MessageSenderSummaries
 * JD-Core Version:    0.6.0
 */