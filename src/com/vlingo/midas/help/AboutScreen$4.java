package com.vlingo.midas.help;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.vlingo.midas.VlingoApplication;

class AboutScreen$4
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (AboutScreen.access$200(this.this$0) == 0)
    {
      AboutScreen.access$202(this.this$0, 1);
      String str = VlingoApplication.getInstance().getResources().getString(2131362184);
      this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.AboutScreen.4
 * JD-Core Version:    0.6.0
 */