package com.vlingo.midas.help;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.samsung.wakeupsetting.CustomCommandRecordingActivity;

class HelpWakeUpScreen$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent = new Intent(this.this$0, CustomCommandRecordingActivity.class);
    localIntent.putExtra("trainType", 0);
    localIntent.putExtra("help", true);
    this.this$0.startActivity(localIntent);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.HelpWakeUpScreen.1
 * JD-Core Version:    0.6.0
 */