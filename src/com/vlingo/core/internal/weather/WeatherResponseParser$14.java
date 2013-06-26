package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$14
  implements EndElementListener
{
  public void end()
  {
    this.val$wUnits.addChild(this.val$wUnit.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.14
 * JD-Core Version:    0.6.0
 */