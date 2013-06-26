package com.tencent.tauth;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TencentOpenUtil
{
  public static final String PNAME_MQZONE = "com.qzone";
  public static final String SDK_VERSION = "0.0.2";

  public static boolean isAvilible(Context paramContext, String paramString)
  {
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (localList != null);
    for (int i = 0; ; i++)
    {
      if (i >= localList.size())
        return localArrayList.contains(paramString);
      localArrayList.add(((PackageInfo)localList.get(i)).packageName);
    }
  }

  public static boolean isInstallOnSDCard(Context paramContext, String paramString)
  {
    int i = 0;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int j = localPackageManager.getApplicationInfo(paramString, 0).flags;
      if ((j & 0x40000) != 0)
        i = 1;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        localNameNotFoundException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.TencentOpenUtil
 * JD-Core Version:    0.6.0
 */