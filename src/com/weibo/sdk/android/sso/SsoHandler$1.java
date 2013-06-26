package com.weibo.sdk.android.sso;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.sina.sso.RemoteSSO;
import com.sina.sso.RemoteSSO.Stub;
import com.weibo.sdk.android.Weibo;

class SsoHandler$1
  implements ServiceConnection
{
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    RemoteSSO localRemoteSSO = RemoteSSO.Stub.asInterface(paramIBinder);
    try
    {
      SsoHandler.access$3(localRemoteSSO.getPackageName());
      SsoHandler.access$4(localRemoteSSO.getActivityName());
      if (!SsoHandler.access$6(this.this$0, SsoHandler.access$1(this.this$0), Weibo.app_key, new String[0], SsoHandler.access$5(this.this$0)))
        SsoHandler.access$0(this.this$0).startAuthDialog(SsoHandler.access$1(this.this$0), SsoHandler.access$2(this.this$0));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    SsoHandler.access$0(this.this$0).startAuthDialog(SsoHandler.access$1(this.this$0), SsoHandler.access$2(this.this$0));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.sso.SsoHandler.1
 * JD-Core Version:    0.6.0
 */