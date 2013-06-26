package com.vlingo.midas.util;

import android.util.SparseArray;
import java.util.HashMap;

public class WeatherResourceUtil
{
  private static final int NO_DRAWABLE = 101;
  private static final SparseArray<WeatherType> weatherCodeMapping = new SparseArray(40);
  private static final HashMap<WeatherType, Integer> weatherCodeToImage;
  private static final HashMap<WeatherType, Integer> weatherCodeToSmallImage;
  private int mWeatherCode;

  static
  {
    weatherCodeMapping.put(1, WeatherType.Sunny);
    weatherCodeMapping.put(2, WeatherType.Sunny);
    weatherCodeMapping.put(3, WeatherType.PartlySunny);
    weatherCodeMapping.put(4, WeatherType.PartlySunny);
    weatherCodeMapping.put(5, WeatherType.PartlySunny);
    weatherCodeMapping.put(6, WeatherType.MostlyCloudy);
    weatherCodeMapping.put(7, WeatherType.MostlyCloudy);
    weatherCodeMapping.put(8, WeatherType.MostlyCloudy);
    weatherCodeMapping.put(11, WeatherType.Fog);
    weatherCodeMapping.put(12, WeatherType.Showers);
    weatherCodeMapping.put(13, WeatherType.Showers);
    weatherCodeMapping.put(14, WeatherType.PartlySunnyWithShowers);
    weatherCodeMapping.put(15, WeatherType.Thunderstorms);
    weatherCodeMapping.put(16, WeatherType.MostlyCloudyWithThunderShowers);
    weatherCodeMapping.put(17, WeatherType.MostlyCloudyWithThunderShowers);
    weatherCodeMapping.put(18, WeatherType.Rain);
    weatherCodeMapping.put(19, WeatherType.Flurries);
    weatherCodeMapping.put(20, WeatherType.MostlyCloudyWithFlurries);
    weatherCodeMapping.put(21, WeatherType.MostlyCloudyWithFlurries);
    weatherCodeMapping.put(22, WeatherType.Snow);
    weatherCodeMapping.put(23, WeatherType.Snow);
    weatherCodeMapping.put(24, WeatherType.Ice);
    weatherCodeMapping.put(25, WeatherType.Ice);
    weatherCodeMapping.put(26, WeatherType.Ice);
    weatherCodeMapping.put(29, WeatherType.RainAndSnowMixed);
    weatherCodeMapping.put(30, WeatherType.Hot);
    weatherCodeMapping.put(31, WeatherType.Cold);
    weatherCodeMapping.put(32, WeatherType.Windy);
    weatherCodeMapping.put(33, WeatherType.Clear);
    weatherCodeMapping.put(34, WeatherType.MosltyClear);
    weatherCodeMapping.put(35, WeatherType.MosltyClear);
    weatherCodeMapping.put(36, WeatherType.MosltyClear);
    weatherCodeMapping.put(37, WeatherType.MosltyClear);
    weatherCodeMapping.put(38, WeatherType.MostlyCloudyNight);
    weatherCodeMapping.put(39, WeatherType.Showers);
    weatherCodeMapping.put(40, WeatherType.Showers);
    weatherCodeMapping.put(41, WeatherType.Thunderstorms);
    weatherCodeMapping.put(42, WeatherType.Thunderstorms);
    weatherCodeMapping.put(43, WeatherType.Flurries);
    weatherCodeMapping.put(44, WeatherType.Snow);
    weatherCodeToImage = new HashMap();
    weatherCodeToImage.put(WeatherType.Sunny, Integer.valueOf(2130838235));
    weatherCodeToImage.put(WeatherType.PartlySunny, Integer.valueOf(2130838236));
    weatherCodeToImage.put(WeatherType.MostlyCloudy, Integer.valueOf(2130838237));
    weatherCodeToImage.put(WeatherType.Fog, Integer.valueOf(2130838239));
    weatherCodeToImage.put(WeatherType.Showers, Integer.valueOf(2130838240));
    weatherCodeToImage.put(WeatherType.PartlySunnyWithShowers, Integer.valueOf(2130838241));
    weatherCodeToImage.put(WeatherType.Thunderstorms, Integer.valueOf(2130838242));
    weatherCodeToImage.put(WeatherType.MostlyCloudyWithThunderShowers, Integer.valueOf(2130838243));
    weatherCodeToImage.put(WeatherType.Rain, Integer.valueOf(2130838238));
    weatherCodeToImage.put(WeatherType.Flurries, Integer.valueOf(2130838244));
    weatherCodeToImage.put(WeatherType.MostlyCloudyWithFlurries, Integer.valueOf(2130838245));
    weatherCodeToImage.put(WeatherType.Snow, Integer.valueOf(2130838246));
    weatherCodeToImage.put(WeatherType.Ice, Integer.valueOf(2130838249));
    weatherCodeToImage.put(WeatherType.RainAndSnowMixed, Integer.valueOf(2130838247));
    weatherCodeToImage.put(WeatherType.Hot, Integer.valueOf(2130838250));
    weatherCodeToImage.put(WeatherType.Cold, Integer.valueOf(2130838251));
    weatherCodeToImage.put(WeatherType.Windy, Integer.valueOf(2130838248));
    weatherCodeToImage.put(WeatherType.Clear, Integer.valueOf(2130838252));
    weatherCodeToImage.put(WeatherType.MosltyClear, Integer.valueOf(2130838253));
    weatherCodeToImage.put(WeatherType.MostlyCloudyNight, Integer.valueOf(2130838253));
    weatherCodeToSmallImage = new HashMap();
    weatherCodeToSmallImage.put(WeatherType.Sunny, Integer.valueOf(2130838295));
    weatherCodeToSmallImage.put(WeatherType.PartlySunny, Integer.valueOf(2130838296));
    weatherCodeToSmallImage.put(WeatherType.MostlyCloudy, Integer.valueOf(2130838297));
    weatherCodeToSmallImage.put(WeatherType.Fog, Integer.valueOf(2130838299));
    weatherCodeToSmallImage.put(WeatherType.Showers, Integer.valueOf(2130838300));
    weatherCodeToSmallImage.put(WeatherType.PartlySunnyWithShowers, Integer.valueOf(2130838301));
    weatherCodeToSmallImage.put(WeatherType.Thunderstorms, Integer.valueOf(2130838302));
    weatherCodeToSmallImage.put(WeatherType.MostlyCloudyWithThunderShowers, Integer.valueOf(2130838303));
    weatherCodeToSmallImage.put(WeatherType.Rain, Integer.valueOf(2130838298));
    weatherCodeToSmallImage.put(WeatherType.Flurries, Integer.valueOf(2130838304));
    weatherCodeToSmallImage.put(WeatherType.MostlyCloudyWithFlurries, Integer.valueOf(2130838305));
    weatherCodeToSmallImage.put(WeatherType.Snow, Integer.valueOf(2130838306));
    weatherCodeToSmallImage.put(WeatherType.Ice, Integer.valueOf(2130838309));
    weatherCodeToSmallImage.put(WeatherType.RainAndSnowMixed, Integer.valueOf(2130838307));
    weatherCodeToSmallImage.put(WeatherType.Hot, Integer.valueOf(2130838310));
    weatherCodeToSmallImage.put(WeatherType.Cold, Integer.valueOf(2130838311));
    weatherCodeToSmallImage.put(WeatherType.Windy, Integer.valueOf(2130838308));
    weatherCodeToSmallImage.put(WeatherType.Clear, Integer.valueOf(2130838312));
    weatherCodeToSmallImage.put(WeatherType.MosltyClear, Integer.valueOf(2130838313));
    weatherCodeToSmallImage.put(WeatherType.MostlyCloudyNight, Integer.valueOf(2130838313));
  }

