package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Intent;
import android.os.Handler;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.LaunchActivityAction;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.LocalSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.DefaultWebSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.WebSearchHandler;
import com.vlingo.core.internal.localsearch.LocalSearchAdaptor;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.WebSearchUtils;

public class MidasLocalSearchHandler extends LocalSearchHandler
  implements WidgetActionListener
{
  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if ("com.vlingo.core.internal.dialogmanager.Default".equals(paramIntent.getAction()))
    {
      DefaultWebSearchHandler localDefaultWebSearchHandler = new DefaultWebSearchHandler();
      localDefaultWebSearchHandler.setListener(getListener());
      localDefaultWebSearchHandler.executeSearchIntentFromURL(localDefaultWebSearchHandler.getWebSearchURL(getAdaptor().getCurrentSearchQuery()));
    }
    while (true)
    {
      return;
      super.handleIntent(paramIntent, paramObject);
    }
  }

  protected void noData()
  {
    getHandler().post(new MidasLocalSearchHandler.1(this));
  }

  protected void showFailure()
  {
    String str1 = getString(ResourceIdProvider.string.core_qa_tts_NO_ANS_WEB_SEARCH, new Object[0]);
    unified().showSystemTurn(str1);
    if ((ClientSuppliedValues.handleNoResultAnswersWithGoogleSearch()) && (WebSearchUtils.isDefaultGoogleSearch()))
    {
      String str2 = getAdaptor().getCurrentSpokenSearch();
      ((LaunchActivityAction)getAction(DMActionType.LAUNCH_ACTIVITY, LaunchActivityAction.class)).enclosingPackage("com.google.android.googlequicksearchbox").activity("com.google.android.googlequicksearchbox.SearchActivity").extra("query," + str2).action("android.intent.action.WEB_SEARCH").app("Google Search").queue();
    }
    while (true)
    {
      return;
      DefaultWebSearchHandler localDefaultWebSearchHandler = new DefaultWebSearchHandler();
      localDefaultWebSearchHandler.setListener(getListener());
      localDefaultWebSearchHandler.executeSearchIntentFromURL(localDefaultWebSearchHandler.getWebSearchURL(getAdaptor().getCurrentSearchQuery()));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.MidasLocalSearchHandler
 * JD-Core Version:    0.6.0
 */