package com.vlingo.sdk.internal.recognizer;

import com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse;

public abstract interface SRRequestListener
{
  public static final int CONNECTING = 1;
  public static final int CONNECT_FAILURE = -1;
  public static final int RECEIVING = 3;
  public static final int RECORDER_ERROR = -2;
  public static final int SENDING = 2;
  public static final int TIMED_OUT = -3;

  public abstract void requestFailed(int paramInt);

  public abstract void resultReceived(SRRecognitionResponse paramSRRecognitionResponse);

  public abstract void stateChanged(int paramInt);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.SRRequestListener
 * JD-Core Version:    0.6.0
 */