package com.samsung.wfd;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;

public class WFDNative
{
  private static final String TAG = "WFDNative_JAVA";
  public static WfdActionListener listener;
  private static String localIpAddress;
  private static String localMacAddress;

  static
  {
    System.loadLibrary("uibc");
    Log.d("WFDNative_JAVA", "try to load libwfdrtsp.so");
    System.loadLibrary("wfdrtsp");
    Log.d("WFDNative_JAVA", "libwfdrtsp.so loaded.");
    Log.d("WFDNative_JAVA", "try to load libwfdnative.so");
    System.loadLibrary("wfdnative");
    Log.d("WFDNative_JAVA", "libwfdnative.so loaded.");
    localMacAddress = "00:00:00:00:00:00";
    localIpAddress = "";
  }

  public static native boolean WFDGetSubtitleStatus();

  public static native boolean WFDGetSuspendStatus();

  public static native boolean WFDPostSubtitle(String paramString, int paramInt);

  public static native boolean WFDPostSuspend(String paramString);

  public static native boolean WFDSetSubtitleStatus(boolean paramBoolean);

  public static native boolean WFDSetSuspendStatus(boolean paramBoolean);

  public static native void audioReroute(int paramInt);

  public static native boolean changeParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public static native void disableUIBC(int paramInt);

  public static native boolean disableWfd();

  public static native void enableUIBC(int paramInt);

  public static native boolean enableWfd(WfdDevice paramWfdDevice);

