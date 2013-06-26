package com.sec.android.app.IWSpeechRecognizer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class BargeInRecognizer$1 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    int i = paramMessage.getData().getInt("commandType");
    this.this$0.delayedStartBargeIn(i);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.IWSpeechRecognizer.BargeInRecognizer.1
 * JD-Core Version:    0.6.0
 */