package com.vlingo.midas.help;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.vlingo.core.internal.settings.Settings;

class AboutScreen$6
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    ((TextView)this.this$0.findViewById(2131558630)).setText(AboutScreen.access$300(this.this$0) + Settings.getUUIDDeviceID());
    ((ClipboardManager)this.this$0.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("uuid", Settings.getUUIDDeviceID()));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.help.AboutScreen.6
 * JD-Core Version:    0.6.0
 */