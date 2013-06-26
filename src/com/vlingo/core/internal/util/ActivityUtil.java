package com.vlingo.core.internal.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import java.util.List;

public class ActivityUtil
{
  public static String getActiveActivityProcess(Context paramContext)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
    if ((localList != null) && (localList.size() != 0));
    for (String str = ((ActivityManager.RunningTaskInfo)localList.get(0)).baseActivity.getPackageName(); ; str = null)
      return str;
  }

  public static Intent getIntentForActivity(Context paramContext, String paramString1, String paramString2)
  {
    return getIntentForActivity(paramContext, paramString1, paramString2, false);
  }

  public static Intent getIntentForActivity(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    List localList = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    if ((paramBoolean) || (localList.size() > 0));
    while (true)
    {
      return localIntent;
      localIntent = null;
    }
  }

  public static void runOnMainThread(Runnable paramRunnable)
  {
    if (Looper.myLooper() == Looper.getMainLooper())
      paramRunnable.run();
    while (true)
    {
      return;
      scheduleOnMainThread(paramRunnable);
    }
  }

  public static void scheduleOnMainThread(Runnable paramRunnable)
  {
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }

  public static void scheduleOnMainThread(Runnable paramRunnable, long paramLong)
  {
    new Handler(Looper.getMainLooper()).postDelayed(paramRunnable, paramLong);
  }

  public static void showToast(String paramString, Context paramContext)
  {
    scheduleOnMainThread(new ActivityUtil.1(paramContext, paramString));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ActivityUtil
 * JD-Core Version:    0.6.0
 */