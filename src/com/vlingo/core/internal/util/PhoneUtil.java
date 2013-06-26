package com.vlingo.core.internal.util;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.settings.Settings;

public class PhoneUtil
{
  public static final int DEFAULT_AUDIO_STREAM = 3;

  public static AudioManager getAudioManager()
  {
    return (AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio");
  }

  public static int getCurrentStream()
  {
    monitorenter;
    try
    {
      boolean bool = BluetoothManager.isBluetoothAudioOn();
      if (bool)
      {
        i = 6;
        return i;
      }
      int i = 3;
    }
    finally
    {
      monitorexit;
    }
  }

  public static float getCurrentStreamMaxVolume()
  {
    return getAudioManager().getStreamMaxVolume(getCurrentStream());
  }

  public static float getCurrentStreamVolume()
  {
    return getAudioManager().getStreamVolume(getCurrentStream());
  }

  public static boolean phoneInUse(Context paramContext)
  {
    int i = 1;
    if (paramContext == null);
    while (true)
    {
      return i;
      if (((TelephonyManager)paramContext.getSystemService("phone")).getCallState() != 0)
        continue;
      i = 0;
    }
  }

  public static void turnOnSpeakerphoneIfRequired(Context paramContext)
  {
    if ((Settings.getBoolean("car_auto_start_speakerphone", false)) && (!BluetoothManager.isHeadsetConnected()))
    {
      AudioManager localAudioManager = getAudioManager();
      if ((!localAudioManager.isWiredHeadsetOn()) && (Build.VERSION.SDK_INT <= 15))
        localAudioManager.setSpeakerphoneOn(true);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.PhoneUtil
 * JD-Core Version:    0.6.0
 */