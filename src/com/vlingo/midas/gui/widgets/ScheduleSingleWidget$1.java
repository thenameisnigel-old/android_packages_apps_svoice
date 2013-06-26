package com.vlingo.midas.gui.widgets;

import android.content.Intent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;

class ScheduleSingleWidget$1
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (!this.this$0.hasFocus())
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.BodyChange");
      localIntent.putExtra("title", ScheduleSingleWidget.access$000(this.this$0).getText().toString());
      this.this$0.mActionListener.handleIntent(localIntent, null);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ScheduleSingleWidget.1
 * JD-Core Version:    0.6.0
 */