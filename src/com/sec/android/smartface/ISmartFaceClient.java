package com.sec.android.smartface;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface ISmartFaceClient extends IInterface
{
  public abstract void onInfo(FaceInfo paramFaceInfo, int paramInt)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ISmartFaceClient
  {
    private static final String DESCRIPTOR = "com.sec.android.smartface.ISmartFaceClient";
    static final int TRANSACTION_onInfo = 1;

    public Stub()
    {
      attachInterface(this, "com.sec.android.smartface.ISmartFaceClient");
    }

    public static ISmartFaceClient asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.smartface.ISmartFaceClient");
        if ((localIInterface != null) && ((localIInterface instanceof ISmartFaceClient)))
        {
          localObject = (ISmartFaceClient)localIInterface;
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
        while (true)
        {
          return bool;
          paramParcel2.writeString("com.sec.android.smartface.ISmartFaceClient");
        }
      case 1:
      }
      paramParcel1.enforceInterface("com.sec.android.smartface.ISmartFaceClient");
      if (paramParcel1.readInt() != 0);
      for (FaceInfo localFaceInfo = (FaceInfo)FaceInfo.CREATOR.createFromParcel(paramParcel1); ; localFaceInfo = null)
      {
        onInfo(localFaceInfo, paramParcel1.readInt());
        break;
      }
    }

    private static class Proxy
      implements ISmartFaceClient
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
        return "com.sec.android.smartface.ISmartFaceClient";
      }

      public void onInfo(FaceInfo paramFaceInfo, int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.smartface.ISmartFaceClient");
          if (paramFaceInfo != null)
          {
            localParcel.writeInt(1);
            paramFaceInfo.writeToParcel(localParcel, 0);
          }
          while (true)
          {
            localParcel.writeInt(paramInt);
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
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.smartface.ISmartFaceClient
 * JD-Core Version:    0.6.0
 */