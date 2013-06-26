package com.vlingo.core.internal.dialogmanager;

import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;

class DialogTurn$VvsActionHandlerListenerProxy$17
  implements Runnable
{
  public void run()
  {
    if (DialogTurn.access$100(this.this$1.this$0) != null)
      DialogTurn.access$100(this.this$1.this$0).showWidget(this.val$key, this.val$decorators, this.val$object, this.val$listener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogTurn.VvsActionHandlerListenerProxy.17
 * JD-Core Version:    0.6.0
 */