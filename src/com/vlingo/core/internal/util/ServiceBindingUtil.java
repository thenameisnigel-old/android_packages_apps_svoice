package com.vlingo.core.internal.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;

public class ServiceBindingUtil
{
  private static HashMap<Context, ServiceBinder> sConnectionMap;
  private static HashMap<Class, Object> sServiceMap = new HashMap();

  static
  {
    sConnectionMap = new HashMap();
  }

  public static ServiceToken bindToService(Context paramContext, Class paramClass1, Class paramClass2, ServiceConnection paramServiceConnection)
  {
    if ((paramContext instanceof Activity))
    {
      Activity localActivity = ((Activity)paramContext).getParent();
      if (localActivity != null)
        paramContext = localActivity;
    }
    ContextWrapper localContextWrapper = new ContextWrapper(paramContext);
    localContextWrapper.startService(new Intent(localContextWrapper, paramClass1));
    ServiceBinder localServiceBinder = new ServiceBinder(paramServiceConnection, paramClass2);
    if (localContextWrapper.bindService(new Intent().setClass(localContextWrapper, paramClass1), localServiceBinder, 0))
      sConnectionMap.put(localContextWrapper, localServiceBinder);
    for (ServiceToken localServiceToken = new ServiceToken(localContextWrapper, paramClass2); ; localServiceToken = null)
      return localServiceToken;
  }

  public static void unbindFromService(ServiceToken paramServiceToken)
  {
    if (paramServiceToken == null);
    while (true)
    {
      return;
      ContextWrapper localContextWrapper = paramServiceToken.mWrappedContext;
      ServiceBinder localServiceBinder = (ServiceBinder)sConnectionMap.remove(localContextWrapper);
      if (localServiceBinder == null)
        continue;
      localContextWrapper.unbindService(localServiceBinder);
      if (!sConnectionMap.isEmpty())
        continue;
      sServiceMap.remove(paramServiceToken.interfaceClass);
    }
  }

  private static class ServiceBinder
    implements ServiceConnection
  {
    Class interfaceClass;
    ServiceConnection mCallback;

    ServiceBinder(ServiceConnection paramServiceConnection, Class paramClass)
    {
      this.mCallback = paramServiceConnection;
      this.interfaceClass = paramClass;
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      ServiceBindingUtil.sServiceMap.put(this.interfaceClass, paramIBinder);
      if (this.mCallback != null)
        this.mCallback.onServiceConnected(paramComponentName, paramIBinder);
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      if (this.mCallback != null)
        this.mCallback.onServiceDisconnected(paramComponentName);
      ServiceBindingUtil.sServiceMap.remove(this.interfaceClass);
    }
  }

  public static class ServiceToken
  {
    public Class interfaceClass;
    public ContextWrapper mWrappedContext;

    public ServiceToken(ContextWrapper paramContextWrapper, Class paramClass)
    {
      this.mWrappedContext = paramContextWrapper;
      this.interfaceClass = paramClass;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ServiceBindingUtil
 * JD-Core Version:    0.6.0
 */