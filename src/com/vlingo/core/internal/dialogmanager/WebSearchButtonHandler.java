package com.vlingo.core.internal.dialogmanager;

import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.DefaultWebSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.WebSearchHandler;
import com.vlingo.sdk.recognition.VLAction;

public class WebSearchButtonHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    String str1 = getLabel();
    String str2 = paramVLAction.getParamValue("Query");
    getListener().showWidget(WidgetUtil.WidgetKey.ShowButton, null, str1, new WebSearchButtonHandler.1(this, str2));
    return false;
  }

  protected String getLabel()
  {
    return getString(ResourceIdProvider.string.core_search_web_label_button, new Object[0]);
  }

  protected void handleSearch(String paramString)
  {
    DefaultWebSearchHandler localDefaultWebSearchHandler = new DefaultWebSearchHandler();
    localDefaultWebSearchHandler.setListener(getListener());
    localDefaultWebSearchHandler.executeSearchIntentFromURL(localDefaultWebSearchHandler.getWebSearchURL(paramString));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.WebSearchButtonHandler
 * JD-Core Version:    0.6.0
 */