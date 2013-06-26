package com.samsung.wakeupsetting;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

class NavigationSetting$4
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (NavigationSetting.access$300(this.this$0).getAction().equals("android.intent.action.CREATE_SHORTCUT"))
    {
      NavigationSetting.access$300(this.this$0).putExtra("address", NavigationSetting.access$000(this.this$0).getText().toString());
      NavigationSetting.access$300(this.this$0).putExtra("name", NavigationSetting.access$100(this.this$0).getText().toString());
      this.this$0.setResult(-1, NavigationSetting.access$300(this.this$0));
    }
    while (true)
    {
      this.this$0.finish();
      return;
      this.this$0.setResult(0);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.NavigationSetting.4
 * JD-Core Version:    0.6.0
 */