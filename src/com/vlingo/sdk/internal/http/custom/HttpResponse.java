package com.vlingo.sdk.internal.http.custom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

public class HttpResponse
{
  private InputStream ivClientDin;
  private String ivCode;
  private Hashtable<String, String> ivHeaders = new Hashtable();
  private HttpInteraction ivInteraction;
  private String ivMessage;
  private String ivProtocol;
  private boolean ivReadHeaders = false;

  public HttpResponse(HttpInteraction paramHttpInteraction)
  {
    this.ivInteraction = paramHttpInteraction;
  }

  private void ensureHeaders()
    throws IOException
  {
    if (!this.ivReadHeaders)
      setupInputStream(this.ivInteraction.getHTTPConnection().getInputStream(this));
  }

  private void setupInputStream(InputStream paramInputStream)
    throws IOException
  {
    if ((String)this.ivHeaders.get("Transfer-Encoding") == null)
    {
      String str = (String)this.ivHeaders.get("Content-Length");
      if (str != null)
        this.ivClientDin = new LimitInputStream(paramInputStream, Integer.parseInt(str));
    }
    while (true)
    {
      return;
      this.ivClientDin = paramInputStream;
      continue;
      this.ivClientDin = new ChunkedInputStream(paramInputStream);
    }
  }

  public void finish()
    throws IOException
  {
    this.ivInteraction.getHTTPConnection().notifyResponseDone(this);
  }

  public String getCode()
    throws IOException
  {
    ensureHeaders();
    return this.ivCode;
  }

  public Enumeration<?> getHeaderNames()
    throws IOException
  {
    ensureHeaders();
    return this.ivHeaders.keys();
  }

  public String getHeaderValue(String paramString)
    throws IOException
  {
    ensureHeaders();
    return (String)this.ivHeaders.get(paramString);
  }

  public InputStream getInputStream()
    throws IOException
  {
    ensureHeaders();
    return this.ivClientDin;
  }

  public String getMessage()
    throws IOException
  {
    ensureHeaders();
    return this.ivMessage;
  }

  public String getProtocol()
    throws IOException
  {
    ensureHeaders();
    return this.ivProtocol;
  }

  public Hashtable<String, String> readHeaders()
  {
    return this.ivHeaders;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.HttpResponse
 * JD-Core Version:    0.6.0
 */