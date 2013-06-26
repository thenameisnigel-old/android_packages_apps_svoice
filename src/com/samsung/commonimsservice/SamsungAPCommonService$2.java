package com.samsung.commonimsservice;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.sec.android.internal.ims.IIMSService.Stub;

class SamsungAPCommonService$2
  implements ServiceConnection
{
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    SamsungAPCommonService.access$200(this.this$0, "IMS service connection connected");
    SamsungAPCommonService.access$302(this.this$0, IIMSService.Stub.asInterface(paramIBinder));
    SamsungAPCommonService.access$400(this.this$0).sendEmptyMessage(101);
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    Log.w("SamsungAPCommonService", "IMS service connection disconnected");
    SamsungAPCommonService.access$400(this.this$0).sendEmptyMessage(102);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.commonimsservice.SamsungAPCommonService.2
 * JD-Core Version:    0.6.0
 */