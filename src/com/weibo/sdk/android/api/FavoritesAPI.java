package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class FavoritesAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/favorites";

  public FavoritesAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void byTags(long paramLong, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("tid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/favorites/by_tags.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void byTagsIds(long paramLong, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("tid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/favorites/by_tags/ids.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void create(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong);
    request("https://api.weibo.com/2/favorites/create.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void destroy(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong);
    request("https://api.weibo.com/2/favorites/destroy.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void destroyBatch(long[] paramArrayOfLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfLong.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
        localWeiboParameters.add("ids", localStringBuilder.toString());
        request("https://api.weibo.com/2/favorites/destroy_batch.json", localWeiboParameters, "POST", paramRequestListener);
        return;
      }
      localStringBuilder.append(String.valueOf(paramArrayOfLong[j])).append(",");
    }
  }

  public void favorites(int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/favorites.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void ids(int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/favorites/ids.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void show(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong);
    request("https://api.weibo.com/2/favorites/show.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void tags(int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/favorites/tags.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void tagsDestroyBatch(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("tid", paramLong);
    request("https://api.weibo.com/2/favorites/tags/destroy_batch.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void tagsUpdate(long paramLong, String[] paramArrayOfString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong);
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
        localWeiboParameters.add("tags", localStringBuilder.toString());
        request("https://api.weibo.com/2/favorites/tags/update.json", localWeiboParameters, "POST", paramRequestListener);
        return;
      }
      localStringBuilder.append(paramArrayOfString[j]).append(",");
    }
  }

  public void tagsUpdateBatch(long paramLong, String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("tid", paramLong);
    localWeiboParameters.add("tag", paramString);
    request("https://api.weibo.com/2/favorites/tags/update_batch.json", localWeiboParameters, "POST", paramRequestListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.FavoritesAPI
 * JD-Core Version:    0.6.0
 */