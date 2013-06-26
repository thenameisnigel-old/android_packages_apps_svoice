package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.WebSearchHandler;
import com.vlingo.sdk.recognition.VLAction;

public class ExecuteUnkownDefSearchHandler extends WebSearchHandler
{
  public void actionSuccess()
  {
    super.actionSuccess();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    setListener(paramVVSActionHandlerListener);
    executeSearchIntentFromURL(getWebSearchURL((String)paramVVSActionHandlerListener.getState(DialogDataType.CURRENT_ACTION)));
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.ExecuteUnkownDefSearchHandler
 * JD-Core Version:    0.6.0
 */