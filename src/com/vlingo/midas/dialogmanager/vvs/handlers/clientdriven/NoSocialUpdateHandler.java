package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import android.content.res.Resources;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.sdk.recognition.VLAction;

public class NoSocialUpdateHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    unified().showSystemTurn(paramVVSActionHandlerListener.getActivityContext().getResources().getString(2131362576));
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.NoSocialUpdateHandler
 * JD-Core Version:    0.6.0
 */