package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.util.WebSearchUtils;
import com.vlingo.sdk.recognition.VLAction;

public abstract class WebSearchHandler extends VVSActionHandler
{
  public abstract boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener);

  public void executeSearchIntentFromURL(String paramString)
  {
    UserLoggingEngine.getInstance().landingPageViewed("web");
    ((ExecuteIntentAction)getAction(DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name("android.intent.action.VIEW").argument(paramString).queue();
    getListener().storeState(DialogDataType.PARSE_TYPE, null);
  }

  public String getWebSearchURL(String paramString)
  {
    Object localObject = null;
    String str1 = (String)getListener().getState(DialogDataType.PARSE_TYPE);
    if (!StringUtils.isNullOrWhiteSpace(str1))
    {
      String str2 = str1.replace("wsearch:", "");
      if (!str2.equals("def"))
        localObject = str2;
    }
    return WebSearchUtils.getDefaultSearchString(paramString, localObject);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.WebSearchHandler
 * JD-Core Version:    0.6.0
 */