package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.memo.Memo;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;

public class DrivingMemoWidget extends MemoWidget
  implements DrivingWidgetInterface
{
  private LinearLayout mMemoBody;
  private boolean mMinimized = false;
  private TextView title;

  public DrivingMemoWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public int getDecreasedHeight()
  {
    int i;
    if (getResources().getConfiguration().orientation == 2)
    {
      i = getResources().getDimensionPixelSize(2131427352);
      this.msgBody.setMaxLines(4);
    }
    while (true)
    {
      return i;
      i = 620;
    }
  }

  public int getProperHeight()
  {
    return 0;
  }

  public void initialize(Memo paramMemo, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    super.initialize(paramMemo, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
    if (MidasSettings.isNightMode())
    {
      setBackgroundResource(2130837875);
      setPadding(0, 0, 0, 0);
      this.msgBody.setTextColor(-2039584);
      this.title.setTextColor(-2039584);
      this.mMemoBody.setBackgroundResource(2130837585);
    }
  }

  public boolean isDecreasedSize()
  {
    return this.mMinimized;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (paramConfiguration.orientation == 2);
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.title = ((TextView)findViewById(2131558926));
    this.mMemoBody = ((LinearLayout)findViewById(2131558927));
    setClickable(false);
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    if (paramBoolean)
      this.msgBody.setMaxLines(2);
    while (true)
    {
      this.mMinimized = paramBoolean;
      return 0;
      this.msgBody.setMaxLines(4);
    }
  }

  public void startAnimationTranslate(View paramView)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingMemoWidget
 * JD-Core Version:    0.6.0
 */