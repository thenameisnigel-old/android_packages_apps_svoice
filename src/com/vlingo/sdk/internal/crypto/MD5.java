package com.vlingo.sdk.internal.crypto;

import com.vlingo.sdk.internal.util.StringUtils;

public class MD5
  implements HashFunction
{
  private static final char[] HEX_CHARS;
  private static final byte[] padding;
  private MD5State finals = null;
  private MD5State state = new MD5State();

  static
  {
    char[] arrayOfChar = new char[16];
    arrayOfChar[0] = 48;
    arrayOfChar[1] = 49;
    arrayOfChar[2] = 50;
    arrayOfChar[3] = 51;
    arrayOfChar[4] = 52;
    arrayOfChar[5] = 53;
    arrayOfChar[6] = 54;
    arrayOfChar[7] = 55;
    arrayOfChar[8] = 56;
    arrayOfChar[9] = 57;
    arrayOfChar[10] = 97;
    arrayOfChar[11] = 98;
    arrayOfChar[12] = 99;
    arrayOfChar[13] = 100;
    arrayOfChar[14] = 101;
    arrayOfChar[15] = 102;
    HEX_CHARS = arrayOfChar;
    byte[] arrayOfByte = new byte[64];
    arrayOfByte[0] = -128;
    arrayOfByte[1] = 0;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = 0;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 0;
    arrayOfByte[7] = 0;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = 0;
    arrayOfByte[10] = 0;
    arrayOfByte[11] = 0;
    arrayOfByte[12] = 0;
    arrayOfByte[13] = 0;
    arrayOfByte[14] = 0;
    arrayOfByte[15] = 0;
    arrayOfByte[16] = 0;
    arrayOfByte[17] = 0;
    arrayOfByte[18] = 0;
    arrayOfByte[19] = 0;
    arrayOfByte[20] = 0;
    arrayOfByte[21] = 0;
    arrayOfByte[22] = 0;
    arrayOfByte[23] = 0;
    arrayOfByte[24] = 0;
    arrayOfByte[25] = 0;
    arrayOfByte[26] = 0;
    arrayOfByte[27] = 0;
    arrayOfByte[28] = 0;
    arrayOfByte[29] = 0;
    arrayOfByte[30] = 0;
    arrayOfByte[31] = 0;
    arrayOfByte[32] = 0;
    arrayOfByte[33] = 0;
    arrayOfByte[34] = 0;
    arrayOfByte[35] = 0;
    arrayOfByte[36] = 0;
    arrayOfByte[37] = 0;
    arrayOfByte[38] = 0;
    arrayOfByte[39] = 0;
    arrayOfByte[40] = 0;
    arrayOfByte[41] = 0;
    arrayOfByte[42] = 0;
    arrayOfByte[43] = 0;
    arrayOfByte[44] = 0;
    arrayOfByte[45] = 0;
    arrayOfByte[46] = 0;
    arrayOfByte[47] = 0;
    arrayOfByte[48] = 0;
    arrayOfByte[49] = 0;
    arrayOfByte[50] = 0;
    arrayOfByte[51] = 0;
    arrayOfByte[52] = 0;
    arrayOfByte[53] = 0;
    arrayOfByte[54] = 0;
    arrayOfByte[55] = 0;
    arrayOfByte[56] = 0;
    arrayOfByte[57] = 0;
    arrayOfByte[58] = 0;
    arrayOfByte[59] = 0;
    arrayOfByte[60] = 0;
    arrayOfByte[61] = 0;
    arrayOfByte[62] = 0;
    arrayOfByte[63] = 0;
    padding = arrayOfByte;
  }

  public MD5()
  {
    this(new byte[0]);
  }

  public MD5(String paramString)
  {
    this(StringUtils.convertStringToBytes(paramString));
  }

  public MD5(byte[] paramArrayOfByte)
  {
    update(paramArrayOfByte);
  }

  private final void decode(byte[] paramArrayOfByte, int paramInt, int[] paramArrayOfInt)
  {
    paramArrayOfInt[0] = (0xFF & paramArrayOfByte[paramInt] | (0xFF & paramArrayOfByte[(paramInt + 1)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 2)]) << 16 | paramArrayOfByte[(paramInt + 3)] << 24);
    paramArrayOfInt[1] = (0xFF & paramArrayOfByte[(paramInt + 4)] | (0xFF & paramArrayOfByte[(paramInt + 5)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 6)]) << 16 | paramArrayOfByte[(paramInt + 7)] << 24);
    paramArrayOfInt[2] = (0xFF & paramArrayOfByte[(paramInt + 8)] | (0xFF & paramArrayOfByte[(paramInt + 9)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 10)]) << 16 | paramArrayOfByte[(paramInt + 11)] << 24);
    paramArrayOfInt[3] = (0xFF & paramArrayOfByte[(paramInt + 12)] | (0xFF & paramArrayOfByte[(paramInt + 13)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 14)]) << 16 | paramArrayOfByte[(paramInt + 15)] << 24);
    paramArrayOfInt[4] = (0xFF & paramArrayOfByte[(paramInt + 16)] | (0xFF & paramArrayOfByte[(paramInt + 17)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 18)]) << 16 | paramArrayOfByte[(paramInt + 19)] << 24);
    paramArrayOfInt[5] = (0xFF & paramArrayOfByte[(paramInt + 20)] | (0xFF & paramArrayOfByte[(paramInt + 21)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 22)]) << 16 | paramArrayOfByte[(paramInt + 23)] << 24);
    paramArrayOfInt[6] = (0xFF & paramArrayOfByte[(paramInt + 24)] | (0xFF & paramArrayOfByte[(paramInt + 25)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 26)]) << 16 | paramArrayOfByte[(paramInt + 27)] << 24);
    paramArrayOfInt[7] = (0xFF & paramArrayOfByte[(paramInt + 28)] | (0xFF & paramArrayOfByte[(paramInt + 29)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 30)]) << 16 | paramArrayOfByte[(paramInt + 31)] << 24);
    paramArrayOfInt[8] = (0xFF & paramArrayOfByte[(paramInt + 32)] | (0xFF & paramArrayOfByte[(paramInt + 33)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 34)]) << 16 | paramArrayOfByte[(paramInt + 35)] << 24);
    paramArrayOfInt[9] = (0xFF & paramArrayOfByte[(paramInt + 36)] | (0xFF & paramArrayOfByte[(paramInt + 37)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 38)]) << 16 | paramArrayOfByte[(paramInt + 39)] << 24);
    paramArrayOfInt[10] = (0xFF & paramArrayOfByte[(paramInt + 40)] | (0xFF & paramArrayOfByte[(paramInt + 41)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 42)]) << 16 | paramArrayOfByte[(paramInt + 43)] << 24);
    paramArrayOfInt[11] = (0xFF & paramArrayOfByte[(paramInt + 44)] | (0xFF & paramArrayOfByte[(paramInt + 45)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 46)]) << 16 | paramArrayOfByte[(paramInt + 47)] << 24);
    paramArrayOfInt[12] = (0xFF & paramArrayOfByte[(paramInt + 48)] | (0xFF & paramArrayOfByte[(paramInt + 49)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 50)]) << 16 | paramArrayOfByte[(paramInt + 51)] << 24);
    paramArrayOfInt[13] = (0xFF & paramArrayOfByte[(paramInt + 52)] | (0xFF & paramArrayOfByte[(paramInt + 53)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 54)]) << 16 | paramArrayOfByte[(paramInt + 55)] << 24);
    paramArrayOfInt[14] = (0xFF & paramArrayOfByte[(paramInt + 56)] | (0xFF & paramArrayOfByte[(paramInt + 57)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 58)]) << 16 | paramArrayOfByte[(paramInt + 59)] << 24);
    paramArrayOfInt[15] = (0xFF & paramArrayOfByte[(paramInt + 60)] | (0xFF & paramArrayOfByte[(paramInt + 61)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 62)]) << 16 | paramArrayOfByte[(paramInt + 63)] << 24);
  }

  private static final byte[] encode(int[] paramArrayOfInt, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    int j = 0;
    while (i < paramInt)
    {
      arrayOfByte[i] = (byte)(0xFF & paramArrayOfInt[j]);
      arrayOfByte[(i + 1)] = (byte)(0xFF & paramArrayOfInt[j] >>> 8);
      arrayOfByte[(i + 2)] = (byte)(0xFF & paramArrayOfInt[j] >>> 16);
      arrayOfByte[(i + 3)] = (byte)(0xFF & paramArrayOfInt[j] >>> 24);
      j++;
      i += 4;
    }
    return arrayOfByte;
  }

  public static final boolean equals(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = 1;
    int j = 0;
    if (paramArrayOfByte1 == null)
      if (paramArrayOfByte2 == null)
        j = i;
    while (true)
    {
      return j;
      i = 0;
      break;
      if (paramArrayOfByte2 == null)
        continue;
      int k = 16;
      if (paramArrayOfByte1.length < 16)
      {
        if (paramArrayOfByte2.length != paramArrayOfByte1.length)
          continue;
        k = paramArrayOfByte1.length;
      }
      do
        for (int m = 0; ; m++)
        {
          if (m >= k)
            break label84;
          if (paramArrayOfByte1[m] != paramArrayOfByte2[m])
            break;
        }
      while (paramArrayOfByte2.length >= 16);
      continue;
      label84: j = i;
    }
  }

  public static final String toBase64(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    char[] arrayOfChar2 = new char[4 * ((2 + paramArrayOfByte.length) / 3)];
    int i = 0;
    int j = 0;
    if (i < paramArrayOfByte.length)
    {
      int k = 0;
      int m = 0;
      int n = (0xFF & paramArrayOfByte[i]) << 8;
      if (i + 1 < paramArrayOfByte.length)
      {
        n |= 0xFF & paramArrayOfByte[(i + 1)];
        m = 1;
      }
      int i1 = n << 8;
      if (i + 2 < paramArrayOfByte.length)
      {
        i1 |= 0xFF & paramArrayOfByte[(i + 2)];
        k = 1;
      }
      int i2 = j + 3;
      int i3;
      label121: int i4;
      int i5;
      if (k != 0)
      {
        i3 = i1 & 0x3F;
        arrayOfChar2[i2] = arrayOfChar1[i3];
        i4 = i1 >> 6;
        i5 = j + 2;
        if (m == 0)
          break label218;
      }
      label218: for (int i6 = i4 & 0x3F; ; i6 = 64)
      {
        arrayOfChar2[i5] = arrayOfChar1[i6];
        int i7 = i4 >> 6;
        arrayOfChar2[(j + 1)] = arrayOfChar1[(i7 & 0x3F)];
        int i8 = i7 >> 6;
        arrayOfChar2[(j + 0)] = arrayOfChar1[(i8 & 0x3F)];
        i += 3;
        j += 4;
        break;
        i3 = 64;
        break label121;
      }
    }
    return new String(arrayOfChar2);
  }

  public static final String toHex(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[2 * paramArrayOfByte.length];
    int i = 0;
    int j = 0;
    while (i < paramArrayOfByte.length)
    {
      int k = j + 1;
      arrayOfChar[j] = HEX_CHARS[(0xF & paramArrayOfByte[i] >>> 4)];
      j = k + 1;
      arrayOfChar[k] = HEX_CHARS[(0xF & paramArrayOfByte[i])];
      i++;
    }
    return new String(arrayOfChar);
  }

  private final void transform(MD5State paramMD5State, byte[] paramArrayOfByte, int paramInt, int[] paramArrayOfInt)
  {
    int i = paramMD5State.state[0];
    int j = paramMD5State.state[1];
    int k = paramMD5State.state[2];
    int m = paramMD5State.state[3];
    decode(paramArrayOfByte, paramInt, paramArrayOfInt);
    int n = i + (-680876936 + ((j & k | m & (j ^ 0xFFFFFFFF)) + paramArrayOfInt[0]));
    int i1 = j + (n << 7 | n >>> 25);
    int i2 = m + (-389564586 + ((i1 & j | k & (i1 ^ 0xFFFFFFFF)) + paramArrayOfInt[1]));
    int i3 = i1 + (i2 << 12 | i2 >>> 20);
    int i4 = k + (606105819 + ((i3 & i1 | j & (i3 ^ 0xFFFFFFFF)) + paramArrayOfInt[2]));
    int i5 = i3 + (i4 << 17 | i4 >>> 15);
    int i6 = j + (-1044525330 + ((i5 & i3 | i1 & (i5 ^ 0xFFFFFFFF)) + paramArrayOfInt[3]));
    int i7 = i5 + (i6 << 22 | i6 >>> 10);
    int i8 = i1 + (-176418897 + ((i7 & i5 | i3 & (i7 ^ 0xFFFFFFFF)) + paramArrayOfInt[4]));
    int i9 = i7 + (i8 << 7 | i8 >>> 25);
    int i10 = i3 + (1200080426 + ((i9 & i7 | i5 & (i9 ^ 0xFFFFFFFF)) + paramArrayOfInt[5]));
    int i11 = i9 + (i10 << 12 | i10 >>> 20);
    int i12 = i5 + (-1473231341 + ((i11 & i9 | i7 & (i11 ^ 0xFFFFFFFF)) + paramArrayOfInt[6]));
    int i13 = i11 + (i12 << 17 | i12 >>> 15);
    int i14 = i7 + (-45705983 + ((i13 & i11 | i9 & (i13 ^ 0xFFFFFFFF)) + paramArrayOfInt[7]));
    int i15 = i13 + (i14 << 22 | i14 >>> 10);
    int i16 = i9 + (1770035416 + ((i15 & i13 | i11 & (i15 ^ 0xFFFFFFFF)) + paramArrayOfInt[8]));
    int i17 = i15 + (i16 << 7 | i16 >>> 25);
    int i18 = i11 + (-1958414417 + ((i17 & i15 | i13 & (i17 ^ 0xFFFFFFFF)) + paramArrayOfInt[9]));
    int i19 = i17 + (i18 << 12 | i18 >>> 20);
    int i20 = i13 + (-42063 + ((i19 & i17 | i15 & (i19 ^ 0xFFFFFFFF)) + paramArrayOfInt[10]));
    int i21 = i19 + (i20 << 17 | i20 >>> 15);
    int i22 = i15 + (-1990404162 + ((i21 & i19 | i17 & (i21 ^ 0xFFFFFFFF)) + paramArrayOfInt[11]));
    int i23 = i21 + (i22 << 22 | i22 >>> 10);
    int i24 = i17 + (1804603682 + ((i23 & i21 | i19 & (i23 ^ 0xFFFFFFFF)) + paramArrayOfInt[12]));
    int i25 = i23 + (i24 << 7 | i24 >>> 25);
    int i26 = i19 + (-40341101 + ((i25 & i23 | i21 & (i25 ^ 0xFFFFFFFF)) + paramArrayOfInt[13]));
    int i27 = i25 + (i26 << 12 | i26 >>> 20);
    int i28 = i21 + (-1502002290 + ((i27 & i25 | i23 & (i27 ^ 0xFFFFFFFF)) + paramArrayOfInt[14]));
    int i29 = i27 + (i28 << 17 | i28 >>> 15);
    int i30 = i23 + (1236535329 + ((i29 & i27 | i25 & (i29 ^ 0xFFFFFFFF)) + paramArrayOfInt[15]));
    int i31 = i29 + (i30 << 22 | i30 >>> 10);
    int i32 = i25 + (-165796510 + ((i31 & i27 | i29 & (i27 ^ 0xFFFFFFFF)) + paramArrayOfInt[1]));
    int i33 = i31 + (i32 << 5 | i32 >>> 27);
    int i34 = i27 + (-1069501632 + ((i33 & i29 | i31 & (i29 ^ 0xFFFFFFFF)) + paramArrayOfInt[6]));
    int i35 = i33 + (i34 << 9 | i34 >>> 23);
    int i36 = i29 + (643717713 + ((i35 & i31 | i33 & (i31 ^ 0xFFFFFFFF)) + paramArrayOfInt[11]));
    int i37 = i35 + (i36 << 14 | i36 >>> 18);
    int i38 = i31 + (-373897302 + ((i37 & i33 | i35 & (i33 ^ 0xFFFFFFFF)) + paramArrayOfInt[0]));
    int i39 = i37 + (i38 << 20 | i38 >>> 12);
    int i40 = i33 + (-701558691 + ((i39 & i35 | i37 & (i35 ^ 0xFFFFFFFF)) + paramArrayOfInt[5]));
    int i41 = i39 + (i40 << 5 | i40 >>> 27);
    int i42 = i35 + (38016083 + ((i41 & i37 | i39 & (i37 ^ 0xFFFFFFFF)) + paramArrayOfInt[10]));
    int i43 = i41 + (i42 << 9 | i42 >>> 23);
    int i44 = i37 + (-660478335 + ((i43 & i39 | i41 & (i39 ^ 0xFFFFFFFF)) + paramArrayOfInt[15]));
    int i45 = i43 + (i44 << 14 | i44 >>> 18);
    int i46 = i39 + (-405537848 + ((i45 & i41 | i43 & (i41 ^ 0xFFFFFFFF)) + paramArrayOfInt[4]));
    int i47 = i45 + (i46 << 20 | i46 >>> 12);
    int i48 = i41 + (568446438 + ((i47 & i43 | i45 & (i43 ^ 0xFFFFFFFF)) + paramArrayOfInt[9]));
    int i49 = i47 + (i48 << 5 | i48 >>> 27);
    int i50 = i43 + (-1019803690 + ((i49 & i45 | i47 & (i45 ^ 0xFFFFFFFF)) + paramArrayOfInt[14]));
    int i51 = i49 + (i50 << 9 | i50 >>> 23);
    int i52 = i45 + (-187363961 + ((i51 & i47 | i49 & (i47 ^ 0xFFFFFFFF)) + paramArrayOfInt[3]));
    int i53 = i51 + (i52 << 14 | i52 >>> 18);
    int i54 = i47 + (1163531501 + ((i53 & i49 | i51 & (i49 ^ 0xFFFFFFFF)) + paramArrayOfInt[8]));
    int i55 = i53 + (i54 << 20 | i54 >>> 12);
    int i56 = i49 + (-1444681467 + ((i55 & i51 | i53 & (i51 ^ 0xFFFFFFFF)) + paramArrayOfInt[13]));
    int i57 = i55 + (i56 << 5 | i56 >>> 27);
    int i58 = i51 + (-51403784 + ((i57 & i53 | i55 & (i53 ^ 0xFFFFFFFF)) + paramArrayOfInt[2]));
    int i59 = i57 + (i58 << 9 | i58 >>> 23);
    int i60 = i53 + (1735328473 + ((i59 & i55 | i57 & (i55 ^ 0xFFFFFFFF)) + paramArrayOfInt[7]));
    int i61 = i59 + (i60 << 14 | i60 >>> 18);
    int i62 = i55 + (-1926607734 + ((i61 & i57 | i59 & (i57 ^ 0xFFFFFFFF)) + paramArrayOfInt[12]));
    int i63 = i61 + (i62 << 20 | i62 >>> 12);
    int i64 = i57 + (-378558 + ((i59 ^ (i63 ^ i61)) + paramArrayOfInt[5]));
    int i65 = i63 + (i64 << 4 | i64 >>> 28);
    int i66 = i59 + (-2022574463 + ((i61 ^ (i65 ^ i63)) + paramArrayOfInt[8]));
    int i67 = i65 + (i66 << 11 | i66 >>> 21);
    int i68 = i61 + (1839030562 + ((i63 ^ (i67 ^ i65)) + paramArrayOfInt[11]));
    int i69 = i67 + (i68 << 16 | i68 >>> 16);
    int i70 = i63 + (-35309556 + ((i65 ^ (i69 ^ i67)) + paramArrayOfInt[14]));
    int i71 = i69 + (i70 << 23 | i70 >>> 9);
    int i72 = i65 + (-1530992060 + ((i67 ^ (i71 ^ i69)) + paramArrayOfInt[1]));
    int i73 = i71 + (i72 << 4 | i72 >>> 28);
    int i74 = i67 + (1272893353 + ((i69 ^ (i73 ^ i71)) + paramArrayOfInt[4]));
    int i75 = i73 + (i74 << 11 | i74 >>> 21);
    int i76 = i69 + (-155497632 + ((i71 ^ (i75 ^ i73)) + paramArrayOfInt[7]));
    int i77 = i75 + (i76 << 16 | i76 >>> 16);
    int i78 = i71 + (-1094730640 + ((i73 ^ (i77 ^ i75)) + paramArrayOfInt[10]));
    int i79 = i77 + (i78 << 23 | i78 >>> 9);
    int i80 = i73 + (681279174 + ((i75 ^ (i79 ^ i77)) + paramArrayOfInt[13]));
    int i81 = i79 + (i80 << 4 | i80 >>> 28);
    int i82 = i75 + (-358537222 + ((i77 ^ (i81 ^ i79)) + paramArrayOfInt[0]));
    int i83 = i81 + (i82 << 11 | i82 >>> 21);
    int i84 = i77 + (-722521979 + ((i79 ^ (i83 ^ i81)) + paramArrayOfInt[3]));
    int i85 = i83 + (i84 << 16 | i84 >>> 16);
    int i86 = i79 + (76029189 + ((i81 ^ (i85 ^ i83)) + paramArrayOfInt[6]));
    int i87 = i85 + (i86 << 23 | i86 >>> 9);
    int i88 = i81 + (-640364487 + ((i83 ^ (i87 ^ i85)) + paramArrayOfInt[9]));
    int i89 = i87 + (i88 << 4 | i88 >>> 28);
    int i90 = i83 + (-421815835 + ((i85 ^ (i89 ^ i87)) + paramArrayOfInt[12]));
    int i91 = i89 + (i90 << 11 | i90 >>> 21);
    int i92 = i85 + (530742520 + ((i87 ^ (i91 ^ i89)) + paramArrayOfInt[15]));
    int i93 = i91 + (i92 << 16 | i92 >>> 16);
    int i94 = i87 + (-995338651 + ((i89 ^ (i93 ^ i91)) + paramArrayOfInt[2]));
    int i95 = i93 + (i94 << 23 | i94 >>> 9);
    int i96 = i89 + (-198630844 + ((i93 ^ (i95 | i91 ^ 0xFFFFFFFF)) + paramArrayOfInt[0]));
    int i97 = i95 + (i96 << 6 | i96 >>> 26);
    int i98 = i91 + (1126891415 + ((i95 ^ (i97 | i93 ^ 0xFFFFFFFF)) + paramArrayOfInt[7]));
    int i99 = i97 + (i98 << 10 | i98 >>> 22);
    int i100 = i93 + (-1416354905 + ((i97 ^ (i99 | i95 ^ 0xFFFFFFFF)) + paramArrayOfInt[14]));
    int i101 = i99 + (i100 << 15 | i100 >>> 17);
    int i102 = i95 + (-57434055 + ((i99 ^ (i101 | i97 ^ 0xFFFFFFFF)) + paramArrayOfInt[5]));
    int i103 = i101 + (i102 << 21 | i102 >>> 11);
    int i104 = i97 + (1700485571 + ((i101 ^ (i103 | i99 ^ 0xFFFFFFFF)) + paramArrayOfInt[12]));
    int i105 = i103 + (i104 << 6 | i104 >>> 26);
    int i106 = i99 + (-1894986606 + ((i103 ^ (i105 | i101 ^ 0xFFFFFFFF)) + paramArrayOfInt[3]));
    int i107 = i105 + (i106 << 10 | i106 >>> 22);
    int i108 = i101 + (-1051523 + ((i105 ^ (i107 | i103 ^ 0xFFFFFFFF)) + paramArrayOfInt[10]));
    int i109 = i107 + (i108 << 15 | i108 >>> 17);
    int i110 = i103 + (-2054922799 + ((i107 ^ (i109 | i105 ^ 0xFFFFFFFF)) + paramArrayOfInt[1]));
    int i111 = i109 + (i110 << 21 | i110 >>> 11);
    int i112 = i105 + (1873313359 + ((i109 ^ (i111 | i107 ^ 0xFFFFFFFF)) + paramArrayOfInt[8]));
    int i113 = i111 + (i112 << 6 | i112 >>> 26);
    int i114 = i107 + (-30611744 + ((i111 ^ (i113 | i109 ^ 0xFFFFFFFF)) + paramArrayOfInt[15]));
    int i115 = i113 + (i114 << 10 | i114 >>> 22);
    int i116 = i109 + (-1560198380 + ((i113 ^ (i115 | i111 ^ 0xFFFFFFFF)) + paramArrayOfInt[6]));
    int i117 = i115 + (i116 << 15 | i116 >>> 17);
    int i118 = i111 + (1309151649 + ((i115 ^ (i117 | i113 ^ 0xFFFFFFFF)) + paramArrayOfInt[13]));
    int i119 = i117 + (i118 << 21 | i118 >>> 11);
    int i120 = i113 + (-145523070 + ((i117 ^ (i119 | i115 ^ 0xFFFFFFFF)) + paramArrayOfInt[4]));
    int i121 = i119 + (i120 << 6 | i120 >>> 26);
    int i122 = i115 + (-1120210379 + ((i119 ^ (i121 | i117 ^ 0xFFFFFFFF)) + paramArrayOfInt[11]));
    int i123 = i121 + (i122 << 10 | i122 >>> 22);
    int i124 = i117 + (718787259 + ((i121 ^ (i123 | i119 ^ 0xFFFFFFFF)) + paramArrayOfInt[2]));
    int i125 = i123 + (i124 << 15 | i124 >>> 17);
    int i126 = i119 + (-343485551 + ((i123 ^ (i125 | i121 ^ 0xFFFFFFFF)) + paramArrayOfInt[9]));
    int i127 = i125 + (i126 << 21 | i126 >>> 11);
    int[] arrayOfInt1 = paramMD5State.state;
    arrayOfInt1[0] = (i121 + arrayOfInt1[0]);
    int[] arrayOfInt2 = paramMD5State.state;
    arrayOfInt2[1] = (i127 + arrayOfInt2[1]);
    int[] arrayOfInt3 = paramMD5State.state;
    arrayOfInt3[2] = (i125 + arrayOfInt3[2]);
    int[] arrayOfInt4 = paramMD5State.state;
    arrayOfInt4[3] = (i123 + arrayOfInt4[3]);
  }

  private final void update(MD5State paramMD5State, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.finals = null;
    if (paramInt2 - paramInt1 > paramArrayOfByte.length)
      paramInt2 = paramArrayOfByte.length - paramInt1;
    int i = (int)(0x3F & paramMD5State.count);
    paramMD5State.count += paramInt2;
    int j = 64 - i;
    int k;
    if (paramInt2 >= j)
    {
      int[] arrayOfInt = new int[16];
      if (j == 64)
        j = 0;
      while (true)
      {
        for (k = j; k + 63 < paramInt2; k += 64)
          transform(paramMD5State, paramArrayOfByte, k + paramInt1, arrayOfInt);
        for (int n = 0; n < j; n++)
          paramMD5State.buffer[(n + i)] = paramArrayOfByte[(n + paramInt1)];
        transform(paramMD5State, paramMD5State.buffer, 0, arrayOfInt);
      }
      i = 0;
    }
    while (k < paramInt2)
    {
      int m = k;
      while (k < paramInt2)
      {
        paramMD5State.buffer[(i + k - m)] = paramArrayOfByte[(k + paramInt1)];
        k++;
      }
      k = 0;
    }
  }

  public final byte[] doFinal()
  {
    monitorenter;
    try
    {
      int i;
      if (this.finals == null)
      {
        MD5State localMD5State = new MD5State(this.state);
        int[] arrayOfInt = new int[2];
        arrayOfInt[0] = (int)(localMD5State.count << 3);
        arrayOfInt[1] = (int)(localMD5State.count >> 29);
        byte[] arrayOfByte1 = encode(arrayOfInt, 8);
        i = (int)(0x3F & localMD5State.count);
        if (i < 56)
        {
          j = 56 - i;
          update(localMD5State, padding, 0, j);
          update(localMD5State, arrayOfByte1, 0, 8);
          this.finals = localMD5State;
        }
      }
      else
      {
        byte[] arrayOfByte2 = encode(this.finals.state, 16);
        return arrayOfByte2;
      }
      int j = 120 - i;
    }
    finally
    {
      monitorexit;
    }
  }

  public final byte[] fingerprint(byte[] paramArrayOfByte)
  {
    update(paramArrayOfByte);
    return doFinal();
  }

  public String hash(String paramString)
  {
    return toHex(new MD5(StringUtils.convertStringToBytes(paramString)).doFinal());
  }

  public final void update(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null);
    while (true)
    {
      return;
      this.state = new MD5State();
      update(this.state, paramArrayOfByte, 0, paramArrayOfByte.length);
    }
  }

  class MD5State
  {
    byte[] buffer = new byte[64];
    long count = 0L;
    int[] state = new int[4];

    public MD5State()
    {
      this.state[0] = 1732584193;
      this.state[1] = -271733879;
      this.state[2] = -1732584194;
      this.state[3] = 271733878;
    }

    public MD5State(MD5State arg2)
    {
      this();
      Object localObject;
      for (int i = 0; i < this.buffer.length; i++)
        this.buffer[i] = localObject.buffer[i];
      for (int j = 0; j < this.state.length; j++)
        this.state[j] = localObject.state[j];
      this.count = localObject.count;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.crypto.MD5
 * JD-Core Version:    0.6.0
 */