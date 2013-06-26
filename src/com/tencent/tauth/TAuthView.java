package com.tencent.tauth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.tauth.bean.OpenId;
import com.tencent.tauth.http.Callback;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;

public class TAuthView extends Activity
{
  private String cache_callback;
  private String cache_client_id;
  private String cache_comment;
  private String cache_images;
  private String cache_playurl;
  private String cache_scope;
  private String cache_share_url;
  private String cache_site;
  private String cache_source;
  private String cache_summary;
  private String cache_target;
  private String cache_title;
  private String cache_type;
  private String cache_url;
  private ProgressDialog dialog;
  private String from_cmd = null;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        if (TAuthView.this.dialog == null)
        {
          TAuthView.this.dialog = new ProgressDialog(TAuthView.this);
          TAuthView.this.dialog.setMessage("请求中,请稍候...");
        }
        if ((TAuthView.this.isFinishing()) || (TAuthView.this.dialog.isShowing()))
          break;
      case 0:
      }
      try
      {
        TAuthView.this.dialog.show();
        while (true)
        {
          return;
          if ((TAuthView.this.dialog != null) && (TAuthView.this.dialog.isShowing()))
          {
            TAuthView.this.dialog.dismiss();
            TAuthView.this.dialog = null;
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        while (true)
          Log.d("tauthdemo", "activity is finished.");
      }
    }
  };
  private JsInterface js_interface = new JsInterface(new Callback()
  {
    public void onCancel(int paramInt)
    {
      if ((TAuthView.this.dialog != null) && (TAuthView.this.dialog.isShowing()))
        TAuthView.this.dialog.dismiss();
      TAuthView.this.finish();
    }

    public void onFail(int paramInt, String paramString)
    {
      if ((TAuthView.this.dialog != null) && (TAuthView.this.dialog.isShowing()))
        TAuthView.this.dialog.dismiss();
      TAuthView.this.finish();
    }

    public void onSuccess(Object paramObject)
    {
      if ((TAuthView.this.dialog != null) && (TAuthView.this.dialog.isShowing()))
        TAuthView.this.dialog.dismiss();
      TAuthView.this.finish();
    }
  });
  private String mAccessToken;
  private String mAuthResult;
  private String mCallback = "auth://tauth.qq.com/";
  private String mErrorDes;
  private String mExpiresIn;
  private String mGraphURL;
  private String mRet;
  private WebView mWebView;
  private String openidString = null;
  private TextView titleTxt;

  private String getIp()
  {
    try
    {
      Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
      break label39;
      label5: boolean bool = localEnumeration1.hasMoreElements();
      if (!bool);
      label39: String str;
      for (Object localObject = ""; ; localObject = str)
      {
        return localObject;
        Enumeration localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
        if (!localEnumeration2.hasMoreElements())
          break label5;
        InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
        if (localInetAddress.isLoopbackAddress())
          break;
        str = localInetAddress.getHostAddress().toString();
      }
    }
    catch (SocketException localSocketException)
    {
      while (true)
        Log.d("TAG", localSocketException.toString());
    }
  }

  private String getMachine()
  {
    return Build.MODEL;
  }

  private String getOS()
  {
    return Build.VERSION.RELEASE;
  }

  private String getVersion()
  {
    return Build.VERSION.SDK;
  }

  private void parseResult(String paramString)
  {
    this.mAuthResult = paramString;
    String str;
    String[] arrayOfString1;
    HashMap localHashMap;
    int i;
    if (paramString.startsWith(this.mCallback + "?#"))
    {
      str = paramString.substring(1 + paramString.indexOf('#'));
      arrayOfString1 = str.split("&");
      localHashMap = new HashMap();
      i = arrayOfString1.length;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        this.mAccessToken = ((String)localHashMap.get("access_token"));
        this.mExpiresIn = ((String)localHashMap.get("expires_in"));
        this.mRet = ((String)localHashMap.get("error"));
        this.mErrorDes = ((String)localHashMap.get("error_description"));
        return;
        str = paramString.substring(1 + paramString.indexOf('?'));
        break;
      }
      String[] arrayOfString2 = arrayOfString1[j].split("=");
      localHashMap.put(arrayOfString2[0], arrayOfString2[1]);
    }
  }

  private void returnResult()
  {
    sendBroadcastResult();
    if ((this.from_cmd != null) && (this.from_cmd.equals("send_store")))
      TencentOpenAPI.openid(this.mAccessToken, new Callback()
      {
        public void onCancel(int paramInt)
        {
        }

        public void onFail(int paramInt, String paramString)
        {
          TAuthView.this.runOnUiThread(new TAuthView.3.2(this, paramString));
        }

        public void onSuccess(Object paramObject)
        {
          TAuthView.this.openidString = ((OpenId)paramObject).getOpenId();
          Bundle localBundle = new Bundle();
          localBundle.putString("title", TAuthView.this.cache_title);
          localBundle.putString("url", TAuthView.this.cache_share_url);
          localBundle.putString("comment", TAuthView.this.cache_comment);
          localBundle.putString("summary", TAuthView.this.cache_summary);
          localBundle.putString("images", TAuthView.this.cache_images);
          localBundle.putString("type", TAuthView.this.cache_type);
          localBundle.putString("playurl", TAuthView.this.cache_playurl);
          localBundle.putString("source", TAuthView.this.cache_source);
          localBundle.putString("site", TAuthView.this.cache_site);
          TencentOpenAPI2.sendStore(TAuthView.this.getBaseContext(), TAuthView.this.mAccessToken, TAuthView.this.cache_client_id, TAuthView.this.openidString, TAuthView.this.cache_target, localBundle, new TAuthView.3.1(this), TAuthView.this.from_cmd);
        }
      });
    finish();
  }

  private void sendBroadcastResult()
  {
    Intent localIntent = new Intent("com.tencent.auth.BROWSER");
    localIntent.putExtra("raw", this.mAuthResult);
    localIntent.putExtra("access_token", this.mAccessToken);
    localIntent.putExtra("expires_in", this.mExpiresIn);
    localIntent.putExtra("error", this.mRet);
    localIntent.putExtra("error_description", this.mErrorDes);
    sendBroadcast(localIntent);
  }

  private void setWinTitle(String paramString)
  {
    this.titleTxt.setText(paramString);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mGraphURL = ("https://graph.qq.com/oauth2.0/authorize?response_type=token&display=mobile&client_id=%s&scope=%s&redirect_uri=%s&status_userip=%s&status_os=%s&status_machine=%s&status_version=%s&cancel_display=1#" + System.currentTimeMillis());
    Bundle localBundle = getIntent().getExtras();
    String str1;
    String str2;
    String str5;
    String str6;
    if (localBundle != null)
    {
      str1 = localBundle.getString("client_id");
      str2 = localBundle.getString("scope");
      String str3 = localBundle.getString("callback");
      if ((str3 != null) && (!str3.equals("")))
        this.mCallback = str3;
      this.cache_title = localBundle.getString("title");
      this.cache_share_url = localBundle.getString("url");
      this.cache_comment = localBundle.getString("comment");
      this.cache_summary = localBundle.getString("summary");
      this.cache_images = localBundle.getString("images");
      this.cache_type = localBundle.getString("type");
      this.cache_playurl = localBundle.getString("playurl");
      this.cache_source = localBundle.getString("source");
      this.cache_site = localBundle.getString("site");
      this.from_cmd = localBundle.getString("from_cmd");
      String str4 = this.mGraphURL;
      Object[] arrayOfObject1 = new Object[7];
      arrayOfObject1[0] = str1;
      arrayOfObject1[1] = str2;
      arrayOfObject1[2] = this.mCallback;
      arrayOfObject1[3] = getIp();
      arrayOfObject1[4] = getOS();
      arrayOfObject1[5] = getMachine();
      arrayOfObject1[6] = getVersion();
      str5 = String.format(str4, arrayOfObject1);
      str6 = localBundle.getString("target");
      this.cache_client_id = str1;
      this.cache_scope = str2;
      this.cache_callback = str3;
      this.cache_url = str5;
      this.cache_target = str6;
      if (str6 != null);
    }
    while (true)
    {
      try
      {
        parseResult(getIntent().getData().toString());
        sendBroadcastResult();
        finish();
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        continue;
      }
      if (str6.equals("_blank"))
      {
        String str7 = "https://graph.qq.com/oauth2.0/authorize?response_type=token&display=mobile&client_id=%s&scope=%s&redirect_uri=%s&status_userip=%s&status_os=%s&status_machine=%s&status_version=%s#" + System.currentTimeMillis();
        Object[] arrayOfObject2 = new Object[7];
        arrayOfObject2[0] = str1;
        arrayOfObject2[1] = str2;
        arrayOfObject2[2] = this.mCallback;
        arrayOfObject2[3] = getIp();
        arrayOfObject2[4] = getOS();
        arrayOfObject2[5] = getMachine();
        arrayOfObject2[6] = getVersion();
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format(str7, arrayOfObject2))));
        finish();
        continue;
      }
      requestWindowFeature(1);
      LinearLayout localLinearLayout = new LinearLayout(this);
      localLinearLayout.setOrientation(1);
      localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
      WebView localWebView1 = new WebView(this);
      this.mWebView = localWebView1;
      this.mWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
      localLinearLayout.addView(this.mWebView);
      this.mWebView.setInitialScale(100);
      this.mWebView.setVerticalScrollBarEnabled(false);
      WebView localWebView2 = this.mWebView;
      MyWebViewClient localMyWebViewClient = new MyWebViewClient(null);
      localWebView2.setWebViewClient(localMyWebViewClient);
      this.mWebView.clearFormData();
      this.mWebView.clearCache(true);
      this.mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
      WebSettings localWebSettings = this.mWebView.getSettings();
      localWebSettings.setJavaScriptEnabled(true);
      localWebSettings.setBuiltInZoomControls(true);
      localWebSettings.setSavePassword(false);
      localWebSettings.setSaveFormData(false);
      localWebSettings.setCacheMode(2);
      localWebSettings.setNeedInitialFocus(false);
      localWebSettings.setDomStorageEnabled(true);
      localWebSettings.setSupportZoom(true);
      this.mWebView.addJavascriptInterface(this.js_interface, "sdk_js_if");
      WebView localWebView3 = this.mWebView;
      MyWebChromeClient localMyWebChromeClient = new MyWebChromeClient(null);
      localWebView3.setWebChromeClient(localMyWebChromeClient);
      setContentView(localLinearLayout);
      this.mWebView.loadUrl(str5);
      this.mWebView.setFocusable(true);
      this.mWebView.requestFocus();
      continue;
      try
      {
        parseResult(getIntent().getData().toString());
        sendBroadcastResult();
        finish();
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.cancel();
  }

  private class MyWebChromeClient extends WebChromeClient
  {
    private MyWebChromeClient()
    {
    }

    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      super.onReceivedTitle(paramWebView, paramString);
    }
  }

  private class MyWebViewClient extends WebViewClient
  {
    private MyWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      TAuthView.this.handler.sendEmptyMessage(0);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      TAuthView.this.handler.sendEmptyMessage(1);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      TAuthView.this.handler.sendEmptyMessage(0);
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }

    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      paramSslErrorHandler.proceed();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      int i = 1;
      if (paramString.startsWith(TAuthView.this.mCallback + "?"))
      {
        TAuthView.this.parseResult(paramString);
        TAuthView.this.handler.sendEmptyMessage(0);
        TAuthView.this.returnResult();
      }
      while (true)
      {
        return i;
        TAuthView.this.handler.sendEmptyMessage(i);
        i = 0;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.TAuthView
 * JD-Core Version:    0.6.0
 */