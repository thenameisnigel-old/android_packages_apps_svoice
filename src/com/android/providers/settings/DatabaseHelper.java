package com.android.providers.settings;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.media.AudioService;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.XmlUtils;
import com.android.internal.widget.LockPatternUtils;
import java.io.IOException;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

public class DatabaseHelper extends SQLiteOpenHelper
{
  private static final String DATABASE_NAME = "settings.db";
  private static final int DATABASE_VERSION = 57;
  private static final String TAG = "SettingsProvider";
  private static final HashSet<String> mValidTables = new HashSet();
  private Context mContext;

  static
  {
    mValidTables.add("system");
    mValidTables.add("secure");
    mValidTables.add("bluetooth_devices");
    mValidTables.add("bookmarks");
    mValidTables.add("favorites");
    mValidTables.add("gservices");
    mValidTables.add("old_favorites");
  }

  public DatabaseHelper(Context paramContext)
  {
    super(paramContext, "settings.db", null, 57);
    this.mContext = paramContext;
  }

  private void createSecureTable(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE secure (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT UNIQUE ON CONFLICT REPLACE,value TEXT);");
    paramSQLiteDatabase.execSQL("CREATE INDEX secureIndex1 ON secure (name);");
  }

  public static boolean isValidTable(String paramString)
  {
    return mValidTables.contains(paramString);
  }

  private int loadBookmarks(SQLiteDatabase paramSQLiteDatabase, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    ContentValues localContentValues = new ContentValues();
    PackageManager localPackageManager = this.mContext.getPackageManager();
    int i = paramInt;
    try
    {
      XmlResourceParser localXmlResourceParser = this.mContext.getResources().getXml(2130903040);
      XmlUtils.beginDocument(localXmlResourceParser, "bookmarks");
      int j = localXmlResourceParser.getDepth();
      while (true)
      {
        int k = localXmlResourceParser.next();
        if (((k != 3) || (localXmlResourceParser.getDepth() > j)) && (k != 1))
        {
          if (k != 2)
            continue;
          if ("bookmark".equals(localXmlResourceParser.getName()))
          {
            String str1 = localXmlResourceParser.getAttributeValue(null, "package");
            String str2 = localXmlResourceParser.getAttributeValue(null, "class");
            String str3 = localXmlResourceParser.getAttributeValue(null, "shortcut");
            int m = str3.charAt(0);
            if (TextUtils.isEmpty(str3))
              Log.w("SettingsProvider", "Unable to get shortcut for: " + str1 + "/" + str2);
            Object localObject = null;
            ComponentName localComponentName = new ComponentName(str1, str2);
            try
            {
              ActivityInfo localActivityInfo2 = localPackageManager.getActivityInfo(localComponentName, 0);
              localObject = localActivityInfo2;
              if (localObject == null)
                continue;
              localIntent.setComponent(localComponentName);
              localIntent.setFlags(268435456);
              localContentValues.put("intent", localIntent.toUri(0));
              localContentValues.put("title", localObject.loadLabel(localPackageManager).toString());
              localContentValues.put("shortcut", Integer.valueOf(m));
              paramSQLiteDatabase.insert("bookmarks", null, localContentValues);
              i++;
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException1)
            {
              while (true)
              {
                String[] arrayOfString = new String[1];
                arrayOfString[0] = str1;
                localComponentName = new ComponentName(localPackageManager.canonicalToCurrentPackageNames(arrayOfString)[0], str2);
                try
                {
                  ActivityInfo localActivityInfo1 = localPackageManager.getActivityInfo(localComponentName, 0);
                  localObject = localActivityInfo1;
                }
                catch (PackageManager.NameNotFoundException localNameNotFoundException2)
                {
                  Log.w("SettingsProvider", "Unable to add bookmark: " + str1 + "/" + str2, localNameNotFoundException1);
                }
              }
            }
          }
        }
      }
    }
    catch (XmlPullParserException localXmlPullParserException)
    {
      Log.w("SettingsProvider", "Got execption parsing bookmarks.", localXmlPullParserException);
    }
    catch (IOException localIOException)
    {
      Log.w("SettingsProvider", "Got execption parsing bookmarks.", localIOException);
    }
    return i;
  }

  private void loadBookmarks(SQLiteDatabase paramSQLiteDatabase)
  {
    loadBookmarks(paramSQLiteDatabase, 0);
  }

  private void loadBooleanSetting(SQLiteStatement paramSQLiteStatement, String paramString, int paramInt)
  {
    if (this.mContext.getResources().getBoolean(paramInt));
    for (String str = "1"; ; str = "0")
    {
      loadSetting(paramSQLiteStatement, paramString, str);
      return;
    }
  }

  private void loadDefaultAnimationSettings(SQLiteStatement paramSQLiteStatement)
  {
    loadFractionSetting(paramSQLiteStatement, "window_animation_scale", 2131165184, 1);
    loadFractionSetting(paramSQLiteStatement, "transition_animation_scale", 2131165185, 1);
  }

  private void loadDefaultHapticSettings(SQLiteStatement paramSQLiteStatement)
  {
    loadBooleanSetting(paramSQLiteStatement, "haptic_feedback_enabled", 2130968581);
  }

  private void loadFractionSetting(SQLiteStatement paramSQLiteStatement, String paramString, int paramInt1, int paramInt2)
  {
    loadSetting(paramSQLiteStatement, paramString, Float.toString(this.mContext.getResources().getFraction(paramInt1, paramInt2, paramInt2)));
  }

  private void loadIntegerSetting(SQLiteStatement paramSQLiteStatement, String paramString, int paramInt)
  {
    loadSetting(paramSQLiteStatement, paramString, Integer.toString(this.mContext.getResources().getInteger(paramInt)));
  }

  private void loadSecure35Settings(SQLiteStatement paramSQLiteStatement)
  {
    loadBooleanSetting(paramSQLiteStatement, "backup_enabled", 2130968588);
    loadStringSetting(paramSQLiteStatement, "backup_transport", 2131099651);
  }

