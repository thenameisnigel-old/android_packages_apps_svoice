package com.vlingo.midas.gui.widgets;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;

class ComposeSocialStatusWidget$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (ComposeSocialStatusWidget.access$100(this.this$0) != null)
    {
      ComposeSocialStatusWidget.access$202(this.this$0, true);
      this.this$0.retire();
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.Default");
      localIntent.putExtra("message", ComposeSocialStatusWidget.access$000(this.this$0).getText().toString());
      ComposeSocialStatusWidget.access$100(this.this$0).handleIntent(localIntent, null);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ComposeSocialStatusWidget.2
 * JD-Core Version:    0.6.0
 */