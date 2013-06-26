package com.vlingo.core.internal.phrasespotter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.media.AudioManager;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled;
import com.vlingo.core.internal.audio.IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored;
import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.DialogFlowTaskRegulator;
import com.vlingo.core.internal.dialogmanager.DialogFlowTaskRegulator.EventType;
import com.vlingo.core.internal.dialogmanager.ResumeControl;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;

public class PhraseSpotter
  implements DialogFlowTaskRegulator, PhraseSpotterListener
{
  private static PhraseSpotter instance;
  private volatile boolean audioPlaying;
  private boolean isBTConnected;
  private boolean isCharging;
  private PhraseSpotterListener listener;
  private PhraseSpotterControl phraseSpotterControl = new PhraseSpotterControl();
  private PhraseSpotterParameters phraseSpotterParams;
  private BroadcastReceiver psBroadcastReceiver = new PSBroadcastReceiver(null);
  private SharedPreferences.OnSharedPreferenceChangeListener psSharedPrefChangeListener = new PSSharedPreferenceChangeListener(null);
  private volatile boolean recoInProgress;
  private volatile boolean spottingRequested;

  private PhraseSpotter()
  {
    Settings.getSharedPreferences().registerOnSharedPreferenceChangeListener(this.psSharedPrefChangeListener);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.BATTERY_CHANGED");
    localIntentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
    ApplicationAdapter.getInstance().getApplicationContext().registerReceiver(this.psBroadcastReceiver, localIntentFilter);
    this.isCharging = isCharging(ApplicationAdapter.getInstance().getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
    this.isBTConnected = ((AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio")).isBluetoothScoOn();
  }

  private boolean asyncStopPhraseSpottingWithResumingControl(ResumeControl paramResumeControl)
  {
    monitorenter;
    try
    {
      if (!this.phraseSpotterControl.getState().is(PhraseSpotterControl.State.IDLE))
      {
        disableSpottingRequest();
        this.phraseSpotterControl.stop(paramResumeControl);
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public static void destroy()
  {
    monitorenter;
    try
    {
      if (instance != null)
      {
        ApplicationAdapter.getInstance().getApplicationContext().unregisterReceiver(instance.psBroadcastReceiver);
        Settings.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(instance.psSharedPrefChangeListener);
        instance.stopPhraseSpotting();
        instance = null;
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

  private void disableSpottingRequest()
  {
    this.spottingRequested = false;
    this.phraseSpotterControl.setDialogFlowTaskRegulator(this);
  }

  public static PhraseSpotter getInstance()
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = new PhraseSpotter();
      PhraseSpotter localPhraseSpotter = instance;
      monitorexit;
      return localPhraseSpotter;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private boolean isCharging(Intent paramIntent)
  {
    if (paramIntent.getIntExtra("plugged", -1) != 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  private void startPhraseSpottingInternal()
  {
    if (ClientSuppliedValues.isIUXComplete());
    try
    {
      DialogFlow.getInstance().registerTaskRegulator(DialogFlowTaskRegulator.EventType.RECOGNITION_START, this);
      this.phraseSpotterControl.start(this.phraseSpotterParams, this);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void stopPhraseSpottingInternal()
  {
    this.phraseSpotterControl.stop();
  }

  private void updatePhraseSpotterState()
  {
    ActivityUtil.runOnMainThread(new Runnable()
    {
      public void run()
      {
        boolean bool1 = true;
        boolean bool2 = Settings.getBoolean("car_word_spotter_enabled", bool1);
        boolean bool3 = Settings.getBoolean("car_word_spotter_when_charging_only", false);
        if ((bool2) && (!PhraseSpotter.this.isBTConnected) && ((!bool3) || (PhraseSpotter.this.isCharging)) && (!PhraseSpotter.this.recoInProgress))
        {
          if ((!bool1) || (!PhraseSpotter.this.spottingRequested))
            break label81;
          PhraseSpotter.this.startPhraseSpottingInternal();
        }
        while (true)
        {
          return;
          bool1 = false;
          break;
          label81: PhraseSpotter.this.stopPhraseSpottingInternal();
        }
      }
    });
  }

  public void finalize()
    throws Throwable
  {
    stopPhraseSpotting();
    super.finalize();
  }

  public void init(PhraseSpotterParameters paramPhraseSpotterParameters, PhraseSpotterListener paramPhraseSpotterListener)
  {
    monitorenter;
    try
    {
      this.phraseSpotterParams = paramPhraseSpotterParameters;
      this.listener = paramPhraseSpotterListener;
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

  public boolean isListening()
  {
    monitorenter;
    try
    {
      boolean bool = this.phraseSpotterControl.isSpotting();
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

  public void onPhraseDetected(String paramString)
  {
    AudioPlayerProxy.stop();
    this.listener.onPhraseDetected(paramString);
  }

  public void onPhraseSpotted(String paramString)
  {
    AudioPlayerProxy.stop();
    this.listener.onPhraseSpotted(paramString);
  }

  public void onPhraseSpotterStarted()
  {
    this.listener.onPhraseSpotterStarted();
  }

  public void onPhraseSpotterStopped()
  {
    if (this.listener != null)
      this.listener.onPhraseSpotterStopped();
  }

  public void onSeamlessPhraseSpotted(String paramString, MicrophoneStream paramMicrophoneStream)
  {
    AudioPlayerProxy.stop();
    this.listener.onSeamlessPhraseSpotted(paramString, paramMicrophoneStream);
  }

  public void onTaskWaitingToStart(DialogFlowTaskRegulator.EventType paramEventType, ResumeControl paramResumeControl)
  {
    int i = 1;
    if ((DialogFlowTaskRegulator.EventType.RECOGNITION_START.equals(paramEventType)) && (asyncStopPhraseSpottingWithResumingControl(paramResumeControl)))
      i = 0;
    if (i != 0)
      paramResumeControl.resume();
  }

  public void startPhraseSpotting()
  {
    monitorenter;
    try
    {
      this.spottingRequested = true;
      updatePhraseSpotterState();
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

  public void stopPhraseSpotting()
  {
    monitorenter;
    try
    {
      disableSpottingRequest();
      stopPhraseSpottingInternal();
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

  public void updateParameters(PhraseSpotterParameters paramPhraseSpotterParameters)
  {
    monitorenter;
    try
    {
      this.phraseSpotterParams = paramPhraseSpotterParameters;
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

  private class PSAudioPlaybackListener
    implements IAudioPlaybackService.AudioPlaybackListener
  {
    private PSAudioPlaybackListener()
    {
    }

    public void onRequestCancelled(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonCanceled paramReasonCanceled)
    {
      PhraseSpotter.access$1002(PhraseSpotter.this, false);
      PhraseSpotter.this.updatePhraseSpotterState();
    }

    public void onRequestDidPlay(AudioRequest paramAudioRequest)
    {
      PhraseSpotter.access$1002(PhraseSpotter.this, false);
      PhraseSpotter.this.updatePhraseSpotterState();
    }

    public void onRequestIgnored(AudioRequest paramAudioRequest, IAudioPlaybackService.AudioPlaybackListener.ReasonIgnored paramReasonIgnored)
    {
      PhraseSpotter.access$1002(PhraseSpotter.this, false);
      PhraseSpotter.this.updatePhraseSpotterState();
    }

    public void onRequestWillPlay(AudioRequest paramAudioRequest)
    {
      PhraseSpotter.access$1002(PhraseSpotter.this, true);
      PhraseSpotter.this.updatePhraseSpotterState();
    }
  }

  private class PSBroadcastReceiver extends BroadcastReceiver
  {
    private PSBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str = paramIntent.getAction();
      if ("android.intent.action.BATTERY_CHANGED".equals(str))
      {
        PhraseSpotter.access$302(PhraseSpotter.this, PhraseSpotter.this.isCharging(paramIntent));
        return;
      }
      PhraseSpotter localPhraseSpotter;
      if ("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED".equals(str))
      {
        int i = paramIntent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
        localPhraseSpotter = PhraseSpotter.this;
        if (i != 12)
          break label77;
      }
      label77: for (boolean bool = true; ; bool = false)
      {
        PhraseSpotter.access$202(localPhraseSpotter, bool);
        break;
        break;
      }
    }
  }

  private class PSSharedPreferenceChangeListener
    implements SharedPreferences.OnSharedPreferenceChangeListener
  {
    private PSSharedPreferenceChangeListener()
    {
    }

    public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
    {
      if (paramString == null);
      while (true)
      {
        return;
        if (("car_word_spotter_when_charging_only".equalsIgnoreCase(paramString)) || ("car_word_spotter_enabled".equalsIgnoreCase(paramString)))
        {
          PhraseSpotter.this.updatePhraseSpotterState();
          continue;
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.phrasespotter.PhraseSpotter
 * JD-Core Version:    0.6.0
 */