package com.vlingo.core.internal.settings.util;

public abstract class CoreSettingsProvider
{
  private static Class<?> ImplClass;
  private static CoreSettingsProvider instance = null;

  static
  {
    ImplClass = null;
  }

  public static boolean getBoolean(String paramString)
  {
    return getInstance().getBooleanSettingValue(paramString);
  }

  public static String getEnumValue(String paramString)
  {
    return getInstance().getEnumSettingValue(paramString);
  }

  public static float getFloatValue(String paramString, float paramFloat)
  {
    return getInstance().getFloatSettingValue(paramString, paramFloat);
  }

  public static CoreSettingsProvider getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        if (ImplClass == null)
          throw new RuntimeException("SettingFactory implementation class is not set");
    }
    finally
    {
      monitorexit;
    }
    try
    {
      instance = (CoreSettingsProvider)ImplClass.newInstance();
      CoreSettingsProvider localCoreSettingsProvider = instance;
      monitorexit;
      return localCoreSettingsProvider;
    }
    catch (InstantiationException localInstantiationException)
    {
      localInstantiationException.printStackTrace();
      throw new RuntimeException("SettingFactory InstantiationException: " + localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
    throw new RuntimeException("SettingFactory IllegalAccessException: " + localIllegalAccessException);
  }

  public static void setCoreSettingsProviderImpl(Class<?> paramClass)
  {
    monitorenter;
    if (paramClass == null)
      try
      {
        throw new RuntimeException("CoreSettingsProvider clazz null");
      }
      finally
      {
        monitorexit;
      }
    if (!CoreSettingsProvider.class.isAssignableFrom(paramClass))
      throw new RuntimeException("CoreSettingsProvider invalid impl: " + paramClass);
    ImplClass = paramClass;
    monitorexit;
  }

  public boolean getBooleanSettingValue(String paramString)
  {
    return false;
  }

  public String getEnumSettingValue(String paramString)
  {
    throw new IllegalArgumentException("Unknown setting: " + paramString);
  }

  public float getFloatSettingValue(String paramString, float paramFloat)
  {
    return paramFloat;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.CoreSettingsProvider
 * JD-Core Version:    0.6.0
 */