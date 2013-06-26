package com.vlingo.sdk.internal.util;

public class CRC32
{
  private static int[] crc_table = make_crc_table();
  private int crc = 0;

  private static int[] make_crc_table()
  {
    int[] arrayOfInt = new int[256];
    for (int i = 0; i < 256; i++)
    {
      int j = i;
      int k = 8;
      while (true)
      {
        k--;
        if (k < 0)
          break;
        if ((j & 0x1) != 0)
        {
          j = 0xEDB88320 ^ j >>> 1;
          continue;
        }
        j >>>= 1;
      }
      arrayOfInt[i] = j;
    }
    return arrayOfInt;
  }

  public long getValue()
  {
    return 0xFFFFFFFF & this.crc;
  }

  public void reset()
  {
    this.crc = 0;
  }

  public void update(int paramInt)
  {
    int i = 0xFFFFFFFF ^ this.crc;
    this.crc = (0xFFFFFFFF ^ (crc_table[(0xFF & (i ^ paramInt))] ^ i >>> 8));
  }

  public void update(byte[] paramArrayOfByte)
  {
    update(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void update(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = 0xFFFFFFFF ^ this.crc;
    int k;
    for (int j = paramInt1; ; j = k)
    {
      paramInt2--;
      if (paramInt2 < 0)
        break;
      int[] arrayOfInt = crc_table;
      k = j + 1;
      i = arrayOfInt[(0xFF & (i ^ paramArrayOfByte[j]))] ^ i >>> 8;
    }
    this.crc = (i ^ 0xFFFFFFFF);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.CRC32
 * JD-Core Version:    0.6.0
 */