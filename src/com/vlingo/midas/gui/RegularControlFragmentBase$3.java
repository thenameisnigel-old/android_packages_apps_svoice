package com.vlingo.midas.gui;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

class RegularControlFragmentBase$3
  implements View.OnLongClickListener
{
  public boolean onLongClick(View paramView)
  {
    if (System.currentTimeMillis() - RegularControlFragmentBase.access$000(this.this$0) > 15000L)
      RegularControlFragmentBase.access$102(this.this$0, 0);
    if (3 == RegularControlFragmentBase.access$100(this.this$0))
    {
      com.vlingo.midas.settings.debug.DebugSettings.SHOW_DEBUG = true;
      Toast.makeText(this.this$0.getContext(), 2131362659, 0).show();
    }
    while (true)
    {
      return false;
      RegularControlFragmentBase.access$102(this.this$0, 1);
      RegularControlFragmentBase.access$002(this.this$0, System.currentTimeMillis());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.RegularControlFragmentBase.3
 * JD-Core Version:    0.6.0
 */