package com.vlingo.sdk.internal.http.custom;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

public class HttpRequest
{
  private DataOutputStream ivClientDout;
  private OutputStream ivClientOut;
  private boolean ivFinished = false;
  private Hashtable<Object, Object> ivHeaders = new Hashtable();
  private boolean ivHeadersSent = false;
  private HttpInteraction ivInteraction;
  private String ivMethod = "POST";
  private String ivPath;

  public HttpRequest(HttpInteraction paramHttpInteraction)
  {
    if (paramHttpInteraction == null)
      throw new IllegalArgumentException("interaction may not be null");
    this.ivInteraction = paramHttpInteraction;
    String str = paramHttpInteraction.getHTTPConnection().getHost();
    int i = paramHttpInteraction.getHTTPConnection().getPort();
    this.ivHeaders.put("Host", str + ":" + i);
    this.ivHeaders.put("User-Agent", "Vlingo HttpClient 2.0");
  }

  private void checkFinished()
  {
    if (this.ivFinished)
      throw new RuntimeException("HttpRequest is closed");
  }

  private void checkHeaderSent()
  {
    if (this.ivHeadersSent)
      throw new RuntimeException("Headers already sent, too late to specify this information");
  }

  private void ensureHeadersSent()
    throws IOException
  {
    if (!this.ivHeadersSent)
    {
      DataOutputStream localDataOutputStream = this.ivInteraction.getHTTPConnection().getOutputStream(this);
      if (this.ivPath == null)
        throw new RuntimeException("Path is null");
      initOutputStream(localDataOutputStream);
    }
  }

  private void initOutputStream(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    String str = (String)this.ivHeaders.get("Transfer-Encoding");
    if ((str != null) && (str.equalsIgnoreCase("chunked")))
      this.ivClientOut = new ChunkedOutputStream(paramDataOutputStream);
    for (this.ivClientDout = new DataOutputStream(this.ivClientOut); ; this.ivClientDout = paramDataOutputStream)
    {
      return;
      this.ivClientOut = paramDataOutputStream;
    }
  }

  public void finish()
    throws IOException
  {
    checkFinished();
    ensureHeadersSent();
    if ((this.ivClientOut instanceof ChunkedOutputStream))
      ((ChunkedOutputStream)this.ivClientOut).writeLastChunk();
    this.ivFinished = true;
    this.ivInteraction.getHTTPConnection().notifyRequestDone(this);
  }

  public String getMethod()
  {
    return this.ivMethod;
  }

  public DataOutputStream getOutputStream()
    throws IOException
  {
    ensureHeadersSent();
    return this.ivClientDout;
  }

  public String getPath()
  {
    return this.ivPath;
  }

  public boolean isFinished()
  {
    return this.ivFinished;
  }

  public Hashtable<?, ?> sendHeaders()
  {
    return this.ivHeaders;
  }

  public void setHeader(String paramString1, String paramString2)
  {
    checkHeaderSent();
    this.ivHeaders.put(paramString1, paramString2);
  }

  public void setMethod(String paramString)
  {
    checkHeaderSent();
    this.ivMethod = paramString;
  }

  public void setPath(String paramString)
  {
    checkHeaderSent();
    this.ivPath = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.HttpRequest
 * JD-Core Version:    0.6.0
 */