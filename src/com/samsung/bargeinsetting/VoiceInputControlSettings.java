package com.samsung.bargeinsetting;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.CorePackageInfoProvider;
import com.vlingo.midas.ClientConfiguration;
import com.vlingo.midas.settings.VLPreferenceActivity;
import com.vlingo.midas.ui.PackageInfoProvider;

public class VoiceInputControlSettings extends VLPreferenceActivity
{
  private static final String VOICEINPUTCONTROL_ALARM = "voice_input_control_alarm";
  private static final String VOICEINPUTCONTROL_CAMERA = "voice_input_control_camera";
  private static final String VOICEINPUTCONTROL_CHATONV = "voice_input_control_chatonv";
  private static final String VOICEINPUTCONTROL_INCOMMING_CALL = "voice_input_control_incomming_calls";
  private static final String VOICEINPUTCONTROL_MUSIC = "voice_input_control_music";
  private static final String VOICEINPUTCONTROL_RADIO = "voice_input_control_radio";
  private static int mTheme = 2131296526;
  private final String KEY_EASY_MODE_SWITCH = "easy_mode_switch";
  private final String KEY_VOICE_INPUT_CONTROL = "voice_input_control";
  private Switch actionBarSwitch;
  private CheckBoxPreference mAlarm;
  private CheckBoxPreference mCamera;
  private CheckBoxPreference mChatonV;
  private float mDensity = 0.0F;
  private CheckBoxPreference mIncommingCalls;
  private CheckBoxPreference mMusic;
  private float mOldLX = 0.0F;
  private float mOldLY = 0.0F;
  private float mOldPX = 0.0F;
  private float mOldPY = 0.0F;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  private PackageInfoProvider mPackageInfo;
  private CheckBoxPreference mRadio;
  private VoiceInputControlEnabler mVoiceInputControlEnabler;
  private ContentObserver mVoiceInputControlObserver = new VoiceInputControlSettings.1(this, new Handler());
  private int my_orientation;

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

  private boolean isDialogMode()
  {
    if (mTheme != 2131296526);
    for (int i = 1; ; i = 0)
      return i;
  }

  private int isEasyModeOn()
  {
    return Settings.System.getInt(getContentResolver(), "easy_mode_switch", 1);
  }

  private void setPreferenceStatusBasedOnEasyMode(CheckBoxPreference paramCheckBoxPreference)
  {
    if (this.mCamera == paramCheckBoxPreference)
    {
      if (isEasyModeOn() != 0)
        break label21;
      paramCheckBoxPreference.setEnabled(false);
    }
    while (true)
    {
      return;
      label21: paramCheckBoxPreference.setEnabled(true);
    }
  }

  private void updateUIVoiceInputControl()
  {
    if (Settings.System.getInt(getContentResolver(), "voice_input_control", 0) == 1)
    {
      this.mIncommingCalls.setEnabled(true);
      this.mChatonV.setEnabled(true);
      this.mAlarm.setEnabled(true);
      setPreferenceStatusBasedOnEasyMode(this.mCamera);
      this.mMusic.setEnabled(true);
      this.mRadio.setEnabled(true);
    }
    while (true)
    {
      return;
      this.mIncommingCalls.setEnabled(false);
      this.mChatonV.setEnabled(false);
      this.mAlarm.setEnabled(false);
      this.mCamera.setEnabled(false);
      this.mMusic.setEnabled(false);
      this.mRadio.setEnabled(false);
    }
  }

