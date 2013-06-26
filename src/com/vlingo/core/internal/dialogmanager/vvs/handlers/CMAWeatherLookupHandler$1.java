package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.weather.china.CMAWeatherAdaptor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

class CMAWeatherLookupHandler$1
  implements Runnable
{
  public void run()
  {
    Object localObject = null;
    String str1 = CMAWeatherLookupHandler.access$000(this.this$0).getDate();
    if (!StringUtils.isNullOrWhiteSpace(str1));
    try
    {
      Date localDate1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(str1);
      boolean bool1;
      if (localDate1.getDate() == new Date().getDate())
      {
        bool1 = true;
        CMAWeatherLookupHandler.access$000(this.this$0).setToday(bool1);
        if (!bool1)
          break label187;
        String str4 = this.this$0.getMsg(CMAWeatherLookupHandler.access$100(ResourceIdProvider.string.core_today, new Object[0]), CMAWeatherLookupHandler.access$000(this.this$0).getLocationCity(), CMAWeatherLookupHandler.access$000(this.this$0).getLocationState());
        localObject = str4;
        label118: if (!StringUtils.isNullOrWhiteSpace((String)localObject))
          CMAWeatherLookupHandler.access$400(this.this$0).showVlingoTextAndTTS((String)localObject, (String)localObject);
        if (StringUtils.isNullOrWhiteSpace(str1))
          break label396;
        CMAWeatherLookupHandler.access$500(this.this$0).showWidget(WidgetUtil.WidgetKey.ShowCMAWeatherDailyWidget, null, CMAWeatherLookupHandler.access$000(this.this$0), this.this$0);
      }
      while (true)
      {
        this.this$0.actionSuccess();
        return;
        bool1 = false;
        break;
        label187: Date localDate2 = new Date();
        GregorianCalendar localGregorianCalendar = new GregorianCalendar();
        localGregorianCalendar.setTime(localDate2);
        localGregorianCalendar.add(5, 6);
        localDate2.setTime(localGregorianCalendar.getTime().getTime());
        if (localDate1.after(localDate2))
          break label431;
        if (!localDate1.before(new Date()))
          break label437;
        break label431;
        CMAWeatherLookupHandler.access$000(this.this$0).setDatePlusSeven(bool2);
        if (bool2)
        {
          localObject = CMAWeatherLookupHandler.access$200(ResourceIdProvider.string.core_weather_plus_seven, new Object[0]);
          break label118;
        }
        String str2 = new SimpleDateFormat("EEEE", Settings.getCurrentLocale()).format(localDate1);
        String str3 = this.this$0.getMsg(str2, CMAWeatherLookupHandler.access$000(this.this$0).getLocationCity(), CMAWeatherLookupHandler.access$000(this.this$0).getLocationState());
        localObject = str3;
        break label118;
        ResourceIdProvider.string localstring = ResourceIdProvider.string.core_weather_with_locatin;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = CMAWeatherLookupHandler.access$000(this.this$0).getLocationCity();
        arrayOfObject[1] = CMAWeatherLookupHandler.access$000(this.this$0).getLocationState();
        localObject = CMAWeatherLookupHandler.access$300(localstring, arrayOfObject);
        break label118;
        label396: CMAWeatherLookupHandler.access$600(this.this$0).showWidget(WidgetUtil.WidgetKey.ShowCMAWeatherWidget, null, CMAWeatherLookupHandler.access$000(this.this$0), this.this$0);
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        continue;
        label431: boolean bool2 = true;
        continue;
        label437: bool2 = false;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.CMAWeatherLookupHandler.1
 * JD-Core Version:    0.6.0
 */