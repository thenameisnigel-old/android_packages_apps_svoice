package com.samsung.bargeinsetting;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

class VoiceInputControlSettings$2
  implements View.OnTouchListener
{
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = 1;
    Log.e("ALEX", "Touch happened");
    WindowManager.LayoutParams localLayoutParams = this.this$0.getWindow().getAttributes();
    if (paramMotionEvent.getAction() == 0)
    {
      VoiceInputControlSettings.access$102(this.this$0, paramMotionEvent.getRawX());
      VoiceInputControlSettings.access$202(this.this$0, paramMotionEvent.getRawY());
    }
    while (true)
    {
      return i;
      if (paramMotionEvent.getAction() == 2)
      {
        float f1 = paramMotionEvent.getRawX() - VoiceInputControlSettings.access$100(this.this$0);
        float f2 = paramMotionEvent.getRawY() - VoiceInputControlSettings.access$200(this.this$0);
        if ((Math.abs(f1) <= 3.0F) && (Math.abs(f2) <= 3.0F))
          continue;
        localLayoutParams.x -= (int)f1;
        localLayoutParams.y -= (int)f2;
        if (VoiceInputControlSettings.access$300(this.this$0) == 2)
        {
          VoiceInputControlSettings.access$402(this.this$0, localLayoutParams.x);
          VoiceInputControlSettings.access$502(this.this$0, localLayoutParams.y);
        }
        while (true)
        {
          VoiceInputControlSettings.access$102(this.this$0, paramMotionEvent.getRawX());
          VoiceInputControlSettings.access$202(this.this$0, paramMotionEvent.getRawY());
          this.this$0.getWindow().setAttributes(localLayoutParams);
          break;
          VoiceInputControlSettings.access$602(this.this$0, localLayoutParams.x);
          VoiceInputControlSettings.access$702(this.this$0, localLayoutParams.y);
        }
      }
      i = 0;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.bargeinsetting.VoiceInputControlSettings.2
 * JD-Core Version:    0.6.0
 */