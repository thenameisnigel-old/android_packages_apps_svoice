package com.vlingo.sdk.internal.recognizer.sr3;

import com.vlingo.sdk.internal.http.custom.MPOutputStream;
import com.vlingo.sdk.internal.http.custom.VHttpConnection;
import java.io.IOException;
import java.io.InputStream;

public abstract class HttpConnectionAdapter
{
  public static String ivBoundary = "-------------------------------1878979834";
  protected int ivRequestID;

  public HttpConnectionAdapter(int paramInt)
    throws IOException
  {
    this.ivRequestID = paramInt;
  }

  public abstract void close()
    throws IOException;

  public abstract void finishRequest()
    throws IOException;

  public abstract void finishResponse()
    throws IOException;

  public abstract VHttpConnection getConnection();

  public abstract InputStream getIn()
    throws IOException;

  public abstract MPOutputStream getOut()
    throws IOException;

  public int getRequestID()
  {
    return this.ivRequestID;
  }

  public abstract String getResponseHeaderField(int paramInt)
    throws IOException;

  public abstract String getResponseHeaderFieldKey(int paramInt)
    throws IOException;

  public abstract void setRequestHeader(String paramString1, String paramString2)
    throws IOException;
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.sr3.HttpConnectionAdapter
 * JD-Core Version:    0.6.0
 */