package com.vlingo.core.internal.messages;

public class MessageSenderSummary
{
  private SMSMMSAlert alert;
  private int count;

  public MessageSenderSummary(SMSMMSAlert paramSMSMMSAlert, int paramInt)
  {
    this.alert = paramSMSMMSAlert;
    this.count = paramInt;
  }

  public SMSMMSAlert getAlert()
  {
    return this.alert;
  }

  public int getCount()
  {
    return this.count;
  }

  public void setAlert(SMSMMSAlert paramSMSMMSAlert)
  {
    this.alert = paramSMSMMSAlert;
  }

  public void setCount(int paramInt)
  {
    this.count = paramInt;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.messages.MessageSenderSummary
 * JD-Core Version:    0.6.0
 */