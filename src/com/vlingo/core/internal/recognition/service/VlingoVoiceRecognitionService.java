package com.vlingo.core.internal.recognition.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.speech.RecognitionService;
import android.speech.RecognitionService.Callback;
import android.util.Log;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.recognition.AndroidRecognitionManager;
import com.vlingo.core.internal.recognition.AndroidRecognitionResults;
import com.vlingo.core.internal.recognition.AndroidResultDispatcher;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.sdk.recognition.AudioSourceInfo;
import com.vlingo.sdk.recognition.AudioSourceInfo.SourceFormat;
import java.util.ArrayList;

public class VlingoVoiceRecognitionService extends RecognitionService
{
  public static final String ACTION_TEST = "com.vlingo.core.internal.recognition.service.vlingovoicerecognitionservice.action.TEST";
  public static final String EXTRA_DEBUG_ACTION = "com.vlingo.core.internal.recognition.service.vlingovoicerecognitionservice.extra.DEBUG_ACTION";
  public static final int KNOWN_INVALID_DURATION = -1;
  private static final int STATE_IDLE = 0;
  private static final int STATE_LISTENING = 1;
  private static final int STATE_THINKING = 2;
  private AudioFocusManager audioFocusManager;
  private AndroidRecognitionStateListener mStateListener = new RecognitionStateListenerImpl(null);
  private AndroidRecognitionManager m_recoMgr = AndroidRecognitionManager.getInstance();
  private int m_state = 0;
  private RecognitionService.Callback serviceCallbackListener;

  public static void enable()
  {
    ComponentName localComponentName = new ComponentName(ApplicationAdapter.getInstance().getApplicationContext(), VlingoVoiceRecognitionService.class);
    ApplicationAdapter.getInstance().getApplicationContext().getPackageManager().setComponentEnabledSetting(localComponentName, 1, 1);
  }

