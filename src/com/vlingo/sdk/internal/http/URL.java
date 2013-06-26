package com.vlingo.sdk.internal.http;

import com.vlingo.sdk.internal.util.StringUtils;

public class URL
{
  public String host = "";
  private int index;
  public String path = "";
  public int port = 80;
  public String url;
  public boolean useSSL = false;

  public URL(String paramString)
  {
    this.url = paramString;
    parseFromURL(paramString);
  }

  public URL(String paramString1, int paramInt, String paramString2)
  {
    this.host = paramString1;
    this.port = paramInt;
    this.path = paramString2;
    this.url = toString();
  }

  private String parseFile(String paramString)
  {
    String str1 = paramString.substring(this.index);
    Object localObject;
    if (str1.length() == 0)
    {
      localObject = "";
      return localObject;
    }
    int i = str1.indexOf('#');
    int j = str1.indexOf('?');
    if ((i < 0) && (j < 0));
    for (i = str1.length(); ; i = j)
      do
      {
        String str2 = str1.substring(0, i);
        this.index = (i + this.index);
        localObject = str2;
        break;
      }
      while ((i >= 0) && ((j <= 0) || (j >= i)));
  }

  private String parseHostname(String paramString)
  {
    String str1 = paramString.substring(this.index);
    if (str1.startsWith("//"))
    {
      str1 = str1.substring(2);
      this.index = (2 + this.index);
    }
    int i = str1.indexOf(':');
    if (i < 0)
      i = str1.indexOf('/');
    if (i < 0)
      i = str1.length();
    String str2 = str1.substring(0, i);
    this.index = (i + this.index);
    return str2;
  }

  private int parsePort(String paramString)
  {
    int i;
    String str1;
    if (this.useSSL)
    {
      i = 443;
      str1 = paramString.substring(this.index);
      if (str1.startsWith(":"))
        break label41;
    }
    label41: int k;
    for (int m = i; ; m = k)
    {
      return m;
      i = 80;
      break;
      String str2 = str1.substring(1);
      this.index = (1 + this.index);
      int j = str2.indexOf('/');
      if (j < 0)
        j = str2.indexOf('?');
      if (j < 0)
        j = str2.length();
      try
      {
        k = Integer.parseInt(str2.substring(0, j));
        if (k <= 0)
          throw new NumberFormatException();
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new IllegalArgumentException("invalid port");
      }
      this.index = (j + this.index);
    }
  }

  private boolean parseUseSSL(String paramString)
  {
    int i = paramString.indexOf(':');
    String str = paramString.substring(0, i);
    this.index = (i + 1);
    return str.equals("https");
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if ((paramObject instanceof URL))
    {
      URL localURL = (URL)paramObject;
      if ((StringUtils.isEqual(this.url, localURL.url)) && (StringUtils.isEqual(this.host, localURL.host)) && (StringUtils.isEqual(this.path, localURL.path)) && (this.port == localURL.port) && (this.useSSL == localURL.useSSL))
        bool = true;
    }
    while (true)
    {
      return bool;
      bool = false;
      continue;
      bool = super.equals(paramObject);
    }
  }

  public String getHost()
  {
    return this.host;
  }

  public String getPath()
  {
    return this.path;
  }

  public int getPort()
  {
    return this.port;
  }

  public String getProtocol()
  {
    if (this.useSSL);
    for (String str = "https"; ; str = "http")
      return str;
  }

  public int hashCode()
  {
    int i = 3 * this.port;
    if (this.useSSL);
    for (int j = 7; ; j = 11)
    {
      int k = i * j;
      if (this.url != null)
        k = k * 89 + this.url.hashCode();
      if (this.host != null)
        k = k * 89 + this.host.hashCode();
      if (this.path != null)
        k = k * 89 + this.path.hashCode();
      return k;
    }
  }

  public void parseFromURL(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 5));
    try
    {
      this.index = 0;
      this.useSSL = parseUseSSL(paramString);
      this.host = parseHostname(paramString);
      this.port = parsePort(paramString);
      this.path = parseFile(paramString);
      label53: return;
    }
    catch (Exception localException)
    {
      break label53;
    }
  }

  public void setHost(String paramString)
  {
    this.host = paramString;
  }

  public void setPath(String paramString)
  {
    this.path = paramString;
  }

  public void setPort(int paramInt)
  {
    this.port = paramInt;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.useSSL)
      localStringBuilder.append("https://");
    while (true)
    {
      localStringBuilder.append(this.host);
      localStringBuilder.append(":");
      localStringBuilder.append(this.port);
      localStringBuilder.append(this.path);
      return localStringBuilder.toString();
      localStringBuilder.append("http://");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.URL
 * JD-Core Version:    0.6.0
 */