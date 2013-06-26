package com.samsung.commonimsservice;

import com.sec.android.internal.ims.IIMSParams;

public abstract interface IIMSCallStateListener
{
  public static final int CALL_TYPE_AUDIO = 2;
  public static final int CALL_TYPE_HDVIDEO = 5;
  public static final int CALL_TYPE_QCIFVIDEO = 1;
  public static final int CALL_TYPE_QVGAVIDEO = 8;
  public static final int CALL_TYPE_QVGA_PORTRAIT = 9;
  public static final int CALL_TYPE_VIDEO_CONFERENCE = 7;
  public static final int CALL_TYPE_VIDEO_SHARE = 3;
  public static final int CAPTURE_TYPE_FAR_END = 1;
  public static final int CAPTURE_TYPE_NEAR_END = 0;
  public static final int INBOUND_ONLY_VIDEO_CALL = 4;
  public static final int INVALID_DATA = -1;

  public abstract void onCallBusy(int paramInt);

  public abstract void onCallEnded(int paramInt);

  public abstract void onCallEnded(int paramInt1, int paramInt2);

  public abstract void onCallEstablished(int paramInt1, int paramInt2, IIMSParams paramIIMSParams);

  public abstract void onCallHeld(int paramInt);

  public abstract void onCallResumed(int paramInt);

  public abstract void onCallSwitched(int paramInt1, int paramInt2, IIMSParams paramIIMSParams);

  public abstract void onCalling(int paramInt);

  public abstract void onCameraEvent(int paramInt, boolean paramBoolean);

  public abstract void onCameraStartFailure(int paramInt);

  public abstract void onCaptureFailure(int paramInt, boolean paramBoolean);

  public abstract void onCaptureSuccess(int paramInt, boolean paramBoolean, String paramString);

  public abstract void onChangeRequest(int paramInt1, int paramInt2, byte[] paramArrayOfByte);

  public abstract void onConferenceEstablished(int paramInt1, int paramInt2, IIMSParams paramIIMSParams);

  public abstract void onEarlyMediaStart(int paramInt1, int paramInt2, IIMSParams paramIIMSParams);

  public abstract void onError(int paramInt1, int paramInt2, String paramString);

  public abstract void onNotifyReceived(int paramInt, int[] paramArrayOfInt, String[] paramArrayOfString);

  public abstract void onRinging(int paramInt1, String paramString, int paramInt2);

  public abstract void onRinging(int paramInt1, String paramString, IIMSParams paramIIMSParams, int paramInt2);

  public abstract void onRingingBack(int paramInt);

  public abstract void onVideoAvailable(int paramInt);

  public abstract void onVideoHeld(int paramInt);

  public abstract void onVideoResumed(int paramInt);

  public abstract void stopAlertTone(int paramInt);

  public static class IMSErrorCode
  {
    public static final int BAD_REQUEST = 400;
    public static final int CALL_5XX_RESPONSE = -52;
    public static final int CALL_HOLD_FAILED = 702;
    public static final int CALL_RESUME_FAILED = 703;
    public static final int CALL_SWITCH_FAILURE = 700;
    public static final int CALL_SWITCH_REJECTED = 701;
    public static final int CALL_TEMP_UNAVAILABLE_WITH_CAUSE = -47;
    public static final int CALL_TIMEOUT = -48;
    public static final int CLIENT_ERROR = -4;
    public static final int CONGESTION = -15;
    public static final int CROSS_DOMAIN_AUTHENTICATION = -11;
    public static final int DATA_CONNECTION_LOST = -10;
    public static final int ERROR = -13;
    public static final int IMS_CALL_ADDRESS_INCOMPLETE = -22;
    public static final int IMS_CALL_ALTERNATIVE_SERVICES = -44;
    public static final int IMS_CALL_END_CALL_NW_HANDOVER = -46;
    public static final int IMS_CALL_FAILED = -19;
    public static final int IMS_CALL_FORBIDDEN = -25;
    public static final int IMS_CALL_FORBIDDEN_RSN_EXPIRED = -40;
    public static final int IMS_CALL_FORBIDDEN_RSN_GROUP_CALL_SERVICE_UNAVAILABLE = -41;
    public static final int IMS_CALL_FORBIDDEN_RSN_OUTGOING_CALLS_IMPOSSIBLE = -43;
    public static final int IMS_CALL_FORBIDDEN_RSN_TEMPORARY_DISABILITY = -42;
    public static final int IMS_CALL_METHOD_NOT_ALLOWED = -24;
    public static final int IMS_CALL_NOT_ACCEPTABLE = -23;
    public static final int IMS_CALL_NOT_ACCEPTABLE_DIVERT = -37;
    public static final int IMS_CALL_REJECTED = -18;
    public static final int IMS_CALL_REQ_FAILED = -16;
    public static final int IMS_CALL_REQ_TERMINATED = -20;
    public static final int IMS_CALL_SERVER_INTERNAL_ERR = -32;
    public static final int IMS_CALL_SERVICE_UNAVAILABLE = -33;
    public static final int IMS_CALL_SESSION_TIMEOUT = -34;
    public static final int IMS_CALL_STATUS_ADD_USER_TO_SESSION_FAILURE = -45;
    public static final int IMS_FAILED_TO_GO_READY = -31;
    public static final int IMS_PPP_OPEN_FAILURE = -38;
    public static final int IMS_PPP_STATUS_CLOSE_EVENT = -30;
    public static final int IMS_QOS_FAILURE = -26;
    public static final int IMS_QOS_INCALL_SUSPEND = -28;
    public static final int IMS_QOS_INCALL_UNAWARE = -29;
    public static final int IMS_QOS_NW_UNAWARE = -27;
    public static final int IMS_REG_NOT_SUBSCRIBED = -50;
    public static final int IMS_REG_REQ_FAILED = -36;
    public static final int IMS_REG_TIMEOUT = -35;
    public static final int IMS_SESSION_ABORT = -21;
    public static final int IMS_SESSION_TERMINATED = -49;
    public static final int IMS_SIP_REG_FAILURE = -17;
    public static final int IMS_USER_REJECT_REASON_USR_BUSY_CS_CALL = -51;
    public static final int INVALID_CREDENTIALS = -8;
    public static final int INVALID_REMOTE_URI = -6;
    public static final int IN_PROGRESS = -9;
    public static final int NOTACCEPTABLE_AUTO_DIVERT = -14;
    public static final int NO_ERROR = 0;
    public static final int PEER_NOT_REACHABLE = -7;
    public static final int RTP_TIME_OUT = -39;
    public static final int SERVER_ERROR = -2;
    public static final int SERVER_UNREACHABLE = -12;
    public static final int SOCKET_ERROR = -1;
    public static final int TIME_OUT = -5;
    public static final int TRANSACTION_TERMINTED = -3;
    public static final int UNSUPPORTED_MEDIA = 415;

