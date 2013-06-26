package com.vlingo.midas.help;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.vlingo.core.internal.settings.Settings;

class WCISCompositionScreen$3$2
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    String str = WCISCompositionScreen.access$700(this.this$1.this$0).getText().toString();
    if (str.trim().getBytes().length <= 0)
      Settings.setString("car_nav_home_address", "");
    while (true)
    {
      ((InputMethodManager)this.this$1.this$0.getSystemService("input_method")).hideSoftInputFromWindow(WCISCompositionScreen.access$700(this.this$1.this$0).getWindowToken(), 0);
      return;
      Settings.setString("car_nav_home_address", str);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.WCISCompositionScreen.3.2
 * JD-Core Version:    0.6.0
 */