package com.weibo.sdk.android.api;

import android.text.TextUtils;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.RequestListener;

public class StatusesAPI extends WeiboAPI
{
  private static final String SERVER_URL_PRIX = "https://api.weibo.com/2/statuses";

  public StatusesAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    super(paramOauth2AccessToken);
  }

  public void bilateralTimeline(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean1, WeiboAPI.FEATURE paramFEATURE, boolean paramBoolean2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean1)
    {
      localWeiboParameters.add("base_app", 1);
      localWeiboParameters.add("feature", paramFEATURE.ordinal());
      if (!paramBoolean2)
        break label105;
      localWeiboParameters.add("trim_user", 1);
    }
    while (true)
    {
      request("https://api.weibo.com/2/statuses/bilateral_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
      break;
      label105: localWeiboParameters.add("trim_user", 0);
    }
  }

  public void count(String[] paramArrayOfString, RequestListener paramRequestListener)
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
        request("https://api.weibo.com/2/statuses/count.json", localWeiboParameters, "GET", paramRequestListener);
        return;
      }
      localStringBuilder.append(paramArrayOfString[j]).append(",");
    }
  }

  public void destroy(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong);
    request("https://api.weibo.com/2/statuses/destroy.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void emotions(WeiboAPI.EMOTION_TYPE paramEMOTION_TYPE, WeiboAPI.LANGUAGE paramLANGUAGE, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("type", paramEMOTION_TYPE.name());
    localWeiboParameters.add("language", paramLANGUAGE.name());
    request("https://api.weibo.com/2/emotions.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void friendsTimeline(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean1, WeiboAPI.FEATURE paramFEATURE, boolean paramBoolean2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean1)
    {
      localWeiboParameters.add("base_app", 1);
      localWeiboParameters.add("feature", paramFEATURE.ordinal());
      if (!paramBoolean2)
        break label105;
      localWeiboParameters.add("trim_user", 1);
    }
    while (true)
    {
      request("https://api.weibo.com/2/statuses/friends_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
      break;
      label105: localWeiboParameters.add("trim_user", 0);
    }
  }

  public void friendsTimelineIds(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean, WeiboAPI.FEATURE paramFEATURE, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      localWeiboParameters.add("feature", paramFEATURE.ordinal());
      request("https://api.weibo.com/2/statuses/friends_timeline/ids.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void homeTimeline(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean1, WeiboAPI.FEATURE paramFEATURE, boolean paramBoolean2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean1)
    {
      localWeiboParameters.add("base_app", 1);
      localWeiboParameters.add("feature", paramFEATURE.ordinal());
      if (!paramBoolean2)
        break label105;
      localWeiboParameters.add("trim_user", 1);
    }
    while (true)
    {
      request("https://api.weibo.com/2/statuses/home_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
      break;
      label105: localWeiboParameters.add("trim_user", 0);
    }
  }

  public void hotCommentsDaily(int paramInt, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/statuses/hot/comments_daily.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void hotCommentsWeekly(int paramInt, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 0);
    while (true)
    {
      request("https://api.weibo.com/2/statuses/hot/comments_weekly.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 1);
    }
  }

  public void hotRepostDaily(int paramInt, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/statuses/hot/repost_daily.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void hotRepostWeekly(int paramInt, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/statuses/hot/repost_weekly.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void mentions(long paramLong1, long paramLong2, int paramInt1, int paramInt2, WeiboAPI.AUTHOR_FILTER paramAUTHOR_FILTER, WeiboAPI.SRC_FILTER paramSRC_FILTER, WeiboAPI.TYPE_FILTER paramTYPE_FILTER, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("filter_by_author", paramAUTHOR_FILTER.ordinal());
    localWeiboParameters.add("filter_by_source", paramSRC_FILTER.ordinal());
    localWeiboParameters.add("filter_by_type", paramTYPE_FILTER.ordinal());
    if (paramBoolean)
      localWeiboParameters.add("trim_user", 1);
    while (true)
    {
      request("https://api.weibo.com/2/statuses/mentions.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("trim_user", 0);
    }
  }

  public void mentionsIds(long paramLong1, long paramLong2, int paramInt1, int paramInt2, WeiboAPI.AUTHOR_FILTER paramAUTHOR_FILTER, WeiboAPI.SRC_FILTER paramSRC_FILTER, WeiboAPI.TYPE_FILTER paramTYPE_FILTER, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("filter_by_author", paramAUTHOR_FILTER.ordinal());
    localWeiboParameters.add("filter_by_source", paramSRC_FILTER.ordinal());
    localWeiboParameters.add("filter_by_type", paramTYPE_FILTER.ordinal());
    request("https://api.weibo.com/2/statuses/mentions/ids.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void publicTimeline(int paramInt1, int paramInt2, boolean paramBoolean, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      request("https://api.weibo.com/2/statuses/public_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void queryID(String[] paramArrayOfString, WeiboAPI.TYPE paramTYPE, boolean paramBoolean1, boolean paramBoolean2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    if (paramArrayOfString != null)
    {
      if (1 == paramArrayOfString.length)
        localWeiboParameters.add("mid", paramArrayOfString[0]);
    }
    else
    {
      localWeiboParameters.add("type", paramTYPE.ordinal());
      if (!paramBoolean1)
        break label159;
      localWeiboParameters.add("inbox", 0);
      label52: if (!paramBoolean2)
        break label170;
      localWeiboParameters.add("isBase62", 0);
    }
    while (true)
    {
      request("https://api.weibo.com/2/statuses/queryid.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("is_batch", 1);
      StringBuilder localStringBuilder = new StringBuilder();
      int i = paramArrayOfString.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
        {
          localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
          localWeiboParameters.add("mid", localStringBuilder.toString());
          break;
        }
        localStringBuilder.append(paramArrayOfString[j]).append(",");
      }
      label159: localWeiboParameters.add("inbox", 1);
      break label52;
      label170: localWeiboParameters.add("isBase62", 1);
    }
  }

  public void queryMID(long[] paramArrayOfLong, WeiboAPI.TYPE paramTYPE, RequestListener paramRequestListener)
  {
    int i = 0;
    WeiboParameters localWeiboParameters = new WeiboParameters();
    if (1 == paramArrayOfLong.length)
    {
      localWeiboParameters.add("id", paramArrayOfLong[0]);
      localWeiboParameters.add("type", paramTYPE.ordinal());
      request("https://api.weibo.com/2/statuses/querymid.json", localWeiboParameters, "GET", paramRequestListener);
      return;
    }
    localWeiboParameters.add("is_batch", 1);
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramArrayOfLong.length;
    while (true)
    {
      if (i >= j)
      {
        localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
        localWeiboParameters.add("id", localStringBuilder.toString());
        break;
      }
      localStringBuilder.append(paramArrayOfLong[i]).append(",");
      i++;
    }
  }

  public void repost(long paramLong, String paramString, WeiboAPI.COMMENTS_TYPE paramCOMMENTS_TYPE, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong);
    localWeiboParameters.add("status", paramString);
    localWeiboParameters.add("is_comment", paramCOMMENTS_TYPE.ordinal());
    request("https://api.weibo.com/2/statuses/repost.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void repostByMe(long paramLong1, long paramLong2, int paramInt1, int paramInt2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    request("https://api.weibo.com/2/statuses/repost_by_me.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void repostTimeline(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, WeiboAPI.AUTHOR_FILTER paramAUTHOR_FILTER, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong1);
    localWeiboParameters.add("since_id", paramLong2);
    localWeiboParameters.add("max_id", paramLong3);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("filter_by_author", paramAUTHOR_FILTER.ordinal());
    request("https://api.weibo.com/2/statuses/repost_timeline.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void repostTimelineIds(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, WeiboAPI.AUTHOR_FILTER paramAUTHOR_FILTER, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong1);
    localWeiboParameters.add("since_id", paramLong2);
    localWeiboParameters.add("max_id", paramLong3);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    localWeiboParameters.add("filter_by_author", paramAUTHOR_FILTER.ordinal());
    request("https://api.weibo.com/2/statuses/repost_timeline/ids.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void show(long paramLong, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("id", paramLong);
    request("https://api.weibo.com/2/statuses/show.json", localWeiboParameters, "GET", paramRequestListener);
  }

  public void update(String paramString1, String paramString2, String paramString3, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("status", paramString1);
    if (!TextUtils.isEmpty(paramString3))
      localWeiboParameters.add("long", paramString3);
    if (!TextUtils.isEmpty(paramString2))
      localWeiboParameters.add("lat", paramString2);
    request("https://api.weibo.com/2/statuses/update.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void upload(String paramString1, String paramString2, String paramString3, String paramString4, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("status", paramString1);
    localWeiboParameters.add("pic", paramString2);
    if (!TextUtils.isEmpty(paramString4))
      localWeiboParameters.add("long", paramString4);
    if (!TextUtils.isEmpty(paramString3))
      localWeiboParameters.add("lat", paramString3);
    request("https://api.weibo.com/2/statuses/upload.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void uploadUrlText(String paramString1, String paramString2, String paramString3, String paramString4, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("status", paramString1);
    localWeiboParameters.add("url", paramString2);
    if (!TextUtils.isEmpty(paramString4))
      localWeiboParameters.add("long", paramString4);
    if (!TextUtils.isEmpty(paramString3))
      localWeiboParameters.add("lat", paramString3);
    request("https://api.weibo.com/2/statuses/upload_url_text.json", localWeiboParameters, "POST", paramRequestListener);
  }

  public void userTimeline(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean1, WeiboAPI.FEATURE paramFEATURE, boolean paramBoolean2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean1)
    {
      localWeiboParameters.add("base_app", 1);
      localWeiboParameters.add("feature", paramFEATURE.ordinal());
      if (!paramBoolean2)
        break label105;
      localWeiboParameters.add("trim_user", 1);
    }
    while (true)
    {
      request("https://api.weibo.com/2/statuses/user_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
      break;
      label105: localWeiboParameters.add("trim_user", 0);
    }
  }

  public void userTimeline(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, boolean paramBoolean1, WeiboAPI.FEATURE paramFEATURE, boolean paramBoolean2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("uid", paramLong1);
    localWeiboParameters.add("since_id", paramLong2);
    localWeiboParameters.add("max_id", paramLong3);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean1)
    {
      localWeiboParameters.add("base_app", 1);
      localWeiboParameters.add("feature", paramFEATURE.ordinal());
      if (!paramBoolean2)
        break label114;
      localWeiboParameters.add("trim_user", 1);
    }
    while (true)
    {
      request("https://api.weibo.com/2/statuses/user_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
      break;
      label114: localWeiboParameters.add("trim_user", 0);
    }
  }

  public void userTimeline(String paramString, long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean1, WeiboAPI.FEATURE paramFEATURE, boolean paramBoolean2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("screen_name", paramString);
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean1)
    {
      localWeiboParameters.add("base_app", 1);
      localWeiboParameters.add("feature", paramFEATURE.ordinal());
      if (!paramBoolean2)
        break label114;
      localWeiboParameters.add("trim_user", 1);
    }
    while (true)
    {
      request("https://api.weibo.com/2/statuses/user_timeline.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
      break;
      label114: localWeiboParameters.add("trim_user", 0);
    }
  }

  public void userTimelineIds(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, boolean paramBoolean, WeiboAPI.FEATURE paramFEATURE, RequestListener paramRequestListener)
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
      localWeiboParameters.add("feature", paramFEATURE.ordinal());
      request("https://api.weibo.com/2/statuses/user_timeline/ids.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }

  public void userTimelineIds(String paramString, long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean, WeiboAPI.FEATURE paramFEATURE, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("screen_name", paramString);
    localWeiboParameters.add("since_id", paramLong1);
    localWeiboParameters.add("max_id", paramLong2);
    localWeiboParameters.add("count", paramInt1);
    localWeiboParameters.add("page", paramInt2);
    if (paramBoolean)
      localWeiboParameters.add("base_app", 1);
    while (true)
    {
      localWeiboParameters.add("feature", paramFEATURE.ordinal());
      request("https://api.weibo.com/2/statuses/user_timeline/ids.json", localWeiboParameters, "GET", paramRequestListener);
      return;
      localWeiboParameters.add("base_app", 0);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.StatusesAPI
 * JD-Core Version:    0.6.0
 */