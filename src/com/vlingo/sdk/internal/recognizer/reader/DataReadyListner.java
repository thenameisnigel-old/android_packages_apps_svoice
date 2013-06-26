package com.vlingo.sdk.internal.recognizer.reader;

public class DataReadyListner
{
  private boolean isDataReady = true;

  public boolean isDataReady()
  {
    return this.isDataReady;
  }

  public void onDataNotReady()
  {
    this.isDataReady = false;
  }

  public void onDataReady()
  {
    this.isDataReady = true;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.reader.DataReadyListner
 * JD-Core Version:    0.6.0
 */