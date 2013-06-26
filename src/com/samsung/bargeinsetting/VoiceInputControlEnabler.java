package com.samsung.bargeinsetting;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings.System;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public final class VoiceInputControlEnabler
  implements CompoundButton.OnCheckedChangeListener
{
  private final String KEY_VOICE_INPUT_CONTROL = "voice_input_control";
  private final Context mContext;
  private Switch mSwitch;

  public VoiceInputControlEnabler(Context paramContext, Switch paramSwitch)
  {
    this.mContext = paramContext;
    this.mSwitch = paramSwitch;
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    int i = Settings.System.getInt(this.mContext.getContentResolver(), "voiceinputcontrol_showNeverAgain", 0);
    int j = Settings.System.getInt(this.mContext.getContentResolver(), "voice_input_control", 0);
    Settings.System.putInt(this.mContext.getContentResolver(), "voice_input_control", j);
    if ((paramBoolean) && (j == 0) && (i == 0))
    {
      View localView = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(2130903045, null);
      CheckBox localCheckBox = (CheckBox)localView.findViewById(2131558514);
      localCheckBox.setOnClickListener(new VoiceInputControlEnabler.1(this));
      AlertDialog localAlertDialog = new AlertDialog.Builder(this.mContext).setTitle(2131362584).setPositiveButton(2131362601, new VoiceInputControlEnabler.3(this, localCheckBox)).setNegativeButton(2131362602, new VoiceInputControlEnabler.2(this)).setView(localView).create();
      localAlertDialog.show();
      localAlertDialog.setOnDismissListener(new VoiceInputControlEnabler.4(this));
      localAlertDialog.setOnKeyListener(new VoiceInputControlEnabler.5(this));
    }
    if ((paramBoolean) && (j == 0))
    {
      this.mSwitch.setChecked(true);
      Settings.System.putInt(this.mContext.getContentResolver(), "voice_input_control", 1);
    }
    while (true)
    {
      return;
      if ((!paramBoolean) && (j == 1))
      {
        this.mSwitch.setChecked(false);
        Settings.System.putInt(this.mContext.getContentResolver(), "voice_input_control", 0);
        continue;
      }
    }
  }

  public void pause()
  {
    ContentResolver localContentResolver = this.mContext.getContentResolver();
    if (this.mSwitch.isChecked());
    for (int i = 1; ; i = 0)
    {
      Settings.System.putInt(localContentResolver, "voice_input_control", i);
      this.mSwitch.setOnCheckedChangeListener(null);
      return;
    }
  }

  public void resume()
  {
    if (Settings.System.getInt(this.mContext.getContentResolver(), "voice_input_control", 0) != 0)
      this.mSwitch.setChecked(true);
    while (true)
    {
      this.mSwitch.setOnCheckedChangeListener(this);
      return;
      this.mSwitch.setChecked(false);
    }
  }

  public void setSwitch(Switch paramSwitch)
  {
    if (this.mSwitch == paramSwitch);
    while (true)
    {
      return;
      this.mSwitch.setOnCheckedChangeListener(null);
      this.mSwitch = paramSwitch;
      this.mSwitch.setOnCheckedChangeListener(this);
      if (Settings.System.getInt(this.mContext.getContentResolver(), "voice_input_control", 0) == 1)
      {
        this.mSwitch.setChecked(true);
        continue;
      }
      this.mSwitch.setChecked(false);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.bargeinsetting.VoiceInputControlEnabler
 * JD-Core Version:    0.6.0
 */