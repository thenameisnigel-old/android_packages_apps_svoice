package com.samsung.lpp;

import android.content.Context;
import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class LPPLoader
{
  private static final String CLASS_LPPManagerService = "com.samsung.lpp.LPPManagerService";
  private static final String METHOD_systemReady = "systemReady";
  private static final String TAG = "LPPManagerService";

  private static Class getClassFromLib(Context paramContext, String paramString)
    throws Throwable
  {
    return paramContext.createPackageContext("com.samsung.lpp", 3).getClassLoader().loadClass(paramString);
  }

  public static Object getLPPManager(IBinder paramIBinder)
    throws Throwable
  {
    ILPPManager localILPPManager = ILPPManager.Stub.asInterface(paramIBinder);
    if (localILPPManager == null)
      Log.e("LPPManagerService", "returned null service!");
    return new LPPManager(localILPPManager);
  }

  public static IBinder getLPPManagerService(Context paramContext)
    throws Throwable
  {
    IBinder localIBinder = null;
    Class localClass = getClassFromLib(paramContext, "com.samsung.lpp.LPPManagerService");
    if (localClass != null)
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Context.class;
      Constructor localConstructor = localClass.getConstructor(arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramContext;
      localIBinder = (IBinder)localConstructor.newInstance(arrayOfObject);
    }
    return localIBinder;
  }

  public static void systemReady(Context paramContext, IBinder paramIBinder)
    throws Throwable
  {
    Class localClass = getClassFromLib(paramContext, "com.samsung.lpp.LPPManagerService");
    ILPPManager localILPPManager = ILPPManager.Stub.asInterface(paramIBinder);
    localClass.getDeclaredMethod("systemReady", new Class[0]).invoke(localILPPManager, new Object[0]);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.lpp.LPPLoader
 * JD-Core Version:    0.6.0
 */