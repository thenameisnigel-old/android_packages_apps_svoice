package com.weibo.sdk.android;

import android.content.Context;
import com.weibo.sdk.android.util.Utility;

public class Weibo
{
  public static final String KEY_EXPIRES = "expires_in";
  public static final String KEY_REFRESHTOKEN = "refresh_token";
  public static final String KEY_TOKEN = "access_token";
  public static String URL_OAUTH2_ACCESS_AUTHORIZE = "https://open.weibo.cn/oauth2/authorize";
  private static final String WEIBO_SDK_VERSION = "2.0";
  public static String app_key;
  public static boolean isWifi;
  private static Weibo mWeiboInstance = null;
  public static String redirecturl;
  public Oauth2AccessToken accessToken = null;

  static
  {
    app_key = "";
    redirecturl = "";
    isWifi = false;
  }

  public static Weibo getInstance(String paramString1, String paramString2)
  {
    monitorenter;
    try
    {
      if (mWeiboInstance == null)
        mWeiboInstance = new Weibo();
      app_key = paramString1;
      redirecturl = paramString2;
      Weibo localWeibo = mWeiboInstance;
      monitorexit;
      return localWeibo;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void authorize(Context paramContext, WeiboAuthListener paramWeiboAuthListener)
  {
    isWifi = Utility.isWifi(paramContext);
    startAuthDialog(paramContext, paramWeiboAuthListener);
  }

  public void setupConsumerConfig(String paramString1, String paramString2)
  {
    app_key = paramString1;
    redirecturl = paramString2;
  }

  public void startAuthDialog(Context paramContext, WeiboAuthListener paramWeiboAuthListener)
  {
    startDialog(paramContext, new WeiboParameters(), new Weibo.1(this, paramWeiboAuthListener));
  }

  public void startDialog(Context paramContext, WeiboParameters paramWeiboParameters, WeiboAuthListener paramWeiboAuthListener)
  {
    paramWeiboParameters.add("client_id", app_key);
    paramWeiboParameters.add("response_type", "token");
    paramWeiboParameters.add("redirect_uri", redirecturl);
    paramWeiboParameters.add("display", "mobile");
    if ((this.accessToken != null) && (this.accessToken.isSessionValid()))
      paramWeiboParameters.add("access_token", this.accessToken.getToken());
    String str = URL_OAUTH2_ACCESS_AUTHORIZE + "?" + Utility.encodeUrl(paramWeiboParameters);
    if (paramContext.checkCallingOrSelfPermission("android.permission.INTERNET") != 0)
      Utility.showAlert(paramContext, "Error", "Application requires permission to access the Internet");
    while (true)
    {
      return;
      new WeiboDialog(paramContext, str, paramWeiboAuthListener).show();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.Weibo
 * JD-Core Version:    0.6.0
 */