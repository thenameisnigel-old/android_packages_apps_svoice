package com.vlingo.core.internal.weather;

import android.sax.StartElementListener;
import org.xml.sax.Attributes;

public class WeatherAttributeListener
  implements StartElementListener
{
  private WeatherElement element;

  public WeatherAttributeListener(WeatherElement paramWeatherElement)
  {
    if (paramWeatherElement == null)
      throw new IllegalArgumentException("element cannot be null");
    this.element = paramWeatherElement;
  }

  public void start(Attributes paramAttributes)
  {
    for (int i = 0; i < paramAttributes.getLength(); i++)
    {
      String str1 = paramAttributes.getLocalName(i);
      String str2 = paramAttributes.getValue(i);
      this.element.addAttribute(str1, str2);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherAttributeListener
 * JD-Core Version:    0.6.0
 */