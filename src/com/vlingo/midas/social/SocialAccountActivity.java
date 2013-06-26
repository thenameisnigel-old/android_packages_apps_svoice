package com.vlingo.midas.social;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.android.FbDialog;
import com.sec.android.app.sns3.svc.sp.FacebookSSOAPI;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.social.api.FacebookAPI;
import com.vlingo.core.internal.social.api.FacebookAPI.FacebookAPICallback;
import com.vlingo.core.internal.social.api.QZoneAPI.QZoneCallBack;
import com.vlingo.core.internal.social.api.SocialAPI;
import com.vlingo.core.internal.social.api.TwitterAPI;
import com.vlingo.core.internal.social.api.TwitterAPI.TwitterCallback;
import com.vlingo.core.internal.social.api.WeiboAPI.WeiboCallBack;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.SocialUtils;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.ui.VLActivity;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;

public class SocialAccountActivity extends VLActivity
  implements TwitterAPI.TwitterCallback, FacebookAPI.FacebookAPICallback, WeiboAPI.WeiboCallBack, QZoneAPI.QZoneCallBack
{
  public static final int SHOW_POPUP_FOLLOW_VLINGO = 205;
  public static final int SHOW_POPUP_LOGGING_IN = 203;
  public static final int SHOW_POPUP_LOGGING_IN_FAIL = 204;
  public static final int SHOW_POPUP_LOGOUT = 202;
  public static final int SHOW_POPUP_NO_GOOGLE_ACCOUNT = 208;
  public static final int SHOW_POPUP_NO_NETWORK = 207;
  public static final int SHOW_POPUP_PLEASE_WAIT = 206;
  public static final int SHOW_POPUP_TEXT_ENTRY = 201;
  private volatile int curDialogID = -1;
  private volatile String errorMessage;
  boolean mDestroyed = false;
  private volatile ProgressDialog progressDialog;
  private BroadcastReceiver screenEventBroadcastReceiver;
  private SocialAPI socialAPI = null;
  private int type;
  private String updateType;

  private void cancelLoginDialog()
  {
    this.socialAPI.clearCredentials();
    sendBroadcast(SocialUtils.loginIntent(false));
    finish();
  }

  private void fetchThumbnail()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        String str = SocialAccountActivity.this.socialAPI.getLoginUrl();
        try
        {
          Bitmap localBitmap = BitmapFactory.decodeStream(new URL(str).openStream());
          SocialUtils.setNetworkPicture(SocialAccountActivity.this.type, localBitmap);
          SocialAccountActivity.this.runOnUiThread(new SocialAccountActivity.11.1(this));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    }).start();
  }

  private boolean haveConnection()
  {
    NetworkInfo[] arrayOfNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getAllNetworkInfo();
    int i = arrayOfNetworkInfo.length;
    int j = 0;
    if (j < i)
    {
      NetworkInfo localNetworkInfo = arrayOfNetworkInfo[j];
      if (((!localNetworkInfo.getTypeName().equalsIgnoreCase("MOBILE")) && (!localNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) && (!localNetworkInfo.getTypeName().equalsIgnoreCase("WIMAX")) && (!localNetworkInfo.getTypeName().equalsIgnoreCase("MOBILE2")) && (!localNetworkInfo.getTypeName().equalsIgnoreCase("BLUETOOTH_TETHER"))) || (!localNetworkInfo.isConnected()));
    }
    for (int k = 1; ; k = 0)
    {
      return k;
      j++;
      break;
    }
  }

  private void removeCurrentDialog()
  {
    if (this.curDialogID != -1)
    {
      removeDialog(this.curDialogID);
      this.curDialogID = -1;
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.socialAPI.authorizeCallback(paramInt1, paramInt2, paramIntent);
  }

  public void onCheckinResult(boolean paramBoolean, Hashtable<Object, Object> paramHashtable)
  {
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.socialAPI != null)
      this.socialAPI.updateDialogs();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    HashMap localHashMap = SocialUtils.getAPIs(this);
    this.type = getIntent().getIntExtra("android.intent.extra.INTENT", 0);
    this.socialAPI = ((SocialAPI)localHashMap.get(Integer.valueOf(this.type)));
    if (this.socialAPI == null)
      throw new RuntimeException("Must specify an Intent.EXTRA_TYPE of either TWITTER (2), or FACEBOOK(3), or QZone(4), or Weibo(5)");
    if (Settings.getInt("twitter_dialog_flags", 0) == 0)
      Settings.setInt("twitter_dialog_flags", 544);
    if (getIntent().getExtras().containsKey("choice"))
      this.updateType = getIntent().getStringExtra("choice");
    if (getIntent().getAction().equals("android.intent.action.MAIN"))
    {
      if ((!this.socialAPI.isLoggedIn()) || (!this.socialAPI.supportsLogout()) || (isFinishing()))
        break label157;
      showDialog(202);
    }
    while (true)
    {
      return;
      label157: if ((!haveConnection()) && (!isFinishing()))
      {
        this.errorMessage = getString(2131362322);
        showDialog(204);
        continue;
      }
      if (this.socialAPI.includesLoginDialog())
      {
        if (!this.socialAPI.intentType().equals("facebook"))
        {
          this.socialAPI.login(this);
          continue;
        }
        if (FacebookSSOAPI.facebookSSO())
          continue;
        this.socialAPI.login(this);
        continue;
      }
      if (isFinishing())
        continue;
      showDialog(201);
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    this.curDialogID = paramInt;
    Object localObject;
    switch (paramInt)
    {
    case 205:
    case 207:
    default:
      localObject = super.onCreateDialog(paramInt);
    case 202:
    case 203:
    case 206:
    case 204:
    case 208:
    }
    while (true)
    {
      return localObject;
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle(ApplicationAdapter.getInstance().getApplicationContext().getString(this.socialAPI.logoutDialogId()));
      localBuilder.setMessage(ApplicationAdapter.getInstance().getApplicationContext().getString(this.socialAPI.logoutMessageId()));
      localBuilder.setNegativeButton(getString(2131362455), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          SocialAccountActivity.this.finish();
        }
      });
      localBuilder.setPositiveButton(getString(2131362458), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          SocialAccountActivity.this.socialAPI.clearCredentials();
          if (SocialAccountActivity.this.socialAPI.intentType().equals("facebook"))
            FacebookSSOAPI.logoutFacebookSSO();
          SocialAccountActivity.this.finish();
        }
      });
      localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          SocialAccountActivity.this.finish();
        }
      });
      localObject = localBuilder.create();
      continue;
      this.progressDialog = new ProgressDialog(this);
      this.progressDialog.setTitle(ApplicationAdapter.getInstance().getApplicationContext().getString(this.socialAPI.wcisId()));
      this.progressDialog.setMessage(getString(2131362457));
      this.progressDialog.setIndeterminate(true);
      this.progressDialog.setCancelable(false);
      localObject = this.progressDialog;
      continue;
      this.progressDialog = new ProgressDialog(this);
      this.progressDialog.setTitle(ApplicationAdapter.getInstance().getApplicationContext().getString(this.socialAPI.wcisId()));
      this.progressDialog.setMessage(getString(2131362457));
      this.progressDialog.setIndeterminate(true);
      this.progressDialog.setCancelable(false);
      localObject = this.progressDialog;
      continue;
      localObject = new AlertDialog.Builder(this).setTitle(getString(2131362251)).setMessage(this.errorMessage).setPositiveButton(getString(2131362250), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          SocialAccountActivity.this.cancelLoginDialog();
        }
      }).setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          SocialAccountActivity.this.cancelLoginDialog();
        }
      }).create();
      continue;
      localObject = new AlertDialog.Builder(this).setTitle(getString(2131362251)).setMessage(getString(2131362717)).setPositiveButton(getString(2131362250), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          SocialAccountActivity.this.finish();
        }
      }).setOnKeyListener(new DialogInterface.OnKeyListener()
      {
        public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
        {
          if (paramInt == 4)
            SocialAccountActivity.this.finish();
          for (int i = 1; ; i = 0)
            return i;
        }
      }).create();
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.socialAPI != null)
      this.socialAPI.shutdown();
    this.mDestroyed = true;
  }

  public void onFacebookAPILogin(FacebookAPI paramFacebookAPI, int paramInt, Bundle paramBundle)
  {
    runOnUiThread(new Runnable(paramInt, paramFacebookAPI)
    {
      public void run()
      {
        if ((SocialAccountActivity.this.isFinishing()) || (SocialAccountActivity.this.mDestroyed))
          SocialAccountActivity.this.socialAPI.clearCredentials();
        while (true)
        {
          return;
          if (this.val$responseType == 901)
          {
            SocialAccountActivity.this.removeCurrentDialog();
            if (!SocialAccountActivity.this.isFinishing())
              SocialAccountActivity.this.showDialog(206);
            this.val$api.fetchUserData();
            continue;
          }
          if (this.val$responseType == 902)
          {
            SocialAccountActivity.this.socialAPI.clearCredentials();
            SocialAccountActivity.this.removeCurrentDialog();
            SocialAccountActivity.access$502(SocialAccountActivity.this, SocialAccountActivity.this.getString(2131362452));
            if (SocialAccountActivity.this.isFinishing())
              continue;
            SocialAccountActivity.this.showDialog(204);
            continue;
          }
          if (this.val$responseType != 903)
            continue;
          SocialAccountActivity.this.cancelLoginDialog();
        }
      }
    });
  }

  public void onFacebookAPIMethod(FacebookAPI paramFacebookAPI, int paramInt, String paramString, Bundle paramBundle)
  {
    runOnUiThread(new Runnable(paramString, paramInt, paramBundle)
    {
      public void run()
      {
        if ((SocialAccountActivity.this.isFinishing()) || (SocialAccountActivity.this.mDestroyed))
          SocialAccountActivity.this.socialAPI.clearCredentials();
        while (true)
        {
          return;
          if (this.val$method.equalsIgnoreCase("me"))
          {
            if (this.val$responseType == 901)
            {
              Settings.setString("facebook_account_name", this.val$params.getString("name"));
              Settings.setString("facebook_picture_url", this.val$params.getString("picture"));
              SocialAccountActivity.this.removeCurrentDialog();
              if (!SocialAccountActivity.this.isFinishing())
                SocialAccountActivity.this.showDialog(206);
              SocialAccountActivity.this.fetchThumbnail();
              continue;
            }
            if (this.val$responseType != 902)
              continue;
            SocialAccountActivity.this.socialAPI.clearCredentials();
            SocialAccountActivity.this.removeCurrentDialog();
            SocialAccountActivity.access$502(SocialAccountActivity.this, SocialAccountActivity.this.getString(2131362451));
            if (SocialAccountActivity.this.isFinishing())
              continue;
            SocialAccountActivity.this.showDialog(204);
            continue;
          }
        }
      }
    });
  }

  public void onFollowVlingoComplete(int paramInt, String paramString)
  {
  }

  public void onLoginComplete(int paramInt, boolean paramBoolean, String paramString)
  {
    runOnUiThread(new Runnable(paramInt, paramString)
    {
      public void run()
      {
        if ((SocialAccountActivity.this.isFinishing()) || (SocialAccountActivity.this.mDestroyed))
          SocialAccountActivity.this.socialAPI.clearCredentials();
        while (true)
        {
          return;
          if (this.val$result == 0)
          {
            if (!SocialAccountActivity.this.isFinishing())
              SocialAccountActivity.this.showDialog(206);
            ((TwitterAPI)SocialAccountActivity.this.socialAPI).verifyCredentials();
            continue;
          }
          if (SocialAccountActivity.this.twitterResultFailed(this.val$result))
          {
            SocialAccountActivity.this.socialAPI.clearCredentials();
            SocialAccountActivity.access$502(SocialAccountActivity.this, this.val$errMessage);
            if (SocialAccountActivity.this.isFinishing())
              continue;
            SocialAccountActivity.this.showDialog(204);
            continue;
          }
          if (this.val$result != 5)
            continue;
          SocialAccountActivity.this.cancelLoginDialog();
        }
      }
    });
  }

  public void onLoginResult(boolean paramBoolean, Hashtable<Object, Object> paramHashtable)
  {
    runOnUiThread(new Runnable(paramBoolean, paramHashtable)
    {
      public void run()
      {
        if ((SocialAccountActivity.this.isFinishing()) || (SocialAccountActivity.this.mDestroyed))
          SocialAccountActivity.this.socialAPI.clearCredentials();
        while (true)
        {
          return;
          SocialAccountActivity.this.removeCurrentDialog();
          if (this.val$success)
          {
            SocialAccountActivity.this.fetchThumbnail();
            continue;
          }
          SocialAccountActivity.access$502(SocialAccountActivity.this, (String)this.val$params.get("error"));
          if (SocialAccountActivity.this.errorMessage.equals("cancelled"))
          {
            SocialAccountActivity.this.onBackPressed();
            SocialAccountActivity.this.socialAPI.clearCredentials();
            continue;
          }
          if (SocialAccountActivity.this.isFinishing())
            continue;
          SocialAccountActivity.this.showDialog(204);
        }
      }
    });
  }

  protected void onPause()
  {
    if (this.curDialogID != -1)
      removeDialog(this.curDialogID);
    if (this.screenEventBroadcastReceiver != null)
    {
      unregisterReceiver(this.screenEventBroadcastReceiver);
      this.screenEventBroadcastReceiver = null;
    }
    if (this.socialAPI != null)
      this.socialAPI.dismissDialogs();
    super.onPause();
  }

  public void onQZoneFail(int paramInt)
  {
    if (paramInt == 0)
      cancelLoginDialog();
    if (paramInt == 3)
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          SocialAccountActivity.this.socialAPI.clearCredentials();
          SocialAccountActivity.this.removeCurrentDialog();
          SocialAccountActivity.access$502(SocialAccountActivity.this, SocialAccountActivity.this.getString(2131362453));
          if (!SocialAccountActivity.this.isFinishing())
            SocialAccountActivity.this.showDialog(204);
          SocialAccountActivity.this.cancelLoginDialog();
        }
      });
  }

  public void onQZoneSuccess(int paramInt)
  {
    if (paramInt == 1)
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          SocialAccountActivity.this.fetchThumbnail();
        }
      });
  }

  protected void onResume()
  {
    super.onResume();
    this.screenEventBroadcastReceiver = new ScreenEventBroadcastReceiver(null);
    registerReceiver(this.screenEventBroadcastReceiver, new IntentFilter("android.intent.action.SCREEN_OFF"));
    if ((this.socialAPI.intentType().equals("facebook")) && (FacebookSSOAPI.facebookSSO()))
    {
      if (!FacebookSSOAPI.isFacebookLoggedIn())
      {
        FacebookSSOAPI.startFacebookSSO(getApplicationContext(), false);
        finish();
        return;
      }
    }
    else
    {
      if ((this.socialAPI.intentType().equals("facebook")) && (this.socialAPI.intentType().equals("weibo")))
        break label185;
      if ((this.curDialogID != -1) && (!isFinishing()))
        showDialog(this.curDialogID);
      if (this.socialAPI != null)
        this.socialAPI.showDialogs();
    }
    while (true)
    {
      Intent localIntent = new Intent(this, VlingoApplicationService.class);
      localIntent.setAction("com.vlingo.client.app.action.ACTIVITY_STATE_CHANGED");
      localIntent.putExtra("com.vlingo.client.app.extra.STATE", 0);
      startService(localIntent);
      break;
      label185: FbDialog localFbDialog = (FbDialog)this.socialAPI.getDialog();
      if (localFbDialog == null)
        continue;
      if (localFbDialog.isDialogActive)
      {
        if ((this.curDialogID != -1) && (!isFinishing()))
          showDialog(this.curDialogID);
        if (this.socialAPI == null)
          continue;
        this.socialAPI.showDialogs();
        continue;
      }
      localFbDialog.isDialogActive = true;
      finish();
    }
  }

  public void onUpdateComplete(int paramInt, String paramString)
  {
  }

  public void onVerifyComplete(int paramInt, String paramString)
  {
    runOnUiThread(new Runnable(paramInt)
    {
      public void run()
      {
        if ((SocialAccountActivity.this.isFinishing()) || (SocialAccountActivity.this.mDestroyed))
          SocialAccountActivity.this.socialAPI.clearCredentials();
        while (true)
        {
          return;
          SocialAccountActivity.this.removeCurrentDialog();
          if ((this.val$result == 0) && (!SocialAccountActivity.this.isFinishing()))
          {
            SocialAccountActivity.this.showDialog(206);
            SocialAccountActivity.this.fetchThumbnail();
            continue;
          }
          SocialAccountActivity.access$502(SocialAccountActivity.this, SocialAccountActivity.this.getString(2131362456));
          if (SocialAccountActivity.this.isFinishing())
            continue;
          SocialAccountActivity.this.showDialog(204);
        }
      }
    });
  }

  public void onVlingoFriendshipExists(int paramInt, boolean paramBoolean, String paramString)
  {
  }

  public void onWeiboFail(int paramInt)
  {
    if (paramInt == 0)
      cancelLoginDialog();
    if (paramInt == 3)
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          SocialAccountActivity.this.socialAPI.clearCredentials();
          SocialAccountActivity.this.removeCurrentDialog();
          SocialAccountActivity.access$502(SocialAccountActivity.this, SocialAccountActivity.this.getString(2131362454));
          if (!SocialAccountActivity.this.isFinishing())
            SocialAccountActivity.this.showDialog(204);
          SocialAccountActivity.this.cancelLoginDialog();
        }
      });
  }

  public void onWeiboSuccess(int paramInt)
  {
    if (paramInt == 1)
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          SocialAccountActivity.this.fetchThumbnail();
        }
      });
  }

  public boolean twitterResultFailed(int paramInt)
  {
    int i = 1;
    if ((paramInt == i) || (paramInt == 2) || (paramInt == 3) || (paramInt == 4));
    while (true)
    {
      return i;
      i = 0;
    }
  }

  private class ScreenEventBroadcastReceiver extends BroadcastReceiver
  {
    private ScreenEventBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramIntent != null) && ("android.intent.action.SCREEN_OFF".equals(paramIntent.getAction())) && (!SocialAccountActivity.this.socialAPI.isLoggedIn()))
        SocialAccountActivity.this.cancelLoginDialog();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.social.SocialAccountActivity
 * JD-Core Version:    0.6.0
 */