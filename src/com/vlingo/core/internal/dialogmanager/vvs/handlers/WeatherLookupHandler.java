package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.array;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import com.vlingo.core.internal.weather.WeatherAdaptor;
import com.vlingo.core.internal.weather.WeatherElement;
import com.vlingo.core.internal.weather.WeatherElement.WeatherNotFound;
import com.vlingo.core.internal.weather.WeatherInfoUtil;
import com.vlingo.sdk.recognition.VLAction;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

public class WeatherLookupHandler extends VVSActionHandler
  implements WidgetActionListener, WidgetResponseReceivedListener
{
  private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
  private WeatherAdaptor adaptor = null;
  Handler handler = null;

  public WeatherLookupHandler()
  {
    this.adaptor.setWidgetListener(this);
    this.handler = new Handler(Looper.getMainLooper());
  }

  private String getForecastDescription()
  {
    String str = getForecastDescriptionByAdaptorDate();
    if (str == null)
      str = getForecastDescriptionFromFirstForecast();
    return str;
  }

  private String getForecastDescription(String paramString)
  {
    String str = getForecastDescriptionByDate(paramString);
    if (str == null)
      str = getForecastDescriptionFromFirstForecast();
    return str;
  }

  private String getForecastDescriptionByAdaptorDate()
  {
    return getForecastDescriptionByDate(this.adaptor.getDate());
  }

  private String getForecastDescriptionByDate(String paramString)
  {
    String str = null;
    try
    {
      str = (String)this.adaptor.getWeatherElement().findNamedChild("Forecasts").findChildWithAttr("ForecastDate", paramString).findNamedChild("DaytimeForecast").findNamedChild("DayNighttimeForecast").getAttributes().get("ShortText");
      label44: if (str == null);
      try
      {
        str = (String)this.adaptor.getWeatherElement().findNamedChild("Forecasts").findChildWithAttr("ForecastDate", paramString).findNamedChild("DaytimeForecast").findNamedChild("DayNighttimeForecast").getAttributes().get("LongText");
        label90: return str;
      }
      catch (WeatherElement.WeatherNotFound localWeatherNotFound2)
      {
        break label90;
      }
    }
    catch (WeatherElement.WeatherNotFound localWeatherNotFound1)
    {
      break label44;
    }
  }

  private String getForecastDescriptionFromFirstForecast()
  {
    String str;
    if (!this.adaptor.isToday())
      str = null;
    while (true)
    {
      return str;
      str = null;
      try
      {
        str = (String)this.adaptor.getWeatherElement().findNamedChild("Forecasts").findNamedChild("Forecast").findNamedChild("DaytimeForecast").findNamedChild("DayNighttimeForecast").getAttributes().get("ShortText");
        try
        {
          label57: str = (String)this.adaptor.getWeatherElement().findNamedChild("Forecasts").findNamedChild("Forecast").findNamedChild("DaytimeForecast").findNamedChild("DayNighttimeForecast").getAttributes().get("LongText");
        }
        catch (WeatherElement.WeatherNotFound localWeatherNotFound2)
        {
        }
      }
      catch (WeatherElement.WeatherNotFound localWeatherNotFound1)
      {
        break label57;
      }
    }
  }

  private String getOneDaySpokenMsgInternal(Integer paramInteger, WeatherInfoUtil paramWeatherInfoUtil, String paramString1, String paramString2, DateType paramDateType)
  {
    Object localObject = null;
    String str1 = paramWeatherInfoUtil.getCurrentTempForTTS();
    String str2 = paramWeatherInfoUtil.getMaximumTempOneDayWeather();
    Integer localInteger = Integer.valueOf(paramWeatherInfoUtil.getCurrentWeatherCode());
    String str3 = getForecastDescription(paramString2);
    if (str3 != null);
    while (true)
    {
      try
      {
        String str4 = getSpokenMsg(paramInteger, str3, paramString1, this.adaptor.getLocationCity(), this.adaptor.getLocationState(), str2, str1, localInteger.intValue(), paramDateType);
        localObject = str4;
        return localObject;
      }
      catch (WeatherElement.WeatherNotFound localWeatherNotFound)
      {
        localWeatherNotFound.printStackTrace();
        continue;
      }
      if (!this.adaptor.isToday())
        continue;
      localObject = getString(ResourceIdProvider.string.core_weather_general, new Object[0]);
    }
  }

  private void showFailure()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        String str = WeatherLookupHandler.access$1700(ResourceIdProvider.string.core_weather_no_results, new Object[0]);
        WeatherLookupHandler.this.unified().showSystemTurn(str);
        WeatherLookupHandler.this.actionFail("Weather lookup failed");
      }
    });
  }

  private void showSuccess()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        Object localObject1 = null;
        Object localObject2 = null;
        boolean bool1 = false;
        boolean bool2 = false;
        int i = 0;
        String str1 = WeatherLookupHandler.this.adaptor.getDate();
        Date localDate1 = new Date();
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(DateUtil.CANONICAL_DATE_FORMAT);
        String str2 = localSimpleDateFormat.format(localDate1);
        Date localDate2 = new Date();
        new GregorianCalendar();
        GregorianCalendar localGregorianCalendar = new GregorianCalendar();
        localGregorianCalendar.setTime(localDate2);
        localGregorianCalendar.add(5, 1);
        localDate2.setTime(localGregorianCalendar.getTime().getTime());
        String str3 = localSimpleDateFormat.format(localDate2);
        WeatherLookupHandler.this.strDatetoDOW(str3);
        if (WeatherLookupHandler.this.adaptor.isDefaultLocation())
        {
          String str14 = WeatherLookupHandler.access$200(ResourceIdProvider.string.core_weather_default_location, new Object[0]);
          WeatherLookupHandler.this.getListener().showVlingoTextAndTTS(str14, str14);
          WeatherLookupHandler.this.actionSuccess();
        }
        while (true)
        {
          return;
          if (!StringUtils.isNullOrWhiteSpace(str1));
          try
          {
            Date localDate3 = new Date();
            localGregorianCalendar.setTime(localDate3);
            localGregorianCalendar.add(5, 6);
            localDate3.setTime(localGregorianCalendar.getTime().getTime());
            Date localDate4 = DateUtil.getDateFromCanonicalString(str1);
            bool1 = localDate4.after(localDate3);
            if (localDate1.getDate() == localDate4.getDate())
            {
              bool2 = true;
              label251: WeatherLookupHandler.this.adaptor.setToday(bool2);
              int k = localDate2.getDate();
              int m = localDate4.getDate();
              if (k != m)
                break label368;
              i = 1;
              label287: if (!bool1)
                break label374;
              localObject1 = WeatherLookupHandler.access$400(ResourceIdProvider.string.core_weather_plus_seven, new Object[0]);
              label302: if (localObject2 != null)
                break label831;
              WeatherLookupHandler.this.unified().showSystemTurn((String)localObject1);
              label317: if (StringUtils.isNullOrWhiteSpace(str1))
                break label848;
              WeatherLookupHandler.this.getListener().showWidget(WidgetUtil.WidgetKey.ShowWeatherDailyWidget, null, WeatherLookupHandler.this.adaptor, WeatherLookupHandler.this);
            }
            while (true)
            {
              while (true)
              {
                WeatherLookupHandler.this.actionSuccess();
                break;
                bool2 = false;
                break label251;
                label368: i = 0;
                break label287;
                label374: WeatherInfoUtil localWeatherInfoUtil = WeatherInfoUtil.getWeatherInfoUtil(WeatherLookupHandler.this.getListener().getActivityContext(), WeatherLookupHandler.this.adaptor.getWeatherElement(), str1);
                if (StringUtils.isNullOrWhiteSpace(str1))
                  try
                  {
                    String str11 = WeatherLookupHandler.access$600(ResourceIdProvider.string.core_today, new Object[0]);
                    String str12 = WeatherLookupHandler.this.getOneDaySpokenMsgInternal(null, localWeatherInfoUtil, str11, str2, WeatherLookupHandler.DateType.TODAY);
                    StringBuilder localStringBuilder1 = new StringBuilder();
                    ResourceIdProvider.string localstring2 = ResourceIdProvider.string.core_weather_with_locatin;
                    Object[] arrayOfObject2 = new Object[2];
                    arrayOfObject2[0] = WeatherLookupHandler.access$000(WeatherLookupHandler.this).getLocationCity();
                    arrayOfObject2[1] = WeatherLookupHandler.access$000(WeatherLookupHandler.this).getLocationState();
                    StringBuilder localStringBuilder2 = localStringBuilder1.append(WeatherLookupHandler.access$800(localstring2, arrayOfObject2));
                    if (str12 != null);
                    for (String str13 = WeatherLookupHandler.access$900(ResourceIdProvider.string.core_space, new Object[0]) + str12; ; str13 = "")
                    {
                      localObject1 = str13;
                      break;
                    }
                  }
                  catch (WeatherElement.WeatherNotFound localWeatherNotFound3)
                  {
                    localWeatherNotFound3.printStackTrace();
                  }
                new String();
                String str4;
                label595: String str5;
                String str6;
                Integer localInteger;
                WeatherLookupHandler.DateType localDateType;
                label637: Object localObject3;
                if (bool2)
                {
                  str4 = WeatherLookupHandler.access$1000(ResourceIdProvider.string.core_today, new Object[0]);
                  str5 = localWeatherInfoUtil.getCurrentTempForTTS();
                  str6 = localWeatherInfoUtil.getMaximumTempOneDayWeather();
                  localInteger = Integer.valueOf(localWeatherInfoUtil.getCurrentWeatherCode());
                  WeatherLookupHandler.this.getForecastDescription();
                  if (!bool2)
                    break label790;
                  localDateType = WeatherLookupHandler.DateType.TODAY;
                  localObject3 = null;
                }
                try
                {
                  ResourceIdProvider.string localstring1 = ResourceIdProvider.string.core_weather_with_locatin;
                  Object[] arrayOfObject1 = new Object[2];
                  arrayOfObject1[0] = WeatherLookupHandler.access$000(WeatherLookupHandler.this).getLocationCity();
                  arrayOfObject1[1] = WeatherLookupHandler.access$000(WeatherLookupHandler.this).getLocationState();
                  String str10 = WeatherLookupHandler.access$1200(localstring1, arrayOfObject1);
                  localObject3 = str10;
                  localObject2 = WeatherLookupHandler.this.getOneDaySpokenMsg(null, localWeatherInfoUtil, str4, str1, localDateType, localObject3);
                }
                catch (WeatherElement.WeatherNotFound localWeatherNotFound1)
                {
                  try
                  {
                    WeatherLookupHandler localWeatherLookupHandler = WeatherLookupHandler.this;
                    String str7 = WeatherLookupHandler.this.adaptor.getLocationCity();
                    String str8 = WeatherLookupHandler.this.adaptor.getLocationState();
                    int j = localInteger.intValue();
                    String str9 = localWeatherLookupHandler.getMsg(null, str4, str7, str8, str6, str5, j, localDateType);
                    localObject1 = str9;
                    break label302;
                    str4 = WeatherLookupHandler.this.strDatetoDOW(str1);
                    break label595;
                    label790: if (i != 0)
                    {
                      localDateType = WeatherLookupHandler.DateType.TOMORROW;
                      break label637;
                    }
                    localDateType = WeatherLookupHandler.DateType.OTHER;
                    break label637;
                    localWeatherNotFound1 = localWeatherNotFound1;
                    localWeatherNotFound1.printStackTrace();
                  }
                  catch (WeatherElement.WeatherNotFound localWeatherNotFound2)
                  {
                    localWeatherNotFound2.printStackTrace();
                  }
                }
              }
              break label302;
              label831: WeatherLookupHandler.this.getListener().showVlingoTextAndTTS((String)localObject1, (String)localObject2);
              break label317;
              label848: WeatherLookupHandler.this.getListener().showWidget(WidgetUtil.WidgetKey.ShowWeatherWidget, null, WeatherLookupHandler.this.adaptor, WeatherLookupHandler.this);
            }
          }
          catch (ParseException localParseException)
          {
            while (true)
            {
              continue;
              localObject2 = localObject1;
            }
          }
        }
      }
    });
  }

  private String strDatetoDOW(String paramString)
  {
    String str = null;
    try
    {
      Date localDate = sdf2.parse(paramString);
      SimpleDateFormat localSimpleDateFormat;
      if (Settings.getISOLanguage().equals("ru-RU"))
      {
        DateFormatSymbols localDateFormatSymbols = new DateFormatSymbols();
        localDateFormatSymbols.setWeekdays(getListener().getActivityContext().getResources().getStringArray(VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.array.core_russian_weekdays)));
        localSimpleDateFormat = new SimpleDateFormat("EEEE", localDateFormatSymbols);
      }
      while (true)
      {
        str = localSimpleDateFormat.format(localDate);
        break;
        localSimpleDateFormat = new SimpleDateFormat("EEEE", Settings.getCurrentLocale());
        localSimpleDateFormat.applyLocalizedPattern(localSimpleDateFormat.toLocalizedPattern());
      }
    }
    catch (Exception localException)
    {
    }
    return str;
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("weather");
    String str1 = VLActionUtil.getParamString(paramVLAction, "Location", false);
    String str2 = VLActionUtil.getParamString(paramVLAction, "Date", false);
    String str3 = VLActionUtil.getParamString(paramVLAction, "SpokenDate", false);
    this.adaptor.sendWeatherRequest(str2, str3, str1);
    return true;
  }

  protected String getMsg(Integer paramInteger, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt, DateType paramDateType)
  {
    ResourceIdProvider.string localstring = ResourceIdProvider.string.core_weather_date_display;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = paramString3;
    return getString(localstring, arrayOfObject);
  }

  protected String getOneDaySpokenMsg(Integer paramInteger, WeatherInfoUtil paramWeatherInfoUtil, String paramString1, String paramString2, DateType paramDateType, String paramString3)
  {
    return getOneDaySpokenMsgInternal(paramInteger, paramWeatherInfoUtil, paramString1, paramString2, paramDateType);
  }

  protected String getSpokenMsg(Integer paramInteger, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt, DateType paramDateType)
  {
    ResourceIdProvider.string localstring = ResourceIdProvider.string.core_weather_current;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = paramString5;
    return getString(localstring, arrayOfObject);
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.NoData"))
    {
      String str = getString(ResourceIdProvider.string.core_qa_tts_NO_ANS_WEB_SEARCH, new Object[0]);
      unified().showSystemTurn(str);
    }
    while (true)
    {
      return;
      throwUnknownActionException(paramIntent.getAction());
    }
  }

  public void onRequestFailed()
  {
    try
    {
      showFailure();
      return;
    }
    finally
    {
      getListener().asyncHandlerDone();
    }
    throw localObject;
  }

  public void onRequestScheduled()
  {
  }

  public void onResponseReceived()
  {
    try
    {
      WeatherElement localWeatherElement = this.adaptor.getWeatherElement();
      if ((localWeatherElement == null) || (localWeatherElement.getChildCount() == 0))
        showFailure();
      while (true)
      {
        return;
        showSuccess();
      }
    }
    finally
    {
      getListener().asyncHandlerDone();
    }
    throw localObject;
  }

  public static enum DateType
  {
    static
    {
      OTHER = new DateType("OTHER", 2);
      DateType[] arrayOfDateType = new DateType[3];
      arrayOfDateType[0] = TODAY;
      arrayOfDateType[1] = TOMORROW;
      arrayOfDateType[2] = OTHER;
      $VALUES = arrayOfDateType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.WeatherLookupHandler
 * JD-Core Version:    0.6.0
 */