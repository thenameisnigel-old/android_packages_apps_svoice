package com.vlingo.core.internal.util;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.util.List;

public class LocationUtil
{
  private static LocationUtil instance = null;
  private Context context = ApplicationAdapter.getInstance().getApplicationContext();
  private String countryCode = null;

  private String _getCountryCode()
  {
    if (StringUtils.isNullOrWhiteSpace(this.countryCode))
    {
      this.countryCode = ((TelephonyManager)this.context.getSystemService("phone")).getSimCountryIso();
      if (StringUtils.isNullOrWhiteSpace(this.countryCode));
    }
    for (String str1 = this.countryCode; ; str1 = null)
      while (true)
      {
        return str1;
        LocationManager localLocationManager = (LocationManager)this.context.getSystemService("location");
        Criteria localCriteria = new Criteria();
        localCriteria.setAccuracy(2);
        localCriteria.setAltitudeRequired(false);
        localCriteria.setBearingRequired(false);
        localCriteria.setCostAllowed(false);
        localCriteria.setSpeedRequired(false);
        String str2 = localLocationManager.getBestProvider(localCriteria, true);
        if (str2 != null)
        {
          Location localLocation = localLocationManager.getLastKnownLocation(str2);
          if (localLocation != null)
          {
            double d1 = localLocation.getLatitude();
            double d2 = localLocation.getLongitude();
            Geocoder localGeocoder = new Geocoder(this.context);
            try
            {
              List localList = localGeocoder.getFromLocation(d1, d2, 1);
              if ((localList != null) && (localList.size() > 0))
              {
                this.countryCode = ((Address)localList.get(0)).getCountryCode();
                str1 = this.countryCode;
              }
            }
            catch (IOException localIOException)
            {
              localIOException.printStackTrace();
            }
          }
        }
      }
  }

  public static void destroy()
  {
    instance = null;
  }

  public static String getCountryCode()
  {
    return getInstance()._getCountryCode();
  }

  public static LocationUtil getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = new LocationUtil();
      LocationUtil localLocationUtil = instance;
      monitorexit;
      return localLocationUtil;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.LocationUtil
 * JD-Core Version:    0.6.0
 */