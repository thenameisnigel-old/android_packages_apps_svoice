package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class SearchAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/search";

  public SearchAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void apps(String paramString, int paramInt, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("q", paramString);
    localWeiboParameters.add("count", paramInt);
    request("https://api.weibo.com/2/search/suggestions/apps.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void atUsers(String paramString, int paramInt, WeiboAPI.FRIEND_TYPE paramFRIEND_TYPE, WeiboAPI.RANGE paramRANGE, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("q", paramString);
    localWeiboParameters.add("count", paramInt);
    localWeiboParameters.add("type", paramFRIEND_TYPE.ordinal());
    localWeiboParameters.add("range", paramRANGE.ordinal());
    request("https://api.weibo.com/2/search/suggestions/at_users.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void companies(String paramString, int paramInt, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("q", paramString);
    localWeiboParameters.add("count", paramInt);
    request("https://api.weibo.com/2/search/suggestions/companies.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void schools(String paramString, int paramInt, WeiboAPI.SCHOOL_TYPE paramSCHOOL_TYPE, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("q", paramString);
    localWeiboParameters.add("count", paramInt);
    localWeiboParameters.add("type", paramSCHOOL_TYPE.ordinal());
    request("https://api.weibo.com/2/search/suggestions/schools.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void statuses(String paramString, int paramInt, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("q", paramString);
    localWeiboParameters.add("count", paramInt);
    request("https://api.weibo.com/2/search/suggestions/statuses.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void users(String paramString, int paramInt, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("q", paramString);
    localWeiboParameters.add("count", paramInt);
    request("https://api.weibo.com/2/search/suggestions/users.json", localWeiboParameters, "GET", paramRequestListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.SearchAPI
 * JD-Core Version:    0.6.0
 */