package com.vlingo.core.internal.audio;

import android.telephony.PhoneStateListener;
import com.vlingo.core.internal.dialogmanager.DialogFlow;

public class PhoneListenerImpl extends PhoneStateListener
{
  public void onCallStateChanged(int paramInt, String paramString)
  {
    try
    {
      localDialogFlow = DialogFlow.getInstance();
      switch (paramInt)
      {
      default:
        return;
      case 1:
      case 2:
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      while (true)
      {
        DialogFlow localDialogFlow;
        continue;
        localDialogFlow.cancelTurn();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.audio.PhoneListenerImpl
 * JD-Core Version:    0.6.0
 */