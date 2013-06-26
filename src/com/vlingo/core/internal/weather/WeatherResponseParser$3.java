package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$3
  implements EndElementListener
{
  public void end()
  {
    this.val$response.addChild(this.val$wForcasts.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.3
 * JD-Core Version:    0.6.0
 */