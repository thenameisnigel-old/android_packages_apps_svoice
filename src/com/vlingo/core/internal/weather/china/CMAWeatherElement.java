package com.vlingo.core.internal.weather.china;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CMAWeatherElement
{
  public Date lastUpdate;
  public String nameCity;
  public String nameCountry;
  public String nameProvince;
  public WeatherCurrent weatherCurrent;
  public List<WeatherItem> weatherItems = new ArrayList();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.china.CMAWeatherElement
 * JD-Core Version:    0.6.0
 */