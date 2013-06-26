package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import android.content.Intent;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.DefaultWebSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.WebSearchHandler;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;

public class SearchWebPromptHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private String question;

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    this.question = paramVLAction.getParamValue("Question");
    if (!StringUtils.isNullOrWhiteSpace(this.question))
      getListener().storeState(DialogDataType.CURRENT_ACTION, this.question);
    String str1 = getString(ResourceIdProvider.string.core_qa_tts_NO_ANS_WEB_SEARCH, new Object[0]);
    getListener().showVlingoTextAndTTS(str1, str1);
    String str2 = getString(ResourceIdProvider.string.core_search_web_label_button, new Object[0]);
    getListener().showWidget(WidgetUtil.WidgetKey.ShowButton, null, str2, this);
    return false;
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    DefaultWebSearchHandler localDefaultWebSearchHandler = new DefaultWebSearchHandler();
    localDefaultWebSearchHandler.setListener(getListener());
    if (StringUtils.isNullOrWhiteSpace(this.question))
      this.question = this.turn.getUserText();
    localDefaultWebSearchHandler.executeSearchIntentFromURL(localDefaultWebSearchHandler.getWebSearchURL(this.question));
    reset();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.SearchWebPromptHandler
 * JD-Core Version:    0.6.0
 */