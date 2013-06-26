package com.vlingo.core.internal.vlservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.vlingo.core.internal.recognition.service.VlingoVoiceRecognitionService;
import com.vlingo.core.internal.settings.Settings;

public class ServiceEnableBootReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool1 = Settings.getBoolean("iux_complete", false);
    boolean bool2 = Settings.getBoolean("tos_accepted", false);
    if ((bool1) && (bool2) && (paramContext != null))
    {
      ComponentName localComponentName = new ComponentName(paramContext, VlingoVoiceRecognitionService.class);
      paramContext.getPackageManager().setComponentEnabledSetting(localComponentName, 1, 1);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.vlservice.ServiceEnableBootReceiver
 * JD-Core Version:    0.6.0
 */