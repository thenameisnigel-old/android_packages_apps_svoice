package com.vlingo.midas.settings;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

class SettingsScreen$4$1
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
      ((EditText)paramView).selectAll();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.SettingsScreen.4.1
 * JD-Core Version:    0.6.0
 */