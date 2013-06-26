package com.vlingo.sdk.internal.http.cookies;

import java.util.Hashtable;

public abstract interface CookieJarManager
{
  public abstract void addAllCookiesToHashtable(Hashtable<?, ?> paramHashtable, String paramString1, String paramString2);

  public abstract void addCookie(Cookie paramCookie);

  public abstract Cookie createCookie(String paramString1, String paramString2);

  public abstract String getCookieValue(String paramString);

  public abstract void mergeCookies(CookieJar paramCookieJar);

  public abstract void removeCookie(String paramString);

  public abstract void save();
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.cookies.CookieJarManager
 * JD-Core Version:    0.6.0
 */