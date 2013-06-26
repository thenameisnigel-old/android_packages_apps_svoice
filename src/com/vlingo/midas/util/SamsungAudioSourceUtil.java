package com.vlingo.midas.util;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build.VERSION;
import com.vlingo.core.internal.audio.AudioSourcePair;
import com.vlingo.core.internal.audio.AudioSourceUtil;
import com.vlingo.core.internal.audio.MicrophoneStream.AudioSourceType;
import com.vlingo.core.internal.audio.MicrophoneStream.TaskType;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.midas.gui.ConversationActivity;
import com.vlingo.midas.gui.Widget;

public class SamsungAudioSourceUtil
  implements AudioSourceUtil
{
  private static final AudioSourcePair audioSourcePairAsrDrivingMode;
  private static final AudioSourcePair audioSourcePairAsrDrivingModeUnderJ;
  private static final AudioSourcePair audioSourcePairAsrRegular = new AudioSourcePair(18, 6);
  private static final AudioSourcePair audioSourcePairAsrRegularUnderJ = new AudioSourcePair(18, 6);
  private static final AudioSourcePair audioSourcePairGeneralRecognition;
  private static final AudioSourcePair audioSourcePairRecording;
  private static final AudioSourcePair audioSourcePairSpotterDrivingMode;
  private static final AudioSourcePair audioSourcePairSpotterDrivingModeUnderJ;
  private static final AudioSourcePair audioSourcePairSpotterEchoCancellationForMusic;
  private static final AudioSourcePair audioSourcePairSpotterEchoCancellationForTts;
  private static final AudioSourcePair audioSourcePairSpotterRegular;
  private static final AudioSourcePair audioSourcePairSpotterRegularUnderJ;
  private static final String knownBadSettingValue = "-1";

  static
  {
    audioSourcePairAsrDrivingMode = new AudioSourcePair(17, 6);
    audioSourcePairAsrDrivingModeUnderJ = new AudioSourcePair(17, 6);
    audioSourcePairSpotterRegular = new AudioSourcePair(6, 6);
    audioSourcePairSpotterRegularUnderJ = new AudioSourcePair(6, 6);
    audioSourcePairSpotterDrivingMode = new AudioSourcePair(6, 6);
    audioSourcePairSpotterDrivingModeUnderJ = new AudioSourcePair(6, 6);
    audioSourcePairGeneralRecognition = new AudioSourcePair(6, 1);
    audioSourcePairSpotterEchoCancellationForMusic = new AudioSourcePair(19, 6);
    audioSourcePairSpotterEchoCancellationForTts = new AudioSourcePair(11, 6);
    audioSourcePairRecording = new AudioSourcePair(1, 6);
  }

  public int chooseAudioSource(MicrophoneStream.TaskType paramTaskType, MicrophoneStream.AudioSourceType paramAudioSourceType)
  {
    boolean bool;
    AudioSourcePair localAudioSourcePair;
    if (MicrophoneStream.AudioSourceType.UNSPECIFIED == paramAudioSourceType)
    {
      if (paramTaskType != MicrophoneStream.TaskType.RECOGNITION)
        break label118;
      if (ClientSuppliedValues.isDrivingModeEnabled())
        paramAudioSourceType = MicrophoneStream.AudioSourceType.ASR_DRIVING_MODE;
    }
    else
    {
      bool = Settings.getBoolean("use_echo_cancel_for_spotter", true);
      switch (1.$SwitchMap$com$vlingo$core$internal$audio$MicrophoneStream$AudioSourceType[paramAudioSourceType.ordinal()])
      {
      default:
        localAudioSourcePair = audioSourcePairGeneralRecognition;
      case 1:
      case 2:
      case 3:
      case 4:
      }
    }
    while (true)
    {
      if (paramTaskType == MicrophoneStream.TaskType.LOCALRECORD)
        localAudioSourcePair = audioSourcePairRecording;
      int j = localAudioSourcePair.getPreferred();
      if (Settings.getBoolean("use_non_j_audio_sources", false))
        j = localAudioSourcePair.getFallback();
      return j;
      paramAudioSourceType = MicrophoneStream.AudioSourceType.ASR_REGULAR;
      break;
      label118: if (paramTaskType == MicrophoneStream.TaskType.PHRASESPOTTING)
      {
        if (ClientSuppliedValues.isDrivingModeEnabled())
        {
          paramAudioSourceType = MicrophoneStream.AudioSourceType.SPOTTER_DRIVING_MODE;
          break;
        }
        paramAudioSourceType = MicrophoneStream.AudioSourceType.SPOTTER_REGULAR;
        break;
      }
      paramAudioSourceType = MicrophoneStream.AudioSourceType.GENERAL_VOICE_RECOGNITION;
      break;
      if (Build.VERSION.SDK_INT > 16)
      {
        localAudioSourcePair = audioSourcePairAsrRegular;
        continue;
      }
      localAudioSourcePair = audioSourcePairAsrRegularUnderJ;
      continue;
      if (Build.VERSION.SDK_INT > 16)
      {
        localAudioSourcePair = audioSourcePairAsrDrivingMode;
        continue;
      }
      localAudioSourcePair = audioSourcePairAsrDrivingModeUnderJ;
      continue;
      AudioManager localAudioManager = (AudioManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("audio");
      int i = 0;
      Widget localWidget = ConversationActivity.getLatestWidget();
      if ((localWidget != null) && (localWidget.isPlayingAudio()))
        i = 1;
      if (Build.VERSION.SDK_INT > 16);
      for (localAudioSourcePair = audioSourcePairSpotterDrivingMode; ; localAudioSourcePair = audioSourcePairSpotterDrivingModeUnderJ)
      {
        if ((!bool) || (!localAudioManager.isMusicActive()))
          break label284;
        if (i == 0)
          break label286;
        localAudioSourcePair = audioSourcePairSpotterEchoCancellationForTts;
        break;
      }
      label284: continue;
      label286: localAudioSourcePair = audioSourcePairSpotterEchoCancellationForMusic;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.SamsungAudioSourceUtil
 * JD-Core Version:    0.6.0
 */