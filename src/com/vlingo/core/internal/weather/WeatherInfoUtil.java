package com.vlingo.core.internal.weather;

import android.content.Context;
import com.vlingo.core.internal.settings.Settings;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WeatherInfoUtil
{
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
  private String sunRise;
  private String sunSet;

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

  public static WeatherInfoUtil getWeatherInfoUtil(Context paramContext, WeatherElement paramWeatherElement, String paramString)
  {
    WeatherInfoUtil localWeatherInfoUtil = null;
    int i = 0;
    WeatherElement localWeatherElement1;
    if (i < paramWeatherElement.getChildCount())
    {
      localWeatherElement1 = paramWeatherElement.getChild(i);
      if ("CurrentCondition".equals(localWeatherElement1.getName()))
        if ("CurrentCondition".equals(localWeatherElement1.getChild(0).getName()))
        {
          WeatherElement localWeatherElement2 = localWeatherElement1.getChild(0);
          localWeatherInfoUtil = new WeatherInfoUtil(localWeatherElement2, paramContext);
          SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd ", Locale.US);
          localWeatherInfoUtil.setSunRise(localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())) + (String)localWeatherElement2.getAttributes().get("Sunrise"));
          localWeatherInfoUtil.setSunSet(localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())) + (String)localWeatherElement2.getAttributes().get("Sunset"));
        }
    }
    while (true)
    {
      i++;
      break;
      if (!"Forecasts".equals(localWeatherElement1.getName()))
        continue;
      ArrayList localArrayList = new ArrayList(localWeatherElement1.getChildCount());
      Object localObject = null;
      int j = 0;
      int k = 0;
      label219: if (k < localWeatherElement1.getChildCount())
      {
        String str = (String)localWeatherElement1.getChild(k).getAttributes().get("ForecastDate");
        localArrayList.add(str);
        if (!str.equalsIgnoreCase(paramString));
      }
      try
      {
        Date localDate3 = sdf2.parse(paramString);
        localObject = localDate3;
        j = 1;
        label284: k++;
        break label219;
        if (j == 0);
        try
        {
          Date localDate2 = sdf2.parse((String)localArrayList.get(0));
          localObject = localDate2;
          label316: int m = 1;
          label319: if ((m >= localArrayList.size()) || (localObject != null));
          try
          {
            if (localObject.after(sdf2.parse((String)localArrayList.get(m))))
            {
              Date localDate1 = sdf2.parse((String)localArrayList.get(m));
              localObject = localDate1;
            }
            label380: m++;
            break label319;
            localWeatherInfoUtil.setWeatherForOneDay(localWeatherElement1, sdf2.format(localObject));
            continue;
            return localWeatherInfoUtil;
          }
          catch (ParseException localParseException2)
          {
            break label380;
          }
        }
        catch (ParseException localParseException1)
        {
          break label316;
        }
      }
      catch (ParseException localParseException3)
      {
        break label284;
      }
    }
  }

  public int getCurrentTemp()
  {
    return (int)Double.parseDouble(this.mCurrentTemperature);
  }

  public String getCurrentTempForTTS()
  {
    return this.mCurrentTemperature;
  }

  public int getCurrentWeatherCode()
  {
    int i = -1;
    try
    {
      int j = Integer.parseInt(this.mWeatherCode);
      i = j;
      label13: return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label13;
    }
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

  public String getSunRise()
  {
    return this.sunRise;
  }

  public String getSunSet()
  {
    return this.sunSet;
  }

  public String getWeatherDate()
  {
    return this.mCurrentDate;
  }

  public String getWeatherString()
  {
    return this.mLongText;
  }

  public void setSunRise(String paramString)
  {
    this.sunRise = paramString;
  }

  public void setSunSet(String paramString)
  {
    this.sunSet = paramString;
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
      str1 = "M dd E";
    sdf = new SimpleDateFormat(str1, new Locale(arrayOfString[0], arrayOfString[1]));
    sdf2 = new SimpleDateFormat("yyyy-MM-dd", new Locale(arrayOfString[0], arrayOfString[1]));
    try
    {
      WeatherElement localWeatherElement1 = paramWeatherElement.findChildWithAttr("ForecastDate", paramString);
      str2 = (String)localWeatherElement1.getAttributes().get("ForecastDate");
      this.mCurrentDate = str2;
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
        label178: this.mMax = ((String)localWeatherElement2.getAttributes().get("TempMax"));
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
        break label178;
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
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherInfoUtil
 * JD-Core Version:    0.6.0
 */