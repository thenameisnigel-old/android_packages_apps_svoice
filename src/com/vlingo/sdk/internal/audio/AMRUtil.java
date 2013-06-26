package com.vlingo.sdk.internal.audio;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AMRUtil
{
  private static final byte[] AMR_HEADER = new byte[6];
  private static final int FRAME_HEADER = 4;
  private static final int FRAME_SIZE = 13;
  private static final int FRAME_TIME = 20;
  private static final short[] frameSizes;
  private static final byte[] magicNum;

  static
  {
    AMR_HEADER[0] = 35;
    AMR_HEADER[1] = 33;
    AMR_HEADER[2] = 65;
    AMR_HEADER[3] = 77;
    AMR_HEADER[4] = 82;
    AMR_HEADER[5] = 10;
    byte[] arrayOfByte = new byte[6];
    arrayOfByte[0] = 35;
    arrayOfByte[1] = 33;
    arrayOfByte[2] = 65;
    arrayOfByte[3] = 77;
    arrayOfByte[4] = 82;
    arrayOfByte[5] = 10;
    magicNum = arrayOfByte;
    short[] arrayOfShort = new short[16];
    arrayOfShort[0] = 12;
    arrayOfShort[1] = 13;
    arrayOfShort[2] = 15;
    arrayOfShort[3] = 17;
    arrayOfShort[4] = 19;
    arrayOfShort[5] = 20;
    arrayOfShort[6] = 26;
    arrayOfShort[7] = 31;
    arrayOfShort[8] = 5;
    arrayOfShort[9] = 0;
    arrayOfShort[10] = 0;
    arrayOfShort[11] = 0;
    arrayOfShort[12] = 0;
    arrayOfShort[13] = 0;
    arrayOfShort[14] = 0;
    arrayOfShort[15] = 0;
    frameSizes = arrayOfShort;
  }

  public static byte[] addPaddingToAMR(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    if (!isAMRAudioOK(paramArrayOfByte, paramInt1, paramInt2));
    while (true)
    {
      return paramArrayOfByte;
      int i = paramInt3 * 13 / 20;
      byte[] arrayOfByte = new byte[paramInt2 + i * 2];
      System.arraycopy(AMR_HEADER, 0, arrayOfByte, 0, AMR_HEADER.length);
      for (int j = AMR_HEADER.length; j < i; j += 13)
        arrayOfByte[j] = 4;
      System.arraycopy(paramArrayOfByte, paramInt1 + AMR_HEADER.length, arrayOfByte, i + AMR_HEADER.length, paramInt2 - AMR_HEADER.length);
      for (int k = paramInt2 + (i + AMR_HEADER.length); k < arrayOfByte.length; k += 13)
        arrayOfByte[k] = 4;
      paramArrayOfByte = arrayOfByte;
    }
  }

  public static boolean isAMRAudioOK(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i;
    if ((paramArrayOfByte == null) || (paramInt2 < 10))
      i = 0;
    label52: 
    while (true)
    {
      return i;
      i = 1;
      for (int j = 0; ; j++)
      {
        if (j >= AMR_HEADER.length)
          break label52;
        if (AMR_HEADER[j] == paramArrayOfByte[(j + paramInt1)])
          continue;
        i = 0;
        break;
      }
    }
  }

  public static byte[] readInAMRMaxFrames(InputStream paramInputStream, int paramInt)
  {
    Object localObject;
    if (paramInt < 1)
      localObject = null;
    while (true)
    {
      return localObject;
      int i = paramInt / 20;
      localObject = null;
      DataInputStream localDataInputStream = new DataInputStream(paramInputStream);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(500);
      byte[] arrayOfByte1 = new byte[500];
      int k;
      while (true)
      {
        int m;
        try
        {
          int j = localDataInputStream.read(arrayOfByte1, 0, 6);
          k = 0 + j;
          m = 0;
          if (m >= 6)
            break label110;
          if (arrayOfByte1[m] == magicNum[m])
            break label104;
          localObject = null;
        }
        catch (IOException localIOException1)
        {
          localIOException1.printStackTrace();
          localObject = null;
        }
        break;
        label104: m++;
      }
      label110: localByteArrayOutputStream.write(arrayOfByte1, 0, 6);
      int n = 0;
      try
      {
        int i1 = localDataInputStream.read();
        int i2 = 0;
        while ((n < i) && (i1 != -1))
        {
          arrayOfByte1[i2] = (byte)i1;
          int i3 = 0 + 1;
          int i4 = 0xF & i1 >> 3;
          int i5 = frameSizes[i4];
          if (i5 != 0)
          {
            i3 = 1 + localDataInputStream.read(arrayOfByte1, i3, i5);
            n++;
          }
          localByteArrayOutputStream.write(arrayOfByte1, 0, i5 + 1);
          k += i3;
          i2 = 0;
          int i6 = localDataInputStream.read();
          i1 = i6;
          if ((i1 == -1) && (n < i))
            continue;
        }
      }
      catch (IOException localIOException2)
      {
        localIOException2.printStackTrace();
        try
        {
          localDataInputStream.close();
          localByteArrayOutputStream.flush();
          byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
          localObject = arrayOfByte2;
        }
        catch (IOException localIOException3)
        {
          localIOException3.printStackTrace();
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.audio.AMRUtil
 * JD-Core Version:    0.6.0
 */