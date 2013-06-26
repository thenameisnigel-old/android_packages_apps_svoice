package com.vlingo.core.internal.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.array;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.recognizer.SRManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public abstract class Settings
{
  public static final String ASR_EDITING_ENABLED_LANGUAGES_ALL = "All";
  public static final String AS_CONFIG_AUTO_ACTION_REC_CONFIDENCE_THRESHOLD = "config.autoaction.rec_confidence_threshold";
  public static final String AS_CONFIG_FIRST_RUN = "appstate.first_run.calypso";
  public static final String AS_CONFIG_LAST_UPDATE = "appstate.config_last_update";
  public static final String AS_CONFIG_RAW_UTT_XMIT_PERCENT = "Config.RawUtt.Xmit.Percent";
  public static final String AS_CONFIG_UPDATE_COUNT = "appstate.config_update_count";
  public static final boolean AUTO_ENDPOINTING_DEFAULT = true;
  public static final String AUTO_LISTEN = "home_auto_listen";
  public static final String CAR_AUTO_LISTEN_ENABLED = "car_auto_listen_enabled";
  public static final boolean CAR_KEYWORD_SPOTTING_DEFAULT = true;
  public static final String CHINESE_APP_CHANNEL = "Preinstall Free China";
  public static final String CHINESE_TEST_APP_CHANNEL = "Preinstall Free China Dev";
  public static final String CONFIG_DISABLE_FETCH_CONFIG = "config.fetch.disable";
  public static final String CONTACTS_USE_OTHER_NAMES = "contacts.use_other_names";
  public static final String DEFAULT_APP_ID = "com.vlingo.d2c";
  public static final String DEFAULT_ASR_EDITING_ENABLED_LANGUAGES = "All";
  public static final boolean DEFAULT_ASR_MANAGER = false;
  public static final String DEFAULT_CARRIER = "";
  public static final String DEFAULT_CARRIER_COUNTRY = "";
  public static final int DEFAULT_CAR_VOLUME = 90;
  public static final String DEFAULT_DEVICE_MODEL = "";
  public static final boolean DEFAULT_DISABLE_FETCH_CONFIG = false;
  public static final String DEFAULT_DOWNLOAD_DELAY = "0";
  public static final String DEFAULT_DOWNLOAD_TIMEOUT = "30";
  public static final int DEFAULT_ENDPOINT_SILENCE_SPEECH_LONG = 1750;
  public static final int DEFAULT_ENDPOINT_SILENCE_SPEECH_MEDIUM = 750;
  public static final int DEFAULT_ENDPOINT_SILENCE_SPEECH_SHORT = 400;
  public static final String DEFAULT_FIELD_ID = "dm_main";
  public static final boolean DEFAULT_HELLO_REQUEST_COMPLETE = false;
  public static final boolean DEFAULT_IMAGE_OVERLAYS = true;
  public static final boolean DEFAULT_IMAGE_PRELOADS = true;
  public static final boolean DEFAULT_LOCATION_ENABLED = false;
  public static final boolean DEFAULT_MULTI_WIDGET_CLIENT_CAPPED = true;
  public static final boolean DEFAULT_MULTI_WIDGET_CLIENT_COLLAPSE = true;
  public static final boolean DEFAULT_MULTI_WIDGET_CLIENT_SHOWCOUNTS = false;
  public static final boolean DEFAULT_MULTI_WIDGET_CLIENT_SHOWMOREBUTTON = false;
  public static final String DEFAULT_MULTI_WIDGET_ITEMS_INITIAL_MAX = "6";
  public static final String DEFAULT_MULTI_WIDGET_ITEMS_ULTIMATE_MAX = "20";
  public static final boolean DEFAULT_PHRASESPOT_WAVEFORMLOGGING_ENABLED = false;
  public static final boolean DEFAULT_SAFEREADER_ALERT_ENABLED = true;
  public static final int DEFAULT_SAFEREADER_DELAY = 0;
  public static final boolean DEFAULT_SAFEREADER_OFF_WHEN_SILENT = false;
  public static final boolean DEFAULT_SAFEREADER_START_ON_BOOT = true;
  public static final String DEFAULT_SERVER_RESONSE_FILE = "serverReponseFile";
  public static final String DEFAULT_SERVER_RESONSE_LOGGGING = "None";
  public static final boolean DEFAULT_SHOW_ALL_LANGUAGES = false;
  public static final int DEFAULT_SPEEX_COMPLEXITY = 3;
  public static final int DEFAULT_SPEEX_QUALITY = 8;
  public static final int DEFAULT_SPEEX_VARIABLE_BITRATE = 0;
  public static final int DEFAULT_SPEEX_VOICE_ACTIVITY_DETECTION = 1;
  public static final boolean DEFAULT_TTS_CACHING_REQUIRED = true;
  public static final String DEFAULT_TTS_LOCAL_FALLBACK_ENGINE = "com.google.android.tts";
  public static final boolean DEFAULT_TTS_LOCAL_IGNORE_USER_SPEECH_RATE = false;
  public static final boolean DEFAULT_TTS_LOCAL_REQUIRED_ENGINE = false;
  public static final boolean DEFAULT_USE_DEFAULT_PHONE = true;
  public static final String DEFAULT_WEATHER_USE_VP_LOCATION = "All";
  public static final float ENDPOINT_SPEECHDETECT_MIN_VOICE_LEVEL_DEFAULT = 57.0F;
  public static final float ENDPOINT_SPEECHDETECT_THRESHOLD_DEFAULT = 11.0F;
  public static final float ENDPOINT_SPEECHDETECT_VOICE_DURATION_DEFAULT = 0.08F;
  public static final float ENDPOINT_SPEECHDETECT_VOICE_PORTION_DEFAULT = 0.02F;
  public static final int ENDPOINT_TIME_WITHOUTSPEECH_DEFAULT = 5000;
  public static final int ENDPOINT_TIME_WITHSPEECH_DEFAULT = 1750;
  public static final String FACEBOOK_APP_ID = "facebook_app_id";
  public static final String FACEBOOK_APP_ID_DEFAULT = "39010226174";
  public static final String KEY_ADS_ENABLED = "ads.enabled";
  public static final String KEY_ADS_IS_USER_IN_APPROVED_GROUP = "ads.user_in_approved_group";
  public static final String KEY_ADS_IS_USER_IN_OPT_OUT_GROUP = "ads.user_in_opt_out_group";
  public static final String KEY_ADS_IS_USER_IN_OPT_OUT_GROUP_CHECKED = "ads.user_in_opt_out_group.checked";
  public static final String KEY_ADS_PERCENT_OF_USERS_IN_OPT_OUT_GROUP = "ads.percent_users_in_ad_opt_out_group";
  public static final String KEY_ALWAYS_SEND_RAW_UTTS = "always_send_raw_utts";
  public static final String KEY_ALWAYS_WARMUP_CONNECTIONS = "always_warmup_connections";
  public static final String KEY_ASR_EDITING_ENABLED_LANGUAGES = "asr.editing.enabled.languages";
  public static final String KEY_ASR_MANAGER = "asr.manager";
  public static final String KEY_AUDIO_FILE_LOG_ENABLED = "audiofilelog_enabled";
  public static final String KEY_AUDIO_FILE_LOG_PHRASESPOT_INDEX = "audiofilelog_index";
  public static final String KEY_AUDIO_TONES = "settings.audio.tones";
  public static final String KEY_AUTO_DIAL = "auto_dial";
  public static final String KEY_AUTO_ENDPOINTING = "auto_endpointing";
  public static final String KEY_AUTO_PUNCTUATION = "auto_punctuation";
  public static final String KEY_AUTO_SPEAK_ANSWER = "auto_speak_answer";
  public static final String KEY_BARGE_IN_ENABLED = "barge_in_enabled";
  public static final String KEY_BILLING_REFRESH_PURCHASES = "billing.refresh_purchases";
  public static final String KEY_BLUETOOTH_HEADSET_CONNECTED = "bluetooth_headset_connected";
  public static final String KEY_CARRIER = "CARRIER";
  public static final String KEY_CARRIER_COUNTRY = "CARRIER_COUNTRY";
  public static final String KEY_CAR_ANSWERS_ENABLED = "car_vlingo_answers_enabled";
  public static final String KEY_CAR_AUTO_START_SPEAKERPHONE = "car_auto_start_speakerphone";
  public static final String KEY_CAR_DO_NOT_REMIND = "car_do_not_remind";
  public static final String KEY_CAR_FIRST_RUN = "car_first_run_calypso";
  public static final String KEY_CAR_IUX_INTRO_REQUIRED = "car_iux_intro_required";
  public static final String KEY_CAR_IUX_TTS_DOWNLOADED = "car_iux_tts_downloaded";
  public static final String KEY_CAR_IUX_WORD_SPOT_INTRO_REQUIRED = "car_iux_word_spot_intro_required";
  public static final String KEY_CAR_KEYWORD_SPOTTING_ENABLED = "car_word_spotter_enabled";
  public static final String KEY_CAR_KEYWORD_SPOTTING_ONLY_WHEN_CHARGING = "car_word_spotter_when_charging_only";
  public static final String KEY_CAR_NAV_HOME_ADDRESS = "car_nav_home_address";
  public static final String KEY_CAR_SMS_LAST_WAS_RECENTS = "car_sms_last_was_recents";
  public static final String KEY_CAR_SPEAK_INCOMING_CALLS = "tts_carmode_speak_incoming_calls";
  public static final String KEY_CAR_SPEAK_WHEN_STARTED = "tts_carmode_speak_car_on_confirmation";
  public static final String KEY_CAR_STARTUP_TTS_PROMPT = "tts_carmode_startup_prompt";
  public static final String KEY_CAR_TTS_DOWNLOAD = "car_tts_download";
  public static final String KEY_CAR_VD_LAST_WAS_RECENTS = "car_vd_last_was_recents";
  public static final String KEY_CONFIG_WARMUP_CONN_PERCENT = "Config.WarmupConnection.Percent";
  public static final String KEY_COOKIE_DATA = "cookie_data";
  public static final String KEY_CUSTOM_TONE_ENCODING = "custom_tone_encoding";
  public static final String KEY_DEFAULT_CALENDAR_KEY = "calendar.default_calendar_key";
  public static final String KEY_DEVICE_MODEL = "DEVICE_MODEL";
  public static final String KEY_DM_AUTO_CONFIRM_TIMEOUT = "DIALOG_MANAGER_AUTO_CONFIRM_TIMEOUT";
  public static final String KEY_DM_ENDPOINT_THRESHOLD = "DIALOG_MANAGER_ENDPOINT_THRESHOLD";
  public static final String KEY_DM_USERNAME = "dm.username";
  public static final String KEY_DM_WORKING_MESSAGES = "DIALOG_MANAGER_WORKING_MESSAGES";
  public static final String KEY_DM_WORKING_MESSAGE_INTERVAL = "DIALOG_MANAGER_WORKING_MESSAGE_INTERVAL";
  public static final String KEY_DOWNLOAD_DELAY = "wa.download.delay";
  public static final String KEY_DOWNLOAD_TIMEOUT = "wa.download.timeout";
  public static final String KEY_DRIVING_MODE_MESSAGES_NOTIFICATION = "driving_mode_message_notification";
  public static final String KEY_DRIVING_MODE_ON = "driving_mode_on";
  public static final String KEY_DYNAMIC_CONFIG_DISABLED = "dynamic_config_disabled";
  public static final String KEY_ENABLE_ASR_EVENT_LOG = "asr_event_logging";
  public static final String KEY_ENABLE_NLU_EVENT_LOG = "nlu_event_logging";
  public static final String KEY_ENDPOINT_SILENCE_SPEECH_LONG = "endpoint.time.speech.long";
  public static final String KEY_ENDPOINT_SILENCE_SPEECH_MEDIUM = "endpoint.time.speech.medium";
  public static final String KEY_ENDPOINT_SILENCE_SPEECH_SHORT = "endpoint.time.speech.short";
  public static final String KEY_ENDPOINT_SPEECHDETECT_MIN_VOICE_LEVEL = "endpoint.speechdetect_min_voice_level";
  public static final String KEY_ENDPOINT_SPEECHDETECT_THRESHOLD = "endpoint.speechdetect_threshold";
  public static final String KEY_ENDPOINT_SPEECHDETECT_VOICE_DURATION = "endpoint.speechdetect_voice_duration";
  public static final String KEY_ENDPOINT_SPEECHDETECT_VOICE_PORTION = "endpoint.speechdetect_voice_portion";
  public static final String KEY_ENDPOINT_TIME_WITHOUTSPEECH = "endpoint.time_withoutspeech";
  public static final String KEY_ENDPOINT_TIME_WITHSPEECH = "endpoint.time_withspeech";
  public static final String KEY_FAKE_DEVICE_MODEL = "FAKE_DEVICE_MODEL";
  public static final String KEY_FAKE_LAT = "FAKE_LAT";
  public static final String KEY_FAKE_LAT_LONG = "FAKE_LAT_LONG";
  public static final String KEY_FAKE_LONG = "FAKE_LONG";
  public static final String KEY_FIELD_ID = "FIELD_ID";
  public static final String KEY_FIRST_UTT_COMPLETE = "vlingo.first_utt_complete";
  public static final String KEY_FORCE_NON_DM = "FORCE_NON_DM";
  public static final String KEY_HAS_USER_PAID_FOR_ADS = "ads.paid";
  public static final String KEY_HELLO_REQUEST_COMPLETE = "hello_request_complete";
  public static final String KEY_HELP_ABOUT = "help_about";
  public static final String KEY_HELP_UPDATE = "help_update";
  public static final String KEY_HOME_SCREEN_FIRST_DISPLAY = "home_screen_first_display_calypso";
  public static final String KEY_HOME_SCREEN_SHOW_INVITE_BAR = "home_screen_show_invite_bar";
  public static final String KEY_HTTPS_ASR = "https.asr_enabled";
  public static final String KEY_HTTPS_HELLO = "https.hello_enabled";
  public static final String KEY_HTTPS_LMTT = "https.lmtt_enabled";
  public static final String KEY_HTTPS_LOG = "https.log_enabled";
  public static final String KEY_HTTPS_ROLLOUT_GROUPID = "https.rollout_groupid";
  public static final String KEY_HTTPS_ROLLOUT_PERCENTAGE = "https.rollout_percentage";
  public static final String KEY_HTTPS_TTS = "https.tts_enabled";
  public static final String KEY_HTTPS_VCS = "https.vcs_enabled";
  public static final String KEY_IMAGE_OVERLAYS = "wa.image.overlays";
  public static final String KEY_IMAGE_PRELOADS = "wa.image.preloads";
  public static final String KEY_IN_CAR_MODE = "in_car_mode";
  public static final String KEY_IUX_COMPLETE = "iux_complete";
  public static final String KEY_IUX_STARTED = "iux_started";
  public static final String KEY_LANGUAGE = "language";
  public static final String KEY_LANGUAGE_DE_DE_ENABLE = "de-DE_enable";
  public static final String KEY_LANGUAGE_EN_GB_ENABLE = "en-GB_enable";
  public static final String KEY_LANGUAGE_EN_US_ENABLE = "en-US_enable";
  public static final String KEY_LANGUAGE_ES_ES_ENABLE = "es-ES_enable";
  public static final String KEY_LANGUAGE_FR_FR_ENABLE = "fr-FR_enable";
  public static final String KEY_LANGUAGE_IT_IT_ENABLE = "it-IT_enable";
  public static final String KEY_LANGUAGE_JA_JP_ENABLE = "ja-JP_enable";
  public static final String KEY_LANGUAGE_KO_KR_ENABLE = "ko-KR_enable";
  public static final String KEY_LANGUAGE_PT_BR_ENABLE = "pt-BR_enable";
  public static final String KEY_LANGUAGE_RU_RU_ENABLE = "ru-RU_enable";
  public static final String KEY_LANGUAGE_V_ES_LA_ENABLE = "v-es-LA_enable";
  public static final String KEY_LANGUAGE_V_ES_NA_ENABLE = "v-es-NA_enable";
  public static final String KEY_LANGUAGE_ZH_CN_ENABLE = "zh-CN_enable";
  public static final String KEY_LAST_HOME_CONTENT_STATE = "home_content_state_last";
  public static final String KEY_LAUNCH_CAR_ON_BT_CONNECT = "launch_car_on_bt_connect";
  public static final String KEY_LISTEN_OVER_BLUETOOTH = "listen_over_bluetooth";
  public static final String KEY_LMTT_CHUNK_DELAY = "lmtt.chunk_delay_ms";
  public static final String KEY_LMTT_CHUNK_RETRIES = "lmtt.chunk_retries";
  public static final String KEY_LMTT_CHUNK_RETRY_DELAY = "lmtt.chunk_retry_delay_ms";
  public static final String KEY_LMTT_CHUNK_SIZE = "lmtt.chunk_size";
  public static final String KEY_LMTT_FORCE_FULLUPDATE_ON_START = "lmtt.force_fullupdate_on_start";
  public static final String KEY_LMTT_LAST_APP_START_TIME = "lmtt.last_app_start_time";
  public static final String KEY_LMTT_NOACTIVITY_SHUTDOWN_PERIOD = "lmtt.no_activity_shutdown_period_mins";
  public static final String KEY_LOCAL_SEARCH_MAX_LISTINGS = "localsearch.max_spon_listing";
  public static final String KEY_LOCATION_ENABLED = "location_enabled";
  public static final String KEY_LOCATION_OVERRIDE_ENABLED = "location.override.enabled";
  public static final String KEY_LOCATION_OVERRIDE_PRESET = "location.override.preset";
  public static final String KEY_MARKET_AVAILABLE = "ads.market.available";
  public static final String KEY_MARKET_CHECKED = "ads.market.checked";
  public static final String KEY_MAX_AUDIO_TIME = "max_audio_time";
  public static final String KEY_MAX_WIDTH = "max.width";
  public static final String KEY_MEID = "MEID";
  public static final String KEY_MIMIC_MODE = "mimic_mode";
  public static final String KEY_MULTI_WIDGET_CLIENT_CAPPED = "multi.widget.client.capped";
  public static final String KEY_MULTI_WIDGET_CLIENT_COLLAPSE = "multi.widget.client.collapse";
  public static final String KEY_MULTI_WIDGET_CLIENT_SHOWCOUNTS = "multi.widget.client.showcounts";
  public static final String KEY_MULTI_WIDGET_CLIENT_SHOWMOREBUTTON = "multi.widget.client.showmorebutton";
  public static final String KEY_MULTI_WIDGET_ITEMS_INITIAL_MAX = "multi.widget.item.initial.max";
  public static final String KEY_MULTI_WIDGET_ITEMS_ULTIMATE_MAX = "multi.widget.item.ultimate.max";
  public static final String KEY_NAME_CALENDAR_PACKAGE = "calendar.app_package";
  public static final String KEY_NAME_CALENDAR_PREFERENCE = "calendar.preference_filename";
  public static final String KEY_NETWORK_TTS_TIMEOUT = "network_tts_timeout";
  public static final String KEY_NEW_FEATURE_DIALOG_SHOWN = "new_feature_dialog_shown";
  public static final String KEY_PHRASESPOT_ABSBEAM = "phrasespot_absbeam";
  public static final String KEY_PHRASESPOT_AOFFSET = "phrasespot_aoffset";
  public static final String KEY_PHRASESPOT_BEAM = "phrasespot_beam";
  public static final String KEY_PHRASESPOT_DELAY = "phrasespot_delay";
  public static final String KEY_PHRASESPOT_PARAMA = "phrasespot_parama";
  public static final String KEY_PHRASESPOT_PARAMB = "phrasespot_paramb";
  public static final String KEY_PHRASESPOT_PARAMC = "phrasespot_paramc";
  public static final String KEY_PHRASESPOT_WAVEFORMLOGGING_ENABLED = "phrasespot_waveformlogging_enabled";
  public static final String KEY_PLOT_WIDTH = "plot.width";
  public static final String KEY_PROCESSING_TONE_FADEOUT = "processing_tone_fadeout_period";
  public static final String KEY_PROFANITY_FILTER = "profanity_filter";
  public static final String KEY_RAW_UTTS_IS_USER_IN_SEND_GROUP = "rawutts.user_in_send_group";
  public static final String KEY_RAW_UTTS_IS_USER_IN_SEND_GROUP_CHECKED = "rawutts.user_in_send_group.checked";
  public static final String KEY_RAW_UTTS_LAST_XMIT = "rawutts.last_xmit_time";
  public static final String KEY_SAFEREADER_ACCOUNTS_CHANGED = "safereader_email_accounts_changed";
  public static final String KEY_SAFEREADER_ALERT_ENABLED = "car_safereader_enable_alert";
  public static final String KEY_SAFEREADER_DELAY = "safereader.delay";
  public static final String KEY_SAFEREADER_EMAIL_ACCOUNTS = "safereader_email_accounts";
  public static final String KEY_SAFEREADER_EMAIL_POLL_INTERVAL = "safereader_email_poll_interval";
  public static final String KEY_SAFEREADER_EMAIL_READBACK_SETTING = "safereader_email_enabled";
  public static final String KEY_SAFEREADER_INTRO_SHOWN = "safereader_shown";
  public static final String KEY_SAFEREADER_LAST_SAFEREAD_TIME = "car_safereader_last_saferead_time";
  public static final String KEY_SAFEREADER_OFF_WHEN_SILENT = "car_safereader_off_when_silent";
  public static final String KEY_SAFEREADER_READ_MESSAGE_BODY = "car_safereader_read_message";
  public static final String KEY_SAFEREADER_RUN_IN_BACKGROUND = "car_safereader_run_in_background";
  public static final String KEY_SAFEREADER_START_ON_BOOT = "safereader_start_on_boot";
  public static final String KEY_SCREEN_MAG = "screen.mag";
  public static final String KEY_SCREEN_OFF_AFTER_LAUNCH_TIMER = "reset_screen_off_after_launch_timer";
  public static final String KEY_SCREEN_WIDTH = "screen.width";
  public static final String KEY_SERVER_RESONSE_FILE = "SERVER_RESONSE_FILE";
  public static final String KEY_SERVER_RESONSE_LOGGGING = "SERVER_RESONSE_LOGGGING";
  public static final String KEY_SHOW_ALL_LANGUAGES = "show_all_languages";
  public static final String KEY_SHOW_UUID_IN_ABOUT_SCREEN = "show_uuid_in_about_screen";
  public static final String KEY_SOCIAL_LOGIN_ATTEMPT_FOR_RESUME = "key_social_login_attemp_for_resume";
  public static final String KEY_SOCIAL_LOGIN_ATTEMPT_FOR_WINDOW_FOCUS_CHANGED = "key_social_login_attemp_for_user_leave_hint";
  public static final String KEY_SPEEX_COMPLEXITY = "speex.complexity";
  public static final String KEY_SPEEX_QUALITY = "speex.quality";
  public static final String KEY_SPEEX_VARIABLE_BITRATE = "speex.variable_bitrate";
  public static final String KEY_SPEEX_VOICE_ACTIVITY_DETECTION = "speex.voice_activity_detection";
  public static final String KEY_START_LISTENING_IMMEDIATELY = "START_LISTENING_IMMEDIATELY";
  public static final String KEY_SUPER_DIALER_DETAILS_REMINDER_SHOWN = "sd_reminder_shown";
  public static final String KEY_TIMINGLOG_ENABLED = "timinglog_enabled";
  public static final String KEY_TOS_ACCEPTED = "tos_accepted";
  public static final String KEY_TOS_ACCEPTED_DATE = "tos_accepted_date";
  public static final String KEY_TTS_CACHING_REQUIRED = "car_iux_tts_cacheing_required";
  public static final String KEY_TTS_CARMODE_CONFIRM = "tts_carmode_confirm";
  public static final String KEY_TTS_LANGUAGE = "tts_language";
  public static final String KEY_TTS_LOCAL_FALLBACK_ENGINE = "tts_local_tts_fallback_engine";
  public static final String KEY_TTS_LOCAL_IGNORE_USER_SPEECH_RATE = "tts_local_ignore_use_speech_rate";
  public static final String KEY_TTS_LOCAL_REQUIRED_ENGINE = "tts_local_required_engine";
  public static final String KEY_TTS_VOICE = "tts_voice";
  public static final String KEY_UNWATERMARKED_VERSIONS = "unwatermarked.versions";
  public static final String KEY_UPDATE_INFO = "update_info_xml";
  public static final String KEY_USE_AUDIOTRACK_TONE_PLAYER = "use_audiotrack_tone_player";
  public static final String KEY_USE_DEFAULT_PHONE = "use_default_phone";
  public static final String KEY_USE_HIDDEN_CALENDARS = "use_hidden_calendars";
  public static final String KEY_USE_MEDIASYNC_APPROACH = "use_mediasync_tone_approach";
  public static final String KEY_USE_NETWORK_TTS = "use_network_tts";
  public static final String KEY_USE_SAFEREADER_NOTIFICATIONS = "safereader_notifications";
  public static final String KEY_USE_VLINGO_TYPED_REQUESTS = "use_vlingo_typed_requests";
  public static final String KEY_UUID = "uuid";
  public static final String KEY_VCS_TIMEOUT_MS = "vcs.timeout.ms";
  public static final String KEY_VOICE_THRESHOLD_LEVEL = "voice_threshold_level";
  public static final String KEY_WEATHER_USE_VP_LOCATION = "weather_use_vp_location";
  public static final String KEY_WEATHER_VERSION_TWO_LANGUAGES = "weather_version_two_languages";
  public static final String KEY_WEB_SEARCH_BAIDU_DEFAULT = "web_search_biadu_default";
  public static final String KEY_WEB_SEARCH_BAIDU_QUERY = "web_search_biadu_query";
  public static final String KEY_WEB_SEARCH_BING_DEFAULT = "web_search_bing_default";
  public static final String KEY_WEB_SEARCH_BING_QUERY = "web_search_bing_query";
  public static final String KEY_WEB_SEARCH_DAUM_DEFAULT = "web_search_daum_default";
  public static final String KEY_WEB_SEARCH_DAUM_QUERY = "web_search_daum_query";
  public static final String KEY_WEB_SEARCH_DIRECT = "web_search_direct";
  public static final String KEY_WEB_SEARCH_ENGINE = "web_search_engine";
  public static final String KEY_WEB_SEARCH_GOOGLE_DEFAULT = "web_search_google_default";
  public static final String KEY_WEB_SEARCH_GOOGLE_QUERY = "web_search_google_query";
  public static final String KEY_WEB_SEARCH_HOME_URL = "web_search_home_url";
  public static final String KEY_WEB_SEARCH_NAVER_DEFAULT = "web_search_naver_default";
  public static final String KEY_WEB_SEARCH_NAVER_QUERY = "web_search_naver_query";
  public static final String KEY_WEB_SEARCH_URL = "web_search_url";
  public static final String KEY_WEB_SEARCH_YAHOO_DEFAULT = "web_search_yahoo_default";
  public static final String KEY_WEB_SEARCH_YAHOO_QUERY = "web_search_yahoo_query";
  public static final String KEY_WELCOME_NOTE_SHOWN = "welcome_note_shown";
  public static final String KEY_WIDGET_DISPLAY_MAX = "widget_display_max";
  public static final int KNOWN_BAD_SYSTEM_SETTING_BOOLEAN_VALUE = -99;
  private static final String KNOWN_BAD_UUID = "0";
  public static final Set<String> LANGUAGES_ALL;
  public static final Set<String> LANGUAGES_CEFIGS;
  public static final Set<String> LANGUAGES_CHINESE_ONLY;
  public static final Set<String> LANGUAGES_CJ;
  public static final Set<String> LANGUAGES_CJK;
  public static final Set<String> LANGUAGES_EFIGS;
  public static final Set<String> LANGUAGES_EFIGSJK;
  public static final Set<String> LANGUAGES_ENGLISH;
  public static final Set<String> LANGUAGES_FIGS;
  public static final Set<String> LANGUAGES_US_ONLY;
  public static final String LANGUAGE_DEFAULT = "en-US";
  public static final String LANGUAGE_DE_DE = "de-DE";
  public static final String LANGUAGE_EN_GB = "en-GB";
  public static final String LANGUAGE_EN_US = "en-US";
  public static final String LANGUAGE_ES_ES = "es-ES";
  public static final String LANGUAGE_ES_US = "es-US";
  public static final String LANGUAGE_FR_FR = "fr-FR";
  public static final String LANGUAGE_IT_IT = "it-IT";
  public static final String LANGUAGE_JA_JP = "ja-JP";
  public static final String LANGUAGE_KO_KR = "ko-KR";
  public static final String LANGUAGE_PT_BR = "pt-BR";
  public static final String LANGUAGE_RU_RU = "ru-RU";
  public static final String LANGUAGE_V_ES_LA = "v-es-LA";
  public static final String LANGUAGE_V_ES_NA = "v-es-NA";
  public static final String LANGUAGE_ZH_CN = "zh-CN";
  public static final String LMTT_STATUS_PREFIX = "lmtt.enable.";
  public static final String LOGIN_FACEBOOK = "facebook_account";
  public static final String LOGIN_FACEBOOK_ACCOUNT_NAME = "facebook_account_name";
  public static final String LOGIN_FACEBOOK_EXPIRES = "facebook_expires";
  public static final String LOGIN_FACEBOOK_PICTURE = "facebook_picture";
  public static final String LOGIN_FACEBOOK_PICTURE_URL = "facebook_picture_url";
  public static final String LOGIN_FACEBOOK_TOKEN = "facebook_token";
  public static final String LOGIN_FOURSQUARE = "foursquare_account";
  public static final String LOGIN_FOURSQUARE_ACCOUNT_NAME = "foursquare_account_name";
  public static final String LOGIN_FOURSQUARE_DEMO = "foursquare_demo";
  public static final String LOGIN_FOURSQUARE_PASSWORD = "foursquare_password";
  public static final String LOGIN_FOURSQUARE_PICTURE = "foursquare_picture";
  public static final String LOGIN_FOURSQUARE_PICTURE_URL = "foursquare_picture_url";
  public static final String LOGIN_FOURSQUARE_USERNAME = "foursquare_username";
  public static final String LOGIN_QZONE = "qzone_account";
  public static final String LOGIN_QZONE_ACCOUNT_NAME = "qzone_account_name";
  public static final String LOGIN_QZONE_OPEN_ID = "qzone_open_id";
  public static final String LOGIN_QZONE_PICTURE = "qzone_picture";
  public static final String LOGIN_QZONE_PICTURE_URL = "qzone_picture_url";
  public static final String LOGIN_QZONE_TOKEN = "qzone_token";
  public static final String LOGIN_TWITTER = "twitter_account";
  public static final String LOGIN_TWITTER_ACCOUNT_NAME = "twitter_account_name";
  public static final String LOGIN_TWITTER_DEMO = "twitter_demo";
  public static final String LOGIN_TWITTER_PICTURE = "twitter_picture";
  public static final String LOGIN_TWITTER_PICTURE_URL = "twitter_picture_url";
  public static final String LOGIN_TWITTER_PROMPTED_FOLLOW_VLINGO = "twitter_prompted_follow_vlingo";
  public static final String LOGIN_TWITTER_USERNAME = "twitter_username";
  public static final String LOGIN_WEIBO = "weibo_account";
  public static final String LOGIN_WEIBO_ACCOUNT_NAME = "weibo_account_name";
  public static final String LOGIN_WEIBO_EXPIRES_IN = "weibo_expires_in";
  public static final String LOGIN_WEIBO_PICTURE = "weibo_picture";
  public static final String LOGIN_WEIBO_PICTURE_URL = "weibo_picture_url";
  public static final String LOGIN_WEIBO_TOKEN = "weibo_token";
  public static final String LOGIN_WEIBO_USER_UID = "weibo_user_uid";
  public static final int MAX_AUDIO_TIME_DEFAULT = 40000;
  public static final String MESSAGE_READBACK_WORD_COUNT = "msg_readback_word_count";
  public static final String MIMIC_MODE_DEFAULT = "-1";
  public static final int NETWORK_TTS_TIMEOUT_DEFAULT = 5000;
  public static final String PLAYBACK_INPUT_FILE = "playback_input_file";
  public static final String QZONE_APP_ID = "qzone_app_id";
  public static final String QZONE_APP_ID_DEFAULT = "100322265";
  public static final String RECORD_OUTPUT_FILE = "record_output_file";
  public static final String SERVER_RESONSE_LOGGGING_NONE = "None";
  public static final boolean SOCIAL_LOGIN_ATTEMPT_DEF_VALUE = false;
  public static final String SOCIAL_NETWORK_UPDATE_MASK = "social_network_update_mask";
  public static final String TTS_VOICE_FEMALE = "Female";
  public static final String TTS_VOICE_MALE = "Male";
  public static final String TWITTER_CONSUMER_KEY = "twitter_consumer_key";
  public static final String TWITTER_CONSUMER_KEY_DEFAULT = "AGv8Ps3AlFKrf2C1YoFkQ";
  public static final String TWITTER_CONSUMER_SECRET = "twitter_consumer_secret";
  public static final String TWITTER_CONSUMER_SECRET_DEFAULT = "qeX5TCXa9HPDlpNmhPACOT7sUerHPmD91Oq9nYuw6Q";
  public static final String TWITTER_DIALOG_FLAGS = "twitter_dialog_flags";
  public static final String TWITTER_REQUEST_SECRET = "twitter_request_secret";
  public static final String TWITTER_REQUEST_TOKEN = "twitter_request_token";
  public static final String TWITTER_USER_SECRET = "twitter_user_secret";
  public static final String TWITTER_USER_TOKEN = "twitter_user_token";
  public static final String USE_VOICE_PROMPT = "use_voice_prompt";
  public static final String USE_VOICE_PROMPT_CONFIRM_WITH_USER = "use_voice_prompt_confirm_with_user";
  public static final boolean USE_VOICE_PROMPT_CONFIRM_WITH_USER_DEFAULT = true;
  public static final boolean USE_VOICE_PROMPT_DEFAULT = true;
  public static final String WEATHER_USE_VP_LOCATION_ALL = "All";
  public static final String WEB_SEARCH_ENGINE_NAME_BAIDU = "Baidu";
  public static final String WEB_SEARCH_ENGINE_NAME_BING = "Bing";
  public static final String WEB_SEARCH_ENGINE_NAME_DAUM = "Daum";
  public static final String WEB_SEARCH_ENGINE_NAME_GOOGLE = "Google";
  public static final String WEB_SEARCH_ENGINE_NAME_NAVER = "Naver";
  public static final String WEB_SEARCH_ENGINE_NAME_YAHOO = "Yahoo";
  public static final String WEIBO_APP_ID = "weibo_app_id";
  public static final String WEIBO_APP_ID_DEFAULT = "3328388872";
  public static final String WEIBO_REDIRECT_URL = "weibo_redirect_url";
  public static final String WEIBO_REDIRECT_URL_DEFAULT = "http://www.nuance.com/";
  private static ArrayList<String> albumNameList;
  private static ArrayList<String> artistNameList;
  private static ArrayList<String> contactNameList;
  public static Context contextForJUnit;
  private static String currentLanguageApplication;
  private static HashMap<String, Integer> langIsoToEnable;
  private static String[] languageDescriptions;
  private static String[] languageEnables;
  private static String[] languages;
  private static int maxNumLanguages = 0;
  private static ArrayList<String> playlistNameList;
  public static final String shake_to_skip = "shake_to_skip";
  private static ArrayList<String> songNameList;

  static
  {
    String[] arrayOfString = new String[13];
    arrayOfString[0] = "en-US_enable";
    arrayOfString[1] = "en-GB_enable";
    arrayOfString[2] = "de-DE_enable";
    arrayOfString[3] = "es-ES_enable";
    arrayOfString[4] = "fr-FR_enable";
    arrayOfString[5] = "it-IT_enable";
    arrayOfString[6] = "ko-KR_enable";
    arrayOfString[7] = "zh-CN_enable";
    arrayOfString[8] = "ja-JP_enable";
    arrayOfString[9] = "v-es-LA_enable";
    arrayOfString[10] = "v-es-NA_enable";
    arrayOfString[11] = "ru-RU_enable";
    arrayOfString[12] = "pt-BR_enable";
    languageEnables = arrayOfString;
    langIsoToEnable = new HashMap();
    LANGUAGES_FIGS = new HashSet();
    LANGUAGES_FIGS.add("fr-FR".toLowerCase());
    LANGUAGES_FIGS.add("it-IT".toLowerCase());
    LANGUAGES_FIGS.add("de-DE".toLowerCase());
    LANGUAGES_FIGS.add("es-ES".toLowerCase());
    LANGUAGES_ENGLISH = new HashSet();
    LANGUAGES_ENGLISH.add("en-US".toLowerCase());
    LANGUAGES_ENGLISH.add("en-GB".toLowerCase());
    LANGUAGES_EFIGS = new HashSet();
    LANGUAGES_EFIGS.addAll(LANGUAGES_ENGLISH);
    LANGUAGES_EFIGS.addAll(LANGUAGES_FIGS);
    LANGUAGES_CEFIGS = new HashSet();
    LANGUAGES_CEFIGS.add("zh-CN".toLowerCase());
    LANGUAGES_CEFIGS.addAll(LANGUAGES_EFIGS);
    LANGUAGES_CJ = new HashSet();
    LANGUAGES_CJ.add("zh-CN".toLowerCase());
    LANGUAGES_CJ.add("ja-JP".toLowerCase());
    LANGUAGES_CJK = new HashSet();
    LANGUAGES_CJK.add("zh-CN".toLowerCase());
    LANGUAGES_CJK.add("ko-KR".toLowerCase());
    LANGUAGES_CJK.add("ja-JP".toLowerCase());
    LANGUAGES_US_ONLY = new HashSet();
    LANGUAGES_US_ONLY.add("en-US".toLowerCase());
    LANGUAGES_CHINESE_ONLY = new HashSet();
    LANGUAGES_CHINESE_ONLY.add("zh-CN".toLowerCase());
    LANGUAGES_ALL = new HashSet();
    LANGUAGES_ALL.addAll(LANGUAGES_EFIGS);
    LANGUAGES_ALL.addAll(LANGUAGES_CJK);
    LANGUAGES_ALL.add("ru-RU".toLowerCase());
    LANGUAGES_ALL.add("pt-BR".toLowerCase());
    LANGUAGES_EFIGSJK = new HashSet();
    LANGUAGES_EFIGSJK.addAll(LANGUAGES_EFIGS);
    LANGUAGES_EFIGSJK.add("ko-KR".toLowerCase());
    LANGUAGES_EFIGSJK.add("ja-JP".toLowerCase());
    DEFAULT_ASR_MANAGER = SRManager.isAsrManager();
    contactNameList = new ArrayList();
    songNameList = new ArrayList();
    albumNameList = new ArrayList();
    artistNameList = new ArrayList();
    playlistNameList = new ArrayList();
    contextForJUnit = null;
  }

  // ERROR //
  private static void clearDataValuesDatabase(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ldc_w 1144
    //   6: iconst_0
    //   7: aconst_null
    //   8: invokevirtual 1150	android/content/Context:openOrCreateDatabase	(Ljava/lang/String;ILandroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   11: astore_1
    //   12: aload_1
    //   13: ldc_w 1152
    //   16: invokevirtual 1158	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   19: aload_1
    //   20: ifnull +7 -> 27
    //   23: aload_1
    //   24: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   27: return
    //   28: astore 4
    //   30: aload_1
    //   31: ifnull -4 -> 27
    //   34: aload_1
    //   35: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   38: goto -11 -> 27
    //   41: astore 5
    //   43: goto -16 -> 27
    //   46: astore_2
    //   47: aload_1
    //   48: ifnull +7 -> 55
    //   51: aload_1
    //   52: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   55: aload_2
    //   56: athrow
    //   57: astore 6
    //   59: goto -32 -> 27
    //   62: astore_3
    //   63: goto -8 -> 55
    //
    // Exception table:
    //   from	to	target	type
    //   2	19	28	java/lang/Exception
    //   34	38	41	java/lang/Exception
    //   2	19	46	finally
    //   23	27	57	java/lang/Exception
    //   51	55	62	java/lang/Exception
  }

  public static void commitBatchEdit(SharedPreferences.Editor paramEditor)
  {
    paramEditor.commit();
  }

  private static String convertToISOLanguage(String paramString)
  {
    if ((paramString.equals("v-es-LA")) || (paramString.equals("v-es-NA")))
      paramString = "es-US";
    return paramString;
  }

  public static void disableDrivingModeSetting()
  {
    ClientSuppliedValues.disableDrivingModeSetting();
  }

  public static void enableDrivingModeSetting()
  {
    ClientSuppliedValues.enableDrivingModeSetting();
  }

  public static ArrayList<String> getAlbumNameList()
  {
    return albumNameList;
  }

  public static String getAppID()
  {
    return getUUIDDeviceID();
  }

  public static ArrayList<String> getArtistNameList()
  {
    return artistNameList;
  }

  public static boolean getBoolean(String paramString, boolean paramBoolean)
  {
    return getSharedPreferences().getBoolean(paramString, paramBoolean);
  }

  public static ArrayList<String> getContactNameList()
  {
    return contactNameList;
  }

  private static Context getContext()
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    if (localContext == null)
      localContext = getContextForJUnit();
    return localContext;
  }

  public static Context getContextForJUnit()
  {
    return contextForJUnit;
  }

  public static Locale getCurrentLocale()
  {
    return getLocaleForIsoLanguage(getISOLanguage());
  }

  // ERROR //
  public static byte[] getData(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: invokestatic 1228	com/vlingo/core/internal/settings/Settings:getDataValuesDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_1
    //   8: iconst_1
    //   9: anewarray 1068	java/lang/String
    //   12: astore 8
    //   14: aload 8
    //   16: iconst_0
    //   17: ldc_w 1230
    //   20: aastore
    //   21: aload_1
    //   22: ldc_w 1232
    //   25: aload 8
    //   27: new 1234	java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial 1235	java/lang/StringBuilder:<init>	()V
    //   34: ldc_w 1237
    //   37: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_0
    //   41: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: ldc_w 1243
    //   47: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 1246	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: aconst_null
    //   54: aconst_null
    //   55: aconst_null
    //   56: aconst_null
    //   57: invokevirtual 1250	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   60: astore_2
    //   61: aload_2
    //   62: invokeinterface 1255 1 0
    //   67: pop
    //   68: aload_2
    //   69: invokeinterface 1259 1 0
    //   74: ifle +37 -> 111
    //   77: aload_2
    //   78: iconst_0
    //   79: invokeinterface 1263 2 0
    //   84: astore 11
    //   86: aload 11
    //   88: astore 6
    //   90: aload_2
    //   91: ifnull +9 -> 100
    //   94: aload_2
    //   95: invokeinterface 1264 1 0
    //   100: aload_1
    //   101: ifnull +7 -> 108
    //   104: aload_1
    //   105: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   108: aload 6
    //   110: areturn
    //   111: aload_2
    //   112: ifnull +9 -> 121
    //   115: aload_2
    //   116: invokeinterface 1264 1 0
    //   121: aload_1
    //   122: ifnull +7 -> 129
    //   125: aload_1
    //   126: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   129: aconst_null
    //   130: astore 6
    //   132: goto -24 -> 108
    //   135: astore 5
    //   137: aload 5
    //   139: invokevirtual 1267	java/lang/Exception:printStackTrace	()V
    //   142: aload_2
    //   143: ifnull +9 -> 152
    //   146: aload_2
    //   147: invokeinterface 1264 1 0
    //   152: aload_1
    //   153: ifnull -24 -> 129
    //   156: aload_1
    //   157: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   160: goto -31 -> 129
    //   163: astore_3
    //   164: aload_2
    //   165: ifnull +9 -> 174
    //   168: aload_2
    //   169: invokeinterface 1264 1 0
    //   174: aload_1
    //   175: ifnull +7 -> 182
    //   178: aload_1
    //   179: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   182: aload_3
    //   183: athrow
    //   184: astore 12
    //   186: goto -86 -> 100
    //   189: astore 10
    //   191: goto -70 -> 121
    //   194: astore 7
    //   196: goto -44 -> 152
    //   199: astore 4
    //   201: goto -27 -> 174
    //
    // Exception table:
    //   from	to	target	type
    //   4	86	135	java/lang/Exception
    //   4	86	163	finally
    //   137	142	163	finally
    //   94	100	184	java/lang/Exception
    //   115	121	189	java/lang/Exception
    //   146	152	194	java/lang/Exception
    //   168	174	199	java/lang/Exception
  }

  private static SQLiteDatabase getDataValuesDatabase()
  {
    SQLiteDatabase localSQLiteDatabase = getContext().openOrCreateDatabase("VlingoDataSettings", 0, null);
    localSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS dataValues (setting_key VARCHAR(255), setting_data BLOB)");
    return localSQLiteDatabase;
  }

  public static String getEnum(String paramString1, String paramString2)
  {
    return getSharedPreferences().getString(paramString1, paramString2);
  }

  public static float getFloat(String paramString, float paramFloat)
  {
    return getSharedPreferences().getFloat(paramString, paramFloat);
  }

  public static String getISOLanguage()
  {
    return getISOLanguage(getLanguageApplication());
  }

  public static String getISOLanguage(String paramString)
  {
    return convertToISOLanguage(paramString);
  }

  public static Bitmap getImage(String paramString)
  {
    Bitmap localBitmap = null;
    byte[] arrayOfByte = getData(paramString);
    if (arrayOfByte != null)
      localBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
    return localBitmap;
  }

  public static int getInt(String paramString, int paramInt)
  {
    return getSharedPreferences().getInt(paramString, paramInt);
  }

  public static String getLanguageApplication()
  {
    String str = getString("language", null);
    if (str == null)
    {
      Locale localLocale = Locale.getDefault();
      CharSequence[] arrayOfCharSequence = getSupportedLanguages();
      for (LanguageLocaleMapping localLanguageLocaleMapping : LanguageLocaleMapping.values())
      {
        if (!localLanguageLocaleMapping.locale.equals(localLocale))
          continue;
        int k = arrayOfCharSequence.length;
        for (int m = 0; m < k; m++)
        {
          CharSequence localCharSequence = arrayOfCharSequence[m];
          if (!localLanguageLocaleMapping.language.equals(localCharSequence))
            continue;
          str = localLanguageLocaleMapping.language;
        }
      }
    }
    if (!StringUtils.isNullOrWhiteSpace(str));
    while (true)
    {
      return str;
      str = LanguageDefaulter.getDefaultLanguage(ApplicationAdapter.getInstance().getApplicationContext());
    }
  }

  public static Locale getLocaleForIsoLanguage(String paramString)
  {
    String[] arrayOfString = paramString.split("-");
    return new Locale(arrayOfString[0], arrayOfString[1]);
  }

  public static long getLong(String paramString, long paramLong)
  {
    return getSharedPreferences().getLong(paramString, paramLong);
  }

  public static int getMultiWidgetItemsInitialMax()
  {
    int i = -1;
    String str = getString("multi.widget.item.initial.max", "6");
    if (str != null)
    {
      int j = Integer.parseInt(str);
      if (j > 0)
        i = j;
    }
    return i;
  }

  public static int getMultiWidgetItemsUltimateMax()
  {
    int i = -1;
    String str = getString("multi.widget.item.ultimate.max", "20");
    if (str != null)
    {
      int j = Integer.parseInt(str);
      if (j > 0)
        i = j;
    }
    return i;
  }

  public static ArrayList<String> getPlaylistNameList()
  {
    return playlistNameList;
  }

  public static SharedPreferences getSharedPreferences()
  {
    return PreferenceManager.getDefaultSharedPreferences(getContext());
  }

  public static ArrayList<String> getSongNameList()
  {
    return songNameList;
  }

  public static String getString(String paramString1, String paramString2)
  {
    return getSharedPreferences().getString(paramString1, paramString2);
  }

  public static CharSequence[] getSupportedLanguageDescriptions()
  {
    int i = maxNumLanguages;
    if (i > languageEnables.length)
      i = languageEnables.length;
    if (i > languages.length)
      i = languages.length;
    if (i > languageDescriptions.length)
      i = languageDescriptions.length;
    boolean bool = getBoolean("show_all_languages", false);
    ArrayList localArrayList = new ArrayList();
    for (int j = 0; j < i; j++)
    {
      int k = ((Integer)langIsoToEnable.get(languages[j])).intValue();
      if (!getBoolean(languageEnables[k], bool))
        continue;
      localArrayList.add(languageDescriptions[j]);
    }
    CharSequence[] arrayOfCharSequence = new CharSequence[localArrayList.size()];
    localArrayList.toArray(arrayOfCharSequence);
    return arrayOfCharSequence;
  }

  public static CharSequence[] getSupportedLanguages()
  {
    int i = maxNumLanguages;
    if (i > languageEnables.length)
      i = languageEnables.length;
    if (i > languages.length)
      i = languages.length;
    boolean bool = getBoolean("show_all_languages", false);
    ArrayList localArrayList = new ArrayList();
    for (int j = 0; j < i; j++)
    {
      int k = ((Integer)langIsoToEnable.get(languages[j])).intValue();
      if (!getBoolean(languageEnables[k], bool))
        continue;
      localArrayList.add(languages[j]);
    }
    CharSequence[] arrayOfCharSequence = new CharSequence[localArrayList.size()];
    localArrayList.toArray(arrayOfCharSequence);
    return arrayOfCharSequence;
  }

  public static String getUUIDDeviceID()
  {
    return VLSdk.getInstance().getDeviceID();
  }

  public static boolean hasSetting(String paramString)
  {
    if (getSharedPreferences().getAll().get(paramString) != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static void init(Context paramContext)
  {
    langIsoToEnable.put("en-US", Integer.valueOf(0));
    langIsoToEnable.put("en-GB", Integer.valueOf(1));
    langIsoToEnable.put("de-DE", Integer.valueOf(2));
    langIsoToEnable.put("es-ES", Integer.valueOf(3));
    langIsoToEnable.put("fr-FR", Integer.valueOf(4));
    langIsoToEnable.put("it-IT", Integer.valueOf(5));
    langIsoToEnable.put("ko-KR", Integer.valueOf(6));
    langIsoToEnable.put("zh-CN", Integer.valueOf(7));
    langIsoToEnable.put("ja-JP", Integer.valueOf(8));
    langIsoToEnable.put("v-es-LA", Integer.valueOf(9));
    langIsoToEnable.put("v-es-NA", Integer.valueOf(10));
    langIsoToEnable.put("ru-RU", Integer.valueOf(11));
    langIsoToEnable.put("pt-BR", Integer.valueOf(12));
    String[] arrayOfString1 = VlingoAndroidCore.getResourceProvider().getStringArray(ResourceIdProvider.array.core_languages_names);
    String[] arrayOfString2 = VlingoAndroidCore.getResourceProvider().getStringArray(ResourceIdProvider.array.core_languages_iso);
    maxNumLanguages = arrayOfString1.length;
    languageDescriptions = new String[maxNumLanguages];
    languages = new String[maxNumLanguages];
    int i = 0;
    int j = 0;
    while (j < arrayOfString2.length)
    {
      String str4 = arrayOfString2[j];
      String[] arrayOfString4 = languages;
      int i2 = i + 1;
      arrayOfString4[i] = str4;
      j++;
      i = i2;
    }
    int k = arrayOfString1.length;
    int m = 0;
    int i1;
    for (int n = 0; m < k; n = i1)
    {
      String str3 = arrayOfString1[m];
      String[] arrayOfString3 = languageDescriptions;
      i1 = n + 1;
      arrayOfString3[n] = str3;
      m++;
    }
    setLanguageApplication(getLanguageApplication(), paramContext);
    boolean bool = getBoolean("appstate.first_run.calypso", true);
    if ((bool) || (!hasSetting(languageEnables[0])))
    {
      setBoolean("en-US_enable", true);
      setBoolean("en-GB_enable", true);
      setBoolean("de-DE_enable", true);
      setBoolean("es-ES_enable", true);
      setBoolean("v-es-LA_enable", true);
      setBoolean("v-es-NA_enable", true);
      setBoolean("fr-FR_enable", true);
      setBoolean("it-IT_enable", true);
      setBoolean("ko-KR_enable", true);
      setBoolean("zh-CN_enable", true);
      setBoolean("ru-RU_enable", true);
      setBoolean("pt-BR_enable", false);
      setBoolean("ja-JP_enable", false);
    }
    if (bool)
    {
      SharedPreferences.Editor localEditor = startBatchEdit();
      setBoolean("appstate.first_run.calypso", false);
      setBoolean("hello_request_complete", false);
      setBoolean("config.fetch.disable", false);
      setBoolean("location_enabled", false);
      setBoolean("car_safereader_enable_alert", true);
      setInt("endpoint.time_withoutspeech", 5000);
      setInt("endpoint.time.speech.short", 400);
      setInt("endpoint.time.speech.medium", 750);
      setInt("endpoint.time.speech.long", 1750);
      setBoolean("multi.widget.client.capped", true);
      setBoolean("multi.widget.client.showmorebutton", false);
      setBoolean("multi.widget.client.showcounts", false);
      setBoolean("multi.widget.client.collapse", true);
      setString("multi.widget.item.initial.max", "6");
      setString("multi.widget.item.ultimate.max", "20");
      setString("FIELD_ID", "dm_main");
      setFloat("phrasespot_beam", 20.0F);
      setFloat("phrasespot_absbeam", 40.0F);
      setFloat("phrasespot_aoffset", 0.0F);
      setFloat("phrasespot_delay", 100.0F);
      setFloat("phrasespot_parama", 0.0F);
      setFloat("phrasespot_paramb", 320.0F);
      setFloat("phrasespot_paramc", 500.0F);
      setBoolean("phrasespot_waveformlogging_enabled", false);
      setInt("speex.quality", 8);
      setInt("speex.variable_bitrate", 0);
      setInt("speex.voice_activity_detection", 1);
      setInt("speex.complexity", 3);
      Context localContext = getContext();
      String str1 = localContext.getString(VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_util_WEB_SEARCH_NAME_DEFAULT));
      String str2 = localContext.getString(VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_DEFAULT));
      setString("web_search_engine", str1);
      setString("web_search_url", str2);
      setBoolean("use_default_phone", true);
      setBoolean("car_safereader_off_when_silent", false);
      setBoolean("safereader_start_on_boot", true);
      setBoolean("tts_local_required_engine", false);
      setBoolean("tts_local_ignore_use_speech_rate", false);
      setString("tts_local_tts_fallback_engine", "com.google.android.tts");
      setBoolean("car_iux_tts_cacheing_required", true);
      setInt("network_tts_timeout", 5000);
      setString("weather_version_two_languages", "ko-KR");
      setString("weather_use_vp_location", "all");
      commitBatchEdit(localEditor);
    }
  }

  public static boolean isAsrEditingEnabled()
  {
    if ((getString("asr.editing.enabled.languages", "All").toLowerCase().contains(getLanguageApplication().toLowerCase())) || (getString("asr.editing.enabled.languages", "All").equals("All")));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isDrivingModeEnabled()
  {
    return ClientSuppliedValues.isDrivingModeEnabled();
  }

  public static boolean isLocationEnabled()
  {
    return getBoolean("location_enabled", false);
  }

  public static boolean isSafeReaderMessageBodyEnabled()
  {
    return getBoolean("car_safereader_read_message", false);
  }

  public static boolean isSafereaderAlertEnabled()
  {
    return isDrivingModeEnabled() & getBoolean("car_safereader_enable_alert", true);
  }

  public static void notifySafeReaderEmailAccountsChanged()
  {
    setString("safereader_email_accounts_changed", Long.toString(System.currentTimeMillis()));
  }

  public static void setAlbumNameList(ArrayList<String> paramArrayList)
  {
    albumNameList = paramArrayList;
  }

  public static void setArtistNameList(ArrayList<String> paramArrayList)
  {
    artistNameList = paramArrayList;
  }

  public static void setBoolean(SharedPreferences.Editor paramEditor, String paramString, boolean paramBoolean)
  {
    paramEditor.putBoolean(paramString, paramBoolean);
  }

  public static void setBoolean(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }

  public static void setContactNameList(ArrayList<String> paramArrayList)
  {
    contactNameList = paramArrayList;
  }

  public static void setContextForJUnit(Context paramContext)
  {
    contextForJUnit = paramContext;
  }

  // ERROR //
  public static void setData(String paramString, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +251 -> 252
    //   4: aload_1
    //   5: arraylength
    //   6: ifle +246 -> 252
    //   9: aconst_null
    //   10: astore 8
    //   12: aconst_null
    //   13: astore 9
    //   15: new 1529	android/content/ContentValues
    //   18: dup
    //   19: invokespecial 1530	android/content/ContentValues:<init>	()V
    //   22: astore 10
    //   24: aload 10
    //   26: ldc_w 1230
    //   29: aload_1
    //   30: invokevirtual 1532	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   33: aload 10
    //   35: ldc_w 1534
    //   38: aload_0
    //   39: invokevirtual 1536	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   42: invokestatic 1228	com/vlingo/core/internal/settings/Settings:getDataValuesDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   45: astore 8
    //   47: iconst_1
    //   48: anewarray 1068	java/lang/String
    //   51: astore 17
    //   53: aload 17
    //   55: iconst_0
    //   56: ldc_w 1230
    //   59: aastore
    //   60: aload 8
    //   62: ldc_w 1232
    //   65: aload 17
    //   67: new 1234	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 1235	java/lang/StringBuilder:<init>	()V
    //   74: ldc_w 1237
    //   77: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: aload_0
    //   81: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: ldc_w 1243
    //   87: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 1246	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: aconst_null
    //   94: aconst_null
    //   95: aconst_null
    //   96: aconst_null
    //   97: invokevirtual 1250	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   100: astore 9
    //   102: aload 9
    //   104: invokeinterface 1259 1 0
    //   109: ifne +38 -> 147
    //   112: aload 8
    //   114: ldc_w 1232
    //   117: aconst_null
    //   118: aload 10
    //   120: invokevirtual 1540	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   123: pop2
    //   124: aload 9
    //   126: ifnull +10 -> 136
    //   129: aload 9
    //   131: invokeinterface 1264 1 0
    //   136: aload 8
    //   138: ifnull +8 -> 146
    //   141: aload 8
    //   143: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   146: return
    //   147: aload 8
    //   149: ldc_w 1232
    //   152: aload 10
    //   154: new 1234	java/lang/StringBuilder
    //   157: dup
    //   158: invokespecial 1235	java/lang/StringBuilder:<init>	()V
    //   161: ldc_w 1237
    //   164: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: aload_0
    //   168: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: ldc_w 1243
    //   174: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual 1246	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: aconst_null
    //   181: invokevirtual 1544	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   184: pop
    //   185: goto -61 -> 124
    //   188: astore 14
    //   190: aload 14
    //   192: invokevirtual 1545	android/database/SQLException:printStackTrace	()V
    //   195: aload 9
    //   197: ifnull +10 -> 207
    //   200: aload 9
    //   202: invokeinterface 1264 1 0
    //   207: aload 8
    //   209: ifnull -63 -> 146
    //   212: aload 8
    //   214: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   217: goto -71 -> 146
    //   220: astore 15
    //   222: goto -76 -> 146
    //   225: astore 11
    //   227: aload 9
    //   229: ifnull +10 -> 239
    //   232: aload 9
    //   234: invokeinterface 1264 1 0
    //   239: aload 8
    //   241: ifnull +8 -> 249
    //   244: aload 8
    //   246: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   249: aload 11
    //   251: athrow
    //   252: aconst_null
    //   253: astore_2
    //   254: invokestatic 1228	com/vlingo/core/internal/settings/Settings:getDataValuesDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   257: astore_2
    //   258: aload_2
    //   259: new 1234	java/lang/StringBuilder
    //   262: dup
    //   263: invokespecial 1235	java/lang/StringBuilder:<init>	()V
    //   266: ldc_w 1547
    //   269: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: aload_0
    //   273: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: ldc_w 1243
    //   279: invokevirtual 1241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: invokevirtual 1246	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   285: invokevirtual 1158	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   288: aload_2
    //   289: ifnull -143 -> 146
    //   292: aload_2
    //   293: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   296: goto -150 -> 146
    //   299: astore 7
    //   301: goto -155 -> 146
    //   304: astore 5
    //   306: aload 5
    //   308: invokevirtual 1545	android/database/SQLException:printStackTrace	()V
    //   311: aload_2
    //   312: ifnull -166 -> 146
    //   315: aload_2
    //   316: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   319: goto -173 -> 146
    //   322: astore 6
    //   324: goto -178 -> 146
    //   327: astore_3
    //   328: aload_2
    //   329: ifnull +7 -> 336
    //   332: aload_2
    //   333: invokevirtual 1161	android/database/sqlite/SQLiteDatabase:close	()V
    //   336: aload_3
    //   337: athrow
    //   338: astore 20
    //   340: goto -204 -> 136
    //   343: astore 19
    //   345: goto -199 -> 146
    //   348: astore 16
    //   350: goto -143 -> 207
    //   353: astore 13
    //   355: goto -116 -> 239
    //   358: astore 12
    //   360: goto -111 -> 249
    //   363: astore 4
    //   365: goto -29 -> 336
    //
    // Exception table:
    //   from	to	target	type
    //   15	124	188	android/database/SQLException
    //   147	185	188	android/database/SQLException
    //   212	217	220	java/lang/Exception
    //   15	124	225	finally
    //   147	185	225	finally
    //   190	195	225	finally
    //   292	296	299	java/lang/Exception
    //   254	288	304	android/database/SQLException
    //   315	319	322	java/lang/Exception
    //   254	288	327	finally
    //   306	311	327	finally
    //   129	136	338	java/lang/Exception
    //   141	146	343	java/lang/Exception
    //   200	207	348	java/lang/Exception
    //   232	239	353	java/lang/Exception
    //   244	249	358	java/lang/Exception
    //   332	336	363	java/lang/Exception
  }

  public static void setFloat(SharedPreferences.Editor paramEditor, String paramString, float paramFloat)
  {
    paramEditor.putFloat(paramString, paramFloat);
  }

  public static void setFloat(String paramString, float paramFloat)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putFloat(paramString, paramFloat);
    localEditor.commit();
  }

  // ERROR //
  public static void setImage(String paramString, Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +65 -> 66
    //   4: new 1558	java/io/ByteArrayOutputStream
    //   7: dup
    //   8: invokespecial 1559	java/io/ByteArrayOutputStream:<init>	()V
    //   11: astore_2
    //   12: aload_1
    //   13: getstatic 1565	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   16: bipush 100
    //   18: aload_2
    //   19: invokevirtual 1571	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   22: pop
    //   23: aload_2
    //   24: invokevirtual 1574	java/io/ByteArrayOutputStream:flush	()V
    //   27: aload_2
    //   28: invokevirtual 1575	java/io/ByteArrayOutputStream:close	()V
    //   31: aload_0
    //   32: aload_2
    //   33: invokevirtual 1579	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   36: invokestatic 1581	com/vlingo/core/internal/settings/Settings:setData	(Ljava/lang/String;[B)V
    //   39: return
    //   40: astore 5
    //   42: aload 5
    //   44: invokevirtual 1582	java/io/IOException:printStackTrace	()V
    //   47: aload_2
    //   48: invokevirtual 1575	java/io/ByteArrayOutputStream:close	()V
    //   51: goto -20 -> 31
    //   54: astore 6
    //   56: goto -25 -> 31
    //   59: astore_3
    //   60: aload_2
    //   61: invokevirtual 1575	java/io/ByteArrayOutputStream:close	()V
    //   64: aload_3
    //   65: athrow
    //   66: aload_0
    //   67: aconst_null
    //   68: invokestatic 1581	com/vlingo/core/internal/settings/Settings:setData	(Ljava/lang/String;[B)V
    //   71: goto -32 -> 39
    //   74: astore 8
    //   76: goto -45 -> 31
    //   79: astore 4
    //   81: goto -17 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   12	27	40	java/io/IOException
    //   47	51	54	java/io/IOException
    //   12	27	59	finally
    //   42	47	59	finally
    //   27	31	74	java/io/IOException
    //   60	64	79	java/io/IOException
  }

  public static void setInt(SharedPreferences.Editor paramEditor, String paramString, int paramInt)
  {
    paramEditor.putInt(paramString, paramInt);
  }

  public static void setInt(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public static void setLanguage(String paramString, Context paramContext)
  {
    setLanguageApplication(paramString, paramContext);
  }

  public static void setLanguageApplication(String paramString, Context paramContext)
  {
    if (paramString.equals(currentLanguageApplication))
      return;
    LanguageChangeReceiver.notifyLanguageChanged(paramString, paramContext);
    currentLanguageApplication = paramString;
    setString("language", paramString);
    String str;
    if (!VlingoAndroidCore.isChineseBuild())
    {
      if (!currentLanguageApplication.equalsIgnoreCase("zh-CN"))
        break label63;
      str = "Baidu";
    }
    while (true)
    {
      setString("web_search_engine", str);
      updateCurrentLocale(paramContext);
      break;
      label63: if (currentLanguageApplication.equalsIgnoreCase("ko-KR"))
      {
        str = "Naver";
        continue;
      }
      str = "Google";
    }
  }

  public static void setLocationEnabled(boolean paramBoolean)
  {
    setBoolean("location_enabled", paramBoolean);
  }

  public static void setLong(SharedPreferences.Editor paramEditor, String paramString, long paramLong)
  {
    paramEditor.putLong(paramString, paramLong);
  }

  public static void setLong(String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }

  public static void setPlaylistNameList(ArrayList<String> paramArrayList)
  {
    playlistNameList = paramArrayList;
  }

  public static void setSafeReaderMessageBodyEnabled(boolean paramBoolean)
  {
    setBoolean("car_safereader_read_message", paramBoolean);
  }

  public static void setSafereaderAlertEnabled(boolean paramBoolean)
  {
    setBoolean("car_safereader_enable_alert", paramBoolean);
  }

  public static void setSongNameList(ArrayList<String> paramArrayList)
  {
    songNameList = paramArrayList;
  }

  public static void setString(SharedPreferences.Editor paramEditor, String paramString1, String paramString2)
  {
    paramEditor.putString(paramString1, paramString2);
  }

  public static void setString(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public static SharedPreferences.Editor startBatchEdit()
  {
    return getSharedPreferences().edit();
  }

  public static void toggleDrivingMode()
  {
    if (isDrivingModeEnabled())
      disableDrivingModeSetting();
    while (true)
    {
      return;
      enableDrivingModeSetting();
    }
  }

  protected static void updateCurrentLocale(Context paramContext)
  {
    updateCurrentLocale(paramContext.getResources());
  }

  protected static void updateCurrentLocale(Resources paramResources)
  {
    if (ClientSuppliedValues.isLanguageChangeAllowed())
    {
      String str = getISOLanguage();
      float f = ApplicationAdapter.getInstance().getApplicationContext().getResources().getConfiguration().fontScale;
      Locale localLocale = getLocaleForIsoLanguage(str);
      Locale.setDefault(localLocale);
      Configuration localConfiguration = new Configuration();
      localConfiguration.locale = localLocale;
      localConfiguration.fontScale = f;
      paramResources.updateConfiguration(localConfiguration, null);
    }
  }

  public static boolean useWeatherVpLocation()
  {
    if ((getString("weather_use_vp_location", "All").toLowerCase().contains(getLanguageApplication().toLowerCase())) || (getString("weather_use_vp_location", "All").equalsIgnoreCase("All")));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void resetAllSettings(Context paramContext)
  {
    clearDataValuesDatabase(paramContext);
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.clear();
    localEditor.commit();
    init(paramContext);
  }

  static enum LanguageLocaleMapping
  {
    private final String language;
    private Locale locale;

    static
    {
      CHINA = new LanguageLocaleMapping("CHINA", 1, "zh-CN", Locale.CHINA);
      GERMANY = new LanguageLocaleMapping("GERMANY", 2, "de-DE", Locale.GERMANY);
      FRANCE = new LanguageLocaleMapping("FRANCE", 3, "fr-FR", Locale.FRANCE);
      ITALY = new LanguageLocaleMapping("ITALY", 4, "it-IT", Locale.ITALY);
      JAPAN = new LanguageLocaleMapping("JAPAN", 5, "ja-JP", Locale.JAPAN);
      RUSSIA = new LanguageLocaleMapping("RUSSIA", 6, "ru-RU", new Locale("ru", "RU"));
      UK = new LanguageLocaleMapping("UK", 7, "en-GB", Locale.UK);
      US = new LanguageLocaleMapping("US", 8, "en-US", Locale.US);
      SPAIN = new LanguageLocaleMapping("SPAIN", 9, "es-ES", new Locale("es", "ES"));
      SPAIN_LATIN = new LanguageLocaleMapping("SPAIN_LATIN", 10, "v-es-LA", new Locale("es", "US"));
      SPAIN_US = new LanguageLocaleMapping("SPAIN_US", 11, "v-es-NA", new Locale("es", "US"));
      BRAZIL = new LanguageLocaleMapping("BRAZIL", 12, "pt-BR", new Locale("pt", "BR"));
      LanguageLocaleMapping[] arrayOfLanguageLocaleMapping = new LanguageLocaleMapping[13];
      arrayOfLanguageLocaleMapping[0] = KOREA;
      arrayOfLanguageLocaleMapping[1] = CHINA;
      arrayOfLanguageLocaleMapping[2] = GERMANY;
      arrayOfLanguageLocaleMapping[3] = FRANCE;
      arrayOfLanguageLocaleMapping[4] = ITALY;
      arrayOfLanguageLocaleMapping[5] = JAPAN;
      arrayOfLanguageLocaleMapping[6] = RUSSIA;
      arrayOfLanguageLocaleMapping[7] = UK;
      arrayOfLanguageLocaleMapping[8] = US;
      arrayOfLanguageLocaleMapping[9] = SPAIN;
      arrayOfLanguageLocaleMapping[10] = SPAIN_LATIN;
      arrayOfLanguageLocaleMapping[11] = SPAIN_US;
      arrayOfLanguageLocaleMapping[12] = BRAZIL;
      $VALUES = arrayOfLanguageLocaleMapping;
    }

    private LanguageLocaleMapping(String paramString, Locale paramLocale)
    {
      this.language = paramString;
      this.locale = paramLocale;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.Settings
 * JD-Core Version:    0.6.0
 */