package com.vlingo.core.internal.weather;

import com.vlingo.core.internal.localsearch.LocalSearchRequestListener;
import com.vlingo.core.internal.localsearch.LocalSearchServiceManager;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import java.util.Map;

public class WeatherAdaptor
  implements LocalSearchRequestListener
{
  private String currentRequest = null;
  private String date;
  private String failReason = null;
  private boolean isRequestComplete = false;
  private boolean isRequestFailed = false;
  private boolean isToday = false;
  private String location;
  private LocalSearchServiceManager lsManager = new LocalSearchServiceManager();
  private String spokenDate;
  private WeatherElement weatherElement = null;
  private WidgetResponseReceivedListener widgetListener = null;

  private void sendWeatherRequest(String paramString1, String paramString2, boolean paramBoolean)
  {
    sendWeatherRequest(paramString1, paramString2, paramBoolean, 1);
  }

  private void sendWeatherRequest(String paramString1, String paramString2, boolean paramBoolean, int paramInt)
  {
    this.isRequestComplete = false;
    if (paramString1 == null)
      paramString1 = "";
    if ((!paramString1.equals(this.currentRequest)) || (paramBoolean))
    {
      this.currentRequest = paramString1;
      this.lsManager.sendWeatherRequest(paramString1, paramString2, this, paramInt);
    }
  }

  private void setWeatherElement(WeatherElement paramWeatherElement)
  {
    monitorenter;
    try
    {
      this.weatherElement = paramWeatherElement;
      Map localMap = paramWeatherElement.getChild(0).getChild(0).getAttributes();
      ((String)localMap.get("WeatherText"));
      ((String)localMap.get("Temperature"));
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

  public String getCurrentRequest()
  {
    return this.currentRequest;
  }

  public String getDate()
  {
    return this.date;
  }

  public String getFailReason()
  {
    return this.failReason;
  }

  public String getFullLocation()
  {
    return (String)this.weatherElement.getAttributes().get("Location");
  }

  public String getLocation()
  {
    return this.location;
  }

  public String getLocationCity()
    throws WeatherElement.WeatherNotFound
  {
    return (String)this.weatherElement.findNamedChild("WeatherLocation").findNamedChild("Location").getAttributes().get("City");
  }

  public String getLocationState()
    throws WeatherElement.WeatherNotFound
  {
    return (String)this.weatherElement.findNamedChild("WeatherLocation").findNamedChild("Location").getAttributes().get("State");
  }

  public String getSpokenDate()
  {
    return this.spokenDate;
  }

  public WeatherElement getWeatherElement()
  {
    return this.weatherElement;
  }

  public WidgetResponseReceivedListener getWidgetListener()
  {
    return this.widgetListener;
  }

  public boolean isDefaultLocation()
  {
    try
    {
      boolean bool2 = "DEFAULT".equals((String)this.weatherElement.findNamedChild("WeatherLocation").findNamedChild("Location").getAttributes().get("SelectedLocationType"));
      bool1 = bool2;
      return bool1;
    }
    catch (WeatherElement.WeatherNotFound localWeatherNotFound)
    {
      while (true)
        boolean bool1 = false;
    }
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
    if ((paramBoolean) && ((paramObject instanceof WeatherElement)))
      setWeatherElement((WeatherElement)paramObject);
    if (this.widgetListener != null)
      this.widgetListener.onResponseReceived();
  }

  public void onRequestFailed(String paramString)
  {
    this.isRequestComplete = true;
    this.isRequestFailed = true;
    this.failReason = paramString;
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

  public void resetWeatherElement()
  {
    this.weatherElement = null;
  }

  public void sendWeatherRequest(String paramString1, String paramString2, String paramString3)
  {
    sendWeatherRequest(paramString1, paramString2, paramString3, "1");
  }

  public void sendWeatherRequest(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.date = paramString1;
    this.spokenDate = paramString2;
    this.location = paramString3;
    if (Settings.useWeatherVpLocation())
    {
      sendWeatherRequest(paramString3, paramString4, false, 2);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramString1 != null)
      localStringBuilder.append("what is the weather ").append(paramString1);
    while (true)
    {
      if (paramString3 != null)
        localStringBuilder.append(" in ").append(paramString3);
      sendWeatherRequest(localStringBuilder.toString(), paramString4, false);
      break;
      localStringBuilder.append("what is the weather today");
    }
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
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherAdaptor
 * JD-Core Version:    0.6.0
 */