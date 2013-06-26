package com.samsung.bargeinsetting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.provider.Settings.System;
import android.widget.Switch;
import com.vlingo.core.internal.settings.Settings;

class VoiceInputControlEnabler$2
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    VoiceInputControlEnabler.access$000(this.this$0).setChecked(false);
    Settings.System.putInt(VoiceInputControlEnabler.access$100(this.this$0).getContentResolver(), "voice_input_control", 0);
    Settings.setInt("temp_input_voice_control", Settings.System.getInt(VoiceInputControlEnabler.access$100(this.this$0).getContentResolver(), "voice_input_control", 0));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.bargeinsetting.VoiceInputControlEnabler.2
 * JD-Core Version:    0.6.0
 */