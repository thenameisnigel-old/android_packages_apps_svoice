package com.vlingo.sdk.internal.recognizer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import com.vlingo.sdk.internal.AndroidServerDetails;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.http.HttpManager;
import com.vlingo.sdk.internal.net.ConnectionManager;
import com.vlingo.sdk.internal.recognizer.reader.DataReader;
import com.vlingo.sdk.internal.recognizer.reader.DataReaderListener;
import com.vlingo.sdk.internal.recognizer.reader.DataReaderListener.ErrorCode;
import com.vlingo.sdk.internal.recognizer.reader.DataReadyListner;
import com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.recognition.AudioSourceInfo;

public class Recognizer
{
  private static final int MIN_AUDIO_DURATION = 500;
  private String lastGuttID = null;
  private int mBeginStopDelta;
  private DataReader mDataReader;
  private DataReaderListener mDataReaderListener;
  private int mEndStopDelta;
  private int mGotResultDelta;
  private RecognizerListener mListener;
  private int mParseResultDelta;
  private SRRequest mRequest;
  private SRRequestListener mRequestListener;
  private SRManager mSRManager = SRManager.create(ConnectionManager.getInstance(), this.mTimings);
  private int mSendFinishDelta;
  private int mSendStartDelta;
  private int mStartDelta;
  private long mStartTime;
  private TimingRepository mTimings = new TimingRepository();
  private int mUttBytes;

  public Recognizer()
  {
    this.mSRManager.init(new AndroidServerDetails(), ClientMeta.getInstance(), SoftwareMeta.getInstance());
  }

  private void cleanup()
  {
    HttpManager.getInstance().resume();
    if (this.mRequest != null)
    {
      this.mRequest.removeListener(this.mRequestListener);
      this.mRequest.cancel(false);
      this.mRequest = null;
    }
    if (this.mDataReader != null)
    {
      this.mDataReader.stop();
      this.mDataReader.writeLog();
      this.mDataReader = null;
    }
    this.mRequestListener = null;
    this.mDataReaderListener = null;
    this.mListener = null;
  }

  private void handleError(RecognizerListener.RecognizerError paramRecognizerError, String paramString)
  {
    this.mListener.onRecognizerError(paramRecognizerError, paramString);
    cleanup();
  }

  private void handleResponse(SRRecognitionResponse paramSRRecognitionResponse)
  {
    String str = paramSRRecognitionResponse.getGUttId();
    if (this.mDataReader != null)
      this.mDataReader.setGuttId(str);
    if ((!paramSRRecognitionResponse.hasActions()) && (!paramSRRecognitionResponse.hasResults()) && (!paramSRRecognitionResponse.hasMessages()) && (!paramSRRecognitionResponse.hasDialogState()))
      this.mListener.onRecognizerError(RecognizerListener.RecognizerError.NO_RESULTS, "No result. Please try again.");
    while (true)
    {
      cleanup();
      if (!StringUtils.isNullOrWhiteSpace(str))
        sendRecTiming(str);
      return;
      this.mListener.onRecognitionResponse(paramSRRecognitionResponse);
    }
  }

  private void handleStateChange(RecognizerListener.RecognizerState paramRecognizerState, Object paramObject)
  {
    if (paramRecognizerState == RecognizerListener.RecognizerState.LISTENING)
      HttpManager.getInstance().pause();
    this.mListener.onRecognizerStateChanged(paramRecognizerState, paramObject);
  }

