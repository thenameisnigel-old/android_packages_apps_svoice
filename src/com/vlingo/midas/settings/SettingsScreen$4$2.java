package com.vlingo.midas.settings;

import android.app.Dialog;
import android.preference.EditTextPreference;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.vlingo.core.internal.settings.Settings;

class SettingsScreen$4$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    String str = SettingsScreen.access$1000(this.this$1.this$0).getEditText().getText().toString();
    if (str.trim().getBytes().length <= 0)
      Settings.setString("car_nav_office_address", "");
    while (true)
    {
      ((InputMethodManager)this.this$1.this$0.getSystemService("input_method")).hideSoftInputFromWindow(SettingsScreen.access$1000(this.this$1.this$0).getEditText().getWindowToken(), 0);
      SettingsScreen.access$1000(this.this$1.this$0).getDialog().cancel();
      return;
      Settings.setString("car_nav_office_address", str);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.SettingsScreen.4.2
 * JD-Core Version:    0.6.0
 */