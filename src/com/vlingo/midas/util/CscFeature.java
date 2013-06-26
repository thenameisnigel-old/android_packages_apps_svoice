package com.vlingo.midas.util;

import java.lang.reflect.Method;

public class CscFeature
{
  private Class<?> c;
  private Method getInstance;
  private Object instance;
  private Class[] parameterTypes;

  public CscFeature()
  {
    try
    {
      this.c = Class.forName("com.sec.android.app.CscFeature");
      this.getInstance = this.c.getMethod("getInstance", (Class[])null);
      this.instance = this.getInstance.invoke(null, new Object[0]);
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = String.class;
      this.parameterTypes = arrayOfClass;
      label61: return;
    }
    catch (Exception localException)
    {
      break label61;
    }
  }

  public boolean getEnableStatus(String paramString)
  {
    int i = 0;
    try
    {
      Method localMethod = this.c.getMethod("getEnableStatus", this.parameterTypes);
      Object localObject = this.instance;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramString;
      boolean bool = ((Boolean)localMethod.invoke(localObject, arrayOfObject)).booleanValue();
      i = bool;
      label54: return i;
    }
    catch (Exception localException)
    {
      break label54;
    }
  }

  public int getInteger(String paramString)
  {
    try
    {
      Method localMethod = this.c.getMethod("getInteger", this.parameterTypes);
      Object localObject = this.instance;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramString;
      int j = ((Integer)localMethod.invoke(localObject, arrayOfObject)).intValue();
      i = j;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = -1;
    }
  }

  public String getString(String paramString)
  {
    try
    {
      Method localMethod = this.c.getMethod("getString", this.parameterTypes);
      Object localObject = this.instance;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramString;
      str = (String)localMethod.invoke(localObject, arrayOfObject);
      return str;
    }
    catch (Exception localException)
    {
      while (true)
        String str = "";
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.CscFeature
 * JD-Core Version:    0.6.0
 */