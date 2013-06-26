package com.vlingo.midas.util;

import java.io.IOException;
import java.io.RandomAccessFile;

public class WAVFileWriter extends RandomOutputStream
{
  int mBytesPerSample = 2;
  long mDataSizePosition = 0L;
  long mRiffSizePosition = 0L;

  public WAVFileWriter(RandomAccessFile paramRandomAccessFile)
    throws IOException
  {
    super(paramRandomAccessFile);
  }

  private void write(short[] paramArrayOfShort)
    throws IOException
  {
    byte[] arrayOfByte = new byte[2 * paramArrayOfShort.length];
    for (int i = 0; i < paramArrayOfShort.length; i++)
    {
      int j = paramArrayOfShort[i];
      arrayOfByte[(i * 2)] = (byte)(j & 0xFF);
      arrayOfByte[(1 + i * 2)] = (byte)(0xFF & j >> 8);
    }
    write(arrayOfByte);
  }

  private void writeDataChunk(short[] paramArrayOfShort)
    throws IOException
  {
    writeDataChunkHeader(2 * paramArrayOfShort.length);
    write(paramArrayOfShort);
  }

  private void writeDataChunkHeader(int paramInt)
    throws IOException
  {
    write(100);
    write(97);
    write(116);
    write(97);
    this.mDataSizePosition = getFilePointer();
    writeIntLittle(paramInt);
  }

  private void writeFormatChunk(int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    this.mBytesPerSample = ((paramInt1 + 7) / 8);
    write(102);
    write(109);
    write(116);
    write(32);
    writeIntLittle(16);
    writeShortLittle(1);
    writeShortLittle((short)paramInt2);
    writeIntLittle(paramInt3);
    writeIntLittle(paramInt3 * paramInt2 * this.mBytesPerSample);
    writeShortLittle((short)(paramInt2 * this.mBytesPerSample));
    writeShortLittle((short)paramInt1);
  }

  private void writeHeader(int paramInt)
    throws IOException
  {
    write(82);
    write(73);
    write(70);
    write(70);
    this.mRiffSizePosition = getFilePointer();
    writeIntLittle(paramInt);
    write(87);
    write(65);
    write(86);
    write(69);
  }

  private void writeIntLittle(int paramInt)
    throws IOException
  {
    write(paramInt & 0xFF);
    write(0xFF & paramInt >> 8);
    write(0xFF & paramInt >> 16);
    write(0xFF & paramInt >> 24);
  }

  private void writeShortLittle(short paramShort)
    throws IOException
  {
    write(paramShort & 0xFF);
    write(0xFF & paramShort >> 8);
  }

  public void write(short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws IOException
  {
    writeHeader(24 + (4 + (8 + 2 * paramArrayOfShort.length)));
    writeFormatChunk(16, paramInt1, paramInt2);
    writeDataChunk(paramArrayOfShort);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.WAVFileWriter
 * JD-Core Version:    0.6.0
 */