package com.vlingo.sdk.internal.http;

import com.vlingo.sdk.internal.net.ConnectionManager;
import com.vlingo.sdk.internal.net.ConnectionProvider;
import com.vlingo.sdk.internal.net.HttpConnection;
import com.vlingo.sdk.internal.recognizer.ClientMeta;
import com.vlingo.sdk.internal.recognizer.SoftwareMeta;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.util.XmlUtils;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

public class HttpUtil
{
  public static String CR;
  public static String CRLF;
  public static byte[] CRLF_BYTES;
  public static char CR_CHAR = '\000';
  public static final String HEADER_CONNECTION = "Connection";
  public static final String HEADER_CONTENT_LENGTH = "Content-Length";
  public static final String HEADER_CONTENT_TYPE = "Content-type";
  public static final String HEADER_COOKIE = "Cookie";
  public static final String HEADER_SET_COOKIE = "Set-Cookie";
  public static final String HEADER_STATUS = "STATUS";
  public static final String HEADER_TRANSFER_ENCODING = "Transfer-Encoding";
  public static final String HEADER_USER_AGENT = "User-Agent";
  public static String LF;
  public static char LF_CHAR = '\000';
  public static final String METHOD_GET = "GET";
  public static final String METHOD_HEAD = "HEAD";
  public static final String METHOD_POST = "POST";
  public static final String VAL_CHUNKED = "chunked";
  public static final String VAL_CLOSE = "Close";
  public static final String VAL_KEEP_ALIVE = "keep-alive";

  static
  {
    CR_CHAR = '\r';
    CR = "" + CR_CHAR;
    LF = "" + LF_CHAR;
    CRLF = "" + CR_CHAR + LF_CHAR;
    CRLF_BYTES = CRLF.getBytes();
  }

