package com.vlingo.core.internal.social.api;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import com.facebook.android.Util;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.location.LocationUtils;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.AccountAPI;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.api.UsersAPI;
import com.weibo.sdk.android.net.RequestListener;
import java.io.IOException;
import org.json.JSONObject;

public class WeiboAPI extends SocialAPI
  implements RequestListener
{
  private static final String ACCEPTED_TEXT_TAG_STRING = "weibo:status";
  private static final String CONSUMER_KEY = Settings.getString("weibo_app_id", "3328388872");
  private static final String INTENT_STRING = "weibo";
  private static final String REDIRECT_URL = Settings.getString("weibo_redirect_url", "http://www.nuance.com/");
  public static final int TYPE_INTENT = 32;
  public static final int WEIBO_CANCELLED = 0;
  public static final int WEIBO_LOGIN_FAILED = 3;
  public static final int WEIBO_LOGIN_SUCCESS = 1;
  public static final int WEIBO_SHARE_FAILED = 4;
  public static final int WEIBO_SHARE_SUCCESS = 2;
  public static Oauth2AccessToken accessToken;
  private SinaWeiboDialog dialog = null;
  WeiboCallBack mCallBack;

  public WeiboAPI(WeiboCallBack paramWeiboCallBack)
  {
    super("weibo_account", "weibo_picture_url", "weibo", -1, -1, VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_social_logout_weibo_msg), VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_social_logout_weibo), VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_wcis_social_weibo), -1, -1, 32, -1, 2147483647, true, true, "weibo:status");
    this.mCallBack = paramWeiboCallBack;
    refreshCredentials();
  }

  public void clearCredentials()
  {
    Util.clearCookies(ApplicationAdapter.getInstance().getApplicationContext());
    Settings.setString("weibo_token", null);
    Settings.setLong("weibo_expires_in", 0L);
    Settings.setBoolean("weibo_account", false);
    Settings.setImage("weibo_picture", null);
    Settings.setString("weibo_picture_url", null);
    Settings.setLong("weibo_user_uid", 0L);
  }

  public void dismissDialogs()
  {
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
  }

  public void fetchUserData()
  {
    accessToken = getCredentialsValues();
    Long localLong = Long.valueOf(Settings.getLong("weibo_user_uid", 0L));
    new UsersAPI(accessToken).show(localLong.longValue(), this);
  }

  protected Oauth2AccessToken getCredentialsValues()
  {
    return new Oauth2AccessToken(Settings.getString("weibo_token", null), Long.valueOf(Settings.getLong("weibo_expires_in", 0L)).toString());
  }

  public Dialog getDialog()
  {
    return this.dialog;
  }

  public void getUserUID()
  {
    accessToken = getCredentialsValues();
    new AccountAPI(accessToken).getUid(this);
  }

  public boolean login(Activity paramActivity)
  {
    this.dialog = new SinaWeiboDialog(paramActivity, CONSUMER_KEY, REDIRECT_URL, new AuthDialogListener());
    return true;
  }

  public void onComplete(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      if (localJSONObject.has("uid"))
      {
        long l = localJSONObject.getLong("uid");
        if (l != 0L)
          saveUserUID(Long.valueOf(l));
        fetchUserData();
      }
      if ((localJSONObject.has("screen_name")) && (localJSONObject.has("profile_image_url")))
      {
        saveUserData(localJSONObject.getString("screen_name"), localJSONObject.getString("profile_image_url"));
        if (this.mCallBack != null)
          this.mCallBack.onWeiboSuccess(1);
      }
      else
      {
        this.mCallBack.onWeiboSuccess(2);
      }
    }
    catch (Exception localException)
    {
      this.mCallBack.onWeiboFail(3);
    }
  }

  public void onError(WeiboException paramWeiboException)
  {
    this.mCallBack.onWeiboFail(4);
  }

  public void onIOException(IOException paramIOException)
  {
    this.mCallBack.onWeiboFail(4);
  }

  public void refreshCredentials()
  {
    if (Settings.getBoolean("weibo_account", false))
      accessToken = getCredentialsValues();
  }

  protected void saveCredentialsValues(Oauth2AccessToken paramOauth2AccessToken)
  {
    Settings.setString("weibo_token", paramOauth2AccessToken.getToken());
    Settings.setLong("weibo_expires_in", paramOauth2AccessToken.getExpiresTime());
    Settings.setBoolean("weibo_account", true);
    getUserUID();
  }

  protected void saveUserData(String paramString1, String paramString2)
  {
    Settings.setString("weibo_account_name", paramString1);
    Settings.setString("weibo_picture_url", paramString2);
  }

  protected void saveUserUID(Long paramLong)
  {
    Settings.setLong("weibo_user_uid", paramLong.longValue());
  }

  public void showDialogs()
  {
    if (this.dialog != null)
      this.dialog.show();
  }

  public void updateDialogs()
  {
    if (this.dialog != null)
      this.dialog.updateDialog();
  }

  public void updateStatus(String paramString)
  {
    Double.valueOf(LocationUtils.getLastLong());
    Double.valueOf(LocationUtils.getLastLat());
    accessToken = getCredentialsValues();
    new StatusesAPI(accessToken).update(paramString, null, null, this);
  }

  class AuthDialogListener
    implements WeiboAuthListener
  {
    AuthDialogListener()
    {
    }

    public void onCancel()
    {
      WeiboAPI.this.mCallBack.onWeiboFail(0);
    }

    public void onComplete(Bundle paramBundle)
    {
      WeiboAPI.accessToken = new Oauth2AccessToken(paramBundle.getString("access_token"), paramBundle.getString("expires_in"));
      if (WeiboAPI.accessToken.isSessionValid())
        WeiboAPI.this.saveCredentialsValues(WeiboAPI.accessToken);
    }

    public void onError(WeiboDialogError paramWeiboDialogError)
    {
      WeiboAPI.this.mCallBack.onWeiboFail(3);
    }

    public void onWeiboException(WeiboException paramWeiboException)
    {
      WeiboAPI.this.mCallBack.onWeiboFail(3);
    }
  }

  public static abstract interface WeiboCallBack extends SocialAPI.SocialCallback
  {
    public abstract void onWeiboFail(int paramInt);

    public abstract void onWeiboSuccess(int paramInt);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.WeiboAPI
 * JD-Core Version:    0.6.0
 */