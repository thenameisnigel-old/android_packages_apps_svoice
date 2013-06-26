package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.actions.ExecuteIntentAction;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.localsearch.LocalSearchAdaptor;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.util.WebSearchUtils;
import com.vlingo.sdk.internal.deviceinfo.PhoneInfo;

class MidasLocalSearchHandler$1
  implements Runnable
{
  public void run()
  {
    String str1 = this.this$0.getAdaptor().getCurrentSearchQuery();
    if (!StringUtils.isNullOrWhiteSpace(str1))
      MidasLocalSearchHandler.access$000(this.this$0).storeState(DialogDataType.CURRENT_ACTION, str1);
    if (!"US".equals(PhoneInfo.getInstance().getCarrierCountry()))
    {
      Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = str1;
      String str4 = localContext.getString(2131362582, arrayOfObject);
      MidasLocalSearchHandler.access$100(this.this$0).showSystemTurn(str4);
      String str5 = WebSearchUtils.getDefaultSearchString(str1, null);
      ((ExecuteIntentAction)MidasLocalSearchHandler.access$200(this.this$0, DMActionType.EXECUTE_INTENT, ExecuteIntentAction.class)).name("android.intent.action.VIEW").argument(str5).queue();
    }
    while (true)
    {
      return;
      String str2 = ApplicationAdapter.getInstance().getApplicationContext().getString(2131362581);
      MidasLocalSearchHandler.access$300(this.this$0).showSystemTurn(str2);
      String str3 = MidasLocalSearchHandler.access$400(ResourceIdProvider.string.core_search_web_label_button, new Object[0]);
      MidasLocalSearchHandler.access$500(this.this$0).showWidget(WidgetUtil.WidgetKey.ShowButton, null, str3, this.this$0);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.MidasLocalSearchHandler.1
 * JD-Core Version:    0.6.0
 */