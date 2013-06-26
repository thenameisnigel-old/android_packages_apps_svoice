package com.tencent.tauth.http.RequestListenerImpl;

import com.tencent.tauth.http.BaseRequestListener;
import com.tencent.tauth.http.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserInfoListener extends BaseRequestListener
{
  private static final String TAG = "UserInfoListener";
  private Callback mCallback;

  public UserInfoListener(Callback paramCallback)
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
    //   43: ifne +58 -> 101
    //   46: new 51	com/tencent/tauth/bean/UserInfo
    //   49: dup
    //   50: aload 6
    //   52: ldc 53
    //   54: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   57: aload 6
    //   59: ldc 55
    //   61: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   64: aload 6
    //   66: ldc 57
    //   68: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   71: aload 6
    //   73: ldc 59
    //   75: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   78: invokespecial 62	com/tencent/tauth/bean/UserInfo:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   81: astore 10
    //   83: aload_0
    //   84: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserInfoListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   87: aload 10
    //   89: invokeinterface 68 2 0
    //   94: ldc 8
    //   96: aload_1
    //   97: invokestatic 74	com/tencent/tauth/http/TDebug:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   100: return
    //   101: aload_0
    //   102: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserInfoListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   105: iload 7
    //   107: aload 8
    //   109: invokeinterface 78 3 0
    //   114: goto -20 -> 94
    //   117: astore 5
    //   119: aload_0
    //   120: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserInfoListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   123: ldc 79
    //   125: aload 5
    //   127: invokevirtual 83	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   130: invokeinterface 78 3 0
    //   135: aload 5
    //   137: invokevirtual 86	java/lang/NumberFormatException:printStackTrace	()V
    //   140: goto -46 -> 94
    //   143: astore 4
    //   145: aload_0
    //   146: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserInfoListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   149: ldc 79
    //   151: aload 4
    //   153: invokevirtual 87	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   156: invokeinterface 78 3 0
    //   161: aload 4
    //   163: invokevirtual 88	org/json/JSONException:printStackTrace	()V
    //   166: goto -72 -> 94
    //   169: astore_3
    //   170: aload_0
    //   171: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserInfoListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   174: ldc 79
    //   176: aload_3
    //   177: invokevirtual 89	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   180: invokeinterface 78 3 0
    //   185: aload_3
    //   186: invokevirtual 90	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   189: goto -95 -> 94
    //   192: astore 9
    //   194: goto -153 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	117	java/lang/NumberFormatException
    //   19	37	117	java/lang/NumberFormatException
    //   46	94	117	java/lang/NumberFormatException
    //   101	114	117	java/lang/NumberFormatException
    //   6	19	143	org/json/JSONException
    //   46	94	143	org/json/JSONException
    //   101	114	143	org/json/JSONException
    //   6	19	169	com/tencent/tauth/http/CommonException
    //   19	37	169	com/tencent/tauth/http/CommonException
    //   46	94	169	com/tencent/tauth/http/CommonException
    //   101	114	169	com/tencent/tauth/http/CommonException
    //   19	37	192	org/json/JSONException
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
 * Qualified Name:     com.tencent.tauth.http.RequestListenerImpl.UserInfoListener
 * JD-Core Version:    0.6.0
 */