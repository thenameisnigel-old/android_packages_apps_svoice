package com.tencent.tauth.http.RequestListenerImpl;

import com.tencent.tauth.http.BaseRequestListener;
import com.tencent.tauth.http.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddWeiBoListener extends BaseRequestListener
{
  private static final String TAG = "AddWeiBoListener";
  private Callback mCallback;

  public AddWeiBoListener(Callback paramCallback)
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
    //   43: ifeq +16 -> 59
    //   46: aload_0
    //   47: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddWeiBoListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   50: iload 7
    //   52: aload 8
    //   54: invokeinterface 55 3 0
    //   59: ldc 8
    //   61: aload_1
    //   62: invokestatic 61	com/tencent/tauth/http/TDebug:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   65: return
    //   66: astore 5
    //   68: aload_0
    //   69: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddWeiBoListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   72: ldc 62
    //   74: aload 5
    //   76: invokevirtual 66	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   79: invokeinterface 55 3 0
    //   84: aload 5
    //   86: invokevirtual 69	java/lang/NumberFormatException:printStackTrace	()V
    //   89: goto -30 -> 59
    //   92: astore 4
    //   94: aload_0
    //   95: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddWeiBoListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   98: ldc 62
    //   100: aload 4
    //   102: invokevirtual 70	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   105: invokeinterface 55 3 0
    //   110: aload 4
    //   112: invokevirtual 71	org/json/JSONException:printStackTrace	()V
    //   115: goto -56 -> 59
    //   118: astore_3
    //   119: aload_0
    //   120: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddWeiBoListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   123: ldc 62
    //   125: aload_3
    //   126: invokevirtual 72	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   129: invokeinterface 55 3 0
    //   134: aload_3
    //   135: invokevirtual 73	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   138: goto -79 -> 59
    //   141: astore 9
    //   143: goto -102 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	66	java/lang/NumberFormatException
    //   19	37	66	java/lang/NumberFormatException
    //   46	59	66	java/lang/NumberFormatException
    //   6	19	92	org/json/JSONException
    //   46	59	92	org/json/JSONException
    //   6	19	118	com/tencent/tauth/http/CommonException
    //   19	37	118	com/tencent/tauth/http/CommonException
    //   46	59	118	com/tencent/tauth/http/CommonException
    //   19	37	141	org/json/JSONException
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
 * Qualified Name:     com.tencent.tauth.http.RequestListenerImpl.AddWeiBoListener
 * JD-Core Version:    0.6.0
 */