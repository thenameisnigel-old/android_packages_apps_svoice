package com.vlingo.midas.settings.debug;

import android.preference.EditTextPreference;
import android.preference.Preference;

class EditTextPreferenceBuilder$1
  implements PreferenceUpdateListener
{
  public void onPreferenceUpdated(Preference paramPreference)
  {
    paramPreference.setSummary(((EditTextPreference)paramPreference).getText());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.EditTextPreferenceBuilder.1
 * JD-Core Version:    0.6.0
 */