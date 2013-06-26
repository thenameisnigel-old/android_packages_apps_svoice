package com.vlingo.core.internal.messages;

import java.util.Comparator;

class SMSMMSProvider$1
  implements Comparator<SMSMMSAlert>
{
  public int compare(SMSMMSAlert paramSMSMMSAlert1, SMSMMSAlert paramSMSMMSAlert2)
  {
    long l1 = paramSMSMMSAlert1.getTimeStamp();
    long l2 = paramSMSMMSAlert2.getTimeStamp();
    if (this.val$ascending);
    for (int i = (int)(l1 - l2); ; i = (int)(l2 - l1))
      return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.messages.SMSMMSProvider.1
 * JD-Core Version:    0.6.0
 */