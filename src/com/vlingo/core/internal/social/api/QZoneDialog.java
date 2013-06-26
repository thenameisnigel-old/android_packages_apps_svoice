package com.vlingo.core.internal.social.api;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook.DialogListener;
import com.tencent.tauth.TencentOpenAPI;
import com.tencent.tauth.bean.OpenId;
import com.tencent.tauth.http.Callback;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.drawable;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import java.util.HashMap;

public class QZoneDialog extends Dialog
{
  static final int MARGIN = 4;
  static final int PADDING = 2;
  static final int QZONE_BLACK = -1269476011;
  final float[] DIMENSIONS_LANDSCAPE;
  final float[] DIMENSIONS_PORTRAIT;
  FrameLayout.LayoutParams FILL;
  private String callback;
  private String client_id;
  private String mAccessToken;
  private String mAuthResult;
  private String mCallback;
  private LinearLayout mContent;
  QZoneDialog mDialog;
  private String mErrorDes;
  private String mExpiresIn;
  private String mGraphURL;
  private Facebook.DialogListener mListener;
  private String mRet;
  private ProgressDialog mSpinner;
  private TextView mTitle;
  private String mUrl;
  private WebView mWebView;
  private String scope;

  public QZoneDialog(Context paramContext, String paramString1, String paramString2, String paramString3, Facebook.DialogListener paramDialogListener)
  {
    super(paramContext);
    float[] arrayOfFloat1 = new float[2];
    arrayOfFloat1[0] = 600.0F;
    arrayOfFloat1[1] = 280.0F;
    this.DIMENSIONS_LANDSCAPE = arrayOfFloat1;
    float[] arrayOfFloat2 = new float[2];
    arrayOfFloat2[0] = 300.0F;
    arrayOfFloat2[1] = 460.0F;
    this.DIMENSIONS_PORTRAIT = arrayOfFloat2;
    this.mCallback = "auth://tauth.qq.com/";
    this.FILL = new FrameLayout.LayoutParams(-1, -1);
    this.mGraphURL = ("https://graph.qq.com/oauth2.0/authorize?response_type=token&display=mobile&client_id=%s&scope=%s&redirect_uri=%s&status_userip=%s&status_os=%s&status_machine=%s&status_version=%s#" + System.currentTimeMillis());
    this.client_id = paramString1;
    this.scope = paramString2;
    this.mListener = paramDialogListener;
    if ((paramString3 != null) && (!paramString3.equals("")))
      this.callback = paramString3;
    String str = this.mGraphURL;
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = this.client_id;
    arrayOfObject[1] = this.scope;
    arrayOfObject[2] = this.callback;
    arrayOfObject[3] = " ";
    arrayOfObject[4] = getOS();
    arrayOfObject[5] = getMachine();
    arrayOfObject[6] = getVersion();
    this.mUrl = String.format(str, arrayOfObject);
    this.mDialog = this;
  }

  private String getMachine()
  {
    return Build.MODEL;
  }

  private String getOS()
  {
    return Build.VERSION.RELEASE;
  }

  private void getOpenID(String paramString)
  {
    TencentOpenAPI.openid(paramString, new Callback(paramString)
    {
      public void onCancel(int paramInt)
      {
        QZoneDialog.this.mListener.onCancel();
      }

      public void onFail(int paramInt, String paramString)
      {
        QZoneDialog.this.mListener.onError(new DialogError(paramString, paramInt, null));
      }

      public void onSuccess(Object paramObject)
      {
        Bundle localBundle = new Bundle();
        localBundle.putString("open_id", ((OpenId)paramObject).getOpenId());
        localBundle.putString("access_token", this.val$access_token);
        QZoneDialog.this.mListener.onComplete(localBundle);
      }
    });
  }

  private String getVersion()
  {
    return Build.VERSION.SDK;
  }

