package com.weibo.sdk.android.net;

import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.WeiboParameters;

class AsyncWeiboRunner$1 extends Thread
{
  public void run()
  {
    try
    {
      String str = HttpManager.openUrl(this.val$url, this.val$httpMethod, this.val$params, this.val$params.getValue("pic"));
      this.val$listener.onComplete(str);
      return;
    }
    catch (WeiboException localWeiboException)
    {
      while (true)
        this.val$listener.onError(localWeiboException);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.net.AsyncWeiboRunner.1
 * JD-Core Version:    0.6.0
 */