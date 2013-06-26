package com.vlingo.core.internal.weather.china;

import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.array;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.sdk.internal.util.StringUtils;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CMAWeatherParser
{
  private CMALocationElement locationElement;
  private CMAWeatherElement weatherElement;
  private String[] weatherPhenomenonStrings;
  private String[] windDirectionStrings;
  private String[] windForceStrings;

  public CMAWeatherParser(CMALocationElement paramCMALocationElement)
  {
    this.locationElement = paramCMALocationElement;
  }

  public CMAWeatherParser(CMAWeatherElement paramCMAWeatherElement)
  {
    this.weatherElement = paramCMAWeatherElement;
    this.weatherPhenomenonStrings = VlingoAndroidCore.getResourceProvider().getStringArray(ResourceIdProvider.array.core_weather_phenomenon);
    this.windForceStrings = VlingoAndroidCore.getResourceProvider().getStringArray(ResourceIdProvider.array.core_weather_wind_force);
    this.windDirectionStrings = VlingoAndroidCore.getResourceProvider().getStringArray(ResourceIdProvider.array.core_weather_wind_direction);
  }

  private void fillCityInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    if (Settings.getCurrentLocale().equals(Locale.CHINA))
    {
      this.weatherElement.nameCity = paramJSONObject.getString("c3");
      this.weatherElement.nameProvince = paramJSONObject.getString("c7");
    }
    for (this.weatherElement.nameCountry = paramJSONObject.getString("c9"); ; this.weatherElement.nameCountry = toFirstUpperCase(paramJSONObject.getString("c8")))
    {
      return;
      this.weatherElement.nameCity = toFirstUpperCase(paramJSONObject.getString("c2"));
      this.weatherElement.nameProvince = toFirstUpperCase(paramJSONObject.getString("c6"));
    }
  }

  private void fillCurrentConditions(JSONObject paramJSONObject)
    throws JSONException
  {
    WeatherCurrent localWeatherCurrent = new WeatherCurrent();
    localWeatherCurrent.temperature = paramJSONObject.getString("l1");
    localWeatherCurrent.weatherCode = paramJSONObject.getString("l5");
    localWeatherCurrent.weatherPhenomenon = getPhenomenonStringFromArrayRes(localWeatherCurrent.weatherCode);
    localWeatherCurrent.precipitation = paramJSONObject.getString("l6");
    localWeatherCurrent.humidity = paramJSONObject.getString("l2");
    localWeatherCurrent.windForce = getWindForceStringFromArrayRes(paramJSONObject.getString("l3"));
    localWeatherCurrent.windDirection = getWindDirectionStringFromArrayRes(paramJSONObject.getString("l4"));
    localWeatherCurrent.timePublished = paramJSONObject.getString("l7");
    this.weatherElement.weatherCurrent = localWeatherCurrent;
  }

  private void fillNode(JSONObject paramJSONObject)
    throws JSONException
  {
    this.locationElement.longitude = paramJSONObject.getString("lon");
    this.locationElement.latitude = paramJSONObject.getString("lat");
  }

  private void fillNode(JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONArray paramJSONArray)
    throws JSONException
  {
    this.weatherElement.lastUpdate = new Date();
    fillCityInfo(paramJSONObject1);
    if (paramJSONObject2 != null)
      fillCurrentConditions(paramJSONObject2);
    fillWeatherSevenDays(paramJSONArray, new Date());
  }

  private void fillWeatherSevenDays(JSONArray paramJSONArray, Date paramDate)
    throws JSONException
  {
    int i = 0;
    while (true)
      if (i < paramJSONArray.length())
      {
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        WeatherItem localWeatherItem = new WeatherItem();
        localWeatherItem.day = new WeatherTimeItem();
        WeatherTimeItem localWeatherTimeItem1 = localWeatherItem.day;
        String str1;
        label59: String str2;
        label107: String str3;
        label135: String str4;
        label167: String str5;
        label211: String str6;
        label259: String str7;
        label287: String str8;
        if (localJSONObject.isNull("fa"))
        {
          str1 = "0";
          localWeatherTimeItem1.weatherCode = str1;
          localWeatherItem.day.weatherPhenomenon = getPhenomenonStringFromArrayRes(localWeatherItem.day.weatherCode);
          WeatherTimeItem localWeatherTimeItem2 = localWeatherItem.day;
          if (!localJSONObject.isNull("fc"))
            break label406;
          str2 = "";
          localWeatherTimeItem2.temperature = str2;
          WeatherTimeItem localWeatherTimeItem3 = localWeatherItem.day;
          if (!localJSONObject.isNull("fe"))
            break label418;
          str3 = "";
          localWeatherTimeItem3.windDirection = getWindDirectionStringFromArrayRes(str3);
          WeatherTimeItem localWeatherTimeItem4 = localWeatherItem.day;
          if (!localJSONObject.isNull("fg"))
            break label430;
          str4 = "";
          localWeatherTimeItem4.windForce = getWindForceStringFromArrayRes(str4);
          localWeatherItem.night = new WeatherTimeItem();
          WeatherTimeItem localWeatherTimeItem5 = localWeatherItem.night;
          if (!localJSONObject.isNull("fb"))
            break label442;
          str5 = "99";
          localWeatherTimeItem5.weatherCode = str5;
          localWeatherItem.night.weatherPhenomenon = getPhenomenonStringFromArrayRes(localWeatherItem.night.weatherCode);
          WeatherTimeItem localWeatherTimeItem6 = localWeatherItem.night;
          if (!localJSONObject.isNull("fd"))
            break label454;
          str6 = "";
          localWeatherTimeItem6.temperature = str6;
          WeatherTimeItem localWeatherTimeItem7 = localWeatherItem.night;
          if (!localJSONObject.isNull("ff"))
            break label466;
          str7 = "";
          localWeatherTimeItem7.windDirection = getWindDirectionStringFromArrayRes(str7);
          WeatherTimeItem localWeatherTimeItem8 = localWeatherItem.night;
          if (!localJSONObject.isNull("fh"))
            break label478;
          str8 = "";
          localWeatherTimeItem8.windForce = getWindForceStringFromArrayRes(str8);
          localWeatherItem.sunrise = getSunrise(localJSONObject.getString("fi"));
          localWeatherItem.sunset = getSunset(localJSONObject.getString("fi"));
        }
        try
        {
          localWeatherItem.date = getDate(i, paramDate);
          this.weatherElement.weatherItems.add(localWeatherItem);
          i++;
          continue;
          str1 = localJSONObject.getString("fa");
          break label59;
          label406: str2 = localJSONObject.getString("fc");
          break label107;
          label418: str3 = localJSONObject.getString("fe");
          break label135;
          label430: str4 = localJSONObject.getString("fg");
          break label167;
          label442: str5 = localJSONObject.getString("fb");
          break label211;
          label454: str6 = localJSONObject.getString("fd");
          break label259;
          label466: str7 = localJSONObject.getString("ff");
          break label287;
          label478: str8 = localJSONObject.getString("fh");
        }
        catch (ParseException localParseException)
        {
          while (true)
            localParseException.printStackTrace();
        }
      }
  }

  private Date getDate(int paramInt, Date paramDate)
    throws ParseException
  {
    return new Date(paramDate.getTime() + 86400000L * paramInt);
  }

  private String getPhenomenonStringFromArrayRes(String paramString)
  {
    String str;
    if ((paramString == "99") || (StringUtils.isNullOrWhiteSpace(paramString)))
      str = "";
    while (true)
    {
      return str;
      try
      {
        str = this.weatherPhenomenonStrings[java.lang.Integer.parseInt(paramString)];
      }
      catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
      {
        str = "";
      }
      catch (NumberFormatException localNumberFormatException)
      {
        str = "";
      }
    }
  }

  private String getSunrise(String paramString)
  {
    return paramString.split("\\|")[0];
  }

  private String getSunset(String paramString)
  {
    return paramString.split("\\|")[1];
  }

  private String getWindDirectionStringFromArrayRes(String paramString)
  {
    String str;
    if (StringUtils.isNullOrWhiteSpace(paramString))
      str = "";
    while (true)
    {
      return str;
      try
      {
        str = this.windDirectionStrings[java.lang.Integer.parseInt(paramString)];
      }
      catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
      {
        str = "";
      }
      catch (NumberFormatException localNumberFormatException)
      {
        str = "";
      }
    }
  }

  private String getWindForceStringFromArrayRes(String paramString)
  {
    String str;
    if (StringUtils.isNullOrWhiteSpace(paramString))
      str = "";
    while (true)
    {
      return str;
      try
      {
        str = this.windForceStrings[java.lang.Integer.parseInt(paramString)];
      }
      catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
      {
        str = "";
      }
      catch (NumberFormatException localNumberFormatException)
      {
        str = "";
      }
    }
  }

  private String toFirstUpperCase(String paramString)
  {
    if (!StringUtils.isNullOrWhiteSpace(paramString));
    for (String str = paramString.length() + Character.toUpperCase(paramString.charAt(0)) + paramString.substring(1); ; str = "")
      return str;
  }

  public CMAWeatherElement parse(String paramString)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray(paramString);
    JSONObject localJSONObject1 = localJSONArray.getJSONObject(4).getJSONObject("c");
    if (localJSONArray.getJSONObject(2).isNull("l"));
    for (JSONObject localJSONObject2 = null; ; localJSONObject2 = localJSONArray.getJSONObject(2).getJSONObject("l"))
    {
      fillNode(localJSONObject1, localJSONObject2, localJSONArray.getJSONObject(4).getJSONObject("f").getJSONArray("f1"));
      this.weatherElement.lastUpdate = new Date();
      return this.weatherElement;
    }
  }

  public CMALocationElement parseLocation(String paramString)
    throws JSONException
  {
    fillNode(new JSONArray(paramString).getJSONObject(0).getJSONObject("cityinfo"));
    return this.locationElement;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.china.CMAWeatherParser
 * JD-Core Version:    0.6.0
 */