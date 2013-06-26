package com.vlingo.core.internal.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings.System;
import android.util.Log;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.TimerSingleton;
import com.vlingo.core.internal.util.VlingoApplicationInterface;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public final class BluetoothManager
  implements IAudioPlaybackService.AudioPlaybackListener
{
  private static final int SCO_RETRY_DELAY = 2000;
  private static final String SCO_RETRY_FLAG = "sco_retry_flag";
  private static final String SETTING_KEY_BVRA_SUPPORT = "supported_bvra";
  public static final int STREAM_BLUETOOTH_SCO = 6;
  private static BTStateBroadcastReceiver btStateBroadcastReceiver;
  private static boolean ignoreBtConnectEvent;
  private static BluetoothManager instance;
  private static final CopyOnWriteArrayList<BluetoothManagerListener> listeners;
  private static CloseType mAppCloseType;
  private static BluetoothDevice mBluetoothDevice;
  private static BluetoothHeadset mBluetoothHeadset;
  private static Runnable mOnScoConnectedTask;
  private static long mOnScoConnectedTaskTimeout;
  private static BluetoothProfile.ServiceListener mProfileListener;
  private static boolean mRightBeforeForeground;
  private static boolean mScoDisconnectingByAudioFocusLoss;
  private static TimerTask mScoTimeoutTask = null;
  private static ScoWorkerTask mScoWorkerTask;
  private static volatile boolean m_isStartSCOinProgress;

  static
  {
    mBluetoothDevice = null;
    mBluetoothHeadset = null;
    btStateBroadcastReceiver = null;
    instance = null;
    mScoWorkerTask = null;
    listeners = new CopyOnWriteArrayList();
    mAppCloseType = CloseType.BT_BUTTON;
    mScoDisconnectingByAudioFocusLoss = false;
    mRightBeforeForeground = false;
    ignoreBtConnectEvent = false;
  }

  public static void BluetoothManagerDestroy()
  {
    monitorenter;
    try
    {
      if ((mScoWorkerTask != null) && (mScoWorkerTask.isAlive()))
      {
        mScoWorkerTask.mHandler.removeCallbacksAndMessages(null);
        mScoWorkerTask.mHandler.getLooper().quit();
      }
      listeners.clear();
      mScoWorkerTask = null;
      mRightBeforeForeground = false;
      if (btStateBroadcastReceiver != null)
      {
        mAppCloseType = CloseType.NORMAL;
        stopSCO(true);
        ApplicationAdapter.getInstance().getApplicationContext().unregisterReceiver(btStateBroadcastReceiver);
        btStateBroadcastReceiver = null;
        BluetoothAdapter.getDefaultAdapter().closeProfileProxy(1, mBluetoothHeadset);
        mBluetoothHeadset = null;
      }
      AudioPlayerProxy.removeListener(instance);
      if (instance != null)
        instance = null;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static boolean BluetoothManagerInit()
  {
    int i = 1;
    monitorenter;
    try
    {
      m_isStartSCOinProgress = false;
      mAppCloseType = CloseType.BT_BUTTON;
      mScoDisconnectingByAudioFocusLoss = false;
      if (mScoWorkerTask == null)
      {
        mScoWorkerTask = new ScoWorkerTask();
        mScoWorkerTask.start();
        while (true)
        {
          Handler localHandler = mScoWorkerTask.mHandler;
          if (localHandler != null)
            break;
          try
          {
            Thread.sleep(0L);
          }
          catch (Exception localException)
          {
          }
        }
      }
      if (btStateBroadcastReceiver == null)
      {
        btStateBroadcastReceiver = new BTStateBroadcastReceiver(null);
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("com.vlingo.client.app.action.APPLICATION_STATE_CHANGED");
        localIntentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        localIntentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        localIntentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        localIntentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        localIntentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
        localIntentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        localIntentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
        localIntentFilter.addAction("com.vlingo.client.app.action.AUDIO_FOCUS_CHANGED");
        ApplicationAdapter.getInstance().getApplicationContext().registerReceiver(btStateBroadcastReceiver, localIntentFilter);
        mProfileListener = new BluetoothProfile.ServiceListener()
        {
          public void onServiceConnected(int paramInt, BluetoothProfile paramBluetoothProfile)
          {
            if (paramInt == 1)
            {
              BluetoothManager.access$102((BluetoothHeadset)paramBluetoothProfile);
              BluetoothManager.access$202(BluetoothManager.getBTdevice());
              if ((BluetoothManager.mBluetoothDevice == null) || (BluetoothManager.mBluetoothHeadset.getConnectionState(BluetoothManager.mBluetoothDevice) != 2));
            }
          }

          public void onServiceDisconnected(int paramInt)
          {
            if (paramInt == 1)
              BluetoothManager.access$102(null);
          }
        };
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(ApplicationAdapter.getInstance().getApplicationContext(), mProfileListener, 1);
        if (instance == null)
          instance = new BluetoothManager();
        AudioPlayerProxy.addListener(instance);
      }
      while (true)
      {
        return i;
        i = 0;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void addListener(BluetoothManagerListener paramBluetoothManagerListener)
  {
    listeners.add(paramBluetoothManagerListener);
  }

  public static CloseType appCloseType()
  {
    return mAppCloseType;
  }

  public static void appCloseType(CloseType paramCloseType)
  {
    mAppCloseType = paramCloseType;
  }

  private static void cancelScoConnectedTask()
  {
    mOnScoConnectedTask = null;
    mOnScoConnectedTaskTimeout = 0L;
  }

  private static void cancelScoTimeoutTask()
  {
    if (mScoTimeoutTask != null)
    {
      mScoTimeoutTask.cancel();
      mScoTimeoutTask = null;
    }
  }

  public static void closeBtProxy()
  {
    monitorenter;
    try
    {
      if (mBluetoothHeadset != null)
      {
        stopSCO(true);
        BluetoothAdapter.getDefaultAdapter().closeProfileProxy(1, mBluetoothHeadset);
        mBluetoothHeadset = null;
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void considerRightBeforeForeground(boolean paramBoolean)
  {
    mRightBeforeForeground = paramBoolean;
  }

  public static BluetoothDevice getBTdevice()
  {
    BluetoothDevice localBluetoothDevice = null;
    if (mBluetoothHeadset != null)
    {
      List localList = mBluetoothHeadset.getConnectedDevices();
      if (localList.size() > 0)
        localBluetoothDevice = (BluetoothDevice)localList.get(0);
    }
    if (localBluetoothDevice != null);
    return localBluetoothDevice;
  }

  public static void ignoreBtConnectEvent(boolean paramBoolean)
  {
    ignoreBtConnectEvent = paramBoolean;
  }

  private static boolean isBVRASupportDevice()
  {
    int i = 1;
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    try
    {
      int j = Settings.System.getInt(localContext.getContentResolver(), "supported_bvra", 1);
      if (j != 0);
      while (true)
      {
        return i;
        i = 0;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        i = 0;
      }
    }
  }

  private static boolean isBluetoothAdapterOn()
  {
    if (BluetoothAdapter.getDefaultAdapter().getState() == 12);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isBluetoothAudioOn()
  {
    return ((AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio")).isBluetoothScoOn();
  }

  public static boolean isBluetoothAudioSupported()
  {
    boolean bool1 = true;
    boolean bool2 = Settings.getBoolean("listen_over_bluetooth", bool1);
    boolean bool3 = isBVRASupportDevice();
    if ((bool2) && (isHeadsetConnected()) && (bool3));
    while (true)
    {
      return bool1;
      bool1 = false;
    }
  }

  public static boolean isHeadsetConnected()
  {
    monitorenter;
    int i = 1;
    try
    {
      int j = BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1);
      if (j != 2)
        i = 0;
      return i;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      while (true)
      {
        boolean bool = Settings.getBoolean("bluetooth_headset_connected", false);
        i = bool;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static boolean isListenOverBTEnabled()
  {
    return Settings.getBoolean("listen_over_bluetooth", true);
  }

  private static void notifyListeners(boolean paramBoolean)
  {
    Iterator localIterator = listeners.iterator();
    while (localIterator.hasNext())
    {
      BluetoothManagerListener localBluetoothManagerListener = (BluetoothManagerListener)localIterator.next();
      if (paramBoolean)
      {
        localBluetoothManagerListener.onScoConnected();
        continue;
      }
      localBluetoothManagerListener.onScoDisconnected();
    }
  }

  public static void onAppStateChanged(boolean paramBoolean)
  {
    monitorenter;
    if (paramBoolean);
    try
    {
      ignoreBtConnectEvent(false);
      if (BluetoothAdapter.getDefaultAdapter().getState() == 12)
        startSCO();
      while (true)
      {
        return;
        stopSCO(false);
        ignoreBtConnectEvent(true);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void onHeadsetStateChanged(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      updateHeadsetStateSetting(paramBoolean);
      if (paramBoolean)
      {
        openBtProxy();
        if ((!ApplicationAdapter.getInstance().getVlingoApp().isAppInForeground()) || (Settings.getBoolean("launch_car_on_bt_connect", false)))
          ApplicationAdapter.getInstance().getVlingoApp().startMainActivity();
      }
      while (true)
      {
        return;
        closeBtProxy();
        if (!ApplicationAdapter.getInstance().getVlingoApp().isAppInForeground())
          continue;
        stopSCO(false);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void onListenOverBTSettingChanged(boolean paramBoolean)
  {
    monitorenter;
    if (paramBoolean);
    try
    {
      startSCO();
      while (true)
      {
        return;
        stopSCO(false);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void onScoStateChanged(boolean paramBoolean, int paramInt)
  {
    monitorenter;
    try
    {
      if ((!mRightBeforeForeground) && (!ApplicationAdapter.getInstance().getVlingoApp().isAppInForeground()))
      {
        mScoDisconnectingByAudioFocusLoss = false;
        if (!paramBoolean)
          notifyListeners(false);
      }
      while (true)
      {
        return;
        if (!paramBoolean)
          break;
        startSCOConnectedTask();
      }
    }
    finally
    {
      monitorexit;
    }
    int i;
    if (11 == paramInt)
    {
      i = 0;
      BluetoothDevice localBluetoothDevice = mBluetoothDevice;
      if (localBluetoothDevice != null)
        break label221;
    }
    while (true)
    {
      try
      {
        int k = BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1);
        j = k;
        if ((j != 2) && (j != 1))
          continue;
        i = 1;
        if (i == 0)
          continue;
        if (mScoTimeoutTask == null)
          continue;
        cancelScoTimeoutTask();
        mScoTimeoutTask = new ScoTimeoutTask(true, false);
        TimerSingleton.getTimer().schedule(mScoTimeoutTask, 2000L);
        mScoDisconnectingByAudioFocusLoss = false;
        break;
        stopSCO(true);
        notifyListeners(false);
        continue;
        try
        {
          Thread.sleep(500L);
          notifyListeners(false);
          if ((!isListenOverBTEnabled()) || (!isHeadsetConnected()) || (mAppCloseType != CloseType.BT_BUTTON) || (mScoDisconnectingByAudioFocusLoss))
            continue;
        }
        catch (Exception localException)
        {
          Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException));
          continue;
        }
      }
      catch (NoSuchMethodError localNoSuchMethodError)
      {
        int j = 1;
        continue;
      }
      label221: i = 1;
    }
  }

  public static void openBtProxy()
  {
    monitorenter;
    try
    {
      if (mBluetoothHeadset == null)
      {
        BluetoothManagerInit();
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(ApplicationAdapter.getInstance().getApplicationContext(), mProfileListener, 1);
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void removeListener(BluetoothManagerListener paramBluetoothManagerListener)
  {
    listeners.remove(paramBluetoothManagerListener);
  }

  public static void resumeListenOverBTSCO()
  {
    if ((isBluetoothAdapterOn()) && (isListenOverBTEnabled()) && (!isBluetoothAudioOn()) && (!isHeadsetConnected()));
  }

  public static void runTaskOnBluetoothAudioOn(Runnable paramRunnable, long paramLong)
  {
    monitorenter;
    try
    {
      mOnScoConnectedTask = paramRunnable;
      mOnScoConnectedTaskTimeout = paramLong + System.currentTimeMillis();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private static void startSCO()
  {
    monitorenter;
    try
    {
      startSCO(true);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private static void startSCO(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      boolean bool = isBVRASupportDevice();
      if (!bool);
      while (true)
      {
        return;
        if ((!isListenOverBTEnabled()) || (!isBluetoothAdapterOn()) || (m_isStartSCOinProgress) || (ignoreBtConnectEvent))
          continue;
        Bundle localBundle = new Bundle();
        localBundle.putBoolean("sco_retry_flag", paramBoolean);
        if ((mScoWorkerTask == null) || (mScoWorkerTask.mHandler == null))
          continue;
        Message localMessage = mScoWorkerTask.mHandler.obtainMessage(0);
        localMessage.setData(localBundle);
        mScoWorkerTask.mHandler.sendMessage(localMessage);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void startSCOConnectedTask()
  {
    cancelScoTimeoutTask();
    if (mOnScoConnectedTask != null)
      ActivityUtil.scheduleOnMainThread(mOnScoConnectedTask, 200L);
    cancelScoConnectedTask();
    notifyListeners(true);
  }

  public static void startScoOnStartRecognition()
  {
    monitorenter;
    try
    {
      ignoreBtConnectEvent(false);
      startSCO(false);
      ignoreBtConnectEvent(true);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private static void stopSCO(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      boolean bool = isListenOverBTEnabled();
      if ((!bool) && (!paramBoolean));
      while (true)
      {
        return;
        if ((!isBVRASupportDevice()) || (!isBluetoothAudioOn()))
          continue;
        cancelScoTimeoutTask();
        if ((mBluetoothHeadset == null) || (mBluetoothDevice == null))
          continue;
        mBluetoothHeadset.stopVoiceRecognition(mBluetoothDevice);
        m_isStartSCOinProgress = false;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void stopScoOnIdle()
  {
    monitorenter;
    try
    {
      mScoDisconnectingByAudioFocusLoss = true;
      stopSCO(true);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void suspendListenOverBTSCO()
  {
    if ((isBluetoothAdapterOn()) && (isListenOverBTEnabled()) && (isBluetoothAudioOn()));
  }

  public static void updateHeadsetStateSetting(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      Settings.setBoolean("bluetooth_headset_connected", paramBoolean);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
  {
    if (DialogFlow.getInstance().isIdle())
      suspendListenOverBTSCO();
  }

  public void onRequestDidPlay(AudioRequest paramAudioRequest)
  {
    if (DialogFlow.getInstance().isIdle())
      suspendListenOverBTSCO();
  }

  public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
  {
    if (DialogFlow.getInstance().isIdle())
      suspendListenOverBTSCO();
  }

  public void onRequestWillPlay(AudioRequest paramAudioRequest)
  {
    resumeListenOverBTSCO();
    if (isBluetoothAudioOn())
      paramAudioRequest.setStream(6);
    while (true)
    {
      return;
      paramAudioRequest.setStream(3);
    }
  }

  private static class BTStateBroadcastReceiver extends BroadcastReceiver
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      int i = 1;
      String str;
      int n;
      if (paramIntent != null)
      {
        str = paramIntent.getAction();
        if (!"com.vlingo.client.app.action.APPLICATION_STATE_CHANGED".equals(str))
          break label66;
        n = paramIntent.getIntExtra("com.vlingo.client.app.extra.STATE", -1);
        if (n != 0)
          break label42;
        BluetoothManager.onAppStateChanged(false);
      }
      while (true)
      {
        return;
        label42: if ((n == i) && (BluetoothManager.isBluetoothAudioOn()) && (BluetoothManager.mOnScoConnectedTask != null))
        {
          BluetoothManager.startSCOConnectedTask();
          continue;
          label66: if ("android.intent.action.LOCALE_CHANGED".equals(str))
          {
            ClientSuppliedValues.updateCurrentLocale(paramContext.getResources());
            continue;
          }
          if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(str))
          {
            paramIntent.getIntExtra("android.bluetooth.adapter.extra.STATE", -2147483648);
            continue;
          }
          if ("android.bluetooth.device.action.ACL_CONNECTED".equals(str))
          {
            ((BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
            BluetoothManager.onHeadsetStateChanged(i);
            continue;
          }
          if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(str))
          {
            ((BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
            BluetoothManager.onHeadsetStateChanged(false);
            continue;
          }
          if ("android.intent.action.MEDIA_BUTTON".equals(str))
            continue;
          if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(str))
          {
            if (Build.VERSION.SDK_INT < 14)
              continue;
            int m = paramIntent.getIntExtra("android.bluetooth.profile.extra.STATE", 2);
            if ((m != 2) && (m != 0))
              continue;
            if (m == 2);
            while (true)
            {
              BluetoothManager.onHeadsetStateChanged(i);
              break;
              i = 0;
            }
          }
          if ("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED".equals(str))
          {
            BluetoothManager.access$902(false);
            if (Build.VERSION.SDK_INT < 14)
              continue;
            int j = paramIntent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
            int k = paramIntent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", 10);
            if ((j != 12) && (j != 10))
              continue;
            if (j == 12);
            while (true)
            {
              BluetoothManager.onScoStateChanged(i, k);
              break;
              i = 0;
            }
          }
          if (!"com.vlingo.client.app.action.AUDIO_FOCUS_CHANGED".equals(str))
            continue;
          switch (paramIntent.getIntExtra("com.vlingo.client.app.extra.FOCUS_CHANGE", -1))
          {
          default:
            break;
          case -3:
          case -2:
          case -1:
            if (!BluetoothManager.isBluetoothAudioOn())
              continue;
            BluetoothManager.access$1202(i);
            BluetoothManager.onAppStateChanged(false);
            continue;
          }
        }
      }
    }
  }

  public static enum CloseType
  {
    static
    {
      CloseType[] arrayOfCloseType = new CloseType[3];
      arrayOfCloseType[0] = BT_BUTTON;
      arrayOfCloseType[1] = CALL;
      arrayOfCloseType[2] = NORMAL;
      $VALUES = arrayOfCloseType;
    }
  }

  private static class ScoTimeoutTask extends TimerTask
  {
    private boolean mPassFlag;
    private boolean mRetry;

    public ScoTimeoutTask(boolean paramBoolean1, boolean paramBoolean2)
    {
      this.mRetry = paramBoolean1;
      this.mPassFlag = paramBoolean2;
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: ldc 6
      //   2: monitorenter
      //   3: invokestatic 26	com/vlingo/core/internal/bluetooth/BluetoothManager:access$300	()Ljava/util/TimerTask;
      //   6: ifnonnull +9 -> 15
      //   9: ldc 6
      //   11: monitorexit
      //   12: goto +65 -> 77
      //   15: iconst_0
      //   16: invokestatic 30	com/vlingo/core/internal/bluetooth/BluetoothManager:access$400	(Z)V
      //   19: aload_0
      //   20: getfield 17	com/vlingo/core/internal/bluetooth/BluetoothManager$ScoTimeoutTask:mRetry	Z
      //   23: istore_2
      //   24: iload_2
      //   25: ifeq +20 -> 45
      //   28: ldc2_w 31
      //   31: invokestatic 38	java/lang/Thread:sleep	(J)V
      //   34: aload_0
      //   35: getfield 19	com/vlingo/core/internal/bluetooth/BluetoothManager$ScoTimeoutTask:mPassFlag	Z
      //   38: ifeq +19 -> 57
      //   41: iconst_0
      //   42: invokestatic 41	com/vlingo/core/internal/bluetooth/BluetoothManager:access$500	(Z)V
      //   45: ldc 6
      //   47: monitorexit
      //   48: goto +29 -> 77
      //   51: astore_1
      //   52: ldc 6
      //   54: monitorexit
      //   55: aload_1
      //   56: athrow
      //   57: invokestatic 44	com/vlingo/core/internal/bluetooth/BluetoothManager:access$600	()V
      //   60: goto -15 -> 45
      //   63: astore_3
      //   64: ldc 46
      //   66: aload_3
      //   67: invokestatic 52	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
      //   70: invokestatic 56	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   73: pop
      //   74: goto -29 -> 45
      //   77: return
      //
      // Exception table:
      //   from	to	target	type
      //   3	24	51	finally
      //   28	45	51	finally
      //   45	55	51	finally
      //   57	60	51	finally
      //   64	74	51	finally
      //   28	45	63	java/lang/Exception
      //   57	60	63	java/lang/Exception
    }
  }

  private static class ScoWorkerTask extends HandlerThread
  {
    public static final int MSG_START_SCO;
    public Handler mHandler;

    public ScoWorkerTask()
    {
      super();
    }

    protected void finalize()
      throws Throwable
    {
      super.finalize();
      quit();
    }

    public void run()
    {
      try
      {
        Looper.prepare();
        this.mHandler = new BluetoothManager.ScoWorkerTask.1(this);
        Looper.loop();
        return;
      }
      catch (Exception localException)
      {
        while (true)
          Log.e("VLG_EXCEPTION", Log.getStackTraceString(localException));
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.bluetooth.BluetoothManager
 * JD-Core Version:    0.6.0
 */