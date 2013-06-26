package com.vlingo.sdk.internal.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import java.util.Iterator;
import java.util.List;

public class PackageUtil
{
  public static boolean canHandleBroadcastIntent(Context paramContext, Intent paramIntent)
  {
    int i = 0;
    List localList = paramContext.getPackageManager().queryBroadcastReceivers(paramIntent, 0);
    if ((localList != null) && (localList.size() > 0))
      i = 1;
    return i;
  }

  public static boolean canHandleIntent(Context paramContext, Intent paramIntent)
  {
    int i = 0;
    List localList = paramContext.getPackageManager().queryIntentActivities(paramIntent, 0);
    if ((localList != null) && (localList.size() > 0))
      i = 1;
    return i;
  }

  public static boolean canHandleIntent(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Intent localIntent = new Intent(paramString1);
    if ((paramString2 != null) && (paramString2.length() > 0))
      localIntent.addCategory(paramString2);
    if ((paramString3 != null) && (paramString3.length() > 0))
      localIntent.setData(Uri.parse(paramString3));
    if ((paramString4 != null) && (paramString4.length() > 0))
      localIntent.setPackage(paramString4);
    return canHandleIntent(paramContext, localIntent);
  }

  public static boolean canHandleServiceIntent(Context paramContext, Intent paramIntent)
  {
    int i = 0;
    List localList = paramContext.getPackageManager().queryIntentServices(paramIntent, 0);
    if ((localList != null) && (localList.size() > 0))
      i = 1;
    return i;
  }

  public static boolean canResolveContentProvider(Context paramContext, String paramString)
  {
    int i = 0;
    if (paramContext.getPackageManager().resolveContentProvider(paramString, 0) != null)
      i = 1;
    return i;
  }

  public static int getPackageVersionCode(Context paramContext, String paramString)
  {
    try
    {
      i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = -1;
    }
  }

  public static boolean isAppInstalled(String paramString, int paramInt)
  {
    int i = 0;
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    PackageManager localPackageManager = localContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    Iterator localIterator = localPackageManager.queryIntentActivities(localIntent, 0).iterator();
    while (localIterator.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
      if ((localResolveInfo.activityInfo.packageName == null) || (!localResolveInfo.activityInfo.packageName.equalsIgnoreCase(paramString)))
        continue;
      if (getPackageVersionCode(localContext, paramString) < paramInt)
        break;
      i = 1;
    }
    return i;
  }

  public static boolean isBingInstalled()
  {
    return isAppInstalled("com.microsoft.mobileexperiences.bing", 1);
  }

  public static boolean isGoogleMapsInstalled()
  {
    return isAppInstalled("com.google.android.apps.maps", 4707);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.PackageUtil
 * JD-Core Version:    0.6.0
 */