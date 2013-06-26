package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.localsearch.LocalSearchAdaptor;
import com.vlingo.core.internal.util.StringUtils;

class LocalSearchHandler$1
  implements Runnable
{
  public void run()
  {
    if (StringUtils.isNullOrWhiteSpace(this.this$0.getAdaptor().getCurrentSpokenLocation()));
    for (String str = LocalSearchHandler.access$000(ResourceIdProvider.string.core_localsearch_no_location, new Object[0]); ; str = LocalSearchHandler.access$100(ResourceIdProvider.string.core_localsearch, new Object[0]))
    {
      LocalSearchHandler.access$200(this.this$0).showVlingoTextAndTTS(str, str);
      WidgetDecorator localWidgetDecorator = WidgetDecorator.makeWebSearchButton();
      LocalSearchHandler.access$300(this.this$0).showWidget(WidgetUtil.WidgetKey.ShowLocalSearchWidget, localWidgetDecorator, this.this$0.getAdaptor(), this.this$0);
      this.this$0.actionSuccess();
      LocalSearchHandler.access$400(this.this$0).finishDialog();
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.LocalSearchHandler.1
 * JD-Core Version:    0.6.0
 */