package com.vlingo.core.internal.social.api;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookAPI extends SocialAPI
{
  private static final String ACCEPTED_TEXT_TAG_STRING = "facebook:status";
  private static final String APP_ID = Settings.getString("facebook_app_id", "39010226174");
  public static final int ERROR_FACEBOOK = 2;
  public static final int ERROR_FILE_NOT_FOUND = 3;
  public static final int ERROR_IO_EXCEPTION = 4;
  public static final int ERROR_JSON = 1;
  public static final int ERROR_MALFORMED_URL = 5;
  public static final String FACEBOOK_ACCOUNT_TYPE = "com.facebook.auth.login";
  private static final String FB_ID = "147940453810";
  private static final String INTENT_STRING = "facebook";
  public static final String ON_FACEBOOK_ERROR = "on_facebook_error";
  private static final String[] PERMISSIONS;
  public static final String SAMSUNG_FB_ACCOUNT_TYPE = "com.sec.android.app.sns3.facebook";
  public static final int TYPE_INTENT = 8;
  private volatile FacebookAPICallback callback;
  private final Facebook fb;
  private final AsyncFacebookRunner runner;

  static
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "publish_stream";
    arrayOfString[1] = "offline_access";
    PERMISSIONS = arrayOfString;
  }

  public FacebookAPI(FacebookAPICallback paramFacebookAPICallback)
  {
    super("facebook_account", "facebook_picture_url", "facebook", -1, -1, VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_social_logout_facebook_msg), VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_social_logout_facebook), VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_wcis_social_facebook), -1, -1, 8, -1, 2147483647, true, true, "facebook:status");
    this.callback = paramFacebookAPICallback;
    this.fb = new Facebook(APP_ID);
    this.runner = new AsyncFacebookRunner(this.fb);
    refreshCredentials();
  }

  private static JSONObject parseJson(String paramString)
    throws JSONException, FacebookError
  {
    if (paramString.equals("false"))
      throw new FacebookError("request failed");
    if (paramString.equals("true"))
      paramString = "{value : true}";
    JSONObject localJSONObject1 = new JSONObject(paramString);
    if (localJSONObject1.has("error"))
    {
      JSONObject localJSONObject2 = localJSONObject1.getJSONObject("error");
      throw new FacebookError(localJSONObject2.getString("message"), localJSONObject2.getString("type"), 0);
    }
    if ((localJSONObject1.has("error_code")) && (localJSONObject1.has("error_msg")))
      throw new FacebookError(localJSONObject1.getString("error_msg"), "", Integer.parseInt(localJSONObject1.getString("error_code")));
    if (localJSONObject1.has("error_code"))
      throw new FacebookError("request failed", "", Integer.parseInt(localJSONObject1.getString("error_code")));
    if (localJSONObject1.has("error_msg"))
      throw new FacebookError(localJSONObject1.getString("error_msg"));
    if (localJSONObject1.has("error_reason"))
      throw new FacebookError(localJSONObject1.getString("error_reason"));
    return localJSONObject1;
  }

  public void authorizeCallback(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.fb.authorizeCallback(paramInt1, paramInt2, paramIntent);
  }

  public void clearCredentials()
  {
    Util.clearCookies(ApplicationAdapter.getInstance().getApplicationContext());
    Settings.setString("facebook_account_name", null);
    Settings.setString("facebook_token", null);
    Settings.setLong("facebook_expires", 0L);
    Settings.setBoolean("facebook_account", false);
    Settings.setImage("facebook_picture", null);
    Settings.setString("facebook_picture_url", null);
  }

  public void dismissDialogs()
  {
    this.fb.dismissDialogs();
  }

  public void fetchUserData()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", "picture,name");
    this.runner.request("me", localBundle, new AsyncRequestListener("me", null));
  }

  public void findUserIDWithName(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", "friends");
    this.runner.request("me", localBundle, new AsyncRequestListener("finduserid", paramString, null));
  }

  public void findUserIDWithNames(String[] paramArrayOfString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", "friends");
    this.runner.request("me", localBundle, new AsyncRequestListener("finduserid", paramArrayOfString, null));
  }

  public Dialog getDialog()
  {
    return this.fb.getDialog();
  }

  public void getFriends()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", "friends");
    this.runner.request("me", localBundle, new AsyncRequestListener("me.friends", null));
  }

  public boolean isLoggedIn()
  {
    return Settings.getBoolean("facebook_account", false);
  }

  public void likeVlingo()
  {
    Bundle localBundle = new Bundle();
    this.runner.request("147940453810/likes" + "", localBundle, "POST", new AsyncRequestListener("like_vlingo", null));
  }

  public boolean login(Activity paramActivity)
  {
    this.fb.authorize(paramActivity, PERMISSIONS, new LoginDialogListener(null));
    return true;
  }

  public void refreshCredentials()
  {
    if (isLoggedIn())
    {
      this.fb.setAccessToken(Settings.getString("facebook_token", null));
      this.fb.setAccessExpires(Settings.getLong("facebook_expires", 0L));
    }
  }

  public void save()
  {
    Settings.setString("facebook_token", this.fb.getAccessToken());
    Settings.setLong("facebook_expires", this.fb.getAccessExpires());
    Settings.setBoolean("facebook_account", true);
  }

  public void sendMesasge(String paramString1, String paramString2)
  {
  }

  public void sendWallPost(String paramString1, String paramString2)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("message", paramString2);
    this.runner.request(paramString1 + "/feed", localBundle, "POST", new AsyncRequestListener("wallpost", null));
  }

  public void showDialogs()
  {
    this.fb.showDialogs();
  }

  public void shutdown()
  {
    this.callback = new FacebookAPICallback()
    {
      public void onFacebookAPILogin(FacebookAPI paramFacebookAPI, int paramInt, Bundle paramBundle)
      {
        FacebookAPI.this.clearCredentials();
      }

      public void onFacebookAPIMethod(FacebookAPI paramFacebookAPI, int paramInt, String paramString, Bundle paramBundle)
      {
        FacebookAPI.this.clearCredentials();
      }
    };
  }

  public void updateDialog()
  {
    this.fb.updateDialog();
  }

  public void updateDialogs()
  {
    if (this.fb != null)
      this.fb.screenOrientationChange();
  }

  public void updateStatus(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("message", paramString);
    this.runner.request("me/feed", localBundle, "POST", new AsyncRequestListener("stream.publish", null));
  }

  private class AsyncRequestListener
    implements AsyncFacebookRunner.RequestListener
  {
    private final String method;
    private Object ob;

    private AsyncRequestListener(String arg2)
    {
      Object localObject;
      this.method = localObject;
    }

    private AsyncRequestListener(String paramObject, Object arg3)
    {
      this.method = paramObject;
      Object localObject;
      this.ob = localObject;
    }

    private void onError(String paramString, int paramInt)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("error", paramString);
      localBundle.putInt("error_id", paramInt);
      localBundle.putString("method", this.method);
      FacebookAPI.this.callback.onFacebookAPIMethod(FacebookAPI.this, 902, this.method, localBundle);
    }

    public void onComplete(String paramString)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("response", paramString);
      localBundle.putString("method", this.method);
      String str1 = null;
      JSONObject localJSONObject1;
      int n;
      label366: int i1;
      while (true)
      {
        String[] arrayOfString;
        Object localObject2;
        String str5;
        JSONArray localJSONArray2;
        int k;
        int m;
        try
        {
          localJSONObject1 = FacebookAPI.access$400(paramString);
          if (!this.method.equalsIgnoreCase("me"))
            continue;
          localBundle.putString("name", localJSONObject1.getString("name"));
          localBundle.putString("picture", localJSONObject1.getJSONObject("picture").getJSONObject("data").getString("url"));
          FacebookAPI.this.callback.onFacebookAPIMethod(FacebookAPI.this, 901, this.method, localBundle);
          if (str1 == null)
            continue;
          onError(str1, 1);
          return;
          if (!this.method.equalsIgnoreCase("me.friends"))
            continue;
          localBundle.putString("json", paramString);
          continue;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          str1 = localJSONException.getMessage();
          continue;
          if (!this.method.equalsIgnoreCase("finduserid"))
            break;
          if (!(this.ob instanceof String))
            continue;
          String str8 = (String)this.ob;
          Object localObject3 = null;
          String str9 = null;
          JSONArray localJSONArray3 = localJSONObject1.getJSONObject("friends").getJSONArray("data");
          int i2 = localJSONArray3.length();
          i3 = 0;
          if (i3 >= i2)
            continue;
          JSONObject localJSONObject4 = localJSONArray3.getJSONObject(i3);
          String str10 = localJSONObject4.getString("name");
          if (!str10.equalsIgnoreCase(str8))
            continue;
          localObject3 = str10;
          str9 = localJSONObject4.getString("id");
          if (localObject3 == null)
            continue;
          localBundle.putString("name", localObject3);
          localBundle.putString("id", str9);
          localBundle.putString("json", paramString);
          continue;
        }
        catch (FacebookError localFacebookError)
        {
          int i3;
          localFacebookError.printStackTrace();
          str1 = localFacebookError.getMessage();
          continue;
          i3++;
          continue;
          if (!(this.ob instanceof String[]))
            continue;
          arrayOfString = (String[])(String[])this.ob;
          localObject2 = null;
          str5 = null;
          localJSONArray2 = localJSONObject1.getJSONArray("data");
          k = localJSONArray2.length();
          m = 0;
          n = 0;
        }
        if ((n < arrayOfString.length) && (m == 0))
        {
          String str6 = arrayOfString[n];
          i1 = 0;
          label389: if ((i1 >= k) || (m != 0))
            break label628;
          JSONObject localJSONObject3 = localJSONArray2.getJSONObject(i1);
          String str7 = localJSONObject3.getString("name");
          if (!str7.equalsIgnoreCase(str6))
            break label634;
          localObject2 = str7;
          str5 = localJSONObject3.getString("id");
          m = 1;
          break label628;
        }
        if (localObject2 == null)
          continue;
        localBundle.putString("name", localObject2);
        localBundle.putString("id", str5);
      }
      String str2;
      Object localObject1;
      String str3;
      JSONArray localJSONArray1;
      int i;
      if (this.method.equalsIgnoreCase("finduserid_one_name"))
      {
        str2 = (String)this.ob;
        localObject1 = null;
        str3 = null;
        localJSONArray1 = localJSONObject1.getJSONArray("data");
        i = localJSONArray1.length();
      }
      for (int j = 0; ; j++)
      {
        if (j < i)
        {
          JSONObject localJSONObject2 = localJSONArray1.getJSONObject(j);
          String str4 = localJSONObject2.getString("name");
          if (!str4.equalsIgnoreCase(str2))
            continue;
          localObject1 = str4;
          str3 = localJSONObject2.getString("id");
        }
        if (localObject1 != null)
        {
          localBundle.putString("name", localObject1);
          localBundle.putString("id", str3);
        }
        localBundle.putString("json", paramString);
        break;
        if (this.method.equalsIgnoreCase("wallpost"))
          break;
        boolean bool = this.method.equalsIgnoreCase("like_vlingo");
        if (!bool)
          break;
        break;
        label628: n++;
        break label366;
        label634: i1++;
        break label389;
      }
    }

    public void onFacebookError(FacebookError paramFacebookError)
    {
      paramFacebookError.printStackTrace();
      onError(paramFacebookError.getMessage(), 2);
    }

    public void onFileNotFoundException(FileNotFoundException paramFileNotFoundException)
    {
      paramFileNotFoundException.printStackTrace();
      onError(null, 3);
    }

    public void onIOException(IOException paramIOException)
    {
      paramIOException.printStackTrace();
      onError(null, 4);
    }

    public void onMalformedURLException(MalformedURLException paramMalformedURLException)
    {
      paramMalformedURLException.printStackTrace();
      onError(null, 5);
    }
  }

  public static abstract interface FacebookAPICallback extends SocialAPI.SocialCallback
  {
    public static final int TYPE_CANCELLED = 903;
    public static final int TYPE_ERROR = 902;
    public static final int TYPE_SUCCESS = 901;

    public abstract void onFacebookAPILogin(FacebookAPI paramFacebookAPI, int paramInt, Bundle paramBundle);

    public abstract void onFacebookAPIMethod(FacebookAPI paramFacebookAPI, int paramInt, String paramString, Bundle paramBundle);
  }

  private class LoginDialogListener
    implements Facebook.DialogListener
  {
    private LoginDialogListener()
    {
    }

    public void onCancel()
    {
      FacebookAPI.this.callback.onFacebookAPILogin(FacebookAPI.this, 903, null);
    }

    public void onComplete(Bundle paramBundle)
    {
      FacebookAPI.this.save();
      FacebookAPI.this.callback.onFacebookAPILogin(FacebookAPI.this, 901, null);
    }

    public void onError(DialogError paramDialogError)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("error", paramDialogError.getMessage());
      localBundle.putBoolean("on_facebook_error", false);
      FacebookAPI.this.callback.onFacebookAPILogin(FacebookAPI.this, 902, localBundle);
    }

    public void onFacebookError(FacebookError paramFacebookError)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("error", paramFacebookError.getMessage());
      localBundle.putBoolean("on_facebook_error", true);
      FacebookAPI.this.callback.onFacebookAPILogin(FacebookAPI.this, 902, localBundle);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.FacebookAPI
 * JD-Core Version:    0.6.0
 */