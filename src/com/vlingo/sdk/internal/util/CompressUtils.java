package com.vlingo.sdk.internal.util;

public class CompressUtils
{
  // ERROR //
  public static byte[] deflate(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: new 14	java/io/ByteArrayOutputStream
    //   7: dup
    //   8: aload_0
    //   9: arraylength
    //   10: invokespecial 17	java/io/ByteArrayOutputStream:<init>	(I)V
    //   13: astore_3
    //   14: new 19	java/util/zip/DeflaterOutputStream
    //   17: dup
    //   18: aload_3
    //   19: invokespecial 22	java/util/zip/DeflaterOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   22: astore 4
    //   24: aload 4
    //   26: aload_0
    //   27: invokevirtual 26	java/util/zip/DeflaterOutputStream:write	([B)V
    //   30: aload 4
    //   32: invokevirtual 29	java/util/zip/DeflaterOutputStream:close	()V
    //   35: aconst_null
    //   36: astore_1
    //   37: aload_3
    //   38: invokevirtual 33	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   41: astore 12
    //   43: aload 12
    //   45: astore_2
    //   46: iconst_0
    //   47: ifeq +7 -> 54
    //   50: aconst_null
    //   51: invokevirtual 29	java/util/zip/DeflaterOutputStream:close	()V
    //   54: aload_3
    //   55: ifnull +7 -> 62
    //   58: aload_3
    //   59: invokevirtual 34	java/io/ByteArrayOutputStream:close	()V
    //   62: aload_2
    //   63: areturn
    //   64: astore 11
    //   66: aload_1
    //   67: ifnull +7 -> 74
    //   70: aload_1
    //   71: invokevirtual 29	java/util/zip/DeflaterOutputStream:close	()V
    //   74: aload_3
    //   75: ifnull -13 -> 62
    //   78: aload_3
    //   79: invokevirtual 34	java/io/ByteArrayOutputStream:close	()V
    //   82: goto -20 -> 62
    //   85: astore 6
    //   87: goto -25 -> 62
    //   90: astore 8
    //   92: aload_1
    //   93: ifnull +7 -> 100
    //   96: aload_1
    //   97: invokevirtual 29	java/util/zip/DeflaterOutputStream:close	()V
    //   100: aload_3
    //   101: ifnull +7 -> 108
    //   104: aload_3
    //   105: invokevirtual 34	java/io/ByteArrayOutputStream:close	()V
    //   108: aload 8
    //   110: athrow
    //   111: astore 14
    //   113: goto -59 -> 54
    //   116: astore 13
    //   118: goto -56 -> 62
    //   121: astore 7
    //   123: goto -49 -> 74
    //   126: astore 10
    //   128: goto -28 -> 100
    //   131: astore 9
    //   133: goto -25 -> 108
    //   136: astore 8
    //   138: aload 4
    //   140: astore_1
    //   141: goto -49 -> 92
    //   144: astore 5
    //   146: aload 4
    //   148: astore_1
    //   149: goto -83 -> 66
    //
    // Exception table:
    //   from	to	target	type
    //   14	24	64	java/lang/Exception
    //   37	43	64	java/lang/Exception
    //   78	82	85	java/lang/Exception
    //   14	24	90	finally
    //   37	43	90	finally
    //   50	54	111	java/lang/Exception
    //   58	62	116	java/lang/Exception
    //   70	74	121	java/lang/Exception
    //   96	100	126	java/lang/Exception
    //   104	108	131	java/lang/Exception
    //   24	35	136	finally
    //   24	35	144	java/lang/Exception
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.util.CompressUtils
 * JD-Core Version:    0.6.0
 */