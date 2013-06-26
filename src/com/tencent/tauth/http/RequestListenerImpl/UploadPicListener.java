package com.tencent.tauth.http.RequestListenerImpl;

import com.tencent.tauth.http.BaseRequestListener;
import com.tencent.tauth.http.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadPicListener extends BaseRequestListener
{
  private static final String TAG = "UploadPicListener";
  private Callback mCallback;

  public UploadPicListener(Callback paramCallback)
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
    //   43: ifne +65 -> 108
    //   46: new 51	com/tencent/tauth/bean/Pic
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
    //   75: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   78: aload 6
    //   80: ldc 61
    //   82: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   85: invokespecial 64	com/tencent/tauth/bean/Pic:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   88: astore 10
    //   90: aload_0
    //   91: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UploadPicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   94: aload 10
    //   96: invokeinterface 70 2 0
    //   101: ldc 8
    //   103: aload_1
    //   104: invokestatic 76	com/tencent/tauth/http/TDebug:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   107: return
    //   108: aload_0
    //   109: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UploadPicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   112: iload 7
    //   114: aload 8
    //   116: invokeinterface 80 3 0
    //   121: goto -20 -> 101
    //   124: astore 5
    //   126: aload_0
    //   127: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UploadPicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   130: ldc 81
    //   132: aload 5
    //   134: invokevirtual 85	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   137: invokeinterface 80 3 0
    //   142: aload 5
    //   144: invokevirtual 88	java/lang/NumberFormatException:printStackTrace	()V
    //   147: goto -46 -> 101
    //   150: astore 4
    //   152: aload_0
    //   153: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UploadPicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   156: ldc 81
    //   158: aload 4
    //   160: invokevirtual 89	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   163: invokeinterface 80 3 0
    //   168: aload 4
    //   170: invokevirtual 90	org/json/JSONException:printStackTrace	()V
    //   173: goto -72 -> 101
    //   176: astore_3
    //   177: aload_0
    //   178: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UploadPicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   181: ldc 81
    //   183: aload_3
    //   184: invokevirtual 91	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   187: invokeinterface 80 3 0
    //   192: aload_3
    //   193: invokevirtual 92	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   196: goto -95 -> 101
    //   199: astore 9
    //   201: goto -160 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	124	java/lang/NumberFormatException
    //   19	37	124	java/lang/NumberFormatException
    //   46	101	124	java/lang/NumberFormatException
    //   108	121	124	java/lang/NumberFormatException
    //   6	19	150	org/json/JSONException
    //   46	101	150	org/json/JSONException
    //   108	121	150	org/json/JSONException
    //   6	19	176	com/tencent/tauth/http/CommonException
    //   19	37	176	com/tencent/tauth/http/CommonException
    //   46	101	176	com/tencent/tauth/http/CommonException
    //   108	121	176	com/tencent/tauth/http/CommonException
    //   19	37	199	org/json/JSONException
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
 * Qualified Name:     com.tencent.tauth.http.RequestListenerImpl.UploadPicListener
 * JD-Core Version:    0.6.0
 */