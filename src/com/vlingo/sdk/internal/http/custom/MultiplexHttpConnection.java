package com.vlingo.sdk.internal.http.custom;

import com.vlingo.sdk.internal.http.URL;
import com.vlingo.sdk.internal.recognizer.Queue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MultiplexHttpConnection extends VHttpConnection
{
  private HttpRequest ivActiveRequest;
  private HttpResponse ivActiveResponse;
  private DataInputStream ivIn;
  private DataOutputStream ivOut;
  private Queue ivRequestQueue = new Queue();
  private Queue ivResponseQueue = new Queue();

  public MultiplexHttpConnection(URL paramURL)
  {
    super(paramURL);
  }

  private void assignReadLock()
  {
    synchronized (this.ivIn)
    {
      this.ivActiveResponse = ((HttpResponse)this.ivResponseQueue.pop());
      this.ivIn.notifyAll();
      return;
    }
  }

  private void assignWriteLock()
  {
    synchronized (this.ivOut)
    {
      this.ivActiveRequest = ((HttpRequest)this.ivRequestQueue.pop());
      this.ivOut.notifyAll();
      return;
    }
  }

  private void getReadLock(HttpResponse paramHttpResponse)
  {
    while (true)
    {
      if (this.ivActiveResponse != paramHttpResponse)
        synchronized (this.ivIn)
        {
          if (this.ivActiveResponse == null)
            assignReadLock();
          HttpResponse localHttpResponse = this.ivActiveResponse;
          if (localHttpResponse == paramHttpResponse);
        }
      try
      {
        this.ivIn.wait();
        label45: monitorexit;
        continue;
        localObject = finally;
        monitorexit;
        throw localObject;
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        break label45;
      }
    }
  }

  private void getWriteLock(HttpRequest paramHttpRequest)
    throws IOException
  {
    while (true)
    {
      if (this.ivActiveRequest != paramHttpRequest)
        synchronized (this.ivOut)
        {
          if (this.ivActiveRequest == null)
            assignWriteLock();
          HttpRequest localHttpRequest = this.ivActiveRequest;
          if (localHttpRequest == paramHttpRequest);
        }
      try
      {
        this.ivOut.wait();
        label45: monitorexit;
        continue;
        localObject = finally;
        monitorexit;
        throw localObject;
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        break label45;
      }
    }
  }

  private int read(HttpResponse paramHttpResponse)
    throws IOException
  {
    getReadLock(paramHttpResponse);
    return this.ivIn.read();
  }

  private void write(HttpRequest paramHttpRequest, int paramInt)
    throws IOException
  {
    getWriteLock(paramHttpRequest);
    this.ivOut.write(paramInt);
  }

  private void write(HttpRequest paramHttpRequest, byte[] paramArrayOfByte)
    throws IOException
  {
    getWriteLock(paramHttpRequest);
    this.ivOut.write(paramArrayOfByte);
  }

  private void write(HttpRequest paramHttpRequest, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    getWriteLock(paramHttpRequest);
    this.ivOut.write(paramArrayOfByte, paramInt1, paramInt2);
  }

  public DataInputStream getInputStream(HttpResponse paramHttpResponse)
    throws IOException
  {
    monitorenter;
    try
    {
      if (this.ivIn == null)
        this.ivIn = this.ivCon.getInputStream();
      return new MultiplexInputStream(this.ivIn, paramHttpResponse);
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public DataOutputStream getOutputStream(HttpRequest paramHttpRequest)
    throws IOException
  {
    monitorenter;
    try
    {
      if (this.ivOut == null)
        this.ivOut = this.ivCon.getOutputStream();
      return new MultiplexOutputStream(this.ivOut, paramHttpRequest);
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  void notifyRequestDone(HttpRequest paramHttpRequest)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 77	com/vlingo/sdk/internal/http/custom/MultiplexHttpConnection:ivOut	Ljava/io/DataOutputStream;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 81	com/vlingo/sdk/internal/http/custom/MultiplexHttpConnection:ivActiveRequest	Lcom/vlingo/sdk/internal/http/custom/HttpRequest;
    //   11: aload_1
    //   12: if_acmpeq +18 -> 30
    //   15: new 38	java/io/IOException
    //   18: dup
    //   19: ldc 139
    //   21: invokespecial 142	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   24: athrow
    //   25: astore_3
    //   26: aload_2
    //   27: monitorexit
    //   28: aload_3
    //   29: athrow
    //   30: aload_0
    //   31: invokespecial 94	com/vlingo/sdk/internal/http/custom/MultiplexHttpConnection:assignWriteLock	()V
    //   34: aload_2
    //   35: monitorexit
    //   36: return
    //
    // Exception table:
    //   from	to	target	type
    //   7	28	25	finally
    //   30	36	25	finally
  }

  // ERROR //
  void notifyResponseDone(HttpResponse paramHttpResponse)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 61	com/vlingo/sdk/internal/http/custom/MultiplexHttpConnection:ivIn	Ljava/io/DataInputStream;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 69	com/vlingo/sdk/internal/http/custom/MultiplexHttpConnection:ivActiveResponse	Lcom/vlingo/sdk/internal/http/custom/HttpResponse;
    //   11: aload_1
    //   12: if_acmpeq +18 -> 30
    //   15: new 38	java/io/IOException
    //   18: dup
    //   19: ldc 145
    //   21: invokespecial 142	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   24: athrow
    //   25: astore_3
    //   26: aload_2
    //   27: monitorexit
    //   28: aload_3
    //   29: athrow
    //   30: aload_0
    //   31: invokespecial 87	com/vlingo/sdk/internal/http/custom/MultiplexHttpConnection:assignReadLock	()V
    //   34: aload_2
    //   35: monitorexit
    //   36: return
    //
    // Exception table:
    //   from	to	target	type
    //   7	28	25	finally
    //   30	36	25	finally
  }

  public HttpInteraction openInteraction(String paramString)
    throws IOException
  {
    monitorenter;
    try
    {
      HttpInteraction localHttpInteraction = new HttpInteraction(this);
      localHttpInteraction.getRequest().setPath(paramString);
      this.ivRequestQueue.add(localHttpInteraction.getRequest());
      this.ivResponseQueue.add(localHttpInteraction.getResponse());
      monitorexit;
      return localHttpInteraction;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private class MultiplexInputStream extends DataInputStream
  {
    private HttpResponse ivResponse;

    MultiplexInputStream(InputStream paramHttpResponse, HttpResponse arg3)
    {
      super();
      Object localObject;
      this.ivResponse = localObject;
    }

    public int read()
      throws IOException
    {
      return MultiplexHttpConnection.this.read(this.ivResponse);
    }
  }

  private class MultiplexOutputStream extends DataOutputStream
  {
    private HttpRequest ivRequest;

    MultiplexOutputStream(OutputStream paramHttpRequest, HttpRequest arg3)
    {
      super();
      Object localObject;
      this.ivRequest = localObject;
    }

    public void write(int paramInt)
      throws IOException
    {
      MultiplexHttpConnection.this.write(this.ivRequest, paramInt);
    }

    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      MultiplexHttpConnection.this.write(this.ivRequest, paramArrayOfByte);
    }

    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      MultiplexHttpConnection.this.write(this.ivRequest, paramArrayOfByte, paramInt1, paramInt2);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.MultiplexHttpConnection
 * JD-Core Version:    0.6.0
 */