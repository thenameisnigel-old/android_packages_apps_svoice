package com.vlingo.midas.mimic;

import com.vlingo.core.internal.audio.AudioRequest;
import com.vlingo.core.internal.audio.MTAudioPlaybackDoneListener;

class MimicRecordTask$ReaderThread$1 extends MTAudioPlaybackDoneListener
{
  public void onRequestDone(AudioRequest paramAudioRequest)
  {
    MimicRecordTask.access$400(MimicRecordTask.ReaderThread.access$300(this.this$0));
    MimicRecordTask.ReaderThread.access$302(this.this$0, null);
    MimicRecordTask.ReaderThread.access$502(this.this$0, null);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.mimic.MimicRecordTask.ReaderThread.1
 * JD-Core Version:    0.6.0
 */