  public boolean isAllOptionDisabled()
  {
    int i = 0;
    int j = Settings.System.getInt(getContentResolver(), "voice_input_control_incomming_calls", 0);
    int k = Settings.System.getInt(getContentResolver(), "voice_input_control_alarm", 0);
    int m = Settings.System.getInt(getContentResolver(), "voice_input_control_camera", 0);
    int n = Settings.System.getInt(getContentResolver(), "voice_input_control_music", 0);
    int i1 = Settings.System.getInt(getContentResolver(), "voice_input_control_radio", 0);
    int i2 = Settings.System.getInt(getContentResolver(), "voice_input_control_chatonv", 0);
    if ((j == 0) && (k == 0) && (m == 0) && (n == 0) && (i1 == 0) && (i2 == 0))
      i = 1;
    return i;
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    ContentResolver localContentResolver = getContentResolver();
    int i;
    if (paramBoolean)
    {
      i = 1;
      Settings.System.putInt(localContentResolver, "voice_input_control", i);
      updateUIVoiceInputControl();
      if (paramBoolean)
        break label42;
      Settings.setInt("temp_input_voice_control", 1);
    }
    while (true)
    {
      return;
      i = 0;
      break;
      label42: Settings.setInt("temp_input_voice_control", 0);
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (paramConfiguration != null)
      super.onConfigurationChanged(paramConfiguration);
    WindowManager.LayoutParams localLayoutParams;
    if (isDialogMode())
    {
      this.my_orientation = getResources().getConfiguration().orientation;
      if (paramConfiguration != null)
      {
        localLayoutParams = getWindow().getAttributes();
        Point localPoint = new Point();
        getWindowManager().getDefaultDisplay().getSize(localPoint);
        if (this.my_orientation != 2)
          break label101;
        localLayoutParams.x = (int)this.mOldLX;
      }
    }
    for (localLayoutParams.y = (int)this.mOldLY; ; localLayoutParams.y = (int)this.mOldPY)
    {
      getWindow().setAttributes(localLayoutParams);
      System.gc();
      return;
      label101: localLayoutParams.x = (int)this.mOldPX;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PackageInfoProvider localPackageInfoProvider = new PackageInfoProvider(this);
    this.mPackageInfo = localPackageInfoProvider;
    DisplayMetrics localDisplayMetrics1 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics1);
    this.mDensity = localDisplayMetrics1.density;
    DisplayMetrics localDisplayMetrics2;
    Point localPoint;
    float f1;
    float f2;
    if (((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2032) && (localDisplayMetrics1.xdpi == 149.82489F) && (localDisplayMetrics1.ydpi == 150.51852F)) || ((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2080) && (localDisplayMetrics1.xdpi == 149.82401F) && (localDisplayMetrics1.ydpi == 150.51801F)))
    {
      mTheme = 2131296529;
      setTheme(mTheme);
      if (!isDialogMode())
        break label891;
      getWindow().clearFlags(2);
      this.my_orientation = getResources().getConfiguration().orientation;
      getWindow().addFlags(17170976);
      addPreferencesFromResource(2131034119);
      getWindow().setBackgroundDrawableResource(2130837952);
      ActionBar localActionBar = getActionBar();
      localActionBar.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
      localActionBar.setDisplayUseLogoEnabled(false);
      localActionBar.setDisplayOptions(0x10 ^ localActionBar.getDisplayOptions(), 16);
      localActionBar.setDisplayShowHomeEnabled(false);
      View localView = localActionBar.getCustomView();
      VoiceInputControlSettings.2 local2 = new VoiceInputControlSettings.2(this);
      localView.setOnTouchListener(local2);
      ImageButton localImageButton = (ImageButton)findViewById(2131558546);
      if (localImageButton != null)
      {
        VoiceInputControlSettings.3 local3 = new VoiceInputControlSettings.3(this);
        localImageButton.setOnClickListener(local3);
      }
      ((ImageView)findViewById(2131558544)).setVisibility(0);
      this.actionBarSwitch = ((Switch)findViewById(2131558545));
      this.actionBarSwitch.setVisibility(0);
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      localLayoutParams.gravity = 85;
      localLayoutParams.type = 2006;
      localDisplayMetrics2 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
      localPoint = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint);
      if ((getWindowManager().getDefaultDisplay().getRotation() != 1) && (getWindowManager().getDefaultDisplay().getRotation() != 3))
        break label836;
      f1 = 436.0F * localDisplayMetrics2.density;
      f2 = 704.0F * localDisplayMetrics2.density;
      this.mOldLX = ((localPoint.y - f1) / 2.0F);
      this.mOldPX = ((localPoint.x - f1) / 2.0F);
      label519: localLayoutParams.height = (int)f2;
      localLayoutParams.width = (int)f1;
      localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
      localLayoutParams.y = 0;
      getWindow().setAttributes(localLayoutParams);
      findViewById(16908298).setBackgroundColor(-16777216);
      label581: if ((getWindowManager().getDefaultDisplay().getRotation() == 1) || (getWindowManager().getDefaultDisplay().getRotation() == 3))
        onConfigurationChanged(null);
      if (ClientConfiguration.isChatONVPhone())
        break label997;
    }
    label836: label997: for (int j = 1; ; j = 0)
    {
      VoiceInputControlEnabler localVoiceInputControlEnabler = new VoiceInputControlEnabler(this, this.actionBarSwitch);
      this.mVoiceInputControlEnabler = localVoiceInputControlEnabler;
      this.mIncommingCalls = ((CheckBoxPreference)findPreference("voice_input_control_incomming_calls"));
      this.mChatonV = ((CheckBoxPreference)findPreference("voice_input_control_chatonv"));
      this.mAlarm = ((CheckBoxPreference)findPreference("voice_input_control_alarm"));
      this.mCamera = ((CheckBoxPreference)findPreference("voice_input_control_camera"));
      this.mMusic = ((CheckBoxPreference)findPreference("voice_input_control_music"));
      this.mRadio = ((CheckBoxPreference)findPreference("voice_input_control_radio"));
      if (!CorePackageInfoProvider.hasDialing())
      {
        getPreferenceScreen().removePreference(this.mIncommingCalls);
        Settings.System.putInt(getContentResolver(), "voice_input_control_incomming_calls", 0);
      }
      if ((!PackageInfoProvider.hasRadio()) || (this.mPackageInfo.hasPenFeature()))
      {
        getPreferenceScreen().removePreference(this.mRadio);
        Settings.System.putInt(getContentResolver(), "voice_input_control_radio", 0);
      }
      if (j == 1)
      {
        getPreferenceScreen().removePreference(this.mChatonV);
        Settings.System.putInt(getContentResolver(), "voice_input_control_chatonv", 0);
      }
      return;
      mTheme = 2131296526;
      break;
      f1 = 436.0F * localDisplayMetrics2.density;
      f2 = 704.0F * localDisplayMetrics2.density;
      this.mOldPX = ((localPoint.y - f1) / 2.0F);
      this.mOldLX = ((localPoint.x - f1) / 2.0F);
      break label519;
      label891: addPreferencesFromResource(2131034119);
      getActionBar().setDisplayOptions(14);
      Switch localSwitch = new Switch(this);
      this.actionBarSwitch = localSwitch;
      int i = getResources().getDimensionPixelSize(2131427336);
      this.actionBarSwitch.setPadding(0, 0, i, 0);
      getActionBar().setDisplayOptions(30);
      getActionBar().setCustomView(this.actionBarSwitch, new ActionBar.LayoutParams(-2, -2, 21));
      getActionBar().setTitle(getString(2131362584));
      break label581;
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
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
    super.onPause();
    int i = Settings.System.getInt(getContentResolver(), "voice_input_control", 0);
    if ((isAllOptionDisabled()) && (1 == i))
      this.actionBarSwitch.setChecked(false);
    this.mVoiceInputControlEnabler.pause();
    getContentResolver().unregisterContentObserver(this.mVoiceInputControlObserver);
  }

  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    int i1;
    if (paramPreference.equals(this.mIncommingCalls))
    {
      ContentResolver localContentResolver6 = getContentResolver();
      if (this.mIncommingCalls.isChecked())
      {
        i1 = 1;
        Settings.System.putInt(localContentResolver6, "voice_input_control_incomming_calls", i1);
        label40: if ((!isAllOptionDisabled()) || ((!paramPreference.equals(this.mIncommingCalls)) && (!paramPreference.equals(this.mAlarm)) && (!paramPreference.equals(this.mCamera)) && (!paramPreference.equals(this.mMusic)) && (!paramPreference.equals(this.mRadio)) && (!paramPreference.equals(this.mChatonV))))
          break label377;
        this.actionBarSwitch.setChecked(false);
      }
    }
    while (true)
    {
      return super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
      i1 = 0;
      break;
      if (paramPreference.equals(this.mChatonV))
      {
        ContentResolver localContentResolver5 = getContentResolver();
        if (this.mChatonV.isChecked());
        for (int n = 1; ; n = 0)
        {
          Settings.System.putInt(localContentResolver5, "voice_input_control_chatonv", n);
          break;
        }
      }
      if (paramPreference.equals(this.mAlarm))
      {
        ContentResolver localContentResolver4 = getContentResolver();
        if (this.mAlarm.isChecked());
        for (int m = 1; ; m = 0)
        {
          Settings.System.putInt(localContentResolver4, "voice_input_control_alarm", m);
          break;
        }
      }
      if (paramPreference.equals(this.mCamera))
      {
        ContentResolver localContentResolver3 = getContentResolver();
        if (this.mCamera.isChecked());
        for (int k = 1; ; k = 0)
        {
          Settings.System.putInt(localContentResolver3, "voice_input_control_camera", k);
          break;
        }
      }
      if (paramPreference.equals(this.mMusic))
      {
        ContentResolver localContentResolver2 = getContentResolver();
        if (this.mMusic.isChecked());
        for (int j = 1; ; j = 0)
        {
          Settings.System.putInt(localContentResolver2, "voice_input_control_music", j);
          break;
        }
      }
      if (!paramPreference.equals(this.mRadio))
        break label40;
      ContentResolver localContentResolver1 = getContentResolver();
      if (this.mRadio.isChecked());
      for (int i = 1; ; i = 0)
      {
        Settings.System.putInt(localContentResolver1, "voice_input_control_radio", i);
        break;
      }
      label377: this.actionBarSwitch.setEnabled(true);
    }
  }

