package com.vlingo.midas.gui.widgets;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;

class MessageReadbackBodyHiddenWidget$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (this.this$0.listener != null)
    {
      MessageReadbackBodyHiddenWidget.access$100(this.this$0).setVisibility(8);
      if (MessageReadbackBodyHiddenWidget.access$000(this.this$0) != null)
        MessageReadbackBodyHiddenWidget.access$000(this.this$0).setVisibility(8);
      this.this$0.retire();
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.Call");
      this.this$0.listener.handleIntent(localIntent, null);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MessageReadbackBodyHiddenWidget.2
 * JD-Core Version:    0.6.0
 */