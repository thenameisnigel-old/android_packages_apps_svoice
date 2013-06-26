package com.samsung.media.fmradio.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IFMPlayer extends IInterface
{
  public abstract void SkipTuning_Value()
    throws RemoteException;

  public abstract void cancelAFSwitching()
    throws RemoteException;

  public abstract boolean cancelScan()
    throws RemoteException;

  public abstract void cancelSeek()
    throws RemoteException;

  public abstract void disableAF()
    throws RemoteException;

  public abstract void disableRDS()
    throws RemoteException;

  public abstract void enableAF()
    throws RemoteException;

  public abstract void enableRDS()
    throws RemoteException;

  public abstract int getAFValid_th()
    throws RemoteException;

  public abstract int getAF_th()
    throws RemoteException;

  public abstract int getCnt_th()
    throws RemoteException;

  public abstract int getCnt_th_2()
    throws RemoteException;

  public abstract long getCurrentChannel()
    throws RemoteException;

  public abstract long getCurrentRSSI()
    throws RemoteException;

  public abstract long[] getLastScanResult()
    throws RemoteException;

  public abstract long getMaxVolume()
    throws RemoteException;

  public abstract int getRSSI_th()
    throws RemoteException;

  public abstract int getRSSI_th_2()
    throws RemoteException;

  public abstract int getSNR_th()
    throws RemoteException;

  public abstract int getSNR_th_2()
    throws RemoteException;

  public abstract boolean getSoftMuteMode()
    throws RemoteException;

  public abstract long getVolume()
    throws RemoteException;

  public abstract boolean isAFEnable()
    throws RemoteException;

  public abstract boolean isAirPlaneMode()
    throws RemoteException;

  public abstract boolean isBatteryLow()
    throws RemoteException;

  public abstract int isBusy()
    throws RemoteException;

  public abstract boolean isHeadsetPlugged()
    throws RemoteException;

  public abstract boolean isOn()
    throws RemoteException;

  public abstract boolean isRDSEnable()
    throws RemoteException;

  public abstract boolean isScanning()
    throws RemoteException;

  public abstract boolean isSeeking()
    throws RemoteException;

  public abstract boolean isTvOutPlugged()
    throws RemoteException;

  public abstract boolean off()
    throws RemoteException;

  public abstract boolean on()
    throws RemoteException;

  public abstract boolean on_in_testmode()
    throws RemoteException;

  public abstract void removeListener(IFMEventListener paramIFMEventListener)
    throws RemoteException;

  public abstract void scan()
    throws RemoteException;

  public abstract long searchAll()
    throws RemoteException;

  public abstract long searchDown()
    throws RemoteException;

  public abstract long searchUp()
    throws RemoteException;

  public abstract long seekDown()
    throws RemoteException;

  public abstract long seekUp()
    throws RemoteException;

  public abstract void setAFValid_th(int paramInt)
    throws RemoteException;

  public abstract void setAF_th(int paramInt)
    throws RemoteException;

  public abstract void setBand(int paramInt)
    throws RemoteException;

  public abstract void setChannelSpacing(int paramInt)
    throws RemoteException;

  public abstract void setCnt_th(int paramInt)
    throws RemoteException;

  public abstract void setCnt_th_2(int paramInt)
    throws RemoteException;

  public abstract void setDEConstant(long paramLong)
    throws RemoteException;

  public abstract void setFMIntenna(boolean paramBoolean)
    throws RemoteException;

  public abstract void setListener(IFMEventListener paramIFMEventListener)
    throws RemoteException;

  public abstract void setMono()
    throws RemoteException;

  public abstract void setRSSI_th(int paramInt)
    throws RemoteException;

  public abstract void setRSSI_th_2(int paramInt)
    throws RemoteException;

  public abstract void setRecordMode(int paramInt)
    throws RemoteException;

  public abstract void setSNR_th(int paramInt)
    throws RemoteException;

  public abstract void setSNR_th_2(int paramInt)
    throws RemoteException;

  public abstract void setSeekRSSI(long paramLong)
    throws RemoteException;

  public abstract void setSeekSNR(long paramLong)
    throws RemoteException;

  public abstract void setSoftMuteControl(int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;

  public abstract void setSoftmute(boolean paramBoolean)
    throws RemoteException;

  public abstract void setSpeakerOn(boolean paramBoolean)
    throws RemoteException;

  public abstract void setStereo()
    throws RemoteException;

  public abstract void setVolume(long paramLong)
    throws RemoteException;

  public abstract void tune(long paramLong)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IFMPlayer
  {
    private static final String DESCRIPTOR = "com.samsung.media.fmradio.internal.IFMPlayer";
    static final int TRANSACTION_SkipTuning_Value = 32;
    static final int TRANSACTION_cancelAFSwitching = 27;
    static final int TRANSACTION_cancelScan = 12;
    static final int TRANSACTION_cancelSeek = 9;
    static final int TRANSACTION_disableAF = 21;
    static final int TRANSACTION_disableRDS = 19;
    static final int TRANSACTION_enableAF = 20;
    static final int TRANSACTION_enableRDS = 18;
    static final int TRANSACTION_getAFValid_th = 61;
    static final int TRANSACTION_getAF_th = 59;
    static final int TRANSACTION_getCnt_th = 52;
    static final int TRANSACTION_getCnt_th_2 = 55;
    static final int TRANSACTION_getCurrentChannel = 10;
    static final int TRANSACTION_getCurrentRSSI = 41;
    static final int TRANSACTION_getLastScanResult = 28;
    static final int TRANSACTION_getMaxVolume = 38;
    static final int TRANSACTION_getRSSI_th = 50;
    static final int TRANSACTION_getRSSI_th_2 = 53;
    static final int TRANSACTION_getSNR_th = 51;
    static final int TRANSACTION_getSNR_th_2 = 54;
    static final int TRANSACTION_getSoftMuteMode = 64;
    static final int TRANSACTION_getVolume = 33;
    static final int TRANSACTION_isAFEnable = 26;
    static final int TRANSACTION_isAirPlaneMode = 39;
    static final int TRANSACTION_isBatteryLow = 57;
    static final int TRANSACTION_isBusy = 24;
    static final int TRANSACTION_isHeadsetPlugged = 34;
    static final int TRANSACTION_isOn = 6;
    static final int TRANSACTION_isRDSEnable = 25;
    static final int TRANSACTION_isScanning = 13;
    static final int TRANSACTION_isSeeking = 14;
    static final int TRANSACTION_isTvOutPlugged = 35;
    static final int TRANSACTION_off = 5;
    static final int TRANSACTION_on = 4;
    static final int TRANSACTION_on_in_testmode = 56;
    static final int TRANSACTION_removeListener = 2;
    static final int TRANSACTION_scan = 11;
    static final int TRANSACTION_searchAll = 17;
    static final int TRANSACTION_searchDown = 15;
    static final int TRANSACTION_searchUp = 16;
    static final int TRANSACTION_seekDown = 8;
    static final int TRANSACTION_seekUp = 7;
    static final int TRANSACTION_setAFValid_th = 60;
    static final int TRANSACTION_setAF_th = 58;
    static final int TRANSACTION_setBand = 22;
    static final int TRANSACTION_setChannelSpacing = 23;
    static final int TRANSACTION_setCnt_th = 46;
    static final int TRANSACTION_setCnt_th_2 = 49;
    static final int TRANSACTION_setDEConstant = 40;
    static final int TRANSACTION_setFMIntenna = 62;
    static final int TRANSACTION_setListener = 1;
    static final int TRANSACTION_setMono = 30;
    static final int TRANSACTION_setRSSI_th = 44;
    static final int TRANSACTION_setRSSI_th_2 = 47;
    static final int TRANSACTION_setRecordMode = 37;
    static final int TRANSACTION_setSNR_th = 45;
    static final int TRANSACTION_setSNR_th_2 = 48;
    static final int TRANSACTION_setSeekRSSI = 42;
    static final int TRANSACTION_setSeekSNR = 43;
    static final int TRANSACTION_setSoftMuteControl = 65;
    static final int TRANSACTION_setSoftmute = 63;
    static final int TRANSACTION_setSpeakerOn = 36;
    static final int TRANSACTION_setStereo = 29;
    static final int TRANSACTION_setVolume = 31;
    static final int TRANSACTION_tune = 3;

    public Stub()
    {
      attachInterface(this, "com.samsung.media.fmradio.internal.IFMPlayer");
    }

    public static IFMPlayer asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        if ((localIInterface != null) && ((localIInterface instanceof IFMPlayer)))
        {
          localObject = (IFMPlayer)localIInterface;
          continue;
        }
        localObject = new Proxy(paramIBinder);
      }
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int i = 0;
      int j = 1;
      switch (paramInt1)
      {
      default:
        j = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
      case 46:
      case 47:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
      case 65:
      }
      while (true)
      {
        return j;
        paramParcel2.writeString("com.samsung.media.fmradio.internal.IFMPlayer");
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setListener(IFMEventListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        removeListener(IFMEventListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        tune(paramParcel1.readLong());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool17 = on();
        paramParcel2.writeNoException();
        if (bool17)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool16 = off();
        paramParcel2.writeNoException();
        if (bool16)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool15 = isOn();
        paramParcel2.writeNoException();
        if (bool15)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long l9 = seekUp();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l9);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long l8 = seekDown();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l8);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        cancelSeek();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long l7 = getCurrentChannel();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l7);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        scan();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool14 = cancelScan();
        paramParcel2.writeNoException();
        if (bool14)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool13 = isScanning();
        paramParcel2.writeNoException();
        if (bool13)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool12 = isSeeking();
        paramParcel2.writeNoException();
        if (bool12)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long l6 = searchDown();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l6);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long l5 = searchUp();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l5);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long l4 = searchAll();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l4);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        enableRDS();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        disableRDS();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        enableAF();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        disableAF();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setBand(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setChannelSpacing(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        int i6 = isBusy();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i6);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool11 = isRDSEnable();
        paramParcel2.writeNoException();
        if (bool11)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool10 = isAFEnable();
        paramParcel2.writeNoException();
        if (bool10)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        cancelAFSwitching();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long[] arrayOfLong = getLastScanResult();
        paramParcel2.writeNoException();
        paramParcel2.writeLongArray(arrayOfLong);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setStereo();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setMono();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setVolume(paramParcel1.readLong());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        SkipTuning_Value();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long l3 = getVolume();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l3);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool9 = isHeadsetPlugged();
        paramParcel2.writeNoException();
        if (bool9)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool8 = isTvOutPlugged();
        paramParcel2.writeNoException();
        if (bool8)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        if (paramParcel1.readInt() != 0);
        for (boolean bool7 = j; ; bool7 = false)
        {
          setSpeakerOn(bool7);
          paramParcel2.writeNoException();
          break;
        }
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setRecordMode(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long l2 = getMaxVolume();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l2);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool6 = isAirPlaneMode();
        paramParcel2.writeNoException();
        if (bool6)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setDEConstant(paramParcel1.readLong());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        long l1 = getCurrentRSSI();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l1);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setSeekRSSI(paramParcel1.readLong());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setSeekSNR(paramParcel1.readLong());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setRSSI_th(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setSNR_th(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setCnt_th(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setRSSI_th_2(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setSNR_th_2(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setCnt_th_2(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        int i5 = getRSSI_th();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i5);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        int i4 = getSNR_th();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i4);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        int i3 = getCnt_th();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i3);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        int i2 = getRSSI_th_2();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i2);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        int i1 = getSNR_th_2();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i1);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        int n = getCnt_th_2();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(n);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool5 = on_in_testmode();
        paramParcel2.writeNoException();
        if (bool5)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool4 = isBatteryLow();
        paramParcel2.writeNoException();
        if (bool4)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setAF_th(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        int m = getAF_th();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(m);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setAFValid_th(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        int k = getAFValid_th();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(k);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        if (paramParcel1.readInt() != 0);
        for (boolean bool3 = j; ; bool3 = false)
        {
          setFMIntenna(bool3);
          paramParcel2.writeNoException();
          break;
        }
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        if (paramParcel1.readInt() != 0);
        for (boolean bool2 = j; ; bool2 = false)
        {
          setSoftmute(bool2);
          paramParcel2.writeNoException();
          break;
        }
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        boolean bool1 = getSoftMuteMode();
        paramParcel2.writeNoException();
        if (bool1)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMPlayer");
        setSoftMuteControl(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
      }
    }

    private static class Proxy
      implements IFMPlayer
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public void SkipTuning_Value()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(32, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public void cancelAFSwitching()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(27, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean cancelScan()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void cancelSeek()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void disableAF()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(21, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void disableRDS()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(19, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void enableAF()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(20, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void enableRDS()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(18, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getAFValid_th()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(61, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getAF_th()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(59, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getCnt_th()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(52, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getCnt_th_2()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(55, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public long getCurrentChannel()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(10, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public long getCurrentRSSI()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(41, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public String getInterfaceDescriptor()
      {
        return "com.samsung.media.fmradio.internal.IFMPlayer";
      }

      public long[] getLastScanResult()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(28, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long[] arrayOfLong = localParcel2.createLongArray();
          return arrayOfLong;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public long getMaxVolume()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(38, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getRSSI_th()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(50, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getRSSI_th_2()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(53, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getSNR_th()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(51, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getSNR_th_2()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(54, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean getSoftMuteMode()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(64, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public long getVolume()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(33, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean isAFEnable()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(26, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean isAirPlaneMode()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(39, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean isBatteryLow()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(57, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int isBusy()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(24, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean isHeadsetPlugged()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(34, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean isOn()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean isRDSEnable()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(25, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean isScanning()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(13, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean isSeeking()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean isTvOutPlugged()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(35, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean off()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean on()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean on_in_testmode()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(56, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            i = 1;
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void removeListener(IFMEventListener paramIFMEventListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          if (paramIFMEventListener != null)
          {
            localIBinder = paramIFMEventListener.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void scan()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public long searchAll()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public long searchDown()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(15, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public long searchUp()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(16, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public long seekDown()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public long seekUp()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setAFValid_th(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(60, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setAF_th(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(58, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setBand(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(22, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setChannelSpacing(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(23, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setCnt_th(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(46, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setCnt_th_2(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(49, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setDEConstant(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeLong(paramLong);
          this.mRemote.transact(40, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setFMIntenna(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.mRemote.transact(62, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setListener(IFMEventListener paramIFMEventListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          if (paramIFMEventListener != null)
          {
            localIBinder = paramIFMEventListener.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void setMono()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(30, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setRSSI_th(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(44, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setRSSI_th_2(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(47, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setRecordMode(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(37, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setSNR_th(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(45, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setSNR_th_2(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(48, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setSeekRSSI(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeLong(paramLong);
          this.mRemote.transact(42, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setSeekSNR(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeLong(paramLong);
          this.mRemote.transact(43, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setSoftMuteControl(int paramInt1, int paramInt2, int paramInt3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          this.mRemote.transact(65, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setSoftmute(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.mRemote.transact(63, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setSpeakerOn(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.mRemote.transact(36, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setStereo()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          this.mRemote.transact(29, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setVolume(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeLong(paramLong);
          this.mRemote.transact(31, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void tune(long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMPlayer");
          localParcel1.writeLong(paramLong);
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.media.fmradio.internal.IFMPlayer
 * JD-Core Version:    0.6.0
 */