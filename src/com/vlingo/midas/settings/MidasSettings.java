package com.vlingo.midas.settings;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.provider.Settings.System;
import android.text.format.Time;
import android.util.Log;
import android.view.ViewConfiguration;
import com.vlingo.core.internal.settings.DynamicFeatureConfig;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.ui.PackageInfoProvider;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MidasSettings extends Settings
{
  public static final String CLIENT_VERSION_JPROJECT = "JProject";
  public static final String CLIENT_VERSION_MIDAS = "Midas";
  public static final String CLIENT_VERSION_Q2 = "Q2";
  public static final String CUSTOMIZE_WAKE_UP_COMMAND = "customize_wake_up_command";
  public static final String DEFAULT_CLIENT_VERSION = "Q2";
  public static final boolean DEFAULT_DETAILED_TTS_FEEDBACK = false;
  public static final boolean DEFAULT_IS_EYES_FREE_MODE = false;
  public static final String DEFAULT_NETWORK_TIMEOUT_STRING = "750";
  public static final int DEFAULT_SAFEREADER_DELAY = 7000;
  public static final boolean DEFAULT_USE_ECHO_CANCEL_FOR_SPOTTER = true;
  public static final int DEFAULT_WIDGET_DISPLAY_MAX = 0;
  public static final int DRIVING_MODE_WIDGET_DISPLAY_MAX = 3;
  public static String FACEBOOK_APP_ID_DEFAULT;
  public static final String IN_CAR_MODE = "in_car_mode";
  public static final String KEY_ASR_SERVER_HOST = "SERVER_NAME";
  public static final String KEY_CAR_MOTION = "car_word_spotter_motion";
  public static final String KEY_CAR_NAV_OFFICE_ADDRESS = "car_nav_office_address";
  public static final String KEY_CLIENT_VERSION = "CLIENT_VERSION";
  public static final String KEY_DETAILED_TTS_FEEDBACK = "detailed_tts_feedback";
  public static final String KEY_ENABLE_SVOX = "KEY_ENABLE_SVOX_NEW";
  public static final String KEY_HEADSET_MODE = "headset_mode";
  public static final String KEY_HELLO_SERVER_HOST = "HELLO_HOST_NAME";
  public static final String KEY_HELP_VISIBLE = "key_help_visible";
  public static final String KEY_HELP_VOICE_TALK = "help_voice_talk";
  public static final String KEY_IS_EYES_FREE_MODE = "is_eyes_free_mode";
  public static final String KEY_LAST_ASR_RESULT = "last_asr_result";
  public static final String KEY_LAUNCH_CAR_ON_CAR_DOCK = "launch_car_on_car_dock";
  public static final String KEY_LAUNCH_VOICETALK = "launch_voicetalk";
  public static final String KEY_LMTT_SERVER_HOST = "LMTT_HOST_NAME";
  public static final String KEY_LMTT_UPDATE_VERSION_CURRENT = "lmtt.update.version";
  public static final String KEY_LMTT_UPDATE_VERSION_PREVIOUS = ".previous";
  public static final String KEY_LOG_SERVER_HOST = "EVENTLOG_HOST_NAME";
  public static final String KEY_MANUALLY_TURNED_ON_PROMPT_IN_TALKBACK = "manually_prompt_on_in_talkback";
  public static final String KEY_MIDAS_DOMAIN_LIST = "midas_supported_domain_list";
  public static final String KEY_MIDAS_FIRST_RUN = "key_midas_first_run";
  public static final String KEY_MIDAS_MODEL_NAME = "midas_model_name";
  public static final String KEY_POPUP_WINDOW_OPENED = "key_popup_window_opened";
  private static final String KEY_SAMSUNG_DISCLAIMER_ACCEPTED = "samsung_disclaimer_accepted";
  public static final String KEY_SAMSUNG_MULTI_ENGINE_ENABLE = "samsung_multi_engine_enable";
  public static final String KEY_SAMSUNG_WAKEUP_DATA_ENABLE = "samsung_wakeup_data_enable";
  public static final String KEY_SAMSUNG_WAKEUP_ENGINE_ENABLE = "samsung_wakeup_engine_enable";
  public static final String KEY_SAY_WAKE_UP_COMMAND = "say_wake_up_command";
  public static final String KEY_SEAMLESS_WAKEUP = "seamless_wakeup";
  public static final String KEY_SET_WAKE_UP_COMMAND = "key_set_wake_up_command";
  public static final String KEY_SHOW_USER_TURN_IN_DRIVING_MODE = "show_user_turn_in_driving_mode";
  public static final String KEY_SOCIAL_SETTINGS = "social_settings";
  public static final String KEY_SOCIAL_SETTINGS_CATEGORY = "social_settings_category";
  public static final String KEY_START_FROM_OPTION_MENU = "is_start_option_menu";
  public static final String KEY_TTS_NETWORK_TIMEOUT = "tts_network_timeout";
  public static final String KEY_USE_ECHO_CANCEL_FOR_SPOTTER = "use_echo_cancel_for_spotter";
  public static final String KEY_USE_NON_J_AUDIO_SOURCES = "use_non_j_audio_sources";
  public static final String KEY_VCS_SERVER_HOST = "SERVICES_HOST_NAME";
  public static final String KEY_VLINGO_CAR_NAV_SETTINGS = "car_nav_settings";
  public static final String KEY_VLINGO_CAR_SETTINGS = "vlingo_car_settings";
  public static final String KEY_VLINGO_FACEBOOK = "facebook_preference_category";
  public static final String KEY_VLINGO_SETTINGS = "vlingo_settings";
  public static final String KEY_VLINGO_TWITTER = "twitter_preference_category";
  public static final String KEY_VLINGO_VOICE_COMMAND = "voicecommand";
  public static final String KEY_WAKE_UP_AND_AUTOFUNCTION1 = "kew_wake_up_and_auto_function1";
  public static final String KEY_WAKE_UP_AND_AUTOFUNCTION2 = "kew_wake_up_and_auto_function2";
  public static final String KEY_WAKE_UP_AND_AUTOFUNCTION3 = "kew_wake_up_and_auto_function3";
  public static final String KEY_WAKE_UP_AND_AUTOFUNCTION4 = "kew_wake_up_and_auto_function4";
  public static final String KEY_WAKE_UP_LOCK_SCREEN = "wake_up_lock_screen";
  public static final int MAIN_MENU = 1;
  public static final int REGULAR_WIDGET_DISPLAY_MAX = 6;
  public static final List<String> SERVER_URL_KEYS;
  public static final String TEMP_INPUT_VOICE_CONTROL = "temp_input_voice_control";
  public static final String TWITTER_CONSUMER_KEY_DEFAULT = "AGv8Ps3AlFKrf2C1YoFkQ";
  public static final String TWITTER_CONSUMER_SECRET_DEFAULT = "qeX5TCXa9HPDlpNmhPACOT7sUerHPmD91Oq9nYuw6Q";
  public static final String WAKE_UP_DEFAULT_ENGINE = "wake_up_default_engine";
  public static final String WEIBO_APP_ID_DEFAULT = "2291996457";
  public static final String WEIBO_REDIRECT_URL_DEFAULT = "http://www.samsung.com/sec";
  static boolean hasHWKey;
  static boolean isSoftKeyEnabledKnown;
  private static final String[] locales;
  private static boolean loggedPhoneLocales;
  public static boolean s_ReadMessage;
  public static boolean s_inCarMode;
  public static boolean s_inMultiwindowedMode;
  public static Time s_nightTimer;
  private static HashMap<String, String> unsupportedLocales;

  static
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "SERVER_NAME";
    arrayOfString[1] = "SERVICES_HOST_NAME";
    arrayOfString[2] = "EVENTLOG_HOST_NAME";
    arrayOfString[3] = "HELLO_HOST_NAME";
    arrayOfString[4] = "LMTT_HOST_NAME";
    SERVER_URL_KEYS = Arrays.asList(arrayOfString);
    s_inMultiwindowedMode = false;
    s_nightTimer = new Time();
    FACEBOOK_APP_ID_DEFAULT = "420500754634289";
    locales = Resources.getSystem().getAssets().getLocales();
    loggedPhoneLocales = false;
  }

  public static String convertToISOLanguage(String paramString)
  {
    if (paramString.equals("v-es-LA"))
      paramString = "es-ES";
    return paramString;
  }

  public static String getDeviceLanguage()
  {
    try
    {
      Process localProcess1 = Runtime.getRuntime().exec("getprop persist.sys.language");
      Process localProcess2 = Runtime.getRuntime().exec("getprop persist.sys.country");
      BufferedReader localBufferedReader1 = new BufferedReader(new InputStreamReader(localProcess1.getInputStream()));
      BufferedReader localBufferedReader2 = new BufferedReader(new InputStreamReader(localProcess2.getInputStream()));
      String str2 = localBufferedReader1.readLine();
      String str3 = localBufferedReader2.readLine();
      str1 = str2 + "-" + str3;
      localBufferedReader1.close();
      localBufferedReader2.close();
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
        String str1 = Settings.getString("language", "en-US");
    }
  }

  public static boolean getHelpVisible()
  {
    return getSharedPreferences().getBoolean("key_help_visible", false);
  }

  public static String getHomeMainPrompt()
  {
    return VlingoApplication.getInstance().getResources().getString(2131362317);
  }

  private static Map<DynamicFeatureConfig, String> getInitiallyDisabledFeatures()
  {
    HashMap localHashMap = new HashMap();
    if (!PackageInfoProvider.hasDialing())
      localHashMap.put(DynamicFeatureConfig.CALL, "Device");
    if (!PackageInfoProvider.hasMemo())
      localHashMap.put(DynamicFeatureConfig.MEMO, "Device");
    if (!PackageInfoProvider.hasMessaging())
      localHashMap.put(DynamicFeatureConfig.MESSAGING, "Device");
    if (!PackageInfoProvider.hasVoiceRecorder())
      localHashMap.put(DynamicFeatureConfig.VOICERECORD, "Device");
    if (!PackageInfoProvider.hasTimer())
      localHashMap.put(DynamicFeatureConfig.TIMER, "Device");
    return localHashMap;
  }

  public static String getModelName()
  {
    return getString("midas_model_name", null);
  }

  public static void init()
  {
    if (getBoolean("key_midas_first_run", true))
    {
      DynamicFeatureConfig.initDisabledFeatures(getInitiallyDisabledFeatures());
      setBoolean("key_midas_first_run", false);
    }
  }

  public static void initializeUnsupportedLocales()
  {
    unsupportedLocales = new HashMap();
    unsupportedLocales.put("en_AU", "en_US");
    unsupportedLocales.put("en_BE", "en_US");
    unsupportedLocales.put("en_CA", "en_US");
    unsupportedLocales.put("en_HK", "en_US");
    unsupportedLocales.put("en_IE", "en_US");
    unsupportedLocales.put("en_IN", "en_US");
    unsupportedLocales.put("en_NZ", "en_US");
    unsupportedLocales.put("en_PH", "en_US");
    unsupportedLocales.put("en_SG", "en_US");
    unsupportedLocales.put("en_ZA", "en_US");
    unsupportedLocales.put("fr_BE", "fr_FR");
    unsupportedLocales.put("fr_CA", "fr_FR");
    unsupportedLocales.put("fr_LU", "fr_FR");
    unsupportedLocales.put("fr_CH", "fr_FR");
    unsupportedLocales.put("de_AT", "de_DE");
    unsupportedLocales.put("de_CH", "de_DE");
    unsupportedLocales.put("de_LU", "de_DE");
  }

  public static boolean isEMailReadbackEnabled()
  {
    return getBoolean("safereader_email_enabled", false);
  }

  public static boolean isHomeKeyEnabled()
  {
    if (!isSoftKeyEnabledKnown)
    {
      hasHWKey = ViewConfiguration.get(VlingoApplication.getInstance().getApplicationContext()).hasPermanentMenuKey();
      isSoftKeyEnabledKnown = true;
    }
    return hasHWKey;
  }

  public static boolean isInCarMode()
  {
    return s_inCarMode;
  }

  public static boolean isMultiwindowedMode()
  {
    return s_inMultiwindowedMode;
  }

  public static boolean isNightMode()
  {
    s_nightTimer.setToNow();
    if ((s_nightTimer.hour >= 8) && (s_nightTimer.hour <= 20));
    for (int i = 0; ; i = 1)
      return i;
  }

  public static boolean isReadMessageBody()
  {
    if ((isInCarMode()) && (ClientSuppliedValues.isReadMessageBodyEnabled()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isSamsungDisclaimerAccepted()
  {
    boolean bool = getBoolean("samsung_disclaimer_accepted", false);
    if (!bool)
    {
      byte[] arrayOfByte = getData("samsung_disclaimer_accepted");
      if ((arrayOfByte != null) && (arrayOfByte.length > 0) && (arrayOfByte[0] == 1))
      {
        bool = true;
        setBoolean("samsung_disclaimer_accepted", true);
      }
    }
    return bool;
  }

  public static boolean isTOSAccepted()
  {
    boolean bool = getBoolean("tos_accepted", false);
    if (!bool)
    {
      byte[] arrayOfByte = getData("tos_accepted");
      if ((arrayOfByte != null) && (arrayOfByte.length > 0) && (arrayOfByte[0] == 1))
      {
        bool = true;
        setBoolean("tos_accepted", true);
      }
    }
    return bool;
  }

  public static boolean isUSEnglishEnabled()
  {
    int i = 1;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.csc.sales_code").getInputStream()));
      String str = localBufferedReader.readLine();
      localBufferedReader.close();
      if (str != null)
        if (!str.equalsIgnoreCase("VZW"))
        {
          boolean bool = str.equalsIgnoreCase("ATT");
          if (!bool);
        }
        else
        {
          i = 0;
        }
      label68: return i;
    }
    catch (IOException localIOException)
    {
      break label68;
    }
  }

  public static void languageSupportListCheck()
  {
    int i = 0;
    String str1 = getISOLanguage(getLanguageApplication());
    String str2 = Settings.getString("language", "en-US");
    CharSequence[] arrayOfCharSequence = Settings.getSupportedLanguages();
    int j = arrayOfCharSequence.length;
    for (int k = 0; ; k++)
    {
      if (k < j)
      {
        if ((arrayOfCharSequence[k].toString().equalsIgnoreCase(str2) != true) || (!phoneSupportsLanguage(str2)))
          continue;
        i = 1;
      }
      boolean bool = Settings.getBoolean("show_all_languages", false);
      if ((i != 1) && (!bool))
      {
        str1 = "en-US";
        Settings.setString("language", "en-US");
      }
      float f = VlingoApplication.getInstance().getResources().getConfiguration().fontScale;
      Locale localLocale = getLocaleForIsoLanguage(str1);
      Locale.setDefault(localLocale);
      Configuration localConfiguration = new Configuration();
      localConfiguration.locale = localLocale;
      localConfiguration.fontScale = f;
      VlingoApplication.getInstance().getResources().updateConfiguration(localConfiguration, null);
      saveString("language.bin", Settings.getString("language", "en-US"));
      Settings.System.putString(VlingoApplication.getInstance().getContentResolver(), "voicetalk_language", convertToISOLanguage(getDeviceLanguage()));
      return;
    }
  }

  public static boolean phoneSupportsLanguage(String paramString)
  {
    initializeUnsupportedLocales();
    boolean bool;
    if (paramString.contains("v-"))
      bool = phoneSupportsVlingoSpecificLanguage(paramString);
    while (true)
    {
      return bool;
      String str1 = Settings.getISOLanguage(paramString).replace('-', '_');
      for (int i = 0; ; i++)
      {
        if (i >= locales.length)
          break label93;
        String str2 = locales[i];
        if (unsupportedLocales.containsKey(str2))
          str2 = (String)unsupportedLocales.get(str2);
        if (!str2.equals(str1))
          continue;
        bool = true;
        break;
      }
      label93: bool = false;
    }
  }

  public static boolean phoneSupportsVlingoSpecificLanguage(String paramString)
  {
    int i = 0;
    String str1 = paramString.substring(2, 4);
    for (int j = 0; ; j++)
    {
      if (j < locales.length)
      {
        String str2 = locales[j];
        if ((str2.length() != 5) || (!str2.substring(0, 2).equals(str1)))
          continue;
        i = 1;
      }
      return i;
    }
  }

  private static void saveString(String paramString1, String paramString2)
  {
    try
    {
      FileOutputStream localFileOutputStream = VlingoApplication.getInstance().openFileOutput(paramString1, 1);
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

  public static void setHelpVisible(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putBoolean("key_help_visible", paramBoolean);
    localEditor.commit();
  }

  public static void setInCarMode(boolean paramBoolean)
  {
    if (paramBoolean == s_inCarMode);
    while (true)
    {
      return;
      s_inCarMode = paramBoolean;
      setBoolean("in_car_mode", paramBoolean);
    }
  }

  public static String setModelName(String paramString)
  {
    return getString("midas_model_name", paramString);
  }

  public static void setMultiwindowedMode(boolean paramBoolean)
  {
    s_inMultiwindowedMode = paramBoolean;
  }

  public static void setSafeReaderReadMessageBodyEnabled(boolean paramBoolean)
  {
    Settings.setBoolean("car_safereader_read_message", paramBoolean);
  }

  public static void setSamsungDisclaimerAccepted(boolean paramBoolean)
  {
    setBoolean("samsung_disclaimer_accepted", paramBoolean);
    byte[] arrayOfByte;
    if (paramBoolean)
    {
      arrayOfByte = new byte[1];
      arrayOfByte[0] = 1;
    }
    while (true)
    {
      setData("samsung_disclaimer_accepted", arrayOfByte);
      return;
      arrayOfByte = new byte[1];
      arrayOfByte[0] = 0;
    }
  }

  public static void setTOSAccepted(boolean paramBoolean)
  {
    setBoolean("tos_accepted", paramBoolean);
    byte[] arrayOfByte;
    if (paramBoolean)
    {
      arrayOfByte = new byte[1];
      arrayOfByte[0] = 1;
    }
    while (true)
    {
      setData("tos_accepted", arrayOfByte);
      if (paramBoolean)
        Settings.setString("tos_accepted_date", new Date(System.currentTimeMillis()).toString());
      return;
      arrayOfByte = new byte[1];
      arrayOfByte[0] = 0;
    }
  }

  public static void updateCurrentLocale()
  {
    updateCurrentLocale(VlingoApplication.getInstance().getResources());
  }

  public static void updateCurrentLocale(Resources paramResources)
  {
    String str2;
    if (((!isTOSAccepted()) && (!SettingsScreen.isLaunchBySettings)) || (!ClientSuppliedValues.isIUXComplete()))
    {
      try
      {
        Class localClass = Class.forName("android.app.ActivityManagerNative");
        Method localMethod1 = localClass.getMethod("getDefault", new Class[0]);
        localMethod1.setAccessible(true);
        Object localObject = localMethod1.invoke(localClass, new Object[0]);
        Method localMethod2 = localClass.getMethod("getConfiguration", new Class[0]);
        localMethod2.setAccessible(true);
        localLocale = ((Configuration)localMethod2.invoke(localObject, new Object[0])).locale;
        arrayOfCharSequence = Settings.getSupportedLanguages();
        str1 = localLocale.toString().replace('_', '-');
        str2 = null;
        if (str1.equalsIgnoreCase(getISOLanguage(getLanguageApplication())))
          break label283;
        int i = arrayOfCharSequence.length;
        for (int j = 0; j < i; j++)
        {
          if (!convertToISOLanguage(arrayOfCharSequence[j].toString()).equalsIgnoreCase(str1))
            continue;
          str2 = str1;
        }
      }
      catch (Exception localException)
      {
        CharSequence[] arrayOfCharSequence;
        String str1;
        while (true)
        {
          localException.printStackTrace();
          Locale localLocale = Locale.getDefault();
        }
        if (StringUtils.isNullOrWhiteSpace(str2))
        {
          int k = arrayOfCharSequence.length;
          for (int m = 0; m < k; m++)
          {
            CharSequence localCharSequence = arrayOfCharSequence[m];
            if (!convertToISOLanguage(localCharSequence.toString()).startsWith(str1.substring(0, 2)))
              continue;
            str2 = localCharSequence.toString();
          }
        }
        if (!StringUtils.isNullOrWhiteSpace(str2))
          break label321;
      }
      Settings.setLanguageApplication(Settings.getString("en-US", "en-US"), VlingoApplication.getInstance().getApplicationContext());
    }
    while (true)
    {
      label283: Log.d("strangeLang", "" + paramResources.getConfiguration().locale);
      Settings.updateCurrentLocale(paramResources);
      return;
      label321: Settings.setLanguageApplication(str2, VlingoApplication.getInstance().getApplicationContext());
      continue;
      if (!isTOSAccepted())
        continue;
      Settings.getString("language", "en-US");
      Settings.System.putString(VlingoApplication.getInstance().getContentResolver(), "voicetalk_language", convertToISOLanguage(getDeviceLanguage()));
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.MidasSettings
 * JD-Core Version:    0.6.0
 */