package com.sec.android.internal.ims;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IIMSEventListener extends IInterface
{
  public abstract void handleEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte, IIMSParams paramIIMSParams)
    throws RemoteException;

  public abstract void notifyEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, String[] paramArrayOfString)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IIMSEventListener
  {
    private static final String DESCRIPTOR = "com.sec.android.internal.ims.IIMSEventListener";
    static final int TRANSACTION_handleEvent = 1;
    static final int TRANSACTION_notifyEvent = 2;

    public Stub()
    {
      attachInterface(this, "com.sec.android.internal.ims.IIMSEventListener");
    }

    public static IIMSEventListener asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.internal.ims.IIMSEventListener");
        if ((localIInterface != null) && ((localIInterface instanceof IIMSEventListener)))
        {
          localObject = (IIMSEventListener)localIInterface;
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
      boolean bool;
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
        paramParcel2.writeString("com.sec.android.internal.ims.IIMSEventListener");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSEventListener");
        int i = paramParcel1.readInt();
        int j = paramParcel1.readInt();
        int k = paramParcel1.readInt();
        int m = paramParcel1.readInt();
        byte[] arrayOfByte = paramParcel1.createByteArray();
        if (paramParcel1.readInt() != 0);
        for (IIMSParams localIIMSParams = (IIMSParams)IIMSParams.CREATOR.createFromParcel(paramParcel1); ; localIIMSParams = null)
        {
          handleEvent(i, j, k, m, arrayOfByte, localIIMSParams);
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSEventListener");
        notifyEvent(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createIntArray(), paramParcel1.createStringArray());
        bool = true;
      }
    }

    private static class Proxy
      implements IIMSEventListener
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
        return "com.sec.android.internal.ims.IIMSEventListener";
      }

      public void handleEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte, IIMSParams paramIIMSParams)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSEventListener");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          localParcel.writeInt(paramInt3);
          localParcel.writeInt(paramInt4);
          localParcel.writeByteArray(paramArrayOfByte);
          if (paramIIMSParams != null)
          {
            localParcel.writeInt(1);
            paramIIMSParams.writeToParcel(localParcel, 0);
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

      public void notifyEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSEventListener");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          localParcel.writeInt(paramInt3);
          localParcel.writeInt(paramInt4);
          localParcel.writeIntArray(paramArrayOfInt);
          localParcel.writeStringArray(paramArrayOfString);
          this.mRemote.transact(2, localParcel, null, 1);
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
 * Qualified Name:     com.sec.android.internal.ims.IIMSEventListener
 * JD-Core Version:    0.6.0
 */