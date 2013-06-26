package com.vlingo.midas.mimic;

import android.content.Context;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.raw;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.audio.AudioPlayerProxy;
import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.AudioType;
import com.vlingo.core.internal.audio.MTAudioPlaybackDoneListener;
import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.audio.MicrophoneStream.TaskType;
import com.vlingo.core.internal.audio.TonePlayer;
import com.vlingo.core.internal.audio.TonePlayer.Listener;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.sdk.VLSdk;
import com.vlingo.sdk.recognition.VLRecognitionStates;
import com.vlingo.sdk.recognition.speech.VLSpeechDetector;
import com.vlingo.sdk.recognition.speech.VLSpeechDetectorContext.Builder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MimicRecordTask extends MimicBaseTask
{
  public static AudioType AUDIO_TYPE;
  private MicrophoneStream micStream;

  public MimicRecordTask(Context paramContext)
  {
    super(paramContext);
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

  private void startRecording()
  {
    this.notificationHandler.notifyEvent(VLRecognitionStates.LISTENING);
    try
    {
      new ReaderThread(this, this.context, this.micStream).start();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
      {
        localFileNotFoundException.printStackTrace();
        notifyFinished();
      }
    }
  }

  public void run()
  {
    int i;
    if (BluetoothManager.isHeadsetConnected())
    {
      i = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.raw.core_start_tone_bt);
      if (!Settings.getBoolean("use_mediasync_tone_approach", false))
        break label66;
      new TonePlayer(i, getAudioType()).play(new TonePlayer.Listener()
      {
        public void onStarted(int paramInt)
        {
        }

        public void onStopped()
        {
          MimicRecordTask.access$002(MimicRecordTask.this, MicrophoneStream.request(null, MicrophoneStream.TaskType.LOCALRECORD));
          MimicRecordTask.this.startRecording();
        }
      });
    }
    while (true)
    {
      return;
      i = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.raw.core_start_tone);
      break;
      label66: AudioPlayerProxy.playTone(i, new MTAudioPlaybackDoneListener()
      {
        public void onRequestDone(AudioRequest paramAudioRequest)
        {
          MimicRecordTask.access$002(MimicRecordTask.this, MicrophoneStream.request(null, MicrophoneStream.TaskType.LOCALRECORD));
          MimicRecordTask.this.startRecording();
        }
      });
    }
  }

  private static class ReaderThread extends Thread
  {
    byte[] buffer = new byte[320];
    final int bufferSize = 320;
    FileOutputStream fos;
    private MicrophoneStream micStream;
    private MimicRecordTask mimicTask;

    public ReaderThread(MimicRecordTask paramMimicRecordTask, Context paramContext, MicrophoneStream paramMicrophoneStream)
      throws FileNotFoundException
    {
      this.mimicTask = paramMimicRecordTask;
      this.micStream = paramMicrophoneStream;
      this.fos = new FileOutputStream(paramMimicRecordTask.filePlay);
    }

    public void run()
    {
      int i = 1;
      VLSpeechDetectorContext.Builder localBuilder = new VLSpeechDetectorContext.Builder().speechDetectorParams(Settings.getFloat("endpoint.speechdetect_threshold", 11.0F), Settings.getFloat("endpoint.speechdetect_voice_duration", 0.08F), Settings.getFloat("endpoint.speechdetect_voice_portion", 0.02F), Settings.getFloat("endpoint.speechdetect_min_voice_level", 57.0F));
      VLSpeechDetector localVLSpeechDetector = VLSdk.getInstance().getSpeechDetector();
      localVLSpeechDetector.startSpeechDetector(localBuilder.build());
      int j = Settings.getInt("endpoint.time_withoutspeech", 5000);
      int k = Settings.getInt("endpoint.time_withspeech", 1750);
      int m = Settings.getInt("max_audio_time", 40000);
      if (m > 30000)
        m = 30000;
      long l1 = System.currentTimeMillis();
      long l2 = 0L;
      short[] arrayOfShort = new short[' '];
      int n = 0;
      int i1 = 0;
      int i2 = 1;
      double d;
      if ((i != 0) && (!this.mimicTask.isCancelled()))
        d = 0.0D;
      while (true)
      {
        boolean bool;
        long l4;
        try
        {
          int i4 = this.micStream.read(this.buffer);
          int i5 = 0;
          if (i5 >= i4 / 2)
            continue;
          arrayOfShort[i5] = (short)((this.buffer[(1 + i5 * 2)] << 8) + this.buffer[(i5 * 2)]);
          d += arrayOfShort[i5] * arrayOfShort[i5];
          i5++;
          continue;
          int i6 = (int)Math.sqrt(d / (i4 / 2)) >> 4;
          this.mimicTask.notificationHandler.notifyRmsChange(Integer.valueOf(i6));
          bool = localVLSpeechDetector.processShortArray(arrayOfShort, 0, i4 / 2);
          if (i2 == 0)
            break label460;
          i2 = 0;
          this.fos.write(this.buffer, 0, i4);
          l4 = System.currentTimeMillis();
          if (l4 - l1 <= j)
            break label426;
          if (i1 == 0)
            break label455;
          break label426;
          n = 0;
          long l3 = System.currentTimeMillis();
          l2 = l3;
          i1 = 1;
          continue;
        }
        catch (IOException localIOException2)
        {
          i = 0;
          localIOException2.printStackTrace();
        }
        break;
        localVLSpeechDetector.stopSpeechDetector();
        try
        {
          this.fos.close();
          this.micStream.close();
          if (BluetoothManager.isHeadsetConnected())
          {
            i3 = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.raw.core_stop_tone_bt);
            MimicRecordTask.ReaderThread.1 local1 = new MimicRecordTask.ReaderThread.1(this);
            AudioPlayerProxy.playTone(i3, local1);
            return;
          }
        }
        catch (IOException localIOException1)
        {
          while (true)
          {
            localIOException1.printStackTrace();
            continue;
            int i3 = VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.raw.core_stop_tone);
          }
        }
        label426: if (((l4 - l2 <= k) || (i1 == 0)) && (l4 - l1 <= m))
          break;
        label455: i = 0;
        break;
        label460: if (bool)
          continue;
        if (n == 0)
          n++;
        if ((n != 5) || (i1 == 0))
          continue;
        i = 0;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.mimic.MimicRecordTask
 * JD-Core Version:    0.6.0
 */