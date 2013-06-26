package com.vlingo.core.internal.bluetooth;

import android.os.Handler;
import com.vlingo.core.internal.util.ActivityUtil;
import com.vlingo.core.internal.util.TimerSingleton;
import java.util.Timer;
import java.util.TimerTask;

public class OnBluetoothAudioOnTimeoutTask
  implements BluetoothManagerListener
{
  Runnable onBluetoothOnTask = null;
  Handler onBluetoothOnTaskThreadHandler = null;
  private TimerTask timeoutTask = null;
  private Timer timer = null;

  private OnBluetoothAudioOnTimeoutTask(long paramLong, Runnable paramRunnable, Handler paramHandler)
  {
    this.onBluetoothOnTask = paramRunnable;
    this.onBluetoothOnTaskThreadHandler = paramHandler;
    if (paramLong > 0L)
    {
      this.timer = TimerSingleton.getTimer();
      this.timeoutTask = new OnBluetoothAudioOnTimeoutTask.1(this);
      this.timer.schedule(this.timeoutTask, paramLong);
    }
    BluetoothManager.addListener(this);
  }

  private void performTask()
  {
    monitorenter;
    try
    {
      BluetoothManager.removeListener(this);
      if (this.timeoutTask != null)
      {
        this.timeoutTask.cancel();
        this.timeoutTask = null;
      }
      if (this.onBluetoothOnTask != null)
      {
        if (this.onBluetoothOnTaskThreadHandler != null)
          break label58;
        ActivityUtil.scheduleOnMainThread(this.onBluetoothOnTask, 200L);
      }
      while (true)
      {
        this.onBluetoothOnTask = null;
        return;
        label58: this.onBluetoothOnTaskThreadHandler.postDelayed(this.onBluetoothOnTask, 200L);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static OnBluetoothAudioOnTimeoutTask runTaskOnBluetoothAudioOn(long paramLong, Runnable paramRunnable)
  {
    return new OnBluetoothAudioOnTimeoutTask(paramLong, paramRunnable, null);
  }

  public static OnBluetoothAudioOnTimeoutTask runTaskOnBluetoothAudioOn(long paramLong, Runnable paramRunnable, Handler paramHandler)
  {
    return new OnBluetoothAudioOnTimeoutTask(paramLong, paramRunnable, paramHandler);
  }

  public void Cancel()
  {
    monitorenter;
    try
    {
      BluetoothManager.removeListener(this);
      if (this.timeoutTask != null)
      {
        this.timeoutTask.cancel();
        this.timeoutTask = null;
      }
      if (this.onBluetoothOnTask != null)
        this.onBluetoothOnTask = null;
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

  public void onScoConnected()
  {
    performTask();
  }

  public void onScoDisconnected()
  {
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.bluetooth.OnBluetoothAudioOnTimeoutTask
 * JD-Core Version:    0.6.0
 */