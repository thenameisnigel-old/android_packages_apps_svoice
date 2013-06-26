package com.vlingo.core.internal.settings.util;

public abstract class SettingFactory
{
  private static Class<?> ImplClass;
  private static SettingFactory instance = null;

  static
  {
    ImplClass = null;
  }

  public static SettingFactory getInstance()
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
      instance = (SettingFactory)ImplClass.newInstance();
      SettingFactory localSettingFactory = instance;
      monitorexit;
      return localSettingFactory;
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

  public static void setSettingFactoryImpl(Class<?> paramClass)
  {
    monitorenter;
    if (paramClass == null)
      try
      {
        throw new RuntimeException("SettingFactory clazz null");
      }
      finally
      {
        monitorexit;
      }
    if (!SettingFactory.class.isAssignableFrom(paramClass))
      throw new RuntimeException("SettingFactory invalid impl: " + paramClass);
    ImplClass = paramClass;
    monitorexit;
  }

  public abstract BooleanSetting newBooleanSetting(String paramString1, boolean paramBoolean, String paramString2);

  public abstract BooleanSetting newBooleanSetting(String paramString1, boolean paramBoolean, String paramString2, String[] paramArrayOfString);

  public abstract DataSetting newDataSetting(String paramString1, byte[] paramArrayOfByte, String paramString2);

  public abstract EnumSetting newEnumSetting(String paramString1, String[][] paramArrayOfString, String paramString2, String paramString3);

  public abstract IntSetting newIntSetting(String paramString1, int paramInt, String paramString2);

  public abstract LongSetting newLongSetting(String paramString1, long paramLong, String paramString2);

  public abstract StringSetting newStringSetting(String paramString1, String paramString2, String paramString3);

  public abstract URLSetting newURLSetting(String paramString1, String paramString2, String paramString3);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.SettingFactory
 * JD-Core Version:    0.6.0
 */