  private void loadSecureSettings(SQLiteDatabase paramSQLiteDatabase)
  {
    int i = 1;
    SQLiteStatement localSQLiteStatement = null;
    try
    {
      localSQLiteStatement = paramSQLiteDatabase.compileStatement("INSERT OR IGNORE INTO secure(name,value) VALUES(?,?);");
      loadBooleanSetting(localSQLiteStatement, "bluetooth_on", 2130968582);
      if ("true".equalsIgnoreCase(SystemProperties.get("ro.com.android.dataroaming", "false")));
      for (int j = i; ; j = 0)
      {
        loadSetting(localSQLiteStatement, "data_roaming", Integer.valueOf(j));
        loadBooleanSetting(localSQLiteStatement, "install_non_market_apps", 2130968583);
        loadStringSetting(localSQLiteStatement, "location_providers_allowed", 2131099650);
        loadBooleanSetting(localSQLiteStatement, "assisted_gps_enabled", 2130968584);
        loadIntegerSetting(localSQLiteStatement, "network_preference", 2131034115);
        loadBooleanSetting(localSQLiteStatement, "usb_mass_storage_enabled", 2130968585);
        loadBooleanSetting(localSQLiteStatement, "wifi_on", 2130968586);
        loadBooleanSetting(localSQLiteStatement, "wifi_networks_available_notification_on", 2130968587);
        String str = SystemProperties.get("ro.com.android.wifi-watchlist");
        if (!TextUtils.isEmpty(str))
          loadSetting(localSQLiteStatement, "wifi_watchdog_watch_list", str);
        loadSetting(localSQLiteStatement, "preferred_network_mode", Integer.valueOf(SystemProperties.getInt("ro.telephony.default_network", 0)));
        loadSetting(localSQLiteStatement, "cdma_cell_broadcast_sms", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "preferred_cdma_subscription", Integer.valueOf(1));
        if (!"1".equals(SystemProperties.get("ro.allow.mock.location")))
          break;
        loadSetting(localSQLiteStatement, "mock_location", Integer.valueOf(i));
        loadSetting(localSQLiteStatement, "usb_setting_mode", Integer.valueOf(2));
        loadSecure35Settings(localSQLiteStatement);
        loadBooleanSetting(localSQLiteStatement, "mount_play_not_snd", 2130968590);
        loadBooleanSetting(localSQLiteStatement, "mount_ums_autostart", 2130968591);
        loadBooleanSetting(localSQLiteStatement, "mount_ums_prompt", 2130968592);
        loadBooleanSetting(localSQLiteStatement, "mount_ums_notify_enabled", 2130968593);
        return;
      }
      i = 0;
    }
    finally
    {
      if (localSQLiteStatement != null)
        localSQLiteStatement.close();
    }
  }

  private void loadSetting(SQLiteStatement paramSQLiteStatement, String paramString, Object paramObject)
  {
    paramSQLiteStatement.bindString(1, paramString);
    paramSQLiteStatement.bindString(2, paramObject.toString());
    paramSQLiteStatement.execute();
  }

  private void loadSettings(SQLiteDatabase paramSQLiteDatabase)
  {
    loadSystemSettings(paramSQLiteDatabase);
    loadSecureSettings(paramSQLiteDatabase);
  }

  private void loadStringSetting(SQLiteStatement paramSQLiteStatement, String paramString, int paramInt)
  {
    loadSetting(paramSQLiteStatement, paramString, this.mContext.getResources().getString(paramInt));
  }

  private void loadSystemSettings(SQLiteDatabase paramSQLiteDatabase)
  {
    int i = 1;
    SQLiteStatement localSQLiteStatement = null;
    try
    {
      localSQLiteStatement = paramSQLiteDatabase.compileStatement("INSERT OR IGNORE INTO system(name,value) VALUES(?,?);");
      loadBooleanSetting(localSQLiteStatement, "dim_screen", 2130968576);
      if ("1".equals(SystemProperties.get("ro.kernel.qemu")))
      {
        loadSetting(localSQLiteStatement, "stay_on_while_plugged_in", Integer.valueOf(i));
        loadIntegerSetting(localSQLiteStatement, "screen_off_timeout", 2131034112);
        loadSetting(localSQLiteStatement, "emergency_tone", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "call_auto_retry", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "dtmf_tone", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "dtmf_tone_type", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "sound_effects_enabled", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "hearing_aid", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "tty_mode", Integer.valueOf(0));
        loadBooleanSetting(localSQLiteStatement, "airplane_mode_on", 2130968577);
        loadStringSetting(localSQLiteStatement, "airplane_mode_radios", 2131099648);
        loadStringSetting(localSQLiteStatement, "airplane_mode_toggleable_radios", 2131099649);
        loadIntegerSetting(localSQLiteStatement, "wifi_sleep_policy", 2131034113);
        loadBooleanSetting(localSQLiteStatement, "auto_time", 2130968578);
        loadIntegerSetting(localSQLiteStatement, "screen_brightness", 2131034114);
        loadBooleanSetting(localSQLiteStatement, "screen_brightness_mode", 2130968580);
        loadDefaultAnimationSettings(localSQLiteStatement);
        loadBooleanSetting(localSQLiteStatement, "accelerometer_rotation", 2130968579);
        loadSetting(localSQLiteStatement, "uartapcpmode", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "usbapcpmode", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "VIB_FEEDBACK_MAGNITUDE", Integer.valueOf(6000));
        loadSetting(localSQLiteStatement, "VIB_RECVCALL_MAGNITUDE", Integer.valueOf(5));
        loadSetting(localSQLiteStatement, "VIB_NOTIFICATION_MAGNITUDE", Integer.valueOf(5));
        loadSetting(localSQLiteStatement, "power_saving_mode", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "button_key_light", Integer.valueOf(-1));
        loadSetting(localSQLiteStatement, "una_setting", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "cradle_connect", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "cradle_enable", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "cradle_launch", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "notifications_use_ring_volume", Integer.valueOf(0));
        loadDefaultHapticSettings(localSQLiteStatement);
        loadBooleanSetting(localSQLiteStatement, "notification_light_pulse", 2130968589);
        loadSetting(localSQLiteStatement, "set_install_location", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "default_install_location", Integer.valueOf(0));
        loadUISoundEffectsSettings(localSQLiteStatement);
        loadBooleanSetting(localSQLiteStatement, "vibrate_in_silent", 2130968594);
        loadSetting(localSQLiteStatement, "driving_mode_on", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "driving_mode_incoming_call_notification", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "driving_mode_message_notification", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "driving_mode_alarm_notification", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "driving_mode_schedule_notification", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "driving_mode_unlock_screen_contents", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "driving_mode_message_contents", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "shopdemo", Integer.valueOf(0));
        loadStringSetting(localSQLiteStatement, "date_format", 2131099659);
        loadSetting(localSQLiteStatement, "show_password", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "motion_engine", Integer.valueOf(0));
        loadSetting(localSQLiteStatement, "motion_shake", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "motion_zooming", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "motion_panning", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "motion_double_tap", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "motion_overturn", Integer.valueOf(1));
        loadSetting(localSQLiteStatement, "motion_zooming_sensitivity", Integer.valueOf(5));
        loadSetting(localSQLiteStatement, "motion_panning_sensitivity", Integer.valueOf(5));
        return;
      }
      i = 0;
    }
    finally
    {
      if (localSQLiteStatement != null)
        localSQLiteStatement.close();
    }
  }

  private void loadUISoundEffectsSettings(SQLiteStatement paramSQLiteStatement)
  {
    loadIntegerSetting(paramSQLiteStatement, "power_sounds_enabled", 2131034116);
    loadStringSetting(paramSQLiteStatement, "low_battery_sound", 2131099652);
    loadIntegerSetting(paramSQLiteStatement, "dock_sounds_enabled", 2131034117);
    loadStringSetting(paramSQLiteStatement, "desk_dock_sound", 2131099653);
    loadStringSetting(paramSQLiteStatement, "desk_undock_sound", 2131099654);
    loadStringSetting(paramSQLiteStatement, "car_dock_sound", 2131099655);
    loadStringSetting(paramSQLiteStatement, "car_undock_sound", 2131099656);
    loadIntegerSetting(paramSQLiteStatement, "lockscreen_sounds_enabled", 2131034118);
    loadStringSetting(paramSQLiteStatement, "lock_sound", 2131099657);
    loadStringSetting(paramSQLiteStatement, "unlock_sound", 2131099658);
  }

