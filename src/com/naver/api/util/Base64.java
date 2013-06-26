package com.naver.api.util;

public class Base64
{
  private static final char[] INT_TO_BASE64;

  static
  {
    char[] arrayOfChar = new char[64];
    arrayOfChar[0] = 65;
    arrayOfChar[1] = 66;
    arrayOfChar[2] = 67;
    arrayOfChar[3] = 68;
    arrayOfChar[4] = 69;
    arrayOfChar[5] = 70;
    arrayOfChar[6] = 71;
    arrayOfChar[7] = 72;
    arrayOfChar[8] = 73;
    arrayOfChar[9] = 74;
    arrayOfChar[10] = 75;
    arrayOfChar[11] = 76;
    arrayOfChar[12] = 77;
    arrayOfChar[13] = 78;
    arrayOfChar[14] = 79;
    arrayOfChar[15] = 80;
    arrayOfChar[16] = 81;
    arrayOfChar[17] = 82;
    arrayOfChar[18] = 83;
    arrayOfChar[19] = 84;
    arrayOfChar[20] = 85;
    arrayOfChar[21] = 86;
    arrayOfChar[22] = 87;
    arrayOfChar[23] = 88;
    arrayOfChar[24] = 89;
    arrayOfChar[25] = 90;
    arrayOfChar[26] = 97;
    arrayOfChar[27] = 98;
    arrayOfChar[28] = 99;
    arrayOfChar[29] = 100;
    arrayOfChar[30] = 101;
    arrayOfChar[31] = 102;
    arrayOfChar[32] = 103;
    arrayOfChar[33] = 104;
    arrayOfChar[34] = 105;
    arrayOfChar[35] = 106;
    arrayOfChar[36] = 107;
    arrayOfChar[37] = 108;
    arrayOfChar[38] = 109;
    arrayOfChar[39] = 110;
    arrayOfChar[40] = 111;
    arrayOfChar[41] = 112;
    arrayOfChar[42] = 113;
    arrayOfChar[43] = 114;
    arrayOfChar[44] = 115;
    arrayOfChar[45] = 116;
    arrayOfChar[46] = 117;
    arrayOfChar[47] = 118;
    arrayOfChar[48] = 119;
    arrayOfChar[49] = 120;
    arrayOfChar[50] = 121;
    arrayOfChar[51] = 122;
    arrayOfChar[52] = 48;
    arrayOfChar[53] = 49;
    arrayOfChar[54] = 50;
    arrayOfChar[55] = 51;
    arrayOfChar[56] = 52;
    arrayOfChar[57] = 53;
    arrayOfChar[58] = 54;
    arrayOfChar[59] = 55;
    arrayOfChar[60] = 56;
    arrayOfChar[61] = 57;
    arrayOfChar[62] = 43;
    arrayOfChar[63] = 47;
    INT_TO_BASE64 = arrayOfChar;
  }

  public static String encodeBase64(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    int j = i / 3;
    int k = i - j * 3;
    StringBuffer localStringBuffer = new StringBuffer(4 * ((i + 2) / 3));
    char[] arrayOfChar = INT_TO_BASE64;
    int m = 0;
    int n = 0;
    int i7;
    int i8;
    if (m >= j)
    {
      if (k == 0)
        break label303;
      i7 = n + 1;
      i8 = 0xFF & paramArrayOfByte[n];
      localStringBuffer.append(arrayOfChar[(i8 >> 2)]);
      if (k != 1)
        break label242;
      localStringBuffer.append(arrayOfChar[(0x3F & i8 << 4)]);
      localStringBuffer.append("==");
    }
    label303: 
    while (true)
    {
      return localStringBuffer.toString();
      int i1 = n + 1;
      int i2 = 0xFF & paramArrayOfByte[n];
      int i3 = i1 + 1;
      int i4 = 0xFF & paramArrayOfByte[i1];
      int i5 = i3 + 1;
      int i6 = 0xFF & paramArrayOfByte[i3];
      localStringBuffer.append(arrayOfChar[(i2 >> 2)]);
      localStringBuffer.append(arrayOfChar[(0x3F & i2 << 4 | i4 >> 4)]);
      localStringBuffer.append(arrayOfChar[(0x3F & i4 << 2 | i6 >> 6)]);
      localStringBuffer.append(arrayOfChar[(i6 & 0x3F)]);
      m++;
      n = i5;
      break;
      label242: n = i7 + 1;
      int i9 = 0xFF & paramArrayOfByte[i7];
      localStringBuffer.append(arrayOfChar[(0x3F & i8 << 4 | i9 >> 4)]);
      localStringBuffer.append(arrayOfChar[(0x3F & i9 << 2)]);
      localStringBuffer.append('=');
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.naver.api.util.Base64
 * JD-Core Version:    0.6.0
 */