package com.samsung.android.service.gesture;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GestureEvent
  implements Parcelable
{
  public static final Parcelable.Creator<GestureEvent> CREATOR;
  public static final int GESTURE_EVENT_APPROACH = 2;
  public static final int GESTURE_EVENT_HANDSHAKE = 6;
  public static final int GESTURE_EVENT_HOVER = 5;
  public static final int GESTURE_EVENT_SWEEP_DOWN = 4;
  public static final int GESTURE_EVENT_SWEEP_LEFT = 1;
  public static final int GESTURE_EVENT_SWEEP_RIGHT = 0;
  public static final int GESTURE_EVENT_SWEEP_UP = 3;
  private static final int MAX_POOL_SIZE = 50;
  private static GestureEvent mPool;
  private static int mPoolSize;
  private static final GestureEvent mPoolSync = new GestureEvent();
  private int mEvent;
  private GestureEvent mNextLink;
  private String mProvider;

  static
  {
    mPoolSize = 0;
    CREATOR = new GestureEvent.1();
  }

  public GestureEvent()
  {
    this.mEvent = 0;
  }

  public GestureEvent(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public static GestureEvent obtain()
  {
    GestureEvent localGestureEvent2;
    synchronized (mPoolSync)
    {
      if (mPool != null)
      {
        localGestureEvent2 = mPool;
        mPool = localGestureEvent2.mNextLink;
        localGestureEvent2.mNextLink = null;
        mPoolSize = -1 + mPoolSize;
      }
      else
      {
        localGestureEvent2 = new GestureEvent();
      }
    }
    return localGestureEvent2;
  }

  private void readFromParcel(Parcel paramParcel)
  {
    this.mEvent = paramParcel.readInt();
    this.mProvider = paramParcel.readString();
  }

  @Deprecated
  public int describeContents()
  {
    return 0;
  }

  public int getEvent()
  {
    return this.mEvent;
  }

  public String getProvider()
  {
    return this.mProvider;
  }

  public void recycle()
  {
    synchronized (mPoolSync)
    {
      if (mPoolSize < 50)
      {
        this.mNextLink = mPool;
        mPool = this;
        mPoolSize = 1 + mPoolSize;
      }
      return;
    }
  }

  public void setEvent(int paramInt)
  {
    this.mEvent = paramInt;
  }

  public void setProvider(String paramString)
  {
    this.mProvider = paramString;
  }

  @Deprecated
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.mEvent);
    paramParcel.writeString(this.mProvider);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.android.service.gesture.GestureEvent
 * JD-Core Version:    0.6.0
 */