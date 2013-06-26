package com.vlingo.midas.util;

import android.util.SparseArray;
import java.util.HashMap;

public class CMAWeatherResourceUtil
{
  private static final int NO_DRAWABLE = 99;
  private static final SparseArray<WeatherType> weatherCodeMapping = new SparseArray(32);
  private static final HashMap<WeatherType, Integer> weatherCodeToImage;
  private static final HashMap<WeatherType, Integer> weatherCodeToMediumImage;
  private static final HashMap<WeatherType, Integer> weatherCodeToSmallImage;
  private int mWeatherCode;

  static
  {
    weatherCodeMapping.put(0, WeatherType.Sunny);
    weatherCodeMapping.put(1, WeatherType.Cloudy);
    weatherCodeMapping.put(2, WeatherType.Overcast);
    weatherCodeMapping.put(3, WeatherType.Shower);
    weatherCodeMapping.put(4, WeatherType.ThunderShower);
    weatherCodeMapping.put(5, WeatherType.ThunderShowerwithHail);
    weatherCodeMapping.put(6, WeatherType.Sleet);
    weatherCodeMapping.put(7, WeatherType.LightRain);
    weatherCodeMapping.put(8, WeatherType.LightRain);
    weatherCodeMapping.put(9, WeatherType.HeavyRain);
    weatherCodeMapping.put(10, WeatherType.Storm);
    weatherCodeMapping.put(11, WeatherType.Storm);
    weatherCodeMapping.put(12, WeatherType.Storm);
    weatherCodeMapping.put(13, WeatherType.SnowFlurry);
    weatherCodeMapping.put(14, WeatherType.LightSnow);
    weatherCodeMapping.put(15, WeatherType.LightSnow);
    weatherCodeMapping.put(16, WeatherType.HeavySnow);
    weatherCodeMapping.put(17, WeatherType.SnowStorm);
    weatherCodeMapping.put(18, WeatherType.Foggy);
    weatherCodeMapping.put(19, WeatherType.Sleet);
    weatherCodeMapping.put(20, WeatherType.DustStorm);
    weatherCodeMapping.put(21, WeatherType.LightRain);
    weatherCodeMapping.put(22, WeatherType.HeavyRain);
    weatherCodeMapping.put(23, WeatherType.HeavyRain);
    weatherCodeMapping.put(24, WeatherType.Storm);
    weatherCodeMapping.put(25, WeatherType.Storm);
    weatherCodeMapping.put(26, WeatherType.LightSnow);
    weatherCodeMapping.put(27, WeatherType.HeavySnow);
    weatherCodeMapping.put(28, WeatherType.HeavySnow);
    weatherCodeMapping.put(29, WeatherType.DustStorm);
    weatherCodeMapping.put(30, WeatherType.DustStorm);
    weatherCodeMapping.put(31, WeatherType.DustStorm);
    weatherCodeToImage = new HashMap();
    weatherCodeToImage.put(WeatherType.Sunny, Integer.valueOf(2130838254));
    weatherCodeToImage.put(WeatherType.Cloudy, Integer.valueOf(2130838256));
    weatherCodeToImage.put(WeatherType.Overcast, Integer.valueOf(2130838255));
    weatherCodeToImage.put(WeatherType.Shower, Integer.valueOf(2130838260));
    weatherCodeToImage.put(WeatherType.ThunderShower, Integer.valueOf(2130838261));
    weatherCodeToImage.put(WeatherType.ThunderShowerwithHail, Integer.valueOf(2130838273));
    weatherCodeToImage.put(WeatherType.Sleet, Integer.valueOf(2130838266));
    weatherCodeToImage.put(WeatherType.LightRain, Integer.valueOf(2130838257));
    weatherCodeToImage.put(WeatherType.HeavyRain, Integer.valueOf(2130838274));
    weatherCodeToImage.put(WeatherType.Storm, Integer.valueOf(2130838259));
    weatherCodeToImage.put(WeatherType.SnowFlurry, Integer.valueOf(2130838264));
    weatherCodeToImage.put(WeatherType.LightSnow, Integer.valueOf(2130838263));
    weatherCodeToImage.put(WeatherType.HeavySnow, Integer.valueOf(2130838265));
    weatherCodeToImage.put(WeatherType.SnowStorm, Integer.valueOf(2130838268));
    weatherCodeToImage.put(WeatherType.Foggy, Integer.valueOf(2130838258));
    weatherCodeToImage.put(WeatherType.DustStorm, Integer.valueOf(2130838275));
    weatherCodeToSmallImage = new HashMap();
    weatherCodeToSmallImage.put(WeatherType.Sunny, Integer.valueOf(2130838295));
    weatherCodeToSmallImage.put(WeatherType.Cloudy, Integer.valueOf(2130838297));
    weatherCodeToSmallImage.put(WeatherType.Overcast, Integer.valueOf(2130838296));
    weatherCodeToSmallImage.put(WeatherType.Shower, Integer.valueOf(2130838301));
    weatherCodeToSmallImage.put(WeatherType.ThunderShower, Integer.valueOf(2130838302));
    weatherCodeToSmallImage.put(WeatherType.ThunderShowerwithHail, Integer.valueOf(2130838302));
    weatherCodeToSmallImage.put(WeatherType.Sleet, Integer.valueOf(2130838307));
    weatherCodeToSmallImage.put(WeatherType.LightRain, Integer.valueOf(2130838298));
    weatherCodeToSmallImage.put(WeatherType.HeavyRain, Integer.valueOf(2130838315));
    weatherCodeToSmallImage.put(WeatherType.Storm, Integer.valueOf(2130838300));
    weatherCodeToSmallImage.put(WeatherType.SnowFlurry, Integer.valueOf(2130838305));
    weatherCodeToSmallImage.put(WeatherType.LightSnow, Integer.valueOf(2130838304));
    weatherCodeToSmallImage.put(WeatherType.HeavySnow, Integer.valueOf(2130838306));
    weatherCodeToSmallImage.put(WeatherType.SnowStorm, Integer.valueOf(2130838309));
    weatherCodeToSmallImage.put(WeatherType.Foggy, Integer.valueOf(2130838299));
    weatherCodeToSmallImage.put(WeatherType.DustStorm, Integer.valueOf(2130838316));
    weatherCodeToMediumImage = new HashMap();
    weatherCodeToMediumImage.put(WeatherType.Sunny, Integer.valueOf(2130838254));
    weatherCodeToMediumImage.put(WeatherType.Cloudy, Integer.valueOf(2130838256));
    weatherCodeToMediumImage.put(WeatherType.Overcast, Integer.valueOf(2130838255));
    weatherCodeToMediumImage.put(WeatherType.Shower, Integer.valueOf(2130838260));
    weatherCodeToMediumImage.put(WeatherType.ThunderShower, Integer.valueOf(2130838261));
    weatherCodeToMediumImage.put(WeatherType.ThunderShowerwithHail, Integer.valueOf(2130838273));
    weatherCodeToMediumImage.put(WeatherType.Sleet, Integer.valueOf(2130838266));
    weatherCodeToMediumImage.put(WeatherType.LightRain, Integer.valueOf(2130838257));
    weatherCodeToMediumImage.put(WeatherType.HeavyRain, Integer.valueOf(2130838274));
    weatherCodeToMediumImage.put(WeatherType.Storm, Integer.valueOf(2130838259));
    weatherCodeToMediumImage.put(WeatherType.SnowFlurry, Integer.valueOf(2130838264));
    weatherCodeToMediumImage.put(WeatherType.LightSnow, Integer.valueOf(2130838263));
    weatherCodeToMediumImage.put(WeatherType.HeavySnow, Integer.valueOf(2130838265));
    weatherCodeToMediumImage.put(WeatherType.SnowStorm, Integer.valueOf(2130838268));
    weatherCodeToMediumImage.put(WeatherType.Foggy, Integer.valueOf(2130838258));
    weatherCodeToMediumImage.put(WeatherType.DustStorm, Integer.valueOf(2130838275));
  }