  public WeatherResourceUtil(int paramInt)
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
    for (int i = 101; ; i = localInteger.intValue())
      return i;
  }

  private int imageForWeather()
  {
    return getWeatherImage(weatherCodeToImage, WeatherCodeToWeatherType(this.mWeatherCode));
  }

  private int imageForWeatherBackground()
  {
    int i = 2130838215;
    switch (this.mWeatherCode)
    {
    case 9:
    case 10:
    case 27:
    case 28:
    default:
      i = 101;
    case 6:
    case 7:
    case 8:
    case 11:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 1:
    case 2:
    case 30:
    case 3:
    case 4:
    case 5:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 39:
    case 40:
    case 41:
    case 42:
    case 19:
    case 20:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
    case 29:
    case 43:
    case 44:
    }
    while (true)
    {
      return i;
      i = 2130838217;
      continue;
      i = 2130838213;
      continue;
      i = 2130838209;
      continue;
      i = 2130838211;
    }
  }

  private int imageForWeatherSevenBackground()
  {
    int i = 2130838215;
    switch (this.mWeatherCode)
    {
    case 9:
    case 10:
    case 27:
    case 28:
    default:
      i = 101;
    case 6:
    case 7:
    case 8:
    case 11:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 1:
    case 2:
    case 30:
    case 3:
    case 4:
    case 5:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 39:
    case 40:
    case 41:
    case 42:
    case 19:
    case 20:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
    case 29:
    case 43:
    case 44:
    }
    while (true)
    {
      return i;
      i = 2130838217;
      continue;
      i = 2130838213;
      continue;
      i = 2130838209;
      continue;
      i = 2130838211;
    }
  }

  private int imageForWeatherSmallSize()
  {
    return getWeatherImage(weatherCodeToSmallImage, WeatherCodeToWeatherType(this.mWeatherCode));
  }

  public int changeToNightBackground(int paramInt)
  {
    int i = 2130838214;
    if (paramInt == 2130838209)
      i = 2130838210;
    while (true)
    {
      return i;
      if (paramInt == 2130838211)
      {
        i = 2130838212;
        continue;
      }
      if (paramInt == 2130838213)
        continue;
      if (paramInt == 2130838215)
      {
        i = 2130838216;
        continue;
      }
      if (paramInt == 2130838217)
      {
        i = 2130838218;
        continue;
      }
      if (paramInt != 2130838219)
        continue;
      i = 2130838220;
    }
  }

  public int changeToNightimage(int paramInt)
  {
    int i = 2130838276;
    if (paramInt == 2130838295);
    while (true)
    {
      return i;
      if (paramInt == 2130838296)
      {
        i = 2130838277;
        continue;
      }
      if (paramInt == 2130838297)
      {
        i = 2130838278;
        continue;
      }
      if (paramInt == 2130838298)
      {
        i = 2130838279;
        continue;
      }
      if (paramInt == 2130838299)
      {
        i = 2130838280;
        continue;
      }
      if (paramInt == 2130838300)
      {
        i = 2130838281;
        continue;
      }
      if (paramInt == 2130838301)
      {
        i = 2130838282;
        continue;
      }
      if (paramInt == 2130838302)
      {
        i = 2130838283;
        continue;
      }
      if (paramInt == 2130838303)
      {
        i = 2130838284;
        continue;
      }
      if (paramInt == 2130838304)
      {
        i = 2130838285;
        continue;
      }
      if (paramInt == 2130838305)
      {
        i = 2130838286;
        continue;
      }
      if (paramInt == 2130838306)
      {
        i = 2130838287;
        continue;
      }
      if (paramInt == 2130838307)
      {
        i = 2130838288;
        continue;
      }
      if (paramInt == 2130838308)
      {
        i = 2130838289;
        continue;
      }
      if (paramInt == 2130838309)
      {
        i = 2130838290;
        continue;
      }
      if (paramInt == 2130838310)
      {
        i = 2130838291;
        continue;
      }
      if (paramInt == 2130838311)
      {
        i = 2130838292;
        continue;
      }
      if (paramInt == 2130838312)
      {
        i = 2130838293;
        continue;
      }
      if (paramInt != 2130838313)
        continue;
      i = 2130838294;
    }
  }

  public int getWeatherBackgroundDrawable()
  {
    return imageForWeatherBackground();
  }

  public int getWeatherDrawable()
  {
    return imageForWeather();
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
      PartlySunny = new WeatherType("PartlySunny", 1);
      MostlyCloudy = new WeatherType("MostlyCloudy", 2);
      Fog = new WeatherType("Fog", 3);
      Showers = new WeatherType("Showers", 4);
      PartlySunnyWithShowers = new WeatherType("PartlySunnyWithShowers", 5);
      Thunderstorms = new WeatherType("Thunderstorms", 6);
      MostlyCloudyWithThunderShowers = new WeatherType("MostlyCloudyWithThunderShowers", 7);
      Rain = new WeatherType("Rain", 8);
      Flurries = new WeatherType("Flurries", 9);
      MostlyCloudyWithFlurries = new WeatherType("MostlyCloudyWithFlurries", 10);
      Snow = new WeatherType("Snow", 11);
      Ice = new WeatherType("Ice", 12);
      RainAndSnowMixed = new WeatherType("RainAndSnowMixed", 13);
      Hot = new WeatherType("Hot", 14);
      Cold = new WeatherType("Cold", 15);
      Windy = new WeatherType("Windy", 16);
      Clear = new WeatherType("Clear", 17);
      MosltyClear = new WeatherType("MosltyClear", 18);
      MostlyCloudyNight = new WeatherType("MostlyCloudyNight", 19);
      WeatherType[] arrayOfWeatherType = new WeatherType[20];
      arrayOfWeatherType[0] = Sunny;
      arrayOfWeatherType[1] = PartlySunny;
      arrayOfWeatherType[2] = MostlyCloudy;
      arrayOfWeatherType[3] = Fog;
      arrayOfWeatherType[4] = Showers;
      arrayOfWeatherType[5] = PartlySunnyWithShowers;
      arrayOfWeatherType[6] = Thunderstorms;
      arrayOfWeatherType[7] = MostlyCloudyWithThunderShowers;
      arrayOfWeatherType[8] = Rain;
      arrayOfWeatherType[9] = Flurries;
      arrayOfWeatherType[10] = MostlyCloudyWithFlurries;
      arrayOfWeatherType[11] = Snow;
      arrayOfWeatherType[12] = Ice;
      arrayOfWeatherType[13] = RainAndSnowMixed;
      arrayOfWeatherType[14] = Hot;
      arrayOfWeatherType[15] = Cold;
      arrayOfWeatherType[16] = Windy;
      arrayOfWeatherType[17] = Clear;
      arrayOfWeatherType[18] = MosltyClear;
      arrayOfWeatherType[19] = MostlyCloudyNight;
      $VALUES = arrayOfWeatherType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.WeatherResourceUtil
 * JD-Core Version:    0.6.0
 */