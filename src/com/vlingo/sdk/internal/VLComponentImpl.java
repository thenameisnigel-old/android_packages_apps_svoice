package com.vlingo.sdk.internal;

import android.os.Handler;
import com.vlingo.sdk.VLComponent;

abstract class VLComponentImpl
  implements VLComponent
{
  private Object mDestroyLock;
  private Handler mHandler;
  private boolean mIsBusy;
  private boolean mIsValid;
  private VLComponentManager mManager;

  VLComponentImpl(VLComponentManager paramVLComponentManager, Handler paramHandler)
  {
    this.mManager = paramVLComponentManager;
    this.mHandler = paramHandler;
    this.mIsValid = true;
    this.mDestroyLock = new Object();
  }

  public void destroy()
  {
    monitorenter;
    try
    {
      synchronized (this.mDestroyLock)
      {
        if (this.mIsValid)
        {
          this.mIsValid = false;
          this.mHandler = null;
          this.mManager.removeComponent(getClass());
          this.mManager = null;
          onDestroy();
        }
        monitorexit;
        return;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  Object getDestroyLock()
  {
    return this.mDestroyLock;
  }

  Handler getHandler()
  {
    return this.mHandler;
  }

  boolean isBusy()
  {
    return this.mIsBusy;
  }

  public boolean isValid()
  {
    return this.mIsValid;
  }

  abstract void onDestroy();

  void setBusy(boolean paramBoolean)
  {
    this.mIsBusy = paramBoolean;
  }

  void validateInstance()
  {
    if (!this.mIsValid)
      throw new IllegalStateException(getClass().getName() + " instance is no longer valid!");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLComponentImpl
 * JD-Core Version:    0.6.0
 */