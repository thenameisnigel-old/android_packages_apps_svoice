package com.vlingo.core.internal.util;

public class CircularShortBlockBuffer
{
  private int mBlockSize;
  private int mBlockWriteIndex;
  private boolean mIsBufferFilled;
  private short[] mShortArray;

  public CircularShortBlockBuffer(int paramInt1, int paramInt2)
  {
    this.mShortArray = new short[paramInt1 * paramInt2];
    this.mBlockSize = paramInt1;
  }

  public short[] getArray()
  {
    int i = 0;
    short[] arrayOfShort;
    if (this.mIsBufferFilled)
    {
      arrayOfShort = new short[this.mShortArray.length];
      System.arraycopy(this.mShortArray, this.mBlockWriteIndex, arrayOfShort, 0, this.mShortArray.length - this.mBlockWriteIndex);
      i = 0 + (this.mShortArray.length - this.mBlockWriteIndex);
    }
    while (true)
    {
      System.arraycopy(this.mShortArray, 0, arrayOfShort, i, this.mBlockWriteIndex);
      return arrayOfShort;
      arrayOfShort = new short[this.mBlockWriteIndex];
    }
  }

  public void write(short[] paramArrayOfShort)
  {
    if (paramArrayOfShort.length != this.mBlockSize)
      throw new IllegalArgumentException("Wrong size blocksize");
    System.arraycopy(paramArrayOfShort, 0, this.mShortArray, this.mBlockWriteIndex, paramArrayOfShort.length);
    this.mBlockWriteIndex += paramArrayOfShort.length;
    if (this.mBlockWriteIndex >= this.mShortArray.length)
    {
      this.mBlockWriteIndex = 0;
      this.mIsBufferFilled = true;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.CircularShortBlockBuffer
 * JD-Core Version:    0.6.0
 */