  public CMAWeatherResourceUtil(int paramInt)
  {
    this.mWeatherCode = paramInt;
  }

  public static WeatherType WeatherCodeToWeatherType(int paramInt)
  {
    return (WeatherType)weatherCodeMapping.get(paramInt);
  }

  private int getWeatherImage(HashMap<WeatherType, Integer> paramHashMap, WeatherType paramWeatherType)
  {
    Integer localInteger = (Integer)paramHashMap.get(paramWeatherType);
    if (localInteger == null);
    for (int i = 99; ; i = localInteger.intValue())
      return i;
  }

  private int imageForWeather()
  {
    return getWeatherImage(weatherCodeToImage, WeatherCodeToWeatherType(this.mWeatherCode));
  }

  private int imageForWeatherBackground()
  {
    int i;
    switch (this.mWeatherCode)
    {
    default:
      i = 99;
    case 0:
    case 1:
    case 2:
    case 20:
    case 29:
    case 30:
    case 31:
    case 3:
    case 4:
    case 5:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 6:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 19:
    case 26:
    case 27:
    case 28:
    case 18:
    }
    while (true)
    {
      return i;
      i = 2130838231;
      continue;
      i = 2130838233;
      continue;
      i = 2130838234;
      continue;
      i = 2130838232;
      continue;
      i = 2130838226;
    }
  }

