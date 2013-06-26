package com.samsung.media.fmradio;

import android.os.Handler;
import android.os.Message;

class FMEventListener$2 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 2:
    case 4:
    case 3:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 16:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 17:
    }
    while (true)
    {
      return;
      Long localLong3 = (Long)paramMessage.obj;
      this.this$0.onChannelFound(localLong3.longValue());
      continue;
      this.this$0.onScanStarted();
      continue;
      long[] arrayOfLong2 = (long[])(long[])paramMessage.obj;
      this.this$0.onScanStopped(arrayOfLong2);
      continue;
      long[] arrayOfLong1 = (long[])(long[])paramMessage.obj;
      this.this$0.onScanFinished(arrayOfLong1);
      continue;
      this.this$0.onOn();
      continue;
      int i = ((Integer)paramMessage.obj).intValue();
      this.this$0.onOff(i);
      continue;
      Long localLong2 = (Long)paramMessage.obj;
      this.this$0.onTune(localLong2.longValue());
      continue;
      this.this$0.earPhoneConnected();
      continue;
      this.this$0.earPhoneDisconnected();
      continue;
      Object[] arrayOfObject2 = (Object[])(Object[])paramMessage.obj;
      this.this$0.onRDSReceived(((Long)arrayOfObject2[0]).longValue(), (String)arrayOfObject2[1], (String)arrayOfObject2[2]);
      continue;
      Object[] arrayOfObject1 = (Object[])(Object[])paramMessage.obj;
      this.this$0.onRTPlusReceived(((Integer)arrayOfObject1[0]).intValue(), ((Integer)arrayOfObject1[1]).intValue(), ((Integer)arrayOfObject1[2]).intValue(), ((Integer)arrayOfObject1[3]).intValue(), ((Integer)arrayOfObject1[4]).intValue(), ((Integer)arrayOfObject1[5]).intValue());
      continue;
      this.this$0.onRDSEnabled();
      continue;
      this.this$0.onRDSDisabled();
      continue;
      this.this$0.onAFStarted();
      continue;
      Long localLong1 = (Long)paramMessage.obj;
      this.this$0.onAFReceived(localLong1.longValue());
      this.this$0.onTune(localLong1.longValue());
      continue;
      this.this$0.volumeLock();
      continue;
      this.this$0.recFinish();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.media.fmradio.FMEventListener.2
 * JD-Core Version:    0.6.0
 */