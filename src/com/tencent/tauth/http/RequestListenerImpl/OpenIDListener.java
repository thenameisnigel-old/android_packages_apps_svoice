package com.tencent.tauth.http.RequestListenerImpl;

import com.tencent.tauth.http.BaseRequestListener;
import com.tencent.tauth.http.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OpenIDListener extends BaseRequestListener
{
  private static final String TAG = "OpenIDListener";
  private Callback mCallback;

  public OpenIDListener(Callback paramCallback)
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
    //   35: astore 11
    //   37: aload 11
    //   39: astore 8
    //   41: iload 7
    //   43: ifne +44 -> 87
    //   46: new 51	com/tencent/tauth/bean/OpenId
    //   49: dup
    //   50: aload 6
    //   52: ldc 53
    //   54: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   57: aload 6
    //   59: ldc 55
    //   61: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   64: invokespecial 58	com/tencent/tauth/bean/OpenId:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   67: astore 10
    //   69: aload_0
    //   70: getfield 17	com/tencent/tauth/http/RequestListenerImpl/OpenIDListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   73: aload 10
    //   75: invokeinterface 64 2 0
    //   80: ldc 8
    //   82: aload_1
    //   83: invokestatic 69	com/tencent/tauth/http/TDebug:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: return
    //   87: aload_0
    //   88: getfield 17	com/tencent/tauth/http/RequestListenerImpl/OpenIDListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   91: iload 7
    //   93: aload 8
    //   95: invokeinterface 73 3 0
    //   100: goto -20 -> 80
    //   103: astore 5
    //   105: aload_0
    //   106: getfield 17	com/tencent/tauth/http/RequestListenerImpl/OpenIDListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   109: ldc 74
    //   111: aload 5
    //   113: invokevirtual 78	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   116: invokeinterface 73 3 0
    //   121: aload 5
    //   123: invokevirtual 81	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   126: goto -46 -> 80
    //   129: astore 4
    //   131: aload_0
    //   132: getfield 17	com/tencent/tauth/http/RequestListenerImpl/OpenIDListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   135: ldc 74
    //   137: aload 4
    //   139: invokevirtual 82	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   142: invokeinterface 73 3 0
    //   147: goto -67 -> 80
    //   150: astore_3
    //   151: aload_0
    //   152: getfield 17	com/tencent/tauth/http/RequestListenerImpl/OpenIDListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   155: ldc 74
    //   157: aload_3
    //   158: invokevirtual 83	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   161: invokeinterface 73 3 0
    //   166: goto -86 -> 80
    //   169: astore 9
    //   171: goto -130 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	103	com/tencent/tauth/http/CommonException
    //   19	37	103	com/tencent/tauth/http/CommonException
    //   46	80	103	com/tencent/tauth/http/CommonException
    //   87	100	103	com/tencent/tauth/http/CommonException
    //   6	19	129	java/lang/NumberFormatException
    //   19	37	129	java/lang/NumberFormatException
    //   46	80	129	java/lang/NumberFormatException
    //   87	100	129	java/lang/NumberFormatException
    //   6	19	150	org/json/JSONException
    //   46	80	150	org/json/JSONException
    //   87	100	150	org/json/JSONException
    //   19	37	169	org/json/JSONException
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
 * Qualified Name:     com.tencent.tauth.http.RequestListenerImpl.OpenIDListener
 * JD-Core Version:    0.6.0
 */