package com.vlingo.core.internal.dialogmanager.actions;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.weather.WeatherElement;

public class WeatherLookupAction extends DMAction
{
  private WeatherElement weatherResp;

  protected void execute()
  {
    Intent localIntent;
    if (this.weatherResp != null)
      localIntent = new Intent("android.intent.action.VIEW");
    try
    {
      getContext().startActivity(localIntent);
      label25: getListener().actionSuccess();
      while (true)
      {
        return;
        getListener().actionFail("Empty Response");
      }
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      break label25;
    }
  }

  public WeatherLookupAction weather(WeatherElement paramWeatherElement)
  {
    this.weatherResp = paramWeatherElement;
    return this;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.WeatherLookupAction
 * JD-Core Version:    0.6.0
 */