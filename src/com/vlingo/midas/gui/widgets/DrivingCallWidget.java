package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.RecipientType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.Widget;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;

public class DrivingCallWidget extends Widget<Object>
  implements DrivingWidgetInterface
{
  private TextView callTitle;

  public DrivingCallWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setNightModeGui()
  {
    if (MidasSettings.isNightMode())
    {
      setBackgroundResource(2130837875);
      this.callTitle.setTextColor(-2039584);
      setPadding(0, 0, 0, 0);
    }
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

  public void initialize(Object paramObject, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.callTitle = ((TextView)findViewById(2131558914));
    if (paramObject != null)
      this.callTitle.setText(((RecipientType)paramObject).getAddress());
    setNightModeGui();
  }

  public boolean isDecreasedSize()
  {
    return false;
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
    setNightModeGui();
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
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingCallWidget
 * JD-Core Version:    0.6.0
 */