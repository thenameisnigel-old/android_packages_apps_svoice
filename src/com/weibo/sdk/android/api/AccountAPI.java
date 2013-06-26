package com.weibo.sdk.android.api;

import android.text.TextUtils;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class AccountAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/account";

  public AccountAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void endSession(RequestListener paramRequestListener)
  {
    request("https://api.weibo.com/2/account/end_session.json", new WeiboParameters(), "POST", paramRequestListener);
  }

  public void getPrivacy(RequestListener paramRequestListener)
  {
    request("https://api.weibo.com/2/account/get_privacy.json", new WeiboParameters(), "GET", paramRequestListener);
  }

  public void getUid(RequestListener paramRequestListener)
  {
    request("https://api.weibo.com/2/account/get_uid.json", new WeiboParameters(), "GET", paramRequestListener);
  }

  public void rateLimitStatus(RequestListener paramRequestListener)
  {
    request("https://api.weibo.com/2/account/rate_limit_status.json", new WeiboParameters(), "GET", paramRequestListener);
  }

  public void schoolList(int paramInt1, int paramInt2, int paramInt3, WeiboAPI.SCHOOL_TYPE paramSCHOOL_TYPE, WeiboAPI.CAPITAL paramCAPITAL, String paramString, int paramInt4, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("province", paramInt1);
    localWeiboParameters.add("city", paramInt2);
    localWeiboParameters.add("area", paramInt3);
    localWeiboParameters.add("type", 1 + paramSCHOOL_TYPE.ordinal());
    if (!TextUtils.isEmpty(paramCAPITAL.name()))
      localWeiboParameters.add("capital", paramCAPITAL.name());
    while (true)
    {
      localWeiboParameters.add("count", paramInt4);
      request("https://api.weibo.com/2/account/profile/school_list.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      if (TextUtils.isEmpty(paramString))
        continue;
      localWeiboParameters.add("keyword", paramString);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.AccountAPI
 * JD-Core Version:    0.6.0
 */