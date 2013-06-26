package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.schedule.DateUtil;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Date;

public class DateLookupHandler extends VVSActionHandler
{
  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("date");
    String str1 = VLActionUtil.getParamString(paramVLAction, "Date", false);
    if (!StringUtils.isNullOrWhiteSpace(str1))
    {
      Date localDate1 = new Date(DateUtil.getMillisFromString(str1, true));
      String str2 = java.text.DateFormat.getDateInstance(1, Settings.getCurrentLocale()).format(localDate1);
      paramVVSActionHandlerListener.showVlingoTextAndTTS(str2, str2);
    }
    String str3 = VLActionUtil.getParamString(paramVLAction, "Time", false);
    Date localDate2;
    if ((!StringUtils.isNullOrWhiteSpace(str3)) && (str3.equalsIgnoreCase("current")))
    {
      localDate2 = new Date();
      if (!android.text.format.DateFormat.is24HourFormat(ApplicationAdapter.getInstance().getApplicationContext()))
        break label153;
    }
    label153: for (String str4 = android.text.format.DateFormat.format("k:mm", localDate2).toString(); ; str4 = java.text.DateFormat.getTimeInstance(3, Settings.getCurrentLocale()).format(localDate2))
    {
      paramVVSActionHandlerListener.tts(str4);
      paramVVSActionHandlerListener.showWidget(WidgetUtil.WidgetKey.ShowClock, null, null, null);
      return false;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.DateLookupHandler
 * JD-Core Version:    0.6.0
 */