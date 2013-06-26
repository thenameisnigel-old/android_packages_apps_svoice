package com.vlingo.midas;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.midas.settings.MidasSettings;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public enum ClientConfiguration
{
  public static final int ANSWERS = 5;
  public static final int AUTO_PUNCTUATION = 16;
  private static final String HOME_KEY_DOUBLE = "1";
  public static final int IN_CAR = 2;
  public static final int LOCALSEARCH = 10;
  public static final int MOVIES = 6;
  public static final int NAVER = 15;
  private static final String PROPERTY_KEY = "ro.vlingo.launch.key";
  public static final int SAFEREADER = 1;
  private static final String SEARCH_KEY_LONG = "2";
  public static final int SOCIAL = 14;
  public static final int SUPERDIALER = 9;
  public static final int TRAVEL = 8;
  private Set<String> supportedLanguages = new HashSet();

  static
  {
    ClientConfiguration[] arrayOfClientConfiguration = new ClientConfiguration[1];
    arrayOfClientConfiguration[0] = LMTT_NOSPACES;
    $VALUES = arrayOfClientConfiguration;
  }

  private ClientConfiguration(Set<String> paramSet)
  {
    this.supportedLanguages.addAll(paramSet);
  }

  public static String getDefaultBrowser()
  {
    Resources localResources = ApplicationAdapter.getInstance().getApplicationContext().getResources();
    MidasSettings.updateCurrentLocale(localResources);
    String[] arrayOfString = localResources.getStringArray(2131165190);
    if ((arrayOfString != null) && (arrayOfString.length > 0));
    for (String str = arrayOfString[0]; ; str = "google")
      return str;
  }

  public static boolean isChatONVPhone()
  {
    int i = 1;
    try
    {
      Class localClass = ApplicationAdapter.getInstance().getApplicationContext().getClassLoader().loadClass("android.os.SystemProperties");
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = String.class;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = new String("ro.csc.sales_code");
      String str = (String)localClass.getMethod("get", arrayOfClass).invoke(localClass, arrayOfObject);
      if ((!str.equals("VZW")) && (!str.equals("SPR")) && (!str.equals("CHN")) && (!str.equals("CHM")) && (!str.equals("CHU")) && (!str.equals("CTC")) && (!str.equals("KTT")) && (!str.equals("LGT")) && (!str.equals("SKT")))
      {
        boolean bool = str.equals("DCM");
        if (!bool);
      }
      else
      {
        i = 0;
      }
      label170: return i;
    }
    catch (Exception localException)
    {
      break label170;
    }
  }

  public static boolean isChineseBuild()
  {
    return "Preinstall Free China".equalsIgnoreCase("Preinstall Free");
  }

  public static boolean isChinesePhone()
  {
    int i = 0;
    try
    {
      Class localClass = ApplicationAdapter.getInstance().getApplicationContext().getClassLoader().loadClass("android.os.SystemProperties");
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = String.class;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = new String("ro.csc.sales_code");
      String str = (String)localClass.getMethod("get", arrayOfClass).invoke(localClass, arrayOfObject);
      if ((!str.equals("CHN")) && (!str.equals("CHM")) && (!str.equals("CHU")))
      {
        boolean bool = str.equals("CTC");
        if (!bool);
      }
      else
      {
        i = 1;
      }
      label110: return i;
    }
    catch (Exception localException)
    {
      break label110;
    }
  }

  public static boolean isChineseTestBuild()
  {
    return "Preinstall Free China Dev".equalsIgnoreCase("Preinstall Free");
  }

  public static boolean isEnabled(int paramInt)
  {
    boolean bool = false;
    switch (paramInt)
    {
    default:
    case 5:
    case 10:
    case 15:
    }
    while (true)
    {
      return bool;
      if ((Settings.getString("language", "en-US").equals("en-US")) || (Settings.getString("language", "en-US").equals("en-GB")))
      {
        bool = true;
        continue;
      }
      bool = false;
      continue;
      bool = Settings.getString("language", "en-US").equals("en-US");
      if (!bool)
        bool = Settings.getString("language", "ko-KR").equals("ko-KR");
      if (bool)
        continue;
      bool = Settings.getString("language", "zh-CN").equals("zh-CN");
      continue;
      bool = Settings.getString("language", "ko-KR").equals("ko-KR");
    }
  }

  public static boolean isVisible(int paramInt)
  {
    boolean bool = isEnabled(paramInt);
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return bool;
      bool = true;
      continue;
      bool = true;
    }
  }

  public static boolean useSearchLongPress()
  {
    int i = 0;
    try
    {
      Class localClass = Class.forName("android.os.SystemProperties");
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = String.class;
      arrayOfClass[1] = String.class;
      Method localMethod = localClass.getMethod("get", arrayOfClass);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = "ro.vlingo.launch.key";
      arrayOfObject[1] = "1";
      boolean bool = "2".equals((String)localMethod.invoke(localClass, arrayOfObject));
      i = bool;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("VLG_EXCEPTION", localException.toString() + "\r\n" + Log.getStackTraceString(localException));
        Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException));
      }
    }
  }

  public boolean isSupported()
  {
    return this.supportedLanguages.contains(MidasSettings.getLanguageApplication().toLowerCase(MidasSettings.getCurrentLocale()));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.ClientConfiguration
 * JD-Core Version:    0.6.0
 */