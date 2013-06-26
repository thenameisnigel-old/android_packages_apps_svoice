package com.vlingo.sdk.internal.http.custom;

import com.vlingo.sdk.internal.net.ConnectionManager;
import com.vlingo.sdk.internal.net.ConnectionResult;
import com.vlingo.sdk.internal.net.HttpConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

public class AndroidVStreamConnection
  implements HttpConnection, VStreamConnection
{
  private HttpURLConnection m_Connection = null;
  private DataInputStream m_DataInputStream = null;
  private DataOutputStream m_DataOutputStream = null;
  private Boolean m_InputStatus = null;
  private InputStream m_InputStream = null;
  private Boolean m_OutputStatus = null;
  private OutputStream m_OutputStream = null;
  private java.net.URL m_URL = null;

  public AndroidVStreamConnection(com.vlingo.sdk.internal.http.URL paramURL)
    throws IOException
  {
    this(new java.net.URL(paramURL.getProtocol(), paramURL.host, paramURL.port, paramURL.path));
  }

  public AndroidVStreamConnection(String paramString)
    throws IOException
  {
    this(new java.net.URL(paramString));
  }

  public AndroidVStreamConnection(java.net.URL paramURL)
    throws IOException
  {
    if (paramURL == null)
      throw new IOException("url is null");
    try
    {
      this.m_URL = paramURL;
      this.m_Connection = ((HttpURLConnection)this.m_URL.openConnection());
      this.m_Connection.setDoInput(true);
      this.m_Connection.setDoOutput(true);
      if (ConnectionManager.isSlowNetwork())
        this.m_Connection.setReadTimeout(40000);
      else
        this.m_Connection.setReadTimeout(20000);
    }
    catch (Exception localException)
    {
    }
  }

  public void close()
    throws IOException
  {
    synchronized (this.m_URL)
    {
      DataInputStream localDataInputStream = this.m_DataInputStream;
      if (localDataInputStream == null);
    }
    try
    {
      this.m_DataInputStream.close();
      this.m_DataInputStream = null;
      label28: InputStream localInputStream = this.m_InputStream;
      if (localInputStream != null);
      try
      {
        this.m_InputStream.close();
        this.m_InputStream = null;
        label51: DataOutputStream localDataOutputStream = this.m_DataOutputStream;
        if (localDataOutputStream != null);
        try
        {
          this.m_DataOutputStream.close();
          this.m_DataOutputStream = null;
          label74: OutputStream localOutputStream = this.m_OutputStream;
          if (localOutputStream != null);
          try
          {
            this.m_OutputStream.close();
            this.m_OutputStream = null;
            label97: if (isOpen())
            {
              this.m_Connection.disconnect();
              this.m_Connection = null;
            }
            this.m_InputStatus = Boolean.valueOf(true);
            this.m_OutputStatus = Boolean.valueOf(true);
            this.m_URL.notifyAll();
            monitorexit;
            return;
            localObject = finally;
            monitorexit;
            throw localObject;
          }
          catch (IOException localIOException1)
          {
            break label97;
          }
        }
        catch (IOException localIOException2)
        {
          break label74;
        }
      }
      catch (IOException localIOException3)
      {
        break label51;
      }
    }
    catch (IOException localIOException4)
    {
      break label28;
    }
  }

  public ConnectionResult getConnectionDetails()
  {
    ConnectionResult localConnectionResult = new ConnectionResult();
    localConnectionResult.connectionType = 0;
    localConnectionResult.connection = this;
    return localConnectionResult;
  }

  public String getFile()
  {
    return this.m_URL.getPath();
  }

  public String getHeaderField(int paramInt)
  {
    return this.m_Connection.getHeaderField(paramInt);
  }

  public String getHeaderFieldKey(int paramInt)
    throws IOException
  {
    return this.m_Connection.getHeaderFieldKey(paramInt);
  }

  public String getHost()
  {
    return this.m_URL.getHost();
  }

  // ERROR //
  public DataInputStream getInputStream()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 120	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:isOpen	()Z
    //   4: ifne +13 -> 17
    //   7: new 27	java/io/IOException
    //   10: dup
    //   11: ldc 162
    //   13: invokespecial 79	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   21: astore_1
    //   22: aload_1
    //   23: monitorenter
    //   24: aload_0
    //   25: getfield 70	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_DataInputStream	Ljava/io/DataInputStream;
    //   28: ifnonnull +45 -> 73
    //   31: aload_0
    //   32: invokevirtual 166	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:openInputStream	()Ljava/io/InputStream;
    //   35: pop
    //   36: aload_0
    //   37: getfield 66	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_InputStream	Ljava/io/InputStream;
    //   40: ifnonnull +18 -> 58
    //   43: new 27	java/io/IOException
    //   46: dup
    //   47: ldc 168
    //   49: invokespecial 79	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   52: athrow
    //   53: astore_2
    //   54: aload_1
    //   55: monitorexit
    //   56: aload_2
    //   57: athrow
    //   58: aload_0
    //   59: new 106	java/io/DataInputStream
    //   62: dup
    //   63: aload_0
    //   64: getfield 66	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_InputStream	Ljava/io/InputStream;
    //   67: invokespecial 171	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   70: putfield 70	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_DataInputStream	Ljava/io/DataInputStream;
    //   73: aload_1
    //   74: monitorexit
    //   75: aload_0
    //   76: getfield 70	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_DataInputStream	Ljava/io/DataInputStream;
    //   79: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   24	56	53	finally
    //   58	75	53	finally
  }

  public int getLength()
  {
    return this.m_Connection.getContentLength();
  }

  // ERROR //
  public DataOutputStream getOutputStream()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 120	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:isOpen	()Z
    //   4: ifne +13 -> 17
    //   7: new 27	java/io/IOException
    //   10: dup
    //   11: ldc 162
    //   13: invokespecial 79	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   21: astore_1
    //   22: aload_1
    //   23: monitorenter
    //   24: aload_0
    //   25: getfield 72	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_DataOutputStream	Ljava/io/DataOutputStream;
    //   28: ifnonnull +99 -> 127
    //   31: aload_0
    //   32: getfield 76	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_OutputStatus	Ljava/lang/Boolean;
    //   35: ifnonnull +18 -> 53
    //   38: new 27	java/io/IOException
    //   41: dup
    //   42: ldc 182
    //   44: invokespecial 79	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   47: athrow
    //   48: astore_2
    //   49: aload_1
    //   50: monitorexit
    //   51: aload_2
    //   52: athrow
    //   53: aload_0
    //   54: getfield 76	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_OutputStatus	Ljava/lang/Boolean;
    //   57: invokevirtual 185	java/lang/Boolean:booleanValue	()Z
    //   60: ifne +35 -> 95
    //   63: aload_0
    //   64: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   67: astore_3
    //   68: aload_3
    //   69: ifnull +26 -> 95
    //   72: aload_0
    //   73: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   76: aload_0
    //   77: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   80: invokevirtual 188	java/net/HttpURLConnection:getReadTimeout	()I
    //   83: i2l
    //   84: invokevirtual 192	java/lang/Object:wait	(J)V
    //   87: goto -34 -> 53
    //   90: astore 4
    //   92: goto -39 -> 53
    //   95: aload_0
    //   96: getfield 68	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_OutputStream	Ljava/io/OutputStream;
    //   99: ifnonnull +13 -> 112
    //   102: new 27	java/io/IOException
    //   105: dup
    //   106: ldc 194
    //   108: invokespecial 79	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   111: athrow
    //   112: aload_0
    //   113: new 113	java/io/DataOutputStream
    //   116: dup
    //   117: aload_0
    //   118: getfield 68	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_OutputStream	Ljava/io/OutputStream;
    //   121: invokespecial 197	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   124: putfield 72	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_DataOutputStream	Ljava/io/DataOutputStream;
    //   127: aload_1
    //   128: monitorexit
    //   129: aload_0
    //   130: getfield 72	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_DataOutputStream	Ljava/io/DataOutputStream;
    //   133: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   24	51	48	finally
    //   53	68	48	finally
    //   72	87	48	finally
    //   95	129	48	finally
    //   72	87	90	java/lang/InterruptedException
  }

  public int getResponseCode()
  {
    try
    {
      int j = this.m_Connection.getResponseCode();
      i = j;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = -1;
    }
    catch (IOException localIOException)
    {
      label15: break label15;
    }
  }

  public boolean isOpen()
  {
    if (this.m_Connection != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public DataInputStream openDataInputStream()
    throws IOException
  {
    return getInputStream();
  }

  public DataOutputStream openDataOutputStream()
    throws IOException
  {
    return getOutputStream();
  }

  // ERROR //
  public InputStream openInputStream()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 120	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:isOpen	()Z
    //   4: ifne +13 -> 17
    //   7: new 27	java/io/IOException
    //   10: dup
    //   11: ldc 162
    //   13: invokespecial 79	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   21: astore_1
    //   22: aload_1
    //   23: monitorenter
    //   24: aload_0
    //   25: getfield 66	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_InputStream	Ljava/io/InputStream;
    //   28: ifnonnull +67 -> 95
    //   31: aload_0
    //   32: getfield 74	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_InputStatus	Ljava/lang/Boolean;
    //   35: ifnonnull +18 -> 53
    //   38: new 27	java/io/IOException
    //   41: dup
    //   42: ldc 210
    //   44: invokespecial 79	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   47: athrow
    //   48: astore_2
    //   49: aload_1
    //   50: monitorexit
    //   51: aload_2
    //   52: athrow
    //   53: aload_0
    //   54: getfield 74	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_InputStatus	Ljava/lang/Boolean;
    //   57: invokevirtual 185	java/lang/Boolean:booleanValue	()Z
    //   60: ifne +35 -> 95
    //   63: aload_0
    //   64: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   67: astore_3
    //   68: aload_3
    //   69: ifnull +26 -> 95
    //   72: aload_0
    //   73: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   76: aload_0
    //   77: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   80: invokevirtual 188	java/net/HttpURLConnection:getReadTimeout	()I
    //   83: i2l
    //   84: invokevirtual 192	java/lang/Object:wait	(J)V
    //   87: goto -34 -> 53
    //   90: astore 4
    //   92: goto -39 -> 53
    //   95: aload_1
    //   96: monitorexit
    //   97: aload_0
    //   98: getfield 66	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_InputStream	Ljava/io/InputStream;
    //   101: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   24	51	48	finally
    //   53	68	48	finally
    //   72	87	48	finally
    //   95	97	48	finally
    //   72	87	90	java/lang/InterruptedException
  }

  public void setDoOutput(boolean paramBoolean)
  {
    this.m_Connection.setDoOutput(paramBoolean);
  }

  public void setRequestMethod(String paramString)
  {
    if (!isOpen());
    while (true)
    {
      return;
      try
      {
        this.m_Connection.setRequestMethod(paramString);
        this.m_Connection.setRequestProperty("Connection", "CLOSE");
      }
      catch (ProtocolException localProtocolException)
      {
      }
    }
  }

  public void setRequestProperty(String paramString1, String paramString2)
  {
    if (!isOpen());
    while (true)
    {
      return;
      this.m_Connection.setRequestProperty(paramString1, paramString2);
    }
  }

  // ERROR //
  public void startRequest(HttpRequest paramHttpRequest)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   4: astore 5
    //   6: aload 5
    //   8: monitorenter
    //   9: aload_1
    //   10: ifnull +115 -> 125
    //   13: aload_0
    //   14: invokevirtual 120	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:isOpen	()Z
    //   17: ifeq +108 -> 125
    //   20: aload_1
    //   21: invokevirtual 231	com/vlingo/sdk/internal/http/custom/HttpRequest:sendHeaders	()Ljava/util/Hashtable;
    //   24: astore 9
    //   26: aload 9
    //   28: invokevirtual 237	java/util/Hashtable:keys	()Ljava/util/Enumeration;
    //   31: astore 10
    //   33: aload 10
    //   35: invokeinterface 242 1 0
    //   40: ifeq +75 -> 115
    //   43: aload 10
    //   45: invokeinterface 246 1 0
    //   50: checkcast 248	java/lang/String
    //   53: astore 11
    //   55: aload 9
    //   57: aload 11
    //   59: invokevirtual 252	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   62: checkcast 248	java/lang/String
    //   65: astore 12
    //   67: aload_0
    //   68: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   71: aload 11
    //   73: aload 12
    //   75: invokevirtual 223	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   78: goto -45 -> 33
    //   81: astore 8
    //   83: aload 5
    //   85: monitorexit
    //   86: aload 8
    //   88: athrow
    //   89: astore_2
    //   90: aload_0
    //   91: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   94: astore_3
    //   95: aload_3
    //   96: monitorenter
    //   97: aload_0
    //   98: iconst_1
    //   99: invokestatic 129	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   102: putfield 76	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_OutputStatus	Ljava/lang/Boolean;
    //   105: aload_0
    //   106: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   109: invokevirtual 132	java/lang/Object:notifyAll	()V
    //   112: aload_3
    //   113: monitorexit
    //   114: return
    //   115: aload_0
    //   116: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   119: sipush 1024
    //   122: invokevirtual 255	java/net/HttpURLConnection:setChunkedStreamingMode	(I)V
    //   125: aload 5
    //   127: monitorexit
    //   128: aload_0
    //   129: iconst_0
    //   130: invokestatic 129	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   133: putfield 76	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_OutputStatus	Ljava/lang/Boolean;
    //   136: aload_0
    //   137: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   140: astore 6
    //   142: aload 6
    //   144: monitorenter
    //   145: aload_0
    //   146: invokevirtual 120	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:isOpen	()Z
    //   149: ifeq +14 -> 163
    //   152: aload_0
    //   153: aload_0
    //   154: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   157: invokevirtual 258	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   160: putfield 68	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_OutputStream	Ljava/io/OutputStream;
    //   163: aload 6
    //   165: monitorexit
    //   166: goto -76 -> 90
    //   169: astore 7
    //   171: aload 6
    //   173: monitorexit
    //   174: aload 7
    //   176: athrow
    //   177: astore 4
    //   179: aload_3
    //   180: monitorexit
    //   181: aload 4
    //   183: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   13	86	81	finally
    //   115	128	81	finally
    //   0	9	89	java/lang/Exception
    //   86	89	89	java/lang/Exception
    //   128	145	89	java/lang/Exception
    //   174	177	89	java/lang/Exception
    //   145	174	169	finally
    //   97	114	177	finally
    //   179	181	177	finally
  }

  // ERROR //
  public void startResponse(HttpResponse paramHttpResponse)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   4: astore 5
    //   6: aload 5
    //   8: monitorenter
    //   9: aload_1
    //   10: ifnull +142 -> 152
    //   13: aload_0
    //   14: invokevirtual 120	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:isOpen	()Z
    //   17: ifeq +135 -> 152
    //   20: aload_1
    //   21: invokevirtual 265	com/vlingo/sdk/internal/http/custom/HttpResponse:readHeaders	()Ljava/util/Hashtable;
    //   24: astore 9
    //   26: aload_0
    //   27: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   30: invokevirtual 269	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   33: astore 10
    //   35: aload 10
    //   37: ifnull +115 -> 152
    //   40: aload 10
    //   42: ldc_w 271
    //   45: invokeinterface 274 2 0
    //   50: checkcast 276	java/util/List
    //   53: astore 11
    //   55: aload 11
    //   57: ifnull +95 -> 152
    //   60: aconst_null
    //   61: astore 12
    //   63: aload 11
    //   65: invokeinterface 280 1 0
    //   70: astore 13
    //   72: aload 13
    //   74: invokeinterface 285 1 0
    //   79: ifeq +62 -> 141
    //   82: aload 12
    //   84: ifnonnull +18 -> 102
    //   87: aload 13
    //   89: invokeinterface 288 1 0
    //   94: checkcast 248	java/lang/String
    //   97: astore 12
    //   99: goto -27 -> 72
    //   102: new 290	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 291	java/lang/StringBuilder:<init>	()V
    //   109: aload 12
    //   111: invokevirtual 295	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: ldc_w 297
    //   117: invokevirtual 295	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: aload 13
    //   122: invokeinterface 288 1 0
    //   127: checkcast 248	java/lang/String
    //   130: invokevirtual 295	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: invokevirtual 300	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: astore 12
    //   138: goto -66 -> 72
    //   141: aload 9
    //   143: ldc_w 302
    //   146: aload 12
    //   148: invokevirtual 306	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   151: pop
    //   152: aload 5
    //   154: monitorexit
    //   155: aload_0
    //   156: iconst_0
    //   157: invokestatic 129	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   160: putfield 74	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_InputStatus	Ljava/lang/Boolean;
    //   163: aload_0
    //   164: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   167: astore 7
    //   169: aload 7
    //   171: monitorenter
    //   172: aload_0
    //   173: invokevirtual 120	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:isOpen	()Z
    //   176: ifeq +14 -> 190
    //   179: aload_0
    //   180: aload_0
    //   181: getfield 62	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_Connection	Ljava/net/HttpURLConnection;
    //   184: invokevirtual 308	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   187: putfield 66	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_InputStream	Ljava/io/InputStream;
    //   190: aload 7
    //   192: monitorexit
    //   193: aload_0
    //   194: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   197: astore_3
    //   198: aload_3
    //   199: monitorenter
    //   200: aload_0
    //   201: iconst_1
    //   202: invokestatic 129	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   205: putfield 74	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_InputStatus	Ljava/lang/Boolean;
    //   208: aload_0
    //   209: getfield 64	com/vlingo/sdk/internal/http/custom/AndroidVStreamConnection:m_URL	Ljava/net/URL;
    //   212: invokevirtual 132	java/lang/Object:notifyAll	()V
    //   215: aload_3
    //   216: monitorexit
    //   217: return
    //   218: astore 6
    //   220: aload 5
    //   222: monitorexit
    //   223: aload 6
    //   225: athrow
    //   226: astore_2
    //   227: goto -34 -> 193
    //   230: astore 8
    //   232: aload 7
    //   234: monitorexit
    //   235: aload 8
    //   237: athrow
    //   238: astore 4
    //   240: aload_3
    //   241: monitorexit
    //   242: aload 4
    //   244: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   13	155	218	finally
    //   220	223	218	finally
    //   0	9	226	java/lang/Exception
    //   155	172	226	java/lang/Exception
    //   223	226	226	java/lang/Exception
    //   235	238	226	java/lang/Exception
    //   172	193	230	finally
    //   232	235	230	finally
    //   200	217	238	finally
    //   240	242	238	finally
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.http.custom.AndroidVStreamConnection
 * JD-Core Version:    0.6.0
 */