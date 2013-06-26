package com.vlingo.sdk.internal.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.logging.Logger;
import com.vlingo.sdk.internal.settings.Settings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class FileUtils
{
  private static final Logger log = Logger.getLogger(FileUtils.class);
  private static Integer versionCode = null;

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
  public static void extractAssetZip(String paramString, File paramFile)
  {
    // Byte code:
    //   0: invokestatic 64	com/vlingo/sdk/internal/core/ApplicationAdapter:getInstance	()Lcom/vlingo/sdk/internal/core/ApplicationAdapter;
    //   3: invokevirtual 68	com/vlingo/sdk/internal/core/ApplicationAdapter:getApplicationContext	()Landroid/content/Context;
    //   6: astore_2
    //   7: aconst_null
    //   8: astore_3
    //   9: aconst_null
    //   10: astore 4
    //   12: aload_2
    //   13: invokevirtual 74	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   16: aload_0
    //   17: invokevirtual 80	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   20: astore_3
    //   21: new 82	java/util/zip/ZipInputStream
    //   24: dup
    //   25: aload_3
    //   26: invokespecial 85	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   29: astore 11
    //   31: aload 11
    //   33: invokevirtual 89	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   36: astore 13
    //   38: aload 13
    //   40: ifnull +230 -> 270
    //   43: new 91	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   50: aload_1
    //   51: invokevirtual 98	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   54: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: getstatic 106	java/io/File:separator	Ljava/lang/String;
    //   60: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload 13
    //   65: invokevirtual 111	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   68: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: astore 16
    //   76: aload 13
    //   78: invokevirtual 111	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   81: ldc 116
    //   83: invokevirtual 122	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   86: bipush 255
    //   88: if_icmpeq +86 -> 174
    //   91: new 94	java/io/File
    //   94: dup
    //   95: aload 16
    //   97: invokespecial 125	java/io/File:<init>	(Ljava/lang/String;)V
    //   100: invokevirtual 129	java/io/File:getParentFile	()Ljava/io/File;
    //   103: astore 20
    //   105: aload 20
    //   107: invokevirtual 133	java/io/File:exists	()Z
    //   110: ifne +64 -> 174
    //   113: aload 20
    //   115: invokevirtual 136	java/io/File:mkdirs	()Z
    //   118: ifne +56 -> 174
    //   121: new 29	java/io/IOException
    //   124: dup
    //   125: new 91	java/lang/StringBuilder
    //   128: dup
    //   129: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   132: ldc 138
    //   134: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload 20
    //   139: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokespecial 142	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   148: athrow
    //   149: astore 12
    //   151: aload 11
    //   153: astore 4
    //   155: aload 4
    //   157: ifnull +8 -> 165
    //   160: aload 4
    //   162: invokevirtual 143	java/util/zip/ZipInputStream:close	()V
    //   165: aload_3
    //   166: ifnull +7 -> 173
    //   169: aload_3
    //   170: invokevirtual 56	java/io/InputStream:close	()V
    //   173: return
    //   174: new 36	java/io/FileOutputStream
    //   177: dup
    //   178: aload 16
    //   180: invokespecial 144	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   183: astore 17
    //   185: sipush 1024
    //   188: newarray byte
    //   190: astore 18
    //   192: aload 11
    //   194: aload 18
    //   196: invokevirtual 145	java/util/zip/ZipInputStream:read	([B)I
    //   199: istore 19
    //   201: iload 19
    //   203: bipush 255
    //   205: if_icmpeq +25 -> 230
    //   208: aload 17
    //   210: aload 18
    //   212: iconst_0
    //   213: iload 19
    //   215: invokevirtual 146	java/io/FileOutputStream:write	([BII)V
    //   218: aload 11
    //   220: aload 18
    //   222: invokevirtual 145	java/util/zip/ZipInputStream:read	([B)I
    //   225: istore 19
    //   227: goto -26 -> 201
    //   230: aload 11
    //   232: invokevirtual 149	java/util/zip/ZipInputStream:closeEntry	()V
    //   235: aload 17
    //   237: invokevirtual 150	java/io/FileOutputStream:close	()V
    //   240: goto -209 -> 31
    //   243: astore 8
    //   245: aload 11
    //   247: astore 4
    //   249: aload 4
    //   251: ifnull +8 -> 259
    //   254: aload 4
    //   256: invokevirtual 143	java/util/zip/ZipInputStream:close	()V
    //   259: aload_3
    //   260: ifnull +7 -> 267
    //   263: aload_3
    //   264: invokevirtual 56	java/io/InputStream:close	()V
    //   267: aload 8
    //   269: athrow
    //   270: aload 11
    //   272: ifnull +8 -> 280
    //   275: aload 11
    //   277: invokevirtual 143	java/util/zip/ZipInputStream:close	()V
    //   280: aload_3
    //   281: ifnull +50 -> 331
    //   284: aload_3
    //   285: invokevirtual 56	java/io/InputStream:close	()V
    //   288: goto -115 -> 173
    //   291: astore 14
    //   293: goto -120 -> 173
    //   296: astore 15
    //   298: goto -18 -> 280
    //   301: astore 7
    //   303: goto -138 -> 165
    //   306: astore 6
    //   308: goto -135 -> 173
    //   311: astore 10
    //   313: goto -54 -> 259
    //   316: astore 9
    //   318: goto -51 -> 267
    //   321: astore 8
    //   323: goto -74 -> 249
    //   326: astore 5
    //   328: goto -173 -> 155
    //   331: goto -158 -> 173
    //
    // Exception table:
    //   from	to	target	type
    //   31	149	149	java/io/IOException
    //   174	240	149	java/io/IOException
    //   31	149	243	finally
    //   174	240	243	finally
    //   284	288	291	java/io/IOException
    //   275	280	296	java/io/IOException
    //   160	165	301	java/io/IOException
    //   169	173	306	java/io/IOException
    //   254	259	311	java/io/IOException
    //   263	267	316	java/io/IOException
    //   12	31	321	finally
    //   12	31	326	java/io/IOException
  }

  public static void extractAssetZipIfNeeded(String paramString, File paramFile)
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    if (versionCode == null);
    try
    {
      versionCode = Integer.valueOf(localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0).versionCode);
      String str = "zip_size_" + paramString;
      int i = Settings.getPersistentInt(str, 0);
      if ((versionCode == null) || (i != versionCode.intValue()))
      {
        Settings.setPersistentInt(str, versionCode.intValue());
        extractAssetZip(paramString, paramFile);
      }
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        localNameNotFoundException.printStackTrace();
    }
  }

  // ERROR //
  public static final byte[] readResource(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 202	java/io/ByteArrayOutputStream
    //   5: dup
    //   6: sipush 500
    //   9: invokespecial 205	java/io/ByteArrayOutputStream:<init>	(I)V
    //   12: astore_2
    //   13: aconst_null
    //   14: astore_3
    //   15: sipush 500
    //   18: newarray byte
    //   20: astore 4
    //   22: ldc 207
    //   24: aload_0
    //   25: invokevirtual 210	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
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
    //   45: invokevirtual 56	java/io/InputStream:close	()V
    //   48: aload_2
    //   49: ifnull +7 -> 56
    //   52: aload_2
    //   53: invokevirtual 211	java/io/ByteArrayOutputStream:close	()V
    //   56: aload 9
    //   58: areturn
    //   59: aload_1
    //   60: aload 4
    //   62: invokevirtual 43	java/io/InputStream:read	([B)I
    //   65: istore 13
    //   67: iload 13
    //   69: bipush 255
    //   71: if_icmpeq +39 -> 110
    //   74: aload_2
    //   75: aload 4
    //   77: iconst_0
    //   78: iload 13
    //   80: invokevirtual 212	java/io/ByteArrayOutputStream:write	([BII)V
    //   83: goto -24 -> 59
    //   86: astore 8
    //   88: aload_1
    //   89: ifnull +7 -> 96
    //   92: aload_1
    //   93: invokevirtual 56	java/io/InputStream:close	()V
    //   96: aload_2
    //   97: ifnull +7 -> 104
    //   100: aload_2
    //   101: invokevirtual 211	java/io/ByteArrayOutputStream:close	()V
    //   104: aload_3
    //   105: astore 9
    //   107: goto -51 -> 56
    //   110: aload_2
    //   111: invokevirtual 213	java/io/ByteArrayOutputStream:flush	()V
    //   114: aload_2
    //   115: invokevirtual 217	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   118: astore 14
    //   120: aload 14
    //   122: astore_3
    //   123: aload_1
    //   124: ifnull +7 -> 131
    //   127: aload_1
    //   128: invokevirtual 56	java/io/InputStream:close	()V
    //   131: aload_2
    //   132: ifnull +7 -> 139
    //   135: aload_2
    //   136: invokevirtual 211	java/io/ByteArrayOutputStream:close	()V
    //   139: goto -35 -> 104
    //   142: astore 5
    //   144: aload_1
    //   145: ifnull +7 -> 152
    //   148: aload_1
    //   149: invokevirtual 56	java/io/InputStream:close	()V
    //   152: aload_2
    //   153: ifnull +7 -> 160
    //   156: aload_2
    //   157: invokevirtual 211	java/io/ByteArrayOutputStream:close	()V
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
 * Qualified Name:     com.vlingo.sdk.internal.util.FileUtils
 * JD-Core Version:    0.6.0
 */