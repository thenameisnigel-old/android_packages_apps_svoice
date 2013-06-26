package com.sec.android.ims;

public abstract interface ImsMMTelSvc
{
  public static final int FUNC_MMTEL_ACCEPT_CALL = 5;
  public static final int FUNC_MMTEL_ADD_USER_CONFERENCE = 21;
  public static final int FUNC_MMTEL_CANCEL_CALL = 4;
  public static final int FUNC_MMTEL_CODEC_SET_PARAM = 13;
  public static final int FUNC_MMTEL_DEREGISTER = 1;
  public static final int FUNC_MMTEL_DISCONNECT_CALL = 3;
  public static final int FUNC_MMTEL_ENABLE_CP_VOLTE = 22;
  public static final int FUNC_MMTEL_GET_MUTE_STATE = 10;
  public static final int FUNC_MMTEL_MAKE_CALL = 2;
  public static final int FUNC_MMTEL_MAKE_CONFERENCE_CALL = 20;
  public static final int FUNC_MMTEL_MODIFY_ACCEPT_CALL = 15;
  public static final int FUNC_MMTEL_MODIFY_REJECT_CALL = 16;
  public static final int FUNC_MMTEL_MUTE_CALL = 9;
  public static final int FUNC_MMTEL_REGISTER = 0;
  public static final int FUNC_MMTEL_REJECT_CALL = 6;
  public static final int FUNC_MMTEL_SEND_DTMF = 7;
  public static final int FUNC_MMTEL_SET_AUTO_RESPONSE = 17;
  public static final int FUNC_MMTEL_SS_BCT = 12;
  public static final int FUNC_MMTEL_SS_CALL_HOLD = 8;
  public static final int FUNC_MMTEL_SS_CALL_RESUME = 11;
  public static final int FUNC_MMTEL_SS_CALL_SWITCH = 14;
  public static final int FUNC_MMTEL_SWITCH_CALL = 18;
  public static final int FUNC_MMTEL_Video_Share_ACCEPT = 19;
  public static final int IMS_SVC_MM_EVT_BASE = 100;
  public static final int IMS_SVC_MM_FAILURE = -1;
  public static final int IMS_SVC_MM_NTFY_FAR_FRAME_READY_IND = 213;
  public static final int IMS_SVC_MM_NTFY_IND_BASE = 200;
  public static final int IMS_SVC_MM_NTFY_NONE = 200;
  public static final int IMS_SVC_MM_NTFY_RECORDSTATUS_IND = 207;
  public static final int IMS_SVC_MM_NTFY_SESSESTABLISHREQ_IND = 201;
  public static final int IMS_SVC_MM_NTFY_SESSESTABLISHSUCC_IND = 202;
  public static final int IMS_SVC_MM_NTFY_SESSIONREL_IND = 203;
  public static final int IMS_SVC_MM_NTFY_SESSMODIFYRREQ_IND = 208;
  public static final int IMS_SVC_MM_NTFY_SESSMODIFYSUCC_IND = 209;
  public static final int IMS_SVC_MM_NTFY_SESSMODIFY_IND = 206;
  public static final int IMS_SVC_MM_NTFY_SESSREPLACES_DONE_IND = 211;
  public static final int IMS_SVC_MM_NTFY_SESSREPLACES_FAILED_IND = 212;
  public static final int IMS_SVC_MM_NTFY_SESSREPLACES_INPRG_IND = 210;
  public static final int IMS_SVC_MM_NTFY_SESSTRANSFERREQ_IND = 204;
  public static final int IMS_SVC_MM_NTFY_SESSTRANSFERSTATUS_IND = 205;
  public static final int IMS_SVC_MM_STATUS_DEREG_FAIL = 129;
  public static final int IMS_SVC_MM_STATUS_DEREG_SUCCESS = 128;
  public static final int IMS_SVC_MM_STATUS_NONE = 100;
  public static final int IMS_SVC_MM_STATUS_RECSTART_FAIL = 113;
  public static final int IMS_SVC_MM_STATUS_RECSTART_SUCC = 112;
  public static final int IMS_SVC_MM_STATUS_RECSTOP_FAIL = 111;
  public static final int IMS_SVC_MM_STATUS_RECSTOP_SUCC = 110;
  public static final int IMS_SVC_MM_STATUS_REG_FAIL = 127;
  public static final int IMS_SVC_MM_STATUS_REG_SUCCESS = 126;
  public static final int IMS_SVC_MM_STATUS_SENDDTMF_FAIL = 115;
  public static final int IMS_SVC_MM_STATUS_SENDDTMF_SUCCESS = 114;
  public static final int IMS_SVC_MM_STATUS_SESSCANCEL_FAIL = 107;
  public static final int IMS_SVC_MM_STATUS_SESSCANCEL_SUCCESS = 106;
  public static final int IMS_SVC_MM_STATUS_SESSIONHOLD_FAIL = 119;
  public static final int IMS_SVC_MM_STATUS_SESSIONHOLD_SUCCESS = 118;
  public static final int IMS_SVC_MM_STATUS_SESSIONMODIFY_FAIL = 123;
  public static final int IMS_SVC_MM_STATUS_SESSIONMODIFY_SUCCESS = 122;
  public static final int IMS_SVC_MM_STATUS_SESSIONRESUME_FAIL = 121;
  public static final int IMS_SVC_MM_STATUS_SESSIONRESUME_SUCCESS = 120;
  public static final int IMS_SVC_MM_STATUS_SESSIONSWAP_FAIL = 125;
  public static final int IMS_SVC_MM_STATUS_SESSIONSWAP_SUCCESS = 124;
  public static final int IMS_SVC_MM_STATUS_SESSSTART_FAILED = 101;
  public static final int IMS_SVC_MM_STATUS_SESSSTART_FAIL_REDIRECTREQ = 105;
  public static final int IMS_SVC_MM_STATUS_SESSSTART_REMOTE_RINGING = 102;
  public static final int IMS_SVC_MM_STATUS_SESSSTART_REMOTE_SESSFORWARDED = 103;
  public static final int IMS_SVC_MM_STATUS_SESSSTART_SUCCESS = 104;
  public static final int IMS_SVC_MM_STATUS_SESSSTOP_FAILED = 130;
  public static final int IMS_SVC_MM_STATUS_SESSTRANSFERREQ_FAIL = 117;
  public static final int IMS_SVC_MM_STATUS_SESSTRANSFERREQ_SUCCESS = 116;
  public static final int IMS_SVC_MM_STATUS_SESS_RELEASE_FAILED = 109;
  public static final int IMS_SVC_MM_STATUS_SESS_RELEASE_SUCCESS = 108;
  public static final int IMS_SVC_MM_SUCCESS = 0;
  public static final int MOD_MMTEL_SVC = 1;
  public static final int MOD_REG_SVC;
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.ims.ImsMMTelSvc
 * JD-Core Version:    0.6.0
 */