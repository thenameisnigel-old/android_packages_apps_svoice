package com.vlingo.sdk.internal.recognizer.reader;

import com.vlingo.sdk.internal.recognizer.SRContext;

public class AMRDataReader extends DataReader
{
  AMRDataReader(SRContext paramSRContext, DataReaderListener paramDataReaderListener, DataReadyListner paramDataReadyListner)
  {
    super(paramSRContext, paramDataReaderListener, paramDataReadyListner);
  }

  // ERROR //
  private byte[] getAMRData(java.io.InputStream paramInputStream, int paramInt)
  {
    // Byte code:
    //   0: new 14	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: sipush 500
    //   7: invokespecial 17	java/io/ByteArrayOutputStream:<init>	(I)V
    //   10: astore_3
    //   11: aconst_null
    //   12: astore 4
    //   14: sipush 500
    //   17: newarray byte
    //   19: astore 5
    //   21: iload_2
    //   22: bipush 255
    //   24: if_icmpne +84 -> 108
    //   27: aload_1
    //   28: aload 5
    //   30: invokevirtual 23	java/io/InputStream:read	([B)I
    //   33: istore 15
    //   35: iload 15
    //   37: bipush 255
    //   39: if_icmpeq +36 -> 75
    //   42: aload_3
    //   43: aload 5
    //   45: iconst_0
    //   46: iload 15
    //   48: invokevirtual 27	java/io/ByteArrayOutputStream:write	([BII)V
    //   51: goto -24 -> 27
    //   54: astore 12
    //   56: aload_1
    //   57: ifnull +7 -> 64
    //   60: aload_1
    //   61: invokevirtual 31	java/io/InputStream:close	()V
    //   64: aload_3
    //   65: ifnull +7 -> 72
    //   68: aload_3
    //   69: invokevirtual 32	java/io/ByteArrayOutputStream:close	()V
    //   72: aload 4
    //   74: areturn
    //   75: aload_3
    //   76: invokevirtual 35	java/io/ByteArrayOutputStream:flush	()V
    //   79: aload_3
    //   80: invokevirtual 39	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   83: astore 16
    //   85: aload 16
    //   87: astore 4
    //   89: aload_1
    //   90: ifnull +7 -> 97
    //   93: aload_1
    //   94: invokevirtual 31	java/io/InputStream:close	()V
    //   97: aload_3
    //   98: ifnull +7 -> 105
    //   101: aload_3
    //   102: invokevirtual 32	java/io/ByteArrayOutputStream:close	()V
    //   105: goto -33 -> 72
    //   108: aload_1
    //   109: iload_2
    //   110: invokestatic 44	com/vlingo/sdk/internal/audio/AMRUtil:readInAMRMaxFrames	(Ljava/io/InputStream;I)[B
    //   113: astore 9
    //   115: aload 9
    //   117: astore 4
    //   119: goto -30 -> 89
    //   122: astore 6
    //   124: aload_1
    //   125: ifnull +7 -> 132
    //   128: aload_1
    //   129: invokevirtual 31	java/io/InputStream:close	()V
    //   132: aload_3
    //   133: ifnull +7 -> 140
    //   136: aload_3
    //   137: invokevirtual 32	java/io/ByteArrayOutputStream:close	()V
    //   140: aload 6
    //   142: athrow
    //   143: astore 11
    //   145: goto -48 -> 97
    //   148: astore 10
    //   150: goto -45 -> 105
    //   153: astore 14
    //   155: goto -91 -> 64
    //   158: astore 13
    //   160: goto -88 -> 72
    //   163: astore 8
    //   165: goto -33 -> 132
    //   168: astore 7
    //   170: goto -30 -> 140
    //
    // Exception table:
    //   from	to	target	type
    //   27	51	54	java/io/IOException
    //   75	85	54	java/io/IOException
    //   108	115	54	java/io/IOException
    //   27	51	122	finally
    //   75	85	122	finally
    //   108	115	122	finally
    //   93	97	143	java/io/IOException
    //   101	105	148	java/io/IOException
    //   60	64	153	java/io/IOException
    //   68	72	158	java/io/IOException
    //   128	132	163	java/io/IOException
    //   136	140	168	java/io/IOException
  }

  protected boolean isSpeechDetected()
  {
    return true;
  }

  protected void onDeinit()
  {
  }

  protected boolean onInit()
  {
    return true;
  }

  protected void onProcessData()
  {
    onDataAvailable(getAMRData(getInputStream(), getMaxDuration()), -1, -1);
    stop();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.reader.AMRDataReader
 * JD-Core Version:    0.6.0
 */