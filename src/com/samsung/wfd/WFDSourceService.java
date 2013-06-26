package com.samsung.wfd;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemProperties;
import android.util.Log;
import android.view.Surface;

public class WFDSourceService extends Service
  implements WFDNative.WfdActionListener
{
  public static final String EXTRA_CONNECTED_SESSION_ID = "connectedSessionID";
  private static final int HALF_SEC = 500;
  private static final int NOTIFICATION_ID = 100;
  private static final int OPERATION_TIMEOUT = 10000;
  private static WfdEnums.AVPlaybackMode PLAYBACK_MODE;
  private static final Object StaticLock = new Object();
  private static final String TAG = "WFDSourceService";
  private static final int TIMEOUT_TRY = 10;
  private static boolean bStandbyMode;
  private static Context mContext;
  private static boolean m_bAlive = false;
  private boolean IS_HDCP_ENABLED = false;
  private boolean IS_RTP_TRANSPORT_NEGOTIATED = false;
  private final int WDTS_PAUSE = 1;
  private final int WDTS_PLAY = 0;
  private AudioManager mAudioManager;
  private int mAudioMode = 1920;
  private boolean mBlockedRemoteEvents = false;
  CountDownTimer mCountdown = null;
  private boolean mCpuLockEnabled = false;
  private int mFrequency = 0;
  private boolean mIsEnabled = false;
  private WfdDevice mMyDevice;
  private boolean mPauseMode = false;
  private WfdDevice mPeerDevice;
  private boolean mPhoneCall = false;
  private PowerManager mPowerManager = null;
  private WfdEnums.ConnectionType mPrevConnectionType = WfdEnums.ConnectionType.UDP;
  private boolean mPrivateEventsMode = false;
  private BroadcastReceiver mReceiver = null;
  private boolean mScreenLock = false;
  private int mSessionId = -1;
  private WfdEnums.SessionState mState = WfdEnums.SessionState.INVALID;
  private String mUpdateURL = null;
  private WfdManager mWFDManager;
  private final IWfdSourceService.Stub mWfdSourceBinder = new IWfdSourceService.Stub()
  {
    public boolean WFDGetSubtitleStatus()
    {
      Log.e("WFDSourceService", "WFDGetSubtitleStatus");
      return WFDNative.WFDGetSubtitleStatus();
    }

    public boolean WFDGetSuspendStatus()
    {
      Log.e("WFDSourceService", "WFDGetSuspendStatus");
      return WFDNative.WFDGetSuspendStatus();
    }

    public boolean WFDPostSubtitle(String paramString, int paramInt)
    {
      return WFDNative.WFDPostSubtitle(paramString, paramInt);
    }

    public boolean WFDPostSuspend(String paramString)
    {
      Log.e("WFDSourceService", "WFDPostSuspend : " + paramString);
      return WFDNative.WFDPostSuspend(paramString);
    }

    public boolean WFDSetSubtitleStatus(boolean paramBoolean)
    {
      Log.e("WFDSourceService", "WFDSetSubtitleStatus : " + paramBoolean);
      return WFDNative.WFDSetSubtitleStatus(paramBoolean);
    }

    public boolean WFDSetSuspendStatus(boolean paramBoolean)
    {
      Log.e("WFDSourceService", "WFDSetSuspendStatus : " + paramBoolean);
      return WFDNative.WFDSetSuspendStatus(paramBoolean);
    }
  };
  private WFDUibcManager mWfdUibcManager;
  private WifiManager.WifiLock mWiFiLock = null;
  private boolean noError = true;
  private int preventCounter = 6;
  private boolean teardownFromApp = false;
  private boolean uibcEnabled = false;

  static
  {
    mContext = null;
    PLAYBACK_MODE = WfdEnums.AVPlaybackMode.AUDIO_VIDEO;
    bStandbyMode = false;
  }

  private void StartRTSP()
  {
    Log.d("WFDSourceService", "StartRTSP");
    this.teardownFromApp = false;
    startForegroundCompat();
    sendBroadcastIntent("com.samsung.wfd.WFD_SERVICE_STARTED");
  }

  private boolean aquireResources()
  {
    int i = 0;
    try
    {
      mContext.enforceCallingOrSelfPermission("android.permission.WAKE_LOCK", "WFDSourceService");
    }
    catch (SecurityException localSecurityException1)
    {
      try
      {
        mContext.enforceCallingOrSelfPermission("android.permission.WAKE_LOCK", "WfdService");
        i = 1;
        while (true)
        {
          return i;
          localSecurityException1 = localSecurityException1;
          Log.e("WFDSourceService", "catch Exception aquireResources");
          localSecurityException1.printStackTrace();
        }
      }
      catch (SecurityException localSecurityException2)
      {
        while (true)
        {
          Log.e("WFDSourceService", "catch Exception aquireResources");
          localSecurityException2.printStackTrace();
        }
      }
    }
  }

  private int internalPause(boolean paramBoolean)
  {
    synchronized (StaticLock)
    {
      WfdEnums.SessionState localSessionState = this.mState;
      if (this.mState != WfdEnums.SessionState.PLAY)
        break label111;
      pause(paramBoolean);
      try
      {
        StaticLock.wait(10000L);
        if (this.mState == localSessionState)
        {
          Log.e("WFDSourceService", "ERROR: pause not set. time out happened");
          int j = WfdEnums.ErrorType.OPERATION_TIMED_OUT.getCode();
          i = j;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.e("WFDSourceService", "Wait for PAUSE interrupted", localInterruptedException);
        i = WfdEnums.ErrorType.UNKNOWN.getCode();
      }
    }
    int i = 0;
    monitorexit;
    break label130;
    label111: Log.e("WFDSourceService", "WfdEnums.Session state is not PLAY");
    i = WfdEnums.ErrorType.INCORRECT_STATE_FOR_OPERATION.getCode();
    monitorexit;
    label130: return i;
  }

  private int internalPlay(boolean paramBoolean)
  {
    synchronized (StaticLock)
    {
      WfdEnums.SessionState localSessionState = this.mState;
      if ((this.mState != WfdEnums.SessionState.ESTABLISHED) && (this.mState != WfdEnums.SessionState.PAUSE))
        break label121;
      play(paramBoolean);
      try
      {
        StaticLock.wait(10000L);
        if (this.mState == localSessionState)
        {
          Log.e("WFDSourceService", "ERROR: play not set. time out happened");
          int j = WfdEnums.ErrorType.OPERATION_TIMED_OUT.getCode();
          i = j;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.e("WFDSourceService", "Wait for PLAY interrupted", localInterruptedException);
        i = WfdEnums.ErrorType.UNKNOWN.getCode();
      }
    }
    int i = 0;
    monitorexit;
    break label140;
    label121: Log.e("WFDSourceService", "WfdEnums.Session state is not ESTABLISHED or PAUSE");
    i = WfdEnums.ErrorType.INCORRECT_STATE_FOR_OPERATION.getCode();
    monitorexit;
    label140: return i;
  }

  public static boolean isAlive()
  {
    Log.d("WFDSourceService", "isAlive");
    return m_bAlive;
  }

  private static void sendBroadcastIntent(String paramString)
  {
    Log.d("WFDSourceService", "sendBroadcastIntent");
    Intent localIntent = new Intent(paramString);
    localIntent.addFlags(134217728);
    if (mContext != null)
      mContext.sendBroadcast(localIntent);
  }

  private static void sendBroadcastIntent(String paramString, int paramInt)
  {
    Log.d("WFDSourceService", "sendBroadcastIntent(Flag)");
    Intent localIntent = new Intent(paramString);
    localIntent.addFlags(134217728);
    localIntent.putExtra("CONNECTION_MODE", paramInt);
    if (mContext != null)
      mContext.sendBroadcast(localIntent);
  }

  private int setBitrate(int paramInt)
  {
    WFDNative.setBitrate(paramInt);
    return 0;
  }

  // ERROR //
  private int setResolution(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   3: astore_3
    //   4: aload_3
    //   5: monitorenter
    //   6: invokestatic 352	com/samsung/wfd/WfdEnums$CapabilityType:values	()[Lcom/samsung/wfd/WfdEnums$CapabilityType;
    //   9: iload_1
    //   10: aaload
    //   11: astore 5
    //   13: getstatic 356	com/samsung/wfd/WFDSourceService$2:$SwitchMap$com$samsung$wfd$WfdEnums$CapabilityType	[I
    //   16: aload 5
    //   18: invokevirtual 359	com/samsung/wfd/WfdEnums$CapabilityType:ordinal	()I
    //   21: iaload
    //   22: tableswitch	default:+26 -> 48, 1:+39->61, 2:+66->88, 3:+86->108
    //   49: aconst_null
    //   50: fmul
    //   51: invokevirtual 289	com/samsung/wfd/WfdEnums$ErrorType:getCode	()I
    //   54: istore 7
    //   56: aload_3
    //   57: monitorexit
    //   58: goto +194 -> 252
    //   61: iload_2
    //   62: invokestatic 368	com/samsung/wfd/WfdEnums:isCeaResolution	(I)Z
    //   65: ifne +63 -> 128
    //   68: getstatic 362	com/samsung/wfd/WfdEnums$ErrorType:INVALID_ARG	Lcom/samsung/wfd/WfdEnums$ErrorType;
    //   71: invokevirtual 289	com/samsung/wfd/WfdEnums$ErrorType:getCode	()I
    //   74: istore 7
    //   76: aload_3
    //   77: monitorexit
    //   78: goto +174 -> 252
    //   81: astore 4
    //   83: aload_3
    //   84: monitorexit
    //   85: aload 4
    //   87: athrow
    //   88: iload_2
    //   89: invokestatic 371	com/samsung/wfd/WfdEnums:isHhResolution	(I)Z
    //   92: ifne +36 -> 128
    //   95: getstatic 362	com/samsung/wfd/WfdEnums$ErrorType:INVALID_ARG	Lcom/samsung/wfd/WfdEnums$ErrorType;
    //   98: invokevirtual 289	com/samsung/wfd/WfdEnums$ErrorType:getCode	()I
    //   101: istore 7
    //   103: aload_3
    //   104: monitorexit
    //   105: goto +147 -> 252
    //   108: iload_2
    //   109: invokestatic 374	com/samsung/wfd/WfdEnums:isVesaResolution	(I)Z
    //   112: ifne +16 -> 128
    //   115: getstatic 362	com/samsung/wfd/WfdEnums$ErrorType:INVALID_ARG	Lcom/samsung/wfd/WfdEnums$ErrorType;
    //   118: invokevirtual 289	com/samsung/wfd/WfdEnums$ErrorType:getCode	()I
    //   121: istore 7
    //   123: aload_3
    //   124: monitorexit
    //   125: goto +127 -> 252
    //   128: aload_0
    //   129: getfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   132: astore 6
    //   134: aload 6
    //   136: getstatic 268	com/samsung/wfd/WfdEnums$SessionState:PLAY	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   139: if_acmpne +61 -> 200
    //   142: aload_0
    //   143: iconst_1
    //   144: invokespecial 218	com/samsung/wfd/WFDSourceService:internalPause	(Z)I
    //   147: istore 10
    //   149: ldc 31
    //   151: new 376	java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial 377	java/lang/StringBuilder:<init>	()V
    //   158: ldc_w 379
    //   161: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: iload 10
    //   166: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   169: ldc_w 388
    //   172: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   181: pop
    //   182: iload 10
    //   184: ifeq +16 -> 200
    //   187: getstatic 297	com/samsung/wfd/WfdEnums$ErrorType:UNKNOWN	Lcom/samsung/wfd/WfdEnums$ErrorType;
    //   190: invokevirtual 289	com/samsung/wfd/WfdEnums$ErrorType:getCode	()I
    //   193: istore 7
    //   195: aload_3
    //   196: monitorexit
    //   197: goto +55 -> 252
    //   200: iload_1
    //   201: iload_2
    //   202: invokestatic 395	com/samsung/wfd/WFDNative:setResolution	(II)V
    //   205: aload 6
    //   207: getstatic 268	com/samsung/wfd/WfdEnums$SessionState:PLAY	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   210: if_acmpne +37 -> 247
    //   213: aload_0
    //   214: iconst_1
    //   215: invokespecial 222	com/samsung/wfd/WFDSourceService:internalPlay	(Z)I
    //   218: istore 8
    //   220: ldc 31
    //   222: new 376	java/lang/StringBuilder
    //   225: dup
    //   226: invokespecial 377	java/lang/StringBuilder:<init>	()V
    //   229: ldc_w 397
    //   232: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: iload 8
    //   237: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   240: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   243: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   246: pop
    //   247: iconst_0
    //   248: istore 7
    //   250: aload_3
    //   251: monitorexit
    //   252: iload 7
    //   254: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   6	85	81	finally
    //   88	252	81	finally
  }

  private void startForegroundCompat()
  {
    Log.d("WFDSourceService", "startForegroundCompat");
    Notification localNotification = new Notification();
    localNotification.setLatestEventInfo(this, "", "", PendingIntent.getService(this, 0, new Intent("android.net.wifi.wfd.WFDSourceService"), 0));
    localNotification.flags = (0x2 | localNotification.flags);
    startForeground(100, localNotification);
  }

  private void stopForegroundCompat()
  {
    Log.d("WFDSourceService", "stopForegroundCompat");
    stopForeground(true);
  }

  // ERROR //
  public void notifyEvent(WfdEnums.WfdEvent paramWfdEvent, int paramInt)
  {
    // Byte code:
    //   0: ldc 31
    //   2: new 376	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 377	java/lang/StringBuilder:<init>	()V
    //   9: ldc_w 432
    //   12: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: iload_2
    //   16: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   19: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   25: pop
    //   26: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   29: astore 4
    //   31: aload 4
    //   33: monitorenter
    //   34: getstatic 438	com/samsung/wfd/WfdEnums$WfdEvent:UIBC_ENABLED	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   37: aload_1
    //   38: if_acmpne +31 -> 69
    //   41: aload_0
    //   42: getfield 136	com/samsung/wfd/WFDSourceService:uibcEnabled	Z
    //   45: ifne +15 -> 60
    //   48: aload_0
    //   49: getfield 440	com/samsung/wfd/WFDSourceService:mWfdUibcManager	Lcom/samsung/wfd/WFDUibcManager;
    //   52: aload_0
    //   53: getfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   56: invokevirtual 445	com/samsung/wfd/WFDUibcManager:start	(I)Z
    //   59: pop
    //   60: aload_0
    //   61: iconst_1
    //   62: putfield 136	com/samsung/wfd/WFDSourceService:uibcEnabled	Z
    //   65: aload 4
    //   67: monitorexit
    //   68: return
    //   69: getstatic 448	com/samsung/wfd/WfdEnums$WfdEvent:UIBC_DISABLED	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   72: aload_1
    //   73: if_acmpne +38 -> 111
    //   76: aload_0
    //   77: getfield 136	com/samsung/wfd/WFDSourceService:uibcEnabled	Z
    //   80: ifeq +15 -> 95
    //   83: aload_0
    //   84: getfield 440	com/samsung/wfd/WFDSourceService:mWfdUibcManager	Lcom/samsung/wfd/WFDUibcManager;
    //   87: aload_0
    //   88: getfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   91: invokevirtual 451	com/samsung/wfd/WFDUibcManager:stop	(I)Z
    //   94: pop
    //   95: aload_0
    //   96: iconst_0
    //   97: putfield 136	com/samsung/wfd/WFDSourceService:uibcEnabled	Z
    //   100: goto -35 -> 65
    //   103: astore 5
    //   105: aload 4
    //   107: monitorexit
    //   108: aload 5
    //   110: athrow
    //   111: getstatic 454	com/samsung/wfd/WfdEnums$WfdEvent:WFD_SERVICE_ENABLED	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   114: aload_1
    //   115: if_acmpne +33 -> 148
    //   118: aload_0
    //   119: iconst_1
    //   120: putfield 114	com/samsung/wfd/WFDSourceService:mIsEnabled	Z
    //   123: ldc_w 456
    //   126: invokestatic 185	com/samsung/wfd/WFDSourceService:sendBroadcastIntent	(Ljava/lang/String;)V
    //   129: aload_0
    //   130: getstatic 459	com/samsung/wfd/WfdEnums$SessionState:ENABLED	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   133: putfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   136: ldc 31
    //   138: ldc_w 461
    //   141: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   144: pop
    //   145: goto -80 -> 65
    //   148: getstatic 464	com/samsung/wfd/WfdEnums$WfdEvent:WFD_SERVICE_DISABLED	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   151: aload_1
    //   152: if_acmpne +55 -> 207
    //   155: aload_0
    //   156: iconst_0
    //   157: putfield 114	com/samsung/wfd/WFDSourceService:mIsEnabled	Z
    //   160: aload_0
    //   161: bipush 255
    //   163: putfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   166: aload_0
    //   167: getfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   170: getstatic 467	com/samsung/wfd/WfdEnums$SessionState:DISABLED	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   173: if_acmpeq +22 -> 195
    //   176: ldc_w 469
    //   179: invokestatic 185	com/samsung/wfd/WFDSourceService:sendBroadcastIntent	(Ljava/lang/String;)V
    //   182: aload_0
    //   183: getstatic 467	com/samsung/wfd/WfdEnums$SessionState:DISABLED	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   186: putfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   189: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   192: invokevirtual 472	java/lang/Object:notifyAll	()V
    //   195: ldc 31
    //   197: ldc_w 474
    //   200: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   203: pop
    //   204: goto -139 -> 65
    //   207: getstatic 477	com/samsung/wfd/WfdEnums$WfdEvent:HDCP_CONNECT_SUCCESS	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   210: aload_1
    //   211: if_acmpne +20 -> 231
    //   214: ldc 31
    //   216: ldc_w 479
    //   219: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   222: pop
    //   223: aload_0
    //   224: iconst_1
    //   225: putfield 144	com/samsung/wfd/WFDSourceService:IS_HDCP_ENABLED	Z
    //   228: goto -163 -> 65
    //   231: getstatic 482	com/samsung/wfd/WfdEnums$WfdEvent:HDCP_CONNECT_FAIL	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   234: aload_1
    //   235: if_acmpne +90 -> 325
    //   238: aload_0
    //   239: getfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   242: ifne +64 -> 306
    //   245: new 322	android/content/Intent
    //   248: dup
    //   249: ldc_w 484
    //   252: invokespecial 324	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   255: astore 32
    //   257: aload 32
    //   259: ldc_w 325
    //   262: invokevirtual 329	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   265: pop
    //   266: aload 32
    //   268: ldc_w 486
    //   271: ldc_w 488
    //   274: invokevirtual 491	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   277: pop
    //   278: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   281: ifnull +11 -> 292
    //   284: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   287: aload 32
    //   289: invokevirtual 333	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   292: ldc 31
    //   294: ldc_w 493
    //   297: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   300: pop
    //   301: aload_0
    //   302: iconst_1
    //   303: putfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   306: aload_0
    //   307: iconst_0
    //   308: putfield 159	com/samsung/wfd/WFDSourceService:noError	Z
    //   311: aload_0
    //   312: iconst_0
    //   313: putfield 144	com/samsung/wfd/WFDSourceService:IS_HDCP_ENABLED	Z
    //   316: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   319: invokevirtual 472	java/lang/Object:notifyAll	()V
    //   322: goto -257 -> 65
    //   325: aload_1
    //   326: getstatic 496	com/samsung/wfd/WfdEnums$WfdEvent:VIDEO_RUNTIME_ERROR	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   329: if_acmpeq +45 -> 374
    //   332: aload_1
    //   333: getstatic 499	com/samsung/wfd/WfdEnums$WfdEvent:AUDIO_RUNTIME_ERROR	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   336: if_acmpeq +38 -> 374
    //   339: aload_1
    //   340: getstatic 502	com/samsung/wfd/WfdEnums$WfdEvent:VIDEO_CONFIGURE_FAILURE	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   343: if_acmpeq +31 -> 374
    //   346: aload_1
    //   347: getstatic 505	com/samsung/wfd/WfdEnums$WfdEvent:HDCP_RUNTIME_ERROR	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   350: if_acmpeq +24 -> 374
    //   353: aload_1
    //   354: getstatic 508	com/samsung/wfd/WfdEnums$WfdEvent:AUDIO_CONFIGURE_FAILURE	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   357: if_acmpeq +17 -> 374
    //   360: aload_1
    //   361: getstatic 511	com/samsung/wfd/WfdEnums$WfdEvent:NETWORK_RUNTIME_ERROR	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   364: if_acmpeq +10 -> 374
    //   367: aload_1
    //   368: getstatic 514	com/samsung/wfd/WfdEnums$WfdEvent:NETWORK_CONFIGURE_FAILURE	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   371: if_acmpne +100 -> 471
    //   374: ldc 31
    //   376: new 376	java/lang/StringBuilder
    //   379: dup
    //   380: invokespecial 377	java/lang/StringBuilder:<init>	()V
    //   383: ldc_w 516
    //   386: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: aload_1
    //   390: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   393: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   396: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   399: pop
    //   400: aload_0
    //   401: getfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   404: ifne -339 -> 65
    //   407: ldc 31
    //   409: ldc_w 521
    //   412: invokestatic 260	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   415: pop
    //   416: new 322	android/content/Intent
    //   419: dup
    //   420: ldc_w 484
    //   423: invokespecial 324	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   426: astore 8
    //   428: aload 8
    //   430: ldc_w 325
    //   433: invokevirtual 329	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   436: pop
    //   437: aload 8
    //   439: ldc_w 486
    //   442: ldc_w 523
    //   445: invokevirtual 491	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   448: pop
    //   449: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   452: ifnull +11 -> 463
    //   455: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   458: aload 8
    //   460: invokevirtual 333	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   463: aload_0
    //   464: iconst_1
    //   465: putfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   468: goto -403 -> 65
    //   471: getstatic 526	com/samsung/wfd/WfdEnums$WfdEvent:MM_RTP_EVENT_FAILURE	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   474: aload_1
    //   475: if_acmpne +85 -> 560
    //   478: aload_0
    //   479: getfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   482: ifne +64 -> 546
    //   485: new 322	android/content/Intent
    //   488: dup
    //   489: ldc_w 484
    //   492: invokespecial 324	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   495: astore 28
    //   497: aload 28
    //   499: ldc_w 325
    //   502: invokevirtual 329	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   505: pop
    //   506: aload 28
    //   508: ldc_w 486
    //   511: ldc_w 527
    //   514: invokevirtual 491	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   517: pop
    //   518: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   521: ifnull +11 -> 532
    //   524: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   527: aload 28
    //   529: invokevirtual 333	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   532: ldc 31
    //   534: ldc_w 529
    //   537: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   540: pop
    //   541: aload_0
    //   542: iconst_1
    //   543: putfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   546: aload_0
    //   547: iconst_0
    //   548: putfield 159	com/samsung/wfd/WFDSourceService:noError	Z
    //   551: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   554: invokevirtual 472	java/lang/Object:notifyAll	()V
    //   557: goto -492 -> 65
    //   560: getstatic 532	com/samsung/wfd/WfdEnums$WfdEvent:RTP_TRANSPORT_NEGOTIATED	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   563: aload_1
    //   564: if_acmpne +26 -> 590
    //   567: ldc 31
    //   569: ldc_w 534
    //   572: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   575: pop
    //   576: aload_0
    //   577: iconst_1
    //   578: putfield 146	com/samsung/wfd/WFDSourceService:IS_RTP_TRANSPORT_NEGOTIATED	Z
    //   581: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   584: invokevirtual 472	java/lang/Object:notifyAll	()V
    //   587: goto -522 -> 65
    //   590: getstatic 537	com/samsung/wfd/WfdEnums$WfdEvent:PAUSE_START	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   593: aload_1
    //   594: if_acmpne +15 -> 609
    //   597: ldc 31
    //   599: ldc_w 539
    //   602: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   605: pop
    //   606: goto -541 -> 65
    //   609: getstatic 542	com/samsung/wfd/WfdEnums$WfdEvent:PLAY_START	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   612: aload_1
    //   613: if_acmpne +15 -> 628
    //   616: ldc 31
    //   618: ldc_w 544
    //   621: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   624: pop
    //   625: goto -560 -> 65
    //   628: getstatic 547	com/samsung/wfd/WfdEnums$WfdEvent:STANDBY_START	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   631: aload_1
    //   632: if_acmpne +15 -> 647
    //   635: ldc 31
    //   637: ldc_w 549
    //   640: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   643: pop
    //   644: goto -579 -> 65
    //   647: getstatic 552	com/samsung/wfd/WfdEnums$WfdEvent:AUDIOPROXY_CLOSED	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   650: aload_1
    //   651: if_acmpne +15 -> 666
    //   654: ldc 31
    //   656: ldc_w 554
    //   659: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   662: pop
    //   663: goto -598 -> 65
    //   666: getstatic 557	com/samsung/wfd/WfdEnums$WfdEvent:TEARDOWN_START	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   669: aload_1
    //   670: if_acmpne +88 -> 758
    //   673: ldc 31
    //   675: ldc_w 559
    //   678: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   681: pop
    //   682: aload_0
    //   683: getfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   686: ifne +64 -> 750
    //   689: ldc 31
    //   691: ldc_w 521
    //   694: invokestatic 260	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   697: pop
    //   698: new 322	android/content/Intent
    //   701: dup
    //   702: ldc_w 484
    //   705: invokespecial 324	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   708: astore 20
    //   710: aload 20
    //   712: ldc_w 325
    //   715: invokevirtual 329	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   718: pop
    //   719: aload 20
    //   721: ldc_w 486
    //   724: ldc_w 561
    //   727: invokevirtual 491	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   730: pop
    //   731: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   734: ifnull +11 -> 745
    //   737: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   740: aload 20
    //   742: invokevirtual 333	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   745: aload_0
    //   746: iconst_1
    //   747: putfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   750: aload_0
    //   751: iconst_0
    //   752: putfield 159	com/samsung/wfd/WFDSourceService:noError	Z
    //   755: goto -690 -> 65
    //   758: getstatic 564	com/samsung/wfd/WfdEnums$WfdEvent:ERROR	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   761: aload_1
    //   762: if_acmpne +85 -> 847
    //   765: aload_0
    //   766: getfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   769: ifne +55 -> 824
    //   772: new 322	android/content/Intent
    //   775: dup
    //   776: ldc_w 484
    //   779: invokespecial 324	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   782: astore 14
    //   784: aload 14
    //   786: ldc_w 325
    //   789: invokevirtual 329	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   792: pop
    //   793: aload 14
    //   795: ldc_w 486
    //   798: ldc_w 566
    //   801: invokevirtual 491	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   804: pop
    //   805: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   808: ifnull +11 -> 819
    //   811: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   814: aload 14
    //   816: invokevirtual 333	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   819: aload_0
    //   820: iconst_1
    //   821: putfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   824: ldc 31
    //   826: ldc_w 568
    //   829: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   832: pop
    //   833: aload_0
    //   834: iconst_0
    //   835: putfield 159	com/samsung/wfd/WFDSourceService:noError	Z
    //   838: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   841: invokevirtual 472	java/lang/Object:notifyAll	()V
    //   844: goto -779 -> 65
    //   847: getstatic 571	com/samsung/wfd/WfdEnums$WfdEvent:WEAK_NETWORK	Lcom/samsung/wfd/WfdEnums$WfdEvent;
    //   850: aload_1
    //   851: if_acmpne -786 -> 65
    //   854: ldc 31
    //   856: ldc_w 573
    //   859: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   862: pop
    //   863: new 322	android/content/Intent
    //   866: dup
    //   867: ldc_w 575
    //   870: invokespecial 324	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   873: astore 12
    //   875: aload 12
    //   877: ldc_w 325
    //   880: invokevirtual 329	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   883: pop
    //   884: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   887: aload 12
    //   889: invokevirtual 333	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   892: goto -827 -> 65
    //
    // Exception table:
    //   from	to	target	type
    //   34	108	103	finally
    //   111	892	103	finally
  }

  public IBinder onBind(Intent paramIntent)
  {
    Log.e("WFDSourceService", "Bind!");
    this.mFrequency = paramIntent.getIntExtra("freq", 0);
    Log.e("WFDSourceService", "freq:" + this.mFrequency);
    if (this.mPowerManager == null)
      this.mPowerManager = ((PowerManager)getSystemService("power"));
    if (this.mPowerManager != null)
    {
      Integer.parseInt(SystemProperties.get("wfd.cpu.freq", "0"));
      this.mCpuLockEnabled = true;
    }
    m_bAlive = true;
    mContext = getApplicationContext();
    this.mAudioManager = ((AudioManager)(AudioManager)mContext.getSystemService("audio"));
    if (aquireResources())
    {
      this.mWiFiLock = ((WifiManager)getSystemService("wifi")).createWifiLock("WFDSourceService");
      this.mWiFiLock.acquire();
      Log.d("WFDSourceService", "wifi lock acquire!");
    }
    WFDNative.listener = this;
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.setPriority(100);
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_UPDATE_INPUT_FROM_APP");
    localIntentFilter.addAction("com.samsung.wfd.LAUNCH_WFD_UPDATE");
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_REQ");
    localIntentFilter.addAction("com.samsung.wfd.WFD_SESSION_TEARDOWN");
    localIntentFilter.addAction("com.samsung.wfd.WFD_SESSION_START");
    localIntentFilter.addAction("com.samsung.wfd.WFD_SESSION_STOP");
    localIntentFilter.addAction("com.samsung.wfd.WFD_SESSION_PAUSE");
    localIntentFilter.addAction("com.samsung.wfd.WFD_SESSION_RESUME");
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_TCP_TRANSPORT");
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_UDP_TRANSPORT");
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_RESOLUTION");
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_BITRATE");
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_BUFFERING_DO");
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_BUFFERING_DONE");
    localIntentFilter.addAction("android.intent.action.SECURE_START");
    localIntentFilter.addAction("android.intent.action.SECURE_START_DONE");
    localIntentFilter.addAction("android.intent.action.SECURE_END");
    localIntentFilter.addAction("android.intent.action.SECURE_END_DONE");
    localIntentFilter.addAction("android.intent.action.HEADSET_PLUG");
    localIntentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_TCP_PLAYBACK_CONTROL");
    localIntentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
    localIntentFilter.addAction("android.intent.action.WIFI_DISPLAY_PLAYBACK_MODE");
    this.mReceiver = new WfdStateReceiver(null);
    mContext.registerReceiver(this.mReceiver, localIntentFilter);
    this.mWfdUibcManager = new WFDUibcManager(mContext);
    StartRTSP();
    return this.mWfdSourceBinder;
  }

  public void onCreate()
  {
    Log.d("WFDSourceService", "OnCreate thread");
    if (this.mCpuLockEnabled)
    {
      Log.e("WFDSourceService", "check cpuLock!!!");
      this.mCpuLockEnabled = false;
    }
    Log.d("WFDSourceService", "OnCreate thread=" + getMainLooper().getThread().getId());
  }

  public void onDestroy()
  {
    Log.d("WFDSourceService", "onDestroy");
    if (this.mCpuLockEnabled)
    {
      Log.e("WFDSourceService", "check cpuLock!!!");
      this.mCpuLockEnabled = false;
    }
  }

  public void onLowMemory()
  {
    Log.d("WFDSourceService", "onLowMemory!!!");
    m_bAlive = false;
    super.onLowMemory();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    Log.d("WFDSourceService", "onStart start id " + paramInt + ": " + paramIntent);
    super.onStart(paramIntent, paramInt);
  }

  public boolean onUnbind(Intent paramIntent)
  {
    Log.e("WFDSourceService", "unBind!!");
    getApplicationContext().unregisterReceiver(this.mReceiver);
    Log.d("WFDSourceService", "Wfd release DVFS_MIN_LIMIT");
    this.mCpuLockEnabled = false;
    synchronized (StaticLock)
    {
      if (this.mSessionId != -1)
      {
        WFDNative.stopWfdSession(this.mSessionId);
        this.mSessionId = -1;
      }
      WFDNative.disableWfd();
      if (this.mState != WfdEnums.SessionState.DISABLED)
      {
        sendBroadcastIntent("com.samsung.wfd.WFD_SESSION_TERMINATED");
        this.mState = WfdEnums.SessionState.DISABLED;
      }
      if (this.mWiFiLock.isHeld())
        this.mWiFiLock.release();
      Log.d("WFDSourceService", "wifi lock release!");
      m_bAlive = false;
      stopForegroundCompat();
      return super.onUnbind(paramIntent);
    }
  }

  public int pause(boolean paramBoolean)
  {
    Log.e("WFDSourceService", "Calling Pause for sid: " + this.mSessionId + " is Secure: " + paramBoolean);
    if (this.mSessionId != -1)
      WFDNative.pause(this.mSessionId, paramBoolean);
    return 0;
  }

  public int play(boolean paramBoolean)
  {
    Log.e("WFDSourceService", "Calling play()  for sid: " + this.mSessionId + " is Secure: " + paramBoolean);
    if (this.mSessionId != -1)
      WFDNative.play(this.mSessionId, paramBoolean);
    return 0;
  }

  public int setAvPlaybackMode(int paramInt)
  {
    Log.d("WFDSourceService", "setAvPlaybackMode mode: " + paramInt);
    while (true)
    {
      int k;
      int m;
      synchronized (StaticLock)
      {
        if ((this.mState != WfdEnums.SessionState.INVALID) && (this.mState != WfdEnums.SessionState.INITIALIZED))
          continue;
        int i = 0;
        Object localObject3 = WfdEnums.AVPlaybackMode.AUDIO_VIDEO;
        WfdEnums.AVPlaybackMode[] arrayOfAVPlaybackMode = WfdEnums.AVPlaybackMode.values();
        int j = arrayOfAVPlaybackMode.length;
        k = 0;
        if (k >= j)
          continue;
        WfdEnums.AVPlaybackMode localAVPlaybackMode = arrayOfAVPlaybackMode[k];
        if (paramInt == localAVPlaybackMode.ordinal())
        {
          localObject3 = localAVPlaybackMode;
          i = 1;
          if (i != 0)
            continue;
          Log.e("WFDSourceService", "Invalid AV playback mode:" + paramInt);
          m = WfdEnums.ErrorType.INVALID_ARG.getCode();
          continue;
          if (!WFDNative.setAvPlaybackMode(paramInt))
            continue;
          PLAYBACK_MODE = (WfdEnums.AVPlaybackMode)localObject3;
          m = 0;
        }
      }
      k++;
    }
  }

  public int setBufferingMode(int paramInt)
  {
    synchronized (StaticLock)
    {
      WFDNative.setBuffering(paramInt);
      return 0;
    }
  }

  // ERROR //
  public int setRtpTransport(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   6: astore 6
    //   8: aload 6
    //   10: monitorenter
    //   11: iconst_0
    //   12: istore 7
    //   14: aload_0
    //   15: getfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   18: pop
    //   19: aload_0
    //   20: getfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   23: getstatic 268	com/samsung/wfd/WfdEnums$SessionState:PLAY	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   26: if_acmpne +43 -> 69
    //   29: aload_0
    //   30: iconst_1
    //   31: invokespecial 218	com/samsung/wfd/WFDSourceService:internalPause	(Z)I
    //   34: istore 7
    //   36: ldc 31
    //   38: new 376	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 377	java/lang/StringBuilder:<init>	()V
    //   45: ldc_w 379
    //   48: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: iload 7
    //   53: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   56: ldc_w 815
    //   59: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   68: pop
    //   69: iload 7
    //   71: ifeq +13 -> 84
    //   74: aload 6
    //   76: monitorexit
    //   77: iload 7
    //   79: istore 5
    //   81: goto +168 -> 249
    //   84: aload_0
    //   85: iconst_0
    //   86: putfield 146	com/samsung/wfd/WFDSourceService:IS_RTP_TRANSPORT_NEGOTIATED	Z
    //   89: iload_1
    //   90: iload_2
    //   91: iload_3
    //   92: invokestatic 819	com/samsung/wfd/WFDNative:negotiateRtpTransport	(III)V
    //   95: ldc 31
    //   97: ldc_w 821
    //   100: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   103: pop
    //   104: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   107: ldc2_w 272
    //   110: invokevirtual 277	java/lang/Object:wait	(J)V
    //   113: aload_0
    //   114: getfield 146	com/samsung/wfd/WFDSourceService:IS_RTP_TRANSPORT_NEGOTIATED	Z
    //   117: ifne +86 -> 203
    //   120: aload_0
    //   121: iconst_1
    //   122: invokespecial 222	com/samsung/wfd/WFDSourceService:internalPlay	(Z)I
    //   125: istore 15
    //   127: ldc 31
    //   129: new 376	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 377	java/lang/StringBuilder:<init>	()V
    //   136: ldc_w 823
    //   139: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: iload 15
    //   144: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   147: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   150: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   153: pop
    //   154: getstatic 297	com/samsung/wfd/WfdEnums$ErrorType:UNKNOWN	Lcom/samsung/wfd/WfdEnums$ErrorType;
    //   157: invokevirtual 289	com/samsung/wfd/WfdEnums$ErrorType:getCode	()I
    //   160: istore 5
    //   162: aload 6
    //   164: monitorexit
    //   165: goto +84 -> 249
    //   168: astore 8
    //   170: aload 6
    //   172: monitorexit
    //   173: aload 8
    //   175: athrow
    //   176: astore 10
    //   178: ldc 31
    //   180: ldc_w 825
    //   183: aload 10
    //   185: invokestatic 294	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   188: pop
    //   189: getstatic 297	com/samsung/wfd/WfdEnums$ErrorType:UNKNOWN	Lcom/samsung/wfd/WfdEnums$ErrorType;
    //   192: invokevirtual 289	com/samsung/wfd/WfdEnums$ErrorType:getCode	()I
    //   195: istore 5
    //   197: aload 6
    //   199: monitorexit
    //   200: goto +49 -> 249
    //   203: aload_0
    //   204: iconst_0
    //   205: putfield 146	com/samsung/wfd/WFDSourceService:IS_RTP_TRANSPORT_NEGOTIATED	Z
    //   208: iload_1
    //   209: invokestatic 827	com/samsung/wfd/WFDNative:setRtpTransport	(I)V
    //   212: aload_0
    //   213: iconst_1
    //   214: invokespecial 222	com/samsung/wfd/WFDSourceService:internalPlay	(Z)I
    //   217: istore 13
    //   219: ldc 31
    //   221: new 376	java/lang/StringBuilder
    //   224: dup
    //   225: invokespecial 377	java/lang/StringBuilder:<init>	()V
    //   228: ldc_w 397
    //   231: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: iload 13
    //   236: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   239: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   245: pop
    //   246: aload 6
    //   248: monitorexit
    //   249: iload 5
    //   251: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   14	95	168	finally
    //   95	113	168	finally
    //   113	173	168	finally
    //   178	249	168	finally
    //   95	113	176	java/lang/InterruptedException
  }

  public int setSurface(Surface paramSurface)
  {
    synchronized (StaticLock)
    {
      WFDNative.setVideoSurface(paramSurface);
      return 0;
    }
  }

  public void sinkResponse(WfdEnums.WfdEvent paramWfdEvent, String paramString1, String paramString2)
  {
    Log.d("WFDSourceService", "sinkResponse() url " + paramString1);
    synchronized (StaticLock)
    {
      this.mUpdateURL = paramString1;
      Intent localIntent = new Intent("android.intent.action.WIFI_DISPLAY_URL_FROM_NATIVE");
      localIntent.addFlags(134217728);
      localIntent.putExtra("URL", paramString1);
      mContext.sendBroadcast(localIntent);
      Log.d("WFDSourceService", "WFD noti to App - Dongle update URL");
      return;
    }
  }

  public int standby()
  {
    Log.d("WFDSourceService", "standby");
    if (this.mSessionId != -1)
      WFDNative.standby(this.mSessionId);
    return 0;
  }

  public int stop()
  {
    Log.d("WFDSourceService", "stop called for session: " + this.mSessionId);
    if (this.mSessionId != -1)
    {
      WFDNative.stopWfdSession(this.mSessionId);
      this.mSessionId = -1;
    }
    this.mState = WfdEnums.SessionState.STOPPED;
    return 0;
  }

  public int tcpPlaybackControl(int paramInt1, int paramInt2)
  {
    Object localObject1 = StaticLock;
    monitorenter;
    int i = 0;
    while (true)
    {
      int k;
      int m;
      try
      {
        WfdEnums.ControlCmdType[] arrayOfControlCmdType = WfdEnums.ControlCmdType.values();
        int j = arrayOfControlCmdType.length;
        k = 0;
        if (k >= j)
          continue;
        if (paramInt1 != arrayOfControlCmdType[k].ordinal())
          break label111;
        i = 1;
        if (i != 0)
          continue;
        Log.e("WFDSourceService", "Invalid control cmd type:" + paramInt1);
        m = WfdEnums.ErrorType.INVALID_ARG.getCode();
        monitorexit;
        break label108;
        WFDNative.tcpPlaybackControl(paramInt1, paramInt2);
        m = 0;
        monitorexit;
      }
      finally
      {
        localObject2 = finally;
        monitorexit;
        throw localObject2;
      }
      label108: return m;
      label111: k++;
    }
  }

  public int teardown()
  {
    int i = 0;
    Log.e("WFDSourceService", "teardown()  for sid: " + this.mSessionId + " in state " + this.mState);
    while (true)
    {
      synchronized (StaticLock)
      {
        if (!this.uibcEnabled)
          continue;
        this.mWfdUibcManager.stop(this.mSessionId);
        if ((this.mState == WfdEnums.SessionState.PLAY) || (this.mState == WfdEnums.SessionState.PAUSE) || (this.mState == WfdEnums.SessionState.ESTABLISHING) || (this.mState == WfdEnums.SessionState.ESTABLISHED))
        {
          WFDNative.teardown(this.mSessionId, true);
          try
          {
            WfdEnums.SessionState localSessionState = this.mState;
            int j = 10;
            int k = j - 1;
            if ((j == 0) || (this.mState != localSessionState))
              continue;
            StaticLock.wait(500L);
            j = k;
            continue;
            if (this.mState != localSessionState)
              continue;
            Log.e("WFDSourceService", "Teardown timed out, trying local teardown");
            WFDNative.teardown(this.mSessionId, false);
            int m = WfdEnums.ErrorType.OPERATION_TIMED_OUT.getCode();
            i = m;
          }
          catch (InterruptedException localInterruptedException)
          {
            Log.e("WFDSourceService", "Teardown wait interrupted");
          }
        }
      }
      if (this.mSessionId == -1)
      {
        if (this.mState == WfdEnums.SessionState.INVALID)
        {
          sendBroadcastIntent("com.samsung.wfd.WFD_SESSION_TERMINATED");
          this.mState = WfdEnums.SessionState.DISABLED;
          continue;
        }
        if (this.mState != WfdEnums.SessionState.ENABLED)
          continue;
        WFDNative.teardown(this.mSessionId, false);
        continue;
      }
      if (this.mState == WfdEnums.SessionState.TEARDOWN)
        continue;
      WFDNative.teardown(this.mSessionId, false);
      this.mState = WfdEnums.SessionState.TEARDOWN;
    }
    return i;
  }

  void teardownForAudioOut()
  {
    Log.d("WFDSourceService", "teardownForAudioOut");
    if (this.mPrevConnectionType == WfdEnums.ConnectionType.TCP)
    {
      int i = setRtpTransport(0, 0, 19000, false);
      Log.d("WFDSourceService", "teardownForAudioOut, setRtpTransport returned: " + i);
      if (i >= 0)
      {
        this.mPrevConnectionType = WfdEnums.ConnectionType.UDP;
        sendBroadcastIntent("android.intent.action.WIFIDISPLAY_NOTI_CONNECTION_MODE", 0);
      }
    }
    Intent localIntent = new Intent("android.intent.action.WFD_TEARDOWN_FOR_AUDIO_OUT");
    localIntent.addFlags(134217728);
    if (mContext != null)
      mContext.sendBroadcast(localIntent);
    if (this.mCountdown != null)
      this.mCountdown.cancel();
  }

  public void updateEvent(WfdEnums.WfdEvent paramWfdEvent, String paramString1, String paramString2)
  {
    Log.d("WFDSourceService", "updateEvent   = " + paramWfdEvent + " timestamp = " + paramString1 + " length = " + paramString2);
  }

  // ERROR //
  public void updateState(WfdEnums.SessionState paramSessionState, int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: ldc 31
    //   2: ldc_w 910
    //   5: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   8: pop
    //   9: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   12: astore 7
    //   14: aload 7
    //   16: monitorenter
    //   17: aload_1
    //   18: aload_0
    //   19: getfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   22: if_acmpne +26 -> 48
    //   25: iload_2
    //   26: aload_0
    //   27: getfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   30: if_icmpne +18 -> 48
    //   33: ldc 31
    //   35: ldc_w 912
    //   38: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   41: pop
    //   42: aload 7
    //   44: monitorexit
    //   45: goto +537 -> 582
    //   48: aload_0
    //   49: aload_1
    //   50: putfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   53: getstatic 89	com/samsung/wfd/WFDSourceService:StaticLock	Ljava/lang/Object;
    //   56: invokevirtual 472	java/lang/Object:notifyAll	()V
    //   59: iload_2
    //   60: aload_0
    //   61: getfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   64: if_icmpeq +82 -> 146
    //   67: ldc 31
    //   69: new 376	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 377	java/lang/StringBuilder:<init>	()V
    //   76: ldc_w 914
    //   79: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: aload_0
    //   83: getfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   86: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   89: ldc_w 916
    //   92: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: iload_2
    //   96: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   99: ldc_w 918
    //   102: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: aload_1
    //   106: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   115: pop
    //   116: aload_1
    //   117: getstatic 308	com/samsung/wfd/WfdEnums$SessionState:PAUSE	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   120: if_acmpne +65 -> 185
    //   123: ldc 31
    //   125: ldc_w 920
    //   128: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   131: pop
    //   132: aload 7
    //   134: monitorexit
    //   135: goto +447 -> 582
    //   138: astore 8
    //   140: aload 7
    //   142: monitorexit
    //   143: aload 8
    //   145: athrow
    //   146: ldc 31
    //   148: new 376	java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial 377	java/lang/StringBuilder:<init>	()V
    //   155: ldc_w 922
    //   158: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: iload_2
    //   162: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   165: ldc_w 918
    //   168: invokevirtual 383	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: aload_1
    //   172: invokevirtual 519	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   181: pop
    //   182: goto -66 -> 116
    //   185: aload_1
    //   186: getstatic 268	com/samsung/wfd/WfdEnums$SessionState:PLAY	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   189: if_acmpne +15 -> 204
    //   192: ldc 31
    //   194: ldc_w 924
    //   197: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   200: pop
    //   201: goto -69 -> 132
    //   204: aload_1
    //   205: getstatic 871	com/samsung/wfd/WfdEnums$SessionState:ESTABLISHING	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   208: if_acmpne +58 -> 266
    //   211: new 322	android/content/Intent
    //   214: dup
    //   215: ldc_w 926
    //   218: invokespecial 324	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   221: astore 10
    //   223: aload 10
    //   225: ldc_w 325
    //   228: invokevirtual 329	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   231: pop
    //   232: aload 10
    //   234: ldc 17
    //   236: iload_2
    //   237: invokevirtual 341	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   240: pop
    //   241: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   244: aload 10
    //   246: invokevirtual 333	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   249: aload_0
    //   250: iload_2
    //   251: putfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   254: ldc 31
    //   256: ldc_w 928
    //   259: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   262: pop
    //   263: goto -131 -> 132
    //   266: aload_1
    //   267: getstatic 882	com/samsung/wfd/WfdEnums$SessionState:TEARDOWN	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   270: if_acmpne +135 -> 405
    //   273: ldc 31
    //   275: ldc_w 930
    //   278: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   281: pop
    //   282: aload_0
    //   283: getfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   286: ifne +69 -> 355
    //   289: ldc 31
    //   291: ldc_w 521
    //   294: invokestatic 260	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   297: pop
    //   298: new 322	android/content/Intent
    //   301: dup
    //   302: ldc_w 484
    //   305: invokespecial 324	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   308: astore 26
    //   310: aload 26
    //   312: ldc_w 325
    //   315: invokevirtual 329	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   318: pop
    //   319: aload 26
    //   321: ldc_w 486
    //   324: ldc_w 561
    //   327: invokevirtual 491	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   330: pop
    //   331: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   334: ifnull +11 -> 345
    //   337: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   340: aload 26
    //   342: invokevirtual 333	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   345: aload_0
    //   346: iconst_1
    //   347: putfield 161	com/samsung/wfd/WFDSourceService:teardownFromApp	Z
    //   350: aload_0
    //   351: iconst_0
    //   352: putfield 159	com/samsung/wfd/WFDSourceService:noError	Z
    //   355: iload_2
    //   356: bipush 255
    //   358: if_icmpeq +21 -> 379
    //   361: iload_2
    //   362: invokestatic 762	com/samsung/wfd/WFDNative:stopWfdSession	(I)V
    //   365: iload_2
    //   366: aload_0
    //   367: getfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   370: if_icmpne +9 -> 379
    //   373: aload_0
    //   374: bipush 255
    //   376: putfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   379: aload_0
    //   380: getfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   383: getstatic 467	com/samsung/wfd/WfdEnums$SessionState:DISABLED	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   386: if_acmpeq -254 -> 132
    //   389: ldc_w 469
    //   392: invokestatic 185	com/samsung/wfd/WFDSourceService:sendBroadcastIntent	(Ljava/lang/String;)V
    //   395: aload_0
    //   396: getstatic 467	com/samsung/wfd/WfdEnums$SessionState:DISABLED	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   399: putfield 110	com/samsung/wfd/WFDSourceService:mState	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   402: goto -270 -> 132
    //   405: aload_1
    //   406: getstatic 305	com/samsung/wfd/WfdEnums$SessionState:ESTABLISHED	Lcom/samsung/wfd/WfdEnums$SessionState;
    //   409: if_acmpne -277 -> 132
    //   412: aload_0
    //   413: iload_2
    //   414: putfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   417: new 322	android/content/Intent
    //   420: dup
    //   421: ldc_w 932
    //   424: invokespecial 324	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   427: astore 14
    //   429: aload 14
    //   431: ldc_w 325
    //   434: invokevirtual 329	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   437: pop
    //   438: aload 14
    //   440: ldc 17
    //   442: iload_2
    //   443: invokevirtual 341	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   446: pop
    //   447: aload 14
    //   449: ldc_w 934
    //   452: iload_3
    //   453: invokevirtual 341	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   456: pop
    //   457: aload_0
    //   458: iload_3
    //   459: putfield 148	com/samsung/wfd/WFDSourceService:mAudioMode	I
    //   462: aload 4
    //   464: ifnull +14 -> 478
    //   467: aload 14
    //   469: ldc_w 936
    //   472: aload 4
    //   474: invokevirtual 491	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   477: pop
    //   478: aload_0
    //   479: getfield 134	com/samsung/wfd/WFDSourceService:mUpdateURL	Ljava/lang/String;
    //   482: ifnull +21 -> 503
    //   485: aload 14
    //   487: ldc_w 841
    //   490: aload_0
    //   491: getfield 134	com/samsung/wfd/WFDSourceService:mUpdateURL	Ljava/lang/String;
    //   494: invokevirtual 491	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   497: pop
    //   498: aload_0
    //   499: aconst_null
    //   500: putfield 134	com/samsung/wfd/WFDSourceService:mUpdateURL	Ljava/lang/String;
    //   503: aload 5
    //   505: ifnull +14 -> 519
    //   508: aload 14
    //   510: ldc_w 938
    //   513: aload 5
    //   515: invokevirtual 491	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   518: pop
    //   519: aload 14
    //   521: ldc_w 940
    //   524: aload_0
    //   525: getfield 144	com/samsung/wfd/WFDSourceService:IS_HDCP_ENABLED	Z
    //   528: invokevirtual 943	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   531: pop
    //   532: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   535: ifnull +11 -> 546
    //   538: getstatic 93	com/samsung/wfd/WFDSourceService:mContext	Landroid/content/Context;
    //   541: aload 14
    //   543: invokevirtual 333	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   546: ldc 31
    //   548: ldc_w 945
    //   551: invokestatic 176	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   554: pop
    //   555: aload_0
    //   556: getfield 136	com/samsung/wfd/WFDSourceService:uibcEnabled	Z
    //   559: ifne +15 -> 574
    //   562: aload_0
    //   563: getfield 440	com/samsung/wfd/WFDSourceService:mWfdUibcManager	Lcom/samsung/wfd/WFDUibcManager;
    //   566: aload_0
    //   567: getfield 112	com/samsung/wfd/WFDSourceService:mSessionId	I
    //   570: invokevirtual 445	com/samsung/wfd/WFDUibcManager:start	(I)Z
    //   573: pop
    //   574: aload_0
    //   575: iconst_1
    //   576: putfield 136	com/samsung/wfd/WFDSourceService:uibcEnabled	Z
    //   579: goto -447 -> 132
    //   582: return
    //
    // Exception table:
    //   from	to	target	type
    //   17	143	138	finally
    //   146	579	138	finally
  }

  private class WfdStateReceiver extends BroadcastReceiver
  {
    private WfdStateReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      while (true)
      {
        String str1;
        int i11;
        int i12;
        int i10;
        int i9;
        int i8;
        int i7;
        int i5;
        int i6;
        int i2;
        int i3;
        int i4;
        int m;
        int n;
        int i1;
        int j;
        int k;
        int i;
        synchronized (WFDSourceService.StaticLock)
        {
          str1 = paramIntent.getAction();
          Log.d("WFDSourceService", "intent recieved " + str1);
          if (!str1.equals("android.intent.action.WIFI_DISPLAY_REQ"))
            continue;
          String str2 = paramIntent.getStringExtra("Control");
          if ((str2 != null) && (str2.equals("terminate")))
            continue;
          i11 = paramIntent.getIntExtra("res", 0);
          String str3 = paramIntent.getStringExtra("3D");
          Log.d("WFDSourceService", "Recvd resolution from app:" + i11 + " 3d:" + str3);
          if ((str3 == null) || (!str3.equals("Yes")))
            continue;
          i12 = 1;
          break label2140;
          Log.d("WFDSourceService", "Choosing the width:" + i13 + " height:" + i14);
          WFDNative.changeParams(0, i13, i14, i12);
        }
        label2140: if ((i11 & 0x1) == 0)
          continue;
        int i13 = 640;
        int i14 = 480;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WFDSourceService
 * JD-Core Version:    0.6.0
 */