package com.vlingo.core.internal.location;

import android.location.Address;
import android.location.Geocoder;
import com.vlingo.core.internal.util.ApplicationAdapter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LocationUtils
{
  private static final int MAX_ADDRESSES = 1;
  private static final double NO_LAT;
  private static final double NO_LONG;
  private static final String[] googleNavSupportedCountriesCodes;

  static
  {
    String[] arrayOfString = new String[39];
    arrayOfString[0] = "DZ";
    arrayOfString[1] = "AR";
    arrayOfString[2] = "AU";
    arrayOfString[3] = "AT";
    arrayOfString[4] = "BH";
    arrayOfString[5] = "BE";
    arrayOfString[6] = "BR";
    arrayOfString[7] = "CA";
    arrayOfString[8] = "CZ";
    arrayOfString[9] = "DK";
    arrayOfString[10] = "EG";
    arrayOfString[11] = "FI";
    arrayOfString[12] = "FR";
    arrayOfString[13] = "DE";
    arrayOfString[14] = "HU";
    arrayOfString[15] = "IE";
    arrayOfString[16] = "IT";
    arrayOfString[17] = "JP";
    arrayOfString[18] = "JO";
    arrayOfString[19] = "KW";
    arrayOfString[20] = "LB";
    arrayOfString[21] = "LU";
    arrayOfString[22] = "NL";
    arrayOfString[23] = "NZ";
    arrayOfString[24] = "NO";
    arrayOfString[25] = "PL";
    arrayOfString[26] = "PT";
    arrayOfString[27] = "QA";
    arrayOfString[28] = "RO";
    arrayOfString[29] = "RU";
    arrayOfString[30] = "SA";
    arrayOfString[31] = "ZA";
    arrayOfString[32] = "ES";
    arrayOfString[33] = "SE";
    arrayOfString[34] = "CH";
    arrayOfString[35] = "AE";
    arrayOfString[36] = "UA";
    arrayOfString[37] = "GB";
    arrayOfString[38] = "US";
    googleNavSupportedCountriesCodes = arrayOfString;
  }

  public static Address getAddress(String paramString)
  {
    Address localAddress1 = null;
    Geocoder localGeocoder = new Geocoder(ApplicationAdapter.getInstance().getApplicationContext());
    try
    {
      List localList = localGeocoder.getFromLocationName(paramString, 1);
      if ((localList != null) && (!localList.isEmpty()))
        localAddress1 = (Address)localList.get(0);
      localAddress2 = localAddress1;
      return localAddress2;
    }
    catch (IOException localIOException)
    {
      while (true)
        Address localAddress2 = null;
    }
  }

  public static String getCellTowerInfo()
  {
    return com.vlingo.sdk.internal.location.LocationUtils.getCellTowerInfo();
  }

  public static double getLastLat()
  {
    return com.vlingo.sdk.internal.location.LocationUtils.getLastLat();
  }

  public static double getLastLong()
  {
    return com.vlingo.sdk.internal.location.LocationUtils.getLastLong();
  }

  public static boolean isGoogleNavAvailable()
  {
    double d1 = getLastLat();
    double d2 = getLastLong();
    int i;
    if ((d2 == 0.0D) || (d1 == 0.0D))
      i = 0;
    while (true)
    {
      return i;
      Geocoder localGeocoder = new Geocoder(ApplicationAdapter.getInstance().getApplicationContext());
      try
      {
        List localList = localGeocoder.getFromLocation(d1, d2, 1);
        if ((localList != null) && (localList.size() > 0))
        {
          Address localAddress = (Address)localList.get(0);
          boolean bool = Arrays.asList(googleNavSupportedCountriesCodes).contains(localAddress.getCountryCode());
          if (bool)
          {
            i = 1;
            continue;
          }
          i = 0;
          continue;
        }
        i = 0;
      }
      catch (IOException localIOException)
      {
        i = 0;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.location.LocationUtils
 * JD-Core Version:    0.6.0
 */