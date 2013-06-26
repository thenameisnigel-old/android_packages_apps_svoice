package com.samsung.media.fmradio;

public class FMPlayerException extends Exception
{
  public static final int AIRPLANE_MODE = 5;
  public static final int BATTERY_LOW = 6;
  public static final int HEAD_SET_IS_NOT_PLUGGED = 4;
  public static final int PLAYER_IS_NOT_ON = 1;
  public static final int PLAYER_SCANNING = 3;
  public static final int RADIO_SERVICE_DOWN = 2;
  public static final int TV_OUT_PLUGGED = 7;
  private static final long serialVersionUID = 1L;
  private int mCode;
  private Throwable mThrowable;
  private String msg;

  public FMPlayerException(int paramInt, String paramString, Throwable paramThrowable)
  {
    this.mCode = paramInt;
    this.msg = paramString;
    this.mThrowable = paramThrowable;
  }

  public int getCode()
  {
    return this.mCode;
  }

  public String getMessage()
  {
    return this.msg;
  }

  public Throwable getThrowable()
  {
    return this.mThrowable;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.media.fmradio.FMPlayerException
 * JD-Core Version:    0.6.0
 */