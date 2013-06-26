package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;

public class DrivingWeatherWidget extends WeatherSevenDayWidget
  implements DrivingWidgetInterface
{
  public DrivingWeatherWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public int getDecreasedHeight()
  {
    if (getResources().getConfiguration().orientation == 2);
    for (int i = 900; ; i = 960)
      return i;
  }

  public int getProperHeight()
  {
    return 0;
  }

  public boolean isDecreasedSize()
  {
    return false;
  }

  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (paramConfiguration != null)
      super.onConfigurationChanged(paramConfiguration);
    this.isMultiwindow = MidasSettings.isMultiwindowedMode();
    if (!this.isMultiwindow)
    {
      if (getResources().getConfiguration().orientation != 2)
        break label42;
      setLandscapeWidget();
    }
    while (true)
    {
      return;
      label42: setPortraitWidget();
    }
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    setMinimize();
    return 0;
  }

  public void startAnimationTranslate(View paramView)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingWeatherWidget
 * JD-Core Version:    0.6.0
 */