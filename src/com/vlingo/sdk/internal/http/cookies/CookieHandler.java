package com.vlingo.sdk.internal.http.cookies;

import com.vlingo.sdk.internal.http.date.HttpDateParser;
import com.vlingo.sdk.internal.net.HttpConnection;
import com.vlingo.sdk.internal.recognizer.sr3.HttpConnectionAdapter;
import com.vlingo.sdk.internal.util.StringUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CookieHandler
{
  private static CookieJar extractCookies(ConnectionWrapper paramConnectionWrapper, CookieJar paramCookieJar)
  {
    String str1 = paramConnectionWrapper.getHost();
    int i = 0;
    try
    {
      while (true)
      {
        String str2 = paramConnectionWrapper.getHeaderFieldKey(i);
        if (str2 == null)
          break;
        if (str2.equalsIgnoreCase("Set-Cookie"))
        {
          String str3 = paramConnectionWrapper.getHeaderField(i);
          if (str3 != null)
          {
            if (paramCookieJar == null)
              paramCookieJar = CookieJarFactory.newInstance();
            parseSetCookieString(str3, str1, paramCookieJar);
          }
        }
        i++;
      }
    }
    catch (Exception localException)
    {
    }
    return paramCookieJar;
  }

  public static CookieJar extractCookies(HttpConnection paramHttpConnection)
  {
    return extractCookies(paramHttpConnection, null);
  }

  public static CookieJar extractCookies(HttpConnection paramHttpConnection, CookieJar paramCookieJar)
  {
    return extractCookies(new HttpConnectionWrapper(paramHttpConnection), paramCookieJar);
  }

  public static CookieJar extractCookies(HttpConnectionAdapter paramHttpConnectionAdapter)
  {
    return extractCookies(paramHttpConnectionAdapter, null);
  }

  public static CookieJar extractCookies(HttpConnectionAdapter paramHttpConnectionAdapter, CookieJar paramCookieJar)
  {
    return extractCookies(new HttpConnectionAdapterWrapper(paramHttpConnectionAdapter), paramCookieJar);
  }

  public static CookieJar extractCookies(HttpURLConnection paramHttpURLConnection)
  {
    return extractCookies(paramHttpURLConnection, null);
  }

  public static CookieJar extractCookies(HttpURLConnection paramHttpURLConnection, CookieJar paramCookieJar)
  {
    return extractCookies(new HttpURLConnectionWrapper(paramHttpURLConnection), paramCookieJar);
  }

  public static void parseSetCookieString(String paramString1, String paramString2, CookieJar paramCookieJar)
  {
    label547: label563: label568: 
    while (true)
    {
      int i;
      Cookie localCookie;
      int m;
      String str3;
      String str4;
      try
      {
        String[] arrayOfString1 = StringUtils.split(paramString1, ',');
        i = 0;
        if (i < arrayOfString1.length)
        {
          String str1 = arrayOfString1[i];
          if ((str1.indexOf("Expires=") == -1) && (str1.indexOf("expires=") == -1))
            continue;
          i++;
          str1 = str1 + "," + arrayOfString1[i];
          int j = str1.indexOf('=');
          int k = -1;
          localCookie = null;
          if (j == -1)
            continue;
          String str5 = str1.substring(0, j);
          String str6 = "";
          if (str1.length() <= j + 1)
            continue;
          k = str1.indexOf(';', j + 1);
          if (k == -1)
            continue;
          str6 = str1.substring(j + 1, k);
          localCookie = CookieFactory.newInstance(str5.trim(), str6.trim());
          localCookie.setDomain(paramString2);
          paramCookieJar.addCookie(localCookie);
          if ((k == -1) || (str1.length() <= k + 1))
            break label547;
          String[] arrayOfString2 = StringUtils.split(str1.substring(k + 1), ';');
          m = 0;
          if (m >= arrayOfString2.length)
            break label547;
          String str2 = arrayOfString2[m].trim();
          int n = str2.indexOf("=");
          str3 = str2.substring(0, n).trim();
          str4 = str2.substring(n + 1).trim();
          if ((!str4.startsWith("\"")) || (!str4.endsWith("\"")))
            break label563;
          str4 = str4.substring(1, -1 + str4.length());
          break label563;
          str6 = str1.substring(j + 1);
          continue;
          if (!"Domain".equalsIgnoreCase(str3))
            break label369;
          localCookie.setDomain(str4);
        }
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
      return;
      label369: if ("Path".equalsIgnoreCase(str3))
      {
        localCookie.setPath(str4);
      }
      else
      {
        boolean bool1 = "Expires".equalsIgnoreCase(str3);
        long l4;
        if (bool1)
          l4 = 0L;
        try
        {
          long l5 = HttpDateParser.parse(str4);
          l4 = l5;
          if ((l4 <= 0L) || ((localCookie.getExpires() != 0L) && (l4 >= localCookie.getExpires())))
            break label568;
          localCookie.setExpires(l4);
          break label568;
          boolean bool2 = "Max-Age".equalsIgnoreCase(str3);
          if (!bool2)
            break label568;
          l1 = 0L;
        }
        catch (Exception localException3)
        {
          try
          {
            long l3 = Long.parseLong(str4);
            long l1 = l3 * 1000L;
            if (l1 <= 0L)
              break label568;
            long l2 = l1 + System.currentTimeMillis();
            if ((localCookie.getExpires() != 0L) && (l2 >= localCookie.getExpires()))
              break label568;
            localCookie.setExpires(l2);
            break label568;
            i++;
            continue;
            localException3 = localException3;
          }
          catch (Exception localException2)
          {
            break label495;
          }
        }
        if (localCookie != null)
          continue;
      }
      label495: m++;
    }
  }

  private static abstract interface ConnectionWrapper
  {
    public abstract String getHeaderField(int paramInt)
      throws IOException;

    public abstract String getHeaderFieldKey(int paramInt)
      throws IOException;

    public abstract String getHost();
  }

  private static class HttpConnectionAdapterWrapper
    implements CookieHandler.ConnectionWrapper
  {
    private HttpConnectionAdapter connection;

    public HttpConnectionAdapterWrapper(HttpConnectionAdapter paramHttpConnectionAdapter)
    {
      if (paramHttpConnectionAdapter == null)
        throw new IllegalArgumentException("you MUST provide a non-null 'connection'");
      this.connection = paramHttpConnectionAdapter;
    }

    public String getHeaderField(int paramInt)
      throws IOException
    {
      return this.connection.getResponseHeaderField(paramInt);
    }

    public String getHeaderFieldKey(int paramInt)
      throws IOException
    {
      return this.connection.getResponseHeaderFieldKey(paramInt);
    }

    public String getHost()
    {
      return "";
    }
  }

  private static class HttpConnectionWrapper
    implements CookieHandler.ConnectionWrapper
  {
    private HttpConnection connection;

    public HttpConnectionWrapper(HttpConnection paramHttpConnection)
    {
      if (paramHttpConnection == null)
        throw new IllegalArgumentException("you MUST provide a non-null 'connection'");
      this.connection = paramHttpConnection;
    }

    public String getHeaderField(int paramInt)
      throws IOException
    {
      return this.connection.getHeaderField(paramInt);
    }

    public String getHeaderFieldKey(int paramInt)
      throws IOException
    {
      return this.connection.getHeaderFieldKey(paramInt);
    }

    public String getHost()
    {
      return this.connection.getHost();
    }
  }

  private static class HttpURLConnectionWrapper
    implements CookieHandler.ConnectionWrapper
  {
    private HttpURLConnection connection;

    public HttpURLConnectionWrapper(HttpURLConnection paramHttpURLConnection)
    {
      if (paramHttpURLConnection == null)
        throw new IllegalArgumentException("you MUST provide a non-null 'connection'");
      this.connection = paramHttpURLConnection;
    }

    public String getHeaderField(int paramInt)
    {
      return this.connection.getHeaderField(paramInt);
    }

    public String getHeaderFieldKey(int paramInt)
    {
      return this.connection.getHeaderFieldKey(paramInt);
    }

    public String getHost()
    {
      return this.connection.getURL().getHost();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.cookies.CookieHandler
 * JD-Core Version:    0.6.0
 */