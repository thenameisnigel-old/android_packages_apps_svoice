package com.vlingo.midas.settings.debug;

import android.preference.Preference;

class ServerPreferenceBuilder$1
  implements PreferenceUpdateListener
{
  public void onPreferenceUpdated(Preference paramPreference)
  {
    paramPreference.setSummary(((ServerPreference)paramPreference).getValue());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.ServerPreferenceBuilder.1
 * JD-Core Version:    0.6.0
 */