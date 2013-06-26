package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$8
  implements EndElementListener
{
  public void end()
  {
    this.val$wNighttimeForecast.addChild(this.val$wNightDayNittimeForecast.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.8
 * JD-Core Version:    0.6.0
 */