package com.vlingo.sdk.internal.http.cookies;

public class CookieJarManagerSingleton
{
  private static Class<?> ImplClass;
  private static CookieJarManager instance = null;

  static
  {
    ImplClass = null;
  }

  public static void cleanup()
  {
    ImplClass = null;
    instance = null;
  }

  public static CookieJarManager getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        if (ImplClass == null)
          throw new RuntimeException("CookieJarManager implementation class is not set");
    }
    finally
    {
      monitorexit;
    }
    try
    {
      instance = (CookieJarManager)ImplClass.newInstance();
      CookieJarManager localCookieJarManager = instance;
      monitorexit;
      return localCookieJarManager;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new RuntimeException("CookieJarManager InstantiationException: " + localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    throw new RuntimeException("CookieJarManager IllegalAccessException: " + localIllegalAccessException);
  }

  public static void setCookieJarManagerImpl(Class<?> paramClass)
  {
    monitorenter;
    if (paramClass == null)
      try
      {
        throw new RuntimeException("CookieJarManager clazz null");
      }
      finally
      {
        monitorexit;
      }
    if (!CookieJarManager.class.isAssignableFrom(paramClass))
      throw new RuntimeException("CookieJarManager invalid impl: " + paramClass);
    ImplClass = paramClass;
    monitorexit;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.cookies.CookieJarManagerSingleton
 * JD-Core Version:    0.6.0
 */