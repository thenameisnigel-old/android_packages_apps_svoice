package com.vlingo.core.internal.social.api;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.tencent.tauth.TencentOpenAPI;
import com.tencent.tauth.http.AsyncHttpRequestRunner;
import com.tencent.tauth.http.Callback;
import com.tencent.tauth.http.RequestListenerImpl.UserInfoListener;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import java.io.IOException;

public class QZoneAPI extends SocialAPI
{
  private static final String ACCEPTED_TEXT_TAG_STRING = "qzone:status";
  private static final String CALLBACK = "auth://tauth.qq.com/";
  private static final String INTENT_STRING = "qzone";
  public static final int QZONE_CANCELLED = 0;
  public static final int QZONE_LOGIN_FAILED = 3;
  public static final int QZONE_LOGIN_SUCCESS = 1;
  public static final int QZONE_SHARE_FAILED = 4;
  public static final int QZONE_SHARE_SUCCESS = 2;
  public static final int TYPE_INTENT = 16;
  public String mAccessToken;
  public String mAppid = "100322265";
  public String mOpenId;
  QZoneCallBack mQZoneCallback;
  QZoneDialog mQZoneDialog;
  private String scope = "get_user_info,get_user_profile,add_share,add_topic,get_simple_userinfo, add_one_blog";

  public QZoneAPI(QZoneCallBack paramQZoneCallBack)
  {
    super("qzone_account", "qzone_picture_url", "qzone", -1, -1, VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_social_logout_qzone_msg), VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_social_logout_qzone), VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_wcis_social_qzone), -1, -1, 16, -1, 2147483647, true, true, "qzone:status");
    this.mQZoneCallback = paramQZoneCallBack;
  }

  private void getUserInfo(String paramString1, String paramString2)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = "100322265";
    arrayOfObject[2] = paramString2;
    String str = String.format("https://graph.qq.com/user/get_simple_userinfo?access_token=%s&oauth_consumer_key=%s&openid=%s", arrayOfObject);
    new AsyncHttpRequestRunner().request(str, null, new UserInfoListener(new Callback()
    {
      public void onCancel(int paramInt)
      {
      }

      public void onFail(int paramInt, String paramString)
      {
      }

      public void onSuccess(Object paramObject)
      {
        try
        {
          QZoneAPI.this.saveUserData(paramObject);
          label8: return;
        }
        catch (IOException localIOException)
        {
          break label8;
        }
      }
    }));
  }

  private void updateQZoneStatus(String paramString1, String paramString2, String paramString3)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("con", paramString1);
    TencentOpenAPI.addTopic(paramString2, "100322265", paramString3, localBundle, new Callback()
    {
      public void onCancel(int paramInt)
      {
      }

      public void onFail(int paramInt, String paramString)
      {
        if (paramInt != 0)
          QZoneAPI.this.mQZoneCallback.onQZoneFail(paramInt);
      }

      public void onSuccess(Object paramObject)
      {
        QZoneAPI.this.mQZoneCallback.onQZoneSuccess(2);
      }
    });
  }

  public void clearCredentials()
  {
    Util.clearCookies(ApplicationAdapter.getInstance().getApplicationContext());
    Settings.setString("qzone_open_id", null);
    Settings.setString("qzone_token", null);
    Settings.setBoolean("qzone_account", false);
    Settings.setImage("qzone_picture", null);
    Settings.setString("qzone_picture_url", null);
  }

  public void dismissDialogs()
  {
    if ((this.mQZoneDialog != null) && (this.mQZoneDialog.isShowing()))
      this.mQZoneDialog.dismiss();
  }

  protected String getAccessToken()
  {
    return Settings.getString("qzone_token", null);
  }

  public Dialog getDialog()
  {
    return this.mQZoneDialog;
  }

  protected String getOpenId()
  {
    return Settings.getString("qzone_open_id", null);
  }

  public boolean login(Activity paramActivity)
  {
    this.mQZoneDialog = new QZoneDialog(paramActivity, this.mAppid, this.scope, "auth://tauth.qq.com/", new QZoneLoginDialogListener(null));
    this.mQZoneDialog.show();
    return true;
  }

  public void refreshCredentials()
  {
    if (Settings.getBoolean("qzone_account", false))
    {
      this.mAccessToken = Settings.getString("qzone_token", null);
      this.mOpenId = Settings.getString("qzone_open_id", null);
    }
  }

  public boolean satisfyConditions()
  {
    if ((this.mAccessToken != null) && (this.mAppid != null) && (this.mOpenId != null) && (!this.mAccessToken.equals("")) && (!this.mAppid.equals("")) && (!this.mOpenId.equals("")));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected void saveCredentialsValues()
  {
    Settings.setString("qzone_token", this.mAccessToken);
    Settings.setString("qzone_open_id", this.mOpenId);
    Settings.setBoolean("qzone_account", true);
    getUserInfo(this.mAccessToken, this.mOpenId);
  }

  protected void saveCredentialsValues(String paramString1, String paramString2)
  {
    Settings.setString("qzone_token", paramString1);
    Settings.setString("qzone_open_id", paramString2);
    Settings.setBoolean("qzone_account", true);
    getUserInfo(paramString1, paramString2);
  }

  public void saveUserData(Object paramObject)
    throws IOException
  {
    String[] arrayOfString = paramObject.toString().split("\\n");
    Settings.setString("qzone_account_name", arrayOfString[0].substring(10));
    Settings.setString("qzone_picture_url", arrayOfString[2].substring(9));
    if (this.mQZoneCallback != null)
      this.mQZoneCallback.onQZoneSuccess(1);
  }

  public void showDialogs()
  {
    if (this.mQZoneDialog != null)
      this.mQZoneDialog.show();
  }

  public void update(String paramString)
  {
    String str1 = getAccessToken();
    String str2 = getOpenId();
    if ((str1 != null) || (str2 != null))
      updateQZoneStatus(paramString, str1, str2);
  }

  public void updateDialogs()
  {
    if ((this.mQZoneDialog != null) && (this.mQZoneDialog.isShowing()))
      this.mQZoneDialog.updateView();
  }

  public void updateStatus(String paramString)
  {
    update(paramString);
  }

  public static abstract interface QZoneCallBack extends SocialAPI.SocialCallback
  {
    public abstract void onQZoneFail(int paramInt);

    public abstract void onQZoneSuccess(int paramInt);
  }

  private class QZoneLoginDialogListener
    implements Facebook.DialogListener
  {
    private QZoneLoginDialogListener()
    {
    }

    public void onCancel()
    {
      if (QZoneAPI.this.mQZoneCallback != null)
        QZoneAPI.this.mQZoneCallback.onQZoneFail(0);
      QZoneAPI.this.clearCredentials();
    }

    public void onComplete(Bundle paramBundle)
    {
      QZoneAPI.this.mAccessToken = paramBundle.getString("access_token");
      QZoneAPI.this.mOpenId = paramBundle.getString("open_id");
      QZoneAPI.this.saveCredentialsValues(QZoneAPI.this.mAccessToken, QZoneAPI.this.mOpenId);
    }

    public void onError(DialogError paramDialogError)
    {
      if (QZoneAPI.this.mQZoneCallback != null)
        QZoneAPI.this.mQZoneCallback.onQZoneFail(3);
      QZoneAPI.this.clearCredentials();
    }

    public void onFacebookError(FacebookError paramFacebookError)
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.QZoneAPI
 * JD-Core Version:    0.6.0
 */