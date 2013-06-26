package com.samsung.wakeupsetting;

import android.view.inputmethod.InputMethodManager;
import java.util.TimerTask;

class NavigationSetting$1 extends TimerTask
{
  public void run()
  {
    ((InputMethodManager)this.this$0.getSystemService("input_method")).showSoftInput(NavigationSetting.access$000(this.this$0), 1);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.NavigationSetting.1
 * JD-Core Version:    0.6.0
 */