package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class CommentsAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/comments";

  public CommentsAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void byME(long paramLong1, long paramLong2, int paramInt1, int paramInt2, WeiboAPI.SRC_FILTER paramSRC_FILTER, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("filter_by_source", paramSRC_FILTER.ordinal());
    request("https://api.weibo.com/2/comments/by_me.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void create(String paramString, long paramLong, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("comment", paramString);
    localWeiboParameters.add("id", paramLong);
    if (paramBoolean)
      localWeiboParameters.add("comment_ori", 0);
    while (true)
    {
      request("https://api.weibo.com/2/comments/create.json", localWeiboParameters, "POST", paramRequestListener);
      return;
      localWeiboParameters.add("comment_ori", 1);
    }
  }

  public void destroy(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("cid", paramLong);
    request("https://api.weibo.com/2/comments/destroy.json", localWeiboParameters, "POST", paramRequestListener);
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
        request("https://api.weibo.com/2/comments/sdestroy_batch.json", localWeiboParameters, "POST", paramRequestListener);
        return;
      }
      localStringBuilder.append(String.valueOf(paramArrayOfLong[j])).append(",");
    }
  }

  public void mentions(long paramLong1, long paramLong2, int paramInt1, int paramInt2, WeiboAPI.AUTHOR_FILTER paramAUTHOR_FILTER, WeiboAPI.SRC_FILTER paramSRC_FILTER, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("filter_by_author", paramAUTHOR_FILTER.ordinal());
    localWeiboParameters.add("filter_by_source", paramSRC_FILTER.ordinal());
    request("https://api.weibo.com/2/comments/mentions.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void reply(long paramLong1, long paramLong2, String paramString, boolean paramBoolean1, boolean paramBoolean2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("cid", paramLong1);
    localWeiboParameters.add("id", paramLong2);
    localWeiboParameters.add("comment", paramString);
    if (paramBoolean1)
    {
      localWeiboParameters.add("without_mention", 1);
      if (!paramBoolean2)
        break label84;
      localWeiboParameters.add("comment_ori", 1);
    }
    while (true)
    {
      request("https://api.weibo.com/2/comments/reply.json", localWeiboParameters, "POST", paramRequestListener);
      return;
      localWeiboParameters.add("without_mention", 0);
      break;
      label84: localWeiboParameters.add("comment_ori", 0);
    }
  }

  public void show(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, WeiboAPI.AUTHOR_FILTER paramAUTHOR_FILTER, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong1);
    localWeiboParameters.add("since_id", paramLong2);
    localWeiboParameters.add("max_id", paramLong3);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("filter_by_author", paramAUTHOR_FILTER.ordinal());
    request("https://api.weibo.com/2/comments/show.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void showBatch(long[] paramArrayOfLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfLong.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
        localWeiboParameters.add("cids", localStringBuilder.toString());
        request("https://api.weibo.com/2/comments/show_batch.json", localWeiboParameters, "GET", paramRequestListener);
        return;
      }
      localStringBuilder.append(String.valueOf(paramArrayOfLong[j])).append(",");
    }
  }

  public void timeline(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("trim_user", 1);
    while (true)
    {
      request("https://api.weibo.com/2/comments/timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("trim_user", 0);
    }
  }

  public void toME(long paramLong1, long paramLong2, int paramInt1, int paramInt2, WeiboAPI.AUTHOR_FILTER paramAUTHOR_FILTER, WeiboAPI.SRC_FILTER paramSRC_FILTER, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("filter_by_author", paramAUTHOR_FILTER.ordinal());
    localWeiboParameters.add("filter_by_source", paramSRC_FILTER.ordinal());
    request("https://api.weibo.com/2/comments/to_me.json", localWeiboParameters, "GET", paramRequestListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.CommentsAPI
 * JD-Core Version:    0.6.0
 */