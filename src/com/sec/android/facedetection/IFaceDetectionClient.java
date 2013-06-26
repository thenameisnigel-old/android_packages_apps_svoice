package com.sec.android.facedetection;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IFaceDetectionClient extends IInterface
{
  public static abstract class Stub extends Binder
    implements IFaceDetectionClient
  {
    private static final String DESCRIPTOR = "com.sec.android.facedetection.IFaceDetectionClient";

    public Stub()
    {
      attachInterface(this, "com.sec.android.facedetection.IFaceDetectionClient");
    }

    public static IFaceDetectionClient asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.facedetection.IFaceDetectionClient");
        if ((localIInterface != null) && ((localIInterface instanceof IFaceDetectionClient)))
        {
          localObject = (IFaceDetectionClient)localIInterface;
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
      switch (paramInt1)
      {
      default:
      case 1598968902:
      }
      for (boolean bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); ; bool = true)
      {
        return bool;
        paramParcel2.writeString("com.sec.android.facedetection.IFaceDetectionClient");
      }
    }

    private static class Proxy
      implements IFaceDetectionClient
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
        return "com.sec.android.facedetection.IFaceDetectionClient";
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.facedetection.IFaceDetectionClient
 * JD-Core Version:    0.6.0
 */