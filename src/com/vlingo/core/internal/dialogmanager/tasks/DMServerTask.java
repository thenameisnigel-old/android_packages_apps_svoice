package com.vlingo.core.internal.dialogmanager.tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.raw;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioType;
import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.audio.MicrophoneStream.TaskType;
import com.vlingo.core.internal.audio.TonePlayer;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.bluetooth.OnBluetoothAudioOnTimeoutTask;
import com.vlingo.core.internal.dialogmanager.DialogFieldID;
import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.RecoContext;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.PhoneUtil;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.internal.recognizer.reader.DataReadyListner;
import com.vlingo.sdk.recognition.VLRecognitionErrors;
import com.vlingo.sdk.recognition.VLRecognitionListener;
import com.vlingo.sdk.recognition.VLRecognitionResult;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import com.vlingo.sdk.recognition.VLRecognitionWarnings;
import com.vlingo.sdk.recognition.VLRecognizer;
import com.vlingo.sdk.recognition.dialog.VLDialogContext;
import com.vlingo.sdk.recognition.dialog.VLDialogContext.Builder;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class DMServerTask extends PausableTask
{
  public static AudioType AUDIO_TYPE;
  public static final long BT_START_DELAY_TIME = 2000L;
  private static int processingToneFadeOut = -1;
  private boolean awaitingStopTone = false;
  private boolean completedReco = false;
  private final VLDialogContext.Builder contextBuilder = new VLDialogContext.Builder();
  private DataReadyListner dataReadyListner = new DataReadyListner();
  private boolean gotResults = false;
  private MicrophoneStream micStream;
  private OnBluetoothAudioOnTimeoutTask onBtOnTask = null;
  private boolean performingReco = false;
  private final VLRecognitionListener recoListener;
  private boolean sendingTextRequest = false;
  private String textRequest;
  private final DialogTurn turn;
  private final boolean useAudioTrackTonePlayer = Settings.getBoolean("use_audiotrack_tone_player", false);
  private final boolean useMediaSyncApproach = Settings.getBoolean("use_mediasync_tone_approach", false);
  private HashMap<String, String> userProperties;

  public DMServerTask(DialogTurn paramDialogTurn, VLRecognitionListener paramVLRecognitionListener, HashMap<String, String> paramHashMap)
  {
    if (paramDialogTurn == null)
      RecoContext.init(this.contextBuilder, null, -1, null);
    while (true)
    {
      this.userProperties = paramHashMap;
      this.turn = paramDialogTurn;
      this.recoListener = paramVLRecognitionListener;
      if (processingToneFadeOut == -1)
        processingToneFadeOut = Settings.getInt("processing_tone_fadeout_period", 0);
      return;
      RecoContext.init(this.contextBuilder, paramDialogTurn.getGUID(), paramDialogTurn.getTurnNumber(), paramDialogTurn.getServerState());
    }
  }

  // ERROR //
  private void closeMicStream()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 152	com/vlingo/core/internal/dialogmanager/tasks/DMServerTask:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   4: ifnull +15 -> 19
    //   7: aload_0
    //   8: getfield 152	com/vlingo/core/internal/dialogmanager/tasks/DMServerTask:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   11: invokevirtual 181	com/vlingo/core/internal/audio/MicrophoneStream:close	()V
    //   14: aload_0
    //   15: aconst_null
    //   16: putfield 152	com/vlingo/core/internal/dialogmanager/tasks/DMServerTask:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   19: return
    //   20: astore_2
    //   21: aload_0
    //   22: aconst_null
    //   23: putfield 152	com/vlingo/core/internal/dialogmanager/tasks/DMServerTask:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   26: goto -7 -> 19
    //   29: astore_1
    //   30: aload_0
    //   31: aconst_null
    //   32: putfield 152	com/vlingo/core/internal/dialogmanager/tasks/DMServerTask:micStream	Lcom/vlingo/core/internal/audio/MicrophoneStream;
    //   35: aload_1
    //   36: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   7	14	20	java/lang/Exception
    //   7	14	29	finally
  }

  private AudioType getAudioType()
  {
    String str;
    if (AUDIO_TYPE == null)
      str = Settings.getString("custom_tone_encoding", null);
    try
    {
      AUDIO_TYPE = AudioType.valueOf(str);
      return AUDIO_TYPE;
    }
    catch (Exception localException)
    {
      while (true)
        AUDIO_TYPE = AudioType.PCM_22k;
    }
  }

  private void logMicStreamCloseProblem(String paramString1, String paramString2)
  {
  }

  private void logStartRecoException(Exception paramException)
  {
  }

  private void notifyIfDone(boolean paramBoolean)
  {
    if (paramBoolean)
      this.awaitingStopTone = false;
    while (true)
    {
      if ((this.completedReco) && (!this.awaitingStopTone))
        notifyFinished();
      return;
      this.completedReco = true;
    }
  }

  private void performReco(DataReadyListner paramDataReadyListner)
  {
    performReco(paramDataReadyListner, 0);
  }

  private void performReco(DataReadyListner paramDataReadyListner, int paramInt)
  {
    try
    {
      if (isCancelled())
      {
        notifyIfDone(false);
      }
      else
      {
        VLDialogContext localVLDialogContext = this.contextBuilder.build();
        if (this.micStream == null)
          this.micStream = MicrophoneStream.request(localVLDialogContext, MicrophoneStream.TaskType.RECOGNITION, paramInt);
        RecoContext.setRecoSource(this.contextBuilder, this.micStream);
        VLSdk.getInstance().getRecognizer().startRecognition(this.contextBuilder.build(), new DMTransactionListener(this.recoListener), paramDataReadyListner);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.recoListener.onError(VLRecognitionErrors.ERROR_AUDIO, localException.getMessage());
      notifyIfDone(false);
    }
  }

  private void performRecoTransaction()
  {
    if (isCancelled())
      notifyIfDone(false);
    while (true)
    {
      return;
      boolean bool1 = BluetoothManager.isHeadsetConnected();
      boolean bool2 = BluetoothManager.isBluetoothAudioSupported();
      if ((bool1 == true) && (bool2 == true))
        AudioFocusManager.getInstance(ApplicationAdapter.getInstance().getApplicationContext()).requestAudioFocus(6, 2);
      while (true)
      {
        if ((!BluetoothManager.isBluetoothAudioSupported()) || (BluetoothManager.isBluetoothAudioOn()))
          break label96;
        this.onBtOnTask = OnBluetoothAudioOnTimeoutTask.runTaskOnBluetoothAudioOn(2000L, new Runnable()
        {
          public void run()
          {
            DMServerTask.this.performRecoTransactionSub();
            DMServerTask.access$102(DMServerTask.this, null);
          }
        });
        break;
        AudioFocusManager.getInstance(ApplicationAdapter.getInstance().getApplicationContext()).requestAudioFocus(3, 2);
      }
      label96: performRecoTransactionSub();
    }
  }

  private void performRecoTransactionSub()
  {
    long l = 0L;
    if (isCancelled())
      notifyIfDone(false);
    while (true)
    {
      return;
      if (this.recoListener != null)
        l = this.recoListener.onRecoToneStarting();
      ActivityUtil.scheduleOnMainThread(new Runnable()
      {
        public void run()
        {
          int i;
          if (BluetoothManager.isHeadsetConnected())
            if (DMServerTask.this.micStream != null)
            {
              i = 0;
              if (i <= 0)
                break label121;
              if (!DMServerTask.this.useAudioTrackTonePlayer)
                break label106;
              new TonePlayer(i, DMServerTask.this.getAudioType()).play(new DMServerTask.2.1(this));
            }
          while (true)
          {
            return;
            i = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.raw.core_start_tone_bt);
            break;
            if (DMServerTask.this.micStream != null);
            for (i = 0; ; i = DMServerTask.this.getToneId(ResourceIdProvider.raw.core_start_tone, ResourceIdProvider.raw.core_start_tone_drv))
              break;
            label106: AudioPlayerProxy.playTone(i, new DMServerTask.2.2(this));
            continue;
            label121: DMServerTask.this.performReco(DMServerTask.this.dataReadyListner);
          }
        }
      }
      , l);
    }
  }

  private void performTextReco(String paramString)
  {
    try
    {
      if (isCancelled())
      {
        notifyIfDone(false);
      }
      else
      {
        RecoContext.setTextSource(this.contextBuilder, paramString);
        VLSdk.getInstance().getRecognizer().startRecognition(this.contextBuilder.build(), new DMTransactionListener(this.recoListener), new DataReadyListner());
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.recoListener.onError(VLRecognitionErrors.ERROR_AUDIO, localException.getMessage());
      notifyIfDone(false);
    }
  }

  protected int getToneId(ResourceIdProvider.raw paramraw1, ResourceIdProvider.raw paramraw2)
  {
    int i = -1;
    if (Settings.isDrivingModeEnabled())
      i = VlingoAndroidCore.getResourceProvider().getResourceId(paramraw2);
    if (i <= 0)
      i = VlingoAndroidCore.getResourceProvider().getResourceId(paramraw1);
    return i;
  }

  public boolean isPerformingReco()
  {
    return this.performingReco;
  }

  public void onCancelled()
  {
    monitorenter;
    try
    {
      if (this.onBtOnTask != null)
      {
        this.onBtOnTask.Cancel();
        this.onBtOnTask = null;
      }
      if (!this.gotResults)
        VLSdk.getInstance().getRecognizer().cancelRecognition();
      closeMicStream();
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

  public void performReco(DialogFieldID paramDialogFieldID, MicrophoneStream paramMicrophoneStream, HashMap<String, String> paramHashMap)
  {
    this.micStream = paramMicrophoneStream;
    if (!this.performingReco)
    {
      RecoContext.addReco(this.contextBuilder, paramDialogFieldID, paramHashMap);
      this.userProperties = paramHashMap;
      this.performingReco = true;
    }
  }

  public void run()
  {
    int i = 0;
    if (this.turn != null)
    {
      List localList = this.turn.getPendingEvents();
      if ((localList != null) && (!localList.isEmpty()))
      {
        RecoContext.addEvents(this.contextBuilder, localList);
        i = 1;
      }
    }
    if (this.performingReco)
      performRecoTransaction();
    while (true)
    {
      return;
      if (this.sendingTextRequest)
      {
        performTextReco(this.textRequest);
        continue;
      }
      if (i != 0)
      {
        if ((this.userProperties != null) && (!this.userProperties.isEmpty()))
        {
          Iterator localIterator = this.userProperties.entrySet().iterator();
          while (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            this.contextBuilder.addDMHeaderKVPair((String)localEntry.getKey(), (String)localEntry.getValue());
          }
        }
        VLSdk.getInstance().getRecognizer().sendEvent(this.contextBuilder.build(), new DMTransactionListener(this.recoListener));
        continue;
      }
      notifyFinished();
    }
  }

  public void sendTextRequest(DialogFieldID paramDialogFieldID, String paramString, HashMap<String, String> paramHashMap)
  {
    if (!this.sendingTextRequest)
    {
      RecoContext.addReco(this.contextBuilder, paramDialogFieldID, paramHashMap);
      this.userProperties = paramHashMap;
      this.textRequest = paramString;
      this.sendingTextRequest = true;
    }
  }

  private class DMTransactionListener
    implements VLRecognitionListener
  {
    private DMServerTask.ProcessingTone processingTone;
    private final VLRecognitionListener recoListener;

    DMTransactionListener(VLRecognitionListener arg2)
    {
      Object localObject;
      this.recoListener = localObject;
      this.processingTone = new DMServerTask.ProcessingTone(DMServerTask.this);
    }

    public void onCancelled()
    {
      this.recoListener.onCancelled();
      this.processingTone.stop();
      DMServerTask.this.notifyIfDone(false);
    }

    public void onError(VLRecognitionErrors paramVLRecognitionErrors, String paramString)
    {
      DMServerTask.this.closeMicStream();
      this.processingTone.stop();
      this.recoListener.onError(paramVLRecognitionErrors, paramString);
      DMServerTask.this.notifyIfDone(false);
    }

    public long onRecoToneStarting()
    {
      return 0L;
    }

    public void onRecognitionResults(VLRecognitionResult paramVLRecognitionResult)
    {
      DMServerTask.access$1302(DMServerTask.this, true);
      this.processingTone.stop();
      if (DMServerTask.this.turn != null)
        DMServerTask.this.turn.clearEvents();
      this.recoListener.onRecognitionResults(paramVLRecognitionResult);
      DMServerTask.this.notifyIfDone(false);
    }

    public void onRmsChanged(int paramInt)
    {
      this.recoListener.onRmsChanged(paramInt);
    }

    public void onStateChanged(VLRecognitionStates paramVLRecognitionStates)
    {
      int i;
      if ((DMServerTask.this.performingReco) && (paramVLRecognitionStates == VLRecognitionStates.THINKING))
      {
        DMServerTask.this.closeMicStream();
        if (!BluetoothManager.isHeadsetConnected())
          break label111;
        i = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.raw.core_stop_tone_bt);
        if (i > 0)
        {
          if (!DMServerTask.this.useAudioTrackTonePlayer)
            break label128;
          DMServerTask.access$1102(DMServerTask.this, true);
          new TonePlayer(i, DMServerTask.this.getAudioType(), false).play(new DMServerTask.DMTransactionListener.1(this));
          DMServerTask.this.notifyIfDone(true);
        }
      }
      while (true)
      {
        this.recoListener.onStateChanged(paramVLRecognitionStates);
        return;
        label111: i = DMServerTask.this.getToneId(ResourceIdProvider.raw.core_stop_tone, ResourceIdProvider.raw.core_stop_tone_drv);
        break;
        label128: DMServerTask.access$1102(DMServerTask.this, true);
        AudioPlayerProxy.playTone(i, new DMServerTask.DMTransactionListener.2(this));
      }
    }

    public void onWarning(VLRecognitionWarnings paramVLRecognitionWarnings, String paramString)
    {
      this.recoListener.onWarning(paramVLRecognitionWarnings, paramString);
    }
  }

  class ProcessingTone extends Handler
  {
    private static final int RELEASE = 3;
    private static final int START = 1;
    private static final int STOP = 2;
    private static final int VOLUME_DOWN = 4;
    private MediaPlayer mediaPlayer;
    private BroadcastReceiver receiver;

    ProcessingTone()
    {
      int i;
      if (Settings.isDrivingModeEnabled())
      {
        i = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.raw.core_processing_tone);
        if (i != -1)
          break label40;
      }
      while (true)
      {
        return;
        i = -1;
        break;
        label40: AssetFileDescriptor localAssetFileDescriptor = ApplicationAdapter.getInstance().getApplicationContext().getResources().openRawResourceFd(i);
        try
        {
          this.mediaPlayer = new MediaPlayer();
          this.mediaPlayer.setDataSource(localAssetFileDescriptor.getFileDescriptor(), localAssetFileDescriptor.getStartOffset(), localAssetFileDescriptor.getLength());
          localAssetFileDescriptor.close();
          this.mediaPlayer.setOnPreparedListener(null);
          this.mediaPlayer.setAudioStreamType(PhoneUtil.getCurrentStream());
          this.mediaPlayer.setVolume(PhoneUtil.getCurrentStreamMaxVolume(), PhoneUtil.getCurrentStreamMaxVolume());
          this.mediaPlayer.prepare();
          this.receiver = new DMServerTask.ProcessingTone.1(this, DMServerTask.this);
          ApplicationAdapter.getInstance().getApplicationContext().registerReceiver(this.receiver, new IntentFilter("com.vlingo.client.app.action.THINKING_AUDIO_SYNC"));
        }
        catch (IOException localIOException)
        {
          this.mediaPlayer = null;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          this.mediaPlayer = null;
        }
      }
    }

    public void handleMessage(Message paramMessage)
    {
      monitorenter;
      while (true)
      {
        try
        {
          MediaPlayer localMediaPlayer = this.mediaPlayer;
          if (localMediaPlayer == null)
            return;
          switch (paramMessage.what)
          {
          case 1:
            boolean bool = DMServerTask.this.gotResults;
            if (bool)
              continue;
            try
            {
              this.mediaPlayer.start();
            }
            catch (Exception localException)
            {
            }
            break;
          case 2:
            float f2 = PhoneUtil.getCurrentStreamVolume();
            int i = DMServerTask.processingToneFadeOut / 50;
            int j = 1;
            if (j > i)
              continue;
            Message localMessage = new Message();
            localMessage.what = 4;
            localMessage.obj = Float.valueOf(f2 * (i - j) / i);
            sendMessageDelayed(localMessage, j * 50);
            j++;
            continue;
            sendEmptyMessageDelayed(3, DMServerTask.processingToneFadeOut);
            continue;
          case 4:
          case 3:
          }
        }
        finally
        {
          monitorexit;
        }
        float f1 = ((Float)paramMessage.obj).floatValue();
        if (this.mediaPlayer == null)
          continue;
        this.mediaPlayer.setVolume(f1, f1);
        continue;
        release();
        continue;
      }
    }

    protected void release()
    {
      if (this.mediaPlayer != null)
      {
        this.mediaPlayer.release();
        this.mediaPlayer = null;
      }
      if (this.receiver != null)
        ApplicationAdapter.getInstance().getApplicationContext().unregisterReceiver(this.receiver);
    }

    void stop()
    {
      DMServerTask.access$1302(DMServerTask.this, true);
      sendEmptyMessage(2);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.tasks.DMServerTask
 * JD-Core Version:    0.6.0
 */