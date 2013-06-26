package com.sec.android.ims.message;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IMessageEnabler extends IInterface
{
  public abstract int sendSMS(String paramString1, byte[] paramArrayOfByte, String paramString2)
    throws RemoteException;

  public abstract int sendSMSResponse(int paramInt1, int paramInt2, String paramString)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IMessageEnabler
  {
    private static final String DESCRIPTOR = "com.sec.android.ims.message.IMessageEnabler";
    static final int TRANSACTION_sendSMS = 1;
    static final int TRANSACTION_sendSMSResponse = 2;

    public Stub()
    {
      attachInterface(this, "com.sec.android.ims.message.IMessageEnabler");
    }

    public static IMessageEnabler asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.ims.message.IMessageEnabler");
        if ((localIInterface != null) && ((localIInterface instanceof IMessageEnabler)))
        {
          localObject = (IMessageEnabler)localIInterface;
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
        paramParcel2.writeString("com.sec.android.ims.message.IMessageEnabler");
        continue;
        paramParcel1.enforceInterface("com.sec.android.ims.message.IMessageEnabler");
        int j = sendSMS(paramParcel1.readString(), paramParcel1.createByteArray(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(j);
        continue;
        paramParcel1.enforceInterface("com.sec.android.ims.message.IMessageEnabler");
        int i = sendSMSResponse(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i);
      }
    }

    private static class Proxy
      implements IMessageEnabler
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
        return "com.sec.android.ims.message.IMessageEnabler";
      }

      public int sendSMS(String paramString1, byte[] paramArrayOfByte, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.ims.message.IMessageEnabler");
          localParcel1.writeString(paramString1);
          localParcel1.writeByteArray(paramArrayOfByte);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
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

      public int sendSMSResponse(int paramInt1, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.ims.message.IMessageEnabler");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeString(paramString);
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
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
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.ims.message.IMessageEnabler
 * JD-Core Version:    0.6.0
 */