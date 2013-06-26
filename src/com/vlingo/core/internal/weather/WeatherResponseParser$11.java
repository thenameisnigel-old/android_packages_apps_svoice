package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$11
  implements EndElementListener
{
  public void end()
  {
    this.val$response.addChild(this.val$wMoonPhases.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.11
 * JD-Core Version:    0.6.0
 */