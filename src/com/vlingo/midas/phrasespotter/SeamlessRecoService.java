package com.vlingo.midas.phrasespotter;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.samsung.voiceshell.MultipleWakeUp;
import com.vlingo.core.internal.CoreAdapterRegistrar;
import com.vlingo.core.internal.CoreAdapterRegistrar.AdapterList;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.audio.MicrophoneStream.AudioSourceType;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.dialogmanager.DialogFlow.DialogFlowState;
import com.vlingo.core.internal.dialogmanager.DialogFlowListener;
import com.vlingo.core.internal.dialogmanager.vvs.VVSDispatcher;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.ListenHandler;
import com.vlingo.core.internal.logging.EventLog;
import com.vlingo.core.internal.phrasespotter.PhraseSpotter;
import com.vlingo.core.internal.phrasespotter.PhraseSpotterListener;
import com.vlingo.core.internal.phrasespotter.PhraseSpotterParameters;
import com.vlingo.core.internal.util.ADMFeatureListener;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.MidasADMController;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.phrasespotter.dialog.BackgroundWidgetBuilder;
import com.vlingo.midas.phrasespotter.dialog.DialogObject;
import com.vlingo.midas.phrasespotter.dialog.SystemTurnObject;
import com.vlingo.midas.phrasespotter.dialog.UserTurnObject;
import com.vlingo.midas.phrasespotter.dialog.WidgetObject;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.tos.TermsOfServiceManager;
import com.vlingo.midas.util.ErrorCodeUtils;
import com.vlingo.sdk.recognition.NBestData;
import com.vlingo.sdk.recognition.VLRecognitionErrors;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import com.vlingo.sdk.recognition.VLRecognitionWarnings;
import java.util.ArrayList;

