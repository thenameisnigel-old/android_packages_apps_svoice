package com.vlingo.midas.help;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.midas.iux.IUXCompoundActivity;

class HelpScreen$4
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (Settings.isAsrEditingEnabled());
    for (int i = 4; ; i = 2)
    {
      IUXCompoundActivity.setMStep(i);
      this.this$0.finish();
      Intent localIntent = new Intent(this.this$0, IUXCompoundActivity.class);
      this.this$0.startActivity(localIntent);
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.HelpScreen.4
 * JD-Core Version:    0.6.0
 */