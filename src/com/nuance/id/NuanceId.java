package com.nuance.id;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.lang.reflect.Field;

public final class NuanceId
{
  public static final int INCLUDE_ALL = 15;
  public static final int INCLUDE_ANDROIDID = 8;
  public static final int INCLUDE_IMEI = 1;
  public static final int INCLUDE_MAC = 4;
  public static final int INCLUDE_SERIAL = 2;
  public static final String NULL_ANDROIDID = "00000000000000000";
  public static final String NULL_HASH = "00000000000000000000000000000000000000000000000000000000000000000";
  public static final String NULL_IMEI = "00000000000000000";
  public static final String NULL_MAC_ADDRESS = "000000000000";
  public static final String NULL_SERIAL = "0000000000000000000000";
  private Context context;
  private NuanceIdImpl hashingInternal;
  private String id;
  private int idsUsed;

  public NuanceId(Context paramContext)
  {
    this(paramContext, 15);
  }

  public NuanceId(Context paramContext, int paramInt)
  {
    this.context = paramContext;
    this.hashingInternal = new NuanceIdImpl();
    this.idsUsed = paramInt;
    if (paramInt == 0)
      throw new RuntimeException("Must include at least one device IDs to generate NuanceID");
  }

  protected static String getCompatibilityDeviceProperty(String paramString)
  {
    try
    {
      Field localField = Build.class.getDeclaredField(paramString);
      localField.setAccessible(true);
      String str2 = localField.get(Build.class).toString();
      str1 = str2;
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
        String str1 = null;
    }
  }

  private int[] getCountOfOnesInBinary(int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = -1;
    for (int k = 0; k < 4; k++)
    {
      int m = 1 << k;
      if (m == paramInt2)
        j = i;
      if ((paramInt1 & m) == 0)
        continue;
      i++;
    }
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = i;
    arrayOfInt[1] = j;
    return arrayOfInt;
  }

  private String getPlatformIdHash(String paramString, int paramInt)
  {
    if (!isIdIncluded(paramInt));
    int k;
    int m;
    for (String str = null; ; str = paramString.substring(m, m + k))
    {
      return str;
      int[] arrayOfInt = getCountOfOnesInBinary(this.idsUsed, paramInt);
      int i = arrayOfInt[1];
      int j = 0;
      if (!isAllIncluded())
        j = 1;
      k = (paramString.length() - j) / arrayOfInt[0];
      m = j + i * k;
    }
  }

  private boolean isAllIncluded()
  {
    if ((0xF & this.idsUsed) == 15);
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isIdIncluded(int paramInt)
  {
    if ((paramInt & this.idsUsed) == paramInt);
    for (int i = 1; ; i = 0)
      return i;
  }

  protected boolean checkPermission(String paramString)
  {
    int i = 0;
    if ((this.context != null) && (this.context.checkCallingOrSelfPermission(paramString) == 0))
      i = 1;
    return i;
  }

  public boolean equals(NuanceId paramNuanceId)
  {
    int i = 0;
    int j = this.idsUsed & paramNuanceId.idsUsed;
    if (j == 0);
    while (true)
    {
      return i;
      for (int k = 0; ; k++)
      {
        if (k >= 4)
          break label72;
        int m = 1 << k;
        if (((j & m) != 0) && (!getPlatformIdHash(getId(), m).equals(paramNuanceId.getPlatformIdHash(paramNuanceId.getId(), m))))
          break;
      }
      label72: i = 1;
    }
  }

  protected String getAndroidId()
  {
    String str = null;
    if ((this.context != null) && (isIdIncluded(8)))
      str = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    if (str == null)
      str = "00000000000000000";
    return str;
  }

  public String getAndroidIdHash()
  {
    return getPlatformIdHash(getId(), 8);
  }

  public String getExternalId()
  {
    return this.hashingInternal.sha1hash(getAndroidId());
  }

  protected String getIMEI()
  {
    String str = null;
    if ((this.context != null) && (isIdIncluded(1)) && (checkPermission("android.permission.READ_PHONE_STATE")))
      str = ((TelephonyManager)this.context.getSystemService("phone")).getDeviceId();
    if (str == null)
      str = "00000000000000000";
    return str;
  }

  public String getIMEIHash()
  {
    return getPlatformIdHash(getId(), 1);
  }

  public String getId()
  {
    if (this.id == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (!isAllIncluded())
      {
        String str = Integer.toHexString(this.idsUsed);
        localStringBuilder.append(str.substring(-1 + str.length()));
      }
      if (isIdIncluded(1))
        localStringBuilder.append(this.hashingInternal.generateHash(getIMEI()));
      if (isIdIncluded(2))
        localStringBuilder.append(this.hashingInternal.generateHash(getSerial()));
      if (isIdIncluded(4))
        localStringBuilder.append(this.hashingInternal.generateHash(getMac()));
      if (isIdIncluded(8))
        localStringBuilder.append(this.hashingInternal.generateHash(getAndroidId()));
      this.id = localStringBuilder.toString();
    }
    return this.id;
  }

  protected String getMac()
  {
    String str = null;
    if ((this.context != null) && (isIdIncluded(4)) && (checkPermission("android.permission.ACCESS_WIFI_STATE")))
    {
      WifiManager localWifiManager = (WifiManager)this.context.getSystemService("wifi");
      if (localWifiManager != null)
      {
        WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
        if (localWifiInfo != null)
        {
          str = localWifiInfo.getMacAddress();
          if (str != null)
            str = str.replaceAll(":", "");
        }
      }
    }
    if (str == null)
      str = "000000000000";
    return str;
  }

  public String getMacHash()
  {
    return getPlatformIdHash(getId(), 4);
  }

  protected String getSerial()
  {
    String str = null;
    if (isIdIncluded(2))
      str = getCompatibilityDeviceProperty("SERIAL");
    if (str == null)
      str = "0000000000000000000000";
    return str;
  }

  public String getSerialHash()
  {
    return getPlatformIdHash(getId(), 2);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.nuance.id.NuanceId
 * JD-Core Version:    0.6.0
 */