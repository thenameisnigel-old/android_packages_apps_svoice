package com.vlingo.core.internal.dialogmanager.actions;

import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.util.SMSUtil.SMSSendCallback;

class SendMessageAction$1
  implements SMSUtil.SMSSendCallback
{
  public void onSMSSent(boolean paramBoolean, int paramInt)
  {
    if (!paramBoolean)
      SendMessageAction.access$000(this.this$0).actionSuccess();
    while (true)
    {
      return;
      SendMessageAction.access$200(this.this$0).actionFail(SendMessageAction.access$100(this.this$0, paramInt));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.SendMessageAction.1
 * JD-Core Version:    0.6.0
 */