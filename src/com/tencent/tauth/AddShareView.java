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
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.tauth.http.Callback;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.Enumeration;

public class AddShareView extends Activity
{
  private ProgressDialog dialog;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
        if (AddShareView.this.dialog == null)
        {
          AddShareView.this.dialog = new ProgressDialog(AddShareView.this);
          AddShareView.this.dialog.setMessage("请求中,请稍候...");
        }
        if ((AddShareView.this.isFinishing()) || (AddShareView.this.dialog.isShowing()))
          break;
      case 0:
      }
      try
      {
        AddShareView.this.dialog.show();
        while (true)
        {
          return;
          if ((AddShareView.this.dialog != null) && (AddShareView.this.dialog.isShowing()))
          {
            AddShareView.this.dialog.dismiss();
            AddShareView.this.dialog = null;
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        while (true)
          Log.d("AddShareView", "activity is finished.");
      }
    }
  };
  private JsInterface js_interface = new JsInterface(new Callback()
  {
    public void onCancel(int paramInt)
    {
      if ((AddShareView.this.dialog != null) && (AddShareView.this.dialog.isShowing()))
        AddShareView.this.dialog.dismiss();
      AddShareView.this.finish();
    }

    public void onFail(int paramInt, String paramString)
    {
      AddShareView.this.runOnUiThread(new AddShareView.1.2(this, paramInt, paramString));
      if ((AddShareView.this.dialog != null) && (AddShareView.this.dialog.isShowing()))
        AddShareView.this.dialog.dismiss();
      AddShareView.this.finish();
    }

    public void onSuccess(Object paramObject)
    {
      AddShareView.this.runOnUiThread(new AddShareView.1.1(this));
      if ((AddShareView.this.dialog != null) && (AddShareView.this.dialog.isShowing()))
        AddShareView.this.dialog.dismiss();
      AddShareView.this.finish();
    }
  });
  private WebView mWebView;
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

  private void setWinTitle(String paramString)
  {
    this.titleTxt.setText(paramString);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    String str15;
    if (localBundle != null)
    {
      String str1 = localBundle.getString("client_id");
      localBundle.getString("scope");
      String str2 = localBundle.getString("access_token");
      String str3 = localBundle.getString("target");
      String str4 = localBundle.getString("openid");
      String str5 = localBundle.getString("title");
      String str6 = localBundle.getString("url");
      String str7 = localBundle.getString("comment");
      String str8 = localBundle.getString("summary");
      String str9 = localBundle.getString("images");
      String str10 = localBundle.getString("type");
      String str11 = localBundle.getString("playurl");
      String str12 = localBundle.getString("source");
      String str13 = localBundle.getString("site");
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = str2;
      arrayOfObject[1] = str1;
      arrayOfObject[2] = str4;
      String str14 = String.format("http://qzs.qq.com/open/mobile/sendstory/sdk_sendstory.html?access_token=%s&oauth_consumer_key=%s&openid=%s&", arrayOfObject);
      StringBuilder localStringBuilder = new StringBuilder();
      if (str5 != null)
      {
        localStringBuilder.append(URLEncoder.encode("title") + "=" + URLEncoder.encode(str5));
        localStringBuilder.append("&");
      }
      if (str6 != null)
      {
        localStringBuilder.append(URLEncoder.encode("url") + "=" + URLEncoder.encode(str6));
        localStringBuilder.append("&");
      }
      if (str7 != null)
      {
        localStringBuilder.append(URLEncoder.encode("comment") + "=" + URLEncoder.encode(str7));
        localStringBuilder.append("&");
      }
      if (str8 != null)
      {
        localStringBuilder.append(URLEncoder.encode("summary") + "=" + URLEncoder.encode(str8));
        localStringBuilder.append("&");
      }
      if (str9 != null)
      {
        localStringBuilder.append(URLEncoder.encode("images") + "=" + URLEncoder.encode(str9));
        localStringBuilder.append("&");
      }
      if (str10 != null)
      {
        localStringBuilder.append(URLEncoder.encode("type") + "=" + URLEncoder.encode(str10));
        localStringBuilder.append("&");
      }
      if (str11 != null)
      {
        localStringBuilder.append(URLEncoder.encode("playurl") + "=" + URLEncoder.encode(str11));
        localStringBuilder.append("&");
      }
      if (str12 != null)
      {
        localStringBuilder.append(URLEncoder.encode("source") + "=" + URLEncoder.encode(str12));
        localStringBuilder.append("&");
      }
      if (str13 != null)
        localStringBuilder.append(URLEncoder.encode("site") + "=" + URLEncoder.encode(str13));
      str15 = str14 + localStringBuilder.toString();
      if (str3.equals("_blank"))
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str15)));
        finish();
      }
    }
    while (true)
    {
      return;
      requestWindowFeature(1);
      getWindow().setFlags(1024, 1024);
      LinearLayout localLinearLayout = new LinearLayout(this);
      localLinearLayout.setOrientation(1);
      localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
      RelativeLayout localRelativeLayout = new RelativeLayout(this);
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      localRelativeLayout.setLayoutParams(localLayoutParams);
      localRelativeLayout.setPadding(0, 0, 0, 0);
      localRelativeLayout.setBackgroundDrawable(TencentOpenRes.getTitleBg(getAssets()));
      localLinearLayout.addView(localRelativeLayout, new ViewGroup.LayoutParams(-1, -2));
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
      this.mWebView.loadUrl(str15);
      this.mWebView.setFocusable(true);
      this.mWebView.requestFocus();
      continue;
      try
      {
        finish();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
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
      AddShareView.this.handler.sendEmptyMessage(0);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      AddShareView.this.handler.sendEmptyMessage(1);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      AddShareView.this.handler.sendEmptyMessage(0);
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }

    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      paramSslErrorHandler.proceed();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      AddShareView.this.handler.sendEmptyMessage(1);
      return false;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.AddShareView
 * JD-Core Version:    0.6.0
 */