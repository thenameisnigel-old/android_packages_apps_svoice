package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.weather.WeatherAdaptor;
import com.vlingo.core.internal.weather.WeatherElement;
import com.vlingo.core.internal.weather.WeatherElement.WeatherNotFound;
import com.vlingo.core.internal.weather.WeatherInfoUtil;
import com.vlingo.core.internal.weather.WeatherInfoUtil.ForecastingWeekly;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.WeatherResourceUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WeatherSevenDayWidget extends BargeInWidget<WeatherAdaptor>
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
  private static ArrayList<WeatherInfoUtil.ForecastingWeekly> forecastArrayList;
  private static SimpleDateFormat sdf = new SimpleDateFormat("EEE", Locale.US);
  private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
  protected ImageView accuWeather;
  protected LinearLayout accuWeather_Web;
  protected LinearLayout citiDetail;
  protected TextView citi_name;
  protected TextView citi_name2;
  private String cityId = "";
  protected TextView climate_name;
  private WeatherElement currentCondition;
  protected TextView date1;
  protected TextView date2;
  protected TextView date3;
  protected TextView date4;
  protected TextView date5;
  protected TextView date6;
  protected String date_val;
  protected TextView date_value;
  protected TextView divider;
  private WeatherInfoUtil.ForecastingWeekly forecastingWeekly;
  private WeatherElement forecasts;
  private WeatherElement images;
  protected boolean isDrivingmode = false;
  protected boolean isMultiwindow = false;
  protected boolean isNightmode = false;
  protected LinearLayout layout1;
  protected LinearLayout layout2;
  protected LinearLayout layout3;
  protected LinearLayout layout4;
  protected LinearLayout layout5;
  protected LinearLayout layout6;
  private WidgetActionListener listener;
  protected String location;
  protected String location2;
  private Context mContext;
  protected TextView mCurrentTemp;
  private WeatherSevenDayWidgetHandler mHandler = new WeatherSevenDayWidgetHandler(this);
  protected String mLangApplication;
  protected TextView mUnit_meter_C;
  protected TextView mUnit_meter_F;
  private WeatherInfoUtil mWeatherInfoUtil;
  private WeatherInfoUtil mWeatherInfoUtilOneDay;
  protected String max;
  protected TextView maxTemperature1;
  protected TextView maxTemperature2;
  protected TextView maxTemperature3;
  protected TextView maxTemperature4;
  protected TextView maxTemperature5;
  protected TextView maxTemperature6;
  protected TextView maxTemperatureOneDay1;
  protected String min;
  protected TextView minTemperature1;
  protected TextView minTemperature2;
  protected TextView minTemperature3;
  protected TextView minTemperature4;
  protected TextView minTemperature5;
  protected TextView minTemperature6;
  protected TextView minTemperatureOneDay1;
  private WeatherElement moonPhases;
  private String sunRise;
  private String sunSet;
  protected LinearLayout tempContainer;
  protected String temp_unit;
  private WeatherAdaptor wAdaptor;
  protected RelativeLayout weatherBackgroundImage;
  protected FrameLayout weatherBackgroundImageNew;
  protected ImageView weatherBox1;
  protected ImageView weatherBox2;
  private WeatherElement weatherDetails;
  protected ImageView weatherImage1;
  protected ImageView weatherImage2;
  protected ImageView weatherImage3;
  protected ImageView weatherImage4;
  protected ImageView weatherImage5;
  protected ImageView weatherImage6;
  protected ImageView weatherImageOneDay1;
  protected TextView weekday1;
  protected TextView weekday2;
  protected TextView weekday3;
  protected TextView weekday4;
  protected TextView weekday5;
  protected TextView weekday6;
  protected LinearLayout weeklyLayout;
  protected RelativeLayout weeklyRelativeLayout;
  protected LinearLayout widgetCitiLinear;

  public WeatherSevenDayWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    this.mWeatherInfoUtil = new WeatherInfoUtil();
  }

  private String buildAccuWeatherUrl(String paramString1, String paramString2, String paramString3)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("http://www.accuweather.com/m/details");
    localStringBuffer.append(paramString1);
    localStringBuffer.append(".aspx?p=samand&lang=");
    localStringBuffer.append(paramString2);
    localStringBuffer.append("&loc=cityId%3A");
    localStringBuffer.append(paramString3);
    return localStringBuffer.toString();
  }

  private String getWeatherDate()
  {
    String str1 = Settings.getLanguageApplication();
    SimpleDateFormat localSimpleDateFormat1;
    SimpleDateFormat localSimpleDateFormat2;
    if ((str1.equals("ko-KR")) || (str1.equals("ja-JP")))
    {
      String[] arrayOfString = str1.split("-");
      localSimpleDateFormat1 = new SimpleDateFormat("MM" + this.mContext.getResources().getString(2131362564) + " dd" + this.mContext.getResources().getString(2131362010) + " E" + this.mContext.getResources().getString(2131362519), new Locale(arrayOfString[0], arrayOfString[1]));
      localSimpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", new Locale(arrayOfString[0], arrayOfString[1]));
    }
    while (true)
    {
      try
      {
        String str3 = localSimpleDateFormat1.format(localSimpleDateFormat2.parse(this.mWeatherInfoUtilOneDay.getWeatherDate()));
        str2 = str3;
        return str2;
      }
      catch (Exception localException)
      {
      }
      String str2 = this.mWeatherInfoUtilOneDay.getDateForOneDayWidget();
    }
  }

  private void openAccuWeather(int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(buildAccuWeatherUrl(String.valueOf(paramInt), Settings.getString("language", "en-US"), this.cityId)));
    getContext().startActivity(localIntent);
  }

  private void openAccuWeatherWeb()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("http://www.accuweather.com/m"));
    getContext().startActivity(localIntent);
  }

  private void setValuesForcastWeather()
  {
    Color.parseColor("#585f64");
    Locale localLocale = MidasSettings.getCurrentLocale();
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("EEE", Locale.US);
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("EEE", localLocale);
    if (forecastArrayList != null)
    {
      int i = 0;
      while (true)
        if (i < forecastArrayList.size())
        {
          int j;
          Object localObject;
          if ((this.isNightmode) && (this.isDrivingmode))
          {
            j = Color.parseColor("#8d8d8d");
            localObject = ((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays();
          }
          try
          {
            String str = localSimpleDateFormat1.format(localSimpleDateFormat2.parse((String)localObject));
            localObject = str;
            if (((String)localObject).compareToIgnoreCase("SUN") == 0)
              j = Color.parseColor("#ea7445");
            this.mWeatherInfoUtil.setWeatherCode(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getWeatherCode());
            localWeatherResourceUtil = new WeatherResourceUtil(Integer.parseInt(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getWeatherCode()));
            switch (i)
            {
            default:
              i++;
              continue;
              j = Color.parseColor("#585f64");
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            }
          }
          catch (ParseException localParseException)
          {
            WeatherResourceUtil localWeatherResourceUtil;
            while (true)
              localParseException.printStackTrace();
            this.weekday1.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays());
            this.weekday1.setTextColor(j);
            this.date1.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
            if ((this.isNightmode) && (this.isDrivingmode))
              this.weatherImage1.setImageResource(localWeatherResourceUtil.changeToNightimage(localWeatherResourceUtil.getWeatherDrawableSmallSize()));
            while (true)
            {
              this.minTemperature1.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp());
              this.maxTemperature1.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp());
              break;
              this.weatherImage1.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
            }
            this.weekday2.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays());
            this.weekday2.setTextColor(j);
            this.date2.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
            if ((this.isNightmode) && (this.isDrivingmode))
              this.weatherImage2.setImageResource(localWeatherResourceUtil.changeToNightimage(localWeatherResourceUtil.getWeatherDrawableSmallSize()));
            while (true)
            {
              this.minTemperature2.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp());
              this.maxTemperature2.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp());
              break;
              this.weatherImage2.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
            }
            this.weekday3.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays());
            this.weekday3.setTextColor(j);
            this.date3.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
            if ((this.isNightmode) && (this.isDrivingmode))
              this.weatherImage3.setImageResource(localWeatherResourceUtil.changeToNightimage(localWeatherResourceUtil.getWeatherDrawableSmallSize()));
            while (true)
            {
              this.minTemperature3.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp());
              this.maxTemperature3.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp());
              break;
              this.weatherImage3.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
            }
            this.weekday4.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays());
            this.weekday4.setTextColor(j);
            this.date4.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
            if ((this.isNightmode) && (this.isDrivingmode))
              this.weatherImage4.setImageResource(localWeatherResourceUtil.changeToNightimage(localWeatherResourceUtil.getWeatherDrawableSmallSize()));
            while (true)
            {
              this.minTemperature4.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp());
              this.maxTemperature4.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp());
              break;
              this.weatherImage4.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
            }
            this.weekday5.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays());
            this.weekday5.setTextColor(j);
            this.date5.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
            if ((this.isNightmode) && (this.isDrivingmode))
              this.weatherImage5.setImageResource(localWeatherResourceUtil.changeToNightimage(localWeatherResourceUtil.getWeatherDrawableSmallSize()));
            while (true)
            {
              this.minTemperature5.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp());
              this.maxTemperature5.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp());
              break;
              this.weatherImage5.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
            }
            this.weekday6.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays());
            this.weekday6.setTextColor(j);
            this.date6.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
            if ((this.isNightmode) && (this.isDrivingmode))
              this.weatherImage6.setImageResource(localWeatherResourceUtil.changeToNightimage(localWeatherResourceUtil.getWeatherDrawableSmallSize()));
            while (true)
            {
              this.minTemperature6.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp());
              this.maxTemperature6.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp());
              break;
              this.weatherImage6.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
            }
          }
        }
    }
  }

  private void setValuesForcastWeatherOneDay()
  {
    if (this.min != null)
    {
      int m = (int)Double.parseDouble(this.min);
      this.minTemperatureOneDay1.setText(m + "" + '°');
    }
    if (this.max != null)
    {
      int k = (int)Double.parseDouble(this.max);
      this.maxTemperatureOneDay1.setText(k + "" + '°');
    }
    WeatherResourceUtil localWeatherResourceUtil = new WeatherResourceUtil(this.mWeatherInfoUtilOneDay.getCurrentWeatherCode());
    this.citi_name.setText(this.location);
    int i = localWeatherResourceUtil.getWeatherDrawable();
    if (i != 101)
      this.weatherImageOneDay1.setImageResource(i);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd h:mm a", Locale.US);
    try
    {
      if (this.sunRise != null)
        localSimpleDateFormat.parse(this.sunRise);
      if (this.sunSet != null)
        localSimpleDateFormat.parse(this.sunSet);
      label191: int j = localWeatherResourceUtil.getWeatherSevenBackgroundDrawable();
      if (j != 101)
      {
        if (this.isNightmode)
          this.weatherBackgroundImageNew.setBackgroundResource(localWeatherResourceUtil.changeToNightBackground(j));
      }
      else
      {
        this.climate_name.setText(this.mWeatherInfoUtilOneDay.getWeatherString());
        this.date_value.setText(this.date_val);
        if (this.mCurrentTemp != null)
        {
          if (!this.wAdaptor.isToday())
            break label359;
          this.mCurrentTemp.setText(this.mWeatherInfoUtilOneDay.getCurrentTemp() + "");
          if ((this.temp_unit == null) || (!this.temp_unit.equals(getResources().getString(2131362583))))
            break label347;
          this.mUnit_meter_C.setVisibility(8);
        }
      }
      while (true)
      {
        return;
        this.weatherBackgroundImageNew.setBackgroundResource(j);
        break;
        label347: this.mUnit_meter_F.setVisibility(8);
        continue;
        label359: this.mCurrentTemp.setVisibility(8);
        this.mUnit_meter_C.setVisibility(8);
        this.mUnit_meter_F.setVisibility(8);
      }
    }
    catch (ParseException localParseException)
    {
      break label191;
    }
  }

  public void initialize(WeatherAdaptor paramWeatherAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    this.wAdaptor = paramWeatherAdaptor;
    onResponseReceived();
    this.isMultiwindow = MidasSettings.isMultiwindowedMode();
    this.isDrivingmode = MidasSettings.isDrivingModeEnabled();
    this.isNightmode = MidasSettings.isNightMode();
    if ((this.isDrivingmode) && (!this.isMultiwindow) && (getResources().getConfiguration().orientation == 2))
      setLandscapeWidget();
    while (true)
    {
      return;
      if ((this.isDrivingmode) && (this.isNightmode))
      {
        setPortraitWidget();
        continue;
      }
    }
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
      openAccuWeather(2);
      continue;
      openAccuWeather(3);
      continue;
      openAccuWeather(4);
      continue;
      openAccuWeather(5);
      continue;
      openAccuWeather(6);
      continue;
      openAccuWeather(7);
      continue;
      openAccuWeatherWeb();
    }
  }

  public void onFinishInflate()
  {
    this.layout1 = ((LinearLayout)findViewById(2131558435));
    this.layout1.setOnClickListener(this);
    this.layout2 = ((LinearLayout)findViewById(2131558438));
    this.layout2.setOnClickListener(this);
    this.layout3 = ((LinearLayout)findViewById(2131558441));
    this.layout3.setOnClickListener(this);
    this.layout4 = ((LinearLayout)findViewById(2131558444));
    this.layout4.setOnClickListener(this);
    this.layout5 = ((LinearLayout)findViewById(2131558447));
    this.layout5.setOnClickListener(this);
    this.layout6 = ((LinearLayout)findViewById(2131558450));
    this.layout6.setOnClickListener(this);
    this.weeklyLayout = ((LinearLayout)findViewById(2131558979));
    this.weeklyRelativeLayout = ((RelativeLayout)findViewById(2131558969));
    this.weekday1 = ((TextView)findViewById(2131558981));
    this.weekday2 = ((TextView)findViewById(2131558986));
    this.weekday3 = ((TextView)findViewById(2131558991));
    this.weekday4 = ((TextView)findViewById(2131558997));
    this.weekday5 = ((TextView)findViewById(2131559002));
    this.weekday6 = ((TextView)findViewById(2131559007));
    this.date1 = ((TextView)findViewById(2131558982));
    this.date2 = ((TextView)findViewById(2131558987));
    this.date3 = ((TextView)findViewById(2131558992));
    this.date4 = ((TextView)findViewById(2131558998));
    this.date5 = ((TextView)findViewById(2131559003));
    this.date6 = ((TextView)findViewById(2131559008));
    this.weatherImage1 = ((ImageView)findViewById(2131558983));
    this.weatherImage2 = ((ImageView)findViewById(2131558988));
    this.weatherImage3 = ((ImageView)findViewById(2131558993));
    this.weatherImage4 = ((ImageView)findViewById(2131558999));
    this.weatherImage5 = ((ImageView)findViewById(2131559004));
    this.weatherImage6 = ((ImageView)findViewById(2131559009));
    this.minTemperature1 = ((TextView)findViewById(2131558985));
    this.minTemperature2 = ((TextView)findViewById(2131558990));
    this.minTemperature3 = ((TextView)findViewById(2131558995));
    this.minTemperature4 = ((TextView)findViewById(2131559001));
    this.minTemperature5 = ((TextView)findViewById(2131559006));
    this.minTemperature6 = ((TextView)findViewById(2131559011));
    this.maxTemperature1 = ((TextView)findViewById(2131558984));
    this.maxTemperature2 = ((TextView)findViewById(2131558989));
    this.maxTemperature3 = ((TextView)findViewById(2131558994));
    this.maxTemperature4 = ((TextView)findViewById(2131559000));
    this.maxTemperature5 = ((TextView)findViewById(2131559005));
    this.maxTemperature6 = ((TextView)findViewById(2131559010));
    this.weatherBox1 = ((ImageView)findViewById(2131558977));
    this.weatherBox2 = ((ImageView)findViewById(2131558978));
    this.citiDetail = ((LinearLayout)findViewById(2131558401));
    this.citi_name = ((TextView)findViewById(2131558963));
    this.weatherImageOneDay1 = ((ImageView)findViewById(2131558966));
    this.date_value = ((TextView)findViewById(2131558964));
    this.climate_name = ((TextView)findViewById(2131558976));
    this.minTemperatureOneDay1 = ((TextView)findViewById(2131558975));
    this.maxTemperatureOneDay1 = ((TextView)findViewById(2131558973));
    this.mCurrentTemp = ((TextView)findViewById(2131558970));
    this.mUnit_meter_F = ((TextView)findViewById(2131558971));
    this.mUnit_meter_C = ((TextView)findViewById(2131558972));
    this.divider = ((TextView)findViewById(2131558974));
    this.weatherBackgroundImage = ((RelativeLayout)findViewById(2131558421));
    this.weatherBackgroundImageNew = ((FrameLayout)findViewById(2131558965));
    this.widgetCitiLinear = ((LinearLayout)findViewById(2131558420));
    this.tempContainer = ((LinearLayout)findViewById(2131558968));
    this.accuWeather = ((ImageView)findViewById(2131558967));
    this.accuWeather.setOnClickListener(this);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onResponseReceived()
  {
    this.weatherDetails = this.wAdaptor.getWeatherElement();
    this.mLangApplication = Settings.getLanguageApplication();
    String[] arrayOfString = this.mLangApplication.split("-");
    sdf = new SimpleDateFormat("EEE", new Locale(arrayOfString[0], arrayOfString[1]));
    sdf2 = new SimpleDateFormat("yyyy-MM-dd", new Locale(arrayOfString[0], arrayOfString[1]));
    if ((this.weatherDetails == null) || (this.weatherDetails.getChildCount() == 0))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.NoData");
      this.listener.handleIntent(localIntent, null);
    }
    while (true)
    {
      return;
      int i;
      label182: WeatherElement localWeatherElement1;
      try
      {
        this.location = this.wAdaptor.getLocationCity();
        this.location2 = this.wAdaptor.getLocationState();
      }
      catch (WeatherElement.WeatherNotFound localWeatherNotFound1)
      {
        try
        {
          while (true)
          {
            this.temp_unit = ((String)this.weatherDetails.findNamedChild("Units").findNamedChild("Units").getAttributes().get("Temperature"));
            i = 0;
            int j = this.weatherDetails.getChildCount();
            if (i >= j)
              break label1426;
            localWeatherElement1 = this.weatherDetails.getChild(i);
            if (!"CurrentCondition".equals(localWeatherElement1.getName()))
              break;
            if (!"CurrentCondition".equals(localWeatherElement1.getChild(0).getName()))
              break label1420;
            this.currentCondition = localWeatherElement1.getChild(0);
            this.mWeatherInfoUtilOneDay = new WeatherInfoUtil(this.currentCondition, this.mContext);
            SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd ", Locale.US);
            this.sunRise = (localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())) + (String)this.currentCondition.getAttributes().get("Sunrise"));
            this.sunSet = (localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())) + (String)this.currentCondition.getAttributes().get("Sunset"));
            Iterator localIterator3 = this.currentCondition.getAttributes().entrySet().iterator();
            while (localIterator3.hasNext())
              ((Map.Entry)localIterator3.next());
            localWeatherNotFound1 = localWeatherNotFound1;
            localWeatherNotFound1.printStackTrace();
          }
        }
        catch (WeatherElement.WeatherNotFound localWeatherNotFound2)
        {
          while (true)
            localWeatherNotFound2.printStackTrace();
        }
      }
      ArrayList localArrayList;
      Object localObject1;
      if ("Forecasts".equals(localWeatherElement1.getName()))
      {
        if (forecastArrayList != null)
          forecastArrayList = null;
        forecastArrayList = new ArrayList();
        this.forecasts = localWeatherElement1;
        localArrayList = new ArrayList(this.forecasts.getChildCount());
        for (int i3 = 0; ; i3++)
        {
          int i4 = this.forecasts.getChildCount();
          if (i3 >= i4)
            break;
          localArrayList.add(this.forecasts.getChild(i3).getAttributes().get("ForecastDate"));
        }
        localObject1 = null;
      }
      try
      {
        Date localDate4 = sdf2.parse((String)localArrayList.get(0));
        localObject1 = localDate4;
        label577: int i5 = 1;
        label580: int i6 = localArrayList.size();
        if ((i5 >= i6) || (localObject1 != null));
        try
        {
          Date localDate2 = sdf2.parse((String)localArrayList.get(i5));
          if (localObject1.after(localDate2))
          {
            Date localDate3 = sdf2.parse((String)localArrayList.get(i5));
            localObject1 = localDate3;
          }
          label649: i5++;
          break label580;
          this.mWeatherInfoUtilOneDay = WeatherInfoUtil.getWeatherInfoUtil(this.mContext, this.weatherDetails, this.wAdaptor.getDate());
          this.date_val = getWeatherDate();
          if (this.date_val == null);
          this.max = this.mWeatherInfoUtilOneDay.getMaximumTempOneDayWeather();
          if (this.max == null);
          this.min = this.mWeatherInfoUtilOneDay.getMinimumTempOneDayWeather();
          if (this.min == null);
          int i7 = 0;
          int i8 = this.forecasts.getChildCount();
          WeatherElement localWeatherElement3;
          WeatherElement localWeatherElement4;
          Object localObject2;
          String str1;
          label856: Object localObject3;
          if (i7 < i8)
          {
            WeatherElement localWeatherElement2 = this.forecasts.getChild(i7);
            if (localWeatherElement2 != null)
            {
              int i9 = localWeatherElement2.getChildCount();
              localWeatherElement3 = null;
              localWeatherElement4 = null;
              if (i9 > 0)
              {
                localWeatherElement3 = localWeatherElement2.getChild(0).getChild(0);
                if (i9 > 1)
                  localWeatherElement4 = localWeatherElement2.getChild(1).getChild(0);
              }
              localObject2 = (String)localWeatherElement2.getAttributes().get("ForecastDate");
              str1 = "";
              if (localWeatherElement3 == null)
                break label966;
              str1 = (String)localWeatherElement3.getAttributes().get("WeatherCode");
              localObject3 = localObject2;
            }
          }
          try
          {
            Date localDate1 = sdf2.parse((String)localObject2);
            String str5 = sdf.format(localDate1);
            localObject2 = str5;
            label884: String str2 = (String)localWeatherElement3.getAttributes().get("TempMax");
            String str3 = (String)localWeatherElement4.getAttributes().get("TempMin");
            switch (i7)
            {
            default:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            }
            while (true)
            {
              i7++;
              break;
              label966: if (localWeatherElement4 == null)
                break label856;
              str1 = (String)localWeatherElement4.getAttributes().get("WeatherCode");
              break label856;
              String str4 = localObject3.substring(5, 7) + "/" + localObject3.substring(8, 10);
              WeatherInfoUtil localWeatherInfoUtil = this.mWeatherInfoUtil;
              localWeatherInfoUtil.getClass();
              this.forecastingWeekly = new WeatherInfoUtil.ForecastingWeekly(localWeatherInfoUtil);
              this.forecastingWeekly.setDays((String)localObject2);
              this.forecastingWeekly.setDate(str4);
              int i10 = (int)Float.parseFloat(str3);
              this.forecastingWeekly.setMinTemp(i10 + "");
              int i11 = (int)Float.parseFloat(str2);
              this.forecastingWeekly.setMaxTemp(i11 + "");
              this.forecastingWeekly.setWeatherCode(str1);
              forecastArrayList.add(this.forecastingWeekly);
            }
            if ("Images".equals(localWeatherElement1.getName()))
            {
              this.images = localWeatherElement1;
              for (int n = 0; ; n++)
              {
                int i1 = this.images.getChildCount();
                if (n >= i1)
                  break;
                Iterator localIterator2 = this.images.getChild(n).getAttributes().entrySet().iterator();
                while (localIterator2.hasNext())
                {
                  Map.Entry localEntry = (Map.Entry)localIterator2.next();
                  if (!((String)localEntry.getKey()).equalsIgnoreCase("url"))
                    continue;
                  int i2 = 7 + ((String)localEntry.getValue()).indexOf("cityId=");
                  try
                  {
                    this.cityId = ((String)localEntry.getValue()).substring(i2);
                  }
                  catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
                  {
                  }
                }
              }
            }
            if ("MoonPhases".equals(localWeatherElement1.getName()))
            {
              this.moonPhases = localWeatherElement1;
              for (int k = 0; ; k++)
              {
                int m = this.moonPhases.getChildCount();
                if (k >= m)
                  break;
                Iterator localIterator1 = this.moonPhases.getChild(k).getAttributes().entrySet().iterator();
                while (localIterator1.hasNext())
                  ((Map.Entry)localIterator1.next());
              }
            }
            label1420: i++;
            break label182;
            label1426: this.mHandler.sendEmptyMessage(0);
          }
          catch (Exception localException)
          {
            break label884;
          }
        }
        catch (ParseException localParseException2)
        {
          break label649;
        }
      }
      catch (ParseException localParseException1)
      {
        break label577;
      }
    }
  }

  public void setLandscapeWidget()
  {
    int i = Color.parseColor("#e0e0e0");
    if (this.isNightmode)
      this.widgetCitiLinear.setBackgroundResource(2130837875);
    while (true)
    {
      this.widgetCitiLinear.setPadding(0, 0, 0, 0);
      if (this.isNightmode)
      {
        this.citi_name.setTextColor(i);
        this.date_value.setTextColor(i);
      }
      this.citiDetail.setOrientation(0);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams.setMargins(0, 0, 0, 0);
      this.date_value.setLayoutParams(localLayoutParams);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(996, 648);
      localLayoutParams1.setMargins(39, 51, 39, 63);
      localLayoutParams1.addRule(3, 2131558401);
      this.weatherBackgroundImageNew.setLayoutParams(localLayoutParams1);
      if (this.isNightmode)
      {
        this.mCurrentTemp.setTextColor(i);
        this.climate_name.setTextColor(i);
        this.mUnit_meter_C.setTextColor(i);
        this.mUnit_meter_F.setTextColor(i);
        this.divider.setTextColor(Color.parseColor("#868686"));
      }
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams2.addRule(8, 2131558965);
      localLayoutParams2.setMargins(75, 0, 0, 90);
      this.tempContainer.setLayoutParams(localLayoutParams2);
      this.weeklyLayout.setOrientation(1);
      this.weeklyLayout.setPadding(9, 0, 9, 0);
      if (this.isNightmode)
        this.weeklyRelativeLayout.setBackgroundResource(2130837877);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams3.addRule(11);
      localLayoutParams3.addRule(12);
      localLayoutParams3.addRule(1, 2131558965);
      localLayoutParams3.setMargins(0, 210, 9, 21);
      this.weeklyRelativeLayout.setLayoutParams(localLayoutParams3);
      this.weeklyRelativeLayout.setPadding(54, 15, 54, 60);
      if (this.isNightmode)
      {
        this.weatherBox1.setBackgroundResource(2130837887);
        this.weatherBox2.setBackgroundResource(2130837887);
      }
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(507, 180);
      localLayoutParams4.addRule(9);
      localLayoutParams4.addRule(10);
      localLayoutParams4.setMargins(0, 195, 0, 0);
      this.weatherBox1.setLayoutParams(localLayoutParams4);
      this.weatherBox2.setVisibility(0);
      return;
      this.widgetCitiLinear.setBackgroundResource(2130837744);
    }
  }

  public void setMinimize()
  {
    if (this.isNightmode)
      this.widgetCitiLinear.setBackgroundResource(2130837875);
    while (true)
    {
      this.widgetCitiLinear.setPadding(0, 0, 0, 0);
      this.weatherBackgroundImageNew.setVisibility(8);
      this.date_value.setVisibility(8);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams1.setMargins(51, 0, 0, 15);
      localLayoutParams1.addRule(2, 2131558969);
      this.tempContainer.setLayoutParams(localLayoutParams1);
      if (this.isNightmode)
        this.weeklyRelativeLayout.setBackgroundResource(2130837877);
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams2.setMargins(9, 0, 9, 21);
      localLayoutParams2.addRule(12);
      this.weeklyRelativeLayout.setLayoutParams(localLayoutParams2);
      this.weeklyRelativeLayout.setPadding(21, 0, 21, 39);
      this.weeklyLayout.setPadding(6, 0, 6, 0);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(285, -2);
      localLayoutParams3.addRule(12);
      localLayoutParams3.addRule(11);
      localLayoutParams3.setMargins(0, 0, 20, 35);
      this.accuWeather.setLayoutParams(localLayoutParams3);
      this.accuWeather.bringToFront();
      return;
      this.widgetCitiLinear.setBackgroundResource(2130837745);
    }
  }

  public void setPortraitWidget()
  {
    int i = Color.parseColor("#e0e0e0");
    if (this.isNightmode)
      this.widgetCitiLinear.setBackgroundResource(2130837876);
    while (true)
    {
      this.widgetCitiLinear.setPadding(0, 0, 0, 0);
      if (this.isNightmode)
      {
        this.citi_name.setTextColor(i);
        this.date_value.setTextColor(i);
      }
      this.citiDetail.setOrientation(1);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams.setMargins(0, 0, 0, 0);
      this.date_value.setLayoutParams(localLayoutParams);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams1.setMargins(33, 33, 33, 0);
      localLayoutParams1.addRule(3, 2131558401);
      this.weatherBackgroundImageNew.setLayoutParams(localLayoutParams1);
      if (this.isNightmode)
      {
        this.mCurrentTemp.setTextColor(i);
        this.climate_name.setTextColor(i);
        this.mUnit_meter_C.setTextColor(i);
        this.mUnit_meter_F.setTextColor(i);
        this.divider.setTextColor(Color.parseColor("#868686"));
      }
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams2.addRule(2, 2131558969);
      localLayoutParams2.setMargins(51, 0, 0, 15);
      this.tempContainer.setLayoutParams(localLayoutParams2);
      this.weeklyLayout.setOrientation(0);
      this.weeklyLayout.setPadding(9, 0, 9, 0);
      if (this.isNightmode)
        this.weeklyRelativeLayout.setBackgroundResource(2130837877);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams3.addRule(12);
      localLayoutParams3.setMargins(9, 0, 9, 21);
      this.weeklyRelativeLayout.setLayoutParams(localLayoutParams3);
      this.weeklyRelativeLayout.setPadding(18, 0, 18, 15);
      if (this.isNightmode)
      {
        this.weatherBox1.setBackgroundResource(2130837887);
        this.weatherBox2.setBackgroundResource(2130837887);
      }
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, 234);
      localLayoutParams4.addRule(9);
      localLayoutParams4.addRule(10);
      localLayoutParams4.setMargins(0, 156, 0, 0);
      this.weatherBox1.setLayoutParams(localLayoutParams4);
      this.weatherBox2.setVisibility(8);
      return;
      this.widgetCitiLinear.setBackgroundResource(2130837589);
    }
  }

  private static class WeatherSevenDayWidgetHandler extends Handler
  {
    private final WeakReference<WeatherSevenDayWidget> outer;

    WeatherSevenDayWidgetHandler(WeatherSevenDayWidget paramWeatherSevenDayWidget)
    {
      this.outer = new WeakReference(paramWeatherSevenDayWidget);
    }

    public void handleMessage(Message paramMessage)
    {
      WeatherSevenDayWidget localWeatherSevenDayWidget = (WeatherSevenDayWidget)this.outer.get();
      if (localWeatherSevenDayWidget != null)
      {
        localWeatherSevenDayWidget.setValuesForcastWeatherOneDay();
        localWeatherSevenDayWidget.setValuesForcastWeather();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.WeatherSevenDayWidget
 * JD-Core Version:    0.6.0
 */