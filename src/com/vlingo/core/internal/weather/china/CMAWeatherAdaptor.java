package com.vlingo.core.internal.weather.china;

import com.vlingo.core.internal.localsearch.LocalSearchRequestListener;
import com.vlingo.core.internal.localsearch.LocalSearchServiceManager;
import com.vlingo.core.internal.location.LocationUtils;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import java.net.URLEncoder;
import org.json.JSONException;

public class CMAWeatherAdaptor
  implements LocalSearchRequestListener
{
  private String date;
  private boolean isDatePlusSeven;
  private boolean isRequestComplete = false;
  private boolean isRequestFailed = false;
  private boolean isToday;
  private LocalSearchServiceManager lsManager = new LocalSearchServiceManager();
  private CMAWeatherElement weatherElement = null;
  private WidgetResponseReceivedListener widgetListener = null;

  private void sendChineseWeatherRequest(CMALocationElement paramCMALocationElement)
  {
    String str2;
    String str1;
    if (paramCMALocationElement != null)
    {
      str2 = paramCMALocationElement.longitude;
      str1 = paramCMALocationElement.latitude;
    }
    while (true)
    {
      String str3 = "http://geoip.weather.com.cn/webapi/locate/?lon=" + str2 + "&lat=" + str1;
      this.lsManager.sendRequest("ChineseWeatherRequest-Request", str3, this);
      return;
      if (Settings.getBoolean("FAKE_LAT_LONG", false))
      {
        str1 = Settings.getString("FAKE_LAT", null);
        str2 = Settings.getString("FAKE_LONG", null);
        continue;
      }
      str1 = String.valueOf(LocationUtils.getLastLat());
      str2 = String.valueOf(LocationUtils.getLastLong());
    }
  }

  private void setWeatherElement(CMAWeatherElement paramCMAWeatherElement)
  {
    monitorenter;
    try
    {
      this.weatherElement = paramCMAWeatherElement;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String getDate()
  {
    return this.date;
  }

  public String getLocationCity()
  {
    return this.weatherElement.nameCity;
  }

  public String getLocationState()
  {
    if (StringUtils.isNullOrWhiteSpace(this.weatherElement.nameProvince));
    for (String str = this.weatherElement.nameCountry; ; str = this.weatherElement.nameProvince)
      return str;
  }

  public CMAWeatherElement getWeatherElement()
  {
    return this.weatherElement;
  }

  public WidgetResponseReceivedListener getWidgetListener()
  {
    return this.widgetListener;
  }

  public boolean isDatePlusSeven()
  {
    return this.isDatePlusSeven;
  }

  public boolean isRequestComplete()
  {
    return this.isRequestComplete;
  }

  public boolean isRequestFailed()
  {
    return this.isRequestFailed;
  }

  public boolean isToday()
  {
    return this.isToday;
  }

  public void onRequestComplete(boolean paramBoolean, Object paramObject)
  {
    this.isRequestComplete = true;
    if (paramBoolean);
    try
    {
      setWeatherElement(new CMAWeatherParser(new CMAWeatherElement()).parse((String)paramObject));
      if ((this.weatherElement == null) || (this.isRequestFailed))
      {
        this.isRequestFailed = true;
        this.widgetListener.onRequestFailed();
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        this.isRequestFailed = true;
        continue;
        if (this.widgetListener == null)
          continue;
        this.widgetListener.onResponseReceived();
      }
    }
  }

  public void onRequestFailed(String paramString)
  {
    this.isRequestComplete = true;
    this.isRequestFailed = true;
    if (this.widgetListener != null)
      this.widgetListener.onRequestFailed();
  }

  public void onRequestScheduled()
  {
    this.isRequestComplete = false;
    this.isRequestFailed = false;
    if (this.widgetListener != null)
      this.widgetListener.onRequestScheduled();
  }

  public void sendWeatherRequest(String paramString1, String paramString2, String paramString3)
  {
    this.isRequestComplete = false;
    this.isRequestFailed = false;
    this.date = paramString1;
    if (!StringUtils.isNullOrWhiteSpace(paramString3))
    {
      String str = "http://webapi.weather.com.cn/data/search/?city=" + URLEncoder.encode(paramString3);
      this.lsManager.sendRequest("SearchCityChineseWeatherRequest-Request", str, new CMAWeatherAdaptor.1(this));
    }
    while (true)
    {
      return;
      sendChineseWeatherRequest(null);
    }
  }

  public void setDatePlusSeven(boolean paramBoolean)
  {
    this.isDatePlusSeven = paramBoolean;
  }

  public void setToday(boolean paramBoolean)
  {
    this.isToday = paramBoolean;
  }

  public void setWidgetListener(WidgetResponseReceivedListener paramWidgetResponseReceivedListener)
  {
    this.widgetListener = paramWidgetResponseReceivedListener;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.china.CMAWeatherAdaptor
 * JD-Core Version:    0.6.0
 */