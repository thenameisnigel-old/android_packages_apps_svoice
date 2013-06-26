package com.sec.android.internal.ims;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.view.Surface;
import com.sec.android.ims.IImsServiceCallback;
import com.sec.android.ims.IImsServiceCallback.Stub;
import com.sec.android.ims.message.IMessageEnabler;
import com.sec.android.ims.message.IMessageEnabler.Stub;

public abstract interface IIMSService extends IInterface
{
  public abstract void captureSurfaceImage(boolean paramBoolean, int paramInt)
    throws RemoteException;

  public abstract void contactSvcCallFunction(int paramInt1, int paramInt2, int paramInt3, String paramString)
    throws RemoteException;

  public abstract void deinitSurface(boolean paramBoolean)
    throws RemoteException;

  public abstract String getCurrentLatchedNetwork()
    throws RemoteException;

  public abstract boolean getImsRegStatus()
    throws RemoteException;

  public abstract IMessageEnabler getMessageEnabler()
    throws RemoteException;

  public abstract int isFrontCamInUse()
    throws RemoteException;

  public abstract boolean isIMSEnabledOnWifi()
    throws RemoteException;

  public abstract boolean isImsForbidden()
    throws RemoteException;

  public abstract boolean isOnEHRPD()
    throws RemoteException;

  public abstract boolean isOnLTE()
    throws RemoteException;

  public abstract boolean isRegistered()
    throws RemoteException;

  public abstract int mmSS_Svc_Api(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2)
    throws RemoteException;

