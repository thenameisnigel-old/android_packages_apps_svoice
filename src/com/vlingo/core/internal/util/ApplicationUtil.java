package com.vlingo.core.internal.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class ApplicationUtil
{
  public static boolean checkApplicationExists(Context paramContext, String paramString)
  {
    Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(8704).iterator();
    do
      if (!localIterator.hasNext())
        break;
    while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ApplicationUtil
 * JD-Core Version:    0.6.0
 */