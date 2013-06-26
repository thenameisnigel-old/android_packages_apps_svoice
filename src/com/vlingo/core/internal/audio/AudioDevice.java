package com.vlingo.core.internal.audio;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.media.AudioManager;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.StringUtils;

public class AudioDevice
{
  public static final int BT_AMR_PADDING_MILLIS = 1200;
  private static final String DEFAULT_AUDIO_DEVICE_NAME = "Android";
  private static final String DEFAULT_BLUETOOTH_DEVICE_NAME = "Unknown";
  private static AudioDevice s_AudioDevice = null;
  private volatile String currentBluetoothHeadsetName = "Unknown";

  public static AudioDevice getInstance()
  {
    monitorenter;
    try
    {
      if (s_AudioDevice == null)
        s_AudioDevice = new AudioDevice();
      AudioDevice localAudioDevice = s_AudioDevice;
      monitorexit;
      return localAudioDevice;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String getAudioDeviceName()
  {
    if (isAudioBluetooth());
    for (String str = "Android/BT/" + this.currentBluetoothHeadsetName; ; str = "Android")
      return str;
  }

  public boolean isAudioBluetooth()
  {
    return ((AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio")).isBluetoothScoOn();
  }

  public boolean isAudioHeadset()
  {
    return ((AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio")).isWiredHeadsetOn();
  }

  public byte[] padAudioWithSilenceIfBT(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (isAudioBluetooth())
      paramArrayOfByte = AMRUtil.addPaddingToAMR(paramArrayOfByte, paramInt1, paramInt2, 1200);
    return paramArrayOfByte;
  }

  public void resetCurrentBluetoothDeviceName()
  {
    this.currentBluetoothHeadsetName = "Unknown";
  }

  public void setCurrentBluetoothDeviceName(BluetoothDevice paramBluetoothDevice)
  {
    if ((paramBluetoothDevice == null) || (StringUtils.isNullOrWhiteSpace(paramBluetoothDevice.getName())))
      resetCurrentBluetoothDeviceName();
    while (true)
    {
      return;
      this.currentBluetoothHeadsetName = paramBluetoothDevice.getName();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioDevice
 * JD-Core Version:    0.6.0
 */