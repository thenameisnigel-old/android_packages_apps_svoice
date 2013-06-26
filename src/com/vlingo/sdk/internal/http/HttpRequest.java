package com.vlingo.sdk.internal.http;

import com.vlingo.sdk.internal.recognizer.ClientMeta;
import com.vlingo.sdk.internal.recognizer.SoftwareMeta;
import java.util.Hashtable;

public class HttpRequest
{
  protected static final int DEFAULT_RETRY_COUNT;
  protected HttpCallback callback;
  protected volatile ClientMeta client = ClientMeta.getInstance();
  private volatile Object cookie;
  protected volatile Hashtable<String, String> cookies = null;
  private volatile int countRetries = 0;
  protected byte[] data;
  protected volatile Hashtable<String, String> extraHeaders = null;
  private volatile boolean flagForRetry = false;
  protected volatile boolean gzipPostData = false;
  private volatile boolean m_Canceled = false;
  protected volatile int maxRetry = 0;
  protected volatile String method = "POST";
  protected volatile SoftwareMeta software = SoftwareMeta.getInstance();
  private volatile long startTime;
  protected String taskName;
  protected volatile int timeout = 0;
  protected String url;

  protected HttpRequest(String paramString1, HttpCallback paramHttpCallback, String paramString2, byte[] paramArrayOfByte)
  {
    this.taskName = paramString1;
    this.callback = paramHttpCallback;
    this.url = paramString2;
    this.data = paramArrayOfByte;
  }

