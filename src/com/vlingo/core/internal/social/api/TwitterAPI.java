package com.vlingo.core.internal.social.api;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import com.facebook.android.Util;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.core.internal.util.UrlUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

public class TwitterAPI extends SocialAPI
  implements TwitterDialogListener
{
  private static final String ACCEPTED_TEXT_TAG_STRING = "twitter:def";
  private static final Uri CALLBACK_URI;
  private static final String INTENT_STRING = "twitter";
  public static final int RESULT_CANCELLED = 5;
  public static final int RESULT_FAIL = 1;
  public static final int RESULT_FAIL_AUTH = 2;
  public static final int RESULT_FAIL_FORBIDDEN = 3;
  public static final int RESULT_FAIL_TWITTER_DOWN = 4;
  public static final int RESULT_SUCCESS = 0;
  private static final String TWITTER_ACCESS_TOKEN_URL = "https://api.twitter.com/oauth/access_token";
  private static final String TWITTER_AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
  private static final String TWITTER_CONSUMER_KEY = Settings.getString("twitter_consumer_key", "AGv8Ps3AlFKrf2C1YoFkQ");
  private static final String TWITTER_CONSUMER_SECRET = Settings.getString("twitter_consumer_secret", "qeX5TCXa9HPDlpNmhPACOT7sUerHPmD91Oq9nYuw6Q");
  private static final String TWITTER_REQUEST_TOKEN_URL = "https://api.twitter.com/oauth/request_token";
  public static final int TYPE_INTENT = 4;
  private static final String URL_TWITTER_CREATE = "http://api.twitter.com:80/1/friendships/create/vlingo.json";
  private static final String URL_TWITTER_FRIENDSHIP = "http://api.twitter.com:80/1/friendships/exists.json?user_a={userA}&user_b={userB}";
  private static final String URL_TWITTER_UPDATE = "http://api.twitter.com:80/1/statuses/update.json";
  private static final String URL_TWITTER_VERIFY = "http://api.twitter.com:80/1/account/verify_credentials.json";
  private volatile TwitterCallback callback;
  private Context context;
  private OAuthTask mAuthTask = null;
  private HttpClient mClient;
  private OAuthConsumer mConsumer;
  private OAuthProvider mProvider;
  private String mSecret;
  private String mToken;
  private TwitterDialog mTwitterDialog;
  private volatile boolean shutdown = false;

  static
  {
    CALLBACK_URI = Uri.parse("http://m.vlingo.com/twitterCallback");
  }

  public TwitterAPI(TwitterCallback paramTwitterCallback)
  {
    super("twitter_account", "twitter_picture_url", "twitter", -1, -1, VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_social_logout_twitter_msg), VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_social_logout_twitter), VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_wcis_social_twitter), -1, -1, 4, -1, 140, true, true, "twitter:def");
    this.callback = paramTwitterCallback;
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(localBasicHttpParams, "ISO-8859-1");
    HttpProtocolParams.setUseExpectContinue(localBasicHttpParams, false);
    HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    ThreadSafeClientConnManager localThreadSafeClientConnManager = new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry);
    this.mClient = new DefaultHttpClient(localThreadSafeClientConnManager, localBasicHttpParams);
    this.mConsumer = new CommonsHttpOAuthConsumer(TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET);
    refreshCredentials();
  }

  public static boolean startsWith(String paramString)
  {
    return paramString.startsWith(CALLBACK_URI.toString());
  }

  public void checkFriendshipWithUser(String paramString)
  {
    CheckFriendshipTask localCheckFriendshipTask = new CheckFriendshipTask(null);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    localCheckFriendshipTask.execute(arrayOfString);
  }

  public void clearCredentials()
  {
    Util.clearCookies(ApplicationAdapter.getInstance().getApplicationContext());
    Settings.setString("twitter_account_name", null);
    Settings.setString("twitter_request_secret", null);
    Settings.setString("twitter_request_token", null);
    Settings.setString("twitter_user_secret", null);
    Settings.setString("twitter_user_token", null);
    Settings.setString("twitter_username", null);
    Settings.setString("twitter_username", null);
    Settings.setString("twitter_picture_url", null);
    Settings.setImage("twitter_picture", null);
    Settings.setBoolean("twitter_account", false);
  }

  public void dismissDialogs()
  {
    if ((this.mAuthTask != null) && (this.mAuthTask.getStatus() == AsyncTask.Status.RUNNING))
      this.mAuthTask.cancel(true);
    if (this.mTwitterDialog != null)
      this.mTwitterDialog.dismissDialogs();
  }

  public boolean followVlingo()
  {
    new FollowVlingoTask(null).execute((String[])null);
    return true;
  }

  public Context getContext()
  {
    return this.context;
  }

  public Dialog getDialog()
  {
    return this.mTwitterDialog;
  }

  public HttpParams getParams()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpProtocolParams.setUseExpectContinue(localBasicHttpParams, false);
    return localBasicHttpParams;
  }

  public boolean login(Activity paramActivity)
  {
    this.context = paramActivity;
    this.mProvider = new CommonsHttpOAuthProvider("https://api.twitter.com/oauth/request_token", "https://api.twitter.com/oauth/access_token", "https://api.twitter.com/oauth/authorize");
    this.mProvider.setOAuth10a(true);
    this.mTwitterDialog = new TwitterDialog(getContext(), "", this);
    this.mTwitterDialog.show();
    this.mAuthTask = new OAuthTask(null);
    OAuthTask localOAuthTask = this.mAuthTask;
    TwitterAPI[] arrayOfTwitterAPI = new TwitterAPI[1];
    arrayOfTwitterAPI[0] = this;
    localOAuthTask.execute(arrayOfTwitterAPI);
    return true;
  }

  public void onCancel()
  {
    this.callback.onLoginComplete(5, false, null);
  }

  public void onComplete(Bundle paramBundle)
  {
    if (this.shutdown);
    while (true)
    {
      return;
      String str1 = Settings.getString("twitter_request_token", null);
      String str2 = Settings.getString("twitter_request_secret", null);
      if ((str1 != null) && (str2 != null))
        this.mConsumer.setTokenWithSecret(str1, str2);
      new Thread(paramBundle.getString("oauth_token"), paramBundle.getString("oauth_verifier"))
      {
        public void run()
        {
          if ((this.val$otoken != null) && (this.val$verifier != null))
          {
            try
            {
              if (TwitterAPI.this.shutdown)
                return;
              TwitterAPI.this.mProvider.retrieveAccessToken(TwitterAPI.this.mConsumer, this.val$verifier);
              Settings.setString("twitter_user_token", TwitterAPI.this.mConsumer.getToken());
              Settings.setString("twitter_user_secret", TwitterAPI.this.mConsumer.getTokenSecret());
              Settings.setString("twitter_request_token", null);
              Settings.setString("twitter_request_secret", null);
              TwitterAPI.this.callback.onLoginComplete(0, true, null);
            }
            catch (OAuthMessageSignerException localOAuthMessageSignerException)
            {
              localOAuthMessageSignerException.printStackTrace();
              String str2 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_err_auth1);
              TwitterAPI.this.callback.onLoginComplete(2, false, str2);
            }
            catch (OAuthNotAuthorizedException localOAuthNotAuthorizedException)
            {
              while (true)
                localOAuthNotAuthorizedException.printStackTrace();
            }
            catch (OAuthExpectationFailedException localOAuthExpectationFailedException)
            {
              while (true)
                localOAuthExpectationFailedException.printStackTrace();
            }
            catch (OAuthCommunicationException localOAuthCommunicationException)
            {
              while (true)
                localOAuthCommunicationException.printStackTrace();
            }
          }
          else
          {
            String str1 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_err_auth2);
            TwitterAPI.this.callback.onLoginComplete(2, false, str1);
          }
        }
      }
      .start();
    }
  }

  public void onError(String paramString)
  {
    String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_error);
    this.callback.onLoginComplete(1, false, str + paramString);
  }

  public void onTwitterError(String paramString)
  {
    String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_twitter_error);
    this.callback.onLoginComplete(1, false, str + paramString);
  }

  public void refreshCredentials()
  {
    this.mToken = Settings.getString("twitter_user_token", null);
    this.mSecret = Settings.getString("twitter_user_secret", null);
    if ((this.mToken != null) && (this.mSecret != null))
      this.mConsumer.setTokenWithSecret(this.mToken, this.mSecret);
  }

  public void showDialogs()
  {
    if (this.mTwitterDialog != null)
      this.mTwitterDialog.showDialogs();
  }

  public void shutdown()
  {
    this.shutdown = true;
    this.callback = new TwitterCallback()
    {
      public void onFollowVlingoComplete(int paramInt, String paramString)
      {
      }

      public void onLoginComplete(int paramInt, boolean paramBoolean, String paramString)
      {
      }

      public void onUpdateComplete(int paramInt, String paramString)
      {
      }

      public void onVerifyComplete(int paramInt, String paramString)
      {
      }

      public void onVlingoFriendshipExists(int paramInt, boolean paramBoolean, String paramString)
      {
      }
    };
    if (this.mTwitterDialog != null)
    {
      this.mTwitterDialog.dismissDialogs();
      this.mTwitterDialog = null;
    }
    if ((this.mAuthTask != null) && (this.mAuthTask.getStatus() == AsyncTask.Status.RUNNING))
      this.mAuthTask.cancel(true);
    this.mClient.getConnectionManager().shutdown();
  }

  public void updateDialogs()
  {
    if (this.mTwitterDialog != null)
      this.mTwitterDialog.updateDialog();
  }

  public void updateStatus(String paramString)
  {
    if (paramString == null)
      paramString = "";
    PostTask localPostTask = new PostTask(null);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    localPostTask.execute(arrayOfString);
  }

  public void verifyCredentials()
  {
    refreshCredentials();
    new GetCredentialsTask(null).execute(new Void[0]);
  }

  private class CheckFriendshipTask extends AsyncTask<String, Void, String>
  {
    private CheckFriendshipTask()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      String str = null;
      try
      {
        HttpGet localHttpGet = new HttpGet(StringUtils.replace(StringUtils.replace("http://api.twitter.com:80/1/friendships/exists.json?user_a={userA}&user_b={userB}", "{userA}", UrlUtils.urlEncode(Settings.getString("twitter_username", null))), "{userB}", UrlUtils.urlEncode(paramArrayOfString[0])));
        str = (String)TwitterAPI.this.mClient.execute(localHttpGet, new BasicResponseHandler());
        return str;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        while (true)
          localUnsupportedEncodingException.printStackTrace();
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        while (true)
          localClientProtocolException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
      catch (IllegalStateException localIllegalStateException)
      {
        while (true)
          localIllegalStateException.printStackTrace();
      }
    }

    protected void onPostExecute(String paramString)
    {
      if (paramString != null)
        TwitterAPI.this.callback.onVlingoFriendshipExists(0, "true".equalsIgnoreCase(paramString), null);
      while (true)
      {
        return;
        String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_twitter_error);
        TwitterAPI.this.callback.onVlingoFriendshipExists(1, false, str);
      }
    }
  }

  private class FollowVlingoTask extends AsyncTask<String, Void, JSONObject>
  {
    private FollowVlingoTask()
    {
    }

    protected JSONObject doInBackground(String[] paramArrayOfString)
    {
      Object localObject = null;
      try
      {
        HttpPost localHttpPost = new HttpPost("http://api.twitter.com:80/1/friendships/create/vlingo.json");
        LinkedList localLinkedList = new LinkedList();
        localLinkedList.add(new BasicNameValuePair("follow", "true"));
        localHttpPost.setEntity(new UrlEncodedFormEntity(localLinkedList, "UTF-8"));
        localHttpPost.setParams(TwitterAPI.this.getParams());
        TwitterAPI.this.mConsumer.sign(localHttpPost);
        JSONObject localJSONObject = new JSONObject((String)TwitterAPI.this.mClient.execute(localHttpPost, new BasicResponseHandler()));
        localObject = localJSONObject;
        return localObject;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        while (true)
          localUnsupportedEncodingException.printStackTrace();
      }
      catch (OAuthMessageSignerException localOAuthMessageSignerException)
      {
        while (true)
          localOAuthMessageSignerException.printStackTrace();
      }
      catch (OAuthExpectationFailedException localOAuthExpectationFailedException)
      {
        while (true)
          localOAuthExpectationFailedException.printStackTrace();
      }
      catch (OAuthCommunicationException localOAuthCommunicationException)
      {
        while (true)
          localOAuthCommunicationException.printStackTrace();
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        while (true)
          localClientProtocolException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
      catch (JSONException localJSONException)
      {
        while (true)
          localJSONException.printStackTrace();
      }
      catch (IllegalStateException localIllegalStateException)
      {
        while (true)
          localIllegalStateException.printStackTrace();
      }
    }

    protected void onPostExecute(JSONObject paramJSONObject)
    {
      if (paramJSONObject != null)
        TwitterAPI.this.callback.onFollowVlingoComplete(0, null);
      while (true)
      {
        return;
        String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_twitter_error);
        TwitterAPI.this.callback.onFollowVlingoComplete(1, str);
      }
    }
  }

  private class GetCredentialsTask extends AsyncTask<Void, Void, JSONObject>
  {
    private GetCredentialsTask()
    {
    }

    protected JSONObject doInBackground(Void[] paramArrayOfVoid)
    {
      Object localObject = null;
      HttpGet localHttpGet = new HttpGet("http://api.twitter.com:80/1/account/verify_credentials.json");
      try
      {
        TwitterAPI.this.mConsumer.sign(localHttpGet);
        JSONObject localJSONObject = new JSONObject((String)TwitterAPI.this.mClient.execute(localHttpGet, new BasicResponseHandler()));
        localObject = localJSONObject;
        return localObject;
      }
      catch (OAuthMessageSignerException localOAuthMessageSignerException)
      {
        while (true)
          localOAuthMessageSignerException.printStackTrace();
      }
      catch (OAuthExpectationFailedException localOAuthExpectationFailedException)
      {
        while (true)
          localOAuthExpectationFailedException.printStackTrace();
      }
      catch (OAuthCommunicationException localOAuthCommunicationException)
      {
        while (true)
          localOAuthCommunicationException.printStackTrace();
      }
      catch (JSONException localJSONException)
      {
        while (true)
          localJSONException.printStackTrace();
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        while (true)
          localClientProtocolException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
      catch (IllegalStateException localIllegalStateException)
      {
        while (true)
          localIllegalStateException.printStackTrace();
      }
    }

    protected void onPostExecute(JSONObject paramJSONObject)
    {
      if (paramJSONObject != null)
      {
        String str2 = paramJSONObject.optString("name", null);
        String str3 = paramJSONObject.optString("screen_name", null);
        String str4 = paramJSONObject.optString("profile_image_url", null);
        Settings.setString("twitter_username", str3);
        Settings.setString("twitter_account_name", str2);
        Settings.setString("twitter_picture_url", str4);
        Settings.setBoolean("twitter_account", true);
        TwitterAPI.this.callback.onVerifyComplete(0, null);
      }
      while (true)
      {
        return;
        String str1 = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_twitter_update_error);
        TwitterAPI.this.callback.onVerifyComplete(1, str1);
      }
    }
  }

  private class OAuthTask extends AsyncTask<TwitterAPI, Void, Boolean>
  {
    private String authUrl = null;
    private int error = 1;
    private TwitterAPI parent = null;

    private OAuthTask()
    {
    }

    private void showTwitterDialog(Boolean paramBoolean)
    {
      if (paramBoolean.booleanValue());
      try
      {
        TwitterAPI.this.mTwitterDialog.updateWebView(this.authUrl);
        if (!paramBoolean.booleanValue())
        {
          TwitterAPI.this.clearCredentials();
          String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_api_twitter_err_login);
          TwitterAPI.this.callback.onLoginComplete(this.error, false, str);
        }
        return;
      }
      catch (Exception localException)
      {
        while (true)
          paramBoolean = Boolean.valueOf(false);
      }
    }

    protected Boolean doInBackground(TwitterAPI[] paramArrayOfTwitterAPI)
    {
      this.parent = paramArrayOfTwitterAPI[0];
      try
      {
        this.authUrl = TwitterAPI.this.mProvider.retrieveRequestToken(TwitterAPI.this.mConsumer, TwitterAPI.CALLBACK_URI.toString());
        Settings.setString("twitter_request_token", TwitterAPI.this.mConsumer.getToken());
        Settings.setString("twitter_request_secret", TwitterAPI.this.mConsumer.getTokenSecret());
        Boolean localBoolean2 = Boolean.valueOf(true);
        localBoolean1 = localBoolean2;
        return localBoolean1;
      }
      catch (OAuthMessageSignerException localOAuthMessageSignerException)
      {
        while (true)
        {
          localOAuthMessageSignerException.printStackTrace();
          Boolean localBoolean1 = Boolean.valueOf(false);
        }
      }
      catch (OAuthNotAuthorizedException localOAuthNotAuthorizedException)
      {
        while (true)
          localOAuthNotAuthorizedException.printStackTrace();
      }
      catch (OAuthExpectationFailedException localOAuthExpectationFailedException)
      {
        while (true)
          localOAuthExpectationFailedException.printStackTrace();
      }
      catch (OAuthCommunicationException localOAuthCommunicationException)
      {
        while (true)
        {
          this.error = 4;
          localOAuthCommunicationException.printStackTrace();
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        while (true)
          localIllegalStateException.printStackTrace();
      }
    }

    protected void onCancelled(Boolean paramBoolean)
    {
      if (paramBoolean != null)
        showTwitterDialog(paramBoolean);
    }

    protected void onPostExecute(Boolean paramBoolean)
    {
      showTwitterDialog(paramBoolean);
    }
  }

  private class PostTask extends AsyncTask<String, Void, JSONObject>
  {
    private int errorType = 1;

    private PostTask()
    {
    }

    // ERROR //
    protected JSONObject doInBackground(String[] paramArrayOfString)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_2
      //   2: new 49	org/apache/http/client/methods/HttpPost
      //   5: dup
      //   6: ldc 51
      //   8: invokespecial 54	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
      //   11: astore_3
      //   12: new 56	java/util/LinkedList
      //   15: dup
      //   16: invokespecial 57	java/util/LinkedList:<init>	()V
      //   19: astore 4
      //   21: aload 4
      //   23: new 59	org/apache/http/message/BasicNameValuePair
      //   26: dup
      //   27: ldc 61
      //   29: aload_1
      //   30: iconst_0
      //   31: aaload
      //   32: invokespecial 64	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   35: invokevirtual 68	java/util/LinkedList:add	(Ljava/lang/Object;)Z
      //   38: pop
      //   39: aload_3
      //   40: new 70	org/apache/http/client/entity/UrlEncodedFormEntity
      //   43: dup
      //   44: aload 4
      //   46: ldc 72
      //   48: invokespecial 75	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
      //   51: invokevirtual 79	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
      //   54: aload_3
      //   55: aload_0
      //   56: getfield 16	com/vlingo/core/internal/social/api/TwitterAPI$PostTask:this$0	Lcom/vlingo/core/internal/social/api/TwitterAPI;
      //   59: invokevirtual 83	com/vlingo/core/internal/social/api/TwitterAPI:getParams	()Lorg/apache/http/params/HttpParams;
      //   62: invokevirtual 87	org/apache/http/client/methods/HttpPost:setParams	(Lorg/apache/http/params/HttpParams;)V
      //   65: aload_0
      //   66: getfield 16	com/vlingo/core/internal/social/api/TwitterAPI$PostTask:this$0	Lcom/vlingo/core/internal/social/api/TwitterAPI;
      //   69: invokestatic 91	com/vlingo/core/internal/social/api/TwitterAPI:access$500	(Lcom/vlingo/core/internal/social/api/TwitterAPI;)Loauth/signpost/OAuthConsumer;
      //   72: aload_3
      //   73: invokeinterface 97 2 0
      //   78: pop
      //   79: new 99	org/json/JSONObject
      //   82: dup
      //   83: aload_0
      //   84: getfield 16	com/vlingo/core/internal/social/api/TwitterAPI$PostTask:this$0	Lcom/vlingo/core/internal/social/api/TwitterAPI;
      //   87: invokestatic 103	com/vlingo/core/internal/social/api/TwitterAPI:access$1000	(Lcom/vlingo/core/internal/social/api/TwitterAPI;)Lorg/apache/http/client/HttpClient;
      //   90: aload_3
      //   91: new 105	org/apache/http/impl/client/BasicResponseHandler
      //   94: dup
      //   95: invokespecial 106	org/apache/http/impl/client/BasicResponseHandler:<init>	()V
      //   98: invokeinterface 112 3 0
      //   103: checkcast 114	java/lang/String
      //   106: invokespecial 115	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   109: astore 15
      //   111: aload_0
      //   112: iconst_0
      //   113: putfield 21	com/vlingo/core/internal/social/api/TwitterAPI$PostTask:errorType	I
      //   116: aload 15
      //   118: astore_2
      //   119: aload_2
      //   120: areturn
      //   121: astore 12
      //   123: aload 12
      //   125: invokevirtual 118	java/io/UnsupportedEncodingException:printStackTrace	()V
      //   128: goto -9 -> 119
      //   131: astore 11
      //   133: aload 11
      //   135: invokevirtual 119	oauth/signpost/exception/OAuthMessageSignerException:printStackTrace	()V
      //   138: goto -19 -> 119
      //   141: astore 10
      //   143: aload 10
      //   145: invokevirtual 120	oauth/signpost/exception/OAuthExpectationFailedException:printStackTrace	()V
      //   148: goto -29 -> 119
      //   151: astore 9
      //   153: aload 9
      //   155: invokevirtual 121	oauth/signpost/exception/OAuthCommunicationException:printStackTrace	()V
      //   158: goto -39 -> 119
      //   161: astore 8
      //   163: aload_0
      //   164: iconst_3
      //   165: putfield 21	com/vlingo/core/internal/social/api/TwitterAPI$PostTask:errorType	I
      //   168: aload 8
      //   170: invokevirtual 122	org/apache/http/client/ClientProtocolException:printStackTrace	()V
      //   173: goto -54 -> 119
      //   176: astore 7
      //   178: aload_0
      //   179: iconst_4
      //   180: putfield 21	com/vlingo/core/internal/social/api/TwitterAPI$PostTask:errorType	I
      //   183: aload 7
      //   185: invokevirtual 123	java/io/IOException:printStackTrace	()V
      //   188: goto -69 -> 119
      //   191: astore 6
      //   193: aload 6
      //   195: invokevirtual 124	org/json/JSONException:printStackTrace	()V
      //   198: goto -79 -> 119
      //   201: astore 5
      //   203: aload 5
      //   205: invokevirtual 125	java/lang/IllegalStateException:printStackTrace	()V
      //   208: goto -89 -> 119
      //   211: astore 5
      //   213: aload 15
      //   215: astore_2
      //   216: goto -13 -> 203
      //   219: astore 6
      //   221: aload 15
      //   223: astore_2
      //   224: goto -31 -> 193
      //   227: astore 7
      //   229: aload 15
      //   231: astore_2
      //   232: goto -54 -> 178
      //   235: astore 8
      //   237: aload 15
      //   239: astore_2
      //   240: goto -77 -> 163
      //   243: astore 9
      //   245: aload 15
      //   247: astore_2
      //   248: goto -95 -> 153
      //   251: astore 10
      //   253: aload 15
      //   255: astore_2
      //   256: goto -113 -> 143
      //   259: astore 11
      //   261: aload 15
      //   263: astore_2
      //   264: goto -131 -> 133
      //   267: astore 12
      //   269: aload 15
      //   271: astore_2
      //   272: goto -149 -> 123
      //
      // Exception table:
      //   from	to	target	type
      //   2	111	121	java/io/UnsupportedEncodingException
      //   2	111	131	oauth/signpost/exception/OAuthMessageSignerException
      //   2	111	141	oauth/signpost/exception/OAuthExpectationFailedException
      //   2	111	151	oauth/signpost/exception/OAuthCommunicationException
      //   2	111	161	org/apache/http/client/ClientProtocolException
      //   2	111	176	java/io/IOException
      //   2	111	191	org/json/JSONException
      //   2	111	201	java/lang/IllegalStateException
      //   111	116	211	java/lang/IllegalStateException
      //   111	116	219	org/json/JSONException
      //   111	116	227	java/io/IOException
      //   111	116	235	org/apache/http/client/ClientProtocolException
      //   111	116	243	oauth/signpost/exception/OAuthCommunicationException
      //   111	116	251	oauth/signpost/exception/OAuthExpectationFailedException
      //   111	116	259	oauth/signpost/exception/OAuthMessageSignerException
      //   111	116	267	java/io/UnsupportedEncodingException
    }

    protected void onPostExecute(JSONObject paramJSONObject)
    {
      if (paramJSONObject != null)
      {
        TwitterAPI.this.callback.onUpdateComplete(0, null);
        return;
      }
      if (this.errorType == 4);
      for (String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_network_error); ; str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_social_update_failed))
      {
        TwitterAPI.this.callback.onUpdateComplete(this.errorType, str);
        break;
      }
    }
  }

  public static abstract interface TwitterCallback extends SocialAPI.SocialCallback
  {
    public abstract void onFollowVlingoComplete(int paramInt, String paramString);

    public abstract void onLoginComplete(int paramInt, boolean paramBoolean, String paramString);

    public abstract void onUpdateComplete(int paramInt, String paramString);

    public abstract void onVerifyComplete(int paramInt, String paramString);

    public abstract void onVlingoFriendshipExists(int paramInt, boolean paramBoolean, String paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.TwitterAPI
 * JD-Core Version:    0.6.0
 */