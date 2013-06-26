package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.weather.china.CMAWeatherAdaptor;
import com.vlingo.core.internal.weather.china.CMAWeatherElement;
import com.vlingo.core.internal.weather.china.WeatherCurrent;
import com.vlingo.core.internal.weather.china.WeatherItem;
import com.vlingo.core.internal.weather.china.WeatherTimeItem;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.CMAWeatherResourceUtil;
import com.vlingo.sdk.internal.util.StringUtils;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CMAWeatherSevenDayWidget extends BargeInWidget<CMAWeatherAdaptor>
  implements View.OnClickListener
{
  private static final int CITY_ID_LENGHT = 7;
  private static final int FIFTH_FORECAST_DAY = 5;
  private static final int FIRST_FORECAST_DAY = 1;
  private static final int FOURTH_FORECAST_DAY = 4;
  private static final int SECOND_FORECAST_DAY = 2;
  private static final int SIXTH_FORECAST_DAY = 6;
  private static final int THIRD_FORECAST_DAY = 3;
  private static final int ZEROTH_FORECAST_DAY;
  private static ArrayList<ForecastingWeekly> forecastArrayList;
  private CMAWeatherElement WeatherElement;
  private TextView citi_name;
  private TextView citi_name2;
  private String city;
  private String cityId = "";
  private String climateName;
  private TextView climate_name;
  private String currentDateString;
  private String currentMax;
  private String currentMin;
  private String currentTemp;
  private String currentWeatherCode;
  private TextView date1;
  private TextView date2;
  private TextView date3;
  private TextView date4;
  private TextView date5;
  private TextView date6;
  private TextView date_value;
  private ForecastingWeekly forecastingWeekly;
  private TextView lastupdate;
  private String lastupdated;
  private WidgetActionListener listener;
  private Context mContext;
  private TextView mCurrentTemp;
  private CMAWeatherSevenDayWidgetHandler mHandler = new CMAWeatherSevenDayWidgetHandler(this);
  private TextView mUnit_meter_C;
  private TextView mUnit_meter_F;
  private TextView maxTemperature1;
  private TextView maxTemperature2;
  private TextView maxTemperature3;
  private TextView maxTemperature4;
  private TextView maxTemperature5;
  private TextView maxTemperature6;
  private TextView maxTemperatureOneDay1;
  private TextView minTemperature1;
  private TextView minTemperature2;
  private TextView minTemperature3;
  private TextView minTemperature4;
  private TextView minTemperature5;
  private TextView minTemperature6;
  private TextView minTemperatureOneDay1;
  private String state;
  private String sunrise;
  private String sunset;
  private CMAWeatherAdaptor wAdaptor;
  private FrameLayout weatherBackgroundImage;
  private ImageView weatherImage1;
  private ImageView weatherImage2;
  private ImageView weatherImage3;
  private ImageView weatherImage4;
  private ImageView weatherImage5;
  private ImageView weatherImage6;
  private ImageView weatherImageOneDay1;
  private TextView weekday1;
  private TextView weekday2;
  private TextView weekday3;
  private TextView weekday4;
  private TextView weekday5;
  private TextView weekday6;

  public CMAWeatherSevenDayWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  private void openChineseWeather(int paramInt)
  {
  }

  private void openChineseWeatherWeb()
  {
  }

  private void setValuesForecastWeather()
  {
    if (forecastArrayList != null)
    {
      int i = 0;
      if (i < forecastArrayList.size())
      {
        int j = Color.parseColor("#585f64");
        String str = ((ForecastingWeekly)forecastArrayList.get(i)).getDay();
        label57: CMAWeatherResourceUtil localCMAWeatherResourceUtil;
        if (DateUtils.getDayOfWeekString(1, 20).compareToIgnoreCase(str) == 0)
        {
          j = Color.parseColor("#ea7445");
          localCMAWeatherResourceUtil = new CMAWeatherResourceUtil(Integer.parseInt(((ForecastingWeekly)forecastArrayList.get(i)).getWeatherCode()));
          switch (i)
          {
          default:
          case 0:
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
          }
        }
        while (true)
        {
          i++;
          break;
          if (DateUtils.getDayOfWeekString(7, 20).compareToIgnoreCase(str) != 0)
            break label57;
          j = Color.parseColor("#585f64");
          break label57;
          this.weekday1.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDay());
          this.weekday1.setTextColor(j);
          this.date1.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDate());
          this.weatherImage1.setImageResource(localCMAWeatherResourceUtil.getWeatherDrawableSmallSize());
          this.minTemperature1.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
          this.maxTemperature1.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
          continue;
          this.weekday2.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDay());
          this.weekday2.setTextColor(j);
          this.date2.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDate());
          this.weatherImage2.setImageResource(localCMAWeatherResourceUtil.getWeatherDrawableSmallSize());
          this.minTemperature2.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
          this.maxTemperature2.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
          continue;
          this.weekday3.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDay());
          this.weekday3.setTextColor(j);
          this.date3.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDate());
          this.weatherImage3.setImageResource(localCMAWeatherResourceUtil.getWeatherDrawableSmallSize());
          this.minTemperature3.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
          this.maxTemperature3.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
          continue;
          this.weekday4.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDay());
          this.weekday4.setTextColor(j);
          this.date4.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDate());
          this.weatherImage4.setImageResource(localCMAWeatherResourceUtil.getWeatherDrawableSmallSize());
          this.minTemperature4.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
          this.maxTemperature4.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
          continue;
          this.weekday5.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDay());
          this.weekday5.setTextColor(j);
          this.date5.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDate());
          this.weatherImage5.setImageResource(localCMAWeatherResourceUtil.getWeatherDrawableSmallSize());
          this.minTemperature5.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
          this.maxTemperature5.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
          continue;
          this.weekday6.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDay());
          this.weekday6.setTextColor(j);
          this.date6.setText(((ForecastingWeekly)forecastArrayList.get(i)).getDate());
          this.weatherImage6.setImageResource(localCMAWeatherResourceUtil.getWeatherDrawableSmallSize());
          this.minTemperature6.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
          this.maxTemperature6.setText(((ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
        }
      }
    }
  }

  private void setValuesForecastWeatherOneDay()
  {
    Date localDate1 = null;
    Object localObject = null;
    this.citi_name.setText(this.city);
    this.citi_name2.setText(this.state);
    this.date_value.setText(this.currentDateString);
    this.lastupdate.setText(this.lastupdated);
    this.climate_name.setText(this.climateName);
    if (this.currentTemp != null)
    {
      this.mCurrentTemp.setText(this.currentTemp);
      this.mUnit_meter_F.setVisibility(8);
    }
    while (true)
    {
      if (!StringUtils.isNullOrWhiteSpace(this.currentMin))
        this.minTemperatureOneDay1.setText(this.currentMin + '°');
      if (!StringUtils.isNullOrWhiteSpace(this.currentMax))
        this.maxTemperatureOneDay1.setText(this.currentMax + '°');
      CMAWeatherResourceUtil localCMAWeatherResourceUtil = new CMAWeatherResourceUtil(Integer.parseInt(this.currentWeatherCode));
      int i = localCMAWeatherResourceUtil.getWeatherDrawableMediumSize();
      if (i != 99)
        this.weatherImageOneDay1.setImageResource(i);
      try
      {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm", Locale.US);
        localDate1 = localSimpleDateFormat.parse(this.sunrise);
        Date localDate2 = localSimpleDateFormat.parse(this.sunset);
        localObject = localDate2;
        label242: int j = localCMAWeatherResourceUtil.getWeatherSevenBackgroundDrawable();
        if (j != 99)
        {
          if (localDate1 == null)
            break label333;
          if ((localDate1.getTime() >= System.currentTimeMillis()) || (localObject.getTime() <= System.currentTimeMillis()))
            break label320;
          this.weatherBackgroundImage.setBackgroundResource(j);
        }
        while (true)
        {
          return;
          this.mCurrentTemp.setVisibility(4);
          this.mUnit_meter_C.setVisibility(8);
          this.mUnit_meter_F.setVisibility(8);
          break;
          label320: this.weatherBackgroundImage.setBackgroundResource(2130838227);
          continue;
          label333: this.weatherBackgroundImage.setBackgroundResource(2130838227);
        }
      }
      catch (ParseException localParseException)
      {
        break label242;
      }
    }
  }

  public void initialize(CMAWeatherAdaptor paramCMAWeatherAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    this.wAdaptor = paramCMAWeatherAdaptor;
    onResponseReceived();
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131558435:
    case 2131558438:
    case 2131558441:
    case 2131558444:
    case 2131558447:
    case 2131558450:
    case 2131558415:
    }
    while (true)
    {
      return;
      openChineseWeather(2);
      continue;
      openChineseWeather(3);
      continue;
      openChineseWeather(4);
      continue;
      openChineseWeather(5);
      continue;
      openChineseWeather(6);
      continue;
      openChineseWeather(7);
      continue;
      openChineseWeatherWeb();
    }
  }

  public void onFinishInflate()
  {
    this.weekday1 = ((TextView)findViewById(2131558436));
    this.weekday2 = ((TextView)findViewById(2131558439));
    this.weekday3 = ((TextView)findViewById(2131558442));
    this.weekday4 = ((TextView)findViewById(2131558445));
    this.weekday5 = ((TextView)findViewById(2131558448));
    this.weekday6 = ((TextView)findViewById(2131558451));
    this.date1 = ((TextView)findViewById(2131558437));
    this.date2 = ((TextView)findViewById(2131558440));
    this.date3 = ((TextView)findViewById(2131558443));
    this.date4 = ((TextView)findViewById(2131558446));
    this.date5 = ((TextView)findViewById(2131558449));
    this.date6 = ((TextView)findViewById(2131558452));
    this.weatherImage1 = ((ImageView)findViewById(2131558455));
    this.weatherImage2 = ((ImageView)findViewById(2131558459));
    this.weatherImage3 = ((ImageView)findViewById(2131558463));
    this.weatherImage4 = ((ImageView)findViewById(2131558467));
    this.weatherImage5 = ((ImageView)findViewById(2131558471));
    this.weatherImage6 = ((ImageView)findViewById(2131558475));
    this.minTemperature1 = ((TextView)findViewById(2131558457));
    this.minTemperature2 = ((TextView)findViewById(2131558461));
    this.minTemperature3 = ((TextView)findViewById(2131558465));
    this.minTemperature4 = ((TextView)findViewById(2131558469));
    this.minTemperature5 = ((TextView)findViewById(2131558473));
    this.minTemperature6 = ((TextView)findViewById(2131558477));
    this.maxTemperature1 = ((TextView)findViewById(2131558456));
    this.maxTemperature2 = ((TextView)findViewById(2131558460));
    this.maxTemperature3 = ((TextView)findViewById(2131558464));
    this.maxTemperature4 = ((TextView)findViewById(2131558468));
    this.maxTemperature5 = ((TextView)findViewById(2131558472));
    this.maxTemperature6 = ((TextView)findViewById(2131558476));
    this.citi_name = ((TextView)findViewById(2131558430));
    this.citi_name2 = ((TextView)findViewById(2131558431));
    this.weatherImageOneDay1 = ((ImageView)findViewById(2131558423));
    this.date_value = ((TextView)findViewById(2131558432));
    this.climate_name = ((TextView)findViewById(2131558429));
    this.minTemperatureOneDay1 = ((TextView)findViewById(2131558428));
    this.maxTemperatureOneDay1 = ((TextView)findViewById(2131558427));
    this.mCurrentTemp = ((TextView)findViewById(2131558424));
    this.mUnit_meter_F = ((TextView)findViewById(2131558425));
    this.mUnit_meter_C = ((TextView)findViewById(2131558426));
    this.weatherBackgroundImage = ((FrameLayout)findViewById(2131558422));
    this.lastupdate = ((TextView)findViewById(2131558481));
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onResponseReceived()
  {
    this.WeatherElement = this.wAdaptor.getWeatherElement();
    if (this.WeatherElement == null)
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.NoData");
      this.listener.handleIntent(localIntent, null);
    }
    while (true)
    {
      return;
      this.city = this.wAdaptor.getLocationCity();
      this.state = this.wAdaptor.getLocationState();
      Locale localLocale = MidasSettings.getCurrentLocale();
      this.lastupdated = SimpleDateFormat.getDateTimeInstance(1, 1, localLocale).format(this.WeatherElement.lastUpdate);
      this.currentDateString = DateUtil.formatWidgetDate(new Date(), localLocale);
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd ", Locale.US);
      this.sunrise = (localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())) + ((WeatherItem)this.WeatherElement.weatherItems.get(0)).sunrise);
      this.sunset = (localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())) + ((WeatherItem)this.WeatherElement.weatherItems.get(0)).sunset);
      if (this.WeatherElement.weatherCurrent != null)
      {
        this.climateName = this.WeatherElement.weatherCurrent.weatherPhenomenon;
        this.currentTemp = this.WeatherElement.weatherCurrent.temperature;
        this.currentWeatherCode = this.WeatherElement.weatherCurrent.weatherCode;
      }
      while (true)
      {
        this.currentMax = ((WeatherItem)this.WeatherElement.weatherItems.get(0)).day.temperature;
        this.currentMin = ((WeatherItem)this.WeatherElement.weatherItems.get(0)).night.temperature;
        forecastArrayList = new ArrayList();
        for (int i = 1; i < this.WeatherElement.weatherItems.size(); i++)
        {
          WeatherItem localWeatherItem = (WeatherItem)this.WeatherElement.weatherItems.get(i);
          if (localWeatherItem == null)
            continue;
          Date localDate = localWeatherItem.date;
          String str1 = new SimpleDateFormat("EEE", localLocale).format(localDate).toUpperCase(localLocale);
          String str2 = new SimpleDateFormat("MM/dd", Locale.US).format(localDate);
          String str3 = localWeatherItem.day.weatherCode;
          if (StringUtils.isNullOrWhiteSpace(str3))
          {
            str3 = localWeatherItem.night.weatherCode;
            if (StringUtils.isNullOrWhiteSpace(str3))
              str3 = "99";
          }
          String str4 = localWeatherItem.day.temperature;
          String str5 = localWeatherItem.night.temperature;
          this.forecastingWeekly = new ForecastingWeekly(null);
          this.forecastingWeekly.setDay(str1);
          this.forecastingWeekly.setDate(str2);
          this.forecastingWeekly.setMinTemp(str5);
          this.forecastingWeekly.setMaxTemp(str4);
          this.forecastingWeekly.setweatherCode(str3);
          forecastArrayList.add(this.forecastingWeekly);
        }
        this.climateName = ((WeatherItem)this.WeatherElement.weatherItems.get(0)).day.weatherPhenomenon;
        if (StringUtils.isNullOrWhiteSpace(this.climateName))
        {
          this.climateName = ((WeatherItem)this.WeatherElement.weatherItems.get(0)).night.weatherPhenomenon;
          if (StringUtils.isNullOrWhiteSpace(this.climateName))
            this.climateName = "";
        }
        this.currentWeatherCode = ((WeatherItem)this.WeatherElement.weatherItems.get(0)).day.weatherCode;
        if (!StringUtils.isNullOrWhiteSpace(this.currentWeatherCode))
          continue;
        this.currentWeatherCode = ((WeatherItem)this.WeatherElement.weatherItems.get(0)).night.weatherCode;
        if (!StringUtils.isNullOrWhiteSpace(this.currentWeatherCode))
          continue;
        this.currentWeatherCode = "99";
      }
      this.mHandler.sendEmptyMessage(0);
    }
  }

  private static class CMAWeatherSevenDayWidgetHandler extends Handler
  {
    private final WeakReference<CMAWeatherSevenDayWidget> outer;

    CMAWeatherSevenDayWidgetHandler(CMAWeatherSevenDayWidget paramCMAWeatherSevenDayWidget)
    {
      this.outer = new WeakReference(paramCMAWeatherSevenDayWidget);
    }

    public void handleMessage(Message paramMessage)
    {
      CMAWeatherSevenDayWidget localCMAWeatherSevenDayWidget = (CMAWeatherSevenDayWidget)this.outer.get();
      if (localCMAWeatherSevenDayWidget != null)
      {
        localCMAWeatherSevenDayWidget.setValuesForecastWeatherOneDay();
        localCMAWeatherSevenDayWidget.setValuesForecastWeather();
      }
    }
  }

  private class ForecastingWeekly
  {
    private String date;
    private String day;
    private String maxTemp;
    private String minTemp;
    private String weatherCode;

    private ForecastingWeekly()
    {
    }

    private String getDay()
    {
      return this.day;
    }

    private String getMaxTemp()
    {
      return this.maxTemp;
    }

    private String getMinTemp()
    {
      return this.minTemp;
    }

    private String getWeatherCode()
    {
      return this.weatherCode;
    }

    private void setDate(String paramString)
    {
      this.date = paramString;
    }

    private void setDay(String paramString)
    {
      this.day = paramString;
    }

    private void setMaxTemp(String paramString)
    {
      this.maxTemp = paramString;
    }

    private void setMinTemp(String paramString)
    {
      this.minTemp = paramString;
    }

    private void setweatherCode(String paramString)
    {
      this.weatherCode = paramString;
    }

    public String getDate()
    {
      return this.date;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.CMAWeatherSevenDayWidget
 * JD-Core Version:    0.6.0
 */