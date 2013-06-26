package com.vlingo.core.internal.util;

public class LoggedCall
{
  private long dateOfCall;
  private String nameOfCaller;

  private LoggedCall(String paramString, long paramLong)
  {
    this.nameOfCaller = paramString;
    this.dateOfCall = paramLong;
  }

  public static LoggedCall newInstance(String paramString, long paramLong)
  {
    return new LoggedCall(paramString, paramLong);
  }

  public long getDate()
  {
    return this.dateOfCall;
  }

  public String getName()
  {
    return this.nameOfCaller;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.LoggedCall
 * JD-Core Version:    0.6.0
 */