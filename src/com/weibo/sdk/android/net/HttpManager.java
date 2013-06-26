package com.weibo.sdk.android.net;

import android.text.TextUtils;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.util.Utility;
import com.weibo.sdk.android.util.Utility.UploadImageUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpManager
{
  private static final String BOUNDARY = getBoundry();
  private static final String END_MP_BOUNDARY;
  public static final String HTTPMETHOD_GET = "GET";
  private static final String HTTPMETHOD_POST = "POST";
  private static final String MP_BOUNDARY = "--" + BOUNDARY;
  private static final String MULTIPART_FORM_DATA = "multipart/form-data";
  private static final int SET_CONNECTION_TIMEOUT = 5000;
  private static final int SET_SOCKET_TIMEOUT = 20000;

  static
  {
    END_MP_BOUNDARY = "--" + BOUNDARY + "--";
  }

  static String getBoundry()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 1;
    if (i >= 12)
      return localStringBuffer.toString();
    long l = System.currentTimeMillis() + i;
    if (l % 3L == 0L)
      localStringBuffer.append((char)(int)l % '\t');
    while (true)
    {
      i++;
      break;
      if (l % 3L == 1L)
      {
        localStringBuffer.append((char)(int)(65L + l % 26L));
        continue;
      }
      localStringBuffer.append((char)(int)(97L + l % 26L));
    }
  }

  private static HttpClient getNewHttpClient()
  {
    try
    {
      KeyStore localKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      localKeyStore.load(null, null);
      MySSLSocketFactory localMySSLSocketFactory = new MySSLSocketFactory(localKeyStore);
      localMySSLSocketFactory.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
      HttpConnectionParams.setSoTimeout(localBasicHttpParams, 10000);
      HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", localMySSLSocketFactory, 443));
      ThreadSafeClientConnManager localThreadSafeClientConnManager = new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry);
      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 5000);
      HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
      localDefaultHttpClient = new DefaultHttpClient(localThreadSafeClientConnManager, localBasicHttpParams);
      return localDefaultHttpClient;
    }
    catch (Exception localException)
    {
      while (true)
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    }
  }

  // ERROR //
  private static void imageContentToUpload(OutputStream paramOutputStream, String paramString)
    throws WeiboException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: new 35	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 178	java/lang/StringBuilder:<init>	()V
    //   12: astore_2
    //   13: aload_2
    //   14: getstatic 50	com/weibo/sdk/android/net/HttpManager:MP_BOUNDARY	Ljava/lang/String;
    //   17: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: ldc 180
    //   22: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload_2
    //   27: ldc 182
    //   29: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: ldc 184
    //   34: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: ldc 186
    //   39: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload_2
    //   44: ldc 188
    //   46: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: ldc 190
    //   51: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: ldc 192
    //   56: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload_2
    //   61: invokevirtual 48	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: invokevirtual 198	java/lang/String:getBytes	()[B
    //   67: astore 6
    //   69: aconst_null
    //   70: astore 7
    //   72: aload_0
    //   73: aload 6
    //   75: invokevirtual 204	java/io/OutputStream:write	([B)V
    //   78: new 206	java/io/FileInputStream
    //   81: dup
    //   82: aload_1
    //   83: invokespecial 207	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   86: astore 11
    //   88: ldc 208
    //   90: newarray byte
    //   92: astore 12
    //   94: aload 11
    //   96: aload 12
    //   98: invokevirtual 212	java/io/FileInputStream:read	([B)I
    //   101: istore 13
    //   103: iload 13
    //   105: bipush 255
    //   107: if_icmpne +62 -> 169
    //   110: aload_0
    //   111: ldc 180
    //   113: invokevirtual 198	java/lang/String:getBytes	()[B
    //   116: invokevirtual 204	java/io/OutputStream:write	([B)V
    //   119: aload_0
    //   120: new 35	java/lang/StringBuilder
    //   123: dup
    //   124: ldc 180
    //   126: invokespecial 41	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   129: getstatic 52	com/weibo/sdk/android/net/HttpManager:END_MP_BOUNDARY	Ljava/lang/String;
    //   132: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: invokevirtual 48	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   138: invokevirtual 198	java/lang/String:getBytes	()[B
    //   141: invokevirtual 204	java/io/OutputStream:write	([B)V
    //   144: aload 11
    //   146: ifnull -142 -> 4
    //   149: aload 11
    //   151: invokevirtual 215	java/io/FileInputStream:close	()V
    //   154: goto -150 -> 4
    //   157: astore 14
    //   159: new 175	com/weibo/sdk/android/WeiboException
    //   162: dup
    //   163: aload 14
    //   165: invokespecial 218	com/weibo/sdk/android/WeiboException:<init>	(Ljava/lang/Exception;)V
    //   168: athrow
    //   169: aload_0
    //   170: aload 12
    //   172: iconst_0
    //   173: iload 13
    //   175: invokevirtual 221	java/io/OutputStream:write	([BII)V
    //   178: goto -84 -> 94
    //   181: astore 8
    //   183: aload 11
    //   185: astore 7
    //   187: new 175	com/weibo/sdk/android/WeiboException
    //   190: dup
    //   191: aload 8
    //   193: invokespecial 218	com/weibo/sdk/android/WeiboException:<init>	(Ljava/lang/Exception;)V
    //   196: athrow
    //   197: astore 9
    //   199: aload 7
    //   201: ifnull +8 -> 209
    //   204: aload 7
    //   206: invokevirtual 215	java/io/FileInputStream:close	()V
    //   209: aload 9
    //   211: athrow
    //   212: astore 10
    //   214: new 175	com/weibo/sdk/android/WeiboException
    //   217: dup
    //   218: aload 10
    //   220: invokespecial 218	com/weibo/sdk/android/WeiboException:<init>	(Ljava/lang/Exception;)V
    //   223: athrow
    //   224: astore 9
    //   226: aload 11
    //   228: astore 7
    //   230: goto -31 -> 199
    //   233: astore 8
    //   235: goto -48 -> 187
    //
    // Exception table:
    //   from	to	target	type
    //   149	154	157	java/io/IOException
    //   88	144	181	java/io/IOException
    //   169	178	181	java/io/IOException
    //   72	88	197	finally
    //   187	197	197	finally
    //   204	209	212	java/io/IOException
    //   88	144	224	finally
    //   169	178	224	finally
    //   72	88	233	java/io/IOException
  }

  public static String openUrl(String paramString1, String paramString2, WeiboParameters paramWeiboParameters, String paramString3)
    throws WeiboException
  {
    HttpResponse localHttpResponse;
    while (true)
    {
      try
      {
        HttpClient localHttpClient = getNewHttpClient();
        localObject = null;
        localHttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
        if (paramString2.equals("GET"))
        {
          localObject = new HttpGet(paramString1 + "?" + Utility.encodeUrl(paramWeiboParameters));
          localHttpResponse = localHttpClient.execute((HttpUriRequest)localObject);
          int i = localHttpResponse.getStatusLine().getStatusCode();
          if (i == 200)
            break;
          throw new WeiboException(readHttpResponse(localHttpResponse), i);
        }
      }
      catch (IOException localIOException)
      {
        throw new WeiboException(localIOException);
      }
      if (paramString2.equals("POST"))
      {
        HttpPost localHttpPost = new HttpPost(paramString1);
        localObject = localHttpPost;
        String str1 = paramWeiboParameters.getValue("content-type");
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        if (!TextUtils.isEmpty(paramString3))
        {
          paramToUpload(localByteArrayOutputStream, paramWeiboParameters);
          localHttpPost.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
          Utility.UploadImageUtils.revitionPostImageSize(paramString3);
          imageContentToUpload(localByteArrayOutputStream, paramString3);
          byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
          localByteArrayOutputStream.close();
          localHttpPost.setEntity(new ByteArrayEntity(arrayOfByte));
          continue;
        }
        if (str1 != null)
        {
          paramWeiboParameters.remove("content-type");
          localHttpPost.setHeader("Content-Type", str1);
        }
        while (true)
        {
          localByteArrayOutputStream.write(Utility.encodeParameters(paramWeiboParameters).getBytes("UTF-8"));
          break;
          localHttpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        }
      }
      if (!paramString2.equals("DELETE"))
        continue;
      Object localObject = new HttpDelete(paramString1);
    }
    String str2 = readHttpResponse(localHttpResponse);
    return (String)str2;
  }

  private static void paramToUpload(OutputStream paramOutputStream, WeiboParameters paramWeiboParameters)
    throws WeiboException
  {
    int i = 0;
    while (true)
    {
      if (i >= paramWeiboParameters.size())
        return;
      String str = paramWeiboParameters.getKey(i);
      StringBuilder localStringBuilder = new StringBuilder(10);
      localStringBuilder.setLength(0);
      localStringBuilder.append(MP_BOUNDARY).append("\r\n");
      localStringBuilder.append("content-disposition: form-data; name=\"").append(str).append("\"\r\n\r\n");
      localStringBuilder.append(paramWeiboParameters.getValue(str)).append("\r\n");
      byte[] arrayOfByte = localStringBuilder.toString().getBytes();
      try
      {
        paramOutputStream.write(arrayOfByte);
        i++;
      }
      catch (IOException localIOException)
      {
      }
    }
    throw new WeiboException(localIOException);
  }

  private static String readHttpResponse(HttpResponse paramHttpResponse)
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    String str;
    try
    {
      Object localObject = localHttpEntity.getContent();
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      Header localHeader = paramHttpResponse.getFirstHeader("Content-Encoding");
      if ((localHeader != null) && (localHeader.getValue().toLowerCase().indexOf("gzip") > -1))
        localObject = new GZIPInputStream((InputStream)localObject);
      byte[] arrayOfByte = new byte[512];
      while (true)
      {
        int i = ((InputStream)localObject).read(arrayOfByte);
        if (i == -1)
        {
          str = new String(localByteArrayOutputStream.toByteArray());
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      str = "";
    }
    catch (IOException localIOException)
    {
      label128: break label128;
    }
    return (String)str;
  }

  private static class MySSLSocketFactory extends org.apache.http.conn.ssl.SSLSocketFactory
  {
    SSLContext sslContext = SSLContext.getInstance("TLS");

    public MySSLSocketFactory(KeyStore paramKeyStore)
      throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
      super();
      HttpManager.MySSLSocketFactory.1 local1 = new HttpManager.MySSLSocketFactory.1(this);
      SSLContext localSSLContext = this.sslContext;
      TrustManager[] arrayOfTrustManager = new TrustManager[1];
      arrayOfTrustManager[0] = local1;
      localSSLContext.init(null, arrayOfTrustManager, null);
    }

    public Socket createSocket()
      throws IOException
    {
      return this.sslContext.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
      throws IOException, UnknownHostException
    {
      return this.sslContext.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.net.HttpManager
 * JD-Core Version:    0.6.0
 */