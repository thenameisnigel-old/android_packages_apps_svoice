package com.vlingo.sdk.internal.audio;

import java.io.IOException;
import java.io.InputStream;

public class RawInputStreamReader
{
  byte[] mInputBuffer;
  int mInputBufferOffset;
  InputStream mInputStream;
  boolean mIsBEInputStream;

  public RawInputStreamReader(InputStream paramInputStream, boolean paramBoolean, int paramInt)
  {
    this.mInputStream = paramInputStream;
    this.mIsBEInputStream = paramBoolean;
    this.mInputBuffer = new byte[paramInt * 2];
    this.mInputBufferOffset = 0;
  }

  public int read(short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt2 * 2;
    if (i > this.mInputBuffer.length)
      i = this.mInputBuffer.length;
    int j = i - this.mInputBufferOffset;
    int k = this.mInputStream.read(this.mInputBuffer, this.mInputBufferOffset, j);
    int i3;
    if (k == -1)
    {
      i3 = -1;
      return i3;
    }
    int m = k + this.mInputBufferOffset;
    int n = m - m % 2;
    int i1;
    int i2;
    label95: int i5;
    int i4;
    if (n != m)
    {
      i1 = 1;
      i2 = 0;
      if (i2 >= n)
        break label195;
      if (!this.mIsBEInputStream)
        break label164;
      i5 = 0xFF & this.mInputBuffer[i2];
      i4 = 0xFF & this.mInputBuffer[(i2 + 1)];
    }
    while (true)
    {
      paramArrayOfShort[paramInt1] = (short)(i4 | i5 << 8);
      paramInt1++;
      i2 += 2;
      break label95;
      i1 = 0;
      break;
      label164: i4 = 0xFF & this.mInputBuffer[i2];
      i5 = 0xFF & this.mInputBuffer[(i2 + 1)];
    }
    label195: if (i1 != 0)
      this.mInputBuffer[0] = this.mInputBuffer[(m - 1)];
    for (this.mInputBufferOffset = 1; ; this.mInputBufferOffset = 0)
    {
      i3 = n / 2;
      break;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.audio.RawInputStreamReader
 * JD-Core Version:    0.6.0
 */