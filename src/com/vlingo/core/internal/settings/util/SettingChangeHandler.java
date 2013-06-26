package com.vlingo.core.internal.settings.util;

import java.util.Vector;

public abstract class SettingChangeHandler
{
  private static Class<?> ImplClass;
  private static SettingChangeHandler instance = null;

  static
  {
    ImplClass = null;
  }

  public static SettingChangeHandler getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        if (ImplClass == null)
          throw new RuntimeException("AudioPlayer implementation class is not set");
    }
    finally
    {
      monitorexit;
    }
    try
    {
      instance = (SettingChangeHandler)ImplClass.newInstance();
      SettingChangeHandler localSettingChangeHandler = instance;
      monitorexit;
      return localSettingChangeHandler;
    }
    catch (InstantiationException localInstantiationException)
    {
      localInstantiationException.printStackTrace();
      throw new RuntimeException("AudioPlayer InstantiationException: " + localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
    }
    throw new RuntimeException("AudioPlayer IllegalAccessException: " + localIllegalAccessException);
  }

  public static void setSettingChangeHandlerImpl(Class<?> paramClass)
  {
    monitorenter;
    if (paramClass == null)
      try
      {
        throw new RuntimeException("SettingChangeHandler clazz null");
      }
      finally
      {
        monitorexit;
      }
    if (!SettingChangeHandler.class.isAssignableFrom(paramClass))
      throw new RuntimeException("SettingChangeHandler invalid impl: " + paramClass);
    ImplClass = paramClass;
    monitorexit;
  }

  protected abstract void addChangeListener(Setting paramSetting, SettingChangeListener paramSettingChangeListener, Vector<?> paramVector);

  protected abstract void onSettingSaved(Setting paramSetting, Vector<?> paramVector);

  protected abstract void removeChangeListener(Setting paramSetting, SettingChangeListener paramSettingChangeListener, Vector<?> paramVector);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.SettingChangeHandler
 * JD-Core Version:    0.6.0
 */