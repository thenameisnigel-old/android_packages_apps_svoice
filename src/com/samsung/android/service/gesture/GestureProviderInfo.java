package com.samsung.android.service.gesture;

import java.util.ArrayList;
import java.util.List;

public class GestureProviderInfo
{
  public static final int GESTURE_APPROACH = 6;
  public static final int GESTURE_DRAW_CIRCLE = 5;
  public static final int GESTURE_HANDSHAPE = 8;
  public static final int GESTURE_HORIZONTAL_SHAKE = 3;
  public static final int GESTURE_HORIZONTAL_SWEEP = 1;
  public static final int GESTURE_HOVER = 10;
  public static final int GESTURE_TAP = 7;
  public static final int GESTURE_THUMB_POSITION = 9;
  public static final int GESTURE_VERTICAL_SHAKE = 4;
  public static final int GESTURE_VERTICAL_SWEEP = 2;
  private String mName;
  private List<Integer> mSupportedGestures = new ArrayList();

  public String getName()
  {
    return this.mName;
  }

  public List<Integer> getSupportedGestures()
  {
    return this.mSupportedGestures;
  }

  public void setName(String paramString)
  {
    this.mName = paramString;
  }

  @Deprecated
  public void setSupportedGestures(List<Integer> paramList)
  {
    this.mSupportedGestures = paramList;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.android.service.gesture.GestureProviderInfo
 * JD-Core Version:    0.6.0
 */