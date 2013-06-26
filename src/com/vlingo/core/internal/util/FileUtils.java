package com.vlingo.core.internal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils
{
  public static boolean copyFile(File paramFile1, File paramFile2)
  {
    int i = 0;
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramFile1);
      FileOutputStream localFileOutputStream = new FileOutputStream(paramFile2);
      byte[] arrayOfByte = new byte[1024];
      while (true)
      {
        int j = localFileInputStream.read(arrayOfByte);
        if (j <= 0)
          break;
        localFileOutputStream.write(arrayOfByte, 0, j);
      }
      localFileOutputStream.flush();
      localFileOutputStream.close();
      localFileInputStream.close();
      i = 1;
    }
    catch (IOException localIOException)
    {
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
    }
    return i;
  }

  // ERROR //
  public static final byte[] readResource(java.lang.String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 45	java/io/ByteArrayOutputStream
    //   5: dup
    //   6: sipush 500
    //   9: invokespecial 48	java/io/ByteArrayOutputStream:<init>	(I)V
    //   12: astore_2
    //   13: aconst_null
    //   14: astore_3
    //   15: sipush 500
    //   18: newarray byte
    //   20: astore 4
    //   22: ldc 50
    //   24: aload_0
    //   25: invokevirtual 54	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   28: astore 12
    //   30: aload 12
    //   32: astore_1
    //   33: aload_1
    //   34: ifnonnull +25 -> 59
    //   37: aconst_null
    //   38: astore 9
    //   40: aload_1
    //   41: ifnull +7 -> 48
    //   44: aload_1
    //   45: invokevirtual 41	java/io/InputStream:close	()V
    //   48: aload_2
    //   49: ifnull +7 -> 56
    //   52: aload_2
    //   53: invokevirtual 55	java/io/ByteArrayOutputStream:close	()V
    //   56: aload 9
    //   58: areturn
    //   59: aload_1
    //   60: aload 4
    //   62: invokevirtual 28	java/io/InputStream:read	([B)I
    //   65: istore 13
    //   67: iload 13
    //   69: bipush 255
    //   71: if_icmpeq +39 -> 110
    //   74: aload_2
    //   75: aload 4
    //   77: iconst_0
    //   78: iload 13
    //   80: invokevirtual 56	java/io/ByteArrayOutputStream:write	([BII)V
    //   83: goto -24 -> 59
    //   86: astore 8
    //   88: aload_1
    //   89: ifnull +7 -> 96
    //   92: aload_1
    //   93: invokevirtual 41	java/io/InputStream:close	()V
    //   96: aload_2
    //   97: ifnull +7 -> 104
    //   100: aload_2
    //   101: invokevirtual 55	java/io/ByteArrayOutputStream:close	()V
    //   104: aload_3
    //   105: astore 9
    //   107: goto -51 -> 56
    //   110: aload_2
    //   111: invokevirtual 57	java/io/ByteArrayOutputStream:flush	()V
    //   114: aload_2
    //   115: invokevirtual 61	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   118: astore 14
    //   120: aload 14
    //   122: astore_3
    //   123: aload_1
    //   124: ifnull +7 -> 131
    //   127: aload_1
    //   128: invokevirtual 41	java/io/InputStream:close	()V
    //   131: aload_2
    //   132: ifnull +7 -> 139
    //   135: aload_2
    //   136: invokevirtual 55	java/io/ByteArrayOutputStream:close	()V
    //   139: goto -35 -> 104
    //   142: astore 5
    //   144: aload_1
    //   145: ifnull +7 -> 152
    //   148: aload_1
    //   149: invokevirtual 41	java/io/InputStream:close	()V
    //   152: aload_2
    //   153: ifnull +7 -> 160
    //   156: aload_2
    //   157: invokevirtual 55	java/io/ByteArrayOutputStream:close	()V
    //   160: aload 5
    //   162: athrow
    //   163: astore 18
    //   165: goto -117 -> 48
    //   168: astore 17
    //   170: goto -114 -> 56
    //   173: astore 16
    //   175: goto -44 -> 131
    //   178: astore 15
    //   180: goto -41 -> 139
    //   183: astore 11
    //   185: goto -89 -> 96
    //   188: astore 10
    //   190: goto -86 -> 104
    //   193: astore 7
    //   195: goto -43 -> 152
    //   198: astore 6
    //   200: goto -40 -> 160
    //
    // Exception table:
    //   from	to	target	type
    //   22	30	86	java/io/IOException
    //   59	83	86	java/io/IOException
    //   110	120	86	java/io/IOException
    //   22	30	142	finally
    //   59	83	142	finally
    //   110	120	142	finally
    //   44	48	163	java/io/IOException
    //   52	56	168	java/io/IOException
    //   127	131	173	java/io/IOException
    //   135	139	178	java/io/IOException
    //   92	96	183	java/io/IOException
    //   100	104	188	java/io/IOException
    //   148	152	193	java/io/IOException
    //   156	160	198	java/io/IOException
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.FileUtils
 * JD-Core Version:    0.6.0
 */