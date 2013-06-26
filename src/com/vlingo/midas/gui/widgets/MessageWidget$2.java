package com.vlingo.midas.gui.widgets;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;

class MessageWidget$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (this.this$0.listener != null)
    {
      MessageWidget.access$200(this.this$0).setVisibility(8);
      if (MessageWidget.access$000(this.this$0) != null)
        MessageWidget.access$000(this.this$0).setVisibility(8);
      this.this$0.retire();
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.Cancel");
      this.this$0.listener.handleIntent(localIntent, null);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MessageWidget.2
 * JD-Core Version:    0.6.0
 */