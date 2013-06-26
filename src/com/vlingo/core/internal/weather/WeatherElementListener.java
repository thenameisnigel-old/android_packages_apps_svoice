package com.vlingo.core.internal.weather;

import android.sax.ElementListener;
import org.xml.sax.Attributes;

public class WeatherElementListener
  implements ElementListener
{
  private WeatherElement element;
  private WeatherElement grandParent;
  private WeatherElement parent;

  public WeatherElementListener(WeatherElement paramWeatherElement1, WeatherElement paramWeatherElement2)
  {
    if ((paramWeatherElement1 == null) || (paramWeatherElement2 == null))
      throw new IllegalArgumentException("element OR parent cannot be null");
    this.element = paramWeatherElement1;
    this.parent = paramWeatherElement2;
  }

  public WeatherElementListener(WeatherElement paramWeatherElement1, WeatherElement paramWeatherElement2, WeatherElement paramWeatherElement3)
  {
    if ((paramWeatherElement1 == null) || (paramWeatherElement2 == null) || (paramWeatherElement3 == null))
      throw new IllegalArgumentException("element, parent OR grandParent cannot be null");
    this.element = paramWeatherElement1;
    this.parent = paramWeatherElement2;
    this.grandParent = paramWeatherElement3;
  }

  public void end()
  {
    if (this.grandParent != null)
    {
      this.parent.addChild(this.element.clone());
      this.grandParent.addChild(this.parent.clone());
    }
    while (true)
    {
      return;
      this.parent.addChild(this.element.clone());
    }
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
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherElementListener
 * JD-Core Version:    0.6.0
 */