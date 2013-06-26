package com.vlingo.core.internal.safereaderimpl;

import com.vlingo.core.internal.messages.SMSMMSProvider;
import com.vlingo.core.internal.safereader.ISafeReaderAlertChangeListener;
import java.util.LinkedList;

class SMSMMSAlertSource$SMSMMSContentObserver$1
  implements Runnable
{
  public void run()
  {
    LinkedList localLinkedList = SMSMMSProvider.getInstance().getNewSafereaderAlerts(0);
    if ((localLinkedList != null) && (!localLinkedList.isEmpty()))
      SMSMMSAlertSource.SMSMMSContentObserver.access$000(this.this$1).onNewSafeReaderAlert(localLinkedList);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.safereaderimpl.SMSMMSAlertSource.SMSMMSContentObserver.1
 * JD-Core Version:    0.6.0
 */