package com.vlingo.core.internal.audio;

public class AMRUtil
{
  private static final byte[] AMR_HEADER = new byte[6];
  private static final int FRAME_HEADER = 4;
  private static final int FRAME_SIZE = 13;
  private static final int FRAME_TIME = 20;

  static
  {
    AMR_HEADER[0] = 35;
    AMR_HEADER[1] = 33;
    AMR_HEADER[2] = 65;
    AMR_HEADER[3] = 77;
    AMR_HEADER[4] = 82;
    AMR_HEADER[5] = 10;
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AMRUtil
 * JD-Core Version:    0.6.0
 */