  public static HttpRequest createRequest(String paramString1, HttpCallback paramHttpCallback, String paramString2)
  {
    monitorenter;
    try
    {
      HttpRequest localHttpRequest = createRequest(paramString1, paramHttpCallback, paramString2, null);
      monitorexit;
      return localHttpRequest;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static HttpRequest createRequest(String paramString1, HttpCallback paramHttpCallback, String paramString2, byte[] paramArrayOfByte)
  {
    monitorenter;
    try
    {
      HttpRequest localHttpRequest = new HttpRequest(paramString1, paramHttpCallback, paramString2, paramArrayOfByte);
      monitorexit;
      return localHttpRequest;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void cancel()
  {
    this.m_Canceled = true;
    HttpManager.getInstance().cancelRequest(this);
  }

  // ERROR //
  protected HttpResponse fetchResponse()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield 40	com/vlingo/sdk/internal/http/HttpRequest:flagForRetry	Z
    //   5: aload_0
    //   6: invokestatic 110	java/lang/System:currentTimeMillis	()J
    //   9: putfield 112	com/vlingo/sdk/internal/http/HttpRequest:startTime	J
    //   12: aconst_null
    //   13: astore_1
    //   14: aconst_null
    //   15: astore_2
    //   16: aconst_null
    //   17: astore_3
    //   18: aconst_null
    //   19: astore 4
    //   21: aconst_null
    //   22: astore 5
    //   24: bipush 255
    //   26: istore 6
    //   28: aload_0
    //   29: getfield 44	com/vlingo/sdk/internal/http/HttpRequest:m_Canceled	Z
    //   32: ifeq +55 -> 87
    //   35: new 114	java/io/IOException
    //   38: dup
    //   39: ldc 116
    //   41: invokespecial 119	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   44: athrow
    //   45: astore 11
    //   47: aload_2
    //   48: ifnull +7 -> 55
    //   51: aload_2
    //   52: invokevirtual 124	java/io/InputStream:close	()V
    //   55: aload_3
    //   56: ifnull +7 -> 63
    //   59: aload_3
    //   60: invokevirtual 127	java/io/DataOutputStream:close	()V
    //   63: aload_1
    //   64: ifnull +9 -> 73
    //   67: aload_1
    //   68: invokeinterface 130 1 0
    //   73: new 132	com/vlingo/sdk/internal/http/HttpResponse
    //   76: dup
    //   77: iload 6
    //   79: aload 4
    //   81: aload 5
    //   83: invokespecial 135	com/vlingo/sdk/internal/http/HttpResponse:<init>	(I[BLcom/vlingo/sdk/internal/http/cookies/CookieJar;)V
    //   86: areturn
    //   87: aload_0
    //   88: getfield 48	com/vlingo/sdk/internal/http/HttpRequest:method	Ljava/lang/String;
    //   91: aload_0
    //   92: getfield 79	com/vlingo/sdk/internal/http/HttpRequest:url	Ljava/lang/String;
    //   95: iconst_0
    //   96: aload_0
    //   97: getfield 50	com/vlingo/sdk/internal/http/HttpRequest:cookies	Ljava/util/Hashtable;
    //   100: aload_0
    //   101: getfield 52	com/vlingo/sdk/internal/http/HttpRequest:extraHeaders	Ljava/util/Hashtable;
    //   104: aload_0
    //   105: getfield 60	com/vlingo/sdk/internal/http/HttpRequest:client	Lcom/vlingo/sdk/internal/recognizer/ClientMeta;
    //   108: aload_0
    //   109: getfield 67	com/vlingo/sdk/internal/http/HttpRequest:software	Lcom/vlingo/sdk/internal/recognizer/SoftwareMeta;
    //   112: invokestatic 141	com/vlingo/sdk/internal/http/HttpUtil:newHttpConnection	(Ljava/lang/String;Ljava/lang/String;ZLjava/util/Hashtable;Ljava/util/Hashtable;Lcom/vlingo/sdk/internal/recognizer/ClientMeta;Lcom/vlingo/sdk/internal/recognizer/SoftwareMeta;)Lcom/vlingo/sdk/internal/net/HttpConnection;
    //   115: astore_1
    //   116: aload_0
    //   117: getfield 44	com/vlingo/sdk/internal/http/HttpRequest:m_Canceled	Z
    //   120: ifeq +44 -> 164
    //   123: new 114	java/io/IOException
    //   126: dup
    //   127: ldc 116
    //   129: invokespecial 119	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   132: athrow
    //   133: astore 7
    //   135: aload_2
    //   136: ifnull +7 -> 143
    //   139: aload_2
    //   140: invokevirtual 124	java/io/InputStream:close	()V
    //   143: aload_3
    //   144: ifnull +7 -> 151
    //   147: aload_3
    //   148: invokevirtual 127	java/io/DataOutputStream:close	()V
    //   151: aload_1
    //   152: ifnull +9 -> 161
    //   155: aload_1
    //   156: invokeinterface 130 1 0
    //   161: aload 7
    //   163: athrow
    //   164: aload_0
    //   165: getfield 48	com/vlingo/sdk/internal/http/HttpRequest:method	Ljava/lang/String;
    //   168: ldc 46
    //   170: invokevirtual 147	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   173: ifeq +102 -> 275
    //   176: aload_0
    //   177: getfield 81	com/vlingo/sdk/internal/http/HttpRequest:data	[B
    //   180: ifnull +95 -> 275
    //   183: aload_0
    //   184: getfield 73	com/vlingo/sdk/internal/http/HttpRequest:gzipPostData	Z
    //   187: ifeq +33 -> 220
    //   190: aload_0
    //   191: getfield 81	com/vlingo/sdk/internal/http/HttpRequest:data	[B
    //   194: invokestatic 153	com/vlingo/sdk/internal/util/CompressUtils:deflate	([B)[B
    //   197: astore 23
    //   199: aload 23
    //   201: ifnull +19 -> 220
    //   204: aload_1
    //   205: ldc 155
    //   207: ldc 156
    //   209: invokeinterface 160 3 0
    //   214: aload_0
    //   215: aload 23
    //   217: putfield 81	com/vlingo/sdk/internal/http/HttpRequest:data	[B
    //   220: aload_1
    //   221: ldc 162
    //   223: aload_0
    //   224: getfield 81	com/vlingo/sdk/internal/http/HttpRequest:data	[B
    //   227: arraylength
    //   228: invokestatic 166	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   231: invokeinterface 160 3 0
    //   236: aload_1
    //   237: aconst_null
    //   238: invokeinterface 170 2 0
    //   243: aload_1
    //   244: invokeinterface 174 1 0
    //   249: astore_3
    //   250: aload_0
    //   251: getfield 44	com/vlingo/sdk/internal/http/HttpRequest:m_Canceled	Z
    //   254: ifeq +13 -> 267
    //   257: new 114	java/io/IOException
    //   260: dup
    //   261: ldc 116
    //   263: invokespecial 119	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   266: athrow
    //   267: aload_3
    //   268: aload_0
    //   269: getfield 81	com/vlingo/sdk/internal/http/HttpRequest:data	[B
    //   272: invokevirtual 178	java/io/DataOutputStream:write	([B)V
    //   275: aload_1
    //   276: invokeinterface 182 1 0
    //   281: istore 6
    //   283: aload_1
    //   284: invokeinterface 185 1 0
    //   289: istore 15
    //   291: aload_0
    //   292: getfield 44	com/vlingo/sdk/internal/http/HttpRequest:m_Canceled	Z
    //   295: ifeq +13 -> 308
    //   298: new 114	java/io/IOException
    //   301: dup
    //   302: ldc 116
    //   304: invokespecial 119	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   307: athrow
    //   308: aload_1
    //   309: aconst_null
    //   310: invokeinterface 189 2 0
    //   315: aload_1
    //   316: invokeinterface 193 1 0
    //   321: astore_2
    //   322: aload_2
    //   323: ifnonnull +13 -> 336
    //   326: new 114	java/io/IOException
    //   329: dup
    //   330: ldc 195
    //   332: invokespecial 119	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   335: athrow
    //   336: iload 15
    //   338: bipush 255
    //   340: if_icmpeq +65 -> 405
    //   343: iload 15
    //   345: newarray byte
    //   347: astore 4
    //   349: iconst_0
    //   350: istore 20
    //   352: iload 15
    //   354: iload 20
    //   356: isub
    //   357: istore 21
    //   359: aload_2
    //   360: aload 4
    //   362: iload 20
    //   364: iload 21
    //   366: invokevirtual 199	java/io/InputStream:read	([BII)I
    //   369: istore 22
    //   371: aload_0
    //   372: getfield 44	com/vlingo/sdk/internal/http/HttpRequest:m_Canceled	Z
    //   375: ifeq +123 -> 498
    //   378: new 114	java/io/IOException
    //   381: dup
    //   382: ldc 116
    //   384: invokespecial 119	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   387: athrow
    //   388: aload_0
    //   389: getfield 44	com/vlingo/sdk/internal/http/HttpRequest:m_Canceled	Z
    //   392: ifeq +22 -> 414
    //   395: new 114	java/io/IOException
    //   398: dup
    //   399: ldc 116
    //   401: invokespecial 119	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   404: athrow
    //   405: aload_2
    //   406: invokestatic 203	com/vlingo/sdk/internal/http/HttpUtil:readData	(Ljava/io/InputStream;)[B
    //   409: astore 4
    //   411: goto -23 -> 388
    //   414: aload_1
    //   415: invokestatic 209	com/vlingo/sdk/internal/http/cookies/CookieHandler:extractCookies	(Lcom/vlingo/sdk/internal/net/HttpConnection;)Lcom/vlingo/sdk/internal/http/cookies/CookieJar;
    //   418: astore 16
    //   420: aload 16
    //   422: astore 5
    //   424: aload_2
    //   425: ifnull +7 -> 432
    //   428: aload_2
    //   429: invokevirtual 124	java/io/InputStream:close	()V
    //   432: aload_3
    //   433: ifnull +7 -> 440
    //   436: aload_3
    //   437: invokevirtual 127	java/io/DataOutputStream:close	()V
    //   440: aload_1
    //   441: ifnull -368 -> 73
    //   444: aload_1
    //   445: invokeinterface 130 1 0
    //   450: goto -377 -> 73
    //   453: astore 17
    //   455: goto -382 -> 73
    //   458: astore 19
    //   460: goto -28 -> 432
    //   463: astore 18
    //   465: goto -25 -> 440
    //   468: astore 14
    //   470: goto -415 -> 55
    //   473: astore 13
    //   475: goto -412 -> 63
    //   478: astore 12
    //   480: goto -407 -> 73
    //   483: astore 10
    //   485: goto -342 -> 143
    //   488: astore 9
    //   490: goto -339 -> 151
    //   493: astore 8
    //   495: goto -334 -> 161
    //   498: iload 22
    //   500: bipush 255
    //   502: if_icmpne +6 -> 508
    //   505: goto -117 -> 388
    //   508: iload 20
    //   510: iload 22
    //   512: iadd
    //   513: istore 20
    //   515: iload 15
    //   517: iload 20
    //   519: if_icmpgt -167 -> 352
    //   522: goto -134 -> 388
    //
    // Exception table:
    //   from	to	target	type
    //   28	45	45	java/lang/Exception
    //   87	133	45	java/lang/Exception
    //   164	420	45	java/lang/Exception
    //   28	45	133	finally
    //   87	133	133	finally
    //   164	420	133	finally
    //   444	450	453	java/lang/Throwable
    //   428	432	458	java/lang/Throwable
    //   436	440	463	java/lang/Throwable
    //   51	55	468	java/lang/Throwable
    //   59	63	473	java/lang/Throwable
    //   67	73	478	java/lang/Throwable
    //   139	143	483	java/lang/Throwable
    //   147	151	488	java/lang/Throwable
    //   155	161	493	java/lang/Throwable
  }

  HttpCallback getCallback()
  {
    return this.callback;
  }

  public Object getCookie()
  {
    return this.cookie;
  }

  public long getElapsedTime()
  {
    return System.currentTimeMillis() - this.startTime;
  }

  public int getMaxRetry()
  {
    return this.maxRetry;
  }

  public String getTaskName()
  {
    return this.taskName;
  }

  int getTimeout()
  {
    return this.timeout;
  }

  public boolean isGzipPostDataEnabled()
  {
    return this.gzipPostData;
  }

  boolean isRetry()
  {
    return this.flagForRetry;
  }

  public void markRetry()
  {
    int i = this.countRetries;
    this.countRetries = (i + 1);
    if (i < this.maxRetry)
      this.flagForRetry = true;
    while (true)
    {
      return;
      this.flagForRetry = false;
      notifyFailure();
    }
  }

  protected void notifyCancelled()
  {
    this.callback.onCancelled(this);
  }

  protected void notifyFailure()
  {
    this.callback.onFailure(this);
  }

  protected void notifyResponse(HttpResponse paramHttpResponse)
  {
    this.callback.onResponse(this, paramHttpResponse);
  }

  protected boolean notifyTimeout()
  {
    return this.callback.onTimeout(this);
  }

  protected void notifyWillExecute()
  {
    this.callback.onWillExecuteRequest(this);
  }

  public boolean reachedMaxRetryCount()
  {
    if (this.countRetries >= this.maxRetry);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void schedule()
  {
    HttpManager.getInstance().doBackgroundRequest(this, false, false);
  }

  public void schedule(long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    HttpManager.getInstance().doBackgroundRequestLater(this, paramLong, paramBoolean1, paramBoolean2);
  }

  public void setClientMeta(ClientMeta paramClientMeta)
  {
    this.client = paramClientMeta;
  }

  public void setCookie(Object paramObject)
  {
    this.cookie = paramObject;
  }

  public void setCookies(Hashtable<String, String> paramHashtable)
  {
    this.cookies = paramHashtable;
  }

  public void setExtraHeaders(Hashtable<String, String> paramHashtable)
  {
    this.extraHeaders = paramHashtable;
  }

  public void setGzipPostData(boolean paramBoolean)
  {
    this.gzipPostData = paramBoolean;
  }

  public void setMaxRetry(int paramInt)
  {
    this.maxRetry = paramInt;
  }

  public void setMethod(String paramString)
  {
    this.method = paramString;
  }

  public void setSoftwareMeta(SoftwareMeta paramSoftwareMeta)
  {
    this.software = paramSoftwareMeta;
  }

  public void setTimeout(int paramInt)
  {
    this.timeout = paramInt;
  }

  public void start()
  {
    HttpManager.getInstance().doRequestNow(this);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.HttpRequest
 * JD-Core Version:    0.6.0
 */