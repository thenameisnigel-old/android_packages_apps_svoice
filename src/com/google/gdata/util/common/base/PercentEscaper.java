package com.google.gdata.util.common.base;

public class PercentEscaper extends UnicodeEscaper
{
  public static final String SAFECHARS_URLENCODER = "-_.*";
  public static final String SAFEPATHCHARS_URLENCODER = "-_.!~*'()@:$&,;=";
  public static final String SAFEQUERYSTRINGCHARS_URLENCODER = "-_.!~*'()@:$,;/?:";
  private static final char[] UPPER_HEX_DIGITS;
  private static final char[] URI_ESCAPED_SPACE;
  private final boolean plusForSpace;
  private final boolean[] safeOctets;

  static
  {
    char[] arrayOfChar = new char[1];
    arrayOfChar[0] = '+';
    URI_ESCAPED_SPACE = arrayOfChar;
    UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
  }

  public PercentEscaper(String paramString, boolean paramBoolean)
  {
    if (paramString.matches(".*[0-9A-Za-z].*"))
      throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
    if ((paramBoolean) && (paramString.contains(" ")))
      throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
    if (paramString.contains("%"))
      throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
    this.plusForSpace = paramBoolean;
    this.safeOctets = createSafeOctets(paramString);
  }

  private static boolean[] createSafeOctets(String paramString)
  {
    int i = 122;
    char[] arrayOfChar = paramString.toCharArray();
    int j = arrayOfChar.length;
    for (int k = 0; k < j; k++)
      i = Math.max(arrayOfChar[k], i);
    boolean[] arrayOfBoolean = new boolean[i + 1];
    for (int m = 48; m <= 57; m++)
      arrayOfBoolean[m] = true;
    for (int n = 65; n <= 90; n++)
      arrayOfBoolean[n] = true;
    for (int i1 = 97; i1 <= 122; i1++)
      arrayOfBoolean[i1] = true;
    int i2 = arrayOfChar.length;
    for (int i3 = 0; i3 < i2; i3++)
      arrayOfBoolean[arrayOfChar[i3]] = true;
    return arrayOfBoolean;
  }

  public String escape(String paramString)
  {
    int i = paramString.length();
    for (int j = 0; ; j++)
    {
      if (j < i)
      {
        int k = paramString.charAt(j);
        if ((k < this.safeOctets.length) && (this.safeOctets[k] != 0))
          continue;
        paramString = escapeSlow(paramString, j);
      }
      return paramString;
    }
  }

  protected char[] escape(int paramInt)
  {
    char[] arrayOfChar;
    if ((paramInt < this.safeOctets.length) && (this.safeOctets[paramInt] != 0))
      arrayOfChar = null;
    while (true)
    {
      return arrayOfChar;
      if ((paramInt == 32) && (this.plusForSpace))
      {
        arrayOfChar = URI_ESCAPED_SPACE;
        continue;
      }
      if (paramInt <= 127)
      {
        arrayOfChar = new char[3];
        arrayOfChar[0] = '%';
        arrayOfChar[2] = UPPER_HEX_DIGITS[(paramInt & 0xF)];
        arrayOfChar[1] = UPPER_HEX_DIGITS[(paramInt >>> 4)];
        continue;
      }
      if (paramInt <= 2047)
      {
        arrayOfChar = new char[6];
        arrayOfChar[0] = '%';
        arrayOfChar[3] = '%';
        arrayOfChar[5] = UPPER_HEX_DIGITS[(paramInt & 0xF)];
        int i6 = paramInt >>> 4;
        arrayOfChar[4] = UPPER_HEX_DIGITS[(0x8 | i6 & 0x3)];
        int i7 = i6 >>> 2;
        arrayOfChar[2] = UPPER_HEX_DIGITS[(i7 & 0xF)];
        int i8 = i7 >>> 4;
        arrayOfChar[1] = UPPER_HEX_DIGITS[(i8 | 0xC)];
        continue;
      }
      if (paramInt <= 65535)
      {
        arrayOfChar = new char[9];
        arrayOfChar[0] = '%';
        arrayOfChar[1] = 'E';
        arrayOfChar[3] = '%';
        arrayOfChar[6] = '%';
        arrayOfChar[8] = UPPER_HEX_DIGITS[(paramInt & 0xF)];
        int i2 = paramInt >>> 4;
        arrayOfChar[7] = UPPER_HEX_DIGITS[(0x8 | i2 & 0x3)];
        int i3 = i2 >>> 2;
        arrayOfChar[5] = UPPER_HEX_DIGITS[(i3 & 0xF)];
        int i4 = i3 >>> 4;
        arrayOfChar[4] = UPPER_HEX_DIGITS[(0x8 | i4 & 0x3)];
        int i5 = i4 >>> 2;
        arrayOfChar[2] = UPPER_HEX_DIGITS[i5];
        continue;
      }
      if (paramInt > 1114111)
        break;
      arrayOfChar = new char[12];
      arrayOfChar[0] = '%';
      arrayOfChar[1] = 'F';
      arrayOfChar[3] = '%';
      arrayOfChar[6] = '%';
      arrayOfChar[9] = '%';
      arrayOfChar[11] = UPPER_HEX_DIGITS[(paramInt & 0xF)];
      int i = paramInt >>> 4;
      arrayOfChar[10] = UPPER_HEX_DIGITS[(0x8 | i & 0x3)];
      int j = i >>> 2;
      arrayOfChar[8] = UPPER_HEX_DIGITS[(j & 0xF)];
      int k = j >>> 4;
      arrayOfChar[7] = UPPER_HEX_DIGITS[(0x8 | k & 0x3)];
      int m = k >>> 2;
      arrayOfChar[5] = UPPER_HEX_DIGITS[(m & 0xF)];
      int n = m >>> 4;
      arrayOfChar[4] = UPPER_HEX_DIGITS[(0x8 | n & 0x3)];
      int i1 = n >>> 2;
      arrayOfChar[2] = UPPER_HEX_DIGITS[(i1 & 0x7)];
    }
    throw new IllegalArgumentException("Invalid unicode character value " + paramInt);
  }

  protected int nextEscapeIndex(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    while (true)
    {
      if (paramInt1 < paramInt2)
      {
        int i = paramCharSequence.charAt(paramInt1);
        if ((i < this.safeOctets.length) && (this.safeOctets[i] != 0));
      }
      else
      {
        return paramInt1;
      }
      paramInt1++;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.google.gdata.util.common.base.PercentEscaper
 * JD-Core Version:    0.6.0
 */