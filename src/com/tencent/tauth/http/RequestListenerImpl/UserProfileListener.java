package com.tencent.tauth.http.RequestListenerImpl;

import com.tencent.tauth.http.BaseRequestListener;
import com.tencent.tauth.http.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserProfileListener extends BaseRequestListener
{
  private static final String TAG = "UserProfileListener";
  private Callback mCallback;

  public UserProfileListener(Callback paramCallback)
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
    //   35: astore 13
    //   37: aload 13
    //   39: astore 8
    //   41: iload 7
    //   43: ifne +98 -> 141
    //   46: aload 6
    //   48: ldc 51
    //   50: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   53: astore 10
    //   55: aload 10
    //   57: ldc 53
    //   59: invokevirtual 59	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   62: ifeq +63 -> 125
    //   65: iconst_1
    //   66: istore 11
    //   68: new 61	com/tencent/tauth/bean/UserProfile
    //   71: dup
    //   72: aload 6
    //   74: ldc 63
    //   76: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   79: iload 11
    //   81: aload 6
    //   83: ldc 65
    //   85: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   88: aload 6
    //   90: ldc 67
    //   92: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   95: aload 6
    //   97: ldc 69
    //   99: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   102: invokespecial 72	com/tencent/tauth/bean/UserProfile:<init>	(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   105: astore 12
    //   107: aload_0
    //   108: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserProfileListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   111: aload 12
    //   113: invokeinterface 78 2 0
    //   118: ldc 8
    //   120: aload_1
    //   121: invokestatic 84	com/tencent/tauth/http/TDebug:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   124: return
    //   125: aload 10
    //   127: ldc 86
    //   129: invokevirtual 59	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   132: ifeq +105 -> 237
    //   135: iconst_0
    //   136: istore 11
    //   138: goto -70 -> 68
    //   141: aload_0
    //   142: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserProfileListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   145: iload 7
    //   147: aload 8
    //   149: invokeinterface 90 3 0
    //   154: goto -36 -> 118
    //   157: astore 5
    //   159: aload_0
    //   160: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserProfileListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   163: ldc 91
    //   165: aload 5
    //   167: invokevirtual 95	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   170: invokeinterface 90 3 0
    //   175: aload 5
    //   177: invokevirtual 98	java/lang/NumberFormatException:printStackTrace	()V
    //   180: goto -62 -> 118
    //   183: astore 4
    //   185: aload_0
    //   186: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserProfileListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   189: ldc 91
    //   191: aload 4
    //   193: invokevirtual 99	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   196: invokeinterface 90 3 0
    //   201: aload 4
    //   203: invokevirtual 100	org/json/JSONException:printStackTrace	()V
    //   206: goto -88 -> 118
    //   209: astore_3
    //   210: aload_0
    //   211: getfield 17	com/tencent/tauth/http/RequestListenerImpl/UserProfileListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   214: ldc 91
    //   216: aload_3
    //   217: invokevirtual 101	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   220: invokeinterface 90 3 0
    //   225: aload_3
    //   226: invokevirtual 102	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   229: goto -111 -> 118
    //   232: astore 9
    //   234: goto -193 -> 41
    //   237: iconst_2
    //   238: istore 11
    //   240: goto -172 -> 68
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	157	java/lang/NumberFormatException
    //   19	37	157	java/lang/NumberFormatException
    //   46	118	157	java/lang/NumberFormatException
    //   125	154	157	java/lang/NumberFormatException
    //   6	19	183	org/json/JSONException
    //   46	118	183	org/json/JSONException
    //   125	154	183	org/json/JSONException
    //   6	19	209	com/tencent/tauth/http/CommonException
    //   19	37	209	com/tencent/tauth/http/CommonException
    //   46	118	209	com/tencent/tauth/http/CommonException
    //   125	154	209	com/tencent/tauth/http/CommonException
    //   19	37	232	org/json/JSONException
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
 * Qualified Name:     com.tencent.tauth.http.RequestListenerImpl.UserProfileListener
 * JD-Core Version:    0.6.0
 */