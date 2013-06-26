package com.vlingo.core.internal.dialogmanager;

import com.vlingo.core.internal.audio.MicrophoneStream;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.sdk.recognition.AudioSourceInfo;
import com.vlingo.sdk.recognition.AudioSourceInfo.SourceFormat;
import com.vlingo.sdk.recognition.VLRecognitionContext.CapitalizationMode;
import com.vlingo.sdk.recognition.dialog.VLDialogContext.Builder;
import com.vlingo.sdk.recognition.dialog.VLDialogEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class RecoContext
{
  private static final boolean CXT_AUTO_PUNCTUATION = true;
  private static final VLRecognitionContext.CapitalizationMode CXT_CAPITALIZATION = VLRecognitionContext.CapitalizationMode.DEFAULT;
  private static final String CXT_CURRENT_TEXT = null;
  private static final int CXT_CURSOR_POSITION;

  public static void addEvents(VLDialogContext.Builder paramBuilder, List<DialogEvent> paramList)
  {
    if ((paramBuilder == null) || (paramList == null));
    while (true)
    {
      return;
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        VLDialogEvent localVLDialogEvent = ((DialogEvent)localIterator.next()).getVLDialogEvent();
        if (localVLDialogEvent == null)
          continue;
        paramBuilder.addEvent(localVLDialogEvent);
      }
    }
  }

  public static void addReco(VLDialogContext.Builder paramBuilder, DialogFieldID paramDialogFieldID, HashMap<String, String> paramHashMap)
  {
    if (paramBuilder == null);
    while (true)
    {
      return;
      paramBuilder.fieldID(paramDialogFieldID.getFieldId());
      paramBuilder.maxAudioTime(Settings.getInt("max_audio_time", 40000));
      paramBuilder.autoEndpointing(Settings.getBoolean("auto_endpointing", true));
      paramBuilder.autoEndPointingTimeouts(paramDialogFieldID.getTimeWithSpeechInMilliseconds(), paramDialogFieldID.getTimeNoSpeechInMillisecond());
      paramBuilder.speechDetectorParams(Settings.getFloat("endpoint.speechdetect_threshold", 11.0F), Settings.getFloat("endpoint.speechdetect_voice_duration", 0.08F), Settings.getFloat("endpoint.speechdetect_voice_portion", 0.02F), Settings.getFloat("endpoint.speechdetect_min_voice_level", 57.0F));
      paramBuilder.autoPunctuation(true);
      paramBuilder.capitalizationMode(CXT_CAPITALIZATION);
      paramBuilder.currentText(CXT_CURRENT_TEXT);
      paramBuilder.cursorPosition(0);
      paramBuilder.profanityFilter(Settings.getBoolean("profanity_filter", true));
      paramBuilder.appInfo("Vlingo", null, null);
      paramBuilder.setUsername(Settings.getString("dm.username", ""));
      paramBuilder.speexParams(Settings.getInt("speex.complexity", 3), Settings.getInt("speex.quality", 8), Settings.getInt("speex.variable_bitrate", 0), Settings.getInt("speex.voice_activity_detection", 1));
      if ((paramHashMap == null) || (paramHashMap.isEmpty()))
        continue;
      Iterator localIterator = paramHashMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramBuilder.addDMHeaderKVPair((String)localEntry.getKey(), (String)localEntry.getValue());
      }
    }
  }

  public static void init(VLDialogContext.Builder paramBuilder, String paramString, int paramInt, byte[] paramArrayOfByte)
  {
    if (paramBuilder == null);
    while (true)
    {
      return;
      paramBuilder.language(Settings.getLanguageApplication());
      paramBuilder.setDialogGUID(paramString);
      paramBuilder.setTurnNumber(paramInt);
      paramBuilder.setState(paramArrayOfByte);
    }
  }

  public static void setRecoSource(VLDialogContext.Builder paramBuilder, MicrophoneStream paramMicrophoneStream)
  {
    if (paramBuilder == null);
    while (true)
    {
      return;
      if (paramMicrophoneStream != null)
      {
        if (paramMicrophoneStream.is16KHz())
        {
          paramBuilder.audioSourceInfo(AudioSourceInfo.getStreamSource(paramMicrophoneStream, AudioSourceInfo.SourceFormat.PCM_16KHZ_16BIT));
          continue;
        }
        if (!paramMicrophoneStream.is8KHz())
          continue;
        paramBuilder.audioSourceInfo(AudioSourceInfo.getStreamSource(paramMicrophoneStream, AudioSourceInfo.SourceFormat.PCM_8KHZ_16BIT));
        continue;
      }
    }
  }

  public static void setTextSource(VLDialogContext.Builder paramBuilder, String paramString)
  {
    if (paramBuilder == null);
    while (true)
    {
      return;
      if (paramString != null)
      {
        paramBuilder.audioSourceInfo(AudioSourceInfo.getStringSource(paramString));
        continue;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.RecoContext
 * JD-Core Version:    0.6.0
 */