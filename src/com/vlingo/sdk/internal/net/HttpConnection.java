package com.vlingo.sdk.internal.net;

import com.vlingo.sdk.internal.http.custom.HttpRequest;
import com.vlingo.sdk.internal.http.custom.HttpResponse;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract interface HttpConnection extends Connection
{
  public abstract void close()
    throws IOException;

  public abstract String getFile();

  public abstract String getHeaderField(int paramInt);

  public abstract String getHeaderFieldKey(int paramInt)
    throws IOException;

  public abstract String getHost();

  public abstract int getLength();

  public abstract int getResponseCode();

  public abstract InputStream openDataInputStream()
    throws IOException;

  public abstract DataOutputStream openDataOutputStream()
    throws IOException;

  public abstract InputStream openInputStream()
    throws IOException;

  public abstract void setRequestMethod(String paramString);

  public abstract void setRequestProperty(String paramString1, String paramString2);

  public abstract void startRequest(HttpRequest paramHttpRequest);

  public abstract void startResponse(HttpResponse paramHttpResponse);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.net.HttpConnection
 * JD-Core Version:    0.6.0
 */