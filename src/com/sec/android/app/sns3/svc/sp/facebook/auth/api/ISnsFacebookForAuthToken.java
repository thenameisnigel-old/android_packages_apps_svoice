package com.sec.android.app.sns3.svc.sp.facebook.auth.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

public abstract interface ISnsFacebookForAuthToken extends IInterface
{
  public abstract Map getAuthTokenNExpires()
    throws RemoteException;

  public abstract int getMyAccountInfo(ISnsFacebookCallbackMyAccountInfo paramISnsFacebookCallbackMyAccountInfo)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ISnsFacebookForAuthToken
  {
    private static final String DESCRIPTOR = "com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken";
    static final int TRANSACTION_getAuthTokenNExpires = 1;
    static final int TRANSACTION_getMyAccountInfo = 2;

    public Stub()
    {
      attachInterface(this, "com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken");
    }

    public static ISnsFacebookForAuthToken asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken");
        if ((localIInterface != null) && ((localIInterface instanceof ISnsFacebookForAuthToken)))
        {
          localObject = (ISnsFacebookForAuthToken)localIInterface;
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
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken");
        continue;
        paramParcel1.enforceInterface("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken");
        Map localMap = getAuthTokenNExpires();
        paramParcel2.writeNoException();
        paramParcel2.writeMap(localMap);
        continue;
        paramParcel1.enforceInterface("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken");
        int i = getMyAccountInfo(ISnsFacebookCallbackMyAccountInfo.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i);
      }
    }

    private static class Proxy
      implements ISnsFacebookForAuthToken
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

      public Map getAuthTokenNExpires()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken");
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          HashMap localHashMap = localParcel2.readHashMap(getClass().getClassLoader());
          return localHashMap;
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
        return "com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken";
      }

      public int getMyAccountInfo(ISnsFacebookCallbackMyAccountInfo paramISnsFacebookCallbackMyAccountInfo)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken");
          if (paramISnsFacebookCallbackMyAccountInfo != null)
          {
            localIBinder = paramISnsFacebookCallbackMyAccountInfo.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(2, localParcel1, localParcel2, 0);
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
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookForAuthToken
 * JD-Core Version:    0.6.0
 */