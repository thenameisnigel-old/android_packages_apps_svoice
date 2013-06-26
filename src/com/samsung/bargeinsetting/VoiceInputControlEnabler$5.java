package com.samsung.bargeinsetting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.provider.Settings.System;
import android.view.KeyEvent;
import android.widget.Switch;
import com.vlingo.core.internal.settings.Settings;

class VoiceInputControlEnabler$5
  implements DialogInterface.OnKeyListener
{
  public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    int i = 0;
    if (paramInt == 4)
    {
      paramDialogInterface.cancel();
      VoiceInputControlEnabler.access$000(this.this$0).setChecked(false);
      Settings.setInt("temp_input_voice_control", Settings.System.getInt(VoiceInputControlEnabler.access$100(this.this$0).getContentResolver(), "voice_input_control", 0));
      i = 1;
    }
    return i;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.bargeinsetting.VoiceInputControlEnabler.5
 * JD-Core Version:    0.6.0
 */