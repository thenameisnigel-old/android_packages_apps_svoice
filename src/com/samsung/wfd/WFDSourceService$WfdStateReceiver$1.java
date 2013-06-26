package com.samsung.wfd;

import android.media.AudioManager;
import android.os.CountDownTimer;
import android.util.Log;

class WFDSourceService$WfdStateReceiver$1 extends CountDownTimer
{
  public void onFinish()
  {
    Log.d("WFDSourceService", "isBluetoothA2dpOn() timer finish");
  }

  public void onTick(long paramLong)
  {
    Log.d("WFDSourceService", "mAudioManager.isBluetoothA2dpOn() : " + WFDSourceService.access$900(this.this$1.this$0).isBluetoothA2dpOn());
    if ((WFDSourceService.access$900(this.this$1.this$0).isBluetoothA2dpOn()) && (WFDSourceService.access$800(this.this$1.this$0) == WfdEnums.ConnectionType.TCP))
    {
      Log.d("WFDSourceService", "isBluetoothA2dpOn()");
      this.this$1.this$0.teardownForAudioOut();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WFDSourceService.WfdStateReceiver.1
 * JD-Core Version:    0.6.0
 */