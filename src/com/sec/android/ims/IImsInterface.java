package com.sec.android.ims;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IImsInterface extends IInterface
{
  public abstract boolean getImsRegStatus()
    throws RemoteException;

  public abstract int mmSS_Svc_Api(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2)
    throws RemoteException;

  public abstract int mmTelSvcCallFunc(int paramInt1, int paramInt2, int paramInt3, String paramString)
    throws RemoteException;

  public abstract int registerApp(int paramInt, String paramString, IImsServiceCallback paramIImsServiceCallback)
    throws RemoteException;

  public abstract int registerSSApp(int paramInt, String paramString, IImsServiceCallback paramIImsServiceCallback)
    throws RemoteException;

  public abstract void unRegisterApp(int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract void unRegisterSSApp(int paramInt1, int paramInt2)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IImsInterface
  {
    private static final String DESCRIPTOR = "com.sec.android.ims.IImsInterface";
    static final int TRANSACTION_getImsRegStatus = 3;
    static final int TRANSACTION_mmSS_Svc_Api = 5;
    static final int TRANSACTION_mmTelSvcCallFunc = 4;
    static final int TRANSACTION_registerApp = 1;
    static final int TRANSACTION_registerSSApp = 6;
    static final int TRANSACTION_unRegisterApp = 2;
    static final int TRANSACTION_unRegisterSSApp = 7;

    public Stub()
    {
      attachInterface(this, "com.sec.android.ims.IImsInterface");
    }

    public static IImsInterface asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.ims.IImsInterface");
        if ((localIInterface != null) && ((localIInterface instanceof IImsInterface)))
        {
          localObject = (IImsInterface)localIInterface;
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
      case 7:
      }
      while (true)
      {
        return i;
        paramParcel2.writeString("com.sec.android.ims.IImsInterface");
        continue;
        paramParcel1.enforceInterface("com.sec.android.ims.IImsInterface");
        int i2 = registerApp(paramParcel1.readInt(), paramParcel1.readString(), IImsServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i2);
        continue;
        paramParcel1.enforceInterface("com.sec.android.ims.IImsInterface");
        unRegisterApp(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.ims.IImsInterface");
        boolean bool = getImsRegStatus();
        paramParcel2.writeNoException();
        if (bool);
        int i1;
        for (int n = i; ; i1 = 0)
        {
          paramParcel2.writeInt(n);
          break;
        }
        paramParcel1.enforceInterface("com.sec.android.ims.IImsInterface");
        int m = mmTelSvcCallFunc(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(m);
        continue;
        paramParcel1.enforceInterface("com.sec.android.ims.IImsInterface");
        int k = mmSS_Svc_Api(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(k);
        continue;
        paramParcel1.enforceInterface("com.sec.android.ims.IImsInterface");
        int j = registerSSApp(paramParcel1.readInt(), paramParcel1.readString(), IImsServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(j);
        continue;
        paramParcel1.enforceInterface("com.sec.android.ims.IImsInterface");
        unRegisterSSApp(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
      }
    }

    private static class Proxy
      implements IImsInterface
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

      public boolean getImsRegStatus()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.ims.IImsInterface");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
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

      public String getInterfaceDescriptor()
      {
        return "com.sec.android.ims.IImsInterface";
      }

      public int mmSS_Svc_Api(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.ims.IImsInterface");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
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

      public int mmTelSvcCallFunc(int paramInt1, int paramInt2, int paramInt3, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.ims.IImsInterface");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeString(paramString);
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
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

      public int registerApp(int paramInt, String paramString, IImsServiceCallback paramIImsServiceCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.ims.IImsInterface");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          if (paramIImsServiceCallback != null)
          {
            localIBinder = paramIImsServiceCallback.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int registerSSApp(int paramInt, String paramString, IImsServiceCallback paramIImsServiceCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.ims.IImsInterface");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          if (paramIImsServiceCallback != null)
          {
            localIBinder = paramIImsServiceCallback.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(6, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void unRegisterApp(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.ims.IImsInterface");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
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

      public void unRegisterSSApp(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.ims.IImsInterface");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
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
 * Qualified Name:     com.sec.android.ims.IImsInterface
 * JD-Core Version:    0.6.0
 */