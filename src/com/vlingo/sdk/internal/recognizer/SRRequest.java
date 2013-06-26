package com.vlingo.sdk.internal.recognizer;

public abstract interface SRRequest
{
  public static final int DEFAULT_TIMEOUT = -1;

  public abstract void addListener(SRRequestListener paramSRRequestListener);

  public abstract void cancel(boolean paramBoolean);

  public abstract void finish();

  public abstract long getTimeGotResult();

  public abstract long getTimeSendFinish();

  public abstract long getTimeSendStart();

  public abstract boolean isCancelled();

  public abstract boolean isResponseReceived();

  public abstract void removeListener(SRRequestListener paramSRRequestListener);

  public abstract void sendAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.SRRequest
 * JD-Core Version:    0.6.0
 */