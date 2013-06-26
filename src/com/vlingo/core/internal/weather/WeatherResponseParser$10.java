package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$10
  implements EndElementListener
{
  public void end()
  {
    this.val$wImages.addChild(this.val$wImage.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.10
 * JD-Core Version:    0.6.0
 */