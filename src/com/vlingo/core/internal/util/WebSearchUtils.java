package com.vlingo.core.internal.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import java.net.URLEncoder;

public class WebSearchUtils
{
  public static final String GOOGLE_NOW_ACTION = "android.intent.action.WEB_SEARCH";
  public static final String GOOGLE_NOW_ACTIVITY = "com.google.android.googlequicksearchbox.SearchActivity";
  public static final String GOOGLE_NOW_APP = "Google Search";
  public static final String GOOGLE_NOW_PACKAGE = "com.google.android.googlequicksearchbox";
  public static final String GOOGLE_NOW_QUERY = "query";
  private static final String WEB_SEARCH_URL_BAIDU_DEFAULT;
  private static final String WEB_SEARCH_URL_BAIDU_HOME_DEFAULT;
  private static final String WEB_SEARCH_URL_BING_DEFAULT;
  private static final String WEB_SEARCH_URL_BING_HOME_DEFAULT;
  private static final String WEB_SEARCH_URL_DAUM_DEFAULT;
  private static final String WEB_SEARCH_URL_DAUM_HOME_DEFAULT;
  private static final String WEB_SEARCH_URL_DEFAULT;
  private static final String WEB_SEARCH_URL_GOOGLE_DEFAULT;
  private static final String WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT;
  private static final String WEB_SEARCH_URL_HOME_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_HOME_DEFAULT);
  private static final String WEB_SEARCH_URL_NAVER_DEFAULT;
  private static final String WEB_SEARCH_URL_NAVER_HOME_DEFAULT;
  private static final String WEB_SEARCH_URL_YAHOO_DEFAULT;
  private static final String WEB_SEARCH_URL_YAHOO_HOME_DEFAULT;

  static
  {
    WEB_SEARCH_URL_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_DEFAULT);
    WEB_SEARCH_URL_BING_HOME_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BING_HOME_DEFAULT);
    WEB_SEARCH_URL_BING_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BING_DEFAULT);
    WEB_SEARCH_URL_YAHOO_HOME_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_YAHOO_HOME_DEFAULT);
    WEB_SEARCH_URL_YAHOO_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_YAHOO_DEFAULT);
    WEB_SEARCH_URL_BAIDU_HOME_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BAIDU_HOME_DEFAULT);
    WEB_SEARCH_URL_BAIDU_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BAIDU_DEFAULT);
    if (ClientSuppliedValues.getConfiguration().mcc == 310);
    for (String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT_US); ; str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT))
    {
      WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT = str;
      WEB_SEARCH_URL_GOOGLE_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_GOOGLE_DEFAULT);
      WEB_SEARCH_URL_NAVER_HOME_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_NAVER_HOME_DEFAULT);
      WEB_SEARCH_URL_NAVER_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_NAVER_DEFAULT);
      WEB_SEARCH_URL_DAUM_HOME_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_DAUM_HOME_DEFAULT);
      WEB_SEARCH_URL_DAUM_DEFAULT = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_DAUM_DEFAULT);
      return;
    }
  }

  public static String getDefaultSearchString(String paramString1, String paramString2)
  {
    String str1 = getDefaultWebSearchEnginName();
    if (StringUtils.isNullOrWhiteSpace(paramString2))
      paramString2 = Settings.getEnum("web_search_engine", str1).toLowerCase();
    String str4;
    if ("Yahoo".equalsIgnoreCase(paramString2))
      if (StringUtils.isNullOrWhiteSpace(paramString1))
        str4 = Settings.getString("web_search_yahoo_default", VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_YAHOO_HOME_DEFAULT));
    while (true)
    {
      if (!StringUtils.isNullOrWhiteSpace(paramString1))
        str4 = str4.replace("{query}", URLEncoder.encode(paramString1));
      return str4;
      str4 = Settings.getString("web_search_yahoo_query", WEB_SEARCH_URL_YAHOO_DEFAULT);
      continue;
      if ("Bing".equalsIgnoreCase(paramString2))
      {
        if (StringUtils.isNullOrWhiteSpace(paramString1))
        {
          str4 = Settings.getString("web_search_bing_default", VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BING_HOME_DEFAULT));
          continue;
        }
        str4 = Settings.getString("web_search_bing_query", WEB_SEARCH_URL_BING_DEFAULT);
        continue;
      }
      if ("Baidu".equalsIgnoreCase(paramString2))
      {
        if (StringUtils.isNullOrWhiteSpace(paramString1))
        {
          str4 = Settings.getString("web_search_biadu_default", VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BAIDU_HOME_DEFAULT));
          continue;
        }
        str4 = Settings.getString("web_search_biadu_query", WEB_SEARCH_URL_BAIDU_DEFAULT);
        continue;
      }
      if (("Google".equalsIgnoreCase(paramString2)) && (!VlingoAndroidCore.isChineseBuild()))
      {
        if (StringUtils.isNullOrWhiteSpace(paramString1))
        {
          str4 = Settings.getString("web_search_google_default", VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT_US));
          continue;
        }
        str4 = Settings.getString("web_search_google_query", WEB_SEARCH_URL_GOOGLE_DEFAULT);
        continue;
      }
      if (("Naver".equalsIgnoreCase(paramString2)) && (!VlingoAndroidCore.isChineseBuild()))
      {
        if (StringUtils.isNullOrWhiteSpace(paramString1))
        {
          str4 = Settings.getString("web_search_naver_default", VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_NAVER_HOME_DEFAULT));
          continue;
        }
        str4 = Settings.getString("web_search_naver_query", WEB_SEARCH_URL_NAVER_DEFAULT);
        continue;
      }
      if (("Daum".equalsIgnoreCase(paramString2)) && (!VlingoAndroidCore.isChineseBuild()))
      {
        if (StringUtils.isNullOrWhiteSpace(paramString1))
        {
          str4 = Settings.getString("web_search_daum_default", VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_DAUM_HOME_DEFAULT));
          continue;
        }
        str4 = Settings.getString("web_search_daum_query", WEB_SEARCH_URL_DAUM_DEFAULT);
        continue;
      }
      String str2 = WEB_SEARCH_URL_HOME_DEFAULT;
      String str3 = WEB_SEARCH_URL_DEFAULT;
      if (StringUtils.isNullOrWhiteSpace(paramString1))
      {
        str4 = Settings.getString("web_search_home_url", str2);
        continue;
      }
      str4 = Settings.getString("web_search_url", str3);
    }
  }

  public static String getDefaultWebSearchEnginName()
  {
    return VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_util_WEB_SEARCH_NAME_DEFAULT);
  }

  public static final void googleNowSearch(String paramString)
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.WEB_SEARCH");
    localIntent.setComponent(new ComponentName("com.google.android.googlequicksearchbox", "com.google.android.googlequicksearchbox.SearchActivity"));
    localIntent.addFlags(268435456);
    localIntent.putExtra("query", paramString);
    localContext.startActivity(localIntent);
  }

  public static boolean isDefaultGoogleSearch()
  {
    if ((!VlingoAndroidCore.isChineseBuild()) && ("Google".equalsIgnoreCase(getDefaultWebSearchEnginName())));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.WebSearchUtils
 * JD-Core Version:    0.6.0
 */