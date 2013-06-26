package com.sec.android.internal.ims;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IIMSBTService extends IInterface
{
  public abstract String getRingingCallNumber()
    throws RemoteException;

  public abstract String getVolteClccResult()
    throws RemoteException;

  public abstract boolean newBTEvent(int paramInt)
    throws RemoteException;

  public abstract void registerListener(IIMSBTCallStateListener paramIIMSBTCallStateListener)
    throws RemoteException;

  public abstract void resetAtState()
    throws RemoteException;

  public abstract void sendDtmf(char paramChar)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IIMSBTService
  {
    private static final String DESCRIPTOR = "com.sec.android.internal.ims.IIMSBTService";
    static final int TRANSACTION_getRingingCallNumber = 3;
    static final int TRANSACTION_getVolteClccResult = 4;
    static final int TRANSACTION_newBTEvent = 2;
    static final int TRANSACTION_registerListener = 1;
    static final int TRANSACTION_resetAtState = 5;
    static final int TRANSACTION_sendDtmf = 6;

    public Stub()
    {
      attachInterface(this, "com.sec.android.internal.ims.IIMSBTService");
    }

    public static IIMSBTService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.internal.ims.IIMSBTService");
        if ((localIInterface != null) && ((localIInterface instanceof IIMSBTService)))
        {
          localObject = (IIMSBTService)localIInterface;
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
      int i = 1;
      switch (paramInt1)
      {
      default:
        i = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      }
      while (true)
      {
        return i;
        paramParcel2.writeString("com.sec.android.internal.ims.IIMSBTService");
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSBTService");
        registerListener(IIMSBTCallStateListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSBTService");
        boolean bool = newBTEvent(paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (bool);
        int k;
        for (int j = i; ; k = 0)
        {
          paramParcel2.writeInt(j);
          break;
        }
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSBTService");
        String str2 = getRingingCallNumber();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str2);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSBTService");
        String str1 = getVolteClccResult();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str1);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSBTService");
        resetAtState();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSBTService");
        sendDtmf((char)paramParcel1.readInt());
        paramParcel2.writeNoException();
      }
    }

    private static class Proxy
      implements IIMSBTService
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

      public String getInterfaceDescriptor()
      {
        return "com.sec.android.internal.ims.IIMSBTService";
      }

      public String getRingingCallNumber()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSBTService");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public String getVolteClccResult()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSBTService");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean newBTEvent(int paramInt)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSBTService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
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

      public void registerListener(IIMSBTCallStateListener paramIIMSBTCallStateListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSBTService");
          if (paramIIMSBTCallStateListener != null)
          {
            localIBinder = paramIIMSBTCallStateListener.asBinder();
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

      public void resetAtState()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSBTService");
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
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

      public void sendDtmf(char paramChar)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSBTService");
          localParcel1.writeInt(paramChar);
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
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
 * Qualified Name:     com.sec.android.internal.ims.IIMSBTService
 * JD-Core Version:    0.6.0
 */