package com.sec.android.internal.ims;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IIMSTelephony extends IInterface
{
  public abstract void answerRingingCall()
    throws RemoteException;

  public abstract void cancelMissedCallsNotification()
    throws RemoteException;

  public abstract boolean endCall()
    throws RemoteException;

  public abstract int getCallState()
    throws RemoteException;

  public abstract boolean isIMSEnabledOnWifi()
    throws RemoteException;

  public abstract boolean isIdle()
    throws RemoteException;

  public abstract boolean isOffhook()
    throws RemoteException;

  public abstract boolean isRinging()
    throws RemoteException;

  public abstract boolean isVideoCall()
    throws RemoteException;

  public abstract boolean showCallScreen()
    throws RemoteException;

  public abstract void silenceRinger()
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IIMSTelephony
  {
    private static final String DESCRIPTOR = "com.sec.android.internal.ims.IIMSTelephony";
    static final int TRANSACTION_answerRingingCall = 4;
    static final int TRANSACTION_cancelMissedCallsNotification = 2;
    static final int TRANSACTION_endCall = 3;
    static final int TRANSACTION_getCallState = 10;
    static final int TRANSACTION_isIMSEnabledOnWifi = 11;
    static final int TRANSACTION_isIdle = 8;
    static final int TRANSACTION_isOffhook = 6;
    static final int TRANSACTION_isRinging = 7;
    static final int TRANSACTION_isVideoCall = 9;
    static final int TRANSACTION_showCallScreen = 1;
    static final int TRANSACTION_silenceRinger = 5;

    public Stub()
    {
      attachInterface(this, "com.sec.android.internal.ims.IIMSTelephony");
    }

    public static IIMSTelephony asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.internal.ims.IIMSTelephony");
        if ((localIInterface != null) && ((localIInterface instanceof IIMSTelephony)))
        {
          localObject = (IIMSTelephony)localIInterface;
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
      case 10:
      case 11:
      }
      while (true)
      {
        return j;
        paramParcel2.writeString("com.sec.android.internal.ims.IIMSTelephony");
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        boolean bool7 = showCallScreen();
        paramParcel2.writeNoException();
        if (bool7)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        cancelMissedCallsNotification();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        boolean bool6 = endCall();
        paramParcel2.writeNoException();
        if (bool6)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        answerRingingCall();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        silenceRinger();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        boolean bool5 = isOffhook();
        paramParcel2.writeNoException();
        if (bool5)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        boolean bool4 = isRinging();
        paramParcel2.writeNoException();
        if (bool4)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        boolean bool3 = isIdle();
        paramParcel2.writeNoException();
        if (bool3)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        boolean bool2 = isVideoCall();
        paramParcel2.writeNoException();
        if (bool2)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        int k = getCallState();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(k);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSTelephony");
        boolean bool1 = isIMSEnabledOnWifi();
        paramParcel2.writeNoException();
        if (bool1)
          i = j;
        paramParcel2.writeInt(i);
      }
    }

    private static class Proxy
      implements IIMSTelephony
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public void answerRingingCall()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
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

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public void cancelMissedCallsNotification()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
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

      public boolean endCall()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
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

      public int getCallState()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
          this.mRemote.transact(10, localParcel1, localParcel2, 0);
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
        return "com.sec.android.internal.ims.IIMSTelephony";
      }

      public boolean isIMSEnabledOnWifi()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
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

      public boolean isIdle()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
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

      public boolean isOffhook()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
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

      public boolean isRinging()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
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

      public boolean isVideoCall()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
          this.mRemote.transact(9, localParcel1, localParcel2, 0);
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

      public boolean showCallScreen()
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
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

      public void silenceRinger()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSTelephony");
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
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
 * Qualified Name:     com.sec.android.internal.ims.IIMSTelephony
 * JD-Core Version:    0.6.0
 */