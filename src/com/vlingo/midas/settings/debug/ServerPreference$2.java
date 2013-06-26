package com.vlingo.midas.settings.debug;

import android.text.Editable;
import android.text.TextWatcher;

class ServerPreference$2
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    this.this$0.updateSpinner();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.ServerPreference.2
 * JD-Core Version:    0.6.0
 */