package com.google.gdata.util.common.base;

public abstract class UnicodeEscaper
  implements Escaper
{
  private static final int DEST_PAD = 32;
  private static final ThreadLocal<char[]> DEST_TL = new UnicodeEscaper.2();

  protected static final int codePointAt(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2)
    {
      int i = paramInt1 + 1;
      int j = paramCharSequence.charAt(paramInt1);
      if ((j < 55296) || (j > 57343));
      int k;
      while (true)
      {
        return j;
        if (j > 56319)
          break;
        if (i == paramInt2)
        {
          j = -j;
          continue;
        }
        char c = paramCharSequence.charAt(i);
        if (Character.isLowSurrogate(c))
        {
          k = Character.toCodePoint(j, c);
          continue;
        }
        throw new IllegalArgumentException("Expected low surrogate but got char '" + c + "' with value " + c + " at index " + i);
      }
      throw new IllegalArgumentException("Unexpected low surrogate character '" + k + "' with value " + k + " at index " + (i - 1));
    }
    throw new IndexOutOfBoundsException("Index exceeds specified range");
  }

  private static final char[] growBuffer(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    char[] arrayOfChar = new char[paramInt2];
    if (paramInt1 > 0)
      System.arraycopy(paramArrayOfChar, 0, arrayOfChar, 0, paramInt1);
    return arrayOfChar;
  }

  public Appendable escape(Appendable paramAppendable)
  {
    Preconditions.checkNotNull(paramAppendable);
    return new UnicodeEscaper.1(this, paramAppendable);
  }

  public String escape(String paramString)
  {
    int i = paramString.length();
    int j = nextEscapeIndex(paramString, 0, i);
    if (j == i);
    while (true)
    {
      return paramString;
      paramString = escapeSlow(paramString, j);
    }
  }

  protected abstract char[] escape(int paramInt);

  protected final String escapeSlow(String paramString, int paramInt)
  {
    int i = paramString.length();
    char[] arrayOfChar1 = (char[])DEST_TL.get();
    int j = 0;
    int k = 0;
    if (paramInt < i)
    {
      int i1 = codePointAt(paramString, paramInt, i);
      if (i1 < 0)
        throw new IllegalArgumentException("Trailing high surrogate at end of input");
      char[] arrayOfChar2 = escape(i1);
      if (arrayOfChar2 != null)
      {
        int i3 = paramInt - k;
        int i4 = j + i3 + arrayOfChar2.length;
        if (arrayOfChar1.length < i4)
          arrayOfChar1 = growBuffer(arrayOfChar1, j, 32 + (i4 + (i - paramInt)));
        if (i3 > 0)
        {
          paramString.getChars(k, paramInt, arrayOfChar1, j);
          j += i3;
        }
        if (arrayOfChar2.length > 0)
        {
          System.arraycopy(arrayOfChar2, 0, arrayOfChar1, j, arrayOfChar2.length);
          j += arrayOfChar2.length;
        }
      }
      if (Character.isSupplementaryCodePoint(i1));
      for (int i2 = 2; ; i2 = 1)
      {
        k = paramInt + i2;
        paramInt = nextEscapeIndex(paramString, k, i);
        break;
      }
    }
    int m = i - k;
    if (m > 0)
    {
      int n = j + m;
      if (arrayOfChar1.length < n)
        arrayOfChar1 = growBuffer(arrayOfChar1, j, n);
      paramString.getChars(k, i, arrayOfChar1, j);
      j = n;
    }
    return new String(arrayOfChar1, 0, j);
  }

  protected int nextEscapeIndex(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    int j;
    if (i < paramInt2)
    {
      j = codePointAt(paramCharSequence, i, paramInt2);
      if ((j >= 0) && (escape(j) == null));
    }
    else
    {
      return i;
    }
    if (Character.isSupplementaryCodePoint(j));
    for (int k = 2; ; k = 1)
    {
      i += k;
      break;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.google.gdata.util.common.base.UnicodeEscaper
 * JD-Core Version:    0.6.0
 */