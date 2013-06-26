package com.vlingo.midas.tss;

import android.speech.tts.TextToSpeech.EngineInfo;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import com.vlingo.core.internal.CoreAdapter;
import com.vlingo.core.internal.audio.TTSLocalEngine;
import com.vlingo.midas.settings.MidasSettings;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class TTSEngineChooser extends TTSLocalEngine
  implements CoreAdapter, TextToSpeech.OnInitListener
{
  private static final TTSEngineType SVOX = TTSEngineType.SVOX;
  private TTSEngineType mCurrentEngine = TTSEngineType.NONE;
  volatile boolean mIsInitialized = false;
  volatile boolean mIsSuccess = false;
  private SVOXWrapper mSVOXTTSEngine;
  private Object svoxCreateDestroyLockObject = new Object();

  private void destroySVOXTTSEngine()
  {
    if (this.mSVOXTTSEngine != null)
    {
      this.mSVOXTTSEngine.destroy();
      this.mSVOXTTSEngine = null;
    }
  }

  protected void finalize()
    throws Throwable
  {
    super.finalize();
  }

  public List<TextToSpeech.EngineInfo> getEngines()
  {
    return null;
  }

  public Object getLanguage()
  {
    return null;
  }

  boolean isInitialized()
  {
    monitorenter;
    try
    {
      boolean bool = this.mIsInitialized;
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

  boolean isSuccess()
  {
    monitorenter;
    try
    {
      boolean bool = this.mIsSuccess;
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

  public void onInit(int paramInt)
  {
    if (paramInt == 0)
      this.mIsSuccess = true;
    this.mIsInitialized = true;
  }

  public int setLanguage(Locale paramLocale)
  {
    boolean bool = true;
    if ((MidasSettings.getBoolean("KEY_ENABLE_SVOX_NEW", bool)) && (SVOXWrapper.isSupportedByLocale(paramLocale)) && (SVOXWrapper.isInstalled(paramLocale)) && (!SVOXWrapper.isExpired()));
    while (true)
    {
      synchronized (this.svoxCreateDestroyLockObject)
      {
        destroySVOXTTSEngine();
        this.mSVOXTTSEngine = new SVOXWrapper(paramLocale);
        if (!this.mSVOXTTSEngine.isInitialized())
          continue;
        this.mCurrentEngine = SVOX;
        if (SVOX == this.mCurrentEngine)
          return bool;
      }
      int i = -1;
    }
  }

  public int setOnUtteranceCompletedListener(TextToSpeech.OnUtteranceCompletedListener paramOnUtteranceCompletedListener)
  {
    int i = 0;
    switch (1.$SwitchMap$com$vlingo$midas$tss$TTSEngineChooser$TTSEngineType[this.mCurrentEngine.ordinal()])
    {
    default:
    case 1:
    }
    while (true)
    {
      return i;
      if (this.mSVOXTTSEngine == null)
        continue;
      i = this.mSVOXTTSEngine.setOnUtteranceCompletedListener(paramOnUtteranceCompletedListener);
    }
  }

  public void setSpeechRate(float paramFloat)
  {
  }

  public void shutdown()
  {
    synchronized (this.svoxCreateDestroyLockObject)
    {
      switch (1.$SwitchMap$com$vlingo$midas$tss$TTSEngineChooser$TTSEngineType[this.mCurrentEngine.ordinal()])
      {
      default:
        break;
      case 1:
        destroySVOXTTSEngine();
      }
    }
  }

  public int stop()
  {
    int i = 0;
    switch (1.$SwitchMap$com$vlingo$midas$tss$TTSEngineChooser$TTSEngineType[this.mCurrentEngine.ordinal()])
    {
    default:
    case 1:
    }
    while (true)
    {
      return i;
      if (this.mSVOXTTSEngine == null)
        continue;
      i = this.mSVOXTTSEngine.stop();
    }
  }

  public int synthesizeToFile(String paramString1, HashMap<String, String> paramHashMap, String paramString2)
  {
    switch (1.$SwitchMap$com$vlingo$midas$tss$TTSEngineChooser$TTSEngineType[this.mCurrentEngine.ordinal()])
    {
    default:
    case 1:
    }
    for (int i = -1; ; i = this.mSVOXTTSEngine.speakToFile(paramString1, paramString2))
      return i;
  }

  private static enum TTSEngineType
  {
    static
    {
      NONE = new TTSEngineType("NONE", 2);
      TTSEngineType[] arrayOfTTSEngineType = new TTSEngineType[3];
      arrayOfTTSEngineType[0] = DEFAULT;
      arrayOfTTSEngineType[1] = SVOX;
      arrayOfTTSEngineType[2] = NONE;
      $VALUES = arrayOfTTSEngineType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.tss.TTSEngineChooser
 * JD-Core Version:    0.6.0
 */