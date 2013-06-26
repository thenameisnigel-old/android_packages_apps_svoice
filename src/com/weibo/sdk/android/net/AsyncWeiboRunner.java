package com.weibo.sdk.android.net;

import com.weibo.sdk.android.WeiboParameters;

public class AsyncWeiboRunner
{
  public static void request(String paramString1, WeiboParameters paramWeiboParameters, String paramString2, RequestListener paramRequestListener)
  {
    new AsyncWeiboRunner.1(paramString1, paramString2, paramWeiboParameters, paramRequestListener).start();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.net.AsyncWeiboRunner
 * JD-Core Version:    0.6.0
 */