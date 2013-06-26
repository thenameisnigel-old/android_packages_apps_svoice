package com.samsung.wfd;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IWfdManager extends IInterface
{
  public abstract boolean WFDGetSubtitleStatus()
    throws RemoteException;

  public abstract boolean WFDGetSuspendStatus()
    throws RemoteException;

  public abstract boolean WFDPostSubtitle(String paramString, int paramInt)
    throws RemoteException;

  public abstract boolean WFDPostSuspend(String paramString)
    throws RemoteException;

  public abstract boolean WFDSetSubtitleStatus(boolean paramBoolean)
    throws RemoteException;

  public abstract boolean WFDSetSuspendStatus(boolean paramBoolean)
    throws RemoteException;

  public abstract String getDisplayDeviceAddress()
    throws RemoteException;

  public abstract String getDisplayDeviceName()
    throws RemoteException;

  public abstract int getDisplayDeviceSecure()
    throws RemoteException;

  public abstract Messenger getMessenger()
    throws RemoteException;

  public abstract WfdInfo getWfdInfo()
    throws RemoteException;

  public abstract int getWfdSinkResolution()
    throws RemoteException;

  public abstract int getWfdState()
    throws RemoteException;

  public abstract void handleDown(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
    throws RemoteException;

  public abstract void handleMove(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
    throws RemoteException;

  public abstract void handleUp(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
    throws RemoteException;

  public abstract boolean isActiveUIBC()
    throws RemoteException;

  public abstract boolean isWfdEnabledPlayer()
    throws RemoteException;

  public abstract void keyDown(int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract void keyUp(int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract boolean notifyContentFinish()
    throws RemoteException;

  public abstract boolean sendToWfdStartRTSP()
    throws RemoteException;

  public abstract boolean setWfdEnabled(int paramInt)
    throws RemoteException;

  public abstract boolean setWfdEnabledDialog(boolean paramBoolean)
    throws RemoteException;

  public abstract boolean setWfdEnabledPlayer(boolean paramBoolean)
    throws RemoteException;

  public abstract void setWfdModeAlways()
    throws RemoteException;

  public abstract boolean setWfdRestart()
    throws RemoteException;

  public abstract boolean setWfdRestartOption(int paramInt)
    throws RemoteException;

  public abstract boolean setWfdTerminate()
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IWfdManager
  {
    private static final String DESCRIPTOR = "com.samsung.wfd.IWfdManager";
    static final int TRANSACTION_WFDGetSubtitleStatus = 13;
    static final int TRANSACTION_WFDGetSuspendStatus = 16;
    static final int TRANSACTION_WFDPostSubtitle = 15;
    static final int TRANSACTION_WFDPostSuspend = 18;
    static final int TRANSACTION_WFDSetSubtitleStatus = 14;
    static final int TRANSACTION_WFDSetSuspendStatus = 17;
    static final int TRANSACTION_getDisplayDeviceAddress = 7;
    static final int TRANSACTION_getDisplayDeviceName = 8;
    static final int TRANSACTION_getDisplayDeviceSecure = 9;
    static final int TRANSACTION_getMessenger = 11;
    static final int TRANSACTION_getWfdInfo = 6;
    static final int TRANSACTION_getWfdSinkResolution = 12;
    static final int TRANSACTION_getWfdState = 10;
    static final int TRANSACTION_handleDown = 26;
    static final int TRANSACTION_handleMove = 25;
    static final int TRANSACTION_handleUp = 27;
    static final int TRANSACTION_isActiveUIBC = 24;
    static final int TRANSACTION_isWfdEnabledPlayer = 21;
    static final int TRANSACTION_keyDown = 28;
    static final int TRANSACTION_keyUp = 29;
    static final int TRANSACTION_notifyContentFinish = 20;
    static final int TRANSACTION_sendToWfdStartRTSP = 23;
    static final int TRANSACTION_setWfdEnabled = 2;
    static final int TRANSACTION_setWfdEnabledDialog = 1;
    static final int TRANSACTION_setWfdEnabledPlayer = 19;
    static final int TRANSACTION_setWfdModeAlways = 22;
    static final int TRANSACTION_setWfdRestart = 4;
    static final int TRANSACTION_setWfdRestartOption = 5;
    static final int TRANSACTION_setWfdTerminate = 3;

    public Stub()
    {
      attachInterface(this, "com.samsung.wfd.IWfdManager");
    }

    public static IWfdManager asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.samsung.wfd.IWfdManager");
        if ((localIInterface != null) && ((localIInterface instanceof IWfdManager)))
        {
          localObject = (IWfdManager)localIInterface;
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
      }
      while (true)
      {
        return j;
        paramParcel2.writeString("com.samsung.wfd.IWfdManager");
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        if (paramParcel1.readInt() != 0);
        int i8;
        for (int i7 = j; ; i8 = 0)
        {
          boolean bool16 = setWfdEnabledDialog(i7);
          paramParcel2.writeNoException();
          if (bool16)
            i = j;
          paramParcel2.writeInt(i);
          break;
        }
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool15 = setWfdEnabled(paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (bool15)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool14 = setWfdTerminate();
        paramParcel2.writeNoException();
        if (bool14)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool13 = setWfdRestart();
        paramParcel2.writeNoException();
        if (bool13)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool12 = setWfdRestartOption(paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (bool12)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        WfdInfo localWfdInfo = getWfdInfo();
        paramParcel2.writeNoException();
        if (localWfdInfo != null)
        {
          paramParcel2.writeInt(j);
          localWfdInfo.writeToParcel(paramParcel2, j);
          continue;
        }
        paramParcel2.writeInt(0);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        String str2 = getDisplayDeviceAddress();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str2);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        String str1 = getDisplayDeviceName();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str1);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        int i6 = getDisplayDeviceSecure();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i6);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        int i5 = getWfdState();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i5);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        Messenger localMessenger = getMessenger();
        paramParcel2.writeNoException();
        if (localMessenger != null)
        {
          paramParcel2.writeInt(j);
          localMessenger.writeToParcel(paramParcel2, j);
          continue;
        }
        paramParcel2.writeInt(0);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        int i4 = getWfdSinkResolution();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i4);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool11 = WFDGetSubtitleStatus();
        paramParcel2.writeNoException();
        if (bool11)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        if (paramParcel1.readInt() != 0);
        int i3;
        for (int i2 = j; ; i3 = 0)
        {
          boolean bool10 = WFDSetSubtitleStatus(i2);
          paramParcel2.writeNoException();
          if (bool10)
            i = j;
          paramParcel2.writeInt(i);
          break;
        }
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool9 = WFDPostSubtitle(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (bool9)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool8 = WFDGetSuspendStatus();
        paramParcel2.writeNoException();
        if (bool8)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        if (paramParcel1.readInt() != 0);
        int i1;
        for (int n = j; ; i1 = 0)
        {
          boolean bool7 = WFDSetSuspendStatus(n);
          paramParcel2.writeNoException();
          if (bool7)
            i = j;
          paramParcel2.writeInt(i);
          break;
        }
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool6 = WFDPostSuspend(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (bool6)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        if (paramParcel1.readInt() != 0);
        int m;
        for (int k = j; ; m = 0)
        {
          boolean bool5 = setWfdEnabledPlayer(k);
          paramParcel2.writeNoException();
          if (bool5)
            i = j;
          paramParcel2.writeInt(i);
          break;
        }
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool4 = notifyContentFinish();
        paramParcel2.writeNoException();
        if (bool4)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool3 = isWfdEnabledPlayer();
        paramParcel2.writeNoException();
        if (bool3)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        setWfdModeAlways();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool2 = sendToWfdStartRTSP();
        paramParcel2.writeNoException();
        if (bool2)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        boolean bool1 = isActiveUIBC();
        paramParcel2.writeNoException();
        if (bool1)
          i = j;
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        handleMove(paramParcel1.readInt(), paramParcel1.createIntArray(), paramParcel1.createIntArray(), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        handleDown(paramParcel1.readInt(), paramParcel1.createIntArray(), paramParcel1.createIntArray(), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        handleUp(paramParcel1.readInt(), paramParcel1.createIntArray(), paramParcel1.createIntArray(), paramParcel1.createIntArray());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        keyDown(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.samsung.wfd.IWfdManager");
        keyUp(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
      }
    }

    private static class Proxy
      implements IWfdManager
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public boolean WFDGetSubtitleStatus()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(13, localParcel1, localParcel2, 0);
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

      public boolean WFDGetSuspendStatus()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(16, localParcel1, localParcel2, 0);
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

      public boolean WFDPostSubtitle(String paramString, int paramInt)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(15, localParcel1, localParcel2, 0);
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

      public boolean WFDPostSuspend(String paramString)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          localParcel1.writeString(paramString);
          this.mRemote.transact(18, localParcel1, localParcel2, 0);
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

      public boolean WFDSetSubtitleStatus(boolean paramBoolean)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          if (paramBoolean);
          for (int j = i; ; j = 0)
          {
            localParcel1.writeInt(j);
            this.mRemote.transact(14, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int k = localParcel2.readInt();
            if (k == 0)
              break;
            return i;
          }
          i = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean WFDSetSuspendStatus(boolean paramBoolean)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          if (paramBoolean);
          for (int j = i; ; j = 0)
          {
            localParcel1.writeInt(j);
            this.mRemote.transact(17, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int k = localParcel2.readInt();
            if (k == 0)
              break;
            return i;
          }
          i = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public String getDisplayDeviceAddress()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
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

      public String getDisplayDeviceName()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
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

      public int getDisplayDeviceSecure()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
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

      public String getInterfaceDescriptor()
      {
        return "com.samsung.wfd.IWfdManager";
      }

      public Messenger getMessenger()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localMessenger = (Messenger)Messenger.CREATOR.createFromParcel(localParcel2);
            return localMessenger;
          }
          Messenger localMessenger = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public WfdInfo getWfdInfo()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localWfdInfo = (WfdInfo)WfdInfo.CREATOR.createFromParcel(localParcel2);
            return localWfdInfo;
          }
          WfdInfo localWfdInfo = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int getWfdSinkResolution()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(12, localParcel1, localParcel2, 0);
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

      public int getWfdState()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
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

      public void handleDown(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          localParcel1.writeInt(paramInt);
          localParcel1.writeIntArray(paramArrayOfInt1);
          localParcel1.writeIntArray(paramArrayOfInt2);
          localParcel1.writeIntArray(paramArrayOfInt3);
          this.mRemote.transact(26, localParcel1, localParcel2, 0);
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

      public void handleMove(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          localParcel1.writeInt(paramInt);
          localParcel1.writeIntArray(paramArrayOfInt1);
          localParcel1.writeIntArray(paramArrayOfInt2);
          localParcel1.writeIntArray(paramArrayOfInt3);
          this.mRemote.transact(25, localParcel1, localParcel2, 0);
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

      public void handleUp(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          localParcel1.writeInt(paramInt);
          localParcel1.writeIntArray(paramArrayOfInt1);
          localParcel1.writeIntArray(paramArrayOfInt2);
          localParcel1.writeIntArray(paramArrayOfInt3);
          this.mRemote.transact(27, localParcel1, localParcel2, 0);
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

      public boolean isActiveUIBC()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(24, localParcel1, localParcel2, 0);
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

      public boolean isWfdEnabledPlayer()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(21, localParcel1, localParcel2, 0);
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

      public void keyDown(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(28, localParcel1, localParcel2, 0);
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

      public void keyUp(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          this.mRemote.transact(29, localParcel1, localParcel2, 0);
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

      public boolean notifyContentFinish()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(20, localParcel1, localParcel2, 0);
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

      public boolean sendToWfdStartRTSP()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(23, localParcel1, localParcel2, 0);
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

      public boolean setWfdEnabled(int paramInt)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          localParcel1.writeInt(paramInt);
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

      public boolean setWfdEnabledDialog(boolean paramBoolean)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          if (paramBoolean);
          for (int j = i; ; j = 0)
          {
            localParcel1.writeInt(j);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int k = localParcel2.readInt();
            if (k == 0)
              break;
            return i;
          }
          i = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setWfdEnabledPlayer(boolean paramBoolean)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          if (paramBoolean);
          for (int j = i; ; j = 0)
          {
            localParcel1.writeInt(j);
            this.mRemote.transact(19, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int k = localParcel2.readInt();
            if (k == 0)
              break;
            return i;
          }
          i = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void setWfdModeAlways()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          this.mRemote.transact(22, localParcel1, localParcel2, 0);
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

      public boolean setWfdRestart()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
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

      public boolean setWfdRestartOption(int paramInt)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
          localParcel1.writeInt(paramInt);
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

      public boolean setWfdTerminate()
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.samsung.wfd.IWfdManager");
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
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.IWfdManager
 * JD-Core Version:    0.6.0
 */