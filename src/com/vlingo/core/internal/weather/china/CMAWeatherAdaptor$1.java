package com.vlingo.core.internal.weather.china;

import com.vlingo.core.internal.localsearch.LocalSearchRequestListener;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import org.json.JSONException;

class CMAWeatherAdaptor$1
  implements LocalSearchRequestListener
{
  public void onRequestComplete(boolean paramBoolean, Object paramObject)
  {
    CMAWeatherAdaptor.access$002(this.this$0, true);
    if (paramBoolean);
    try
    {
      CMALocationElement localCMALocationElement = new CMAWeatherParser(new CMALocationElement()).parseLocation((String)paramObject);
      CMAWeatherAdaptor.access$100(this.this$0, localCMALocationElement);
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        if (CMAWeatherAdaptor.access$200(this.this$0) == null)
          continue;
        CMAWeatherAdaptor.access$200(this.this$0).onRequestFailed();
      }
    }
  }

  public void onRequestFailed(String paramString)
  {
    CMAWeatherAdaptor.access$002(this.this$0, true);
    CMAWeatherAdaptor.access$302(this.this$0, true);
    if (CMAWeatherAdaptor.access$200(this.this$0) != null)
      CMAWeatherAdaptor.access$200(this.this$0).onRequestFailed();
  }

  public void onRequestScheduled()
  {
    CMAWeatherAdaptor.access$002(this.this$0, false);
    CMAWeatherAdaptor.access$302(this.this$0, false);
    if (CMAWeatherAdaptor.access$200(this.this$0) != null)
      CMAWeatherAdaptor.access$200(this.this$0).onRequestScheduled();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.china.CMAWeatherAdaptor.1
 * JD-Core Version:    0.6.0
 */