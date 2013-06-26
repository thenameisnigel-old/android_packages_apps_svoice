package com.tencent.tauth.http.RequestListenerImpl;

import com.tencent.tauth.http.BaseRequestListener;
import com.tencent.tauth.http.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddTopicListener extends BaseRequestListener
{
  private static final String TAG = "AddShareListener";
  private Callback mCallback;

  public AddTopicListener(Callback paramCallback)
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
    //   35: astore 8
    //   37: aload_0
    //   38: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddTopicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   41: iload 7
    //   43: aload 8
    //   45: invokeinterface 55 3 0
    //   50: iload 7
    //   52: ifne +75 -> 127
    //   55: aconst_null
    //   56: astore 10
    //   58: aload 6
    //   60: ldc 57
    //   62: invokevirtual 60	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   65: astore 11
    //   67: aload 11
    //   69: ifnull +40 -> 109
    //   72: new 62	com/tencent/tauth/bean/TopicRichInfo
    //   75: dup
    //   76: aload 11
    //   78: ldc 64
    //   80: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   83: aload 11
    //   85: ldc 66
    //   87: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   90: aload 11
    //   92: ldc 68
    //   94: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   97: aload 11
    //   99: ldc 70
    //   101: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   104: invokespecial 73	com/tencent/tauth/bean/TopicRichInfo:<init>	(ILjava/lang/String;Ljava/lang/String;I)V
    //   107: astore 10
    //   109: aload_0
    //   110: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddTopicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   113: aload 10
    //   115: invokeinterface 77 2 0
    //   120: ldc 8
    //   122: aload_1
    //   123: invokestatic 83	com/tencent/tauth/http/TDebug:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   126: return
    //   127: aload_0
    //   128: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddTopicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   131: iload 7
    //   133: aload 8
    //   135: invokeinterface 55 3 0
    //   140: goto -20 -> 120
    //   143: astore 5
    //   145: aload_0
    //   146: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddTopicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   149: ldc 84
    //   151: aload 5
    //   153: invokevirtual 88	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   156: invokeinterface 55 3 0
    //   161: aload 5
    //   163: invokevirtual 91	java/lang/NumberFormatException:printStackTrace	()V
    //   166: goto -46 -> 120
    //   169: astore 4
    //   171: aload_0
    //   172: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddTopicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   175: ldc 84
    //   177: aload 4
    //   179: invokevirtual 92	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   182: invokeinterface 55 3 0
    //   187: aload 4
    //   189: invokevirtual 93	org/json/JSONException:printStackTrace	()V
    //   192: goto -72 -> 120
    //   195: astore_3
    //   196: aload_0
    //   197: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddTopicListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   200: ldc 84
    //   202: aload_3
    //   203: invokevirtual 94	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   206: invokeinterface 55 3 0
    //   211: aload_3
    //   212: invokevirtual 95	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   215: goto -95 -> 120
    //   218: astore 9
    //   220: goto -170 -> 50
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	143	java/lang/NumberFormatException
    //   19	50	143	java/lang/NumberFormatException
    //   58	120	143	java/lang/NumberFormatException
    //   127	140	143	java/lang/NumberFormatException
    //   6	19	169	org/json/JSONException
    //   58	120	169	org/json/JSONException
    //   127	140	169	org/json/JSONException
    //   6	19	195	com/tencent/tauth/http/CommonException
    //   19	50	195	com/tencent/tauth/http/CommonException
    //   58	120	195	com/tencent/tauth/http/CommonException
    //   127	140	195	com/tencent/tauth/http/CommonException
    //   19	50	218	org/json/JSONException
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
 * Qualified Name:     com.tencent.tauth.http.RequestListenerImpl.AddTopicListener
 * JD-Core Version:    0.6.0
 */