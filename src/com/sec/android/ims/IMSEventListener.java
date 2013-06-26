package com.sec.android.ims;

import android.os.RemoteException;
import android.util.Log;
import com.sec.android.internal.ims.IIMSEventListener;
import com.sec.android.internal.ims.IIMSEventListener.Stub;
import com.sec.android.internal.ims.IIMSParams;

public abstract class IMSEventListener
{
  private static final int HANDLE_EVENT = 0;
  public static final int LISTEN_ENABLER_MESSAGE = 4;
  public static final int LISTEN_ENABLER_VOIP = 2;
  public static final int LISTEN_IMS_CORE = 1;
  public static final int LISTEN_NONE = 0;
  public static final int LISTEN_SIGNAL_STRENGTHS = 256;
  public IIMSEventListener callback = new IIMSEventListener.Stub()
  {
    public void handleEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte, IIMSParams paramIIMSParams)
      throws RemoteException
    {
      IMSEventListener.this.handleEvent(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfByte, paramIIMSParams);
      Log.d("IIMSEventListener", "Inside handleEvent");
    }

    public void notifyEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, String[] paramArrayOfString)
      throws RemoteException
    {
      IMSEventListener.this.notifyEvent(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, paramArrayOfString);
      Log.d("IIMSEventListener", "Inside notifyEvent");
    }
  };

  public abstract void handleEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte, IIMSParams paramIIMSParams);

  public abstract void notifyEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, String[] paramArrayOfString);

  class MessageData
  {
    int arg1;
    int arg2;
    byte[] data;

    MessageData(int paramInt1, int paramArrayOfByte, byte[] arg4)
    {
      this.arg1 = paramInt1;
      this.arg2 = paramArrayOfByte;
      Object localObject;
      this.data = localObject;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.ims.IMSEventListener
 * JD-Core Version:    0.6.0
 */