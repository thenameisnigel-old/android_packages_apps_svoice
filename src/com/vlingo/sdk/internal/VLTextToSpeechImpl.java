package com.vlingo.sdk.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.vlingo.sdk.internal.audio.TTSRequest;
import com.vlingo.sdk.internal.audio.TTSServerManager;
import com.vlingo.sdk.internal.http.HttpCallback;
import com.vlingo.sdk.internal.http.HttpRequest;
import com.vlingo.sdk.internal.http.HttpResponse;
import com.vlingo.sdk.internal.settings.Settings;
import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.internal.vlservice.VLHttpServiceRequest;
import com.vlingo.sdk.tts.VLTextToSpeech;
import com.vlingo.sdk.tts.VLTextToSpeechErrors;
import com.vlingo.sdk.tts.VLTextToSpeechListener;
import com.vlingo.sdk.tts.VLTextToSpeechRequest;
import com.vlingo.sdk.tts.VLTextToSpeechRequest.SpeechRate;
import com.vlingo.sdk.tts.VLTextToSpeechRequest.Type;
import com.vlingo.sdk.tts.VLTextToSpeechRequest.VoiceType;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public final class VLTextToSpeechImpl extends VLComponentImpl
  implements VLTextToSpeech, HttpCallback
{
  private VLHttpServiceRequest mCurrentRequest;
  private String mFilename;
  private NotificationHandler mNotificationHandler = new NotificationHandler();
  private VLTextToSpeechListener mTextToSpeechListener;

  public VLTextToSpeechImpl(VLComponentManager paramVLComponentManager, Handler paramHandler)
  {
    super(paramVLComponentManager, paramHandler);
  }

  private static TTSRequest getTTSRequest(VLTextToSpeechRequest paramVLTextToSpeechRequest)
  {
    boolean bool = true;
    VLTextToSpeechRequest.Type localType = paramVLTextToSpeechRequest.getType();
    TTSRequest localTTSRequest;
    String str3;
    label73: String str4;
    switch (2.$SwitchMap$com$vlingo$sdk$tts$VLTextToSpeechRequest$Type[localType.ordinal()])
    {
    default:
      localTTSRequest = TTSRequest.getText(paramVLTextToSpeechRequest.getText());
      VLTextToSpeechRequest.VoiceType localVoiceType = paramVLTextToSpeechRequest.getVoice();
      VLTextToSpeechRequest.SpeechRate localSpeechRate = paramVLTextToSpeechRequest.getSpeechRate();
      if (localVoiceType != VLTextToSpeechRequest.VoiceType.MALE)
        break;
      str3 = "Male";
      switch (2.$SwitchMap$com$vlingo$sdk$tts$VLTextToSpeechRequest$SpeechRate[localSpeechRate.ordinal()])
      {
      default:
        str4 = "Normal";
      case 1:
      case 2:
      case 3:
      case 4:
      }
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      localTTSRequest.setGender(str3);
      localTTSRequest.setWordLimit(paramVLTextToSpeechRequest.getMaxWords());
      localTTSRequest.setSpeechRate(str4);
      return localTTSRequest;
      String str7 = paramVLTextToSpeechRequest.getMsgFrom();
      String str8 = paramVLTextToSpeechRequest.getMsgSubject();
      String str9 = paramVLTextToSpeechRequest.getText();
      if (!paramVLTextToSpeechRequest.getMsgReadyBody());
      while (true)
      {
        localTTSRequest = TTSRequest.getEmail(str7, str8, str9, bool);
        break;
        bool = false;
      }
      String str5 = paramVLTextToSpeechRequest.getMsgFrom();
      String str6 = paramVLTextToSpeechRequest.getText();
      if (!paramVLTextToSpeechRequest.getMsgReadyBody());
      while (true)
      {
        localTTSRequest = TTSRequest.getSMS(str5, str6, bool);
        break;
        bool = false;
      }
      String str1 = paramVLTextToSpeechRequest.getMsgFrom();
      String str2 = paramVLTextToSpeechRequest.getMsgSubject();
      if (!paramVLTextToSpeechRequest.getMsgReadyBody());
      while (true)
      {
        localTTSRequest = TTSRequest.getMMS(str1, str2, bool);
        break;
        bool = false;
      }
      str3 = "Female";
      break label73;
      str4 = "VerySlow";
      continue;
      str4 = "Slow";
      continue;
      str4 = "Fast";
      continue;
      str4 = "VeryFast";
    }
  }

  public void cancel()
  {
    validateInstance();
    monitorenter;
    try
    {
      if (this.mCurrentRequest != null)
      {
        this.mCurrentRequest.cancel();
        this.mCurrentRequest = null;
      }
      monitorexit;
      this.mTextToSpeechListener = null;
      this.mNotificationHandler.cancelNotifications();
      setBusy(false);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public String[] getSupportedLanguageList()
  {
    validateInstance();
    return Settings.SUPPORTED_LANGUAGES;
  }

  public void onCancelled(HttpRequest paramHttpRequest)
  {
    monitorenter;
    monitorexit;
  }

  void onDestroy()
  {
    this.mTextToSpeechListener = null;
  }

  public void onFailure(HttpRequest paramHttpRequest)
  {
    monitorenter;
    try
    {
      VLHttpServiceRequest localVLHttpServiceRequest = this.mCurrentRequest;
      if (paramHttpRequest != localVLHttpServiceRequest);
      while (true)
      {
        return;
        this.mCurrentRequest = null;
        this.mNotificationHandler.notifyError(VLTextToSpeechErrors.ERROR_NETWORK, "Network error");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void onResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
  {
    monitorenter;
    while (true)
    {
      try
      {
        VLHttpServiceRequest localVLHttpServiceRequest = this.mCurrentRequest;
        if (paramHttpRequest != localVLHttpServiceRequest)
          return;
        this.mCurrentRequest = null;
        if (paramHttpResponse.responseCode == 200)
        {
          byte[] arrayOfByte = TTSServerManager.parseTTSResponse(paramHttpResponse);
          if (arrayOfByte != null)
          {
            try
            {
              BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.mFilename));
              localBufferedOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
              localBufferedOutputStream.flush();
              localBufferedOutputStream.close();
              this.mNotificationHandler.notifySuccess();
            }
            catch (Exception localException)
            {
              this.mNotificationHandler.notifyError(VLTextToSpeechErrors.ERROR_CLIENT, "Error creating/writing to file");
            }
            continue;
          }
        }
      }
      finally
      {
        monitorexit;
      }
      this.mNotificationHandler.notifyError(VLTextToSpeechErrors.ERROR_SERVER, paramHttpResponse.getDataAsString());
    }
  }

  public boolean onTimeout(HttpRequest paramHttpRequest)
  {
    monitorenter;
    try
    {
      VLHttpServiceRequest localVLHttpServiceRequest = this.mCurrentRequest;
      if (paramHttpRequest != localVLHttpServiceRequest);
      while (true)
      {
        return true;
        this.mCurrentRequest = null;
        this.mNotificationHandler.notifyError(VLTextToSpeechErrors.ERROR_NETWORK_TIMEOUT, "Timeout waiting for request");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void onWillExecuteRequest(HttpRequest paramHttpRequest)
  {
    monitorenter;
    monitorexit;
  }

  public void synthesizeToFile(VLTextToSpeechRequest paramVLTextToSpeechRequest, String paramString, VLTextToSpeechListener paramVLTextToSpeechListener)
  {
    validateInstance();
    if (paramVLTextToSpeechRequest == null)
      throw new IllegalArgumentException("request cannot be null");
    if (StringUtils.isNullOrWhiteSpace(paramString))
      throw new IllegalArgumentException("filename cannot be null or empty");
    if (paramVLTextToSpeechListener == null)
      throw new IllegalArgumentException("listener must be specifed");
    if (isBusy())
      throw new IllegalStateException("TextToSpeech request already in progress");
    setBusy(true);
    this.mTextToSpeechListener = paramVLTextToSpeechListener;
    this.mFilename = paramString;
    TTSRequest localTTSRequest = getTTSRequest(paramVLTextToSpeechRequest);
    String str = paramVLTextToSpeechRequest.getLanguage();
    getHandler().post(new Runnable(localTTSRequest, str)
    {
      public void run()
      {
        VLTextToSpeechImpl.access$102(VLTextToSpeechImpl.this, TTSServerManager.createTTSRequest(this.val$ttsRequest, this.val$language, VLTextToSpeechImpl.this));
        VLTextToSpeechImpl.this.mCurrentRequest.schedule(0L, true, true);
      }
    });
  }

  private class NotificationHandler extends Handler
  {
    static final int ERROR = 2;
    static final int SUCCESS = 1;

    NotificationHandler()
    {
      super();
    }

    void cancelNotifications()
    {
      monitorenter;
      try
      {
        removeMessages(1);
        removeMessages(2);
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
      VLTextToSpeechImpl.this.setBusy(false);
      VLTextToSpeechListener localVLTextToSpeechListener = VLTextToSpeechImpl.this.mTextToSpeechListener;
      VLTextToSpeechImpl.access$002(VLTextToSpeechImpl.this, null);
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      }
      while (true)
      {
        return;
        if (localVLTextToSpeechListener == null)
          continue;
        localVLTextToSpeechListener.onSuccess();
        continue;
        if (localVLTextToSpeechListener == null)
          continue;
        localVLTextToSpeechListener.onError((VLTextToSpeechErrors)((Object[])(Object[])paramMessage.obj)[0], (String)((Object[])(Object[])paramMessage.obj)[1]);
      }
    }

    void notifyError(VLTextToSpeechErrors paramVLTextToSpeechErrors, String paramString)
    {
      monitorenter;
      try
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramVLTextToSpeechErrors;
        arrayOfObject[1] = paramString;
        obtainMessage(2, arrayOfObject).sendToTarget();
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

    void notifySuccess()
    {
      monitorenter;
      try
      {
        obtainMessage(1).sendToTarget();
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLTextToSpeechImpl
 * JD-Core Version:    0.6.0
 */