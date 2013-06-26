package com.samsung.media.fmradio;

import android.content.Context;
import android.media.AudioManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.samsung.media.fmradio.internal.IFMPlayer;
import com.samsung.media.fmradio.internal.IFMPlayer.Stub;

public class FMPlayer
{
  private static final boolean DEBUG = true;
  private static final String LOG_TAG = "FMPlayer";
  public static final int OFF_AIRPLANE_MODE_SET = 3;
  public static final int OFF_BATTERY_LOW = 7;
  public static final int OFF_CALL_ACTIVE = 1;
  public static final int OFF_DEVICE_SHUTDOWN = 6;
  public static final int OFF_EAR_PHONE_DISCONNECT = 2;
  public static final int OFF_NORMAL = 0;
  public static final int OFF_PAUSE_COMMAND = 5;
  public static final int OFF_STOP_COMMAND = 4;
  static Context mContext;
  private AudioManager mAudioManager;
  private IFMPlayer mPlayer;

  public FMPlayer(Context paramContext)
  {
    mContext = paramContext;
    this.mPlayer = IFMPlayer.Stub.asInterface(ServiceManager.getService("FMPlayer"));
    this.mAudioManager = ((AudioManager)paramContext.getSystemService("audio"));
    log("Player created :" + this.mPlayer);
  }

