package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.FieldIds;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.localsearch.LocalSearchAdaptor;
import com.vlingo.core.internal.util.StringUtils;

class LocalSearchHandler$2
  implements Runnable
{
  public void run()
  {
    String str1 = this.this$0.getAdaptor().getCurrentSpokenLocation();
    if (!StringUtils.isNullOrWhiteSpace(str1))
      LocalSearchHandler.access$500(this.this$0).storeState(DialogDataType.CURRENT_ACTION, str1);
    String str2 = LocalSearchHandler.access$600(ResourceIdProvider.string.core_qa_tts_NO_ANS_WEB_SEARCH, new Object[0]);
    LocalSearchHandler.access$700(this.this$0).showSystemTurn(str2, VlingoAndroidCore.getFieldId(FieldIds.VP_MAIN_WSEARCH_PROMPT));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.LocalSearchHandler.2
 * JD-Core Version:    0.6.0
 */