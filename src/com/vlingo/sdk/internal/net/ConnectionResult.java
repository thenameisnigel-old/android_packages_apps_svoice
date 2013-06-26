package com.vlingo.sdk.internal.net;

public class ConnectionResult
{
  public boolean apnUsed = false;
  public Connection connection = null;
  public int connectionType = -1;
  public String connectionTypeString = "";
  public Exception failureException = null;
  public boolean isFailure = false;
  public long timeToComplete = 0L;
  public String url = "";
  public boolean wifiActive = false;
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.net.ConnectionResult
 * JD-Core Version:    0.6.0
 */