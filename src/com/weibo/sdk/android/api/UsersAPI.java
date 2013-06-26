package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class UsersAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/users";

  public UsersAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void counts(long[] paramArrayOfLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfLong.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
        localWeiboParameters.add("uids", localStringBuilder.toString());
        request("https://api.weibo.com/2/users/counts.json", localWeiboParameters, "GET", paramRequestListener);
        return;
      }
      localStringBuilder.append(String.valueOf(paramArrayOfLong[j])).append(",");
    }
  }

  public void domainShow(String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("domain", paramString);
    request("https://api.weibo.com/2/users/domain_show.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void show(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    request("https://api.weibo.com/2/users/show.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void show(String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("screen_name", paramString);
    request("https://api.weibo.com/2/users/show.json", localWeiboParameters, "GET", paramRequestListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.UsersAPI
 * JD-Core Version:    0.6.0
 */