package com.vlingo.core.internal.dialogmanager.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageType
{
  private String address;
  private String contactName;
  private long id = -1L;
  private String message;
  private List<RecipientType> recipients;
  private String subject;
  private String type;

  public MessageType(String paramString1, String paramString2, String paramString3)
  {
    this.message = paramString1;
    this.contactName = paramString2;
    this.address = paramString3;
    this.recipients = null;
    this.subject = null;
    this.type = null;
  }

  public void addRecipient(RecipientType paramRecipientType)
  {
    if (this.recipients == null)
    {
      this.recipients = new ArrayList();
      this.recipients.add(paramRecipientType);
    }
    while (true)
    {
      return;
      int i = 0;
      Iterator localIterator = this.recipients.iterator();
      while (localIterator.hasNext())
      {
        if (!((RecipientType)localIterator.next()).getDisplayName().equals(paramRecipientType.getDisplayName()))
          continue;
        i = 1;
      }
      if (i != 0)
        continue;
      this.recipients.add(paramRecipientType);
    }
  }

  public boolean equals(Object paramObject)
  {
    int i = 0;
    if ((paramObject == null) || (getClass() != paramObject.getClass()));
    label38: label167: 
    while (true)
    {
      return i;
      MessageType localMessageType = (MessageType)paramObject;
      if (this.address == null)
      {
        if (localMessageType.address != null)
          continue;
        if (this.contactName != null)
          break label116;
        if (localMessageType.contactName != null)
          continue;
        label52: if (this.recipients != null)
          break label133;
        if (localMessageType.recipients != null)
          continue;
        label66: if (this.type != null)
          break label150;
        if (localMessageType.type != null)
          continue;
        label80: if (this.message != null)
          break label167;
        if (localMessageType.message != null)
          continue;
      }
      label116: 
      do
      {
        i = 1;
        break;
        if (this.address.equals(localMessageType.address))
          break label38;
        break;
        if (this.contactName.equals(localMessageType.contactName))
          break label52;
        break;
        if (this.recipients.equals(localMessageType.recipients))
          break label66;
        break;
        if (this.type.equals(localMessageType.type))
          break label80;
        break;
      }
      while (this.message.equals(localMessageType.message));
    }
  }

  public String getAddress()
  {
    return this.address;
  }

  public String getContactName()
  {
    return this.contactName;
  }

  public long getId()
  {
    return this.id;
  }

  public String getMessage()
  {
    return this.message;
  }

  public List<RecipientType> getRecipients()
  {
    return this.recipients;
  }

  public String getSubject()
  {
    return this.subject;
  }

  public String getType()
  {
    return this.type;
  }

  public boolean isSimple()
  {
    return true;
  }

  public void setAddress(String paramString)
  {
    this.address = paramString;
  }

  public void setContactName(String paramString)
  {
    this.contactName = paramString;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setSubject(String paramString)
  {
    this.subject = paramString;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.types.MessageType
 * JD-Core Version:    0.6.0
 */