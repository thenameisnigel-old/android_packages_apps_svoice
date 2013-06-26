package com.samsung.felicaremotelock;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IFelica extends IInterface
{
  public abstract int getLockState()
    throws RemoteException;

  public abstract boolean lock()
    throws RemoteException;

  public abstract boolean unlock()
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IFelica
  {
    private static final String DESCRIPTOR = "com.samsung.felicaremotelock.IFelica";
    static final int TRANSACTION_getLockState = 3;
    static final int TRANSACTION_lock = 1;
    static final int TRANSACTION_unlock = 2;

    public Stub()
    {
      attachInterface(this, "com.samsung.felicaremotelock.IFelica");
    }

    public static IFelica asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.samsung.felicaremotelock.IFelica");
        if ((localIInterface != null) && ((localIInterface instanceof IFelica)))
        {
          localObject = (IFelica)localIInterface;
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
      }
      while (true)
      {
        return j;
        paramParcel2.writeString("com.samsung.felicaremotelock.IFelica");
        continue;
        paramParcel1.enforceInterface("com.samsung.felicaremotelock.IFelica");
        boolean bool2 = lock();
        paramParcel2.writeNoException();
        if (bool2)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.felicaremotelock.IFelica");
        boolean bool1 = unlock();
        paramParcel2.writeNoException();
        if (bool1)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.felicaremotelock.IFelica");
        int k = getLockState();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(k);
      }
    }

    private static class Proxy
      implements IFelica
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
        return "com.samsung.felicaremotelock.IFelica";
      }

      public int getLockState()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.felicaremotelock.IFelica");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
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

      public boolean lock()
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.felicaremotelock.IFelica");
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

      public boolean unlock()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.felicaremotelock.IFelica");
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
 * Qualified Name:     com.samsung.felicaremotelock.IFelica
 * JD-Core Version:    0.6.0
 */