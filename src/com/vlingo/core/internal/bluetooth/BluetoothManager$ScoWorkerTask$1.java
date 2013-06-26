package com.vlingo.core.internal.bluetooth;

import android.bluetooth.BluetoothHeadset;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.vlingo.core.internal.util.TimerSingleton;
import java.util.Timer;

class BluetoothManager$ScoWorkerTask$1 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    }
    while (true)
    {
      return;
      if (BluetoothManager.access$100() == null)
      {
        if (BluetoothManager.access$700() == null)
          continue;
        if (BluetoothManager.access$300() != null)
          BluetoothManager.access$800();
        BluetoothManager.access$302(new BluetoothManager.ScoTimeoutTask(true, false));
        TimerSingleton.getTimer().schedule(BluetoothManager.access$300(), 2000L);
        continue;
      }
      if (BluetoothManager.access$200() == null)
      {
        BluetoothManager.access$202(BluetoothManager.getBTdevice());
        if (BluetoothManager.access$200() == null)
        {
          if (BluetoothManager.access$700() == null)
            continue;
          if (BluetoothManager.access$300() != null)
            BluetoothManager.access$800();
          BluetoothManager.access$302(new BluetoothManager.ScoTimeoutTask(true, false));
          TimerSingleton.getTimer().schedule(BluetoothManager.access$300(), 2000L);
          continue;
        }
      }
      if (BluetoothManager.access$300() != null)
        BluetoothManager.access$800();
      if (!BluetoothManager.isBluetoothAudioOn())
      {
        if (!BluetoothManager.access$100().startVoiceRecognition(BluetoothManager.access$200()))
        {
          boolean bool = true;
          if (paramMessage.getData().containsKey("sco_retry_flag"))
            bool = paramMessage.getData().getBoolean("sco_retry_flag");
          BluetoothManager.access$302(new BluetoothManager.ScoTimeoutTask(bool, true));
          TimerSingleton.getTimer().schedule(BluetoothManager.access$300(), 2000L);
          continue;
        }
        BluetoothManager.access$902(true);
        BluetoothManager.access$1002(BluetoothManager.CloseType.BT_BUTTON);
        continue;
      }
      BluetoothManager.access$1100(true);
      if (BluetoothManager.access$300() == null)
        continue;
      BluetoothManager.access$800();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.bluetooth.BluetoothManager.ScoWorkerTask.1
 * JD-Core Version:    0.6.0
 */