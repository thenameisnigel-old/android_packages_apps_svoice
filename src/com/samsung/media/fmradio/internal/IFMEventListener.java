package com.samsung.media.fmradio.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IFMEventListener extends IInterface
{
  public abstract void earPhoneConnected()
    throws RemoteException;

  public abstract void earPhoneDisconnected()
    throws RemoteException;

  public abstract void onAFReceived(long paramLong)
    throws RemoteException;

  public abstract void onAFStarted()
    throws RemoteException;

  public abstract void onChannelFound(long paramLong)
    throws RemoteException;

  public abstract void onOff(int paramInt)
    throws RemoteException;

  public abstract void onOn()
    throws RemoteException;

  public abstract void onRDSDisabled()
    throws RemoteException;

  public abstract void onRDSEnabled()
    throws RemoteException;

  public abstract void onRDSReceived(long paramLong, String paramString1, String paramString2)
    throws RemoteException;

  public abstract void onRTPlusReceived(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    throws RemoteException;

  public abstract void onScanFinished(long[] paramArrayOfLong)
    throws RemoteException;

  public abstract void onScanStarted()
    throws RemoteException;

  public abstract void onScanStopped(long[] paramArrayOfLong)
    throws RemoteException;

  public abstract void onTune(long paramLong)
    throws RemoteException;

  public abstract void recFinish()
    throws RemoteException;

  public abstract void volumeLock()
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IFMEventListener
  {
    private static final String DESCRIPTOR = "com.samsung.media.fmradio.internal.IFMEventListener";
    static final int TRANSACTION_earPhoneConnected = 8;
    static final int TRANSACTION_earPhoneDisconnected = 9;
    static final int TRANSACTION_onAFReceived = 15;
    static final int TRANSACTION_onAFStarted = 14;
    static final int TRANSACTION_onChannelFound = 3;
    static final int TRANSACTION_onOff = 2;
    static final int TRANSACTION_onOn = 1;
    static final int TRANSACTION_onRDSDisabled = 13;
    static final int TRANSACTION_onRDSEnabled = 12;
    static final int TRANSACTION_onRDSReceived = 10;
    static final int TRANSACTION_onRTPlusReceived = 11;
    static final int TRANSACTION_onScanFinished = 6;
    static final int TRANSACTION_onScanStarted = 4;
    static final int TRANSACTION_onScanStopped = 5;
    static final int TRANSACTION_onTune = 7;
    static final int TRANSACTION_recFinish = 17;
    static final int TRANSACTION_volumeLock = 16;

    public Stub()
    {
      attachInterface(this, "com.samsung.media.fmradio.internal.IFMEventListener");
    }

    public static IFMEventListener asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        if ((localIInterface != null) && ((localIInterface instanceof IFMEventListener)))
        {
          localObject = (IFMEventListener)localIInterface;
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
      boolean bool;
      switch (paramInt1)
      {
      default:
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
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
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("com.samsung.media.fmradio.internal.IFMEventListener");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onOn();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onOff(paramParcel1.readInt());
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onChannelFound(paramParcel1.readLong());
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onScanStarted();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        int j = paramParcel1.readInt();
        if (j < 0);
        for (long[] arrayOfLong2 = null; ; arrayOfLong2 = new long[j])
        {
          onScanStopped(arrayOfLong2);
          paramParcel2.writeLongArray(arrayOfLong2);
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        int i = paramParcel1.readInt();
        if (i < 0);
        for (long[] arrayOfLong1 = null; ; arrayOfLong1 = new long[i])
        {
          onScanFinished(arrayOfLong1);
          paramParcel2.writeLongArray(arrayOfLong1);
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onTune(paramParcel1.readLong());
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        earPhoneConnected();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        earPhoneDisconnected();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onRDSReceived(paramParcel1.readLong(), paramParcel1.readString(), paramParcel1.readString());
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onRTPlusReceived(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onRDSEnabled();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onRDSDisabled();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onAFStarted();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        onAFReceived(paramParcel1.readLong());
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        volumeLock();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.samsung.media.fmradio.internal.IFMEventListener");
        recFinish();
        bool = true;
      }
    }

    private static class Proxy
      implements IFMEventListener
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public void earPhoneConnected()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          this.mRemote.transact(8, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void earPhoneDisconnected()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          this.mRemote.transact(9, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public String getInterfaceDescriptor()
      {
        return "com.samsung.media.fmradio.internal.IFMEventListener";
      }

      public void onAFReceived(long paramLong)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          localParcel.writeLong(paramLong);
          this.mRemote.transact(15, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onAFStarted()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          this.mRemote.transact(14, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onChannelFound(long paramLong)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          localParcel.writeLong(paramLong);
          this.mRemote.transact(3, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onOff(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(2, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onOn()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          this.mRemote.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onRDSDisabled()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          this.mRemote.transact(13, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onRDSEnabled()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          this.mRemote.transact(12, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onRDSReceived(long paramLong, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          localParcel.writeLong(paramLong);
          localParcel.writeString(paramString1);
          localParcel.writeString(paramString2);
          this.mRemote.transact(10, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onRTPlusReceived(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          localParcel.writeInt(paramInt3);
          localParcel.writeInt(paramInt4);
          localParcel.writeInt(paramInt5);
          localParcel.writeInt(paramInt6);
          this.mRemote.transact(11, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onScanFinished(long[] paramArrayOfLong)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          if (paramArrayOfLong == null)
            localParcel.writeInt(-1);
          while (true)
          {
            this.mRemote.transact(6, localParcel, null, 1);
            return;
            localParcel.writeInt(paramArrayOfLong.length);
          }
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onScanStarted()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          this.mRemote.transact(4, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onScanStopped(long[] paramArrayOfLong)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          if (paramArrayOfLong == null)
            localParcel.writeInt(-1);
          while (true)
          {
            this.mRemote.transact(5, localParcel, null, 1);
            return;
            localParcel.writeInt(paramArrayOfLong.length);
          }
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onTune(long paramLong)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          localParcel.writeLong(paramLong);
          this.mRemote.transact(7, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void recFinish()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          this.mRemote.transact(17, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void volumeLock()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.media.fmradio.internal.IFMEventListener");
          this.mRemote.transact(16, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.media.fmradio.internal.IFMEventListener
 * JD-Core Version:    0.6.0
 */