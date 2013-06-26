package com.vlingo.sdk.internal.recognizer;

import java.util.Vector;

public class TimingRepository
{
  private final Vector<String> eventTimings = new Vector();
  private volatile long timeZero;

  public void clear()
  {
    this.eventTimings.removeAllElements();
    this.timeZero = 0L;
  }

  public String getStatString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("ZERO:" + this.timeZero);
    int i = this.eventTimings.size();
    for (int j = 0; j < i; j++)
    {
      localStringBuffer.append(",");
      localStringBuffer.append((String)this.eventTimings.elementAt(j));
    }
    return localStringBuffer.toString();
  }

  public void markTimeZero()
  {
    this.timeZero = System.currentTimeMillis();
  }

  public void recordAndTimeStampEvent(String paramString)
  {
    this.eventTimings.addElement(paramString + ":" + (System.currentTimeMillis() - this.timeZero));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.TimingRepository
 * JD-Core Version:    0.6.0
 */