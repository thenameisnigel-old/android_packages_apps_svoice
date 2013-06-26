package com.samsung.lpp;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface ILPPListener extends IInterface
{
  public abstract void onGeoFenceAvailabilityCallback(int paramInt, LPPGeoFenceParameter paramLPPGeoFenceParameter)
    throws RemoteException;

  public abstract void onGeoFenceDetected(int paramInt, LPPGeoFenceParameter paramLPPGeoFenceParameter)
    throws RemoteException;

  public abstract void onLocationReported(Location paramLocation)
    throws RemoteException;

  public abstract void onStatusDisabled(int paramInt)
    throws RemoteException;

  public abstract void onStatusEnabled(int paramInt)
    throws RemoteException;

  public abstract void onWifiGeoFenceRequestResult(int paramInt)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ILPPListener
  {
    private static final String DESCRIPTOR = "com.samsung.lpp.ILPPListener";
    static final int TRANSACTION_onGeoFenceAvailabilityCallback = 2;
    static final int TRANSACTION_onGeoFenceDetected = 1;
    static final int TRANSACTION_onLocationReported = 6;
    static final int TRANSACTION_onStatusDisabled = 5;
    static final int TRANSACTION_onStatusEnabled = 4;
    static final int TRANSACTION_onWifiGeoFenceRequestResult = 3;

    public Stub()
    {
      attachInterface(this, "com.samsung.lpp.ILPPListener");
    }

    public static ILPPListener asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.samsung.lpp.ILPPListener");
        if ((localIInterface != null) && ((localIInterface instanceof ILPPListener)))
        {
          localObject = (ILPPListener)localIInterface;
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
      case 5:
        while (true)
        {
          return bool;
          paramParcel2.writeString("com.samsung.lpp.ILPPListener");
          continue;
          paramParcel1.enforceInterface("com.samsung.lpp.ILPPListener");
          int j = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0);
          for (LPPGeoFenceParameter localLPPGeoFenceParameter2 = (LPPGeoFenceParameter)LPPGeoFenceParameter.CREATOR.createFromParcel(paramParcel1); ; localLPPGeoFenceParameter2 = null)
          {
            onGeoFenceDetected(j, localLPPGeoFenceParameter2);
            break;
          }
          paramParcel1.enforceInterface("com.samsung.lpp.ILPPListener");
          int i = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0);
          for (LPPGeoFenceParameter localLPPGeoFenceParameter1 = (LPPGeoFenceParameter)LPPGeoFenceParameter.CREATOR.createFromParcel(paramParcel1); ; localLPPGeoFenceParameter1 = null)
          {
            onGeoFenceAvailabilityCallback(i, localLPPGeoFenceParameter1);
            break;
          }
          paramParcel1.enforceInterface("com.samsung.lpp.ILPPListener");
          onWifiGeoFenceRequestResult(paramParcel1.readInt());
          continue;
          paramParcel1.enforceInterface("com.samsung.lpp.ILPPListener");
          onStatusEnabled(paramParcel1.readInt());
          continue;
          paramParcel1.enforceInterface("com.samsung.lpp.ILPPListener");
          onStatusDisabled(paramParcel1.readInt());
        }
      case 6:
      }
      paramParcel1.enforceInterface("com.samsung.lpp.ILPPListener");
      if (paramParcel1.readInt() != 0);
      for (Location localLocation = (Location)Location.CREATOR.createFromParcel(paramParcel1); ; localLocation = null)
      {
        onLocationReported(localLocation);
        break;
      }
    }

    private static class Proxy
      implements ILPPListener
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
        return "com.samsung.lpp.ILPPListener";
      }

      public void onGeoFenceAvailabilityCallback(int paramInt, LPPGeoFenceParameter paramLPPGeoFenceParameter)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.lpp.ILPPListener");
          localParcel.writeInt(paramInt);
          if (paramLPPGeoFenceParameter != null)
          {
            localParcel.writeInt(1);
            paramLPPGeoFenceParameter.writeToParcel(localParcel, 0);
          }
          while (true)
          {
            this.mRemote.transact(2, localParcel, null, 1);
            return;
            localParcel.writeInt(0);
          }
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onGeoFenceDetected(int paramInt, LPPGeoFenceParameter paramLPPGeoFenceParameter)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.lpp.ILPPListener");
          localParcel.writeInt(paramInt);
          if (paramLPPGeoFenceParameter != null)
          {
            localParcel.writeInt(1);
            paramLPPGeoFenceParameter.writeToParcel(localParcel, 0);
          }
          while (true)
          {
            this.mRemote.transact(1, localParcel, null, 1);
            return;
            localParcel.writeInt(0);
          }
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onLocationReported(Location paramLocation)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.lpp.ILPPListener");
          if (paramLocation != null)
          {
            localParcel.writeInt(1);
            paramLocation.writeToParcel(localParcel, 0);
          }
          while (true)
          {
            this.mRemote.transact(6, localParcel, null, 1);
            return;
            localParcel.writeInt(0);
          }
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onStatusDisabled(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.lpp.ILPPListener");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(5, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onStatusEnabled(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.lpp.ILPPListener");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(4, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onWifiGeoFenceRequestResult(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.samsung.lpp.ILPPListener");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(3, localParcel, null, 1);
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
 * Qualified Name:     com.samsung.lpp.ILPPListener
 * JD-Core Version:    0.6.0
 */