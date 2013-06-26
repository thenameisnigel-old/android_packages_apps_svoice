package com.vlingo.sdk.internal.http.cookies;

public abstract interface CookieJar
{
  public abstract void addCookie(Cookie paramCookie);

  public abstract void mergeCookies(CookieJar paramCookieJar);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.cookies.CookieJar
 * JD-Core Version:    0.6.0
 */