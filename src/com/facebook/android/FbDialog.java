package com.facebook.android;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.sdk.internal.deviceinfo.PhoneInfo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FbDialog extends Dialog
{
  static final float[] DIMENSIONS_LANDSCAPE;
  static final float[] DIMENSIONS_LANDSCAPE_TABLET;
  static final float[] DIMENSIONS_PORTRAIT;
  static final float[] DIMENSIONS_PORTRAIT_TABLET;
  static final String DISPLAY_STRING = "touch";
  static final int FB_BLUE = -9599820;
  static final String FB_ICON = "icon.png";
  static final FrameLayout.LayoutParams FILL;
  static final Set<String> KONA_DEVICES;
  static final int MARGIN = 4;
  static final int PADDING = 2;
  private Handler handler;
  public boolean isDialogActive = true;
  private LinearLayout mContent;
  private FbDialog mDialog;
  private Facebook.DialogListener mListener;
  private ProgressDialog mSpinner;
  private TextView mTitle;
  private String mUrl;
  private WebView mWebView;

  static
  {
    float[] arrayOfFloat1 = new float[2];
    arrayOfFloat1[0] = 460.0F;
    arrayOfFloat1[1] = 260.0F;
    DIMENSIONS_LANDSCAPE = arrayOfFloat1;
    float[] arrayOfFloat2 = new float[2];
    arrayOfFloat2[0] = 280.0F;
    arrayOfFloat2[1] = 420.0F;
    DIMENSIONS_PORTRAIT = arrayOfFloat2;
    float[] arrayOfFloat3 = new float[2];
    arrayOfFloat3[0] = 280.0F;
    arrayOfFloat3[1] = 350.0F;
    DIMENSIONS_LANDSCAPE_TABLET = arrayOfFloat3;
    float[] arrayOfFloat4 = new float[2];
    arrayOfFloat4[0] = 280.0F;
    arrayOfFloat4[1] = 360.0F;
    DIMENSIONS_PORTRAIT_TABLET = arrayOfFloat4;
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "GT-N5100";
    arrayOfString[1] = "GT-N5110";
    arrayOfString[2] = "GT-N5120";
    arrayOfString[3] = "SGH-I467";
    arrayOfString[4] = "SHW-M500W";
    KONA_DEVICES = new HashSet(Arrays.asList(arrayOfString));
    FILL = new FrameLayout.LayoutParams(-1, -1);
  }

  public FbDialog(Context paramContext, String paramString, Facebook.DialogListener paramDialogListener)
  {
    super(paramContext);
    this.mUrl = paramString;
    this.mListener = paramDialogListener;
    this.handler = new Handler();
    this.mDialog = this;
  }

  private boolean isWindowShowing()
  {
    if ((isShowing()) && (getWindow() != null) && (getWindow().getWindowManager() != null));
    for (int i = 1; ; i = 0)
      return i;
  }

  // ERROR //
  private void removeSpinner()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 129	com/facebook/android/FbDialog:mSpinner	Landroid/app/ProgressDialog;
    //   6: ifnull +57 -> 63
    //   9: aload_0
    //   10: invokespecial 163	com/facebook/android/FbDialog:isWindowShowing	()Z
    //   13: ifeq +50 -> 63
    //   16: aload_0
    //   17: getfield 129	com/facebook/android/FbDialog:mSpinner	Landroid/app/ProgressDialog;
    //   20: invokevirtual 166	android/app/ProgressDialog:isShowing	()Z
    //   23: ifeq +40 -> 63
    //   26: aload_0
    //   27: getfield 129	com/facebook/android/FbDialog:mSpinner	Landroid/app/ProgressDialog;
    //   30: invokevirtual 167	android/app/ProgressDialog:getWindow	()Landroid/view/Window;
    //   33: ifnull +30 -> 63
    //   36: aload_0
    //   37: getfield 129	com/facebook/android/FbDialog:mSpinner	Landroid/app/ProgressDialog;
    //   40: invokevirtual 167	android/app/ProgressDialog:getWindow	()Landroid/view/Window;
    //   43: invokevirtual 159	android/view/Window:getWindowManager	()Landroid/view/WindowManager;
    //   46: astore_2
    //   47: aload_2
    //   48: ifnull +15 -> 63
    //   51: aload_0
    //   52: getfield 129	com/facebook/android/FbDialog:mSpinner	Landroid/app/ProgressDialog;
    //   55: invokevirtual 170	android/app/ProgressDialog:dismiss	()V
    //   58: aload_0
    //   59: aconst_null
    //   60: putfield 129	com/facebook/android/FbDialog:mSpinner	Landroid/app/ProgressDialog;
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
    Drawable localDrawable = VlingoAndroidCore.getResourceProvider().getDrawable(ResourceIdProvider.drawable.core_facebook_icon);
    this.mTitle = new TextView(getContext());
    this.mTitle.setText("Facebook");
    this.mTitle.setTextColor(-1);
    this.mTitle.setTypeface(Typeface.DEFAULT_BOLD);
    this.mTitle.setBackgroundColor(-9599820);
    this.mTitle.setPadding(6, 4, 4, 4);
    this.mTitle.setCompoundDrawablePadding(6);
    this.mTitle.setCompoundDrawablesWithIntrinsicBounds(localDrawable, null, null, null);
    this.mContent.addView(this.mTitle);
  }

  private void setUpWebView()
  {
    if (this.mWebView == null)
    {
      this.mWebView = new WebView(getContext());
      this.mWebView.setLayoutParams(FILL);
      this.mContent.addView(this.mWebView);
    }
    this.mWebView.setVerticalScrollBarEnabled(false);
    this.mWebView.setHorizontalScrollBarEnabled(false);
    this.mWebView.setWebViewClient(new FbWebViewClient(null));
    this.mWebView.getSettings().setJavaScriptEnabled(true);
    this.mWebView.getSettings().setSavePassword(false);
    this.mWebView.getSettings().setSaveFormData(false);
    this.mWebView.getSettings().getUserAgentString();
    this.mWebView.loadUrl(this.mUrl, new HashMap()
    {
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    String str = VlingoAndroidCore.getResourceProvider().getString(ResourceIdProvider.string.core_car_util_loading);
    super.onCreate(paramBundle);
    this.mSpinner = new ProgressDialog(getContext());
    this.mSpinner.requestWindowFeature(1);
    this.mSpinner.setMessage(str);
    this.mSpinner.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        FbDialog.this.mDialog.cancel();
      }
    });
    getWindow().requestFeature(1);
    getWindow().setBackgroundDrawable(new ColorDrawable(0));
    getWindow().setSoftInputMode(19);
    this.mContent = new LinearLayout(getContext());
    this.mContent.setOrientation(1);
    setUpTitle();
    setUpWebView();
    updateView();
    setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        FbDialog.this.mListener.onCancel();
      }
    });
  }

  protected void onStop()
  {
    removeSpinner();
    super.onStop();
  }

  public void updateDialog()
  {
    Display localDisplay = getWindow().getWindowManager().getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    float f = localDisplayMetrics.density;
    if (KONA_DEVICES.contains(PhoneInfo.getInstance().getModel()))
    {
      arrayOfFloat = new float[2];
      arrayOfFloat[0] = (0.7F * localDisplay.getWidth());
      arrayOfFloat[1] = (0.7F * localDisplay.getHeight());
      f = 1.0F;
      getWindow().setLayout((int)(0.5F + f * arrayOfFloat[0]), (int)(0.5F + f * arrayOfFloat[1]));
      ViewGroup.LayoutParams localLayoutParams = this.mContent.getLayoutParams();
      localLayoutParams.width = (int)(0.5F + f * arrayOfFloat[0]);
      localLayoutParams.height = (int)(0.5F + f * arrayOfFloat[1]);
      this.mContent.setLayoutParams(localLayoutParams);
      return;
    }
    if ((f == 1.0D) && (localDisplayMetrics.heightPixels + localDisplayMetrics.widthPixels == 2032) && (localDisplayMetrics.xdpi == 149.82489F) && (localDisplayMetrics.ydpi == 150.51852F))
    {
      if (localDisplay.getWidth() < localDisplay.getHeight());
      for (arrayOfFloat = DIMENSIONS_PORTRAIT_TABLET; ; arrayOfFloat = DIMENSIONS_LANDSCAPE_TABLET)
      {
        f = 1.5F;
        break;
      }
    }
    if (localDisplay.getWidth() < localDisplay.getHeight());
    for (float[] arrayOfFloat = DIMENSIONS_PORTRAIT; ; arrayOfFloat = DIMENSIONS_LANDSCAPE)
      break;
  }

  public void updateView()
  {
    Display localDisplay = getWindow().getWindowManager().getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    float f = localDisplayMetrics.density;
    if (KONA_DEVICES.contains(PhoneInfo.getInstance().getModel()))
    {
      arrayOfFloat = new float[2];
      arrayOfFloat[0] = (0.7F * localDisplay.getWidth());
      arrayOfFloat[1] = (0.7F * localDisplay.getHeight());
      f = 1.0F;
      setContentView(this.mContent, new FrameLayout.LayoutParams((int)(0.5F + f * arrayOfFloat[0]), (int)(0.5F + f * arrayOfFloat[1])));
      return;
    }
    if ((f == 1.0D) && (localDisplayMetrics.heightPixels + localDisplayMetrics.widthPixels == 2032) && (localDisplayMetrics.xdpi == 149.82489F) && (localDisplayMetrics.ydpi == 150.51852F))
    {
      if (localDisplay.getWidth() < localDisplay.getHeight());
      for (arrayOfFloat = DIMENSIONS_PORTRAIT_TABLET; ; arrayOfFloat = DIMENSIONS_LANDSCAPE_TABLET)
      {
        f = 1.5F;
        break;
      }
    }
    if (localDisplay.getWidth() < localDisplay.getHeight());
    for (float[] arrayOfFloat = DIMENSIONS_PORTRAIT; ; arrayOfFloat = DIMENSIONS_LANDSCAPE)
      break;
  }

  private class FbWebViewClient extends WebViewClient
  {
    private FbWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      FbDialog.this.handler.post(new FbDialog.FbWebViewClient.1(this));
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      if ((FbDialog.this.isShowing()) && (FbDialog.this.mSpinner != null))
        FbDialog.this.mSpinner.show();
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      FbDialog.this.mListener.onError(new DialogError(paramString1, paramInt, paramString2));
      FbDialog.this.dismiss();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      boolean bool = true;
      FbDialog.this.isDialogActive = bool;
      String str;
      if (paramString.startsWith("fbconnect://success"))
      {
        Bundle localBundle = Util.parseUrl(paramString);
        str = localBundle.getString("error");
        if (str == null)
          str = localBundle.getString("error_type");
        if (str == null)
        {
          FbDialog.this.mListener.onComplete(localBundle);
          FbDialog.this.dismiss();
        }
      }
      while (true)
      {
        return bool;
        if ((str.equals("access_denied")) || (str.equals("OAuthAccessDeniedException")))
        {
          FbDialog.this.mListener.onCancel();
          break;
        }
        FbDialog.this.mListener.onFacebookError(new FacebookError(str));
        break;
        if (paramString.startsWith("fbconnect://cancel"))
        {
          FbDialog.this.mListener.onCancel();
          FbDialog.this.dismiss();
          continue;
        }
        if (paramString.contains("touch"))
        {
          bool = false;
          continue;
        }
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        localIntent.setFlags(335544320);
        FbDialog.this.getContext().startActivity(localIntent);
        FbDialog.this.isDialogActive = false;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.facebook.android.FbDialog
 * JD-Core Version:    0.6.0
 */