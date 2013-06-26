package com.vlingo.midas.help;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.vlingo.midas.iux.IUXManager;

class HelpEditWhatYouSaidScreen$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    this.this$0.sendBroadcast(new Intent("com.vlingo.client.iux.cleanup"));
    IUXManager.setIUXIntroRequired(true);
    this.this$0.finish();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.HelpEditWhatYouSaidScreen.2
 * JD-Core Version:    0.6.0
 */