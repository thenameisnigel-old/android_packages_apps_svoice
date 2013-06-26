package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.Widget;

public class ButtonWidget extends Widget<String>
  implements View.OnClickListener
{
  Button button;
  WidgetActionListener listener;

  public ButtonWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void initialize(String paramString, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    this.button.setText(paramString);
    CharSequence localCharSequence = this.button.getContentDescription();
    if ((localCharSequence == null) || (localCharSequence.length() == 0))
      this.button.setContentDescription(paramString);
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void onClick(View paramView)
  {
    retire();
    Intent localIntent = new Intent();
    localIntent.setAction("com.vlingo.core.internal.dialogmanager.Default");
    this.listener.handleIntent(localIntent, null);
  }

  public void onFinishInflate()
  {
    this.button = ((Button)findViewById(2131558875));
    this.button.setOnClickListener(this);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ButtonWidget
 * JD-Core Version:    0.6.0
 */