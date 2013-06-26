package com.samsung.wfd;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.sec.enterprise.EnterpriseDeviceManager;
import android.sec.enterprise.RestrictionPolicy;
import android.util.Log;
import com.android.internal.util.AsyncChannel;
import java.util.HashMap;

public class WfdManager
{
  public static final String ACTION_LAUNCH_WFD_PICKER_DLG = "com.samsung.wfd.LAUNCH_WFD_PICKER_DLG";
  public static final String ACTION_LAUNCH_WFD_POPUP = "com.samsung.wfd.LAUNCH_WFD_POPUP";
  public static final String ACTION_LAUNCH_WFD_UPDATE = "com.samsung.wfd.LAUNCH_WFD_UPDATE";
  public static final String ACTION_PICK_WFD_NETWORK = "com.samsung.wfd.PICK_WFD_NETWORK";
  public static final String ACTION_RESULT_WFD_UPDATE = "com.samsung.wfd.RESULT_WFD_UPDATE";
  public static final String ACTION_SECURE_END = "android.intent.action.SECURE_END";
  public static final String ACTION_SECURE_END_DONE = "android.intent.action.SECURE_END_DONE";
  public static final String ACTION_SECURE_START = "android.intent.action.SECURE_START";
  public static final String ACTION_SECURE_START_DONE = "android.intent.action.SECURE_START_DONE";
  public static final String ACTION_WIFI_DISPLAY_BITRATE = "android.intent.action.WIFI_DISPLAY_BITRATE";
  public static final String ACTION_WIFI_DISPLAY_BUFFERING_DO = "android.intent.action.WIFI_DISPLAY_BUFFERING_DO";
  public static final String ACTION_WIFI_DISPLAY_BUFFERING_DONE = "android.intent.action.WIFI_DISPLAY_BUFFERING_DONE";
  public static final String ACTION_WIFI_DISPLAY_RESOLUTION = "android.intent.action.WIFI_DISPLAY_RESOLUTION";
  public static final String ACTION_WIFI_DISPLAY_TCP_PLAYBACK_CONTROL = "android.intent.action.WIFI_DISPLAY_TCP_PLAYBACK_CONTROL";
  public static final String ACTION_WIFI_DISPLAY_TCP_TRANSPORT = "android.intent.action.WIFI_DISPLAY_TCP_TRANSPORT";
  public static final String ACTION_WIFI_DISPLAY_UDP_TRANSPORT = "android.intent.action.WIFI_DISPLAY_UDP_TRANSPORT";
  public static final String ACTION_WIFI_DISPLAY_VIDEO = "android.intent.action.WIFI_DISPLAY_VIDEO";
  private static final int BASE = 139264;
  public static final int BUSY = 2;
  public static final int DISABLE_WFD = 139368;
  public static final int DISABLE_WFD_FAILED = 139369;
  public static final int DISABLE_WFD_SUCCEEDED = 139370;
  public static final int ENABLE_WFD = 139365;
  public static final int ENABLE_WFD_DIALOG = 139375;
  public static final int ENABLE_WFD_FAILED = 139366;
  public static final int ENABLE_WFD_SUCCEEDED = 139367;
  public static final int ERROR = 0;
  public static final String EXTRA_CAUSE_INFO = "cause";
  public static final String EXTRA_CURRENT_RESOLUTION_INFO = "curRes";
  public static final String EXTRA_CUSTOM_SETTING = "wfdCustomSetting";
  public static final String EXTRA_LINK_CAPABILITIES = "linkCapabilities";
  public static final String EXTRA_LINK_PROPERTIES = "linkProperties";
  public static final String EXTRA_NETWORK_INFO = "networkInfo";
  public static final String EXTRA_RESOLUTION_INFO = "resBitMask";
  public static final String EXTRA_RESULT_RET = "result";
  public static final String EXTRA_SAMPLE_COUNT = "count";
  public static final String EXTRA_STATE_INFO = "state";
  public static final String EXTRA_UPDATE_URL = "url";
  public static final String EXTRA_WFD_DEVICE = "wfdPeerDeviceDescriptor";
  public static final String EXTRA_WFD_STATE = "wfd_state";
  public static final String P2P_CONNECTION_ESTABLISHED = "com.samsung.wfd.P2P_CONNECTION_ESTABLISHED";
  public static final String P2P_CONNECTION_TERMINATED = "com.samsung.wfd.P2P_CONNECTION_TERMINATED";
  public static final int PAUSE_RTSP = 139374;
  public static final int POPUP_CAUSE_ALERT_RESTART = 139379;
  public static final int POPUP_CAUSE_BLUETOOTH_OR_EARPHONE_ON = 139388;
  public static final int POPUP_CAUSE_CONNECTION_DISCONNECT = 139387;
  public static final int POPUP_CAUSE_DISCONNECT_NO_BUSY = 139390;
  public static final int POPUP_CAUSE_DONGLE_UPDATE = 139384;
  public static final int POPUP_CAUSE_DONGLE_UPDATE_RESULT = 139385;
  public static final int POPUP_CAUSE_HDMI_BUSY = 139380;
  public static final int POPUP_CAUSE_HOTSPOT_BUSY = 139381;
  public static final int POPUP_CAUSE_ONGOING_RESCAN = 139383;
  public static final int POPUP_CAUSE_P2P_BUSY = 139378;
  public static final int POPUP_CAUSE_SBEAM_BUSY = 139382;
  public static final int POPUP_CAUSE_SPLIT_WINDOW = 139391;
  public static final int POPUP_CAUSE_TERMINATE = 139376;
  public static final int POPUP_CAUSE_WEAK_NETWORK = 139386;
  public static final int RESUME_RTSP = 139373;
  public static final int START_RTSP = 139371;
  public static final int STOP_RTSP = 139372;
  private static final String TAG = "WfdManager";
  public static final int TEARDOWN = 139394;
  public static final int UPDATE_RESOURCES = 139392;
  public static final String WFD_SERVICE_STARTED = "com.samsung.wfd.WFD_SERVICE_STARTED";
  public static final String WFD_SESSION_ENABLED = "com.samsung.wfd.WFD_SESSION_ENABLED";
  public static final String WFD_SESSION_ESTABLISHED = "com.samsung.wfd.WFD_SESSION_ESTABLISHED";
  public static final String WFD_SESSION_ESTABLISHING = "com.samsung.wfd.WFD_SESSION_ESTABLISHING";
  public static final String WFD_SESSION_PAUSE = "com.samsung.wfd.WFD_SESSION_PAUSE";
  public static final String WFD_SESSION_RESUME = "com.samsung.wfd.WFD_SESSION_RESUME";
  public static final String WFD_SESSION_START = "com.samsung.wfd.WFD_SESSION_START";
  public static final String WFD_SESSION_STOP = "com.samsung.wfd.WFD_SESSION_STOP";
  public static final String WFD_SESSION_STOPPED = "com.samsung.wfd.WFD_SESSION_STOPPED";
  public static final String WFD_SESSION_TEARDOWN = "com.samsung.wfd.WFD_SESSION_TEARDOWN";
  public static final String WFD_SESSION_TERMINATED = "com.samsung.wfd.WFD_SESSION_TERMINATED";
  public static final String WFD_STATE_CHANGED_ACTION = "com.samsung.wfd.STATE_CHANGED";
  public static final int WFD_STATE_DISABLED = 1;
  public static final int WFD_STATE_ENABLED = 2;
  public static final int WFD_STATE_P2P_CONNECTED = 3;
  public static final int WFD_STATE_SESSION_ESTABLISHED = 5;
  public static final int WFD_STATE_SESSION_STARTING = 4;
  public static final int WFD_STATE_SESSION_TEARDOWNING = 0;
  public static final int WFD_STATE_UNKNOWN = -1;
  public static final String WFD_TEARDOWN_FOR_AUDIO_OUT = "android.intent.action.WFD_TEARDOWN_FOR_AUDIO_OUT";
  public static final int WFD_UNSUPPORTED = 1;
  public static final String WIFIDISPLAY_CONTROL_FROM_BROKER = "android.intent.action.WIFIDISPLAY_CONTROL_FROM_BROKER";
  public static final String WIFIDISPLAY_CONTROL_FROM_SERVICE = "android.intent.action.WIFIDISPLAY_CONTROL_FROM_SERVICE";
  public static final String WIFIDISPLAY_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
  public static final String WIFIDISPLAY_NOTI_CONNECTION_MODE = "android.intent.action.WIFIDISPLAY_NOTI_CONNECTION_MODE";
  public static final String WIFIDISPLAY_NOTI_ERROR_FROM_NATIVE = "android.intent.action.WIFI_DISPLAY_ERROR_FROM_NATIVE";
  public static final String WIFIDISPLAY_NOTI_HDCP_INFO_FROM_NATIVE = "android.intent.action.WIFIDISPLAY_NOTI_HDCP_INFO_FROM_NATIVE";
  public static final String WIFIDISPLAY_PARAM_CHANGED_NOTIFICATION = "android.intent.action.WIFI_DISPLAY_PARAM_CHANGED";
  public static final String WIFIDISPLAY_RESOLUTION_FROM_APP = "android.intent.action.WIFI_DISPLAY_REQ";
  public static final String WIFIDISPLAY_RESOLUTION_FROM_NATIVE = "android.intent.action.WIFI_DISPLAY_RES_FROM_NATIVE";
  public static final String WIFIDISPLAY_SESSION_INFO = "android.intent.action.WIFI_DISPLAY_RES";
  public static final String WIFIDISPLAY_SESSION_STATE = "android.intent.action.WIFI_DISPLAY";
  public static final String WIFIDISPLAY_UPDATE_INPUT_FROM_APP = "android.intent.action.WIFI_DISPLAY_UPDATE_INPUT_FROM_APP";
  public static final String WIFIDISPLAY_UPDATE_URL_FROM_NATIVE = "android.intent.action.WIFI_DISPLAY_URL_FROM_NATIVE";
  public static final String WIFIDISPLAY_WEAK_NETWORK = "android.intent.action.WIFIDISPLAY_WEAK_NETWORK";
  public static final int r_1024_768 = 64;
  public static final int r_1152_864 = 128;
  public static final int r_1280_1024 = 4096;
  public static final int r_1280_720 = 8;
  public static final int r_1280_768 = 256;
  public static final int r_1280_800 = 512;
  public static final int r_1360_768 = 1024;
  public static final int r_1366_768 = 2048;
  public static final int r_1400_1050 = 8192;
  public static final int r_1440_900 = 16384;
  public static final int r_1600_1200 = 65536;
  public static final int r_1600_900 = 32768;
  public static final int r_1680_1024 = 131072;
  public static final int r_1680_1050 = 262144;
  public static final int r_1920_1080 = 16;
  public static final int r_1920_1200 = 524288;
  public static final int r_640_360 = 8388608;
  public static final int r_640_480 = 1;
  public static final int r_720_480 = 2;
  public static final int r_720_576 = 4;
  public static final int r_800_480 = 1048576;
  public static final int r_800_600 = 32;
  public static final int r_848_480 = 33554432;
  public static final int r_854_480 = 2097152;
  public static final int r_864_480 = 4194304;
  public static final int r_960_540 = 16777216;
  public static final int r_unknown;
  IWfdManager mService;

