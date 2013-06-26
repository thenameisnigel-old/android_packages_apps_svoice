package com.vlingo.sdk.internal.http.cookies;

public class CookieFactory
{
  public static Cookie newInstance(String paramString1, String paramString2)
  {
    monitorenter;
    try
    {
      Cookie localCookie = CookieJarManagerSingleton.getInstance().createCookie(paramString1, paramString2);
      monitorexit;
      return localCookie;
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
 * Qualified Name:     com.vlingo.sdk.internal.http.cookies.CookieFactory
 * JD-Core Version:    0.6.0
 */