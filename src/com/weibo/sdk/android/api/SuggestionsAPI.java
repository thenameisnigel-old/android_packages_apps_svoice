package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class SuggestionsAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/suggestions";

  public SuggestionsAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void byStatus(String paramString, int paramInt, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("content", paramString);
    localWeiboParameters.add("num", paramInt);
    request("https://api.weibo.com/2/suggestions/users/may_interested.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void favoritesHot(int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/suggestions/favorites/hot.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void mayInterested(int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/suggestions/users/may_interested.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void notInterested(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    request("https://api.weibo.com/2/suggestions/users/not_interested.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void statusesHot(WeiboAPI.STATUSES_TYPE paramSTATUSES_TYPE, boolean paramBoolean, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("type", 1 + paramSTATUSES_TYPE.ordinal());
    if (paramBoolean)
      localWeiboParameters.add("is_pic", 1);
    while (true)
    {
      localWeiboParameters.add("count", paramInt1);
      localWeiboParameters.add("page", paramInt2);
      request("https://api.weibo.com/2/suggestions/statuses/hot.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("is_pic", 0);
    }
  }

  public void usersHot(WeiboAPI.USER_CATEGORY paramUSER_CATEGORY, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("category", paramUSER_CATEGORY.name());
    request("https://api.weibo.com/2/suggestions/users/hot.json", localWeiboParameters, "GET", paramRequestListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.SuggestionsAPI
 * JD-Core Version:    0.6.0
 */