package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class NavigateHomeHandler extends VVSActionHandler
{
  private static final String ACTION_NAVIGATE_CHINESE = "com.autonavi.xmgd.action.NAVIGATE";

  public void actionSuccess()
  {
    super.actionSuccess();
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    String str = ClientSuppliedValues.getHomeAddress();
    UserLoggingEngine.getInstance().landingPageViewed("navigate");
    if (!StringUtils.isNullOrWhiteSpace(str))
    {
      unified().showSystemTurn(ResourceIdProvider.string.core_navigate_home);
      if (VlingoAndroidCore.isChineseBuild())
        ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name("com.autonavi.xmgd.action.NAVIGATE").extra("target," + str).queue();
    }
    while (true)
    {
      return false;
      ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name("android.intent.action.VIEW").argument("google.navigation:q=" + str.replaceAll(" ", "+")).broadcast(false).queue();
      continue;
      unified().showSystemTurn(ResourceIdProvider.string.core_nav_home_prompt);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.NavigateHomeHandler
 * JD-Core Version:    0.6.0
 */