  public void onResume()
  {
    int i = 1;
    super.onResume();
    updateUIVoiceInputControl();
    this.mVoiceInputControlEnabler.resume();
    getContentResolver().registerContentObserver(Settings.System.getUriFor("voice_input_control"), i, this.mVoiceInputControlObserver);
    label99: CheckBoxPreference localCheckBoxPreference1;
    if (this.mIncommingCalls != null)
    {
      CheckBoxPreference localCheckBoxPreference6 = this.mIncommingCalls;
      if (Settings.System.getInt(getContentResolver(), "voice_input_control_incomming_calls", i) != 0)
      {
        int i6 = i;
        localCheckBoxPreference6.setChecked(i6);
      }
    }
    else
    {
      if (this.mChatonV != null)
      {
        CheckBoxPreference localCheckBoxPreference5 = this.mChatonV;
        if (Settings.System.getInt(getContentResolver(), "voice_input_control_chatonv", i) == 0)
          break label249;
        int i4 = i;
        localCheckBoxPreference5.setChecked(i4);
      }
      if (this.mAlarm != null)
      {
        CheckBoxPreference localCheckBoxPreference4 = this.mAlarm;
        if (Settings.System.getInt(getContentResolver(), "voice_input_control_alarm", i) == 0)
          break label255;
        int i2 = i;
        label135: localCheckBoxPreference4.setChecked(i2);
      }
      if (this.mCamera != null)
      {
        CheckBoxPreference localCheckBoxPreference3 = this.mCamera;
        if (Settings.System.getInt(getContentResolver(), "voice_input_control_camera", i) == 0)
          break label261;
        int n = i;
        label171: localCheckBoxPreference3.setChecked(n);
      }
      if (this.mMusic != null)
      {
        CheckBoxPreference localCheckBoxPreference2 = this.mMusic;
        if (Settings.System.getInt(getContentResolver(), "voice_input_control_music", i) == 0)
          break label267;
        int k = i;
        label206: localCheckBoxPreference2.setChecked(k);
      }
      if (this.mRadio != null)
      {
        localCheckBoxPreference1 = this.mRadio;
        if (Settings.System.getInt(getContentResolver(), "voice_input_control_radio", i) == 0)
          break label273;
      }
    }
    while (true)
    {
      localCheckBoxPreference1.setChecked(i);
      return;
      int i7 = 0;
      break;
      label249: int i5 = 0;
      break label99;
      label255: int i3 = 0;
      break label135;
      label261: int i1 = 0;
      break label171;
      label267: int m = 0;
      break label206;
      label273: int j = 0;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.bargeinsetting.VoiceInputControlSettings
 * JD-Core Version:    0.6.0
 */