package com.vlingo.midas.help;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

class WCISCompositionScreen$1
  implements View.OnTouchListener
{
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = 1;
    WindowManager.LayoutParams localLayoutParams = this.this$0.getWindow().getAttributes();
    if (paramMotionEvent.getAction() == 0)
    {
      WCISCompositionScreen.access$002(this.this$0, paramMotionEvent.getRawX());
      WCISCompositionScreen.access$102(this.this$0, paramMotionEvent.getRawY());
    }
    while (true)
    {
      return i;
      if (paramMotionEvent.getAction() == 2)
      {
        float f1 = paramMotionEvent.getRawX() - WCISCompositionScreen.access$000(this.this$0);
        float f2 = paramMotionEvent.getRawY() - WCISCompositionScreen.access$100(this.this$0);
        if ((Math.abs(f1) <= 3.0F) && (Math.abs(f2) <= 3.0F))
          continue;
        localLayoutParams.x -= (int)f1;
        localLayoutParams.y -= (int)f2;
        if (WCISCompositionScreen.access$200(this.this$0) == 2)
        {
          WCISCompositionScreen.access$302(this.this$0, localLayoutParams.x);
          WCISCompositionScreen.access$402(this.this$0, localLayoutParams.y);
        }
        while (true)
        {
          WCISCompositionScreen.access$002(this.this$0, paramMotionEvent.getRawX());
          WCISCompositionScreen.access$102(this.this$0, paramMotionEvent.getRawY());
          this.this$0.getWindow().setAttributes(localLayoutParams);
          break;
          WCISCompositionScreen.access$502(this.this$0, localLayoutParams.x);
          WCISCompositionScreen.access$602(this.this$0, localLayoutParams.y);
        }
      }
      i = 0;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.WCISCompositionScreen.1
 * JD-Core Version:    0.6.0
 */