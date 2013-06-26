package com.sec.android.app.IWSpeechRecognizer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class AudioTask$1 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    String[] arrayOfString = paramMessage.getData().getStringArray("recognition_result");
    if (AudioTask.access$000(this.this$0) != null)
      AudioTask.access$000(this.this$0).onResults(arrayOfString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.IWSpeechRecognizer.AudioTask.1
 * JD-Core Version:    0.6.0
 */