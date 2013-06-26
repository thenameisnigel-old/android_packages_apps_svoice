package com.tencent.tauth.http;

import android.os.Bundle;

public class AsyncHttpPostRunner
{
  public void request(String paramString, Bundle paramBundle, IRequestListener paramIRequestListener)
  {
    request(paramString, paramBundle, "POST", paramIRequestListener, null);
  }

  public void request(String paramString1, Bundle paramBundle, String paramString2, IRequestListener paramIRequestListener, Object paramObject)
  {
    new AsyncHttpPostRunner.1(this, paramString1, paramString2, paramBundle, paramIRequestListener, paramObject).start();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.http.AsyncHttpPostRunner
 * JD-Core Version:    0.6.0
 */