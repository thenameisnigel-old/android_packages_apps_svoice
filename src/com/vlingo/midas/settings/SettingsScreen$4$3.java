package com.vlingo.midas.settings;

import android.app.Dialog;
import android.preference.EditTextPreference;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

class SettingsScreen$4$3
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    ((InputMethodManager)this.this$1.this$0.getSystemService("input_method")).hideSoftInputFromWindow(SettingsScreen.access$1000(this.this$1.this$0).getEditText().getWindowToken(), 0);
    SettingsScreen.access$1000(this.this$1.this$0).getDialog().cancel();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.SettingsScreen.4.3
 * JD-Core Version:    0.6.0
 */