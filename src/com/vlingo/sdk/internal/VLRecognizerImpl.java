package com.vlingo.sdk.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.vlingo.sdk.internal.audio.SpeexJNI;
import com.vlingo.sdk.internal.recognizer.Recognizer;
import com.vlingo.sdk.internal.recognizer.RecognizerListener;
import com.vlingo.sdk.internal.recognizer.RecognizerListener.RecognizerError;
import com.vlingo.sdk.internal.recognizer.RecognizerListener.RecognizerState;
import com.vlingo.sdk.internal.recognizer.SRContext;
import com.vlingo.sdk.internal.recognizer.SRStatisticsCollection;
import com.vlingo.sdk.internal.recognizer.reader.DataReadyListner;
import com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.vlservice.response.ServerMessage;
import com.vlingo.sdk.recognition.VLRecognitionContext;
import com.vlingo.sdk.recognition.VLRecognitionErrors;
import com.vlingo.sdk.recognition.VLRecognitionListener;
import com.vlingo.sdk.recognition.VLRecognitionResult;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import com.vlingo.sdk.recognition.VLRecognitionWarnings;
import com.vlingo.sdk.recognition.VLRecognizer;
import com.vlingo.sdk.recognition.dialog.VLDialogContext;
import java.util.List;

public final class VLRecognizerImpl extends VLComponentImpl
  implements VLRecognizer
{
  private RecognizerListener mCurrentRecognizerListener;
  private DataReadyListner mDataReadyListner;
  private final NotificationHandler mNotificationHandler = new NotificationHandler();
  private VLRecognitionContext mRecoContext;
  private VLRecognitionListener mRecoListener;
  private Recognizer mRecognizer;

  public VLRecognizerImpl(VLComponentManager paramVLComponentManager, Handler paramHandler)
  {
    super(paramVLComponentManager, paramHandler);
    SpeexJNI.init();
    this.mRecognizer = new Recognizer();
  }

  private SRContext getSRContext()
  {
    SRContext localSRContext = new SRContext();
    localSRContext.setFieldID(this.mRecoContext.getFieldID());
    StringBuilder localStringBuilder = new StringBuilder();
    if (!StringUtils.isNullOrWhiteSpace(this.mRecoContext.getAppName()))
      localStringBuilder.append(this.mRecoContext.getAppName());
    if (!StringUtils.isNullOrWhiteSpace(this.mRecoContext.getScreenName()))
    {
      localStringBuilder.append(':' + this.mRecoContext.getScreenName());
      if (!StringUtils.isNullOrWhiteSpace(this.mRecoContext.getControlName()))
        localStringBuilder.append(':' + this.mRecoContext.getControlName());
    }
    while (true)
    {
      localSRContext.setFieldContext(localStringBuilder.toString());
      localSRContext.setCurText(this.mRecoContext.getCurrentText());
      localSRContext.setCursorPos(this.mRecoContext.getCursorPosition());
      localSRContext.setAutoPunctuation(this.mRecoContext.getAutoPunctuation());
      localSRContext.setProfanityFilter(this.mRecoContext.getProfanityFilter());
      localSRContext.setCapitalizationMode(this.mRecoContext.getCapitalizationMode());
      localSRContext.setMaxAudioTime(this.mRecoContext.getMaxAudioTime());
      localSRContext.setAutoEndpointing(this.mRecoContext.autoEndpointingEnabled());
      localSRContext.setSilenceDetectionParams(this.mRecoContext.getSilenceThreshold(), this.mRecoContext.getMinVoiceDuration(), this.mRecoContext.getVoicePortion(), this.mRecoContext.getMinVoiceLevel());
      localSRContext.setSpeexParams(this.mRecoContext.getSpeexComplexity(), this.mRecoContext.getSpeexQuality(), this.mRecoContext.getSpeexVariableBitrate(), this.mRecoContext.getSpeexVoiceActivityDetection());
      localSRContext.setSpeechEndpointTimeout(this.mRecoContext.getSpeechEndpointTimeout());
      localSRContext.setNoSpeechEndpointTimeout(this.mRecoContext.getNoSpeechEndPointTimeout());
      localSRContext.setAudioSourceInfo(this.mRecoContext.getAudioSourceInfo());
      if ((this.mRecoContext instanceof VLDialogContext))
      {
        localSRContext.setIsDMRequest(true);
        localSRContext.setDialogState(((VLDialogContext)this.mRecoContext).getDialogState());
        localSRContext.setEvents(((VLDialogContext)this.mRecoContext).getEvents());
        localSRContext.setUsername(((VLDialogContext)this.mRecoContext).getUsername());
        localSRContext.setDialogGUID(((VLDialogContext)this.mRecoContext).getDialogGUID());
        localSRContext.setDialogTurnNumber(((VLDialogContext)this.mRecoContext).getDialogTurnNumber());
        localSRContext.setDMHeaderKVPairs(((VLDialogContext)this.mRecoContext).getDMHeaderKVPairs());
      }
      return localSRContext;
      if (StringUtils.isNullOrWhiteSpace(this.mRecoContext.getControlName()))
        continue;
      localStringBuilder.append("::" + this.mRecoContext.getControlName());
    }
  }

  public void acceptedText(String paramString1, String paramString2)
  {
    validateInstance();
    if (StringUtils.isNullOrWhiteSpace(paramString2));
    while (true)
    {
      return;
      SRStatisticsCollection localSRStatisticsCollection = new SRStatisticsCollection();
      localSRStatisticsCollection.setAcceptedText(paramString2);
      getHandler().post(new Runnable(paramString1, localSRStatisticsCollection)
      {
        public void run()
        {
          synchronized (VLRecognizerImpl.this)
          {
            if (VLRecognizerImpl.this.isValid())
              VLRecognizerImpl.this.mRecognizer.sendAcceptedText(this.val$GUttId, this.val$collection);
          }
        }
      });
    }
  }

  public void cancelRecognition()
  {
    validateInstance();
    VLRecognitionListener localVLRecognitionListener = this.mRecoListener;
    this.mRecoListener = null;
    this.mCurrentRecognizerListener = null;
    this.mNotificationHandler.clear();
    getHandler().post(new Runnable()
    {
      public void run()
      {
        synchronized (VLRecognizerImpl.this)
        {
          if (VLRecognizerImpl.this.isValid())
            VLRecognizerImpl.this.mRecognizer.cancel();
        }
      }
    });
    if (localVLRecognitionListener != null)
      this.mNotificationHandler.post(new Runnable(localVLRecognitionListener)
      {
        public void run()
        {
          VLRecognizerImpl.this.setBusy(false);
          this.val$l.onCancelled();
        }
      });
  }

  public String[] getSupportedLanguageList()
  {
    validateInstance();
    return Settings.SUPPORTED_LANGUAGES;
  }

  void onDestroy()
  {
    this.mCurrentRecognizerListener = null;
    this.mRecognizer.destroy();
    this.mRecognizer = null;
    this.mRecoListener = null;
  }

  public void sendEvent(VLDialogContext paramVLDialogContext, VLRecognitionListener paramVLRecognitionListener)
  {
    validateInstance();
    if (paramVLDialogContext == null)
      throw new IllegalArgumentException("context must be specified");
    if (paramVLRecognitionListener == null)
      throw new IllegalArgumentException("listener must be specifed");
    if ((paramVLDialogContext.getEvents() == null) || (paramVLDialogContext.getEvents().size() == 0))
      throw new IllegalArgumentException("context must contain at least 1 event");
    if (isBusy())
      throw new IllegalStateException("Recognition already in progress");
    setBusy(true);
    this.mRecoListener = paramVLRecognitionListener;
    this.mRecoContext = paramVLDialogContext;
    Settings.LANGUAGE = this.mRecoContext.getLanguage();
    this.mCurrentRecognizerListener = new RecognitionListenerImpl(null);
    getHandler().post(new Runnable()
    {
      RecognizerListener mListener = VLRecognizerImpl.this.mCurrentRecognizerListener;

      public void run()
      {
        synchronized (VLRecognizerImpl.this.getDestroyLock())
        {
          if (!VLRecognizerImpl.this.isValid())
            return;
          VLRecognizerImpl.this.mNotificationHandler.notifyEvent(VLRecognitionStates.GETTING_READY);
        }
        try
        {
          VLRecognizerImpl.this.mRecognizer.startSendEvent(VLRecognizerImpl.this.getSRContext(), this.mListener);
          monitorexit;
          return;
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          while (true)
            VLRecognizerImpl.this.mNotificationHandler.notifyError(VLRecognitionErrors.ERROR_RECOGNIZER_BUSY, "Failed to start recognition.");
        }
      }
    });
  }

  public void startRecognition(VLRecognitionContext paramVLRecognitionContext, VLRecognitionListener paramVLRecognitionListener, DataReadyListner paramDataReadyListner)
  {
    validateInstance();
    if (paramVLRecognitionContext == null)
      throw new IllegalArgumentException("context must be specified");
    if (paramVLRecognitionListener == null)
      throw new IllegalArgumentException("listener must be specified");
    if (paramVLRecognitionContext.getAudioSourceInfo() == null)
      throw new IllegalArgumentException("AudioSourceInfo is required for startRecognition()");
    if (isBusy())
      throw new IllegalStateException("Recognition already in progress");
    setBusy(true);
    this.mRecoListener = paramVLRecognitionListener;
    this.mRecoContext = paramVLRecognitionContext;
    this.mDataReadyListner = paramDataReadyListner;
    Settings.LANGUAGE = this.mRecoContext.getLanguage();
    this.mCurrentRecognizerListener = new RecognitionListenerImpl(null);
    getHandler().post(new Runnable()
    {
      RecognizerListener mListener = VLRecognizerImpl.this.mCurrentRecognizerListener;

      public void run()
      {
        synchronized (VLRecognizerImpl.this.getDestroyLock())
        {
          if (!VLRecognizerImpl.this.isValid())
            return;
          VLRecognizerImpl.this.mNotificationHandler.notifyEvent(VLRecognitionStates.GETTING_READY);
        }
        try
        {
          VLRecognizerImpl.this.mRecognizer.startRecognition(VLRecognizerImpl.this.getSRContext(), this.mListener, VLRecognizerImpl.this.mDataReadyListner);
          monitorexit;
          return;
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          while (true)
            VLRecognizerImpl.this.mNotificationHandler.notifyError(VLRecognitionErrors.ERROR_RECOGNIZER_BUSY, "Failed to start recognition.");
        }
      }
    });
  }

  public void stopRecognition()
  {
    validateInstance();
    getHandler().post(new Runnable()
    {
      public void run()
      {
        synchronized (VLRecognizerImpl.this.getDestroyLock())
        {
          if (VLRecognizerImpl.this.isValid())
            VLRecognizerImpl.this.mRecognizer.stop();
        }
      }
    });
  }

  private class NotificationHandler extends Handler
  {
    static final int ERROR = 5;
    static final int RESULT = 3;
    static final int RMS = 2;
    static final int STATE = 1;
    static final int WARNING = 4;

    NotificationHandler()
    {
      super();
    }

    void clear()
    {
      monitorenter;
      try
      {
        removeMessages(2);
        removeMessages(1);
        removeMessages(3);
        removeMessages(4);
        removeMessages(5);
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

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 2:
      case 1:
      case 3:
      case 4:
      case 5:
      }
      while (true)
      {
        return;
        if (VLRecognizerImpl.this.mRecoListener == null)
          continue;
        VLRecognizerImpl.this.mRecoListener.onRmsChanged(((Integer)paramMessage.obj).intValue());
        continue;
        if (VLRecognizerImpl.this.mRecoListener == null)
          continue;
        VLRecognizerImpl.this.mRecoListener.onStateChanged((VLRecognitionStates)paramMessage.obj);
        continue;
        VLRecognizerImpl.this.setBusy(false);
        if (VLRecognizerImpl.this.mRecoListener == null)
          continue;
        VLRecognitionListener localVLRecognitionListener2 = VLRecognizerImpl.this.mRecoListener;
        VLRecognizerImpl.access$002(VLRecognizerImpl.this, null);
        localVLRecognitionListener2.onRecognitionResults((VLRecognitionResult)paramMessage.obj);
        continue;
        if (VLRecognizerImpl.this.mRecoListener == null)
          continue;
        VLRecognitionWarnings localVLRecognitionWarnings = (VLRecognitionWarnings)((Object[])(Object[])paramMessage.obj)[0];
        String str2 = (String)((Object[])(Object[])paramMessage.obj)[1];
        VLRecognizerImpl.this.mRecoListener.onWarning(localVLRecognitionWarnings, str2);
        continue;
        VLRecognitionErrors localVLRecognitionErrors = (VLRecognitionErrors)((Object[])(Object[])paramMessage.obj)[0];
        String str1 = (String)((Object[])(Object[])paramMessage.obj)[1];
        VLRecognizerImpl.this.setBusy(false);
        if (VLRecognizerImpl.this.mRecoListener == null)
          continue;
        VLRecognitionListener localVLRecognitionListener1 = VLRecognizerImpl.this.mRecoListener;
        VLRecognizerImpl.access$002(VLRecognizerImpl.this, null);
        localVLRecognitionListener1.onError(localVLRecognitionErrors, str1);
      }
    }

    void notifyError(VLRecognitionErrors paramVLRecognitionErrors, String paramString)
    {
      monitorenter;
      try
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramVLRecognitionErrors;
        arrayOfObject[1] = paramString;
        obtainMessage(5, arrayOfObject).sendToTarget();
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

    void notifyEvent(VLRecognitionStates paramVLRecognitionStates)
    {
      monitorenter;
      try
      {
        obtainMessage(1, paramVLRecognitionStates).sendToTarget();
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

    void notifyResult(VLRecognitionResult paramVLRecognitionResult)
    {
      monitorenter;
      try
      {
        obtainMessage(3, paramVLRecognitionResult).sendToTarget();
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

    void notifyRmsChange(Object paramObject)
    {
      monitorenter;
      try
      {
        obtainMessage(2, paramObject).sendToTarget();
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

    void notifyWarning(VLRecognitionWarnings paramVLRecognitionWarnings, String paramString)
    {
      monitorenter;
      try
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramVLRecognitionWarnings;
        arrayOfObject[1] = paramString;
        obtainMessage(4, arrayOfObject).sendToTarget();
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

  private class RecognitionListenerImpl
    implements RecognizerListener
  {
    private RecognitionListenerImpl()
    {
    }

    public void onRecognitionResponse(SRRecognitionResponse paramSRRecognitionResponse)
    {
      if (this != VLRecognizerImpl.this.mCurrentRecognizerListener);
      while (true)
      {
        return;
        if (!paramSRRecognitionResponse.isError())
          break;
        ServerMessage localServerMessage2 = paramSRRecognitionResponse.getFirstMessage();
        VLRecognizerImpl.this.mNotificationHandler.notifyError(VLRecognitionErrors.ERROR_SERVER, localServerMessage2.getMessage());
      }
      VLRecognitionResultImpl localVLRecognitionResultImpl = new VLRecognitionResultImpl(paramSRRecognitionResponse);
      ServerMessage localServerMessage1;
      if (paramSRRecognitionResponse.hasWarnings())
      {
        localServerMessage1 = paramSRRecognitionResponse.getFirstMessage();
        if ((!localServerMessage1.getCode().equals("NothingRecognized")) || (!StringUtils.isNullOrWhiteSpace(localVLRecognitionResultImpl.getResultString())))
          break label122;
        VLRecognizerImpl.this.mNotificationHandler.notifyWarning(VLRecognitionWarnings.WARNING_NOTHING_RECOGNIZED, localServerMessage1.getMessage());
      }
      while (true)
      {
        VLRecognizerImpl.this.mNotificationHandler.notifyResult(localVLRecognitionResultImpl);
        break;
        label122: VLRecognizerImpl.this.mNotificationHandler.notifyWarning(VLRecognitionWarnings.WARNING_SERVER, localServerMessage1.getMessage());
      }
    }

    public void onRecognizerError(RecognizerListener.RecognizerError paramRecognizerError, String paramString)
    {
      if (this != VLRecognizerImpl.this.mCurrentRecognizerListener);
      while (true)
      {
        return;
        switch (VLRecognizerImpl.7.$SwitchMap$com$vlingo$sdk$internal$recognizer$RecognizerListener$RecognizerError[paramRecognizerError.ordinal()])
        {
        default:
          break;
        case 1:
          VLRecognizerImpl.this.mNotificationHandler.notifyError(VLRecognitionErrors.ERROR_SERVER, paramString);
          break;
        case 2:
          VLRecognizerImpl.this.mNotificationHandler.notifyError(VLRecognitionErrors.ERROR_NETWORK_TIMEOUT, paramString);
          break;
        case 3:
          VLRecognizerImpl.this.mNotificationHandler.notifyError(VLRecognitionErrors.ERROR_NO_MATCH, paramString);
          break;
        case 4:
          VLRecognizerImpl.this.mNotificationHandler.notifyError(VLRecognitionErrors.ERROR_NETWORK, paramString);
          break;
        case 5:
          VLRecognizerImpl.this.mNotificationHandler.notifyError(VLRecognitionErrors.ERROR_AUDIO, paramString);
          break;
        case 6:
          VLRecognizerImpl.this.mNotificationHandler.notifyError(VLRecognitionErrors.ERROR_SPEECH_TIMEOUT, paramString);
        }
      }
    }

    public void onRecognizerStateChanged(RecognizerListener.RecognizerState paramRecognizerState, Object paramObject)
    {
      if (this != VLRecognizerImpl.this.mCurrentRecognizerListener);
      while (true)
      {
        return;
        switch (VLRecognizerImpl.7.$SwitchMap$com$vlingo$sdk$internal$recognizer$RecognizerListener$RecognizerState[paramRecognizerState.ordinal()])
        {
        default:
          break;
        case 1:
          VLRecognizerImpl.this.mNotificationHandler.notifyEvent(VLRecognitionStates.LISTENING);
          break;
        case 2:
          VLRecognizerImpl.this.mNotificationHandler.notifyEvent(VLRecognitionStates.CONNECTING);
          break;
        case 3:
          VLRecognizerImpl.this.mNotificationHandler.notifyEvent(VLRecognitionStates.THINKING);
          break;
        case 4:
          VLRecognizerImpl.this.mNotificationHandler.notifyRmsChange(paramObject);
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLRecognizerImpl
 * JD-Core Version:    0.6.0
 */