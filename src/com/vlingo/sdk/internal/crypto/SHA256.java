package com.vlingo.sdk.internal.crypto;

public class SHA256
  implements HashFunction
{
  private static final int BLOCK_SIZE = 64;
  private static final int HASH_SIZE = 32;
  private static final char[] HEX_CHARS;
  private static final int[] K;
  private final byte[] buf = new byte[64];
  private int bufOff;
  private final int[] buffer = new int[64];
  private long byteCount;
  private final int[] context = new int[8];

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
    int[] arrayOfInt = new int[64];
    arrayOfInt[0] = 1116352408;
    arrayOfInt[1] = 1899447441;
    arrayOfInt[2] = -1245643825;
    arrayOfInt[3] = -373957723;
    arrayOfInt[4] = 961987163;
    arrayOfInt[5] = 1508970993;
    arrayOfInt[6] = -1841331548;
    arrayOfInt[7] = -1424204075;
    arrayOfInt[8] = -670586216;
    arrayOfInt[9] = 310598401;
    arrayOfInt[10] = 607225278;
    arrayOfInt[11] = 1426881987;
    arrayOfInt[12] = 1925078388;
    arrayOfInt[13] = -2132889090;
    arrayOfInt[14] = -1680079193;
    arrayOfInt[15] = -1046744716;
    arrayOfInt[16] = -459576895;
    arrayOfInt[17] = -272742522;
    arrayOfInt[18] = 264347078;
    arrayOfInt[19] = 604807628;
    arrayOfInt[20] = 770255983;
    arrayOfInt[21] = 1249150122;
    arrayOfInt[22] = 1555081692;
    arrayOfInt[23] = 1996064986;
    arrayOfInt[24] = -1740746414;
    arrayOfInt[25] = -1473132947;
    arrayOfInt[26] = -1341970488;
    arrayOfInt[27] = -1084653625;
    arrayOfInt[28] = -958395405;
    arrayOfInt[29] = -710438585;
    arrayOfInt[30] = 113926993;
    arrayOfInt[31] = 338241895;
    arrayOfInt[32] = 666307205;
    arrayOfInt[33] = 773529912;
    arrayOfInt[34] = 1294757372;
    arrayOfInt[35] = 1396182291;
    arrayOfInt[36] = 1695183700;
    arrayOfInt[37] = 1986661051;
    arrayOfInt[38] = -2117940946;
    arrayOfInt[39] = -1838011259;
    arrayOfInt[40] = -1564481375;
    arrayOfInt[41] = -1474664885;
    arrayOfInt[42] = -1035236496;
    arrayOfInt[43] = -949202525;
    arrayOfInt[44] = -778901479;
    arrayOfInt[45] = -694614492;
    arrayOfInt[46] = -200395387;
    arrayOfInt[47] = 275423344;
    arrayOfInt[48] = 430227734;
    arrayOfInt[49] = 506948616;
    arrayOfInt[50] = 659060556;
    arrayOfInt[51] = 883997877;
    arrayOfInt[52] = 958139571;
    arrayOfInt[53] = 1322822218;
    arrayOfInt[54] = 1537002063;
    arrayOfInt[55] = 1747873779;
    arrayOfInt[56] = 1955562222;
    arrayOfInt[57] = 2024104815;
    arrayOfInt[58] = -2067236844;
    arrayOfInt[59] = -1933114872;
    arrayOfInt[60] = -1866530822;
    arrayOfInt[61] = -1538233109;
    arrayOfInt[62] = -1090935817;
    arrayOfInt[63] = -965641998;
    K = arrayOfInt;
  }

  public SHA256()
  {
    coreReset();
  }

  private final int Ch(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt2 ^ paramInt3 & (paramInt1 ^ 0xFFFFFFFF);
  }

  private final int Maj(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt2 ^ paramInt1 & paramInt3 ^ paramInt2 & paramInt3;
  }

  private final int R(int paramInt1, int paramInt2)
  {
    return paramInt2 >>> paramInt1;
  }

  private final int S(int paramInt1, int paramInt2)
  {
    return paramInt2 >>> paramInt1 | paramInt2 << 32 - paramInt1;
  }

  private final int Sig0(int paramInt)
  {
    return S(2, paramInt) ^ S(13, paramInt) ^ S(22, paramInt);
  }

  private final int Sig1(int paramInt)
  {
    return S(6, paramInt) ^ S(11, paramInt) ^ S(25, paramInt);
  }

  private int privateDigest(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    byte[] arrayOfByte1 = this.buf;
    int i = this.bufOff;
    this.bufOff = (i + 1);
    arrayOfByte1[i] = -128;
    int j = 64 - 8;
    if (this.bufOff > j)
    {
      while (this.bufOff < 64)
      {
        byte[] arrayOfByte4 = this.buf;
        int i1 = this.bufOff;
        this.bufOff = (i1 + 1);
        arrayOfByte4[i1] = 0;
      }
      coreUpdate(this.buf, 0);
      this.bufOff = 0;
    }
    while (this.bufOff < j)
    {
      byte[] arrayOfByte3 = this.buf;
      int n = this.bufOff;
      this.bufOff = (n + 1);
      arrayOfByte3[n] = 0;
    }
    long l = 8L * this.byteCount;
    for (int k = 56; k >= 0; k -= 8)
    {
      byte[] arrayOfByte2 = this.buf;
      int m = this.bufOff;
      this.bufOff = (m + 1);
      arrayOfByte2[m] = (byte)(int)(l >>> k);
    }
    coreUpdate(this.buf, 0);
    coreDigest(paramArrayOfByte, paramInt1);
    if (paramBoolean)
      coreReset();
    return 32;
  }

  private final int sig0(int paramInt)
  {
    return S(7, paramInt) ^ S(18, paramInt) ^ R(3, paramInt);
  }

  private final int sig1(int paramInt)
  {
    return S(17, paramInt) ^ S(19, paramInt) ^ R(10, paramInt);
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

  protected void coreDigest(byte[] paramArrayOfByte, int paramInt)
  {
    for (int i = 0; i < this.context.length; i++)
      for (int j = 0; j < 4; j++)
        paramArrayOfByte[(paramInt + (i * 4 + (3 - j)))] = (byte)(this.context[i] >>> j * 8);
  }

  protected void coreReset()
  {
    this.bufOff = 0;
    this.byteCount = 0L;
    this.context[0] = 1779033703;
    this.context[1] = -1150833019;
    this.context[2] = 1013904242;
    this.context[3] = -1521486534;
    this.context[4] = 1359893119;
    this.context[5] = -1694144372;
    this.context[6] = 528734635;
    this.context[7] = 1541459225;
  }

  protected void coreUpdate(byte[] paramArrayOfByte, int paramInt)
  {
    int[] arrayOfInt1 = this.buffer;
    int i = 0;
    int j = paramInt;
    while (i < 16)
    {
      int i10 = j + 1;
      int i11 = paramArrayOfByte[j] << 24;
      int i12 = i10 + 1;
      int i13 = i11 | (0xFF & paramArrayOfByte[i10]) << 16;
      int i14 = i12 + 1;
      int i15 = i13 | (0xFF & paramArrayOfByte[i12]) << 8;
      j = i14 + 1;
      arrayOfInt1[i] = (i15 | 0xFF & paramArrayOfByte[i14]);
      i++;
    }
    for (int k = 16; k < 64; k++)
      arrayOfInt1[k] = (sig1(arrayOfInt1[(k - 2)]) + arrayOfInt1[(k - 7)] + sig0(arrayOfInt1[(k - 15)]) + arrayOfInt1[(k - 16)]);
    int m = this.context[0];
    int n = this.context[1];
    int i1 = this.context[2];
    int i2 = this.context[3];
    int i3 = this.context[4];
    int i4 = this.context[5];
    int i5 = this.context[6];
    int i6 = this.context[7];
    for (int i7 = 0; i7 < 64; i7++)
    {
      int i8 = i6 + Sig1(i3) + Ch(i3, i4, i5) + K[i7] + arrayOfInt1[i7];
      int i9 = Sig0(m) + Maj(m, n, i1);
      i6 = i5;
      i5 = i4;
      i4 = i3;
      i3 = i2 + i8;
      i2 = i1;
      i1 = n;
      n = m;
      m = i8 + i9;
    }
    int[] arrayOfInt2 = this.context;
    arrayOfInt2[0] = (m + arrayOfInt2[0]);
    int[] arrayOfInt3 = this.context;
    arrayOfInt3[1] = (n + arrayOfInt3[1]);
    int[] arrayOfInt4 = this.context;
    arrayOfInt4[2] = (i1 + arrayOfInt4[2]);
    int[] arrayOfInt5 = this.context;
    arrayOfInt5[3] = (i2 + arrayOfInt5[3]);
    int[] arrayOfInt6 = this.context;
    arrayOfInt6[4] = (i3 + arrayOfInt6[4]);
    int[] arrayOfInt7 = this.context;
    arrayOfInt7[5] = (i4 + arrayOfInt7[5]);
    int[] arrayOfInt8 = this.context;
    arrayOfInt8[6] = (i5 + arrayOfInt8[6]);
    int[] arrayOfInt9 = this.context;
    arrayOfInt9[7] = (i6 + arrayOfInt9[7]);
  }

  public void digest(boolean paramBoolean, byte[] paramArrayOfByte, int paramInt)
  {
    privateDigest(this.buf, paramInt, paramArrayOfByte.length, true);
  }

  public byte[] digest()
  {
    byte[] arrayOfByte = new byte[32];
    privateDigest(arrayOfByte, 0, 32, true);
    return arrayOfByte;
  }

  public final int digestSize()
  {
    return 256;
  }

  public void extract(int[] paramArrayOfInt, int paramInt)
  {
    for (int i = 0; i < this.context.length; i++)
      paramArrayOfInt[(i + paramInt)] = this.context[i];
  }

  public String hash(String paramString)
  {
    coreReset();
    for (int i = 0; i < paramString.length(); i++)
      update((byte)paramString.charAt(i));
    return toHex(digest());
  }

  public void update(byte paramByte)
  {
    this.byteCount = (1L + this.byteCount);
    byte[] arrayOfByte = this.buf;
    int i = this.bufOff;
    this.bufOff = (i + 1);
    arrayOfByte[i] = paramByte;
    if (this.bufOff == 64)
    {
      coreUpdate(this.buf, 0);
      this.bufOff = 0;
    }
  }

  public void update(byte[] paramArrayOfByte)
  {
    update(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void update(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.byteCount += paramInt2;
    while (true)
    {
      int i = 64 - this.bufOff;
      if (paramInt2 < i)
        break;
      System.arraycopy(paramArrayOfByte, paramInt1, this.buf, this.bufOff, i);
      coreUpdate(this.buf, 0);
      paramInt2 -= i;
      paramInt1 += i;
      this.bufOff = 0;
    }
    System.arraycopy(paramArrayOfByte, paramInt1, this.buf, this.bufOff, paramInt2);
    this.bufOff = (paramInt2 + this.bufOff);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.crypto.SHA256
 * JD-Core Version:    0.6.0
 */