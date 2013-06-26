package com.android.email.backgroundsender;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IEmailRemoteService extends IInterface
{
  public abstract boolean registerCallback(IEmailRemoteServiceCallback paramIEmailRemoteServiceCallback)
    throws RemoteException;

  public abstract void unregisterCallback(IEmailRemoteServiceCallback paramIEmailRemoteServiceCallback)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IEmailRemoteService
  {
    private static final String DESCRIPTOR = "com.android.email.backgroundsender.IEmailRemoteService";
    static final int TRANSACTION_registerCallback = 1;
    static final int TRANSACTION_unregisterCallback = 2;

    public Stub()
    {
      attachInterface(this, "com.android.email.backgroundsender.IEmailRemoteService");
    }

    public static IEmailRemoteService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.email.backgroundsender.IEmailRemoteService");
        if ((localIInterface != null) && ((localIInterface instanceof IEmailRemoteService)))
        {
          localObject = (IEmailRemoteService)localIInterface;
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
      }
      while (true)
      {
        return i;
        paramParcel2.writeString("com.android.email.backgroundsender.IEmailRemoteService");
        continue;
        paramParcel1.enforceInterface("com.android.email.backgroundsender.IEmailRemoteService");
        boolean bool = registerCallback(IEmailRemoteServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        if (bool);
        int k;
        for (int j = i; ; k = 0)
        {
          paramParcel2.writeInt(j);
          break;
        }
        paramParcel1.enforceInterface("com.android.email.backgroundsender.IEmailRemoteService");
        unregisterCallback(IEmailRemoteServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
      }
    }

    private static class Proxy
      implements IEmailRemoteService
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
        return "com.android.email.backgroundsender.IEmailRemoteService";
      }

      public boolean registerCallback(IEmailRemoteServiceCallback paramIEmailRemoteServiceCallback)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.email.backgroundsender.IEmailRemoteService");
          if (paramIEmailRemoteServiceCallback != null);
          for (IBinder localIBinder = paramIEmailRemoteServiceCallback.asBinder(); ; localIBinder = null)
          {
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int j = localParcel2.readInt();
            if (j == 0)
              break;
            return i;
          }
          i = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void unregisterCallback(IEmailRemoteServiceCallback paramIEmailRemoteServiceCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.email.backgroundsender.IEmailRemoteService");
          if (paramIEmailRemoteServiceCallback != null)
          {
            localIBinder = paramIEmailRemoteServiceCallback.asBinder();
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
 * Qualified Name:     com.android.email.backgroundsender.IEmailRemoteService
 * JD-Core Version:    0.6.0
 */