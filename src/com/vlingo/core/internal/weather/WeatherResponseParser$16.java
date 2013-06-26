package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$16
  implements EndElementListener
{
  public void end()
  {
    this.val$wWeatherLocation.addChild(this.val$wLocation.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.16
 * JD-Core Version:    0.6.0
 */