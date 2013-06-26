package com.vlingo.core.internal.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;

class ServiceProxyBase$2
  implements ServiceConnection
{
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    ServiceProxyBase.access$100(this.this$0).sendMessage(ServiceProxyBase.access$100(this.this$0).obtainMessage(1, paramIBinder));
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    ServiceProxyBase.access$100(this.this$0).sendMessage(ServiceProxyBase.access$100(this.this$0).obtainMessage(2));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.ServiceProxyBase.2
 * JD-Core Version:    0.6.0
 */