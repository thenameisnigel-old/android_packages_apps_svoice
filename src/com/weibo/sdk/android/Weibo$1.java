package com.weibo.sdk.android;

import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieSyncManager;

class Weibo$1
  implements WeiboAuthListener
{
  public void onCancel()
  {
    Log.d("Weibo-authorize", "Login canceled");
    this.val$listener.onCancel();
  }

  public void onComplete(Bundle paramBundle)
  {
    CookieSyncManager.getInstance().sync();
    if (this.this$0.accessToken == null)
      this.this$0.accessToken = new Oauth2AccessToken();
    this.this$0.accessToken.setToken(paramBundle.getString("access_token"));
    this.this$0.accessToken.setExpiresIn(paramBundle.getString("expires_in"));
    this.this$0.accessToken.setRefreshToken(paramBundle.getString("refresh_token"));
    if (this.this$0.accessToken.isSessionValid())
    {
      Log.d("Weibo-authorize", "Login Success! access_token=" + this.this$0.accessToken.getToken() + " expires=" + this.this$0.accessToken.getExpiresTime() + " refresh_token=" + this.this$0.accessToken.getRefreshToken());
      this.val$listener.onComplete(paramBundle);
    }
    while (true)
    {
      return;
      Log.d("Weibo-authorize", "Failed to receive access token");
      this.val$listener.onWeiboException(new WeiboException("Failed to receive access token."));
    }
  }

  public void onError(WeiboDialogError paramWeiboDialogError)
  {
    Log.d("Weibo-authorize", "Login failed: " + paramWeiboDialogError);
    this.val$listener.onError(paramWeiboDialogError);
  }

  public void onWeiboException(WeiboException paramWeiboException)
  {
    Log.d("Weibo-authorize", "Login failed: " + paramWeiboException);
    this.val$listener.onWeiboException(paramWeiboException);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.Weibo.1
 * JD-Core Version:    0.6.0
 */