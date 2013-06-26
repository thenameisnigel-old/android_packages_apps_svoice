package com.vlingo.sdk.internal.http.custom;

import com.vlingo.sdk.internal.http.URL;
import com.vlingo.sdk.internal.net.ConnectionResult;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class VHttpConnection
{
  private int ivChunkSize = 1000;
  protected VStreamConnection ivCon;
  private HttpInteraction m_HttpInteraction;
  private URL m_URL;

  public VHttpConnection(URL paramURL)
  {
    this.m_URL = paramURL;
  }

  private void primeConnection()
    throws IOException
  {
    HttpInteraction localHttpInteraction = new HttpInteraction(this);
    HttpRequest localHttpRequest = localHttpInteraction.getRequest();
    localHttpRequest.setPath("/voicepad/sr");
    localHttpRequest.setMethod("HEAD");
    localHttpRequest.setHeader("Connection", "keep-alive");
    localHttpRequest.finish();
    InputStream localInputStream = localHttpInteraction.getResponse().getInputStream();
    while (localInputStream.read() != -1);
  }

  public void close()
    throws IOException
  {
    if (this.ivCon != null);
    try
    {
      this.ivCon.close();
      label16: return;
    }
    catch (Exception localException)
    {
      break label16;
    }
  }

  public int getChunkSize()
  {
    return this.ivChunkSize;
  }

  public ConnectionResult getConnectionDetails()
  {
    return this.ivCon.getConnectionDetails();
  }

  public String getHost()
  {
    return this.m_URL.host;
  }

  public HttpInteraction getHttpInteraction()
  {
    return this.m_HttpInteraction;
  }

  public DataInputStream getInputStream(HttpResponse paramHttpResponse)
    throws IOException
  {
    return this.ivCon.getInputStream();
  }

  public DataOutputStream getOutputStream(HttpRequest paramHttpRequest)
    throws IOException
  {
    return this.ivCon.getOutputStream();
  }

  public int getPort()
  {
    return this.m_URL.port;
  }

  public VStreamConnection getVStreamConnection()
  {
    return this.ivCon;
  }

  public boolean isOpen()
  {
    if ((this.ivCon != null) && (this.ivCon.isOpen()));
    for (int i = 1; ; i = 0)
      return i;
  }

  void notifyRequestDone(HttpRequest paramHttpRequest)
    throws IOException
  {
  }

  void notifyResponseDone(HttpResponse paramHttpResponse)
    throws IOException
  {
  }

  public void open()
    throws IOException
  {
    this.ivCon = VConnectionFactory.createConnection(this.m_URL);
    if (this.ivCon == null)
      throw new RuntimeException("Connection factory returned null");
    if (getConnectionDetails().connectionType != 0)
      primeConnection();
  }

  public HttpInteraction openInteraction(String paramString)
    throws IOException
  {
    if (this.m_HttpInteraction != null)
      throw new IOException("New HTTP interaction attempted on non-keep-alive connection");
    this.m_HttpInteraction = new HttpInteraction(this);
    this.m_HttpInteraction.getRequest().setPath(paramString);
    this.m_HttpInteraction.getRequest().setHeader("Transfer-Encoding", "chunked");
    return this.m_HttpInteraction;
  }

  public void setChunkSize(int paramInt)
  {
    this.ivChunkSize = paramInt;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.VHttpConnection
 * JD-Core Version:    0.6.0
 */