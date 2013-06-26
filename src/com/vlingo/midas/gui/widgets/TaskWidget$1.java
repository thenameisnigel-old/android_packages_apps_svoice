package com.vlingo.midas.gui.widgets;

import android.content.Intent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;

class TaskWidget$1
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (!this.this$0.hasFocus())
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.BodyChange");
      localIntent.putExtra("title", TaskWidget.access$000(this.this$0).getText().toString());
      TaskWidget.access$100(this.this$0).handleIntent(localIntent, null);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.TaskWidget.1
 * JD-Core Version:    0.6.0
 */