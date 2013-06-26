package com.vlingo.sdk.internal.vlservice;

import com.vlingo.sdk.internal.http.cookies.CookieJarManager;
import com.vlingo.sdk.internal.http.cookies.CookieJarManagerSingleton;

public abstract class VLServiceCookieManager
{
  public static CookieJarManager getInstance()
  {
    monitorenter;
    try
    {
      CookieJarManager localCookieJarManager = CookieJarManagerSingleton.getInstance();
      monitorexit;
      return localCookieJarManager;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.VLServiceCookieManager
 * JD-Core Version:    0.6.0
 */