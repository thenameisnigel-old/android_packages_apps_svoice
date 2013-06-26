package com.vlingo.core.internal.audio;

import com.vlingo.core.internal.CoreAdapter;

public abstract interface AudioSourceUtil extends CoreAdapter
{
  public abstract int chooseAudioSource(MicrophoneStream.TaskType paramTaskType, MicrophoneStream.AudioSourceType paramAudioSourceType);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.AudioSourceUtil
 * JD-Core Version:    0.6.0
 */