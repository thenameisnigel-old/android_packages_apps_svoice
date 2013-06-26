package com.vlingo.sdk.internal;

import android.os.Handler;
import com.vlingo.sdk.internal.audio.VLSilJNI;
import com.vlingo.sdk.recognition.AudioSourceInfo;
import com.vlingo.sdk.recognition.AudioSourceInfo.SourceFormat;
import com.vlingo.sdk.recognition.speech.VLSpeechDetector;
import com.vlingo.sdk.recognition.speech.VLSpeechDetectorContext;
import java.util.Arrays;

public final class VLSpeechDetectorImpl extends VLComponentImpl
  implements VLSpeechDetector
{
  private boolean mIsStarted;
  private int mPrevLastSpeechSample;
  private VLSilJNI mVLSilJNI;

  public VLSpeechDetectorImpl(VLComponentManager paramVLComponentManager, Handler paramHandler)
  {
    super(paramVLComponentManager, paramHandler);
    VLSilJNI.init();
    this.mVLSilJNI = new VLSilJNI();
  }

  void onDestroy()
  {
    this.mVLSilJNI = null;
  }

  public boolean processShortArray(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    monitorenter;
    try
    {
      validateInstance();
      if (!this.mIsStarted)
        throw new IllegalStateException("Cannot call processShortArray when SpeechDetector is not started.");
    }
    finally
    {
      monitorexit;
    }
    if (paramInt1 > 0)
    {
      int k = paramInt1 + paramInt2;
      paramArrayOfShort = Arrays.copyOfRange(paramArrayOfShort, paramInt1, k);
    }
    int i = 0;
    int j = this.mVLSilJNI.processShortArray(paramArrayOfShort, paramInt2);
    if (j > this.mPrevLastSpeechSample)
      i = 1;
    this.mPrevLastSpeechSample = j;
    monitorexit;
    return i;
  }

  public boolean startSpeechDetector(VLSpeechDetectorContext paramVLSpeechDetectorContext)
  {
    monitorenter;
    try
    {
      validateInstance();
      if (paramVLSpeechDetectorContext == null)
        throw new IllegalArgumentException("context must be specified");
    }
    finally
    {
      monitorexit;
    }
    if (this.mIsStarted)
      throw new IllegalStateException("SpeechDetector already started");
    if (paramVLSpeechDetectorContext.getAudioSourceInfo().getFormat() == AudioSourceInfo.SourceFormat.PCM_16KHZ_16BIT);
    for (int i = 16000; ; i = 8000)
    {
      this.mVLSilJNI.initialize(i, paramVLSpeechDetectorContext.getSilenceThreshold(), paramVLSpeechDetectorContext.getMinVoiceDuration(), paramVLSpeechDetectorContext.getVoicePortion(), paramVLSpeechDetectorContext.getMinVoiceLevel());
      this.mPrevLastSpeechSample = -1;
      this.mIsStarted = true;
      monitorexit;
      return true;
    }
  }

  public void stopSpeechDetector()
  {
    monitorenter;
    try
    {
      validateInstance();
      if (this.mIsStarted)
        this.mIsStarted = false;
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLSpeechDetectorImpl
 * JD-Core Version:    0.6.0
 */