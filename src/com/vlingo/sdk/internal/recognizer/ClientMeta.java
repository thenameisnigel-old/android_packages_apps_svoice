package com.vlingo.sdk.internal.recognizer;

import android.os.Build;
import com.nuance.id.NuanceId;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.deviceinfo.PhoneInfo;
import com.vlingo.sdk.internal.location.LocationUtils;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.util.SDKDebugSettings;

public class ClientMeta
{
  private static ClientMeta instance = null;
  String deviceID = null;
  String deviceModel = null;
  String deviceOS = null;
  Boolean fakeLatLong = Boolean.valueOf(false);
  String hardwareID = null;

  public static void destroy()
  {
    instance = null;
  }

  public static ClientMeta getInstance()
  {
    if (instance == null)
      instance = new ClientMeta();
    return instance;
  }

  public String geHardwareID()
  {
    if (this.hardwareID == null)
      this.hardwareID = PhoneInfo.getInstance().getDeviceID();
    return this.hardwareID;
  }

  public String getCarrier()
  {
    SDKDebugSettings localSDKDebugSettings = VLSdk.getInstance().getDebugSettings();
    String str;
    if (localSDKDebugSettings != null)
    {
      str = localSDKDebugSettings.getCarrier();
      if (StringUtils.isNullOrWhiteSpace(str));
    }
    while (true)
    {
      return str;
      str = PhoneInfo.getInstance().getCurrentCarrier();
    }
  }

  public String getCarrierCountry()
  {
    SDKDebugSettings localSDKDebugSettings = VLSdk.getInstance().getDebugSettings();
    String str;
    if (localSDKDebugSettings != null)
    {
      str = localSDKDebugSettings.getCarrierCountry();
      if (StringUtils.isNullOrWhiteSpace(str));
    }
    while (true)
    {
      return str;
      str = PhoneInfo.getInstance().getCarrierCountry();
    }
  }

  public String getDeviceID()
  {
    String str = Settings.getPersistentString("uuid", null);
    if (str == null)
    {
      str = new NuanceId(ApplicationAdapter.getInstance().getApplicationContext(), 14).getId();
      Settings.setPersistentString("uuid", str);
    }
    return str;
  }

  public String getDeviceMake()
  {
    return Build.MANUFACTURER;
  }

  public String getDeviceModel()
  {
    SDKDebugSettings localSDKDebugSettings = VLSdk.getInstance().getDebugSettings();
    if (localSDKDebugSettings != null)
    {
      this.deviceModel = localSDKDebugSettings.getModelNumber();
      if (StringUtils.isNullOrWhiteSpace(this.deviceModel));
    }
    for (String str = this.deviceModel; ; str = this.deviceModel)
    {
      return str;
      if (this.deviceModel != null)
        continue;
      this.deviceModel = PhoneInfo.getInstance().getModel();
    }
  }

  public String getDeviceOS()
  {
    return PhoneInfo.getInstance().getOSVersion();
  }

  public String getDeviceOSName()
  {
    return "Android";
  }

  public String getLanguage()
  {
    return Settings.LANGUAGE;
  }

  public String getLocation()
  {
    SDKDebugSettings localSDKDebugSettings = VLSdk.getInstance().getDebugSettings();
    String str2;
    if (localSDKDebugSettings != null)
    {
      str2 = localSDKDebugSettings.getLocation();
      if (StringUtils.isNullOrWhiteSpace(str2));
    }
    String str1;
    for (Object localObject = str2; ; localObject = str1)
    {
      return localObject;
      str1 = LocationUtils.getCellTowerInfo();
      if (str1 != null)
        continue;
      str1 = "";
    }
  }

  public String getPhoneNumber()
  {
    return "";
  }

  public int getVendorID()
  {
    return PhoneInfo.getInstance().getVendorID();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.ClientMeta
 * JD-Core Version:    0.6.0
 */