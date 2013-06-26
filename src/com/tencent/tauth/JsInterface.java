package com.tencent.tauth;

import com.tencent.tauth.http.Callback;

public class JsInterface
{
  private Callback mCallback;

  public JsInterface(Callback paramCallback)
  {
    this.mCallback = paramCallback;
  }

  // ERROR //
  public void onAddShare(java.lang.String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 27	com/tencent/tauth/http/ParseResoneJson:parseJson	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   4: astore 5
    //   6: ldc 29
    //   8: astore 6
    //   10: aload 5
    //   12: ldc 31
    //   14: invokevirtual 37	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   17: istore 8
    //   19: aload 5
    //   21: ldc 39
    //   23: invokevirtual 43	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   26: astore 9
    //   28: aload 9
    //   30: astore 6
    //   32: iload 8
    //   34: ifne +17 -> 51
    //   37: aload_0
    //   38: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   41: aload 5
    //   43: invokeinterface 49 2 0
    //   48: goto +88 -> 136
    //   51: aload_0
    //   52: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   55: iload 8
    //   57: aload 6
    //   59: invokeinterface 53 3 0
    //   64: goto +72 -> 136
    //   67: astore 4
    //   69: aload_0
    //   70: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   73: ldc 54
    //   75: aload 4
    //   77: invokevirtual 58	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   80: invokeinterface 53 3 0
    //   85: aload 4
    //   87: invokevirtual 61	java/lang/NumberFormatException:printStackTrace	()V
    //   90: goto +46 -> 136
    //   93: astore_3
    //   94: aload_0
    //   95: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   98: ldc 54
    //   100: aload_3
    //   101: invokevirtual 62	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   104: invokeinterface 53 3 0
    //   109: aload_3
    //   110: invokevirtual 63	org/json/JSONException:printStackTrace	()V
    //   113: goto +23 -> 136
    //   116: astore_2
    //   117: aload_0
    //   118: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   121: ldc 54
    //   123: aload_2
    //   124: invokevirtual 64	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   127: invokeinterface 53 3 0
    //   132: aload_2
    //   133: invokevirtual 65	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   136: return
    //   137: astore 7
    //   139: bipush 255
    //   141: istore 8
    //   143: goto -111 -> 32
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	67	java/lang/NumberFormatException
    //   10	28	67	java/lang/NumberFormatException
    //   37	64	67	java/lang/NumberFormatException
    //   0	10	93	org/json/JSONException
    //   37	64	93	org/json/JSONException
    //   0	10	116	com/tencent/tauth/http/CommonException
    //   10	28	116	com/tencent/tauth/http/CommonException
    //   37	64	116	com/tencent/tauth/http/CommonException
    //   10	28	137	org/json/JSONException
  }

  public void onCancelAddShare(int paramInt)
  {
    this.mCallback.onCancel(paramInt);
  }

  public void onCancelGift()
  {
    this.mCallback.onCancel(0);
  }

  public void onCancelInvite()
  {
    this.mCallback.onCancel(0);
  }

  public void onCancelLogin()
  {
    this.mCallback.onCancel(0);
  }

  public void onCancelRequest()
  {
    this.mCallback.onCancel(0);
  }

  // ERROR //
  public void onGift(java.lang.String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 27	com/tencent/tauth/http/ParseResoneJson:parseJson	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   4: astore 5
    //   6: ldc 29
    //   8: astore 6
    //   10: aload 5
    //   12: ldc 31
    //   14: invokevirtual 37	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   17: istore 8
    //   19: aload 5
    //   21: ldc 39
    //   23: invokevirtual 43	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   26: astore 9
    //   28: aload 9
    //   30: astore 6
    //   32: iload 8
    //   34: ifne +17 -> 51
    //   37: aload_0
    //   38: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   41: aload 5
    //   43: invokeinterface 49 2 0
    //   48: goto +88 -> 136
    //   51: aload_0
    //   52: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   55: iload 8
    //   57: aload 6
    //   59: invokeinterface 53 3 0
    //   64: goto +72 -> 136
    //   67: astore 4
    //   69: aload_0
    //   70: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   73: ldc 54
    //   75: aload 4
    //   77: invokevirtual 58	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   80: invokeinterface 53 3 0
    //   85: aload 4
    //   87: invokevirtual 61	java/lang/NumberFormatException:printStackTrace	()V
    //   90: goto +46 -> 136
    //   93: astore_3
    //   94: aload_0
    //   95: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   98: ldc 54
    //   100: aload_3
    //   101: invokevirtual 62	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   104: invokeinterface 53 3 0
    //   109: aload_3
    //   110: invokevirtual 63	org/json/JSONException:printStackTrace	()V
    //   113: goto +23 -> 136
    //   116: astore_2
    //   117: aload_0
    //   118: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   121: ldc 54
    //   123: aload_2
    //   124: invokevirtual 64	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   127: invokeinterface 53 3 0
    //   132: aload_2
    //   133: invokevirtual 65	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   136: return
    //   137: astore 7
    //   139: bipush 255
    //   141: istore 8
    //   143: goto -111 -> 32
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	67	java/lang/NumberFormatException
    //   10	28	67	java/lang/NumberFormatException
    //   37	64	67	java/lang/NumberFormatException
    //   0	10	93	org/json/JSONException
    //   37	64	93	org/json/JSONException
    //   0	10	116	com/tencent/tauth/http/CommonException
    //   10	28	116	com/tencent/tauth/http/CommonException
    //   37	64	116	com/tencent/tauth/http/CommonException
    //   10	28	137	org/json/JSONException
  }

