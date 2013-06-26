package com.vlingo.midas;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.DisplayMetrics;
import android.util.Log;
import com.samsung.speech.enhance.NoiseCancellationAdapter;
import com.sec.android.app.sns3.svc.sp.FacebookSSOAPI;
import com.sec.android.app.sns3.svc.sp.FacebookSSOAPI.ActionType;
import com.vlingo.core.internal.CoreAdapterRegistrar;
import com.vlingo.core.internal.CoreAdapterRegistrar.AdapterList;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.audio.TTSCache;
import com.vlingo.core.internal.dialogmanager.DMActionFactory;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.vvs.VVSDispatcher;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.CMAWeatherLookupHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.AlertReadbackHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.resolve.ResolveMessageHandler;
import com.vlingo.core.internal.endpoints.EndpointManager;
import com.vlingo.core.internal.endpoints.WithSpeechSilenceDurationCategory;
import com.vlingo.core.internal.safereader.SafeReaderProxy;
import com.vlingo.core.internal.safereaderimpl.SafeReaderEngine;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.social.api.SocialAPI;
import com.vlingo.core.internal.social.api.SocialNetworkType;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.DeviceWorkarounds;
import com.vlingo.core.internal.util.SayHello;
import com.vlingo.core.internal.util.VlingoApplicationInterface;
import com.vlingo.core.internal.vlservice.VlingoApplicationService;
import com.vlingo.midas.dialogmanager.actions.AddTaskAction;
import com.vlingo.midas.dialogmanager.actions.DeleteAlarmAction;
import com.vlingo.midas.dialogmanager.actions.DeleteMemoAction;
import com.vlingo.midas.dialogmanager.actions.DeleteTaskAction;
import com.vlingo.midas.dialogmanager.actions.ModifyAlarmAction;
import com.vlingo.midas.dialogmanager.actions.ModifyTaskAction;
import com.vlingo.midas.dialogmanager.actions.PlayAlbumAction;
import com.vlingo.midas.dialogmanager.actions.PlayArtistAction;
import com.vlingo.midas.dialogmanager.actions.PlayMusicViaIntentAction;
import com.vlingo.midas.dialogmanager.actions.PlayOutNewsAction;
import com.vlingo.midas.dialogmanager.actions.PlayOutNewsMultiAction;
import com.vlingo.midas.dialogmanager.actions.PlayPlaylistAction;
import com.vlingo.midas.dialogmanager.actions.PlaySongListAction;
import com.vlingo.midas.dialogmanager.actions.PlayTitleAction;
import com.vlingo.midas.dialogmanager.actions.SamsungSendEmailAction;
import com.vlingo.midas.dialogmanager.actions.SamsungSettingChangeAction;
import com.vlingo.midas.dialogmanager.actions.SaveMemoAction;
import com.vlingo.midas.dialogmanager.actions.SetAlarmAndReminderAction;
import com.vlingo.midas.dialogmanager.vvs.handlers.ChatbotWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.SamsungContactLookupHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.SamsungMapHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.SamsungNavHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.SamsungNavigateHomeHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.SamsungNavigateHomeKoreanHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.AddTaskPageHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ChatbotSing;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ChinaNavHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ConfirmHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.JPWeatherLookupHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.MidasLocalSearchHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.MidasWebSearchButtonHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.MimicHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.NavLocalHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.NaverContentHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.PlayMusicByCharacteristicHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.RecordVoiceHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.SamsungShowWCISWidget;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowComposeMemoHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowMemoLookupWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowMessagesWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowNavWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowPlayAlbumWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowPlayArtistWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowPlayGenericWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowPlayMusicWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowPlayPlaylistWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.ShowPlayTitleWidgetHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.create.ShowCreateAlarmHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.create.ShowCreateTaskHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.delete.ShowDeleteAlarmHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.delete.ShowDeleteTaskHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.edit.ShowModifyAlarmHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.edit.ShowModifyTaskHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.resolve.ResolveAlarmHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.resolve.ResolveTaskHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.resolve.ShowAlarmChoicesHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.resolve.ShowAlarmsHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.resolve.ShowTaskChoicesHandler;
import com.vlingo.midas.dialogmanager.vvs.handlers.resolve.ShowTasksHandler;
import com.vlingo.midas.drivingmode.DrivingModeUtil;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.social.SocialAccountActivity;
import com.vlingo.midas.tss.SVOXWrapper;
import com.vlingo.midas.tss.TTSEngineChooser;
import com.vlingo.midas.util.SamsungAudioSourceUtil;
import com.vlingo.midas.util.ServerDetails;
import com.vlingo.sdk.internal.util.PackageUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class VlingoApplication extends Application
  implements VlingoApplicationInterface
{
  public static String ACTION_VLINGO_APP_START;
  public static final String APP_DISTRIBUTION_CHANNEL = "Preinstall Free";
  private static final String APP_ID = "com.samsung.android.jproject";
  private static final String APP_NAME = "SamsungJProject";
  private static String APP_VERSION;
  public static final String DEFAULT_FIELD_ID = "dm_main";
  private static final String J_SCREEN_MAG = "9.5";
  private static final String LMTT_UTILITY_PACKAGE = "com.vlingo.midas.lmttutility";
  private static final String LMTT_UTILITY_SHARED_PREFS_NAME = "lmttutility";
  private static String LOG_TAG = "VlingoApplication";
  private static final String[] SVOICE_HOST_KEYS;
  private static String appVersion;
  private static VlingoApplication instance;
  private boolean isInForeground;
  private SharedPreferences.OnSharedPreferenceChangeListener preferenceListener;
  private boolean socialLogin;

  static
  {
    APP_VERSION = "11.0";
    appVersion = APP_VERSION;
    ACTION_VLINGO_APP_START = "com.vlingo.client.app.action.VLINGO_APP_START";
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "SERVER_NAME";
    arrayOfString[1] = "SERVICES_HOST_NAME";
    arrayOfString[2] = "EVENTLOG_HOST_NAME";
    arrayOfString[3] = "HELLO_HOST_NAME";
    arrayOfString[4] = "LMTT_HOST_NAME";
    SVOICE_HOST_KEYS = arrayOfString;
    instance = null;
    VVSDispatcher.setupStandardMappings();
    VVSDispatcher.registerHandler("AddressBook", SamsungContactLookupHandler.class);
    VVSDispatcher.registerHandler("ChinaNav", ChinaNavHandler.class);
    VVSDispatcher.registerHandler("ChatbotMimic", MimicHandler.class);
    VVSDispatcher.registerHandler("ChatbotSing", ChatbotSing.class);
    VVSDispatcher.registerHandler("ChatbotWidget", ChatbotWidgetHandler.class);
    VVSDispatcher.registerHandler("ContactLookup", SamsungContactLookupHandler.class);
    VVSDispatcher.registerHandler("Map", SamsungMapHandler.class);
    VVSDispatcher.registerHandler("NaverContent", NaverContentHandler.class);
    VVSDispatcher.registerHandler("NavigateHome", SamsungNavigateHomeHandler.class);
    VVSDispatcher.registerHandler("NavigateHomeKorean", SamsungNavigateHomeKoreanHandler.class);
    VVSDispatcher.registerHandler("NavLocal", NavLocalHandler.class);
    VVSDispatcher.registerHandler("Nav", SamsungNavHandler.class);
    VVSDispatcher.registerHandler("RecordVoice", RecordVoiceHandler.class);
    VVSDispatcher.registerHandler("ResolveAlarm", ResolveAlarmHandler.class);
    VVSDispatcher.registerHandler("ResolveTask", ResolveTaskHandler.class);
    VVSDispatcher.registerHandler("ShowAlarmsWidget", ShowAlarmsHandler.class);
    VVSDispatcher.registerHandler("ShowAlarmChoicesWidget", ShowAlarmChoicesHandler.class);
    VVSDispatcher.registerHandler("ShowComposeMemoWidget", ShowComposeMemoHandler.class);
    VVSDispatcher.registerHandler("ShowCreateAlarmWidget", ShowCreateAlarmHandler.class);
    VVSDispatcher.registerHandler("ShowCreateTaskWidget", ShowCreateTaskHandler.class);
    VVSDispatcher.registerHandler("ShowDeleteAlarmWidget", ShowDeleteAlarmHandler.class);
    VVSDispatcher.registerHandler("ShowDeleteTaskWidget", ShowDeleteTaskHandler.class);
    VVSDispatcher.registerHandler("ShowEditAlarmWidget", ShowModifyAlarmHandler.class);
    VVSDispatcher.registerHandler("ShowEditTaskWidget", ShowModifyTaskHandler.class);
    VVSDispatcher.registerHandler("ShowLocalSearchWidget", MidasLocalSearchHandler.class);
    VVSDispatcher.registerHandler("ShowMemoLookupWidget", ShowMemoLookupWidgetHandler.class);
    VVSDispatcher.registerHandler("ShowNavWidget", ShowNavWidgetHandler.class);
    VVSDispatcher.registerHandler("ShowPlayTitleWidget", ShowPlayTitleWidgetHandler.class);
    VVSDispatcher.registerHandler("ShowPlayArtistWidget", ShowPlayArtistWidgetHandler.class);
    VVSDispatcher.registerHandler("ShowPlayAlbumWidget", ShowPlayAlbumWidgetHandler.class);
    VVSDispatcher.registerHandler("ShowPlayPlaylistWidget", ShowPlayPlaylistWidgetHandler.class);
    VVSDispatcher.registerHandler("ShowPlayMusicWidget", ShowPlayMusicWidgetHandler.class);
    VVSDispatcher.registerHandler("ShowPlayGenericWidget", ShowPlayGenericWidgetHandler.class);
    VVSDispatcher.registerHandler("ShowTasksWidget", ShowTasksHandler.class);
    VVSDispatcher.registerHandler("ShowTaskChoicesWidget", ShowTaskChoicesHandler.class);
    VVSDispatcher.registerHandler("ShowUnreadMessagesWidget", ShowMessagesWidgetHandler.class);
    VVSDispatcher.registerHandler("ShowWCISWidget", SamsungShowWCISWidget.class);
    VVSDispatcher.registerHandler("Task", AddTaskPageHandler.class);
    VVSDispatcher.registerHandler("PlayMusicByCharacteristic", PlayMusicByCharacteristicHandler.class);
    VVSDispatcher.registerHandler("ConfirmHandler", ConfirmHandler.class);
    VVSDispatcher.registerHandler("ShowWebSearchButton", MidasWebSearchButtonHandler.class);
    if (ClientConfiguration.isChineseBuild())
      VVSDispatcher.registerHandler("WeatherLookup", CMAWeatherLookupHandler.class);
    while (true)
    {
      VVSDispatcher.registerHandler("ResolveMessage", ResolveMessageHandler.class);
      DMActionFactory.setupStandardMappings();
      DMActionFactory.registerHandler(DMActionType.SEND_EMAIL, SamsungSendEmailAction.class);
      DMActionFactory.registerHandler(DMActionType.CREATE_TASK, AddTaskAction.class);
      DMActionFactory.registerHandler(DMActionType.DELETE_TASK, DeleteTaskAction.class);
      DMActionFactory.registerHandler(DMActionType.MODIFY_TASK, ModifyTaskAction.class);
      DMActionFactory.registerHandler(DMActionType.SAVE_MEMO, SaveMemoAction.class);
      DMActionFactory.registerHandler(DMActionType.SET_ALARM, SetAlarmAndReminderAction.class);
      DMActionFactory.registerHandler(DMActionType.DELETE_ALARM, DeleteAlarmAction.class);
      DMActionFactory.registerHandler(DMActionType.DELETE_MEMO, DeleteMemoAction.class);
      DMActionFactory.registerHandler(DMActionType.MODIFY_ALARM, ModifyAlarmAction.class);
      DMActionFactory.registerHandler(DMActionType.SAVE_MEMO, SaveMemoAction.class);
      DMActionFactory.registerHandler(DMActionType.SETTING_CHANGE, SamsungSettingChangeAction.class);
      DMActionFactory.registerHandler(DMActionType.PLAY_ALBUM, PlayAlbumAction.class);
      DMActionFactory.registerHandler(DMActionType.PLAY_ARTIST, PlayArtistAction.class);
      DMActionFactory.registerHandler(DMActionType.PLAY_MUSIC, PlayMusicViaIntentAction.class);
      DMActionFactory.registerHandler(DMActionType.PLAY_PLAYLIST, PlayPlaylistAction.class);
      DMActionFactory.registerHandler(DMActionType.PLAY_TITLE, PlayTitleAction.class);
      DMActionFactory.registerHandler(DMActionType.PLAY_SONGLIST, PlaySongListAction.class);
      DMActionFactory.registerHandler(DMActionType.PLAY_OUT_NEWS, PlayOutNewsAction.class);
      DMActionFactory.registerHandler(DMActionType.PLAY_OUT_NEWS_MULTI, PlayOutNewsMultiAction.class);
      CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.NoiseCancel, NoiseCancellationAdapter.class);
      CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.ExternalTTSEngine, TTSEngineChooser.class);
      CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.AudioSourceSelector, SamsungAudioSourceUtil.class);
      CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.FbStringSelector, FacebookSSOAPI.class);
      return;
      VVSDispatcher.registerHandler("WeatherLookup", JPWeatherLookupHandler.class);
    }
  }

  private void copyFile(String paramString1, String paramString2)
  {
    InputStream localInputStream;
    FileOutputStream localFileOutputStream;
    try
    {
      localInputStream = getAssets().open(paramString1);
      localFileOutputStream = openFileOutput(paramString2, 0);
      byte[] arrayOfByte = new byte[1024];
      while (true)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i <= -1)
          break;
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    while (true)
    {
      return;
      localInputStream.close();
      localFileOutputStream.close();
    }
  }

  private void copySVOXLicence()
  {
    copyFile("svox/SVOXKEYS.txt", "SVOXKEYS.txt");
    copyFile("svox/svoxconfig.txt", "svoxconfig.txt");
    copyFile("svox/lxen-US0.pil", "lxen-US0.pil");
    copyFile("svox/ppen-US0.pil", "ppen-US0.pil");
    copyFile("svox/lxen-GB0.pil", "lxen-GB0.pil");
    copyFile("svox/ppen-GB0.pil", "ppen-GB0.pil");
  }

  private void demonstrateEndpointSetup()
  {
    VlingoAndroidCore.getSmEndpointManager().setSilenceDurationWithSpeech(WithSpeechSilenceDurationCategory.LONG, 1250);
  }

  public static VlingoApplication getInstance()
  {
    return instance;
  }

  public static String getVersion()
  {
    return appVersion;
  }

  private void initClientSpecificSettings()
  {
    MidasSettings.init();
    Settings.setInt("max_audio_time", 20000);
    Settings.setFloat("endpoint.speechdetect_min_voice_level", 45.0F);
    Settings.setBoolean("driving_mode_on", ClientSuppliedValues.isDrivingModeEnabled());
    Settings.setString("calendar.app_package", "com.android.calendar");
    Settings.setString("calendar.preference_filename", "com.android.calendar_preferences");
    Settings.setString("calendar.default_calendar_key", "preference_defaultCalendar");
    Settings.setBoolean("use_mediasync_tone_approach", true);
    Settings.setBoolean("use_audiotrack_tone_player", true);
    Settings.setInt("processing_tone_fadeout_period", 500);
    Settings.setString("custom_tone_encoding", "PCM_22k");
    Settings.setString("screen.mag", Float.toString(getResources().getDisplayMetrics().density));
    Settings.setString("screen.width", Integer.toString(getResources().getDisplayMetrics().widthPixels));
    Settings.setInt("vcs.timeout.ms", 10000);
    this.preferenceListener = new VlingoApplication.1(this);
    Settings.getSharedPreferences().registerOnSharedPreferenceChangeListener(this.preferenceListener);
    if (MicrophoneStream.lockFileDeleteIfExists())
      startMainService(true);
  }

  private void setSpeakerPhoneOnByDefault()
  {
    MidasSettings.setBoolean("car_auto_start_speakerphone", MidasSettings.getBoolean("car_auto_start_speakerphone", true));
  }

  public Class<?> getMainActivityClass()
  {
    return ConversationActivity.class;
  }

  public boolean isAppInForeground()
  {
    return VlingoApplicationService.isAppInForeground();
  }

  public boolean isInForeground()
  {
    return this.isInForeground;
  }

  public boolean isSocialLogin()
  {
    return this.socialLogin;
  }

  public void onCreate()
  {
    super.onCreate();
    instance = this;
    SVOXWrapper.init(this);
    Version localVersion = new Version();
    try
    {
      PackageInfo localPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
      appVersion = localPackageInfo.versionName + "." + localVersion.getSVN();
      Log.i(LOG_TAG, LOG_TAG + " appVersion ='" + appVersion + "'");
      ClientSuppliedValues.setInterface(new MidasValues(this));
      VlingoAndroidCore.init(this, new CoreResourceProviderImpl(), this, "com.samsung.android.jproject", "SamsungJProject", appVersion, "Preinstall Free", ServerDetails.getInstance());
      demonstrateEndpointSetup();
      initClientSpecificSettings();
      if ((!Settings.getBoolean("hello_request_complete", false)) && (!MidasSettings.isTOSAccepted()))
        SayHello.sendHello();
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().build());
      MidasSettings.setString("facebook_app_id", MidasSettings.FACEBOOK_APP_ID_DEFAULT);
      MidasSettings.setString("twitter_consumer_key", "AGv8Ps3AlFKrf2C1YoFkQ");
      MidasSettings.setString("twitter_consumer_secret", "qeX5TCXa9HPDlpNmhPACOT7sUerHPmD91Oq9nYuw6Q");
      MidasSettings.setString("weibo_app_id", "2291996457");
      MidasSettings.setString("weibo_redirect_url", "http://www.samsung.com/sec");
      MidasSettings.setBoolean("use_network_tts", false);
      MidasSettings.setBoolean("car_iux_tts_cacheing_required", true);
      TTSCache.purgeCache(this, true);
      if (!DeviceWorkarounds.shouldIgnoreRequiredSamsungTTSEngine())
        MidasSettings.setBoolean("tts_local_required_engine", true);
      MidasSettings.setBoolean("tts_local_ignore_use_speech_rate", true);
      setSpeakerPhoneOnByDefault();
      ClientSuppliedValues.getADMController();
      copySVOXLicence();
      Intent localIntent1 = new Intent();
      localIntent1.setAction(ACTION_VLINGO_APP_START);
      sendBroadcast(localIntent1);
      VVSDispatcher.registerHandler("ShowUnreadMessagesWidget", AlertReadbackHandler.class);
      Intent localIntent2 = new Intent("com.sec.android.app.music.intent.action.PLAY_VIA");
      if (PackageUtil.canHandleIntent(getApplicationContext(), localIntent2))
      {
        DMActionFactory.registerHandler(DMActionType.PLAY_ALBUM, PlayMusicViaIntentAction.class);
        DMActionFactory.registerHandler(DMActionType.PLAY_ARTIST, PlayMusicViaIntentAction.class);
        DMActionFactory.registerHandler(DMActionType.PLAY_PLAYLIST, PlayMusicViaIntentAction.class);
        DMActionFactory.registerHandler(DMActionType.PLAY_TITLE, PlayMusicViaIntentAction.class);
        DMActionFactory.registerHandler(DMActionType.PLAY_SONGLIST, PlayMusicViaIntentAction.class);
      }
      SafeReaderProxy.safeReadingInit(new SafeReaderEngine(SafeReaderProxy.getContext()));
      ClientSuppliedValues.getForegroundFocus(DialogFlow.getInstance());
      if ((ClientSuppliedValues.shouldIncomingMessagesReadout()) && (Settings.getBoolean("tos_accepted", false)) && (Settings.getBoolean("iux_complete", false)))
      {
        if (DrivingModeUtil.isDrivingModeEnabled(getApplicationContext()))
        {
          ClientSuppliedValues.releaseForegroundFocus(DialogFlow.getInstance());
          SafeReaderProxy.stopSafeReading();
        }
      }
      else
        return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        appVersion = localVersion.getVersion();
        continue;
        SafeReaderProxy.startSafeReading();
      }
    }
  }

  public void onTerminate()
  {
    SafeReaderProxy.deinit();
    VlingoAndroidCore.destroy();
    super.onTerminate();
  }

  public void setIsInForeground(boolean paramBoolean)
  {
    this.isInForeground = paramBoolean;
  }

  public void setSocialLogin(boolean paramBoolean)
  {
    this.socialLogin = paramBoolean;
  }

  public void startMainActivity()
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    Intent localIntent = new Intent(ApplicationAdapter.getInstance().getApplicationContext(), getMainActivityClass());
    localIntent.setFlags(268435456);
    localContext.startActivity(localIntent);
  }

  public void startMainService(boolean paramBoolean)
  {
    Intent localIntent = new Intent(ApplicationAdapter.getInstance().getApplicationContext(), VlingoApplicationService.class);
    if (paramBoolean)
      localIntent.setAction("com.vlingo.client.app.action.CLOSE_APPLICATION");
    ApplicationAdapter.getInstance().getApplicationContext().startService(localIntent);
  }

  public void startSocialLogin(Context paramContext, SocialNetworkType paramSocialNetworkType, SocialAPI paramSocialAPI)
  {
    this.socialLogin = true;
    Intent localIntent = new Intent(paramContext, SocialAccountActivity.class);
    localIntent.setAction("android.intent.action.MAIN");
    if (VlingoAndroidCore.isChineseBuild())
      if ((paramSocialNetworkType == SocialNetworkType.WEIBO) || (paramSocialNetworkType == SocialNetworkType.ALL))
      {
        localIntent.putExtra("android.intent.extra.INTENT", 32);
        localIntent.putExtra("choice", paramSocialNetworkType.toString());
        localIntent.setFlags(268435456);
        if (!FacebookSSOAPI.facebookSSO())
          break label168;
        FacebookSSOAPI.setFbUpdateType(paramSocialNetworkType);
        if (paramSocialNetworkType != SocialNetworkType.FACEBOOK)
          break label159;
        FacebookSSOAPI.setActionAfterTTS(FacebookSSOAPI.ActionType.START_FACEBOOK_APP);
      }
    while (true)
    {
      return;
      localIntent.putExtra("android.intent.extra.INTENT", 16);
      break;
      if ((paramSocialNetworkType == SocialNetworkType.TWITTER) || (paramSocialNetworkType == SocialNetworkType.ALL))
      {
        localIntent.putExtra("android.intent.extra.INTENT", 4);
        break;
      }
      localIntent.putExtra("android.intent.extra.INTENT", 8);
      break;
      label159: paramContext.startActivity(localIntent);
      continue;
      label168: paramContext.startActivity(localIntent);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.VlingoApplication
 * JD-Core Version:    0.6.0
 */