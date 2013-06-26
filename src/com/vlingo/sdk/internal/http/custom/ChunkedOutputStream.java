package com.vlingo.sdk.internal.http.custom;

import com.vlingo.sdk.internal.logging.Logger;
import com.vlingo.sdk.internal.util.NoCopyByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ChunkedOutputStream extends OutputStream
{
  private static final int DEFAULT_MIN_CHUNK_SIZE = 1024;
  private static Logger log = Logger.getLogger(ChunkedOutputStream.class);
  private boolean ivClosed = false;
  private int ivMinChunkSize = 1024;
  private OutputStream ivOut = new BufferedOutputStream(paramOutputStream, 3 * this.ivMinChunkSize);
  private NoCopyByteArrayOutputStream ivWriteBuffer = new NoCopyByteArrayOutputStream(2 * this.ivMinChunkSize);
  private byte[] singleByteBuffer = new byte[1];

  public ChunkedOutputStream(OutputStream paramOutputStream)
  {
  }

  private void bufferedWrite(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    checkClosed();
    this.ivWriteBuffer.write(paramArrayOfByte, paramInt1, paramInt2);
    if (this.ivWriteBuffer.size() >= this.ivMinChunkSize)
      flushBuffer();
  }

  private void checkClosed()
  {
    if (this.ivClosed)
      throw new RuntimeException("Stream is closed already");
  }

  private void flushBuffer()
    throws IOException
  {
    writeChunk(this.ivWriteBuffer.getByteArray(), 0, this.ivWriteBuffer.size());
    this.ivWriteBuffer.reset();
  }

  private void writeChunk(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.ivOut.write(paramArrayOfByte, paramInt1, paramInt2);
    this.ivOut.flush();
  }

  public void close()
    throws IOException
  {
    this.ivClosed = true;
    this.ivOut.close();
  }

  public void flush()
    throws IOException
  {
    monitorenter;
    try
    {
      flushBuffer();
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
      this.singleByteBuffer[0] = (byte)paramInt;
      bufferedWrite(this.singleByteBuffer, 0, 1);
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
      bufferedWrite(paramArrayOfByte, 0, paramArrayOfByte.length);
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
      bufferedWrite(paramArrayOfByte, paramInt1, paramInt2);
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

  public void writeLastChunk()
    throws IOException
  {
    monitorenter;
    try
    {
      checkClosed();
      flushBuffer();
      this.ivOut.flush();
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
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.ChunkedOutputStream
 * JD-Core Version:    0.6.0
 */