  private boolean isWindowShowing()
  {
    if ((isShowing()) && (getWindow() != null) && (getWindow().getWindowManager() != null));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void parseResult(String paramString)
  {
    this.mAuthResult = paramString;
    if (paramString.startsWith(this.mCallback + "?#"));
    HashMap localHashMap;
    for (String str = paramString.substring(1 + paramString.indexOf('#')); ; str = paramString.substring(1 + paramString.indexOf('?')))
    {
      String[] arrayOfString1 = str.split("&");
      localHashMap = new HashMap();
      int i = arrayOfString1.length;
      for (int j = 0; j < i; j++)
      {
        String[] arrayOfString2 = arrayOfString1[j].split("=");
        localHashMap.put(arrayOfString2[0], arrayOfString2[1]);
      }
    }
    this.mAccessToken = ((String)localHashMap.get("access_token"));
    this.mExpiresIn = ((String)localHashMap.get("expires_in"));
    this.mRet = ((String)localHashMap.get("error"));
    this.mErrorDes = ((String)localHashMap.get("error_description"));
    getOpenID(this.mAccessToken);
  }

  // ERROR //
  private void removeSpinner()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 150	com/vlingo/core/internal/social/api/QZoneDialog:mSpinner	Landroid/app/ProgressDialog;
    //   6: ifnull +57 -> 63
    //   9: aload_0
    //   10: invokespecial 249	com/vlingo/core/internal/social/api/QZoneDialog:isWindowShowing	()Z
    //   13: ifeq +50 -> 63
    //   16: aload_0
    //   17: getfield 150	com/vlingo/core/internal/social/api/QZoneDialog:mSpinner	Landroid/app/ProgressDialog;
    //   20: invokevirtual 252	android/app/ProgressDialog:isShowing	()Z
    //   23: ifeq +40 -> 63
    //   26: aload_0
    //   27: getfield 150	com/vlingo/core/internal/social/api/QZoneDialog:mSpinner	Landroid/app/ProgressDialog;
    //   30: invokevirtual 253	android/app/ProgressDialog:getWindow	()Landroid/view/Window;
    //   33: ifnull +30 -> 63
    //   36: aload_0
    //   37: getfield 150	com/vlingo/core/internal/social/api/QZoneDialog:mSpinner	Landroid/app/ProgressDialog;
    //   40: invokevirtual 253	android/app/ProgressDialog:getWindow	()Landroid/view/Window;
    //   43: invokevirtual 191	android/view/Window:getWindowManager	()Landroid/view/WindowManager;
    //   46: astore_2
    //   47: aload_2
    //   48: ifnull +15 -> 63
    //   51: aload_0
    //   52: getfield 150	com/vlingo/core/internal/social/api/QZoneDialog:mSpinner	Landroid/app/ProgressDialog;
    //   55: invokevirtual 256	android/app/ProgressDialog:dismiss	()V
    //   58: aload_0
    //   59: aconst_null
    //   60: putfield 150	com/vlingo/core/internal/social/api/QZoneDialog:mSpinner	Landroid/app/ProgressDialog;
    //   63: aload_0
    //   64: monitorexit
    //   65: return
    //   66: astore_1
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: athrow
    //   71: astore_3
    //   72: goto -14 -> 58
    //
    // Exception table:
    //   from	to	target	type
    //   2	47	66	finally
    //   51	58	66	finally
    //   58	63	66	finally
    //   51	58	71	java/lang/Exception
  }

  private void setUpTitle()
  {
    requestWindowFeature(1);
    Drawable localDrawable = VlingoAndroidCore.getResourceProvider().getDrawable(ResourceIdProvider.drawable.core_qzone_icon);
    this.mTitle = new TextView(getContext());
    this.mTitle.setText(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_qzone_dialog_title));
    this.mTitle.setTextColor(-1);
    this.mTitle.setTypeface(Typeface.DEFAULT_BOLD);
    this.mTitle.setBackgroundColor(-1269476011);
    this.mTitle.setPadding(6, 4, 4, 4);
    this.mTitle.setCompoundDrawablePadding(6);
    this.mTitle.setCompoundDrawablesWithIntrinsicBounds(localDrawable, null, null, null);
    this.mContent.addView(this.mTitle);
  }

  private void setUpWebView()
  {
    this.mWebView = new WebView(getContext());
    this.mWebView.setVerticalScrollBarEnabled(false);
    this.mWebView.setHorizontalScrollBarEnabled(false);
    this.mWebView.getSettings().setJavaScriptEnabled(true);
    this.mWebView.getSettings().setSavePassword(false);
    this.mWebView.getSettings().setSaveFormData(false);
    this.mWebView.getSettings().getUserAgentString();
    this.mWebView.setWebViewClient(new MyWebViewClient(null));
    this.mWebView.loadUrl(this.mUrl);
    this.mWebView.setLayoutParams(this.FILL);
    this.mContent.addView(this.mWebView);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_util_loading);
    this.mSpinner = new ProgressDialog(getContext());
    this.mSpinner.requestWindowFeature(1);
    this.mSpinner.setMessage(str);
    this.mSpinner.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
      {
        QZoneDialog.this.mDialog.cancel();
        return false;
      }
    });
    requestWindowFeature(1);
    this.mContent = new LinearLayout(getContext());
    this.mContent.setOrientation(1);
    setUpTitle();
    setUpWebView();
    updateView();
    setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        QZoneDialog.this.mDialog.cancel();
        QZoneDialog.this.mListener.onCancel();
      }
    });
  }

  protected void onStop()
  {
    removeSpinner();
    super.onStop();
  }

  public void updateView()
  {
    Display localDisplay = getWindow().getWindowManager().getDefaultDisplay();
    float f = getContext().getResources().getDisplayMetrics().density;
    if (localDisplay.getWidth() < localDisplay.getHeight());
    for (float[] arrayOfFloat = this.DIMENSIONS_PORTRAIT; ; arrayOfFloat = this.DIMENSIONS_LANDSCAPE)
    {
      setContentView(this.mContent, new FrameLayout.LayoutParams((int)(0.5F + f * arrayOfFloat[0]), (int)(0.5F + f * arrayOfFloat[1])));
      return;
    }
  }

  private class MyWebViewClient extends WebViewClient
  {
    private MyWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      if ((QZoneDialog.this.mSpinner != null) && (QZoneDialog.this.mSpinner.isShowing()))
        QZoneDialog.this.mSpinner.dismiss();
      QZoneDialog.this.mWebView.setVisibility(0);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      if ((QZoneDialog.this.isShowing()) && (QZoneDialog.this.mSpinner != null))
        QZoneDialog.this.mSpinner.show();
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      QZoneDialog.this.mListener.onError(new DialogError(paramString1, paramInt, paramString2));
      QZoneDialog.this.dismiss();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      if (paramString.startsWith(QZoneDialog.this.mCallback + "?"))
        QZoneDialog.this.parseResult(paramString);
      for (int i = 1; ; i = 0)
        return i;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.QZoneDialog
 * JD-Core Version:    0.6.0
 */