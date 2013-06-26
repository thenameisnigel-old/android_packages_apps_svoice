package com.samsung.android.service.gesture;

import android.content.Context;

public class TspInputEventObserver
{
  public static final String NAME = "tsp_inputevent_observer";
  private boolean mConnected = false;
  private GestureManager mGestureManager = new GestureManager(paramContext, new TspInputEventObserver.1(this));

  public TspInputEventObserver(Context paramContext)
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.android.service.gesture.TspInputEventObserver
 * JD-Core Version:    0.6.0
 */