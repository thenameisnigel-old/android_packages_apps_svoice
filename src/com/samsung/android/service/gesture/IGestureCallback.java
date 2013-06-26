package com.samsung.android.service.gesture;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IGestureCallback extends IInterface
{
  public abstract void gestureCallback(GestureEvent paramGestureEvent)
    throws RemoteException;

  public abstract String getListenerInfo()
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IGestureCallback
  {
    private static final String DESCRIPTOR = "com.samsung.android.service.gesture.IGestureCallback";
    static final int TRANSACTION_gestureCallback = 1;
    static final int TRANSACTION_getListenerInfo = 2;

    public Stub()
    {
      attachInterface(this, "com.samsung.android.service.gesture.IGestureCallback");
    }

    public static IGestureCallback asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.samsung.android.service.gesture.IGestureCallback");
        if ((localIInterface != null) && ((localIInterface instanceof IGestureCallback)))
        {
          localObject = (IGestureCallback)localIInterface;
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
        paramParcel2.writeString("com.samsung.android.service.gesture.IGestureCallback");
        continue;
        paramParcel1.enforceInterface("com.samsung.android.service.gesture.IGestureCallback");
        if (paramParcel1.readInt() != 0);
        for (GestureEvent localGestureEvent = (GestureEvent)GestureEvent.CREATOR.createFromParcel(paramParcel1); ; localGestureEvent = null)
        {
          gestureCallback(localGestureEvent);
          paramParcel2.writeNoException();
          break;
        }
        paramParcel1.enforceInterface("com.samsung.android.service.gesture.IGestureCallback");
        String str = getListenerInfo();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str);
      }
    }

    private static class Proxy
      implements IGestureCallback
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

      public void gestureCallback(GestureEvent paramGestureEvent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.android.service.gesture.IGestureCallback");
          if (paramGestureEvent != null)
          {
            localParcel1.writeInt(1);
            paramGestureEvent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
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

      public String getInterfaceDescriptor()
      {
        return "com.samsung.android.service.gesture.IGestureCallback";
      }

      public String getListenerInfo()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.android.service.gesture.IGestureCallback");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
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
 * Qualified Name:     com.samsung.android.service.gesture.IGestureCallback
 * JD-Core Version:    0.6.0
 */