package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;

public class DrivingMessageReadbackBodyHiddenWidget extends MessageReadbackBodyHiddenWidget
  implements DrivingWidgetInterface
{
  private String hiddenBody = null;
  private boolean mHiddenBodyMode = false;

  public DrivingMessageReadbackBodyHiddenWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setClickable(false);
  }

  private void setNightModeGui()
  {
    if (MidasSettings.isNightMode())
    {
      if (!this.mHiddenBodyMode)
        break label38;
      setBackgroundResource(2130837874);
      setPadding(0, 0, 0, 21);
    }
    while (true)
    {
      this.contactName.setTextColor(-2039584);
      return;
      label38: setBackgroundResource(2130837875);
      this.msgBody.setTextColor(-11316397);
      setPadding(0, 0, 0, 0);
    }
  }

  public int getDecreasedHeight()
  {
    if (getResources().getConfiguration().orientation == 2);
    for (int i = getResources().getDimensionPixelSize(2131427352); ; i = 264)
      return i;
  }

  public int getProperHeight()
  {
    return 252;
  }

  public void initialize(MessageType paramMessageType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    super.initialize(paramMessageType, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
    this.msgBody.setText(2131362796);
    this.msgBody.setTextColor(-6842473);
    if ((getResources().getConfiguration().orientation == 2) && (MidasSettings.isMultiwindowedMode()))
    {
      setBackgroundResource(2130837588);
      setPadding(0, 0, 0, 0);
      this.msgBody.setVisibility(0);
    }
    for (this.mHiddenBodyMode = false; ; this.mHiddenBodyMode = true)
    {
      setNightModeGui();
      return;
      setBackgroundResource(2130837587);
      setPadding(0, 0, 0, 21);
      this.msgBody.setVisibility(8);
    }
  }

  public boolean isDecreasedSize()
  {
    return false;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if ((getResources().getConfiguration().orientation == 2) && (MidasSettings.isMultiwindowedMode()))
    {
      setBackgroundResource(2130837588);
      setPadding(0, 0, 0, 0);
      this.msgBody.setVisibility(0);
    }
    for (this.mHiddenBodyMode = false; ; this.mHiddenBodyMode = true)
    {
      setNightModeGui();
      return;
      setBackgroundResource(2130837587);
      setPadding(0, 0, 0, 21);
      this.msgBody.setVisibility(8);
    }
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    return 0;
  }

  public void startAnimationTranslate(View paramView)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingMessageReadbackBodyHiddenWidget
 * JD-Core Version:    0.6.0
 */