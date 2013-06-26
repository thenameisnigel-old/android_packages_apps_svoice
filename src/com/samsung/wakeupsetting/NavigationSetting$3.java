package com.samsung.wakeupsetting;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

class NavigationSetting$3
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
    if ((NavigationSetting.access$000(this.this$0).getText().toString().isEmpty()) || (NavigationSetting.access$100(this.this$0).getText().toString().isEmpty()))
      NavigationSetting.access$200(this.this$0).setEnabled(false);
    while (true)
    {
      return;
      NavigationSetting.access$200(this.this$0).setEnabled(true);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.NavigationSetting.3
 * JD-Core Version:    0.6.0
 */