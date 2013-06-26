package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;

class CMAWeatherLookupHandler$2
  implements Runnable
{
  public void run()
  {
    String str = CMAWeatherLookupHandler.access$700(ResourceIdProvider.string.core_weather_no_results, new Object[0]);
    CMAWeatherLookupHandler.access$800(this.this$0).showSystemTurn(str);
    this.this$0.actionFail("Weather lookup failed");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.CMAWeatherLookupHandler.2
 * JD-Core Version:    0.6.0
 */