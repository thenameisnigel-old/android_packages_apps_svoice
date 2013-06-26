package com.tencent.tauth.http;

import android.os.Bundle;

public class AsyncHttpRequestRunner
{
  public void request(String paramString, Bundle paramBundle, IRequestListener paramIRequestListener)
  {
    request(paramString, paramBundle, "GET", paramIRequestListener, null);
  }

  public void request(String paramString1, Bundle paramBundle, String paramString2, IRequestListener paramIRequestListener, Object paramObject)
  {
    new AsyncHttpRequestRunner.1(this, paramString1, paramString2, paramBundle, paramIRequestListener, paramObject).start();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.http.AsyncHttpRequestRunner
 * JD-Core Version:    0.6.0
 */