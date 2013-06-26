package com.samsung.wakeupsetting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;
import com.vlingo.core.internal.util.ApplicationAdapter;
import java.util.List;

public class BluetoothManager
{
  private static final int PROXY_NOT_ACQUIRED = 0;
  private static final int PROXY_RELEASE_REQUESTED = 2;
  private static final int PROXY_REQUESTED = 1;
  private static final String TAG = "SV_BluetoothManager";
  private static BluetoothManager mInstance = null;
  private BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
  private BluetoothDevice mDevice;
  private BluetoothHeadset mHeadset;
  private int mProxyState = 0;
  private BluetoothProfile.ServiceListener mServiceListener = new BluetoothManager.2(this);
  private final BroadcastReceiver mSystemUpdateReceiver = new BluetoothManager.1(this);

  private BluetoothManager()
  {
    this.mBtAdapter.getProfileProxy(ApplicationAdapter.getInstance().getApplicationContext(), this.mServiceListener, 1);
    this.mProxyState = 1;
  }

  private BluetoothDevice getConnectedDevice()
  {
    List localList;
    if (this.mHeadset != null)
    {
      localList = this.mHeadset.getConnectedDevices();
      if (localList.size() <= 0);
    }
    for (BluetoothDevice localBluetoothDevice = (BluetoothDevice)localList.get(0); ; localBluetoothDevice = null)
      return localBluetoothDevice;
  }

  public static BluetoothManager getInstance()
  {
    if (mInstance == null)
      mInstance = new BluetoothManager();
    return mInstance;
  }

  private void handleStateChange(int paramInt)
  {
    switch (paramInt)
    {
    case 1:
    default:
    case 0:
    case 3:
    case 2:
    }
    while (true)
    {
      return;
      stopSCO();
      continue;
      startSCO();
    }
  }

  public static boolean isBluetoothAudioOn()
  {
    AudioManager localAudioManager = (AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio");
    Log.e("SV_BluetoothManager", "isBluetoothAudioOn " + localAudioManager.isBluetoothScoOn());
    return localAudioManager.isBluetoothScoOn();
  }

  public static boolean isHeadsetConnected()
  {
    boolean bool = true;
    if ((2 != BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1)) && (1 != BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1)))
      bool = false;
    Log.e("SV_BluetoothManager", "isHeadsetConnected(): " + bool);
    return bool;
  }

  public static void setBluetoothAudioOn(boolean paramBoolean)
  {
    Log.e("SV_BluetoothManager", "setBluetoothAudioOn: " + paramBoolean);
    ((AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio")).setBluetoothScoOn(paramBoolean);
  }

  public void destroy()
  {
    try
    {
      ApplicationAdapter.getInstance().getApplicationContext().unregisterReceiver(this.mSystemUpdateReceiver);
      if (this.mHeadset != null)
      {
        if ((isHeadsetConnected()) && (isBluetoothAudioOn()))
        {
          setBluetoothAudioOn(false);
          if (this.mDevice != null)
            this.mHeadset.stopVoiceRecognition(this.mDevice);
        }
        BluetoothAdapter.getDefaultAdapter().closeProfileProxy(1, this.mHeadset);
        this.mProxyState = 0;
        this.mHeadset = null;
        mInstance = null;
        return;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
      {
        localIllegalArgumentException.printStackTrace();
        continue;
        this.mProxyState = 2;
      }
    }
  }

  public void init()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
    localIntentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
    localIntentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
    localIntentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
    localIntentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
    localIntentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
    ApplicationAdapter.getInstance().getApplicationContext().registerReceiver(this.mSystemUpdateReceiver, localIntentFilter);
  }

  public void startSCO()
  {
    if ((isHeadsetConnected()) && (!isBluetoothAudioOn()))
    {
      setBluetoothAudioOn(true);
      if ((this.mHeadset != null) && (this.mDevice != null))
        this.mHeadset.startVoiceRecognition(this.mDevice);
    }
  }

  public void stopSCO()
  {
    if (isBluetoothAudioOn())
    {
      setBluetoothAudioOn(false);
      if ((this.mHeadset != null) && (isHeadsetConnected()) && (this.mDevice != null))
        this.mHeadset.stopVoiceRecognition(this.mDevice);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wakeupsetting.BluetoothManager
 * JD-Core Version:    0.6.0
 */