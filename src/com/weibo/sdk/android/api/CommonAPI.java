package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class CommonAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/common";

  public CommonAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void getCity(String paramString1, WeiboAPI.CAPITAL paramCAPITAL, String paramString2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("province", paramString1);
    if (paramCAPITAL != null)
      localWeiboParameters.add("capital", paramCAPITAL.name().toLowerCase());
    localWeiboParameters.add("language", paramString2);
    request("https://api.weibo.com/2/common/get_city.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void getCountry(WeiboAPI.CAPITAL paramCAPITAL, String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    if (paramCAPITAL != null)
      localWeiboParameters.add("capital", paramCAPITAL.name().toLowerCase());
    localWeiboParameters.add("language", paramString);
    request("https://api.weibo.com/2/common/get_country.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void getTimezone(String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("language", paramString);
    request("https://api.weibo.com/2/common/get_timezone.json", localWeiboParameters, "GET", paramRequestListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.CommonAPI
 * JD-Core Version:    0.6.0
 */