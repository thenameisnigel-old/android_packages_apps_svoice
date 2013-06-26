package com.android.email.backgroundsender;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IEmailBackgroundSender extends IInterface
{
  public abstract int addAttchment(Intent paramIntent, int paramInt)
    throws RemoteException;

  public abstract boolean hasAccount()
    throws RemoteException;

  public abstract void sendMessage(int paramInt, Intent paramIntent)
    throws RemoteException;

  public abstract void startListening()
    throws RemoteException;

  public abstract void stopListening()
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IEmailBackgroundSender
  {
    private static final String DESCRIPTOR = "com.android.email.backgroundsender.IEmailBackgroundSender";
    static final int TRANSACTION_addAttchment = 3;
    static final int TRANSACTION_hasAccount = 4;
    static final int TRANSACTION_sendMessage = 5;
    static final int TRANSACTION_startListening = 1;
    static final int TRANSACTION_stopListening = 2;

    public Stub()
    {
      attachInterface(this, "com.android.email.backgroundsender.IEmailBackgroundSender");
    }

    public static IEmailBackgroundSender asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.email.backgroundsender.IEmailBackgroundSender");
        if ((localIInterface != null) && ((localIInterface instanceof IEmailBackgroundSender)))
        {
          localObject = (IEmailBackgroundSender)localIInterface;
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
      int i = 1;
      switch (paramInt1)
      {
      default:
        i = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      case 2:
        while (true)
        {
          return i;
          paramParcel2.writeString("com.android.email.backgroundsender.IEmailBackgroundSender");
          continue;
          paramParcel1.enforceInterface("com.android.email.backgroundsender.IEmailBackgroundSender");
          startListening();
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.android.email.backgroundsender.IEmailBackgroundSender");
          stopListening();
          paramParcel2.writeNoException();
        }
      case 3:
        paramParcel1.enforceInterface("com.android.email.backgroundsender.IEmailBackgroundSender");
        if (paramParcel1.readInt() != 0);
        for (Intent localIntent2 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1); ; localIntent2 = null)
        {
          int n = addAttchment(localIntent2, paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(n);
          break;
        }
      case 4:
        paramParcel1.enforceInterface("com.android.email.backgroundsender.IEmailBackgroundSender");
        boolean bool = hasAccount();
        paramParcel2.writeNoException();
        if (bool);
        int m;
        for (int k = i; ; m = 0)
        {
          paramParcel2.writeInt(k);
          break;
        }
      case 5:
      }
      paramParcel1.enforceInterface("com.android.email.backgroundsender.IEmailBackgroundSender");
      int j = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0);
      for (Intent localIntent1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1); ; localIntent1 = null)
      {
        sendMessage(j, localIntent1);
        paramParcel2.writeNoException();
        break;
      }
    }

    private static class Proxy
      implements IEmailBackgroundSender
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public int addAttchment(Intent paramIntent, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.email.backgroundsender.IEmailBackgroundSender");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeInt(paramInt);
            this.mRemote.transact(3, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
            localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public String getInterfaceDescriptor()
      {
        return "com.android.email.backgroundsender.IEmailBackgroundSender";
      }

      public boolean hasAccount()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.email.backgroundsender.IEmailBackgroundSender");
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

      public void sendMessage(int paramInt, Intent paramIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.email.backgroundsender.IEmailBackgroundSender");
          localParcel1.writeInt(paramInt);
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(5, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void startListening()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.email.backgroundsender.IEmailBackgroundSender");
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

      public void stopListening()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.email.backgroundsender.IEmailBackgroundSender");
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
 * Qualified Name:     com.android.email.backgroundsender.IEmailBackgroundSender
 * JD-Core Version:    0.6.0
 */