package com.vlingo.core.internal.dialogmanager.tasks;

import com.vlingo.core.internal.audio.TonePlayer.Listener;
import com.vlingo.sdk.internal.recognizer.reader.DataReadyListner;

class DMServerTask$2$1
  implements TonePlayer.Listener
{
  private boolean usingMediaSyncEvent = false;

  public void onStarted(int paramInt)
  {
    if ((DMServerTask.access$600(this.this$1.this$0)) && (paramInt != 0))
    {
      this.usingMediaSyncEvent = true;
      DMServerTask.access$400(this.this$1.this$0).onDataNotReady();
      DMServerTask.access$700(this.this$1.this$0, DMServerTask.access$400(this.this$1.this$0), paramInt);
    }
  }

  public void onStopped()
  {
    DMServerTask.access$400(this.this$1.this$0).onDataReady();
    if (!this.usingMediaSyncEvent)
      DMServerTask.access$500(this.this$1.this$0, DMServerTask.access$400(this.this$1.this$0));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.tasks.DMServerTask.2.1
 * JD-Core Version:    0.6.0
 */