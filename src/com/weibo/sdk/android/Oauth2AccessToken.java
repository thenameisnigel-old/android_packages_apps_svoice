package com.weibo.sdk.android;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Oauth2AccessToken
{
  private String mAccessToken = "";
  private long mExpiresTime = 0L;
  private String mRefreshToken = "";

  public Oauth2AccessToken()
  {
  }

  public Oauth2AccessToken(String paramString)
  {
    if ((paramString != null) && (paramString.indexOf("{") >= 0));
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      setToken(localJSONObject.optString("access_token"));
      setExpiresIn(localJSONObject.optString("expires_in"));
      setRefreshToken(localJSONObject.optString("refresh_token"));
      label73: return;
    }
    catch (JSONException localJSONException)
    {
      break label73;
    }
  }

  public Oauth2AccessToken(String paramString1, String paramString2)
  {
    this.mAccessToken = paramString1;
    this.mExpiresTime = (System.currentTimeMillis() + 1000L * Long.parseLong(paramString2));
  }

  public long getExpiresTime()
  {
    return this.mExpiresTime;
  }

  public String getRefreshToken()
  {
    return this.mRefreshToken;
  }

  public String getToken()
  {
    return this.mAccessToken;
  }

  public boolean isSessionValid()
  {
    if ((!TextUtils.isEmpty(this.mAccessToken)) && ((this.mExpiresTime == 0L) || (System.currentTimeMillis() < this.mExpiresTime)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void setExpiresIn(String paramString)
  {
    if ((paramString != null) && (!paramString.equals("0")))
      setExpiresTime(System.currentTimeMillis() + 1000L * Long.parseLong(paramString));
  }

  public void setExpiresTime(long paramLong)
  {
    this.mExpiresTime = paramLong;
  }

  public void setRefreshToken(String paramString)
  {
    this.mRefreshToken = paramString;
  }

  public void setToken(String paramString)
  {
    this.mAccessToken = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.Oauth2AccessToken
 * JD-Core Version:    0.6.0
 */