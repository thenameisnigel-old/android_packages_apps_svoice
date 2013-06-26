package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$6
  implements EndElementListener
{
  public void end()
  {
    this.val$wDaytimeForecast.addChild(this.val$wDayDayNittimeForecast.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.6
 * JD-Core Version:    0.6.0
 */