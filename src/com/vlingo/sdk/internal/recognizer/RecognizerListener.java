package com.vlingo.sdk.internal.recognizer;

import com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse;

public abstract interface RecognizerListener
{
  public abstract void onRecognitionResponse(SRRecognitionResponse paramSRRecognitionResponse);

  public abstract void onRecognizerError(RecognizerError paramRecognizerError, String paramString);

  public abstract void onRecognizerStateChanged(RecognizerState paramRecognizerState, Object paramObject);

  public static enum RecognizerError
  {
    static
    {
      READER_ERROR = new RecognizerError("READER_ERROR", 2);
      TOO_SHORT = new RecognizerError("TOO_SHORT", 3);
      NO_RESULTS = new RecognizerError("NO_RESULTS", 4);
      NO_SPEECH = new RecognizerError("NO_SPEECH", 5);
      RecognizerError[] arrayOfRecognizerError = new RecognizerError[6];
      arrayOfRecognizerError[0] = FAIL_CONNECT;
      arrayOfRecognizerError[1] = TIMEOUT;
      arrayOfRecognizerError[2] = READER_ERROR;
      arrayOfRecognizerError[3] = TOO_SHORT;
      arrayOfRecognizerError[4] = NO_RESULTS;
      arrayOfRecognizerError[5] = NO_SPEECH;
      $VALUES = arrayOfRecognizerError;
    }
  }

  public static enum RecognizerState
  {
    static
    {
      RECEIVING = new RecognizerState("RECEIVING", 5);
      RESULT = new RecognizerState("RESULT", 6);
      RecognizerState[] arrayOfRecognizerState = new RecognizerState[7];
      arrayOfRecognizerState[0] = CONNECTING;
      arrayOfRecognizerState[1] = LISTENING;
      arrayOfRecognizerState[2] = RMS_CHANGED;
      arrayOfRecognizerState[3] = SENDING;
      arrayOfRecognizerState[4] = THINKING;
      arrayOfRecognizerState[5] = RECEIVING;
      arrayOfRecognizerState[6] = RESULT;
      $VALUES = arrayOfRecognizerState;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.RecognizerListener
 * JD-Core Version:    0.6.0
 */