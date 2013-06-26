package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.logging.Logger;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.weather.WeatherAdaptor;
import com.vlingo.core.internal.weather.WeatherElement;
import com.vlingo.core.internal.weather.WeatherElement.WeatherNotFound;
import com.vlingo.core.internal.weather.WeatherInfoUtil;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.WeatherResourceUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

public class WeatherOneDayWidget extends BargeInWidget<WeatherAdaptor>
  implements View.OnClickListener
{
  private static final SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM", Locale.US);
  private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
  private ImageView accuWeather_Web;
  private TextView citi_name;
  private TextView climate_name;
  private WeatherElement currentCondition;
  private String date_val;
  private TextView date_value;
  private TextView deviderImageView;
  private WeatherElement forecasts;
  private WidgetActionListener listener;
  private String location;
  private Logger log = Logger.getLogger(WeatherOneDayWidget.class);
  private LinearLayout mCityDetail;
  private Context mContext;
  private TextView mCurrentTemp;
  private WeatherOneDayWidgetHandler mHandler = new WeatherOneDayWidgetHandler(this);
  private LinearLayout mTemparature;
  private TextView mUnit_meter_C;
  private TextView mUnit_meter_F;
  private WeatherInfoUtil mWeatherInfoUtil;
  private String max;
  private TextView maxTemperature1;
  private String min;
  private TextView minTemperature1;
  private String sunRise;
  private String sunSet;
  private String temp_unit;
  private WeatherAdaptor wAdaptor;
  private FrameLayout weatherBackgroundImage;
  private ImageView weatherBox;
  private WeatherElement weatherDetails;
  private ImageView weatherImage1;

  public WeatherOneDayWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
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
        String str3 = localSimpleDateFormat1.format(localSimpleDateFormat2.parse(this.mWeatherInfoUtil.getWeatherDate()));
        str2 = str3;
        return str2;
      }
      catch (Exception localException)
      {
      }
      String str2 = this.mWeatherInfoUtil.getDateForOneDayWidget();
    }
  }

  private void onResponseReceived()
  {
    this.weatherDetails = this.wAdaptor.getWeatherElement();
    if ((this.weatherDetails == null) || (this.weatherDetails.getChildCount() == 0))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.vlingo.core.internal.dialogmanager.NoData");
      this.listener.handleIntent(localIntent, null);
    }
    while (true)
    {
      return;
      this.location = ((String)this.weatherDetails.getAttributes().get("Location"));
      try
      {
        this.temp_unit = ((String)this.weatherDetails.findNamedChild("Units").findNamedChild("Units").getAttributes().get("Temperature"));
        this.mWeatherInfoUtil = WeatherInfoUtil.getWeatherInfoUtil(this.mContext, this.weatherDetails, this.wAdaptor.getDate());
        this.date_val = getWeatherDate();
        this.max = this.mWeatherInfoUtil.getMaximumTempOneDayWeather();
        this.min = this.mWeatherInfoUtil.getMinimumTempOneDayWeather();
        this.sunRise = this.mWeatherInfoUtil.getSunRise();
        this.sunSet = this.mWeatherInfoUtil.getSunSet();
        this.mHandler.sendEmptyMessage(0);
      }
      catch (WeatherElement.WeatherNotFound localWeatherNotFound)
      {
        while (true)
          localWeatherNotFound.printStackTrace();
      }
    }
  }

  private void openAccuWeatherWeb()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("http://www.accuweather.com/m"));
    getContext().startActivity(localIntent);
  }

  private void setValuesForcastWeather()
  {
    if (this.min != null)
    {
      int m = (int)Double.parseDouble(this.min);
      this.minTemperature1.setText(m + "" + '°');
    }
    if (this.max != null)
    {
      int k = (int)Double.parseDouble(this.max);
      this.maxTemperature1.setText(k + "" + '°');
    }
    if ((this.min == null) || (this.max == null))
      this.deviderImageView.setVisibility(8);
    while (true)
    {
      this.citi_name.setText(this.location);
      WeatherResourceUtil localWeatherResourceUtil = new WeatherResourceUtil(this.mWeatherInfoUtil.getCurrentWeatherCode());
      int i = localWeatherResourceUtil.getWeatherDrawable();
      if (i != 101)
        this.weatherImage1.setImageResource(i);
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd h:mm a", Locale.US);
      try
      {
        localSimpleDateFormat.parse(this.sunRise);
        localSimpleDateFormat.parse(this.sunSet);
        label202: int j = localWeatherResourceUtil.getWeatherBackgroundDrawable();
        if (j != 101)
        {
          if (MidasSettings.isNightMode())
            this.weatherBackgroundImage.setBackgroundResource(localWeatherResourceUtil.changeToNightBackground(j));
        }
        else
        {
          label234: this.climate_name.setText(this.mWeatherInfoUtil.getWeatherString());
          if (this.date_value != null)
            this.date_value.setText(this.date_val);
          if (this.mCurrentTemp != null)
          {
            if (!this.wAdaptor.isToday())
              break label388;
            this.mCurrentTemp.setText(this.mWeatherInfoUtil.getCurrentTemp() + "");
            if ((this.temp_unit == null) || (!this.temp_unit.equals(getResources().getString(2131362583))))
              break label376;
            this.mUnit_meter_C.setVisibility(8);
          }
        }
        while (true)
        {
          return;
          this.deviderImageView.setVisibility(0);
          break;
          this.weatherBackgroundImage.setBackgroundResource(j);
          break label234;
          label376: this.mUnit_meter_F.setVisibility(8);
          continue;
          label388: this.mCurrentTemp.setVisibility(8);
          this.mUnit_meter_C.setVisibility(8);
          this.mUnit_meter_F.setVisibility(8);
        }
      }
      catch (ParseException localParseException)
      {
        break label202;
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
      openAccuWeatherWeb();
    }
  }

  public void onFinishInflate()
  {
    try
    {
      super.onFinishInflate();
      this.citi_name = ((TextView)findViewById(2131558963));
      this.weatherImage1 = ((ImageView)findViewById(2131558966));
      this.date_value = ((TextView)findViewById(2131558964));
      this.climate_name = ((TextView)findViewById(2131558976));
      this.minTemperature1 = ((TextView)findViewById(2131558975));
      this.maxTemperature1 = ((TextView)findViewById(2131558973));
      this.mCurrentTemp = ((TextView)findViewById(2131558970));
      this.mUnit_meter_F = ((TextView)findViewById(2131558971));
      this.mUnit_meter_C = ((TextView)findViewById(2131558972));
      this.deviderImageView = ((TextView)findViewById(2131558974));
      this.weatherBackgroundImage = ((FrameLayout)findViewById(2131558421));
      this.mCityDetail = ((LinearLayout)findViewById(2131558401));
      this.mTemparature = ((LinearLayout)findViewById(2131558404));
      this.accuWeather_Web = ((ImageView)findViewById(2131558967));
      this.accuWeather_Web.setOnClickListener(this);
      label208: return;
    }
    catch (Exception localException)
    {
      break label208;
    }
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void setMinimizeWindow()
  {
    this.weatherBackgroundImage.setLayoutParams(new LinearLayout.LayoutParams(-1, 780));
    this.weatherImage1.setLayoutParams(new FrameLayout.LayoutParams(687, 645, 5));
    this.date_value.setTextSize(24.0F);
    this.citi_name.setTextSize(35.0F);
    this.climate_name.setTextSize(15.0F);
    FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-2, -2, 80);
    localLayoutParams1.setMargins(51, 0, 0, 48);
    this.climate_name.setLayoutParams(localLayoutParams1);
    this.mCurrentTemp.setTextSize(40.0F);
    this.mUnit_meter_F.setTextSize(28.0F);
    this.mUnit_meter_C.setTextSize(28.0F);
    this.minTemperature1.setTextSize(25.0F);
    this.maxTemperature1.setTextSize(25.0F);
    this.deviderImageView.setTextSize(24.0F);
    FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-2, -2, 80);
    localLayoutParams2.setMargins(51, 0, 0, 99);
    this.mTemparature.setLayoutParams(localLayoutParams2);
  }

  private static class WeatherOneDayWidgetHandler extends Handler
  {
    private final WeakReference<WeatherOneDayWidget> outer;

    WeatherOneDayWidgetHandler(WeatherOneDayWidget paramWeatherOneDayWidget)
    {
      this.outer = new WeakReference(paramWeatherOneDayWidget);
    }

    public void handleMessage(Message paramMessage)
    {
      WeatherOneDayWidget localWeatherOneDayWidget = (WeatherOneDayWidget)this.outer.get();
      if (localWeatherOneDayWidget != null)
        localWeatherOneDayWidget.setValuesForcastWeather();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.WeatherOneDayWidget
 * JD-Core Version:    0.6.0
 */