package com.samsung.lpp;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface ILPPManager extends IInterface
{
  public abstract int getDisabledServices()
    throws RemoteException;

  public abstract Location getLastOnDemandLocation()
    throws RemoteException;

  public abstract boolean modifyGeoFence(LPPGeoFenceParameter paramLPPGeoFenceParameter, ILPPListener paramILPPListener)
    throws RemoteException;

  public abstract void removeGeoFence(ILPPListener paramILPPListener)
    throws RemoteException;

  public abstract void removeGeoFenceWifi(ILPPListener paramILPPListener)
    throws RemoteException;

  public abstract boolean requestGeoFence(LPPGeoFenceParameter paramLPPGeoFenceParameter, ILPPListener paramILPPListener)
    throws RemoteException;

  public abstract boolean requestGeoFenceSetting(String paramString, ILPPListener paramILPPListener)
    throws RemoteException;

  public abstract boolean requestGeoFenceWifi(String paramString1, String paramString2, int paramInt, ILPPListener paramILPPListener)
    throws RemoteException;

  public abstract void requestOnDemandLocation(ILPPListener paramILPPListener)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ILPPManager
  {
    private static final String DESCRIPTOR = "com.samsung.lpp.ILPPManager";
    static final int TRANSACTION_getDisabledServices = 9;
    static final int TRANSACTION_getLastOnDemandLocation = 8;
    static final int TRANSACTION_modifyGeoFence = 6;
    static final int TRANSACTION_removeGeoFence = 4;
    static final int TRANSACTION_removeGeoFenceWifi = 5;
    static final int TRANSACTION_requestGeoFence = 1;
    static final int TRANSACTION_requestGeoFenceSetting = 3;
    static final int TRANSACTION_requestGeoFenceWifi = 2;
    static final int TRANSACTION_requestOnDemandLocation = 7;

    public Stub()
    {
      attachInterface(this, "com.samsung.lpp.ILPPManager");
    }

    public static ILPPManager asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.samsung.lpp.ILPPManager");
        if ((localIInterface != null) && ((localIInterface instanceof ILPPManager)))
        {
          localObject = (ILPPManager)localIInterface;
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
      case 7:
      case 8:
      case 9:
      }
      while (true)
      {
        return j;
        paramParcel2.writeString("com.samsung.lpp.ILPPManager");
        continue;
        paramParcel1.enforceInterface("com.samsung.lpp.ILPPManager");
        if (paramParcel1.readInt() != 0);
        for (LPPGeoFenceParameter localLPPGeoFenceParameter2 = (LPPGeoFenceParameter)LPPGeoFenceParameter.CREATOR.createFromParcel(paramParcel1); ; localLPPGeoFenceParameter2 = null)
        {
          boolean bool4 = requestGeoFence(localLPPGeoFenceParameter2, ILPPListener.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          if (bool4)
            i = j;
          paramParcel2.writeInt(i);
          break;
        }
        paramParcel1.enforceInterface("com.samsung.lpp.ILPPManager");
        boolean bool3 = requestGeoFenceWifi(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt(), ILPPListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        if (bool3)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.lpp.ILPPManager");
        boolean bool2 = requestGeoFenceSetting(paramParcel1.readString(), ILPPListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        if (bool2)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.lpp.ILPPManager");
        removeGeoFence(ILPPListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.lpp.ILPPManager");
        removeGeoFenceWifi(ILPPListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.lpp.ILPPManager");
        if (paramParcel1.readInt() != 0);
        for (LPPGeoFenceParameter localLPPGeoFenceParameter1 = (LPPGeoFenceParameter)LPPGeoFenceParameter.CREATOR.createFromParcel(paramParcel1); ; localLPPGeoFenceParameter1 = null)
        {
          boolean bool1 = modifyGeoFence(localLPPGeoFenceParameter1, ILPPListener.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          if (bool1)
            i = j;
          paramParcel2.writeInt(i);
          break;
        }
        paramParcel1.enforceInterface("com.samsung.lpp.ILPPManager");
        requestOnDemandLocation(ILPPListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.lpp.ILPPManager");
        Location localLocation = getLastOnDemandLocation();
        paramParcel2.writeNoException();
        if (localLocation != null)
        {
          paramParcel2.writeInt(j);
          localLocation.writeToParcel(paramParcel2, j);
          continue;
        }
        paramParcel2.writeInt(0);
        continue;
        paramParcel1.enforceInterface("com.samsung.lpp.ILPPManager");
        int k = getDisabledServices();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(k);
      }
    }

    private static class Proxy
      implements ILPPManager
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

      public int getDisabledServices()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.lpp.ILPPManager");
          this.mRemote.transact(9, localParcel1, localParcel2, 0);
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

      public String getInterfaceDescriptor()
      {
        return "com.samsung.lpp.ILPPManager";
      }

      public Location getLastOnDemandLocation()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.lpp.ILPPManager");
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localLocation = (Location)Location.CREATOR.createFromParcel(localParcel2);
            return localLocation;
          }
          Location localLocation = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean modifyGeoFence(LPPGeoFenceParameter paramLPPGeoFenceParameter, ILPPListener paramILPPListener)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.samsung.lpp.ILPPManager");
            if (paramLPPGeoFenceParameter == null)
              continue;
            localParcel1.writeInt(1);
            paramLPPGeoFenceParameter.writeToParcel(localParcel1, 0);
            if (paramILPPListener != null)
            {
              localIBinder = paramILPPListener.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.mRemote.transact(6, localParcel1, localParcel2, 0);
              localParcel2.readException();
              int j = localParcel2.readInt();
              if (j == 0)
                break label131;
              return i;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          IBinder localIBinder = null;
          continue;
          label131: i = 0;
        }
      }

      public void removeGeoFence(ILPPListener paramILPPListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.lpp.ILPPManager");
          if (paramILPPListener != null)
          {
            localIBinder = paramILPPListener.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(4, localParcel1, localParcel2, 0);
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

      public void removeGeoFenceWifi(ILPPListener paramILPPListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.lpp.ILPPManager");
          if (paramILPPListener != null)
          {
            localIBinder = paramILPPListener.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(5, localParcel1, localParcel2, 0);
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

      public boolean requestGeoFence(LPPGeoFenceParameter paramLPPGeoFenceParameter, ILPPListener paramILPPListener)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.samsung.lpp.ILPPManager");
            if (paramLPPGeoFenceParameter == null)
              continue;
            localParcel1.writeInt(1);
            paramLPPGeoFenceParameter.writeToParcel(localParcel1, 0);
            if (paramILPPListener != null)
            {
              localIBinder = paramILPPListener.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.mRemote.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
              int j = localParcel2.readInt();
              if (j == 0)
                break label130;
              return i;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          IBinder localIBinder = null;
          continue;
          label130: i = 0;
        }
      }

      public boolean requestGeoFenceSetting(String paramString, ILPPListener paramILPPListener)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.lpp.ILPPManager");
          localParcel1.writeString(paramString);
          if (paramILPPListener != null)
          {
            localIBinder = paramILPPListener.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(3, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int j = localParcel2.readInt();
            if (j != 0)
              i = 1;
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

      public boolean requestGeoFenceWifi(String paramString1, String paramString2, int paramInt, ILPPListener paramILPPListener)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.lpp.ILPPManager");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
          if (paramILPPListener != null)
          {
            localIBinder = paramILPPListener.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int j = localParcel2.readInt();
            if (j != 0)
              i = 1;
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

      public void requestOnDemandLocation(ILPPListener paramILPPListener)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.lpp.ILPPManager");
          if (paramILPPListener != null)
          {
            localIBinder = paramILPPListener.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(7, localParcel1, localParcel2, 0);
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
 * Qualified Name:     com.samsung.lpp.ILPPManager
 * JD-Core Version:    0.6.0
 */