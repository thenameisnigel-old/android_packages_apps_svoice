package com.android.email.backgroundsender;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IEmailRemoteServiceCallback extends IInterface
{
  public abstract void onResponse(int paramInt1, int paramInt2)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IEmailRemoteServiceCallback
  {
    private static final String DESCRIPTOR = "com.android.email.backgroundsender.IEmailRemoteServiceCallback";
    static final int TRANSACTION_onResponse = 1;

    public Stub()
    {
      attachInterface(this, "com.android.email.backgroundsender.IEmailRemoteServiceCallback");
    }

    public static IEmailRemoteServiceCallback asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.email.backgroundsender.IEmailRemoteServiceCallback");
        if ((localIInterface != null) && ((localIInterface instanceof IEmailRemoteServiceCallback)))
        {
          localObject = (IEmailRemoteServiceCallback)localIInterface;
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
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("com.android.email.backgroundsender.IEmailRemoteServiceCallback");
        continue;
        paramParcel1.enforceInterface("com.android.email.backgroundsender.IEmailRemoteServiceCallback");
        onResponse(paramParcel1.readInt(), paramParcel1.readInt());
      }
    }

    private static class Proxy
      implements IEmailRemoteServiceCallback
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
        return "com.android.email.backgroundsender.IEmailRemoteServiceCallback";
      }

      public void onResponse(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.android.email.backgroundsender.IEmailRemoteServiceCallback");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          this.mRemote.transact(1, localParcel, null, 1);
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
 * Qualified Name:     com.android.email.backgroundsender.IEmailRemoteServiceCallback
 * JD-Core Version:    0.6.0
 */