package com.vlingo.sdk.internal.recognizer.sr3;

import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.http.HttpUtil;
import com.vlingo.sdk.internal.net.ConnectionManager;
import com.vlingo.sdk.internal.recognizer.ClientMeta;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.internal.recognizer.SRRequest;
import com.vlingo.sdk.internal.recognizer.SRRequestListener;
import com.vlingo.sdk.internal.recognizer.SoftwareMeta;
import com.vlingo.sdk.internal.recognizer.TimingRepository;
import com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.CRC32;

public class SR3Request
  implements SRRequest, Runnable
{
  private static final String ATR_ClientRequestID = "ClientRequestID";
  private volatile boolean allAudioReceived;
  private final Queue<AudioSegment> audioQueue = new ConcurrentLinkedQueue();
  private int ivAudioBytesWritten;
  private final CRC32 ivCRC32 = new CRC32();
  private final ClientMeta ivClientMeta;
  private int ivClientRequestID = 0;
  private final SRContext ivContext;
  private final CopyOnWriteArrayList<SRRequestListener> ivListeners = new CopyOnWriteArrayList();
  private HttpConnectionAdapter ivSRCon;
  private final SR3Manager ivSRManager;
  private final SoftwareMeta ivSoftwareMeta;
  private long ivTimeGotResults;
  private long ivTimeSendFinish;
  private long ivTimeSendStart;
  private TimingRepository ivTimings;
  private volatile boolean requestCancelled;
  private boolean requestFinished;
  private volatile Thread requestThread;
  private volatile boolean responseReceived;
  private final boolean sendAudio;
  private boolean sentCancelRequest;
  private volatile boolean startedWritingAudio;

  SR3Request(SRContext paramSRContext, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta, SR3Manager paramSR3Manager, TimingRepository paramTimingRepository, boolean paramBoolean)
  {
    log("instantiation");
    this.ivContext = paramSRContext;
    this.ivClientMeta = paramClientMeta;
    this.ivSoftwareMeta = paramSoftwareMeta;
    this.ivSRManager = paramSR3Manager;
    this.sendAudio = paramBoolean;
    this.ivTimings = paramTimingRepository;
  }

  private String buildEventElement()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    List localList = this.ivContext.getEvents();
    if ((localList != null) && (localList.size() > 0))
    {
      localStringBuffer.append("<Events>");
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
        localStringBuffer.append(((VLDialogEvent)localIterator.next()).getXML());
      localStringBuffer.append("</Events>");
    }
    return localStringBuffer.toString();
  }

  private String buildMetaElement()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<Request ");
    localStringBuffer.append(HttpUtil.genAtr("FieldID", this.ivContext.getFieldID()));
    localStringBuffer.append(HttpUtil.genAtr("AppID", this.ivSoftwareMeta.getAppId()));
    localStringBuffer.append(HttpUtil.genAtr("FieldType", this.ivContext.getFieldType()));
    localStringBuffer.append(HttpUtil.genAtr("FieldContext", this.ivContext.getFieldContext()));
    String str1 = this.ivContext.getCurrentText();
    if (str1.length() > 0)
    {
      localStringBuffer.append(HttpUtil.genAtr("CurrentText", str1));
      localStringBuffer.append(HttpUtil.genAtr("CursorPosition", this.ivContext.getCursorPosition()));
    }
    for (int i = 1; i <= 6; i++)
    {
      String str4 = this.ivContext.getCustomParam("Custom" + i);
      if (str4.length() <= 0)
        continue;
      localStringBuffer.append(HttpUtil.genAtr("Custom" + i, str4));
    }
    String str2;
    if (ApplicationAdapter.getInstance().isAudioStreamingEnabled())
    {
      str2 = "true";
      localStringBuffer.append(HttpUtil.genAtr("StreamingAudio", str2));
      if (!this.ivContext.getAutoPunctuation())
        break label299;
    }
    label299: for (String str3 = "true"; ; str3 = "false")
    {
      localStringBuffer.append(HttpUtil.genAtr("Punctuate", str3));
      localStringBuffer.append(HttpUtil.genAtr("Capitalize", this.ivContext.getCapitalization()));
      localStringBuffer.append("/>");
      return localStringBuffer.toString();
      str2 = "false";
      break;
    }
  }

  // ERROR //
  private void closeConnection()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +15 -> 23
    //   11: aload_0
    //   12: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   15: invokevirtual 238	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:close	()V
    //   18: aload_0
    //   19: aconst_null
    //   20: putfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    //   31: astore_3
    //   32: goto -14 -> 18
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	26	finally
    //   11	18	26	finally
    //   18	23	26	finally
    //   11	18	31	java/lang/Exception
  }

  private void fail(String paramString, Exception paramException)
  {
    if ((paramException instanceof InterruptedIOException))
      ConnectionManager.setTimedOut(true);
    notifyListeners(-1);
    closeConnection();
  }

  // ERROR //
  private void finishRequest()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   6: ifne +12 -> 18
    //   9: aload_0
    //   10: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   13: astore_2
    //   14: aload_2
    //   15: ifnonnull +6 -> 21
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: aload_0
    //   22: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   25: invokevirtual 261	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getOut	()Lcom/vlingo/sdk/internal/http/custom/MPOutputStream;
    //   28: astore 5
    //   30: aload_0
    //   31: getfield 90	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:sendAudio	Z
    //   34: ifeq +55 -> 89
    //   37: aload 5
    //   39: invokevirtual 266	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeEndFieldValue	()V
    //   42: aload 5
    //   44: invokevirtual 269	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeBoundary	()V
    //   47: aload 5
    //   49: ldc_w 271
    //   52: ldc_w 273
    //   55: new 187	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   62: ldc_w 275
    //   65: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: aload_0
    //   69: getfield 67	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivCRC32	Ljava/util/zip/CRC32;
    //   72: invokevirtual 279	java/util/zip/CRC32:getValue	()J
    //   75: invokevirtual 282	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   78: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: invokevirtual 286	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeField	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   84: aload 5
    //   86: invokevirtual 289	com/vlingo/sdk/internal/http/custom/MPOutputStream:flush	()V
    //   89: aload_0
    //   90: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   93: invokevirtual 291	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:finishRequest	()V
    //   96: aload_0
    //   97: ldc_w 293
    //   100: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   103: aload_0
    //   104: iconst_1
    //   105: putfield 298	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestFinished	Z
    //   108: aload_0
    //   109: invokestatic 303	java/lang/System:currentTimeMillis	()J
    //   112: putfield 305	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivTimeSendFinish	J
    //   115: goto -97 -> 18
    //   118: astore 4
    //   120: goto -102 -> 18
    //   123: astore_3
    //   124: aload_3
    //   125: athrow
    //   126: astore_1
    //   127: aload_0
    //   128: monitorexit
    //   129: aload_1
    //   130: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   21	115	118	java/lang/Exception
    //   21	115	123	finally
    //   2	14	126	finally
    //   124	126	126	finally
  }

  private String getTimingString()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      localStringBuffer.append("Timing Data:\n\tSend start:\t\t\t\t" + localSimpleDateFormat.format(new Date(this.ivTimeSendStart)));
      localStringBuffer.append("\n\tSend finish:\t\t\t" + localSimpleDateFormat.format(new Date(this.ivTimeSendFinish)));
      localStringBuffer.append("\n\tTime got results:\t\t" + localSimpleDateFormat.format(new Date(this.ivTimeGotResults)));
      localStringBuffer.append("\n\tAudio bytes written:\t" + this.ivAudioBytesWritten);
      label167: return localStringBuffer.toString();
    }
    catch (Exception localException)
    {
      break label167;
    }
  }

  private boolean isErrorState(int paramInt)
  {
    if (paramInt < 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  private void log(String paramString)
  {
  }

  private void notifyListeners(int paramInt)
  {
    Iterator localIterator = this.ivListeners.iterator();
    while (localIterator.hasNext())
    {
      SRRequestListener localSRRequestListener = (SRRequestListener)localIterator.next();
      if (isErrorState(paramInt))
      {
        localSRRequestListener.requestFailed(paramInt);
        continue;
      }
      localSRRequestListener.stateChanged(paramInt);
    }
  }

  private void notifyListeners(SRRecognitionResponse paramSRRecognitionResponse)
  {
    Iterator localIterator = this.ivListeners.iterator();
    while (localIterator.hasNext())
      ((SRRequestListener)localIterator.next()).resultReceived(paramSRRecognitionResponse);
  }

  // ERROR //
  private void receiveResponse()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   4: ifne +10 -> 14
    //   7: aload_0
    //   8: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   11: ifnonnull +4 -> 15
    //   14: return
    //   15: aload_0
    //   16: monitorenter
    //   17: aload_0
    //   18: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   21: invokevirtual 364	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getConnection	()Lcom/vlingo/sdk/internal/http/custom/VHttpConnection;
    //   24: astore 4
    //   26: aload 4
    //   28: invokevirtual 370	com/vlingo/sdk/internal/http/custom/VHttpConnection:getVStreamConnection	()Lcom/vlingo/sdk/internal/http/custom/VStreamConnection;
    //   31: aload 4
    //   33: invokevirtual 374	com/vlingo/sdk/internal/http/custom/VHttpConnection:getHttpInteraction	()Lcom/vlingo/sdk/internal/http/custom/HttpInteraction;
    //   36: invokevirtual 380	com/vlingo/sdk/internal/http/custom/HttpInteraction:getResponse	()Lcom/vlingo/sdk/internal/http/custom/HttpResponse;
    //   39: invokeinterface 386 2 0
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_0
    //   47: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   50: ifne -36 -> 14
    //   53: aload_0
    //   54: monitorenter
    //   55: aload_0
    //   56: iconst_3
    //   57: invokespecial 252	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:notifyListeners	(I)V
    //   60: aload_0
    //   61: ldc_w 388
    //   64: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   67: aload_0
    //   68: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   71: invokevirtual 392	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getIn	()Ljava/io/InputStream;
    //   74: astore 6
    //   76: aload_0
    //   77: ldc_w 394
    //   80: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_0
    //   86: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   89: ifne -75 -> 14
    //   92: aload_0
    //   93: monitorenter
    //   94: aload 6
    //   96: invokestatic 398	com/vlingo/sdk/internal/http/HttpUtil:readData	(Ljava/io/InputStream;)[B
    //   99: astore 8
    //   101: aload_0
    //   102: invokestatic 303	java/lang/System:currentTimeMillis	()J
    //   105: putfield 338	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivTimeGotResults	J
    //   108: aload_0
    //   109: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   112: invokestatic 404	com/vlingo/sdk/internal/http/cookies/CookieHandler:extractCookies	(Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;)Lcom/vlingo/sdk/internal/http/cookies/CookieJar;
    //   115: invokestatic 410	com/vlingo/sdk/internal/vlservice/VLServiceUtil:handleResponseCookies	(Lcom/vlingo/sdk/internal/http/cookies/CookieJar;)V
    //   118: new 175	java/lang/String
    //   121: dup
    //   122: aload 8
    //   124: ldc_w 412
    //   127: invokespecial 415	java/lang/String:<init>	([BLjava/lang/String;)V
    //   130: astore 9
    //   132: aload_0
    //   133: getfield 88	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRManager	Lcom/vlingo/sdk/internal/recognizer/sr3/SR3Manager;
    //   136: invokevirtual 421	com/vlingo/sdk/internal/recognizer/sr3/SR3Manager:getResponseParser	()Lcom/vlingo/sdk/internal/recognizer/results/SRResponseParser;
    //   139: aload 9
    //   141: invokevirtual 427	com/vlingo/sdk/internal/recognizer/results/SRResponseParser:parseResponseXml	(Ljava/lang/String;)Lcom/vlingo/sdk/internal/recognizer/results/SRRecognitionResponse;
    //   144: astore 10
    //   146: aload_0
    //   147: iconst_1
    //   148: putfield 429	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:responseReceived	Z
    //   151: aload_0
    //   152: monitorexit
    //   153: aload_0
    //   154: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   157: ifne -143 -> 14
    //   160: aload_0
    //   161: getfield 88	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRManager	Lcom/vlingo/sdk/internal/recognizer/sr3/SR3Manager;
    //   164: aload 10
    //   166: invokevirtual 434	com/vlingo/sdk/internal/recognizer/results/SRRecognitionResponse:getGUttId	()Ljava/lang/String;
    //   169: invokevirtual 437	com/vlingo/sdk/internal/recognizer/sr3/SR3Manager:setLastGuttID	(Ljava/lang/String;)V
    //   172: aload_0
    //   173: aload 10
    //   175: invokespecial 439	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:notifyListeners	(Lcom/vlingo/sdk/internal/recognizer/results/SRRecognitionResponse;)V
    //   178: aload_0
    //   179: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   182: ifnull -168 -> 14
    //   185: aload_0
    //   186: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   189: invokevirtual 442	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:finishResponse	()V
    //   192: iconst_0
    //   193: invokestatic 248	com/vlingo/sdk/internal/net/ConnectionManager:setTimedOut	(Z)V
    //   196: goto -182 -> 14
    //   199: astore_2
    //   200: aload_0
    //   201: ldc_w 443
    //   204: aload_2
    //   205: invokespecial 445	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:fail	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   208: goto -194 -> 14
    //   211: astore_1
    //   212: aload_1
    //   213: athrow
    //   214: astore_3
    //   215: aload_0
    //   216: monitorexit
    //   217: aload_3
    //   218: athrow
    //   219: astore 5
    //   221: aload_0
    //   222: monitorexit
    //   223: aload 5
    //   225: athrow
    //   226: astore 7
    //   228: aload_0
    //   229: monitorexit
    //   230: aload 7
    //   232: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   15	17	199	java/lang/Exception
    //   46	55	199	java/lang/Exception
    //   85	94	199	java/lang/Exception
    //   153	196	199	java/lang/Exception
    //   217	219	199	java/lang/Exception
    //   223	226	199	java/lang/Exception
    //   230	233	199	java/lang/Exception
    //   15	17	211	finally
    //   46	55	211	finally
    //   85	94	211	finally
    //   153	196	211	finally
    //   200	208	211	finally
    //   217	219	211	finally
    //   223	226	211	finally
    //   230	233	211	finally
    //   17	46	214	finally
    //   215	217	214	finally
    //   55	85	219	finally
    //   221	223	219	finally
    //   94	153	226	finally
    //   228	230	226	finally
  }

  private void recordDetailedTiming(String paramString)
  {
    if (this.ivTimings != null)
      this.ivTimings.recordAndTimeStampEvent(paramString);
  }

  // ERROR //
  private boolean sendAudioSegment(AudioSegment paramAudioSegment)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   6: ifnonnull +5 -> 11
    //   9: iload_2
    //   10: ireturn
    //   11: aload_1
    //   12: getfield 454	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment:length	I
    //   15: ifne +8 -> 23
    //   18: iconst_1
    //   19: istore_2
    //   20: goto -11 -> 9
    //   23: aload_0
    //   24: getfield 456	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:startedWritingAudio	Z
    //   27: ifne +36 -> 63
    //   30: aload_0
    //   31: iconst_1
    //   32: putfield 456	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:startedWritingAudio	Z
    //   35: aload_0
    //   36: invokestatic 303	java/lang/System:currentTimeMillis	()J
    //   39: putfield 325	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivTimeSendStart	J
    //   42: aload_0
    //   43: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   46: invokevirtual 261	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getOut	()Lcom/vlingo/sdk/internal/http/custom/MPOutputStream;
    //   49: ldc_w 458
    //   52: ldc_w 458
    //   55: invokevirtual 462	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeFileFieldHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: aload_0
    //   59: iconst_2
    //   60: invokespecial 252	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:notifyListeners	(I)V
    //   63: aload_0
    //   64: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   67: invokevirtual 261	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getOut	()Lcom/vlingo/sdk/internal/http/custom/MPOutputStream;
    //   70: astore 5
    //   72: aload 5
    //   74: aload_1
    //   75: getfield 465	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment:audio	[B
    //   78: aload_1
    //   79: getfield 468	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment:offset	I
    //   82: aload_1
    //   83: getfield 454	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment:length	I
    //   86: invokevirtual 472	com/vlingo/sdk/internal/http/custom/MPOutputStream:write	([BII)V
    //   89: aload 5
    //   91: invokevirtual 289	com/vlingo/sdk/internal/http/custom/MPOutputStream:flush	()V
    //   94: aload_0
    //   95: new 187	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   102: ldc_w 474
    //   105: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: aload_1
    //   109: getfield 454	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment:length	I
    //   112: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   115: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   118: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   121: aload_0
    //   122: aload_0
    //   123: getfield 342	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivAudioBytesWritten	I
    //   126: aload_1
    //   127: getfield 454	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment:length	I
    //   130: iadd
    //   131: putfield 342	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivAudioBytesWritten	I
    //   134: aload_0
    //   135: getfield 67	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivCRC32	Ljava/util/zip/CRC32;
    //   138: aload_1
    //   139: getfield 465	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment:audio	[B
    //   142: aload_1
    //   143: getfield 468	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment:offset	I
    //   146: aload_1
    //   147: getfield 454	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment:length	I
    //   150: invokevirtual 477	java/util/zip/CRC32:update	([BII)V
    //   153: iconst_1
    //   154: istore_2
    //   155: goto -146 -> 9
    //   158: astore 4
    //   160: goto -151 -> 9
    //   163: astore_3
    //   164: aload_3
    //   165: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   11	153	158	java/lang/Exception
    //   11	153	163	finally
  }

  // ERROR //
  private void sendCancelRequest()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   4: ifnonnull +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield 298	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestFinished	Z
    //   12: ifeq +140 -> 152
    //   15: aload_0
    //   16: getfield 482	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:sentCancelRequest	Z
    //   19: ifne -12 -> 7
    //   22: aload_0
    //   23: getfield 88	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRManager	Lcom/vlingo/sdk/internal/recognizer/sr3/SR3Manager;
    //   26: invokevirtual 486	com/vlingo/sdk/internal/recognizer/sr3/SR3Manager:getServerDetails	()Lcom/vlingo/sdk/internal/recognizer/SRServerDetails;
    //   29: astore 6
    //   31: new 96	java/lang/StringBuffer
    //   34: dup
    //   35: invokespecial 97	java/lang/StringBuffer:<init>	()V
    //   38: astore 7
    //   40: aload 7
    //   42: ldc_w 488
    //   45: invokevirtual 115	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   48: pop
    //   49: aload 7
    //   51: ldc 15
    //   53: new 187	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   60: ldc_w 275
    //   63: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: aload_0
    //   67: getfield 69	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivClientRequestID	I
    //   70: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   73: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: invokestatic 153	com/vlingo/sdk/internal/http/HttpUtil:genAtr	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   79: invokevirtual 115	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   82: pop
    //   83: aload 7
    //   85: ldc 226
    //   87: invokevirtual 115	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   90: pop
    //   91: ldc_w 490
    //   94: new 492	com/vlingo/sdk/internal/http/BaseHttpCallback
    //   97: dup
    //   98: invokespecial 493	com/vlingo/sdk/internal/http/BaseHttpCallback:<init>	()V
    //   101: aload 6
    //   103: invokeinterface 499 1 0
    //   108: aload 7
    //   110: invokevirtual 139	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   113: invokestatic 505	com/vlingo/sdk/internal/vlservice/VLHttpServiceRequest:createVLRequest	(Ljava/lang/String;Lcom/vlingo/sdk/internal/http/HttpCallback;Lcom/vlingo/sdk/internal/http/URL;Ljava/lang/String;)Lcom/vlingo/sdk/internal/vlservice/VLHttpServiceRequest;
    //   116: astore 11
    //   118: aload 11
    //   120: aload_0
    //   121: getfield 84	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivClientMeta	Lcom/vlingo/sdk/internal/recognizer/ClientMeta;
    //   124: invokevirtual 509	com/vlingo/sdk/internal/vlservice/VLHttpServiceRequest:setClientMeta	(Lcom/vlingo/sdk/internal/recognizer/ClientMeta;)V
    //   127: aload 11
    //   129: aload_0
    //   130: getfield 86	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSoftwareMeta	Lcom/vlingo/sdk/internal/recognizer/SoftwareMeta;
    //   133: invokevirtual 513	com/vlingo/sdk/internal/vlservice/VLHttpServiceRequest:setSoftwareMeta	(Lcom/vlingo/sdk/internal/recognizer/SoftwareMeta;)V
    //   136: aload 11
    //   138: lconst_0
    //   139: iconst_1
    //   140: iconst_0
    //   141: invokevirtual 517	com/vlingo/sdk/internal/vlservice/VLHttpServiceRequest:schedule	(JZZ)V
    //   144: aload_0
    //   145: iconst_1
    //   146: putfield 482	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:sentCancelRequest	Z
    //   149: goto -142 -> 7
    //   152: aload_0
    //   153: monitorenter
    //   154: aload_0
    //   155: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   158: ifnonnull +21 -> 179
    //   161: aload_0
    //   162: monitorexit
    //   163: goto -156 -> 7
    //   166: astore_2
    //   167: aload_0
    //   168: monitorexit
    //   169: aload_2
    //   170: athrow
    //   171: astore_1
    //   172: aload_0
    //   173: invokespecial 254	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:closeConnection	()V
    //   176: goto -169 -> 7
    //   179: aload_0
    //   180: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   183: invokevirtual 261	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getOut	()Lcom/vlingo/sdk/internal/http/custom/MPOutputStream;
    //   186: astore_3
    //   187: aload_0
    //   188: getfield 456	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:startedWritingAudio	Z
    //   191: ifeq +47 -> 238
    //   194: aload_3
    //   195: invokevirtual 266	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeEndFieldValue	()V
    //   198: aload_3
    //   199: invokevirtual 269	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeBoundary	()V
    //   202: aload_3
    //   203: ldc_w 271
    //   206: ldc_w 273
    //   209: new 187	java/lang/StringBuilder
    //   212: dup
    //   213: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   216: ldc_w 275
    //   219: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: aload_0
    //   223: getfield 67	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivCRC32	Ljava/util/zip/CRC32;
    //   226: invokevirtual 279	java/util/zip/CRC32:getValue	()J
    //   229: invokevirtual 282	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   232: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: invokevirtual 286	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeField	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   238: aload_3
    //   239: ldc_w 519
    //   242: ldc_w 521
    //   245: ldc_w 523
    //   248: invokevirtual 286	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeField	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   251: aload_3
    //   252: invokevirtual 289	com/vlingo/sdk/internal/http/custom/MPOutputStream:flush	()V
    //   255: aload_0
    //   256: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   259: invokevirtual 291	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:finishRequest	()V
    //   262: aload_0
    //   263: iconst_1
    //   264: putfield 298	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestFinished	Z
    //   267: aload_0
    //   268: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   271: invokevirtual 392	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getIn	()Ljava/io/InputStream;
    //   274: invokestatic 398	com/vlingo/sdk/internal/http/HttpUtil:readData	(Ljava/io/InputStream;)[B
    //   277: pop
    //   278: aload_0
    //   279: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   282: invokevirtual 442	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:finishResponse	()V
    //   285: aload_0
    //   286: monitorexit
    //   287: goto -280 -> 7
    //   290: astore 4
    //   292: goto -14 -> 278
    //
    // Exception table:
    //   from	to	target	type
    //   154	169	166	finally
    //   179	267	166	finally
    //   267	278	166	finally
    //   278	287	166	finally
    //   152	154	171	java/lang/Exception
    //   169	171	171	java/lang/Exception
    //   267	278	290	java/io/IOException
  }

  public void addListener(SRRequestListener paramSRRequestListener)
  {
    this.ivListeners.add(paramSRRequestListener);
  }

  // ERROR //
  public void cancel(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 429	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:responseReceived	Z
    //   6: ifne +36 -> 42
    //   9: aload_0
    //   10: iconst_1
    //   11: putfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   14: iload_1
    //   15: ifeq +7 -> 22
    //   18: iconst_1
    //   19: invokestatic 248	com/vlingo/sdk/internal/net/ConnectionManager:setTimedOut	(Z)V
    //   22: aload_0
    //   23: getfield 531	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestThread	Ljava/lang/Thread;
    //   26: astore_3
    //   27: aload_3
    //   28: ifnull +10 -> 38
    //   31: aload_0
    //   32: getfield 531	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestThread	Ljava/lang/Thread;
    //   35: invokevirtual 536	java/lang/Thread:interrupt	()V
    //   38: aload_0
    //   39: invokevirtual 539	java/lang/Object:notifyAll	()V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_2
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_2
    //   49: athrow
    //   50: astore 4
    //   52: goto -14 -> 38
    //
    // Exception table:
    //   from	to	target	type
    //   2	27	45	finally
    //   31	38	45	finally
    //   38	42	45	finally
    //   31	38	50	java/lang/Exception
  }

  public void finish()
  {
    monitorenter;
    try
    {
      this.allAudioReceived = true;
      notifyAll();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public long getTimeGotResult()
  {
    return this.ivTimeGotResults;
  }

  public long getTimeSendFinish()
  {
    return this.ivTimeSendFinish;
  }

  public long getTimeSendStart()
  {
    return this.ivTimeSendStart;
  }

  public boolean isCancelled()
  {
    monitorenter;
    try
    {
      boolean bool = this.requestCancelled;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isResponseReceived()
  {
    monitorenter;
    try
    {
      boolean bool = this.responseReceived;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void removeListener(SRRequestListener paramSRRequestListener)
  {
    monitorenter;
    try
    {
      this.ivListeners.remove(paramSRRequestListener);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 558	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   4: putfield 531	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestThread	Ljava/lang/Thread;
    //   7: aload_0
    //   8: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   11: istore_3
    //   12: iload_3
    //   13: ifeq +26 -> 39
    //   16: aload_0
    //   17: monitorenter
    //   18: aload_0
    //   19: invokespecial 254	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:closeConnection	()V
    //   22: aload_0
    //   23: aconst_null
    //   24: putfield 531	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestThread	Ljava/lang/Thread;
    //   27: aload_0
    //   28: monitorexit
    //   29: goto +316 -> 345
    //   32: astore 16
    //   34: aload_0
    //   35: monitorexit
    //   36: aload 16
    //   38: athrow
    //   39: aload_0
    //   40: invokevirtual 561	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:startRequest	()V
    //   43: aload_0
    //   44: getfield 90	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:sendAudio	Z
    //   47: ifeq +189 -> 236
    //   50: iconst_0
    //   51: istore 9
    //   53: iconst_0
    //   54: istore 10
    //   56: aload_0
    //   57: monitorenter
    //   58: aload_0
    //   59: getfield 74	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:audioQueue	Ljava/util/Queue;
    //   62: invokeinterface 566 1 0
    //   67: checkcast 10	com/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment
    //   70: astore 12
    //   72: aload 12
    //   74: ifnonnull +25 -> 99
    //   77: aload_0
    //   78: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   81: ifne +18 -> 99
    //   84: aload_0
    //   85: getfield 542	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:allAudioReceived	Z
    //   88: istore 14
    //   90: iload 14
    //   92: ifne +109 -> 201
    //   95: aload_0
    //   96: invokevirtual 569	java/lang/Object:wait	()V
    //   99: aload_0
    //   100: monitorexit
    //   101: aload 12
    //   103: ifnull +22 -> 125
    //   106: aload_0
    //   107: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   110: ifne +15 -> 125
    //   113: aload_0
    //   114: aload 12
    //   116: invokespecial 571	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:sendAudioSegment	(Lcom/vlingo/sdk/internal/recognizer/sr3/SR3Request$AudioSegment;)Z
    //   119: ifne +111 -> 230
    //   122: iconst_1
    //   123: istore 10
    //   125: iload 9
    //   127: ifne +15 -> 142
    //   130: aload_0
    //   131: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   134: ifne +8 -> 142
    //   137: iload 10
    //   139: ifeq -83 -> 56
    //   142: aload_0
    //   143: getfield 456	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:startedWritingAudio	Z
    //   146: ifne +21 -> 167
    //   149: aload_0
    //   150: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   153: ifne +14 -> 167
    //   156: aload_0
    //   157: iconst_1
    //   158: putfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   161: aload_0
    //   162: bipush 254
    //   164: invokespecial 252	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:notifyListeners	(I)V
    //   167: aload_0
    //   168: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   171: ifeq +65 -> 236
    //   174: aload_0
    //   175: invokespecial 573	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:sendCancelRequest	()V
    //   178: aload_0
    //   179: monitorenter
    //   180: aload_0
    //   181: invokespecial 254	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:closeConnection	()V
    //   184: aload_0
    //   185: aconst_null
    //   186: putfield 531	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestThread	Ljava/lang/Thread;
    //   189: aload_0
    //   190: monitorexit
    //   191: goto +154 -> 345
    //   194: astore 13
    //   196: aload_0
    //   197: monitorexit
    //   198: aload 13
    //   200: athrow
    //   201: iconst_1
    //   202: istore 9
    //   204: goto -105 -> 99
    //   207: astore 11
    //   209: aload_0
    //   210: monitorexit
    //   211: aload 11
    //   213: athrow
    //   214: astore_1
    //   215: aload_0
    //   216: monitorenter
    //   217: aload_0
    //   218: invokespecial 254	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:closeConnection	()V
    //   221: aload_0
    //   222: aconst_null
    //   223: putfield 531	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestThread	Ljava/lang/Thread;
    //   226: aload_0
    //   227: monitorexit
    //   228: aload_1
    //   229: athrow
    //   230: iconst_0
    //   231: istore 10
    //   233: goto -108 -> 125
    //   236: aload_0
    //   237: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   240: istore 4
    //   242: iload 4
    //   244: ifeq +26 -> 270
    //   247: aload_0
    //   248: monitorenter
    //   249: aload_0
    //   250: invokespecial 254	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:closeConnection	()V
    //   253: aload_0
    //   254: aconst_null
    //   255: putfield 531	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestThread	Ljava/lang/Thread;
    //   258: aload_0
    //   259: monitorexit
    //   260: goto +85 -> 345
    //   263: astore 8
    //   265: aload_0
    //   266: monitorexit
    //   267: aload 8
    //   269: athrow
    //   270: aload_0
    //   271: invokespecial 574	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:finishRequest	()V
    //   274: aload_0
    //   275: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   278: istore 5
    //   280: iload 5
    //   282: ifeq +26 -> 308
    //   285: aload_0
    //   286: monitorenter
    //   287: aload_0
    //   288: invokespecial 254	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:closeConnection	()V
    //   291: aload_0
    //   292: aconst_null
    //   293: putfield 531	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestThread	Ljava/lang/Thread;
    //   296: aload_0
    //   297: monitorexit
    //   298: goto +47 -> 345
    //   301: astore 7
    //   303: aload_0
    //   304: monitorexit
    //   305: aload 7
    //   307: athrow
    //   308: aload_0
    //   309: invokespecial 576	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:receiveResponse	()V
    //   312: aload_0
    //   313: monitorenter
    //   314: aload_0
    //   315: invokespecial 254	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:closeConnection	()V
    //   318: aload_0
    //   319: aconst_null
    //   320: putfield 531	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestThread	Ljava/lang/Thread;
    //   323: aload_0
    //   324: monitorexit
    //   325: goto +20 -> 345
    //   328: astore 6
    //   330: aload_0
    //   331: monitorexit
    //   332: aload 6
    //   334: athrow
    //   335: astore_2
    //   336: aload_0
    //   337: monitorexit
    //   338: aload_2
    //   339: athrow
    //   340: astore 15
    //   342: goto -243 -> 99
    //   345: return
    //
    // Exception table:
    //   from	to	target	type
    //   18	36	32	finally
    //   180	198	194	finally
    //   58	90	207	finally
    //   95	99	207	finally
    //   99	101	207	finally
    //   209	211	207	finally
    //   0	12	214	finally
    //   39	58	214	finally
    //   106	178	214	finally
    //   211	214	214	finally
    //   236	242	214	finally
    //   270	280	214	finally
    //   308	312	214	finally
    //   249	267	263	finally
    //   287	305	301	finally
    //   314	332	328	finally
    //   217	228	335	finally
    //   336	338	335	finally
    //   95	99	340	java/lang/InterruptedException
  }

  public void sendAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    monitorenter;
    try
    {
      AudioSegment localAudioSegment = new AudioSegment(paramArrayOfByte, paramInt1, paramInt2);
      this.audioQueue.add(localAudioSegment);
      notifyAll();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  void startRequest()
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 581
    //   4: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   7: aload_0
    //   8: iconst_1
    //   9: invokespecial 252	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:notifyListeners	(I)V
    //   12: aload_0
    //   13: monitorenter
    //   14: aload_0
    //   15: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   18: ifeq +6 -> 24
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: aload_0
    //   25: aload_0
    //   26: getfield 88	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRManager	Lcom/vlingo/sdk/internal/recognizer/sr3/SR3Manager;
    //   29: aload_0
    //   30: getfield 82	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivContext	Lcom/vlingo/sdk/internal/recognizer/SRContext;
    //   33: invokevirtual 584	com/vlingo/sdk/internal/recognizer/sr3/SR3Manager:getConnection	(Lcom/vlingo/sdk/internal/recognizer/SRContext;)Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   36: putfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   39: aload_0
    //   40: ldc_w 586
    //   43: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   46: aload_0
    //   47: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   50: ldc_w 588
    //   53: new 187	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   60: ldc_w 590
    //   63: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: aload_0
    //   67: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   70: invokevirtual 593	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getRequestID	()I
    //   73: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   76: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokevirtual 596	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:setRequestHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   82: aload_0
    //   83: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   86: invokevirtual 364	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getConnection	()Lcom/vlingo/sdk/internal/http/custom/VHttpConnection;
    //   89: astore 5
    //   91: aload 5
    //   93: invokevirtual 370	com/vlingo/sdk/internal/http/custom/VHttpConnection:getVStreamConnection	()Lcom/vlingo/sdk/internal/http/custom/VStreamConnection;
    //   96: aload 5
    //   98: invokevirtual 374	com/vlingo/sdk/internal/http/custom/VHttpConnection:getHttpInteraction	()Lcom/vlingo/sdk/internal/http/custom/HttpInteraction;
    //   101: invokevirtual 600	com/vlingo/sdk/internal/http/custom/HttpInteraction:getRequest	()Lcom/vlingo/sdk/internal/http/custom/HttpRequest;
    //   104: invokeinterface 603 2 0
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_0
    //   112: monitorenter
    //   113: aload_0
    //   114: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   117: ifne +10 -> 127
    //   120: aload_0
    //   121: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   124: ifnonnull +48 -> 172
    //   127: aload_0
    //   128: monitorexit
    //   129: goto -106 -> 23
    //   132: astore 6
    //   134: aload_0
    //   135: monitorexit
    //   136: aload 6
    //   138: athrow
    //   139: astore_2
    //   140: aload_0
    //   141: ldc_w 604
    //   144: aload_2
    //   145: invokespecial 445	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:fail	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   148: goto -125 -> 23
    //   151: astore_1
    //   152: aload_1
    //   153: athrow
    //   154: astore 4
    //   156: aload_0
    //   157: bipush 255
    //   159: invokespecial 252	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:notifyListeners	(I)V
    //   162: aload_0
    //   163: monitorexit
    //   164: goto -141 -> 23
    //   167: astore_3
    //   168: aload_0
    //   169: monitorexit
    //   170: aload_3
    //   171: athrow
    //   172: aload_0
    //   173: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   176: invokevirtual 261	com/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter:getOut	()Lcom/vlingo/sdk/internal/http/custom/MPOutputStream;
    //   179: astore 7
    //   181: aload_0
    //   182: ldc_w 606
    //   185: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   188: aload 7
    //   190: invokevirtual 269	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeBoundary	()V
    //   193: aload_0
    //   194: monitorexit
    //   195: aload_0
    //   196: monitorenter
    //   197: aload_0
    //   198: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   201: ifne +10 -> 211
    //   204: aload_0
    //   205: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   208: ifnonnull +15 -> 223
    //   211: aload_0
    //   212: monitorexit
    //   213: goto -190 -> 23
    //   216: astore 8
    //   218: aload_0
    //   219: monitorexit
    //   220: aload 8
    //   222: athrow
    //   223: aload_0
    //   224: getfield 82	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivContext	Lcom/vlingo/sdk/internal/recognizer/SRContext;
    //   227: invokevirtual 103	com/vlingo/sdk/internal/recognizer/SRContext:getEvents	()Ljava/util/List;
    //   230: astore 9
    //   232: aload 9
    //   234: ifnull +44 -> 278
    //   237: aload 9
    //   239: invokeinterface 109 1 0
    //   244: ifle +34 -> 278
    //   247: aload 7
    //   249: ldc_w 608
    //   252: ldc_w 521
    //   255: aload_0
    //   256: invokespecial 610	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:buildEventElement	()Ljava/lang/String;
    //   259: invokestatic 616	com/vlingo/sdk/internal/util/StringUtils:convertStringToBytes	(Ljava/lang/String;)[B
    //   262: iconst_1
    //   263: invokevirtual 620	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeDataField	(Ljava/lang/String;Ljava/lang/String;[BZ)V
    //   266: aload 7
    //   268: invokevirtual 289	com/vlingo/sdk/internal/http/custom/MPOutputStream:flush	()V
    //   271: aload_0
    //   272: ldc_w 622
    //   275: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   278: aload_0
    //   279: monitorexit
    //   280: aload_0
    //   281: monitorenter
    //   282: aload_0
    //   283: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   286: ifne +10 -> 296
    //   289: aload_0
    //   290: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   293: ifnonnull +15 -> 308
    //   296: aload_0
    //   297: monitorexit
    //   298: goto -275 -> 23
    //   301: astore 10
    //   303: aload_0
    //   304: monitorexit
    //   305: aload 10
    //   307: athrow
    //   308: aload_0
    //   309: getfield 82	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivContext	Lcom/vlingo/sdk/internal/recognizer/SRContext;
    //   312: invokevirtual 626	com/vlingo/sdk/internal/recognizer/SRContext:getDialogState	()[B
    //   315: astore 11
    //   317: aload 11
    //   319: ifnull +34 -> 353
    //   322: aload 11
    //   324: arraylength
    //   325: ifle +28 -> 353
    //   328: aload 7
    //   330: ldc_w 628
    //   333: ldc_w 630
    //   336: aload 11
    //   338: invokevirtual 633	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeDataField	(Ljava/lang/String;Ljava/lang/String;[B)V
    //   341: aload 7
    //   343: invokevirtual 289	com/vlingo/sdk/internal/http/custom/MPOutputStream:flush	()V
    //   346: aload_0
    //   347: ldc_w 635
    //   350: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   353: aload_0
    //   354: monitorexit
    //   355: aload_0
    //   356: getfield 82	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivContext	Lcom/vlingo/sdk/internal/recognizer/SRContext;
    //   359: invokevirtual 639	com/vlingo/sdk/internal/recognizer/SRContext:getAudioSourceInfo	()Lcom/vlingo/sdk/recognition/AudioSourceInfo;
    //   362: astore 12
    //   364: aload 12
    //   366: ifnull +54 -> 420
    //   369: aload 12
    //   371: invokevirtual 644	com/vlingo/sdk/recognition/AudioSourceInfo:isString	()Z
    //   374: ifeq +46 -> 420
    //   377: iconst_1
    //   378: istore 13
    //   380: aload_0
    //   381: getfield 90	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:sendAudio	Z
    //   384: ifne +8 -> 392
    //   387: iload 13
    //   389: ifeq +66 -> 455
    //   392: aload_0
    //   393: monitorenter
    //   394: aload_0
    //   395: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   398: ifne +10 -> 408
    //   401: aload_0
    //   402: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   405: ifnonnull +21 -> 426
    //   408: aload_0
    //   409: monitorexit
    //   410: goto -387 -> 23
    //   413: astore 14
    //   415: aload_0
    //   416: monitorexit
    //   417: aload 14
    //   419: athrow
    //   420: iconst_0
    //   421: istore 13
    //   423: goto -43 -> 380
    //   426: aload 7
    //   428: ldc_w 646
    //   431: ldc_w 521
    //   434: aload_0
    //   435: invokespecial 648	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:buildMetaElement	()Ljava/lang/String;
    //   438: invokevirtual 286	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeField	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   441: aload 7
    //   443: invokevirtual 289	com/vlingo/sdk/internal/http/custom/MPOutputStream:flush	()V
    //   446: aload_0
    //   447: ldc_w 650
    //   450: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   453: aload_0
    //   454: monitorexit
    //   455: iload 13
    //   457: ifeq -434 -> 23
    //   460: aload_0
    //   461: monitorenter
    //   462: aload_0
    //   463: getfield 257	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:requestCancelled	Z
    //   466: ifne +10 -> 476
    //   469: aload_0
    //   470: getfield 233	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivSRCon	Lcom/vlingo/sdk/internal/recognizer/sr3/HttpConnectionAdapter;
    //   473: ifnonnull +15 -> 488
    //   476: aload_0
    //   477: monitorexit
    //   478: goto -455 -> 23
    //   481: astore 15
    //   483: aload_0
    //   484: monitorexit
    //   485: aload 15
    //   487: athrow
    //   488: aload_0
    //   489: getfield 82	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivContext	Lcom/vlingo/sdk/internal/recognizer/SRContext;
    //   492: invokevirtual 639	com/vlingo/sdk/internal/recognizer/SRContext:getAudioSourceInfo	()Lcom/vlingo/sdk/recognition/AudioSourceInfo;
    //   495: invokevirtual 653	com/vlingo/sdk/recognition/AudioSourceInfo:getText	()Ljava/lang/String;
    //   498: astore 16
    //   500: aload 7
    //   502: ldc_w 655
    //   505: ldc_w 657
    //   508: aload 16
    //   510: invokevirtual 286	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeField	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   513: aload 7
    //   515: invokevirtual 289	com/vlingo/sdk/internal/http/custom/MPOutputStream:flush	()V
    //   518: aload_0
    //   519: ldc_w 659
    //   522: invokespecial 296	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:recordDetailedTiming	(Ljava/lang/String;)V
    //   525: aload_0
    //   526: getfield 67	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivCRC32	Ljava/util/zip/CRC32;
    //   529: aload 16
    //   531: invokevirtual 662	java/lang/String:getBytes	()[B
    //   534: invokevirtual 665	java/util/zip/CRC32:update	([B)V
    //   537: aload 7
    //   539: ldc_w 271
    //   542: ldc_w 273
    //   545: new 187	java/lang/StringBuilder
    //   548: dup
    //   549: invokespecial 188	java/lang/StringBuilder:<init>	()V
    //   552: ldc_w 275
    //   555: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   558: aload_0
    //   559: getfield 67	com/vlingo/sdk/internal/recognizer/sr3/SR3Request:ivCRC32	Ljava/util/zip/CRC32;
    //   562: invokevirtual 279	java/util/zip/CRC32:getValue	()J
    //   565: invokevirtual 282	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   568: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   571: invokevirtual 286	com/vlingo/sdk/internal/http/custom/MPOutputStream:writeField	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   574: aload 7
    //   576: invokevirtual 289	com/vlingo/sdk/internal/http/custom/MPOutputStream:flush	()V
    //   579: aload_0
    //   580: monitorexit
    //   581: goto -558 -> 23
    //
    // Exception table:
    //   from	to	target	type
    //   113	136	132	finally
    //   172	195	132	finally
    //   7	14	139	java/lang/Exception
    //   111	113	139	java/lang/Exception
    //   136	139	139	java/lang/Exception
    //   170	172	139	java/lang/Exception
    //   195	197	139	java/lang/Exception
    //   220	223	139	java/lang/Exception
    //   280	282	139	java/lang/Exception
    //   305	308	139	java/lang/Exception
    //   355	394	139	java/lang/Exception
    //   417	420	139	java/lang/Exception
    //   460	462	139	java/lang/Exception
    //   485	488	139	java/lang/Exception
    //   7	14	151	finally
    //   111	113	151	finally
    //   136	139	151	finally
    //   140	148	151	finally
    //   170	172	151	finally
    //   195	197	151	finally
    //   220	223	151	finally
    //   280	282	151	finally
    //   305	308	151	finally
    //   355	394	151	finally
    //   417	420	151	finally
    //   460	462	151	finally
    //   485	488	151	finally
    //   24	46	154	java/io/IOException
    //   14	23	167	finally
    //   24	46	167	finally
    //   46	111	167	finally
    //   156	170	167	finally
    //   197	220	216	finally
    //   223	280	216	finally
    //   282	305	301	finally
    //   308	355	301	finally
    //   394	417	413	finally
    //   426	455	413	finally
    //   462	485	481	finally
    //   488	581	481	finally
  }

  private static class AudioSegment
  {
    byte[] audio;
    int length;
    int offset;

    AudioSegment(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      this.audio = paramArrayOfByte;
      this.offset = paramInt1;
      this.length = paramInt2;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.sr3.SR3Request
 * JD-Core Version:    0.6.0
 */