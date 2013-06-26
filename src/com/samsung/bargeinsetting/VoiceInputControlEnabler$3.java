package com.samsung.bargeinsetting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.provider.Settings.System;
import android.widget.CheckBox;
import com.vlingo.core.internal.settings.Settings;

class VoiceInputControlEnabler$3
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.val$check.isChecked())
      Settings.System.putInt(VoiceInputControlEnabler.access$100(this.this$0).getContentResolver(), "voiceinputcontrol_showNeverAgain", 1);
    Settings.setInt("temp_input_voice_control", Settings.System.getInt(VoiceInputControlEnabler.access$100(this.this$0).getContentResolver(), "voice_input_control", 1));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.bargeinsetting.VoiceInputControlEnabler.3
 * JD-Core Version:    0.6.0
 */