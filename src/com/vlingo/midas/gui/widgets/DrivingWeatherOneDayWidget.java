package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.vlingo.midas.util.DrivingWidgetInterface;

public class DrivingWeatherOneDayWidget extends WeatherOneDayWidget
  implements DrivingWidgetInterface
{
  public DrivingWeatherOneDayWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public int getDecreasedHeight()
  {
    setMinimizeWindow();
    return 860;
  }

  public int getProperHeight()
  {
    return 0;
  }

  public boolean isDecreasedSize()
  {
    return false;
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
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingWeatherOneDayWidget
 * JD-Core Version:    0.6.0
 */