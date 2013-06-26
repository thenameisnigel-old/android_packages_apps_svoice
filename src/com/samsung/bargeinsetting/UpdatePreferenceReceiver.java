package com.samsung.bargeinsetting;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings.System;

public class UpdatePreferenceReceiver extends BroadcastReceiver
{
  public static final String DB_KEY_DRIVING_MODE_ON = "driving_mode_on";
  public static final String DRIVING_MODE_ALARM_NOTIFICATION = "driving_mode_alarm_notification";
  private static final String DRIVING_MODE_CHATON_CALL_NOTIFICATION = "driving_mode_chaton_call_notification";
  public static final String DRIVING_MODE_INCOMING_CALL_NOTIFICATION = "driving_mode_incoming_call_notification";
  public static final String DRIVING_MODE_MESSAGE_NOTIFICATION = "driving_mode_message_notification";
  public static final String DRIVING_MODE_SCHEDULE_NOTIFICATION = "driving_mode_schedule_notification";
  private static final int NOT_SET = -1;
  private static final String VOICEINPUTCONTROL_ALARM = "voice_input_control_alarm";
  private static final String VOICEINPUTCONTROL_CAMERA = "voice_input_control_camera";
  private static final String VOICEINPUTCONTROL_CHATONV = "voice_input_control_chatonv";
  private static final String VOICEINPUTCONTROL_INCOMMING_CALL = "voice_input_control_incomming_calls";
  private static final String VOICEINPUTCONTROL_MUSIC = "voice_input_control_music";
  private static final String VOICEINPUTCONTROL_RADIO = "voice_input_control_radio";
  private final String KEY_VOICE_INPUT_CONTROL = "voice_input_control";

  private void onDrivingModePreference(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    int i = 1;
    int k = Settings.System.getInt(paramContext.getContentResolver(), "driving_mode_on", -1);
    int m = Settings.System.getInt(paramContext.getContentResolver(), "driving_mode_incoming_call_notification", -1);
    int n = Settings.System.getInt(paramContext.getContentResolver(), "driving_mode_message_notification", -1);
    int i1 = Settings.System.getInt(paramContext.getContentResolver(), "driving_mode_alarm_notification", -1);
    int i2 = Settings.System.getInt(paramContext.getContentResolver(), "driving_mode_schedule_notification", -1);
    int i3 = Settings.System.getInt(paramContext.getContentResolver(), "driving_mode_chaton_call_notification", -1);
    label146: label184: label222: ContentResolver localContentResolver1;
    if (k == -1)
    {
      ContentResolver localContentResolver6 = paramContext.getContentResolver();
      if (paramSharedPreferences.getBoolean("driving_mode_on", false))
      {
        int i12 = i;
        Settings.System.putInt(localContentResolver6, "driving_mode_on", i12);
      }
    }
    else
    {
      if (m == -1)
      {
        ContentResolver localContentResolver5 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("driving_mode_incoming_call_notification", i))
          break label311;
        int i10 = i;
        Settings.System.putInt(localContentResolver5, "driving_mode_incoming_call_notification", i10);
      }
      if (n == -1)
      {
        ContentResolver localContentResolver4 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("driving_mode_message_notification", i))
          break label317;
        int i8 = i;
        Settings.System.putInt(localContentResolver4, "driving_mode_message_notification", i8);
      }
      if (i1 == -1)
      {
        ContentResolver localContentResolver3 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("driving_mode_alarm_notification", i))
          break label323;
        int i6 = i;
        Settings.System.putInt(localContentResolver3, "driving_mode_alarm_notification", i6);
      }
      if (i2 == -1)
      {
        ContentResolver localContentResolver2 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("driving_mode_schedule_notification", i))
          break label329;
        int i4 = i;
        label260: Settings.System.putInt(localContentResolver2, "driving_mode_schedule_notification", i4);
      }
      if (i3 == -1)
      {
        localContentResolver1 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("driving_mode_chaton_call_notification", i))
          break label335;
      }
    }
    while (true)
    {
      Settings.System.putInt(localContentResolver1, "driving_mode_chaton_call_notification", i);
      return;
      int i13 = 0;
      break;
      label311: int i11 = 0;
      break label146;
      label317: int i9 = 0;
      break label184;
      label323: int i7 = 0;
      break label222;
      label329: int i5 = 0;
      break label260;
      label335: int j = 0;
    }
  }

  private void onVoiceControlPreference(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    int i = 1;
    int k = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control", -1);
    int m = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_incomming_calls", -1);
    int n = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_chatonv", -1);
    int i1 = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_alarm", -1);
    int i2 = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_camera", -1);
    int i3 = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_music", -1);
    int i4 = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_radio", -1);
    label159: label197: label235: ContentResolver localContentResolver1;
    if (k == -1)
    {
      ContentResolver localContentResolver7 = paramContext.getContentResolver();
      if (paramSharedPreferences.getBoolean("voice_input_control", false))
      {
        int i15 = i;
        Settings.System.putInt(localContentResolver7, "voice_input_control", i15);
      }
    }
    else
    {
      if (m == -1)
      {
        ContentResolver localContentResolver6 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("voice_input_control_incomming_calls", i))
          break label362;
        int i13 = i;
        Settings.System.putInt(localContentResolver6, "voice_input_control_incomming_calls", i13);
      }
      if (n == -1)
      {
        ContentResolver localContentResolver5 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("voice_input_control_chatonv", i))
          break label368;
        int i11 = i;
        Settings.System.putInt(localContentResolver5, "voice_input_control_chatonv", i11);
      }
      if (i1 == -1)
      {
        ContentResolver localContentResolver4 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("voice_input_control_alarm", i))
          break label374;
        int i9 = i;
        Settings.System.putInt(localContentResolver4, "voice_input_control_alarm", i9);
      }
      if (i2 == -1)
      {
        ContentResolver localContentResolver3 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("voice_input_control_camera", i))
          break label380;
        int i7 = i;
        label273: Settings.System.putInt(localContentResolver3, "voice_input_control_camera", i7);
      }
      if (i3 == -1)
      {
        ContentResolver localContentResolver2 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("voice_input_control_music", i))
          break label386;
        int i5 = i;
        label311: Settings.System.putInt(localContentResolver2, "voice_input_control_music", i5);
      }
      if (i4 == -1)
      {
        localContentResolver1 = paramContext.getContentResolver();
        if (!paramSharedPreferences.getBoolean("voice_input_control_radio", i))
          break label392;
      }
    }
    while (true)
    {
      Settings.System.putInt(localContentResolver1, "voice_input_control_radio", i);
      return;
      int i16 = 0;
      break;
      label362: int i14 = 0;
      break label159;
      label368: int i12 = 0;
      break label197;
      label374: int i10 = 0;
      break label235;
      label380: int i8 = 0;
      break label273;
      label386: int i6 = 0;
      break label311;
      label392: int j = 0;
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
      onVoiceControlPreference(paramContext, localSharedPreferences);
      onDrivingModePreference(paramContext, localSharedPreferences);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.bargeinsetting.UpdatePreferenceReceiver
 * JD-Core Version:    0.6.0
 */