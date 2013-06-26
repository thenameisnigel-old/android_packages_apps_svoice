package com.vlingo.core.internal.dialogmanager.tasks;

import com.vlingo.core.internal.dialogmanager.ResumeControl;
import com.vlingo.core.internal.util.TaskQueue;
import com.vlingo.core.internal.util.TaskQueue.Task;
import com.vlingo.sdk.recognition.VLRecognitionListener;

public abstract class PausableTask extends TaskQueue.Task
  implements ResumeControl
{
  private boolean hasBeenPaused;
  private boolean hasBeenResumed;
  private VLRecognitionListener vlRecognitionListener;

  public VLRecognitionListener getVlRecognitionListener()
  {
    return this.vlRecognitionListener;
  }

  public boolean isPausable()
  {
    if ((!this.hasBeenPaused) && (!this.hasBeenResumed));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void pause()
  {
    if (!this.hasBeenPaused)
    {
      this.hasBeenPaused = true;
      getParentQueue().pause();
    }
  }

  public void resume()
  {
    if ((this.hasBeenPaused) && (!this.hasBeenResumed))
    {
      this.hasBeenResumed = true;
      getParentQueue().resume();
    }
  }

  public void setVlRecognitionListener(VLRecognitionListener paramVLRecognitionListener)
  {
    this.vlRecognitionListener = paramVLRecognitionListener;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.tasks.PausableTask
 * JD-Core Version:    0.6.0
 */