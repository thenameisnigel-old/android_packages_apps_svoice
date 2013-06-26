package com.sec.android.smartface;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface ISmartFaceService extends IInterface
{
  public abstract int getSupportedServices()
    throws RemoteException;

  public abstract void register(ISmartFaceClient paramISmartFaceClient, int paramInt)
    throws RemoteException;

  public abstract void setValue(ISmartFaceClient paramISmartFaceClient, String paramString1, String paramString2)
    throws RemoteException;

  public abstract void unregister(ISmartFaceClient paramISmartFaceClient)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ISmartFaceService
  {
    private static final String DESCRIPTOR = "com.sec.android.smartface.ISmartFaceService";
    static final int TRANSACTION_getSupportedServices = 4;
    static final int TRANSACTION_register = 1;
    static final int TRANSACTION_setValue = 3;
    static final int TRANSACTION_unregister = 2;

    public Stub()
    {
      attachInterface(this, "com.sec.android.smartface.ISmartFaceService");
    }

    public static ISmartFaceService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.smartface.ISmartFaceService");
        if ((localIInterface != null) && ((localIInterface instanceof ISmartFaceService)))
        {
          localObject = (ISmartFaceService)localIInterface;
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
      boolean bool = true;
      switch (paramInt1)
      {
      default:
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      case 2:
      case 3:
      case 4:
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("com.sec.android.smartface.ISmartFaceService");
        continue;
        paramParcel1.enforceInterface("com.sec.android.smartface.ISmartFaceService");
        register(ISmartFaceClient.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.smartface.ISmartFaceService");
        unregister(ISmartFaceClient.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.smartface.ISmartFaceService");
        setValue(ISmartFaceClient.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.smartface.ISmartFaceService");
        int i = getSupportedServices();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i);
      }
    }

    private static class Proxy
      implements ISmartFaceService
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
        return "com.sec.android.smartface.ISmartFaceService";
      }

      public int getSupportedServices()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.smartface.ISmartFaceService");
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

      public void register(ISmartFaceClient paramISmartFaceClient, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.smartface.ISmartFaceService");
          if (paramISmartFaceClient != null)
          {
            localIBinder = paramISmartFaceClient.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
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

      public void setValue(ISmartFaceClient paramISmartFaceClient, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.smartface.ISmartFaceService");
          if (paramISmartFaceClient != null)
          {
            localIBinder = paramISmartFaceClient.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            this.mRemote.transact(3, localParcel1, localParcel2, 0);
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

      public void unregister(ISmartFaceClient paramISmartFaceClient)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.smartface.ISmartFaceService");
          if (paramISmartFaceClient != null)
          {
            localIBinder = paramISmartFaceClient.asBinder();
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
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.smartface.ISmartFaceService
 * JD-Core Version:    0.6.0
 */