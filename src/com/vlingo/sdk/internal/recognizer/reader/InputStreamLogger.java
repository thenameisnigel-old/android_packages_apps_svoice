package com.vlingo.sdk.internal.recognizer.reader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamLogger extends InputStream
{
  private ByteArrayOutputStream mByteArrayOutputStream;
  private InputStream mInputStream;

  InputStreamLogger(InputStream paramInputStream)
  {
    this.mInputStream = paramInputStream;
    this.mByteArrayOutputStream = new ByteArrayOutputStream();
  }

  public int read()
    throws IOException
  {
    monitorenter;
    try
    {
      int i = this.mInputStream.read();
      if (i > -1)
        this.mByteArrayOutputStream.write(i);
      int j = this.mInputStream.read();
      monitorexit;
      return j;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    monitorenter;
    try
    {
      int i = this.mInputStream.read(paramArrayOfByte);
      if (i > 0)
        this.mByteArrayOutputStream.write(paramArrayOfByte, 0, i);
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    monitorenter;
    try
    {
      int i = this.mInputStream.read(paramArrayOfByte, paramInt1, paramInt2);
      if (i > 0)
        this.mByteArrayOutputStream.write(paramArrayOfByte, paramInt1, i);
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  void writeLog(java.lang.String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 20	com/vlingo/sdk/internal/recognizer/reader/InputStreamLogger:mByteArrayOutputStream	Ljava/io/ByteArrayOutputStream;
    //   6: invokevirtual 44	java/io/ByteArrayOutputStream:size	()I
    //   9: istore_3
    //   10: iload_3
    //   11: ifle +99 -> 110
    //   14: aconst_null
    //   15: astore 4
    //   17: aload_1
    //   18: ifnonnull +6 -> 24
    //   21: ldc 46
    //   23: astore_1
    //   24: invokestatic 52	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   27: astore 9
    //   29: aload 9
    //   31: invokevirtual 58	java/io/File:canWrite	()Z
    //   34: ifeq +66 -> 100
    //   37: new 60	java/io/FileOutputStream
    //   40: dup
    //   41: new 54	java/io/File
    //   44: dup
    //   45: aload 9
    //   47: new 62	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 63	java/lang/StringBuilder:<init>	()V
    //   54: ldc 65
    //   56: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload_1
    //   60: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: ldc 71
    //   65: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: invokevirtual 75	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: invokespecial 78	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   74: invokespecial 81	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   77: astore 10
    //   79: aload 10
    //   81: aload_0
    //   82: getfield 20	com/vlingo/sdk/internal/recognizer/reader/InputStreamLogger:mByteArrayOutputStream	Ljava/io/ByteArrayOutputStream;
    //   85: invokevirtual 85	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   88: invokevirtual 88	java/io/FileOutputStream:write	([B)V
    //   91: aload 10
    //   93: invokevirtual 91	java/io/FileOutputStream:flush	()V
    //   96: aload 10
    //   98: astore 4
    //   100: aload 4
    //   102: ifnull +8 -> 110
    //   105: aload 4
    //   107: invokevirtual 94	java/io/FileOutputStream:close	()V
    //   110: aload_0
    //   111: monitorexit
    //   112: return
    //   113: astore 11
    //   115: aload 11
    //   117: invokevirtual 97	java/io/IOException:printStackTrace	()V
    //   120: goto -10 -> 110
    //   123: astore_2
    //   124: aload_0
    //   125: monitorexit
    //   126: aload_2
    //   127: athrow
    //   128: astore 7
    //   130: aload 7
    //   132: invokevirtual 97	java/io/IOException:printStackTrace	()V
    //   135: aload 4
    //   137: ifnull -27 -> 110
    //   140: aload 4
    //   142: invokevirtual 94	java/io/FileOutputStream:close	()V
    //   145: goto -35 -> 110
    //   148: astore 8
    //   150: aload 8
    //   152: invokevirtual 97	java/io/IOException:printStackTrace	()V
    //   155: goto -45 -> 110
    //   158: astore 5
    //   160: aload 4
    //   162: ifnull +8 -> 170
    //   165: aload 4
    //   167: invokevirtual 94	java/io/FileOutputStream:close	()V
    //   170: aload 5
    //   172: athrow
    //   173: astore 6
    //   175: aload 6
    //   177: invokevirtual 97	java/io/IOException:printStackTrace	()V
    //   180: goto -10 -> 170
    //   183: astore 5
    //   185: aload 10
    //   187: astore 4
    //   189: goto -29 -> 160
    //   192: astore 7
    //   194: aload 10
    //   196: astore 4
    //   198: goto -68 -> 130
    //
    // Exception table:
    //   from	to	target	type
    //   105	110	113	java/io/IOException
    //   2	10	123	finally
    //   105	110	123	finally
    //   115	120	123	finally
    //   140	145	123	finally
    //   150	155	123	finally
    //   165	170	123	finally
    //   170	180	123	finally
    //   21	79	128	java/io/IOException
    //   140	145	148	java/io/IOException
    //   21	79	158	finally
    //   130	135	158	finally
    //   165	170	173	java/io/IOException
    //   79	96	183	finally
    //   79	96	192	java/io/IOException
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.reader.InputStreamLogger
 * JD-Core Version:    0.6.0
 */