  public static String byteArrayToString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null);
    String str;
    try
    {
      str = "null";
      break label39;
      if (paramArrayOfByte.length == 0)
        str = "";
      else
        str = new String(paramArrayOfByte, "UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      str = "unsupported encoding: UTF-8";
    }
    label39: return str;
  }

  public static String bytesToString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramInt1 + paramInt2;
    String str = "";
    for (int j = paramInt1; j < i; j++)
    {
      if (str.length() > 0)
        str = str + "|";
      str = str + paramArrayOfByte[j];
    }
    return str;
  }

  public static String genAtr(String paramString1, String paramString2)
  {
    return paramString1 + "=\"" + XmlUtils.xmlEncode(paramString2) + "\" ";
  }

  public static Hashtable<String, String> getCacheControlHeaders(HttpConnection paramHttpConnection)
  {
    Hashtable localHashtable = new Hashtable();
    String str1 = getHttpResponseHeader("cache-control", paramHttpConnection);
    if ((str1 != null) && (str1.length() > 0))
    {
      String[] arrayOfString;
      int i;
      label47: int j;
      String str2;
      if (str1.indexOf(",") != -1)
      {
        arrayOfString = StringUtils.split(str1, ',');
        i = 0;
        if (i >= arrayOfString.length)
          break label135;
        j = str1.indexOf("=");
        if (j == -1)
          break label125;
        str2 = str1.substring(0, j);
      }
      for (String str3 = str1.substring(j + 1, -1 + (str1.length() - j)); ; str3 = "1")
      {
        localHashtable.put(str2, str3);
        i++;
        break label47;
        arrayOfString = new String[1];
        arrayOfString[0] = str1;
        break;
        label125: str2 = str1;
      }
    }
    label135: return localHashtable;
  }

  public static String getCookies(Hashtable<?, ?> paramHashtable)
    throws IOException
  {
    Enumeration localEnumeration = paramHashtable.keys();
    StringBuffer localStringBuffer = new StringBuffer();
    while (localEnumeration.hasMoreElements())
    {
      String str1 = (String)localEnumeration.nextElement();
      String str2 = (String)paramHashtable.get(str1);
      if (localStringBuffer.length() > 0)
        localStringBuffer.append(";");
      localStringBuffer.append(str1 + "=" + str2);
    }
    return localStringBuffer.toString();
  }

  public static String getDomain(String paramString)
  {
    Object localObject = null;
    try
    {
      URL localURL = new URL(paramString);
      localObject = localURL;
      label13: if (localObject != null);
      for (String str = localObject.getHost(); ; str = null)
        return str;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      break label13;
    }
  }

  public static String getHttpResponseHeader(String paramString, HttpConnection paramHttpConnection)
  {
    String str1 = "";
    int i = 0;
    try
    {
      while (true)
      {
        str1 = paramHttpConnection.getHeaderFieldKey(i);
        if (str1 == null)
          break;
        if (str1.equalsIgnoreCase(paramString))
        {
          String str2 = paramHttpConnection.getHeaderField(i);
          if (str2 == null);
        }
        i++;
      }
    }
    catch (IOException localIOException)
    {
    }
    return str1;
  }

  public static String getPath(String paramString)
  {
    Object localObject = null;
    try
    {
      URL localURL = new URL(paramString);
      localObject = localURL;
      label13: if (localObject != null);
      for (String str = localObject.getPath(); ; str = null)
        return str;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      break label13;
    }
  }

  public static String httpStatusCodeToString(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default:
      str = "Unknown";
    case 200:
    case 301:
    case 302:
    case 304:
    case 400:
    case 401:
    case 403:
    case 404:
    case 405:
    case 500:
    case 502:
    case 503:
    }
    while (true)
    {
      return str;
      str = "OK";
      continue;
      str = "Moved Permanently";
      continue;
      str = "Found";
      continue;
      str = "Not Modified";
      continue;
      str = "Bad Request";
      continue;
      str = "Not Authorized";
      continue;
      str = "Forbidden";
      continue;
      str = "Not Found";
      continue;
      str = "Method Not Allowed";
      continue;
      str = "Internal Server Error";
      continue;
      str = "Bad Gateway";
      continue;
      str = "Service Unavailable";
    }
  }

  public static HttpConnection newHttpConnection(ConnectionProvider paramConnectionProvider, String paramString1, String paramString2, boolean paramBoolean, Hashtable<?, ?> paramHashtable1, Hashtable<?, ?> paramHashtable2, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta)
    throws IOException
  {
    if (paramString1.equalsIgnoreCase("GET"));
    for (HttpConnection localHttpConnection = (HttpConnection)paramConnectionProvider.getConnection(paramString2, 0, true); ; localHttpConnection = (HttpConnection)paramConnectionProvider.getConnection(paramString2, 1, true))
    {
      localHttpConnection.setRequestMethod(paramString1);
      if (paramBoolean)
        localHttpConnection.setRequestProperty("Connection", "keep-alive");
      if (paramHashtable2 == null)
        break;
      Enumeration localEnumeration = paramHashtable2.keys();
      while (localEnumeration.hasMoreElements())
      {
        String str2 = (String)localEnumeration.nextElement();
        localHttpConnection.setRequestProperty(str2, (String)paramHashtable2.get(str2));
      }
    }
    if (paramHashtable1 != null)
    {
      String str1 = getCookies(paramHashtable1);
      if (str1.length() > 0)
        localHttpConnection.setRequestProperty("Cookie", str1);
    }
    return localHttpConnection;
  }

  public static HttpConnection newHttpConnection(String paramString1, String paramString2, boolean paramBoolean, Hashtable<?, ?> paramHashtable1, Hashtable<?, ?> paramHashtable2, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta)
    throws IOException
  {
    return newHttpConnection(ConnectionManager.getInstance(), paramString1, paramString2, paramBoolean, paramHashtable1, paramHashtable2, paramClientMeta, paramSoftwareMeta);
  }

  public static void println(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("out is null");
    paramOutputStream.write(paramString.getBytes());
    paramOutputStream.write(CRLF_BYTES);
  }

  // ERROR //
  public static byte[] readData(InputStream paramInputStream)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 297	java/io/ByteArrayOutputStream
    //   5: dup
    //   6: invokespecial 298	java/io/ByteArrayOutputStream:<init>	()V
    //   9: astore_2
    //   10: aload_0
    //   11: aload_2
    //   12: invokestatic 302	com/vlingo/sdk/internal/http/HttpUtil:transfer	(Ljava/io/InputStream;Ljava/io/ByteArrayOutputStream;)V
    //   15: aload_2
    //   16: invokevirtual 305	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   19: astore 5
    //   21: aload_2
    //   22: ifnull +7 -> 29
    //   25: aload_2
    //   26: invokevirtual 308	java/io/ByteArrayOutputStream:close	()V
    //   29: aload 5
    //   31: areturn
    //   32: astore_3
    //   33: aload_1
    //   34: ifnull +7 -> 41
    //   37: aload_1
    //   38: invokevirtual 308	java/io/ByteArrayOutputStream:close	()V
    //   41: aload_3
    //   42: athrow
    //   43: astore 6
    //   45: goto -16 -> 29
    //   48: astore 4
    //   50: goto -9 -> 41
    //   53: astore_3
    //   54: aload_2
    //   55: astore_1
    //   56: goto -23 -> 33
    //
    // Exception table:
    //   from	to	target	type
    //   2	10	32	finally
    //   25	29	43	java/lang/Exception
    //   37	41	48	java/lang/Exception
    //   10	21	53	finally
  }

  public static String readLine(DataInputStream paramDataInputStream)
    throws IOException
  {
    int i = paramDataInputStream.read();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    if ((i < 0) || (i == LF_CHAR))
      if (localByteArrayOutputStream.size() != 0)
        break label48;
    label48: for (String str = null; ; str = localByteArrayOutputStream.toString())
    {
      return str;
      localByteArrayOutputStream.write(i);
      i = paramDataInputStream.read();
      break;
    }
  }

  public static void transfer(InputStream paramInputStream, ByteArrayOutputStream paramByteArrayOutputStream)
  {
    byte[] arrayOfByte = new byte[1024];
    int i = 1;
    while (true)
    {
      try
      {
        j = paramInputStream.available();
        break label66;
        int k = paramInputStream.read(arrayOfByte, 0, j);
        if (k >= 0)
          continue;
        i = 0;
        break label66;
        paramByteArrayOutputStream.write(arrayOfByte, 0, k);
        j = paramInputStream.available();
        if (j == 0)
          paramByteArrayOutputStream.flush();
      }
      catch (Exception localException)
      {
      }
      label66: 
      do
        return;
      while (i == 0);
      if (j <= 0)
        j = 1;
      if (j <= 1024)
        continue;
      int j = 1024;
    }
  }

  public static void writeCRLF(OutputStream paramOutputStream)
    throws IOException
  {
    println(paramOutputStream, "");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.HttpUtil
 * JD-Core Version:    0.6.0
 */