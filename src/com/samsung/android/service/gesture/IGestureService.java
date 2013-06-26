package com.samsung.android.service.gesture;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract interface IGestureService extends IInterface
{
  public abstract Bundle getProviderInfo(String paramString)
    throws RemoteException;

  public abstract List<String> getProviders()
    throws RemoteException;

  public abstract void registerCallback(IBinder paramIBinder, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException;

  public abstract void resetGestureService(String paramString)
    throws RemoteException;

  public abstract boolean unregisterCallback(IBinder paramIBinder, String paramString)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IGestureService
  {
    private static final String DESCRIPTOR = "com.samsung.android.service.gesture.IGestureService";
    static final int TRANSACTION_getProviderInfo = 5;
    static final int TRANSACTION_getProviders = 4;
    static final int TRANSACTION_registerCallback = 1;
    static final int TRANSACTION_resetGestureService = 3;
    static final int TRANSACTION_unregisterCallback = 2;

    public Stub()
    {
      attachInterface(this, "com.samsung.android.service.gesture.IGestureService");
    }

    public static IGestureService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.samsung.android.service.gesture.IGestureService");
        if ((localIInterface != null) && ((localIInterface instanceof IGestureService)))
        {
          localObject = (IGestureService)localIInterface;
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
      }
      while (true)
      {
        return j;
        paramParcel2.writeString("com.samsung.android.service.gesture.IGestureService");
        continue;
        paramParcel1.enforceInterface("com.samsung.android.service.gesture.IGestureService");
        IBinder localIBinder = paramParcel1.readStrongBinder();
        String str1 = paramParcel1.readString();
        String str2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0)
          i = j;
        registerCallback(localIBinder, str1, str2, i);
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.android.service.gesture.IGestureService");
        boolean bool = unregisterCallback(paramParcel1.readStrongBinder(), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (bool)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.android.service.gesture.IGestureService");
        resetGestureService(paramParcel1.readString());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.android.service.gesture.IGestureService");
        List localList = getProviders();
        paramParcel2.writeNoException();
        paramParcel2.writeStringList(localList);
        continue;
        paramParcel1.enforceInterface("com.samsung.android.service.gesture.IGestureService");
        Bundle localBundle = getProviderInfo(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localBundle != null)
        {
          paramParcel2.writeInt(j);
          localBundle.writeToParcel(paramParcel2, j);
          continue;
        }
        paramParcel2.writeInt(0);
      }
    }

    private static class Proxy
      implements IGestureService
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
        return "com.samsung.android.service.gesture.IGestureService";
      }

      public Bundle getProviderInfo(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.android.service.gesture.IGestureService");
          localParcel1.writeString(paramString);
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localBundle = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
            return localBundle;
          }
          Bundle localBundle = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public List<String> getProviders()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.android.service.gesture.IGestureService");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          ArrayList localArrayList = localParcel2.createStringArrayList();
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void registerCallback(IBinder paramIBinder, String paramString1, String paramString2, boolean paramBoolean)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.android.service.gesture.IGestureService");
          localParcel1.writeStrongBinder(paramIBinder);
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          if (paramBoolean)
          {
            localParcel1.writeInt(i);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          i = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void resetGestureService(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.android.service.gesture.IGestureService");
          localParcel1.writeString(paramString);
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

      public boolean unregisterCallback(IBinder paramIBinder, String paramString)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.android.service.gesture.IGestureService");
          localParcel1.writeStrongBinder(paramIBinder);
          localParcel1.writeString(paramString);
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
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.android.service.gesture.IGestureService
 * JD-Core Version:    0.6.0
 */