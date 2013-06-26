package com.vlingo.core.internal.social.api;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.drawable;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.util.Utility;

public class SinaWeiboDialog extends Dialog
{
  static final int MARGIN = 4;
  static final int PADDING = 2;
  private static final String URL_OAUTH_ACCESS_AUTH = "https://open.weibo.cn/oauth2/authorize";
  static final int WEIBO_RED = -1258356659;
  final float[] DIMENSIONS_LANDSCAPE;
  final float[] DIMENSIONS_PORTRAIT;
  FrameLayout.LayoutParams FILL;
  private LinearLayout mContent;
  private WeiboAuthListener mListener;
  private String mRedirectUrl;
  private ProgressDialog mSpinner;
  private TextView mTitle;
  private String mUrl;
  private WebView mWebView;

  public SinaWeiboDialog(Context paramContext, String paramString1, String paramString2, WeiboAuthListener paramWeiboAuthListener)
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
    this.FILL = new FrameLayout.LayoutParams(-1, -1);
    this.mRedirectUrl = paramString2;
    WeiboParameters localWeiboParameters = new WeiboParameters();
    localWeiboParameters.add("client_id", paramString1);
    localWeiboParameters.add("response_type", "token");
    localWeiboParameters.add("redirect_uri", this.mRedirectUrl);
    localWeiboParameters.add("display", "mobile");
    this.mUrl = ("https://open.weibo.cn/oauth2/authorize?" + Utility.encodeUrl(localWeiboParameters));
    this.mListener = paramWeiboAuthListener;
  }

  private void handleRedirectUrl(WebView paramWebView, String paramString)
  {
    Bundle localBundle = Utility.parseUrl(paramString);
    String str1 = localBundle.getString("error");
    String str2 = localBundle.getString("error_code");
    if ((str1 == null) && (str2 == null))
      this.mListener.onComplete(localBundle);
    while (true)
    {
      return;
      if (str1.equals("access_denied"))
      {
        this.mListener.onCancel();
        continue;
      }
    }
  }

  private boolean isWindowShowing()
  {
    if ((isShowing()) && (getWindow() != null) && (getWindow().getWindowManager() != null));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void removeSpinner()
  {
    if ((this.mSpinner != null) && (isWindowShowing()) && (this.mSpinner.isShowing()) && (this.mSpinner.getWindow() != null) && (this.mSpinner.getWindow().getWindowManager() != null));
    try
    {
      this.mSpinner.dismiss();
      label54: this.mSpinner = null;
      return;
    }
    catch (Exception localException)
    {
      break label54;
    }
  }

  private void setUpTitle()
  {
    requestWindowFeature(1);
    Drawable localDrawable = VlingoAndroidCore.getResourceProvider().getDrawable(ResourceIdProvider.drawable.core_weibo_icon);
    this.mTitle = new TextView(getContext());
    this.mTitle.setText(VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_weibo_dialog_title));
    this.mTitle.setTextColor(-1);
    this.mTitle.setTypeface(Typeface.DEFAULT_BOLD);
    this.mTitle.setBackgroundColor(-1258356659);
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
    this.mWebView.setWebViewClient(new WeiboWebViewClient(null));
    this.mWebView.loadUrl(this.mUrl);
    this.mWebView.setLayoutParams(this.FILL);
    this.mContent.addView(this.mWebView);
  }

  protected void onBack()
  {
    try
    {
      removeSpinner();
      if (this.mWebView != null)
      {
        this.mWebView.stopLoading();
        this.mWebView.destroy();
      }
      label25: dismiss();
      return;
    }
    catch (Exception localException)
    {
      break label25;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_util_loading);
    this.mSpinner = new ProgressDialog(getContext());
    this.mSpinner.requestWindowFeature(1);
    this.mSpinner.setMessage(str);
    this.mSpinner.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        SinaWeiboDialog.this.removeSpinner();
        SinaWeiboDialog.this.mListener.onCancel();
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
        SinaWeiboDialog.this.mListener.onCancel();
        SinaWeiboDialog.this.cancel();
      }
    });
  }

  public void updateDialog()
  {
    Display localDisplay = getWindow().getWindowManager().getDefaultDisplay();
    float f = getContext().getResources().getDisplayMetrics().density;
    if (localDisplay.getWidth() < localDisplay.getHeight());
    for (float[] arrayOfFloat = this.DIMENSIONS_PORTRAIT; ; arrayOfFloat = this.DIMENSIONS_LANDSCAPE)
    {
      getWindow().setLayout((int)(0.5F + f * arrayOfFloat[0]), (int)(0.5F + f * arrayOfFloat[1]));
      ViewGroup.LayoutParams localLayoutParams = this.mContent.getLayoutParams();
      localLayoutParams.width = (int)(0.5F + f * arrayOfFloat[0]);
      localLayoutParams.height = (int)(0.5F + f * arrayOfFloat[1]);
      this.mContent.setLayoutParams(localLayoutParams);
      return;
    }
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

  private class WeiboWebViewClient extends WebViewClient
  {
    private WeiboWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      SinaWeiboDialog.this.removeSpinner();
      SinaWeiboDialog.this.mWebView.setVisibility(0);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      if (paramString.startsWith(SinaWeiboDialog.this.mRedirectUrl))
      {
        SinaWeiboDialog.this.handleRedirectUrl(paramWebView, paramString);
        paramWebView.stopLoading();
        SinaWeiboDialog.this.dismiss();
      }
      while (true)
      {
        return;
        super.onPageStarted(paramWebView, paramString, paramBitmap);
        if ((SinaWeiboDialog.this.mSpinner == null) || (SinaWeiboDialog.this.mSpinner.isShowing()))
          continue;
        SinaWeiboDialog.this.mSpinner.show();
      }
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      SinaWeiboDialog.this.mListener.onError(new WeiboDialogError(paramString1, paramInt, paramString2));
      SinaWeiboDialog.this.dismiss();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.social.api.SinaWeiboDialog
 * JD-Core Version:    0.6.0
 */