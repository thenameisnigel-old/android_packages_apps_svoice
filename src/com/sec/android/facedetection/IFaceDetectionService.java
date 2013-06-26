package com.sec.android.facedetection;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IFaceDetectionService extends IInterface
{
  public abstract void disable(IFaceDetectionClient paramIFaceDetectionClient)
    throws RemoteException;

  public abstract void enable(IFaceDetectionClient paramIFaceDetectionClient)
    throws RemoteException;

  public abstract SecFace[] getFaceInfo()
    throws RemoteException;

  public abstract SecFace[] getFaceInfoHint(int paramInt1, int paramInt2)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IFaceDetectionService
  {
    private static final String DESCRIPTOR = "com.sec.android.facedetection.IFaceDetectionService";
    static final int TRANSACTION_disable = 2;
    static final int TRANSACTION_enable = 1;
    static final int TRANSACTION_getFaceInfo = 3;
    static final int TRANSACTION_getFaceInfoHint = 4;

    public Stub()
    {
      attachInterface(this, "com.sec.android.facedetection.IFaceDetectionService");
    }

    public static IFaceDetectionService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.facedetection.IFaceDetectionService");
        if ((localIInterface != null) && ((localIInterface instanceof IFaceDetectionService)))
        {
          localObject = (IFaceDetectionService)localIInterface;
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
      case 3:
      case 4:
      }
      while (true)
      {
        return i;
        paramParcel2.writeString("com.sec.android.facedetection.IFaceDetectionService");
        continue;
        paramParcel1.enforceInterface("com.sec.android.facedetection.IFaceDetectionService");
        enable(IFaceDetectionClient.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.facedetection.IFaceDetectionService");
        disable(IFaceDetectionClient.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.facedetection.IFaceDetectionService");
        SecFace[] arrayOfSecFace2 = getFaceInfo();
        paramParcel2.writeNoException();
        paramParcel2.writeTypedArray(arrayOfSecFace2, i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.facedetection.IFaceDetectionService");
        SecFace[] arrayOfSecFace1 = getFaceInfoHint(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedArray(arrayOfSecFace1, i);
      }
    }

    private static class Proxy
      implements IFaceDetectionService
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

      public void disable(IFaceDetectionClient paramIFaceDetectionClient)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.facedetection.IFaceDetectionService");
          if (paramIFaceDetectionClient != null)
          {
            localIBinder = paramIFaceDetectionClient.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void enable(IFaceDetectionClient paramIFaceDetectionClient)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.facedetection.IFaceDetectionService");
          if (paramIFaceDetectionClient != null)
          {
            localIBinder = paramIFaceDetectionClient.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public SecFace[] getFaceInfo()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.facedetection.IFaceDetectionService");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          SecFace[] arrayOfSecFace = (SecFace[])localParcel2.createTypedArray(SecFace.CREATOR);
          return arrayOfSecFace;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public SecFace[] getFaceInfoHint(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.facedetection.IFaceDetectionService");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          SecFace[] arrayOfSecFace = (SecFace[])localParcel2.createTypedArray(SecFace.CREATOR);
          return arrayOfSecFace;
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
        return "com.sec.android.facedetection.IFaceDetectionService";
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.facedetection.IFaceDetectionService
 * JD-Core Version:    0.6.0
 */