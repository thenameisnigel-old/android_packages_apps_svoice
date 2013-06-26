package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CMAWeatherOneDayWidget extends BargeInWidget<CMAWeatherAdaptor>
  implements View.OnClickListener
{
  private CMAWeatherElement WeatherElement;
  private TextView citi_name;
  private TextView citi_name2;
  private String city;
  private TextView climate_name;
  private String currentTemp;
  private String dateString;
  private TextView date_value;
  private TextView dividerImageView;
  private TextView lastupdate;
  private String lastupdated;
  private WidgetActionListener listener;
  private Context mContext;
  private TextView mCurrentTemp;
  private CMAWeatherOneDayWidgetHandler mHandler = new CMAWeatherOneDayWidgetHandler(this);
  private TextView mUnit_meter_C;
  private TextView mUnit_meter_F;
  private TextView maxTemperature1;
  private TextView minTemperature1;
  private String state;
  private String sunrise;
  private String sunset;
  private String tempMax;
  private String tempMin;
  private CMAWeatherAdaptor wAdaptor;
  private FrameLayout weatherBackgroundImage;
  private String weatherCode;
  private ImageView weatherImage1;
  private String weatherPhenomenon;

  public CMAWeatherOneDayWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  private void ChineseWeatherWeb()
  {
  }

  private void onResponseReceived()
  {
    this.WeatherElement = this.wAdaptor.getWeatherElement();
    if (this.WeatherElement == null)
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.NoData");
      this.listener.handleIntent(localIntent, null);
      return;
    }
    this.city = this.wAdaptor.getLocationCity();
    this.state = this.wAdaptor.getLocationState();
    Locale localLocale = MidasSettings.getCurrentLocale();
    this.lastupdated = SimpleDateFormat.getDateTimeInstance(1, 1, localLocale).format(this.WeatherElement.lastUpdate);
    WeatherItem localWeatherItem = null;
    if ((this.wAdaptor.isToday()) || (this.wAdaptor.isDatePlusSeven()))
    {
      this.dateString = DateUtil.formatWidgetDate(new Date(), localLocale);
      localWeatherItem = (WeatherItem)this.WeatherElement.weatherItems.get(0);
      if (this.WeatherElement.weatherCurrent != null)
      {
        this.currentTemp = this.WeatherElement.weatherCurrent.temperature;
        this.weatherPhenomenon = this.WeatherElement.weatherCurrent.weatherPhenomenon;
        this.weatherCode = this.WeatherElement.weatherCurrent.weatherCode;
      }
    }
    while (true)
    {
      if (localWeatherItem != null)
      {
        this.tempMax = localWeatherItem.day.temperature;
        this.tempMin = localWeatherItem.night.temperature;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd ", Locale.US);
        this.sunrise = (localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())) + localWeatherItem.sunrise);
        this.sunset = (localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())) + localWeatherItem.sunset);
        this.mHandler.sendEmptyMessage(0);
        break;
        setPhenomenonWeatherCode(localWeatherItem);
        continue;
      }
      break;
      Object localObject = null;
      try
      {
        Date localDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(this.wAdaptor.getDate());
        localObject = localDate;
        label366: for (int i = 1; i < this.WeatherElement.weatherItems.size(); i++)
        {
          if (localObject.getDate() != ((WeatherItem)this.WeatherElement.weatherItems.get(i)).date.getDate())
            continue;
          localWeatherItem = (WeatherItem)this.WeatherElement.weatherItems.get(i);
        }
        if (localWeatherItem == null)
          continue;
        this.dateString = DateUtil.formatWidgetDate(localObject, localLocale);
        setPhenomenonWeatherCode(localWeatherItem);
      }
      catch (ParseException localParseException)
      {
        break label366;
      }
    }
  }

  private void setPhenomenonWeatherCode(WeatherItem paramWeatherItem)
  {
    this.weatherPhenomenon = paramWeatherItem.day.weatherPhenomenon;
    if (StringUtils.isNullOrWhiteSpace(this.weatherPhenomenon))
    {
      this.weatherPhenomenon = paramWeatherItem.night.weatherPhenomenon;
      if (StringUtils.isNullOrWhiteSpace(this.weatherPhenomenon))
        this.weatherPhenomenon = "";
    }
    this.weatherCode = paramWeatherItem.day.weatherCode;
    if (StringUtils.isNullOrWhiteSpace(this.weatherCode))
    {
      this.weatherCode = paramWeatherItem.night.weatherCode;
      if (StringUtils.isNullOrWhiteSpace(this.weatherCode))
        this.weatherCode = "99";
    }
  }

  private void setValuesForcastWeather()
  {
    if (!StringUtils.isNullOrWhiteSpace(this.tempMin))
      this.minTemperature1.setText(this.tempMin + '°');
    if (!StringUtils.isNullOrWhiteSpace(this.tempMax))
      this.maxTemperature1.setText(this.tempMax + '°');
    if ((StringUtils.isNullOrWhiteSpace(this.tempMin)) || (StringUtils.isNullOrWhiteSpace(this.tempMax)))
      this.dividerImageView.setVisibility(8);
    while (true)
    {
      this.citi_name.setText(this.city);
      this.citi_name2.setText(this.state);
      this.date_value.setText(this.dateString);
      this.climate_name.setText(this.weatherPhenomenon);
      this.lastupdate.setText(this.lastupdated);
      CMAWeatherResourceUtil localCMAWeatherResourceUtil = new CMAWeatherResourceUtil(Integer.parseInt(this.weatherCode));
      int i = localCMAWeatherResourceUtil.getWeatherDrawable();
      if (i != 99)
        this.weatherImage1.setImageResource(i);
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm", Locale.US);
      Date localDate1 = null;
      Object localObject = null;
      try
      {
        localDate1 = localSimpleDateFormat.parse(this.sunrise);
        Date localDate2 = localSimpleDateFormat.parse(this.sunset);
        localObject = localDate2;
        label242: int j = localCMAWeatherResourceUtil.getWeatherBackgroundDrawable();
        if (j != 99)
        {
          if ((localDate1.getTime() < System.currentTimeMillis()) && (localObject.getTime() > System.currentTimeMillis()))
            this.weatherBackgroundImage.setBackgroundResource(j);
        }
        else
        {
          label288: if (this.currentTemp == null)
            break label340;
          this.mCurrentTemp.setText(this.currentTemp);
          this.mUnit_meter_F.setVisibility(8);
        }
        while (true)
        {
          return;
          this.dividerImageView.setVisibility(0);
          break;
          this.weatherBackgroundImage.setBackgroundResource(2130838227);
          break label288;
          label340: this.mCurrentTemp.setVisibility(4);
          this.mUnit_meter_C.setVisibility(8);
          this.mUnit_meter_F.setVisibility(8);
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
    case 2131558967:
    }
    while (true)
    {
      return;
      ChineseWeatherWeb();
    }
  }

  public void onFinishInflate()
  {
    try
    {
      super.onFinishInflate();
      this.citi_name = ((TextView)findViewById(2131558412));
      this.citi_name2 = ((TextView)findViewById(2131558413));
      this.weatherImage1 = ((ImageView)findViewById(2131558403));
      this.date_value = ((TextView)findViewById(2131558414));
      this.climate_name = ((TextView)findViewById(2131558411));
      this.minTemperature1 = ((TextView)findViewById(2131558410));
      this.maxTemperature1 = ((TextView)findViewById(2131558408));
      this.mCurrentTemp = ((TextView)findViewById(2131558405));
      this.mUnit_meter_F = ((TextView)findViewById(2131558406));
      this.mUnit_meter_C = ((TextView)findViewById(2131558407));
      this.dividerImageView = ((TextView)findViewById(2131558409));
      this.weatherBackgroundImage = ((FrameLayout)findViewById(2131558402));
      this.lastupdate = ((TextView)findViewById(2131558419));
      label186: return;
    }
    catch (Exception localException)
    {
      break label186;
    }
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  private static class CMAWeatherOneDayWidgetHandler extends Handler
  {
    private final WeakReference<CMAWeatherOneDayWidget> outer;

    CMAWeatherOneDayWidgetHandler(CMAWeatherOneDayWidget paramCMAWeatherOneDayWidget)
    {
      this.outer = new WeakReference(paramCMAWeatherOneDayWidget);
    }

    public void handleMessage(Message paramMessage)
    {
      CMAWeatherOneDayWidget localCMAWeatherOneDayWidget = (CMAWeatherOneDayWidget)this.outer.get();
      if (localCMAWeatherOneDayWidget != null)
        localCMAWeatherOneDayWidget.setValuesForcastWeather();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.CMAWeatherOneDayWidget
 * JD-Core Version:    0.6.0
 */