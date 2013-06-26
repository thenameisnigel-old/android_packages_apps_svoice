package com.samsung.media.fmradio;

import android.os.Handler;
import com.samsung.media.fmradio.internal.IFMEventListener;

public class FMEventListener
{
  private static final int EVENT_AF_RECEIVED = 14;
  private static final int EVENT_AF_STARTED = 13;
  private static final int EVENT_CHANNEL_FOUND = 1;
  private static final int EVENT_EAR_PHONE_CONNECT = 8;
  private static final int EVENT_EAR_PHONE_DISCONNECT = 9;
  private static final int EVENT_OFF = 6;
  private static final int EVENT_ON = 5;
  private static final int EVENT_RDS_DISABLED = 12;
  private static final int EVENT_RDS_ENABLED = 11;
  private static final int EVENT_RDS_EVENT = 10;
  private static final int EVENT_REC_FINISH = 17;
  private static final int EVENT_RTPLUS_EVENT = 16;
  private static final int EVENT_SCAN_FINISHED = 3;
  private static final int EVENT_SCAN_STARTED = 2;
  private static final int EVENT_SCAN_STOPPED = 4;
  private static final int EVENT_TUNE = 7;
  private static final int EVENT_VOLUME_LOCK = 15;
  IFMEventListener callback = new FMEventListener.1(this);
  Handler mHandler = new FMEventListener.2(this);

  public void earPhoneConnected()
  {
  }

  public void earPhoneDisconnected()
  {
  }

  public void onAFReceived(long paramLong)
  {
  }

  public void onAFStarted()
  {
  }

  public void onChannelFound(long paramLong)
  {
  }

  public void onOff(int paramInt)
  {
  }

  public void onOn()
  {
  }

  public void onRDSDisabled()
  {
  }

  public void onRDSEnabled()
  {
  }

  public void onRDSReceived(long paramLong, String paramString1, String paramString2)
  {
  }

  public void onRTPlusReceived(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
  }

  public void onScanFinished(long[] paramArrayOfLong)
  {
  }

  public void onScanStarted()
  {
  }

  public void onScanStopped(long[] paramArrayOfLong)
  {
  }

  public void onTune(long paramLong)
  {
  }

  public void recFinish()
  {
  }

  public void volumeLock()
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.media.fmradio.FMEventListener
 * JD-Core Version:    0.6.0
 */