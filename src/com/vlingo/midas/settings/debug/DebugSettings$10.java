package com.vlingo.midas.settings.debug;

import android.content.Intent;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import com.vlingo.core.internal.lmtt.LMTTService;

class DebugSettings$10
  implements Preference.OnPreferenceClickListener
{
  public boolean onPreferenceClick(Preference paramPreference)
  {
    String str = paramPreference.getKey();
    Intent localIntent = new Intent(this.this$0.getApplicationContext(), LMTTService.class);
    localIntent.setAction("com.vlingo.lmtt.action.DEBUG");
    if (str.startsWith("LMTT_CONTACTS"))
    {
      localIntent.putExtra("com.vlingo.lmtt.extra.DEBUG_TYPE", 2);
      if (!str.endsWith("CLEAR_SERVERDB"))
        break label85;
      localIntent.putExtra("com.vlingo.lmtt.extra.DEBUG_ACTION", 2);
    }
    while (true)
    {
      this.this$0.startService(localIntent);
      return true;
      localIntent.putExtra("com.vlingo.lmtt.extra.DEBUG_TYPE", 1);
      break;
      label85: if (str.endsWith("CLEAR_CLIENTDB"))
      {
        localIntent.putExtra("com.vlingo.lmtt.extra.DEBUG_ACTION", 1);
        continue;
      }
      localIntent.putExtra("com.vlingo.lmtt.extra.DEBUG_ACTION", 3);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.DebugSettings.10
 * JD-Core Version:    0.6.0
 */