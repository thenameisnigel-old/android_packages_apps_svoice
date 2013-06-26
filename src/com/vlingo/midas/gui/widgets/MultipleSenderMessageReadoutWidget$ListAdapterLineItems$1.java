package com.vlingo.midas.gui.widgets;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;

class MultipleSenderMessageReadoutWidget$ListAdapterLineItems$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.vlingo.core.internal.dialogmanager.Choice");
    localIntent.putExtra("id", this.val$position);
    localIntent.putExtra("message_type", this.val$smsMMSAlert.getType());
    localIntent.putExtra("from_read_messages", "true");
    localIntent.putExtra("item_count", this.val$c);
    this.this$1.this$0.listener.handleIntent(localIntent, null);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MultipleSenderMessageReadoutWidget.ListAdapterLineItems.1
 * JD-Core Version:    0.6.0
 */