package com.vlingo.midas.settings.debug;

import android.preference.ListPreference;
import android.preference.Preference;
import com.vlingo.core.internal.VlingoAndroidCore;

class DebugSettings$9
  implements PreferenceUpdateListener
{
  public void onPreferenceUpdated(Preference paramPreference)
  {
    this.this$0.updateServerReponseHandling(((ListPreference)paramPreference).getValue());
    VlingoAndroidCore.updateServerResponseLogging();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.DebugSettings.9
 * JD-Core Version:    0.6.0
 */