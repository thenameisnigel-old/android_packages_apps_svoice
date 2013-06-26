package com.vlingo.midas.dialogmanager.vvs.handlers;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.sdk.recognition.VLAction;

public class SamsungNavBaseHandler extends VVSActionHandler
{
  public void actionSuccess()
  {
    super.actionSuccess();
    Intent localIntent = new Intent("com.sec.android.action.ARRANGE_CONTROLL_BAR");
    localIntent.putExtra("com.sec.android.extra.CONTROL_BAR_POS", 1800);
    getListener().getActivityContext().sendBroadcast(localIntent);
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("navigate");
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.SamsungNavBaseHandler
 * JD-Core Version:    0.6.0
 */