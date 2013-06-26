package com.samsung.media.fmradio;

import android.os.Handler;
import android.os.Message;
import com.samsung.media.fmradio.internal.IFMEventListener.Stub;

class FMEventListener$1 extends IFMEventListener.Stub
{
  public void earPhoneConnected()
  {
    Message.obtain(this.this$0.mHandler, 8, 0, 0, null).sendToTarget();
  }

  public void earPhoneDisconnected()
  {
    Message.obtain(this.this$0.mHandler, 9, 0, 0, null).sendToTarget();
  }

  public void onAFReceived(long paramLong)
  {
    Message.obtain(this.this$0.mHandler, 14, 0, 0, Long.valueOf(paramLong)).sendToTarget();
  }

  public void onAFStarted()
  {
    Message.obtain(this.this$0.mHandler, 13, 0, 0, null).sendToTarget();
  }

  public void onChannelFound(long paramLong)
  {
    Message.obtain(this.this$0.mHandler, 1, 0, 0, Long.valueOf(paramLong)).sendToTarget();
  }

  public void onOff(int paramInt)
  {
    Message.obtain(this.this$0.mHandler, 6, 0, 0, Integer.valueOf(paramInt)).sendToTarget();
  }

  public void onOn()
  {
    Message.obtain(this.this$0.mHandler, 5, 0, 0, null).sendToTarget();
  }

  public void onRDSDisabled()
  {
    Message.obtain(this.this$0.mHandler, 12, 0, 0, null).sendToTarget();
    this.this$0.mHandler.removeMessages(10);
    this.this$0.mHandler.removeMessages(16);
  }

  public void onRDSEnabled()
  {
    Message.obtain(this.this$0.mHandler, 11, 0, 0, null).sendToTarget();
  }

  public void onRDSReceived(long paramLong, String paramString1, String paramString2)
  {
    Handler localHandler = this.this$0.mHandler;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Long.valueOf(paramLong);
    arrayOfObject[1] = paramString1;
    arrayOfObject[2] = paramString2;
    Message.obtain(localHandler, 10, 0, 0, arrayOfObject).sendToTarget();
  }

  public void onRTPlusReceived(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    Handler localHandler = this.this$0.mHandler;
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = Integer.valueOf(paramInt2);
    arrayOfObject[2] = Integer.valueOf(paramInt3);
    arrayOfObject[3] = Integer.valueOf(paramInt4);
    arrayOfObject[4] = Integer.valueOf(paramInt5);
    arrayOfObject[5] = Integer.valueOf(paramInt6);
    Message.obtain(localHandler, 16, 0, 0, arrayOfObject).sendToTarget();
  }

  public void onScanFinished(long[] paramArrayOfLong)
  {
    Message.obtain(this.this$0.mHandler, 3, 0, 0, paramArrayOfLong).sendToTarget();
  }

  public void onScanStarted()
  {
    Message.obtain(this.this$0.mHandler, 2, 0, 0, null).sendToTarget();
  }

  public void onScanStopped(long[] paramArrayOfLong)
  {
    Message.obtain(this.this$0.mHandler, 4, 0, 0, paramArrayOfLong).sendToTarget();
  }

  public void onTune(long paramLong)
  {
    Message.obtain(this.this$0.mHandler, 7, 0, 0, Long.valueOf(paramLong)).sendToTarget();
  }

  public void recFinish()
  {
    Message.obtain(this.this$0.mHandler, 17, 0, 0, null).sendToTarget();
  }

  public void volumeLock()
  {
    Message.obtain(this.this$0.mHandler, 15, 0, 0, null).sendToTarget();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.media.fmradio.FMEventListener.1
 * JD-Core Version:    0.6.0
 */