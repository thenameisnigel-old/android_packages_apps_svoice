package com.vlingo.sdk.internal.http.custom;

import com.vlingo.sdk.internal.net.ConnectionResult;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract interface VStreamConnection
{
  public abstract void close()
    throws IOException;

  public abstract ConnectionResult getConnectionDetails();

  public abstract DataInputStream getInputStream()
    throws IOException;

  public abstract DataOutputStream getOutputStream()
    throws IOException;

  public abstract boolean isOpen();

  public abstract void startRequest(HttpRequest paramHttpRequest);

  public abstract void startResponse(HttpResponse paramHttpResponse);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.VStreamConnection
 * JD-Core Version:    0.6.0
 */