    public static String toString(int paramInt)
    {
      String str;
      switch (paramInt)
      {
      default:
        str = "Unknown";
      case 0:
      case -34:
      case -1:
      case -2:
      case -3:
      case -4:
      case -5:
      case -6:
      case -7:
      case -47:
      case -52:
      case -8:
      case -9:
      case -10:
      case -11:
      case -12:
      case -13:
      case 415:
      case -16:
      case -17:
      case -18:
      case -19:
      case -20:
      case -21:
      case -22:
      case -23:
      case -24:
      case -43:
      case -42:
      case -41:
      case -40:
      case -25:
      case -33:
      case -32:
      case -31:
      case -30:
      case -29:
      case -28:
      case -27:
      case -26:
      case -14:
      case -15:
      case -35:
      case -36:
      case -39:
      case -38:
      case -48:
      case -49:
      case -50:
      case -51:
      }
      while (true)
      {
        return str;
        str = "No Error";
        continue;
        str = "Session time out";
        continue;
        str = "Socket failure";
        continue;
        str = "Server error";
        continue;
        str = "Transaction terminated";
        continue;
        str = "Client error";
        continue;
        str = "Timeout";
        continue;
        str = "Invalid remote address";
        continue;
        str = "Unreachable";
        continue;
        str = "Cannot connect HD call";
        continue;
        str = "Server error";
        continue;
        str = "Authentication failed";
        continue;
        str = "In progress";
        continue;
        str = "Network disconnected";
        continue;
        str = "Invalid domain";
        continue;
        str = "Server not rechable";
        continue;
        str = "Error";
        continue;
        str = "Media not supported";
        continue;
        str = "Call request failed";
        continue;
        str = "Registration failed";
        continue;
        str = "Call rejected";
        continue;
        str = "Call failed";
        continue;
        str = "Request terminated";
        continue;
        str = "Session aborted";
        continue;
        str = "Invalid address";
        continue;
        str = "call not allowed";
        continue;
        str = "Request type not allowed";
        continue;
        str = "Call not allowed(Invite Failure)";
        continue;
        str = "Service unavailable";
        continue;
        str = "Internal server error";
        continue;
        str = "Call failed";
        continue;
        str = "PPP Closed";
        continue;
        str = "QOS incall unaware";
        continue;
        str = "QOS suspended";
        continue;
        str = "QOS network unaware";
        continue;
        str = "QOS failed";
        continue;
        str = "Call not acceptable and auto divert";
        continue;
        str = "Network busy";
        continue;
        str = "SIP Registration time out";
        continue;
        str = "Registration request failed";
        continue;
        str = "RTP Timeout";
        continue;
        str = "PPP Open Failed";
        continue;
        str = "ACK wait timer timeout";
        continue;
        str = "ACK for 200 OK but call terminated";
        continue;
        str = "403 response for registering";
        continue;
        str = "Call rejected due to active CS Call";
      }
    }
  }

  public static class State
  {
    public static final int ALREADY_IN_CALL = 8;
    public static final int ANSWERING = 2;
    public static final int CANCELING = 5;
    public static final int IN_CALL = 6;
    public static final int NOT_DEFINED = 101;
    public static final int OUTGOING_CALL = 3;
    public static final int PINGING = 7;
    public static final int READY_TO_CALL = 0;
    public static final int RINGING = 1;
    public static final int RING_BACK = 4;

    public static String toString(int paramInt)
    {
      String str;
      switch (paramInt)
      {
      default:
        str = "NOT_DEFINED";
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      }
      while (true)
      {
        return str;
        str = "READY_TO_CALL";
        continue;
        str = "INCOMING_CALL";
        continue;
        str = "ANSWERING";
        continue;
        str = "OUTGOING_CALL";
        continue;
        str = "RING_BACK";
        continue;
        str = "CANCELING";
        continue;
        str = "IN_CALL";
        continue;
        str = "PINGING";
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.commonimsservice.IIMSCallStateListener
 * JD-Core Version:    0.6.0
 */