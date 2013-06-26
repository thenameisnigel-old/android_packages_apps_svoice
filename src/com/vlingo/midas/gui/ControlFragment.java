package com.vlingo.midas.gui;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.voiceshell.MultipleWakeUp;
import com.samsung.wakeupsetting.CustomCommandRecordingActivity;
import com.sec.android.svoice.equalizer.MicEqualizerView;
import com.vlingo.core.internal.CoreAdapterRegistrar;
import com.vlingo.core.internal.CoreAdapterRegistrar.AdapterList;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.display.WakeLockManager;
import com.vlingo.core.internal.phrasespotter.PhraseSpotter;
import com.vlingo.core.internal.phrasespotter.PhraseSpotterListener;
import com.vlingo.core.internal.phrasespotter.PhraseSpotterParameters;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.settings.VoicePrompt;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.midas.VlingoApplication;
import com.vlingo.midas.iux.IUXManager;
import com.vlingo.midas.phrasespotter.PhraseSpotterUtil;
import com.vlingo.midas.phrasespotter.SamsungPhraseSpotter;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.CheckPhoneEvents;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public abstract class ControlFragment extends Fragment
  implements PhraseSpotterListener
{
  public static final long BT_START_REC_DELAY_TIME = 2000L;
  private static final int MSG_START_SPOTTER;
  private String TAG = "ControlFragment";
  private FocusChangeBroadcastReceiver audioFocusChangeBroadcastReceiver;
  protected ControlFragmentCallback callback;
  private boolean mActivityCreated = false;
  private boolean mActivityPaused = false;
  private ControlFragmentHandler mHandler = new ControlFragmentHandler(this);
  private KeyguardManager mKm;
  private Runnable mTaskOnFinishGreetingTTS;
  private boolean m_isRecognitionPostBTConnect = false;
  private BroadcastReceiver playMusicBroadcastReceiver;
  private WakeLockManager wakeLockManager;

  public static int getRandom(int paramInt1, int paramInt2)
  {
    return (int)(paramInt1 + Math.floor(Math.random() * (paramInt2 + 1 - paramInt1)));
  }

  private ArrayList<String> getTTSOnIdleList()
  {
    return ControlFragmentData.getIntance().getTTSOnIdleList();
  }

  private boolean isActivityPaused()
  {
    return this.mActivityPaused;
  }

  private boolean isPaused()
  {
    return ControlFragmentData.getIntance().isPaused();
  }

  private boolean isStartRecoOnSpotterStop()
  {
    return ControlFragmentData.getIntance().isStartRecoOnSpotterStop();
  }

  private void setPaused(boolean paramBoolean)
  {
    ControlFragmentData.getIntance().setPaused(paramBoolean);
  }

  private void setStartRecoOnSpotterStop(boolean paramBoolean)
  {
    ControlFragmentData.getIntance().setStartRecoOnSpotterStop(paramBoolean);
  }

  private void setTTSOnIdleList(ArrayList<String> paramArrayList)
  {
    ControlFragmentData.getIntance().setTTSOnIdleList(paramArrayList);
  }

  public void announceTTS(String paramString)
  {
    DialogFlow.getInstance().tts(paramString);
  }

  public void announceTTSOnDialogIdle(String paramString)
  {
    if (DialogFlow.getInstance().isIdle())
      announceTTS(paramString);
    while (true)
    {
      return;
      getTTSOnIdleList().add(paramString);
    }
  }

  public void avoidWakeUpCommandTTS()
  {
    if (PhraseSpotter.getInstance().isListening())
    {
      stopSpotting();
      scheduleStartSpotter(1000L);
    }
  }

  public void cancelTTS()
  {
    DialogFlow.getInstance().cancelTTS();
  }

  public boolean checkMissedEvents()
  {
    boolean bool1 = Settings.getBoolean("listen_over_bluetooth", true);
    boolean bool2 = Settings.getBoolean("headset_mode", true);
    AudioManager localAudioManager = (AudioManager)getActivity().getSystemService("audio");
    int i = 0;
    if ((bool1) && (BluetoothManager.isHeadsetConnected()))
      if (!BluetoothManager.isBluetoothAudioOn())
      {
        if (bool2)
          this.m_isRecognitionPostBTConnect = true;
        i = 1;
        BluetoothManager.runTaskOnBluetoothAudioOn(new Runnable()
        {
          public void run()
          {
            if (!ControlFragment.this.isActivityCreated());
            while (true)
            {
              return;
              if (Settings.getBoolean("headset_mode", true))
              {
                CheckPhoneEvents.getInstance(ControlFragment.this).check();
                ControlFragment.access$302(ControlFragment.this, false);
              }
              ControlFragment.this.startRecognition(null);
            }
          }
        }
        , 2000L);
      }
    while (true)
    {
      return i;
      if (!bool2)
        continue;
      CheckPhoneEvents.getInstance(this).check();
      continue;
      if ((!localAudioManager.isWiredHeadsetOn()) || (!bool2))
        continue;
      CheckPhoneEvents.getInstance(this).check();
    }
  }

  protected void disableHelpWidgetButton()
  {
  }

  protected void enableHelpWidgetButton()
  {
  }

  protected Context getContext()
  {
    if (getActivity() != null);
    for (Context localContext = getActivity().getBaseContext(); ; localContext = VlingoApplication.getInstance().getApplicationContext())
      return localContext;
  }

  public ControlState getControlFragmentState()
  {
    return getCurrentState();
  }

  public ControlState getCurrentState()
  {
    return ControlFragmentData.getIntance().getCurrentState();
  }

  public PhraseSpotterParameters getPhraseSpotterParams()
  {
    PhraseSpotterParameters localPhraseSpotterParameters;
    if (MidasSettings.getBoolean("samsung_wakeup_engine_enable", false))
    {
      CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.PhraseSpotter, SamsungPhraseSpotter.class);
      localPhraseSpotterParameters = SamsungPhraseSpotter.getPhraseSpotterParameters();
    }
    while (true)
    {
      return localPhraseSpotterParameters;
      if (MidasSettings.getBoolean("samsung_multi_engine_enable", false))
      {
        CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.PhraseSpotter, SamsungPhraseSpotter.class);
        localPhraseSpotterParameters = PhraseSpotterUtil.getPhraseSpotterParameters(getResources());
        continue;
      }
      localPhraseSpotterParameters = PhraseSpotterUtil.getPhraseSpotterParameters(getResources());
    }
  }

  protected VoicePrompt getVoicePrompt()
  {
    return new VoicePrompt().registerDelegate(new VoicePromptUserConfirmation(getActivity()));
  }

  protected void initPhraseSpotter()
  {
    PhraseSpotter.getInstance().init(getPhraseSpotterParams(), this);
  }

  public boolean isActivityCreated()
  {
    if ((getActivity() != null) && (ismActivityCreated()));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected boolean ismActivityCreated()
  {
    return this.mActivityCreated;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.wakeLockManager = WakeLockManagerMidas.getInstance();
    this.mKm = ((KeyguardManager)getActivity().getSystemService("keyguard"));
    getResources();
    Settings.getBoolean("car_word_spotter_enabled", true);
    this.audioFocusChangeBroadcastReceiver = new FocusChangeBroadcastReceiver(null);
    this.playMusicBroadcastReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramContext, Intent paramIntent)
      {
        ControlFragment.this.audioFocusChangeBroadcastReceiver.setOnAudioFocusLossTask(new ControlFragment.1.1(this));
      }
    };
    IntentFilter localIntentFilter1 = new IntentFilter();
    localIntentFilter1.addAction("com.vlingo.client.app.action.AUDIO_FOCUS_CHANGED");
    getContext().registerReceiver(this.audioFocusChangeBroadcastReceiver, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter();
    localIntentFilter2.addAction("com.sec.android.app.music.musicservicecommand.pause");
    getContext().registerReceiver(this.playMusicBroadcastReceiver, localIntentFilter2);
  }

  public abstract View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle);

  public void onDestroy()
  {
    if (this.audioFocusChangeBroadcastReceiver != null)
    {
      getContext().unregisterReceiver(this.audioFocusChangeBroadcastReceiver);
      this.audioFocusChangeBroadcastReceiver = null;
    }
    if (this.playMusicBroadcastReceiver != null)
    {
      getContext().unregisterReceiver(this.playMusicBroadcastReceiver);
      this.playMusicBroadcastReceiver = null;
    }
    super.onDestroy();
  }

  public void onIdle()
  {
  }

  public void onLanguageChanged()
  {
  }

  public void onPause()
  {
    super.onPause();
    setPaused(true);
    PhraseSpotter.destroy();
  }

  public void onPhraseDetected(String paramString)
  {
  }

  public void onPhraseSpotted(String paramString)
  {
    int i = -1;
    int j;
    if (PhraseSpotterUtil.isCustomWakeupWordPhrase(paramString))
    {
      j = Integer.parseInt(paramString);
      switch (j)
      {
      default:
        label56: if (i != -1)
        {
          Intent localIntent = MultipleWakeUp.getMultipleWakeUpIntent(i, getContext());
          if (localIntent == null)
            break;
          startActivity(localIntent);
          stopSpotting();
        }
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
    }
    while (true)
    {
      return;
      j = 0;
      break;
      setStartRecoOnSpotterStop(true);
      break label56;
      setStartRecoOnSpotterStop(true);
      break label56;
      i = MidasSettings.getInt("kew_wake_up_and_auto_function1", -1);
      break label56;
      i = MidasSettings.getInt("kew_wake_up_and_auto_function2", -1);
      break label56;
      i = MidasSettings.getInt("kew_wake_up_and_auto_function3", -1);
      break label56;
      i = MidasSettings.getInt("kew_wake_up_and_auto_function4", -1);
      break label56;
      stopSpotting();
    }
  }

  public void onPhraseSpotterStarted()
  {
    this.callback.onStartSpotting();
  }

  public void onPhraseSpotterStopped()
  {
    if (isStartRecoOnSpotterStop())
    {
      setStartRecoOnSpotterStop(false);
      ActivityUtil.scheduleOnMainThread(new Runnable()
      {
        public void run()
        {
          ControlFragment.this.callback.onStopSpotting();
          ControlFragment.this.startRecognition(null);
        }
      });
    }
    while (true)
    {
      return;
      ActivityUtil.scheduleOnMainThread(new Runnable()
      {
        public void run()
        {
          ControlFragment.this.callback.onStopSpotting();
        }
      });
    }
  }

  public void onPromptOffClick(boolean paramBoolean)
  {
    VoicePrompt localVoicePrompt = getVoicePrompt();
    if (!localVoicePrompt.isOn())
    {
      if (paramBoolean)
        MidasSettings.setBoolean("manually_prompt_on_in_talkback", true);
      localVoicePrompt.setManually(paramBoolean);
      localVoicePrompt.on();
    }
  }

  public void onPromptOnClick(boolean paramBoolean)
  {
    VoicePrompt localVoicePrompt = getVoicePrompt();
    if (localVoicePrompt.isOn())
    {
      MidasSettings.setBoolean("manually_prompt_on_in_talkback", false);
      localVoicePrompt.setManually(paramBoolean);
      localVoicePrompt.off();
    }
  }

  public void onResume()
  {
    super.onResume();
    setPaused(false);
    if (getCurrentState() == ControlState.IDLE)
    {
      if (Settings.getBoolean("key_social_login_attemp_for_resume", false))
        break label51;
      if (!(getActivity() instanceof CustomCommandRecordingActivity))
        scheduleStartSpotter(300L);
    }
    while (true)
    {
      setPrompt();
      return;
      label51: Settings.setBoolean("key_social_login_attemp_for_resume", false);
    }
  }

  public void onSeamlessPhraseSpotted(String paramString, MicrophoneStream paramMicrophoneStream)
  {
    stopSpotting();
    ActivityUtil.scheduleOnMainThread(new Runnable(paramMicrophoneStream)
    {
      public void run()
      {
        ControlFragment.this.callback.onStopSpotting();
        ControlFragment.this.startRecognition(this.val$micStream);
      }
    });
  }

  public abstract void onSpectrumUpdate(MicEqualizerView paramMicEqualizerView, int[] paramArrayOfInt);

  public void onStart()
  {
    super.onStart();
    ClientSuppliedValues.getForegroundFocus(DialogFlow.getInstance());
  }

  public void onStop()
  {
    super.onStop();
    ClientSuppliedValues.releaseForegroundFocus(DialogFlow.getInstance());
  }

  abstract boolean performClickFromDriveControl();

  protected void resetThinkingState()
  {
  }

  public void scheduleStartSpotter(long paramLong)
  {
    this.mHandler.removeMessages(0);
    this.mHandler.sendEmptyMessageDelayed(0, paramLong);
  }

  public void setActivityPaused(boolean paramBoolean)
  {
    this.mActivityPaused = paramBoolean;
  }

  public abstract void setBlueHeight(int paramInt);

  protected void setButtonIdle(boolean paramBoolean)
  {
  }

  protected void setButtonListening()
  {
  }

  protected void setButtonThinking()
  {
  }

  public void setCallback(ControlFragmentCallback paramControlFragmentCallback)
  {
    this.callback = paramControlFragmentCallback;
  }

  public void setCurrentState(ControlState paramControlState)
  {
    ControlFragmentData.getIntance().setCurrentState(paramControlState);
  }

  protected void setPrompt()
  {
  }

  void setState(ControlState paramControlState)
  {
    Log.w("Vlingo", "setState: " + paramControlState.name());
    if (!isActivityCreated());
    while (true)
    {
      return;
      if (!isActivityPaused())
        break;
      AudioFocusManager.getInstance(getContext()).abandonAudioFocus();
      BluetoothManager.stopScoOnIdle();
    }
    switch (7.$SwitchMap$com$vlingo$midas$gui$ControlFragment$ControlState[paramControlState.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    }
    while (true)
    {
      setCurrentState(paramControlState);
      break;
      if (this.mTaskOnFinishGreetingTTS != null)
      {
        ActivityUtil.scheduleOnMainThread(this.mTaskOnFinishGreetingTTS);
        this.mTaskOnFinishGreetingTTS = null;
        break;
      }
      enableHelpWidgetButton();
      resetThinkingState();
      setButtonIdle(true);
      while (!getTTSOnIdleList().isEmpty())
        announceTTS((String)getTTSOnIdleList().remove(0));
      AudioFocusManager.getInstance(getContext()).abandonAudioFocus();
      BluetoothManager.stopScoOnIdle();
      if ((getCurrentState() != ControlState.IDLE) && (!isPaused()))
        scheduleStartSpotter(300L);
      this.wakeLockManager.releaseWakeLock();
      continue;
      setButtonIdle(false);
      setButtonListening();
      this.wakeLockManager.acquireWakeLock();
      continue;
      setButtonListening();
      disableHelpWidgetButton();
      resetThinkingState();
      this.wakeLockManager.acquireWakeLock();
      continue;
      disableHelpWidgetButton();
      setButtonThinking();
      resetThinkingState();
      setThinkingAnimation();
      this.wakeLockManager.acquireWakeLock();
      continue;
      resetThinkingState();
      continue;
      stopSpotting();
      continue;
      if (PhraseSpotter.getInstance().isListening())
        stopSpotting();
      resetThinkingState();
      if (getCurrentState() == ControlState.ASR_GETTING_READY)
        continue;
      setButtonIdle(true);
    }
  }

  public void setTaskOnFinishGreetingTTS(Runnable paramRunnable)
  {
    this.mTaskOnFinishGreetingTTS = paramRunnable;
  }

  protected void setThinkingAnimation()
  {
  }

  protected void setmActivityCreated(boolean paramBoolean)
  {
    this.mActivityCreated = paramBoolean;
  }

  public abstract void showRMSChange(int paramInt);

  public abstract void showSpectrum(int[] paramArrayOfInt);

  public void startRecognition(MicrophoneStream paramMicrophoneStream)
  {
    if (isPaused());
    while (true)
    {
      return;
      if ((!this.m_isRecognitionPostBTConnect) && (isActivityCreated()))
      {
        this.callback.onRecognitionStarted();
        if (PhraseSpotter.getInstance().isListening())
        {
          setStartRecoOnSpotterStop(true);
          stopSpotting();
          continue;
        }
        PhraseSpotter.getInstance().stopPhraseSpotting();
        Context localContext = getContext();
        boolean bool1 = BluetoothManager.isHeadsetConnected();
        boolean bool2 = BluetoothManager.isBluetoothAudioSupported();
        if ((bool1 != true) || (bool2 != true))
          AudioFocusManager.getInstance(localContext).requestAudioFocus(3, 2);
        while (true)
        {
          if (!DialogFlow.getInstance().startUserFlow(paramMicrophoneStream))
            break label139;
          if ((bool1 == true) && (bool2 == true))
            break;
          setState(ControlState.ASR_GETTING_READY);
          break;
          AudioFocusManager.getInstance(localContext).requestAudioFocus(6, 2);
          BluetoothManager.startScoOnStartRecognition();
        }
        label139: setState(ControlState.IDLE);
        continue;
      }
    }
  }

  public void startSpotting()
  {
    setPrompt();
    ActivityUtil.scheduleOnMainThread(new Runnable()
    {
      public void run()
      {
        ControlFragment.this.enableHelpWidgetButton();
      }
    });
    if ((this.mKm.inKeyguardRestrictedInputMode()) || ((BluetoothManager.isHeadsetConnected()) && (BluetoothManager.isBluetoothAudioSupported())));
    while (true)
    {
      return;
      try
      {
        if (BluetoothManager.isBluetoothAudioOn())
          continue;
        PhraseSpotter.getInstance().startPhraseSpotting();
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
    }
  }

  protected void stopSpotting()
  {
    this.mHandler.removeMessages(0);
    PhraseSpotter.getInstance().stopPhraseSpotting();
  }

  public static abstract interface ControlFragmentCallback
  {
    public abstract void onRecognitionStarted();

    public abstract void onStartSpotting();

    public abstract void onStopSpotting();
  }

  private static class ControlFragmentHandler extends Handler
  {
    private final WeakReference<ControlFragment> outer;

    ControlFragmentHandler(ControlFragment paramControlFragment)
    {
      this.outer = new WeakReference(paramControlFragment);
    }

    public void handleMessage(Message paramMessage)
    {
      ControlFragment localControlFragment = (ControlFragment)this.outer.get();
      if (localControlFragment != null)
        switch (paramMessage.what)
        {
        default:
        case 0:
        }
      while (true)
      {
        return;
        if ((localControlFragment.getControlFragmentState() == ControlFragment.ControlState.IDLE) && (!localControlFragment.isPaused()))
        {
          localControlFragment.startSpotting();
          continue;
        }
      }
    }
  }

  public static enum ControlState
  {
    static
    {
      ASR_GETTING_READY = new ControlState("ASR_GETTING_READY", 1);
      ASR_LISTENING = new ControlState("ASR_LISTENING", 2);
      ASR_THINKING = new ControlState("ASR_THINKING", 3);
      ASR_POST_RESPONSE = new ControlState("ASR_POST_RESPONSE", 4);
      ASR_EDITING = new ControlState("ASR_EDITING", 5);
      DIALOG_BUSY = new ControlState("DIALOG_BUSY", 6);
      ControlState[] arrayOfControlState = new ControlState[7];
      arrayOfControlState[0] = IDLE;
      arrayOfControlState[1] = ASR_GETTING_READY;
      arrayOfControlState[2] = ASR_LISTENING;
      arrayOfControlState[3] = ASR_THINKING;
      arrayOfControlState[4] = ASR_POST_RESPONSE;
      arrayOfControlState[5] = ASR_EDITING;
      arrayOfControlState[6] = DIALOG_BUSY;
      $VALUES = arrayOfControlState;
    }
  }

  private class FocusChangeBroadcastReceiver extends BroadcastReceiver
  {
    private Runnable audioFocusLossTask;

    private FocusChangeBroadcastReceiver()
    {
    }

    private void resetTask()
    {
      this.audioFocusLossTask = null;
    }

    private void runAudioFocusLossTask()
    {
      if (this.audioFocusLossTask != null)
      {
        this.audioFocusLossTask.run();
        resetTask();
      }
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      switch (paramIntent.getIntExtra("com.vlingo.client.app.extra.FOCUS_CHANGE", -1))
      {
      case 0:
      default:
      case 1:
      case 2:
      case 3:
      case -1:
      }
      while (true)
      {
        return;
        if (!IUXManager.isIUXComplete())
          continue;
        FragmentActivity localFragmentActivity = ControlFragment.this.getActivity();
        if ((localFragmentActivity == null) || (localFragmentActivity.isFinishing()))
          continue;
        continue;
        if (ControlFragment.this.getCurrentState() == ControlFragment.ControlState.ASR_LISTENING)
          DialogFlow.getInstance().cancelTurn();
        runAudioFocusLossTask();
      }
    }

    public void setOnAudioFocusLossTask(Runnable paramRunnable)
    {
      this.audioFocusLossTask = paramRunnable;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.ControlFragment
 * JD-Core Version:    0.6.0
 */