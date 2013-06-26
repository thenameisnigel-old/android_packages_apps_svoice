package com.vlingo.sdk.internal.deviceinfo;

public class CountryUtils
{
  public static String ISO2ToISO3CountryCode(String paramString)
  {
    return CountryCodes.getInstance().getIso3FromIso2(paramString);
  }

  public static String ISO3ToISO2CountryCode(String paramString)
  {
    return CountryCodes.getInstance().getIso2FromIso3(paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.deviceinfo.CountryUtils
 * JD-Core Version:    0.6.0
 */