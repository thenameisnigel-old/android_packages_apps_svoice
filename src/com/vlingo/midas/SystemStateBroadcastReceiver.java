package com.vlingo.midas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.System;
import com.vlingo.core.internal.audio.AudioFocusManager;
import com.vlingo.core.internal.bluetooth.BluetoothManager;
import com.vlingo.core.internal.lmtt.LMTTService;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.midas.settings.MidasSettings;

public class SystemStateBroadcastReceiver extends BroadcastReceiver
{
  AudioFocusManager audioFocusManager = null;
  private boolean isAttached;

  private void startActivity(Context paramContext, Class<?> paramClass)
  {
    Intent localIntent = new Intent(paramContext, paramClass);
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (this.audioFocusManager == null)
      this.audioFocusManager = AudioFocusManager.getInstance(paramContext);
    String str;
    int i;
    if (paramIntent != null)
    {
      str = paramIntent.getAction();
      if (!"com.vlingo.client.app.action.APPLICATION_STATE_CHANGED".equals(str))
        break label100;
      i = paramIntent.getIntExtra("com.vlingo.client.app.extra.STATE", -1);
      if (i != 1)
        break label92;
      boolean bool1 = BluetoothManager.isHeadsetConnected();
      boolean bool2 = BluetoothManager.isBluetoothAudioSupported();
      if ((bool1 == true) && (bool2 == true))
        break label79;
      LMTTService.updateAppStartTime();
      BluetoothManager.considerRightBeforeForeground(false);
    }
    while (true)
    {
      return;
      label79: this.audioFocusManager.requestAudioFocus(6, 2);
      break;
      label92: if (i != 0)
        continue;
      continue;
      label100: if ("android.intent.action.LOCALE_CHANGED".equals(str))
      {
        if ((Settings.System.getInt(paramContext.getContentResolver(), "driving_mode_on", 1) != 1) || (ClientSuppliedValues.isTalkbackOn()))
          continue;
        new MidasADMController().notibuilder();
        continue;
      }
      if ((!"android.intent.action.VOICE_COMMAND".equals(paramIntent.getAction())) || (!MidasSettings.getBoolean("launch_voicetalk", true)))
        continue;
      Intent localIntent = new Intent(paramContext, VlingoApplication.getInstance().getMainActivityClass());
      localIntent.setFlags(268435456);
      if (!paramIntent.getBooleanExtra("isSecure", false))
        localIntent.putExtra("AUTO_LISTEN", paramIntent.getBooleanExtra("AUTO_LISTEN", true));
      localIntent.putExtra("isThisComeFromHomeKeyDoubleClickConcept", paramIntent.getBooleanExtra("isThisComeFromHomeKeyDoubleClickConcept", true));
      localIntent.putExtra("CHECK_SCHEDULE_ENABLED", paramIntent.getBooleanExtra("CHECK_SCHEDULE_ENABLED", false));
      paramContext.startActivity(localIntent);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.SystemStateBroadcastReceiver
 * JD-Core Version:    0.6.0
 */