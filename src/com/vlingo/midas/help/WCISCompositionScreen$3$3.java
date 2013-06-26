package com.vlingo.midas.help;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

class WCISCompositionScreen$3$3
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    ((InputMethodManager)this.this$1.this$0.getSystemService("input_method")).hideSoftInputFromWindow(WCISCompositionScreen.access$700(this.this$1.this$0).getWindowToken(), 0);
    paramDialogInterface.cancel();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.WCISCompositionScreen.3.3
 * JD-Core Version:    0.6.0
 */