public class SeamlessRecoService extends Service
  implements DialogFlowListener, PhraseSpotterListener, ADMFeatureListener
{
  public static final String ABORT_RECO = "abort_reco";
  public static final String AUDIO_SOURCE = "bg_audio_source";
  public static final String DISPLAYING_GUI = "speech_display";
  public static final String IDLE = "speech_idle";
  public static final String LISTENING = "speech_listening";
  public static final String LISTEN_CLICKED = "listen_clicked";
  public static final String PASSING_RECO = "speech_passing";
  public static final String PAUSE = "seamless_pause";
  public static final String RESUME = "seamless_resume";
  public static final String RMS_CHANGE = "speech_rms_change";
  public static final String RMS_VALUE = "speech_rms_value";
  public static final int SERVICE_ID = 74269;
  public static final String STATUS = "reco_status";
  public static final String STATUS_CHANGE = "reco_status_change";
  public static final String STOP_SERVICE = "stop_seamlessreco_service";
  public static final String THINKING = "speech_thinking";
  private static ArrayList<DialogObject> dialogObjects;
  private MicrophoneStream.AudioSourceType audioSource = null;
  private boolean hasFlowControl = false;
  private boolean isFirstUtt = true;
  private PauseReceiver pauseReceiver;
  private boolean startRecoOnSpotterStop = false;
  private ActivityStateReceiver stateReceiver;
  private TelephonyManager telephonyManager;

  private void abortReco()
  {
    DialogFlow.getInstance().cancelAudio();
    DialogFlow.getInstance().cancelTurn();
  }

  public static ArrayList<DialogObject> getDialogObjects()
  {
    return dialogObjects;
  }

  @TargetApi(16)
  private Notification getNotification()
  {
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("stop_seamlessreco_service"), 0);
    Notification.Builder localBuilder = new Notification.Builder(this).setContentTitle(getString(2131362708)).setContentText(getString(2131362709)).setSmallIcon(2130837958).setWhen(0L).setContentIntent(localPendingIntent);
    if (Build.VERSION.SDK_INT >= 16);
    for (Notification localNotification = localBuilder.setPriority(-1).build(); ; localNotification = localBuilder.getNotification())
      return localNotification;
  }

  private void initPhraseSpotter()
  {
    if (MidasSettings.getBoolean("samsung_wakeup_engine_enable", false))
    {
      CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.PhraseSpotter, SamsungPhraseSpotter.class);
      if (this.audioSource != null);
      for (localPhraseSpotterParameters = SamsungPhraseSpotter.getPhraseSpotterParameters(this.audioSource); ; localPhraseSpotterParameters = SamsungPhraseSpotter.getPhraseSpotterParameters())
      {
        PhraseSpotter.getInstance().init(localPhraseSpotterParameters, this);
        return;
      }
    }
    if (MidasSettings.getBoolean("samsung_multi_engine_enable", false))
    {
      CoreAdapterRegistrar.registerHandler(CoreAdapterRegistrar.AdapterList.PhraseSpotter, SamsungPhraseSpotter.class);
      if (this.audioSource != null);
      for (localPhraseSpotterParameters = PhraseSpotterUtil.getPhraseSpotterParameters(getResources(), this.audioSource); ; localPhraseSpotterParameters = PhraseSpotterUtil.getPhraseSpotterParameters(getResources()))
        break;
    }
    if (this.audioSource != null);
    for (PhraseSpotterParameters localPhraseSpotterParameters = PhraseSpotterUtil.getPhraseSpotterParameters(getResources(), this.audioSource); ; localPhraseSpotterParameters = PhraseSpotterUtil.getPhraseSpotterParameters(getResources()))
      break;
  }

  private void pause()
  {
    PhraseSpotter.getInstance().stopPhraseSpotting();
  }

  private void resume()
  {
    initPhraseSpotter();
    startSpotting();
  }

  private void startRecognition(MicrophoneStream paramMicrophoneStream)
  {
    if (PhraseSpotter.getInstance().isListening())
    {
      this.startRecoOnSpotterStop = true;
      PhraseSpotter.getInstance().stopPhraseSpotting();
      return;
    }
    PhraseSpotter.getInstance().stopPhraseSpotting();
    boolean bool1 = BluetoothManager.isHeadsetConnected();
    boolean bool2 = BluetoothManager.isBluetoothAudioSupported();
    if ((bool1 != true) || (!bool2))
      AudioFocusManager.getInstance(this).requestAudioFocus(3, 2);
    while (true)
    {
      ActivityUtil.runOnMainThread(new Runnable(paramMicrophoneStream)
      {
        public void run()
        {
          DialogFlow.getInstance().startUserFlow(this.val$micStream);
        }
      });
      break;
      AudioFocusManager.getInstance(this).requestAudioFocus(6, 2);
    }
  }

  private void startSpotting()
  {
    this.isFirstUtt = true;
    dialogObjects = new ArrayList();
    try
    {
      PhraseSpotter.getInstance().startPhraseSpotting();
      label21: return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      break label21;
    }
  }

  public <T> void addWidget(WidgetObject<T> paramWidgetObject)
  {
    dialogObjects.add(paramWidgetObject);
    if (paramWidgetObject.mustBeShown())
    {
      Intent localIntent = new Intent("reco_status_change");
      localIntent.putExtra("reco_status", "speech_display");
      sendBroadcast(localIntent);
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    this.pauseReceiver = new PauseReceiver(null);
    IntentFilter localIntentFilter1 = new IntentFilter("seamless_pause");
    localIntentFilter1.addAction("seamless_resume");
    localIntentFilter1.addAction("stop_seamlessreco_service");
    registerReceiver(this.pauseReceiver, localIntentFilter1);
    this.stateReceiver = new ActivityStateReceiver(null);
    IntentFilter localIntentFilter2 = new IntentFilter("abort_reco");
    localIntentFilter2.addAction("listen_clicked");
    registerReceiver(this.stateReceiver, localIntentFilter2);
    this.telephonyManager = ((TelephonyManager)getSystemService("phone"));
    this.telephonyManager.listen(new SeamlessPhoneStateListener(null), 32);
    SeamlessRecoActivity.init(getBaseContext());
    if (ClientSuppliedValues.isDrivingModeEnabled())
    {
      MidasSettings.setInt("widget_display_max", 3);
      if (!ClientSuppliedValues.isTalkbackOn())
        break label164;
      VVSDispatcher.unregisterHandler("Listen");
    }
    while (true)
    {
      return;
      MidasSettings.setInt("widget_display_max", 6);
      break;
      label164: VVSDispatcher.registerHandler("Listen", ListenHandler.class);
    }
  }

  public void onDestroy()
  {
    unregisterReceiver(this.pauseReceiver);
    unregisterReceiver(this.stateReceiver);
    pause();
    PhraseSpotter.destroy();
    super.onDestroy();
  }

  public boolean onInterceptStartReco()
  {
    int i = 0;
    if (!this.isFirstUtt)
    {
      Intent localIntent = new Intent("reco_status_change");
      localIntent.putExtra("reco_status", "speech_passing");
      sendBroadcast(localIntent);
      this.hasFlowControl = false;
      i = 1;
    }
    while (true)
    {
      return i;
      this.isFirstUtt = false;
    }
  }

  public void onPhraseDetected(String paramString)
  {
    Intent localIntent = new Intent(this, SeamlessRecoActivity.class);
    localIntent.addFlags(268435456);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    DialogFlow.getInstance().initFlow(this, this, null, new BackgroundWidgetBuilder(this));
    this.hasFlowControl = true;
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
      if (i != -1)
      {
        Intent localIntent = MultipleWakeUp.getMultipleWakeUpIntent(i, this);
        if (localIntent != null)
        {
          startActivity(localIntent);
          PhraseSpotter.getInstance().stopPhraseSpotting();
        }
      }
      return;
      j = 0;
      break;
      this.startRecoOnSpotterStop = true;
      PhraseSpotter.getInstance().stopPhraseSpotting();
      continue;
      i = MidasSettings.getInt("kew_wake_up_and_auto_function1", -1);
      continue;
      i = MidasSettings.getInt("kew_wake_up_and_auto_function2", -1);
      continue;
      i = MidasSettings.getInt("kew_wake_up_and_auto_function3", -1);
      continue;
      i = MidasSettings.getInt("kew_wake_up_and_auto_function4", -1);
    }
  }

  public void onPhraseSpotterStarted()
  {
    startForeground(74269, getNotification());
  }

  public void onPhraseSpotterStopped()
  {
    stopForeground(true);
    if (this.startRecoOnSpotterStop)
      ActivityUtil.runOnMainThread(new Runnable()
      {
        public void run()
        {
          SeamlessRecoService.access$302(SeamlessRecoService.this, false);
          SeamlessRecoService.this.startRecognition(null);
        }
      });
  }

  public void onRecoCancelled()
  {
  }

  public long onRecoToneStarting()
  {
    return 0L;
  }

  public void onResultsNoAction()
  {
  }

  public void onSeamlessPhraseSpotted(String paramString, MicrophoneStream paramMicrophoneStream)
  {
    ActivityUtil.runOnMainThread(new Runnable(paramMicrophoneStream)
    {
      public void run()
      {
        DialogFlow.getInstance().startUserFlow(this.val$micStream);
      }
    });
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if ((paramIntent != null) && (paramIntent.hasExtra("bg_audio_source")) && (!StringUtils.isNullOrWhiteSpace(paramIntent.getStringExtra("bg_audio_source"))));
    try
    {
      this.audioSource = MicrophoneStream.AudioSourceType.valueOf(paramIntent.getStringExtra("bg_audio_source"));
      if (TermsOfServiceManager.isTOSRequired())
      {
        Intent localIntent = new Intent(this, ConversationActivity.class);
        localIntent.addFlags(268435456);
        startActivity(localIntent);
        return 1;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
      {
        Log.e("SeamlessRecoService", "Invalid AudioSourceType provided. Using default value.");
        continue;
        resume();
      }
    }
  }

  public void onStatusChanged(String paramString, boolean paramBoolean)
  {
    if (paramString.equals(MidasADMController.AGGRESSIVE_NOISE_CANCELLATION))
      if (!paramBoolean);
    while (true)
    {
      return;
      if (paramString.equals(MidasADMController.ENDPOINT_DETECTION))
      {
        if (!paramBoolean)
          continue;
        continue;
      }
      if (paramString.equals(MidasADMController.EYES_FREE))
      {
        if (paramBoolean)
        {
          DialogFlow.getInstance().addUserProperties("isEyesFree", "true");
          continue;
        }
        DialogFlow.getInstance().removeUserProperties("isEyesFree");
        continue;
      }
      if (paramString.equals(MidasADMController.DRIVING_MODE_GUI))
      {
        if (paramBoolean)
        {
          MidasSettings.setInt("widget_display_max", 3);
          continue;
        }
        MidasSettings.setInt("widget_display_max", 6);
        continue;
      }
      if (!paramString.equals(MidasADMController.TALKBACK))
        continue;
      if (paramBoolean)
      {
        VVSDispatcher.unregisterHandler("Listen");
        continue;
      }
      VVSDispatcher.registerHandler("Listen", ListenHandler.class);
    }
  }

  public void showDialogFlowStateChange(DialogFlow.DialogFlowState paramDialogFlowState)
  {
    switch (5.$SwitchMap$com$vlingo$core$internal$dialogmanager$DialogFlow$DialogFlowState[paramDialogFlowState.ordinal()])
    {
    default:
    case 1:
    }
    while (true)
    {
      return;
      Intent localIntent = new Intent("reco_status_change");
      localIntent.putExtra("reco_status", "speech_idle");
      sendBroadcast(localIntent);
      if (!this.hasFlowControl)
        continue;
      this.hasFlowControl = false;
      DialogFlow.getInstance().releaseFlow(this);
      startSpotting();
    }
  }

  public void showError(VLRecognitionErrors paramVLRecognitionErrors, String paramString)
  {
    if (ConversationActivity.shouldReportError(paramVLRecognitionErrors))
      Toast.makeText(this, ErrorCodeUtils.getLocalizedMessageForErrorCode(paramVLRecognitionErrors, this), 1).show();
  }

  public void showRMSChange(int paramInt)
  {
    Intent localIntent = new Intent("speech_rms_change");
    localIntent.putExtra("speech_rms_value", paramInt);
    sendBroadcast(localIntent);
  }

  public void showReceivedResults(EventLog paramEventLog)
  {
  }

  public void showRecoStateChange(VLRecognitionStates paramVLRecognitionStates)
  {
    ActivityUtil.runOnMainThread(new Runnable(paramVLRecognitionStates)
    {
      public void run()
      {
        Intent localIntent = new Intent("reco_status_change");
        switch (SeamlessRecoService.5.$SwitchMap$com$vlingo$sdk$recognition$VLRecognitionStates[this.val$newState.ordinal()])
        {
        default:
        case 1:
        case 2:
        }
        while (true)
        {
          return;
          localIntent.putExtra("reco_status", "speech_listening");
          SeamlessRecoService.this.sendBroadcast(localIntent);
          continue;
          localIntent.putExtra("reco_status", "speech_thinking");
          SeamlessRecoService.this.sendBroadcast(localIntent);
        }
      }
    });
  }

  public void showUserText(String paramString, NBestData paramNBestData)
  {
    dialogObjects.add(new UserTurnObject(paramString, paramNBestData));
  }

  public void showVlingoText(String paramString)
  {
    dialogObjects.add(new SystemTurnObject(paramString));
  }

  public void showWarning(VLRecognitionWarnings paramVLRecognitionWarnings, String paramString)
  {
    if (paramVLRecognitionWarnings != VLRecognitionWarnings.WARNING_NOTHING_RECOGNIZED)
      Toast.makeText(this, paramString, 1).show();
  }

  public void userCancel()
  {
  }

  private class ActivityStateReceiver extends BroadcastReceiver
  {
    private ActivityStateReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("abort_reco"))
        SeamlessRecoService.this.abortReco();
      while (true)
      {
        return;
        if (paramIntent.getAction().equals("listen_clicked"))
        {
          DialogFlow.getInstance().endpointReco();
          continue;
        }
      }
    }
  }

  private class PauseReceiver extends BroadcastReceiver
  {
    private PauseReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("seamless_pause"))
        SeamlessRecoService.this.pause();
      while (true)
      {
        return;
        if (paramIntent.getAction().equals("seamless_resume"))
        {
          SeamlessRecoService.this.resume();
          continue;
        }
        if (!paramIntent.getAction().equals("stop_seamlessreco_service"))
          continue;
        SeamlessRecoService.this.stopSelf();
      }
    }
  }

  private class SeamlessPhoneStateListener extends PhoneStateListener
  {
    private SeamlessPhoneStateListener()
    {
    }

    public void onCallStateChanged(int paramInt, String paramString)
    {
      switch (paramInt)
      {
      default:
      case 0:
      case 2:
      case 1:
      }
      while (true)
      {
        return;
        SeamlessRecoService.this.resume();
        continue;
        SeamlessRecoService.this.pause();
        continue;
        SeamlessRecoService.this.pause();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.phrasespotter.SeamlessRecoService
 * JD-Core Version:    0.6.0
 */