package com.sec.android.internal.ims;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IIMSBTCallStateListener extends IInterface
{
  public abstract void handleImsEvent(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte)
    throws RemoteException;

  public abstract void setMode(int paramInt1, int paramInt2)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IIMSBTCallStateListener
  {
    private static final String DESCRIPTOR = "com.sec.android.internal.ims.IIMSBTCallStateListener";
    static final int TRANSACTION_handleImsEvent = 1;
    static final int TRANSACTION_setMode = 2;

    public Stub()
    {
      attachInterface(this, "com.sec.android.internal.ims.IIMSBTCallStateListener");
    }

    public static IIMSBTCallStateListener asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.internal.ims.IIMSBTCallStateListener");
        if ((localIInterface != null) && ((localIInterface instanceof IIMSBTCallStateListener)))
        {
          localObject = (IIMSBTCallStateListener)localIInterface;
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
        paramParcel2.writeString("com.sec.android.internal.ims.IIMSBTCallStateListener");
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSBTCallStateListener");
        handleImsEvent(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSBTCallStateListener");
        setMode(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
      }
    }

    private static class Proxy
      implements IIMSBTCallStateListener
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
        return "com.sec.android.internal.ims.IIMSBTCallStateListener";
      }

      public void handleImsEvent(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSBTCallStateListener");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
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

      public void setMode(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSBTCallStateListener");
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
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.IIMSBTCallStateListener
 * JD-Core Version:    0.6.0
 */