  private void loadVibrateSetting(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    if (paramBoolean)
      paramSQLiteDatabase.execSQL("DELETE FROM system WHERE name='vibrate_on'");
    SQLiteStatement localSQLiteStatement = null;
    try
    {
      localSQLiteStatement = paramSQLiteDatabase.compileStatement("INSERT OR IGNORE INTO system(name,value) VALUES(?,?);");
      int i = AudioService.getValueForVibrateSetting(0, 1, 1);
      loadSetting(localSQLiteStatement, "vibrate_on", Integer.valueOf(i | AudioService.getValueForVibrateSetting(i, 0, 0)));
      return;
    }
    finally
    {
      if (localSQLiteStatement != null)
        localSQLiteStatement.close();
    }
    throw localObject;
  }

  private void loadVolumeLevels(SQLiteDatabase paramSQLiteDatabase)
  {
    SQLiteStatement localSQLiteStatement = null;
    try
    {
      localSQLiteStatement = paramSQLiteDatabase.compileStatement("INSERT OR IGNORE INTO system(name,value) VALUES(?,?);");
      loadSetting(localSQLiteStatement, "volume_music", Integer.valueOf(android.media.AudioManager.DEFAULT_STREAM_VOLUME[3]));
      loadSetting(localSQLiteStatement, "volume_ring", Integer.valueOf(android.media.AudioManager.DEFAULT_STREAM_VOLUME[2]));
      loadSetting(localSQLiteStatement, "volume_system", Integer.valueOf(android.media.AudioManager.DEFAULT_STREAM_VOLUME[1]));
      loadSetting(localSQLiteStatement, "volume_voice", Integer.valueOf(android.media.AudioManager.DEFAULT_STREAM_VOLUME[0]));
      loadSetting(localSQLiteStatement, "volume_alarm", Integer.valueOf(android.media.AudioManager.DEFAULT_STREAM_VOLUME[4]));
      loadSetting(localSQLiteStatement, "volume_notification", Integer.valueOf(android.media.AudioManager.DEFAULT_STREAM_VOLUME[5]));
      loadSetting(localSQLiteStatement, "volume_bluetooth_sco", Integer.valueOf(android.media.AudioManager.DEFAULT_STREAM_VOLUME[6]));
      loadSetting(localSQLiteStatement, "mode_ringer", Integer.valueOf(2));
      loadVibrateSetting(paramSQLiteDatabase, false);
      loadSetting(localSQLiteStatement, "mode_ringer_streams_affected", Integer.valueOf(166));
      loadSetting(localSQLiteStatement, "mute_streams_affected", Integer.valueOf(46));
      return;
    }
    finally
    {
      if (localSQLiteStatement != null)
        localSQLiteStatement.close();
    }
    throw localObject;
  }

