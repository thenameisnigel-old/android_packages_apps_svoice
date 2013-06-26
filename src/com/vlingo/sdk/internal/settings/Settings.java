package com.vlingo.sdk.internal.settings;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.vlingo.sdk.internal.core.ApplicationAdapter;

public abstract class Settings
{
  public static final String ACOUSTIC_RAW_ZIP_FILENAME = "vlsdk_acoustic_raw.zip";
  public static final String ACOUSTIC_RAW_ZIP_FILENAME_S2 = "vlsdk_acoustic_raw_s2.0.zip";
  public static final boolean AUDIO_FILE_LOG_ENABLED = false;
  public static final String DEFAULT_LANGUAGE = "en-US";
  public static final String DEFAULT_MAX_WIDTH = "0";
  public static final String DEFAULT_PLOT_WIDTH = "0";
  public static final boolean DETAILED_TIMINGS = true;
  public static final String KEY_ACCEPTEDTEXT_ENABLE = "acceptedtext.enable";
  public static final String KEY_ACTIVITYLOG_ENABLE = "activitylog.enable";
  public static final String KEY_ASR_KEEPALIVE = "asr.http.keep_alive";
  public static final String KEY_ASR_MANAGER = "asr.manager";
  public static final String KEY_COOKIE_DATA = "cookie_data";
  public static final String KEY_HELLO_ENABLE = "hello.enable";
  public static final String KEY_MAX_WIDTH = "max.width";
  public static final String KEY_PLOT_WIDTH = "plot.width";
  public static final String KEY_SCREEN_MAG = "screen.mag";
  public static final String KEY_SCREEN_WIDTH = "screen.width";
  public static final String KEY_STAT_ENABLE = "stats.enable";
  public static final String KEY_TOS_ACCEPTED_DATE = "tos_accepted_date";
  public static final String KEY_UUID = "uuid";
  public static final String KEY_ZIP_SIZE_PREFIX = "zip_size_";
  public static String LANGUAGE = "en-US";
  public static final String LIB_PATH = "vlsdk_lib";
  public static final String LIB_ZIP_FILENAME = "vlsdk_lib.zip";
  public static final String LIB_ZIP_FILENAME_S2 = "vlsdk_lib_s2.0.zip";
  public static final String LTS_RAW_ZIP_FILENAME = "vlsdk_lts_raw.zip";
  public static final String RAW_PATH = "vlsdk_raw";
  public static final boolean SERVERLOG_ENABLED;
  public static String[] SUPPORTED_LANGUAGES;

  static
  {
    String[] arrayOfString = new String[11];
    arrayOfString[0] = "en-US";
    arrayOfString[1] = "en-GB";
    arrayOfString[2] = "fr-FR";
    arrayOfString[3] = "it-IT";
    arrayOfString[4] = "de-DE";
    arrayOfString[5] = "es-ES";
    arrayOfString[6] = "v-es-LA";
    arrayOfString[7] = "v-es-NA";
    arrayOfString[8] = "zh-CN";
    arrayOfString[9] = "ja-JP";
    arrayOfString[10] = "ko-KR";
    SUPPORTED_LANGUAGES = arrayOfString;
  }

  public static boolean getPersistentBoolean(String paramString, boolean paramBoolean)
  {
    return getSharedPreferences().getBoolean(paramString, paramBoolean);
  }

  public static float getPersistentFloat(String paramString, float paramFloat)
  {
    return getSharedPreferences().getFloat(paramString, paramFloat);
  }

  public static int getPersistentInt(String paramString, int paramInt)
  {
    return getSharedPreferences().getInt(paramString, paramInt);
  }

  public static String getPersistentString(String paramString1, String paramString2)
  {
    return getSharedPreferences().getString(paramString1, paramString2);
  }

  private static SharedPreferences getSharedPreferences()
  {
    return PreferenceManager.getDefaultSharedPreferences(ApplicationAdapter.getInstance().getApplicationContext());
  }

  public static boolean isAsrKeepAliveEnabled()
  {
    return getPersistentBoolean("asr.http.keep_alive", true);
  }

  public static void setPersistentInt(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public static void setPersistentString(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.settings.Settings
 * JD-Core Version:    0.6.0
 */