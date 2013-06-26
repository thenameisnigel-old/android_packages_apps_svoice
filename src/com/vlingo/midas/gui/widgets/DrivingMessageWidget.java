package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;

public class DrivingMessageWidget extends MessageWidget
  implements DrivingWidgetInterface
{
  private TextView contact_name;
  private TextView content;
  private boolean isMinimized = false;
  private Context mContext;
  private TextView title;

  public DrivingMessageWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    setClickable(false);
  }

  public int getDecreasedHeight()
  {
    int i = 594;
    if (getResources().getConfiguration().orientation == 2)
      i = getResources().getDimensionPixelSize(2131427352);
    return i;
  }

  public int getProperHeight()
  {
    return 0;
  }

  public void initialize(MessageType paramMessageType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    super.initialize(paramMessageType, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
    if (MidasSettings.isNightMode())
    {
      setBackgroundResource(2130837875);
      setPadding(0, 0, 0, 0);
      this.msgBody.setTextColor(-2039584);
      this.contact_name.setTextColor(-2039584);
    }
  }

  public boolean isDecreasedSize()
  {
    return this.isMinimized;
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (getResources().getConfiguration().orientation == 1)
      if (this.isMinimized)
        this.msgBody.setMaxLines(2);
    while (true)
    {
      return;
      this.msgBody.setMaxLines(6);
      continue;
      this.msgBody.setMaxLines(3);
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.contact_name = ((TextView)findViewById(2131558898));
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    this.isMinimized = paramBoolean;
    if (getResources().getConfiguration().orientation == 2)
      this.msgBody.setMaxLines(3);
    while (true)
    {
      return getLayoutParams().height;
      this.msgBody.setMaxLines(2);
    }
  }

  public void showNewMessageView(int paramInt)
  {
    ((TextView)findViewById(2131559053)).setText(paramInt + " new messages");
    this.title.setText("");
    this.content.setText("");
  }

  public void startAnimationTranslate(View paramView)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingMessageWidget
 * JD-Core Version:    0.6.0
 */