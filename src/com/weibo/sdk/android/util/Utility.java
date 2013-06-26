package com.weibo.sdk.android.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboParameters;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Utility
{
  private static byte[] decodes;
  private static char[] encodes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

  static
  {
    decodes = new byte[256];
  }

  private static boolean __createNewFile(File paramFile)
  {
    int i = 0;
    if (paramFile == null);
    while (true)
    {
      return i;
      makesureParentExist(paramFile);
      if (paramFile.exists())
        delete(paramFile);
      try
      {
        boolean bool = paramFile.createNewFile();
        i = bool;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
  }

  private static void createNewFile(File paramFile)
  {
    if (paramFile == null);
    do
      return;
    while (__createNewFile(paramFile));
    throw new RuntimeException(paramFile.getAbsolutePath() + " doesn't be created!");
  }

  public static byte[] decodeBase62(String paramString)
  {
    if (paramString == null);
    char[] arrayOfChar;
    ByteArrayOutputStream localByteArrayOutputStream;
    int i;
    int j;
    int k;
    for (byte[] arrayOfByte = null; ; arrayOfByte = localByteArrayOutputStream.toByteArray())
    {
      return arrayOfByte;
      arrayOfChar = paramString.toCharArray();
      localByteArrayOutputStream = new ByteArrayOutputStream(paramString.toCharArray().length);
      i = 0;
      j = 0;
      k = 0;
      if (k < arrayOfChar.length)
        break;
    }
    int m = arrayOfChar[k];
    int n;
    if (m == 105)
    {
      k++;
      n = arrayOfChar[k];
      if (n == 97)
        m = 105;
    }
    else
    {
      label85: j = j << 6 | decodes[m];
      i += 6;
    }
    while (true)
    {
      if (i <= 7)
      {
        k++;
        break;
        if (n == 98)
        {
          m = 43;
          break label85;
        }
        if (n == 99)
        {
          m = 47;
          break label85;
        }
        k--;
        m = arrayOfChar[k];
        break label85;
      }
      i -= 8;
      localByteArrayOutputStream.write(j >> i);
      j &= -1 + (1 << i);
    }
  }

  public static Bundle decodeUrl(String paramString)
  {
    Bundle localBundle = new Bundle();
    String[] arrayOfString1;
    int i;
    if (paramString != null)
    {
      arrayOfString1 = paramString.split("&");
      i = arrayOfString1.length;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localBundle;
      String[] arrayOfString2 = arrayOfString1[j].split("=");
      localBundle.putString(URLDecoder.decode(arrayOfString2[0]), URLDecoder.decode(arrayOfString2[1]));
    }
  }

  private static void delete(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()) && (!paramFile.delete()))
      throw new RuntimeException(paramFile.getAbsolutePath() + " doesn't be deleted!");
  }

  private static boolean deleteDependon(File paramFile, int paramInt)
  {
    int i = 1;
    if (paramInt < 1)
      paramInt = 5;
    boolean bool = false;
    if (paramFile != null);
    while (true)
    {
      if ((bool) || (i > paramInt) || (!paramFile.isFile()) || (!paramFile.exists()))
        return bool;
      bool = paramFile.delete();
      if (bool)
        continue;
      i++;
    }
  }

  private static boolean deleteDependon(String paramString)
  {
    return deleteDependon(paramString, 0);
  }

  private static boolean deleteDependon(String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString));
    for (boolean bool = false; ; bool = deleteDependon(new File(paramString), paramInt))
      return bool;
  }

  private static boolean doesExisted(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()));
    for (int i = 1; ; i = 0)
      return i;
  }

  private static boolean doesExisted(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    for (boolean bool = false; ; bool = doesExisted(new File(paramString)))
      return bool;
  }

  public static String encodeBase62(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(2 * paramArrayOfByte.length);
    int i = 0;
    int j = 0;
    int k = 0;
    char c2;
    Object localObject2;
    if (k >= paramArrayOfByte.length)
      if (i > 0)
      {
        c2 = encodes[(j << 6 - i)];
        if (c2 != 'i')
          break label177;
        localObject2 = "ia";
      }
    while (true)
    {
      localStringBuffer.append(localObject2);
      return localStringBuffer.toString();
      j = j << 8 | 0xFF & paramArrayOfByte[k];
      i += 8;
      if (i <= 5)
      {
        k++;
        break;
      }
      char[] arrayOfChar = encodes;
      i -= 6;
      char c1 = arrayOfChar[(j >> i)];
      Object localObject1;
      if (c1 == 'i')
        localObject1 = "ia";
      while (true)
      {
        localStringBuffer.append(localObject1);
        j &= -1 + (1 << i);
        break;
        if (c1 == '+')
        {
          localObject1 = "ib";
          continue;
        }
        if (c1 == '/')
        {
          localObject1 = "ic";
          continue;
        }
        localObject1 = Character.valueOf(c1);
      }
      label177: if (c2 == '+')
      {
        localObject2 = "ib";
        continue;
      }
      if (c2 == '/')
      {
        localObject2 = "ic";
        continue;
      }
      localObject2 = Character.valueOf(c2);
    }
  }

  public static String encodeParameters(WeiboParameters paramWeiboParameters)
  {
    String str1;
    if ((paramWeiboParameters == null) || (isBundleEmpty(paramWeiboParameters)))
    {
      str1 = "";
      return str1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = 0;
    while (true)
    {
      if (j >= paramWeiboParameters.size())
      {
        str1 = localStringBuilder.toString();
        break;
      }
      String str2 = paramWeiboParameters.getKey(j);
      if (i != 0)
        localStringBuilder.append("&");
      try
      {
        localStringBuilder.append(URLEncoder.encode(str2, "UTF-8")).append("=").append(URLEncoder.encode(paramWeiboParameters.getValue(str2), "UTF-8"));
        label96: i++;
        j++;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        break label96;
      }
    }
  }

  public static String encodeUrl(WeiboParameters paramWeiboParameters)
  {
    if (paramWeiboParameters == null);
    StringBuilder localStringBuilder;
    int i;
    int j;
    for (String str2 = ""; ; str2 = localStringBuilder.toString())
    {
      return str2;
      localStringBuilder = new StringBuilder();
      i = 1;
      j = 0;
      if (j < paramWeiboParameters.size())
        break;
    }
    if (i != 0)
    {
      i = 0;
      label46: String str1 = paramWeiboParameters.getKey(j);
      if (paramWeiboParameters.getValue(str1) != null)
        break label106;
      Log.i("encodeUrl", "key:" + str1 + " 's value is null");
    }
    while (true)
    {
      j++;
      break;
      localStringBuilder.append("&");
      break label46;
      label106: localStringBuilder.append(URLEncoder.encode(paramWeiboParameters.getKey(j)) + "=" + URLEncoder.encode(paramWeiboParameters.getValue(j)));
    }
  }

  private static boolean isBundleEmpty(WeiboParameters paramWeiboParameters)
  {
    if ((paramWeiboParameters == null) || (paramWeiboParameters.size() == 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isWifi(Context paramContext)
  {
    int i = 1;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.getType() == i));
    while (true)
    {
      return i;
      i = 0;
    }
  }

  private static void makesureFileExist(File paramFile)
  {
    if (paramFile == null);
    while (true)
    {
      return;
      if (!paramFile.exists())
      {
        makesureParentExist(paramFile);
        createNewFile(paramFile);
        continue;
      }
    }
  }

  private static void makesureFileExist(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      makesureFileExist(new File(paramString));
    }
  }

  private static void makesureParentExist(File paramFile)
  {
    if (paramFile == null);
    while (true)
    {
      return;
      File localFile = paramFile.getParentFile();
      if ((localFile == null) || (localFile.exists()))
        continue;
      mkdirs(localFile);
    }
  }

  private static void mkdirs(File paramFile)
  {
    if (paramFile == null);
    do
      return;
    while ((paramFile.exists()) || (paramFile.mkdirs()));
    throw new RuntimeException("fail to make " + paramFile.getAbsolutePath());
  }

  public static Bundle parseUrl(String paramString)
  {
    try
    {
      URL localURL = new URL(paramString);
      localBundle = decodeUrl(localURL.getQuery());
      localBundle.putAll(decodeUrl(localURL.getRef()));
      return localBundle;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      while (true)
        Bundle localBundle = new Bundle();
    }
  }

  public static void showAlert(Context paramContext, String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.create().show();
  }

  public static final class UploadImageUtils
  {
    private static void revitionImageSize(String paramString, int paramInt1, int paramInt2)
      throws IOException
    {
      if (paramInt1 <= 0)
        throw new IllegalArgumentException("size must be greater than 0!");
      if (!Utility.access$0(paramString))
      {
        if (paramString == null)
          paramString = "null";
        throw new FileNotFoundException(paramString);
      }
      if (!BitmapHelper.verifyBitmap(paramString))
        throw new IOException("");
      FileInputStream localFileInputStream = new FileInputStream(paramString);
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(localFileInputStream, null, localOptions);
      Bitmap localBitmap;
      label177: FileOutputStream localFileOutputStream;
      try
      {
        localFileInputStream.close();
        i = 0;
        if ((localOptions.outWidth >> i <= paramInt1) && (localOptions.outHeight >> i <= paramInt1))
        {
          localOptions.inSampleSize = (int)Math.pow(2.0D, i);
          localOptions.inJustDecodeBounds = false;
          localBitmap = safeDecodeBimtapFile(paramString, localOptions);
          if (localBitmap != null)
            break label177;
          throw new IOException("Bitmap decode error!");
        }
      }
      catch (Exception localException1)
      {
        while (true)
        {
          int i;
          localException1.printStackTrace();
          continue;
          i++;
        }
        Utility.access$1(paramString);
        Utility.access$2(paramString);
        localFileOutputStream = new FileOutputStream(paramString);
        if (localOptions == null)
          break label245;
      }
      if ((localOptions.outMimeType != null) && (localOptions.outMimeType.contains("png")))
        localBitmap.compress(Bitmap.CompressFormat.PNG, paramInt2, localFileOutputStream);
      try
      {
        while (true)
        {
          localFileOutputStream.close();
          localBitmap.recycle();
          return;
          label245: localBitmap.compress(Bitmap.CompressFormat.JPEG, paramInt2, localFileOutputStream);
        }
      }
      catch (Exception localException2)
      {
        while (true)
          localException2.printStackTrace();
      }
    }

    // ERROR //
    private static void revitionImageSizeHD(String paramString, int paramInt1, int paramInt2)
      throws IOException
    {
      // Byte code:
      //   0: iload_1
      //   1: ifgt +13 -> 14
      //   4: new 19	java/lang/IllegalArgumentException
      //   7: dup
      //   8: ldc 21
      //   10: invokespecial 24	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   13: athrow
      //   14: aload_0
      //   15: invokestatic 28	com/weibo/sdk/android/util/Utility:access$0	(Ljava/lang/String;)Z
      //   18: ifne +23 -> 41
      //   21: aload_0
      //   22: ifnonnull +6 -> 28
      //   25: ldc 30
      //   27: astore_0
      //   28: new 32	java/io/FileNotFoundException
      //   31: dup
      //   32: aload_0
      //   33: invokespecial 33	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
      //   36: astore 21
      //   38: aload 21
      //   40: athrow
      //   41: aload_0
      //   42: invokestatic 38	com/weibo/sdk/android/util/BitmapHelper:verifyBitmap	(Ljava/lang/String;)Z
      //   45: ifne +13 -> 58
      //   48: new 15	java/io/IOException
      //   51: dup
      //   52: ldc 40
      //   54: invokespecial 41	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   57: athrow
      //   58: iload_1
      //   59: iconst_2
      //   60: imul
      //   61: istore_3
      //   62: new 43	java/io/FileInputStream
      //   65: dup
      //   66: aload_0
      //   67: invokespecial 44	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
      //   70: astore 4
      //   72: new 46	android/graphics/BitmapFactory$Options
      //   75: dup
      //   76: invokespecial 47	android/graphics/BitmapFactory$Options:<init>	()V
      //   79: astore 5
      //   81: aload 5
      //   83: iconst_1
      //   84: putfield 51	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
      //   87: aload 4
      //   89: aconst_null
      //   90: aload 5
      //   92: invokestatic 57	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   95: pop
      //   96: aload 4
      //   98: invokevirtual 60	java/io/FileInputStream:close	()V
      //   101: iconst_0
      //   102: istore 8
      //   104: aload 5
      //   106: getfield 64	android/graphics/BitmapFactory$Options:outWidth	I
      //   109: iload 8
      //   111: ishr
      //   112: iload_3
      //   113: if_icmpgt +69 -> 182
      //   116: aload 5
      //   118: getfield 67	android/graphics/BitmapFactory$Options:outHeight	I
      //   121: iload 8
      //   123: ishr
      //   124: iload_3
      //   125: if_icmpgt +57 -> 182
      //   128: aload 5
      //   130: ldc2_w 68
      //   133: iload 8
      //   135: i2d
      //   136: invokestatic 75	java/lang/Math:pow	(DD)D
      //   139: d2i
      //   140: putfield 78	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   143: aload 5
      //   145: iconst_0
      //   146: putfield 51	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
      //   149: aload_0
      //   150: aload 5
      //   152: invokestatic 82	com/weibo/sdk/android/util/Utility$UploadImageUtils:safeDecodeBimtapFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   155: astore 9
      //   157: aload 9
      //   159: ifnonnull +29 -> 188
      //   162: new 15	java/io/IOException
      //   165: dup
      //   166: ldc 84
      //   168: invokespecial 41	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   171: athrow
      //   172: astore 7
      //   174: aload 7
      //   176: invokevirtual 87	java/lang/Exception:printStackTrace	()V
      //   179: goto -78 -> 101
      //   182: iinc 8 1
      //   185: goto -81 -> 104
      //   188: aload_0
      //   189: invokestatic 90	com/weibo/sdk/android/util/Utility:access$1	(Ljava/lang/String;)Z
      //   192: pop
      //   193: aload_0
      //   194: invokestatic 93	com/weibo/sdk/android/util/Utility:access$2	(Ljava/lang/String;)V
      //   197: aload 9
      //   199: invokevirtual 134	android/graphics/Bitmap:getWidth	()I
      //   202: aload 9
      //   204: invokevirtual 137	android/graphics/Bitmap:getHeight	()I
      //   207: if_icmple +176 -> 383
      //   210: aload 9
      //   212: invokevirtual 134	android/graphics/Bitmap:getWidth	()I
      //   215: istore 11
      //   217: iload_1
      //   218: i2f
      //   219: iload 11
      //   221: i2f
      //   222: fdiv
      //   223: fstore 12
      //   225: fload 12
      //   227: fconst_1
      //   228: fcmpg
      //   229: ifge +95 -> 324
      //   232: fload 12
      //   234: aload 9
      //   236: invokevirtual 134	android/graphics/Bitmap:getWidth	()I
      //   239: i2f
      //   240: fmul
      //   241: f2i
      //   242: fload 12
      //   244: aload 9
      //   246: invokevirtual 137	android/graphics/Bitmap:getHeight	()I
      //   249: i2f
      //   250: fmul
      //   251: f2i
      //   252: getstatic 143	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
      //   255: invokestatic 147	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
      //   258: astore 18
      //   260: aload 18
      //   262: ifnonnull +8 -> 270
      //   265: aload 9
      //   267: invokevirtual 124	android/graphics/Bitmap:recycle	()V
      //   270: new 149	android/graphics/Canvas
      //   273: dup
      //   274: aload 18
      //   276: invokespecial 152	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
      //   279: astore 19
      //   281: new 154	android/graphics/Matrix
      //   284: dup
      //   285: invokespecial 155	android/graphics/Matrix:<init>	()V
      //   288: astore 20
      //   290: aload 20
      //   292: fload 12
      //   294: fload 12
      //   296: invokevirtual 159	android/graphics/Matrix:setScale	(FF)V
      //   299: aload 19
      //   301: aload 9
      //   303: aload 20
      //   305: new 161	android/graphics/Paint
      //   308: dup
      //   309: invokespecial 162	android/graphics/Paint:<init>	()V
      //   312: invokevirtual 166	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
      //   315: aload 9
      //   317: invokevirtual 124	android/graphics/Bitmap:recycle	()V
      //   320: aload 18
      //   322: astore 9
      //   324: new 95	java/io/FileOutputStream
      //   327: dup
      //   328: aload_0
      //   329: invokespecial 96	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
      //   332: astore 13
      //   334: aload 5
      //   336: ifnull +75 -> 411
      //   339: aload 5
      //   341: getfield 100	android/graphics/BitmapFactory$Options:outMimeType	Ljava/lang/String;
      //   344: ifnull +67 -> 411
      //   347: aload 5
      //   349: getfield 100	android/graphics/BitmapFactory$Options:outMimeType	Ljava/lang/String;
      //   352: ldc 102
      //   354: invokevirtual 108	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   357: ifeq +54 -> 411
      //   360: aload 9
      //   362: getstatic 114	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
      //   365: iload_2
      //   366: aload 13
      //   368: invokevirtual 120	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
      //   371: pop
      //   372: aload 13
      //   374: invokevirtual 121	java/io/FileOutputStream:close	()V
      //   377: aload 9
      //   379: invokevirtual 124	android/graphics/Bitmap:recycle	()V
      //   382: return
      //   383: aload 9
      //   385: invokevirtual 137	android/graphics/Bitmap:getHeight	()I
      //   388: istore 11
      //   390: goto -173 -> 217
      //   393: astore 17
      //   395: invokestatic 171	java/lang/System:gc	()V
      //   398: ldc2_w 172
      //   401: fload 12
      //   403: f2d
      //   404: dmul
      //   405: d2f
      //   406: fstore 12
      //   408: goto -176 -> 232
      //   411: aload 9
      //   413: getstatic 127	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
      //   416: iload_2
      //   417: aload 13
      //   419: invokevirtual 120	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
      //   422: pop
      //   423: goto -51 -> 372
      //   426: astore 15
      //   428: aload 15
      //   430: invokevirtual 87	java/lang/Exception:printStackTrace	()V
      //   433: goto -56 -> 377
      //
      // Exception table:
      //   from	to	target	type
      //   96	101	172	java/lang/Exception
      //   232	260	393	java/lang/OutOfMemoryError
      //   372	377	426	java/lang/Exception
    }

    public static boolean revitionPostImageSize(String paramString)
    {
      try
      {
        if (Weibo.isWifi)
          revitionImageSizeHD(paramString, 1600, 75);
        else
          revitionImageSize(paramString, 1024, 75);
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        i = 0;
        break label42;
      }
      int i = 1;
      label42: return i;
    }

    // ERROR //
    private static Bitmap safeDecodeBimtapFile(String paramString, BitmapFactory.Options paramOptions)
    {
      // Byte code:
      //   0: aload_1
      //   1: astore_2
      //   2: aload_2
      //   3: ifnonnull +16 -> 19
      //   6: new 46	android/graphics/BitmapFactory$Options
      //   9: dup
      //   10: invokespecial 47	android/graphics/BitmapFactory$Options:<init>	()V
      //   13: astore_2
      //   14: aload_2
      //   15: iconst_1
      //   16: putfield 78	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   19: aconst_null
      //   20: astore_3
      //   21: iconst_0
      //   22: istore 4
      //   24: aconst_null
      //   25: astore 5
      //   27: iload 4
      //   29: iconst_5
      //   30: if_icmplt +8 -> 38
      //   33: aload 5
      //   35: pop
      //   36: aload_3
      //   37: areturn
      //   38: new 43	java/io/FileInputStream
      //   41: dup
      //   42: aload_0
      //   43: invokespecial 44	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
      //   46: astore 6
      //   48: aload 6
      //   50: aconst_null
      //   51: aload_1
      //   52: invokestatic 57	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   55: astore 10
      //   57: aload 10
      //   59: astore_3
      //   60: aload 6
      //   62: invokevirtual 60	java/io/FileInputStream:close	()V
      //   65: goto -29 -> 36
      //   68: astore 11
      //   70: aload 11
      //   72: invokevirtual 184	java/io/IOException:printStackTrace	()V
      //   75: goto -39 -> 36
      //   78: astore 8
      //   80: aload 8
      //   82: invokevirtual 185	java/lang/OutOfMemoryError:printStackTrace	()V
      //   85: aload_2
      //   86: iconst_2
      //   87: aload_2
      //   88: getfield 78	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   91: imul
      //   92: putfield 78	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   95: aload 6
      //   97: invokevirtual 60	java/io/FileInputStream:close	()V
      //   100: iinc 4 1
      //   103: aload 6
      //   105: astore 5
      //   107: goto -80 -> 27
      //   110: astore 9
      //   112: aload 9
      //   114: invokevirtual 184	java/io/IOException:printStackTrace	()V
      //   117: goto -17 -> 100
      //   120: astore 12
      //   122: aload 5
      //   124: pop
      //   125: goto -89 -> 36
      //   128: astore 7
      //   130: goto -5 -> 125
      //   133: astore 8
      //   135: aload 5
      //   137: astore 6
      //   139: goto -59 -> 80
      //
      // Exception table:
      //   from	to	target	type
      //   60	65	68	java/io/IOException
      //   48	57	78	java/lang/OutOfMemoryError
      //   60	65	78	java/lang/OutOfMemoryError
      //   70	75	78	java/lang/OutOfMemoryError
      //   95	100	110	java/io/IOException
      //   38	48	120	java/io/FileNotFoundException
      //   48	57	128	java/io/FileNotFoundException
      //   60	65	128	java/io/FileNotFoundException
      //   70	75	128	java/io/FileNotFoundException
      //   38	48	133	java/lang/OutOfMemoryError
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.util.Utility
 * JD-Core Version:    0.6.0
 */