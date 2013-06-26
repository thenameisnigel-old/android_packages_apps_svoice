package com.tencent.tauth.http.RequestListenerImpl;

import com.tencent.tauth.http.BaseRequestListener;
import com.tencent.tauth.http.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddAlbumListener extends BaseRequestListener
{
  private static final String TAG = "AddAlbumListener";
  private Callback mCallback;

  public AddAlbumListener(Callback paramCallback)
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
    //   43: ifne +73 -> 116
    //   46: new 51	com/tencent/tauth/bean/Album
    //   49: dup
    //   50: aload 6
    //   52: ldc 53
    //   54: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   57: aload 6
    //   59: ldc 55
    //   61: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   64: aload 6
    //   66: ldc 57
    //   68: invokevirtual 61	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   71: aload 6
    //   73: ldc 63
    //   75: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   78: aload 6
    //   80: ldc 65
    //   82: invokevirtual 49	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   85: lconst_0
    //   86: aload 6
    //   88: ldc 67
    //   90: invokevirtual 43	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   93: invokespecial 70	com/tencent/tauth/bean/Album:<init>	(Ljava/lang/String;IJLjava/lang/String;Ljava/lang/String;JI)V
    //   96: astore 10
    //   98: aload_0
    //   99: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   102: aload 10
    //   104: invokeinterface 76 2 0
    //   109: ldc 8
    //   111: aload_1
    //   112: invokestatic 82	com/tencent/tauth/http/TDebug:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   115: return
    //   116: aload_0
    //   117: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   120: iload 7
    //   122: aload 8
    //   124: invokeinterface 86 3 0
    //   129: goto -20 -> 109
    //   132: astore 5
    //   134: aload_0
    //   135: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   138: ldc 87
    //   140: aload 5
    //   142: invokevirtual 91	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   145: invokeinterface 86 3 0
    //   150: aload 5
    //   152: invokevirtual 94	java/lang/NumberFormatException:printStackTrace	()V
    //   155: goto -46 -> 109
    //   158: astore 4
    //   160: aload_0
    //   161: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   164: ldc 87
    //   166: aload 4
    //   168: invokevirtual 95	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   171: invokeinterface 86 3 0
    //   176: aload 4
    //   178: invokevirtual 96	org/json/JSONException:printStackTrace	()V
    //   181: goto -72 -> 109
    //   184: astore_3
    //   185: aload_0
    //   186: getfield 17	com/tencent/tauth/http/RequestListenerImpl/AddAlbumListener:mCallback	Lcom/tencent/tauth/http/Callback;
    //   189: ldc 87
    //   191: aload_3
    //   192: invokevirtual 97	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   195: invokeinterface 86 3 0
    //   200: aload_3
    //   201: invokevirtual 98	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   204: goto -95 -> 109
    //   207: astore 9
    //   209: goto -168 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	132	java/lang/NumberFormatException
    //   19	37	132	java/lang/NumberFormatException
    //   46	109	132	java/lang/NumberFormatException
    //   116	129	132	java/lang/NumberFormatException
    //   6	19	158	org/json/JSONException
    //   46	109	158	org/json/JSONException
    //   116	129	158	org/json/JSONException
    //   6	19	184	com/tencent/tauth/http/CommonException
    //   19	37	184	com/tencent/tauth/http/CommonException
    //   46	109	184	com/tencent/tauth/http/CommonException
    //   116	129	184	com/tencent/tauth/http/CommonException
    //   19	37	207	org/json/JSONException
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
 * Qualified Name:     com.tencent.tauth.http.RequestListenerImpl.AddAlbumListener
 * JD-Core Version:    0.6.0
 */