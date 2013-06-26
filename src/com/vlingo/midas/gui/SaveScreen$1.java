package com.vlingo.midas.gui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class SaveScreen$1
  implements View.OnTouchListener
{
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (SaveScreen.access$000(this.this$0)))
      this.this$0.finish();
    return false;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.SaveScreen.1
 * JD-Core Version:    0.6.0
 */