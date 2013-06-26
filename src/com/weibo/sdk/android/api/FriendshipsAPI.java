package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class FriendshipsAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/friendships";

  public FriendshipsAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void bilateral(long paramLong, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/friendships/friends/bilateral.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void bilateralIds(long paramLong, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/friendships/friends/bilateral/ids.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void chainFollowers(long paramLong, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/friendships/friends_chain/followers.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void create(long paramLong, String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("screen_name", paramString);
    request("https://api.weibo.com/2/friendships/create.json", localWeiboParameters, "POST", paramRequestListener);
  }

  @Deprecated
  public void create(String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("screen_name", paramString);
    request("https://api.weibo.com/2/friendships/create.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void destroy(long paramLong, String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("screen_name", paramString);
    request("https://api.weibo.com/2/friendships/destroy.json", localWeiboParameters, "POST", paramRequestListener);
  }

  @Deprecated
  public void destroy(String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("screen_name", paramString);
    request("https://api.weibo.com/2/friendships/destroy.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void followers(long paramLong, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("cursor", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("trim_status", 0);
    while (true)
    {
      request("https://api.weibo.com/2/friendships/followers.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("trim_status", 1);
    }
  }

  public void followers(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("screen_name", paramString);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("cursor", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("trim_status", 0);
    while (true)
    {
      request("https://api.weibo.com/2/friendships/followers.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("trim_status", 1);
    }
  }

  public void followersActive(long paramLong, int paramInt, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt);
    request("https://api.weibo.com/2/friendships/followers/active.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void followersIds(long paramLong, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("cursor", paramInt2);
    request("https://api.weibo.com/2/friendships/followers/ids.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void followersIds(String paramString, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("screen_name", paramString);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("cursor", paramInt2);
    request("https://api.weibo.com/2/friendships/followers/ids.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void friends(long paramLong, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("cursor", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("trim_status", 1);
    while (true)
    {
      request("https://api.weibo.com/2/friendships/friends.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("trim_status", 0);
    }
  }

  public void friends(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("screen_name", paramString);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("cursor", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("trim_status", 0);
    while (true)
    {
      request("https://api.weibo.com/2/friendships/friends.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("trim_status", 1);
    }
  }

  public void friendsIds(long paramLong, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("cursor", paramInt2);
    request("https://api.weibo.com/2/friendships/friends/ids.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void friendsIds(String paramString, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("screen_name", paramString);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("cursor", paramInt2);
    request("https://api.weibo.com/2/friendships/friends/ids.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void inCommon(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong1);
    localWeiboParameters.add("suid", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("trim_status", 1);
    while (true)
    {
      request("https://api.weibo.com/2/friendships/friends/in_common.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("trim_status", 0);
    }
  }

  public void show(long paramLong1, long paramLong2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("source_id", paramLong1);
    localWeiboParameters.add("target_id", paramLong2);
    request("https://api.weibo.com/2/friendships/show.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void show(long paramLong, String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("source_id", paramLong);
    localWeiboParameters.add("target_screen_name", paramString);
    request("https://api.weibo.com/2/friendships/show.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void show(String paramString, long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("source_screen_name", paramString);
    localWeiboParameters.add("target_id", paramLong);
    request("https://api.weibo.com/2/friendships/show.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void show(String paramString1, String paramString2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("target_screen_name", paramString2);
    localWeiboParameters.add("source_screen_name", paramString1);
    request("https://api.weibo.com/2/friendships/show.json", localWeiboParameters, "GET", paramRequestListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.FriendshipsAPI
 * JD-Core Version:    0.6.0
 */