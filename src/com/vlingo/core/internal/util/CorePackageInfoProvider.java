package com.vlingo.core.internal.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;

public class CorePackageInfoProvider
{
  public static boolean hasDialing()
  {
    int i = 0;
    try
    {
      int j = ((TelephonyManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("phone")).getPhoneType();
      if (j != 0)
        i = 1;
      label26: return i;
    }
    catch (Exception localException)
    {
      break label26;
    }
  }

  public static boolean hasMessaging()
  {
    try
    {
      ApplicationInfo localApplicationInfo2 = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo("com.android.mms", 128);
      localApplicationInfo1 = localApplicationInfo2;
      if (localApplicationInfo1 != null)
      {
        i = 1;
        return i;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        ApplicationInfo localApplicationInfo1 = null;
        continue;
        int i = 0;
      }
    }
  }

  public static boolean hasTelephony()
  {
    try
    {
      boolean bool2 = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.telephony");
      bool1 = bool2;
      return bool1;
    }
    catch (Exception localException)
    {
      while (true)
        boolean bool1 = false;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.CorePackageInfoProvider
 * JD-Core Version:    0.6.0
 */