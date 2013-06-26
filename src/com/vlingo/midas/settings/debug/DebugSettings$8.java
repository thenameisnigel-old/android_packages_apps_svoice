package com.vlingo.midas.settings.debug;

import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;

class DebugSettings$8
  implements PreferenceUpdateListener
{
  public void onPreferenceUpdated(Preference paramPreference)
  {
    DebugSettings.access$100(this.this$0).setEnabled(((CheckBoxPreference)paramPreference).isChecked());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.DebugSettings.8
 * JD-Core Version:    0.6.0
 */