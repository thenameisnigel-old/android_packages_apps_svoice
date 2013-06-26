package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import android.content.res.Resources;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.WeatherLookupHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.WeatherLookupHandler.DateType;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.weather.WeatherInfoUtil;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.gui.ConversationActivity.DrivingMode;
import com.vlingo.midas.util.WeatherResourceUtil;
import com.vlingo.midas.util.WeatherResourceUtil.WeatherType;
import java.util.HashMap;
import java.util.Map;

public class JPWeatherLookupHandler extends WeatherLookupHandler
{
  private static final HashMap<WeatherResourceUtil.WeatherType, Integer> weatherCodeToString;
  private static final HashMap<WeatherResourceUtil.WeatherType, Integer> weatherCodeToStringArray = new HashMap();
  private static final HashMap<WeatherResourceUtil.WeatherType, Integer> weatherCodeTodayToStringArray;
  private int index = -1;

  static
  {
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Sunny, Integer.valueOf(2131165231));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.PartlySunny, Integer.valueOf(2131165233));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.MostlyCloudy, Integer.valueOf(2131165235));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Fog, Integer.valueOf(2131165237));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Showers, Integer.valueOf(2131165239));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.PartlySunnyWithShowers, Integer.valueOf(2131165241));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Thunderstorms, Integer.valueOf(2131165243));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.MostlyCloudyWithThunderShowers, Integer.valueOf(2131165245));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Rain, Integer.valueOf(2131165247));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Flurries, Integer.valueOf(2131165249));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.MostlyCloudyWithFlurries, Integer.valueOf(2131165251));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Snow, Integer.valueOf(2131165253));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Ice, Integer.valueOf(2131165255));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.RainAndSnowMixed, Integer.valueOf(2131165257));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Hot, Integer.valueOf(2131165259));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Cold, Integer.valueOf(2131165261));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Windy, Integer.valueOf(2131165263));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.Clear, Integer.valueOf(2131165265));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.MosltyClear, Integer.valueOf(2131165267));
    weatherCodeToStringArray.put(WeatherResourceUtil.WeatherType.MostlyCloudyNight, Integer.valueOf(2131165269));
    weatherCodeTodayToStringArray = new HashMap();
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Sunny, Integer.valueOf(2131165232));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.PartlySunny, Integer.valueOf(2131165234));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.MostlyCloudy, Integer.valueOf(2131165236));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Fog, Integer.valueOf(2131165238));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Showers, Integer.valueOf(2131165240));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.PartlySunnyWithShowers, Integer.valueOf(2131165242));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Thunderstorms, Integer.valueOf(2131165244));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.MostlyCloudyWithThunderShowers, Integer.valueOf(2131165246));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Rain, Integer.valueOf(2131165248));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Flurries, Integer.valueOf(2131165250));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.MostlyCloudyWithFlurries, Integer.valueOf(2131165252));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Snow, Integer.valueOf(2131165254));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Ice, Integer.valueOf(2131165256));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.RainAndSnowMixed, Integer.valueOf(2131165258));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Hot, Integer.valueOf(2131165260));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Cold, Integer.valueOf(2131165262));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Windy, Integer.valueOf(2131165264));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.Clear, Integer.valueOf(2131165266));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.MosltyClear, Integer.valueOf(2131165268));
    weatherCodeTodayToStringArray.put(WeatherResourceUtil.WeatherType.MostlyCloudyNight, Integer.valueOf(2131165269));
    weatherCodeToString = new HashMap();
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Sunny, Integer.valueOf(2131362680));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.PartlySunny, Integer.valueOf(2131362681));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.MostlyCloudy, Integer.valueOf(2131362682));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Fog, Integer.valueOf(2131362683));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Showers, Integer.valueOf(2131362684));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.PartlySunnyWithShowers, Integer.valueOf(2131362685));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Thunderstorms, Integer.valueOf(2131362686));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.MostlyCloudyWithThunderShowers, Integer.valueOf(2131362687));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Rain, Integer.valueOf(2131362688));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Flurries, Integer.valueOf(2131362689));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.MostlyCloudyWithFlurries, Integer.valueOf(2131362690));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Snow, Integer.valueOf(2131362691));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Ice, Integer.valueOf(2131362692));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.RainAndSnowMixed, Integer.valueOf(2131362693));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Hot, Integer.valueOf(2131362694));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Cold, Integer.valueOf(2131362695));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Windy, Integer.valueOf(2131362696));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.Clear, Integer.valueOf(2131362697));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.MosltyClear, Integer.valueOf(2131362698));
    weatherCodeToString.put(WeatherResourceUtil.WeatherType.MostlyCloudyNight, Integer.valueOf(2131362699));
  }

  private String getFormattedForecast(Integer paramInteger, int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
  {
    String str1 = tempToIntValue(paramString2);
    String str2 = tempToIntValue(paramString3);
    HashMap localHashMap = new HashMap();
    localHashMap.put("day", paramString1);
    localHashMap.put("temp", str1);
    localHashMap.put("cur_temp", str2);
    localHashMap.put("condition", getWeatherConditionByCode(paramInt2));
    return StringUtils.replaceTokens(getWeatherStringFromArrayRes(paramInteger, paramInt1), localHashMap);
  }

  private String tempToIntValue(String paramString)
  {
    Double localDouble = Double.valueOf(Double.parseDouble(paramString));
    if (localDouble != null)
      paramString = Integer.valueOf((int)localDouble.longValue()).toString();
    return paramString;
  }

  protected String getMsg(Integer paramInteger, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt, WeatherLookupHandler.DateType paramDateType)
  {
    HashMap localHashMap;
    Integer localInteger;
    if (paramDateType == WeatherLookupHandler.DateType.TODAY)
    {
      localHashMap = weatherCodeTodayToStringArray;
      localInteger = (Integer)localHashMap.get(WeatherResourceUtil.WeatherCodeToWeatherType(paramInt));
      if (localInteger != null)
        break label66;
    }
    label66: for (String str = super.getMsg(paramInteger, paramString1, paramString2, paramString3, paramString4, paramString5, paramInt, paramDateType); ; str = getFormattedForecast(paramInteger, localInteger.intValue(), paramInt, paramString1, paramString4, paramString5))
    {
      return StringUtils.capitalize(str);
      localHashMap = weatherCodeToStringArray;
      break;
    }
  }

  protected String getOneDaySpokenMsg(Integer paramInteger, WeatherInfoUtil paramWeatherInfoUtil, String paramString1, String paramString2, WeatherLookupHandler.DateType paramDateType, String paramString3)
  {
    Context localContext = getListener().getActivityContext();
    int i;
    StringBuilder localStringBuilder;
    if (((localContext instanceof ConversationActivity)) && (((ConversationActivity)localContext).isDrivingMode() == ConversationActivity.DrivingMode.Driving))
    {
      i = 1;
      localStringBuilder = new StringBuilder();
      if (i == 0)
        break label121;
    }
    label121: for (String str = paramString3 + getListener().getActivityContext().getResources().getString(2131362061); ; str = "")
    {
      return str + super.getOneDaySpokenMsg(paramInteger, paramWeatherInfoUtil, paramString1, paramString2, paramDateType, paramString3);
      i = 0;
      break;
    }
  }

  protected String getSpokenMsg(Integer paramInteger, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt, WeatherLookupHandler.DateType paramDateType)
  {
    HashMap localHashMap;
    Integer localInteger;
    if (paramDateType == WeatherLookupHandler.DateType.TODAY)
    {
      localHashMap = weatherCodeTodayToStringArray;
      localInteger = (Integer)localHashMap.get(WeatherResourceUtil.WeatherCodeToWeatherType(paramInt));
      if (localInteger != null)
        break label68;
    }
    label68: for (String str = super.getSpokenMsg(paramInteger, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramInt, paramDateType); ; str = getFormattedForecast(paramInteger, localInteger.intValue(), paramInt, paramString2, paramString5, paramString6))
    {
      return StringUtils.capitalize(str);
      localHashMap = weatherCodeToStringArray;
      break;
    }
  }

  String getWeatherConditionByCode(int paramInt)
  {
    return getListener().getActivityContext().getResources().getString(((Integer)weatherCodeToString.get(WeatherResourceUtil.WeatherCodeToWeatherType(paramInt))).intValue());
  }

  String getWeatherStringFromArrayRes(Integer paramInteger, int paramInt)
  {
    String[] arrayOfString = getListener().getActivityContext().getResources().getStringArray(paramInt);
    if (paramInteger == null)
    {
      if (this.index == -1)
        this.index = (int)(Math.random() * arrayOfString.length);
      paramInteger = Integer.valueOf(this.index);
    }
    while (true)
    {
      return arrayOfString[paramInteger.intValue()];
      this.index = paramInteger.intValue();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.JPWeatherLookupHandler
 * JD-Core Version:    0.6.0
 */