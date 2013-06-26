package com.vlingo.midas.gui.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class RelativeLayout extends android.widget.RelativeLayout
{
  public RelativeLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.customviews.RelativeLayout
 * JD-Core Version:    0.6.0
 */