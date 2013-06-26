package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$7
  implements EndElementListener
{
  public void end()
  {
    this.val$wForecast.addChild(this.val$wNighttimeForecast.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.7
 * JD-Core Version:    0.6.0
 */