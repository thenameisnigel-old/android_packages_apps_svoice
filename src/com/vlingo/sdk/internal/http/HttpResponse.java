package com.vlingo.sdk.internal.http;

import com.vlingo.sdk.internal.http.cookies.CookieJar;
import java.io.UnsupportedEncodingException;

public class HttpResponse
{
  CookieJar cookies;
  private final byte[] data;
  public final int responseCode;

  public HttpResponse(int paramInt, byte[] paramArrayOfByte)
  {
    this(paramInt, paramArrayOfByte, null);
  }

  public HttpResponse(int paramInt, byte[] paramArrayOfByte, CookieJar paramCookieJar)
  {
    this.responseCode = paramInt;
    this.data = paramArrayOfByte;
    this.cookies = paramCookieJar;
  }

  public CookieJar getCookies()
  {
    return this.cookies;
  }

  public byte[] getDataAsBytes()
  {
    return this.data;
  }

  public String getDataAsString()
  {
    return getDataAsString("UTF-8");
  }

  public String getDataAsString(String paramString)
  {
    String str = "";
    try
    {
      if (this.data != null)
        str = new String(this.data, paramString);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        str = new String(this.data);
    }
  }

  public int getDataLength()
  {
    if (this.data != null);
    for (int i = this.data.length; ; i = 0)
      return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.HttpResponse
 * JD-Core Version:    0.6.0
 */