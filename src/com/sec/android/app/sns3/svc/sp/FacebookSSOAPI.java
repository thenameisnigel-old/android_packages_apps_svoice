package com.sec.android.app.sns3.svc.sp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookCallbackMyAccountInfo.Stub;
import com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken;
import com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken.Stub;
import com.sec.android.app.sns3.svc.sp.facebook.response.SnsFbResponseMyAccountInfo;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.social.api.SocialNetworkType;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.FacebookStringUtil;
import com.vlingo.core.internal.util.SocialUtils;
import java.net.URL;
import java.util.Map;

public class FacebookSSOAPI
  implements FacebookStringUtil
{
  public static final String FACEBOOK_MARKET_URL = "market://details?id=com.facebook.katana";
  public static final String FACEBOOK_PACKAGE = "com.facebook.katana";
  public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
  public static final String SAMSUNG_FB_ACCOUNT_TYPE = "com.sec.android.app.sns3.facebook";
  public static final int TYPE_INTENT = 8;
  private static FbUpdateType fbUpdateType;
  private static ActionType mActionType = ActionType.NONE;
  private static ServiceConnection mFbForAuthtokenConnection;
  private static ISnsFacebookForAuthToken mSnsFacebookForAuthToken = null;
  private static volatile FacebookSSOCallback ssoCallback;
  private static boolean updateAfterBackToApp;

  public static void actionCleanUp()
  {
    mActionType = ActionType.NONE;
  }

  public static boolean backFromFacebookAppToLogin()
  {
    return updateAfterBackToApp;
  }

  public static void bindFacebookService(Context paramContext)
  {
    if (facebookSSO())
    {
      if (!isLoggedInFacebook(paramContext))
      {
        Settings.setString("facebook_token", null);
        Settings.setLong("facebook_expires", 0L);
        Settings.setBoolean("facebook_account", false);
        Settings.setImage("facebook_picture", null);
        Settings.setString("facebook_picture_url", null);
      }
      if (mFbForAuthtokenConnection == null)
        mFbForAuthtokenConnection = new ServiceConnection()
        {
          public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
          {
            FacebookSSOAPI.access$102(ISnsFacebookForAuthToken.Stub.asInterface(paramIBinder));
            FacebookSSOAPI.getFbAccountInfo(null);
          }

          public void onServiceDisconnected(ComponentName paramComponentName)
          {
            FacebookSSOAPI.access$102(null);
          }
        };
      if ((mSnsFacebookForAuthToken != null) || (paramContext == null))
        break label104;
      paramContext.bindService(new Intent(ISnsFacebookForAuthToken.class.getName()), mFbForAuthtokenConnection, 1);
    }
    while (true)
    {
      if (isLoggedInFacebook(paramContext))
        Settings.setBoolean("facebook_account", true);
      return;
      label104: getFbAccountInfo(null);
    }
  }

  public static void continueSocialUpdateAll(Context paramContext)
  {
    if ((paramContext != null) && (updateAfterBackToApp))
      paramContext.sendBroadcast(SocialUtils.loginIntent(true));
    init();
  }

  public static boolean facebookSSO()
  {
    try
    {
      ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().getPackageInfo("com.sec.android.app.sns3", 128);
      i = 1;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        int i = 0;
    }
  }

  public static ActionType getActionToExecute()
  {
    return mActionType;
  }

  public static void getFbAccountInfo(FacebookSSOCallback paramFacebookSSOCallback)
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    ssoCallback = paramFacebookSSOCallback;
    if (mSnsFacebookForAuthToken != null);
    try
    {
      Map localMap = mSnsFacebookForAuthToken.getAuthTokenNExpires();
      ((String)localMap.get("app_id"));
      ((String)localMap.get("secret_key"));
      ((String)localMap.get("access_token"));
      if ((String)localMap.get("expires") != null)
        Settings.setLong("facebook_expires", Long.valueOf((String)localMap.get("expires")).longValue());
      Settings.setString("facebook_token", (String)localMap.get("access_token"));
      if (isLoggedInFacebook(localContext))
        Settings.setBoolean("facebook_account", true);
      mSnsFacebookForAuthToken.getMyAccountInfo(new ISnsFacebookCallbackMyAccountInfo.Stub()
      {
        public void onResponse(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, Bundle paramBundle, SnsFbResponseMyAccountInfo paramSnsFbResponseMyAccountInfo)
          throws RemoteException
        {
          String str;
          if (paramSnsFbResponseMyAccountInfo != null)
          {
            int i = paramSnsFbResponseMyAccountInfo.mPic.length();
            paramSnsFbResponseMyAccountInfo.mPic = paramSnsFbResponseMyAccountInfo.mPic.substring(0, i - 6);
            paramSnsFbResponseMyAccountInfo.mPic += "_q.jpg";
            Settings.setString("facebook_account_name", paramSnsFbResponseMyAccountInfo.mName);
            Settings.setString("facebook_picture_url", paramSnsFbResponseMyAccountInfo.mPic);
            str = paramSnsFbResponseMyAccountInfo.mPic;
          }
          try
          {
            SocialUtils.setNetworkPicture(8, BitmapFactory.decodeStream(new URL(str).openStream()));
            if (FacebookSSOAPI.ssoCallback != null)
              FacebookSSOAPI.ssoCallback.onChangeAccountInfo();
            FacebookSSOAPI.continueSocialUpdateAll(ApplicationAdapter.getInstance().getApplicationContext());
            return;
          }
          catch (Exception localException)
          {
            while (true)
              localException.printStackTrace();
          }
        }
      });
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  public static FbUpdateType getFbUpdateType()
  {
    return fbUpdateType;
  }

  public static int getPackageVersionCode(Context paramContext, String paramString)
  {
    try
    {
      i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = -1;
    }
  }

  public static void init()
  {
    fbUpdateType = FbUpdateType.NONE;
    updateAfterBackToApp = false;
  }

  public static boolean isFacebookLoggedIn()
  {
    return Settings.getBoolean("facebook_account", false);
  }

  public static boolean isLoggedInFacebook(Context paramContext)
  {
    Account[] arrayOfAccount = AccountManager.get(paramContext).getAccountsByType("com.sec.android.app.sns3.facebook");
    if ((arrayOfAccount != null) && (arrayOfAccount.length > 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static void logoutFacebookSSO()
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    if ((localContext != null) && (facebookSSO()))
    {
      localContext.sendBroadcast(new Intent("com.sec.android.app.sns3.auth.sp.facebook.SNS_FB_LOGOUT"));
      Settings.setString("facebook_token", null);
      Settings.setLong("facebook_expires", 0L);
      Settings.setBoolean("facebook_account", false);
      Settings.setImage("facebook_picture", null);
      Settings.setString("facebook_picture_url", null);
      if (ssoCallback != null)
        ssoCallback.onChangeAccountInfo();
    }
  }

  public static void setActionAfterTTS(ActionType paramActionType)
  {
    mActionType = paramActionType;
  }

  public static void setFbUpdateType(SocialNetworkType paramSocialNetworkType)
  {
    if (paramSocialNetworkType == SocialNetworkType.ALL)
      fbUpdateType = FbUpdateType.ALL;
    while (true)
    {
      return;
      if (paramSocialNetworkType == SocialNetworkType.FACEBOOK)
      {
        fbUpdateType = FbUpdateType.FACEBOOK_ONLY;
        continue;
      }
      fbUpdateType = FbUpdateType.NONE;
    }
  }

  public static void startFacebookSSO(Context paramContext, boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.settings.ADD_ACCOUNT_SETTINGS");
    localIntent.setFlags(268435456);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "com.sec.android.app.sns3.facebook";
    localIntent.putExtra("account_types", arrayOfString);
    paramContext.startActivity(localIntent);
    updateAfterBackToApp = paramBoolean;
  }

  public static void unbindFacebookService(Context paramContext)
  {
    if ((facebookSSO()) && (paramContext != null) && (mFbForAuthtokenConnection != null))
    {
      paramContext.unbindService(mFbForAuthtokenConnection);
      mFbForAuthtokenConnection = null;
      mSnsFacebookForAuthToken = null;
      mActionType = ActionType.NONE;
    }
  }

  public String getLoginToNetworkErrorMsg()
  {
    return ApplicationAdapter.getInstance().getApplicationContext().getResources().getString(2131361918);
  }

  public static enum ActionType
  {
    static
    {
      ActionType[] arrayOfActionType = new ActionType[2];
      arrayOfActionType[0] = NONE;
      arrayOfActionType[1] = START_FACEBOOK_APP;
      $VALUES = arrayOfActionType;
    }
  }

  public static abstract interface FacebookSSOCallback
  {
    public abstract void onChangeAccountInfo();
  }

  public static enum FbUpdateType
  {
    static
    {
      ALL = new FbUpdateType("ALL", 1);
      FACEBOOK_ONLY = new FbUpdateType("FACEBOOK_ONLY", 2);
      FbUpdateType[] arrayOfFbUpdateType = new FbUpdateType[3];
      arrayOfFbUpdateType[0] = NONE;
      arrayOfFbUpdateType[1] = ALL;
      arrayOfFbUpdateType[2] = FACEBOOK_ONLY;
      $VALUES = arrayOfFbUpdateType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.sns3.svc.sp.FacebookSSOAPI
 * JD-Core Version:    0.6.0
 */