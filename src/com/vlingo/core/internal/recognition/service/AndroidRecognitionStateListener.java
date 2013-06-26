package com.vlingo.core.internal.recognition.service;

import com.vlingo.core.internal.recognition.AndroidRecognitionManager;

public abstract interface AndroidRecognitionStateListener
{
  public static final int ERROR = 4;
  public static final int INFO = 5;
  public static final int POPUP = 2;
  public static final int REC_STATE_ABORTED = 105;
  public static final int REC_STATE_CONNECTING = 102;
  public static final int REC_STATE_FAIL_CONNECT = 106;
  public static final int REC_STATE_FAIL_OPEN_RECORDER = 108;
  public static final int REC_STATE_LISTENING = 101;
  public static final int REC_STATE_NO_RESULTS = 111;
  public static final int REC_STATE_OTHER = 200;
  public static final int REC_STATE_RECOGNIZER_BUSY = 113;
  public static final int REC_STATE_RECORDED = 103;
  public static final int REC_STATE_RECORD_STARTED = 100;
  public static final int REC_STATE_RESULT = 112;
  public static final int REC_STATE_RMS_CHANGED = 114;
  public static final int REC_STATE_TIMEOUT = 107;
  public static final int REC_STATE_TOO_LONG = 110;
  public static final int REC_STATE_TOO_SHORT = 109;
  public static final int REC_STATE_WORKING = 104;
  public static final int WARNING = 3;

  public abstract void onRecognitionEvent(AndroidRecognitionManager paramAndroidRecognitionManager, int paramInt1, int paramInt2, String paramString);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.service.AndroidRecognitionStateListener
 * JD-Core Version:    0.6.0
 */