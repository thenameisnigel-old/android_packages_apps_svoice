package com.vlingo.midas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.lmtt.LMTTService;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.ServerDetails;
import java.util.List;

class VlingoApplication$1
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      if (MidasSettings.SERVER_URL_KEYS.contains(paramString))
      {
        VlingoAndroidCore.updateServerInfo(ServerDetails.getInstance());
        continue;
      }
      if (paramString.startsWith("lmtt.enable."))
      {
        Intent localIntent1 = new Intent(this.this$0.getApplicationContext(), LMTTService.class);
        localIntent1.setAction("com.vlingo.lmtt.action.REINITIALIZE");
        this.this$0.startService(localIntent1);
        continue;
      }
      if ((!paramString.startsWith("lmtt.update.version")) || (paramString.endsWith(".previous")))
        continue;
      String str = paramString + ".previous";
      int i = paramSharedPreferences.getInt(str, -1);
      int j = paramSharedPreferences.getInt(paramString, -1);
      if (paramSharedPreferences.getLong("appstate.config_update_count", 0L) == 0L)
      {
        MidasSettings.setInt(str, j);
        continue;
      }
      if (i >= j)
        continue;
      MidasSettings.setInt(str, j);
      Intent localIntent2 = new Intent(this.this$0.getApplicationContext(), LMTTService.class);
      localIntent2.setAction("com.vlingo.lmtt.action.SEND_UPDATE");
      localIntent2.putExtra("com.vlingo.lmtt.extra.CLEAR_LMTT", true);
      if (paramString.length() > "lmtt.update.version".length())
        localIntent2.putExtra("com.vlingo.lmtt.extra.LMTT_TYPE", paramString.substring(1 + "lmtt.update.version".length()));
      this.this$0.startService(localIntent2);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.VlingoApplication.1
 * JD-Core Version:    0.6.0
 */