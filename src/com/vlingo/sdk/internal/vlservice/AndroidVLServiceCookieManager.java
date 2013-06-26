package com.vlingo.sdk.internal.vlservice;

import com.vlingo.sdk.internal.http.cookies.AndroidCookie;
import com.vlingo.sdk.internal.http.cookies.AndroidCookieJar;
import com.vlingo.sdk.internal.http.cookies.Cookie;
import com.vlingo.sdk.internal.http.cookies.CookieJar;
import com.vlingo.sdk.internal.http.cookies.CookieJarManager;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class AndroidVLServiceCookieManager
  implements CookieJarManager
{
  private AndroidCookieJar persistentCookieJar = new AndroidCookieJar("cookie_data");

  public void addAllCookiesToHashtable(Hashtable paramHashtable, String paramString1, String paramString2)
  {
    this.persistentCookieJar.clearExpired();
    Iterator localIterator = this.persistentCookieJar.getCookies().iterator();
    while (localIterator.hasNext())
    {
      Cookie localCookie = (Cookie)localIterator.next();
      if (!localCookie.isMatch(paramString1, paramString2))
        continue;
      paramHashtable.put(localCookie.getName(), localCookie.getValue());
    }
  }

  public void addCookie(Cookie paramCookie)
  {
    throw new UnsupportedOperationException("??? Is this really an un-used method ???");
  }

  public Cookie createCookie(String paramString1, String paramString2)
  {
    return new AndroidCookie(paramString1, paramString2);
  }

  public String getCookieValue(String paramString)
  {
    Cookie localCookie = this.persistentCookieJar.getCookieByName(paramString);
    if (localCookie != null);
    for (String str = localCookie.getValue(); ; str = null)
      return str;
  }

  public void mergeCookies(CookieJar paramCookieJar)
  {
    this.persistentCookieJar.mergeCookies(paramCookieJar);
    this.persistentCookieJar.save();
  }

  public void removeCookie(String paramString)
  {
    throw new UnsupportedOperationException("??? Is this really an un-used method ???");
  }

  public void resetCookies()
  {
    throw new UnsupportedOperationException("??? Is this really an un-used method ???");
  }

  public void save()
  {
    throw new UnsupportedOperationException("??? Is this really an un-used method ???");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.vlservice.AndroidVLServiceCookieManager
 * JD-Core Version:    0.6.0
 */