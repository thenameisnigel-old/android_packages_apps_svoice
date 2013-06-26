package com.vlingo.midas.gui;

import android.os.PowerManager.WakeLock;

class WakeLockManagerMidas$1 extends Thread
{
  public void run()
  {
    try
    {
      sleep(3000L);
    }
    catch (InterruptedException localInterruptedException)
    {
      try
      {
        while (true)
        {
          if ((WakeLockManagerMidas.access$000(this.this$0) != null) && (WakeLockManagerMidas.access$000(this.this$0).isHeld()))
          {
            WakeLockManagerMidas.access$000(this.this$0).release();
            WakeLockManagerMidas.access$002(this.this$0, null);
          }
          label48: return;
          localInterruptedException = localInterruptedException;
        }
      }
      catch (Exception localException)
      {
        break label48;
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.WakeLockManagerMidas.1
 * JD-Core Version:    0.6.0
 */