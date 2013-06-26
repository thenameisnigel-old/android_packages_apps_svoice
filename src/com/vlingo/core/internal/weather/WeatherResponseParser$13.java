package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$13
  implements EndElementListener
{
  public void end()
  {
    this.val$response.addChild(this.val$wUnits.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.13
 * JD-Core Version:    0.6.0
 */