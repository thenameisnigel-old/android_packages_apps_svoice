package com.google.gdata.util.common.base;

import java.io.IOException;

class UnicodeEscaper$1
  implements Appendable
{
  char[] decodedChars = new char[2];
  int pendingHighSurrogate = -1;

  private void outputChars(char[] paramArrayOfChar, int paramInt)
    throws IOException
  {
    for (int i = 0; i < paramInt; i++)
      this.val$out.append(paramArrayOfChar[i]);
  }

  public Appendable append(char paramChar)
    throws IOException
  {
    if (this.pendingHighSurrogate != -1)
    {
      if (!Character.isLowSurrogate(paramChar))
        throw new IllegalArgumentException("Expected low surrogate character but got '" + paramChar + "' with value " + paramChar);
      char[] arrayOfChar2 = this.this$0.escape(Character.toCodePoint((char)this.pendingHighSurrogate, paramChar));
      if (arrayOfChar2 != null)
      {
        outputChars(arrayOfChar2, arrayOfChar2.length);
        this.pendingHighSurrogate = -1;
      }
    }
    while (true)
    {
      return this;
      this.val$out.append((char)this.pendingHighSurrogate);
      this.val$out.append(paramChar);
      break;
      if (Character.isHighSurrogate(paramChar))
      {
        this.pendingHighSurrogate = paramChar;
        continue;
      }
      if (Character.isLowSurrogate(paramChar))
        throw new IllegalArgumentException("Unexpected low surrogate character '" + paramChar + "' with value " + paramChar);
      char[] arrayOfChar1 = this.this$0.escape(paramChar);
      if (arrayOfChar1 != null)
      {
        outputChars(arrayOfChar1, arrayOfChar1.length);
        continue;
      }
      this.val$out.append(paramChar);
    }
  }

  public Appendable append(CharSequence paramCharSequence)
    throws IOException
  {
    return append(paramCharSequence, 0, paramCharSequence.length());
  }

  public Appendable append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt1;
    int j;
    int k;
    if (i < paramInt2)
    {
      j = i;
      if (this.pendingHighSurrogate != -1)
      {
        int i2 = i + 1;
        char c = paramCharSequence.charAt(i);
        if (!Character.isLowSurrogate(c))
          throw new IllegalArgumentException("Expected low surrogate character but got " + c);
        char[] arrayOfChar2 = this.this$0.escape(Character.toCodePoint((char)this.pendingHighSurrogate, c));
        if (arrayOfChar2 == null)
          break label163;
        outputChars(arrayOfChar2, arrayOfChar2.length);
        j++;
        this.pendingHighSurrogate = -1;
        i = i2;
      }
      k = this.this$0.nextEscapeIndex(paramCharSequence, i, paramInt2);
      if (k > j)
        this.val$out.append(paramCharSequence, j, k);
      if (k != paramInt2)
        break label181;
    }
    label163: label181: int m;
    while (true)
    {
      return this;
      this.val$out.append((char)this.pendingHighSurrogate);
      break;
      m = UnicodeEscaper.codePointAt(paramCharSequence, k, paramInt2);
      if (m >= 0)
        break label205;
      this.pendingHighSurrogate = (-m);
    }
    label205: char[] arrayOfChar1 = this.this$0.escape(m);
    if (arrayOfChar1 != null)
    {
      outputChars(arrayOfChar1, arrayOfChar1.length);
      label230: if (!Character.isSupplementaryCodePoint(m))
        break label280;
    }
    label280: for (int i1 = 2; ; i1 = 1)
    {
      i = k + i1;
      j = i;
      break;
      int n = Character.toChars(m, this.decodedChars, 0);
      outputChars(this.decodedChars, n);
      break label230;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.google.gdata.util.common.base.UnicodeEscaper.1
 * JD-Core Version:    0.6.0
 */