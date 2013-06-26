package com.samsung.android.service.gesture;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class GestureManager$GestureListenerDelegate$1 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    if (GestureManager.GestureListenerDelegate.access$400(this.this$1) != null)
    {
      GestureEvent localGestureEvent = (GestureEvent)paramMessage.obj;
      if (localGestureEvent == null)
        break label36;
      GestureManager.GestureListenerDelegate.access$400(this.this$1).onGestureEvent(localGestureEvent);
    }
    while (true)
    {
      return;
      label36: Log.e("GestureManager", "gestureEvent : null");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.android.service.gesture.GestureManager.GestureListenerDelegate.1
 * JD-Core Version:    0.6.0
 */