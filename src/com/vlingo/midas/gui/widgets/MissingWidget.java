package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.customviews.TextView;

public class MissingWidget extends BargeInWidget<Object>
{
  TextView objectInfo;
  TextView widgetName;

  public MissingWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void initialize(Object paramObject, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.widgetName.setText(paramWidgetKey.toString());
    if (paramObject != null)
      this.objectInfo.setText(paramObject.toString());
    while (true)
    {
      return;
      this.objectInfo.setText(2131362656);
    }
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void onFinishInflate()
  {
    this.widgetName = ((TextView)findViewById(2131559062));
    this.objectInfo = ((TextView)findViewById(2131559064));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MissingWidget
 * JD-Core Version:    0.6.0
 */