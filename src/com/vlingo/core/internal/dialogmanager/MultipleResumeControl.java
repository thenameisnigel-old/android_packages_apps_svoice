package com.vlingo.core.internal.dialogmanager;

public class MultipleResumeControl
  implements ResumeControl
{
  private int mNumberOfResumeControlsToWait;
  private ResumeControl mTargetResumeControl;

  public MultipleResumeControl(ResumeControl paramResumeControl, int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("Number of resume controls to wait should be greater than 0");
    this.mNumberOfResumeControlsToWait = paramInt;
    this.mTargetResumeControl = paramResumeControl;
  }

  public void resume()
  {
    this.mNumberOfResumeControlsToWait = (-1 + this.mNumberOfResumeControlsToWait);
    if (this.mNumberOfResumeControlsToWait == 0)
      this.mTargetResumeControl.resume();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.MultipleResumeControl
 * JD-Core Version:    0.6.0
 */