  // ERROR //
  public void onInvite(java.lang.String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 27	com/tencent/tauth/http/ParseResoneJson:parseJson	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   4: astore 5
    //   6: ldc 29
    //   8: astore 6
    //   10: aload 5
    //   12: ldc 31
    //   14: invokevirtual 37	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   17: istore 8
    //   19: aload 5
    //   21: ldc 39
    //   23: invokevirtual 43	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   26: astore 9
    //   28: aload 9
    //   30: astore 6
    //   32: iload 8
    //   34: ifne +17 -> 51
    //   37: aload_0
    //   38: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   41: aload 5
    //   43: invokeinterface 49 2 0
    //   48: goto +88 -> 136
    //   51: aload_0
    //   52: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   55: iload 8
    //   57: aload 6
    //   59: invokeinterface 53 3 0
    //   64: goto +72 -> 136
    //   67: astore 4
    //   69: aload_0
    //   70: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   73: ldc 54
    //   75: aload 4
    //   77: invokevirtual 58	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   80: invokeinterface 53 3 0
    //   85: aload 4
    //   87: invokevirtual 61	java/lang/NumberFormatException:printStackTrace	()V
    //   90: goto +46 -> 136
    //   93: astore_3
    //   94: aload_0
    //   95: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   98: ldc 54
    //   100: aload_3
    //   101: invokevirtual 62	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   104: invokeinterface 53 3 0
    //   109: aload_3
    //   110: invokevirtual 63	org/json/JSONException:printStackTrace	()V
    //   113: goto +23 -> 136
    //   116: astore_2
    //   117: aload_0
    //   118: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   121: ldc 54
    //   123: aload_2
    //   124: invokevirtual 64	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   127: invokeinterface 53 3 0
    //   132: aload_2
    //   133: invokevirtual 65	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   136: return
    //   137: astore 7
    //   139: bipush 255
    //   141: istore 8
    //   143: goto -111 -> 32
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	67	java/lang/NumberFormatException
    //   10	28	67	java/lang/NumberFormatException
    //   37	64	67	java/lang/NumberFormatException
    //   0	10	93	org/json/JSONException
    //   37	64	93	org/json/JSONException
    //   0	10	116	com/tencent/tauth/http/CommonException
    //   10	28	116	com/tencent/tauth/http/CommonException
    //   37	64	116	com/tencent/tauth/http/CommonException
    //   10	28	137	org/json/JSONException
  }

  // ERROR //
  public void onRequest(java.lang.String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 27	com/tencent/tauth/http/ParseResoneJson:parseJson	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   4: astore 5
    //   6: ldc 29
    //   8: astore 6
    //   10: aload 5
    //   12: ldc 31
    //   14: invokevirtual 37	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   17: istore 8
    //   19: aload 5
    //   21: ldc 39
    //   23: invokevirtual 43	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   26: astore 9
    //   28: aload 9
    //   30: astore 6
    //   32: iload 8
    //   34: ifne +17 -> 51
    //   37: aload_0
    //   38: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   41: aload 5
    //   43: invokeinterface 49 2 0
    //   48: goto +88 -> 136
    //   51: aload_0
    //   52: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   55: iload 8
    //   57: aload 6
    //   59: invokeinterface 53 3 0
    //   64: goto +72 -> 136
    //   67: astore 4
    //   69: aload_0
    //   70: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   73: ldc 54
    //   75: aload 4
    //   77: invokevirtual 58	java/lang/NumberFormatException:getMessage	()Ljava/lang/String;
    //   80: invokeinterface 53 3 0
    //   85: aload 4
    //   87: invokevirtual 61	java/lang/NumberFormatException:printStackTrace	()V
    //   90: goto +46 -> 136
    //   93: astore_3
    //   94: aload_0
    //   95: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   98: ldc 54
    //   100: aload_3
    //   101: invokevirtual 62	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   104: invokeinterface 53 3 0
    //   109: aload_3
    //   110: invokevirtual 63	org/json/JSONException:printStackTrace	()V
    //   113: goto +23 -> 136
    //   116: astore_2
    //   117: aload_0
    //   118: getfield 13	com/tencent/tauth/JsInterface:mCallback	Lcom/tencent/tauth/http/Callback;
    //   121: ldc 54
    //   123: aload_2
    //   124: invokevirtual 64	com/tencent/tauth/http/CommonException:getMessage	()Ljava/lang/String;
    //   127: invokeinterface 53 3 0
    //   132: aload_2
    //   133: invokevirtual 65	com/tencent/tauth/http/CommonException:printStackTrace	()V
    //   136: return
    //   137: astore 7
    //   139: bipush 255
    //   141: istore 8
    //   143: goto -111 -> 32
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	67	java/lang/NumberFormatException
    //   10	28	67	java/lang/NumberFormatException
    //   37	64	67	java/lang/NumberFormatException
    //   0	10	93	org/json/JSONException
    //   37	64	93	org/json/JSONException
    //   0	10	116	com/tencent/tauth/http/CommonException
    //   10	28	116	com/tencent/tauth/http/CommonException
    //   37	64	116	com/tencent/tauth/http/CommonException
    //   10	28	137	org/json/JSONException
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.JsInterface
 * JD-Core Version:    0.6.0
 */