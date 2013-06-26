package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.weather.WeatherAdaptor;
import com.vlingo.core.internal.weather.WeatherElement;
import com.vlingo.core.internal.weather.WeatherInfoUtil;
import com.vlingo.core.internal.weather.WeatherInfoUtil.ForecastingWeekly;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.WeatherResourceUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WeatherWidget extends BargeInWidget<WeatherAdaptor>
{
  private static final int FIFTH_FORECAST_DAY = 5;
  private static final int FIRST_FORECAST_DAY = 1;
  private static final int FOURTH_FORECAST_DAY = 4;
  private static final int SECOND_FORECAST_DAY = 2;
  private static final int SIXTH_FORECAST_DAY = 6;
  private static final int THIRD_FORECAST_DAY = 3;
  private static final int ZEROTH_FORECAST_DAY;
  private static ArrayList<WeatherInfoUtil.ForecastingWeekly> forecastArrayList;
  private static final SimpleDateFormat sdf = new SimpleDateFormat("EEE", Locale.US);
  private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
  private WeatherElement currentCondition;
  private TextView date1;
  private TextView date2;
  private TextView date3;
  private TextView date4;
  private TextView date5;
  private TextView date6;
  private WeatherInfoUtil.ForecastingWeekly forecastingWeekly;
  private WeatherElement forecasts;
  private WeatherElement images;
  private WidgetActionListener listener;
  private Context mContext;
  private WeatherWidgetHandler mHandler = new WeatherWidgetHandler(this);
  private WeatherInfoUtil mWeatherInfoUtil;
  private TextView maxTemperature1;
  private TextView maxTemperature2;
  private TextView maxTemperature3;
  private TextView maxTemperature4;
  private TextView maxTemperature5;
  private TextView maxTemperature6;
  private TextView minTemperature1;
  private TextView minTemperature2;
  private TextView minTemperature3;
  private TextView minTemperature4;
  private TextView minTemperature5;
  private TextView minTemperature6;
  private WeatherElement moonPhases;
  private WeatherAdaptor wAdaptor;
  private WeatherElement weatherDetails;
  private ImageView weatherImage1;
  private ImageView weatherImage2;
  private ImageView weatherImage3;
  private ImageView weatherImage4;
  private ImageView weatherImage5;
  private ImageView weatherImage6;
  private TextView weekday1;
  private TextView weekday2;
  private TextView weekday3;
  private TextView weekday4;
  private TextView weekday5;
  private TextView weekday6;

  public WeatherWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    this.mWeatherInfoUtil = new WeatherInfoUtil();
  }

  private void setValuesForcastWeather()
  {
    WeatherResourceUtil localWeatherResourceUtil = new WeatherResourceUtil(this.mWeatherInfoUtil.getCurrentWeatherCode());
    Locale localLocale = MidasSettings.getCurrentLocale();
    int i = 0;
    if (i < forecastArrayList.size())
    {
      int j = Color.parseColor("#d7d7d7");
      String str = ((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays().toUpperCase(localLocale);
      if (DateUtils.getDayOfWeekString(1, 20).compareToIgnoreCase(str) == 0)
      {
        j = Color.parseColor("#ff6633");
        label78: this.mWeatherInfoUtil.setWeatherCode(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getWeatherCode());
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
          break label78;
        j = Color.parseColor("#6699cc");
        break label78;
        this.weekday1.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays().toUpperCase(localLocale));
        this.weekday1.setTextColor(j);
        this.date1.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
        this.weatherImage1.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
        this.minTemperature1.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
        this.maxTemperature1.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
        continue;
        this.weekday2.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays().toUpperCase(localLocale));
        this.weekday2.setTextColor(j);
        this.date2.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
        this.weatherImage2.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
        this.minTemperature2.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
        this.maxTemperature2.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
        continue;
        this.weekday3.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays().toUpperCase(localLocale));
        this.weekday3.setTextColor(j);
        this.date3.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
        this.weatherImage3.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
        this.minTemperature3.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
        this.maxTemperature3.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
        continue;
        this.weekday4.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays().toUpperCase(localLocale));
        this.weekday4.setTextColor(j);
        this.date4.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
        this.weatherImage4.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
        this.minTemperature4.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
        this.maxTemperature4.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
        continue;
        this.weekday5.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays().toUpperCase(localLocale));
        this.weekday5.setTextColor(j);
        this.date5.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
        this.weatherImage5.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
        this.minTemperature5.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
        this.maxTemperature5.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
        continue;
        this.weekday6.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDays().toUpperCase(localLocale));
        this.weekday6.setTextColor(j);
        this.date6.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getDate());
        this.weatherImage6.setImageResource(localWeatherResourceUtil.getWeatherDrawableSmallSize());
        this.minTemperature6.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMinTemp() + '°');
        this.maxTemperature6.setText(((WeatherInfoUtil.ForecastingWeekly)forecastArrayList.get(i)).getMaxTemp() + '°');
      }
    }
  }

  public void initialize(WeatherAdaptor paramWeatherAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    this.wAdaptor = paramWeatherAdaptor;
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

  public void onFinishInflate()
  {
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
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onResponseReceived()
  {
    this.weatherDetails = this.wAdaptor.getWeatherElement();
    if ((this.weatherDetails == null) || (this.weatherDetails.getChildCount() == 0))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.NoData");
      this.listener.handleIntent(localIntent, null);
      return;
    }
    int i = 0;
    while (i < this.weatherDetails.getChildCount())
    {
      WeatherElement localWeatherElement1 = this.weatherDetails.getChild(i);
      int m;
      WeatherElement localWeatherElement3;
      WeatherElement localWeatherElement4;
      Object localObject1;
      String str1;
      Object localObject2;
      if ("CurrentCondition".equals(localWeatherElement1.getName()))
      {
        if ("CurrentCondition".equals(localWeatherElement1.getChild(0).getName()))
        {
          this.currentCondition = localWeatherElement1.getChild(0);
          Iterator localIterator3 = this.currentCondition.getAttributes().entrySet().iterator();
          while (localIterator3.hasNext())
            ((Map.Entry)localIterator3.next());
        }
      }
      else if ("Forecasts".equals(localWeatherElement1.getName()))
      {
        if (forecastArrayList != null)
          forecastArrayList = null;
        forecastArrayList = new ArrayList();
        this.forecasts = localWeatherElement1;
        m = 0;
        if (m >= this.forecasts.getChildCount())
          break label759;
        WeatherElement localWeatherElement2 = this.forecasts.getChild(m);
        localWeatherElement3 = localWeatherElement2.getChild(0).getChild(0);
        localWeatherElement4 = localWeatherElement2.getChild(1).getChild(0);
        localObject1 = (String)localWeatherElement2.getAttributes().get("ForecastDate");
        str1 = (String)localWeatherElement3.getAttributes().get("WeatherCode");
        localObject2 = localObject1;
      }
      try
      {
        Date localDate = sdf2.parse((String)localObject1);
        String str5 = sdf.format(localDate);
        localObject1 = str5;
        label318: String str2 = (String)localWeatherElement3.getAttributes().get("TempMax");
        String str3 = (String)localWeatherElement4.getAttributes().get("TempMin");
        switch (m)
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
          m++;
          break;
          String str4 = localObject2.substring(5, 7) + "/" + localObject2.substring(8, 10);
          WeatherInfoUtil localWeatherInfoUtil = this.mWeatherInfoUtil;
          localWeatherInfoUtil.getClass();
          this.forecastingWeekly = new WeatherInfoUtil.ForecastingWeekly(localWeatherInfoUtil);
          this.forecastingWeekly.setDays((String)localObject1);
          this.forecastingWeekly.setDate(str4);
          int n = (int)Float.parseFloat(str3);
          this.forecastingWeekly.setMinTemp(n + "");
          int i1 = (int)Float.parseFloat(str2);
          this.forecastingWeekly.setMaxTemp(i1 + "");
          this.forecastingWeekly.setWeatherCode(str1);
          forecastArrayList.add(this.forecastingWeekly);
        }
        if ("Images".equals(localWeatherElement1.getName()))
        {
          this.images = localWeatherElement1;
          for (int k = 0; k < this.images.getChildCount(); k++)
          {
            Iterator localIterator2 = this.images.getChild(k).getAttributes().entrySet().iterator();
            while (localIterator2.hasNext())
              ((Map.Entry)localIterator2.next());
          }
        }
        if ("MoonPhases".equals(localWeatherElement1.getName()))
        {
          this.moonPhases = localWeatherElement1;
          for (int j = 0; j < this.moonPhases.getChildCount(); j++)
          {
            Iterator localIterator1 = this.moonPhases.getChild(j).getAttributes().entrySet().iterator();
            while (localIterator1.hasNext())
              ((Map.Entry)localIterator1.next());
          }
        }
        label759: i++;
      }
      catch (Exception localException)
      {
        break label318;
      }
    }
  }

  private static class WeatherWidgetHandler extends Handler
  {
    private final WeakReference<WeatherWidget> outer;

    WeatherWidgetHandler(WeatherWidget paramWeatherWidget)
    {
      this.outer = new WeakReference(paramWeatherWidget);
    }

    public void handleMessage(Message paramMessage)
    {
      WeatherWidget localWeatherWidget = (WeatherWidget)this.outer.get();
      if (localWeatherWidget != null)
        localWeatherWidget.setValuesForcastWeather();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.WeatherWidget
 * JD-Core Version:    0.6.0
 */