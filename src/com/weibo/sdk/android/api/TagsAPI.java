package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class TagsAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/tags";

  public TagsAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void create(String[] paramArrayOfString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
        localWeiboParameters.add("tags", localStringBuilder.toString());
        request("https://api.weibo.com/2/tags/create.json", localWeiboParameters, "POST", paramRequestListener);
        return;
      }
      localStringBuilder.append(paramArrayOfString[j]).append(",");
    }
  }

  public void destroy(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("tag_id", paramLong);
    request("https://api.weibo.com/2/tags/destroy.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void destroyBatch(String[] paramArrayOfString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
        localWeiboParameters.add("ids", localStringBuilder.toString());
        request("https://api.weibo.com/2/tags/destroy_batch.json", localWeiboParameters, "POST", paramRequestListener);
        return;
      }
      localStringBuilder.append(paramArrayOfString[j]).append(",");
    }
  }

  public void suggestions(int paramInt, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt);
    request("https://api.weibo.com/2/tags/suggestions.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void tags(long paramLong, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/tags.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void tagsBatch(String[] paramArrayOfString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
        localWeiboParameters.add("uids", localStringBuilder.toString());
        request("https://api.weibo.com/2/tags/tags_batch.json", localWeiboParameters, "GET", paramRequestListener);
        return;
      }
      localStringBuilder.append(paramArrayOfString[j]).append(",");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.TagsAPI
 * JD-Core Version:    0.6.0
 */