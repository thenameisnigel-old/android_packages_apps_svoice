package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$2
  implements EndElementListener
{
  public void end()
  {
    this.val$wConditions.addChild(this.val$wCondition.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.2
 * JD-Core Version:    0.6.0
 */