  public static void eventCallback(String paramString, Object[] paramArrayOfObject)
  {
    Log.d("WFDNative_JAVA", "eventCallback triggered");
    if ((paramString == null) || (paramArrayOfObject == null))
      Log.d("WFDNative_JAVA", "No event info, ignore.");
    while (true)
    {
      return;
      int i = paramArrayOfObject.length;
      Log.d("WFDNative_JAVA", "CallbackEvent \"" + paramString + "\" --- objectArray length=" + i);
      for (int j = 0; j < i; j++)
        Log.d("WFDNative_JAVA", "\tobjectArray[" + j + "] = " + paramArrayOfObject[j].toString());
      if (listener == null)
      {
        Log.d("WFDNative_JAVA", "Listener is destroyed, can't notify");
        continue;
      }
      if ("Error".equalsIgnoreCase(paramString))
      {
        Log.e("WFDNative_JAVA", "eventName: " + paramString);
        if (paramArrayOfObject.length <= 0)
          continue;
        String str15 = paramArrayOfObject[0].toString();
        if ("ERROR_CMD".equalsIgnoreCase(str15))
        {
          listener.notifyEvent(WfdEnums.WfdEvent.ERROR, 0);
          continue;
        }
        if (!"RTSPCloseCallback".equalsIgnoreCase(str15))
          continue;
        Log.d("WFDNative_JAVA", "RTSP close callback, treat as TEARDOWN start");
        listener.notifyEvent(WfdEnums.WfdEvent.TEARDOWN_START, -1);
        continue;
      }
      if (paramString.compareToIgnoreCase("ServiceStateChanged") == 0)
      {
        if (paramArrayOfObject[0].toString().compareToIgnoreCase("enabled") == 0);
        for (boolean bool = true; ; bool = false)
        {
          Log.d("WFDNative_JAVA", paramString + " WFD service isActive:" + bool);
          if (!bool)
            break label351;
          listener.notifyEvent(WfdEnums.WfdEvent.WFD_SERVICE_ENABLED, 0);
          Log.d("WFDNative_JAVA", paramString + " WFD_SERVICE_ENABLED");
          break;
        }
        label351: listener.notifyEvent(WfdEnums.WfdEvent.WFD_SERVICE_DISABLED, 0);
        Log.d("WFDNative_JAVA", paramString + " WFD_SERVICE_DISABLED");
        continue;
      }
      if (paramString.compareToIgnoreCase("SessionStateChanged") == 0)
      {
        String str10 = paramArrayOfObject[0].toString();
        int n = 0;
        if (i > 2)
          n = Integer.parseInt((String)paramArrayOfObject[(i - 1)]);
        Log.d("WFDNative_JAVA", "Event: " + paramString + " SessionState: " + str10);
        if ("STANDBY".equalsIgnoreCase(str10))
        {
          listener.notifyEvent(WfdEnums.WfdEvent.STANDBY_START, n);
          continue;
        }
        if ("STOPPED".equalsIgnoreCase(str10))
        {
          listener.updateState(WfdEnums.SessionState.TEARDOWN, n, 0, null, null);
          continue;
        }
        if ("ESTABLISHING".equalsIgnoreCase(str10))
        {
          listener.updateState(WfdEnums.SessionState.ESTABLISHING, n, 0, null, null);
          continue;
        }
        if ("ESTABLISHED".equalsIgnoreCase(str10))
        {
          Log.d("WFDNative_JAVA", "WFD client connected");
          int i1 = Integer.parseInt((String)paramArrayOfObject[1]);
          String str13 = paramArrayOfObject[2].toString();
          String str14 = paramArrayOfObject[3].toString();
          listener.updateState(WfdEnums.SessionState.ESTABLISHED, n, i1, str13, str14);
          continue;
        }
        if ("ERROR".equalsIgnoreCase(str10))
        {
          Log.d("WFDNative_JAVA", "WFD client ERROR");
          listener.notifyEvent(WfdEnums.WfdEvent.ERROR, n);
          continue;
        }
        if (!"SINK_RESPOND".equalsIgnoreCase(str10))
          continue;
        String str11 = paramArrayOfObject[1].toString();
        String str12 = paramArrayOfObject[2].toString();
        Log.d("WFDNative_JAVA", "Event: " + str10 + " url:  " + str11 + " ver: " + str12);
        listener.sinkResponse(WfdEnums.WfdEvent.SINK_RESPOND, str11, str12);
        continue;
      }
      if ("StreamControlCompleted".equalsIgnoreCase(paramString))
      {
        Log.d("WFDNative_JAVA", paramString);
        if (paramArrayOfObject.length < 2)
          continue;
        String str9 = (String)paramArrayOfObject[1];
        int m = Integer.parseInt((String)paramArrayOfObject[0]);
        if ("PLAY".equalsIgnoreCase(str9))
        {
          listener.notifyEvent(WfdEnums.WfdEvent.PLAY_START, m);
          continue;
        }
        if ("PLAY_DONE".equalsIgnoreCase(str9))
        {
          listener.updateState(WfdEnums.SessionState.PLAY, m, 0, null, null);
          continue;
        }
        if ("PAUSE".equalsIgnoreCase(str9))
        {
          listener.notifyEvent(WfdEnums.WfdEvent.PAUSE_START, m);
          continue;
        }
        if ("PAUSE_DONE".equalsIgnoreCase(str9))
        {
          listener.updateState(WfdEnums.SessionState.PAUSE, m, 0, null, null);
          continue;
        }
        if (!"TEARDOWN".equalsIgnoreCase(str9))
          continue;
        listener.notifyEvent(WfdEnums.WfdEvent.TEARDOWN_START, m);
        continue;
      }
      if ("UIBCControlCompleted".equalsIgnoreCase(paramString))
      {
        if (paramArrayOfObject.length < 2)
          continue;
        int k = Integer.parseInt((String)paramArrayOfObject[0]);
        if ("ENABLED".equalsIgnoreCase((String)paramArrayOfObject[1]))
        {
          listener.notifyEvent(WfdEnums.WfdEvent.UIBC_ENABLED, k);
          Log.d("WFDNative_JAVA", paramString + " ENABLED");
          continue;
        }
        listener.notifyEvent(WfdEnums.WfdEvent.UIBC_DISABLED, k);
        Log.d("WFDNative_JAVA", paramString + " DISABLED");
        continue;
      }
      if ("MMEvent".equalsIgnoreCase(paramString))
      {
        if (paramArrayOfObject.length < 2)
          continue;
        String str7 = (String)paramArrayOfObject[0];
        String str8 = (String)paramArrayOfObject[1];
        if ("HDCP_CONNECT".equalsIgnoreCase(str7))
        {
          if ("SUCCESS".equalsIgnoreCase(str8))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.HDCP_CONNECT_SUCCESS, -1);
            continue;
          }
          listener.notifyEvent(WfdEnums.WfdEvent.HDCP_CONNECT_FAIL, -1);
          continue;
        }
        if ((!"MM_RTP_EVENT".equalsIgnoreCase(str7)) || (!"FAIL".equalsIgnoreCase(str8)))
          continue;
        listener.notifyEvent(WfdEnums.WfdEvent.MM_RTP_EVENT_FAILURE, -1);
        continue;
      }
      if ("VideoEvent".equalsIgnoreCase(paramString))
      {
        if (paramArrayOfObject.length >= 1)
        {
          String str6 = (String)paramArrayOfObject[0];
          if ("RuntimeError".equalsIgnoreCase(str6))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.VIDEO_RUNTIME_ERROR, -1);
            continue;
          }
          if ("ConfigureFailure".equalsIgnoreCase(str6))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.VIDEO_CONFIGURE_FAILURE, -1);
            continue;
          }
          Log.d("WFDNative_JAVA", "Unknown description:" + str6);
          continue;
        }
        Log.e("WFDNative_JAVA", "No description for VideoEvent");
        continue;
      }
      if ("AudioEvent".equalsIgnoreCase(paramString))
      {
        if (paramArrayOfObject.length >= 1)
        {
          String str5 = (String)paramArrayOfObject[0];
          if ("RuntimeError".equalsIgnoreCase(str5))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.AUDIO_RUNTIME_ERROR, -1);
            continue;
          }
          if ("ConfigureFailure".equalsIgnoreCase(str5))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.AUDIO_CONFIGURE_FAILURE, -1);
            continue;
          }
          if ("AudioProxyOpened".equalsIgnoreCase(str5))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.AUDIOPROXY_OPENED, -1);
            continue;
          }
          if ("AudioProxyClosed".equalsIgnoreCase(str5))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.AUDIOPROXY_CLOSED, -1);
            continue;
          }
          Log.e("WFDNative_JAVA", "Unknown description:" + str5);
          continue;
        }
        Log.e("WFDNative_JAVA", "No description for AudioEvent");
        continue;
      }
      if ("HdcpEvent".equalsIgnoreCase(paramString))
      {
        if (paramArrayOfObject.length >= 1)
        {
          String str4 = (String)paramArrayOfObject[0];
          if ("RuntimeError".equalsIgnoreCase(str4))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.HDCP_RUNTIME_ERROR, -1);
            continue;
          }
          Log.e("WFDNative_JAVA", "Unknown description:" + str4);
          continue;
        }
        Log.e("WFDNative_JAVA", "No description for AudioEvent");
        continue;
      }
      if ("NetworkEvent".equalsIgnoreCase(paramString))
      {
        if (paramArrayOfObject.length >= 1)
        {
          String str1 = (String)paramArrayOfObject[0];
          if ("RuntimeError".equalsIgnoreCase(str1))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.NETWORK_RUNTIME_ERROR, -1);
            continue;
          }
          if ("ConfigureFailure".equalsIgnoreCase(str1))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.NETWORK_CONFIGURE_FAILURE, -1);
            continue;
          }
          if ("RtpTransportNegotiationSuccess".equalsIgnoreCase(str1))
          {
            listener.notifyEvent(WfdEnums.WfdEvent.RTP_TRANSPORT_NEGOTIATED, -1);
            continue;
          }
          if (("BufferingUpdate".equalsIgnoreCase(str1)) && (paramArrayOfObject.length >= 3))
          {
            String str2 = paramArrayOfObject[2].toString();
            String str3 = paramArrayOfObject[1].toString();
            listener.updateEvent(WfdEnums.WfdEvent.BUFFER_UPDATE_EVENT, str2, str3);
            continue;
          }
          if ("PlaybackControl".equalsIgnoreCase(str1))
          {
            ((String)paramArrayOfObject[1]);
            ((String)paramArrayOfObject[2]);
            continue;
          }
          Log.e("WFDNative_JAVA", "Unknown description:" + str1);
          continue;
        }
        Log.e("WFDNative_JAVA", "No description for NetworkEvent");
        continue;
      }
      if ("WeakNetwork".equalsIgnoreCase(paramString))
      {
        Log.d("WFDNative_JAVA", paramArrayOfObject[0].toString());
        listener.notifyEvent(WfdEnums.WfdEvent.WEAK_NETWORK, 0);
        continue;
      }
      Log.e("WFDNative_JAVA", "Receive unrecognized event from WFDNative.cpp: " + paramString);
    }
  }

  public static native void eventWiFiStateChanged();

  public static native void executeRuntimeCommand(int paramInt);

  public static native int getEngineStatus();

  public static String getIpAddress(String paramString)
  {
    String str = "0.0.0.0";
    Log.d("WFDNative_JAVA", "getIpAddress() called.");
    Log.d("WFDNative_JAVA", "peerIP = " + str);
    if (0 >= 0)
      Log.d("WFDNative_JAVA", "ip info in deviceList = ");
    if (((str.length() <= 0) || (0 < 0)) || (str.length() > 0));
    while (true)
    {
      return str;
      if (0 >= 0)
        continue;
      str = "";
    }
  }

  public static int getLinkSpeed()
  {
    return 0;
  }

  public static String getLocalIpAddress()
  {
    if (localIpAddress.length() == 0)
      localIpAddress = "192.168.49.1";
    return localIpAddress;
  }

  public static int getWfdDeviceCoupleSinkStatusBitmap(String paramString)
  {
    return 0;
  }

  public static long getWfdDeviceInfoBitmap(String paramString)
  {
    return 0L;
  }

  public static int getWfdDeviceMaxThroughput(String paramString)
  {
    return 0;
  }

  public static int getWfdDeviceRtspPort(String paramString)
  {
    return 854;
  }

  public static native int[] getWfdIeInfo();

  public static native void handleDown(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3);

  public static native void handleMove(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3);

  public static native void handleUp(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3);

  public static native boolean isActiveUIBC();

  public static native void keyDown(int paramInt1, int paramInt2);

  public static native void keyUp(int paramInt1, int paramInt2);

  public static native void negotiateRtpTransport(int paramInt1, int paramInt2, int paramInt3);

  public static native void pause(int paramInt, boolean paramBoolean);

  public static native void play(int paramInt, boolean paramBoolean);

  public static native void sendUIBCKeyEvent(KeyEvent paramKeyEvent);

  public static native void sendUIBCMotionEvent(MotionEvent paramMotionEvent);

  public static native boolean sendUpdateUserInput(int paramInt);

  public static native boolean setAvPlaybackMode(int paramInt);

  public static native void setBitrate(int paramInt);

  public static native void setBuffering(int paramInt);

  public static native void setCapability(int paramInt, String paramString);

  public static native void setResolution(int paramInt1, int paramInt2);

  public static native void setRtpTransport(int paramInt);

  public static native void setVideoSurface(Surface paramSurface);

  public static native void standby(int paramInt);

  public static native void startUIBC(Object paramObject);

  public static native void startWfdSession(WfdDevice paramWfdDevice);

  public static native void stopUIBC();

  public static native void stopWfdSession(int paramInt);

  public static native void tcpPlaybackControl(int paramInt1, int paramInt2);

  public static native void teardown(int paramInt, boolean paramBoolean);

  public static abstract interface WfdActionListener
  {
    public abstract void notifyEvent(WfdEnums.WfdEvent paramWfdEvent, int paramInt);

    public abstract void sinkResponse(WfdEnums.WfdEvent paramWfdEvent, String paramString1, String paramString2);

    public abstract void updateEvent(WfdEnums.WfdEvent paramWfdEvent, String paramString1, String paramString2);

    public abstract void updateState(WfdEnums.SessionState paramSessionState, int paramInt1, int paramInt2, String paramString1, String paramString2);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.wfd.WFDNative
 * JD-Core Version:    0.6.0
 */