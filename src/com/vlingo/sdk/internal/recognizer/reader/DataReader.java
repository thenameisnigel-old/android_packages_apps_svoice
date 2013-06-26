package com.vlingo.sdk.internal.recognizer.reader;

import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.internal.recognizer.TimingRepository;
import com.vlingo.sdk.internal.util.AutoCloseFileInputStream;
import com.vlingo.sdk.recognition.AudioSourceInfo;
import com.vlingo.sdk.util.SDKDebugSettings;
import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class DataReader
{
  private DataReadyListner mDataReadyListner;
  private String mGuttId;
  private InputStream mInputStream;
  private boolean mIsStopped;
  private DataReaderListener mListener;
  private ReaderThread mReaderThread;
  private SRContext mSrContext;
  private TimingRepository mTimings;
  private int mTotalDuration;

  public DataReader(SRContext paramSRContext, DataReaderListener paramDataReaderListener, DataReadyListner paramDataReadyListner)
  {
    this.mSrContext = paramSRContext;
    this.mListener = paramDataReaderListener;
    this.mDataReadyListner = paramDataReadyListner;
  }

  public static DataReader getDataReader(SRContext paramSRContext, DataReaderListener paramDataReaderListener, DataReadyListner paramDataReadyListner)
  {
    if (paramSRContext.getAudioSourceInfo().isAMR());
    for (Object localObject = new AMRDataReader(paramSRContext, paramDataReaderListener, paramDataReadyListner); ; localObject = new SpeexDataReader(paramSRContext, paramDataReaderListener, paramDataReadyListner))
      return localObject;
  }

  public final void SetTimings(TimingRepository paramTimingRepository)
  {
    this.mTimings = paramTimingRepository;
  }

  protected DataReadyListner getDataReadyListner()
  {
    return this.mDataReadyListner;
  }

  protected InputStream getInputStream()
  {
    return this.mInputStream;
  }

  protected int getMaxDuration()
  {
    return this.mSrContext.getMaxAudioTime();
  }

  protected SRContext getSRContext()
  {
    return this.mSrContext;
  }

  public boolean init()
  {
    boolean bool = false;
    if (this.mTimings != null)
      this.mTimings.recordAndTimeStampEvent("PPRS");
    AudioSourceInfo localAudioSourceInfo = this.mSrContext.getAudioSourceInfo();
    if (localAudioSourceInfo.isFile());
    while (true)
    {
      try
      {
        this.mInputStream = new AutoCloseFileInputStream(localAudioSourceInfo.getFilename());
        SDKDebugSettings localSDKDebugSettings = VLSdk.getInstance().getDebugSettings();
        if ((localSDKDebugSettings == null) || (!localSDKDebugSettings.isLogRecoWaveform()))
          continue;
        this.mInputStream = new InputStreamLogger(this.mInputStream);
        bool = onInit();
        if (this.mTimings == null)
          continue;
        this.mTimings.recordAndTimeStampEvent("PPRE");
        return bool;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        continue;
      }
      if (!localAudioSourceInfo.isStream())
        continue;
      this.mInputStream = localAudioSourceInfo.getInputStream();
    }
  }

  abstract boolean isSpeechDetected();

  protected void onDataAvailable(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.mTotalDuration = paramInt2;
    this.mListener.onDataAvailable(paramArrayOfByte, paramInt1);
  }

  protected abstract void onDeinit();

  protected void onError(DataReaderListener.ErrorCode paramErrorCode, String paramString)
  {
    stop();
    this.mListener.onError(paramErrorCode, paramString);
  }

  protected abstract boolean onInit();

  protected abstract void onProcessData();

  public final void setGuttId(String paramString)
  {
    this.mGuttId = paramString;
  }

  public final void start()
  {
    this.mReaderThread = new ReaderThread();
    this.mReaderThread.start();
  }

  public final void stop()
  {
    this.mIsStopped = true;
  }

  public final void writeLog()
  {
    if ((this.mInputStream instanceof InputStreamLogger))
      ((InputStreamLogger)this.mInputStream).writeLog(this.mGuttId);
  }

  private class ReaderThread extends Thread
  {
    ReaderThread()
    {
      super();
    }

    public void run()
    {
      if (DataReader.this.mTimings != null)
        DataReader.this.mTimings.recordAndTimeStampEvent("STARTED");
      DataReader.this.mListener.onStarted();
      while (!DataReader.this.mIsStopped)
        try
        {
          DataReader.this.onProcessData();
          Thread.sleep(10L);
        }
        catch (InterruptedException localInterruptedException)
        {
        }
        catch (Exception localException2)
        {
          DataReader.this.onError(DataReaderListener.ErrorCode.READ_ERROR, localException2.toString());
        }
      DataReader.this.mListener.onStopped(DataReader.this.mTotalDuration, DataReader.this.isSpeechDetected());
      DataReader.this.onDeinit();
      if ((DataReader.this.mInputStream instanceof AutoCloseFileInputStream));
      try
      {
        DataReader.this.mInputStream.close();
        label138: return;
      }
      catch (Exception localException1)
      {
        break label138;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.reader.DataReader
 * JD-Core Version:    0.6.0
 */