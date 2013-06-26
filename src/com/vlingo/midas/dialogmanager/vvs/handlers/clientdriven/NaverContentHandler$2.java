package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;

class NaverContentHandler$2
  implements Runnable
{
  public void run()
  {
    String str = NaverContentHandler.access$400(this.this$0).getActivityContext().getString(2131362612);
    NaverContentHandler.access$500(this.this$0).showSystemTurn(str);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.NaverContentHandler.2
 * JD-Core Version:    0.6.0
 */