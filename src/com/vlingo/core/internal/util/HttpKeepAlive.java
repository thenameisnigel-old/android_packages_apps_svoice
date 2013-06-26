package com.vlingo.core.internal.util;

public class HttpKeepAlive
{
  public static final String HTTP_KEEP_ALIVE = "http.keepAlive";
  private static HttpKeepAlive httpKeepAlive;

  private static HttpKeepAlive getInstance()
  {
    if (httpKeepAlive == null);
    for (HttpKeepAlive localHttpKeepAlive = new HttpKeepAlive(); ; localHttpKeepAlive = httpKeepAlive)
    {
      httpKeepAlive = localHttpKeepAlive;
      return localHttpKeepAlive;
    }
  }

  public static boolean isOn()
  {
    return "true".equals(System.getProperty("http.keepAlive"));
  }

  public static boolean isSet()
  {
    if (System.getProperty("http.keepAlive") != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static HttpKeepAlive off()
  {
    System.setProperty("http.keepAlive", "false");
    return getInstance();
  }

  public static HttpKeepAlive on()
  {
    System.setProperty("http.keepAlive", "true");
    return getInstance();
  }

  public static void toggle(boolean paramBoolean)
  {
    if (paramBoolean);
    for (String str = "true"; ; str = "false")
    {
      System.setProperty("http.keepAlive", str);
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.HttpKeepAlive
 * JD-Core Version:    0.6.0
 */