  public WfdManager(IWfdManager paramIWfdManager)
  {
    this.mService = paramIWfdManager;
  }

  public boolean WFDGetStatus()
  {
    if (getWfdState() == 5);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean WFDGetSubtitleStatus()
  {
    try
    {
      boolean bool2 = this.mService.WFDGetSubtitleStatus();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean WFDGetSuspendStatus()
  {
    try
    {
      boolean bool2 = this.mService.WFDGetSuspendStatus();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean WFDPostSubtitle(String paramString, int paramInt)
  {
    try
    {
      boolean bool2 = this.mService.WFDPostSubtitle(paramString, paramInt);
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean WFDPostSuspend(String paramString)
  {
    try
    {
      boolean bool2 = this.mService.WFDPostSuspend(paramString);
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean WFDSetSubtitleStatus(boolean paramBoolean)
  {
    try
    {
      boolean bool2 = this.mService.WFDSetSubtitleStatus(paramBoolean);
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean WFDSetSuspendStatus(boolean paramBoolean)
  {
    try
    {
      boolean bool2 = this.mService.WFDSetSuspendStatus(paramBoolean);
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public void disableWfd(Channel paramChannel)
  {
    if (paramChannel == null);
    while (true)
    {
      return;
      paramChannel.mAsyncChannel.sendMessage(139368);
    }
  }

  public void enableWfd(Channel paramChannel)
  {
    if (paramChannel == null);
    while (true)
    {
      return;
      RestrictionPolicy localRestrictionPolicy = EnterpriseDeviceManager.getInstance().getRestrictionPolicy();
      if ((localRestrictionPolicy != null) && (!localRestrictionPolicy.isWifiDirectAllowed(true)))
      {
        paramChannel.mAsyncChannel.sendMessage(139368);
        continue;
      }
      paramChannel.mAsyncChannel.sendMessage(139365);
    }
  }

  public String getDisplayDeviceAddress()
  {
    return null;
  }

  public String getDisplayDeviceName()
  {
    return null;
  }

  public int getDisplayDeviceSecure()
  {
    return -1;
  }

  public Messenger getMessenger()
  {
    try
    {
      Messenger localMessenger2 = this.mService.getMessenger();
      localMessenger1 = localMessenger2;
      return localMessenger1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        Messenger localMessenger1 = null;
    }
  }

  public WfdInfo getWfdInfo()
  {
    try
    {
      WfdInfo localWfdInfo2 = this.mService.getWfdInfo();
      localWfdInfo1 = localWfdInfo2;
      return localWfdInfo1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        WfdInfo localWfdInfo1 = null;
    }
  }

  public int getWfdSinkResolution()
  {
    try
    {
      int j = this.mService.getWfdSinkResolution();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        int i = 0;
    }
  }

  public int getWfdState()
  {
    try
    {
      int j = this.mService.getWfdState();
      i = j;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        int i = 0;
    }
  }

  public void handleDown(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    try
    {
      this.mService.handleDown(paramInt, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3);
      label14: return;
    }
    catch (RemoteException localRemoteException)
    {
      break label14;
    }
  }

  public void handleMove(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    try
    {
      this.mService.handleMove(paramInt, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3);
      label14: return;
    }
    catch (RemoteException localRemoteException)
    {
      break label14;
    }
  }

  public void handleUp(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    try
    {
      this.mService.handleUp(paramInt, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3);
      label14: return;
    }
    catch (RemoteException localRemoteException)
    {
      break label14;
    }
  }

  public Channel initialize(Context paramContext, Looper paramLooper, ChannelListener paramChannelListener)
  {
    Messenger localMessenger = getMessenger();
    Channel localChannel;
    if (localMessenger == null)
      localChannel = null;
    while (true)
    {
      return localChannel;
      localChannel = new Channel(paramLooper, paramChannelListener);
      if (localChannel.mAsyncChannel.connectSync(paramContext, localChannel.mHandler, localMessenger) == 0)
        continue;
      localChannel = null;
    }
  }

  public boolean isActiveUIBC()
  {
    try
    {
      boolean bool2 = this.mService.isActiveUIBC();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean isWfdEnabledPlayer()
  {
    try
    {
      boolean bool2 = this.mService.isWfdEnabledPlayer();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public void keyDown(int paramInt1, int paramInt2)
  {
    try
    {
      this.mService.keyDown(paramInt1, paramInt2);
      label11: return;
    }
    catch (RemoteException localRemoteException)
    {
      break label11;
    }
  }

  public void keyUp(int paramInt1, int paramInt2)
  {
    try
    {
      this.mService.keyUp(paramInt1, paramInt2);
      label11: return;
    }
    catch (RemoteException localRemoteException)
    {
      break label11;
    }
  }

  public boolean notifyContentFinish()
  {
    try
    {
      boolean bool2 = this.mService.notifyContentFinish();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public void pauseRTSP(Channel paramChannel)
  {
    if (paramChannel == null);
    while (true)
    {
      return;
      paramChannel.mAsyncChannel.sendMessage(139374);
    }
  }

  public void resumeRTSP(Channel paramChannel)
  {
    if (paramChannel == null);
    while (true)
    {
      return;
      paramChannel.mAsyncChannel.sendMessage(139373);
    }
  }

  public boolean sendToWfdStartRTSP()
  {
    try
    {
      boolean bool2 = this.mService.sendToWfdStartRTSP();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean setWfdEnabled(int paramInt)
  {
    try
    {
      boolean bool2 = this.mService.setWfdEnabled(paramInt);
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean setWfdEnabledDialog(boolean paramBoolean)
  {
    try
    {
      boolean bool2 = this.mService.setWfdEnabledDialog(paramBoolean);
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean setWfdEnabledPlayer(boolean paramBoolean)
  {
    try
    {
      boolean bool2 = this.mService.setWfdEnabledPlayer(paramBoolean);
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public void setWfdModeAlways()
  {
    try
    {
      this.mService.setWfdModeAlways();
      label9: return;
    }
    catch (RemoteException localRemoteException)
    {
      break label9;
    }
  }

  public boolean setWfdRestart()
  {
    try
    {
      boolean bool2 = this.mService.setWfdRestart();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean setWfdRestartOption(int paramInt)
  {
    try
    {
      boolean bool2 = this.mService.setWfdRestartOption(paramInt);
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean setWfdTerminate()
  {
    try
    {
      boolean bool2 = this.mService.setWfdTerminate();
      bool1 = bool2;
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public void startRTSP(Channel paramChannel)
  {
    if (paramChannel == null);
    while (true)
    {
      return;
      paramChannel.mAsyncChannel.sendMessage(139371);
    }
  }

  public void stopRTSP(Channel paramChannel)
  {
    if (paramChannel == null);
    while (true)
    {
      return;
      paramChannel.mAsyncChannel.sendMessage(139372);
    }
  }

  public static abstract interface ActionListener
  {
    public abstract void onFailure(int paramInt);

    public abstract void onSuccess();
  }

  public static class Channel
  {
    AsyncChannel mAsyncChannel = new AsyncChannel();
    private WfdManager.ChannelListener mChannelListener;
    WfdHandler mHandler;
    private int mListenerKey = 0;
    private HashMap<Integer, Object> mListenerMap = new HashMap();
    private Object mListenerMapLock = new Object();

    Channel(Looper paramLooper, WfdManager.ChannelListener paramChannelListener)
    {
      this.mHandler = new WfdHandler(paramLooper);
      this.mChannelListener = paramChannelListener;
    }

    Object getListener(int paramInt)
    {
      synchronized (this.mListenerMapLock)
      {
        Object localObject3 = this.mListenerMap.remove(Integer.valueOf(paramInt));
        return localObject3;
      }
    }

    int putListener(Object paramObject)
    {
      int i;
      if (paramObject == null)
        i = 0;
      while (true)
      {
        return i;
        synchronized (this.mListenerMapLock)
        {
          i = this.mListenerKey;
          this.mListenerKey = (i + 1);
          this.mListenerMap.put(Integer.valueOf(i), paramObject);
        }
      }
    }

    class WfdHandler extends Handler
    {
      WfdHandler(Looper arg2)
      {
        super();
      }

      public void handleMessage(Message paramMessage)
      {
        WfdManager.Channel.this.getListener(paramMessage.arg2);
        switch (paramMessage.what)
        {
        default:
          Log.d("WfdManager", "Ignored " + paramMessage);
        case 69636:
        }
        while (true)
        {
          return;
          if (WfdManager.Channel.this.mChannelListener != null)
          {
            WfdManager.Channel.this.mChannelListener.onChannelDisconnected();
            WfdManager.Channel.access$002(WfdManager.Channel.this, null);
            continue;
          }
        }
      }
    }
  }

  public static abstract interface ChannelListener
  {
    public abstract void onChannelDisconnected();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WfdManager
 * JD-Core Version:    0.6.0
 */