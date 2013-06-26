package com.weibo.sdk.android;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.weibo.sdk.android.util.Utility;

public class WeiboDialog extends Dialog
{
  static FrameLayout.LayoutParams FILL = new FrameLayout.LayoutParams(-1, -1);
  private static final String TAG = "Weibo-WebView";
  private static int bottom_margin;
  private static int left_margin;
  private static int right_margin;
  private static int theme = 16973840;
  private static int top_margin;
  private RelativeLayout mContent;
  private WeiboAuthListener mListener;
  private ProgressDialog mSpinner;
  private String mUrl;
  private WebView mWebView;
  private RelativeLayout webViewContainer;

  static
  {
    left_margin = 0;
    top_margin = 0;
    right_margin = 0;
    bottom_margin = 0;
  }

  public WeiboDialog(Context paramContext, String paramString, WeiboAuthListener paramWeiboAuthListener)
  {
    super(paramContext, theme);
    this.mUrl = paramString;
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
      if (str2 == null)
      {
        this.mListener.onWeiboException(new WeiboException(str1, 0));
        continue;
      }
      this.mListener.onWeiboException(new WeiboException(str1, Integer.parseInt(str2)));
    }
  }

  // ERROR //
  private boolean parseDimens()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: invokevirtual 134	com/weibo/sdk/android/WeiboDialog:getContext	()Landroid/content/Context;
    //   6: invokevirtual 140	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   9: astore_2
    //   10: aload_0
    //   11: invokevirtual 134	com/weibo/sdk/android/WeiboDialog:getContext	()Landroid/content/Context;
    //   14: invokevirtual 144	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   17: invokevirtual 150	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   20: getfield 156	android/util/DisplayMetrics:density	F
    //   23: fstore_3
    //   24: aconst_null
    //   25: astore 4
    //   27: aload_2
    //   28: ldc 158
    //   30: invokevirtual 164	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   33: astore 4
    //   35: invokestatic 170	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   38: astore 9
    //   40: aload 9
    //   42: aload 4
    //   44: ldc 172
    //   46: invokeinterface 178 3 0
    //   51: aload 9
    //   53: invokeinterface 182 1 0
    //   58: istore 12
    //   60: iload 12
    //   62: istore 13
    //   64: iconst_1
    //   65: istore_1
    //   66: iload 13
    //   68: iconst_1
    //   69: if_icmpne +15 -> 84
    //   72: aload 4
    //   74: ifnull +8 -> 82
    //   77: aload 4
    //   79: invokevirtual 187	java/io/InputStream:close	()V
    //   82: iload_1
    //   83: ireturn
    //   84: iload 13
    //   86: tableswitch	default:+18 -> 104, 2:+30->116
    //   105: lconst_0
    //   106: invokeinterface 190 1 0
    //   111: istore 13
    //   113: goto -47 -> 66
    //   116: aload 9
    //   118: invokeinterface 194 1 0
    //   123: ldc 196
    //   125: invokevirtual 106	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   128: ifeq -24 -> 104
    //   131: aload 9
    //   133: aconst_null
    //   134: ldc 198
    //   136: invokeinterface 202 3 0
    //   141: astore 14
    //   143: ldc 204
    //   145: aload 14
    //   147: invokevirtual 106	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   150: ifeq +63 -> 213
    //   153: fload_3
    //   154: aload 9
    //   156: invokeinterface 207 1 0
    //   161: invokestatic 124	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   164: i2f
    //   165: fmul
    //   166: f2i
    //   167: putstatic 46	com/weibo/sdk/android/WeiboDialog:left_margin	I
    //   170: goto -66 -> 104
    //   173: astore 10
    //   175: aload 10
    //   177: invokevirtual 210	org/xmlpull/v1/XmlPullParserException:printStackTrace	()V
    //   180: goto -108 -> 72
    //   183: astore 7
    //   185: aload 7
    //   187: invokevirtual 211	java/io/IOException:printStackTrace	()V
    //   190: aload 4
    //   192: ifnull -110 -> 82
    //   195: aload 4
    //   197: invokevirtual 187	java/io/InputStream:close	()V
    //   200: goto -118 -> 82
    //   203: astore 8
    //   205: aload 8
    //   207: invokevirtual 211	java/io/IOException:printStackTrace	()V
    //   210: goto -128 -> 82
    //   213: ldc 213
    //   215: aload 14
    //   217: invokevirtual 106	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   220: ifeq +38 -> 258
    //   223: fload_3
    //   224: aload 9
    //   226: invokeinterface 207 1 0
    //   231: invokestatic 124	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   234: i2f
    //   235: fmul
    //   236: f2i
    //   237: putstatic 48	com/weibo/sdk/android/WeiboDialog:top_margin	I
    //   240: goto -136 -> 104
    //   243: astore 5
    //   245: aload 4
    //   247: ifnull +8 -> 255
    //   250: aload 4
    //   252: invokevirtual 187	java/io/InputStream:close	()V
    //   255: aload 5
    //   257: athrow
    //   258: ldc 215
    //   260: aload 14
    //   262: invokevirtual 106	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   265: ifeq +23 -> 288
    //   268: fload_3
    //   269: aload 9
    //   271: invokeinterface 207 1 0
    //   276: invokestatic 124	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   279: i2f
    //   280: fmul
    //   281: f2i
    //   282: putstatic 50	com/weibo/sdk/android/WeiboDialog:right_margin	I
    //   285: goto -181 -> 104
    //   288: ldc 217
    //   290: aload 14
    //   292: invokevirtual 106	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   295: ifeq -191 -> 104
    //   298: fload_3
    //   299: aload 9
    //   301: invokeinterface 207 1 0
    //   306: invokestatic 124	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   309: i2f
    //   310: fmul
    //   311: f2i
    //   312: putstatic 52	com/weibo/sdk/android/WeiboDialog:bottom_margin	I
    //   315: goto -211 -> 104
    //   318: astore 6
    //   320: aload 6
    //   322: invokevirtual 211	java/io/IOException:printStackTrace	()V
    //   325: goto -70 -> 255
    //   328: astore 11
    //   330: aload 11
    //   332: invokevirtual 211	java/io/IOException:printStackTrace	()V
    //   335: goto -253 -> 82
    //
    // Exception table:
    //   from	to	target	type
    //   40	60	173	org/xmlpull/v1/XmlPullParserException
    //   104	170	173	org/xmlpull/v1/XmlPullParserException
    //   213	240	173	org/xmlpull/v1/XmlPullParserException
    //   258	315	173	org/xmlpull/v1/XmlPullParserException
    //   27	40	183	java/io/IOException
    //   40	60	183	java/io/IOException
    //   104	170	183	java/io/IOException
    //   175	180	183	java/io/IOException
    //   213	240	183	java/io/IOException
    //   258	315	183	java/io/IOException
    //   195	200	203	java/io/IOException
    //   27	40	243	finally
    //   40	60	243	finally
    //   104	170	243	finally
    //   175	180	243	finally
    //   185	190	243	finally
    //   213	240	243	finally
    //   258	315	243	finally
    //   250	255	318	java/io/IOException
    //   77	82	328	java/io/IOException
  }

  // ERROR //
  private void setUpWebView()
  {
    // Byte code:
    //   0: aload_0
    //   1: new 222	android/widget/RelativeLayout
    //   4: dup
    //   5: aload_0
    //   6: invokevirtual 134	com/weibo/sdk/android/WeiboDialog:getContext	()Landroid/content/Context;
    //   9: invokespecial 225	android/widget/RelativeLayout:<init>	(Landroid/content/Context;)V
    //   12: putfield 227	com/weibo/sdk/android/WeiboDialog:webViewContainer	Landroid/widget/RelativeLayout;
    //   15: aload_0
    //   16: new 229	android/webkit/WebView
    //   19: dup
    //   20: aload_0
    //   21: invokevirtual 134	com/weibo/sdk/android/WeiboDialog:getContext	()Landroid/content/Context;
    //   24: invokespecial 230	android/webkit/WebView:<init>	(Landroid/content/Context;)V
    //   27: putfield 76	com/weibo/sdk/android/WeiboDialog:mWebView	Landroid/webkit/WebView;
    //   30: aload_0
    //   31: getfield 76	com/weibo/sdk/android/WeiboDialog:mWebView	Landroid/webkit/WebView;
    //   34: iconst_0
    //   35: invokevirtual 234	android/webkit/WebView:setVerticalScrollBarEnabled	(Z)V
    //   38: aload_0
    //   39: getfield 76	com/weibo/sdk/android/WeiboDialog:mWebView	Landroid/webkit/WebView;
    //   42: iconst_0
    //   43: invokevirtual 237	android/webkit/WebView:setHorizontalScrollBarEnabled	(Z)V
    //   46: aload_0
    //   47: getfield 76	com/weibo/sdk/android/WeiboDialog:mWebView	Landroid/webkit/WebView;
    //   50: invokevirtual 241	android/webkit/WebView:getSettings	()Landroid/webkit/WebSettings;
    //   53: iconst_1
    //   54: invokevirtual 246	android/webkit/WebSettings:setJavaScriptEnabled	(Z)V
    //   57: aload_0
    //   58: getfield 76	com/weibo/sdk/android/WeiboDialog:mWebView	Landroid/webkit/WebView;
    //   61: new 8	com/weibo/sdk/android/WeiboDialog$WeiboWebViewClient
    //   64: dup
    //   65: aload_0
    //   66: aconst_null
    //   67: invokespecial 249	com/weibo/sdk/android/WeiboDialog$WeiboWebViewClient:<init>	(Lcom/weibo/sdk/android/WeiboDialog;Lcom/weibo/sdk/android/WeiboDialog$WeiboWebViewClient;)V
    //   70: invokevirtual 253	android/webkit/WebView:setWebViewClient	(Landroid/webkit/WebViewClient;)V
    //   73: aload_0
    //   74: getfield 76	com/weibo/sdk/android/WeiboDialog:mWebView	Landroid/webkit/WebView;
    //   77: aload_0
    //   78: getfield 58	com/weibo/sdk/android/WeiboDialog:mUrl	Ljava/lang/String;
    //   81: invokevirtual 257	android/webkit/WebView:loadUrl	(Ljava/lang/String;)V
    //   84: aload_0
    //   85: getfield 76	com/weibo/sdk/android/WeiboDialog:mWebView	Landroid/webkit/WebView;
    //   88: getstatic 41	com/weibo/sdk/android/WeiboDialog:FILL	Landroid/widget/FrameLayout$LayoutParams;
    //   91: invokevirtual 261	android/webkit/WebView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   94: aload_0
    //   95: getfield 76	com/weibo/sdk/android/WeiboDialog:mWebView	Landroid/webkit/WebView;
    //   98: iconst_4
    //   99: invokevirtual 265	android/webkit/WebView:setVisibility	(I)V
    //   102: new 267	android/widget/RelativeLayout$LayoutParams
    //   105: dup
    //   106: bipush 255
    //   108: bipush 255
    //   110: invokespecial 268	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   113: astore_1
    //   114: new 267	android/widget/RelativeLayout$LayoutParams
    //   117: dup
    //   118: bipush 255
    //   120: bipush 255
    //   122: invokespecial 268	android/widget/RelativeLayout$LayoutParams:<init>	(II)V
    //   125: astore_2
    //   126: aload_0
    //   127: getfield 270	com/weibo/sdk/android/WeiboDialog:mContent	Landroid/widget/RelativeLayout;
    //   130: iconst_0
    //   131: invokevirtual 273	android/widget/RelativeLayout:setBackgroundColor	(I)V
    //   134: aload_0
    //   135: invokevirtual 134	com/weibo/sdk/android/WeiboDialog:getContext	()Landroid/content/Context;
    //   138: invokevirtual 140	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   141: astore_3
    //   142: aconst_null
    //   143: astore 4
    //   145: aload_3
    //   146: ldc_w 275
    //   149: invokevirtual 164	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   152: astore 4
    //   154: aload_0
    //   155: invokevirtual 134	com/weibo/sdk/android/WeiboDialog:getContext	()Landroid/content/Context;
    //   158: invokevirtual 144	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   161: invokevirtual 150	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   164: getfield 156	android/util/DisplayMetrics:density	F
    //   167: fstore 14
    //   169: aload_2
    //   170: ldc_w 276
    //   173: fload 14
    //   175: fmul
    //   176: f2i
    //   177: putfield 279	android/widget/RelativeLayout$LayoutParams:leftMargin	I
    //   180: aload_2
    //   181: ldc_w 276
    //   184: fload 14
    //   186: fmul
    //   187: f2i
    //   188: putfield 282	android/widget/RelativeLayout$LayoutParams:topMargin	I
    //   191: aload_2
    //   192: ldc_w 276
    //   195: fload 14
    //   197: fmul
    //   198: f2i
    //   199: putfield 285	android/widget/RelativeLayout$LayoutParams:rightMargin	I
    //   202: aload_2
    //   203: ldc_w 276
    //   206: fload 14
    //   208: fmul
    //   209: f2i
    //   210: putfield 288	android/widget/RelativeLayout$LayoutParams:bottomMargin	I
    //   213: aload 4
    //   215: ifnonnull +132 -> 347
    //   218: aload_0
    //   219: getfield 227	com/weibo/sdk/android/WeiboDialog:webViewContainer	Landroid/widget/RelativeLayout;
    //   222: getstatic 293	com/weibo/sdk/android/R$drawable:weibosdk_dialog_bg	I
    //   225: invokevirtual 296	android/widget/RelativeLayout:setBackgroundResource	(I)V
    //   228: aload 4
    //   230: ifnull +8 -> 238
    //   233: aload 4
    //   235: invokevirtual 187	java/io/InputStream:close	()V
    //   238: aload_0
    //   239: getfield 227	com/weibo/sdk/android/WeiboDialog:webViewContainer	Landroid/widget/RelativeLayout;
    //   242: aload_0
    //   243: getfield 76	com/weibo/sdk/android/WeiboDialog:mWebView	Landroid/webkit/WebView;
    //   246: aload_2
    //   247: invokevirtual 300	android/widget/RelativeLayout:addView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   250: aload_0
    //   251: getfield 227	com/weibo/sdk/android/WeiboDialog:webViewContainer	Landroid/widget/RelativeLayout;
    //   254: bipush 17
    //   256: invokevirtual 303	android/widget/RelativeLayout:setGravity	(I)V
    //   259: aload_0
    //   260: invokespecial 305	com/weibo/sdk/android/WeiboDialog:parseDimens	()Z
    //   263: ifeq +166 -> 429
    //   266: aload_1
    //   267: getstatic 46	com/weibo/sdk/android/WeiboDialog:left_margin	I
    //   270: putfield 279	android/widget/RelativeLayout$LayoutParams:leftMargin	I
    //   273: aload_1
    //   274: getstatic 48	com/weibo/sdk/android/WeiboDialog:top_margin	I
    //   277: putfield 282	android/widget/RelativeLayout$LayoutParams:topMargin	I
    //   280: aload_1
    //   281: getstatic 50	com/weibo/sdk/android/WeiboDialog:right_margin	I
    //   284: putfield 285	android/widget/RelativeLayout$LayoutParams:rightMargin	I
    //   287: aload_1
    //   288: getstatic 52	com/weibo/sdk/android/WeiboDialog:bottom_margin	I
    //   291: putfield 288	android/widget/RelativeLayout$LayoutParams:bottomMargin	I
    //   294: aload_0
    //   295: getfield 270	com/weibo/sdk/android/WeiboDialog:mContent	Landroid/widget/RelativeLayout;
    //   298: aload_0
    //   299: getfield 227	com/weibo/sdk/android/WeiboDialog:webViewContainer	Landroid/widget/RelativeLayout;
    //   302: aload_1
    //   303: invokevirtual 300	android/widget/RelativeLayout:addView	(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   306: return
    //   307: astore 7
    //   309: aload 7
    //   311: invokevirtual 306	java/lang/Exception:printStackTrace	()V
    //   314: goto -101 -> 213
    //   317: astore 8
    //   319: aload 8
    //   321: invokevirtual 306	java/lang/Exception:printStackTrace	()V
    //   324: aload 4
    //   326: ifnull -88 -> 238
    //   329: aload 4
    //   331: invokevirtual 187	java/io/InputStream:close	()V
    //   334: goto -96 -> 238
    //   337: astore 9
    //   339: aload 9
    //   341: invokevirtual 211	java/io/IOException:printStackTrace	()V
    //   344: goto -106 -> 238
    //   347: aload 4
    //   349: invokestatic 312	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   352: astore 11
    //   354: new 314	android/graphics/drawable/NinePatchDrawable
    //   357: dup
    //   358: aload 11
    //   360: aload 11
    //   362: invokevirtual 320	android/graphics/Bitmap:getNinePatchChunk	()[B
    //   365: new 322	android/graphics/Rect
    //   368: dup
    //   369: iconst_0
    //   370: iconst_0
    //   371: iconst_0
    //   372: iconst_0
    //   373: invokespecial 325	android/graphics/Rect:<init>	(IIII)V
    //   376: aconst_null
    //   377: invokespecial 328	android/graphics/drawable/NinePatchDrawable:<init>	(Landroid/graphics/Bitmap;[BLandroid/graphics/Rect;Ljava/lang/String;)V
    //   380: astore 12
    //   382: aload_0
    //   383: getfield 227	com/weibo/sdk/android/WeiboDialog:webViewContainer	Landroid/widget/RelativeLayout;
    //   386: aload 12
    //   388: invokevirtual 332	android/widget/RelativeLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   391: goto -163 -> 228
    //   394: astore 5
    //   396: aload 4
    //   398: ifnull +8 -> 406
    //   401: aload 4
    //   403: invokevirtual 187	java/io/InputStream:close	()V
    //   406: aload 5
    //   408: athrow
    //   409: astore 6
    //   411: aload 6
    //   413: invokevirtual 211	java/io/IOException:printStackTrace	()V
    //   416: goto -10 -> 406
    //   419: astore 13
    //   421: aload 13
    //   423: invokevirtual 211	java/io/IOException:printStackTrace	()V
    //   426: goto -188 -> 238
    //   429: aload_0
    //   430: invokevirtual 134	com/weibo/sdk/android/WeiboDialog:getContext	()Landroid/content/Context;
    //   433: invokevirtual 144	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   436: astore 10
    //   438: aload_1
    //   439: aload 10
    //   441: getstatic 336	com/weibo/sdk/android/R$dimen:weibosdk_dialog_left_margin	I
    //   444: invokevirtual 340	android/content/res/Resources:getDimensionPixelSize	(I)I
    //   447: putfield 279	android/widget/RelativeLayout$LayoutParams:leftMargin	I
    //   450: aload_1
    //   451: aload 10
    //   453: getstatic 342	com/weibo/sdk/android/R$dimen:weibosdk_dialog_right_margin	I
    //   456: invokevirtual 340	android/content/res/Resources:getDimensionPixelSize	(I)I
    //   459: putfield 285	android/widget/RelativeLayout$LayoutParams:rightMargin	I
    //   462: aload_1
    //   463: aload 10
    //   465: getstatic 344	com/weibo/sdk/android/R$dimen:weibosdk_dialog_top_margin	I
    //   468: invokevirtual 340	android/content/res/Resources:getDimensionPixelSize	(I)I
    //   471: putfield 282	android/widget/RelativeLayout$LayoutParams:topMargin	I
    //   474: aload_1
    //   475: aload 10
    //   477: getstatic 346	com/weibo/sdk/android/R$dimen:weibosdk_dialog_bottom_margin	I
    //   480: invokevirtual 340	android/content/res/Resources:getDimensionPixelSize	(I)I
    //   483: putfield 288	android/widget/RelativeLayout$LayoutParams:bottomMargin	I
    //   486: goto -192 -> 294
    //
    // Exception table:
    //   from	to	target	type
    //   145	213	307	java/lang/Exception
    //   218	228	317	java/lang/Exception
    //   309	314	317	java/lang/Exception
    //   347	391	317	java/lang/Exception
    //   329	334	337	java/io/IOException
    //   145	213	394	finally
    //   218	228	394	finally
    //   309	314	394	finally
    //   319	324	394	finally
    //   347	391	394	finally
    //   401	406	409	java/io/IOException
    //   233	238	419	java/io/IOException
  }

  protected void onBack()
  {
    try
    {
      this.mSpinner.dismiss();
      if (this.mWebView != null)
      {
        this.mWebView.stopLoading();
        this.mWebView.destroy();
      }
      label28: dismiss();
      return;
    }
    catch (Exception localException)
    {
      break label28;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mSpinner = new ProgressDialog(getContext());
    this.mSpinner.requestWindowFeature(1);
    this.mSpinner.setMessage("Loading...");
    this.mSpinner.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
      {
        WeiboDialog.this.onBack();
        return false;
      }
    });
    requestWindowFeature(1);
    getWindow().setFeatureDrawableAlpha(0, 0);
    this.mContent = new RelativeLayout(getContext());
    setUpWebView();
    addContentView(this.mContent, new ViewGroup.LayoutParams(-1, -1));
  }

  private class WeiboWebViewClient extends WebViewClient
  {
    private WeiboWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      Log.d("Weibo-WebView", "onPageFinished URL: " + paramString);
      super.onPageFinished(paramWebView, paramString);
      if (WeiboDialog.this.mSpinner.isShowing())
        WeiboDialog.this.mSpinner.dismiss();
      WeiboDialog.this.mWebView.setVisibility(0);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      Log.d("Weibo-WebView", "onPageStarted URL: " + paramString);
      if (paramString.startsWith(Weibo.redirecturl))
      {
        WeiboDialog.this.handleRedirectUrl(paramWebView, paramString);
        paramWebView.stopLoading();
        WeiboDialog.this.dismiss();
      }
      while (true)
      {
        return;
        super.onPageStarted(paramWebView, paramString, paramBitmap);
        WeiboDialog.this.mSpinner.show();
      }
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      WeiboDialog.this.mListener.onError(new WeiboDialogError(paramString1, paramInt, paramString2));
      WeiboDialog.this.dismiss();
    }

    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      paramSslErrorHandler.proceed();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Log.d("Weibo-WebView", "Redirect URL: " + paramString);
      if (paramString.startsWith("sms:"))
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.putExtra("address", paramString.replace("sms:", ""));
        localIntent.setType("vnd.android-dir/mms-sms");
        WeiboDialog.this.getContext().startActivity(localIntent);
      }
      for (boolean bool = true; ; bool = super.shouldOverrideUrlLoading(paramWebView, paramString))
        return bool;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.WeiboDialog
 * JD-Core Version:    0.6.0
 */