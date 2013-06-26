package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.location.LocationUtils;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class NavHandler extends VVSActionHandler
{
  public void actionSuccess()
  {
    super.actionSuccess();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("navigate");
    String str1 = VLActionUtil.getParamString(paramVLAction, "Query", false);
    if (!StringUtils.isNullOrWhiteSpace(str1))
    {
      ResourceIdProvider.string localstring = ResourceIdProvider.string.core_car_tts_NAVIGATE_TO;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = str1;
      String str6 = getString(localstring, arrayOfObject);
      paramVVSActionHandlerListener.showVlingoTextAndTTS(str6, str6);
    }
    String str2 = null;
    String str3 = VLActionUtil.getParamString(paramVLAction, "IntentName", true);
    String str4 = VLActionUtil.getParamString(paramVLAction, "IntentArgument", false);
    String str5 = VLActionUtil.getParamString(paramVLAction, "Extras", false);
    if (!LocationUtils.isGoogleNavAvailable())
      str2 = VLActionUtil.getParamString(paramVLAction, "ClassName", false);
    boolean bool = VLActionUtil.getParamBool(paramVLAction, "broadcast", false, false);
    ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name(str3).argument(str4).extra(str5).className(str2).broadcast(bool).queue();
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.NavHandler
 * JD-Core Version:    0.6.0
 */