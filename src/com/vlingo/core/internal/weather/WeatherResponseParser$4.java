package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$4
  implements EndElementListener
{
  public void end()
  {
    this.val$wForcasts.addChild(this.val$wForecast.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.4
 * JD-Core Version:    0.6.0
 */