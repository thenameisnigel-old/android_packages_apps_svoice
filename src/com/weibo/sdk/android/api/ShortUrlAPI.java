package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class ShortUrlAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/short_url";

  public ShortUrlAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void clicks(String[] paramArrayOfString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    int i;
    if (paramArrayOfString != null)
      i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        request("https://api.weibo.com/2/short_url/clicks.json", localWeiboParameters, "GET", paramRequestListener);
        return;
      }
      localWeiboParameters.add("url_short", paramArrayOfString[j]);
    }
  }

  public void commentCounts(String[] paramArrayOfString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    int i;
    if (paramArrayOfString != null)
      i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        request("https://api.weibo.com/2/short_url/comment/counts.json", localWeiboParameters, "GET", paramRequestListener);
        return;
      }
      localWeiboParameters.add("url_short", paramArrayOfString[j]);
    }
  }

  public void comments(String paramString, long paramLong1, long paramLong2, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("url_short", paramString);
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/short_url/comment/comments.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void expand(String[] paramArrayOfString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    int i;
    if (paramArrayOfString != null)
      i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        request("https://api.weibo.com/2/short_url/expand.json", localWeiboParameters, "GET", paramRequestListener);
        return;
      }
      localWeiboParameters.add("url_short", paramArrayOfString[j]);
    }
  }

  public void locations(String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("url_short", paramString);
    request("https://api.weibo.com/2/short_url/locations.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void referers(String paramString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("url_short", paramString);
    request("https://api.weibo.com/2/short_url/referers.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void shareCounts(String[] paramArrayOfString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    int i;
    if (paramArrayOfString != null)
      i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        request("https://api.weibo.com/2/short_url/share/counts.json", localWeiboParameters, "GET", paramRequestListener);
        return;
      }
      localWeiboParameters.add("url_short", paramArrayOfString[j]);
    }
  }

  public void shareStatuses(String paramString, long paramLong1, long paramLong2, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("url_short", paramString);
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/short_url/share/statuses.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void shorten(String[] paramArrayOfString, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    int i;
    if (paramArrayOfString != null)
      i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        request("https://api.weibo.com/2/short_url/shorten.json", localWeiboParameters, "GET", paramRequestListener);
        return;
      }
      localWeiboParameters.add("url_long", paramArrayOfString[j]);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.ShortUrlAPI
 * JD-Core Version:    0.6.0
 */