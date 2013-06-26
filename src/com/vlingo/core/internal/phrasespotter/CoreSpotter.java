package com.vlingo.core.internal.phrasespotter;

import android.util.Log;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.recognition.spotter.VLSpotter;
import com.vlingo.sdk.recognition.spotter.VLSpotterContext;
import com.vlingo.sdk.recognition.spotter.VLSpotterContext.Builder;
import com.vlingo.sdk.recognition.spotter.VLSpotterContext.GrammarSource;
import java.nio.ByteBuffer;

public class CoreSpotter
  implements VLPhraseSpotter
{
  private static CoreSpotter smInstance = null;
  private int mDeltaD;
  private int mDeltaS;
  private VLSpotter mSpotter;
  private long mSpotterCtx;
  private boolean mStarted;
  private VLSpotterContext mVLSpotterContext;
  private String wakeUpExternalStorage;

  private CoreSpotter(CoreSpotterParameters paramCoreSpotterParameters)
  {
    updateParams(paramCoreSpotterParameters);
  }

  public static CoreSpotter getInstance(CoreSpotterParameters paramCoreSpotterParameters)
    throws IllegalArgumentException
  {
    monitorenter;
    try
    {
      if (smInstance == null)
        smInstance = new CoreSpotter(paramCoreSpotterParameters);
      while (true)
      {
        CoreSpotter localCoreSpotter = smInstance;
        return localCoreSpotter;
        if (smInstance.mVLSpotterContext.getLanguage().equalsIgnoreCase(paramCoreSpotterParameters.getLanguage()))
          continue;
        smInstance.updateParams(paramCoreSpotterParameters);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void updateParams(CoreSpotterParameters paramCoreSpotterParameters)
  {
    if (paramCoreSpotterParameters.getCGFilename() != null);
    for (VLSpotterContext.GrammarSource localGrammarSource = VLSpotterContext.GrammarSource.getCompiledFileSource(paramCoreSpotterParameters.getCGFilename()); ; localGrammarSource = VLSpotterContext.GrammarSource.getGrammarSpecSource(paramCoreSpotterParameters.getGrammarSpec(), paramCoreSpotterParameters.getWordList(), paramCoreSpotterParameters.getPronunList()))
    {
      VLSpotterContext.Builder localBuilder = new VLSpotterContext.Builder(localGrammarSource);
      localBuilder.spotterParams(paramCoreSpotterParameters.getBeam(), paramCoreSpotterParameters.getAbsbeam(), paramCoreSpotterParameters.getAoffset(), paramCoreSpotterParameters.getDelay(), paramCoreSpotterParameters.getLanguage());
      this.mVLSpotterContext = localBuilder.build();
      this.mDeltaD = paramCoreSpotterParameters.getDeltaD();
      this.mDeltaS = paramCoreSpotterParameters.getDeltaS();
      this.wakeUpExternalStorage = paramCoreSpotterParameters.getWakeUpExternalStorage();
      return;
      if (paramCoreSpotterParameters.getGrammarSpec() == null)
        break;
    }
    throw new IllegalArgumentException("Invalid parameters");
  }

  public void destroy()
  {
    if (this.mStarted)
      this.mSpotter.stopSpotter();
    this.mSpotter.destroy();
  }

  public int getDeltaD()
  {
    return this.mDeltaD;
  }

  public int getDeltaS()
  {
    return this.mDeltaS;
  }

  public long getPhraseContext()
  {
    return this.mSpotterCtx;
  }

  public float getSpottedPhraseScore()
  {
    return this.mSpotter.getLastScore();
  }

  public void init()
  {
    this.mSpotter = VLSdk.getInstance().getSpotter();
    this.mStarted = this.mSpotter.startSpotter(this.mVLSpotterContext, this.wakeUpExternalStorage);
    if (!this.mStarted)
      Log.e("VLG_EXCEPTION", "startSpotter failed");
    if (VLSdk.isSensory2Using())
      this.mSpotterCtx = this.mSpotter.getSpotterContext();
  }

  public String phrasespotPipe(long paramLong1, ByteBuffer paramByteBuffer, long paramLong2)
  {
    try
    {
      String str2 = this.mSpotter.phrasespotPipe(paramLong1, paramByteBuffer, paramLong2);
      str1 = str2;
      return str1;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      while (true)
      {
        init();
        String str1 = this.mSpotter.phrasespotPipe(paramLong1, paramByteBuffer, paramLong2);
      }
    }
  }

  public String processShortArray(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    Object localObject = null;
    if (this.mStarted);
    try
    {
      String str = this.mSpotter.processShortArray(paramArrayOfShort, paramInt1, paramInt2);
      localObject = str;
      return localObject;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      while (true)
      {
        init();
        localObject = this.mSpotter.processShortArray(paramArrayOfShort, paramInt1, paramInt2);
      }
    }
  }

  public boolean useSeamlessFeature(String paramString)
  {
    return ClientSuppliedValues.isSeamless();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.phrasespotter.CoreSpotter
 * JD-Core Version:    0.6.0
 */