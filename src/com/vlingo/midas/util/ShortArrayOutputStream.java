package com.vlingo.midas.util;

import java.io.IOException;
import java.io.OutputStream;

public class ShortArrayOutputStream extends OutputStream
{
  protected short[] buf;
  protected int count;

  public ShortArrayOutputStream()
  {
    this.buf = new short[50000];
  }

  public ShortArrayOutputStream(int paramInt)
  {
    if (paramInt >= 0)
    {
      this.buf = new short[paramInt];
      return;
    }
    throw new IllegalArgumentException("size < 0");
  }

  private void expand(int paramInt)
  {
    if (paramInt + this.count <= this.buf.length);
    while (true)
    {
      return;
      short[] arrayOfShort = new short[2 * (paramInt + this.count)];
      System.arraycopy(this.buf, 0, arrayOfShort, 0, this.count);
      this.buf = arrayOfShort;
    }
  }

  public void close()
    throws IOException
  {
    super.close();
  }

  public int size()
  {
    return this.count;
  }

  public short[] toShortArray()
  {
    monitorenter;
    try
    {
      short[] arrayOfShort = new short[this.count];
      System.arraycopy(this.buf, 0, arrayOfShort, 0, this.count);
      monitorexit;
      return arrayOfShort;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void write(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.count == this.buf.length)
        expand(1);
      short[] arrayOfShort = this.buf;
      int i = this.count;
      this.count = (i + 1);
      arrayOfShort[i] = (short)paramInt;
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

  public void write(short[] paramArrayOfShort)
  {
    monitorenter;
    try
    {
      write(paramArrayOfShort, 0, paramArrayOfShort.length);
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

  public void write(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    monitorenter;
    if (paramInt2 == 0);
    while (true)
    {
      monitorexit;
      return;
      try
      {
        expand(paramInt2);
        System.arraycopy(paramArrayOfShort, paramInt1, this.buf, this.count, paramInt2);
        this.count = (paramInt2 + this.count);
      }
      finally
      {
        monitorexit;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.ShortArrayOutputStream
 * JD-Core Version:    0.6.0
 */