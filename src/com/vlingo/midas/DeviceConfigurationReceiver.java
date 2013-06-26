package com.vlingo.midas;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings.System;
import android.util.Log;
import com.vlingo.midas.settings.MidasSettings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeviceConfigurationReceiver extends BroadcastReceiver
{
  public static final String ACTION_CHECK_DOUBLE_TAP_CONFIG = "com.vlingo.client.app.action.CHECK_DOUBLE_TAP_CONFIG";
  private static final int NOT_SET = -1;
  private static final String SYSTEM_DOUBLE_TAP_SETTING = "double_tab_launch";
  private static final String TAG = "DeviceConfigurationReceiver";

  private boolean doCheckDoubleTapConfig(Context paramContext)
  {
    int i = 1;
    boolean bool1 = true;
    int j = Settings.System.getInt(paramContext.getContentResolver(), "double_tab_launch", -1);
    boolean bool2;
    ContentResolver localContentResolver1;
    int k;
    if (j == -1)
    {
      bool2 = getDeviceDoubleTapConfig();
      localContentResolver1 = paramContext.getContentResolver();
      if (bool2)
        k = i;
    }
    while (true)
    {
      bool1 = Settings.System.putInt(localContentResolver1, "double_tab_launch", k);
      if (!bool1)
        Log.e("DeviceConfigurationReceiver", "double_tab_launch set is failed. Try to set again.");
      try
      {
        Thread.sleep(500L);
        ContentResolver localContentResolver2 = paramContext.getContentResolver();
        if (bool2);
        while (true)
        {
          boolean bool3 = Settings.System.putInt(localContentResolver2, "double_tab_launch", i);
          bool1 = bool3;
          label96: if (bool1)
            MidasSettings.setBoolean("launch_voicetalk", bool2);
          return bool1;
          k = 0;
          break;
          i = 0;
        }
        if (j == i);
        for (bool2 = i; ; bool2 = false)
          break;
      }
      catch (InterruptedException localInterruptedException)
      {
        break label96;
      }
    }
  }

  private boolean doCheckVoiceInputControl(Context paramContext)
  {
    int i = 0;
    int j = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control", -1);
    int k = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_incomming_calls", -1);
    int m = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_chatonv", -1);
    int n = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_alarm", -1);
    int i1 = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_camera", -1);
    int i2 = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_music", -1);
    int i3 = Settings.System.getInt(paramContext.getContentResolver(), "voice_input_control_radio", -1);
    if ((j == -1) && (!Settings.System.putInt(paramContext.getContentResolver(), "voice_input_control", 0)));
    label319: label332: 
    while (true)
    {
      return i;
      if (((k == -1) && (!Settings.System.putInt(paramContext.getContentResolver(), "voice_input_control_incomming_calls", 1))) || ((m == -1) && (!Settings.System.putInt(paramContext.getContentResolver(), "voice_input_control_chatonv", 1))) || ((n == -1) && (!Settings.System.putInt(paramContext.getContentResolver(), "voice_input_control_alarm", 1))) || ((i1 == -1) && (!Settings.System.putInt(paramContext.getContentResolver(), "voice_input_control_camera", 1))))
        continue;
      if (i2 == -1)
        if ((!Build.MODEL.equals("SHV-E250S")) && (!Build.MODEL.equals("SHV-E250L")) && (!Build.MODEL.equals("SHV-E250K")) && (!Build.MODEL.equals("SHV-E210S")) && (!Build.MODEL.equals("SHV-E210L")) && (!Build.MODEL.equals("SHV-E210K")) && (!Build.MODEL.equals("SHW-M440S")))
          break label319;
      for (boolean bool = Settings.System.putInt(paramContext.getContentResolver(), "voice_input_control_music", 0); ; bool = Settings.System.putInt(paramContext.getContentResolver(), "voice_input_control_music", 1))
      {
        if (!bool)
          break label332;
        if ((i3 == -1) && (!Settings.System.putInt(paramContext.getContentResolver(), "voice_input_control_radio", 1)))
          break;
        i = 1;
        break;
      }
    }
  }

  private boolean getDeviceDoubleTapConfig()
  {
    int i = 1;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop ro.csc.sales_code").getInputStream()));
      String str = localBufferedReader.readLine();
      localBufferedReader.close();
      boolean bool = str.equalsIgnoreCase("VZW");
      if (bool)
        i = 0;
      label54: return i;
    }
    catch (IOException localIOException)
    {
      break label54;
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    int i;
    if ((str.equals("android.intent.action.BOOT_COMPLETED")) || (str.equals("com.vlingo.client.app.action.CHECK_DOUBLE_TAP_CONFIG")))
    {
      doCheckDoubleTapConfig(paramContext);
      if (!doCheckVoiceInputControl(paramContext))
        break label89;
      if (!str.equals("android.intent.action.BOOT_COMPLETED"))
        break label83;
      i = 0;
      if (i == 0)
        Log.w("DeviceConfigurationReceiver", "Device configuration is succeed. S Voice will be force killed. It is not a bug.");
      paramContext.getPackageManager().setComponentEnabledSetting(new ComponentName(paramContext, DeviceConfigurationReceiver.class), 2, i);
    }
    while (true)
    {
      return;
      label83: i = 1;
      break;
      label89: Log.e("DeviceConfigurationReceiver", "Device configuration is failed. It will be try to set again at next booting time.");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.DeviceConfigurationReceiver
 * JD-Core Version:    0.6.0
 */