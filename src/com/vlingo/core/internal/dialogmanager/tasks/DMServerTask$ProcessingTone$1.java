package com.vlingo.core.internal.dialogmanager.tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class DMServerTask$ProcessingTone$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.this$1.sendEmptyMessageDelayed(1, DMServerTask.access$1500() / 2);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.tasks.DMServerTask.ProcessingTone.1
 * JD-Core Version:    0.6.0
 */