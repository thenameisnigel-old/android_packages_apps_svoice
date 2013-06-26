package com.vlingo.core.internal.localsearch;

import com.vlingo.core.internal.location.LocationUtils;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LocalSearchChineseURLMaker
{
  private static final String APPKEY = "014473244";
  private static final String APP_SECRET = "76243fd4ea6c491f982433a034030c3a";
  private static final String BUSINESS = "/business/find_businesses?";
  private static final String SINGLE_BUSINESS = "/review/get_recent_reviews?";
  private static Map<String, String> paramMap = null;

  private static String byteArrayToHexString(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfByte[j];
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(k & 0xFF);
      localStringBuilder.append(String.format("%02x", arrayOfObject));
    }
    return localStringBuilder.toString();
  }

  public static String getBusinessURL(String paramString1, String paramString2, String paramString3)
    throws NoSuchAlgorithmException
  {
    paramMap = new LocalSearchChineseURLMaker.1();
    if (!StringUtils.isNullOrWhiteSpace(paramString1));
    for (String str = getUrlFindBusinessesWithCity(paramString1, paramString2, paramString3); ; str = getUrlFindBusinessesWithLocation(paramString3))
      return str;
  }

  private static String getLat()
  {
    if (Settings.getBoolean("FAKE_LAT_LONG", false));
    for (String str = Settings.getString("FAKE_LAT", null); ; str = "" + LocationUtils.getLastLat())
      return str;
  }

  private static String getLong()
  {
    if (Settings.getBoolean("FAKE_LAT_LONG", false));
    for (String str = Settings.getString("FAKE_LONG", null); ; str = "" + LocationUtils.getLastLong())
      return str;
  }

  private static String getQueryString(Map<String, String> paramMap1)
  {
    String str1 = "";
    Iterator localIterator = paramMap1.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str2 = str1 + URLEncoder.encode(localEntry.getKey().toString()) + "=";
      str1 = str2 + URLEncoder.encode(localEntry.getValue().toString()) + "&";
    }
    return str1;
  }

  private static String getSHASign(Map<String, String> paramMap1)
    throws NoSuchAlgorithmException
  {
    String[] arrayOfString = (String[])paramMap1.keySet().toArray(new String[0]);
    Arrays.sort(arrayOfString);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("014473244");
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
    {
      String str = arrayOfString[j];
      localStringBuilder.append(str).append((String)paramMap1.get(str));
    }
    localStringBuilder.append("76243fd4ea6c491f982433a034030c3a");
    return byteArrayToHexString(MessageDigest.getInstance("SHA-1").digest(localStringBuilder.toString().getBytes()));
  }

  private static String getURL(Map<String, String> paramMap1, String paramString)
    throws NoSuchAlgorithmException
  {
    String str1 = getQueryString(paramMap1);
    String str2 = getSHASign(paramMap1).toUpperCase();
    return "http://api.dianping.com/v1" + paramString + str1 + "&appkey=" + "014473244" + "&sign=" + str2;
  }

  private static String getUrlFindBusinessesWithCity(String paramString1, String paramString2, String paramString3)
    throws NoSuchAlgorithmException
  {
    paramMap.put("city", paramString1);
    if (!StringUtils.isNullOrWhiteSpace(paramString2))
      paramMap.put("region", paramString2);
    if (!StringUtils.isNullOrWhiteSpace(paramString3))
      paramMap.put("keyword", paramString3);
    return getURL(paramMap, "/business/find_businesses?");
  }

  private static String getUrlFindBusinessesWithLocation(String paramString)
    throws NoSuchAlgorithmException
  {
    paramMap.put("sort", "7");
    paramMap.put("latitude", getLat());
    paramMap.put("longitude", getLong());
    paramMap.put("radius", "5000");
    if (!StringUtils.isNullOrWhiteSpace(paramString))
      paramMap.put("keyword", paramString);
    return getURL(paramMap, "/business/find_businesses?");
  }

  public static String getUrlRecentReviewsForBusiness(String paramString)
    throws NoSuchAlgorithmException
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("format", "json");
    localHashMap.put("business_id", paramString);
    return getURL(localHashMap, "/review/get_recent_reviews?");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchChineseURLMaker
 * JD-Core Version:    0.6.0
 */