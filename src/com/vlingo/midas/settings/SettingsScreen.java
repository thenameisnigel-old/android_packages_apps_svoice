package com.vlingo.midas.settings;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings.System;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.samsung.bargeinsetting.VoiceInputControlSettings;
import com.samsung.wakeupsetting.CustomWakeupSettingActivity;
import com.sec.android.app.IWSpeechRecognizer.BargeInRecognizer;
import com.sec.android.app.sns3.svc.sp.FacebookSSOAPI;
import com.sec.android.app.sns3.svc.sp.FacebookSSOAPI.FacebookSSOCallback;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.lmtt.LMTTService;
import com.vlingo.core.internal.phrasespotter.PhraseSpotter;
import com.vlingo.core.internal.phrasespotter.PhraseSpotterParameters;
import com.vlingo.core.internal.safereader.SafeReaderProxy;
import com.vlingo.core.internal.settings.DynamicFeatureConfig;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.ClientConfiguration;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.drivingmode.DrivingModeUtil;
import com.vlingo.midas.gui.homewidget.InCarWidget;
import com.vlingo.midas.help.AboutScreen;
import com.vlingo.midas.help.HelpScreen;
import com.vlingo.midas.iux.IUXManager;
import com.vlingo.midas.phrasespotter.PhraseSpotterUtil;
import com.vlingo.midas.social.SocialAccountActivity;
import com.vlingo.midas.ui.PackageInfoProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsScreen extends VLPreferenceActivity
  implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener, FacebookSSOAPI.FacebookSSOCallback
{
  public static final String ACTION_SHOW_SOCIAL_SETTING = "com.vlingo.client.settings.SOCIAL_SETTING";
  public static final String ACTION_SHOW_VOICE_TALK_SETTING = "com.vlingo.client.settings.VOICE_TALK_SETTINGS";
  public static final String DRIVING_MODE_ALARM_NOTIFICATION = "driving_mode_alarm_notification";
  private static final String DRIVING_MODE_CHATON_CALL_NOTIFICATION = "driving_mode_chaton_call_notification";
  public static final String DRIVING_MODE_INCOMING_CALL_NOTIFICATION = "driving_mode_incoming_call_notification";
  public static final String DRIVING_MODE_MESSAGE_NOTIFICATION = "driving_mode_message_notification";
  public static final String DRIVING_MODE_SCHEDULE_NOTIFICATION = "driving_mode_schedule_notification";
  public static final String EXTRA_SHOW_CHILD_SCREEN = "child_screen";
  private static final int JB_API_NUM = 16;
  private static final String KEY_DRIVING_MODE = "driving_mode";
  private static final String KEY_VOICE_INPUT_CONTROL = "voice_input_control";
  private static final String KEY_VOICE_INPUT_CONTROL_CATEGORY = "voice_input_control_category";
  public static final String LANGUAGE_CHANGED = "com.vlingo.LANGUAGE_CHANGED";
  private static final int MSG_UPDATE_SOCIAL_UI = 0;
  private static final int SET_WAKEUP_DIALOG = 1;
  private static final String VOICEINPUTCONTROL_ALARM = "voice_input_control_alarm";
  private static final String VOICEINPUTCONTROL_CAMERA = "voice_input_control_camera";
  private static final String VOICEINPUTCONTROL_CHATONV = "voice_input_control_chatonv";
  private static final String VOICEINPUTCONTROL_INCOMMING_CALL = "voice_input_control_incomming_calls";
  private static final String VOICEINPUTCONTROL_MUSIC = "voice_input_control_music";
  private static final String VOICEINPUTCONTROL_RADIO = "voice_input_control_radio";
  private static Pattern alphaPattern = Pattern.compile(".*\\p{Alnum}.*", 32);
  public static boolean isLaunchBySettings;
  private static int mTheme = 2131296527;
  private static HashMap<String, String> unsupportedLocales;
  private boolean appLangChanged = false;
  private String bargeInSoFilePath = "/system/lib/libsasr-jni.so";
  private boolean customTitleSupported = false;
  DrivingModeContentObserver drivingModeContentObserver = null;
  private final boolean greyDisabled = false;
  private boolean homePromptAutoReset = false;
  private AlertDialog mAutoHapticDialog = null;
  private boolean mAutoHapticNoPopup;
  private BargeInRecognizer mBargeInRecognizer;
  private float mDensity = 0.0F;
  private SwitchPreference mDriveMode;
  private float mOldLX = 0.0F;
  private float mOldLY = 0.0F;
  private float mOldPX = 0.0F;
  private float mOldPY = 0.0F;
  private float mOldX = 0.0F;
  private float mOldY = 0.0F;
  private PackageInfoProvider mPackageInfo;
  private SwitchPreference mVoiceInputControl;
  private CheckBoxPreference m_EmailReadback;
  private CheckBoxPreference m_LocationOn;
  private CheckBoxPreference m_ProfanityFilter;
  private CheckBoxPreference m_SMSReadback;
  private CheckBoxPreference m_SafeReaderReadMessageBody;
  private CheckBoxPreference m_ShakeToSkip;
  private CheckBoxPreference m_TTSSetting;
  private Preference m_about;
  private boolean m_alreadyTTSedWakeupWord = false;
  private ListPreference m_autoDial;
  private CheckBoxPreference m_autoPunctuation;
  private CheckBoxPreference m_auto_start_speaker;
  private EditTextPreference m_carModeHomeAddress;
  private EditTextPreference m_carModeOfficeAddress;
  private EditTextPreference m_carModeStartupTtsPrompt;
  private EditTextPreference m_carModeTtsPrompt;
  private ImagePreference m_facebook;
  private ImagePreference m_foursquare;
  private CheckBoxPreference m_headsetMode;
  private ListPreference m_languageApplication;
  private CheckBoxPreference m_launchOnCarMode;
  private CheckBoxPreference m_launchVoicetalk;
  private CheckBoxPreference m_listenOverBluetooth;
  private CheckBoxPreference m_motionSettings;
  private ListPreference m_safereaderEmailPollInterval;
  private CheckBoxPreference m_sayWakeUpMode;
  private ImagePreference m_twitter;
  private CheckBoxPreference m_useNetworkTTS;
  private Preference m_uuid;
  private Preference m_voiceCommandHelp;
  private Preference m_voiceTalkHelp;
  private CheckBoxPreference m_wakeUpWord;
  private ListPreference m_wake_up_engine;
  private CheckBoxPreference m_wake_up_lock_screen;
  private ListPreference m_webSearchEngine;
  private SettingsScreenHandler mhandler = new SettingsScreenHandler(this);
  private int my_orientation;
  private boolean startupPromptAutoReset = false;

  static
  {
    isLaunchBySettings = false;
  }

  private void checkSystemMotionSettings()
  {
    int i = Settings.System.getInt(getContentResolver(), "motion_engine", 0);
    int j = Settings.System.getInt(getContentResolver(), "motion_double_tap", 0);
    if ((i == 0) || (j == 0))
      this.m_motionSettings.setEnabled(false);
    while (true)
    {
      return;
      this.m_motionSettings.setEnabled(true);
    }
  }

  private boolean containsNoAlphaNumeric(String paramString)
  {
    if (!alphaPattern.matcher(paramString.trim()).matches());
    for (int i = 1; ; i = 0)
      return i;
  }

  private void initializeUnsupportedLocales()
  {
    MidasSettings.initializeUnsupportedLocales();
  }

