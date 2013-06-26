package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import com.vlingo.core.internal.weather.china.CMAWeatherAdaptor;
import com.vlingo.sdk.recognition.VLAction;

public class CMAWeatherLookupHandler extends VVSActionHandler
  implements WidgetActionListener, WidgetResponseReceivedListener
{
  private CMAWeatherAdaptor adaptor = null;
  Handler handler = null;

  public CMAWeatherLookupHandler()
  {
    this.adaptor.setWidgetListener(this);
    this.handler = new Handler(Looper.getMainLooper());
  }

  private void showFailure()
  {
    this.handler.post(new CMAWeatherLookupHandler.2(this));
  }

  private void showSuccess()
  {
    this.handler.post(new CMAWeatherLookupHandler.1(this));
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("weather");
    String str1 = VLActionUtil.getParamString(paramVLAction, "Location", false);
    String str2 = VLActionUtil.getParamString(paramVLAction, "Date", false);
    String str3 = VLActionUtil.getParamString(paramVLAction, "SpokenDate", false);
    this.adaptor.sendWeatherRequest(str2, str3, str1);
    return true;
  }

  protected String getMsg(String paramString1, String paramString2, String paramString3)
  {
    ResourceIdProvider.string localstring = ResourceIdProvider.string.core_weather_date_display;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = paramString3;
    return getString(localstring, arrayOfObject);
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.NoData"))
    {
      String str = getString(ResourceIdProvider.string.core_qa_tts_NO_ANS_WEB_SEARCH, new Object[0]);
      unified().showSystemTurn(str);
    }
    while (true)
    {
      return;
      throwUnknownActionException(paramIntent.getAction());
    }
  }

  public void onRequestFailed()
  {
    try
    {
      showFailure();
      return;
    }
    finally
    {
      getListener().asyncHandlerDone();
    }
    throw localObject;
  }

  public void onRequestScheduled()
  {
  }

  public void onResponseReceived()
  {
    try
    {
      showSuccess();
      return;
    }
    finally
    {
      getListener().asyncHandlerDone();
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.CMAWeatherLookupHandler
 * JD-Core Version:    0.6.0
 */