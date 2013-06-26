package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.util.IntegratedAppInfoInterface;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class PlayMediaHandler extends VVSActionHandler
  implements DMAction.Listener
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("media");
    String str1 = VLActionUtil.getParamString(paramVLAction, "Type", false);
    if ((!StringUtils.isNullOrWhiteSpace(str1)) && (str1.equals("Radio:Play")))
    {
      Intent localIntent = new Intent();
      paramVVSActionHandlerListener.getActivityContext().sendBroadcast(localIntent);
      String str2 = ClientSuppliedValues.getFmRadioApplicationInfo().getIntentNameStart();
      ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name(str2).argument(null).extra(VLActionUtil.getParamString(paramVLAction, "Extras", false)).broadcast(VLActionUtil.getParamBool(paramVLAction, "broadcast", false, false)).queue();
    }
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.PlayMediaHandler
 * JD-Core Version:    0.6.0
 */