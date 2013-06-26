package com.tencent.tauth.http.RequestListenerImpl;

import com.tencent.tauth.http.BaseRequestListener;
import com.tencent.tauth.http.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ListAlbumListener extends BaseRequestListener
{
  private static final String TAG = "ListAlbumListener";
  private Callback mCallback;

  public ListAlbumListener(Callback paramCallback)
  {
    this.mCallback = paramCallback;
  }

  // ERROR //
  public void onComplete(String paramString, Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokespecial 27	com/tencent/tauth/http/BaseRequestListener:onComplete	(Ljava/lang/String;Ljava/lang/Object;)V
    //   6: aload_1
    //   7: invokestatic 33	com/tencent/tauth/http/ParseResoneJson:parseJson	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   10: astore 6
    //   12: iconst_0
    //   13: istore 7
    //   15: ldc 35
    //   17: astore 8
    //   19: aload 6
    //   21: ldc 37
    //   23: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   26: istore 7
    //   28: aload 6
    //   30: ldc 45
    //   32: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   35: astore 16
    //   37: aload 16
    //   39: astore 8
    //   41: iload 7
    //   43: ifne +136 -> 179
    //   46: aload 6
    //   48: ldc 51
    //   50: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   53: istore 10
    //   55: aload 6
    //   57: ldc 53
    //   59: invokevirtual 57	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   62: astore 11
    //   64: new 59	java/util/ArrayList
    //   67: dup
    //   68: invokespecial 60	java/util/ArrayList:<init>	()V
    //   71: astore 12
    //   73: iconst_0
    //   74: istore 13
    //   76: iload 13
    //   78: iload 10
    //   80: if_icmplt +21 -> 101
    //   83: aload_0
    //   84: getfield 17	com/tencent/tauth/http/RequestListenerImpl/ListAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   87: aload 12
    //   89: invokeinterface 66 2 0
    //   94: ldc 8
    //   96: aload_1
    //   97: invokestatic 72	com/tencent/tauth/http/TDebug:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   100: return
    //   101: aload 11
    //   103: iload 13
    //   105: invokevirtual 78	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   108: astore 14
    //   110: aload 12
    //   112: new 80	com/tencent/tauth/bean/Album
    //   115: dup
    //   116: aload 14
    //   118: ldc 82
    //   120: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   123: aload 14
    //   125: ldc 84
    //   127: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   130: aload 14
    //   132: ldc 86
    //   134: invokevirtual 90	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   137: aload 14
    //   139: ldc 92
    //   141: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   144: aload 14
    //   146: ldc 94
    //   148: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   151: aload 14
    //   153: ldc 96
    //   155: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   158: i2l
    //   159: aload 14
    //   161: ldc 98
    //   163: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   166: invokespecial 101	com/tencent/tauth/bean/Album:<init>	(Ljava/lang/String;IJLjava/lang/String;Ljava/lang/String;JI)V
    //   169: invokevirtual 105	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   172: pop
    //   173: iinc 13 1
    //   176: goto -100 -> 76
    //   179: aload_0
    //   180: getfield 17	com/tencent/tauth/http/RequestListenerImpl/ListAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   183: iload 7
    //   185: aload 8
    //   187: invokeinterface 109 3 0
    //   192: goto -98 -> 94
    //   195: astore 5
    //   197: aload_0
    //   198: getfield 17	com/tencent/tauth/http/RequestListenerImpl/ListAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   201: ldc 110
    //   203: aload 5
    //   205: invokevirtual 114	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   208: invokeinterface 109 3 0
    //   213: aload 5
    //   215: invokevirtual 117	java/lang/NumberFormatException:printStackTrace	()V
    //   218: goto -124 -> 94
    //   221: astore 4
    //   223: aload_0
    //   224: getfield 17	com/tencent/tauth/http/RequestListenerImpl/ListAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   227: ldc 110
    //   229: aload 4
    //   231: invokevirtual 118	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   234: invokeinterface 109 3 0
    //   239: aload 4
    //   241: invokevirtual 119	org/json/JSONException:printStackTrace	()V
    //   244: goto -150 -> 94
    //   247: astore_3
    //   248: aload_0
    //   249: getfield 17	com/tencent/tauth/http/RequestListenerImpl/ListAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   252: ldc 110
    //   254: aload_3
    //   255: invokevirtual 120	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   258: invokeinterface 109 3 0
    //   263: aload_3
    //   264: invokevirtual 121	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   267: goto -173 -> 94
    //   270: astore 9
    //   272: goto -231 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	195	java/lang/NumberFormatException
    //   19	37	195	java/lang/NumberFormatException
    //   46	94	195	java/lang/NumberFormatException
    //   101	192	195	java/lang/NumberFormatException
    //   6	19	221	org/json/JSONException
    //   46	94	221	org/json/JSONException
    //   101	192	221	org/json/JSONException
    //   6	19	247	com/tencent/tauth/http/CommonException
    //   19	37	247	com/tencent/tauth/http/CommonException
    //   46	94	247	com/tencent/tauth/http/CommonException
    //   101	192	247	com/tencent/tauth/http/CommonException
    //   19	37	270	org/json/JSONException
  }

  public void onFileNotFoundException(FileNotFoundException paramFileNotFoundException, Object paramObject)
  {
    this.mCallback.onFail(-2147483648, "Resource not found:" + paramFileNotFoundException.getMessage());
  }

  public void onIOException(IOException paramIOException, Object paramObject)
  {
    this.mCallback.onFail(-2147483648, "Network Error:" + paramIOException.getMessage());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.http.RequestListenerImpl.ListAlbumListener
 * JD-Core Version:    0.6.0
 */