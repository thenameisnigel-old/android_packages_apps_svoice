package com.sec.android.app.sns3.svc.sp.facebook.auth.api;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.sec.android.app.sns3.svc.sp.facebook.response.SnsFbResponseMyAccountInfo;

public abstract interface ISnsFacebookCallbackMyAccountInfo extends IInterface
{
  public abstract void onResponse(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, Bundle paramBundle, SnsFbResponseMyAccountInfo paramSnsFbResponseMyAccountInfo)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ISnsFacebookCallbackMyAccountInfo
  {
    private static final String DESCRIPTOR = "com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookCallbackMyAccountInfo";
    static final int TRANSACTION_onResponse = 1;

    public Stub()
    {
      attachInterface(this, "com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookCallbackMyAccountInfo");
    }

    public static ISnsFacebookCallbackMyAccountInfo asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookCallbackMyAccountInfo");
        if ((localIInterface != null) && ((localIInterface instanceof ISnsFacebookCallbackMyAccountInfo)))
        {
          localObject = (ISnsFacebookCallbackMyAccountInfo)localIInterface;
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
      boolean bool1 = true;
      switch (paramInt1)
      {
      default:
        bool1 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        while (true)
        {
          return bool1;
          paramParcel2.writeString("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookCallbackMyAccountInfo");
        }
      case 1:
      }
      paramParcel1.enforceInterface("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookCallbackMyAccountInfo");
      int i = paramParcel1.readInt();
      boolean bool2;
      label78: int j;
      int k;
      Bundle localBundle;
      if (paramParcel1.readInt() != 0)
      {
        bool2 = bool1;
        j = paramParcel1.readInt();
        k = paramParcel1.readInt();
        if (paramParcel1.readInt() == 0)
          break label157;
        localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        label111: if (paramParcel1.readInt() == 0)
          break label163;
      }
      label157: label163: for (SnsFbResponseMyAccountInfo localSnsFbResponseMyAccountInfo = (SnsFbResponseMyAccountInfo)SnsFbResponseMyAccountInfo.CREATOR.createFromParcel(paramParcel1); ; localSnsFbResponseMyAccountInfo = null)
      {
        onResponse(i, bool2, j, k, localBundle, localSnsFbResponseMyAccountInfo);
        break;
        bool2 = false;
        break label78;
        localBundle = null;
        break label111;
      }
    }

    private static class Proxy
      implements ISnsFacebookCallbackMyAccountInfo
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
        return "com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookCallbackMyAccountInfo";
      }

      public void onResponse(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, Bundle paramBundle, SnsFbResponseMyAccountInfo paramSnsFbResponseMyAccountInfo)
        throws RemoteException
      {
        int i = 1;
        Parcel localParcel = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel.writeInterfaceToken("com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookCallbackMyAccountInfo");
            localParcel.writeInt(paramInt1);
            if (!paramBoolean)
              continue;
            localParcel.writeInt(i);
            localParcel.writeInt(paramInt2);
            localParcel.writeInt(paramInt3);
            if (paramBundle == null)
              continue;
            localParcel.writeInt(1);
            paramBundle.writeToParcel(localParcel, 0);
            if (paramSnsFbResponseMyAccountInfo != null)
            {
              localParcel.writeInt(1);
              paramSnsFbResponseMyAccountInfo.writeToParcel(localParcel, 0);
              this.mRemote.transact(1, localParcel, null, 1);
              return;
              i = 0;
              continue;
              localParcel.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel.recycle();
          }
          localParcel.writeInt(0);
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.sns3.svc.sp.facebook.auth.api.ISnsFacebookCallbackMyAccountInfo
 * JD-Core Version:    0.6.0
 */