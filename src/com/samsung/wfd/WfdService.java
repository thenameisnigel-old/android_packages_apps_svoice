package com.samsung.wfd;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pWfdInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.INetworkManagementService;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings.Secure;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.util.AsyncChannel;
import com.android.internal.util.State;
import com.android.internal.util.StateMachine;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class WfdService extends IWfdManager.Stub
{
  private static final int BASE = 143360;
  public static final int CONN_MODE_ALWAYS = 1;
  public static final int CONN_MODE_ONETIME = 2;
  private static final boolean DBG = true;
  private static final String TAG = "WfdService";
  private final int WFD_CHECK_TURN_OFF_INTERVAL = 300000;
  private final int WFD_CHECK_TURN_OFF_MSG = 700;
  private int mAudioCnt = 0;
  private AudioManager mAudioManager = null;
  private WifiP2pManager.Channel mChannel = null;
  private int mConnectedDeviceHDCP = 0;
  private String mConnectedDeviceMacAddr = null;
  private String mConnectedDeviceName = null;
  private int mConnectionMode = 1;
  private Context mContext = null;
  private String mCurrentDimm = null;
  private int mCurrentResln = 0;
  private boolean mDongleUpdateResult = false;
  private String mDongleVer = null;
  private int mFrequencyAp = 0;
  private int mFrequencyWfd = 0;
  WifiP2pGroup mGroup = null;
  private boolean mHDMIConnected = false;
  private boolean mHDMITrigger = false;
  private String mInterface = null;
  private boolean mIsShowingNotification = false;
  private boolean mKeepP2pConnection = false;
  private NetworkInfo mNetworkInfo = null;
  private Notification mNotification = null;
  INetworkManagementService mNwService = null;
  private String mRemoteIP = null;
  private AsyncChannel mReplyChannel = new AsyncChannel();
  private String mRequestSessionControl = null;
  private int mResolutionBitMask = 0;
  private boolean mScreenStatus = false;
  private int mSourceReslnBitMask = 25;
  private String mUpdateURL = null;
  private boolean mUseRTSPService = false;
  private boolean mWaitHotspotDone = false;
  private WfdDevice mWfdDevice = null;
  private boolean mWfdEnabledFromPicker = false;
  private WfdInfo mWfdInfo = null;
  private boolean mWfdOffTimer = false;
  private boolean mWfdRestartOngoing = false;
  private boolean mWfdRestartTrigger = false;
  private ServiceConnection mWfdSourceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      WfdService.this.mWfdSourceService = IWfdSourceService.Stub.asInterface(paramIBinder);
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      WfdService.this.mWfdSourceService = null;
    }
  };
  IWfdSourceService mWfdSourceService = null;
  private final AtomicInteger mWfdState = new AtomicInteger(1);
  private WfdStateMachine mWfdStateMachine = null;
  private final boolean mWfdSupported;
  private AsyncChannel mWifiChannel = null;
  private WifiManager mWifiManager = null;
  private boolean mWifiP2pConnected = false;
  private boolean mWifiP2pEnabled = false;
  private WifiP2pInfo mWifiP2pInfo = null;
  private WifiP2pManager mWifiP2pManager = null;
  private boolean mWifiP2pTrigger = false;

  public WfdService(Context paramContext)
  {
    this.mContext = paramContext;
    this.mInterface = SystemProperties.get("wifi.interface", "wlan0");
    SystemProperties.set("wlan.wfd.status", "disconnected");
    this.mWfdSupported = true;
    this.mWfdEnabledFromPicker = false;
    this.mWifiP2pConnected = false;
    this.mWifiP2pEnabled = false;
    this.mWifiP2pTrigger = false;
    this.mWfdRestartTrigger = false;
    this.mWaitHotspotDone = false;
    this.mWfdRestartOngoing = false;
    this.mFrequencyAp = 0;
    this.mFrequencyWfd = 0;
    this.mDongleUpdateResult = false;
    this.mScreenStatus = false;
    this.mWfdOffTimer = false;
    this.mWfdStateMachine = new WfdStateMachine("WfdService", this.mWfdSupported);
    this.mWfdStateMachine.start();
    this.mWfdInfo = new WfdInfo();
    this.mWifiP2pManager = ((WifiP2pManager)(WifiP2pManager)this.mContext.getSystemService("wifip2p"));
    if (this.mWifiP2pManager != null)
    {
      this.mChannel = this.mWifiP2pManager.initialize(this.mContext, this.mContext.getMainLooper(), null);
      if (this.mChannel == null)
      {
        logd("Failed to set up connection with wifi p2p service");
        this.mWifiP2pManager = null;
      }
    }
    while (true)
    {
      this.mAudioManager = ((AudioManager)this.mContext.getSystemService("audio"));
      if (this.mWifiP2pManager != null)
        logd("mAudioManager is null !");
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
      localIntentFilter.addAction("android.net.wifi.STATE_CHANGE");
      localIntentFilter.addAction("com.samsung.wfd.P2P_CONNECTION_ESTABLISHED");
      localIntentFilter.addAction("com.samsung.wfd.P2P_CONNECTION_TERMINATED");
      localIntentFilter.addAction("com.samsung.wfd.WFD_SESSION_ESTABLISHED");
      localIntentFilter.addAction("com.samsung.wfd.WFD_SESSION_TERMINATED");
      localIntentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
      localIntentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
      localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_REQ");
      localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_RES_FROM_NATIVE");
      localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_PARAM_CHANGED");
      localIntentFilter.addAction("android.intent.action.HDMI_PLUGGED");
      localIntentFilter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
      localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_URL_FROM_NATIVE");
      localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_ERROR_FROM_NATIVE");
      localIntentFilter.addAction("android.intent.action.WIFIDISPLAY_WEAK_NETWORK");
      localIntentFilter.addAction("com.samsung.wfd.RESULT_WFD_UPDATE");
      localIntentFilter.addAction("android.intent.action.WIFIDISPLAY_CONTROL_FROM_BROKER");
      localIntentFilter.addAction("android.intent.action.WFD_TEARDOWN_FOR_AUDIO_OUT");
      localIntentFilter.addAction("android.intent.action.SCREEN_ON");
      localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
      localIntentFilter.addAction("android.intent.action.WIFIDISPLAY_NOTI_HDCP_INFO_FROM_NATIVE");
      localIntentFilter.addAction("com.samsung.wfd.WFD_SESSION_ENABLED");
      localIntentFilter.addAction("com.samsung.wfd.WFD_SERVICE_STARTED");
      localIntentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
      localIntentFilter.addAction("com.android.server.enterprise.WFD_DISABLE");
      this.mContext.registerReceiver(new WifiStateReceiver(null), localIntentFilter);
      this.mUseRTSPService = false;
      return;
      logd("mWifiP2pManager is null !");
    }
  }

  private void WfdStartHDCPSuspend()
  {
    boolean bool = WFDGetSuspendStatus();
    logd("WFDGetSuspendStatus >> " + bool);
    WFDSetSuspendStatus(true);
    WFDPostSuspend((String)Resources.getSystem().getText(17040948));
  }

  private void WfdStopHDCPSuspend()
  {
    if (WFDGetSuspendStatus())
    {
      WFDSetSuspendStatus(false);
      logd("done WfdStopHDCPSuspend");
    }
  }

  private void broadcastDongleVerToFota()
  {
    logd("broadcastDongleVerToFota");
    Intent localIntent = new Intent("com.samsung.wfd.START_WFD");
    if (this.mDongleVer != null)
      localIntent.putExtra("version", this.mDongleVer);
    localIntent.addFlags(32);
    this.mContext.sendBroadcast(localIntent);
  }

  private void broadcastIntent2HDMI(int paramInt)
  {
    logd("broadcastIntent2HDMI: " + paramInt + " " + this.mHDMIConnected + " " + this.mHDMITrigger);
    Intent localIntent = new Intent("android.intent.action.HDMI_PLUGGED");
    localIntent.addFlags(134217728);
    if (this.mHDMIConnected)
      if (paramInt == 0)
      {
        localIntent.putExtra("state", false);
        this.mHDMITrigger = true;
        this.mContext.sendBroadcast(localIntent);
        logd("send broadcastIntent2HDMI:" + paramInt);
      }
    while (true)
    {
      return;
      logd("no send broadcastIntent2HDMI:" + paramInt);
      continue;
      if (this.mHDMITrigger)
      {
        if (paramInt == 1)
        {
          localIntent.putExtra("state", true);
          this.mHDMITrigger = false;
          break;
        }
        logd("no send broadcastIntent2HDMI:" + paramInt);
        continue;
      }
      logd("HDMI is not triggered! no send broadcastIntent2HDMI:" + paramInt);
    }
  }

  private void broadcastWfdSessionInfo()
  {
    logd("broadcastWfdSessionInfo");
    Intent localIntent = new Intent("android.intent.action.WIFI_DISPLAY_RES");
    localIntent.addFlags(134217728);
    localIntent.putExtra("state", 1);
    localIntent.putExtra("resBitMask", this.mResolutionBitMask);
    localIntent.putExtra("curRes", this.mCurrentResln);
    logd("s:1 ResBit:" + this.mResolutionBitMask + " ResIn:" + this.mCurrentResln);
    this.mContext.sendStickyBroadcast(localIntent);
  }

  private void broadcastWfdSessionPause()
  {
    logd("broadcastWfdSessionPause");
    Intent localIntent = new Intent("com.samsung.wfd.WFD_SESSION_PAUSE");
    this.mContext.sendBroadcast(localIntent);
  }

  private void broadcastWfdSessionResume()
  {
    logd("broadcastWfdSessionResume");
    Intent localIntent = new Intent("com.samsung.wfd.WFD_SESSION_RESUME");
    this.mContext.sendBroadcast(localIntent);
  }

  private boolean broadcastWfdSessionStart()
  {
    int i = 1;
    if (this.mWfdDevice == null)
      i = 0;
    while (true)
    {
      return i;
      if (this.mWfdDevice.wfdInfo == null)
        this.mWfdDevice.wfdInfo = new WfdInfo(i);
      logd("broadcastWfdSessionStart");
      Intent localIntent = new Intent("com.samsung.wfd.WFD_SESSION_START");
      localIntent.putExtra("wfdPeerDeviceDescriptor", this.mWfdDevice);
      if (this.mKeepP2pConnection)
        localIntent.putExtra("wfdCustomSetting", i);
      this.mContext.sendBroadcast(localIntent);
    }
  }

  private void broadcastWfdSessionState(int paramInt)
  {
    logd("broadcastWfdSessionState: " + paramInt);
    Intent localIntent1 = new Intent("android.intent.action.WIFI_DISPLAY");
    localIntent1.addFlags(134217728);
    localIntent1.putExtra("state", paramInt);
    localIntent1.putExtra("resBitMask", this.mResolutionBitMask);
    localIntent1.putExtra("curRes", this.mCurrentResln);
    Intent localIntent2;
    if (this.mAudioCnt != 0)
    {
      localIntent1.putExtra("count", this.mAudioCnt);
      logd("s:" + paramInt + " ResBit:" + this.mResolutionBitMask + " ResIn:" + this.mCurrentResln);
      this.mContext.sendStickyBroadcast(localIntent1);
      logd("broadcastWfdSessionInfo << to AllShare!");
      localIntent2 = new Intent("com.sec.android.allshare.intent.action.CAST_GETSTATE");
      localIntent2.addFlags(134217728);
      if (paramInt != 1)
        break label284;
      localIntent2.putExtra("com.sec.android.allshare.intent.extra.CAST_STATE", true);
      if (this.mRemoteIP != null)
        localIntent2.putExtra("com.sec.android.allshare.intent.extra.CAST_IPADDR", this.mRemoteIP);
    }
    while (true)
    {
      this.mContext.sendStickyBroadcast(localIntent2);
      if (paramInt == 0)
      {
        Intent localIntent3 = new Intent("android.intent.action.WIFIDISPLAY_NOTI_CONNECTION_MODE");
        localIntent3.addFlags(134217728);
        localIntent3.putExtra("CONNECTION_MODE", 0);
        this.mContext.sendStickyBroadcast(localIntent3);
      }
      return;
      localIntent1.putExtra("count", 1920);
      break;
      label284: localIntent2.putExtra("com.sec.android.allshare.intent.extra.CAST_STATE", false);
    }
  }

  private void broadcastWfdSessionStop()
  {
    logd("broadcastWfdSessionStop");
    Intent localIntent = new Intent("com.samsung.wfd.WFD_SESSION_STOP");
    this.mContext.sendBroadcast(localIntent);
  }

  private void broadcastWfdSessionTeardown()
  {
    logd("broadcastWfdSessionTeardown");
    Intent localIntent = new Intent("com.samsung.wfd.WFD_SESSION_TEARDOWN");
    this.mContext.sendBroadcast(localIntent);
  }

  private void broadcastWifiDisplayVideoEnabled(int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.WIFI_DISPLAY_VIDEO");
    localIntent.putExtra("state", paramInt);
    logd("Broadcasting WFD video intent: " + localIntent);
    this.mContext.sendBroadcast(localIntent);
  }

  private void enforceAccessPermission()
  {
    this.mContext.enforceCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE", "WfdService");
  }

  private void enforceChangePermission()
  {
    this.mContext.enforceCallingOrSelfPermission("android.permission.CHANGE_WIFI_STATE", "WfdService");
  }

  private String getDefaultDeviceName()
  {
    String str = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
    return "Android_" + str.substring(0, 4);
  }

  private boolean isHotspotOn()
  {
    if (this.mWifiManager == null)
      this.mWifiManager = ((WifiManager)this.mContext.getSystemService("wifi"));
    int i;
    if (this.mWifiManager == null)
    {
      logd("isHotspotOn >> No wifiManager.");
      i = 0;
    }
    while (true)
    {
      return i;
      switch (this.mWifiManager.getWifiApState())
      {
      default:
        logd("Hotspot is Off");
        i = 0;
        break;
      case 12:
      case 13:
        logd("Hotspot is ON");
        i = 1;
      }
    }
  }

  private boolean isSBeamOn()
  {
    return false;
  }

  private boolean isSplitWindow()
  {
    return false;
  }

  private void sendToWfdPickerControlInfo(int paramInt)
  {
    logd("sendToWfdPickerControlInfo << " + paramInt);
    Intent localIntent = new Intent("android.intent.action.WIFIDISPLAY_CONTROL_FROM_SERVICE");
    localIntent.addFlags(134217728);
    localIntent.putExtra("cause", paramInt);
    this.mContext.sendStickyBroadcast(localIntent);
  }

  private void sendWfdBrokerStartForPopup(int paramInt)
  {
    Intent localIntent = new Intent("com.samsung.wfd.LAUNCH_WFD_POPUP");
    localIntent.addFlags(276824064);
    switch (paramInt)
    {
    case 139377:
    case 139383:
    case 139385:
    case 139386:
    case 139388:
    case 139389:
    case 139390:
    default:
      loge("sendWfdStartForPopup:" + paramInt);
      return;
    case 139376:
      logd("sendWfdStartForPopup << POPUP_CAUSE_TERMINATE");
    case 139378:
    case 139387:
    case 139379:
    case 139380:
    case 139381:
    case 139382:
    case 139384:
    case 139391:
    }
    while (true)
    {
      localIntent.putExtra("cause", paramInt);
      this.mContext.startActivity(localIntent);
      break;
      logd("sendWfdStartForPopup << POPUP_CAUSE_P2P_BUSY");
      continue;
      logd("sendWfdStartForPopup << POPUP_CAUSE_CONNECTION_DISCONNECT");
      if (this.mWfdRestartTrigger)
      {
        logd("do not show disconnect popup when restart!");
        break;
      }
      setWfdTerminate();
      continue;
      logd("sendWfdStartForPopup << POPUP_CAUSE_RESTART");
      continue;
      logd("sendWfdStartForPopup << POPUP_CAUSE_HDMI_BUSY");
      continue;
      logd("sendWfdStartForPopup << POPUP_CAUSE_HOTSPOT_BUSY");
      continue;
      logd("sendWfdStartForPopup << POPUP_CAUSE_SBEAM_BUSY");
      continue;
      logd("sendWfdStartForPopup << POPUP_CAUSE_DONGLE_UPDATE");
      localIntent.putExtra("url", this.mUpdateURL);
      this.mUpdateURL = null;
      continue;
      logd("sendWfdStartForPopup << POPUP_CAUSE_SPLIT_WINDOW");
    }
  }

  private void sendWfdStartRTSP()
  {
    Log.e("WfdService", "sendWfdStartRTSP");
    if (this.mGroup != null)
    {
      this.mFrequencyWfd = this.mGroup.getFrequency();
      logd("wifi P2P_CONNECTION_ESTABLISHED >> Wfd frequency:" + this.mFrequencyWfd);
      if (this.mWfdState.get() != 2)
        break label107;
      setWfdState(3);
      if (!this.mUseRTSPService)
      {
        logd("send wfd start msg");
        this.mWfdStateMachine.sendMessage(139371);
      }
    }
    while (true)
    {
      return;
      Log.e("WfdService", "mGroup == null");
      break;
      label107: logd("Recvd Wifi P2P connected intent in wrong state:" + this.mWfdState.get());
    }
  }

  private boolean setHotspotOff()
  {
    if (this.mWifiManager == null)
      this.mWifiManager = ((WifiManager)this.mContext.getSystemService("wifi"));
    if (this.mWifiManager == null)
      logd("setHotspotOff >> No wifiManager.");
    for (int i = 0; ; i = 1)
    {
      return i;
      this.mWifiManager.setWifiApEnabled(null, false);
      this.mWaitHotspotDone = true;
    }
  }

  private boolean setSBeamOff()
  {
    return true;
  }

  private void setWfdState(int paramInt)
  {
    this.mWfdState.set(paramInt);
    logd("setWfdState: " + paramInt);
    if ((!this.mWifiP2pEnabled) && (this.mWfdState.get() == 2))
      logd("setWfdState just returned");
    while (true)
    {
      return;
      if ((this.mWfdState.get() == 1) && (!isWiFiConnected(this.mContext)) && (!this.mKeepP2pConnection))
      {
        logd("!mKeepP2pConnection / disableWiFiP2P");
        this.mWfdStateMachine.disableWiFiP2P();
      }
      Intent localIntent = new Intent("com.samsung.wfd.STATE_CHANGED");
      localIntent.addFlags(134217728);
      localIntent.putExtra("wfd_state", paramInt);
      this.mContext.sendStickyBroadcast(localIntent);
    }
  }

  private void startWfdRoutine()
  {
    if ((this.mWifiP2pManager == null) || (this.mChannel == null))
      logd("startWfdRoutine failed");
    while (true)
    {
      return;
      this.mWfdInfo = new WfdInfo();
      WifiP2pWfdInfo localWifiP2pWfdInfo = new WifiP2pWfdInfo();
      localWifiP2pWfdInfo.setWfdEnabled(true);
      localWifiP2pWfdInfo.setDeviceType(0);
      localWifiP2pWfdInfo.setSessionAvailable(true);
      localWifiP2pWfdInfo.setControlPort(7236);
      localWifiP2pWfdInfo.setMaxThroughput(40);
      this.mWifiP2pManager.setWFDInfo(this.mChannel, localWifiP2pWfdInfo, new WifiP2pManager.ActionListener()
      {
        public void onFailure(int paramInt)
        {
          Log.d("WfdService", "Failed to set WFD info with reason " + paramInt + ".");
        }

        public void onSuccess()
        {
          Log.d("WfdService", "Successfully set WFD info.");
        }
      });
    }
  }

  public boolean WFDGetSubtitleStatus()
  {
    int i = 0;
    logd("WFDGetSubtitleStatus :");
    if (this.mWfdSourceService != null);
    try
    {
      boolean bool = this.mWfdSourceService.WFDGetSubtitleStatus();
      i = bool;
      label28: return i;
    }
    catch (RemoteException localRemoteException)
    {
      break label28;
    }
  }

  public boolean WFDGetSuspendStatus()
  {
    int i = 0;
    if (this.mWfdSourceService != null);
    try
    {
      boolean bool = this.mWfdSourceService.WFDGetSuspendStatus();
      i = bool;
      label21: return i;
    }
    catch (RemoteException localRemoteException)
    {
      break label21;
    }
  }

  public boolean WFDPostSubtitle(String paramString, int paramInt)
  {
    if (this.mWfdSourceService != null);
    try
    {
      this.mWfdSourceService.WFDPostSubtitle(paramString, paramInt);
      i = 1;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        int i = 0;
    }
  }

  public boolean WFDPostSuspend(String paramString)
  {
    if (this.mWfdSourceService != null);
    try
    {
      this.mWfdSourceService.WFDPostSuspend(paramString);
      i = 1;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        int i = 0;
    }
  }

  public boolean WFDSetSubtitleStatus(boolean paramBoolean)
  {
    int i = 0;
    logd("WFDSetSubtitleStatus : " + paramBoolean);
    if (this.mWfdSourceService != null);
    try
    {
      this.mWfdSourceService.WFDSetSubtitleStatus(paramBoolean);
      i = 1;
      label46: return i;
    }
    catch (RemoteException localRemoteException)
    {
      break label46;
    }
  }

  public boolean WFDSetSuspendStatus(boolean paramBoolean)
  {
    int i = 0;
    logd("WFDSetSuspendStatus : " + paramBoolean);
    if (this.mWfdSourceService != null);
    try
    {
      this.mWfdSourceService.WFDSetSuspendStatus(paramBoolean);
      i = 1;
      label46: return i;
    }
    catch (RemoteException localRemoteException)
    {
      break label46;
    }
  }

  protected void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    if (this.mContext.checkCallingOrSelfPermission("android.permission.DUMP") != 0)
      paramPrintWriter.println("Permission Denial: can't dump WfdService from from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid());
  }

  public String getDisplayDeviceAddress()
  {
    Log.d("WfdService", "getDisplayDeviceAddress: " + this.mConnectedDeviceMacAddr);
    return this.mConnectedDeviceMacAddr;
  }

  public String getDisplayDeviceName()
  {
    Log.d("WfdService", "getDisplayDeviceName: " + this.mConnectedDeviceName);
    return this.mConnectedDeviceName;
  }

  public int getDisplayDeviceSecure()
  {
    Log.d("WfdService", "getDisplayDeviceSecure: " + this.mConnectedDeviceHDCP);
    return this.mConnectedDeviceHDCP;
  }

  public Messenger getMessenger()
  {
    enforceAccessPermission();
    enforceChangePermission();
    return new Messenger(this.mWfdStateMachine.getHandler());
  }

  public WfdInfo getWfdInfo()
  {
    enforceAccessPermission();
    if (this.mWfdInfo != null)
      this.mWfdInfo.coupledDeviceAddress = this.mConnectedDeviceMacAddr;
    while (true)
    {
      return this.mWfdInfo;
      logd("getWfdInfo is null");
    }
  }

  public int getWfdSinkResolution()
  {
    return this.mResolutionBitMask;
  }

  public int getWfdState()
  {
    return this.mWfdState.get();
  }

  public void handleDown(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    WFDNative.handleDown(paramInt, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3);
  }

  public void handleMove(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    WFDNative.handleMove(paramInt, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3);
  }

  public void handleUp(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    WFDNative.handleUp(paramInt, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3);
  }

  public boolean isActiveUIBC()
  {
    return WFDNative.isActiveUIBC();
  }

  public boolean isSessionEstablished()
  {
    return SystemProperties.get("wlan.wfd.status").equals("connected");
  }

  public boolean isWfdEnabledPlayer()
  {
    logd("current conn_mode is " + this.mConnectionMode);
    if (this.mConnectionMode == 2);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isWiFiConnected(Context paramContext)
  {
    if (paramContext == null)
      logd("isWiFiConnected context is null");
    for (boolean bool = false; ; bool = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).isConnectedOrConnecting())
      return bool;
  }

  public void keyDown(int paramInt1, int paramInt2)
  {
    WFDNative.keyDown(paramInt1, paramInt2);
  }

  public void keyUp(int paramInt1, int paramInt2)
  {
    WFDNative.keyUp(paramInt1, paramInt2);
  }

  void logd(String paramString)
  {
    Log.d("WfdService", paramString);
  }

  void loge(String paramString)
  {
    Log.e("WfdService", paramString);
  }

  public boolean notifyContentFinish()
  {
    if (this.mConnectionMode == 2)
      logd("connectionMode == ONETIME so finish");
    for (boolean bool = setWfdTerminate(); ; bool = false)
      return bool;
  }

  public boolean sendToWfdStartRTSP()
  {
    Log.e("WfdService", "sendToWfdStartRTSP");
    startWfdRoutine();
    setWfdState(2);
    sendWfdStartRTSP();
    return true;
  }

  public boolean setWfdEnabled(int paramInt)
  {
    boolean bool = true;
    enforceChangePermission();
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      this.mWfdStateMachine.sendMessage(139375);
      while (true)
      {
        return bool;
        logd("enforce to disconnect currrent p2p...");
        this.mWifiP2pManager.removeGroup(this.mChannel, new WifiP2pManager.ActionListener()
        {
          public void onFailure(int paramInt)
          {
            WfdService.this.logd(" remove group fail " + paramInt);
          }

          public void onSuccess()
          {
            WfdService.this.logd(" remove group success");
            WfdService.access$3902(WfdService.this, true);
            WfdService.this.mWfdStateMachine.sendMessage(139375);
          }
        });
        continue;
        logd("enforce to disconnect currrent HDMI...");
        continue;
        logd("enforce to disconnect currrent hotspot...");
        setHotspotOff();
        bool = false;
        continue;
        logd("enforce to disconnect currrent sbeam...");
        setSBeamOff();
      }
      logd("WiFi Direct is already connected! Just enable WFD only ");
      this.mKeepP2pConnection = bool;
    }
  }

  public boolean setWfdEnabledDialog(boolean paramBoolean)
  {
    int i = 0;
    enforceChangePermission();
    if (paramBoolean)
      if (this.mHDMIConnected)
        sendWfdBrokerStartForPopup(139380);
    while (true)
    {
      return i;
      if (isHotspotOn())
      {
        sendWfdBrokerStartForPopup(139381);
        continue;
      }
      if (isSBeamOn())
      {
        sendWfdBrokerStartForPopup(139382);
        continue;
      }
      if ((this.mWifiP2pConnected) && (!this.mUseRTSPService))
      {
        sendWfdBrokerStartForPopup(139378);
        continue;
      }
      if (isSplitWindow())
      {
        sendWfdBrokerStartForPopup(139391);
        continue;
      }
      if (this.mUseRTSPService)
        logd("already Wfd running...");
      while (true)
      {
        i = 1;
        break;
        this.mWfdStateMachine.sendMessage(139375);
      }
      sendWfdBrokerStartForPopup(139376);
    }
  }

  public boolean setWfdEnabledPlayer(boolean paramBoolean)
  {
    boolean bool1 = true;
    logd("setWfdEnabledPlayer is called with arg onetime(" + paramBoolean + ") mConnectionMode = " + this.mConnectionMode);
    if (this.mConnectionMode == 2);
    boolean bool3;
    for (boolean bool2 = bool1; ; bool2 = false)
    {
      bool3 = setWfdEnabledDialog(bool1);
      if ((bool2) && (paramBoolean == bool1))
        this.mConnectionMode = 2;
      if (getWfdState() < 3)
        break;
      return bool1;
    }
    if (paramBoolean)
    {
      this.mConnectionMode = 2;
      logd("conn_mode_onetime");
    }
    while (true)
    {
      bool1 = bool3;
      break;
      this.mConnectionMode = bool1;
      logd("conn_mode_always");
    }
  }

  public void setWfdModeAlways()
  {
    logd("conn_mode will be setted CONN_MODE_ALWAYS");
    this.mConnectionMode = 1;
  }

  public boolean setWfdRestart()
  {
    this.mWfdRestartTrigger = true;
    setWfdTerminate();
    return true;
  }

  public boolean setWfdRestartOption(int paramInt)
  {
    this.mWfdRestartTrigger = true;
    switch (paramInt)
    {
    default:
    case 20:
    }
    while (true)
    {
      setWfdTerminate();
      return true;
      this.mWfdRestartOngoing = true;
    }
  }

  public boolean setWfdTerminate()
  {
    logd("setWfdTerminate called with mUseRTSPService " + this.mUseRTSPService);
    enforceChangePermission();
    if (this.mUseRTSPService)
      this.mWfdStateMachine.sendMessage(139394);
    while (true)
    {
      return true;
      this.mWfdStateMachine.sendMessage(139368);
    }
  }

  private class WfdStateMachine extends StateMachine
  {
    private DefaultState mDefaultState = new DefaultState();
    private InactiveState mInactiveState = new InactiveState();
    private WfdNotSupportedState mWfdNotSupportedState = new WfdNotSupportedState();

    WfdStateMachine(String paramBoolean, boolean arg3)
    {
      super();
      addState(this.mDefaultState);
      addState(this.mInactiveState, this.mDefaultState);
      int i;
      if (i != 0)
        setInitialState(this.mInactiveState);
      while (true)
      {
        return;
        setInitialState(this.mWfdNotSupportedState);
      }
    }

    private void clearNotification()
    {
      NotificationManager localNotificationManager = (NotificationManager)WfdService.this.mContext.getSystemService("notification");
      if ((localNotificationManager != null) && (WfdService.this.mNotification != null))
      {
        localNotificationManager.cancel(WfdService.this.mNotification.icon);
        WfdService.access$6002(WfdService.this, false);
        WfdService.access$5902(WfdService.this, null);
      }
    }

    private void disableWiFiP2P()
    {
      WfdService.this.logd("disabling WiFi P2P");
      if (WfdService.this.mWifiP2pManager == null)
        WfdService.this.logd(" mWifiP2pManager is null!! check!!");
      if (isP2pConnected())
        WfdService.this.mWifiP2pManager.removeGroup(WfdService.this.mChannel, new WifiP2pManager.ActionListener()
        {
          public void onFailure(int paramInt)
          {
            WfdService.this.logd(" remove group fail " + paramInt);
            if (!WfdService.this.isWiFiConnected(WfdService.this.mContext))
              WfdService.this.mWifiP2pManager.disableP2p(WfdService.this.mChannel);
          }

          public void onSuccess()
          {
            WfdService.this.logd(" remove group success");
            if (WfdService.this.mWfdRestartTrigger)
            {
              WfdService.this.logd(" only remove group && restart! do not disable @ JB!!!");
              WfdService.WfdStateMachine.this.sendWfdPickerStartBroadCast();
            }
            while (true)
            {
              return;
              if (!WfdService.this.isWiFiConnected(WfdService.this.mContext))
              {
                WfdService.this.mWifiP2pManager.disableP2p(WfdService.this.mChannel);
                continue;
              }
            }
          }
        });
      while (true)
      {
        return;
        if (!WfdService.this.isWiFiConnected(WfdService.this.mContext))
        {
          WfdService.this.mWifiP2pManager.disableP2p(WfdService.this.mChannel);
          continue;
        }
      }
    }

    private void enableWiFiP2P()
    {
      WfdService.this.logd("enabling WiFi P2P");
      if (WfdService.this.mWifiP2pManager != null)
        WfdService.this.mWifiP2pManager.enableP2p(WfdService.this.mChannel);
    }

    private boolean isP2pConnected()
    {
      int i = 0;
      try
      {
        if (((ConnectivityManager)WfdService.this.mContext.getSystemService("connectivity")).getNetworkInfo(13).getDetailedState() == NetworkInfo.DetailedState.CONNECTED)
        {
          WfdService.this.logd("isP2pConnected >> true!");
          i = 1;
        }
        return i;
      }
      catch (NullPointerException localNullPointerException)
      {
        while (true)
          WfdService.this.loge("isP2pConnected - NullPointerException");
      }
    }

    private Message obtainMessage(Message paramMessage)
    {
      Message localMessage = Message.obtain();
      localMessage.arg2 = paramMessage.arg2;
      return localMessage;
    }

    private void replyToMessage(Message paramMessage, int paramInt)
    {
      if (paramMessage.replyTo == null);
      while (true)
      {
        return;
        Message localMessage = obtainMessage(paramMessage);
        localMessage.what = paramInt;
        WfdService.this.mReplyChannel.replyToMessage(paramMessage, localMessage);
      }
    }

    private void replyToMessage(Message paramMessage, int paramInt1, int paramInt2)
    {
      if (paramMessage.replyTo == null);
      while (true)
      {
        return;
        Message localMessage = obtainMessage(paramMessage);
        localMessage.what = paramInt1;
        localMessage.arg1 = paramInt2;
        WfdService.this.mReplyChannel.replyToMessage(paramMessage, localMessage);
      }
    }

    private void replyToMessage(Message paramMessage, int paramInt, Object paramObject)
    {
      if (paramMessage.replyTo == null);
      while (true)
      {
        return;
        Message localMessage = obtainMessage(paramMessage);
        localMessage.what = paramInt;
        localMessage.obj = paramObject;
        WfdService.this.mReplyChannel.replyToMessage(paramMessage, localMessage);
      }
    }

    private void showNotification()
    {
      NotificationManager localNotificationManager = (NotificationManager)WfdService.this.mContext.getSystemService("notification");
      if ((localNotificationManager == null) || (WfdService.this.mNotification != null));
      while (true)
      {
        return;
        Intent localIntent = new Intent("com.samsung.wfd.PICK_WFD_NETWORK");
        localIntent.setFlags(1073741824);
        localIntent.addFlags(276824064);
        localIntent.putExtra("cause", 139383);
        PendingIntent localPendingIntent = PendingIntent.getActivity(WfdService.this.mContext, 0, localIntent, 0);
        Resources localResources = Resources.getSystem();
        CharSequence localCharSequence1 = localResources.getText(17040942);
        CharSequence localCharSequence2 = localResources.getText(17040862);
        WfdService.access$5902(WfdService.this, new Notification());
        WfdService.this.mNotification.when = 0L;
        WfdService.this.mNotification.icon = 17303205;
        Notification localNotification = WfdService.this.mNotification;
        localNotification.defaults = (0xFFFFFFFE & localNotification.defaults);
        WfdService.this.mNotification.flags = 2;
        WfdService.this.mNotification.tickerText = localCharSequence1;
        WfdService.this.mNotification.setLatestEventInfo(WfdService.this.mContext, localCharSequence1, localCharSequence2, localPendingIntent);
        WfdService.access$6002(WfdService.this, true);
        localNotificationManager.notify(WfdService.this.mNotification.icon, WfdService.this.mNotification);
      }
    }

    private void showTerminatedByAudioNotification()
    {
      WfdService.this.loge("showTerminatedByAudioNotification");
      CharSequence localCharSequence = Resources.getSystem().getText(17040947);
      Toast.makeText(WfdService.this.mContext, localCharSequence, 1).show();
      Toast.makeText(WfdService.this.mContext, localCharSequence, 0).show();
    }

    private void showWeakNetworkNotification()
    {
      CharSequence localCharSequence = Resources.getSystem().getText(17040944);
      Toast.makeText(WfdService.this.mContext, localCharSequence, 1).show();
    }

    private void terminateWfdRoutine(int paramInt)
    {
      WifiP2pWfdInfo localWifiP2pWfdInfo = new WifiP2pWfdInfo();
      localWifiP2pWfdInfo.setWfdEnabled(false);
      WfdService.this.mWifiP2pManager.setWFDInfo(WfdService.this.mChannel, localWifiP2pWfdInfo, new WifiP2pManager.ActionListener()
      {
        public void onFailure(int paramInt)
        {
          Log.d("WfdService", "Failed to set WFD info with reason " + paramInt + ".");
        }

        public void onSuccess()
        {
          Log.d("WfdService", "Successfully set WFD info.");
        }
      });
      clearNotification();
      WfdService.access$5502(WfdService.this, false);
      if (WfdService.this.mWfdState.get() != 0)
      {
        SystemProperties.set("wlan.wfd.status", "disconnected");
        WfdService.this.broadcastWfdSessionState(0);
        WfdService.this.broadcastWfdSessionTeardown();
        WfdService.this.mWfdState.set(0);
      }
      if (WfdService.this.mKeepP2pConnection)
      {
        WfdService.this.setWfdState(1);
        WfdService.this.broadcastIntent2HDMI(1);
        WfdService.access$2402(WfdService.this, 0);
        WfdService.access$2202(WfdService.this, false);
        WfdService.access$502(WfdService.this, false);
        WfdService.this.logd("terminateWfdRoutine / mKeepP2pConnection");
      }
      while (true)
      {
        return;
        if (!WfdService.this.mWfdRestartTrigger)
          break;
        WfdService.this.logd("[WFD] Let's start Wfd with Last connect!!!");
        WfdService.this.logd("p2p disable by Wfd!!!");
        disableWiFiP2P();
        WfdService.access$3902(WfdService.this, false);
      }
      WfdService.access$3702(WfdService.this, null);
      WfdService.access$5702(WfdService.this, null);
      if (WfdService.this.mWifiP2pTrigger)
      {
        WfdService.this.logd("p2p disable by Wfd!!!");
        disableWiFiP2P();
        WfdService.access$3902(WfdService.this, false);
      }
      while (true)
      {
        WfdService.this.setWfdState(1);
        WfdService.this.broadcastIntent2HDMI(1);
        WfdService.access$2402(WfdService.this, 0);
        WfdService.access$2202(WfdService.this, false);
        WfdService.access$502(WfdService.this, false);
        WfdService.this.logd("[WFD] done!" + paramInt);
        break;
        WfdService.this.logd("now we should turn off wifip2p even though turned on by wifip2pservice!!!");
        disableWiFiP2P();
        WfdService.access$2002(WfdService.this, false);
      }
    }

    private void updateResources()
    {
      WfdService.this.logd("updateResources");
      if ((WfdService.this.mNotification != null) && (WfdService.this.mIsShowingNotification))
      {
        NotificationManager localNotificationManager = (NotificationManager)WfdService.this.mContext.getSystemService("notification");
        Resources localResources = Resources.getSystem();
        CharSequence localCharSequence1 = localResources.getText(17040942);
        CharSequence localCharSequence2 = localResources.getText(17040862);
        WfdService.this.mNotification.tickerText = localCharSequence1;
        WfdService.this.mNotification.setLatestEventInfo(WfdService.this.mContext, localCharSequence1, localCharSequence2, WfdService.this.mNotification.contentIntent);
        localNotificationManager.notify(WfdService.this.mNotification.icon, WfdService.this.mNotification);
      }
    }

    public void sendWfdPickerStartBroadCast()
    {
      WfdService.this.logd("sendWfdPickerStartBroadCast");
      Intent localIntent = new Intent("com.samsung.wfd.LAUNCH_WFD_POPUP");
      localIntent.addFlags(268566528);
      localIntent.putExtra("state", WfdService.this.mWifiP2pTrigger);
      if (WfdService.this.mWfdRestartOngoing)
      {
        localIntent.putExtra("cause", 20);
        WfdService.access$4402(WfdService.this, false);
        WfdService.access$702(WfdService.this, false);
        WfdService.this.logd("putExtra >> cause::restart by ongoing");
      }
      while (true)
      {
        WfdService.this.mContext.startActivity(localIntent);
        return;
        if (!WfdService.this.mWfdRestartTrigger)
          continue;
        localIntent.putExtra("cause", 10);
        WfdService.access$702(WfdService.this, false);
        WfdService.this.logd("putExtra >> cause::restart by AP connected");
      }
    }

    public int syncGetWfdState()
    {
      return WfdService.this.mWfdState.get();
    }

    class DefaultState extends State
    {
      DefaultState()
      {
      }

      public boolean processMessage(Message paramMessage)
      {
        WfdService.this.logd(getName() + paramMessage.toString());
        int i;
        switch (paramMessage.what)
        {
        default:
          WfdService.this.loge("Unhandled message " + paramMessage);
          i = 0;
          return i;
        case 69632:
          if (paramMessage.arg1 != 0)
            break;
          WfdService.this.logd("Full connection with WifiStateMachine established");
          WfdService.access$4002(WfdService.this, (AsyncChannel)paramMessage.obj);
        case 69636:
        case 69633:
        case 139365:
        case 139375:
        case 139386:
        case 139368:
        case 139371:
        case 139372:
        case 139373:
        case 139374:
        case 139392:
        }
        while (true)
        {
          i = 1;
          break;
          WfdService.this.loge("Full connection failure, error = " + paramMessage.arg1);
          WfdService.access$4002(WfdService.this, null);
          continue;
          if (paramMessage.arg1 == 2)
            WfdService.this.loge("Send failed, client connection lost");
          while (true)
          {
            WfdService.access$4002(WfdService.this, null);
            break;
            WfdService.this.loge("Client connection lost with reason: " + paramMessage.arg1);
          }
          new AsyncChannel().connect(WfdService.this.mContext, WfdService.WfdStateMachine.this.getHandler(), paramMessage.replyTo);
          continue;
          WfdService.WfdStateMachine.this.replyToMessage(paramMessage, 139366, 2);
          continue;
          WfdService.WfdStateMachine.this.replyToMessage(paramMessage, 139366, 2);
          continue;
          WfdService.WfdStateMachine.this.replyToMessage(paramMessage, 139366, 2);
          continue;
          WfdService.WfdStateMachine.this.replyToMessage(paramMessage, 139369, 2);
          continue;
          WfdService.WfdStateMachine.this.replyToMessage(paramMessage, 139369, 2);
          continue;
          WfdService.WfdStateMachine.this.replyToMessage(paramMessage, 139369, 2);
          continue;
          WfdService.WfdStateMachine.this.replyToMessage(paramMessage, 139369, 2);
          continue;
          WfdService.WfdStateMachine.this.replyToMessage(paramMessage, 139369, 2);
          continue;
          WfdService.WfdStateMachine.this.replyToMessage(paramMessage, 139369, 2);
        }
      }
    }

    class InactiveState extends State
    {
      InactiveState()
      {
      }

      public void enter()
      {
        WfdService.this.logd(getName() + "Enter InactiveState");
      }

      public boolean processMessage(Message paramMessage)
      {
        int i = 0;
        WfdService.this.logd(getName() + paramMessage.toString());
        Intent localIntent = new Intent("android.net.wifi.wfd.WFDSourceService");
        switch (paramMessage.what)
        {
        default:
          return i;
        case 139375:
          WfdService.this.logd("Enable Wi-Fi Display dialog");
          if (WfdService.this.mWifiP2pEnabled)
          {
            WfdService.this.startWfdRoutine();
            WfdService.this.logd("p2p already enable! so setWfdParam...");
            label189: WfdService.access$3902(WfdService.this, true);
            WfdService.access$502(WfdService.this, true);
            WfdService.this.setWfdState(2);
            if ((!WfdService.this.mWfdRestartTrigger) && (!WfdService.this.mWfdRestartOngoing))
              break;
            WfdService.WfdStateMachine.this.sendWfdPickerStartBroadCast();
          }
        case 139371:
        case 139386:
        case 139388:
        case 139368:
        case 139372:
        case 139373:
        case 139374:
        case 139392:
        case 139394:
        }
        while (true)
        {
          i = 1;
          break;
          WfdService.this.logd("now p2p enable!");
          WfdService.WfdStateMachine.this.enableWiFiP2P();
          break label189;
          WfdService.this.logd("recv wfd start msg...START_RTSP");
          WfdService.WfdStateMachine.this.showNotification();
          if (WfdService.this.mFrequencyWfd != 0)
          {
            localIntent.putExtra("freq", WfdService.this.mFrequencyWfd);
            WfdService.this.logd("putExtra frequency:" + WfdService.this.mFrequencyWfd);
          }
          boolean bool = WfdService.this.mContext.bindService(localIntent, WfdService.this.mWfdSourceConnection, 1);
          WfdService.this.logd("bindService WFDSourceService,  isConnected : " + bool);
          if (!bool)
            continue;
          WfdService.access$2102(WfdService.this, true);
          continue;
          WfdService.this.logd("recv weak network notification");
          WfdService.WfdStateMachine.this.showWeakNetworkNotification();
          continue;
          WfdService.this.logd("recv bluetooth or earphone on notification");
          WfdService.WfdStateMachine.this.showTerminatedByAudioNotification();
          continue;
          WfdService.this.logd("Disble Wi-Fi Display");
          WfdService.this.WfdStopHDCPSuspend();
          if (WfdService.this.mUseRTSPService)
            try
            {
              WfdService.this.logd("try to stopService...");
              WfdService.this.logd("mContext:" + WfdService.this.mContext + " intent:" + localIntent);
              WfdService.this.mContext.unbindService(WfdService.this.mWfdSourceConnection);
              WfdService.WfdStateMachine.this.terminateWfdRoutine(3);
              localWfdService2 = WfdService.this;
              WfdService.access$2102(localWfdService2, false);
              continue;
            }
            catch (Exception localException2)
            {
              while (true)
              {
                WfdService.this.loge("catch Exception @ stopService");
                localException2.printStackTrace();
                WfdService.WfdStateMachine.this.terminateWfdRoutine(3);
                WfdService localWfdService2 = WfdService.this;
              }
            }
            finally
            {
              WfdService.WfdStateMachine.this.terminateWfdRoutine(3);
              WfdService.access$2102(WfdService.this, false);
            }
          WfdService.WfdStateMachine.this.terminateWfdRoutine(2);
          continue;
          WfdService.this.logd("recv wfd stop msg...STOP_RTSP");
          if (WfdService.this.mUseRTSPService);
          try
          {
            WfdService.this.logd("try to stopService..!");
            WfdService.this.logd("mContext:" + WfdService.this.mContext + " intent:" + localIntent);
            WfdService.this.mContext.unbindService(WfdService.this.mWfdSourceConnection);
            localWfdService1 = WfdService.this;
            WfdService.access$2102(localWfdService1, false);
            WfdService.WfdStateMachine.this.terminateWfdRoutine(1);
            continue;
          }
          catch (Exception localException1)
          {
            while (true)
            {
              WfdService.this.loge("catch Exception @ stopService");
              localException1.printStackTrace();
              WfdService localWfdService1 = WfdService.this;
            }
          }
          finally
          {
            WfdService.access$2102(WfdService.this, false);
          }
          WfdService.this.logd("Resume RTSP session");
          WfdService.this.broadcastWfdSessionState(1);
          WfdService.this.broadcastWfdSessionResume();
          continue;
          WfdService.this.logd("Pause RTSP session");
          WfdService.this.broadcastWfdSessionState(0);
          WfdService.this.broadcastWfdSessionPause();
          continue;
          WfdService.WfdStateMachine.this.updateResources();
          continue;
          WfdService.access$502(WfdService.this, false);
          if (WfdService.this.mWfdState.get() == 0)
            continue;
          SystemProperties.set("wlan.wfd.status", "disconnected");
          WfdService.this.mWfdState.set(0);
          WfdService.this.broadcastWfdSessionState(0);
          WfdService.this.broadcastWfdSessionTeardown();
        }
      }
    }

    class WfdNotSupportedState extends State
    {
      WfdNotSupportedState()
      {
      }

      public boolean processMessage(Message paramMessage)
      {
        WfdService.this.logd("Wi-Fi Display is not supported");
        return false;
      }
    }
  }

  private class WifiStateReceiver extends BroadcastReceiver
  {
    private WifiStateReceiver()
    {
    }

    // ERROR //
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_2
      //   3: invokevirtual 27	android/content/Intent:getAction	()Ljava/lang/String;
      //   6: astore 4
      //   8: aload_0
      //   9: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   12: new 29	java/lang/StringBuilder
      //   15: dup
      //   16: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   19: ldc 32
      //   21: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   24: aload 4
      //   26: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   29: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   32: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   35: aload 4
      //   37: ldc 45
      //   39: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   42: ifeq +190 -> 232
      //   45: aload_2
      //   46: ldc 53
      //   48: iconst_1
      //   49: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   52: tableswitch	default:+24 -> 76, 1:+106->158, 2:+29->81
      //   77: monitorexit
      //   78: goto +2794 -> 2872
      //   81: aload_0
      //   82: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   85: iconst_1
      //   86: invokestatic 61	com/samsung/wfd/WfdService:access$202	(Lcom/samsung/wfd/WfdService;Z)Z
      //   89: pop
      //   90: aload_0
      //   91: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   94: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   97: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   100: iconst_2
      //   101: if_icmpne +20 -> 121
      //   104: aload_0
      //   105: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   108: aload_0
      //   109: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   112: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   115: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   118: invokestatic 75	com/samsung/wfd/WfdService:access$400	(Lcom/samsung/wfd/WfdService;I)V
      //   121: aload_0
      //   122: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   125: invokestatic 79	com/samsung/wfd/WfdService:access$500	(Lcom/samsung/wfd/WfdService;)Z
      //   128: ifeq +18 -> 146
      //   131: aload_0
      //   132: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   135: invokestatic 82	com/samsung/wfd/WfdService:access$600	(Lcom/samsung/wfd/WfdService;)V
      //   138: goto -62 -> 76
      //   141: astore_3
      //   142: aload_0
      //   143: monitorexit
      //   144: aload_3
      //   145: athrow
      //   146: aload_0
      //   147: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   150: ldc 84
      //   152: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   155: goto -79 -> 76
      //   158: aload_0
      //   159: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   162: iconst_0
      //   163: invokestatic 61	com/samsung/wfd/WfdService:access$202	(Lcom/samsung/wfd/WfdService;Z)Z
      //   166: pop
      //   167: aload_0
      //   168: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   171: iconst_0
      //   172: invokestatic 87	com/samsung/wfd/WfdService:access$702	(Lcom/samsung/wfd/WfdService;Z)Z
      //   175: pop
      //   176: aload_0
      //   177: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   180: aconst_null
      //   181: invokestatic 91	com/samsung/wfd/WfdService:access$802	(Lcom/samsung/wfd/WfdService;Ljava/lang/String;)Ljava/lang/String;
      //   184: pop
      //   185: aload_0
      //   186: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   189: ldc 93
      //   191: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   194: aload_0
      //   195: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   198: iconst_0
      //   199: invokestatic 96	com/samsung/wfd/WfdService:access$902	(Lcom/samsung/wfd/WfdService;Z)Z
      //   202: pop
      //   203: aload_0
      //   204: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   207: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   210: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   213: iconst_1
      //   214: if_icmpeq -138 -> 76
      //   217: aload_0
      //   218: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   221: invokestatic 100	com/samsung/wfd/WfdService:access$1000	(Lcom/samsung/wfd/WfdService;)Lcom/samsung/wfd/WfdService$WfdStateMachine;
      //   224: ldc 101
      //   226: invokevirtual 107	com/samsung/wfd/WfdService$WfdStateMachine:sendMessage	(I)V
      //   229: goto -153 -> 76
      //   232: aload 4
      //   234: ldc 109
      //   236: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   239: ifeq +338 -> 577
      //   242: aload_0
      //   243: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   246: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   249: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   252: iconst_4
      //   253: if_icmpne +286 -> 539
      //   256: aload_0
      //   257: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   260: aload_2
      //   261: ldc 111
      //   263: invokevirtual 115	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
      //   266: invokestatic 118	com/samsung/wfd/WfdService:access$1102	(Lcom/samsung/wfd/WfdService;Ljava/lang/String;)Ljava/lang/String;
      //   269: pop
      //   270: aload_0
      //   271: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   274: invokestatic 122	com/samsung/wfd/WfdService:access$1100	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   277: ifnull +35 -> 312
      //   280: aload_0
      //   281: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   284: new 29	java/lang/StringBuilder
      //   287: dup
      //   288: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   291: ldc 124
      //   293: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   296: aload_0
      //   297: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   300: invokestatic 122	com/samsung/wfd/WfdService:access$1100	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   303: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   306: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   309: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   312: aload_0
      //   313: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   316: aload_2
      //   317: ldc 126
      //   319: invokevirtual 115	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
      //   322: invokestatic 129	com/samsung/wfd/WfdService:access$1202	(Lcom/samsung/wfd/WfdService;Ljava/lang/String;)Ljava/lang/String;
      //   325: pop
      //   326: aload_0
      //   327: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   330: invokestatic 132	com/samsung/wfd/WfdService:access$1200	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   333: ifnull +35 -> 368
      //   336: aload_0
      //   337: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   340: new 29	java/lang/StringBuilder
      //   343: dup
      //   344: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   347: ldc 134
      //   349: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   352: aload_0
      //   353: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   356: invokestatic 132	com/samsung/wfd/WfdService:access$1200	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   359: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   362: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   365: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   368: aload_0
      //   369: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   372: aload_2
      //   373: ldc 136
      //   375: iconst_0
      //   376: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   379: invokestatic 140	com/samsung/wfd/WfdService:access$1302	(Lcom/samsung/wfd/WfdService;I)I
      //   382: pop
      //   383: aload_0
      //   384: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   387: invokestatic 144	com/samsung/wfd/WfdService:access$1300	(Lcom/samsung/wfd/WfdService;)I
      //   390: ifeq +35 -> 425
      //   393: aload_0
      //   394: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   397: new 29	java/lang/StringBuilder
      //   400: dup
      //   401: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   404: ldc 146
      //   406: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   409: aload_0
      //   410: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   413: invokestatic 144	com/samsung/wfd/WfdService:access$1300	(Lcom/samsung/wfd/WfdService;)I
      //   416: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   419: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   422: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   425: aload_0
      //   426: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   429: aload_2
      //   430: ldc 151
      //   432: invokevirtual 115	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
      //   435: invokestatic 154	com/samsung/wfd/WfdService:access$1402	(Lcom/samsung/wfd/WfdService;Ljava/lang/String;)Ljava/lang/String;
      //   438: pop
      //   439: aload_0
      //   440: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   443: invokestatic 157	com/samsung/wfd/WfdService:access$1400	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   446: ifnull +35 -> 481
      //   449: aload_0
      //   450: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   453: new 29	java/lang/StringBuilder
      //   456: dup
      //   457: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   460: ldc 159
      //   462: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   465: aload_0
      //   466: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   469: invokestatic 157	com/samsung/wfd/WfdService:access$1400	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   472: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   475: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   478: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   481: aload_0
      //   482: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   485: invokestatic 163	com/samsung/wfd/WfdService:access$1600	(Lcom/samsung/wfd/WfdService;)Landroid/net/wifi/p2p/WifiP2pManager;
      //   488: aload_0
      //   489: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   492: invokestatic 167	com/samsung/wfd/WfdService:access$1500	(Lcom/samsung/wfd/WfdService;)Landroid/net/wifi/p2p/WifiP2pManager$Channel;
      //   495: new 169	com/samsung/wfd/WfdService$WifiStateReceiver$1
      //   498: dup
      //   499: aload_0
      //   500: invokespecial 172	com/samsung/wfd/WfdService$WifiStateReceiver$1:<init>	(Lcom/samsung/wfd/WfdService$WifiStateReceiver;)V
      //   503: invokevirtual 178	android/net/wifi/p2p/WifiP2pManager:requestGroupInfo	(Landroid/net/wifi/p2p/WifiP2pManager$Channel;Landroid/net/wifi/p2p/WifiP2pManager$GroupInfoListener;)V
      //   506: ldc 180
      //   508: ldc 182
      //   510: invokestatic 188	android/os/SystemProperties:set	(Ljava/lang/String;Ljava/lang/String;)V
      //   513: aload_0
      //   514: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   517: iconst_5
      //   518: invokestatic 75	com/samsung/wfd/WfdService:access$400	(Lcom/samsung/wfd/WfdService;I)V
      //   521: aload_0
      //   522: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   525: iconst_1
      //   526: invokestatic 191	com/samsung/wfd/WfdService:access$1700	(Lcom/samsung/wfd/WfdService;I)V
      //   529: aload_0
      //   530: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   533: invokestatic 194	com/samsung/wfd/WfdService:access$1800	(Lcom/samsung/wfd/WfdService;)V
      //   536: goto -460 -> 76
      //   539: aload_0
      //   540: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   543: new 29	java/lang/StringBuilder
      //   546: dup
      //   547: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   550: ldc 196
      //   552: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   555: aload_0
      //   556: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   559: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   562: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   565: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   568: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   571: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   574: goto -498 -> 76
      //   577: aload 4
      //   579: ldc 198
      //   581: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   584: ifeq +132 -> 716
      //   587: aload_0
      //   588: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   591: new 29	java/lang/StringBuilder
      //   594: dup
      //   595: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   598: ldc 200
      //   600: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   603: aload_0
      //   604: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   607: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   610: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   613: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   616: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   619: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   622: aload_0
      //   623: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   626: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   629: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   632: ifeq +31 -> 663
      //   635: aload_0
      //   636: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   639: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   642: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   645: iconst_5
      //   646: if_icmpeq +17 -> 663
      //   649: aload_0
      //   650: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   653: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   656: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   659: iconst_4
      //   660: if_icmpne +18 -> 678
      //   663: aload_0
      //   664: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   667: invokestatic 100	com/samsung/wfd/WfdService:access$1000	(Lcom/samsung/wfd/WfdService;)Lcom/samsung/wfd/WfdService$WfdStateMachine;
      //   670: ldc 101
      //   672: invokevirtual 107	com/samsung/wfd/WfdService$WfdStateMachine:sendMessage	(I)V
      //   675: goto -599 -> 76
      //   678: aload_0
      //   679: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   682: new 29	java/lang/StringBuilder
      //   685: dup
      //   686: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   689: ldc 202
      //   691: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   694: aload_0
      //   695: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   698: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   701: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   704: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   707: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   710: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   713: goto -637 -> 76
      //   716: ldc 204
      //   718: aload 4
      //   720: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   723: ifeq +278 -> 1001
      //   726: aload_0
      //   727: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   730: ldc 206
      //   732: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   735: aload_2
      //   736: ldc 208
      //   738: invokevirtual 212	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
      //   741: checkcast 214	android/net/NetworkInfo
      //   744: astore 35
      //   746: aload_0
      //   747: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   750: aload_2
      //   751: ldc 216
      //   753: invokevirtual 212	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
      //   756: checkcast 218	android/net/wifi/p2p/WifiP2pInfo
      //   759: invokestatic 222	com/samsung/wfd/WfdService:access$1902	(Lcom/samsung/wfd/WfdService;Landroid/net/wifi/p2p/WifiP2pInfo;)Landroid/net/wifi/p2p/WifiP2pInfo;
      //   762: pop
      //   763: aload 35
      //   765: invokevirtual 226	android/net/NetworkInfo:isConnected	()Z
      //   768: ifeq +130 -> 898
      //   771: aload_0
      //   772: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   775: invokestatic 163	com/samsung/wfd/WfdService:access$1600	(Lcom/samsung/wfd/WfdService;)Landroid/net/wifi/p2p/WifiP2pManager;
      //   778: ifnonnull +8 -> 786
      //   781: aload_0
      //   782: monitorexit
      //   783: goto +2089 -> 2872
      //   786: aload_0
      //   787: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   790: ldc 228
      //   792: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   795: aload_0
      //   796: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   799: iconst_1
      //   800: invokestatic 231	com/samsung/wfd/WfdService:access$2002	(Lcom/samsung/wfd/WfdService;Z)Z
      //   803: pop
      //   804: aload_0
      //   805: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   808: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   811: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   814: iconst_2
      //   815: if_icmpne +45 -> 860
      //   818: aload_0
      //   819: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   822: iconst_3
      //   823: invokestatic 75	com/samsung/wfd/WfdService:access$400	(Lcom/samsung/wfd/WfdService;I)V
      //   826: aload_0
      //   827: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   830: invokestatic 234	com/samsung/wfd/WfdService:access$2100	(Lcom/samsung/wfd/WfdService;)Z
      //   833: ifne -757 -> 76
      //   836: aload_0
      //   837: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   840: ldc 236
      //   842: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   845: aload_0
      //   846: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   849: invokestatic 100	com/samsung/wfd/WfdService:access$1000	(Lcom/samsung/wfd/WfdService;)Lcom/samsung/wfd/WfdService$WfdStateMachine;
      //   852: ldc 237
      //   854: invokevirtual 107	com/samsung/wfd/WfdService$WfdStateMachine:sendMessage	(I)V
      //   857: goto -781 -> 76
      //   860: aload_0
      //   861: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   864: new 29	java/lang/StringBuilder
      //   867: dup
      //   868: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   871: ldc 239
      //   873: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   876: aload_0
      //   877: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   880: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   883: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   886: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   889: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   892: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   895: goto -819 -> 76
      //   898: aload_0
      //   899: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   902: ldc 241
      //   904: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   907: aload_0
      //   908: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   911: iconst_0
      //   912: invokestatic 96	com/samsung/wfd/WfdService:access$902	(Lcom/samsung/wfd/WfdService;Z)Z
      //   915: pop
      //   916: aload_0
      //   917: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   920: iconst_0
      //   921: invokestatic 231	com/samsung/wfd/WfdService:access$2002	(Lcom/samsung/wfd/WfdService;Z)Z
      //   924: pop
      //   925: aload_0
      //   926: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   929: aconst_null
      //   930: invokestatic 91	com/samsung/wfd/WfdService:access$802	(Lcom/samsung/wfd/WfdService;Ljava/lang/String;)Ljava/lang/String;
      //   933: pop
      //   934: aload_0
      //   935: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   938: invokestatic 244	com/samsung/wfd/WfdService:access$2200	(Lcom/samsung/wfd/WfdService;)Z
      //   941: ifeq +25 -> 966
      //   944: aload_0
      //   945: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   948: ldc 246
      //   950: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   953: aload_0
      //   954: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   957: invokevirtual 249	com/samsung/wfd/WfdService:setWfdTerminate	()Z
      //   960: pop
      //   961: aload_0
      //   962: monitorexit
      //   963: goto +1909 -> 2872
      //   966: aload_0
      //   967: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   970: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   973: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   976: iconst_3
      //   977: if_icmplt -901 -> 76
      //   980: aload_0
      //   981: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   984: ldc 251
      //   986: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   989: aload_0
      //   990: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   993: ldc 252
      //   995: invokestatic 255	com/samsung/wfd/WfdService:access$2300	(Lcom/samsung/wfd/WfdService;I)V
      //   998: goto -922 -> 76
      //   1001: aload 4
      //   1003: ldc_w 257
      //   1006: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1009: ifeq +205 -> 1214
      //   1012: aload_0
      //   1013: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1016: invokestatic 163	com/samsung/wfd/WfdService:access$1600	(Lcom/samsung/wfd/WfdService;)Landroid/net/wifi/p2p/WifiP2pManager;
      //   1019: ifnonnull +8 -> 1027
      //   1022: aload_0
      //   1023: monitorexit
      //   1024: goto +1848 -> 2872
      //   1027: aload_0
      //   1028: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1031: aload_2
      //   1032: ldc_w 259
      //   1035: invokevirtual 212	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
      //   1038: checkcast 261	android/net/wifi/p2p/WifiP2pGroup
      //   1041: putfield 265	com/samsung/wfd/WfdService:mGroup	Landroid/net/wifi/p2p/WifiP2pGroup;
      //   1044: aload_0
      //   1045: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1048: getfield 265	com/samsung/wfd/WfdService:mGroup	Landroid/net/wifi/p2p/WifiP2pGroup;
      //   1051: ifnonnull +18 -> 1069
      //   1054: aload_0
      //   1055: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1058: ldc_w 267
      //   1061: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1064: aload_0
      //   1065: monitorexit
      //   1066: goto +1806 -> 2872
      //   1069: aload_0
      //   1070: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1073: aload_0
      //   1074: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1077: getfield 265	com/samsung/wfd/WfdService:mGroup	Landroid/net/wifi/p2p/WifiP2pGroup;
      //   1080: invokevirtual 270	android/net/wifi/p2p/WifiP2pGroup:getFrequency	()I
      //   1083: invokestatic 273	com/samsung/wfd/WfdService:access$2402	(Lcom/samsung/wfd/WfdService;I)I
      //   1086: pop
      //   1087: aload_0
      //   1088: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1091: new 29	java/lang/StringBuilder
      //   1094: dup
      //   1095: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   1098: ldc_w 275
      //   1101: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1104: aload_0
      //   1105: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1108: invokestatic 278	com/samsung/wfd/WfdService:access$2400	(Lcom/samsung/wfd/WfdService;)I
      //   1111: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1114: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1117: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1120: aload_0
      //   1121: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1124: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   1127: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   1130: iconst_2
      //   1131: if_icmpne +45 -> 1176
      //   1134: aload_0
      //   1135: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1138: iconst_3
      //   1139: invokestatic 75	com/samsung/wfd/WfdService:access$400	(Lcom/samsung/wfd/WfdService;I)V
      //   1142: aload_0
      //   1143: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1146: invokestatic 234	com/samsung/wfd/WfdService:access$2100	(Lcom/samsung/wfd/WfdService;)Z
      //   1149: ifne -1073 -> 76
      //   1152: aload_0
      //   1153: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1156: ldc 236
      //   1158: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1161: aload_0
      //   1162: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1165: invokestatic 100	com/samsung/wfd/WfdService:access$1000	(Lcom/samsung/wfd/WfdService;)Lcom/samsung/wfd/WfdService$WfdStateMachine;
      //   1168: ldc 237
      //   1170: invokevirtual 107	com/samsung/wfd/WfdService$WfdStateMachine:sendMessage	(I)V
      //   1173: goto -1097 -> 76
      //   1176: aload_0
      //   1177: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1180: new 29	java/lang/StringBuilder
      //   1183: dup
      //   1184: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   1187: ldc 239
      //   1189: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1192: aload_0
      //   1193: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1196: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   1199: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   1202: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1205: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1208: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1211: goto -1135 -> 76
      //   1214: aload 4
      //   1216: ldc_w 280
      //   1219: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1222: ifeq +40 -> 1262
      //   1225: aload_0
      //   1226: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1229: invokestatic 163	com/samsung/wfd/WfdService:access$1600	(Lcom/samsung/wfd/WfdService;)Landroid/net/wifi/p2p/WifiP2pManager;
      //   1232: ifnonnull +8 -> 1240
      //   1235: aload_0
      //   1236: monitorexit
      //   1237: goto +1635 -> 2872
      //   1240: aload_0
      //   1241: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1244: iconst_0
      //   1245: invokestatic 273	com/samsung/wfd/WfdService:access$2402	(Lcom/samsung/wfd/WfdService;I)I
      //   1248: pop
      //   1249: aload_0
      //   1250: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1253: ldc_w 282
      //   1256: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1259: goto -1183 -> 76
      //   1262: aload 4
      //   1264: ldc_w 284
      //   1267: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1270: ifeq +101 -> 1371
      //   1273: aload_2
      //   1274: ldc_w 286
      //   1277: iconst_0
      //   1278: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   1281: istore 31
      //   1283: aload_0
      //   1284: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1287: iload 31
      //   1289: aload_0
      //   1290: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1293: invokestatic 289	com/samsung/wfd/WfdService:access$2600	(Lcom/samsung/wfd/WfdService;)I
      //   1296: iand
      //   1297: invokestatic 292	com/samsung/wfd/WfdService:access$2502	(Lcom/samsung/wfd/WfdService;I)I
      //   1300: pop
      //   1301: aload_0
      //   1302: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1305: new 29	java/lang/StringBuilder
      //   1308: dup
      //   1309: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   1312: ldc_w 294
      //   1315: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1318: iload 31
      //   1320: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1323: ldc_w 296
      //   1326: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1329: aload_0
      //   1330: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1333: invokestatic 289	com/samsung/wfd/WfdService:access$2600	(Lcom/samsung/wfd/WfdService;)I
      //   1336: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1339: ldc_w 298
      //   1342: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1345: aload_0
      //   1346: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1349: invokestatic 301	com/samsung/wfd/WfdService:access$2500	(Lcom/samsung/wfd/WfdService;)I
      //   1352: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1355: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1358: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1361: aload_0
      //   1362: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1365: invokestatic 304	com/samsung/wfd/WfdService:access$2700	(Lcom/samsung/wfd/WfdService;)V
      //   1368: goto -1292 -> 76
      //   1371: aload 4
      //   1373: ldc_w 306
      //   1376: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1379: ifeq +23 -> 1402
      //   1382: aload_0
      //   1383: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1386: ldc_w 308
      //   1389: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1392: aload_0
      //   1393: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1396: invokestatic 304	com/samsung/wfd/WfdService:access$2700	(Lcom/samsung/wfd/WfdService;)V
      //   1399: goto -1323 -> 76
      //   1402: aload 4
      //   1404: ldc_w 310
      //   1407: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1410: ifeq +168 -> 1578
      //   1413: aload_0
      //   1414: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1417: aload_2
      //   1418: ldc_w 286
      //   1421: iconst_0
      //   1422: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   1425: invokestatic 313	com/samsung/wfd/WfdService:access$2802	(Lcom/samsung/wfd/WfdService;I)I
      //   1428: pop
      //   1429: aload_0
      //   1430: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1433: aload_2
      //   1434: ldc_w 315
      //   1437: invokevirtual 115	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
      //   1440: invokestatic 318	com/samsung/wfd/WfdService:access$2902	(Lcom/samsung/wfd/WfdService;Ljava/lang/String;)Ljava/lang/String;
      //   1443: pop
      //   1444: aload_0
      //   1445: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1448: new 29	java/lang/StringBuilder
      //   1451: dup
      //   1452: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   1455: ldc_w 320
      //   1458: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1461: aload_0
      //   1462: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1465: invokestatic 323	com/samsung/wfd/WfdService:access$2800	(Lcom/samsung/wfd/WfdService;)I
      //   1468: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1471: ldc_w 325
      //   1474: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1477: aload_0
      //   1478: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1481: invokestatic 328	com/samsung/wfd/WfdService:access$2900	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   1484: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1487: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1490: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1493: aload_0
      //   1494: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1497: aload_2
      //   1498: ldc_w 330
      //   1501: invokevirtual 115	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
      //   1504: invokestatic 333	com/samsung/wfd/WfdService:access$3002	(Lcom/samsung/wfd/WfdService;Ljava/lang/String;)Ljava/lang/String;
      //   1507: pop
      //   1508: aload_0
      //   1509: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1512: invokestatic 336	com/samsung/wfd/WfdService:access$3000	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   1515: ifnull -1439 -> 76
      //   1518: aload_0
      //   1519: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1522: invokestatic 336	com/samsung/wfd/WfdService:access$3000	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   1525: ldc_w 338
      //   1528: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1531: ifeq -1455 -> 76
      //   1534: aload_0
      //   1535: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1538: new 29	java/lang/StringBuilder
      //   1541: dup
      //   1542: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   1545: ldc_w 340
      //   1548: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1551: aload_0
      //   1552: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1555: invokestatic 336	com/samsung/wfd/WfdService:access$3000	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   1558: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1561: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1564: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1567: aload_0
      //   1568: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1571: invokevirtual 249	com/samsung/wfd/WfdService:setWfdTerminate	()Z
      //   1574: pop
      //   1575: goto -1499 -> 76
      //   1578: aload 4
      //   1580: ldc_w 342
      //   1583: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1586: ifeq +229 -> 1815
      //   1589: aload_2
      //   1590: ldc 208
      //   1592: invokevirtual 212	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
      //   1595: checkcast 214	android/net/NetworkInfo
      //   1598: astore 23
      //   1600: aload 23
      //   1602: ifnull +200 -> 1802
      //   1605: aload 23
      //   1607: invokevirtual 226	android/net/NetworkInfo:isConnected	()Z
      //   1610: ifeq +170 -> 1780
      //   1613: aload_0
      //   1614: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1617: ldc_w 344
      //   1620: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1623: aload_2
      //   1624: ldc_w 346
      //   1627: invokevirtual 212	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
      //   1630: checkcast 348	android/net/wifi/WifiInfo
      //   1633: astore 25
      //   1635: aload 25
      //   1637: ifnonnull +18 -> 1655
      //   1640: aload_0
      //   1641: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1644: ldc_w 350
      //   1647: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1650: aload_0
      //   1651: monitorexit
      //   1652: goto +1220 -> 2872
      //   1655: aload_0
      //   1656: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1659: aload 25
      //   1661: invokevirtual 351	android/net/wifi/WifiInfo:getFrequency	()I
      //   1664: invokestatic 354	com/samsung/wfd/WfdService:access$3102	(Lcom/samsung/wfd/WfdService;I)I
      //   1667: pop
      //   1668: aload_0
      //   1669: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1672: invokestatic 234	com/samsung/wfd/WfdService:access$2100	(Lcom/samsung/wfd/WfdService;)Z
      //   1675: ifeq +92 -> 1767
      //   1678: aload_0
      //   1679: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1682: new 29	java/lang/StringBuilder
      //   1685: dup
      //   1686: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   1689: ldc_w 356
      //   1692: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1695: aload_0
      //   1696: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1699: invokestatic 359	com/samsung/wfd/WfdService:access$3100	(Lcom/samsung/wfd/WfdService;)I
      //   1702: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1705: ldc_w 361
      //   1708: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1711: aload_0
      //   1712: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1715: invokestatic 278	com/samsung/wfd/WfdService:access$2400	(Lcom/samsung/wfd/WfdService;)I
      //   1718: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1721: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1724: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1727: aload_0
      //   1728: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1731: invokestatic 359	com/samsung/wfd/WfdService:access$3100	(Lcom/samsung/wfd/WfdService;)I
      //   1734: aload_0
      //   1735: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1738: invokestatic 278	com/samsung/wfd/WfdService:access$2400	(Lcom/samsung/wfd/WfdService;)I
      //   1741: if_icmpeq -1665 -> 76
      //   1744: aload_0
      //   1745: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1748: ldc_w 363
      //   1751: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1754: aload_0
      //   1755: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1758: ldc_w 364
      //   1761: invokestatic 255	com/samsung/wfd/WfdService:access$2300	(Lcom/samsung/wfd/WfdService;I)V
      //   1764: goto -1688 -> 76
      //   1767: aload_0
      //   1768: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1771: ldc_w 366
      //   1774: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1777: goto -1701 -> 76
      //   1780: aload_0
      //   1781: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1784: iconst_0
      //   1785: invokestatic 354	com/samsung/wfd/WfdService:access$3102	(Lcom/samsung/wfd/WfdService;I)I
      //   1788: pop
      //   1789: aload_0
      //   1790: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1793: ldc_w 368
      //   1796: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1799: goto -1723 -> 76
      //   1802: aload_0
      //   1803: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1806: ldc_w 370
      //   1809: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1812: goto -1736 -> 76
      //   1815: aload 4
      //   1817: ldc_w 372
      //   1820: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1823: ifeq +94 -> 1917
      //   1826: aload_2
      //   1827: ldc_w 374
      //   1830: bipush 14
      //   1832: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   1835: istore 21
      //   1837: aload_0
      //   1838: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1841: new 29	java/lang/StringBuilder
      //   1844: dup
      //   1845: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   1848: ldc_w 376
      //   1851: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1854: iload 21
      //   1856: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1859: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1862: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1865: aload_0
      //   1866: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1869: invokestatic 379	com/samsung/wfd/WfdService:access$3200	(Lcom/samsung/wfd/WfdService;)Z
      //   1872: ifeq -1796 -> 76
      //   1875: iload 21
      //   1877: bipush 11
      //   1879: if_icmpne -1803 -> 76
      //   1882: aload_0
      //   1883: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1886: ldc_w 381
      //   1889: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   1892: aload_0
      //   1893: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1896: iconst_0
      //   1897: invokestatic 384	com/samsung/wfd/WfdService:access$3202	(Lcom/samsung/wfd/WfdService;Z)Z
      //   1900: pop
      //   1901: aload_0
      //   1902: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1905: invokestatic 100	com/samsung/wfd/WfdService:access$1000	(Lcom/samsung/wfd/WfdService;)Lcom/samsung/wfd/WfdService$WfdStateMachine;
      //   1908: ldc_w 385
      //   1911: invokevirtual 107	com/samsung/wfd/WfdService$WfdStateMachine:sendMessage	(I)V
      //   1914: goto -1838 -> 76
      //   1917: aload 4
      //   1919: ldc_w 387
      //   1922: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1925: ifeq +22 -> 1947
      //   1928: aload_0
      //   1929: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1932: aload_2
      //   1933: ldc_w 389
      //   1936: iconst_0
      //   1937: invokevirtual 393	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
      //   1940: invokestatic 396	com/samsung/wfd/WfdService:access$3302	(Lcom/samsung/wfd/WfdService;Z)Z
      //   1943: pop
      //   1944: goto -1868 -> 76
      //   1947: aload 4
      //   1949: ldc_w 398
      //   1952: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1955: ifeq +72 -> 2027
      //   1958: aload_0
      //   1959: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1962: aload_2
      //   1963: ldc 126
      //   1965: invokevirtual 115	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
      //   1968: invokestatic 129	com/samsung/wfd/WfdService:access$1202	(Lcom/samsung/wfd/WfdService;Ljava/lang/String;)Ljava/lang/String;
      //   1971: pop
      //   1972: aload_0
      //   1973: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1976: invokestatic 132	com/samsung/wfd/WfdService:access$1200	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   1979: ifnull -1903 -> 76
      //   1982: aload_0
      //   1983: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   1986: new 29	java/lang/StringBuilder
      //   1989: dup
      //   1990: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   1993: ldc 134
      //   1995: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1998: aload_0
      //   1999: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2002: invokestatic 132	com/samsung/wfd/WfdService:access$1200	(Lcom/samsung/wfd/WfdService;)Ljava/lang/String;
      //   2005: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2008: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2011: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2014: aload_0
      //   2015: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2018: ldc_w 399
      //   2021: invokestatic 255	com/samsung/wfd/WfdService:access$2300	(Lcom/samsung/wfd/WfdService;I)V
      //   2024: goto -1948 -> 76
      //   2027: aload 4
      //   2029: ldc_w 401
      //   2032: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2035: ifeq +29 -> 2064
      //   2038: aload_0
      //   2039: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2042: ldc_w 403
      //   2045: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2048: aload_0
      //   2049: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2052: invokestatic 100	com/samsung/wfd/WfdService:access$1000	(Lcom/samsung/wfd/WfdService;)Lcom/samsung/wfd/WfdService$WfdStateMachine;
      //   2055: ldc_w 404
      //   2058: invokevirtual 107	com/samsung/wfd/WfdService$WfdStateMachine:sendMessage	(I)V
      //   2061: goto -1985 -> 76
      //   2064: aload 4
      //   2066: ldc_w 406
      //   2069: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2072: ifeq +53 -> 2125
      //   2075: aload_2
      //   2076: ldc_w 408
      //   2079: iconst_0
      //   2080: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   2083: istore 18
      //   2085: aload_0
      //   2086: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2089: new 29	java/lang/StringBuilder
      //   2092: dup
      //   2093: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   2096: ldc_w 410
      //   2099: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2102: iload 18
      //   2104: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2107: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2110: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2113: aload_0
      //   2114: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2117: ldc 252
      //   2119: invokestatic 255	com/samsung/wfd/WfdService:access$2300	(Lcom/samsung/wfd/WfdService;I)V
      //   2122: goto -2046 -> 76
      //   2125: aload 4
      //   2127: ldc_w 412
      //   2130: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2133: ifeq +75 -> 2208
      //   2136: aload_0
      //   2137: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2140: aload_2
      //   2141: ldc_w 414
      //   2144: iconst_0
      //   2145: invokevirtual 393	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
      //   2148: invokestatic 417	com/samsung/wfd/WfdService:access$2202	(Lcom/samsung/wfd/WfdService;Z)Z
      //   2151: pop
      //   2152: aload_0
      //   2153: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2156: new 29	java/lang/StringBuilder
      //   2159: dup
      //   2160: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   2163: ldc_w 419
      //   2166: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2169: aload_0
      //   2170: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2173: invokestatic 244	com/samsung/wfd/WfdService:access$2200	(Lcom/samsung/wfd/WfdService;)Z
      //   2176: invokevirtual 422	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
      //   2179: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2182: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2185: aload_0
      //   2186: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2189: invokestatic 244	com/samsung/wfd/WfdService:access$2200	(Lcom/samsung/wfd/WfdService;)Z
      //   2192: ifne -2116 -> 76
      //   2195: aload_0
      //   2196: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2199: ldc_w 424
      //   2202: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2205: goto -2129 -> 76
      //   2208: aload 4
      //   2210: ldc_w 426
      //   2213: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2216: ifeq +196 -> 2412
      //   2219: aload_2
      //   2220: ldc_w 408
      //   2223: iconst_0
      //   2224: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   2227: istore 15
      //   2229: iload 15
      //   2231: lookupswitch	default:+81->2312, 1:+134->2365, 2:+134->2365, 3:+134->2365, 4:+134->2365, 5:+134->2365, 10:+103->2334, 30:+103->2334, 40:+103->2334, 50:+103->2334
      //   2313: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2316: ldc_w 428
      //   2319: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2322: aload_0
      //   2323: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2326: iload 15
      //   2328: invokestatic 431	com/samsung/wfd/WfdService:access$3400	(Lcom/samsung/wfd/WfdService;I)V
      //   2331: goto -2255 -> 76
      //   2334: aload_0
      //   2335: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2338: new 29	java/lang/StringBuilder
      //   2341: dup
      //   2342: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   2345: ldc_w 433
      //   2348: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2351: iload 15
      //   2353: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2356: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2359: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2362: goto -40 -> 2322
      //   2365: aload_0
      //   2366: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2369: new 29	java/lang/StringBuilder
      //   2372: dup
      //   2373: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   2376: ldc_w 435
      //   2379: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2382: iload 15
      //   2384: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2387: ldc_w 437
      //   2390: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2393: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2396: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2399: aload_0
      //   2400: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2403: iload 15
      //   2405: invokevirtual 441	com/samsung/wfd/WfdService:setWfdEnabled	(I)Z
      //   2408: pop
      //   2409: goto -87 -> 2322
      //   2412: aload 4
      //   2414: ldc_w 443
      //   2417: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2420: ifeq +40 -> 2460
      //   2423: aload_0
      //   2424: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2427: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   2430: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   2433: iconst_1
      //   2434: if_icmple -2358 -> 76
      //   2437: aload_0
      //   2438: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2441: invokevirtual 249	com/samsung/wfd/WfdService:setWfdTerminate	()Z
      //   2444: pop
      //   2445: aload_0
      //   2446: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2449: invokestatic 100	com/samsung/wfd/WfdService:access$1000	(Lcom/samsung/wfd/WfdService;)Lcom/samsung/wfd/WfdService$WfdStateMachine;
      //   2452: ldc 101
      //   2454: invokevirtual 107	com/samsung/wfd/WfdService$WfdStateMachine:sendMessage	(I)V
      //   2457: goto -2381 -> 76
      //   2460: aload 4
      //   2462: ldc_w 445
      //   2465: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2468: ifeq +80 -> 2548
      //   2471: aload_2
      //   2472: ldc_w 408
      //   2475: iconst_0
      //   2476: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
      //   2479: istore 13
      //   2481: aload_0
      //   2482: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2485: new 29	java/lang/StringBuilder
      //   2488: dup
      //   2489: invokespecial 30	java/lang/StringBuilder:<init>	()V
      //   2492: ldc_w 447
      //   2495: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2498: iload 13
      //   2500: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   2503: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   2506: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2509: iload 13
      //   2511: tableswitch	default:+362 -> 2873, 0:+17->2528
      //   2529: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2532: ldc_w 449
      //   2535: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2538: aload_0
      //   2539: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2542: invokestatic 452	com/samsung/wfd/WfdService:access$3500	(Lcom/samsung/wfd/WfdService;)V
      //   2545: goto -2469 -> 76
      //   2548: aload 4
      //   2550: ldc_w 454
      //   2553: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2556: ifeq +37 -> 2593
      //   2559: aload_0
      //   2560: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2563: ldc_w 456
      //   2566: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2569: aload_0
      //   2570: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2573: invokestatic 100	com/samsung/wfd/WfdService:access$1000	(Lcom/samsung/wfd/WfdService;)Lcom/samsung/wfd/WfdService$WfdStateMachine;
      //   2576: ldc_w 457
      //   2579: invokevirtual 107	com/samsung/wfd/WfdService$WfdStateMachine:sendMessage	(I)V
      //   2582: aload_0
      //   2583: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2586: invokevirtual 249	com/samsung/wfd/WfdService:setWfdTerminate	()Z
      //   2589: pop
      //   2590: goto -2514 -> 76
      //   2593: aload 4
      //   2595: ldc_w 459
      //   2598: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2601: ifeq +15 -> 2616
      //   2604: aload_0
      //   2605: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2608: iconst_1
      //   2609: invokestatic 462	com/samsung/wfd/WfdService:access$3602	(Lcom/samsung/wfd/WfdService;Z)Z
      //   2612: pop
      //   2613: goto -2537 -> 76
      //   2616: aload 4
      //   2618: ldc_w 464
      //   2621: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2624: ifeq +15 -> 2639
      //   2627: aload_0
      //   2628: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2631: iconst_0
      //   2632: invokestatic 462	com/samsung/wfd/WfdService:access$3602	(Lcom/samsung/wfd/WfdService;Z)Z
      //   2635: pop
      //   2636: goto -2560 -> 76
      //   2639: aload 4
      //   2641: ldc_w 466
      //   2644: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2647: ifeq +29 -> 2676
      //   2650: aload_0
      //   2651: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2654: invokestatic 234	com/samsung/wfd/WfdService:access$2100	(Lcom/samsung/wfd/WfdService;)Z
      //   2657: ifeq -2581 -> 76
      //   2660: aload_0
      //   2661: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2664: invokestatic 100	com/samsung/wfd/WfdService:access$1000	(Lcom/samsung/wfd/WfdService;)Lcom/samsung/wfd/WfdService$WfdStateMachine;
      //   2667: ldc_w 467
      //   2670: invokevirtual 107	com/samsung/wfd/WfdService$WfdStateMachine:sendMessage	(I)V
      //   2673: goto -2597 -> 76
      //   2676: aload 4
      //   2678: ldc_w 469
      //   2681: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2684: ifeq +85 -> 2769
      //   2687: aload_0
      //   2688: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2691: ldc_w 471
      //   2694: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2697: aload_2
      //   2698: ldc_w 473
      //   2701: invokevirtual 212	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
      //   2704: checkcast 475	android/net/wifi/p2p/WifiP2pDevice
      //   2707: astore 7
      //   2709: aload 7
      //   2711: ifnull -2635 -> 76
      //   2714: aload_0
      //   2715: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2718: aconst_null
      //   2719: invokestatic 479	com/samsung/wfd/WfdService:access$3702	(Lcom/samsung/wfd/WfdService;Lcom/samsung/wfd/WfdDevice;)Lcom/samsung/wfd/WfdDevice;
      //   2722: pop
      //   2723: aload 7
      //   2725: getfield 483	android/net/wifi/p2p/WifiP2pDevice:status	I
      //   2728: ifne -2652 -> 76
      //   2731: aload_0
      //   2732: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2735: ldc_w 485
      //   2738: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2741: aload_0
      //   2742: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2745: new 487	com/samsung/wfd/WfdDevice
      //   2748: dup
      //   2749: new 489	com/samsung/wfd/WfdInfo
      //   2752: dup
      //   2753: iconst_1
      //   2754: invokespecial 491	com/samsung/wfd/WfdInfo:<init>	(I)V
      //   2757: aload 7
      //   2759: invokespecial 494	com/samsung/wfd/WfdDevice:<init>	(Lcom/samsung/wfd/WfdInfo;Landroid/net/wifi/p2p/WifiP2pDevice;)V
      //   2762: invokestatic 479	com/samsung/wfd/WfdService:access$3702	(Lcom/samsung/wfd/WfdService;Lcom/samsung/wfd/WfdDevice;)Lcom/samsung/wfd/WfdDevice;
      //   2765: pop
      //   2766: goto -2690 -> 76
      //   2769: aload 4
      //   2771: ldc_w 496
      //   2774: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2777: ifeq +71 -> 2848
      //   2780: aload_0
      //   2781: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2784: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   2787: invokevirtual 71	java/util/concurrent/atomic/AtomicInteger:get	()I
      //   2790: istore 5
      //   2792: iload 5
      //   2794: iconst_4
      //   2795: if_icmpge -2719 -> 76
      //   2798: iload 5
      //   2800: ifeq -2724 -> 76
      //   2803: aload_0
      //   2804: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2807: invokestatic 499	com/samsung/wfd/WfdService:access$3800	(Lcom/samsung/wfd/WfdService;)Z
      //   2810: ifeq +17 -> 2827
      //   2813: aload_0
      //   2814: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2817: invokestatic 65	com/samsung/wfd/WfdService:access$300	(Lcom/samsung/wfd/WfdService;)Ljava/util/concurrent/atomic/AtomicInteger;
      //   2820: iconst_4
      //   2821: invokevirtual 501	java/util/concurrent/atomic/AtomicInteger:set	(I)V
      //   2824: goto -2748 -> 76
      //   2827: aload_0
      //   2828: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2831: ldc_w 503
      //   2834: invokevirtual 506	com/samsung/wfd/WfdService:loge	(Ljava/lang/String;)V
      //   2837: aload_0
      //   2838: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2841: invokevirtual 249	com/samsung/wfd/WfdService:setWfdTerminate	()Z
      //   2844: pop
      //   2845: goto -2769 -> 76
      //   2848: aload 4
      //   2850: ldc_w 508
      //   2853: invokevirtual 51	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2856: ifeq -2780 -> 76
      //   2859: aload_0
      //   2860: getfield 13	com/samsung/wfd/WfdService$WifiStateReceiver:this$0	Lcom/samsung/wfd/WfdService;
      //   2863: ldc_w 510
      //   2866: invokevirtual 43	com/samsung/wfd/WfdService:logd	(Ljava/lang/String;)V
      //   2869: goto -2793 -> 76
      //   2872: return
      //   2873: goto -2797 -> 76
      //
      // Exception table:
      //   from	to	target	type
      //   2	144	141	finally
      //   146	2869	141	finally
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WfdService
 * JD-Core Version:    0.6.0
 */