  private void endRecognition()
  {
    monitorenter;
    try
    {
      if (this.m_state != 0)
      {
        this.m_state = 0;
        AndroidRecognitionManager.getInstance().removeRecognitionStateListener(this.mStateListener);
      }
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

  private AudioFocusManager getAudioFocusManager()
  {
    monitorenter;
    try
    {
      if (this.audioFocusManager == null)
        this.audioFocusManager = AudioFocusManager.getInstance(this);
      AudioFocusManager localAudioFocusManager = this.audioFocusManager;
      monitorexit;
      return localAudioFocusManager;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected void onCancel(RecognitionService.Callback paramCallback)
  {
    AndroidRecognitionManager.getInstance().removeRecognitionStateListener(this.mStateListener);
    monitorenter;
    try
    {
      this.serviceCallbackListener = paramCallback;
      if (this.m_state != 0)
      {
        AndroidRecognitionManager.cancelRecognition();
        endRecognition();
      }
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

  public void onDestroy()
  {
    onCancel(null);
    super.onDestroy();
  }

  protected void onStartListening(Intent paramIntent, RecognitionService.Callback paramCallback)
  {
    monitorenter;
    try
    {
      if (this.m_state == 0)
      {
        boolean bool = this.m_recoMgr.isActive();
        if (!bool);
      }
      else
      {
        try
        {
          paramCallback.error(8);
          monitorexit;
          return;
        }
        catch (RemoteException localRemoteException1)
        {
          while (true)
            Log.e("VLG_EXCEPTION", Log.getStackTraceString(localRemoteException1));
        }
      }
    }
    finally
    {
      monitorexit;
    }
    this.serviceCallbackListener = paramCallback;
    AndroidRecognitionManager.setResultDispatcher(new MyResultDispatcher(null));
    AndroidRecognitionManager.getInstance().addRecognitionStateListener(this.mStateListener);
    String str1 = paramIntent.getStringExtra("com.vlingo.client.actions.RECOGNIZE.pcm_wavefile");
    Object localObject2 = null;
    int i = -1;
    if (str1 != null)
      localObject2 = AudioSourceInfo.getFileSource(str1, AudioSourceInfo.SourceFormat.PCM_AUTO);
    while (true)
    {
      while (true)
      {
        String str3 = paramIntent.getStringExtra("com.vlingo.client.actions.RECOGNIZE.field_id");
        if (str3 == null)
          str3 = "vp_main";
        if (AndroidRecognitionManager.initiateRecognition(this, str3, (AudioSourceInfo)localObject2, i))
          break;
        endRecognition();
        try
        {
          paramCallback.error(2);
        }
        catch (RemoteException localRemoteException2)
        {
          Log.e("VLG_EXCEPTION", Log.getStackTraceString(localRemoteException2));
        }
      }
      break;
      String str2 = paramIntent.getStringExtra("com.vlingo.client.actions.RECOGNIZE.amr_wavefile");
      if (str2 == null)
        continue;
      i = paramIntent.getIntExtra("com.vlingo.client.actions.RECOGNIZE.duration_ms", -1);
      AudioSourceInfo localAudioSourceInfo = AudioSourceInfo.getFileSource(str2, AudioSourceInfo.SourceFormat.AMR);
      localObject2 = localAudioSourceInfo;
    }
  }

  protected void onStopListening(RecognitionService.Callback paramCallback)
  {
    monitorenter;
    try
    {
      this.serviceCallbackListener = paramCallback;
      if (this.m_state == 1)
      {
        this.m_state = 2;
        AndroidRecognitionManager.processRecognition();
      }
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

  private class MyResultDispatcher extends AndroidResultDispatcher
  {
    private MyResultDispatcher()
    {
    }

    public boolean handleResults(AndroidRecognitionResults paramAndroidRecognitionResults)
    {
      while (true)
      {
        ArrayList localArrayList;
        synchronized (VlingoVoiceRecognitionService.this)
        {
          localArrayList = new ArrayList();
          if (VlingoVoiceRecognitionService.this.m_state != 2)
            continue;
          RecognitionService.Callback localCallback = VlingoVoiceRecognitionService.this.serviceCallbackListener;
          if ((localCallback != null) && (paramAndroidRecognitionResults != null))
            continue;
          try
          {
            VlingoVoiceRecognitionService.this.serviceCallbackListener.error(7);
            VlingoVoiceRecognitionService.this.endRecognition();
            return true;
            if (paramAndroidRecognitionResults.hasError())
            {
              VlingoVoiceRecognitionService.this.serviceCallbackListener.error(7);
              continue;
            }
          }
          catch (RemoteException localRemoteException)
          {
            Log.e("VLG_EXCEPTION", Log.getStackTraceString(localRemoteException));
            continue;
          }
        }
        String str = paramAndroidRecognitionResults.getRecognitionResultPhrase();
        if ((str != null) && (str.length() > 0))
          localArrayList.add(str);
        if (localArrayList.size() > 0)
        {
          Bundle localBundle = new Bundle();
          localBundle.putStringArrayList("results_recognition", localArrayList);
          VlingoVoiceRecognitionService.this.serviceCallbackListener.results(localBundle);
          continue;
        }
        VlingoVoiceRecognitionService.this.serviceCallbackListener.error(7);
      }
    }

    public void notifyWorking()
    {
    }

    public void onRmsChanged(int paramInt)
    {
      try
      {
        VlingoVoiceRecognitionService.this.serviceCallbackListener.rmsChanged(paramInt - 30);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          Log.e("VLG_EXCEPTION", Log.getStackTraceString(localRemoteException));
      }
    }
  }

  private class RecognitionStateListenerImpl
    implements AndroidRecognitionStateListener
  {
    private RecognitionStateListenerImpl()
    {
    }

    public void onRecognitionEvent(AndroidRecognitionManager paramAndroidRecognitionManager, int paramInt1, int paramInt2, String paramString)
    {
      int i1;
      int n;
      int m;
      int k;
      int j;
      int i;
      label472: synchronized (VlingoVoiceRecognitionService.this)
      {
        if (VlingoVoiceRecognitionService.this.serviceCallbackListener == null)
          break label472;
      }
      switch (paramInt1)
      {
      case 100:
      case 102:
      case 104:
      case 111:
      case 112:
      case 200:
      default:
      case 101:
      case 103:
      case 107:
      case 110:
      case 109:
      case 106:
      case 108:
      case 105:
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.service.VlingoVoiceRecognitionService
 * JD-Core Version:    0.6.0
 */