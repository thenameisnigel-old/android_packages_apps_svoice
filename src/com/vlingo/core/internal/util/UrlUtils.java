package com.vlingo.core.internal.util;

import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class UrlUtils
{
  public static String extractValueForKey(String paramString1, String paramString2)
  {
    int i = paramString2.indexOf(paramString1 + "=");
    int j;
    int k;
    if (i != -1)
    {
      j = i + (1 + paramString1.length());
      k = paramString2.indexOf("&", j + 1);
      if (k == -1)
        k = paramString2.length();
    }
    for (String str = urlDecode(paramString2.substring(j, k)); ; str = null)
      return str;
  }

  public static Object fetchContet(String paramString)
    throws MalformedURLException, IOException
  {
    return new URL(paramString).getContent();
  }

  public static Drawable fetchDrawable(String paramString)
    throws MalformedURLException, IOException
  {
    InputStream localInputStream = (InputStream)fetchContet(paramString);
    Drawable localDrawable = Drawable.createFromStream(localInputStream, "src");
    localInputStream.close();
    return localDrawable;
  }

  public static String urlDecode(String paramString)
  {
    String str;
    if (paramString == null)
      str = null;
    while (true)
    {
      return str;
      int i = 0;
      StringBuffer localStringBuffer = null;
      int j = 0;
      label16: int k;
      int m;
      if (j < -2 + paramString.length())
        if (paramString.charAt(j) == '%')
        {
          k = j + 1;
          m = j + 3;
        }
      try
      {
        char c = (char)Integer.parseInt(paramString.substring(k, m), 16);
        if (localStringBuffer == null)
          localStringBuffer = new StringBuffer(paramString);
        localStringBuffer.delete(j - i, 3 + (j - i));
        localStringBuffer.insert(j - i, c);
        i += 2;
        label102: j++;
        break label16;
        if (localStringBuffer == null)
        {
          str = paramString.replace('+', ' ');
          continue;
        }
        str = localStringBuffer.toString().replace('+', ' ');
      }
      catch (Throwable localThrowable)
      {
        break label102;
      }
    }
  }

  public static String urlEncode(String paramString)
  {
    if (paramString != null);
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      paramString = str;
      label13: return paramString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      break label13;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.UrlUtils
 * JD-Core Version:    0.6.0
 */