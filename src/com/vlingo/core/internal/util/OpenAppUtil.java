package com.vlingo.core.internal.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.vlingo.core.internal.logging.Logger;
import com.vlingo.core.internal.settings.Settings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class OpenAppUtil
{
  private static Logger log = Logger.getLogger(OpenAppUtil.class);
  private static Pattern p = Pattern.compile("(?<=[^\\p{Upper}])(?=\\p{Upper})|\\s");
  protected List<AppInfo> appInfoList;

  private void createAppList()
  {
    PackageManager localPackageManager = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    List localList = localPackageManager.queryIntentActivities(localIntent, 0);
    this.appInfoList = new ArrayList(localList.size());
    Configuration localConfiguration1 = new Configuration();
    String[] arrayOfString = Settings.getISOLanguage().split("-");
    localConfiguration1.locale = new Locale(arrayOfString[0], arrayOfString[1]);
    Configuration localConfiguration2 = new Configuration();
    localConfiguration2.locale = Locale.ENGLISH;
    float f = ApplicationAdapter.getInstance().getApplicationContext().getResources().getConfiguration().fontScale;
    localConfiguration2.fontScale = f;
    localConfiguration1.fontScale = f;
    Iterator localIterator = localList.iterator();
    ResolveInfo localResolveInfo;
    int i;
    if (localIterator.hasNext())
    {
      localResolveInfo = (ResolveInfo)localIterator.next();
      i = localResolveInfo.activityInfo.labelRes;
      String str1 = (String)localResolveInfo.activityInfo.nonLocalizedLabel;
      if ((i == 0) && (str1 == null))
      {
        i = localResolveInfo.activityInfo.applicationInfo.labelRes;
        String str4 = (String)localResolveInfo.activityInfo.applicationInfo.nonLocalizedLabel;
        if ((i != 0) || (str4 != null));
      }
    }
    while (true)
    {
      String str2;
      String str3;
      try
      {
        Resources localResources = localPackageManager.getResourcesForApplication(localResolveInfo.activityInfo.applicationInfo.packageName);
        localResources.updateConfiguration(localConfiguration2, null);
        str2 = localResolveInfo.loadLabel(localPackageManager).toString().toLowerCase().replaceAll("\\W", "");
        str3 = "";
        if (i == 0)
          break label379;
        localResources.updateConfiguration(localConfiguration1, null);
        str3 = localResources.getString(i);
        break label379;
        this.appInfoList.add(new AppInfo(str2, str3, localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        break;
        return;
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
      }
      break;
      label379: if (str2 != null)
        continue;
      if (str3 == null)
        break;
    }
  }

  public void buildMatchingAppList(String paramString)
  {
    createAppList();
    Object localObject = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString1;
    int i;
    String str2;
    int j;
    AppInfo localAppInfo;
    String str3;
    String str4;
    int k;
    if (paramString != null)
    {
      arrayOfString1 = p.split(paramString);
      String str1 = cleanAppName(paramString);
      i = str1.length();
      str2 = str1.trim().toLowerCase();
      Iterator localIterator = this.appInfoList.iterator();
      if (localIterator.hasNext())
      {
        j = 0;
        localAppInfo = (AppInfo)localIterator.next();
        str3 = cleanAppName(localAppInfo.appName);
        str4 = cleanAppName(localAppInfo.appNameLabel);
        k = str3.length();
        if ((str3.equals(str2)) || (str4.equals(str2)))
        {
          ((List)localObject).clear();
          ((List)localObject).add(localAppInfo);
          this.appInfoList.clear();
          this.appInfoList.addAll((Collection)localObject);
        }
      }
    }
    while (true)
    {
      return;
      Pattern localPattern = p;
      String str5;
      String[] arrayOfString2;
      int m;
      if (!StringUtils.isNullOrWhiteSpace(localAppInfo.appNameLabel))
      {
        str5 = localAppInfo.appNameLabel;
        arrayOfString2 = localPattern.split(str5);
        m = arrayOfString2.length;
      }
      for (int n = 0; ; n++)
      {
        if (n >= m)
          break label325;
        String str6 = arrayOfString2[n];
        int i1 = arrayOfString1.length;
        int i2 = 0;
        while (true)
          if (i2 < i1)
          {
            String str7 = arrayOfString1[i2];
            if ((j == 0) && (!StringUtils.isNullOrWhiteSpace(str6)) && (!StringUtils.isNullOrWhiteSpace(str7)) && (str6.toLowerCase().equals(str7.toLowerCase())))
            {
              ((List)localObject).add(localAppInfo);
              j = 1;
            }
            i2++;
            continue;
            str5 = localAppInfo.appName;
            break;
          }
      }
      label325: if ((j != 0) || (i * 10 / k < 4) || ((!str3.contains(str2)) && (!str4.contains(str2))))
        break;
      localArrayList.add(localAppInfo);
      break;
      if ((((List)localObject).isEmpty()) && (!localArrayList.isEmpty()))
        localObject = localArrayList;
      Collections.sort((List)localObject);
      this.appInfoList.clear();
      this.appInfoList.addAll((Collection)localObject);
    }
  }

  protected String cleanAppName(String paramString)
  {
    return paramString.toLowerCase().replaceAll("\\W", "");
  }

  public List<AppInfo> getAppInfoList()
  {
    return this.appInfoList;
  }

  public class AppInfo
    implements Comparable<AppInfo>
  {
    private final String activityName;
    private final String appName;
    private final String appNameLabel;
    private final String packageName;

    public AppInfo(String paramString1, String paramString2, String paramString3, String arg5)
    {
      if (paramString1 == null)
        paramString1 = "";
      this.appName = paramString1;
      this.packageName = paramString3;
      Object localObject;
      this.activityName = localObject;
      if (paramString2 == null)
        paramString2 = "";
      this.appNameLabel = paramString2;
    }

    public int compareTo(AppInfo paramAppInfo)
    {
      return this.appName.compareTo(paramAppInfo.appName);
    }

    public String getActivityName()
    {
      return this.activityName;
    }

    public Intent getLaunchIntent()
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName(this.packageName, this.activityName));
      localIntent.setFlags(268435456);
      return localIntent;
    }

    public String getPackageName()
    {
      return this.packageName;
    }

    public String toString()
    {
      if (this.appNameLabel.equals(""));
      for (String str = this.appName; ; str = this.appNameLabel)
        return str;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.OpenAppUtil
 * JD-Core Version:    0.6.0
 */