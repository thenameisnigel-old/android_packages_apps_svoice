package com.vlingo.sdk.internal.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class URLEncoder
{
  private static String _dontNeedEncoding = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ -_.*";

  public static boolean dontNeedEncoding(int paramInt)
  {
    int i = _dontNeedEncoding.length();
    int j = 0;
    for (int k = 0; ; k++)
    {
      if (k < i)
      {
        if (_dontNeedEncoding.charAt(k) != paramInt)
          continue;
        j = 1;
      }
      return j;
    }
  }

  public static String encode(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    int i = 0;
    int j = 0;
    StringBuffer localStringBuffer = new StringBuffer(paramString1.length());
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(10);
    Object localObject = new OutputStreamWriter(localByteArrayOutputStream, paramString2);
    int k = 0;
    if (k < paramString1.length())
    {
      int m = paramString1.charAt(k);
      if (dontNeedEncoding(m))
      {
        if (m == 32)
        {
          m = 43;
          i = 1;
        }
        localStringBuffer.append(m);
        j = 1;
      }
      while (true)
      {
        k++;
        break;
        if (j != 0);
        try
        {
          OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(localByteArrayOutputStream, paramString2);
          j = 0;
          localObject = localOutputStreamWriter;
          ((OutputStreamWriter)localObject).write(m);
          if ((m >= 55296) && (m <= 56319) && (k + 1 < paramString1.length()))
          {
            int i1 = k + 1;
            int i2 = paramString1.charAt(i1);
            if ((i2 >= 56320) && (i2 <= 57343))
            {
              ((OutputStreamWriter)localObject).write(i2);
              k++;
            }
          }
          ((OutputStreamWriter)localObject).flush();
          byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
          for (int n = 0; n < arrayOfByte.length; n++)
          {
            localStringBuffer.append('%');
            localStringBuffer.append(CCharacter.forDigit(0xF & arrayOfByte[n] >> 4, 16));
            localStringBuffer.append(CCharacter.forDigit(0xF & arrayOfByte[n], 16));
          }
        }
        catch (IOException localIOException)
        {
          localByteArrayOutputStream.reset();
        }
        continue;
        localByteArrayOutputStream.reset();
        i = 1;
      }
    }
    if (i != 0)
      paramString1 = localStringBuffer.toString();
    return (String)paramString1;
  }

  static class CCharacter
  {
    public static char forDigit(int paramInt1, int paramInt2)
    {
      int i = 0;
      if ((paramInt1 >= paramInt2) || (paramInt1 < 0));
      while (true)
      {
        return i;
        if ((paramInt2 < 2) || (paramInt2 > 36))
          continue;
        if (paramInt1 < 10)
        {
          i = (char)(paramInt1 + 48);
          continue;
        }
        i = (char)(paramInt1 + 87);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.URLEncoder
 * JD-Core Version:    0.6.0
 */