  public abstract int mmTelSvcCallFunc(int paramInt1, int paramInt2, int paramInt3, String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract void mmTelSvcCallFuncAsync(int paramInt1, int paramInt2, int paramInt3, String paramString)
    throws RemoteException;

  public abstract void register(String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract int registerApp(int paramInt, String paramString, IImsServiceCallback paramIImsServiceCallback)
    throws RemoteException;

  public abstract void registerListener(IIMSEventListener paramIIMSEventListener, int paramInt)
    throws RemoteException;

  public abstract int registerSSApp(int paramInt, String paramString, IImsServiceCallback paramIImsServiceCallback)
    throws RemoteException;

  public abstract void registerWithISIMResponse(String paramString, byte[] paramArrayOfByte)
    throws RemoteException;

  public abstract void resetCameraID()
    throws RemoteException;

  public abstract void sendLiveVideo()
    throws RemoteException;

  public abstract void sendStillImage(String paramString)
    throws RemoteException;

  public abstract void setAudioMode(int paramInt)
    throws RemoteException;

  public abstract void setAudioTuningParameters(int paramInt)
    throws RemoteException;

  public abstract void setOrientation(int paramInt)
    throws RemoteException;

  public abstract void startAudio()
    throws RemoteException;

  public abstract void startCamera(Surface paramSurface, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, String paramString)
    throws RemoteException;

  public abstract void startVideo()
    throws RemoteException;

  public abstract void startVideoRenderer(Surface paramSurface, int paramInt1, int paramInt2, String paramString)
    throws RemoteException;

  public abstract void stopCamera()
    throws RemoteException;

  public abstract void stopVideo()
    throws RemoteException;

  public abstract void swapVideoSurface()
    throws RemoteException;

  public abstract void switchCamera()
    throws RemoteException;

  public abstract void unRegisterApp(int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract void unRegisterSSApp(int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract void unregister()
    throws RemoteException;

  public abstract void unregisterListener(IIMSEventListener paramIIMSEventListener)
    throws RemoteException;

  public abstract void unregisterWithISIMResponse(String paramString, byte[] paramArrayOfByte)
    throws RemoteException;

  public abstract void voiceRecord(int paramInt, String paramString)
    throws RemoteException;

  public abstract void writeErrorData(String paramString1, String paramString2)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IIMSService
  {
    private static final String DESCRIPTOR = "com.sec.android.internal.ims.IIMSService";
    static final int TRANSACTION_captureSurfaceImage = 17;
    static final int TRANSACTION_contactSvcCallFunction = 36;
    static final int TRANSACTION_deinitSurface = 18;
    static final int TRANSACTION_getCurrentLatchedNetwork = 38;
    static final int TRANSACTION_getImsRegStatus = 5;
    static final int TRANSACTION_getMessageEnabler = 4;
    static final int TRANSACTION_isFrontCamInUse = 9;
    static final int TRANSACTION_isIMSEnabledOnWifi = 39;
    static final int TRANSACTION_isImsForbidden = 12;
    static final int TRANSACTION_isOnEHRPD = 11;
    static final int TRANSACTION_isOnLTE = 10;
    static final int TRANSACTION_isRegistered = 1;
    static final int TRANSACTION_mmSS_Svc_Api = 6;
    static final int TRANSACTION_mmTelSvcCallFunc = 35;
    static final int TRANSACTION_mmTelSvcCallFuncAsync = 34;
    static final int TRANSACTION_register = 28;
    static final int TRANSACTION_registerApp = 2;
    static final int TRANSACTION_registerListener = 32;
    static final int TRANSACTION_registerSSApp = 7;
    static final int TRANSACTION_registerWithISIMResponse = 30;
    static final int TRANSACTION_resetCameraID = 14;
    static final int TRANSACTION_sendLiveVideo = 16;
    static final int TRANSACTION_sendStillImage = 15;
    static final int TRANSACTION_setAudioMode = 41;
    static final int TRANSACTION_setAudioTuningParameters = 37;
    static final int TRANSACTION_setOrientation = 24;
    static final int TRANSACTION_startAudio = 21;
    static final int TRANSACTION_startCamera = 26;
    static final int TRANSACTION_startVideo = 22;
    static final int TRANSACTION_startVideoRenderer = 20;
    static final int TRANSACTION_stopCamera = 27;
    static final int TRANSACTION_stopVideo = 23;
    static final int TRANSACTION_swapVideoSurface = 19;
    static final int TRANSACTION_switchCamera = 13;
    static final int TRANSACTION_unRegisterApp = 3;
    static final int TRANSACTION_unRegisterSSApp = 8;
    static final int TRANSACTION_unregister = 29;
    static final int TRANSACTION_unregisterListener = 33;
    static final int TRANSACTION_unregisterWithISIMResponse = 31;
    static final int TRANSACTION_voiceRecord = 25;
    static final int TRANSACTION_writeErrorData = 40;

    public Stub()
    {
      attachInterface(this, "com.sec.android.internal.ims.IIMSService");
    }

    public static IIMSService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.internal.ims.IIMSService");
        if ((localIInterface != null) && ((localIInterface instanceof IIMSService)))
        {
          localObject = (IIMSService)localIInterface;
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
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      }
      while (true)
      {
        return j;
        paramParcel2.writeString("com.sec.android.internal.ims.IIMSService");
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        boolean bool12 = isRegistered();
        paramParcel2.writeNoException();
        if (bool12)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        int i8 = registerApp(paramParcel1.readInt(), paramParcel1.readString(), IImsServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i8);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        unRegisterApp(paramParcel1.readInt(), paramParcel1.readInt());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        IMessageEnabler localIMessageEnabler = getMessageEnabler();
        paramParcel2.writeNoException();
        if (localIMessageEnabler != null);
        for (IBinder localIBinder = localIMessageEnabler.asBinder(); ; localIBinder = null)
        {
          paramParcel2.writeStrongBinder(localIBinder);
          break;
        }
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        boolean bool11 = getImsRegStatus();
        paramParcel2.writeNoException();
        if (bool11)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        int i7 = mmSS_Svc_Api(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i7);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        int i6 = registerSSApp(paramParcel1.readInt(), paramParcel1.readString(), IImsServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i6);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        unRegisterSSApp(paramParcel1.readInt(), paramParcel1.readInt());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        int i5 = isFrontCamInUse();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i5);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        boolean bool10 = isOnLTE();
        paramParcel2.writeNoException();
        if (bool10)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        boolean bool9 = isOnEHRPD();
        paramParcel2.writeNoException();
        if (bool9)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        boolean bool8 = isImsForbidden();
        paramParcel2.writeNoException();
        if (bool8)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        switchCamera();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        resetCameraID();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        sendStillImage(paramParcel1.readString());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        sendLiveVideo();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        if (paramParcel1.readInt() != 0);
        for (boolean bool7 = j; ; bool7 = false)
        {
          captureSurfaceImage(bool7, paramParcel1.readInt());
          break;
        }
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        if (paramParcel1.readInt() != 0);
        for (boolean bool6 = j; ; bool6 = false)
        {
          deinitSurface(bool6);
          paramParcel2.writeNoException();
          break;
        }
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        swapVideoSurface();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        if (paramParcel1.readInt() != 0);
        for (Surface localSurface2 = (Surface)Surface.CREATOR.createFromParcel(paramParcel1); ; localSurface2 = null)
        {
          startVideoRenderer(localSurface2, paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
          break;
        }
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        startAudio();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        startVideo();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        stopVideo();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        setOrientation(paramParcel1.readInt());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        voiceRecord(paramParcel1.readInt(), paramParcel1.readString());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        Surface localSurface1;
        label1081: int i2;
        int i3;
        int i4;
        boolean bool4;
        if (paramParcel1.readInt() != 0)
        {
          localSurface1 = (Surface)Surface.CREATOR.createFromParcel(paramParcel1);
          i2 = paramParcel1.readInt();
          i3 = paramParcel1.readInt();
          i4 = paramParcel1.readInt();
          if (paramParcel1.readInt() == 0)
            break label1150;
          bool4 = j;
          label1110: if (paramParcel1.readInt() == 0)
            break label1156;
        }
        label1156: for (boolean bool5 = j; ; bool5 = false)
        {
          startCamera(localSurface1, i2, i3, i4, bool4, bool5, paramParcel1.readString());
          break;
          localSurface1 = null;
          break label1081;
          label1150: bool4 = false;
          break label1110;
        }
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        stopCamera();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        String str3 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (boolean bool3 = j; ; bool3 = false)
        {
          register(str3, bool3);
          break;
        }
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        unregister();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        registerWithISIMResponse(paramParcel1.readString(), paramParcel1.createByteArray());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        unregisterWithISIMResponse(paramParcel1.readString(), paramParcel1.createByteArray());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        registerListener(IIMSEventListener.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        unregisterListener(IIMSEventListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        mmTelSvcCallFuncAsync(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        int k = paramParcel1.readInt();
        int m = paramParcel1.readInt();
        int n = paramParcel1.readInt();
        String str2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (boolean bool2 = j; ; bool2 = false)
        {
          int i1 = mmTelSvcCallFunc(k, m, n, str2, bool2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          break;
        }
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        contactSvcCallFunction(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        setAudioTuningParameters(paramParcel1.readInt());
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        String str1 = getCurrentLatchedNetwork();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str1);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        boolean bool1 = isIMSEnabledOnWifi();
        paramParcel2.writeNoException();
        if (bool1)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        writeErrorData(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.sec.android.internal.ims.IIMSService");
        setAudioMode(paramParcel1.readInt());
      }
    }

    private static class Proxy
      implements IIMSService
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

      public void captureSurfaceImage(boolean paramBoolean, int paramInt)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          if (paramBoolean)
          {
            localParcel.writeInt(i);
            localParcel.writeInt(paramInt);
            this.mRemote.transact(17, localParcel, null, 1);
            return;
          }
          i = 0;
        }
        finally
        {
          localParcel.recycle();
        }
      }

      public void contactSvcCallFunction(int paramInt1, int paramInt2, int paramInt3, String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          localParcel.writeInt(paramInt3);
          localParcel.writeString(paramString);
          this.mRemote.transact(36, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void deinitSurface(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.mRemote.transact(18, localParcel1, localParcel2, 0);
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

      public String getCurrentLatchedNetwork()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(38, localParcel1, localParcel2, 0);
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

      public boolean getImsRegStatus()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
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

      public String getInterfaceDescriptor()
      {
        return "com.sec.android.internal.ims.IIMSService";
      }

      public IMessageEnabler getMessageEnabler()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          IMessageEnabler localIMessageEnabler = IMessageEnabler.Stub.asInterface(localParcel2.readStrongBinder());
          return localIMessageEnabler;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int isFrontCamInUse()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(9, localParcel1, localParcel2, 0);
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

      public boolean isIMSEnabledOnWifi()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(39, localParcel1, localParcel2, 0);
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

      public boolean isImsForbidden()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(12, localParcel1, localParcel2, 0);
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

      public boolean isOnEHRPD()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
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

      public boolean isOnLTE()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(10, localParcel1, localParcel2, 0);
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

      public boolean isRegistered()
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
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

      public int mmSS_Svc_Api(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
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

      public int mmTelSvcCallFunc(int paramInt1, int paramInt2, int paramInt3, String paramString, boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeInt(paramInt3);
          localParcel1.writeString(paramString);
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.mRemote.transact(35, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int j = localParcel2.readInt();
          return j;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void mmTelSvcCallFuncAsync(int paramInt1, int paramInt2, int paramInt3, String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          localParcel.writeInt(paramInt3);
          localParcel.writeString(paramString);
          this.mRemote.transact(34, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void register(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeString(paramString);
          if (paramBoolean)
          {
            localParcel.writeInt(i);
            this.mRemote.transact(28, localParcel, null, 1);
            return;
          }
          i = 0;
        }
        finally
        {
          localParcel.recycle();
        }
      }

      public int registerApp(int paramInt, String paramString, IImsServiceCallback paramIImsServiceCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          if (paramIImsServiceCallback != null)
          {
            localIBinder = paramIImsServiceCallback.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void registerListener(IIMSEventListener paramIIMSEventListener, int paramInt)
        throws RemoteException
      {
        IBinder localIBinder = null;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          if (paramIIMSEventListener != null)
            localIBinder = paramIIMSEventListener.asBinder();
          localParcel.writeStrongBinder(localIBinder);
          localParcel.writeInt(paramInt);
          this.mRemote.transact(32, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public int registerSSApp(int paramInt, String paramString, IImsServiceCallback paramIImsServiceCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          if (paramIImsServiceCallback != null)
          {
            localIBinder = paramIImsServiceCallback.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(7, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void registerWithISIMResponse(String paramString, byte[] paramArrayOfByte)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeString(paramString);
          localParcel.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(30, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void resetCameraID()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(14, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void sendLiveVideo()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(16, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void sendStillImage(String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeString(paramString);
          this.mRemote.transact(15, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void setAudioMode(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(41, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void setAudioTuningParameters(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(37, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void setOrientation(int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeInt(paramInt);
          this.mRemote.transact(24, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void startAudio()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(21, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void startCamera(Surface paramSurface, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, String paramString)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
            if (paramSurface == null)
              continue;
            localParcel.writeInt(1);
            paramSurface.writeToParcel(localParcel, 0);
            localParcel.writeInt(paramInt1);
            localParcel.writeInt(paramInt2);
            localParcel.writeInt(paramInt3);
            if (paramBoolean1)
            {
              j = i;
              localParcel.writeInt(j);
              if (!paramBoolean2)
                break label133;
              localParcel.writeInt(i);
              localParcel.writeString(paramString);
              this.mRemote.transact(26, localParcel, null, 1);
              return;
              localParcel.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel.recycle();
          }
          int j = 0;
          continue;
          label133: i = 0;
        }
      }

      public void startVideo()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(22, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void startVideoRenderer(Surface paramSurface, int paramInt1, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          if (paramSurface != null)
          {
            localParcel.writeInt(1);
            paramSurface.writeToParcel(localParcel, 0);
          }
          while (true)
          {
            localParcel.writeInt(paramInt1);
            localParcel.writeInt(paramInt2);
            localParcel.writeString(paramString);
            this.mRemote.transact(20, localParcel, null, 1);
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

      public void stopCamera()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(27, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void stopVideo()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(23, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void swapVideoSurface()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(19, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void switchCamera()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(13, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void unRegisterApp(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          this.mRemote.transact(3, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void unRegisterSSApp(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeInt(paramInt1);
          localParcel.writeInt(paramInt2);
          this.mRemote.transact(8, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void unregister()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          this.mRemote.transact(29, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void unregisterListener(IIMSEventListener paramIIMSEventListener)
        throws RemoteException
      {
        IBinder localIBinder = null;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          if (paramIIMSEventListener != null)
            localIBinder = paramIIMSEventListener.asBinder();
          localParcel.writeStrongBinder(localIBinder);
          this.mRemote.transact(33, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void unregisterWithISIMResponse(String paramString, byte[] paramArrayOfByte)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeString(paramString);
          localParcel.writeByteArray(paramArrayOfByte);
          this.mRemote.transact(31, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void voiceRecord(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel.writeInt(paramInt);
          localParcel.writeString(paramString);
          this.mRemote.transact(25, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void writeErrorData(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.sec.android.internal.ims.IIMSService");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(40, localParcel1, localParcel2, 0);
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
 * Qualified Name:     com.sec.android.internal.ims.IIMSService
 * JD-Core Version:    0.6.0
 */