  private void checkBusy()
    throws FMPlayerException
  {
    int i = 0;
    try
    {
      int j = this.mPlayer.isBusy();
      i = j;
      if (i == 1)
        throw new FMPlayerException(3, "Player is scanning channel", new Throwable("Player is busy in scanning. Use cancelScan to stop scanning"));
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  private void checkOnStatus()
    throws FMPlayerException
  {
    if (!isOn())
      throw new FMPlayerException(1, "Player is not ON.Call on() method to start player", new Throwable("Player is not ON. use method on() to switch on FM player"));
  }

  private void remoteError(RemoteException paramRemoteException)
    throws FMPlayerException
  {
    paramRemoteException.printStackTrace();
    throw new FMPlayerException(1, "Radio service is not running restart the phone.", paramRemoteException.fillInStackTrace());
  }

  public int GetAFValid_th()
    throws FMPlayerException
  {
    int i = -1;
    if (isOn());
    try
    {
      int j = this.mPlayer.getAFValid_th();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public int GetAF_th()
    throws FMPlayerException
  {
    int i = -1;
    if (isOn());
    try
    {
      int j = this.mPlayer.getAF_th();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void SetAFValid_th(int paramInt)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setAFValid_th(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void SetAF_th(int paramInt)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setAF_th(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void SkipTuning_Value()
    throws FMPlayerException
  {
    try
    {
      this.mPlayer.SkipTuning_Value();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void cancelAFSwitching()
    throws FMPlayerException
  {
    try
    {
      this.mPlayer.cancelAFSwitching();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public boolean cancelScan()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.cancelScan();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public void cancelSeek()
    throws FMPlayerException
  {
    try
    {
      this.mPlayer.cancelSeek();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void disableAF()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.disableAF();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void disableRDS()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.disableRDS();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void enableAF()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.enableAF();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void enableRDS()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.enableRDS();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  protected void finalize()
    throws Throwable
  {
    super.finalize();
    this.mAudioManager = null;
    this.mPlayer = null;
  }

  public int getCnt_th()
    throws FMPlayerException
  {
    int i = -1;
    if (isOn());
    try
    {
      int j = this.mPlayer.getCnt_th();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public int getCnt_th_2()
    throws FMPlayerException
  {
    int i = -1;
    if (isOn());
    try
    {
      int j = this.mPlayer.getCnt_th_2();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public long getCurrentChannel()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      checkBusy();
      long l2 = this.mPlayer.getCurrentChannel();
      l1 = l2;
      return l1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        long l1 = -1L;
      }
    }
  }

  public long getCurrentRSSI()
    throws FMPlayerException
  {
    if (isOn());
    while (true)
    {
      try
      {
        long l2 = this.mPlayer.getCurrentRSSI();
        l1 = l2;
        return l1;
      }
      catch (RemoteException localRemoteException)
      {
        remoteError(localRemoteException);
      }
      long l1 = -1L;
    }
  }

  public long[] getLastScanResult()
    throws FMPlayerException
  {
    Object localObject = null;
    if (isScanning());
    while (true)
    {
      return localObject;
      try
      {
        long[] arrayOfLong = this.mPlayer.getLastScanResult();
        localObject = arrayOfLong;
      }
      catch (RemoteException localRemoteException)
      {
        remoteError(localRemoteException);
      }
    }
  }

  public long getMaxVolume()
    throws FMPlayerException
  {
    try
    {
      long l2 = this.mPlayer.getMaxVolume();
      l1 = l2;
      return l1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        long l1 = -1L;
      }
    }
  }

  public int getRSSI_th()
    throws FMPlayerException
  {
    int i = -1;
    if (isOn());
    try
    {
      int j = this.mPlayer.getRSSI_th();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public int getRSSI_th_2()
    throws FMPlayerException
  {
    int i = -1;
    if (isOn());
    try
    {
      int j = this.mPlayer.getRSSI_th_2();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public int getSNR_th()
    throws FMPlayerException
  {
    int i = -1;
    if (isOn());
    try
    {
      int j = this.mPlayer.getSNR_th();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public int getSNR_th_2()
    throws FMPlayerException
  {
    int i = -1;
    if (isOn());
    try
    {
      int j = this.mPlayer.getSNR_th_2();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public boolean getSoftMuteMode()
    throws FMPlayerException
  {
    int i = 0;
    try
    {
      boolean bool = this.mPlayer.getSoftMuteMode();
      i = bool;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public long getVolume()
    throws FMPlayerException
  {
    try
    {
      long l2 = this.mPlayer.getVolume();
      l1 = l2;
      return l1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        long l1 = -1L;
      }
    }
  }

  public boolean isAFEnable()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.isAFEnable();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public boolean isAirPlaneMode()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.isAirPlaneMode();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public boolean isBatteryLow()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.isBatteryLow();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public boolean isHeadsetPlugged()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.isHeadsetPlugged();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public boolean isOn()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.isOn();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public boolean isRDSEnable()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.isRDSEnable();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public boolean isScanning()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.isScanning();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public boolean isSeeking()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.isSeeking();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public boolean isTvOutPlugged()
    throws FMPlayerException
  {
    try
    {
      boolean bool2 = this.mPlayer.isTvOutPlugged();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        boolean bool1 = false;
      }
    }
  }

  public void log(String paramString)
  {
    Log.i("FMPlayer", paramString);
  }

  public boolean off()
    throws FMPlayerException
  {
    int i = 0;
    try
    {
      boolean bool = this.mPlayer.off();
      i = bool;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public boolean on()
    throws FMPlayerException
  {
    if (isTvOutPlugged())
      throw new FMPlayerException(7, "TV out is on", new Throwable("TV out is on."));
    if (!isHeadsetPlugged())
      throw new FMPlayerException(4, "Headset is not presents.", new Throwable("Headset is not presents."));
    if (isAirPlaneMode())
      throw new FMPlayerException(5, "AirPlane mode is on.", new Throwable("AirPlane mode is on."));
    int i = 0;
    try
    {
      boolean bool = this.mPlayer.on();
      i = bool;
      if (isBatteryLow())
        throw new FMPlayerException(6, "Battery is low.", new Throwable("Batterys is low."));
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
    return i;
  }

  public boolean on(boolean paramBoolean)
    throws FMPlayerException
  {
    if (paramBoolean)
      if (isAirPlaneMode())
        throw new FMPlayerException(5, "AirPlane mode is on.", new Throwable("AirPlane mode is on."));
    for (boolean bool1 = false; ; bool1 = on())
      try
      {
        boolean bool2 = this.mPlayer.on_in_testmode();
        bool1 = bool2;
        return bool1;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          remoteError(localRemoteException);
      }
  }

  public void removeListener(FMEventListener paramFMEventListener)
    throws FMPlayerException
  {
    if (paramFMEventListener == null);
    while (true)
    {
      return;
      try
      {
        this.mPlayer.removeListener(paramFMEventListener.callback);
      }
      catch (RemoteException localRemoteException)
      {
        remoteError(localRemoteException);
      }
    }
  }

  public void scan()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      checkBusy();
      this.mPlayer.scan();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public long searchAll()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      checkBusy();
      long l2 = this.mPlayer.searchAll();
      l1 = l2;
      return l1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        long l1 = -1L;
      }
    }
  }

  public long searchDown()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      checkBusy();
      long l2 = this.mPlayer.searchDown();
      l1 = l2;
      return l1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        long l1 = -1L;
      }
    }
  }

  public long searchUp()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      checkBusy();
      long l2 = this.mPlayer.searchUp();
      l1 = l2;
      return l1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        long l1 = -1L;
      }
    }
  }

  public long seekDown()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      checkBusy();
      long l2 = this.mPlayer.seekDown();
      l1 = l2;
      return l1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        long l1 = -1L;
      }
    }
  }

  public long seekUp()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      checkBusy();
      long l2 = this.mPlayer.seekUp();
      l1 = l2;
      return l1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        long l1 = -1L;
      }
    }
  }

  public void setBand(int paramInt)
    throws FMPlayerException
  {
    try
    {
      this.mPlayer.setBand(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setChannelSpacing(int paramInt)
    throws FMPlayerException
  {
    try
    {
      this.mPlayer.setChannelSpacing(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setCnt_th(int paramInt)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setCnt_th(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setCnt_th_2(int paramInt)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setCnt_th_2(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setDEConstant(long paramLong)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setDEConstant(paramLong);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setFMIntenna(boolean paramBoolean)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setFMIntenna(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setListener(FMEventListener paramFMEventListener)
    throws FMPlayerException
  {
    if (paramFMEventListener == null);
    while (true)
    {
      return;
      try
      {
        this.mPlayer.setListener(paramFMEventListener.callback);
      }
      catch (RemoteException localRemoteException)
      {
        remoteError(localRemoteException);
      }
    }
  }

  public void setMono()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setMono();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setRSSI_th(int paramInt)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setRSSI_th(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setRSSI_th_2(int paramInt)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setRSSI_th_2(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setRecordMode(int paramInt)
    throws FMPlayerException
  {
    try
    {
      this.mPlayer.setRecordMode(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setSNR_th(int paramInt)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setSNR_th(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setSNR_th_2(int paramInt)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setSNR_th_2(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setSeekRSSI(long paramLong)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setSeekRSSI(paramLong);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setSeekSNR(long paramLong)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setSeekSNR(paramLong);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setSoftMuteControl(int paramInt1, int paramInt2, int paramInt3)
    throws FMPlayerException
  {
    try
    {
      this.mPlayer.setSoftMuteControl(paramInt1, paramInt2, paramInt3);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setSoftmute(boolean paramBoolean)
    throws FMPlayerException
  {
    try
    {
      this.mPlayer.setSoftmute(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public boolean setSpeakerOn(boolean paramBoolean)
    throws FMPlayerException
  {
    log("setting bSpeakerOn = :" + paramBoolean);
    try
    {
      this.mPlayer.setSpeakerOn(paramBoolean);
      this.mAudioManager.setRadioSpeakerOn(paramBoolean);
      if (!this.mAudioManager.isRadioSpeakerOn())
      {
        i = 1;
        return i;
      }
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        continue;
        int i = 0;
      }
    }
  }

  public void setStereo()
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.setStereo();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public void setVolume(long paramLong)
    throws FMPlayerException
  {
    try
    {
      this.mPlayer.setVolume(paramLong);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        remoteError(localRemoteException);
    }
  }

  public boolean tune(long paramLong)
    throws FMPlayerException
  {
    checkOnStatus();
    try
    {
      this.mPlayer.tune(paramLong);
      i = 1;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
      {
        remoteError(localRemoteException);
        int i = 0;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.media.fmradio.FMPlayer
 * JD-Core Version:    0.6.0
 */