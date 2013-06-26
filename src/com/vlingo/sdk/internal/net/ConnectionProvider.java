package com.vlingo.sdk.internal.net;

import java.io.IOException;

public abstract class ConnectionProvider
{
  public Connection getConnection(String paramString, int paramInt, boolean paramBoolean)
    throws IOException
  {
    return getConnectionWithDetails(paramString, paramInt, paramBoolean).connection;
  }

  public abstract ConnectionResult getConnectionWithDetails(String paramString, int paramInt, boolean paramBoolean)
    throws IOException;
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.net.ConnectionProvider
 * JD-Core Version:    0.6.0
 */