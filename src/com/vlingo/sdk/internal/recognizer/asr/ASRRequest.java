package com.vlingo.sdk.internal.recognizer.asr;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.vlingo.sdk.internal.core.ApplicationAdapter;
import com.vlingo.sdk.internal.http.HttpUtil;
import com.vlingo.sdk.internal.http.cookies.CookieHandler;
import com.vlingo.sdk.internal.http.custom.MPOutputStream;
import com.vlingo.sdk.internal.net.ConnectionManager;
import com.vlingo.sdk.internal.recognizer.ClientMeta;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.internal.recognizer.SRRequest;
import com.vlingo.sdk.internal.recognizer.SRRequestListener;
import com.vlingo.sdk.internal.recognizer.SoftwareMeta;
import com.vlingo.sdk.internal.recognizer.TimingRepository;
import com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse;
import com.vlingo.sdk.internal.recognizer.results.SRResponseParser;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.vlservice.VLServiceUtil;
import com.vlingo.sdk.recognition.AudioSourceInfo;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.zip.CRC32;

public class ASRRequest
  implements SRRequest
{
  public static final String BOUNDRY = "-------------------------------1878979834";
  private static final int HTTP_CHUNK_SIZE = 512;
  private static final int MAX_RETRY_COUNT = 2;
  private boolean isExpectingAudio;
  private boolean isFinished;
  private boolean isTimeoutScheduled;
  private AudioOutputStream mAudioOutputStream = null;
  private final BlockingQueue<AudioSegment> mAudioQueue = new LinkedBlockingQueue();
  private final CRC32 mCRC32 = new CRC32();
  private State mCancelledState = new CancelledState(null);
  private final ClientMeta mClientMeta;
  private int mConnectTimeout = -1;
  private HttpURLConnection mConnection = null;
  private State mDisconnectedState = new DisconnectedState(null);
  private State mFinishedState = new FinishedState(null);
  private Handler mHandler;
  private final CopyOnWriteArrayList<SRRequestListener> mListeners = new CopyOnWriteArrayList();
  private MPOutputStream mMPOutputStream = null;
  private HandlerThread mNetworkThread;
  private int mReadTimeout = -1;
  private State mReceivedState = new ReceivedState(null);
  private State mReceivingState = new ReceivingState(null);
  private int mRequestId = 1;
  private SRRecognitionResponse mResponse = null;
  private final SRContext mSRContext;
  private final ASRManager mSRManager;
  private final SoftwareMeta mSoftwareMeta;
  private State mState = this.mDisconnectedState;
  private State mStreamingState = new StreamingState(null);
  private long mTimeGotResults;
  private long mTimeSendFinish;
  private long mTimeSendStart;
  private final Timer mTimeoutTimer = new Timer("ASRRequest:TimeoutTimer");
  private TimingRepository mTimings;

  ASRRequest(SRContext paramSRContext, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta, ASRManager paramASRManager, TimingRepository paramTimingRepository, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("ctor(): ");
    String str;
    if (paramBoolean)
    {
      str = "with";
      log(str + " audio stream");
      this.mSRContext = paramSRContext;
      this.mClientMeta = paramClientMeta;
      this.mSoftwareMeta = paramSoftwareMeta;
      this.mSRManager = paramASRManager;
      this.isExpectingAudio = paramBoolean;
      this.mTimings = paramTimingRepository;
      this.mDisconnectedState.transitionTo(this.mStreamingState);
      this.mStreamingState.transitionTo(this.mFinishedState);
      this.mFinishedState.transitionTo(this.mReceivingState);
      this.mReceivingState.transitionTo(this.mReceivedState);
      this.mReceivedState.noTransition();
      this.mCancelledState.noTransition();
      if (!paramBoolean)
        break label335;
    }
    label335: for (Object localObject = new AudioOutputStream(); ; localObject = new NoAudioOutputStream(null))
    {
      this.mAudioOutputStream = ((AudioOutputStream)localObject);
      return;
      str = "without";
      break;
    }
  }

  private String buildEventElement()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    List localList = getRequestContext().getEvents();
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
    String str1 = getRequestContext().getCapitalization();
    String str2;
    if (getRequestContext().getAutoPunctuation())
    {
      str2 = "true";
      if (!ApplicationAdapter.getInstance().isAudioStreamingEnabled())
        break label272;
    }
    StringBuffer localStringBuffer;
    label272: for (String str3 = "true"; ; str3 = "false")
    {
      String str4 = getRequestContext().getCurrentText();
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("<Request ");
      localStringBuffer.append(HttpUtil.genAtr("FieldID", getRequestContext().getFieldID()));
      localStringBuffer.append(HttpUtil.genAtr("AppID", this.mSoftwareMeta.getAppId()));
      localStringBuffer.append(HttpUtil.genAtr("FieldType", getRequestContext().getFieldType()));
      localStringBuffer.append(HttpUtil.genAtr("FieldContext", getRequestContext().getFieldContext()));
      if (str4.length() > 0)
      {
        localStringBuffer.append(HttpUtil.genAtr("CurrentText", str4));
        localStringBuffer.append(HttpUtil.genAtr("CursorPosition", getRequestContext().getCursorPosition()));
      }
      for (int i = 1; i <= 6; i++)
      {
        String str5 = getRequestContext().getCustomParam("Custom" + i);
        if (str5.length() <= 0)
          continue;
        localStringBuffer.append(HttpUtil.genAtr("Custom" + i, str5));
      }
      str2 = "false";
      break;
    }
    localStringBuffer.append(HttpUtil.genAtr("StreamingAudio", str3));
    localStringBuffer.append(HttpUtil.genAtr("Punctuate", str2));
    localStringBuffer.append(HttpUtil.genAtr("Capitalize", str1));
    localStringBuffer.append("/>");
    return localStringBuffer.toString();
  }

  private void changeState(State paramState)
  {
    log("setState(" + paramState + ")");
    this.mState = paramState;
  }

  private byte[] consume(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[256];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    while (true)
    {
      int i = getConnection().getInputStream().read(arrayOfByte);
      if (i < 0)
        break;
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    return localByteArrayOutputStream.toByteArray();
  }

  private AudioOutputStream getAudioOutputStream()
  {
    return this.mAudioOutputStream;
  }

  private BlockingQueue<AudioSegment> getAudioQueue()
  {
    return this.mAudioQueue;
  }

  private ClientMeta getClientData()
  {
    return this.mClientMeta;
  }

  private HttpURLConnection getConnection()
  {
    return this.mConnection;
  }

  private MPOutputStream getOutputStream()
  {
    return this.mMPOutputStream;
  }

  private SRContext getRequestContext()
  {
    return this.mSRContext;
  }

  private ASRManager getRequestManager()
  {
    return this.mSRManager;
  }

  private SRRecognitionResponse getResponse()
  {
    return this.mResponse;
  }

  private SoftwareMeta getSoftwareData()
  {
    return this.mSoftwareMeta;
  }

  private State getState()
  {
    return this.mState;
  }

  private String getTimingString()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      localStringBuffer.append("Timing Data:\n\tSend start:\t\t\t\t").append(localSimpleDateFormat.format(new Date(getTimeSendStart()))).append("\n\tSend finish:\t\t\t").append(localSimpleDateFormat.format(new Date(getTimeSendFinish()))).append("\n\tTime got results:\t\t").append(localSimpleDateFormat.format(new Date(getTimeGotResult()))).append("\n\tAudio bytes written:\t").append(getAudioOutputStream().getBytesWritten());
      label112: return localStringBuffer.toString();
    }
    catch (Exception localException)
    {
      break label112;
    }
  }

  private void insertCRCTag(MPOutputStream paramMPOutputStream)
    throws IOException
  {
    paramMPOutputStream.writeField("checksum", "text/crc32", "" + this.mCRC32.getValue());
    paramMPOutputStream.flush();
  }

  private void insertMetaTag(MPOutputStream paramMPOutputStream)
    throws IOException
  {
    paramMPOutputStream.writeField("meta", "text/xml", buildMetaElement());
    paramMPOutputStream.flush();
    recordDetailedTiming("META");
  }

  private void insertTextTag(MPOutputStream paramMPOutputStream)
    throws IOException
  {
    paramMPOutputStream.writeField("text", "text/plain; charset=utf-8", getRequestContext().getAudioSourceInfo().getText());
    paramMPOutputStream.flush();
    recordDetailedTiming("TEXT");
  }

  private boolean isErrorState(int paramInt)
  {
    if (paramInt < 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isExpectingAudio()
  {
    return this.isExpectingAudio;
  }

  private void log(State paramState, String paramString)
  {
  }

  private void log(String paramString)
  {
    log(getState(), paramString);
  }

  private void log(Throwable paramThrowable)
  {
    log("EXCEPTION: " + paramThrowable.toString() + "\n" + Log.getStackTraceString(paramThrowable));
  }

  private void notifyListeners(int paramInt)
  {
    Iterator localIterator = this.mListeners.iterator();
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
    Iterator localIterator = this.mListeners.iterator();
    while (localIterator.hasNext())
      ((SRRequestListener)localIterator.next()).resultReceived(paramSRRecognitionResponse);
  }

  private void onError(int paramInt)
  {
    cancel(false);
    notifyListeners(paramInt);
  }

  private void onTimeout(int paramInt)
  {
    log("onTimeout()");
    ConnectionManager.setTimedOut(true);
    cancel(true);
    notifyListeners(paramInt);
  }

  private void recordDetailedTiming(String paramString)
  {
    if (this.mTimings != null)
      this.mTimings.recordAndTimeStampEvent(paramString);
  }

  private void runCurrentState()
  {
    this.mHandler.post(getState());
  }

  private void setConnection(HttpURLConnection paramHttpURLConnection)
  {
    this.mConnection = paramHttpURLConnection;
  }

  private void setOutputStream(MPOutputStream paramMPOutputStream)
  {
    this.mMPOutputStream = paramMPOutputStream;
  }

  private void setResponse(SRRecognitionResponse paramSRRecognitionResponse)
  {
    this.mResponse = paramSRRecognitionResponse;
    getRequestManager().setLastGuttID(paramSRRecognitionResponse.getGUttId());
  }

  private void startTimeoutTimer()
  {
    log("starting timeout timer [" + getReadTimeout() + " ms]");
    1 local1 = new TimerTask()
    {
      public void run()
      {
        ASRRequest.this.onTimeout(-3);
      }
    };
    this.mTimeoutTimer.schedule(local1, getReadTimeout());
    this.isTimeoutScheduled = true;
  }

  private void stop()
  {
    log("stop(): background processing halted");
    if (this.mNetworkThread != null)
    {
      this.mNetworkThread.quit();
      this.mNetworkThread = null;
    }
    if (this.isTimeoutScheduled)
    {
      this.mTimeoutTimer.cancel();
      this.mTimeoutTimer.purge();
      this.isTimeoutScheduled = false;
    }
  }

  public void addListener(SRRequestListener paramSRRequestListener)
  {
    this.mListeners.add(paramSRRequestListener);
  }

  public void cancel(boolean paramBoolean)
  {
    getState().cancel(paramBoolean);
  }

  public void finish()
  {
    getState().finish();
  }

  public int getConnectTimeout()
  {
    if (this.mConnectTimeout == -1);
    for (int i = ConnectionManager.getOptimalConnectTimeout(); ; i = this.mConnectTimeout)
    {
      this.mConnectTimeout = i;
      return i;
    }
  }

  public int getReadTimeout()
  {
    if (this.mReadTimeout == -1);
    for (int i = ConnectionManager.getOptimalConnectTimeout(); ; i = this.mReadTimeout)
    {
      this.mReadTimeout = i;
      return i;
    }
  }

  public long getTimeGotResult()
  {
    return this.mTimeGotResults;
  }

  public long getTimeSendFinish()
  {
    return this.mTimeSendFinish;
  }

  public long getTimeSendStart()
  {
    return this.mTimeSendStart;
  }

  public boolean isCancelled()
  {
    return getState().isCancelled();
  }

  public boolean isResponseReceived()
  {
    return getState().isResponseReceived();
  }

  public void removeListener(SRRequestListener paramSRRequestListener)
  {
    this.mListeners.remove(paramSRRequestListener);
  }

  public void sendAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    getState().sendAudio(paramArrayOfByte, paramInt1, paramInt2);
  }

  public ASRRequest setConnectTimeout(int paramInt)
  {
    this.mConnectTimeout = paramInt;
    return this;
  }

  public ASRRequest setReadTimeout(int paramInt)
  {
    this.mReadTimeout = paramInt;
    return this;
  }

  public void start()
  {
    log("start()");
    this.mNetworkThread = new HandlerThread("ASRRequest:NetworkThread" + this.mRequestId);
    this.mNetworkThread.start();
    this.mHandler = new Handler(this.mNetworkThread.getLooper());
    runCurrentState();
  }

  private class AudioOutputStream
  {
    private boolean flushOnWrite = false;
    private int mAudioBytesWritten;
    private Boolean mAudioTagSent = Boolean.valueOf(false);
    private boolean mIsOpen = false;
    private MPOutputStream out;

    public AudioOutputStream()
    {
    }

    private void insertAudioTag()
      throws IOException
    {
      if (!this.mAudioTagSent.booleanValue())
      {
        this.mAudioTagSent = Boolean.valueOf(true);
        ASRRequest.access$1202(ASRRequest.this, System.currentTimeMillis());
        ASRRequest.this.notifyListeners(2);
        this.out.writeFileFieldHeader("audio", "audio");
      }
    }

    public AudioOutputStream close()
      throws IOException
    {
      this.mIsOpen = false;
      this.out.writeEndFieldValue();
      this.out.writeBoundary();
      ASRRequest.this.insertCRCTag(this.out);
      ASRRequest.this.recordDetailedTiming("REQD");
      return this;
    }

    public AudioOutputStream flush()
      throws IOException
    {
      this.out.flush();
      return this;
    }

    public int getBytesWritten()
    {
      return this.mAudioBytesWritten;
    }

    public boolean isOpen()
    {
      return this.mIsOpen;
    }

    public AudioOutputStream open(MPOutputStream paramMPOutputStream)
      throws IOException, NullPointerException
    {
      if (paramMPOutputStream == null)
        throw new NullPointerException("you MUST provide an output stream");
      this.out = paramMPOutputStream;
      ASRRequest.this.insertMetaTag(paramMPOutputStream);
      flush();
      this.mIsOpen = true;
      return this;
    }

    public AudioOutputStream setFlushOnWrite(boolean paramBoolean)
    {
      this.flushOnWrite = paramBoolean;
      return this;
    }

    public AudioOutputStream write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if (paramInt2 > 0)
      {
        insertAudioTag();
        this.out.write(paramArrayOfByte, paramInt1, paramInt2);
        ASRRequest.this.recordDetailedTiming("AUD" + paramInt2);
        this.mAudioBytesWritten = (paramInt2 + this.mAudioBytesWritten);
        ASRRequest.this.mCRC32.update(paramArrayOfByte, paramInt1, paramInt2);
        if (this.flushOnWrite)
          flush();
      }
      return this;
    }
  }

  private static class AudioSegment
  {
    byte[] audio;
    boolean endSegment;
    int length;
    int offset;

    AudioSegment(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      this.audio = paramArrayOfByte;
      this.offset = paramInt1;
      this.length = paramInt2;
    }

    public boolean isEndSegment()
    {
      return this.endSegment;
    }

    public void setEndSegment()
    {
      this.endSegment = true;
    }

    public String toString()
    {
      return getClass().getSimpleName() + "(" + this.offset + "," + (this.offset + this.length) + ") [" + this.length + " bytes]";
    }
  }

  private class CancelledState extends ASRRequest.State
  {
    private CancelledState()
    {
      super(null);
    }

    public void cancel(boolean paramBoolean)
    {
      log("cancel(): ignored");
    }

    public boolean endAudio()
    {
      log("endAudio(): ignored");
      return true;
    }

    public void finish()
    {
      log("finish(): ignored");
    }

    public boolean isCancelled()
    {
      return true;
    }

    public void onRun()
      throws IOException
    {
      ASRRequest.this.stop();
      log("clearing queue");
      ASRRequest.this.getAudioQueue().clear();
      if (ASRRequest.this.getAudioOutputStream().isOpen())
      {
        log("sending cancellation to server ...");
        ASRRequest.this.getAudioOutputStream().close();
        ASRRequest.this.getOutputStream().writeField("cancel", "text/xml", "<cancel/>");
        ASRRequest.this.getOutputStream().flush();
        ASRRequest.this.getOutputStream().close();
      }
      try
      {
        byte[] arrayOfByte = ASRRequest.this.consume(ASRRequest.access$2800(ASRRequest.this).getInputStream());
        StringBuilder localStringBuilder = new StringBuilder().append("cancel response = ");
        if (arrayOfByte == null);
        for (String str = ""; ; str = new String(arrayOfByte, "UTF-8"))
        {
          log(str);
          ASRRequest.this.getConnection().disconnect();
          log("cancel complete @ " + ASRRequest.this.getTimingString());
          return;
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          log(localIOException);
          ASRRequest.this.getConnection().disconnect();
        }
      }
      finally
      {
        ASRRequest.this.getConnection().disconnect();
      }
      throw localObject;
    }

    public void sendAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      log("sendAudio(): ignored");
    }

    public void setState(ASRRequest.State paramState)
    {
      log("setState(" + paramState + "): ignored");
    }
  }

  private static class Connection
  {
    public static String describe(HttpURLConnection paramHttpURLConnection)
    {
      return paramHttpURLConnection.getClass().getSimpleName() + "(" + quoted(paramHttpURLConnection.getURL()) + "," + paramHttpURLConnection.getConnectTimeout() + "," + paramHttpURLConnection.getReadTimeout() + ")";
    }

    public static String quoted(Object paramObject)
    {
      return "\"" + paramObject.toString() + "\"";
    }
  }

  private class DisconnectedState extends ASRRequest.State
  {
    private DisconnectedState()
    {
      super(null);
    }

    public void onRun()
      throws IOException
    {
      int i = 0;
      ASRRequest.this.recordDetailedTiming("RUN");
      ASRRequest.this.recordDetailedTiming("OPEN");
      ASRRequest.this.notifyListeners(1);
      int j = 1;
      while ((i < 2) && (j != 0))
      {
        log("connecting to asr service");
        ASRRequest.this.setConnection(ASRRequest.access$2600(ASRRequest.this).getConnection(ASRRequest.access$2500(ASRRequest.this)));
        ASRRequest.this.getConnection().setDoInput(true);
        ASRRequest.this.getConnection().setDoOutput(true);
        ASRRequest.this.getConnection().setUseCaches(false);
        ASRRequest.this.getConnection().setReadTimeout(ASRRequest.this.getReadTimeout());
        ASRRequest.this.getConnection().setConnectTimeout(ASRRequest.this.getConnectTimeout());
        ASRRequest.this.getConnection().setRequestProperty("X-vlrequest", "ClientRequestID:" + ASRRequest.access$2908(ASRRequest.this));
        if (!Settings.isAsrKeepAliveEnabled())
          ASRRequest.this.getConnection().setRequestProperty("Connection", "CLOSE");
        Hashtable localHashtable1 = new Hashtable();
        Hashtable localHashtable2 = new Hashtable();
        VLServiceUtil.addStandardVlingoHttpHeaders(localHashtable1, ASRRequest.this.getClientData(), ASRRequest.this.getSoftwareData(), ASRRequest.this.getRequestContext());
        VLServiceUtil.addVLServiceCookies(localHashtable2, ASRRequest.this.getConnection().getURL().getHost(), ASRRequest.this.getConnection().getURL().getPath());
        Iterator localIterator = localHashtable1.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          ASRRequest.this.getConnection().setRequestProperty((String)localEntry.getKey(), (String)localEntry.getValue());
        }
        String str1 = HttpUtil.getCookies(localHashtable2);
        if (str1.length() > 0)
          ASRRequest.this.getConnection().setRequestProperty("Cookie", str1);
        ASRRequest.this.getConnection().setChunkedStreamingMode(512);
        ASRRequest.this.recordDetailedTiming("HDRS");
        log("connecting to " + ASRRequest.Connection.describe(ASRRequest.this.getConnection()));
        try
        {
          ASRRequest.this.setOutputStream(new MPOutputStream(new DataOutputStream(ASRRequest.this.getConnection().getOutputStream()), "-------------------------------1878979834"));
          ASRRequest.this.getOutputStream().writeBoundary();
          List localList = ASRRequest.this.getRequestContext().getEvents();
          if ((localList != null) && (localList.size() > 0))
          {
            String str2 = ASRRequest.this.buildEventElement();
            ASRRequest.this.getOutputStream().writeDataField("events", "text/xml", StringUtils.convertStringToBytes(str2), true);
            ASRRequest.this.getOutputStream().flush();
            ASRRequest.this.recordDetailedTiming("DMEV");
          }
          byte[] arrayOfByte = ASRRequest.this.getRequestContext().getDialogState();
          if ((arrayOfByte != null) && (arrayOfByte.length > 0))
          {
            ASRRequest.this.getOutputStream().writeDataField("dialog-data", "binary", arrayOfByte);
            ASRRequest.this.getOutputStream().flush();
            ASRRequest.this.recordDetailedTiming("DMST");
          }
          AudioSourceInfo localAudioSourceInfo = ASRRequest.this.getRequestContext().getAudioSourceInfo();
          if ((localAudioSourceInfo != null) && (localAudioSourceInfo.isString()))
          {
            ASRRequest.this.mCRC32.update(localAudioSourceInfo.getText().getBytes());
            ASRRequest.this.insertMetaTag(ASRRequest.access$3300(ASRRequest.this));
            ASRRequest.this.insertTextTag(ASRRequest.access$3300(ASRRequest.this));
            ASRRequest.this.insertCRCTag(ASRRequest.access$3300(ASRRequest.this));
          }
          while (true)
          {
            log("connected");
            j = 0;
            break;
            ASRRequest.this.getAudioOutputStream().open(ASRRequest.this.getOutputStream()).setFlushOnWrite(true);
          }
        }
        catch (SocketTimeoutException localSocketTimeoutException)
        {
          log("connection TIMED OUT");
          ASRRequest.this.onTimeout(-1);
          j = 0;
        }
        catch (IOException localIOException)
        {
          log("connection FAILED: " + localIOException);
          j = 1;
          ASRRequest.this.mCRC32.reset();
          i++;
        }
      }
      if (j != 0)
        ASRRequest.this.onError(-1);
    }
  }

  private class FinishedState extends ASRRequest.State
  {
    private FinishedState()
    {
      super(null);
    }

    public void finish()
    {
      log("finish(): ignored");
    }

    public void onRun()
      throws IOException
    {
      log("audio stream complete");
      ASRRequest.this.getAudioOutputStream().close();
    }

    public void sendAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      log("sendAudio(): ignored");
    }
  }

  private class NoAudioOutputStream extends ASRRequest.AudioOutputStream
  {
    private NoAudioOutputStream()
    {
      super();
    }

    public ASRRequest.AudioOutputStream close()
    {
      return this;
    }

    public ASRRequest.AudioOutputStream flush()
    {
      return this;
    }

    public ASRRequest.AudioOutputStream open(MPOutputStream paramMPOutputStream)
    {
      return this;
    }

    public ASRRequest.AudioOutputStream write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      return this;
    }
  }

  private class ReceivedState extends ASRRequest.State
  {
    private ReceivedState()
    {
      super(null);
    }

    public void cancel(boolean paramBoolean)
    {
      log("cancel(): ignored");
    }

    public void finish()
    {
      log("finish(): ignored");
    }

    public boolean isResponseReceived()
    {
      return true;
    }

    public void onRun()
      throws IOException
    {
      ASRRequest.this.stop();
      log("response = " + ASRRequest.this.getResponse());
      ConnectionManager.setTimedOut(false);
      log("closing connection");
      ASRRequest.this.getOutputStream().close();
      ASRRequest.this.getConnection().disconnect();
      ASRRequest.this.notifyListeners(ASRRequest.access$4200(ASRRequest.this));
      log("done at " + ASRRequest.this.getTimingString());
    }

    public void sendAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      log("sendAudio(): ignored");
    }

    public void setState(ASRRequest.State paramState)
    {
      log("setState(" + paramState + "): ignored");
    }
  }

  private class ReceivingState extends ASRRequest.State
  {
    private ReceivingState()
    {
      super(null);
    }

    public void onRun()
      throws IOException
    {
      log("receiving ...");
      ASRRequest.this.notifyListeners(3);
      ASRRequest.this.recordDetailedTiming("RESP");
      ASRRequest.this.recordDetailedTiming("RESH");
      try
      {
        ASRRequest.access$3802(ASRRequest.this, System.currentTimeMillis());
        VLServiceUtil.handleResponseCookies(CookieHandler.extractCookies(ASRRequest.this.getConnection()));
        SRResponseParser localSRResponseParser = new SRResponseParser();
        ASRRequest.this.setResponse(localSRResponseParser.parseResponseXml(new String(ASRRequest.this.consume(ASRRequest.access$2800(ASRRequest.this).getInputStream()), "UTF-8")));
        return;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        while (true)
        {
          log("response TIMED OUT");
          ASRRequest.this.onTimeout(-3);
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          log(localIOException);
          ASRRequest.this.onError(-2);
        }
      }
    }

    public void sendAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      log("sendAudio()");
    }
  }

  private class State
    implements Runnable
  {
    private State mNextState;

    private State()
    {
    }

    public void cancel(boolean paramBoolean)
    {
      StringBuilder localStringBuilder = new StringBuilder().append("cancel(): ");
      if (paramBoolean);
      for (String str = "due"; ; str = "not due")
      {
        log(str + " to timeout");
        setState(ASRRequest.this.mCancelledState);
        endAudio();
        return;
      }
    }

    public boolean endAudio()
    {
      ASRRequest.AudioSegment localAudioSegment = new ASRRequest.AudioSegment(null, 0, 0);
      localAudioSegment.setEndSegment();
      log("endAudio(): queuing end-of-audio segment");
      boolean bool = ASRRequest.this.getAudioQueue().offer(localAudioSegment);
      if (!bool)
        log("Unable to add end-of-audio AudioSegment to audio queue (queue full?)");
      return bool;
    }

    public void finish()
    {
      log("finish()");
      if (!ASRRequest.this.isFinished)
      {
        ASRRequest.access$1602(ASRRequest.this, true);
        ASRRequest.this.startTimeoutTimer();
        if (!endAudio())
          ASRRequest.this.onError(-2);
      }
    }

    protected boolean hasTransition()
    {
      if (this.mNextState != null);
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean isCancelled()
    {
      return false;
    }

    public boolean isResponseReceived()
    {
      return false;
    }

    protected void log(String paramString)
    {
      ASRRequest.this.log(this, paramString);
    }

    protected void log(Throwable paramThrowable)
    {
      ASRRequest.this.log(paramThrowable);
    }

    public void noTransition()
    {
      this.mNextState = null;
    }

    protected void onRun()
      throws IOException
    {
    }

    public final void run()
    {
      try
      {
        onRun();
        transition();
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          ASRRequest.this.onError(-2);
      }
    }

    public void sendAudio(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      ASRRequest.AudioSegment localAudioSegment = new ASRRequest.AudioSegment(paramArrayOfByte, paramInt1, paramInt2);
      log("sendAudio(): queuing " + localAudioSegment);
      if (!ASRRequest.this.getAudioQueue().offer(localAudioSegment))
      {
        log("Unable to add AudioSegment to audio queue (queue full?)");
        ASRRequest.this.onError(-2);
      }
    }

    protected void setState(State paramState)
    {
      ASRRequest.this.changeState(paramState);
    }

    public String toString()
    {
      return getClass().getSimpleName();
    }

    protected void transition()
    {
      if (hasTransition())
      {
        ASRRequest.this.getState().setState(this.mNextState);
        ASRRequest.this.runCurrentState();
      }
    }

    public void transitionTo(State paramState)
    {
      this.mNextState = paramState;
    }
  }

  private class StreamingState extends ASRRequest.State
  {
    private StreamingState()
    {
      super(null);
    }

    public void onRun()
      throws IOException
    {
      if (!ASRRequest.this.isExpectingAudio())
        log("no audio excpected, transitioning ...");
      while (true)
      {
        return;
        try
        {
          boolean bool;
          do
          {
            log("streaming " + localObject);
            ASRRequest.this.getAudioOutputStream().write(((ASRRequest.AudioSegment)localObject).audio, ((ASRRequest.AudioSegment)localObject).offset, ((ASRRequest.AudioSegment)localObject).length);
            if (ASRRequest.this.getState().isCancelled())
              break;
            Object localObject = (ASRRequest.AudioSegment)ASRRequest.this.getAudioQueue().take();
            bool = ((ASRRequest.AudioSegment)localObject).isEndSegment();
          }
          while (!bool);
        }
        catch (InterruptedException localInterruptedException)
        {
          log(localInterruptedException);
          ASRRequest.this.onError(-2);
        }
        catch (SocketTimeoutException localSocketTimeoutException)
        {
          log("streaming TIMED OUT");
          ASRRequest.this.onTimeout(-3);
        }
        catch (IOException localIOException)
        {
          log(localIOException);
          ASRRequest.this.onError(-2);
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.asr.ASRRequest
 * JD-Core Version:    0.6.0
 */