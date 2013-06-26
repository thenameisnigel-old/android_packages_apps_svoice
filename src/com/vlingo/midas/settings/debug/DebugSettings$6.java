package com.vlingo.midas.settings.debug;

import android.content.Intent;
import android.preference.Preference;
import com.vlingo.core.internal.settings.Settings;

class DebugSettings$6
  implements PreferenceUpdateListener
{
  public void onPreferenceUpdated(Preference paramPreference)
  {
    Intent localIntent = new Intent("com.vlingo.client.settings.LANGUAGE_CHANGED");
    localIntent.putExtra("com.vlingo.client.settings.LANGUAGE", Settings.getLanguageApplication());
    this.this$0.sendBroadcast(localIntent);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.DebugSettings.6
 * JD-Core Version:    0.6.0
 */