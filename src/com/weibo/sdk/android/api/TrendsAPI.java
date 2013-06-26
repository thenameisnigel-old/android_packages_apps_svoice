package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class TrendsAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/trends";

  public TrendsAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void daily(boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/trends/daily.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void destroy(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("trend_id", paramLong);
    request("https://api.weibo.com/2/trends/destroy.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void follow(String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("trend_name", paramString);
    request("https://api.weibo.com/2/trends/follow.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void hourly(boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/trends/hourly.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void isFollow(String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("trend_name", paramString);
    request("https://api.weibo.com/2/trends/is_follow.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void trends(long paramLong, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/trends.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void weekly(boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    if (paramBoolean)
      localWeiboParameters.add("base_app", 0);
    while (true)
    {
      request("https://api.weibo.com/2/trends/weekly.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 1);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.TrendsAPI
 * JD-Core Version:    0.6.0
 */