  private void moveFromSystemToSecure(SQLiteDatabase paramSQLiteDatabase, String[] paramArrayOfString)
  {
    SQLiteStatement localSQLiteStatement1 = null;
    SQLiteStatement localSQLiteStatement2 = null;
    paramSQLiteDatabase.beginTransaction();
    try
    {
      localSQLiteStatement1 = paramSQLiteDatabase.compileStatement("INSERT INTO secure (name,value) SELECT name,value FROM system WHERE name=?");
      localSQLiteStatement2 = paramSQLiteDatabase.compileStatement("DELETE FROM system WHERE name=?");
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        String str = paramArrayOfString[j];
        localSQLiteStatement1.bindString(1, str);
        localSQLiteStatement1.execute();
        localSQLiteStatement2.bindString(1, str);
        localSQLiteStatement2.execute();
      }
      paramSQLiteDatabase.setTransactionSuccessful();
      return;
    }
    finally
    {
      paramSQLiteDatabase.endTransaction();
      if (localSQLiteStatement1 != null)
        localSQLiteStatement1.close();
      if (localSQLiteStatement2 != null)
        localSQLiteStatement2.close();
    }
    throw localObject;
  }

  private void upgradeLockPatternLocation(SQLiteDatabase paramSQLiteDatabase)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "value";
    Cursor localCursor = paramSQLiteDatabase.query("system", arrayOfString, "name='lock_pattern'", null, null, null, null);
    String str;
    if (localCursor.getCount() > 0)
    {
      localCursor.moveToFirst();
      str = localCursor.getString(1);
      if (TextUtils.isEmpty(str));
    }
    try
    {
      new LockPatternUtils(this.mContext).saveLockPattern(LockPatternUtils.stringToPattern(str));
      label84: localCursor.close();
      paramSQLiteDatabase.delete("system", "name='lock_pattern'", null);
      while (true)
      {
        return;
        localCursor.close();
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      break label84;
    }
  }

  private void upgradeScreenTimeoutFromNever(SQLiteDatabase paramSQLiteDatabase)
  {
    String[] arrayOfString1 = new String[2];
    arrayOfString1[0] = "_id";
    arrayOfString1[1] = "value";
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "screen_off_timeout";
    arrayOfString2[1] = "-1";
    Cursor localCursor = paramSQLiteDatabase.query("system", arrayOfString1, "name=? AND value=?", arrayOfString2, null, null, null);
    SQLiteStatement localSQLiteStatement = null;
    if (localCursor.getCount() > 0)
      localCursor.close();
    while (true)
    {
      try
      {
        localSQLiteStatement = paramSQLiteDatabase.compileStatement("INSERT OR REPLACE INTO system(name,value) VALUES(?,?);");
        loadSetting(localSQLiteStatement, "screen_off_timeout", Integer.toString(1800000));
        return;
      }
      finally
      {
        if (localSQLiteStatement == null)
          continue;
        localSQLiteStatement.close();
      }
      localCursor.close();
    }
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE system (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT UNIQUE ON CONFLICT REPLACE,value TEXT);");
    paramSQLiteDatabase.execSQL("CREATE INDEX systemIndex1 ON system (name);");
    createSecureTable(paramSQLiteDatabase);
    paramSQLiteDatabase.execSQL("CREATE TABLE bluetooth_devices (_id INTEGER PRIMARY KEY,name TEXT,addr TEXT,channel INTEGER,type INTEGER);");
    paramSQLiteDatabase.execSQL("CREATE TABLE bookmarks (_id INTEGER PRIMARY KEY,title TEXT,folder TEXT,intent TEXT,shortcut INTEGER,ordering INTEGER);");
    paramSQLiteDatabase.execSQL("CREATE INDEX bookmarksIndex1 ON bookmarks (folder);");
    paramSQLiteDatabase.execSQL("CREATE INDEX bookmarksIndex2 ON bookmarks (shortcut);");
    loadBookmarks(paramSQLiteDatabase);
    loadVolumeLevels(paramSQLiteDatabase);
    loadSettings(paramSQLiteDatabase);
  }

  // ERROR //
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: ldc 14
    //   2: new 158	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   9: ldc_w 696
    //   12: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: iload_2
    //   16: invokevirtual 699	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   19: ldc_w 701
    //   22: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: iload_3
    //   26: invokevirtual 699	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   29: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   32: invokestatic 176	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   35: pop
    //   36: iload_2
    //   37: istore 5
    //   39: iload 5
    //   41: bipush 20
    //   43: if_icmpne +13 -> 56
    //   46: aload_0
    //   47: aload_1
    //   48: iconst_1
    //   49: invokespecial 604	com/android/providers/settings/DatabaseHelper:loadVibrateSetting	(Landroid/database/sqlite/SQLiteDatabase;Z)V
    //   52: bipush 21
    //   54: istore 5
    //   56: iload 5
    //   58: bipush 22
    //   60: if_icmpge +12 -> 72
    //   63: bipush 22
    //   65: istore 5
    //   67: aload_0
    //   68: aload_1
    //   69: invokespecial 703	com/android/providers/settings/DatabaseHelper:upgradeLockPatternLocation	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   72: iload 5
    //   74: bipush 23
    //   76: if_icmpge +14 -> 90
    //   79: aload_1
    //   80: ldc_w 705
    //   83: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   86: bipush 23
    //   88: istore 5
    //   90: iload 5
    //   92: bipush 23
    //   94: if_icmpne +54 -> 148
    //   97: aload_1
    //   98: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   101: aload_1
    //   102: ldc_w 707
    //   105: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   108: aload_1
    //   109: ldc_w 709
    //   112: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   115: aload_1
    //   116: ldc_w 711
    //   119: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   122: aload_1
    //   123: ldc_w 713
    //   126: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   129: aload_1
    //   130: ldc_w 715
    //   133: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   136: aload_1
    //   137: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   140: aload_1
    //   141: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   144: bipush 24
    //   146: istore 5
    //   148: iload 5
    //   150: bipush 24
    //   152: if_icmpne +33 -> 185
    //   155: aload_1
    //   156: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   159: aload_1
    //   160: ldc_w 717
    //   163: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   166: aload_1
    //   167: ldc_w 719
    //   170: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   173: aload_1
    //   174: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   177: aload_1
    //   178: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   181: bipush 25
    //   183: istore 5
    //   185: iload 5
    //   187: bipush 25
    //   189: if_icmpne +33 -> 222
    //   192: aload_1
    //   193: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   196: aload_1
    //   197: ldc_w 721
    //   200: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   203: aload_1
    //   204: ldc_w 723
    //   207: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   210: aload_1
    //   211: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   214: aload_1
    //   215: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   218: bipush 26
    //   220: istore 5
    //   222: iload 5
    //   224: bipush 26
    //   226: if_icmpne +24 -> 250
    //   229: aload_1
    //   230: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   233: aload_0
    //   234: aload_1
    //   235: invokespecial 678	com/android/providers/settings/DatabaseHelper:createSecureTable	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   238: aload_1
    //   239: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   242: aload_1
    //   243: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   246: bipush 27
    //   248: istore 5
    //   250: iload 5
    //   252: bipush 27
    //   254: if_icmpne +263 -> 517
    //   257: bipush 31
    //   259: anewarray 133	java/lang/String
    //   262: astore 48
    //   264: aload 48
    //   266: iconst_0
    //   267: ldc_w 725
    //   270: aastore
    //   271: aload 48
    //   273: iconst_1
    //   274: ldc_w 727
    //   277: aastore
    //   278: aload 48
    //   280: iconst_2
    //   281: ldc_w 309
    //   284: aastore
    //   285: aload 48
    //   287: iconst_3
    //   288: ldc_w 326
    //   291: aastore
    //   292: aload 48
    //   294: iconst_4
    //   295: ldc_w 729
    //   298: aastore
    //   299: aload 48
    //   301: iconst_5
    //   302: ldc_w 731
    //   305: aastore
    //   306: aload 48
    //   308: bipush 6
    //   310: ldc_w 328
    //   313: aastore
    //   314: aload 48
    //   316: bipush 7
    //   318: ldc_w 331
    //   321: aastore
    //   322: aload 48
    //   324: bipush 8
    //   326: ldc_w 733
    //   329: aastore
    //   330: aload 48
    //   332: bipush 9
    //   334: ldc_w 337
    //   337: aastore
    //   338: aload 48
    //   340: bipush 10
    //   342: ldc_w 735
    //   345: aastore
    //   346: aload 48
    //   348: bipush 11
    //   350: ldc_w 737
    //   353: aastore
    //   354: aload 48
    //   356: bipush 12
    //   358: ldc_w 739
    //   361: aastore
    //   362: aload 48
    //   364: bipush 13
    //   366: ldc_w 741
    //   369: aastore
    //   370: aload 48
    //   372: bipush 14
    //   374: ldc_w 342
    //   377: aastore
    //   378: aload 48
    //   380: bipush 15
    //   382: ldc_w 743
    //   385: aastore
    //   386: aload 48
    //   388: bipush 16
    //   390: ldc_w 348
    //   393: aastore
    //   394: aload 48
    //   396: bipush 17
    //   398: ldc_w 745
    //   401: aastore
    //   402: aload 48
    //   404: bipush 18
    //   406: ldc_w 747
    //   409: aastore
    //   410: aload 48
    //   412: bipush 19
    //   414: ldc_w 345
    //   417: aastore
    //   418: aload 48
    //   420: bipush 20
    //   422: ldc_w 749
    //   425: aastore
    //   426: aload 48
    //   428: bipush 21
    //   430: ldc_w 751
    //   433: aastore
    //   434: aload 48
    //   436: bipush 22
    //   438: ldc_w 753
    //   441: aastore
    //   442: aload 48
    //   444: bipush 23
    //   446: ldc_w 755
    //   449: aastore
    //   450: aload 48
    //   452: bipush 24
    //   454: ldc_w 757
    //   457: aastore
    //   458: aload 48
    //   460: bipush 25
    //   462: ldc_w 759
    //   465: aastore
    //   466: aload 48
    //   468: bipush 26
    //   470: ldc_w 761
    //   473: aastore
    //   474: aload 48
    //   476: bipush 27
    //   478: ldc_w 763
    //   481: aastore
    //   482: aload 48
    //   484: bipush 28
    //   486: ldc_w 765
    //   489: aastore
    //   490: aload 48
    //   492: bipush 29
    //   494: ldc_w 767
    //   497: aastore
    //   498: aload 48
    //   500: bipush 30
    //   502: ldc_w 769
    //   505: aastore
    //   506: aload_0
    //   507: aload_1
    //   508: aload 48
    //   510: invokespecial 771	com/android/providers/settings/DatabaseHelper:moveFromSystemToSecure	(Landroid/database/sqlite/SQLiteDatabase;[Ljava/lang/String;)V
    //   513: bipush 28
    //   515: istore 5
    //   517: iload 5
    //   519: bipush 28
    //   521: if_icmpeq +10 -> 531
    //   524: iload 5
    //   526: bipush 29
    //   528: if_icmpne +60 -> 588
    //   531: aload_1
    //   532: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   535: aload_1
    //   536: ldc_w 773
    //   539: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   542: aload_1
    //   543: new 158	java/lang/StringBuilder
    //   546: dup
    //   547: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   550: ldc_w 775
    //   553: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   556: bipush 38
    //   558: invokestatic 777	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   561: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   564: ldc_w 779
    //   567: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   570: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   573: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   576: aload_1
    //   577: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   580: aload_1
    //   581: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   584: bipush 30
    //   586: istore 5
    //   588: iload 5
    //   590: bipush 30
    //   592: if_icmpne +33 -> 625
    //   595: aload_1
    //   596: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   599: aload_1
    //   600: ldc_w 781
    //   603: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   606: aload_1
    //   607: ldc_w 783
    //   610: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   613: aload_1
    //   614: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   617: aload_1
    //   618: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   621: bipush 31
    //   623: istore 5
    //   625: iload 5
    //   627: bipush 31
    //   629: if_icmpne +61 -> 690
    //   632: aload_1
    //   633: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   636: aconst_null
    //   637: astore 45
    //   639: aload_1
    //   640: ldc_w 785
    //   643: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   646: aload_1
    //   647: ldc_w 787
    //   650: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   653: aload_1
    //   654: ldc_w 789
    //   657: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   660: astore 45
    //   662: aload_0
    //   663: aload 45
    //   665: invokespecial 458	com/android/providers/settings/DatabaseHelper:loadDefaultAnimationSettings	(Landroid/database/sqlite/SQLiteStatement;)V
    //   668: aload_1
    //   669: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   672: aload_1
    //   673: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   676: aload 45
    //   678: ifnull +8 -> 686
    //   681: aload 45
    //   683: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   686: bipush 32
    //   688: istore 5
    //   690: iload 5
    //   692: bipush 32
    //   694: if_icmpne +66 -> 760
    //   697: ldc_w 351
    //   700: invokestatic 354	android/os/SystemProperties:get	(Ljava/lang/String;)Ljava/lang/String;
    //   703: astore 43
    //   705: aload 43
    //   707: invokestatic 156	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   710: ifne +46 -> 756
    //   713: aload_1
    //   714: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   717: aload_1
    //   718: new 158	java/lang/StringBuilder
    //   721: dup
    //   722: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   725: ldc_w 791
    //   728: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   731: aload 43
    //   733: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: ldc_w 793
    //   739: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   742: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   745: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   748: aload_1
    //   749: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   752: aload_1
    //   753: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   756: bipush 33
    //   758: istore 5
    //   760: iload 5
    //   762: bipush 33
    //   764: if_icmpne +26 -> 790
    //   767: aload_1
    //   768: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   771: aload_1
    //   772: ldc_w 795
    //   775: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   778: aload_1
    //   779: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   782: aload_1
    //   783: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   786: bipush 34
    //   788: istore 5
    //   790: iload 5
    //   792: bipush 34
    //   794: if_icmpne +47 -> 841
    //   797: aload_1
    //   798: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   801: aconst_null
    //   802: astore 40
    //   804: aload_1
    //   805: ldc_w 303
    //   808: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   811: astore 40
    //   813: aload_0
    //   814: aload 40
    //   816: invokespecial 376	com/android/providers/settings/DatabaseHelper:loadSecure35Settings	(Landroid/database/sqlite/SQLiteStatement;)V
    //   819: aload_1
    //   820: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   823: aload_1
    //   824: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   827: aload 40
    //   829: ifnull +8 -> 837
    //   832: aload 40
    //   834: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   837: bipush 35
    //   839: istore 5
    //   841: iload 5
    //   843: bipush 35
    //   845: if_icmpne +7 -> 852
    //   848: bipush 36
    //   850: istore 5
    //   852: iload 5
    //   854: bipush 36
    //   856: if_icmpne +61 -> 917
    //   859: aload_1
    //   860: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   863: aload_1
    //   864: ldc_w 773
    //   867: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   870: aload_1
    //   871: new 158	java/lang/StringBuilder
    //   874: dup
    //   875: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   878: ldc_w 775
    //   881: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   884: sipush 166
    //   887: invokestatic 777	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   890: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   893: ldc_w 779
    //   896: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   899: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   902: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   905: aload_1
    //   906: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   909: aload_1
    //   910: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   913: bipush 37
    //   915: istore 5
    //   917: iload 5
    //   919: bipush 37
    //   921: if_icmpne +53 -> 974
    //   924: aload_1
    //   925: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   928: aconst_null
    //   929: astore 37
    //   931: aload_1
    //   932: ldc_w 411
    //   935: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   938: astore 37
    //   940: aload_0
    //   941: aload 37
    //   943: ldc_w 443
    //   946: ldc_w 444
    //   949: invokespecial 300	com/android/providers/settings/DatabaseHelper:loadStringSetting	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/String;I)V
    //   952: aload_1
    //   953: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   956: aload_1
    //   957: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   960: aload 37
    //   962: ifnull +8 -> 970
    //   965: aload 37
    //   967: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   970: bipush 38
    //   972: istore 5
    //   974: iload 5
    //   976: bipush 38
    //   978: if_icmpne +70 -> 1048
    //   981: aload_1
    //   982: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   985: aload_0
    //   986: getfield 52	com/android/providers/settings/DatabaseHelper:mContext	Landroid/content/Context;
    //   989: invokevirtual 103	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   992: ldc_w 335
    //   995: invokevirtual 248	android/content/res/Resources:getBoolean	(I)Z
    //   998: ifeq +1313 -> 2311
    //   1001: ldc 250
    //   1003: astore 36
    //   1005: aload_1
    //   1006: new 158	java/lang/StringBuilder
    //   1009: dup
    //   1010: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   1013: ldc_w 797
    //   1016: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1019: aload 36
    //   1021: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1024: ldc_w 793
    //   1027: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1030: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1033: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1036: aload_1
    //   1037: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1040: aload_1
    //   1041: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1044: bipush 39
    //   1046: istore 5
    //   1048: iload 5
    //   1050: bipush 39
    //   1052: if_icmpne +70 -> 1122
    //   1055: aload_1
    //   1056: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1059: aload_0
    //   1060: getfield 52	com/android/providers/settings/DatabaseHelper:mContext	Landroid/content/Context;
    //   1063: invokevirtual 103	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   1066: ldc_w 456
    //   1069: invokevirtual 248	android/content/res/Resources:getBoolean	(I)Z
    //   1072: ifeq +1256 -> 2328
    //   1075: ldc 250
    //   1077: astore 34
    //   1079: aload_1
    //   1080: new 158	java/lang/StringBuilder
    //   1083: dup
    //   1084: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   1087: ldc_w 799
    //   1090: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1093: aload 34
    //   1095: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1098: ldc_w 793
    //   1101: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1104: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1107: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1110: aload_1
    //   1111: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1114: aload_1
    //   1115: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1118: bipush 40
    //   1120: istore 5
    //   1122: iload 5
    //   1124: bipush 40
    //   1126: if_icmpne +61 -> 1187
    //   1129: aload_1
    //   1130: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1133: aconst_null
    //   1134: astore 31
    //   1136: aload_1
    //   1137: ldc_w 785
    //   1140: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1143: aload_1
    //   1144: ldc_w 787
    //   1147: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1150: aload_1
    //   1151: ldc_w 789
    //   1154: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   1157: astore 31
    //   1159: aload_0
    //   1160: aload 31
    //   1162: invokespecial 458	com/android/providers/settings/DatabaseHelper:loadDefaultAnimationSettings	(Landroid/database/sqlite/SQLiteStatement;)V
    //   1165: aload_1
    //   1166: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1169: aload_1
    //   1170: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1173: aload 31
    //   1175: ifnull +8 -> 1183
    //   1178: aload 31
    //   1180: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   1183: bipush 41
    //   1185: istore 5
    //   1187: iload 5
    //   1189: bipush 41
    //   1191: if_icmpne +54 -> 1245
    //   1194: aload_1
    //   1195: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1198: aconst_null
    //   1199: astore 29
    //   1201: aload_1
    //   1202: ldc_w 801
    //   1205: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1208: aload_1
    //   1209: ldc_w 789
    //   1212: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   1215: astore 29
    //   1217: aload_0
    //   1218: aload 29
    //   1220: invokespecial 487	com/android/providers/settings/DatabaseHelper:loadDefaultHapticSettings	(Landroid/database/sqlite/SQLiteStatement;)V
    //   1223: aload_1
    //   1224: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1227: aload_1
    //   1228: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1231: aload 29
    //   1233: ifnull +8 -> 1241
    //   1236: aload 29
    //   1238: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   1241: bipush 42
    //   1243: istore 5
    //   1245: iload 5
    //   1247: bipush 42
    //   1249: if_icmpne +53 -> 1302
    //   1252: aload_1
    //   1253: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1256: aconst_null
    //   1257: astore 27
    //   1259: aload_1
    //   1260: ldc_w 789
    //   1263: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   1266: astore 27
    //   1268: aload_0
    //   1269: aload 27
    //   1271: ldc_w 489
    //   1274: ldc_w 490
    //   1277: invokespecial 274	com/android/providers/settings/DatabaseHelper:loadBooleanSetting	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/String;I)V
    //   1280: aload_1
    //   1281: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1284: aload_1
    //   1285: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1288: aload 27
    //   1290: ifnull +8 -> 1298
    //   1293: aload 27
    //   1295: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   1298: bipush 43
    //   1300: istore 5
    //   1302: iload 5
    //   1304: bipush 43
    //   1306: if_icmpne +59 -> 1365
    //   1309: aload_1
    //   1310: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1313: aconst_null
    //   1314: astore 25
    //   1316: aload_1
    //   1317: ldc_w 411
    //   1320: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   1323: astore 25
    //   1325: aload_0
    //   1326: aload 25
    //   1328: ldc_w 600
    //   1331: getstatic 588	android/media/AudioManager:DEFAULT_STREAM_VOLUME	[I
    //   1334: bipush 6
    //   1336: iaload
    //   1337: invokestatic 222	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1340: invokespecial 254	com/android/providers/settings/DatabaseHelper:loadSetting	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/String;Ljava/lang/Object;)V
    //   1343: aload_1
    //   1344: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1347: aload_1
    //   1348: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1351: aload 25
    //   1353: ifnull +8 -> 1361
    //   1356: aload 25
    //   1358: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   1361: bipush 44
    //   1363: istore 5
    //   1365: iload 5
    //   1367: bipush 44
    //   1369: if_icmpne +21 -> 1390
    //   1372: aload_1
    //   1373: ldc_w 803
    //   1376: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1379: aload_1
    //   1380: ldc_w 805
    //   1383: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1386: bipush 45
    //   1388: istore 5
    //   1390: iload 5
    //   1392: bipush 45
    //   1394: if_icmpne +47 -> 1441
    //   1397: aload_1
    //   1398: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1401: aload_1
    //   1402: ldc_w 807
    //   1405: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1408: aload_1
    //   1409: ldc_w 809
    //   1412: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1415: aload_1
    //   1416: ldc_w 811
    //   1419: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1422: aload_1
    //   1423: ldc_w 813
    //   1426: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1429: aload_1
    //   1430: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1433: aload_1
    //   1434: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1437: bipush 46
    //   1439: istore 5
    //   1441: iload 5
    //   1443: bipush 46
    //   1445: if_icmpne +26 -> 1471
    //   1448: aload_1
    //   1449: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1452: aload_1
    //   1453: ldc_w 815
    //   1456: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1459: aload_1
    //   1460: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1463: aload_1
    //   1464: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1467: bipush 47
    //   1469: istore 5
    //   1471: iload 5
    //   1473: bipush 47
    //   1475: if_icmpne +26 -> 1501
    //   1478: aload_1
    //   1479: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1482: aload_1
    //   1483: ldc_w 815
    //   1486: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1489: aload_1
    //   1490: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1493: aload_1
    //   1494: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1497: bipush 48
    //   1499: istore 5
    //   1501: iload 5
    //   1503: bipush 48
    //   1505: if_icmpne +7 -> 1512
    //   1508: bipush 49
    //   1510: istore 5
    //   1512: iload 5
    //   1514: bipush 49
    //   1516: if_icmpne +47 -> 1563
    //   1519: aload_1
    //   1520: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1523: aconst_null
    //   1524: astore 20
    //   1526: aload_1
    //   1527: ldc_w 789
    //   1530: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   1533: astore 20
    //   1535: aload_0
    //   1536: aload 20
    //   1538: invokespecial 497	com/android/providers/settings/DatabaseHelper:loadUISoundEffectsSettings	(Landroid/database/sqlite/SQLiteStatement;)V
    //   1541: aload_1
    //   1542: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1545: aload_1
    //   1546: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1549: aload 20
    //   1551: ifnull +8 -> 1559
    //   1554: aload 20
    //   1556: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   1559: bipush 50
    //   1561: istore 5
    //   1563: iload 5
    //   1565: bipush 50
    //   1567: if_icmpne +7 -> 1574
    //   1570: bipush 51
    //   1572: istore 5
    //   1574: iload 5
    //   1576: bipush 51
    //   1578: if_icmpne +87 -> 1665
    //   1581: bipush 9
    //   1583: anewarray 133	java/lang/String
    //   1586: astore 19
    //   1588: aload 19
    //   1590: iconst_0
    //   1591: ldc_w 817
    //   1594: aastore
    //   1595: aload 19
    //   1597: iconst_1
    //   1598: ldc_w 819
    //   1601: aastore
    //   1602: aload 19
    //   1604: iconst_2
    //   1605: ldc_w 821
    //   1608: aastore
    //   1609: aload 19
    //   1611: iconst_3
    //   1612: ldc_w 823
    //   1615: aastore
    //   1616: aload 19
    //   1618: iconst_4
    //   1619: ldc_w 825
    //   1622: aastore
    //   1623: aload 19
    //   1625: iconst_5
    //   1626: ldc_w 827
    //   1629: aastore
    //   1630: aload 19
    //   1632: bipush 6
    //   1634: ldc_w 817
    //   1637: aastore
    //   1638: aload 19
    //   1640: bipush 7
    //   1642: ldc_w 829
    //   1645: aastore
    //   1646: aload 19
    //   1648: bipush 8
    //   1650: ldc_w 831
    //   1653: aastore
    //   1654: aload_0
    //   1655: aload_1
    //   1656: aload 19
    //   1658: invokespecial 771	com/android/providers/settings/DatabaseHelper:moveFromSystemToSecure	(Landroid/database/sqlite/SQLiteDatabase;[Ljava/lang/String;)V
    //   1661: bipush 52
    //   1663: istore 5
    //   1665: iload 5
    //   1667: bipush 52
    //   1669: if_icmpne +53 -> 1722
    //   1672: aload_1
    //   1673: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1676: aconst_null
    //   1677: astore 17
    //   1679: aload_1
    //   1680: ldc_w 789
    //   1683: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   1686: astore 17
    //   1688: aload_0
    //   1689: aload 17
    //   1691: ldc_w 499
    //   1694: ldc_w 500
    //   1697: invokespecial 274	com/android/providers/settings/DatabaseHelper:loadBooleanSetting	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/String;I)V
    //   1700: aload_1
    //   1701: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1704: aload_1
    //   1705: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1708: aload 17
    //   1710: ifnull +8 -> 1718
    //   1713: aload 17
    //   1715: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   1718: bipush 53
    //   1720: istore 5
    //   1722: iload 5
    //   1724: bipush 53
    //   1726: if_icmpne +7 -> 1733
    //   1729: bipush 54
    //   1731: istore 5
    //   1733: iload 5
    //   1735: bipush 54
    //   1737: if_icmpne +24 -> 1761
    //   1740: aload_1
    //   1741: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1744: aload_0
    //   1745: aload_1
    //   1746: invokespecial 833	com/android/providers/settings/DatabaseHelper:upgradeScreenTimeoutFromNever	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   1749: aload_1
    //   1750: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1753: aload_1
    //   1754: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1757: bipush 55
    //   1759: istore 5
    //   1761: iload 5
    //   1763: bipush 55
    //   1765: if_icmpne +156 -> 1921
    //   1768: iconst_2
    //   1769: anewarray 133	java/lang/String
    //   1772: astore 11
    //   1774: aload 11
    //   1776: iconst_0
    //   1777: ldc_w 492
    //   1780: aastore
    //   1781: aload 11
    //   1783: iconst_1
    //   1784: ldc_w 494
    //   1787: aastore
    //   1788: aload_0
    //   1789: aload_1
    //   1790: aload 11
    //   1792: invokespecial 771	com/android/providers/settings/DatabaseHelper:moveFromSystemToSecure	(Landroid/database/sqlite/SQLiteDatabase;[Ljava/lang/String;)V
    //   1795: aload_1
    //   1796: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1799: aconst_null
    //   1800: astore 12
    //   1802: aconst_null
    //   1803: astore 13
    //   1805: aload_1
    //   1806: ldc_w 789
    //   1809: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   1812: astore 12
    //   1814: aload_1
    //   1815: ldc_w 835
    //   1818: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   1821: astore 13
    //   1823: aload_0
    //   1824: aload 12
    //   1826: ldc_w 492
    //   1829: iconst_0
    //   1830: invokestatic 222	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1833: invokespecial 254	com/android/providers/settings/DatabaseHelper:loadSetting	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/String;Ljava/lang/Object;)V
    //   1836: aload_0
    //   1837: aload 12
    //   1839: ldc_w 494
    //   1842: iconst_0
    //   1843: invokestatic 222	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1846: invokespecial 254	com/android/providers/settings/DatabaseHelper:loadSetting	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/String;Ljava/lang/Object;)V
    //   1849: ldc 14
    //   1851: new 158	java/lang/StringBuilder
    //   1854: dup
    //   1855: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   1858: ldc_w 837
    //   1861: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1864: iload 5
    //   1866: invokevirtual 699	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1869: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1872: invokestatic 176	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   1875: pop
    //   1876: aload_0
    //   1877: aload 13
    //   1879: ldc_w 374
    //   1882: iconst_0
    //   1883: invokestatic 222	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1886: invokespecial 254	com/android/providers/settings/DatabaseHelper:loadSetting	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/String;Ljava/lang/Object;)V
    //   1889: aload_1
    //   1890: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1893: aload_1
    //   1894: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1897: aload 12
    //   1899: ifnull +8 -> 1907
    //   1902: aload 12
    //   1904: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   1907: aload 13
    //   1909: ifnull +8 -> 1917
    //   1912: aload 13
    //   1914: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   1917: bipush 56
    //   1919: istore 5
    //   1921: iload 5
    //   1923: bipush 56
    //   1925: if_icmpne +60 -> 1985
    //   1928: aload_1
    //   1929: invokevirtual 613	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   1932: aconst_null
    //   1933: astore 9
    //   1935: aload_1
    //   1936: ldc_w 839
    //   1939: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1942: aload_1
    //   1943: ldc_w 411
    //   1946: invokevirtual 307	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   1949: astore 9
    //   1951: aload_0
    //   1952: aload 9
    //   1954: ldc_w 443
    //   1957: ldc_w 444
    //   1960: invokespecial 300	com/android/providers/settings/DatabaseHelper:loadStringSetting	(Landroid/database/sqlite/SQLiteStatement;Ljava/lang/String;I)V
    //   1963: aload_1
    //   1964: invokevirtual 620	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   1967: aload_1
    //   1968: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1971: aload 9
    //   1973: ifnull +8 -> 1981
    //   1976: aload 9
    //   1978: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   1981: bipush 57
    //   1983: istore 5
    //   1985: iload 5
    //   1987: iload_3
    //   1988: if_icmpeq +184 -> 2172
    //   1991: ldc 14
    //   1993: new 158	java/lang/StringBuilder
    //   1996: dup
    //   1997: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   2000: ldc_w 841
    //   2003: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2006: iload 5
    //   2008: invokevirtual 699	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2011: ldc_w 843
    //   2014: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2017: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2020: invokestatic 176	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   2023: pop
    //   2024: aload_1
    //   2025: ldc_w 845
    //   2028: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2031: aload_1
    //   2032: ldc_w 847
    //   2035: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2038: aload_1
    //   2039: ldc_w 849
    //   2042: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2045: aload_1
    //   2046: ldc_w 851
    //   2049: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2052: aload_1
    //   2053: ldc_w 803
    //   2056: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2059: aload_1
    //   2060: ldc_w 805
    //   2063: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2066: aload_1
    //   2067: ldc_w 853
    //   2070: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2073: aload_1
    //   2074: ldc_w 855
    //   2077: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2080: aload_1
    //   2081: ldc_w 857
    //   2084: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2087: aload_1
    //   2088: ldc_w 859
    //   2091: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2094: aload_1
    //   2095: ldc_w 861
    //   2098: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2101: aload_0
    //   2102: aload_1
    //   2103: invokevirtual 863	com/android/providers/settings/DatabaseHelper:onCreate	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   2106: new 158	java/lang/StringBuilder
    //   2109: dup
    //   2110: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   2113: iload_2
    //   2114: invokevirtual 699	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2117: ldc 167
    //   2119: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2122: iload 5
    //   2124: invokevirtual 699	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2127: ldc 167
    //   2129: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2132: iload_3
    //   2133: invokevirtual 699	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2136: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2139: astore 8
    //   2141: aload_1
    //   2142: new 158	java/lang/StringBuilder
    //   2145: dup
    //   2146: invokespecial 159	java/lang/StringBuilder:<init>	()V
    //   2149: ldc_w 865
    //   2152: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2155: aload 8
    //   2157: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2160: ldc_w 793
    //   2163: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2166: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2169: invokevirtual 62	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   2172: return
    //   2173: astore 52
    //   2175: aload_1
    //   2176: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2179: aload 52
    //   2181: athrow
    //   2182: astore 51
    //   2184: aload_1
    //   2185: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2188: aload 51
    //   2190: athrow
    //   2191: astore 50
    //   2193: aload_1
    //   2194: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2197: aload 50
    //   2199: athrow
    //   2200: astore 49
    //   2202: aload_1
    //   2203: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2206: aload 49
    //   2208: athrow
    //   2209: astore 6
    //   2211: aload_1
    //   2212: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2215: aload 6
    //   2217: athrow
    //   2218: astore 47
    //   2220: aload_1
    //   2221: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2224: aload 47
    //   2226: athrow
    //   2227: astore 46
    //   2229: aload_1
    //   2230: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2233: aload 45
    //   2235: ifnull +8 -> 2243
    //   2238: aload 45
    //   2240: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2243: aload 46
    //   2245: athrow
    //   2246: astore 44
    //   2248: aload_1
    //   2249: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2252: aload 44
    //   2254: athrow
    //   2255: astore 42
    //   2257: aload_1
    //   2258: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2261: aload 42
    //   2263: athrow
    //   2264: astore 41
    //   2266: aload_1
    //   2267: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2270: aload 40
    //   2272: ifnull +8 -> 2280
    //   2275: aload 40
    //   2277: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2280: aload 41
    //   2282: athrow
    //   2283: astore 39
    //   2285: aload_1
    //   2286: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2289: aload 39
    //   2291: athrow
    //   2292: astore 38
    //   2294: aload_1
    //   2295: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2298: aload 37
    //   2300: ifnull +8 -> 2308
    //   2303: aload 37
    //   2305: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2308: aload 38
    //   2310: athrow
    //   2311: ldc_w 256
    //   2314: astore 36
    //   2316: goto -1311 -> 1005
    //   2319: astore 35
    //   2321: aload_1
    //   2322: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2325: aload 35
    //   2327: athrow
    //   2328: ldc_w 256
    //   2331: astore 34
    //   2333: goto -1254 -> 1079
    //   2336: astore 33
    //   2338: aload_1
    //   2339: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2342: aload 33
    //   2344: athrow
    //   2345: astore 32
    //   2347: aload_1
    //   2348: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2351: aload 31
    //   2353: ifnull +8 -> 2361
    //   2356: aload 31
    //   2358: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2361: aload 32
    //   2363: athrow
    //   2364: astore 30
    //   2366: aload_1
    //   2367: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2370: aload 29
    //   2372: ifnull +8 -> 2380
    //   2375: aload 29
    //   2377: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2380: aload 30
    //   2382: athrow
    //   2383: astore 28
    //   2385: aload_1
    //   2386: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2389: aload 27
    //   2391: ifnull +8 -> 2399
    //   2394: aload 27
    //   2396: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2399: aload 28
    //   2401: athrow
    //   2402: astore 26
    //   2404: aload_1
    //   2405: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2408: aload 25
    //   2410: ifnull +8 -> 2418
    //   2413: aload 25
    //   2415: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2418: aload 26
    //   2420: athrow
    //   2421: astore 24
    //   2423: aload_1
    //   2424: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2427: aload 24
    //   2429: athrow
    //   2430: astore 23
    //   2432: aload_1
    //   2433: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2436: aload 23
    //   2438: athrow
    //   2439: astore 22
    //   2441: aload_1
    //   2442: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2445: aload 22
    //   2447: athrow
    //   2448: astore 21
    //   2450: aload_1
    //   2451: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2454: aload 20
    //   2456: ifnull +8 -> 2464
    //   2459: aload 20
    //   2461: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2464: aload 21
    //   2466: athrow
    //   2467: astore 18
    //   2469: aload_1
    //   2470: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2473: aload 17
    //   2475: ifnull +8 -> 2483
    //   2478: aload 17
    //   2480: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2483: aload 18
    //   2485: athrow
    //   2486: astore 16
    //   2488: aload_1
    //   2489: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2492: aload 16
    //   2494: athrow
    //   2495: astore 14
    //   2497: aload_1
    //   2498: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2501: aload 12
    //   2503: ifnull +8 -> 2511
    //   2506: aload 12
    //   2508: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2511: aload 13
    //   2513: ifnull +8 -> 2521
    //   2516: aload 13
    //   2518: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2521: aload 14
    //   2523: athrow
    //   2524: astore 10
    //   2526: aload_1
    //   2527: invokevirtual 623	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   2530: aload 9
    //   2532: ifnull +8 -> 2540
    //   2535: aload 9
    //   2537: invokevirtual 393	android/database/sqlite/SQLiteStatement:close	()V
    //   2540: aload 10
    //   2542: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   101	140	2173	finally
    //   159	177	2182	finally
    //   196	214	2191	finally
    //   233	242	2200	finally
    //   535	580	2209	finally
    //   599	617	2218	finally
    //   639	672	2227	finally
    //   717	752	2246	finally
    //   771	782	2255	finally
    //   804	823	2264	finally
    //   863	909	2283	finally
    //   931	956	2292	finally
    //   985	1040	2319	finally
    //   2311	2316	2319	finally
    //   1059	1114	2336	finally
    //   2328	2333	2336	finally
    //   1136	1169	2345	finally
    //   1201	1227	2364	finally
    //   1259	1284	2383	finally
    //   1316	1347	2402	finally
    //   1401	1433	2421	finally
    //   1452	1463	2430	finally
    //   1482	1493	2439	finally
    //   1526	1545	2448	finally
    //   1679	1704	2467	finally
    //   1744	1753	2486	finally
    //   1805	1893	2495	finally
    //   1935	1967	2524	finally
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.providers.settings.DatabaseHelper
 * JD-Core Version:    0.6.0
 */