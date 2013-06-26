package com.vlingo.midas.weather;

import android.content.Context;
import android.content.res.Resources;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.weather.WeatherElement;
import com.vlingo.core.internal.weather.WeatherElement.WeatherNotFound;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WeatherInfoUtil
{
  private static final int NO_DRAWABLE = 101;
  private static SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM", Locale.US);
  private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
  private String date_val;
  private Context mContext;
  private String mCurrentDate = null;
  private String mCurrentTemperature;
  private String mLangApplication;
  private String mLongText;
  private String mMax = null;
  private String mMin = null;
  private String mWeatherCode;
  private WeatherElement mWeatherElement;

  public WeatherInfoUtil()
  {
  }

  public WeatherInfoUtil(WeatherElement paramWeatherElement, Context paramContext)
  {
    this.mWeatherElement = paramWeatherElement;
    getWeatherInfoFromResponse();
    this.mContext = paramContext;
  }

  private void getWeatherInfoFromResponse()
  {
    Iterator localIterator = this.mWeatherElement.getAttributes().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((String)localEntry.getKey()).equalsIgnoreCase("Temperature"))
        this.mCurrentTemperature = ((String)localEntry.getValue());
      if (((String)localEntry.getKey()).equalsIgnoreCase("WeatherText"))
        this.mLongText = ((String)localEntry.getValue());
      if (!((String)localEntry.getKey()).equalsIgnoreCase("WeatherCode"))
        continue;
      this.mWeatherCode = ((String)localEntry.getValue());
    }
  }

  public int getCurrentTemp()
  {
    return (int)Double.parseDouble(this.mCurrentTemperature);
  }

  public int getCurrentWeatherCode()
  {
    return 0;
  }

  public String getDateForOneDayWidget()
  {
    return this.date_val;
  }

  public String getMaximumTempOneDayWeather()
  {
    return this.mMax;
  }

  public String getMinimumTempOneDayWeather()
  {
    return this.mMin;
  }

  public String getWeatherDate()
  {
    return this.mCurrentDate;
  }

  public String getWeatherString()
  {
    return this.mLongText;
  }

  public void setWeatherCode(String paramString)
  {
    this.mWeatherCode = paramString;
  }

  public void setWeatherForOneDay(WeatherElement paramWeatherElement, String paramString)
  {
    String str1 = "EEE, d MMM";
    this.mLangApplication = Settings.getLanguageApplication();
    String[] arrayOfString = this.mLangApplication.split("-");
    if (this.mLangApplication.equals("ko-KR"))
      str1 = "M" + this.mContext.getResources().getString(2131362564) + " dd" + this.mContext.getResources().getString(2131362010) + " E" + this.mContext.getResources().getString(2131362519);
    sdf = new SimpleDateFormat(str1, new Locale(arrayOfString[0], arrayOfString[1]));
    sdf2 = new SimpleDateFormat("yyyy-MM-dd", new Locale(arrayOfString[0], arrayOfString[1]));
    try
    {
      WeatherElement localWeatherElement1 = paramWeatherElement.findChildWithAttr("ForecastDate", paramString);
      str2 = (String)localWeatherElement1.getAttributes().get("ForecastDate");
      localWeatherElement2 = localWeatherElement1.findNamedChild("DaytimeForecast").findNamedChild("DayNighttimeForecast");
      localWeatherElement3 = localWeatherElement1.findNamedChild("NighttimeForecast").findNamedChild("DayNighttimeForecast");
    }
    catch (WeatherElement.WeatherNotFound localWeatherNotFound)
    {
      try
      {
        String str2;
        WeatherElement localWeatherElement2;
        WeatherElement localWeatherElement3;
        Date localDate = sdf2.parse(str2);
        this.date_val = sdf.format(localDate);
        label233: this.mMax = ((String)localWeatherElement2.getAttributes().get("TempMax"));
        this.mMin = ((String)localWeatherElement3.getAttributes().get("TempMin"));
        this.mLongText = ((String)localWeatherElement2.getAttributes().get("ShortText"));
        this.mWeatherCode = ((String)this.mWeatherElement.getAttributes().get("WeatherCode"));
        this.mMax = ((String)localWeatherElement2.getAttributes().get("TempMax"));
        this.mMin = ((String)localWeatherElement3.getAttributes().get("TempMin"));
        this.mLongText = ((String)localWeatherElement2.getAttributes().get("ShortText"));
        this.mWeatherCode = ((String)localWeatherElement2.getAttributes().get("WeatherCode"));
        while (true)
        {
          return;
          localWeatherNotFound = localWeatherNotFound;
          localWeatherNotFound.printStackTrace();
        }
      }
      catch (Exception localException)
      {
        break label233;
      }
    }
  }

  public class ForecastingWeekly
  {
    private String date;
    private String days;
    private String image;
    private String maxTemp;
    private String minTemp;
    private String weatherCode;

    public ForecastingWeekly()
    {
    }

    public String getDate()
    {
      return this.date;
    }

    public String getDays()
    {
      return this.days;
    }

    public String getImage()
    {
      return this.image;
    }

    public String getMaxTemp()
    {
      return this.maxTemp;
    }

    public String getMinTemp()
    {
      return this.minTemp;
    }

    public String getWeatherCode()
    {
      return this.weatherCode;
    }

    public void setDate(String paramString)
    {
      this.date = paramString;
    }

    public void setDays(String paramString)
    {
      this.days = paramString;
    }

    public void setImage(String paramString)
    {
      this.image = paramString;
    }

    public void setMaxTemp(String paramString)
    {
      this.maxTemp = paramString;
    }

    public void setMinTemp(String paramString)
    {
      this.minTemp = paramString;
    }

    public void setWeatherCode(String paramString)
    {
      this.weatherCode = paramString;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.weather.WeatherInfoUtil
 * JD-Core Version:    0.6.0
 */