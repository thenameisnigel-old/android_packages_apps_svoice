package com.vlingo.midas.settings.debug;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.os.Process;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.midas.util.ServerDetails;
import com.vlingo.sdk.internal.util.Screen;
import com.vlingo.sdk.util.SDKDebugSettings.ServerResponseLoggingState;

public class DebugSettings extends PreferenceActivity
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  public static final int DEBUG_MENU = 1000;
  public static final int MENU_FORCE_FORCE_CLOSE = 1002;
  public static final int MENU_RUN_SCHEDULE_TESTS = 1001;
  public static final int MENU_START_AUTOMATION_PLAYBACK = 1003;
  public static final int MENU_START_AUTOMATION_RECORDING = 1005;
  public static final int MENU_STOP_AUTOMATION_PLAYBACK = 1004;
  public static final int MENU_STOP_AUTOMATION_RECORDING = 1006;
  public static boolean SHOW_DEBUG = false;
  private PreferencesUpdater changeActions = new PreferencesUpdater();
  private EditTextPreference m_Carrier;
  private EditTextPreference m_CarrierCountry;
  private boolean m_KillProcessOnDestroy = false;
  private EditTextPreference m_TTS_Network_Timeout;
  private EditTextPreference m_fake_device_model;
  private EditTextPreference m_fake_lat;
  private EditTextPreference m_fake_long;
  private EditTextPreference m_server_response_file;
  private ListPreference m_server_response_logging;

  void enableFakeLatLong(boolean paramBoolean)
  {
    this.m_CarrierCountry.setEnabled(paramBoolean);
    this.m_Carrier.setEnabled(paramBoolean);
    this.m_fake_lat.setEnabled(paramBoolean);
    this.m_fake_long.setEnabled(paramBoolean);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131034112);
    DebugSettings.1 local1 = new DebugSettings.1(this);
    DebugSettings.2 local2 = new DebugSettings.2(this);
    PreferenceBuilder.onAllUpdatedPreferences(local2);
    this.m_TTS_Network_Timeout = ((EditTextPreference)new EditTextPreferenceBuilder().onSetting("tts_network_timeout").onPreferenceChange(new DebugSettings.3(this)).register(this, this.changeActions));
    if (this.m_TTS_Network_Timeout.getText() == null)
      this.m_TTS_Network_Timeout.setText("750");
    ServerDetails localServerDetails = ServerDetails.getInstance();
    new ServerPreferenceBuilder("SERVER_NAME").defaultTo(localServerDetails.getASRHost()).showAsSummary().register(this, this.changeActions);
    new ServerPreferenceBuilder("SERVICES_HOST_NAME").defaultTo(localServerDetails.getVCSHost()).showAsSummary().register(this, this.changeActions);
    new ServerPreferenceBuilder("EVENTLOG_HOST_NAME").defaultTo(localServerDetails.getLogHost()).showAsSummary().register(this, this.changeActions);
    new ServerPreferenceBuilder("HELLO_HOST_NAME").defaultTo(localServerDetails.getHelloHost()).showAsSummary().register(this, this.changeActions);
    new ServerPreferenceBuilder("LMTT_HOST_NAME").defaultTo(localServerDetails.getLMTTHost()).showAsSummary().register(this, this.changeActions);
    Screen localScreen = Screen.getInstance();
    new EditTextPreferenceBuilder("screen.width").defaultTo(Integer.toString(localScreen.getWidth())).showAsSummary().register(this, this.changeActions);
    new EditTextPreferenceBuilder("screen.mag").defaultTo(Float.toString(Screen.getMagnification())).showAsSummary().register(this, this.changeActions);
    new EditTextPreferenceBuilder("plot.width").defaultTo(Integer.toString(0)).showAsSummary().register(this, this.changeActions);
    new EditTextPreferenceBuilder("max.width").defaultTo(Integer.toString(0)).showAsSummary().register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("wa.image.preloads").defaultTo(Boolean.valueOf(true)).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("wa.image.overlays").defaultTo(Boolean.valueOf(true)).register(this, this.changeActions);
    new EditTextPreferenceBuilder("wa.download.timeout").defaultTo("30").showAsSummary().register(this, this.changeActions);
    new EditTextPreferenceBuilder("wa.download.delay").defaultTo("0").showAsSummary().register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("asr.manager").defaultTo(Boolean.valueOf(Settings.DEFAULT_ASR_MANAGER)).register(this, this.changeActions);
    new EditTextPreferenceBuilder("FIELD_ID").defaultTo("dm_main").showAsSummary().register(this, this.changeActions);
    new EditTextPreferenceBuilder("SMS_Simulation").withValue("1").register(this, this.changeActions);
    new EditTextPreferenceBuilder("EMail_Simulation").withValue("1").register(this, this.changeActions);
    new EditTextPreferenceBuilder("TTS_Simulation").withValue("1").register(this, this.changeActions);
    new EditTextPreferenceBuilder("trial_tts_string").onPreferenceChange(new DebugSettings.4(this)).register(this, this.changeActions);
    new EditTextPreferenceBuilder("vcs.timeout.ms.str").onPreferenceChange(new DebugSettings.5(this)).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("barge_in_enabled").onPreferenceUpdated(local2).onPreferenceUpdated(local1).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("dynamic_config_disabled").defaultTo(Boolean.valueOf(false)).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("asr_event_logging").defaultTo(Boolean.valueOf(false)).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("nlu_event_logging").defaultTo(Boolean.valueOf(false)).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("show_all_languages").onPreferenceUpdated(local1).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("ru-RU_enable").onPreferenceUpdated(local1).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("pt-BR_enable").onPreferenceUpdated(local1).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("ja-JP_enable").onPreferenceUpdated(local1).register(this, this.changeActions);
    new CheckBoxPreferenceBuilder("KEY_ENABLE_SVOX_NEW").defaultTo(Boolean.valueOf(true)).onPreferenceUpdated(new DebugSettings.6(this)).register(this, this.changeActions);
    this.m_CarrierCountry = ((EditTextPreference)new EditTextPreferenceBuilder("CARRIER_COUNTRY").defaultTo("").showAsSummary().register(this, this.changeActions));
    this.m_Carrier = ((EditTextPreference)new EditTextPreferenceBuilder("CARRIER").defaultTo("").showAsSummary().register(this, this.changeActions));
    this.m_fake_lat = ((EditTextPreference)new EditTextPreferenceBuilder("FAKE_LAT").defaultTo("").showAsSummary().register(this, this.changeActions));
    this.m_fake_long = ((EditTextPreference)new EditTextPreferenceBuilder("FAKE_LONG").defaultTo("").showAsSummary().register(this, this.changeActions));
    enableFakeLatLong(((CheckBoxPreference)new CheckBoxPreferenceBuilder("FAKE_LAT_LONG").onPreferenceUpdated(new DebugSettings.7(this)).register(this, this.changeActions)).isChecked());
    this.m_fake_device_model = ((EditTextPreference)new EditTextPreferenceBuilder("DEVICE_MODEL").defaultTo("").showAsSummary().register(this, this.changeActions));
    CheckBoxPreference localCheckBoxPreference = (CheckBoxPreference)new CheckBoxPreferenceBuilder("FAKE_DEVICE_MODEL").onPreferenceUpdated(new DebugSettings.8(this)).register(this, this.changeActions);
    this.m_fake_device_model.setEnabled(localCheckBoxPreference.isChecked());
    this.m_server_response_file = ((EditTextPreference)new EditTextPreferenceBuilder("SERVER_RESONSE_FILE").defaultTo("serverReponseFile").showAsSummary().register(this, this.changeActions));
    String[] arrayOfString = SDKDebugSettings.ServerResponseLoggingState.getStringValues();
    this.m_server_response_logging = ((ListPreference)new ListPreferenceBuilder("SERVER_RESONSE_LOGGGING").defaultTo("None").showAsSummary().setEntries(arrayOfString).setEntryValues(arrayOfString).onPreferenceUpdated(new DebugSettings.9(this)).register(this, this.changeActions));
    updateServerReponseHandling(this.m_server_response_logging.getValue());
    new CheckBoxPreferenceBuilder("seamless_wakeup").defaultTo(Boolean.FALSE).showAsSummary();
    DebugSettings.10 local10 = new DebugSettings.10(this);
    findPreference("LMTT_CONTACTS_CLEAR_SERVERDB").setOnPreferenceClickListener(local10);
    findPreference("LMTT_CONTACTS_CLEAR_CLIENTDB").setOnPreferenceClickListener(local10);
    findPreference("LMTT_CONTACTS_SENDLMTT").setOnPreferenceClickListener(local10);
    findPreference("LMTT_MUSIC_CLEAR_SERVERDB").setOnPreferenceClickListener(local10);
    findPreference("LMTT_MUSIC_CLEAR_CLIENTDB").setOnPreferenceClickListener(local10);
    findPreference("LMTT_MUSIC_SENDLMTT").setOnPreferenceClickListener(local10);
    registerLMTTUpdateVersionListener("");
    registerLMTTUpdateVersionListener(".pim");
    registerLMTTUpdateVersionListener(".music");
    setTitle("Debug Settings");
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (this.m_KillProcessOnDestroy)
      Process.killProcess(Process.myPid());
  }

  protected void onPause()
  {
    super.onPause();
    Settings.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
  }

  protected void onResume()
  {
    super.onResume();
    Settings.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
  }

  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      if (this.changeActions.containsKey(paramString))
      {
        ((PreferenceUpdateAction)this.changeActions.get(paramString)).onPreferenceUpdated();
        continue;
      }
    }
  }

  protected void registerLMTTUpdateVersionListener(String paramString)
  {
    findPreference("debug.lmtt.update.version" + paramString).setOnPreferenceChangeListener(new DebugSettings.11(this, paramString));
  }

  void updateServerReponseHandling(String paramString)
  {
    EditTextPreference localEditTextPreference = this.m_server_response_file;
    if (!"None".equals(paramString));
    for (boolean bool = true; ; bool = false)
    {
      localEditTextPreference.setEnabled(bool);
      this.m_server_response_logging.setSummary(paramString);
      return;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.DebugSettings
 * JD-Core Version:    0.6.0
 */