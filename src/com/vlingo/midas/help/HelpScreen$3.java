package com.vlingo.midas.help;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.vlingo.midas.iux.IUXManager;

class HelpScreen$3
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    IUXManager.setIUXIntroRequired(false);
    IUXManager.finishIUX(this.this$0);
    this.this$0.finish();
    this.this$0.sendBroadcast(new Intent("com.vlingo.client.iux.cleanup"));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.HelpScreen.3
 * JD-Core Version:    0.6.0
 */