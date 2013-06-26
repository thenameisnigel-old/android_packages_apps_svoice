package com.sina.sso;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface RemoteSSO extends IInterface
{
  public abstract String getActivityName()
    throws RemoteException;

  public abstract String getPackageName()
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements RemoteSSO
  {
    private static final String DESCRIPTOR = "com.sina.sso.RemoteSSO";
    static final int TRANSACTION_getActivityName = 2;
    static final int TRANSACTION_getPackageName = 1;

    public Stub()
    {
      attachInterface(this, "com.sina.sso.RemoteSSO");
    }

    public static RemoteSSO asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sina.sso.RemoteSSO");
        if ((localIInterface != null) && ((localIInterface instanceof RemoteSSO)))
        {
          localObject = (RemoteSSO)localIInterface;
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
        paramParcel2.writeString("com.sina.sso.RemoteSSO");
        continue;
        paramParcel1.enforceInterface("com.sina.sso.RemoteSSO");
        String str2 = getPackageName();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str2);
        continue;
        paramParcel1.enforceInterface("com.sina.sso.RemoteSSO");
        String str1 = getActivityName();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str1);
      }
    }

    private static class Proxy
      implements RemoteSSO
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

      public String getActivityName()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sina.sso.RemoteSSO");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
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

      public String getInterfaceDescriptor()
      {
        return "com.sina.sso.RemoteSSO";
      }

      public String getPackageName()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sina.sso.RemoteSSO");
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
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
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sina.sso.RemoteSSO
 * JD-Core Version:    0.6.0
 */