  private int imageForWeatherMediumSize()
  {
    return getWeatherImage(weatherCodeToMediumImage, WeatherCodeToWeatherType(this.mWeatherCode));
  }

  private int imageForWeatherSevenBackground()
  {
    int i;
    switch (this.mWeatherCode)
    {
    default:
      i = 99;
    case 0:
    case 1:
    case 2:
    case 20:
    case 29:
    case 30:
    case 31:
    case 3:
    case 4:
    case 5:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 6:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 19:
    case 26:
    case 27:
    case 28:
    case 18:
    }
    while (true)
    {
      return i;
      i = 2130838231;
      continue;
      i = 2130838233;
      continue;
      i = 2130838234;
      continue;
      i = 2130838232;
      continue;
      i = 2130838226;
    }
  }

  private int imageForWeatherSmallSize()
  {
    return getWeatherImage(weatherCodeToSmallImage, WeatherCodeToWeatherType(this.mWeatherCode));
  }

  public int getWeatherBackgroundDrawable()
  {
    return imageForWeatherBackground();
  }

  public int getWeatherDrawable()
  {
    return imageForWeather();
  }

  public int getWeatherDrawableMediumSize()
  {
    return imageForWeatherMediumSize();
  }

  public int getWeatherDrawableSmallSize()
  {
    return imageForWeatherSmallSize();
  }

  public int getWeatherSevenBackgroundDrawable()
  {
    return imageForWeatherSevenBackground();
  }

  public static enum WeatherType
  {
    static
    {
      Cloudy = new WeatherType("Cloudy", 1);
      Overcast = new WeatherType("Overcast", 2);
      Shower = new WeatherType("Shower", 3);
      ThunderShower = new WeatherType("ThunderShower", 4);
      ThunderShowerwithHail = new WeatherType("ThunderShowerwithHail", 5);
      Sleet = new WeatherType("Sleet", 6);
      LightRain = new WeatherType("LightRain", 7);
      HeavyRain = new WeatherType("HeavyRain", 8);
      Storm = new WeatherType("Storm", 9);
      SnowFlurry = new WeatherType("SnowFlurry", 10);
      LightSnow = new WeatherType("LightSnow", 11);
      HeavySnow = new WeatherType("HeavySnow", 12);
      SnowStorm = new WeatherType("SnowStorm", 13);
      Foggy = new WeatherType("Foggy", 14);
      DustStorm = new WeatherType("DustStorm", 15);
      WeatherType[] arrayOfWeatherType = new WeatherType[16];
      arrayOfWeatherType[0] = Sunny;
      arrayOfWeatherType[1] = Cloudy;
      arrayOfWeatherType[2] = Overcast;
      arrayOfWeatherType[3] = Shower;
      arrayOfWeatherType[4] = ThunderShower;
      arrayOfWeatherType[5] = ThunderShowerwithHail;
      arrayOfWeatherType[6] = Sleet;
      arrayOfWeatherType[7] = LightRain;
      arrayOfWeatherType[8] = HeavyRain;
      arrayOfWeatherType[9] = Storm;
      arrayOfWeatherType[10] = SnowFlurry;
      arrayOfWeatherType[11] = LightSnow;
      arrayOfWeatherType[12] = HeavySnow;
      arrayOfWeatherType[13] = SnowStorm;
      arrayOfWeatherType[14] = Foggy;
      arrayOfWeatherType[15] = DustStorm;
      $VALUES = arrayOfWeatherType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.CMAWeatherResourceUtil
 * JD-Core Version:    0.6.0
 */