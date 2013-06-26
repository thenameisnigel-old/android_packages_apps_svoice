package com.vlingo.sdk.internal.deviceinfo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.crypto.CryptoUtils;
import com.vlingo.sdk.internal.location.LocationUtils;
import com.vlingo.sdk.internal.util.StringUtils;

public class PhoneInfo
{
  private static PhoneInfo instance;
  String deviceId = null;

  public static void destroy()
  {
    instance = null;
  }

  public static PhoneInfo getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = new PhoneInfo();
      PhoneInfo localPhoneInfo = instance;
      monitorexit;
      return localPhoneInfo;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static String hashForPhoneNumber(String paramString)
  {
    int i = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(i);
    for (int j = 0; j < i; j++)
    {
      char c = paramString.charAt(j);
      if (!Character.isDigit(c))
        continue;
      localStringBuffer.append(c);
    }
    if (localStringBuffer.length() > 7)
      localStringBuffer.delete(0, -7 + localStringBuffer.length());
    return CryptoUtils.getHash(localStringBuffer.toString(), 2);
  }

  private static String myModel()
  {
    String str;
    if (Build.MODEL.equals("HTC Paradise"))
      str = "ventura";
    while (true)
    {
      return str;
      if (Build.MODEL.equals("HTC Liberty"))
      {
        str = "intruder";
        continue;
      }
      str = Build.MODEL;
    }
  }

  public String getCarrierCountry()
  {
    String str = LocationUtils.getCarrierCountry(getCtx());
    if ((str == null) || (str.length() == 0))
    {
      str = LocationUtils.getMCC();
      if ("000".equals(str))
        str = null;
    }
    if (str == null)
      str = "";
    return str.toUpperCase();
  }

  Context getCtx()
  {
    return ApplicationAdapter.getInstance().getApplicationContext();
  }

  public String getCurrentCarrier()
  {
    String str1 = getManager().getNetworkOperatorName();
    String str2 = str1.replaceAll("[^A-Za-z0-9]", "");
    if (!str2.equals(str1));
    return str2;
  }

  public String getCurrentNetworkISO3CountryCode()
  {
    return CountryUtils.ISO2ToISO3CountryCode(getCarrierCountry());
  }

  public String getDeviceID()
  {
    if (this.deviceId == null)
    {
      this.deviceId = getManager().getDeviceId();
      if ((StringUtils.isNullOrWhiteSpace(this.deviceId)) && (Build.VERSION.SDK_INT >= 9))
        this.deviceId = Build.SERIAL;
      if (StringUtils.isNullOrWhiteSpace(this.deviceId))
      {
        WifiInfo localWifiInfo = getWifiManager().getConnectionInfo();
        if (localWifiInfo != null)
        {
          this.deviceId = localWifiInfo.getMacAddress();
          if (this.deviceId != null)
            this.deviceId = StringUtils.replace(this.deviceId, ":", "");
        }
      }
      if ((StringUtils.isNullOrWhiteSpace(this.deviceId)) && (Build.VERSION.SDK_INT >= 8))
        this.deviceId = Settings.Secure.getString(getCtx().getContentResolver(), "android_id");
    }
    return this.deviceId;
  }

  TelephonyManager getManager()
  {
    return (TelephonyManager)getCtx().getSystemService("phone");
  }

  public String getModel()
  {
    return myModel();
  }

  public String getOSVersion()
  {
    return Build.VERSION.RELEASE;
  }

  public String getPhoneNumber()
  {
    return "";
  }

  public String getPhoneNumberHash()
  {
    return "";
  }

  public String getShortOSVersion()
  {
    return Build.VERSION.RELEASE;
  }

  public String getVendorCarrier()
  {
    return Build.BRAND;
  }

  public String getVendorCountry()
  {
    return null;
  }

  public int getVendorID()
  {
    return 0;
  }

  public String getVendorName()
  {
    return Build.MANUFACTURER;
  }

  WifiManager getWifiManager()
  {
    return (WifiManager)getCtx().getSystemService("wifi");
  }

  public boolean isSimulator()
  {
    return false;
  }

  public boolean isTouchDevice()
  {
    return true;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.deviceinfo.PhoneInfo
 * JD-Core Version:    0.6.0
 */