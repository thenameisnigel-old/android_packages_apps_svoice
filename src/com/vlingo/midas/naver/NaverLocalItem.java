package com.vlingo.midas.naver;

import java.util.Hashtable;

public class NaverLocalItem extends NaverItem
{
  private final String MAP_URI = "mapImage";
  private final String PHONE_URI = "phoneNumber";
  private final String ROUTE_URI = "route";

  public NaverLocalItem(Hashtable<String, String> paramHashtable)
  {
    super(paramHashtable);
  }

  public static String getSeparatedLocation(String paramString1, String paramString2)
  {
    if ((paramString2 == null) || (paramString2.length() == 0) || (paramString1 == null));
    label64: 
    while (true)
    {
      return paramString2;
      String str = paramString1;
      for (int i = str.lastIndexOf(" "); ; i = str.lastIndexOf(" "))
      {
        if (i <= 0)
          break label64;
        str = str.substring(0, i);
        if (!paramString2.startsWith(str))
          continue;
        paramString2 = paramString2.substring(i + 1);
        break;
      }
    }
  }

  public String getAddress()
  {
    return getStringOrEmptyString("address");
  }

  public String getCategory()
  {
    return getStringOrEmptyString("category");
  }

  public String getDistance()
  {
    String str1 = "0";
    int i = 0;
    String str2 = "km";
    float f = Float.valueOf(getString("distance")).floatValue();
    int j;
    if (getString("distance") != null)
    {
      if (f <= 10000.0F)
        break label116;
      j = (int)f / 1000;
    }
    while (true)
    {
      String str3 = new String("" + j);
      if (i != 0)
        str3 = str3.concat("." + i);
      str1 = str3.concat(str2);
      return str1;
      label116: if (f > 1000.0F)
      {
        j = (int)f / 1000;
        i = (int)f % 1000 / 100;
        continue;
      }
      j = (int)f;
      str2 = "m";
    }
  }

  public String getMapImageUrl()
  {
    return getString("mapImage");
  }

  public String getPhoneUrl()
  {
    return getString("phoneNumber");
  }

  public Float getRating()
  {
    if (getString("reviewScore") != null);
    for (Float localFloat = Float.valueOf(getString("reviewScore")); ; localFloat = Float.valueOf(0.0F))
      return localFloat;
  }

  public Integer getReviewCount()
  {
    if (getString("reviewCount") != null);
    for (Integer localInteger = Integer.valueOf(getString("reviewCount")); ; localInteger = Integer.valueOf(0))
      return localInteger;
  }

  public String getRouteUrl()
  {
    return getString("route");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.naver.NaverLocalItem
 * JD-Core Version:    0.6.0
 */