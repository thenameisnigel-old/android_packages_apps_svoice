package com.samsung.wfd;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IWfdSourceService extends IInterface
{
  public abstract boolean WFDGetSubtitleStatus()
    throws RemoteException;

  public abstract boolean WFDGetSuspendStatus()
    throws RemoteException;

  public abstract boolean WFDPostSubtitle(String paramString, int paramInt)
    throws RemoteException;

  public abstract boolean WFDPostSuspend(String paramString)
    throws RemoteException;

  public abstract boolean WFDSetSubtitleStatus(boolean paramBoolean)
    throws RemoteException;

  public abstract boolean WFDSetSuspendStatus(boolean paramBoolean)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IWfdSourceService
  {
    private static final String DESCRIPTOR = "com.samsung.wfd.IWfdSourceService";
    static final int TRANSACTION_WFDGetSubtitleStatus = 1;
    static final int TRANSACTION_WFDGetSuspendStatus = 4;
    static final int TRANSACTION_WFDPostSubtitle = 2;
    static final int TRANSACTION_WFDPostSuspend = 6;
    static final int TRANSACTION_WFDSetSubtitleStatus = 3;
    static final int TRANSACTION_WFDSetSuspendStatus = 5;

    public Stub()
    {
      attachInterface(this, "com.samsung.wfd.IWfdSourceService");
    }

    public static IWfdSourceService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.samsung.wfd.IWfdSourceService");
        if ((localIInterface != null) && ((localIInterface instanceof IWfdSourceService)))
        {
          localObject = (IWfdSourceService)localIInterface;
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
      }
      while (true)
      {
        return j;
        paramParcel2.writeString("com.samsung.wfd.IWfdSourceService");
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdSourceService");
        boolean bool8 = WFDGetSubtitleStatus();
        paramParcel2.writeNoException();
        if (bool8)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdSourceService");
        boolean bool7 = WFDPostSubtitle(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (bool7)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdSourceService");
        if (paramParcel1.readInt() != 0);
        for (boolean bool5 = j; ; bool5 = false)
        {
          boolean bool6 = WFDSetSubtitleStatus(bool5);
          paramParcel2.writeNoException();
          if (bool6)
            i = j;
          paramParcel2.writeInt(i);
          break;
        }
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdSourceService");
        boolean bool4 = WFDGetSuspendStatus();
        paramParcel2.writeNoException();
        if (bool4)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdSourceService");
        if (paramParcel1.readInt() != 0);
        for (boolean bool2 = j; ; bool2 = false)
        {
          boolean bool3 = WFDSetSuspendStatus(bool2);
          paramParcel2.writeNoException();
          if (bool3)
            i = j;
          paramParcel2.writeInt(i);
          break;
        }
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdSourceService");
        boolean bool1 = WFDPostSuspend(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (bool1)
          i = j;
        paramParcel2.writeInt(i);
      }
    }

    private static class Proxy
      implements IWfdSourceService
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public boolean WFDGetSubtitleStatus()
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdSourceService");
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          if (j != 0)
            return i;
          i = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean WFDGetSuspendStatus()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdSourceService");
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

      public boolean WFDPostSubtitle(String paramString, int paramInt)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdSourceService");
          localParcel1.writeString(paramString);
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

      public boolean WFDPostSuspend(String paramString)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdSourceService");
          localParcel1.writeString(paramString);
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

      public boolean WFDSetSubtitleStatus(boolean paramBoolean)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdSourceService");
          if (paramBoolean);
          for (int j = i; ; j = 0)
          {
            localParcel1.writeInt(j);
            this.mRemote.transact(3, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int k = localParcel2.readInt();
            if (k == 0)
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

      public boolean WFDSetSuspendStatus(boolean paramBoolean)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdSourceService");
          if (paramBoolean);
          for (int j = i; ; j = 0)
          {
            localParcel1.writeInt(j);
            this.mRemote.transact(5, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int k = localParcel2.readInt();
            if (k == 0)
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

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public String getInterfaceDescriptor()
      {
        return "com.samsung.wfd.IWfdSourceService";
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.IWfdSourceService
 * JD-Core Version:    0.6.0
 */