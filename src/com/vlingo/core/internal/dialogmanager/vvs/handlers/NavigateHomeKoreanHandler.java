package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class NavigateHomeKoreanHandler extends VVSActionHandler
{
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
      ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name("android.intent.action.VIEW").argument("http://maps.google.com/maps?saddr=&daddr=" + str.replace(" ", "+")).className("com.google.android.apps.maps,com.google.android.maps.MapsActivity").broadcast(false).queue();
    }
    while (true)
    {
      return false;
      unified().showSystemTurn(ResourceIdProvider.string.core_nav_home_prompt);
    }
  }

  public void reset()
  {
    getListener().finishDialog();
    getListener().interruptTurn();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.NavigateHomeKoreanHandler
 * JD-Core Version:    0.6.0
 */