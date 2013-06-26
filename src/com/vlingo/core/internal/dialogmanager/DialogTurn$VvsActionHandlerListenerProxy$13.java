package com.vlingo.core.internal.dialogmanager;

class DialogTurn$VvsActionHandlerListenerProxy$13
  implements Runnable
{
  public void run()
  {
    if (this.val$turn == null)
      throw new IllegalStateException("Received a queue event before our first turn");
    if ((this.val$turn.getTurnNumber() == this.this$1.this$0.getTurnNumber()) && (this.val$event.shouldStopTtsReco()))
      this.this$1.interruptTurn();
    this.this$1.this$0.addEvent(this.val$event);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DialogTurn.VvsActionHandlerListenerProxy.13
 * JD-Core Version:    0.6.0
 */