package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class PlaceAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/place";

  public PlaceAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void friendsTimeline(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("type", 0);
    while (true)
    {
      request("https://api.weibo.com/2/place/friends_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("type", 1);
    }
  }

  public void nearbyPhotos(String paramString1, String paramString2, int paramInt1, long paramLong1, long paramLong2, WeiboAPI.SORT3 paramSORT3, int paramInt2, int paramInt3, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("lat", paramString1);
    localWeiboParameters.add("long", paramString2);
    localWeiboParameters.add("range", paramInt1);
    localWeiboParameters.add("starttime", paramLong1);
    localWeiboParameters.add("endtime", paramLong2);
    localWeiboParameters.add("sort", paramSORT3.ordinal());
    localWeiboParameters.add("count", paramInt2);
    localWeiboParameters.add("page", paramInt3);
    if (paramBoolean)
      localWeiboParameters.add("offset", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/nearby/photos.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("offset", 0);
    }
  }

  public void nearbyPois(String paramString1, String paramString2, int paramInt1, String paramString3, String paramString4, int paramInt2, int paramInt3, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("lat", paramString1);
    localWeiboParameters.add("long", paramString2);
    localWeiboParameters.add("range", paramInt1);
    localWeiboParameters.add("q", paramString3);
    localWeiboParameters.add("category", paramString4);
    localWeiboParameters.add("count", paramInt2);
    localWeiboParameters.add("page", paramInt3);
    if (paramBoolean)
      localWeiboParameters.add("offset", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/nearby/pois.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("offset", 0);
    }
  }

  public void nearbyTimeline(String paramString1, String paramString2, int paramInt1, long paramLong1, long paramLong2, WeiboAPI.SORT3 paramSORT3, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("lat", paramString1);
    localWeiboParameters.add("long", paramString2);
    localWeiboParameters.add("range", paramInt1);
    localWeiboParameters.add("starttime", paramLong1);
    localWeiboParameters.add("endtime", paramLong2);
    localWeiboParameters.add("sort", paramSORT3.ordinal());
    localWeiboParameters.add("count", paramInt2);
    localWeiboParameters.add("page", paramInt3);
    if (paramBoolean1)
    {
      localWeiboParameters.add("base_app", 1);
      if (!paramBoolean2)
        break label131;
      localWeiboParameters.add("offset", 1);
    }
    while (true)
    {
      request("https://api.weibo.com/2/place/nearby_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
      break;
      label131: localWeiboParameters.add("offset", 0);
    }
  }

  public void nearbyUsers(String paramString1, String paramString2, int paramInt1, long paramLong1, long paramLong2, WeiboAPI.SORT3 paramSORT3, int paramInt2, int paramInt3, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("lat", paramString1);
    localWeiboParameters.add("long", paramString2);
    localWeiboParameters.add("range", paramInt1);
    localWeiboParameters.add("starttime", paramLong1);
    localWeiboParameters.add("endtime", paramLong2);
    localWeiboParameters.add("sort", paramSORT3.ordinal());
    localWeiboParameters.add("count", paramInt2);
    localWeiboParameters.add("page", paramInt3);
    if (paramBoolean)
      localWeiboParameters.add("offset", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/nearby/users.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("offset", 0);
    }
  }

  public void poiTimeline(String paramString, long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("poiid", paramString);
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 0);
    while (true)
    {
      request("https://api.weibo.com/2/place/poi_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 1);
    }
  }

  public void poisAddCheckin(String paramString1, String paramString2, String paramString3, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("poiid", paramString1);
    localWeiboParameters.add("status", paramString2);
    localWeiboParameters.add("pic", paramString3);
    if (paramBoolean)
      localWeiboParameters.add("public", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/pois/add_checkin.json", localWeiboParameters, "POST", paramRequestListener);
      return;
      localWeiboParameters.add("public", 0);
    }
  }

  public void poisAddPhoto(String paramString1, String paramString2, String paramString3, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("poiid", paramString1);
    localWeiboParameters.add("status", paramString2);
    localWeiboParameters.add("pic", paramString3);
    if (paramBoolean)
      localWeiboParameters.add("public", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/pois/add_photo.json", localWeiboParameters, "POST", paramRequestListener);
      return;
      localWeiboParameters.add("public", 0);
    }
  }

  public void poisAddTip(String paramString1, String paramString2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("poiid", paramString1);
    localWeiboParameters.add("status", paramString2);
    if (paramBoolean)
      localWeiboParameters.add("public", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/pois/add_tip.json", localWeiboParameters, "POST", paramRequestListener);
      return;
      localWeiboParameters.add("public", 0);
    }
  }

  public void poisCategory(int paramInt, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("pid", paramInt);
    if (paramBoolean)
      localWeiboParameters.add("flag", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/pois/category.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("flag", 0);
    }
  }

  public void poisPhotos(String paramString, int paramInt1, int paramInt2, WeiboAPI.SORT2 paramSORT2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramString);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("sort", paramSORT2.ordinal());
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/pois/photos.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void poisSearch(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("keyword", paramString1);
    localWeiboParameters.add("city", paramString2);
    localWeiboParameters.add("category", paramString3);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/place/pois/search.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void poisShow(String paramString, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("poiid", paramString);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/pois/show.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void poisTips(String paramString, int paramInt1, int paramInt2, WeiboAPI.SORT2 paramSORT2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("poiid", paramString);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("sort", paramSORT2.ordinal());
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/pois/tips.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void poisUsers(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("poiid", paramString);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/pois/users.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void statusesShow(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong);
    request("https://api.weibo.com/2/place/statuses/show.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void userTimeline(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong1);
    localWeiboParameters.add("since_id", paramLong2);
    localWeiboParameters.add("max_id", paramLong3);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/user_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void usersCheckins(long paramLong, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/users/checkins.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void usersPhotos(long paramLong, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/users/photos.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void usersShow(long paramLong, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/users/show.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void usersTips(long paramLong, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/place/users/tips.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.PlaceAPI
 * JD-Core Version:    0.6.0
 */