package com.vlingo.core.internal.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.bluetooth.BluetoothManager.CloseType;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.util.DialUtil;

public class VoiceDialAction extends DMAction
{
  private String address;

  public VoiceDialAction address(String paramString)
  {
    this.address = paramString;
    return this;
  }

  protected void execute()
  {
    if (this.address != null)
    {
      Intent localIntent = DialUtil.getDialIntent(this.address);
      if (BluetoothManager.isBluetoothAudioOn())
        BluetoothManager.appCloseType(BluetoothManager.CloseType.CALL);
      DialogFlow.getInstance().clearPendingSafeReaderTurn();
      getContext().startActivity(localIntent);
      getListener().actionSuccess();
    }
    while (true)
    {
      return;
      getListener().actionSuccess();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.VoiceDialAction
 * JD-Core Version:    0.6.0
 */