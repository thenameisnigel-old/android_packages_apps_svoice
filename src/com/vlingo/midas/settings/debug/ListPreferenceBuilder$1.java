package com.vlingo.midas.settings.debug;

import android.preference.ListPreference;
import android.preference.Preference;

class ListPreferenceBuilder$1
  implements PreferenceUpdateListener
{
  public void onPreferenceUpdated(Preference paramPreference)
  {
    paramPreference.setSummary(((ListPreference)paramPreference).getValue());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.ListPreferenceBuilder.1
 * JD-Core Version:    0.6.0
 */