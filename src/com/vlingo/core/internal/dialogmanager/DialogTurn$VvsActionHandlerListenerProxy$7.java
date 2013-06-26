package com.vlingo.core.internal.dialogmanager;

class DialogTurn$VvsActionHandlerListenerProxy$7
  implements Runnable
{
  public void run()
  {
    this.this$1.queueEvent(this.val$event, this.val$turn);
    if (DialogTurn.access$100(this.this$1.this$0) != null)
      DialogTurn.access$100(this.this$1.this$0).sendEvent(this.val$event);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogTurn.VvsActionHandlerListenerProxy.7
 * JD-Core Version:    0.6.0
 */