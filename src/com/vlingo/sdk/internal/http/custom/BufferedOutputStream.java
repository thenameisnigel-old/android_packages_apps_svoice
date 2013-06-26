package com.vlingo.sdk.internal.http.custom;

import com.vlingo.sdk.internal.util.NoCopyByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream
{
  private NoCopyByteArrayOutputStream buffer;
  private OutputStream out;

  public BufferedOutputStream(OutputStream paramOutputStream, int paramInt)
  {
    this.out = paramOutputStream;
    this.buffer = new NoCopyByteArrayOutputStream(paramInt);
  }

  public void flush()
    throws IOException
  {
    monitorenter;
    try
    {
      this.out.write(this.buffer.getByteArray(), 0, this.buffer.size());
      this.out.flush();
      this.buffer.reset();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void write(int paramInt)
    throws IOException
  {
    monitorenter;
    try
    {
      this.buffer.write(paramInt);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    monitorenter;
    try
    {
      this.buffer.write(paramArrayOfByte, 0, paramArrayOfByte.length);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    monitorenter;
    try
    {
      this.buffer.write(paramArrayOfByte, paramInt1, paramInt2);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.BufferedOutputStream
 * JD-Core Version:    0.6.0
 */