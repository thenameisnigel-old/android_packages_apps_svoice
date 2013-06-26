package com.vlingo.midas.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

public class HomeKeyListener
{
  private static int CALLED_CREATE_DESCRIPTION;
  private static int CALLED_PAUSE;
  private static int CALLED_SAVE_INSTANCE_STATE;
  private static int CALLED_STOP;
  private static int CALLED_TRIM_MEMORY;
  private static int CALLED_USER_INTERACTION;
  private static int CALLED_USER_LEAVE_HINT;
  private static int CALLED_WINDOW_FOCUS_CHANGED;
  private static int SIGNATURE = -1412567183;
  private Runnable action;
  private boolean explicitLaunch;
  private int presentState;

  static
  {
    CALLED_USER_INTERACTION = 10;
    CALLED_USER_LEAVE_HINT = 11;
    CALLED_PAUSE = 12;
    CALLED_WINDOW_FOCUS_CHANGED = 13;
    CALLED_TRIM_MEMORY = 14;
    CALLED_CREATE_DESCRIPTION = 15;
    CALLED_SAVE_INSTANCE_STATE = 7;
    CALLED_STOP = 1;
  }

  public HomeKeyListener(Runnable paramRunnable)
  {
    if (paramRunnable == null)
      throw new NullPointerException(getClass().getSimpleName() + ": 'action' must be non-null");
    this.action = paramRunnable;
  }

  public static HomeKeyListener onHomePressed(Runnable paramRunnable)
  {
    return new HomeKeyListener(paramRunnable);
  }

  private void reset()
  {
    this.presentState = 0;
  }

  private void setState(int paramInt)
  {
    this.presentState = (paramInt | this.presentState << 4);
  }

  public void onCreateDescription()
  {
    setState(CALLED_CREATE_DESCRIPTION);
  }

  public void onPause()
  {
    setState(CALLED_PAUSE);
  }

  public void onSaveInstanceState()
  {
    setState(CALLED_SAVE_INSTANCE_STATE);
  }

  public void onStop()
  {
    setState(CALLED_STOP);
    if (this.presentState == SIGNATURE)
      this.action.run();
    reset();
  }

  public void onTrimMemory(int paramInt)
  {
    if (paramInt == 20)
      setState(CALLED_TRIM_MEMORY);
  }

  public void onUserInteraction()
  {
    reset();
    if (!this.explicitLaunch)
      setState(CALLED_USER_INTERACTION);
    this.explicitLaunch = false;
  }

  public void onUserLeaveHint()
  {
    setState(CALLED_USER_LEAVE_HINT);
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if (paramBoolean)
      reset();
    while (true)
    {
      return;
      setState(CALLED_WINDOW_FOCUS_CHANGED);
    }
  }

  public void startActivity(Intent paramIntent)
  {
    this.explicitLaunch = true;
  }

  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.explicitLaunch = true;
  }

  public void startActivityFromChild(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    this.explicitLaunch = true;
  }

  public void startActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt)
  {
    this.explicitLaunch = true;
  }

  public void startActivityIfNeeded(Intent paramIntent, int paramInt)
  {
    this.explicitLaunch = true;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.ui.HomeKeyListener
 * JD-Core Version:    0.6.0
 */