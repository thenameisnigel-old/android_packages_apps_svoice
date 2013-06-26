package com.vlingo.midas.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.vlingo.core.internal.util.CorePackageInfoProvider;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import java.util.List;

public class PackageInfoProvider extends CorePackageInfoProvider
{
  public static final String[] ALARM;
  public static final String[] EMAIL;
  public static final String[] EVENT;
  public static final String[] MAP;
  public static final String[] MEMO;
  public static final String[] MESSAGING;
  public static final String[] MUSIC;
  public static final String[] NAV;
  public static final String[] RADIO;
  public static final String[] TASK;
  public static final String[] TIMER;
  public static final String[] VOICERECORDER;
  public static final String[] WORLDCLOCK_TAB;
  private Context mContext;
  List<ApplicationInfo> mInstalledPkgList;
  private int mRadioText;
  private Drawable memoDrawable = null;
  private Drawable messageDrawable = null;
  private Drawable naviDrawable = null;
  private Drawable plannerDrawable = null;
  private Drawable weatherDrawable = null;

  static
  {
    String[] arrayOfString1 = new String[3];
    arrayOfString1[0] = "com.sec.android.app.memo";
    arrayOfString1[1] = "com.sec.android.widgetapp.diotek.smemo";
    arrayOfString1[2] = "com.sec.android.app.snotebook";
    MEMO = arrayOfString1;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = "com.google.android.apps.maps";
    NAV = arrayOfString2;
    String[] arrayOfString3 = new String[1];
    arrayOfString3[0] = "com.google.android.apps.maps";
    MAP = arrayOfString3;
    String[] arrayOfString4 = new String[1];
    arrayOfString4[0] = "com.sec.android.app.clockpackage";
    ALARM = arrayOfString4;
    String[] arrayOfString5 = new String[1];
    arrayOfString5[0] = "com.android.email";
    EMAIL = arrayOfString5;
    String[] arrayOfString6 = new String[2];
    arrayOfString6[0] = "com.android.calendar";
    arrayOfString6[1] = "com.nttdocomo.android.schedulememo";
    EVENT = arrayOfString6;
    String[] arrayOfString7 = new String[1];
    arrayOfString7[0] = "com.android.calendar";
    TASK = arrayOfString7;
    String[] arrayOfString8 = new String[2];
    arrayOfString8[0] = "com.sec.android.app.music";
    arrayOfString8[1] = "com.samsung.music";
    MUSIC = arrayOfString8;
    String[] arrayOfString9 = new String[1];
    arrayOfString9[0] = "com.sec.android.app.fm";
    RADIO = arrayOfString9;
    String[] arrayOfString10 = new String[1];
    arrayOfString10[0] = "com.sec.android.app.voicerecorder";
    VOICERECORDER = arrayOfString10;
    String[] arrayOfString11 = new String[2];
    arrayOfString11[0] = "com.sec.android.app.clockpackage.timer";
    arrayOfString11[1] = "com.sec.android.app.clockpackage";
    TIMER = arrayOfString11;
    String[] arrayOfString12 = new String[1];
    arrayOfString12[0] = "com.android.mms";
    MESSAGING = arrayOfString12;
    String[] arrayOfString13 = new String[1];
    arrayOfString13[0] = "com.sec.android.app.worldclock";
    WORLDCLOCK_TAB = arrayOfString13;
  }

  public PackageInfoProvider()
  {
  }

  public PackageInfoProvider(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static boolean hasAlarm()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = ALARM;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasEvent()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = EVENT;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasMaps()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = MAP;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasMemo()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = MEMO;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasMessaging()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = MESSAGING;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasMusic()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = MUSIC;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasNav()
  {
    int i = 1;
    Object localObject = null;
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    String[] arrayOfString = NAV;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ComponentName localComponentName = new ComponentName(str, "com.google.android.maps.driveabout.app.DestinationActivity");
        ActivityInfo localActivityInfo2 = localContext.getPackageManager().getActivityInfo(localComponentName, 128);
        localActivityInfo1 = localActivityInfo2;
        if ((localObject != null) || (localActivityInfo1 != null))
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        ActivityInfo localActivityInfo1 = null;
        try
        {
          ApplicationInfo localApplicationInfo = localContext.getPackageManager().getApplicationInfo("com.autonavi.xmgd.navigator.keyboard", 128);
          localObject = localApplicationInfo;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2)
        {
          localObject = null;
        }
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasRadio()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = RADIO;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasTask()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = TASK;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasTimer()
  {
    int i = 1;
    ApplicationInfo localApplicationInfo = null;
    String[] arrayOfString = TIMER;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        if (localApplicationInfo == null)
          continue;
        boolean bool = hasWorldClock();
        if (!bool)
          continue;
        i = 0;
        return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localApplicationInfo = null;
        if (localApplicationInfo != null)
          continue;
        k++;
      }
      break;
      if (localApplicationInfo != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasVoiceRecorder()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = VOICERECORDER;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public static boolean hasWorldClock()
  {
    int i = 1;
    Object localObject = null;
    String[] arrayOfString = WORLDCLOCK_TAB;
    int j = arrayOfString.length;
    int k = 0;
    String str;
    if (k < j)
      str = arrayOfString[k];
    while (true)
    {
      try
      {
        ApplicationInfo localApplicationInfo = ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getApplicationInfo(str, 128);
        localObject = localApplicationInfo;
        if (localObject != null)
          return i;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localObject = null;
        continue;
        k++;
      }
      break;
      if (localObject != null)
        continue;
      i = 0;
    }
  }

  public Drawable getMemoIcon()
  {
    return this.memoDrawable;
  }

  public Drawable getMessageIcon()
  {
    return this.messageDrawable;
  }

  public Drawable getNavigationIcon()
  {
    return this.naviDrawable;
  }

  public Drawable getPlannerIcon()
  {
    return this.plannerDrawable;
  }

  public int getRadioText()
  {
    return this.mRadioText;
  }

  public Drawable getWeatherIcon()
  {
    return this.weatherDrawable;
  }

  public boolean hasPenFeature()
  {
    return this.mContext.getPackageManager().hasSystemFeature("com.sec.feature.spen_usp");
  }

  public void setMemoIcon()
  {
    this.memoDrawable = this.mContext.getResources().getDrawable(2130837846);
  }

  public void setMessageIcon()
  {
    this.messageDrawable = this.mContext.getResources().getDrawable(2130837843);
  }

  public void setNavigationIcon()
  {
    this.naviDrawable = this.mContext.getResources().getDrawable(2130837803);
  }

  public void setPlannerIcon()
  {
    this.plannerDrawable = this.mContext.getResources().getDrawable(2130837847);
  }

  public void setRadioText()
  {
    if (hasRadio());
    for (this.mRadioText = 2131165205; ; this.mRadioText = 2131165206)
      return;
  }

  public void setWeatherIcon()
  {
    this.weatherDrawable = this.mContext.getResources().getDrawable(2130837808);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.ui.PackageInfoProvider
 * JD-Core Version:    0.6.0
 */