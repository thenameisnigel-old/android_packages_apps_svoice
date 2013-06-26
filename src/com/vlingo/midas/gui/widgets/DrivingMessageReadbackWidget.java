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

public class DrivingMessageReadbackWidget extends MessageReadbackWidget
  implements DrivingWidgetInterface
{
  private String hiddenBody = null;
  private boolean isMinimized = false;
  private boolean mHiddenBodyMode = false;
  private boolean mReadout = false;

  public DrivingMessageReadbackWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
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
      setPadding(0, 0, 0, 0);
      this.msgBody.setTextColor(-11316397);
    }
  }

  public int getDecreasedHeight()
  {
    int i;
    if (getResources().getConfiguration().orientation == 2)
      i = getResources().getDimensionPixelSize(2131427352);
    while (true)
    {
      return i;
      if (this.mHiddenBodyMode)
      {
        i = 264;
        continue;
      }
      i = 594;
    }
  }

  public int getProperHeight()
  {
    this.msgBody.setMaxLines(6);
    int i;
    if (this.mHiddenBodyMode)
    {
      i = 252;
      return i;
    }
    if (getResources().getConfiguration().orientation == 1)
      this.msgBody.setMaxLines(6);
    while (true)
    {
      i = 0;
      break;
      this.msgBody.setMaxLines(3);
    }
  }

  public void initialize(MessageType paramMessageType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    super.initialize(paramMessageType, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
    if ((paramMessageType != null) && (paramMessageType.getMessage() != null))
    {
      String str = this.context.getResources().getString(2131361797);
      if (!paramMessageType.getMessage().equals(str))
        break label137;
      this.msgBody.setText(2131362796);
      this.msgBody.setTextColor(-6842473);
      if ((getResources().getConfiguration().orientation == 1) || ((getResources().getConfiguration().orientation == 2) && (!MidasSettings.isMultiwindowedMode())))
      {
        setBackgroundResource(2130837587);
        setPadding(0, 0, 0, 21);
        this.msgBody.setVisibility(8);
        this.mHiddenBodyMode = true;
      }
    }
    label137: for (this.mReadout = false; ; this.mReadout = true)
    {
      setNightModeGui();
      return;
    }
  }

  public boolean isDecreasedSize()
  {
    return this.isMinimized;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (!this.mReadout)
      if ((getResources().getConfiguration().orientation == 2) && (this.isMinimized))
      {
        setBackgroundResource(2130837588);
        setPadding(0, 0, 0, 0);
        this.msgBody.setVisibility(0);
        this.mHiddenBodyMode = false;
      }
    while (true)
    {
      setNightModeGui();
      return;
      setBackgroundResource(2130837587);
      setPadding(0, 0, 0, 21);
      this.msgBody.setVisibility(8);
      this.mHiddenBodyMode = true;
      continue;
      if (getResources().getConfiguration().orientation == 1)
      {
        if (this.isMinimized)
        {
          this.msgBody.setMaxLines(2);
          continue;
        }
        this.msgBody.setMaxLines(6);
        continue;
      }
      this.msgBody.setMaxLines(3);
    }
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
      return 0;
      this.msgBody.setMaxLines(2);
    }
  }

  public void startAnimationTranslate(View paramView)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingMessageReadbackWidget
 * JD-Core Version:    0.6.0
 */