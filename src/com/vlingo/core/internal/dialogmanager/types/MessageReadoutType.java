package com.vlingo.core.internal.dialogmanager.types;

import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.util.StringUtils;
import java.util.Iterator;
import java.util.LinkedList;

public class MessageReadoutType
{
  private String address;
  private String displayName;
  private LinkedList<SMSMMSAlert> messagesFromSender;

  public MessageReadoutType()
  {
  }

  public MessageReadoutType(String paramString, LinkedList<SMSMMSAlert> paramLinkedList)
  {
    this.address = paramString;
    this.messagesFromSender = paramLinkedList;
  }

  public boolean equals(Object paramObject)
  {
    int i = 0;
    if ((paramObject == null) || (getClass() != paramObject.getClass()));
    while (true)
    {
      return i;
      MessageReadoutType localMessageReadoutType = (MessageReadoutType)paramObject;
      if (this.address == null)
      {
        if (localMessageReadoutType.address != null)
          continue;
        label38: if (this.displayName != null)
          break label88;
        if (localMessageReadoutType.displayName != null)
          continue;
        label52: if (this.messagesFromSender != null)
          break label105;
        if (localMessageReadoutType.messagesFromSender != null)
          continue;
      }
      label88: label105: 
      do
      {
        i = 1;
        break;
        if (this.address.equals(localMessageReadoutType.address))
          break label38;
        break;
        if (this.displayName.equals(localMessageReadoutType.displayName))
          break label52;
        break;
      }
      while (this.messagesFromSender.equals(localMessageReadoutType.messagesFromSender));
    }
  }

  public String getAddress()
  {
    return this.address;
  }

  public String getDisplayName()
  {
    return this.displayName;
  }

  public String getDisplayNameOrAddress()
  {
    if (StringUtils.isNullOrWhiteSpace(this.displayName));
    for (String str = this.address; ; str = this.displayName)
      return str;
  }

  public int getMessageCount()
  {
    if (this.messagesFromSender != null);
    for (int i = this.messagesFromSender.size(); ; i = 0)
      return i;
  }

  public LinkedList<SMSMMSAlert> getMessagesFromSender()
  {
    return this.messagesFromSender;
  }

  public long getMostRecentTimestamp()
  {
    long l = 0L;
    if (this.messagesFromSender != null)
    {
      Iterator localIterator = this.messagesFromSender.iterator();
      while (localIterator.hasNext())
      {
        SMSMMSAlert localSMSMMSAlert = (SMSMMSAlert)localIterator.next();
        if (l >= localSMSMMSAlert.getTimeStamp())
          continue;
        l = localSMSMMSAlert.getTimeStamp();
      }
    }
    return l;
  }

  public void setAddress(String paramString)
  {
    this.address = paramString;
  }

  public void setDisplayName(String paramString)
  {
    this.displayName = paramString;
  }

  public void setMessagesFromSender(LinkedList<SMSMMSAlert> paramLinkedList)
  {
    this.messagesFromSender = paramLinkedList;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.types.MessageReadoutType
 * JD-Core Version:    0.6.0
 */