  private boolean prepareStart(SRContext paramSRContext, boolean paramBoolean, RecognizerListener paramRecognizerListener)
  {
    int i = 0;
    if (paramRecognizerListener == null)
      throw new IllegalArgumentException("listener is null");
    if (this.mListener != null)
      throw new IllegalStateException("Recognizer is busy");
    this.mListener = paramRecognizerListener;
    this.mTimings.clear();
    this.mTimings.markTimeZero();
    this.mStartTime = System.currentTimeMillis();
    this.mStartDelta = -1;
    this.mBeginStopDelta = -1;
    this.mEndStopDelta = -1;
    this.mSendStartDelta = -1;
    this.mSendFinishDelta = -1;
    this.mGotResultDelta = -1;
    this.mParseResultDelta = -1;
    this.mUttBytes = 0;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (localNetworkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTED))
      handleError(RecognizerListener.RecognizerError.FAIL_CONNECT, "Network not available");
    while (true)
    {
      return i;
      this.mRequestListener = new SRRequestListenerImpl(null);
      this.mRequest = this.mSRManager.newRequest(paramSRContext, this.mRequestListener, paramBoolean);
      this.mTimings.recordAndTimeStampEvent("RSC");
      i = 1;
    }
  }

  private void sendRecTiming(String paramString)
  {
    SRStatistics localSRStatistics = new SRStatistics(paramString, SRStatistics.TYPE_REC_TIMING);
    localSRStatistics.addStatistic("BOR", "" + this.mStartDelta);
    localSRStatistics.addStatistic("EOS", "" + this.mBeginStopDelta);
    localSRStatistics.addStatistic("EOR", "" + this.mEndStopDelta);
    localSRStatistics.addStatistic("Custom1", "" + this.mSendStartDelta);
    localSRStatistics.addStatistic("EOD", "" + this.mSendFinishDelta);
    localSRStatistics.addStatistic("SED", "-1");
    localSRStatistics.addStatistic("RES", "" + this.mGotResultDelta);
    localSRStatistics.addStatistic("PAR", "" + this.mParseResultDelta);
    localSRStatistics.addStatistic("UTT", "" + this.mUttBytes);
    localSRStatistics.addStatistic("Custom2", this.mTimings.getStatString());
    this.mSRManager.sendStatistics(localSRStatistics);
  }

  public void cancel()
  {
    monitorenter;
    try
    {
      cleanup();
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

  public void destroy()
  {
    monitorenter;
    try
    {
      cleanup();
      this.mSRManager.destroy();
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

  public void sendAcceptedText(String paramString, SRStatisticsCollection paramSRStatisticsCollection)
  {
    monitorenter;
    try
    {
      if ((paramSRStatisticsCollection.getAcceptedText() != null) && (paramString != null) && ((this.lastGuttID == null) || (!paramString.equals(this.lastGuttID))))
      {
        SRStatistics localSRStatistics = new SRStatistics(paramString, SRStatistics.TYPE_ACCEPTED_TEXT);
        localSRStatistics.addStatistic(SRStatistics.STAT_ACCEPTED_TEXT, paramSRStatisticsCollection.getAcceptedText());
        paramSRStatisticsCollection.addStatistics(localSRStatistics);
        this.lastGuttID = paramString;
      }
      this.mSRManager.sendStatsCollection(paramSRStatisticsCollection);
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

  public void startRecognition(SRContext paramSRContext, RecognizerListener paramRecognizerListener, DataReadyListner paramDataReadyListner)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (paramSRContext.getAudioSourceInfo().isString())
        {
          boolean bool = prepareStart(paramSRContext, false, paramRecognizerListener);
          if (!bool)
            return;
          this.mStartDelta = (int)(System.currentTimeMillis() - this.mStartTime);
          handleStateChange(RecognizerListener.RecognizerState.THINKING, null);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (!prepareStart(paramSRContext, true, paramRecognizerListener))
        continue;
      this.mDataReaderListener = new DataReaderListenerImpl(null);
      this.mDataReader = DataReader.getDataReader(paramSRContext, this.mDataReaderListener, paramDataReadyListner);
      this.mDataReader.SetTimings(this.mTimings);
      if (!this.mDataReader.init())
      {
        handleError(RecognizerListener.RecognizerError.READER_ERROR, "Error initializing reader");
        continue;
      }
      this.mDataReader.start();
    }
  }

  public void startSendEvent(SRContext paramSRContext, RecognizerListener paramRecognizerListener)
  {
    monitorenter;
    try
    {
      boolean bool = prepareStart(paramSRContext, false, paramRecognizerListener);
      if (!bool);
      while (true)
      {
        return;
        this.mStartDelta = (int)(System.currentTimeMillis() - this.mStartTime);
        handleStateChange(RecognizerListener.RecognizerState.THINKING, null);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void stop()
  {
    monitorenter;
    try
    {
      this.mBeginStopDelta = (int)(System.currentTimeMillis() - this.mStartTime);
      if (this.mDataReader != null)
        this.mDataReader.stop();
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

  private class DataReaderListenerImpl
    implements DataReaderListener
  {
    private DataReaderListenerImpl()
    {
    }

    public void onDataAvailable(byte[] paramArrayOfByte, int paramInt)
    {
      synchronized (Recognizer.this)
      {
        if (this == Recognizer.this.mDataReaderListener)
        {
          if (paramInt > -1)
            Recognizer.this.handleStateChange(RecognizerListener.RecognizerState.RMS_CHANGED, Integer.valueOf(paramInt));
          Recognizer.this.mRequest.sendAudio(paramArrayOfByte, 0, paramArrayOfByte.length);
          Recognizer.access$1612(Recognizer.this, paramArrayOfByte.length);
        }
      }
    }

    public void onError(DataReaderListener.ErrorCode paramErrorCode, String paramString)
    {
      synchronized (Recognizer.this)
      {
        if (this == Recognizer.this.mDataReaderListener)
          Recognizer.this.handleError(RecognizerListener.RecognizerError.READER_ERROR, paramString);
      }
    }

    public void onStarted()
    {
      synchronized (Recognizer.this)
      {
        if (this == Recognizer.this.mDataReaderListener)
        {
          Recognizer.access$1302(Recognizer.this, (int)(System.currentTimeMillis() - Recognizer.this.mStartTime));
          Recognizer.this.handleStateChange(RecognizerListener.RecognizerState.LISTENING, null);
        }
      }
    }

    // ERROR //
    public void onStopped(int paramInt, boolean paramBoolean)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   4: astore_3
      //   5: aload_3
      //   6: monitorenter
      //   7: aload_0
      //   8: aload_0
      //   9: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   12: invokestatic 27	com/vlingo/sdk/internal/recognizer/Recognizer:access$1200	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;)Lcom/vlingo/sdk/internal/recognizer/reader/DataReaderListener;
      //   15: if_acmpeq +8 -> 23
      //   18: aload_3
      //   19: monitorexit
      //   20: goto +132 -> 152
      //   23: aload_0
      //   24: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   27: invokestatic 76	java/lang/System:currentTimeMillis	()J
      //   30: aload_0
      //   31: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   34: invokestatic 80	com/vlingo/sdk/internal/recognizer/Recognizer:access$500	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;)J
      //   37: lsub
      //   38: l2i
      //   39: invokestatic 91	com/vlingo/sdk/internal/recognizer/Recognizer:access$1402	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;I)I
      //   42: pop
      //   43: aload_0
      //   44: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   47: invokestatic 95	com/vlingo/sdk/internal/recognizer/Recognizer:access$1500	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;)I
      //   50: bipush 255
      //   52: if_icmpne +18 -> 70
      //   55: aload_0
      //   56: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   59: aload_0
      //   60: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   63: invokestatic 98	com/vlingo/sdk/internal/recognizer/Recognizer:access$1400	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;)I
      //   66: invokestatic 101	com/vlingo/sdk/internal/recognizer/Recognizer:access$1502	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;I)I
      //   69: pop
      //   70: iload_2
      //   71: ifne +27 -> 98
      //   74: aload_0
      //   75: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   78: getstatic 104	com/vlingo/sdk/internal/recognizer/RecognizerListener$RecognizerError:NO_SPEECH	Lcom/vlingo/sdk/internal/recognizer/RecognizerListener$RecognizerError;
      //   81: ldc 106
      //   83: invokestatic 69	com/vlingo/sdk/internal/recognizer/Recognizer:access$1000	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;Lcom/vlingo/sdk/internal/recognizer/RecognizerListener$RecognizerError;Ljava/lang/String;)V
      //   86: aload_3
      //   87: monitorexit
      //   88: goto +64 -> 152
      //   91: astore 4
      //   93: aload_3
      //   94: monitorexit
      //   95: aload 4
      //   97: athrow
      //   98: iload_1
      //   99: bipush 255
      //   101: if_icmple +25 -> 126
      //   104: iload_1
      //   105: sipush 500
      //   108: if_icmpge +18 -> 126
      //   111: aload_0
      //   112: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   115: getstatic 109	com/vlingo/sdk/internal/recognizer/RecognizerListener$RecognizerError:TOO_SHORT	Lcom/vlingo/sdk/internal/recognizer/RecognizerListener$RecognizerError;
      //   118: ldc 111
      //   120: invokestatic 69	com/vlingo/sdk/internal/recognizer/Recognizer:access$1000	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;Lcom/vlingo/sdk/internal/recognizer/RecognizerListener$RecognizerError;Ljava/lang/String;)V
      //   123: goto -37 -> 86
      //   126: aload_0
      //   127: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   130: getstatic 114	com/vlingo/sdk/internal/recognizer/RecognizerListener$RecognizerState:THINKING	Lcom/vlingo/sdk/internal/recognizer/RecognizerListener$RecognizerState;
      //   133: aconst_null
      //   134: invokestatic 43	com/vlingo/sdk/internal/recognizer/Recognizer:access$1100	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;Lcom/vlingo/sdk/internal/recognizer/RecognizerListener$RecognizerState;Ljava/lang/Object;)V
      //   137: aload_0
      //   138: getfield 15	com/vlingo/sdk/internal/recognizer/Recognizer$DataReaderListenerImpl:this$0	Lcom/vlingo/sdk/internal/recognizer/Recognizer;
      //   141: invokestatic 47	com/vlingo/sdk/internal/recognizer/Recognizer:access$400	(Lcom/vlingo/sdk/internal/recognizer/Recognizer;)Lcom/vlingo/sdk/internal/recognizer/SRRequest;
      //   144: invokeinterface 117 1 0
      //   149: goto -63 -> 86
      //   152: return
      //
      // Exception table:
      //   from	to	target	type
      //   7	95	91	finally
      //   111	149	91	finally
    }
  }

  private class SRRequestListenerImpl
    implements SRRequestListener
  {
    private SRRequestListenerImpl()
    {
    }

    public void requestFailed(int paramInt)
    {
      synchronized (Recognizer.this)
      {
        if (this == Recognizer.this.mRequestListener)
          Recognizer.this.handleError(RecognizerListener.RecognizerError.FAIL_CONNECT, "Network error");
      }
    }

    public void resultReceived(SRRecognitionResponse paramSRRecognitionResponse)
    {
      synchronized (Recognizer.this)
      {
        if (this == Recognizer.this.mRequestListener)
        {
          Recognizer.access$302(Recognizer.this, (int)(Recognizer.this.mRequest.getTimeSendStart() - Recognizer.this.mStartTime));
          Recognizer.access$602(Recognizer.this, (int)(Recognizer.this.mRequest.getTimeSendFinish() - Recognizer.this.mStartTime));
          Recognizer.access$702(Recognizer.this, (int)(Recognizer.this.mRequest.getTimeGotResult() - Recognizer.this.mStartTime));
          Recognizer.access$802(Recognizer.this, (int)(System.currentTimeMillis() - Recognizer.this.mStartTime));
          Recognizer.this.handleResponse(paramSRRecognitionResponse);
        }
      }
    }

    public void stateChanged(int paramInt)
    {
      label75: synchronized (Recognizer.this)
      {
        if (this != Recognizer.this.mRequestListener)
          break label75;
      }
      switch (paramInt)
      {
      default:
      case 1:
      case 2:
      case 3:
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.Recognizer
 * JD-Core Version:    0.6.0
 */