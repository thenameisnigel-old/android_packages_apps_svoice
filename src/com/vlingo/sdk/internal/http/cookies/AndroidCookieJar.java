package com.vlingo.sdk.internal.http.cookies;

import com.vlingo.sdk.internal.net.HttpConnection;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.util.StringUtils;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.impl.client.BasicCookieStore;

public class AndroidCookieJar
  implements CookieJar
{
  protected BasicCookieStore m_Store = new BasicCookieStore();
  private String prefName;

  public AndroidCookieJar()
  {
  }

  public AndroidCookieJar(String paramString)
  {
    this();
    this.prefName = paramString;
    load();
  }

  private void load()
  {
    if (this.prefName == null)
      throw new IllegalStateException("Can't call load() on non-persistant AndroidCookieJar");
    String str = Settings.getPersistentString(this.prefName, null);
    String[] arrayOfString;
    int i;
    if ((str != null) && (str.length() > 0))
    {
      arrayOfString = StringUtils.split(str, '|');
      i = 0;
    }
    while (true)
    {
      if (i < arrayOfString.length);
      try
      {
        addCookie(AndroidCookie.deserialize(arrayOfString[i]));
        label62: i++;
        continue;
        clearExpired();
        return;
      }
      catch (Exception localException)
      {
        break label62;
      }
    }
  }

  public void addCookie(Cookie paramCookie)
  {
    AndroidCookie localAndroidCookie = (AndroidCookie)paramCookie;
    this.m_Store.addCookie(localAndroidCookie);
  }

  public void addCookiesToHttpRequest(HttpConnection paramHttpConnection)
    throws IOException
  {
    throw new UnsupportedOperationException("??? Is this really an un-used method ???");
  }

  public boolean clearExpired()
  {
    return this.m_Store.clearExpired(new Date());
  }

  public Cookie getCookieByName(String paramString)
  {
    Iterator localIterator = this.m_Store.getCookies().iterator();
    AndroidCookie localAndroidCookie;
    do
    {
      if (!localIterator.hasNext())
        break;
      localAndroidCookie = (AndroidCookie)localIterator.next();
    }
    while (!localAndroidCookie.getName().equals(paramString));
    while (true)
    {
      return localAndroidCookie;
      localAndroidCookie = null;
    }
  }

  public List<Cookie> getCookies()
  {
    Iterator localIterator = this.m_Store.getCookies().iterator();
    LinkedList localLinkedList = new LinkedList();
    while (localIterator.hasNext())
      localLinkedList.add((AndroidCookie)localIterator.next());
    return localLinkedList;
  }

  public Enumeration<?> keys()
  {
    throw new UnsupportedOperationException("??? Is this really an un-used method ???");
  }

  public void mergeCookies(CookieJar paramCookieJar)
  {
    if (paramCookieJar != null)
    {
      List localList = ((AndroidCookieJar)paramCookieJar).m_Store.getCookies();
      org.apache.http.cookie.Cookie[] arrayOfCookie = new org.apache.http.cookie.Cookie[localList.size()];
      localList.toArray(arrayOfCookie);
      this.m_Store.addCookies(arrayOfCookie);
    }
  }

  public void removeCookie(String paramString)
  {
    throw new UnsupportedOperationException("??? Is this really an un-used method ???");
  }

  public void save()
  {
    if (this.prefName == null)
      throw new IllegalStateException("Can't call save() on non-persistant AndroidCookieJar");
    Iterator localIterator = this.m_Store.getCookies().iterator();
    StringBuffer localStringBuffer = new StringBuffer();
    while (localIterator.hasNext())
    {
      localStringBuffer.append(((AndroidCookie)localIterator.next()).serialize());
      localStringBuffer.append('|');
    }
    if (localStringBuffer.length() > 0)
      localStringBuffer.setLength(-1 + localStringBuffer.length());
    Settings.setPersistentString(this.prefName, localStringBuffer.toString());
  }

  public void saveCookiesFromHttpResponse(HttpConnection paramHttpConnection)
  {
    throw new UnsupportedOperationException("??? Is this really an un-used method ???");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.cookies.AndroidCookieJar
 * JD-Core Version:    0.6.0
 */