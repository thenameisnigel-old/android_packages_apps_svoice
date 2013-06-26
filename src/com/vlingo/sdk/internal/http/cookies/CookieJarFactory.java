package com.vlingo.sdk.internal.http.cookies;

public class CookieJarFactory
{
  private static Class<?> ImplClass = null;

  public static void cleanup()
  {
    ImplClass = null;
  }

  public static CookieJar newInstance()
  {
    monitorenter;
    try
    {
      if (ImplClass == null)
        throw new RuntimeException("CookieJar implementation class is not set");
    }
    finally
    {
      monitorexit;
    }
    try
    {
      CookieJar localCookieJar = (CookieJar)ImplClass.newInstance();
      monitorexit;
      return localCookieJar;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new RuntimeException("CookieJar InstantiationException: " + localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    throw new RuntimeException("CookieJar IllegalAccessException: " + localIllegalAccessException);
  }

  public static void setCookieJarImpl(Class<?> paramClass)
  {
    monitorenter;
    if (paramClass == null)
      try
      {
        throw new RuntimeException("CookieJar clazz null");
      }
      finally
      {
        monitorexit;
      }
    if (!CookieJar.class.isAssignableFrom(paramClass))
      throw new RuntimeException("CookieJar invalid impl: " + paramClass);
    ImplClass = paramClass;
    monitorexit;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.cookies.CookieJarFactory
 * JD-Core Version:    0.6.0
 */