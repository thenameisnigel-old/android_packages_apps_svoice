package com.vlingo.midas.settings;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import com.samsung.myplace.MyPlaceActivity;
import com.vlingo.midas.ClientConfiguration;

public class DrivingModeSettings extends VLPreferenceActivity
  implements CompoundButton.OnCheckedChangeListener
{
  private static final String ALARM_NOTIFICATION = "alarm_notification";
  private static final String CHATONV_NOTIFICATION = "chaton_call_notification";
  public static final String DB_KEY_DRIVING_MODE_ON = "driving_mode_on";
  public static final String DRIVING_MODE_ALARM_NOTIFICATION = "driving_mode_alarm_notification";
  private static final String DRIVING_MODE_CHATON_CALL_NOTIFICATION = "driving_mode_chaton_call_notification";
  public static final String DRIVING_MODE_INCOMING_CALL_NOTIFICATION = "driving_mode_incoming_call_notification";
  public static final String DRIVING_MODE_MESSAGE_CONTENTS = "driving_mode_message_contents";
  public static final String DRIVING_MODE_MESSAGE_NOTIFICATION = "driving_mode_message_notification";
  public static final String DRIVING_MODE_SCHEDULE_NOTIFICATION = "driving_mode_schedule_notification";
  public static final String DRIVING_MODE_VOICE_MAIL_NOTIFICATION = "driving_mode_voice_mail_notification";
  private static final String INCOMING_CALL_NOTIFICATION = "incoming_call_notification";
  private static final String MESSAGE_NOTIFICATION = "message_notification";
  private static final String MY_PLACE = "my_place";
  private static final String SCHEDULE_NOTIFICATION = "schedule_notification";
  private static final String TAG = "DrivingModeSettings";
  private static final String VOICE_MAIL_NOTIFICATION = "voice_mail_notification";
  private static SettingsObserver sSettingsObserver;
  private View mActionBarLayout;
  private Switch mActionBarSwitch;
  private CheckBoxPreference mAlarmNotification;
  private CheckBoxPreference mChatonV;
  private CheckBoxPreference mIncomingCallNotification;
  private CheckBoxPreference mMessageNotification;
  private TwoLinePreference mMyPlace;
  private CheckBoxPreference mScheduleNotification;
  private CheckBoxPreference mVoiceMailNotification;

  public static boolean areAllDrivingModeOptionsDisabled(ContentResolver paramContentResolver)
  {
    int i = 1;
    if (Settings.System.getInt(paramContentResolver, "driving_mode_on", 0) == 0);
    while (true)
    {
      return i;
      if (Settings.System.getInt(paramContentResolver, "driving_mode_incoming_call_notification", 0) + Settings.System.getInt(paramContentResolver, "driving_mode_message_notification", 0) + Settings.System.getInt(paramContentResolver, "driving_mode_voice_mail_notification", 0) + Settings.System.getInt(paramContentResolver, "driving_mode_alarm_notification", 0) + Settings.System.getInt(paramContentResolver, "driving_mode_schedule_notification", 0) + Settings.System.getInt(paramContentResolver, "driving_mode_chaton_call_notification", 0) <= 0)
        continue;
      i = 0;
    }
  }

  private boolean isChatOnVInstalled()
  {
    PackageManager localPackageManager = getPackageManager();
    try
    {
      localPackageManager.getApplicationInfo("com.sec.chaton", 128);
      i = 1;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        localNameNotFoundException.printStackTrace();
        int i = 0;
      }
    }
  }

  private void updateState()
  {
    boolean bool = false;
    if (Settings.System.getInt(getContentResolver(), "driving_mode_on", 0) != 0)
      bool = true;
    this.mActionBarSwitch.setChecked(bool);
    this.mIncomingCallNotification.setEnabled(bool);
    this.mMessageNotification.setEnabled(bool);
    this.mAlarmNotification.setEnabled(bool);
    this.mScheduleNotification.setEnabled(bool);
    if (this.mChatonV != null)
      this.mChatonV.setEnabled(bool);
  }

  public void onActivityCreated()
  {
    this.mActionBarSwitch = new Switch(this);
    if ((this instanceof PreferenceActivity))
    {
      int i = getResources().getDimensionPixelSize(2131427336);
      this.mActionBarSwitch.setPadding(0, 0, i, 0);
      getActionBar().setDisplayOptions(30);
      getActionBar().setCustomView(this.mActionBarSwitch, new ActionBar.LayoutParams(-2, -2, 21));
      this.mActionBarLayout = getActionBar().getCustomView();
    }
    this.mActionBarSwitch.setOnCheckedChangeListener(this);
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    Log.d("DrivingModeSettings", "onCheckChanged : Driving mode changed broadcast");
    Intent localIntent = new Intent("android.settings.DRIVING_MODE_CHANGED");
    if (paramBoolean)
    {
      Settings.System.putInt(getContentResolver(), "driving_mode_on", 1);
      localIntent.putExtra("DrivingMode", 1);
    }
    while (true)
    {
      sendBroadcast(localIntent);
      return;
      Settings.System.putInt(getContentResolver(), "driving_mode_on", 0);
      localIntent.putExtra("DrivingMode", 0);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    int i;
    if (!ClientConfiguration.isChatONVPhone())
      i = 1;
    while (true)
    {
      addPreferencesFromResource(2131034113);
      this.mMyPlace = ((TwoLinePreference)findPreference("my_place"));
      this.mIncomingCallNotification = ((CheckBoxPreference)findPreference("incoming_call_notification"));
      this.mMessageNotification = ((CheckBoxPreference)findPreference("message_notification"));
      this.mVoiceMailNotification = ((CheckBoxPreference)findPreference("voice_mail_notification"));
      this.mAlarmNotification = ((CheckBoxPreference)findPreference("alarm_notification"));
      this.mScheduleNotification = ((CheckBoxPreference)findPreference("schedule_notification"));
      this.mChatonV = ((CheckBoxPreference)findPreference("chaton_call_notification"));
      if (i == 1)
      {
        getPreferenceScreen().removePreference(this.mChatonV);
        Settings.System.putInt(getContentResolver(), "driving_mode_chaton_call_notification", 0);
      }
      if (this.mVoiceMailNotification != null)
      {
        getPreferenceScreen().removePreference(this.mVoiceMailNotification);
        Settings.System.putInt(getContentResolver(), "driving_mode_voice_mail_notification", 0);
      }
      try
      {
        getPackageManager().getApplicationInfo("com.sec.android.app.clockpackage", 0);
        onActivityCreated();
        return;
        i = 0;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
        {
          Log.d("DrivingModeSettings", "Samsung Alarm Clock is not found");
          getPreferenceScreen().removePreference(this.mAlarmNotification);
          Settings.System.putInt(getContentResolver(), "driving_mode_alarm_notification", 0);
        }
      }
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 16908332:
    }
    while (true)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      finish();
    }
  }

  public void onPause()
  {
    if (areAllDrivingModeOptionsDisabled(getContentResolver()))
      this.mActionBarSwitch.setChecked(false);
    while (true)
    {
      if (sSettingsObserver != null)
      {
        sSettingsObserver.stopObserving();
        sSettingsObserver = null;
      }
      super.onPause();
      return;
      this.mActionBarSwitch.setEnabled(true);
    }
  }

  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    ContentResolver localContentResolver1 = getContentResolver();
    int i1;
    if (paramPreference.equals(this.mIncomingCallNotification))
      if (this.mIncomingCallNotification.isChecked())
      {
        i1 = 1;
        Settings.System.putInt(localContentResolver1, "driving_mode_incoming_call_notification", i1);
        label38: if (!areAllDrivingModeOptionsDisabled(localContentResolver1))
          break label312;
        this.mActionBarSwitch.setChecked(false);
      }
    while (true)
    {
      return super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
      i1 = 0;
      break;
      if (paramPreference.equals(this.mMessageNotification))
      {
        if (this.mMessageNotification.isChecked());
        for (int n = 1; ; n = 0)
        {
          Settings.System.putInt(localContentResolver1, "driving_mode_message_notification", n);
          break;
        }
      }
      if (paramPreference.equals(this.mVoiceMailNotification))
      {
        if (this.mVoiceMailNotification.isChecked());
        for (int m = 1; ; m = 0)
        {
          Settings.System.putInt(localContentResolver1, "driving_mode_voice_mail_notification", m);
          break;
        }
      }
      if (paramPreference.equals(this.mAlarmNotification))
      {
        if (this.mAlarmNotification.isChecked());
        for (int k = 1; ; k = 0)
        {
          Settings.System.putInt(localContentResolver1, "driving_mode_alarm_notification", k);
          break;
        }
      }
      if (paramPreference.equals(this.mScheduleNotification))
      {
        if (this.mScheduleNotification.isChecked());
        for (int j = 1; ; j = 0)
        {
          Settings.System.putInt(localContentResolver1, "driving_mode_schedule_notification", j);
          break;
        }
      }
      if (paramPreference.equals(this.mChatonV))
      {
        ContentResolver localContentResolver2 = getContentResolver();
        if (this.mChatonV.isChecked());
        for (int i = 1; ; i = 0)
        {
          Settings.System.putInt(localContentResolver2, "driving_mode_chaton_call_notification", i);
          break;
        }
      }
      if (!paramPreference.equals(this.mMyPlace))
        break label38;
      startActivity(new Intent(this, MyPlaceActivity.class));
      break label38;
      label312: this.mActionBarSwitch.setEnabled(true);
    }
  }

  public void onResume()
  {
    int i = 1;
    super.onResume();
    updateState();
    if (this.mActionBarLayout != null)
      this.mActionBarLayout.setVisibility(0);
    label90: label126: CheckBoxPreference localCheckBoxPreference2;
    if (this.mIncomingCallNotification != null)
    {
      CheckBoxPreference localCheckBoxPreference6 = this.mIncomingCallNotification;
      if (Settings.System.getInt(getContentResolver(), "driving_mode_incoming_call_notification", 0) != 0)
      {
        int i6 = i;
        localCheckBoxPreference6.setChecked(i6);
      }
    }
    else
    {
      if (this.mMessageNotification != null)
      {
        CheckBoxPreference localCheckBoxPreference5 = this.mMessageNotification;
        if (Settings.System.getInt(getContentResolver(), "driving_mode_message_notification", 0) == 0)
          break label267;
        int i4 = i;
        localCheckBoxPreference5.setChecked(i4);
      }
      if (this.mVoiceMailNotification != null)
      {
        CheckBoxPreference localCheckBoxPreference4 = this.mVoiceMailNotification;
        if (Settings.System.getInt(getContentResolver(), "driving_mode_voice_mail_notification", 0) == 0)
          break label273;
        int i2 = i;
        localCheckBoxPreference4.setChecked(i2);
      }
      if (this.mAlarmNotification != null)
      {
        CheckBoxPreference localCheckBoxPreference3 = this.mAlarmNotification;
        if (Settings.System.getInt(getContentResolver(), "driving_mode_alarm_notification", 0) == 0)
          break label279;
        int n = i;
        label162: localCheckBoxPreference3.setChecked(n);
      }
      CheckBoxPreference localCheckBoxPreference1 = this.mScheduleNotification;
      if (Settings.System.getInt(getContentResolver(), "driving_mode_schedule_notification", 0) == 0)
        break label285;
      int k = i;
      label189: localCheckBoxPreference1.setChecked(k);
      if (this.mChatonV != null)
      {
        localCheckBoxPreference2 = this.mChatonV;
        if (Settings.System.getInt(getContentResolver(), "driving_mode_chaton_call_notification", i) == 0)
          break label290;
      }
    }
    while (true)
    {
      localCheckBoxPreference2.setChecked(i);
      if (sSettingsObserver == null)
      {
        sSettingsObserver = new SettingsObserver(new Handler(), getApplicationContext());
        sSettingsObserver.startObserving();
      }
      return;
      int i7 = 0;
      break;
      label267: int i5 = 0;
      break label90;
      label273: int i3 = 0;
      break label126;
      label279: int i1 = 0;
      break label162;
      label285: int m = 0;
      break label189;
      label290: int j = 0;
    }
  }

  public void onStop()
  {
    super.onStop();
    if (this.mActionBarLayout != null)
      this.mActionBarLayout.setVisibility(4);
  }

  private class SettingsObserver extends ContentObserver
  {
    private Context mContext;

    SettingsObserver(Handler paramContext, Context arg3)
    {
      super();
      Object localObject;
      this.mContext = localObject;
    }

    public void onChange(boolean paramBoolean)
    {
      DrivingModeSettings.this.updateState();
    }

    void startObserving()
    {
      this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("driving_mode_on"), false, this);
    }

    void stopObserving()
    {
      this.mContext.getContentResolver().unregisterContentObserver(this);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.DrivingModeSettings
 * JD-Core Version:    0.6.0
 */