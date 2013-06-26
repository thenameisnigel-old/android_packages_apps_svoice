package com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven;

import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.actions.LaunchActivityAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.util.WebSearchUtils;
import com.vlingo.sdk.recognition.VLAction;

public class DefaultWebSearchHandler extends WebSearchHandler
{
  private String url = null;

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    setListener(paramVVSActionHandlerListener);
    String str1 = VLActionUtil.getParamString(paramVLAction, "query", false);
    String str2 = (String)getListener().getState(DialogDataType.PARSE_TYPE);
    String str3 = "def";
    if (!StringUtils.isNullOrWhiteSpace(str2))
      str3 = str2.replace("wsearch:", "");
    if ((ClientSuppliedValues.handleNoResultAnswersWithGoogleSearch()) && (((WebSearchUtils.isDefaultGoogleSearch()) && (str3.equalsIgnoreCase("def"))) || (str3.equalsIgnoreCase("google"))))
    {
      ((LaunchActivityAction)getAction(DMActionType.LAUNCH_ACTIVITY, LaunchActivityAction.class)).enclosingPackage("com.google.android.googlequicksearchbox").activity("com.google.android.googlequicksearchbox.SearchActivity").extra("query," + str1).action("android.intent.action.WEB_SEARCH").app("Google Search").queue();
      getListener().finishDialog();
    }
    while (true)
    {
      return false;
      this.url = getWebSearchURL(str1);
      if (this.url == null)
        continue;
      executeSearchIntentFromURL(this.url);
      this.url = null;
      getListener().finishDialog();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.DefaultWebSearchHandler
 * JD-Core Version:    0.6.0
 */