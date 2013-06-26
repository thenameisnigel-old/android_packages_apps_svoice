package com.facebook.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.webkit.CookieSyncManager;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class Facebook
{
  public static final String CANCEL_URI = "fbconnect://cancel";
  private static final int DEFAULT_AUTH_ACTIVITY_CODE = 32665;
  protected static String DIALOG_BASE_URL = "https://m.facebook.com/dialog/";
  public static final String EXPIRES = "expires_in";
  public static final String FB_APP_SIGNATURE = "30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2";
  public static final int FORCE_DIALOG_AUTH = -1;
  protected static String GRAPH_BASE_URL = "https://graph.facebook.com/";
  private static final String LOGIN = "oauth";
  public static final String REDIRECT_URI = "fbconnect://success";
  protected static String RESTSERVER_URL = "https://api.facebook.com/restserver.php";
  public static final String SINGLE_SIGN_ON_DISABLED = "service_disabled";
  public static final String TOKEN = "access_token";
  private long mAccessExpires = 0L;
  private String mAccessToken = null;
  private String mAppId;
  private Activity mAuthActivity;
  private int mAuthActivityCode;
  private DialogListener mAuthDialogListener;
  private String[] mAuthPermissions;
  private FbDialog mFBDialog;

  public Facebook(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("You must specify your application ID when instantiating a Facebook object. See README for details.");
    this.mAppId = paramString;
  }

  private void startDialogAuth(Activity paramActivity, String[] paramArrayOfString)
  {
    Bundle localBundle = new Bundle();
    if (paramArrayOfString.length > 0)
      localBundle.putString("scope", TextUtils.join(",", paramArrayOfString));
    CookieSyncManager.createInstance(paramActivity);
    this.mFBDialog = dialog(paramActivity, "oauth", localBundle, new DialogListener()
    {
      public void onCancel()
      {
        Facebook.this.mAuthDialogListener.onCancel();
      }

      public void onComplete(Bundle paramBundle)
      {
        CookieSyncManager.getInstance().sync();
        Facebook.this.setAccessToken(paramBundle.getString("access_token"));
        Facebook.this.setAccessExpiresIn(paramBundle.getString("expires_in"));
        if (Facebook.this.isSessionValid())
          Facebook.this.mAuthDialogListener.onComplete(paramBundle);
        while (true)
        {
          return;
          Facebook.this.mAuthDialogListener.onFacebookError(new FacebookError("Failed to receive access token."));
        }
      }

      public void onError(DialogError paramDialogError)
      {
        Facebook.this.mAuthDialogListener.onError(paramDialogError);
      }

      public void onFacebookError(FacebookError paramFacebookError)
      {
        Facebook.this.mAuthDialogListener.onFacebookError(paramFacebookError);
      }
    });
    this.mFBDialog.getWindow().addFlags(512);
  }

  private boolean startSingleSignOn(Activity paramActivity, String paramString, String[] paramArrayOfString, int paramInt)
  {
    int i = 1;
    Intent localIntent = new Intent();
    localIntent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
    localIntent.putExtra("client_id", paramString);
    if (paramArrayOfString.length > 0)
      localIntent.putExtra("scope", TextUtils.join(",", paramArrayOfString));
    int j;
    if (!validateAppSignatureForIntent(paramActivity, localIntent))
      j = 0;
    while (true)
    {
      return j;
      this.mAuthActivity = paramActivity;
      this.mAuthPermissions = paramArrayOfString;
      this.mAuthActivityCode = paramInt;
      try
      {
        paramActivity.startActivityForResult(localIntent, paramInt);
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
    if (localResolveInfo == null)
      return i;
    String str = localResolveInfo.activityInfo.packageName;
    while (true)
    {
      int k;
      try
      {
        PackageInfo localPackageInfo = paramActivity.getPackageManager().getPackageInfo(str, 64);
        Signature[] arrayOfSignature = localPackageInfo.signatures;
        int j = arrayOfSignature.length;
        k = 0;
        if (k >= j)
          break;
        if (!arrayOfSignature[k].toCharsString().equals("30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2"))
          break label91;
        i = 1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
      }
      break;
      label91: k++;
    }
  }

  public void authorize(Activity paramActivity, DialogListener paramDialogListener)
  {
    authorize(paramActivity, new String[0], 32665, paramDialogListener);
  }

  public void authorize(Activity paramActivity, String[] paramArrayOfString, int paramInt, DialogListener paramDialogListener)
  {
    this.mAuthDialogListener = paramDialogListener;
    startDialogAuth(paramActivity, paramArrayOfString);
  }

  public void authorize(Activity paramActivity, String[] paramArrayOfString, DialogListener paramDialogListener)
  {
    authorize(paramActivity, paramArrayOfString, 32665, paramDialogListener);
  }

  public void authorizeCallback(int paramInt1, int paramInt2, Intent paramIntent)
  {
    String str;
    if (paramInt1 == this.mAuthActivityCode)
    {
      if (paramInt2 != -1)
        break label191;
      str = paramIntent.getStringExtra("error");
      if (str == null)
        str = paramIntent.getStringExtra("error_type");
      if (str == null)
        break label126;
      if ((!str.equals("service_disabled")) && (!str.equals("AndroidAuthKillSwitchException")))
        break label73;
      startDialogAuth(this.mAuthActivity, this.mAuthPermissions);
    }
    while (true)
    {
      return;
      label73: if ((str.equals("access_denied")) || (str.equals("OAuthAccessDeniedException")))
      {
        this.mAuthDialogListener.onCancel();
        continue;
      }
      this.mAuthDialogListener.onFacebookError(new FacebookError(str));
      continue;
      label126: setAccessToken(paramIntent.getStringExtra("access_token"));
      setAccessExpiresIn(paramIntent.getStringExtra("expires_in"));
      if (isSessionValid())
      {
        this.mAuthDialogListener.onComplete(paramIntent.getExtras());
        continue;
      }
      this.mAuthDialogListener.onFacebookError(new FacebookError("Failed to receive access token."));
      continue;
      label191: if (paramInt2 != 0)
        continue;
      if (paramIntent != null)
      {
        this.mAuthDialogListener.onError(new DialogError(paramIntent.getStringExtra("error"), paramIntent.getIntExtra("error_code", -1), paramIntent.getStringExtra("failing_url")));
        continue;
      }
      this.mAuthDialogListener.onCancel();
    }
  }

  public FbDialog dialog(Context paramContext, String paramString, Bundle paramBundle, DialogListener paramDialogListener)
  {
    this.mFBDialog = null;
    String str1 = DIALOG_BASE_URL + paramString;
    paramBundle.putString("display", "touch");
    paramBundle.putString("redirect_uri", "fbconnect://success");
    String str2;
    if (paramString.equals("oauth"))
    {
      paramBundle.putString("type", "user_agent");
      paramBundle.putString("client_id", this.mAppId);
      if (isSessionValid())
        paramBundle.putString("access_token", getAccessToken());
      str2 = str1 + "?" + Util.encodeUrl(paramBundle);
      if (paramContext.checkCallingOrSelfPermission("android.permission.INTERNET") == 0)
        break label177;
      Util.showAlert(paramContext, VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_error), VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_permission_internet_error));
    }
    while (true)
    {
      return this.mFBDialog;
      paramBundle.putString("app_id", this.mAppId);
      break;
      label177: this.mFBDialog = new FbDialog(paramContext, str2, paramDialogListener);
      this.mFBDialog.show();
    }
  }

  public FbDialog dialog(Context paramContext, String paramString, DialogListener paramDialogListener)
  {
    return dialog(paramContext, paramString, new Bundle(), paramDialogListener);
  }

  public void dismissDialogs()
  {
    if ((this.mFBDialog != null) && (this.mFBDialog.isShowing()))
      this.mFBDialog.dismiss();
  }

  public long getAccessExpires()
  {
    return this.mAccessExpires;
  }

  public String getAccessToken()
  {
    return this.mAccessToken;
  }

  public String getAppId()
  {
    return this.mAppId;
  }

  public Dialog getDialog()
  {
    return this.mFBDialog;
  }

  public boolean isSessionValid()
  {
    if ((getAccessToken() != null) && ((getAccessExpires() == 0L) || (System.currentTimeMillis() < getAccessExpires())));
    for (int i = 1; ; i = 0)
      return i;
  }

  public String logout(Context paramContext)
    throws MalformedURLException, IOException
  {
    Util.clearCookies(paramContext);
    Bundle localBundle = new Bundle();
    localBundle.putString("method", "auth.expireSession");
    String str = request(localBundle);
    setAccessToken(null);
    setAccessExpires(0L);
    return str;
  }

  public String request(Bundle paramBundle)
    throws MalformedURLException, IOException
  {
    if (!paramBundle.containsKey("method"))
      throw new IllegalArgumentException("API method must be specified. (parameters must contain key \"method\" and value). See http://developers.facebook.com/docs/reference/rest/");
    return request(null, paramBundle, "GET");
  }

  public String request(String paramString)
    throws MalformedURLException, IOException
  {
    return request(paramString, new Bundle(), "GET");
  }

  public String request(String paramString, Bundle paramBundle)
    throws MalformedURLException, IOException
  {
    return request(paramString, paramBundle, "GET");
  }

  public String request(String paramString1, Bundle paramBundle, String paramString2)
    throws FileNotFoundException, MalformedURLException, IOException
  {
    paramBundle.putString("format", "json");
    if (isSessionValid())
      paramBundle.putString("access_token", getAccessToken());
    if (paramString1 != null);
    for (String str = GRAPH_BASE_URL + paramString1; ; str = RESTSERVER_URL)
      return Util.openUrl(str, paramString2, paramBundle);
  }

  public void screenOrientationChange()
  {
    if ((this.mFBDialog != null) && (this.mFBDialog.isShowing()))
      this.mFBDialog.updateDialog();
  }

  public void setAccessExpires(long paramLong)
  {
    this.mAccessExpires = paramLong;
  }

  public void setAccessExpiresIn(String paramString)
  {
    if ((paramString != null) && (!paramString.equals("0")))
      setAccessExpires(System.currentTimeMillis() + 1000 * Integer.parseInt(paramString));
  }

  public void setAccessToken(String paramString)
  {
    this.mAccessToken = paramString;
  }

  public void setAppId(String paramString)
  {
    this.mAppId = paramString;
  }

  public void showDialogs()
  {
    if ((this.mFBDialog != null) && (!this.mFBDialog.isShowing()))
      this.mFBDialog.show();
  }

  public void updateDialog()
  {
    if ((this.mFBDialog != null) && (this.mFBDialog.isShowing()))
      this.mFBDialog.updateView();
  }

  public static abstract interface DialogListener
  {
    public abstract void onCancel();

    public abstract void onComplete(Bundle paramBundle);

    public abstract void onError(DialogError paramDialogError);

    public abstract void onFacebookError(FacebookError paramFacebookError);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.facebook.android.Facebook
 * JD-Core Version:    0.6.0
 */