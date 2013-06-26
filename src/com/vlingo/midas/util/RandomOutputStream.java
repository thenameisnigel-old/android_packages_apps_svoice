package com.vlingo.midas.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class RandomOutputStream extends OutputStream
{
  RandomAccessFile randomFile;

  public RandomOutputStream(RandomAccessFile paramRandomAccessFile)
  {
    this.randomFile = paramRandomAccessFile;
  }

  public void close()
    throws IOException
  {
    this.randomFile.close();
  }

  public long getFilePointer()
    throws IOException
  {
    return this.randomFile.getFilePointer();
  }

  public void seek(long paramLong)
    throws IOException
  {
    this.randomFile.seek(paramLong);
  }

  public void write(int paramInt)
    throws IOException
  {
    this.randomFile.write(paramInt);
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    this.randomFile.write(paramArrayOfByte);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.randomFile.write(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.RandomOutputStream
 * JD-Core Version:    0.6.0
 */