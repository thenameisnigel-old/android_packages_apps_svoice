package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$9
  implements EndElementListener
{
  public void end()
  {
    this.val$response.addChild(this.val$wImages.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.9
 * JD-Core Version:    0.6.0
 */