package com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve;

import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.sdk.recognition.VLAction;

public abstract class QueryHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    if (this.turn.doesEventResolveQuery())
      sendEmptyEvent();
    for (boolean bool = false; ; bool = executeQuery(paramVLAction))
      return bool;
  }

  protected abstract boolean executeQuery(VLAction paramVLAction);

  protected abstract void sendEmptyEvent();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.QueryHandler
 * JD-Core Version:    0.6.0
 */