package com.weibo.sdk.android.sso;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Log;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.util.Utility;

public class SsoHandler
{
  private static final int DEFAULT_AUTH_ACTIVITY_CODE = 32973;
  private static final String WEIBO_SIGNATURE = "30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4";
  private static String ssoActivityName;
  private static String ssoPackageName = "";
  private ServiceConnection conn = null;
  private Oauth2AccessToken mAccessToken = null;
  private Activity mAuthActivity;
  private int mAuthActivityCode;
  private WeiboAuthListener mAuthDialogListener;
  private Weibo mWeibo;

  static
  {
    ssoActivityName = "";
  }

  public SsoHandler(Activity paramActivity, Weibo paramWeibo)
  {
    this.mAuthActivity = paramActivity;
    this.mWeibo = paramWeibo;
    Weibo.isWifi = Utility.isWifi(paramActivity);
    this.conn = new SsoHandler.1(this);
  }

  private void authorize(int paramInt, WeiboAuthListener paramWeiboAuthListener)
  {
    this.mAuthActivityCode = paramInt;
    this.mAuthDialogListener = paramWeiboAuthListener;
    if ((!bindRemoteSSOService(this.mAuthActivity)) && (this.mWeibo != null))
      this.mWeibo.startAuthDialog(this.mAuthActivity, this.mAuthDialogListener);
  }

  private boolean bindRemoteSSOService(Activity paramActivity)
  {
    return paramActivity.getApplicationContext().bindService(new Intent("com.sina.weibo.remotessoservice"), this.conn, 1);
  }

  private boolean startSingleSignOn(Activity paramActivity, String paramString, String[] paramArrayOfString, int paramInt)
  {
    int i = 1;
    Intent localIntent = new Intent();
    localIntent.setClassName(ssoPackageName, ssoActivityName);
    localIntent.putExtra("appKey", paramString);
    localIntent.putExtra("redirectUri", Weibo.redirecturl);
    if (paramArrayOfString.length > 0)
      localIntent.putExtra("scope", TextUtils.join(",", paramArrayOfString));
    int j;
    if (!validateAppSignatureForIntent(paramActivity, localIntent))
      j = 0;
    while (true)
    {
      return j;
      try
      {
        paramActivity.startActivityForResult(localIntent, paramInt);
        paramActivity.getApplication().unbindService(this.conn);
        j = i;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        while (true)
          i = 0;
      }
    }
  }

  private boolean validateAppSignatureForIntent(Activity paramActivity, Intent paramIntent)
  {
    int i = 0;
    ResolveInfo localResolveInfo = paramActivity.getPackageManager().resolveActivity(paramIntent, 0);
    if (localResolveInfo == null);
    while (true)
    {
      return i;
      String str = localResolveInfo.activityInfo.packageName;
      try
      {
        Signature[] arrayOfSignature = paramActivity.getPackageManager().getPackageInfo(str, 64).signatures;
        int j = arrayOfSignature.length;
        for (int k = 0; k < j; k++)
        {
          boolean bool = "30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4".equals(arrayOfSignature[k].toCharsString());
          if (!bool)
            continue;
          i = 1;
          break;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
      }
    }
  }

  public void authorize(WeiboAuthListener paramWeiboAuthListener)
  {
    authorize(32973, paramWeiboAuthListener);
  }

  public void authorizeCallBack(int paramInt1, int paramInt2, Intent paramIntent)
  {
    String str1;
    if (paramInt1 == this.mAuthActivityCode)
    {
      if (paramInt2 != -1)
        break label339;
      str1 = paramIntent.getStringExtra("error");
      if (str1 == null)
        str1 = paramIntent.getStringExtra("error_type");
      if (str1 == null)
        break label165;
      if ((!str1.equals("access_denied")) && (!str1.equals("OAuthAccessDeniedException")))
        break label78;
      Log.d("Weibo-authorize", "Login canceled by user.");
      this.mAuthDialogListener.onCancel();
    }
    while (true)
    {
      return;
      label78: String str2 = paramIntent.getStringExtra("error_description");
      if (str2 != null)
        str1 = str1 + ":" + str2;
      Log.d("Weibo-authorize", "Login failed: " + str1);
      this.mAuthDialogListener.onError(new WeiboDialogError(str1, paramInt2, str2));
      continue;
      label165: if (this.mAccessToken == null)
        this.mAccessToken = new Oauth2AccessToken();
      this.mAccessToken.setToken(paramIntent.getStringExtra("access_token"));
      this.mAccessToken.setExpiresIn(paramIntent.getStringExtra("expires_in"));
      this.mAccessToken.setRefreshToken(paramIntent.getStringExtra("refresh_token"));
      if (this.mAccessToken.isSessionValid())
      {
        Log.d("Weibo-authorize", "Login Success! access_token=" + this.mAccessToken.getToken() + " expires=" + this.mAccessToken.getExpiresTime() + "refresh_token=" + this.mAccessToken.getRefreshToken());
        this.mAuthDialogListener.onComplete(paramIntent.getExtras());
        continue;
      }
      Log.d("Weibo-authorize", "Failed to receive access token by SSO");
      this.mWeibo.startAuthDialog(this.mAuthActivity, this.mAuthDialogListener);
      continue;
      label339: if (paramInt2 != 0)
        continue;
      if (paramIntent != null)
      {
        Log.d("Weibo-authorize", "Login failed: " + paramIntent.getStringExtra("error"));
        this.mAuthDialogListener.onError(new WeiboDialogError(paramIntent.getStringExtra("error"), paramIntent.getIntExtra("error_code", -1), paramIntent.getStringExtra("failing_url")));
        continue;
      }
      Log.d("Weibo-authorize", "Login canceled by user.");
      this.mAuthDialogListener.onCancel();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.sso.SsoHandler
 * JD-Core Version:    0.6.0
 */