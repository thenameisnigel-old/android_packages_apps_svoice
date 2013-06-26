package com.vlingo.sdk.internal.http.custom;

import com.vlingo.sdk.internal.http.URL;
import java.io.IOException;

public class VConnectionFactory
{
  public static AndroidVStreamConnection createConnection(URL paramURL)
    throws IOException
  {
    return new AndroidVStreamConnection(paramURL);
  }

  public static AndroidVStreamConnection createConnection(String paramString)
    throws IOException
  {
    return new AndroidVStreamConnection(paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.VConnectionFactory
 * JD-Core Version:    0.6.0
 */