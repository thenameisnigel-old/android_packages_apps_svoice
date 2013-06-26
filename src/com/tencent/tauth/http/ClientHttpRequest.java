package com.tencent.tauth.http;

import android.os.Bundle;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;

public class ClientHttpRequest
{
  private static final String BOUNDRY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
  private static final String ENDLINE = "\r\n";
  private static final int SET_CONNECTION_TIMEOUT = 5000;
  private static final int SET_SOCKET_TIMEOUT = 10000;
  private static final String TAG = "HttpRequest";

  public static String encodePostBody(Bundle paramBundle, String paramString)
  {
    String str2;
    if (paramBundle == null)
    {
      str2 = "";
      return str2;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        str2 = localStringBuilder.toString();
        break;
      }
      String str1 = (String)localIterator.next();
      if (paramBundle.getByteArray(str1) != null)
        continue;
      localStringBuilder.append("Content-Disposition: form-data; name=\"" + str1 + "\"\r\n\r\n" + paramBundle.getString(str1));
      localStringBuilder.append("\r\n--" + paramString + "\r\n");
    }
  }

  public static String encodeUrl(Bundle paramBundle)
  {
    if (paramBundle == null);
    StringBuilder localStringBuilder;
    int i;
    Iterator localIterator;
    for (String str2 = ""; ; str2 = localStringBuilder.toString())
    {
      return str2;
      localStringBuilder = new StringBuilder();
      i = 1;
      localIterator = paramBundle.keySet().iterator();
      if (localIterator.hasNext())
        break;
    }
    String str1 = (String)localIterator.next();
    if (i != 0)
      i = 0;
    while (true)
    {
      localStringBuilder.append(URLEncoder.encode(str1) + "=" + URLEncoder.encode(paramBundle.getString(str1)));
      break;
      localStringBuilder.append("&");
    }
  }

  public static int getFileSizeAtURL(URL paramURL)
  {
    int i = -1;
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramURL.openConnection();
      i = localHttpURLConnection.getContentLength();
      localHttpURLConnection.disconnect();
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        TDebug.i("HttpRequest", localException.toString());
    }
  }

  // ERROR //
  private static HttpClient getHttpClient()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: invokestatic 146	java/security/KeyStore:getDefaultType	()Ljava/lang/String;
    //   5: invokestatic 150	java/security/KeyStore:getInstance	(Ljava/lang/String;)Ljava/security/KeyStore;
    //   8: astore 16
    //   10: aload 16
    //   12: astore_0
    //   13: aload_0
    //   14: aconst_null
    //   15: aconst_null
    //   16: invokevirtual 154	java/security/KeyStore:load	(Ljava/io/InputStream;[C)V
    //   19: aconst_null
    //   20: astore_3
    //   21: new 10	com/tencent/tauth/http/ClientHttpRequest$TSSLSocketFactory
    //   24: dup
    //   25: aload_0
    //   26: invokespecial 157	com/tencent/tauth/http/ClientHttpRequest$TSSLSocketFactory:<init>	(Ljava/security/KeyStore;)V
    //   29: astore 4
    //   31: aload 4
    //   33: astore_3
    //   34: aload_3
    //   35: getstatic 163	org/apache/http/conn/ssl/SSLSocketFactory:ALLOW_ALL_HOSTNAME_VERIFIER	Lorg/apache/http/conn/ssl/X509HostnameVerifier;
    //   38: invokevirtual 167	org/apache/http/conn/ssl/SSLSocketFactory:setHostnameVerifier	(Lorg/apache/http/conn/ssl/X509HostnameVerifier;)V
    //   41: new 169	org/apache/http/params/BasicHttpParams
    //   44: dup
    //   45: invokespecial 170	org/apache/http/params/BasicHttpParams:<init>	()V
    //   48: astore 5
    //   50: aload 5
    //   52: sipush 10000
    //   55: invokestatic 176	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   58: aload 5
    //   60: sipush 10000
    //   63: invokestatic 179	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   66: aload 5
    //   68: getstatic 185	org/apache/http/HttpVersion:HTTP_1_1	Lorg/apache/http/HttpVersion;
    //   71: invokestatic 191	org/apache/http/params/HttpProtocolParams:setVersion	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/ProtocolVersion;)V
    //   74: aload 5
    //   76: ldc 193
    //   78: invokestatic 197	org/apache/http/params/HttpProtocolParams:setContentCharset	(Lorg/apache/http/params/HttpParams;Ljava/lang/String;)V
    //   81: new 199	org/apache/http/conn/scheme/SchemeRegistry
    //   84: dup
    //   85: invokespecial 200	org/apache/http/conn/scheme/SchemeRegistry:<init>	()V
    //   88: astore 6
    //   90: aload 6
    //   92: new 202	org/apache/http/conn/scheme/Scheme
    //   95: dup
    //   96: ldc 204
    //   98: invokestatic 210	org/apache/http/conn/scheme/PlainSocketFactory:getSocketFactory	()Lorg/apache/http/conn/scheme/PlainSocketFactory;
    //   101: bipush 80
    //   103: invokespecial 213	org/apache/http/conn/scheme/Scheme:<init>	(Ljava/lang/String;Lorg/apache/http/conn/scheme/SocketFactory;I)V
    //   106: invokevirtual 217	org/apache/http/conn/scheme/SchemeRegistry:register	(Lorg/apache/http/conn/scheme/Scheme;)Lorg/apache/http/conn/scheme/Scheme;
    //   109: pop
    //   110: aload 6
    //   112: new 202	org/apache/http/conn/scheme/Scheme
    //   115: dup
    //   116: ldc 219
    //   118: aload_3
    //   119: sipush 443
    //   122: invokespecial 213	org/apache/http/conn/scheme/Scheme:<init>	(Ljava/lang/String;Lorg/apache/http/conn/scheme/SocketFactory;I)V
    //   125: invokevirtual 217	org/apache/http/conn/scheme/SchemeRegistry:register	(Lorg/apache/http/conn/scheme/Scheme;)Lorg/apache/http/conn/scheme/Scheme;
    //   128: pop
    //   129: new 221	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager
    //   132: dup
    //   133: aload 5
    //   135: aload 6
    //   137: invokespecial 224	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:<init>	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/conn/scheme/SchemeRegistry;)V
    //   140: astore 9
    //   142: aload 5
    //   144: sipush 5000
    //   147: invokestatic 176	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   150: aload 5
    //   152: sipush 10000
    //   155: invokestatic 179	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   158: new 226	org/apache/http/impl/client/DefaultHttpClient
    //   161: dup
    //   162: aload 9
    //   164: aload 5
    //   166: invokespecial 229	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/conn/ClientConnectionManager;Lorg/apache/http/params/HttpParams;)V
    //   169: areturn
    //   170: astore_1
    //   171: aload_1
    //   172: invokevirtual 232	java/security/KeyStoreException:printStackTrace	()V
    //   175: goto -162 -> 13
    //   178: astore 15
    //   180: aload 15
    //   182: invokevirtual 233	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   185: goto -166 -> 19
    //   188: astore 14
    //   190: aload 14
    //   192: invokevirtual 234	java/security/cert/CertificateException:printStackTrace	()V
    //   195: goto -176 -> 19
    //   198: astore_2
    //   199: aload_2
    //   200: invokevirtual 235	java/io/IOException:printStackTrace	()V
    //   203: goto -184 -> 19
    //   206: astore 13
    //   208: aload 13
    //   210: invokevirtual 236	java/security/KeyManagementException:printStackTrace	()V
    //   213: goto -179 -> 34
    //   216: astore 12
    //   218: aload 12
    //   220: invokevirtual 233	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   223: goto -189 -> 34
    //   226: astore 11
    //   228: aload 11
    //   230: invokevirtual 232	java/security/KeyStoreException:printStackTrace	()V
    //   233: goto -199 -> 34
    //   236: astore 10
    //   238: aload 10
    //   240: invokevirtual 237	java/security/UnrecoverableKeyException:printStackTrace	()V
    //   243: goto -209 -> 34
    //
    // Exception table:
    //   from	to	target	type
    //   2	10	170	java/security/KeyStoreException
    //   13	19	178	java/security/NoSuchAlgorithmException
    //   13	19	188	java/security/cert/CertificateException
    //   13	19	198	java/io/IOException
    //   21	31	206	java/security/KeyManagementException
    //   21	31	216	java/security/NoSuchAlgorithmException
    //   21	31	226	java/security/KeyStoreException
    //   21	31	236	java/security/UnrecoverableKeyException
  }

  public static String openUrl(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (paramString2.equals("GET"))
      paramString1 = paramString1 + encodeUrl(paramBundle);
    TDebug.i("HttpRequest", paramString2 + " URL: " + paramString1);
    Object localObject;
    if (paramString1.startsWith("https"))
    {
      localObject = (HttpsURLConnection)new URL(paramString1).openConnection();
      ((HttpsURLConnection)localObject).setHostnameVerifier(new HostnameVerifier()
      {
        public boolean verify(String paramString, SSLSession paramSSLSession)
        {
          return true;
        }
      });
    }
    try
    {
      2 local2 = new X509TrustManager()
      {
        public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
          throws CertificateException
        {
        }

        public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
          throws CertificateException
        {
        }

        public X509Certificate[] getAcceptedIssuers()
        {
          return null;
        }
      };
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      TrustManager[] arrayOfTrustManager = new TrustManager[1];
      arrayOfTrustManager[0] = local2;
      localSSLContext.init(null, arrayOfTrustManager, null);
      ((HttpsURLConnection)localObject).setSSLSocketFactory(localSSLContext.getSocketFactory());
      label147: TDebug.i("HttpRequest", "start https");
      while (true)
      {
        ((HttpURLConnection)localObject).setRequestProperty("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK");
        Bundle localBundle;
        Iterator localIterator1;
        label219: BufferedOutputStream localBufferedOutputStream;
        Iterator localIterator2;
        if (!paramString2.equals("GET"))
        {
          localBundle = new Bundle();
          localIterator1 = paramBundle.keySet().iterator();
          if (localIterator1.hasNext())
            break label469;
          if (!paramBundle.containsKey("method"))
            paramBundle.putString("method", paramString2);
          ((HttpURLConnection)localObject).setRequestMethod("POST");
          ((HttpURLConnection)localObject).setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
          ((HttpURLConnection)localObject).setDoOutput(true);
          ((HttpURLConnection)localObject).setDoInput(true);
          ((HttpURLConnection)localObject).setRequestProperty("Connection", "Keep-Alive");
          ((HttpURLConnection)localObject).connect();
          localBufferedOutputStream = new BufferedOutputStream(((HttpURLConnection)localObject).getOutputStream());
          localBufferedOutputStream.write(("--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
          localBufferedOutputStream.write(encodePostBody(paramBundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
          localBufferedOutputStream.write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
          if (!localBundle.isEmpty())
          {
            localIterator2 = localBundle.keySet().iterator();
            if (localIterator2.hasNext())
              break label506;
          }
          localBufferedOutputStream.flush();
        }
        try
        {
          String str3 = read(((HttpURLConnection)localObject).getInputStream());
          str2 = str3;
          return str2;
          localObject = (HttpURLConnection)new URL(paramString1).openConnection();
          continue;
          label469: String str1 = (String)localIterator1.next();
          if (paramBundle.getByteArray(str1) == null)
            break label219;
          localBundle.putByteArray(str1, paramBundle.getByteArray(str1));
          break label219;
          label506: String str4 = (String)localIterator2.next();
          localBufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" + str4 + "\"" + "\r\n").getBytes());
          localBufferedOutputStream.write(("Content-Type: content/unknown" + "\r\n" + "\r\n").getBytes());
          localBufferedOutputStream.write(localBundle.getByteArray(str4));
          localBufferedOutputStream.write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          while (true)
            String str2 = read(((HttpURLConnection)localObject).getErrorStream());
        }
      }
    }
    catch (Exception localException)
    {
      break label147;
    }
  }

  public static String openUrl(String paramString1, String paramString2, Bundle paramBundle, int paramInt)
    throws MalformedURLException, IOException
  {
    HttpClient localHttpClient = getHttpClient();
    Object localObject1 = null;
    if (paramString2.equals("GET"))
      localObject1 = new HttpGet(paramString1 + encodeUrl(paramBundle));
    int i;
    while (true)
    {
      HttpResponse localHttpResponse = localHttpClient.execute((HttpUriRequest)localObject1);
      i = localHttpResponse.getStatusLine().getStatusCode();
      if (i != 200)
        break;
      return readHttpResponse(localHttpResponse);
      if (!paramString2.equals("POST"))
        continue;
      HttpPost localHttpPost = new HttpPost(paramString1);
      Bundle localBundle = new Bundle();
      Iterator localIterator1 = paramBundle.keySet().iterator();
      label126: ByteArrayOutputStream localByteArrayOutputStream;
      Iterator localIterator2;
      if (!localIterator1.hasNext())
      {
        if (paramBundle.containsKey("access_token"))
          paramBundle.putString("access_token", URLDecoder.decode(paramBundle.getString("access_token")));
        localHttpPost.setHeader("Content-Type", "multipart/form-data;boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
        localHttpPost.setHeader("Connection", "Keep-Alive");
        localByteArrayOutputStream = new ByteArrayOutputStream();
        localByteArrayOutputStream.write("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n".getBytes());
        localByteArrayOutputStream.write(encodePostBody(paramBundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
        localByteArrayOutputStream.write("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n".getBytes());
        if (!localBundle.isEmpty())
          localIterator2 = localBundle.keySet().iterator();
      }
      while (true)
      {
        if (!localIterator2.hasNext())
        {
          byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
          localByteArrayOutputStream.close();
          localHttpPost.setEntity(new ByteArrayEntity(arrayOfByte));
          localObject1 = localHttpPost;
          break;
          String str1 = (String)localIterator1.next();
          Object localObject2 = paramBundle.get(str1);
          if (!(localObject2 instanceof byte[]))
            break label126;
          localBundle.putByteArray(str1, (byte[])localObject2);
          break label126;
        }
        String str2 = (String)localIterator2.next();
        localByteArrayOutputStream.write(("Content-Disposition: form-data; filename=\"" + str2 + "\"" + "\r\n").getBytes());
        localByteArrayOutputStream.write("Content-Type: content/unknown\r\n\r\n".getBytes());
        localByteArrayOutputStream.write(localBundle.getByteArray(str2));
        localByteArrayOutputStream.write("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n".getBytes());
      }
    }
    throw new IOException("Http statusCode:" + i);
  }

  private static String read(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 1000);
    for (String str = localBufferedReader.readLine(); ; str = localBufferedReader.readLine())
    {
      if (str == null)
      {
        paramInputStream.close();
        return localStringBuilder.toString();
      }
      localStringBuilder.append(str);
    }
  }

  private static String readHttpResponse(HttpResponse paramHttpResponse)
    throws IllegalStateException, IOException
  {
    Object localObject = paramHttpResponse.getEntity().getContent();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Header localHeader = paramHttpResponse.getFirstHeader("Content-Encoding");
    if ((localHeader != null) && (localHeader.getValue().toLowerCase().indexOf("gzip") > -1))
      localObject = new GZIPInputStream((InputStream)localObject);
    byte[] arrayOfByte = new byte[512];
    while (true)
    {
      int i = ((InputStream)localObject).read(arrayOfByte);
      if (i == -1)
        return new String(localByteArrayOutputStream.toByteArray());
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }

  private static class TSSLSocketFactory extends org.apache.http.conn.ssl.SSLSocketFactory
  {
    SSLContext sslContext = SSLContext.getInstance("TLS");

    public TSSLSocketFactory(KeyStore paramKeyStore)
      throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
      super();
      ClientHttpRequest.TSSLSocketFactory.1 local1 = new ClientHttpRequest.TSSLSocketFactory.1(this);
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
 * Qualified Name:     com.tencent.tauth.http.ClientHttpRequest
 * JD-Core Version:    0.6.0
 */