package com.vlingo.sdk.internal.recognizer.reader;

import com.vlingo.sdk.internal.audio.RawInputStreamReader;
import com.vlingo.sdk.internal.audio.SpeexJNI;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.recognition.AudioSourceInfo;

public class SpeexDataReader extends DataReader
{
  private static final int MAX_CODEC_OUTPUT_CHUNK = 1000;
  private static final float MIN_SPEECH_TIME = 0.5F;
  private static final int SPEEX_NB = 0;
  private static final int SPEEX_WB = 1;
  private static final int USE_SILENCE_DETECTION = 1;
  private boolean mAutoEndpointingEnabled;
  private float mAutoEndpointingTimeWithSpeech;
  private float mAutoEndpointingTimeWithoutSpeech;
  private RawInputStreamReader mRawInputStreamReader;
  private int mSampleRate;
  private boolean mSpeechDetected;
  private SpeexJNI mSpeex;
  private short[] mSpeexInputBuffer;
  private int mSpeexInputBufferSamples;
  private byte[] mSpeexOutputBuffer = new byte[1000];
  private int mTotalMillisecProcessed;
  private int mTotalSamplesProcessed;

  SpeexDataReader(SRContext paramSRContext, DataReaderListener paramDataReaderListener, DataReadyListner paramDataReadyListner)
  {
    super(paramSRContext, paramDataReaderListener, paramDataReadyListner);
  }

  private int convertBytesToInt(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    int j = 0;
    for (int k = paramInt; k < paramInt + 4; k++)
    {
      j |= (0xFF & paramArrayOfByte[k]) << i;
      i += 8;
    }
    return j;
  }

  protected boolean isSpeechDetected()
  {
    return this.mSpeechDetected;
  }

  protected void onDeinit()
  {
    this.mSpeex.encode(this.mSpeexInputBuffer, this.mSpeexOutputBuffer, 1000, 1);
    this.mSpeex = null;
    this.mRawInputStreamReader = null;
  }

  protected boolean onInit()
  {
    try
    {
      SRContext localSRContext = getSRContext();
      AudioSourceInfo localAudioSourceInfo = localSRContext.getAudioSourceInfo();
      this.mAutoEndpointingEnabled = localSRContext.getAutoEndpointing();
      this.mAutoEndpointingTimeWithSpeech = (localSRContext.getSpeechEndpointTimeout() / 1000.0F);
      this.mAutoEndpointingTimeWithoutSpeech = (localSRContext.getNoSpeechEndpointTimeout() / 1000.0F);
      bool = false;
      int j;
      switch (1.$SwitchMap$com$vlingo$sdk$recognition$AudioSourceInfo$SourceFormat[localAudioSourceInfo.getFormat().ordinal()])
      {
      default:
        this.mSampleRate = 16000;
        this.mSpeex = new SpeexJNI();
        if (this.mSampleRate != 16000)
          break;
        j = 1;
        float f1 = localSRContext.getSilenceThreshold();
        float f2 = localSRContext.getMinVoiceDuration();
        float f3 = localSRContext.getVoicePortion();
        float f4 = localSRContext.getMinVoiceLevel();
        int k = localSRContext.getSpeexQuality();
        int m = localSRContext.getSpeexComplexity();
        int n = localSRContext.getSpeexVariableBitrate();
        int i1 = localSRContext.getSpeexVoiceActivityDetection();
        int i2 = this.mSpeex.initialize(j, k, m, n, i1, 1, f1, f2, f3, f4);
        this.mSpeexInputBuffer = new short[i2];
        this.mRawInputStreamReader = new RawInputStreamReader(getInputStream(), bool, i2);
        i = 1;
        break;
      case 2:
      case 4:
      case 1:
        while (true)
        {
          label119: this.mSampleRate = 16000;
          break;
          this.mSampleRate = 8000;
          break;
          j = 0;
          break label119;
          return i;
          bool = true;
        }
      case 3:
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        int i = 0;
        continue;
        boolean bool = true;
      }
    }
  }

  protected void onProcessData()
  {
    int i = 0;
    int j = this.mSpeexInputBuffer.length - this.mSpeexInputBufferSamples;
    try
    {
      int k = this.mRawInputStreamReader.read(this.mSpeexInputBuffer, this.mSpeexInputBufferSamples, j);
      if (k == -1)
        i = 1;
      if ((k > 0) && (getDataReadyListner().isDataReady()))
      {
        this.mSpeexInputBufferSamples = (k + this.mSpeexInputBufferSamples);
        this.mTotalSamplesProcessed = (k + this.mTotalSamplesProcessed);
        this.mTotalMillisecProcessed = (1000 * this.mTotalSamplesProcessed / this.mSampleRate);
        if (this.mTotalMillisecProcessed > getMaxDuration())
          i = 1;
      }
      if ((i == 0) || (this.mSpeexInputBufferSamples <= 0) || (this.mSpeexInputBufferSamples >= this.mSpeexInputBuffer.length))
        break label184;
      while (this.mSpeexInputBufferSamples < this.mSpeexInputBuffer.length)
      {
        short[] arrayOfShort = this.mSpeexInputBuffer;
        int i2 = this.mSpeexInputBufferSamples;
        this.mSpeexInputBufferSamples = (i2 + 1);
        arrayOfShort[i2] = 0;
      }
    }
    catch (Exception localException)
    {
      onError(DataReaderListener.ErrorCode.READ_ERROR, localException.toString());
    }
    return;
    label184: if (this.mSpeexInputBufferSamples >= this.mSpeexInputBuffer.length)
    {
      int m = this.mSpeex.encode(this.mSpeexInputBuffer, this.mSpeexOutputBuffer, 1000, 0);
      if (m <= 0)
        break label319;
      int n = -1;
      if (m < 992)
      {
        int i1 = convertBytesToInt(this.mSpeexOutputBuffer, m);
        n = convertBytesToInt(this.mSpeexOutputBuffer, m + 4);
        if (i1 > -1)
          this.mSpeechDetected = true;
      }
      byte[] arrayOfByte = new byte[m];
      System.arraycopy(this.mSpeexOutputBuffer, 0, arrayOfByte, 0, m);
      onDataAvailable(arrayOfByte, n, this.mTotalMillisecProcessed);
    }
    while (true)
    {
      this.mSpeexInputBufferSamples = 0;
      if (i == 0)
        break;
      stop();
      break;
      label319: i = 1;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.reader.SpeexDataReader
 * JD-Core Version:    0.6.0
 */