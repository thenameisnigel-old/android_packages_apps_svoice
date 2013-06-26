package com.vlingo.midas.gui;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.vlingo.core.internal.display.WakeLockManager;
import com.vlingo.core.internal.display.WakeLockManagerNoop;
import com.vlingo.core.internal.util.ApplicationAdapter;

public class WakeLockManagerMidas extends WakeLockManagerNoop
{
  private static final String WAKE_LOCK_NAME = "VlingoMain";
  private static final long WAKE_LOCK_PAUSE = 3000L;
  private static WakeLockManager instance;
  private PowerManager powerManager = (PowerManager)ApplicationAdapter.getInstance().getApplicationContext().getSystemService("power");
  private PowerManager.WakeLock wakeLock;

  public static WakeLockManager getInstance()
  {
    if (instance == null)
      instance = new WakeLockManagerMidas();
    return instance;
  }

  // ERROR //
  public void acquireWakeLock()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 48	com/vlingo/midas/gui/WakeLockManagerMidas:wakeLock	Landroid/os/PowerManager$WakeLock;
    //   6: ifnonnull +18 -> 24
    //   9: aload_0
    //   10: aload_0
    //   11: getfield 44	com/vlingo/midas/gui/WakeLockManagerMidas:powerManager	Landroid/os/PowerManager;
    //   14: ldc 58
    //   16: ldc 8
    //   18: invokevirtual 62	android/os/PowerManager:newWakeLock	(ILjava/lang/String;)Landroid/os/PowerManager$WakeLock;
    //   21: putfield 48	com/vlingo/midas/gui/WakeLockManagerMidas:wakeLock	Landroid/os/PowerManager$WakeLock;
    //   24: aload_0
    //   25: getfield 48	com/vlingo/midas/gui/WakeLockManagerMidas:wakeLock	Landroid/os/PowerManager$WakeLock;
    //   28: ifnull +20 -> 48
    //   31: aload_0
    //   32: getfield 48	com/vlingo/midas/gui/WakeLockManagerMidas:wakeLock	Landroid/os/PowerManager$WakeLock;
    //   35: invokevirtual 68	android/os/PowerManager$WakeLock:isHeld	()Z
    //   38: ifne +10 -> 48
    //   41: aload_0
    //   42: getfield 48	com/vlingo/midas/gui/WakeLockManagerMidas:wakeLock	Landroid/os/PowerManager$WakeLock;
    //   45: invokevirtual 71	android/os/PowerManager$WakeLock:acquire	()V
    //   48: aload_0
    //   49: monitorexit
    //   50: return
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    //   56: astore_2
    //   57: goto -9 -> 48
    //
    // Exception table:
    //   from	to	target	type
    //   2	24	51	finally
    //   24	48	51	finally
    //   24	48	56	java/lang/Exception
  }

  public void releaseWakeLock()
  {
    monitorenter;
    try
    {
      new WakeLockManagerMidas.1(this).start();
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
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.WakeLockManagerMidas
 * JD-Core Version:    0.6.0
 */