  private boolean isBargeInAvailable()
  {
    if (!new File(this.bargeInSoFilePath).exists());
    for (int i = 0; ; i = 1)
      return i;
  }

  private boolean isDialogMode()
  {
    if (mTheme != 2131296527);
    for (int i = 1; ; i = 0)
      return i;
  }

  private void launchSetWakeupDialog()
  {
    showDialog(1);
  }

  private static boolean phoneSupportsLanguage(String paramString)
  {
    return MidasSettings.phoneSupportsLanguage(paramString);
  }

  private static boolean phoneSupportsVlingoSpecificLanguage(String paramString)
  {
    return MidasSettings.phoneSupportsVlingoSpecificLanguage(paramString);
  }

  private void saveString(String paramString1, String paramString2)
  {
    try
    {
      FileOutputStream localFileOutputStream = getApplicationContext().openFileOutput(paramString1, 1);
      localFileOutputStream.write(paramString2.getBytes());
      localFileOutputStream.close();
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  private void updateDrivingModeControl(int paramInt)
  {
    Settings.System.putInt(getContentResolver(), "driving_mode_on", paramInt);
  }

  private void updateState()
  {
    boolean bool1 = true;
    boolean bool2;
    SwitchPreference localSwitchPreference;
    if (Settings.System.getInt(getContentResolver(), "voice_input_control", 0) != 0)
    {
      bool2 = bool1;
      this.mAutoHapticNoPopup = bool2;
      if ((this.mAutoHapticNoPopup == bool1) && (isAllOptionDisabled()))
      {
        this.mAutoHapticNoPopup = false;
        updateVoiceInputControl(0);
      }
      if (isAllDrivingModeOptionsDisabled())
        updateDrivingModeControl(0);
      this.mVoiceInputControl.setChecked(this.mAutoHapticNoPopup);
      localSwitchPreference = this.mDriveMode;
      if (Settings.System.getInt(getContentResolver(), "driving_mode_on", 0) == 0)
        break label100;
    }
    while (true)
    {
      localSwitchPreference.setChecked(bool1);
      return;
      bool2 = false;
      break;
      label100: bool1 = false;
    }
  }

  private void updateVoiceInputControl(int paramInt)
  {
    Settings.System.putInt(getContentResolver(), "voice_input_control", paramInt);
  }

  public boolean isAllDrivingModeOptionsDisabled()
  {
    int i = 0;
    int j = Settings.System.getInt(getContentResolver(), "driving_mode_incoming_call_notification", 0);
    int k = Settings.System.getInt(getContentResolver(), "driving_mode_message_notification", 0);
    int m = Settings.System.getInt(getContentResolver(), "driving_mode_alarm_notification", 0);
    int n = Settings.System.getInt(getContentResolver(), "driving_mode_schedule_notification", 0);
    int i1 = Settings.System.getInt(getContentResolver(), "driving_mode_chaton_call_notification", 0);
    if ((j == 0) && (k == 0) && (m == 0) && (n == 0) && (i1 == 0))
      i = 1;
    return i;
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

  public void onChangeAccountInfo()
  {
    this.mhandler.sendEmptyMessage(0);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    MidasSettings.updateCurrentLocale();
    this.my_orientation = getResources().getConfiguration().orientation;
    WindowManager.LayoutParams localLayoutParams;
    if ((isDialogMode()) && (paramConfiguration != null))
    {
      localLayoutParams = getWindow().getAttributes();
      Point localPoint = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint);
      if (this.my_orientation != 2)
        break label100;
      localLayoutParams.x = (int)this.mOldLX;
    }
    for (localLayoutParams.y = (int)this.mOldLY; ; localLayoutParams.y = (int)this.mOldPY)
    {
      getWindow().setAttributes(localLayoutParams);
      System.gc();
      return;
      label100: localLayoutParams.x = (int)this.mOldPX;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    DisplayMetrics localDisplayMetrics1 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics1);
    this.mDensity = localDisplayMetrics1.density;
    int i;
    int j;
    DisplayMetrics localDisplayMetrics2;
    Point localPoint;
    float f1;
    float f2;
    label554: String str1;
    if (((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2032) && (localDisplayMetrics1.xdpi == 149.82489F) && (localDisplayMetrics1.ydpi == 150.51852F)) || ((localDisplayMetrics1.density == 1.0D) && (localDisplayMetrics1.heightPixels + localDisplayMetrics1.widthPixels == 2080) && (localDisplayMetrics1.xdpi == 149.82401F) && (localDisplayMetrics1.ydpi == 150.51801F)))
    {
      mTheme = 2131296529;
      setTheme(mTheme);
      this.customTitleSupported = requestWindowFeature(8);
      super.onCreate(paramBundle);
      this.my_orientation = getResources().getConfiguration().orientation;
      i = getResources().getDisplayMetrics().widthPixels;
      j = getResources().getDisplayMetrics().heightPixels;
      Settings.setBoolean("is_start_option_menu", getIntent().getBooleanExtra("is_start_option_menu", false));
      setVolumeControlStream(3);
      if (!isDialogMode())
        break label1183;
      getWindow().clearFlags(2);
      getWindow().addFlags(17170976);
      addPreferencesFromResource(2131034118);
      getWindow().setBackgroundDrawableResource(2130837952);
      ActionBar localActionBar2 = getActionBar();
      if (localActionBar2 != null)
      {
        localActionBar2.setCustomView(getLayoutInflater().inflate(2130903048, null), new ActionBar.LayoutParams(-1, 40));
        localActionBar2.setDisplayUseLogoEnabled(false);
        localActionBar2.setDisplayOptions(0x10 ^ localActionBar2.getDisplayOptions(), 16);
        TextView localTextView = (TextView)findViewById(2131558542);
        if (localTextView != null)
          localTextView.setText(2131362233);
        localActionBar2.setDisplayShowHomeEnabled(false);
        View localView = localActionBar2.getCustomView();
        1 local1 = new View.OnTouchListener()
        {
          public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
          {
            int i = 1;
            WindowManager.LayoutParams localLayoutParams = SettingsScreen.this.getWindow().getAttributes();
            if (paramMotionEvent.getAction() == 0)
            {
              SettingsScreen.access$202(SettingsScreen.this, paramMotionEvent.getRawX());
              SettingsScreen.access$302(SettingsScreen.this, paramMotionEvent.getRawY());
            }
            while (true)
            {
              return i;
              if (paramMotionEvent.getAction() == 2)
              {
                float f1 = paramMotionEvent.getRawX() - SettingsScreen.this.mOldX;
                float f2 = paramMotionEvent.getRawY() - SettingsScreen.this.mOldY;
                if ((Math.abs(f1) <= 3.0F) && (Math.abs(f2) <= 3.0F))
                  continue;
                localLayoutParams.x -= (int)f1;
                localLayoutParams.y -= (int)f2;
                if (SettingsScreen.this.my_orientation == 2)
                {
                  SettingsScreen.access$502(SettingsScreen.this, localLayoutParams.x);
                  SettingsScreen.access$602(SettingsScreen.this, localLayoutParams.y);
                }
                while (true)
                {
                  SettingsScreen.access$202(SettingsScreen.this, paramMotionEvent.getRawX());
                  SettingsScreen.access$302(SettingsScreen.this, paramMotionEvent.getRawY());
                  SettingsScreen.this.getWindow().setAttributes(localLayoutParams);
                  break;
                  SettingsScreen.access$702(SettingsScreen.this, localLayoutParams.x);
                  SettingsScreen.access$802(SettingsScreen.this, localLayoutParams.y);
                }
              }
              i = 0;
            }
          }
        };
        localView.setOnTouchListener(local1);
      }
      ImageButton localImageButton = (ImageButton)findViewById(2131558546);
      if (localImageButton != null)
      {
        2 local2 = new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            SettingsScreen.this.sendBroadcast(new Intent("com.vlingo.client.iux.cleanup"));
            if (!IUXManager.isIUXComplete())
              IUXManager.setIUXIntroRequired(true);
            SettingsScreen.this.finish();
          }
        };
        localImageButton.setOnClickListener(local2);
      }
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      localLayoutParams.gravity = 85;
      localLayoutParams.type = 2006;
      localDisplayMetrics2 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
      localPoint = new Point();
      getWindowManager().getDefaultDisplay().getSize(localPoint);
      if ((getWindowManager().getDefaultDisplay().getRotation() != 1) && (getWindowManager().getDefaultDisplay().getRotation() != 3))
        break label1128;
      f1 = 436.0F * localDisplayMetrics2.density;
      f2 = 704.0F * localDisplayMetrics2.density;
      this.mOldLX = ((localPoint.y - f1) / 2.0F);
      this.mOldPX = ((localPoint.x - f1) / 2.0F);
      localLayoutParams.height = (int)f2;
      localLayoutParams.width = (int)f1;
      localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
      localLayoutParams.y = 0;
      localLayoutParams.height = (int)f2;
      localLayoutParams.width = (int)f1;
      localLayoutParams.x = ((localPoint.x - localLayoutParams.width) / 2);
      localLayoutParams.y = 0;
      getWindow().setAttributes(localLayoutParams);
      findViewById(16908298).setBackgroundColor(-16777216);
      ((ViewGroup)((ListView)findViewById(16908298)).getParent()).setPadding(0, 0, 0, 0);
      label679: this.mVoiceInputControl = ((SwitchPreference)findPreference("voice_input_control"));
      this.mVoiceInputControl.setOnPreferenceChangeListener(this);
      this.mDriveMode = ((SwitchPreference)findPreference("driving_mode"));
      this.mDriveMode.setOnPreferenceChangeListener(this);
      this.mBargeInRecognizer = new BargeInRecognizer();
      if ((Integer.parseInt(Build.VERSION.SDK) < 16) || (!isBargeInAvailable()))
      {
        getPreferenceScreen().removePreference(findPreference("voice_input_control_category"));
        getPreferenceScreen().removePreference(findPreference("voice_input_control"));
        Settings.System.putInt(getContentResolver(), "voice_input_control", 0);
      }
      if ((!DynamicFeatureConfig.CALL.isEnabled()) || (!PackageInfoProvider.hasDialing()))
        getPreferenceScreen().removePreference(findPreference("call"));
      if ((!DynamicFeatureConfig.MESSAGING.isEnabled()) || (!PackageInfoProvider.hasMessaging()))
        getPreferenceScreen().removePreference(findPreference("message"));
      str1 = getIntent().getAction();
      if (str1 != null)
      {
        if (!str1.equals("com.vlingo.client.settings.SOCIAL_SETTING"))
          break label1231;
        PreferenceScreen localPreferenceScreen3 = (PreferenceScreen)findPreference("social_settings");
        setTitle(localPreferenceScreen3.getTitle());
        setPreferenceScreen(localPreferenceScreen3);
      }
    }
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    while (true)
    {
      String str2 = getIntent().getStringExtra("child_screen");
      if ((str2 != null) && (str2.length() > 0))
      {
        PreferenceScreen localPreferenceScreen1 = (PreferenceScreen)findPreference(str2);
        setTitle(localPreferenceScreen1.getTitle());
        setPreferenceScreen(localPreferenceScreen1);
      }
      this.m_auto_start_speaker = ((CheckBoxPreference)findPreference("car_auto_start_speakerphone"));
      if (this.m_auto_start_speaker != null)
        this.m_auto_start_speaker.setChecked(Settings.getBoolean("car_auto_start_speakerphone", true));
      this.m_languageApplication = ((ListPreference)findPreference("language"));
      PackageInfoProvider localPackageInfoProvider = new PackageInfoProvider(this);
      this.mPackageInfo = localPackageInfoProvider;
      initializeUnsupportedLocales();
      CharSequence[] arrayOfCharSequence1 = Settings.getSupportedLanguages();
      CharSequence[] arrayOfCharSequence2 = Settings.getSupportedLanguageDescriptions();
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      boolean bool = Settings.getBoolean("show_all_languages", false);
      for (int k = 0; k < arrayOfCharSequence1.length; k++)
      {
        if ((!phoneSupportsLanguage(arrayOfCharSequence1[k].toString())) && (!bool))
          continue;
        localArrayList1.add(arrayOfCharSequence1[k].toString());
        localArrayList2.add(arrayOfCharSequence2[k].toString());
      }
      mTheme = 2131296527;
      break;
      label1128: f1 = 436.0F * localDisplayMetrics2.density;
      f2 = 704.0F * localDisplayMetrics2.density;
      this.mOldPX = ((localPoint.y - f1) / 2.0F);
      this.mOldLX = ((localPoint.x - f1) / 2.0F);
      break label554;
      label1183: addPreferencesFromResource(2131034118);
      ActionBar localActionBar1 = getActionBar();
      localActionBar1.setIcon(2130837974);
      if (localActionBar1 == null)
        break label679;
      localActionBar1.setDisplayOptions(14);
      localActionBar1.setTitle(getString(2131362233));
      break label679;
      label1231: if (!str1.equals("com.vlingo.client.settings.VOICE_TALK_SETTINGS"))
        continue;
      PreferenceScreen localPreferenceScreen2 = (PreferenceScreen)findPreference("vlingo_car_settings");
      setTitle(localPreferenceScreen2.getTitle());
      setPreferenceScreen(localPreferenceScreen2);
    }
    this.m_languageApplication.setEntries((CharSequence[])localArrayList2.toArray(new CharSequence[localArrayList2.size()]));
    this.m_languageApplication.setEntryValues((CharSequence[])localArrayList1.toArray(new CharSequence[localArrayList1.size()]));
    this.m_languageApplication.setWidgetLayoutResource(2130903117);
    this.m_launchVoicetalk = ((CheckBoxPreference)findPreference("launch_voicetalk"));
    if (Settings.System.getInt(getContentResolver(), "double_tab_launch", -1) == 1)
      this.m_launchVoicetalk.setChecked(true);
    while (true)
    {
      if (!MidasSettings.isHomeKeyEnabled())
        ((PreferenceCategory)findPreference("general_settings")).removePreference(findPreference("launch_voicetalk"));
      this.m_webSearchEngine = ((ListPreference)findPreference("web_search_engine"));
      this.m_autoDial = ((ListPreference)findPreference("auto_dial"));
      if (this.m_auto_start_speaker != null)
        this.m_autoDial.setWidgetLayoutResource(2130903117);
      this.m_safereaderEmailPollInterval = ((ListPreference)findPreference("safereader_email_poll_interval"));
      this.m_ShakeToSkip = ((CheckBoxPreference)findPreference("shake_to_skip"));
      this.m_carModeStartupTtsPrompt = ((EditTextPreference)findPreference("tts_carmode_startup_prompt"));
      this.m_carModeHomeAddress = ((EditTextPreference)findPreference("car_nav_home_address"));
      this.m_carModeOfficeAddress = ((EditTextPreference)findPreference("car_nav_office_address"));
      EditText localEditText1 = this.m_carModeHomeAddress.getEditText();
      InputFilter[] arrayOfInputFilter1 = new InputFilter[1];
      arrayOfInputFilter1[0] = new InputFilter.LengthFilter(300);
      localEditText1.setFilters(arrayOfInputFilter1);
      this.m_carModeHomeAddress.setWidgetLayoutResource(2130903117);
      EditText localEditText2 = this.m_carModeOfficeAddress.getEditText();
      InputFilter[] arrayOfInputFilter2 = new InputFilter[1];
      arrayOfInputFilter2[0] = new InputFilter.LengthFilter(300);
      localEditText2.setFilters(arrayOfInputFilter2);
      this.m_carModeOfficeAddress.setWidgetLayoutResource(2130903117);
      EditTextPreference localEditTextPreference1 = this.m_carModeHomeAddress;
      3 local3 = new Preference.OnPreferenceClickListener()
      {
        public boolean onPreferenceClick(Preference paramPreference)
        {
          if (SettingsScreen.this.m_carModeHomeAddress.getDialog() != null)
          {
            SettingsScreen.this.m_carModeHomeAddress.getEditText().setOnFocusChangeListener(new SettingsScreen.3.1(this));
            ((Button)SettingsScreen.this.m_carModeHomeAddress.getDialog().findViewById(16908313)).setOnClickListener(new SettingsScreen.3.2(this));
            ((Button)SettingsScreen.this.m_carModeHomeAddress.getDialog().findViewById(16908314)).setOnClickListener(new SettingsScreen.3.3(this));
          }
          return false;
        }
      };
      localEditTextPreference1.setOnPreferenceClickListener(local3);
      EditTextPreference localEditTextPreference2 = this.m_carModeOfficeAddress;
      4 local4 = new Preference.OnPreferenceClickListener()
      {
        public boolean onPreferenceClick(Preference paramPreference)
        {
          if (SettingsScreen.this.m_carModeOfficeAddress.getDialog() != null)
          {
            SettingsScreen.this.m_carModeOfficeAddress.getEditText().setOnFocusChangeListener(new SettingsScreen.4.1(this));
            ((Button)SettingsScreen.this.m_carModeOfficeAddress.getDialog().findViewById(16908313)).setOnClickListener(new SettingsScreen.4.2(this));
            ((Button)SettingsScreen.this.m_carModeOfficeAddress.getDialog().findViewById(16908314)).setOnClickListener(new SettingsScreen.4.3(this));
          }
          return false;
        }
      };
      localEditTextPreference2.setOnPreferenceClickListener(local4);
      this.m_carModeHomeAddress.getEditText().setInputType(16385);
      this.m_carModeOfficeAddress.getEditText().setInputType(16385);
      if (!Settings.getString("language", "en-US").equals("ko-KR"))
        ((PreferenceCategory)findPreference("car_nav_settings")).removePreference(findPreference("car_nav_office_address"));
      this.m_wakeUpWord = ((CheckBoxPreference)findPreference("car_word_spotter_enabled"));
      if (((i == 800) && (j == 1232)) || ((i == 1280) && (j == 752)))
      {
        this.m_wake_up_lock_screen = ((CheckBoxPreference)findPreference("wake_up_lock_screen"));
        this.m_wake_up_lock_screen.setSummary(2131362562);
        this.m_wake_up_lock_screen.setDefaultValue(Boolean.valueOf(false));
        CheckBoxPreference localCheckBoxPreference1 = this.m_wake_up_lock_screen;
        5 local5 = new Preference.OnPreferenceClickListener()
        {
          public boolean onPreferenceClick(Preference paramPreference)
          {
            int i = 1;
            MidasSettings.updateCurrentLocale();
            Boolean localBoolean = Boolean.valueOf(false);
            if ((!SettingsScreen.this.getSharedPreferences("checkBoxPrefForAlert", 0).getBoolean("isCheckBoxChecked", false)) && (!SettingsScreen.this.m_wake_up_lock_screen.isChecked()))
            {
              localBoolean = Boolean.valueOf(SettingsScreen.this.wakeup_lockscreen_dialog(paramPreference));
              return localBoolean.booleanValue();
            }
            label85: ContentResolver localContentResolver;
            if (SettingsScreen.this.m_wake_up_lock_screen.isChecked())
            {
              SettingsScreen.this.m_wake_up_lock_screen.setChecked(false);
              localContentResolver = SettingsScreen.this.getContentResolver();
              if (!SettingsScreen.this.m_wake_up_lock_screen.isChecked())
                break label133;
            }
            while (true)
            {
              Settings.System.putInt(localContentResolver, "wake_up_lock_screen", i);
              break;
              SettingsScreen.this.m_wake_up_lock_screen.setChecked(i);
              break label85;
              label133: int j = 0;
            }
          }
        };
        localCheckBoxPreference1.setOnPreferenceClickListener(local5);
        CheckBoxPreference localCheckBoxPreference2 = this.m_wake_up_lock_screen;
        6 local6 = new Preference.OnPreferenceChangeListener()
        {
          public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
          {
            return false;
          }
        };
        localCheckBoxPreference2.setOnPreferenceChangeListener(local6);
        this.m_useNetworkTTS = ((CheckBoxPreference)findPreference("use_network_tts"));
        this.m_SafeReaderReadMessageBody = ((CheckBoxPreference)findPreference("car_safereader_read_message"));
        this.m_listenOverBluetooth = ((CheckBoxPreference)findPreference("listen_over_bluetooth"));
        this.m_ProfanityFilter = ((CheckBoxPreference)findPreference("profanity_filter"));
        this.m_LocationOn = ((CheckBoxPreference)findPreference("location_enabled"));
        this.m_autoPunctuation = ((CheckBoxPreference)findPreference("auto_punctuation"));
        this.m_headsetMode = ((CheckBoxPreference)findPreference("headset_mode"));
        this.m_sayWakeUpMode = ((CheckBoxPreference)findPreference("say_wake_up_command"));
        this.m_motionSettings = ((CheckBoxPreference)findPreference("car_word_spotter_motion"));
        this.m_voiceTalkHelp = findPreference("help_voice_talk");
        this.m_about = findPreference("help_about");
        this.m_uuid = findPreference("uuid");
        if (this.m_uuid != null)
        {
          this.m_uuid.setSummary(Settings.getUUIDDeviceID());
          Preference localPreference3 = this.m_uuid;
          7 local7 = new Preference.OnPreferenceClickListener()
          {
            public boolean onPreferenceClick(Preference paramPreference)
            {
              ((ClipboardManager)SettingsScreen.this.getSystemService("clipboard")).setText(Settings.getUUIDDeviceID());
              return false;
            }
          };
          localPreference3.setOnPreferenceClickListener(local7);
        }
        if (this.m_voiceTalkHelp != null)
        {
          Preference localPreference2 = this.m_voiceTalkHelp;
          Intent localIntent2 = new Intent(this, HelpScreen.class);
          localPreference2.setIntent(localIntent2);
        }
        if (this.m_about != null)
        {
          Preference localPreference1 = this.m_about;
          Intent localIntent1 = new Intent(this, AboutScreen.class);
          localPreference1.setIntent(localIntent1);
        }
        updateSettingUI();
        updateSocialUI();
        UserLoggingEngine.getInstance().helpPageViewed("settings");
      }
      try
      {
        Process localProcess1 = Runtime.getRuntime().exec("getprop persist.sys.language");
        Process localProcess2 = Runtime.getRuntime().exec("getprop persist.sys.country");
        BufferedReader localBufferedReader1 = new BufferedReader(new InputStreamReader(localProcess1.getInputStream()));
        BufferedReader localBufferedReader2 = new BufferedReader(new InputStreamReader(localProcess2.getInputStream()));
        String str3 = localBufferedReader1.readLine();
        String str4 = localBufferedReader2.readLine();
        new StringBuilder().append(str3).append("_").append(str4).toString();
        localBufferedReader1.close();
        localBufferedReader2.close();
        label2232: return;
        this.m_launchVoicetalk.setChecked(false);
        continue;
        this.m_wake_up_lock_screen = ((CheckBoxPreference)findPreference("wake_up_lock_screen"));
      }
      catch (IOException localIOException)
      {
        break label2232;
      }
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    AlertDialog localAlertDialog = null;
    String str1 = getResources().getString(2131362529);
    String str2 = getResources().getString(2131362527);
    String str3 = getResources().getString(2131362528);
    String str4 = getResources().getString(2131362355);
    View localView = getLayoutInflater().inflate(2130903125, null);
    ((CheckBox)localView.findViewById(2131558514)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
      {
        SharedPreferences.Editor localEditor = SettingsScreen.this.getSharedPreferences("checkBoxPrefForAlert", 0).edit();
        localEditor.putBoolean("isCheckBoxChecked", paramBoolean);
        localEditor.commit();
      }
    });
    switch (paramInt)
    {
    default:
    case 1:
    }
    while (true)
    {
      return localAlertDialog;
      localAlertDialog = new AlertDialog.Builder(this).setTitle(str4).setMessage(str1).setView(localView).setPositiveButton(str2, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          Intent localIntent = new Intent("android.intent.action.VOICE_SETTING_SET_WAKEUP_COMMAND");
          SettingsScreen.this.startActivity(localIntent);
        }
      }).setNegativeButton(str3, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
        }
      }).create();
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 3)
    {
      Intent localIntent = new Intent(ApplicationAdapter.getInstance().getApplicationContext(), VlingoApplicationService.class);
      localIntent.setAction("com.vlingo.client.app.action.CLOSE_APPLICATION");
      startService(localIntent);
    }
    for (boolean bool = true; ; bool = super.onKeyDown(paramInt, paramKeyEvent))
      return bool;
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

  protected void onPause()
  {
    int i = 1;
    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    int j;
    if (this.mVoiceInputControl.isChecked())
    {
      j = i;
      updateVoiceInputControl(j);
      if (!this.mDriveMode.isChecked())
        break label68;
    }
    while (true)
    {
      updateDrivingModeControl(i);
      getContentResolver().unregisterContentObserver(this.drivingModeContentObserver);
      super.onPause();
      return;
      j = 0;
      break;
      label68: i = 0;
    }
  }

  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    int i = 1;
    if (paramPreference == this.mVoiceInputControl)
    {
      boolean bool = ((Boolean)paramObject).booleanValue();
      if (bool)
      {
        int n = Settings.System.getInt(getContentResolver(), "voiceinputcontrol_showNeverAgain", 0);
        Settings.setInt("temp_input_voice_control", i);
        if ((n == 0) && (!this.mAutoHapticNoPopup))
          showGuideDialog();
      }
      if (bool)
      {
        int k = i;
        updateVoiceInputControl(k);
        Settings.setInt("temp_input_voice_control", Settings.System.getInt(getContentResolver(), "voice_input_control", 0));
        this.mAutoHapticNoPopup = false;
      }
    }
    while (true)
    {
      return i;
      int m = 0;
      break;
      if (paramPreference == this.mDriveMode)
      {
        if (((Boolean)paramObject).booleanValue())
        {
          Settings.System.putInt(getContentResolver(), "driving_mode_on", i);
          if (!this.mVoiceInputControl.isChecked())
          {
            Settings.setInt("temp_input_voice_control", 0);
            Settings.System.putInt(getContentResolver(), "voice_input_control", i);
            this.mVoiceInputControl.setChecked(i);
          }
          while (true)
          {
            sendBroadcast(new Intent("DrivingMode"));
            break;
            Settings.setInt("temp_input_voice_control", i);
          }
        }
        if (Settings.getInt("temp_input_voice_control", 0) == 0)
        {
          this.mVoiceInputControl.setChecked(false);
          Settings.System.putInt(getContentResolver(), "voice_input_control", 0);
        }
        while (true)
        {
          Settings.System.putInt(getContentResolver(), "driving_mode_on", 0);
          break;
          this.mVoiceInputControl.setChecked(i);
          Settings.System.putInt(getContentResolver(), "voice_input_control", i);
        }
      }
      int j = 0;
    }
  }

  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    int i = 1;
    boolean bool = super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
    if (paramPreference.getKey().equals("social_settings"))
      ActivityUtil.scheduleOnMainThread(new Runnable()
      {
        public void run()
        {
          SettingsScreen.this.updateSocialUI();
        }
      });
    while (true)
    {
      return bool;
      if (paramPreference.equals(this.m_launchVoicetalk))
      {
        ContentResolver localContentResolver = getContentResolver();
        if (this.m_launchVoicetalk.isChecked());
        while (true)
        {
          Settings.System.putInt(localContentResolver, "double_tab_launch", i);
          break;
          i = 0;
        }
      }
      if ("key_set_wake_up_command".equals(paramPreference.getKey()))
      {
        Intent localIntent = new Intent(getApplicationContext(), CustomWakeupSettingActivity.class);
        localIntent.putExtra("svoice", i);
        startActivity(localIntent);
        continue;
      }
      if (paramPreference.getKey().equals("voice_input_control"))
      {
        startActivity(new Intent(getApplicationContext(), VoiceInputControlSettings.class));
        continue;
      }
      if (!paramPreference.getKey().equals("driving_mode"))
        continue;
      startActivity(new Intent(getApplicationContext(), DrivingModeSettings.class));
    }
  }

  protected void onResume()
  {
    super.onResume();
    if (Settings.getLanguageApplication().equals(this.m_languageApplication.getValue()))
    {
      if (FacebookSSOAPI.facebookSSO())
        FacebookSSOAPI.getFbAccountInfo(this);
      updateSettingUI();
      updateSocialUI();
      this.m_alreadyTTSedWakeupWord = false;
      getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
      updateState();
      updateCheckState();
    }
    while (true)
    {
      ((VlingoApplication)getApplication()).setSocialLogin(false);
      this.drivingModeContentObserver = new DrivingModeContentObserver(new Handler());
      getContentResolver().registerContentObserver(DrivingModeUtil.getUri(), false, this.drivingModeContentObserver);
      return;
      finish();
      startActivity(new Intent(this, SettingsScreen.class));
    }
  }

  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      if ("auto_dial".equals(paramString))
      {
        updateSettingUI();
        continue;
      }
      if ("wake_up_default_engine".equals(paramString))
      {
        if (!Settings.getString("wake_up_default_engine", "").toString().equalsIgnoreCase(this.m_wake_up_engine.getEntry().toString()))
          Settings.setString("wake_up_default_engine", this.m_wake_up_engine.getEntry().toString());
        updateSettingUI();
        continue;
      }
      if ("language".equals(paramString))
      {
        if (this.appLangChanged)
          continue;
        this.appLangChanged = true;
        String str = Settings.getString("language", "en-US");
        saveString("language.bin", str);
        this.homePromptAutoReset = true;
        this.startupPromptAutoReset = true;
        Settings.setLanguageApplication(str, getApplicationContext());
        ActivityUtil.scheduleOnMainThread(new Runnable()
        {
          public void run()
          {
            Context localContext = SettingsScreen.this.getApplicationContext();
            InCarWidget.updateAllWidgets(localContext, DrivingModeUtil.isDrivingModeEnabled(localContext));
          }
        }
        , 250L);
        sendLanguageUpdatedLMTTRequest();
        PhraseSpotterParameters localPhraseSpotterParameters = PhraseSpotterUtil.getPhraseSpotterParameters(getApplicationContext().getResources());
        PhraseSpotter.getInstance().updateParameters(localPhraseSpotterParameters);
        Settings.System.putString(getContentResolver(), "voicetalk_language", MidasSettings.convertToISOLanguage(str));
        sendBroadcast(new Intent("com.vlingo.LANGUAGE_CHANGED"));
        isLaunchBySettings = true;
        MidasSettings.updateCurrentLocale();
        isLaunchBySettings = false;
        updateSettingUI();
        finish();
        startActivity(new Intent(this, SettingsScreen.class));
        continue;
      }
      if ("web_search_engine".equals(paramString))
      {
        if (this.appLangChanged)
          continue;
        updateSettingUI();
        continue;
      }
      if ("driving_mode_on".equals(paramString))
      {
        if (Settings.isDrivingModeEnabled())
        {
          SafeReaderProxy.startSafeReading();
          continue;
        }
        SafeReaderProxy.stopSafeReading();
        continue;
      }
      if ("car_nav_home_address".equals(paramString))
      {
        updateSettingUI();
        continue;
      }
      if ("car_nav_office_address".equals(paramString))
      {
        updateSettingUI();
        continue;
      }
      if ("shake_to_skip".equals(paramString))
      {
        MidasSettings.setBoolean("shake_to_skip", this.m_ShakeToSkip.isChecked());
        continue;
      }
      if ("car_word_spotter_enabled".equals(paramString))
      {
        if (this.m_wakeUpWord.isChecked())
          UserLoggingEngine.getInstance().landingPageViewed("car-settings-wake-up-word-enabled");
        while (true)
        {
          Settings.setBoolean("car_word_spotter_enabled", this.m_wakeUpWord.isChecked());
          if ((!this.m_wakeUpWord.isChecked()) || (this.m_alreadyTTSedWakeupWord))
            break;
          this.m_alreadyTTSedWakeupWord = true;
          break;
          UserLoggingEngine.getInstance().landingPageViewed("car-settings-wake-up-word-disabled");
        }
      }
      if ("car_safereader_read_message".equals(paramString))
      {
        MidasSettings.setSafeReaderReadMessageBodyEnabled(this.m_SafeReaderReadMessageBody.isChecked());
        continue;
      }
      if ("location_enabled".equals(paramString))
      {
        MidasSettings.setLocationEnabled(this.m_LocationOn.isChecked());
        continue;
      }
      if ("profanity_filter".equals(paramString))
      {
        MidasSettings.setBoolean("profanity_filter", this.m_ProfanityFilter.isChecked());
        continue;
      }
      if ("auto_punctuation".equals(paramString))
      {
        MidasSettings.setBoolean("auto_punctuation", this.m_autoPunctuation.isChecked());
        continue;
      }
      if ("listen_over_bluetooth".equals(paramString))
      {
        Settings.setBoolean("listen_over_bluetooth", this.m_listenOverBluetooth.isChecked());
        BluetoothManager.onListenOverBTSettingChanged(this.m_listenOverBluetooth.isChecked());
        continue;
      }
      if ("launch_car_on_car_dock".equals(paramString))
      {
        Settings.setBoolean("launch_car_on_car_dock", this.m_launchOnCarMode.isChecked());
        continue;
      }
      if ("car_auto_start_speakerphone".equals(paramString))
      {
        Settings.setBoolean("car_auto_start_speakerphone", this.m_auto_start_speaker.isChecked());
        continue;
      }
      if ("headset_mode".equals(paramString))
      {
        Settings.setBoolean("headset_mode", this.m_headsetMode.isChecked());
        continue;
      }
      if ("car_word_spotter_motion".equals(paramString))
      {
        Settings.setBoolean("car_word_spotter_motion", this.m_motionSettings.isChecked());
        continue;
      }
      if (!"say_wake_up_command".equals(paramString))
        continue;
      Settings.setBoolean("say_wake_up_command", this.m_sayWakeUpMode.isChecked());
      Intent localIntent = new Intent("android.intent.action.SAY_WAKE_UP");
      localIntent.putExtra("isChecked", this.m_sayWakeUpMode.isChecked());
      sendBroadcast(localIntent);
    }
  }

  void sendLanguageUpdatedLMTTRequest()
  {
    Intent localIntent = new Intent(getApplicationContext(), LMTTService.class);
    localIntent.setAction("com.vlingo.lmtt.action.LANGUAGE_CHANGE");
    startService(localIntent);
  }

  public void showGuideDialog()
  {
    if (this.mAutoHapticDialog != null)
    {
      this.mAutoHapticDialog.dismiss();
      this.mAutoHapticDialog = null;
    }
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903045, null);
    TextView localTextView = (TextView)localView.findViewById(2131558513);
    if (PackageInfoProvider.hasDialing());
    for (int i = 2131362599; ; i = 2131362600)
    {
      localTextView.setText(i);
      CheckBox localCheckBox = (CheckBox)localView.findViewById(2131558514);
      localCheckBox.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
        }
      });
      this.mAutoHapticDialog = new AlertDialog.Builder(this).setTitle(2131362584).setPositiveButton(2131362601, new DialogInterface.OnClickListener(localCheckBox)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          if (this.val$check.isChecked())
            Settings.System.putInt(SettingsScreen.this.getContentResolver(), "voiceinputcontrol_showNeverAgain", 1);
          Settings.setInt("temp_input_voice_control", Settings.System.getInt(SettingsScreen.this.getContentResolver(), "voice_input_control", 1));
        }
      }).setNegativeButton(2131362602, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          SettingsScreen.this.mVoiceInputControl.setChecked(false);
          Settings.System.putInt(SettingsScreen.this.getContentResolver(), "voice_input_control", 0);
          Settings.setInt("temp_input_voice_control", Settings.System.getInt(SettingsScreen.this.getContentResolver(), "voice_input_control", 0));
        }
      }).setView(localView).create();
      this.mAutoHapticDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
      {
        public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
        {
          int i = 0;
          if (paramInt == 4)
          {
            paramDialogInterface.cancel();
            SettingsScreen.this.mVoiceInputControl.setChecked(false);
            Settings.System.putInt(SettingsScreen.this.getContentResolver(), "voice_input_control", 0);
            Settings.setInt("temp_input_voice_control", Settings.System.getInt(SettingsScreen.this.getContentResolver(), "voice_input_control", 0));
            i = 1;
          }
          return i;
        }
      });
      this.mAutoHapticDialog.show();
      this.mAutoHapticDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
      {
        public void onDismiss(DialogInterface paramDialogInterface)
        {
        }
      });
      return;
    }
  }

  protected void updateCheckState()
  {
    boolean bool = false;
    if (this.m_launchVoicetalk != null)
      this.m_launchVoicetalk.setChecked(Settings.getBoolean("launch_voicetalk", true));
    if (this.m_launchVoicetalk != null)
      this.m_LocationOn.setChecked(Settings.getBoolean("location_enabled", true));
    if (this.m_ProfanityFilter != null)
      this.m_ProfanityFilter.setChecked(Settings.getBoolean("profanity_filter", true));
    if (this.m_auto_start_speaker != null)
      this.m_auto_start_speaker.setChecked(Settings.getBoolean("car_auto_start_speakerphone", true));
    if (this.m_SafeReaderReadMessageBody != null)
      this.m_SafeReaderReadMessageBody.setChecked(ClientSuppliedValues.isReadMessageBodyEnabled());
    if (this.m_wakeUpWord != null)
      this.m_wakeUpWord.setChecked(Settings.getBoolean("car_word_spotter_enabled", true));
    if (this.m_wake_up_lock_screen != null)
    {
      CheckBoxPreference localCheckBoxPreference = this.m_wake_up_lock_screen;
      if (Settings.System.getInt(getContentResolver(), "wake_up_lock_screen", 0) != 0)
        bool = true;
      localCheckBoxPreference.setChecked(bool);
    }
    if (this.m_headsetMode != null)
      this.m_headsetMode.setChecked(Settings.getBoolean("headset_mode", true));
  }

  protected void updateSettingUI()
  {
    boolean bool = true;
    MidasSettings.updateCurrentLocale();
    if (this.m_webSearchEngine != null)
      this.m_webSearchEngine.setSummary(this.m_webSearchEngine.getEntry());
    PreferenceCategory localPreferenceCategory = (PreferenceCategory)findPreference("call");
    if ((this.m_autoDial != null) && (localPreferenceCategory != null))
      localPreferenceCategory.removePreference(this.m_autoDial);
    if ((isDialogMode()) && (localPreferenceCategory != null) && (this.m_auto_start_speaker != null))
    {
      localPreferenceCategory.removePreference(this.m_auto_start_speaker);
      if (localPreferenceCategory.getPreferenceCount() <= 0)
        getPreferenceScreen().removePreference(localPreferenceCategory);
    }
    if (this.m_languageApplication != null)
      this.m_languageApplication.setSummary(this.m_languageApplication.getEntry());
    if (this.m_wake_up_engine != null)
      this.m_wake_up_engine.setSummary(this.m_wake_up_engine.getEntry());
    if (this.m_carModeStartupTtsPrompt != null)
      this.m_carModeStartupTtsPrompt.setSummary(Settings.getString("tts_carmode_startup_prompt", getString(2131362317)));
    if (this.m_autoPunctuation != null)
      this.m_autoPunctuation.setEnabled(MidasSettings.getBoolean("auto_punctuation", bool));
    label302: CheckBoxPreference localCheckBoxPreference;
    if (this.m_carModeHomeAddress != null)
    {
      String str3 = Settings.getString("car_nav_home_address", null);
      if ((str3 != null) && (str3.length() > 0))
      {
        this.m_carModeHomeAddress.setSummary(str3);
        this.m_carModeHomeAddress.setText(str3);
      }
    }
    else
    {
      if ((Settings.getString("language", "en-US").equals("ko-KR")) && (this.m_carModeOfficeAddress != null))
      {
        String str2 = Settings.getString("car_nav_office_address", null);
        if ((str2 == null) || (str2.length() <= 0))
          break label479;
        this.m_carModeOfficeAddress.setSummary(str2);
        this.m_carModeOfficeAddress.setText(str2);
      }
      if (this.m_safereaderEmailPollInterval != null)
      {
        long l = Long.parseLong(Settings.getString("safereader_email_poll_interval", "30000")) / 60000L;
        String str1 = getString(2131362441);
        if (l == 1L)
          str1 = getString(2131362440);
        this.m_safereaderEmailPollInterval.setSummary(l + str1);
      }
      if ((this.m_EmailReadback != null) && (MidasSettings.isEMailReadbackEnabled() != this.m_EmailReadback.isChecked()))
        this.m_EmailReadback.setChecked(MidasSettings.isEMailReadbackEnabled());
      if (this.m_motionSettings != null)
        checkSystemMotionSettings();
      if (this.m_wake_up_lock_screen != null)
      {
        localCheckBoxPreference = this.m_wake_up_lock_screen;
        if (Settings.System.getInt(getContentResolver(), "wake_up_lock_screen", 0) == 0)
          break label506;
      }
    }
    while (true)
    {
      localCheckBoxPreference.setChecked(bool);
      return;
      this.m_carModeHomeAddress.setSummary(getString(2131362383));
      this.m_carModeHomeAddress.setText("");
      break;
      label479: this.m_carModeOfficeAddress.setSummary(getString(2131362803));
      this.m_carModeOfficeAddress.setText("");
      break label302;
      label506: bool = false;
    }
  }

  protected void updateSocialUI()
  {
    MidasSettings.updateCurrentLocale();
    if (ClientConfiguration.isChineseBuild())
    {
      this.m_facebook = ((ImagePreference)findPreference("facebook_account"));
      if (this.m_facebook != null)
      {
        Intent localIntent3 = new Intent(this, SocialAccountActivity.class);
        localIntent3.setAction("android.intent.action.MAIN");
        localIntent3.putExtra("android.intent.extra.INTENT", 32);
        localIntent3.putExtra("android.intent.extra.TEXT", "false");
        this.m_facebook.setIntent(localIntent3);
        if (!Settings.getBoolean("weibo_account", false))
          break label358;
        this.m_facebook.setTitle(getString(2131362417) + " " + Settings.getString("weibo_account_name", ""));
        this.m_facebook.setSummary(getString(2131362422));
        Bitmap localBitmap4 = Settings.getImage("weibo_picture");
        this.m_facebook.setImageViewVisibility(0);
        if (localBitmap4 != null)
          this.m_facebook.setImageViewBitmap(localBitmap4);
      }
      this.m_twitter = ((ImagePreference)findPreference("twitter_account"));
      if (this.m_twitter != null)
      {
        Intent localIntent4 = new Intent(this, SocialAccountActivity.class);
        localIntent4.setAction("android.intent.action.MAIN");
        localIntent4.putExtra("android.intent.extra.INTENT", 16);
        localIntent4.putExtra("android.intent.extra.TEXT", "false");
        this.m_twitter.setIntent(localIntent4);
        if (!Settings.getBoolean("qzone_account", false))
          break label397;
        this.m_twitter.setTitle(getString(2131362417) + " " + Settings.getString("qzone_account_name", ""));
        this.m_twitter.setSummary(getString(2131362422));
        Bitmap localBitmap3 = Settings.getImage("qzone_picture");
        this.m_twitter.setImageViewVisibility(0);
        if (localBitmap3 != null)
          this.m_twitter.setImageViewBitmap(localBitmap3);
      }
    }
    while (true)
    {
      return;
      label358: this.m_facebook.setTitle(getString(2131362723));
      this.m_facebook.setSummary(getString(2131362724));
      this.m_facebook.setImageViewVisibility(4);
      break;
      label397: this.m_twitter.setSummary(2131362431);
      this.m_twitter.setTitle(2131362432);
      this.m_twitter.setImageViewVisibility(4);
      continue;
      this.m_twitter = ((ImagePreference)findPreference("twitter_account"));
      if (this.m_twitter != null)
      {
        if (ClientConfiguration.isChineseBuild())
          break label830;
        Intent localIntent2 = new Intent(this, SocialAccountActivity.class);
        localIntent2.setAction("android.intent.action.MAIN");
        localIntent2.putExtra("android.intent.extra.INTENT", 4);
        localIntent2.putExtra("android.intent.extra.TEXT", "false");
        this.m_twitter.setIntent(localIntent2);
        if (!Settings.getBoolean("twitter_account", false))
          break label791;
        this.m_twitter.setTitle(getString(2131362417) + " " + Settings.getString("twitter_account_name", ""));
        this.m_twitter.setSummary(getString(2131362422));
        Bitmap localBitmap2 = Settings.getImage("twitter_picture");
        this.m_twitter.setImageViewVisibility(0);
        if (localBitmap2 != null)
          this.m_twitter.setImageViewBitmap(localBitmap2);
      }
      label607: this.m_facebook = ((ImagePreference)findPreference("facebook_account"));
      if (this.m_facebook == null)
        continue;
      if (!ClientConfiguration.isChineseBuild())
      {
        Intent localIntent1 = new Intent(this, SocialAccountActivity.class);
        localIntent1.setAction("android.intent.action.MAIN");
        localIntent1.putExtra("android.intent.extra.INTENT", 8);
        localIntent1.putExtra("android.intent.extra.TEXT", "false");
        this.m_facebook.setIntent(localIntent1);
        if (FacebookSSOAPI.facebookSSO());
        for (boolean bool = FacebookSSOAPI.isFacebookLoggedIn(); ; bool = Settings.getBoolean("facebook_account", false))
        {
          if (!bool)
            break label878;
          this.m_facebook.setTitle(getString(2131362417) + " " + Settings.getString("facebook_account_name", ""));
          this.m_facebook.setSummary(getString(2131362422));
          Bitmap localBitmap1 = Settings.getImage("facebook_picture");
          this.m_facebook.setImageViewVisibility(0);
          if (localBitmap1 == null)
            break;
          this.m_facebook.setImageViewBitmap(localBitmap1);
          break;
          label791: this.m_twitter.setTitle(getString(2131362421));
          this.m_twitter.setSummary(getString(2131362382));
          this.m_twitter.setImageViewVisibility(4);
          break label607;
          label830: Preference localPreference2 = findPreference("twitter_account");
          if (getPreferenceScreen().removePreference(localPreference2))
            break label607;
          localPreference2.setEnabled(false);
          localPreference2.setShouldDisableView(true);
          break label607;
        }
        label878: this.m_facebook.setTitle(getString(2131362418));
        this.m_facebook.setSummary(getString(2131362382));
        this.m_facebook.setImageViewVisibility(4);
        continue;
      }
      Preference localPreference1 = findPreference("facebook_account");
      if (getPreferenceScreen().removePreference(localPreference1))
        continue;
      localPreference1.setEnabled(false);
      localPreference1.setShouldDisableView(true);
    }
  }

  public boolean wakeup_lockscreen_dialog(Preference paramPreference)
  {
    View localView = getLayoutInflater().inflate(2130903125, null);
    CheckBox localCheckBox = (CheckBox)localView.findViewById(2131558514);
    localCheckBox.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
      }
    });
    localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
      {
      }
    });
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(2131362575);
    if (PackageInfoProvider.hasRadio())
      localBuilder.setMessage(2131362578);
    while (true)
    {
      localBuilder.setView(localView);
      localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener(localCheckBox)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          int i = 1;
          ContentResolver localContentResolver;
          if (SettingsScreen.this.m_wake_up_lock_screen.isChecked())
          {
            SettingsScreen.this.m_wake_up_lock_screen.setChecked(false);
            if (this.val$checkBox.isChecked())
            {
              SharedPreferences.Editor localEditor = SettingsScreen.this.getSharedPreferences("checkBoxPrefForAlert", 0).edit();
              localEditor.putBoolean("isCheckBoxChecked", i);
              localEditor.commit();
            }
            localContentResolver = SettingsScreen.this.getContentResolver();
            if (!SettingsScreen.this.m_wake_up_lock_screen.isChecked())
              break label118;
          }
          while (true)
          {
            Settings.System.putInt(localContentResolver, "wake_up_lock_screen", i);
            return;
            SettingsScreen.this.m_wake_up_lock_screen.setChecked(i);
            break;
            label118: int j = 0;
          }
        }
      });
      localBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          SharedPreferences.Editor localEditor = SettingsScreen.this.getSharedPreferences("checkBoxPrefForAlert", 0).edit();
          localEditor.putBoolean("isCheckBoxChecked", false);
          localEditor.commit();
        }
      });
      localBuilder.show();
      return false;
      localBuilder.setMessage(2131362579);
    }
  }

  private class DrivingModeContentObserver extends ContentObserver
  {
    public DrivingModeContentObserver(Handler arg2)
    {
      super();
    }

    public void onChange(boolean paramBoolean)
    {
      int i = 1;
      DialogFlow.getInstance().cancelTurn();
      label91: SwitchPreference localSwitchPreference1;
      if (Settings.getBoolean("driving_mode_on", i))
      {
        if (Settings.System.getInt(SettingsScreen.this.getContentResolver(), "voice_input_control", 0) == 0)
          Settings.setInt("temp_input_voice_control", 0);
        Settings.System.putInt(SettingsScreen.this.getContentResolver(), "voice_input_control", i);
        if (SettingsScreen.this.mDriveMode != null)
        {
          SwitchPreference localSwitchPreference2 = SettingsScreen.this.mDriveMode;
          if (Settings.System.getInt(SettingsScreen.this.getContentResolver(), "driving_mode_on", 0) == 0)
            break label183;
          int k = i;
          localSwitchPreference2.setChecked(k);
        }
        if (SettingsScreen.this.mVoiceInputControl != null)
        {
          localSwitchPreference1 = SettingsScreen.this.mVoiceInputControl;
          if (Settings.System.getInt(SettingsScreen.this.getContentResolver(), "voice_input_control", 0) == 0)
            break label189;
        }
      }
      while (true)
      {
        localSwitchPreference1.setChecked(i);
        return;
        if (Settings.getInt("temp_input_voice_control", 0) == 0)
        {
          Settings.System.putInt(SettingsScreen.this.getContentResolver(), "voice_input_control", 0);
          break;
        }
        Settings.System.putInt(SettingsScreen.this.getContentResolver(), "voice_input_control", i);
        break;
        label183: int m = 0;
        break label91;
        label189: int j = 0;
      }
    }
  }

  private static class SettingsScreenHandler extends Handler
  {
    private final WeakReference<SettingsScreen> outer;

    SettingsScreenHandler(SettingsScreen paramSettingsScreen)
    {
      this.outer = new WeakReference(paramSettingsScreen);
    }

    public void handleMessage(Message paramMessage)
    {
      SettingsScreen localSettingsScreen = (SettingsScreen)this.outer.get();
      if (localSettingsScreen != null)
      {
        super.handleMessage(paramMessage);
        switch (paramMessage.what)
        {
        default:
        case 0:
        }
      }
      while (true)
      {
        return;
        localSettingsScreen.updateSocialUI();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.SettingsScreen
 * JD-Core Version:    0.6.0
 */