package com.tencent.tauth.http.RequestListenerImpl;

import com.tencent.tauth.http.BaseRequestListener;
import com.tencent.tauth.http.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddShareListener extends BaseRequestListener
{
  private static final String TAG = "AddShareListener";
  private Callback mCallback;

  public AddShareListener(Callback paramCallback)
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
    //   35: astore 10
    //   37: aload 10
    //   39: astore 8
    //   41: iload 7
    //   43: ifne +26 -> 69
    //   46: aload_0
    //   47: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddShareListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   50: aload 6
    //   52: ldc 51
    //   54: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   57: invokeinterface 57 2 0
    //   62: ldc 8
    //   64: aload_1
    //   65: invokestatic 63	com/tencent/tauth/http/TDebug:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   68: return
    //   69: aload_0
    //   70: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddShareListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   73: iload 7
    //   75: aload 8
    //   77: invokeinterface 67 3 0
    //   82: goto -20 -> 62
    //   85: astore 5
    //   87: aload_0
    //   88: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddShareListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   91: ldc 68
    //   93: aload 5
    //   95: invokevirtual 72	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   98: invokeinterface 67 3 0
    //   103: aload 5
    //   105: invokevirtual 75	java/lang/NumberFormatException:printStackTrace	()V
    //   108: goto -46 -> 62
    //   111: astore 4
    //   113: aload_0
    //   114: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddShareListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   117: ldc 68
    //   119: aload 4
    //   121: invokevirtual 76	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   124: invokeinterface 67 3 0
    //   129: aload 4
    //   131: invokevirtual 77	org/json/JSONException:printStackTrace	()V
    //   134: goto -72 -> 62
    //   137: astore_3
    //   138: aload_0
    //   139: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddShareListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   142: ldc 68
    //   144: aload_3
    //   145: invokevirtual 78	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   148: invokeinterface 67 3 0
    //   153: aload_3
    //   154: invokevirtual 79	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   157: goto -95 -> 62
    //   160: astore 9
    //   162: goto -121 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	85	java/lang/NumberFormatException
    //   19	37	85	java/lang/NumberFormatException
    //   46	62	85	java/lang/NumberFormatException
    //   69	82	85	java/lang/NumberFormatException
    //   6	19	111	org/json/JSONException
    //   46	62	111	org/json/JSONException
    //   69	82	111	org/json/JSONException
    //   6	19	137	com/tencent/tauth/http/CommonException
    //   19	37	137	com/tencent/tauth/http/CommonException
    //   46	62	137	com/tencent/tauth/http/CommonException
    //   69	82	137	com/tencent/tauth/http/CommonException
    //   19	37	160	org/json/JSONException
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
 * Qualified Name:     com.tencent.tauth.http.RequestListenerImpl.AddShareListener
 * JD-Core Version:    0.6.0
 */