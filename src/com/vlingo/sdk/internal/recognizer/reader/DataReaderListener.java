package com.vlingo.sdk.internal.recognizer.reader;

public abstract interface DataReaderListener
{
  public abstract void onDataAvailable(byte[] paramArrayOfByte, int paramInt);

  public abstract void onError(ErrorCode paramErrorCode, String paramString);

  public abstract void onStarted();

  public abstract void onStopped(int paramInt, boolean paramBoolean);

  public static enum ErrorCode
  {
    static
    {
      ErrorCode[] arrayOfErrorCode = new ErrorCode[1];
      arrayOfErrorCode[0] = READ_ERROR;
      $VALUES = arrayOfErrorCode;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.reader.DataReaderListener
 * JD-Core Version:    0.6.0
 */