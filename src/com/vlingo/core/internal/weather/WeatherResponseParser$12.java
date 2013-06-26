package com.vlingo.core.internal.weather;

import android.sax.EndElementListener;

class WeatherResponseParser$12
  implements EndElementListener
{
  public void end()
  {
    this.val$wMoonPhases.addChild(this.val$wMoonPhase.clone());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser.12
 * JD-Core Version:    0.6.0
 */